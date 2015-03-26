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

package com.liferay.portal.events;

import com.liferay.portal.action.TCKStrutsAction;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.filters.invoker.FilterMapping;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.servlet.filters.tck.TCKAutoLoginFilter;
import com.liferay.portal.struts.StrutsActionRegistryUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author Matthew Tambara
 */

public class TCKStartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		ServletContext servletContext = ServletContextPool.get(
			PortalUtil.getPathContext());

		Filter filter = new TCKAutoLoginFilter();

		FilterConfig filterConfig = new InvokerFilterConfig(
			servletContext, _FILTER_NAME, Collections.EMPTY_MAP);

		try {
			filter.init(filterConfig);
		}
		catch (ServletException se) {
			throw new ActionException(se);
		}

		InvokerFilterHelper invokerFilterHelper =
			(InvokerFilterHelper)servletContext.getAttribute(
				InvokerFilterHelper.class.getName());

		invokerFilterHelper.registerFilter(_FILTER_NAME, filter);

		invokerFilterHelper.registerFilterMapping(
			new FilterMapping(
				filter, filterConfig, Collections.singletonList("/*"),
				Collections.EMPTY_LIST), _FILTER_NAME, false);

		StrutsActionRegistryUtil.register(_PATH, new TCKStrutsAction());
	}

	private static final String _FILTER_NAME = "TCK Auto Login Filter";

	private static final String _PATH = "/portal/tck";

}