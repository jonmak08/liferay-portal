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

package com.liferay.calendar.service.permission;

<<<<<<< HEAD
import com.liferay.calendar.constants.CalendarConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
=======
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;
>>>>>>> compatible

/**
 * @author Eduardo Lundgren
 * @author Andrea Di Giorgi
<<<<<<< HEAD
 * @deprecated As of 3.0.0, with no direct replacement
 */
@Component(
	immediate = true,
	property = {"resource.name=" + CalendarConstants.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
@Deprecated
public class CalendarPortletPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = CalendarConstants.RESOURCE_NAME;
=======
 */
@Component(
	immediate = true,
	property = {"resource.name=" + CalendarPortletPermission.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
public class CalendarPortletPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.calendar";
>>>>>>> compatible

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_portletResourcePermission.check(permissionChecker, groupId, actionId);
=======
		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException();
		}
>>>>>>> compatible
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, groupId, actionId);
=======
		return contains(
			permissionChecker, RESOURCE_NAME, CalendarPortletKeys.CALENDAR,
			groupId, actionId);
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
		target = "(resource.name=" + CalendarConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	public void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static PortletResourcePermission _portletResourcePermission;

=======
		return contains(permissionChecker, classPK, actionId);
	}

>>>>>>> compatible
}