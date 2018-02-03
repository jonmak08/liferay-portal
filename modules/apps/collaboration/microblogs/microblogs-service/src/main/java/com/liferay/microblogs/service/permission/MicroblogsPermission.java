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

package com.liferay.microblogs.service.permission;

<<<<<<< HEAD
import com.liferay.microblogs.constants.MicroblogsConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 * @deprecated As of 2.1.0, with no direct replacement
 */
@Component(
	immediate = true,
	property = {"resource.name=" + MicroblogsConstants.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
@Deprecated
public class MicroblogsPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME =
		MicroblogsConstants.RESOURCE_NAME;
=======
import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan Lee
 */
@Component(
	immediate = true,
	property = {"resource.name=" + MicroblogsPermission.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
public class MicroblogsPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.microblogs";
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
			permissionChecker, RESOURCE_NAME, MicroblogsPortletKeys.MICROBLOGS,
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
		target = "(resource.name=" + MicroblogsConstants.RESOURCE_NAME + ")",
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