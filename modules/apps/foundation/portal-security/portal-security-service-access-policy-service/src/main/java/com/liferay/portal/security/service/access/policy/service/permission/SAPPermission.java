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

package com.liferay.portal.security.service.access.policy.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
<<<<<<< HEAD
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.security.service.access.policy.constants.SAPConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 * @deprecated As of 2.1.0, with no direct replacement
 */
@Component(
	immediate = true,
	property = {"resource.name=" + SAPConstants.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
@Deprecated
public class SAPPermission implements ResourcePermissionChecker {

	public static final String RESOURCE_NAME = SAPConstants.RESOURCE_NAME;
=======
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.security.service.access.policy.constants.SAPConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tomas Polesovsky
 */
@Component(
	immediate = true,
	property = {"resource.name=" + SAPPermission.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
public class SAPPermission implements ResourcePermissionChecker {

	public static final String RESOURCE_NAME = SAPConstants.SERVICE_NAME;
>>>>>>> compatible

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_portletResourcePermission.check(permissionChecker, null, actionId);
=======
		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, RESOURCE_NAME, actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, null, actionId);
=======
		return permissionChecker.hasPermission(
			0, RESOURCE_NAME, RESOURCE_NAME, actionId);
>>>>>>> compatible
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, null, actionId);
	}

	@Reference(
		target = "(resource.name=" + SAPConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static PortletResourcePermission _portletResourcePermission;

=======
		return contains(permissionChecker, actionId);
	}

>>>>>>> compatible
}