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

package com.liferay.portal.scheduler.single.internal;

import com.liferay.portal.kernel.scheduler.SchedulerEngine;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
=======
import com.liferay.portal.scheduler.BaseSchedulerEngineConfigurator;
>>>>>>> compatible

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
<<<<<<< HEAD
import org.osgi.service.component.annotations.Reference;
=======
>>>>>>> compatible

/**
 * @author Tina Tian
 */
@Component(
	enabled = false, immediate = true,
	service = SingleSchedulerEngineConfigurator.class
)
<<<<<<< HEAD
public class SingleSchedulerEngineConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		Dictionary<String, Object> schedulerEngineDictionary =
			new HashMapDictionary<>();

		schedulerEngineDictionary.put("scheduler.engine.proxy", Boolean.TRUE);

		_schedulerEngineServiceRegistration = bundleContext.registerService(
			SchedulerEngine.class, _schedulerEngine, schedulerEngineDictionary);
=======
public class SingleSchedulerEngineConfigurator
	extends BaseSchedulerEngineConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		SchedulerEngine schedulerEngine = createSchedulerEngineProxy();

		_schedulerEngineServiceRegistration = registerSchedulerEngine(
			bundleContext, schedulerEngine);
>>>>>>> compatible
	}

	@Deactivate
	protected void deactivate() {
		if (_schedulerEngineServiceRegistration != null) {
			_schedulerEngineServiceRegistration.unregister();
		}
	}

<<<<<<< HEAD
	@Reference(target = "(scheduler.engine.proxy.bean=true)", unbind = "-")
	protected void setSchedulerEngine(SchedulerEngine schedulerEngine) {
		_schedulerEngine = schedulerEngine;
	}

	private SchedulerEngine _schedulerEngine;
=======
>>>>>>> compatible
	private volatile ServiceRegistration<SchedulerEngine>
		_schedulerEngineServiceRegistration;

}