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

package com.liferay.portal.messaging.internal.jmx;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"jmx.objectname=com.liferay.portal.messaging:classification=message_bus,name=MessageBusManager",
		"jmx.objectname.cache.key=MessageBusManager"
	},
	service = DynamicMBean.class
)
public class MessageBusManager
	extends StandardMBean implements MessageBusManagerMBean {

	public MessageBusManager() throws NotCompliantMBeanException {
		super(MessageBusManagerMBean.class);
	}

	@Override
	public int getDestinationCount() {
		return _messageBus.getDestinationCount();
	}

	@Override
	public int getMessageListenerCount(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);

		if (destination == null) {
			return 0;
		}

		return destination.getMessageListenerCount();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(destination.name=*)"
	)
	protected synchronized void addDestination(Destination destination) {
		ServiceRegistration<DynamicMBean> serviceRegistration =
			_mbeanServiceRegistrations.remove(destination.getName());

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}

		try {
			DestinationStatisticsManager destinationStatisticsManager =
				new DestinationStatisticsManager(destination);

			Dictionary<String, Object> mBeanProperties =
				new HashMapDictionary<>();

			mBeanProperties.put(
				"jmx.objectname", destinationStatisticsManager.getObjectName());
			mBeanProperties.put(
				"jmx.objectname.cache.key",
				destinationStatisticsManager.getObjectNameCacheKey());

			serviceRegistration = _bundleContext.registerService(
				DynamicMBean.class, destinationStatisticsManager,
				mBeanProperties);

			_mbeanServiceRegistrations.put(
				destination.getName(), serviceRegistration);
		}
		catch (NotCompliantMBeanException e) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to register destination mbean", e);
			}
		}
	}

	@Deactivate
	protected void deactivate() {
		for (ServiceRegistration<DynamicMBean> serviceRegistration :
				_mbeanServiceRegistrations.values()) {

			serviceRegistration.unregister();
		}

		_mbeanServiceRegistrations.clear();
	}

	protected synchronized void removeDestination(Destination destination) {
		ServiceRegistration<DynamicMBean> mbeanServiceRegistration =
			_mbeanServiceRegistrations.get(destination.getName());

		if (mbeanServiceRegistration != null) {
			mbeanServiceRegistration.unregister();
		}
	}

	@Reference(unbind = "-")
	protected void setMBeanServer(MBeanServer mBeanServer) {
	}

	@Reference(unbind = "-")
	protected void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageBusManager.class);

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<DynamicMBean>>
		_mbeanServiceRegistrations = new HashMap<>();
	private MessageBus _messageBus;

}