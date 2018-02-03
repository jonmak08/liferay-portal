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

package com.liferay.portal.cluster.multiple.internal;

<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.cluster.multiple.configuration.ClusterExecutorConfiguration;
=======
import com.liferay.portal.cluster.multiple.configuration.ClusterExecutorConfiguration;
import com.liferay.portal.cluster.multiple.internal.constants.ClusterPropsKeys;
>>>>>>> compatible
import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterInvokeThreadLocal;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.test.rule.AspectJNewEnvTestRule;

import java.io.Serializable;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.Dictionary;
import java.util.List;
>>>>>>> compatible
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tina Tian
 */
@NewEnv(type = NewEnv.Type.CLASSLOADER)
public class ClusterExecutorImplTest extends BaseClusterTestCase {

	@Test
	public void testClusterEventListener() {

		// Test 1, add cluster event listener

<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(true);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl();
>>>>>>> compatible

		List<ClusterEventListener> clusterEventListeners =
			clusterExecutorImpl.getClusterEventListeners();

		Assert.assertEquals(
			clusterEventListeners.toString(), 0, clusterEventListeners.size());

		ClusterEventListener clusterEventListener = new ClusterEventListener() {

			@Override
			public void processClusterEvent(ClusterEvent clusterEvent) {
			}

		};

		clusterExecutorImpl.addClusterEventListener(clusterEventListener);

		clusterEventListeners = clusterExecutorImpl.getClusterEventListeners();

		Assert.assertEquals(
			clusterEventListeners.toString(), 1, clusterEventListeners.size());

		// Test 2, remove cluster event listener

		clusterExecutorImpl.removeClusterEventListener(clusterEventListener);

		clusterEventListeners = clusterExecutorImpl.getClusterEventListeners();

		Assert.assertEquals(
			clusterEventListeners.toString(), 0, clusterEventListeners.size());

		// Test 3, set cluster event listener

		clusterEventListeners = new ArrayList<>();

		clusterEventListeners.add(clusterEventListener);

		clusterExecutorImpl.setClusterEventListeners(clusterEventListeners);

		clusterEventListeners = clusterExecutorImpl.getClusterEventListeners();

		Assert.assertEquals(
			clusterEventListeners.toString(), 1, clusterEventListeners.size());
	}

	@Test
	public void testDeactivate() {
<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(true);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(
			true, true);
>>>>>>> compatible

		List<TestClusterChannel> clusterChannels =
			TestClusterChannel.getClusterChannels();

		Assert.assertEquals(
			clusterChannels.toString(), 1, clusterChannels.size());

		TestClusterChannel clusterChannel = clusterChannels.get(0);

		ExecutorService executorService =
			clusterExecutorImpl.getExecutorService();

		Assert.assertFalse(executorService.isShutdown());

		Assert.assertFalse(clusterChannel.isClosed());

		clusterExecutorImpl.deactivate();

		Assert.assertTrue(clusterChannel.isClosed());
		Assert.assertTrue(executorService.isShutdown());
	}

	@Test
	public void testDebugClusterEventListener() {
<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(true);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl();
>>>>>>> compatible

		clusterExecutorImpl.clusterExecutorConfiguration =
			new ClusterExecutorConfiguration() {

				@Override
				public boolean debugEnabled() {
					return true;
				}

			};

		clusterExecutorImpl.manageDebugClusterEventListener();

		List<ClusterEventListener> clusterEventListeners =
			clusterExecutorImpl.getClusterEventListeners();

		Assert.assertEquals(
			clusterEventListeners.toString(), 1, clusterEventListeners.size());

		ClusterEventListener clusterEventListener = clusterEventListeners.get(
			0);

		Class<?> clusterEventListenerClass = clusterEventListener.getClass();

		Assert.assertEquals(
			DebuggingClusterEventListenerImpl.class.getName(),
			clusterEventListenerClass.getName());
	}

	@Test
	public void testDisabledClusterLink() {

		// Test 1, initialize

<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(false);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(
			true, false);
>>>>>>> compatible

		List<TestClusterChannel> clusterChannels =
			TestClusterChannel.getClusterChannels();

<<<<<<< HEAD
		Assert.assertTrue(
			clusterChannels.toString(), clusterChannels.isEmpty());
=======
		Assert.assertTrue(clusterChannels.isEmpty());
>>>>>>> compatible

		Assert.assertNull(clusterExecutorImpl.getExecutorService());

		// Test 2, send unitcast message

		List<Serializable> multicastMessages =
			TestClusterChannel.getMulticastMessages();
		List<ObjectValuePair<Serializable, Address>> unicastMessages =
			TestClusterChannel.getUnicastMessages();

		clusterExecutorImpl.execute(
			ClusterRequest.createUnicastRequest(
				StringPool.BLANK, StringPool.BLANK));

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		// Test 3, send multicast message

		clusterExecutorImpl.execute(
			ClusterRequest.createMulticastRequest(StringPool.BLANK));

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		// Test 4, destroy

		clusterExecutorImpl.deactivate();
	}

