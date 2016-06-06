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

package com.liferay.portlet.workflowtasks.action;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Adam Brandizzi
 */
public class WorkflowTaskPermissionChecker {

	public static boolean hasAssignmentPermission(
		long groupId, WorkflowTask workflowTask,
		PermissionChecker permissionChecker) {

		if (permissionChecker.isOmniadmin() ||
			permissionChecker.isCompanyAdmin() ||
			permissionChecker.isContentReviewer(
					permissionChecker.getCompanyId(), groupId)) {

			return true;
		}

		long[] roleIds = permissionChecker.getRoleIds(
			permissionChecker.getUserId(), groupId);

		for (WorkflowTaskAssignee workflowTaskAssignee :
				workflowTask.getWorkflowTaskAssignees()) {

			if (isWorkflowTaskAssignableToRoles(
					workflowTaskAssignee, roleIds) ||
				isWorkflowTaskAssignableToUser(
					workflowTaskAssignee, permissionChecker.getUserId())) {

				return true;
			}
		}

		return false;
	}

	protected static boolean isWorkflowTaskAssignableToRoles(
		WorkflowTaskAssignee workflowTaskAssignee, long[] roleIds) {

		String assigneeClassName = workflowTaskAssignee.getAssigneeClassName();

		if (!assigneeClassName.equals(Role.class.getName())) {
			return false;
		}

		if (ArrayUtil.contains(
				roleIds, workflowTaskAssignee.getAssigneeClassPK())) {

			return true;
		}

		return false;
	}

	protected static boolean isWorkflowTaskAssignableToUser(
		WorkflowTaskAssignee workflowTaskAssignee, long userId) {

		String assigneeClassName = workflowTaskAssignee.getAssigneeClassName();

		if (!assigneeClassName.equals(User.class.getName())) {
			return false;
		}

		if (workflowTaskAssignee.getAssigneeClassPK() == userId) {
			return true;
		}

		return false;
	}

}