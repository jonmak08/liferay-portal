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

package com.liferay.app.builder.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jeyvison Nascimento
 */
public class ProductMenuAppPanelApp extends BasePanelApp {

	public ProductMenuAppPanelApp(
		boolean inControlPanel, String portletId, long[] siteIds) {

		_inControlPanel = inControlPanel;
		_portletId = portletId;
		_siteIds = siteIds;
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public PortletURL getPortletURL(HttpServletRequest httpServletRequest) {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			httpServletRequest, getPortletId(), PortletRequest.RENDER_PHASE);

		Group group = groupProvider.getGroup(httpServletRequest);

		if (group == null) {
			return portletURL;
		}

		portletURL.setParameter(
			"p_v_l_s_g_id", String.valueOf(group.getGroupId()));

		return portletURL;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		if (super.isShow(permissionChecker, group) &&
			(_inControlPanel ||
			 ArrayUtil.contains(_siteIds, group.getGroupId()))) {

			return true;
		}

		return false;
	}

	private final boolean _inControlPanel;
	private final String _portletId;
	private final long[] _siteIds;

}