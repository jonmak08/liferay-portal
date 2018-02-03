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

package com.liferay.dynamic.data.lists.service.permission;

<<<<<<< HEAD
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 * @deprecated As of 1.2.0, with no direct replacement
 */
@Component(immediate = true)
@Deprecated
=======
import com.liferay.dynamic.data.lists.constants.DDLActionKeys;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Marcellus Tavares
 */
>>>>>>> compatible
public class DDLRecordSetPermission {

	public static void check(
			PermissionChecker permissionChecker, DDLRecordSet recordSet,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_ddlRecordSetModelResourcePermission.check(
			permissionChecker, recordSet, actionId);
=======
		if (!contains(permissionChecker, recordSet, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecordSet.class.getName(),
				recordSet.getRecordSetId(), actionId);
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_ddlRecordSetModelResourcePermission.check(
			permissionChecker, recordSetId, actionId);
	}

	/**
	 * @deprecated As of 1.1.0, with no direct replacement
	 */
	@Deprecated
=======
		if (!contains(permissionChecker, recordSetId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecordSet.class.getName(), recordSetId,
				actionId);
		}
	}

>>>>>>> compatible
	public static void check(
			PermissionChecker permissionChecker, long groupId,
			String recordSetKey, String actionId)
		throws PortalException {

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			groupId, recordSetKey);

<<<<<<< HEAD
		_ddlRecordSetModelResourcePermission.check(
			permissionChecker, recordSet, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DDLRecordSet recordSet,
			String actionId)
		throws PortalException {

		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, recordSet, actionId);
=======
		check(permissionChecker, recordSet, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, DDLRecordSet recordSet,
		String actionId) {

		String portletId = PortletProviderUtil.getPortletId(
			DDLRecord.class.getName(), PortletProvider.Action.EDIT);

		if (!actionId.equals(DDLActionKeys.ADD_RECORD) &&
			(recordSet.getScope() ==
				DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS)) {

			Boolean hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, recordSet.getGroupId(),
				DDLRecordSet.class.getName(), recordSet.getRecordSetId(),
				portletId, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (permissionChecker.hasOwnerPermission(
				recordSet.getCompanyId(), DDLRecordSet.class.getName(),
				recordSet.getRecordSetId(), recordSet.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			recordSet.getGroupId(), DDLRecordSet.class.getName(),
			recordSet.getRecordSetId(), actionId);
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, recordSetId, actionId);
	}

	/**
	 * @deprecated As of 1.1.0, with no direct replacement
	 */
	@Deprecated
=======
		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			recordSetId);

		return contains(permissionChecker, recordSet, actionId);
	}

>>>>>>> compatible
	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			String recordSetKey, String actionId)
		throws PortalException {

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			groupId, recordSetKey);

<<<<<<< HEAD
		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, recordSet, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.lists.model.DDLRecordSet)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<DDLRecordSet> modelResourcePermission) {

		_ddlRecordSetModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<DDLRecordSet>
		_ddlRecordSetModelResourcePermission;

=======
		return contains(permissionChecker, recordSet, actionId);
	}

>>>>>>> compatible
}