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

package com.liferay.portal.kernel.model.impl;

<<<<<<< HEAD
import com.liferay.portal.kernel.exception.PortalException;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Leonardo Barros
<<<<<<< HEAD
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
=======
 */
>>>>>>> compatible
public class ModificationDeniedLayoutTypeAccessPolicyImpl
	extends DefaultLayoutTypeAccessPolicyImpl {

	@Override
	public boolean isAddLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isCustomizeLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isDeleteLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isUpdateLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isViewLayoutAllowed(
<<<<<<< HEAD
			PermissionChecker permissionChecker, Layout layout)
		throws PortalException {

		return super.isViewLayoutAllowed(permissionChecker, layout);
=======
		PermissionChecker permissionChecker, Layout layout) {

		return true;
>>>>>>> compatible
	}

	@Override
	protected boolean hasAccessPermission(
<<<<<<< HEAD
			HttpServletRequest request, Layout layout, Portlet portlet)
		throws PortalException {

		return super.hasAccessPermission(request, layout, portlet);
=======
		HttpServletRequest request, Layout layout, Portlet portlet) {

		return true;
>>>>>>> compatible
	}

	@Override
	protected boolean isAccessAllowedToLayoutPortlet(
		HttpServletRequest request, Layout layout, Portlet portlet) {

<<<<<<< HEAD
		return super.isAccessAllowedToLayoutPortlet(request, layout, portlet);
=======
		return true;
>>>>>>> compatible
	}

}