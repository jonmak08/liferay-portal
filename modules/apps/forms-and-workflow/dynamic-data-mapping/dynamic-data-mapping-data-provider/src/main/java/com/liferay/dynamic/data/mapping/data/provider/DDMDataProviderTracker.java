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

package com.liferay.dynamic.data.mapping.data.provider;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = DDMDataProviderTracker.class)
public class DDMDataProviderTracker {

	public DDMDataProvider getDDMDataProvider(String type) {
<<<<<<< HEAD
		return _ddmDataProviderTypeTrackerMap.getService(type);
	}

	public DDMDataProvider getDDMDataProviderByInstanceId(String instanceId) {
		return _ddmDataProviderInstanceIdTrackerMap.getService(instanceId);
=======
		return _ddmDataProviderTrackerMap.getService(type);
>>>>>>> compatible
	}

	public List<DDMDataProviderContextContributor>
		getDDMDataProviderContextContributors(String type) {

		List<DDMDataProviderContextContributor>
			ddmDataProviderContextContributors =
				_ddmDataProviderContextContributorTrackerMap.getService(type);

		if (ddmDataProviderContextContributors != null) {
			return ddmDataProviderContextContributors;
		}

		return Collections.emptyList();
	}

	public Set<String> getDDMDataProviderTypes() {
<<<<<<< HEAD
		return _ddmDataProviderTypeTrackerMap.keySet();
=======
		return _ddmDataProviderTrackerMap.keySet();
>>>>>>> compatible
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_ddmDataProviderContextContributorTrackerMap =
			ServiceTrackerMapFactory.multiValueMap(
				bundleContext, DDMDataProviderContextContributor.class,
				"ddm.data.provider.type");

		_ddmDataProviderContextContributorTrackerMap.open();

<<<<<<< HEAD
		_ddmDataProviderInstanceIdTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				bundleContext, DDMDataProvider.class,
				"ddm.data.provider.instance.id");

		_ddmDataProviderInstanceIdTrackerMap.open();

		_ddmDataProviderTypeTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				bundleContext, DDMDataProvider.class, "ddm.data.provider.type");

		_ddmDataProviderTypeTrackerMap.open();
=======
		_ddmDataProviderTrackerMap = ServiceTrackerMapFactory.singleValueMap(
			bundleContext, DDMDataProvider.class, "ddm.data.provider.type");

		_ddmDataProviderTrackerMap.open();
>>>>>>> compatible
	}

	@Deactivate
	protected void deactivate() {
		_ddmDataProviderContextContributorTrackerMap.close();

<<<<<<< HEAD
		_ddmDataProviderInstanceIdTrackerMap.close();

		_ddmDataProviderTypeTrackerMap.close();
=======
		_ddmDataProviderTrackerMap.close();
>>>>>>> compatible
	}

	private ServiceTrackerMap<String, List<DDMDataProviderContextContributor>>
		_ddmDataProviderContextContributorTrackerMap;
	private ServiceTrackerMap<String, DDMDataProvider>
<<<<<<< HEAD
		_ddmDataProviderInstanceIdTrackerMap;
	private ServiceTrackerMap<String, DDMDataProvider>
		_ddmDataProviderTypeTrackerMap;
=======
		_ddmDataProviderTrackerMap;
>>>>>>> compatible

}