	@Test
	public void testExecute() throws Exception {

		// Test 1, execute multicast request and not skip local

<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(true);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl();
>>>>>>> compatible

		TestClusterChannel.clearAllMessages();

		List<Serializable> multicastMessages =
			TestClusterChannel.getMulticastMessages();
		List<ObjectValuePair<Serializable, Address>> unicastMessages =
			TestClusterChannel.getUnicastMessages();

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			StringPool.BLANK);

		FutureClusterResponses futureClusterResponses =
			clusterExecutorImpl.execute(clusterRequest);

		Assert.assertEquals(
			multicastMessages.toString(), 1, multicastMessages.size());
<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(),
			multicastMessages.contains(clusterRequest));
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.contains(clusterRequest));
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		ClusterNodeResponses clusterNodeResponses =
			futureClusterResponses.get();

		Assert.assertEquals(1, clusterNodeResponses.size());

		// Test 2, execute multicast request and skip local

		TestClusterChannel.clearAllMessages();

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		clusterRequest = ClusterRequest.createMulticastRequest(
			StringPool.BLANK, true);

		futureClusterResponses = clusterExecutorImpl.execute(clusterRequest);

		Assert.assertEquals(
			multicastMessages.toString(), 1, multicastMessages.size());
<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(),
			multicastMessages.contains(clusterRequest));
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.contains(clusterRequest));
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		clusterNodeResponses = futureClusterResponses.get();

		Assert.assertEquals(0, clusterNodeResponses.size());

		// Test 3, execute unicast request to local address

		TestClusterChannel.clearAllMessages();

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		ClusterNode localClusterNode =
			clusterExecutorImpl.getLocalClusterNode();

		clusterRequest = ClusterRequest.createUnicastRequest(
			clusterRequest, localClusterNode.getClusterNodeId());

		futureClusterResponses = clusterExecutorImpl.execute(clusterRequest);

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		clusterNodeResponses = futureClusterResponses.get();

		Assert.assertEquals(1, clusterNodeResponses.size());

		// Test 4, execute unicast request to other address

		TestClusterChannel.clearAllMessages();

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());

		ClusterExecutorImpl newClusterExecutorImpl = getClusterExecutorImpl(
			true);

		Assert.assertEquals(
			multicastMessages.toString(), 1, multicastMessages.size());
		Assert.assertTrue(
			unicastMessages.toString(), unicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
		Assert.assertTrue(unicastMessages.isEmpty());

		ClusterExecutorImpl newClusterExecutorImpl = getClusterExecutorImpl();

		Assert.assertEquals(
			multicastMessages.toString(), 1, multicastMessages.size());
		Assert.assertTrue(unicastMessages.isEmpty());
>>>>>>> compatible

		Serializable serializable = multicastMessages.get(0);

		clusterExecutorImpl.handleReceivedClusterRequest(
			(ClusterRequest)serializable);

		TestClusterChannel.clearAllMessages();

		ClusterNode newClusterNode =
			newClusterExecutorImpl.getLocalClusterNode();

		clusterRequest = ClusterRequest.createUnicastRequest(
			StringPool.BLANK, newClusterNode.getClusterNodeId());

		clusterExecutorImpl.execute(clusterRequest);

<<<<<<< HEAD
		Assert.assertTrue(
			multicastMessages.toString(), multicastMessages.isEmpty());
=======
		Assert.assertTrue(multicastMessages.isEmpty());
>>>>>>> compatible
		Assert.assertEquals(
			unicastMessages.toString(), 1, unicastMessages.size());

		ObjectValuePair<Serializable, Address> receivedMessage =
			unicastMessages.get(0);

		Assert.assertEquals(clusterRequest, receivedMessage.getKey());
	}

	@Test
	public void testExecuteClusterRequest() throws Exception {
<<<<<<< HEAD
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl(true);
=======
		ClusterExecutorImpl clusterExecutorImpl = getClusterExecutorImpl();
>>>>>>> compatible

		// Test 1, payload is not method handler

		ClusterNodeResponse clusterNodeResponse =
			clusterExecutorImpl.executeClusterRequest(
				ClusterRequest.createMulticastRequest(StringPool.BLANK));

		Exception exception = clusterNodeResponse.getException();

		Assert.assertEquals(
			"Payload is not of type " + MethodHandler.class.getName(),
			exception.getMessage());

		// Test 2, invoke with exception

		String timestamp = String.valueOf(System.currentTimeMillis());

		clusterNodeResponse = clusterExecutorImpl.executeClusterRequest(
			ClusterRequest.createMulticastRequest(
				new MethodHandler(
					new MethodKey(TestBean.class, "testMethod3", String.class),
					timestamp)));

		try {
			clusterNodeResponse.getResult();

			Assert.fail();
		}
		catch (Exception e) {
			Throwable throwable = e.getCause();

			Assert.assertEquals(timestamp, throwable.getMessage());
		}

		// Test 3, invoke without exception

		timestamp = String.valueOf(System.currentTimeMillis());

		clusterNodeResponse = clusterExecutorImpl.executeClusterRequest(
			ClusterRequest.createMulticastRequest(
				new MethodHandler(
					new MethodKey(TestBean.class, "testMethod1", String.class),
					timestamp)));

		Assert.assertEquals(timestamp, clusterNodeResponse.getResult());

		// Test 4, thread local

		Assert.assertTrue(ClusterInvokeThreadLocal.isEnabled());

		clusterNodeResponse = clusterExecutorImpl.executeClusterRequest(
			ClusterRequest.createMulticastRequest(
				new MethodHandler(
					new MethodKey(TestBean.class, "testMethod5"))));

		Assert.assertFalse((Boolean)clusterNodeResponse.getResult());

		Assert.assertTrue(ClusterInvokeThreadLocal.isEnabled());
	}

	@Rule
	public final AspectJNewEnvTestRule aspectJNewEnvTestRule =
		AspectJNewEnvTestRule.INSTANCE;

