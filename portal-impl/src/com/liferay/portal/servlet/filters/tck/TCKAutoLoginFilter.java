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

package com.liferay.portal.servlet.filters.tck;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Shuyang Zhou
 */
public class TCKAutoLoginFilter extends BasePortalFilter {

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		// The portlet TCK has two tests named GetRemoteUserNullTestPortlet. One
		// tests an action request and the other tests a render request. Those
		// two tests assume that the current user is not authenticated. This
		// filter skips automatic authentication as a workaround for those two
		// tests.

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute(_TCK_SKIP_LOGIN) == Boolean.TRUE) {
			processFilter(
				TCKAutoLoginFilter.class, request, response, filterChain);

			return;
		}

		String[] portletIds = request.getParameterValues("portletName");

		if (portletIds != null) {
			for (String portlet : portletIds) {
				if (portlet.endsWith("GetRemoteUserNullTestPortlet")) {
					httpSession.setAttribute(_TCK_SKIP_LOGIN, Boolean.TRUE);

					processFilter(
						TCKAutoLoginFilter.class, request, response,
						filterChain);

					return;
				}
			}
		}

		User tckUser = UserLocalServiceUtil.fetchUserByEmailAddress(
			PortalUtil.getCompanyId(request), "tck@liferay.com");

		if (tckUser != null) {
			request.setAttribute(WebKeys.USER_ID, tckUser.getUserId());
		}

		processFilter(TCKAutoLoginFilter.class, request, response, filterChain);
	}

	private static final String _TCK_SKIP_LOGIN = "TCK_SKIP_LOGIN";

}