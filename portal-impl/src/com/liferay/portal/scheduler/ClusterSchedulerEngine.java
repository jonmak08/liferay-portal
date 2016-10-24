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

package com.liferay.portal.scheduler;

import com.liferay.portal.cluster.ClusterableContextThreadLocal;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.cluster.BaseClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.cluster.ClusterInvokeAcceptor;
import com.liferay.portal.kernel.cluster.ClusterInvokeThreadLocal;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.servlet.PluginContextLifecycleThreadLocal;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.scheduler.quartz.QuartzTriggerHelperUtil;
import com.liferay.portal.util.PropsValues;

import java.io.Serializable;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Tina Tian
 */
public class ClusterSchedulerEngine
	implements IdentifiableBean, SchedulerEngine {

	public static SchedulerEngine createClusterSchedulerEngine(
		SchedulerEngine schedulerEngine) {

		if (PropsValues.CLUSTER_LINK_ENABLED && PropsValues.SCHEDULER_ENABLED) {
			schedulerEngine = new ClusterSchedulerEngine(schedulerEngine);
		}

		return schedulerEngine;
	}

	public ClusterSchedulerEngine(SchedulerEngine schedulerEngine) {
		_schedulerEngine = schedulerEngine;

		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		_readLock = readWriteLock.readLock();
		_writeLock = readWriteLock.writeLock();
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void delete(String groupName) throws SchedulerException {
		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				removeMemoryClusteredJobs(groupName);
			}
			else {
				_schedulerEngine.delete(groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void delete(String jobName, String groupName)
		throws SchedulerException {

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				_memoryClusteredJobs.remove(getFullName(jobName, groupName));
			}
			else {
				_schedulerEngine.delete(jobName, groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	@Clusterable(onMaster = true)
	@Override
	public SchedulerResponse getScheduledJob(String jobName, String groupName)
		throws SchedulerException {

		_readLock.lock();

		try {
			return _schedulerEngine.getScheduledJob(jobName, groupName);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Clusterable(onMaster = true)
	@Override
	public List<SchedulerResponse> getScheduledJobs()
		throws SchedulerException {

		_readLock.lock();

		try {
			return _schedulerEngine.getScheduledJobs();
		}
		finally {
			_readLock.unlock();
		}
	}

	@Clusterable(onMaster = true)
	@Override
	public List<SchedulerResponse> getScheduledJobs(String groupName)
		throws SchedulerException {

		_readLock.lock();

		try {
			return _schedulerEngine.getScheduledJobs(groupName);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void pause(String groupName) throws SchedulerException {
		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				updateMemoryClusteredJobs(groupName, TriggerState.PAUSED);
			}
			else {
				_schedulerEngine.pause(groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void pause(String jobName, String groupName)
		throws SchedulerException {

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				updateMemoryClusteredJob(
					jobName, groupName, TriggerState.PAUSED);
			}
			else {
				_schedulerEngine.pause(jobName, groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void resume(String groupName) throws SchedulerException {
		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				updateMemoryClusteredJobs(groupName, TriggerState.NORMAL);
			}
			else {
				_schedulerEngine.resume(groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void resume(String jobName, String groupName)
		throws SchedulerException {

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				updateMemoryClusteredJob(
					jobName, groupName, TriggerState.NORMAL);
			}
			else {
				_schedulerEngine.resume(jobName, groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void schedule(
			Trigger trigger, String description, String destinationName,
			Message message)
		throws SchedulerException {

		String groupName = trigger.getGroupName();
		String jobName = trigger.getJobName();

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				ObjectValuePair<SchedulerResponse, TriggerState> value =
					_memoryClusteredJobs.get(getFullName(jobName, groupName));

				if (value == null) {
					ObjectValuePair<String, StorageType> resolvedGroupName =
						resolveGroupName(groupName);

					MethodHandler methodHandler = new MethodHandler(
						_getScheduledJobMethodKey, jobName,
						resolvedGroupName.getKey(),
						resolvedGroupName.getValue());

					Future<SchedulerResponse> future =
						ClusterMasterExecutorUtil.executeOnMaster(
							methodHandler);

					SchedulerResponse schedulerResponse = future.get(
						PropsValues.CLUSTERABLE_ADVICE_CALL_MASTER_TIMEOUT,
						TimeUnit.SECONDS);

					if (schedulerResponse == null) {
						throw new NullPointerException(
							"Unable to find memory clustered job (job name : " +
								trigger.getJobName() + ", group name : " +
								trigger.getGroupName() + " on master node.");
					}

					addMemoryClusteredJob(schedulerResponse);
				}
			}
			else {
				_schedulerEngine.schedule(
					trigger, description, destinationName, message);
			}
		}
		catch (Exception e) {
			if (!(e instanceof SchedulerException)) {
				e = new SchedulerException(
					"Unable to retrieve memory clustered job from master", e);
			}

			throw (SchedulerException)e;
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public void shutdown() throws SchedulerException {
		_portalReady = false;

		ClusterMasterExecutorUtil.
			unregisterClusterMasterTokenTransitionListener(
				_schedulerClusterMasterTokenTransitionListener);

		_schedulerEngine.shutdown();
	}

	@Override
	public void start() throws SchedulerException {
		try {
			if (!ClusterMasterExecutorUtil.isMaster()) {
				initMemoryClusteredJobs();
			}

			_schedulerClusterMasterTokenTransitionListener =
				new SchedulerClusterMasterTokenTransitionListener();

			ClusterMasterExecutorUtil.
				registerClusterMasterTokenTransitionListener(
					_schedulerClusterMasterTokenTransitionListener);
		}
		catch (Exception e) {
			throw new SchedulerException("Unable to initialize scheduler", e);
		}

		_schedulerEngine.start();

		_portalReady = true;
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void suppressError(String jobName, String groupName)
		throws SchedulerException {

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		if (!memoryClusteredSlaveJob) {
			_readLock.lock();

			try {
				_schedulerEngine.suppressError(jobName, groupName);
			}
			finally {
				_readLock.unlock();
			}
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void unschedule(String groupName) throws SchedulerException {
		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				removeMemoryClusteredJobs(groupName);
			}
			else {
				_schedulerEngine.unschedule(groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void unschedule(String jobName, String groupName)
		throws SchedulerException {

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				_memoryClusteredJobs.remove(getFullName(jobName, groupName));
			}
			else {
				_schedulerEngine.unschedule(jobName, groupName);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	@Clusterable(acceptor = SchedulerClusterInvokeAcceptor.class)
	@Override
	public void update(Trigger trigger) throws SchedulerException {
		String jobName = trigger.getJobName();
		String groupName = trigger.getGroupName();

		boolean memoryClusteredSlaveJob = isMemoryClusteredSlaveJob(groupName);

		_readLock.lock();

		try {
			if (memoryClusteredSlaveJob) {
				boolean updated = false;

				for (ObjectValuePair<SchedulerResponse, TriggerState>
						memoryClusteredJob : _memoryClusteredJobs.values()) {

					SchedulerResponse schedulerResponse =
						memoryClusteredJob.getKey();

					if (jobName.equals(schedulerResponse.getJobName()) &&
						groupName.equals(schedulerResponse.getGroupName())) {

						schedulerResponse.setTrigger(trigger);

						updated = true;

						break;
					}
				}

				if (!updated) {
					throw new SchedulerException(
						"Unable to update trigger for memory clustered job");
				}
			}
			else {
				_schedulerEngine.update(trigger);
			}
		}
		finally {
			_readLock.unlock();
		}

		setClusterableThreadLocal(groupName);
	}

	protected void addMemoryClusteredJob(SchedulerResponse schedulerResponse)
		throws SchedulerException {

		Trigger oldTrigger = schedulerResponse.getTrigger();

		String jobName = schedulerResponse.getJobName();
		String groupName = SchedulerEngineHelperUtil.namespaceGroupName(
			schedulerResponse.getGroupName(), StorageType.MEMORY_CLUSTERED);

		Trigger newTrigger = TriggerFactoryUtil.buildTrigger(
			oldTrigger.getTriggerType(), jobName, groupName,
			oldTrigger.getStartDate(), oldTrigger.getEndDate(),
			oldTrigger.getTriggerContent());

		schedulerResponse.setTrigger(newTrigger);

		TriggerState triggerState = SchedulerEngineHelperUtil.getJobState(
			schedulerResponse);

		Message message = schedulerResponse.getMessage();

		message.remove(JOB_STATE);

		_memoryClusteredJobs.put(
			getFullName(jobName, groupName),
			new ObjectValuePair<SchedulerResponse, TriggerState>(
				schedulerResponse, triggerState));
	}

	protected String getFullName(String jobName, String groupName) {
		return groupName.concat(StringPool.PERIOD).concat(jobName);
	}

	protected void initMemoryClusteredJobs() throws Exception {
		MethodHandler methodHandler = new MethodHandler(
			_getScheduledJobsMethodKey, StorageType.MEMORY_CLUSTERED);

		Future<List<SchedulerResponse>> future =
			ClusterMasterExecutorUtil.executeOnMaster(methodHandler);

		List<SchedulerResponse> schedulerResponses = future.get(
			PropsValues.CLUSTERABLE_ADVICE_CALL_MASTER_TIMEOUT,
			TimeUnit.SECONDS);

		for (SchedulerResponse schedulerResponse : schedulerResponses) {
			addMemoryClusteredJob(schedulerResponse);
		}
	}

	protected boolean isMemoryClusteredSlaveJob(String groupName)
		throws SchedulerException {

		ObjectValuePair<String, StorageType> objectValuePair = resolveGroupName(
			groupName);

		StorageType storageType = objectValuePair.getValue();

		if ((storageType != StorageType.MEMORY_CLUSTERED) ||
			ClusterMasterExecutorUtil.isMaster()) {

			return false;
		}

		return true;
	}

	protected void removeMemoryClusteredJobs(String groupName) {
		Set<Map.Entry<String, ObjectValuePair<SchedulerResponse, TriggerState>>>
			memoryClusteredJobs = _memoryClusteredJobs.entrySet();

		Iterator
			<Map.Entry<String,
				ObjectValuePair<SchedulerResponse, TriggerState>>> itr =
					memoryClusteredJobs.iterator();

		while (itr.hasNext()) {
			Map.Entry <String, ObjectValuePair<SchedulerResponse, TriggerState>>
				entry = itr.next();

			ObjectValuePair<SchedulerResponse, TriggerState>
				memoryClusteredJob = entry.getValue();

			SchedulerResponse schedulerResponse = memoryClusteredJob.getKey();

			if (groupName.equals(schedulerResponse.getGroupName())) {
				itr.remove();
			}
		}
	}

	protected ObjectValuePair<String, StorageType> resolveGroupName(
		String groupName) {

		int index = groupName.indexOf(CharPool.POUND);

		String storageTypeString = groupName.substring(0, index);

		StorageType storageType = StorageType.valueOf(storageTypeString);

		String orginalGroupName = groupName.substring(index + 1);

		return new ObjectValuePair<String, StorageType>(
			orginalGroupName, storageType);
	}

	protected void setClusterableThreadLocal(String groupName) {
		ObjectValuePair<String, StorageType> objectValuePair = resolveGroupName(
			groupName);

		ClusterableContextThreadLocal.putThreadLocalContext(
			STORAGE_TYPE, objectValuePair.getValue());
		ClusterableContextThreadLocal.putThreadLocalContext(
			_PORTAL_READY, _portalReady);

		boolean pluginReady = true;

		if (PluginContextLifecycleThreadLocal.isInitializing() ||
			PluginContextLifecycleThreadLocal.isDestroying()) {

			pluginReady = false;
		}

		ClusterableContextThreadLocal.putThreadLocalContext(
			_PLUGIN_READY, pluginReady);
	}

	protected void updateMemoryClusteredJob(
		String jobName, String groupName, TriggerState triggerState) {

		ObjectValuePair<SchedulerResponse, TriggerState>
			memoryClusteredJob = _memoryClusteredJobs.get(
				getFullName(jobName, groupName));

		if (memoryClusteredJob != null) {
			memoryClusteredJob.setValue(triggerState);
		}
	}

	protected void updateMemoryClusteredJobs(
		String groupName, TriggerState triggerState) {

		for (ObjectValuePair<SchedulerResponse, TriggerState>
				memoryClusteredJob : _memoryClusteredJobs.values()) {

			SchedulerResponse schedulerResponse = memoryClusteredJob.getKey();

			if (groupName.equals(schedulerResponse.getGroupName())) {
				memoryClusteredJob.setValue(triggerState);
			}
		}
	}

	@BeanReference(
		name = "com.liferay.portal.scheduler.ClusterSchedulerEngineService")
	protected SchedulerEngine schedulerEngine;

	private static final String _PLUGIN_READY = "plugin.ready";

	private static final String _PORTAL_READY = "portal.ready";

	private static Log _log = LogFactoryUtil.getLog(
		ClusterSchedulerEngine.class);

	private static final MethodKey _getScheduledJobMethodKey = new MethodKey(
			SchedulerEngineHelperUtil.class, "getScheduledJob", String.class,
			String.class, StorageType.class);

	private static final MethodKey _getScheduledJobsMethodKey = new MethodKey(
		SchedulerEngineHelperUtil.class, "getScheduledJobs", StorageType.class);

	private String _beanIdentifier;
	private final Map<String, ObjectValuePair<SchedulerResponse, TriggerState>>
		_memoryClusteredJobs = new ConcurrentHashMap
			<String, ObjectValuePair<SchedulerResponse, TriggerState>>();
	private boolean _portalReady;
	private final java.util.concurrent.locks.Lock _readLock;
	private ClusterMasterTokenTransitionListener
		_schedulerClusterMasterTokenTransitionListener;
	private final SchedulerEngine _schedulerEngine;
	private final java.util.concurrent.locks.Lock _writeLock;

	private static class SchedulerClusterInvokeAcceptor
		implements ClusterInvokeAcceptor {

		@Override
		public boolean accept(Map<String, Serializable> context) {
			if (ClusterInvokeThreadLocal.isEnabled()) {
				return false;
			}

			StorageType storageType = (StorageType)context.get(STORAGE_TYPE);
			boolean portalReady = (Boolean)context.get(_PORTAL_READY);
			boolean pluginReady = (Boolean)context.get(_PLUGIN_READY);

			if (storageType.equals(StorageType.PERSISTED) || !portalReady ||
				!pluginReady) {

				return false;
			}

			return true;
		}

	}

	private class SchedulerClusterMasterTokenTransitionListener
		extends BaseClusterMasterTokenTransitionListener {

		@Override
		protected void doMasterTokenAcquired() throws Exception {
			boolean forceSync = ProxyModeThreadLocal.isForceSync();

			ProxyModeThreadLocal.setForceSync(true);

			_writeLock.lock();

			try {
				for (ObjectValuePair<SchedulerResponse, TriggerState>
						memoryClusteredJob : _memoryClusteredJobs.values()) {

					SchedulerResponse schedulerResponse =
						memoryClusteredJob.getKey();

					Trigger oldTrigger = schedulerResponse.getTrigger();

					Date startDate = QuartzTriggerHelperUtil.getFireTimeAfter(
						oldTrigger, new Date());

					Trigger newTrigger = TriggerFactoryUtil.buildTrigger(
						oldTrigger.getTriggerType(), oldTrigger.getJobName(),
						oldTrigger.getGroupName(), startDate,
						oldTrigger.getEndDate(),
						oldTrigger.getTriggerContent());

					_schedulerEngine.schedule(
						newTrigger, schedulerResponse.getDescription(),
						schedulerResponse.getDestinationName(),
						schedulerResponse.getMessage());

					TriggerState triggerState = memoryClusteredJob.getValue();

					if (triggerState.equals(TriggerState.PAUSED)) {
						_schedulerEngine.pause(
							schedulerResponse.getJobName(),
							schedulerResponse.getGroupName());
					}
				}

				_memoryClusteredJobs.clear();

				if (_log.isInfoEnabled()) {
					_log.info("MEMORY_CLUSTERED jobs are running on this node");
				}
			}
			finally {
				ProxyModeThreadLocal.setForceSync(forceSync);

				_writeLock.unlock();
			}
		}

		@Override
		protected void doMasterTokenReleased() throws Exception {
			_writeLock.lock();

			try {
				for (SchedulerResponse schedulerResponse :
						_schedulerEngine.getScheduledJobs()) {

					if (StorageType.MEMORY_CLUSTERED ==
							schedulerResponse.getStorageType()) {

						String groupName =
							SchedulerEngineHelperUtil.namespaceGroupName(
								schedulerResponse.getGroupName(),
								StorageType.MEMORY_CLUSTERED);

						_schedulerEngine.delete(
							schedulerResponse.getJobName(), groupName);
					}
				}

				initMemoryClusteredJobs();

				if (_log.isInfoEnabled()) {
					_log.info(
						"MEMORY_CLUSTERED jobs stop running on this node");
				}
			}
			finally {
				_writeLock.unlock();
			}
		}

	}

}