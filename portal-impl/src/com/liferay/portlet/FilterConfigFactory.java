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

package com.liferay.portlet;

import com.liferay.portal.kernel.model.PortletApp;
<<<<<<< HEAD
import com.liferay.portal.kernel.model.PortletFilter;
=======
>>>>>>> compatible

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class FilterConfigFactory {

	public static FilterConfig create(
<<<<<<< HEAD
		PortletFilter portletFilter, PortletContext ctx) {
=======
		com.liferay.portal.kernel.model.PortletFilter portletFilter,
		PortletContext ctx) {
>>>>>>> compatible

		return _instance._create(portletFilter, ctx);
	}

<<<<<<< HEAD
	public static void destroy(PortletFilter portletFilter) {
=======
	public static void destroy(
		com.liferay.portal.kernel.model.PortletFilter portletFilter) {

>>>>>>> compatible
		_instance._destroy(portletFilter);
	}

	private FilterConfigFactory() {
		_pool = new ConcurrentHashMap<>();
	}

	private FilterConfig _create(
<<<<<<< HEAD
		PortletFilter portletFilter, PortletContext ctx) {
=======
		com.liferay.portal.kernel.model.PortletFilter portletFilter,
		PortletContext ctx) {
>>>>>>> compatible

		PortletApp portletApp = portletFilter.getPortletApp();

		Map<String, FilterConfig> filterConfigs = _pool.get(
			portletApp.getServletContextName());

		if (filterConfigs == null) {
			filterConfigs = new ConcurrentHashMap<>();

			_pool.put(portletApp.getServletContextName(), filterConfigs);
		}

		FilterConfig filterConfig = filterConfigs.get(
			portletFilter.getFilterName());

		if (filterConfig == null) {
			filterConfig = new FilterConfigImpl(
				portletFilter.getFilterName(), ctx,
				portletFilter.getInitParams());

			filterConfigs.put(portletFilter.getFilterName(), filterConfig);
		}

		return filterConfig;
	}

<<<<<<< HEAD
	private void _destroy(PortletFilter portletFilter) {
=======
	private void _destroy(
		com.liferay.portal.kernel.model.PortletFilter portletFilter) {

>>>>>>> compatible
		PortletApp portletApp = portletFilter.getPortletApp();

		_pool.remove(portletApp.getServletContextName());
	}

	private static final FilterConfigFactory _instance =
		new FilterConfigFactory();

	private final Map<String, Map<String, FilterConfig>> _pool;

}