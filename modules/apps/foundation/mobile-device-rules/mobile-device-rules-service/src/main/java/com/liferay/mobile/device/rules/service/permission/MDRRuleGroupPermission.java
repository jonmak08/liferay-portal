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

<<<<<<< HEAD
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
=======
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
>>>>>>> compatible

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
<<<<<<< HEAD
 * @deprecated As of 1.2.0, with no direct replacement
=======
>>>>>>> compatible
 */
@Component(
	property = {"model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup"},
	service = BaseModelPermissionChecker.class
)
<<<<<<< HEAD
@Deprecated
=======
>>>>>>> compatible
public class MDRRuleGroupPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_mdrRuleGroupModelResourcePermission.check(
			permissionChecker, ruleGroupId, actionId);
=======
		if (!contains(permissionChecker, ruleGroupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroup.class.getName(), ruleGroupId,
				actionId);
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_mdrRuleGroupModelResourcePermission.check(
			permissionChecker, ruleGroup, actionId);
=======
		if (!contains(permissionChecker, ruleGroup, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroup.class.getName(),
				ruleGroup.getRuleGroupId(), actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _mdrRuleGroupModelResourcePermission.contains(
			permissionChecker, ruleGroupId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException {

		return _mdrRuleGroupModelResourcePermission.contains(
			permissionChecker, ruleGroup, actionId);
=======
		MDRRuleGroup ruleGroup = _mdrRuleGroupLocalService.getMDRRuleGroup(
			ruleGroupId);

		return contains(permissionChecker, ruleGroup, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, ruleGroup.getGroupId(),
			MDRRuleGroup.class.getName(), ruleGroup.getRuleGroupId(),
			MDRPortletKeys.MOBILE_DEVICE_RULES, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return permissionChecker.hasPermission(
			ruleGroup.getGroupId(), MDRRuleGroup.class.getName(),
			ruleGroup.getRuleGroupId(), actionId);
>>>>>>> compatible
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_mdrRuleGroupModelResourcePermission.check(
			permissionChecker, primaryKey, actionId);
	}

	protected void setMDRRuleGroupLocalService(
		MDRRuleGroupLocalService mdrRuleGroupLocalService) {
	}

	@Reference(
		target = "(model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MDRRuleGroup> modelResourcePermission) {

		_mdrRuleGroupModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MDRRuleGroup>
		_mdrRuleGroupModelResourcePermission;
=======
		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupLocalService(
		MDRRuleGroupLocalService mdrRuleGroupLocalService) {

		_mdrRuleGroupLocalService = mdrRuleGroupLocalService;
	}

	private static MDRRuleGroupLocalService _mdrRuleGroupLocalService;
>>>>>>> compatible

}