<<<<<<< HEAD
	protected ClusterExecutorImpl getClusterExecutorImpl(boolean enabled) {
		ClusterExecutorImpl clusterExecutorImpl = new ClusterExecutorImpl();

		Map<String, String> properties = new HashMap<>();

		properties.put(
			PropsKeys.CLUSTER_LINK_CHANNEL_NAME_CONTROL,
			"test-channel-name-control");
		properties.put(
			PropsKeys.CLUSTER_LINK_CHANNEL_PROPERTIES_CONTROL,
			"test-channel-properties-control");
		properties.put(PropsKeys.CLUSTER_LINK_ENABLED, String.valueOf(enabled));
=======
	protected ClusterExecutorImpl getClusterExecutorImpl() {
		return getClusterExecutorImpl(true, true);
	}

	protected ClusterExecutorImpl getClusterExecutorImpl(
		final boolean debugEnabled, final boolean enabled) {

		ClusterExecutorImpl clusterExecutorImpl = new ClusterExecutorImpl();
>>>>>>> compatible

		clusterExecutorImpl.setProps(
			new Props() {

				@Override
				public boolean contains(String key) {
					return false;
				}

				@Override
				public String get(String key) {
<<<<<<< HEAD
					return properties.getOrDefault(key, StringPool.BLANK);
=======
					if (PropsKeys.CLUSTER_LINK_ENABLED.equals(key)) {
						return String.valueOf(enabled);
					}

					return StringPool.BLANK;
>>>>>>> compatible
				}

				@Override
				public String get(String key, Filter filter) {
					return null;
				}

				@Override
				public String[] getArray(String key) {
					return null;
				}

				@Override
				public String[] getArray(String key, Filter filter) {
					return null;
				}

				@Override
				public Properties getProperties() {
					return null;
				}

				@Override
				public Properties getProperties(
					String prefix, boolean removePrefix) {

					return null;
				}

			});

		clusterExecutorImpl.setClusterChannelFactory(
			new TestClusterChannelFactory());

		clusterExecutorImpl.setPortalExecutorManager(
			new MockPortalExecutorManager());

<<<<<<< HEAD
		clusterExecutorImpl.activate(
			new MockComponentContext(new HashMapDictionary<>()));
=======
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			ClusterPropsKeys.CHANNEL_NAME_CONTROL, "test-channel-name-control");
		properties.put(
			ClusterPropsKeys.CHANNEL_PROPERTIES_CONTROL,
			"test-channel-properties-control");

		clusterExecutorImpl.activate(new MockComponentContext(properties));
>>>>>>> compatible

		return clusterExecutorImpl;
	}

}