/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterEventType;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Michael C. Han
 */
public class ClusterMasterExecutorImpl implements ClusterMasterExecutor {

	public void destroy() {
		if (!_enabled) {
			return;
		}

		_clusterExecutor.removeClusterEventListener(_clusterEventListener);
	}

	@Override
	public <T> Future<T> executeOnMaster(MethodHandler methodHandler)
		throws SystemException {

		if (!_enabled) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Executing on the local node because the cluster master " +
						"executor is disabled");
			}

			try {
				return new LocalFuture<T>((T)methodHandler.invoke(true));
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}

		Address address = getMasterAddress();

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			methodHandler, address);

		try {
			FutureClusterResponses futureClusterResponses =
				_clusterExecutor.execute(clusterRequest);

			return new RemoteFuture<T>(address, futureClusterResponses);
		}
		catch (Exception e) {
			throw new SystemException(
				"Unable to execute on master " + address.getDescription(), e);
		}
	}

	@Override
	public void initialize() {
		if (!_clusterExecutor.isEnabled()) {
			return;
		}

		try {
			_localClusterNodeAddress =
				_clusterExecutor.getLocalClusterNodeAddress();

			_clusterEventListener =
				new ClusterMasterTokenClusterEventListener();

			_clusterExecutor.addClusterEventListener(_clusterEventListener);

			Address masterAddress = getMasterAddress();

			_enabled = true;

			notifyMasterTokenTransitionListeners(
				_localClusterNodeAddress.equals(masterAddress));
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unable to initialize cluster master executor", e);
		}
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public boolean isMaster() {
		if (isEnabled()) {
			return _master;
		}

		return true;
	}

	@Override
	public void registerClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {

		_clusterMasterTokenTransitionListeners.add(
			clusterMasterTokenTransitionListener);
	}

	public void setClusterExecutor(ClusterExecutor clusterExecutor) {
		_clusterExecutor = clusterExecutor;
	}

	public void setClusterMasterTokenTransitionListeners(
		Set<ClusterMasterTokenTransitionListener>
			clusterMasterTokenTransitionListeners) {

		_clusterMasterTokenTransitionListeners.addAll(
			clusterMasterTokenTransitionListeners);
	}

	@Override
	public void unregisterClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {

		_clusterMasterTokenTransitionListeners.remove(
			clusterMasterTokenTransitionListener);
	}

	protected Address getMasterAddress() {
		Address coordinator = _clusterExecutor.getCoordinatorAddress();

		boolean master = _localClusterNodeAddress.equals(coordinator);

		if (master != _master) {
			_master = master;

			if (_enabled) {
				notifyMasterTokenTransitionListeners(master);
			}
		}

		return coordinator;
	}

	protected void notifyMasterTokenTransitionListeners(
		boolean masterTokenAcquired) {

		for (ClusterMasterTokenTransitionListener
				clusterMasterTokenTransitionListener :
					_clusterMasterTokenTransitionListeners) {

			if (masterTokenAcquired) {
				clusterMasterTokenTransitionListener.masterTokenAcquired();
			}
			else {
				clusterMasterTokenTransitionListener.masterTokenReleased();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClusterMasterExecutorImpl.class);

	private static volatile boolean _master;

	private ClusterEventListener _clusterEventListener;
	private ClusterExecutor _clusterExecutor;
	private Set<ClusterMasterTokenTransitionListener>
		_clusterMasterTokenTransitionListeners =
		new HashSet<ClusterMasterTokenTransitionListener>();
	private volatile boolean _enabled;
	private volatile Address _localClusterNodeAddress;

	private class ClusterMasterTokenClusterEventListener
		implements ClusterEventListener {

		@Override
		public void processClusterEvent(ClusterEvent clusterEvent) {
			ClusterEventType clusterEventType =
				clusterEvent.getClusterEventType();

			if (clusterEventType.equals(ClusterEventType.COORDINATOR_UPDATE)) {
				getMasterAddress();
			}
		}
	}

	private class LocalFuture<T> implements Future<T> {

		public LocalFuture(T result) {
			_result = result;
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return false;
		}

		@Override
		public boolean isCancelled() {
			return false;
		}

		@Override
		public boolean isDone() {
			return true;
		}

		@Override
		public T get() {
			return _result;
		}

		@Override
		public T get(long timeout, TimeUnit unit) {
			return _result;
		}

		private final T _result;

	}

	private class RemoteFuture<T> implements Future<T> {

		public RemoteFuture(
			Address address, FutureClusterResponses futureClusterResponses) {

			_address = address;
			_futureClusterResponses = futureClusterResponses;
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return _futureClusterResponses.cancel(mayInterruptIfRunning);
		}

		@Override
		public boolean isCancelled() {
			return _futureClusterResponses.isCancelled();
		}

		@Override
		public boolean isDone() {
			return _futureClusterResponses.isDone();
		}

		@Override
		public T get() throws ExecutionException, InterruptedException {
			ClusterNodeResponses clusterNodeResponses =
				_futureClusterResponses.get();

			ClusterNodeResponse clusterNodeResponse =
				clusterNodeResponses.getClusterResponse(_address);

			try {
				return (T)clusterNodeResponse.getResult();
			}
			catch (Exception e) {
				throw new ExecutionException(e);
			}
		}

		@Override
		public T get(long timeout, TimeUnit unit)
			throws ExecutionException, InterruptedException, TimeoutException {

			ClusterNodeResponses clusterNodeResponses =
				_futureClusterResponses.get(timeout, unit);

			ClusterNodeResponse clusterNodeResponse =
				clusterNodeResponses.getClusterResponse(_address);

			try {
				return (T)clusterNodeResponse.getResult();
			}
			catch (Exception e) {
				throw new ExecutionException(e);
			}
		}

		private final Address _address;
		private final FutureClusterResponses _futureClusterResponses;

	}

}