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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marcellus Tavares
 */
public class ActionUtil {

	public static void getWorkflowTask(HttpServletRequest request)
		throws Exception {

		long workflowTaskId = ParamUtil.getLong(request, "workflowTaskId");

		WorkflowTask workflowTask = null;

		if (workflowTaskId > 0) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
				themeDisplay.getCompanyId(), workflowTaskId);

			if (!WorkflowTaskPermissionChecker.hasAssignmentPermission(
					themeDisplay.getScopeGroupId(), workflowTask,
				permissionChecker)) {

				throw new PrincipalException(
					"User " + themeDisplay.getUserId() + " does not have " +
					"permissions to assign the task " + workflowTaskId +
					"to someone.");
			}
		}

		request.setAttribute(WebKeys.WORKFLOW_TASK, workflowTask);
	}

	public static void getWorkflowTask(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getWorkflowTask(request);
	}

}