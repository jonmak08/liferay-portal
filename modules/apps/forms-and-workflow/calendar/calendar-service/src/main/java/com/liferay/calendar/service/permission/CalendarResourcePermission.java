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
import com.liferay.calendar.model.CalendarResource;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
=======
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
>>>>>>> compatible

/**
 * @author Eduardo Lundgren
 * @author Michael C. Han
<<<<<<< HEAD
 * @deprecated As of 3.0.0, with no direct replacement
 */
@Component(immediate = true)
@Deprecated
=======
 */
>>>>>>> compatible
public class CalendarResourcePermission {

	public static void check(
			PermissionChecker permissionChecker,
			CalendarResource calendarResource, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_calendarResourceModelResourcePermission.check(
			permissionChecker, calendarResource, actionId);
=======
		if (!contains(permissionChecker, calendarResource, actionId)) {
			throw new PrincipalException();
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_calendarResourceModelResourcePermission.check(
			permissionChecker, calendarResourceId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			CalendarResource calendarResource, String actionId)
		throws PortalException {

		return _calendarResourceModelResourcePermission.contains(
			permissionChecker, calendarResource, actionId);
=======
		if (!contains(permissionChecker, calendarResourceId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarResource calendarResource,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, calendarResource.getGroupId(),
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(),
			CalendarPortletKeys.CALENDAR, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				calendarResource.getCompanyId(),
				CalendarResource.class.getName(),
				calendarResource.getCalendarResourceId(),
				calendarResource.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarResource.getGroupId(), CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), actionId);
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _calendarResourceModelResourcePermission.contains(
			permissionChecker, calendarResourceId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.CalendarResource)",
		unbind = "-"
	)
	protected void setModelPermissionChecker(
		ModelResourcePermission<CalendarResource> modelResourcePermission) {

		_calendarResourceModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CalendarResource>
		_calendarResourceModelResourcePermission;

=======
		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.getCalendarResource(
				calendarResourceId);

		return contains(permissionChecker, calendarResource, actionId);
	}

>>>>>>> compatible
}