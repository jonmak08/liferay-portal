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

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0;

<<<<<<< HEAD
import com.liferay.portal.kernel.util.LoggingTimer;
=======
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Validator;
>>>>>>> compatible
import com.liferay.portal.upgrade.util.Table;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.io.Serializable;

<<<<<<< HEAD
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;

>>>>>>> compatible
import java.util.Map;

/**
 * @author Lino Alves
 */
<<<<<<< HEAD
public class UpgradeClassNames extends BaseUpgradeClassNames {

	@Override
=======
public class UpgradeClassNames extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateClassName("KaleoInstance", "className");
		updateClassName("KaleoInstanceToken", "className");
		updateClassName("KaleoLog", "currentAssigneeClassName");
		updateClassName("KaleoLog", "previousAssigneeClassName");
		updateClassName("KaleoNotificationRecipient", "recipientClassName");
		updateClassName("KaleoTaskAssignment", "assigneeClassName");
		updateClassName("KaleoTaskAssignmentInstance", "assigneeClassName");
		updateClassName("KaleoTaskInstanceToken", "className");

		updateWorkflowContextEntryClassName("KaleoInstance", "kaleoInstanceId");
		updateWorkflowContextEntryClassName("KaleoLog", "kaleoLogId");
		updateWorkflowContextEntryClassName(
			"KaleoTaskInstanceToken", "kaleoTaskInstanceTokenId");
		updateWorkflowContextEntryClassName(
			"KaleoTimerInstanceToken", "kaleoTimerInstanceTokenId");
	}

>>>>>>> compatible
	protected void updateClassName(String tableName, String columnName) {
		try (LoggingTimer loggingTimer = new LoggingTimer(tableName)) {
			Table table = new Table(tableName);

			for (Map.Entry<String, String> entry :
					_workflowContextUpgradeHelper.
						getRenamedClassNamesEntrySet()) {

				table.updateColumnValue(
					columnName, entry.getKey(), entry.getValue());
			}
		}
	}

<<<<<<< HEAD
	@Override
	protected Map<String, Serializable> updateWorkflowContext(
		String workflowContextJSON) {

		String updatedWorkflowContextJSON =
			_workflowContextUpgradeHelper.renamePortalClassNames(
				workflowContextJSON);

		Map<String, Serializable> workflowContext = WorkflowContextUtil.convert(
			updatedWorkflowContextJSON);

		if (workflowContextJSON.equals(updatedWorkflowContextJSON) &&
			!_workflowContextUpgradeHelper.isEntryClassNameRenamed(
				workflowContext)) {

			return null;
		}

		return _workflowContextUpgradeHelper.renameEntryClassName(
			workflowContext);
=======
	protected void updateWorkflowContext(
			String tableName, String primaryKeyName, long primaryKeyValue,
			String workflowContext)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update " + tableName + " set workflowContext = ? where " +
					primaryKeyName + " = ?")) {

			ps.setString(1, workflowContext);
			ps.setLong(2, primaryKeyValue);

			ps.executeUpdate();
		}
	}

	protected void updateWorkflowContextEntryClassName(
			String tableName, String primaryKeyName)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer(tableName);
			PreparedStatement ps = connection.prepareStatement(
				"select " + primaryKeyName + ", workflowContext from " +
					tableName + " where workflowContext is not null");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long primaryKeyValue = rs.getLong(primaryKeyName);
				String workflowContextJSON = rs.getString("workflowContext");

				if (Validator.isNull(workflowContextJSON)) {
					continue;
				}

				String updatedWorkflowContextJSON =
					_workflowContextUpgradeHelper.renamePortalClassNames(
						workflowContextJSON);

				Map<String, Serializable> workflowContext =
					WorkflowContextUtil.convert(updatedWorkflowContextJSON);

				if (workflowContextJSON.equals(updatedWorkflowContextJSON) &&
					!_workflowContextUpgradeHelper.isEntryClassNameRenamed(
						workflowContext)) {

					continue;
				}

				workflowContext =
					_workflowContextUpgradeHelper.renameEntryClassName(
						workflowContext);

				updateWorkflowContext(
					tableName, primaryKeyName, primaryKeyValue,
					WorkflowContextUtil.convert(workflowContext));
			}
		}
>>>>>>> compatible
	}

	private final WorkflowContextUpgradeHelper _workflowContextUpgradeHelper =
		new WorkflowContextUpgradeHelper();

}