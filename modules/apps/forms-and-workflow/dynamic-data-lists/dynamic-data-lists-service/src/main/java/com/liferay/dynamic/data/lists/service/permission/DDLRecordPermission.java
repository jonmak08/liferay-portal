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

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
<<<<<<< HEAD
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
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
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Marcellus Tavares
 */
>>>>>>> compatible
public class DDLRecordPermission {

	public static void check(
			PermissionChecker permissionChecker, DDLRecord record,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_ddlRecordSetModelResourcePermission.check(
			permissionChecker, record.getRecordSetId(), actionId);
=======
		if (!contains(permissionChecker, record, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecord.class.getName(),
				record.getRecordId(), actionId);
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, long recordId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		DDLRecord record = _ddlRecordLocalService.getDDLRecord(recordId);

		_ddlRecordSetModelResourcePermission.check(
			permissionChecker, record.getRecordSetId(), actionId);
=======
		if (!contains(permissionChecker, recordId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecord.class.getName(), recordId,
				actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DDLRecord record,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, record.getRecordSetId(), actionId);
=======
		DDLRecordSet recordSet = record.getRecordSet();

		return DDLRecordSetPermission.contains(
			permissionChecker, recordSet, actionId);
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long recordId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		DDLRecord record = _ddlRecordLocalService.getDDLRecord(recordId);

		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, record.getRecordSetId(), actionId);
	}

	@Reference(unbind = "-")
	protected void setDDLRecordLocalService(
		DDLRecordLocalService ddlRecordLocalService) {

		_ddlRecordLocalService = ddlRecordLocalService;
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.lists.model.DDLRecordSet)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<DDLRecordSet> modelResourcePermission) {

		_ddlRecordSetModelResourcePermission = modelResourcePermission;
	}

	private static DDLRecordLocalService _ddlRecordLocalService;
	private static ModelResourcePermission<DDLRecordSet>
		_ddlRecordSetModelResourcePermission;

=======
		DDLRecord record = DDLRecordLocalServiceUtil.getRecord(recordId);

		return contains(permissionChecker, record, actionId);
	}

>>>>>>> compatible
}