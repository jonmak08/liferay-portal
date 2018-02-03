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
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceUtil;
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
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBBanLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.util.PropsValues;
>>>>>>> compatible

/**
 * @author Brian Wing Shun Chan
 */
<<<<<<< HEAD
=======
@OSGiBeanProperties(
	property =
		{"model.class.name=com.liferay.message.boards.kernel.model.MBMessage"}
)
>>>>>>> compatible
public class MBMessagePermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long messageId,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		MBMessage message = _getMBMessage(messageId);

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, message.getGroupId(),
				MBMessage.class.getName(), message.getMessageId(), actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBMessage.class.getName(),
				message.getMessageId(), actionId);
=======
		if (!contains(permissionChecker, messageId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBMessage.class.getName(), messageId,
				actionId);
>>>>>>> compatible
		}
	}

	public static void check(
			PermissionChecker permissionChecker, MBMessage message,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, message.getGroupId(),
				MBMessage.class.getName(), message.getMessageId(), actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

=======
		if (!contains(permissionChecker, message, actionId)) {
>>>>>>> compatible
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBMessage.class.getName(),
				message.getMessageId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		MBMessage message = _getMBMessage(classPK);

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, message.getGroupId(),
				MBMessage.class.getName(), message.getMessageId(), actionId);

		if ((containsBaseModelPermission != null) &&
			containsBaseModelPermission) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBMessage message,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
=======
		if (MBBanLocalServiceUtil.hasBan(
				message.getGroupId(), permissionChecker.getUserId())) {

			return false;
		}

		String portletId = PortletProviderUtil.getPortletId(
			MBMessage.class.getName(), PortletProvider.Action.EDIT);

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, message.getGroupId(), MBMessage.class.getName(),
			message.getMessageId(), portletId, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (message.isDraft() || message.isScheduled()) {
			if (actionId.equals(ActionKeys.VIEW) &&
				!contains(permissionChecker, message, ActionKeys.UPDATE)) {

				return false;
			}
		}
		else if (message.isPending()) {
			hasPermission = WorkflowPermissionUtil.hasPermission(
>>>>>>> compatible
				permissionChecker, message.getGroupId(),
				MBMessage.class.getName(), message.getMessageId(), actionId);

		if ((containsBaseModelPermission != null) &&
			containsBaseModelPermission) {

			return true;
		}

<<<<<<< HEAD
		return false;
=======
		if (!permissionChecker.hasPermission(
				message.getGroupId(), MBMessage.class.getName(),
				message.getMessageId(), actionId)) {

			return false;
		}

		if (message.isRoot() || !actionId.equals(ActionKeys.VIEW)) {
			return true;
		}

		return contains(
			permissionChecker,
			MBMessageLocalServiceUtil.getMessage(message.getParentMessageId()),
			actionId);
>>>>>>> compatible
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             com.liferay.message.boards.internal.service.permission.MBMessagePermission#checkBaseModel(PermissionChecker, long, long, String)}
	 */
	@Deprecated
	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		Boolean containsBaseModelPermission =
			BaseModelPermissionCheckerUtil.containsBaseModelPermission(
				permissionChecker, groupId, MBMessage.class.getName(),
				primaryKey, actionId);

		if ((containsBaseModelPermission == null) ||
			!containsBaseModelPermission) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, MBMessage.class.getName(), primaryKey,
				actionId);
		}
	}

	private static MBMessage _getMBMessage(long classPK)
		throws PortalException {

		MBThread mbThread = MBThreadLocalServiceUtil.fetchThread(classPK);

		if (mbThread == null) {
			return MBMessageLocalServiceUtil.getMessage(classPK);
		}

		return MBMessageLocalServiceUtil.getMessage(
			mbThread.getRootMessageId());
	}

}