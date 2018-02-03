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
import com.liferay.calendar.model.Calendar;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
=======
import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
>>>>>>> compatible

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
<<<<<<< HEAD
 * @deprecated As of 3.0.0, with no direct replacement
 */
@Component(immediate = true)
@Deprecated
=======
 */
>>>>>>> compatible
public class CalendarPermission {

	public static void check(
			PermissionChecker permissionChecker, Calendar calendar,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_calendarModelResourcePermission.check(
			permissionChecker, calendar, actionId);
=======
		if (!contains(permissionChecker, calendar, actionId)) {
			throw new PrincipalException();
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_calendarModelResourcePermission.check(
			permissionChecker, calendarId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Calendar calendar,
			String actionId)
		throws PortalException {

		return _calendarModelResourcePermission.contains(
			permissionChecker, calendar, actionId);
=======
		if (!contains(permissionChecker, calendarId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Calendar calendar,
		String actionId) {

		if (!actionId.equals(CalendarActionKeys.VIEW_BOOKING_DETAILS)) {
			Boolean hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, calendar.getGroupId(),
				Calendar.class.getName(), calendar.getCalendarId(),
				CalendarPortletKeys.CALENDAR, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (permissionChecker.hasOwnerPermission(
				calendar.getCompanyId(), Calendar.class.getName(),
				calendar.getCalendarId(), calendar.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendar.getGroupId(), Calendar.class.getName(),
			calendar.getCalendarId(), actionId);
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _calendarModelResourcePermission.contains(
			permissionChecker, calendarId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.Calendar)",
		unbind = "-"
	)
	protected void setModelPermissionChecker(
		ModelResourcePermission<Calendar> modelResourcePermission) {

		_calendarModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Calendar>
		_calendarModelResourcePermission;

=======
		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		return contains(permissionChecker, calendar, actionId);
	}

>>>>>>> compatible
}