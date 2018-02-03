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

package com.liferay.portlet.messageboards.service.permission;

<<<<<<< HEAD
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionCheckerUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
=======
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.message.boards.kernel.exception.NoSuchCategoryException;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.service.MBBanLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.util.PropsValues;
>>>>>>> compatible

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
<<<<<<< HEAD
=======
@OSGiBeanProperties(
	property =
		{"model.class.name=com.liferay.message.boards.kernel.model.MBCategory"}
)
>>>>>>> compatible
public class MBCategoryPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long groupId, long categoryId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, groupId, MBCategory.class.getName(),
				categoryId, actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

=======
		if (!contains(permissionChecker, groupId, categoryId, actionId)) {
>>>>>>> compatible
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBCategory.class.getName(), categoryId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long categoryId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		MBCategory category = MBCategoryLocalServiceUtil.getCategory(
			categoryId);

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, category.getGroupId(),
				MBCategory.class.getName(), categoryId, actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

=======
		if (!contains(permissionChecker, categoryId, actionId)) {
>>>>>>> compatible
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBCategory.class.getName(), categoryId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, MBCategory category,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, category.getGroupId(),
				MBCategory.class.getName(), category.getCategoryId(), actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

=======
		if (!contains(permissionChecker, category, actionId)) {
>>>>>>> compatible
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBCategory.class.getName(),
				category.getCategoryId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long categoryId,
			String actionId)
		throws PortalException {

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, groupId, MBCategory.class.getName(),
				categoryId, actionId);

		if ((containsBaseModelPermission != null) &&
			containsBaseModelPermission) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long categoryId,
			String actionId)
		throws PortalException {

		MBCategory category = MBCategoryLocalServiceUtil.getCategory(
			categoryId);

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, category.getGroupId(),
				MBCategory.class.getName(), categoryId, actionId);

		if ((containsBaseModelPermission != null) &&
			containsBaseModelPermission) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBCategory category,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, category.getGroupId(),
				MBCategory.class.getName(), category.getCategoryId(), actionId);
=======
		if (MBBanLocalServiceUtil.hasBan(
				category.getGroupId(), permissionChecker.getUserId())) {

			return false;
		}

		if (actionId.equals(ActionKeys.ADD_CATEGORY)) {
			actionId = ActionKeys.ADD_SUBCATEGORY;
		}

		String portletId = PortletProviderUtil.getPortletId(
			MBCategory.class.getName(), PortletProvider.Action.EDIT);

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, category.getGroupId(),
			MBCategory.class.getName(), category.getCategoryId(), portletId,
			actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			try {
				long categoryId = category.getCategoryId();

				while (categoryId !=
							MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
>>>>>>> compatible

		if ((containsBaseModelPermission != null) &&
			containsBaseModelPermission) {

			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             com.liferay.message.boards.internal.service.permission.MBCategoryPermission#checkBaseModel(PermissionChecker, long, long, String)}
	 */
	@Deprecated
	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, groupId, MBCategory.class.getName(),
				primaryKey, actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBCategory.class.getName(), primaryKey,
				actionId);
		}
	}

}