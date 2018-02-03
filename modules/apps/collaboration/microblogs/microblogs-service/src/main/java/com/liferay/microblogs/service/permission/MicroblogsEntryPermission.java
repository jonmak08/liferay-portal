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

import com.liferay.microblogs.model.MicroblogsEntry;
<<<<<<< HEAD
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 * @deprecated As of 2.1.0, with no direct replacement
 */
@Component(immediate = true)
@Deprecated
=======
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.social.kernel.service.SocialRelationLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
>>>>>>> compatible
public class MicroblogsEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_microblogsEntryModelResourcePermission.check(
			permissionChecker, microblogsEntryId, actionId);
=======
		if (!contains(permissionChecker, microblogsEntryId, actionId)) {
			throw new PrincipalException();
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker,
			MicroblogsEntry microblogsEntry, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_microblogsEntryModelResourcePermission.check(
			permissionChecker, microblogsEntry, actionId);
=======
		if (!contains(permissionChecker, microblogsEntry, actionId)) {
			throw new PrincipalException();
		}
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _microblogsEntryModelResourcePermission.contains(
			permissionChecker, microblogsEntryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			MicroblogsEntry microblogsEntry, String actionId)
		throws PortalException {

		return _microblogsEntryModelResourcePermission.contains(
			permissionChecker, microblogsEntry, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.microblogs.model.MicroblogsEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MicroblogsEntry> modelResourcePermission) {

		_microblogsEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MicroblogsEntry>
		_microblogsEntryModelResourcePermission;
=======
		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				microblogsEntryId);

		return contains(permissionChecker, microblogsEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MicroblogsEntry microblogsEntry,
		String actionId) {

		if (actionId.equals(ActionKeys.DELETE) ||
			actionId.equals(ActionKeys.UPDATE)) {

			if (permissionChecker.hasOwnerPermission(
					microblogsEntry.getCompanyId(),
					MicroblogsEntry.class.getName(),
					microblogsEntry.getMicroblogsEntryId(),
					microblogsEntry.getUserId(), actionId)) {

				return true;
			}

			return false;
		}

		if (permissionChecker.hasOwnerPermission(
				microblogsEntry.getCompanyId(), MicroblogsEntry.class.getName(),
				microblogsEntry.getMicroblogsEntryId(),
				microblogsEntry.getUserId(), actionId)) {

			return true;
		}

		if (microblogsEntry.getSocialRelationType() == 0) {
			return true;
		}

		if ((microblogsEntry.getUserId() != permissionChecker.getUserId()) &&
			SocialRelationLocalServiceUtil.hasRelation(
				permissionChecker.getUserId(), microblogsEntry.getUserId(),
				microblogsEntry.getSocialRelationType())) {

			return true;
		}

		return false;
	}
>>>>>>> compatible

}