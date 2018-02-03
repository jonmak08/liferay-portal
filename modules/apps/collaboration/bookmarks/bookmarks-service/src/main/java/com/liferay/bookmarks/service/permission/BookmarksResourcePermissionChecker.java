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

package com.liferay.bookmarks.service.permission;

<<<<<<< HEAD
import com.liferay.bookmarks.constants.BookmarksConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 * @deprecated As of 1.2.0, with no direct replacement
 */
@Component(
	immediate = true,
	property = {"resource.name=" + BookmarksConstants.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
@Deprecated
public class BookmarksResourcePermissionChecker
	extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = BookmarksConstants.RESOURCE_NAME;
=======
import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = {
		"resource.name=" + BookmarksResourcePermissionChecker.RESOURCE_NAME
	},
	service = ResourcePermissionChecker.class
)
public class BookmarksResourcePermissionChecker
	extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.bookmarks";
>>>>>>> compatible

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_portletResourcePermission.check(permissionChecker, groupId, actionId);
=======
		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, groupId, actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classPK, String actionId) {

<<<<<<< HEAD
		return _portletResourcePermission.contains(
			permissionChecker, classPK, actionId);
=======
		return contains(
			permissionChecker, RESOURCE_NAME, BookmarksPortletKeys.BOOKMARKS,
			classPK, actionId);
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
		target = "(resource.name=" + BookmarksConstants.RESOURCE_NAME + ")",
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