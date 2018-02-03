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

package com.liferay.mobile.device.rules.service.permission;

import com.liferay.mobile.device.rules.constants.MDRConstants;
<<<<<<< HEAD
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
=======
import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;
>>>>>>> compatible

/**
 * @author Edward Han
 * @author Daniel Kocsis
<<<<<<< HEAD
 * @deprecated As of 1.2.0, with no direct replacement
 */
@Component(
	immediate = true,
	property = {"resource.name=" + MDRConstants.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
@Deprecated
=======
 */
@Component(
	immediate = true, property = {"resource.name=" + MDRConstants.SERVICE_NAME},
	service = ResourcePermissionChecker.class
)
>>>>>>> compatible
public class MDRPermission extends BaseResourcePermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_portletResourcePermission.check(permissionChecker, groupId, actionId);
=======
		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRConstants.SERVICE_NAME, groupId,
				actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classPK, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, classPK, actionId);
=======
		String portletId = PortletProviderUtil.getPortletId(
			MDRRule.class.getName(), PortletProvider.Action.EDIT);

		return contains(
			permissionChecker, MDRConstants.SERVICE_NAME, portletId, classPK,
			actionId);
>>>>>>> compatible
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(
		target = "(resource.name=" + MDRConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static PortletResourcePermission _portletResourcePermission;

=======
		return contains(permissionChecker, classPK, actionId);
	}

>>>>>>> compatible
}