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

package com.liferay.users.admin.web.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;

import javax.portlet.RenderResponse;

/**
 * @author Pei-Jung Lan
 */
public class OrganizationUserChecker extends EmptyOnClickRowChecker {

	public OrganizationUserChecker(RenderResponse renderResponse) {
		super(renderResponse);
	}

	@Override
	public boolean isDisabled(Object obj) {
		Organization organization = null;
		User user = null;

		if (obj instanceof Organization) {
			organization = (Organization)obj;
		}
		else {
			user = (User)obj;
		}

		try {
			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if ((organization != null) &&
				!OrganizationPermissionUtil.contains(
					permissionChecker, organization, ActionKeys.DELETE)) {

				return true;
			}

			if ((user != null) &&
				!UserPermissionUtil.contains(
					permissionChecker, user.getUserId(), ActionKeys.DELETE)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return super.isDisabled(obj);
	}

}