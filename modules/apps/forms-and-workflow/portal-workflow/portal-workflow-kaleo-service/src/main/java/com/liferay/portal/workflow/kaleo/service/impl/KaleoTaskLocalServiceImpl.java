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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Task;
<<<<<<< HEAD
import com.liferay.portal.workflow.kaleo.definition.TaskForm;
=======
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskLocalServiceImpl extends KaleoTaskLocalServiceBaseImpl {

	@Override
	public KaleoTask addKaleoTask(
<<<<<<< HEAD
			long kaleoDefinitionVersionId, long kaleoNodeId, Task task,
=======
			long kaleoDefinitionId, long kaleoNodeId, Task task,
>>>>>>> compatible
			ServiceContext serviceContext)
		throws PortalException {

		// Kaleo task

		User user = userLocalService.getUser(serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTaskId = counterLocalService.increment();

		KaleoTask kaleoTask = kaleoTaskPersistence.create(kaleoTaskId);

		kaleoTask.setCompanyId(user.getCompanyId());
		kaleoTask.setUserId(user.getUserId());
		kaleoTask.setUserName(user.getFullName());
		kaleoTask.setCreateDate(now);
		kaleoTask.setModifiedDate(now);
<<<<<<< HEAD
		kaleoTask.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
=======
		kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
>>>>>>> compatible
		kaleoTask.setKaleoNodeId(kaleoNodeId);
		kaleoTask.setName(task.getName());
		kaleoTask.setDescription(task.getDescription());

		kaleoTaskPersistence.update(kaleoTask);

		// Kaleo assignments

		Set<Assignment> assignments = task.getAssignments();

		for (Assignment assignment : assignments) {
			kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
<<<<<<< HEAD
				KaleoTask.class.getName(), kaleoTaskId,
				kaleoDefinitionVersionId, assignment, serviceContext);
		}

		// Kaleo forms

		Set<TaskForm> taskForms = task.getTaskForms();

		for (TaskForm taskForm : taskForms) {
			kaleoTaskFormLocalService.addKaleoTaskForm(
				kaleoDefinitionVersionId, kaleoNodeId, kaleoTask, taskForm,
				serviceContext);
=======
				KaleoTask.class.getName(), kaleoTaskId, kaleoDefinitionId,
				assignment, serviceContext);
>>>>>>> compatible
		}

		return kaleoTask;
	}

	@Override
	public void deleteCompanyKaleoTasks(long companyId) {

		// Kaleo tasks

		kaleoTaskPersistence.removeByCompanyId(companyId);

		// Kaleo task assignments

		kaleoTaskAssignmentLocalService.deleteCompanyKaleoTaskAssignments(
			companyId);
<<<<<<< HEAD

		// Kaleo task forms

		kaleoTaskFormLocalService.deleteCompanyKaleoTaskForms(companyId);
	}

	@Override
	public void deleteKaleoDefinitionVersionKaleoTasks(
		long kaleoDefinitionVersionId) {

		// Kaleo tasks

		kaleoTaskPersistence.removeByKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);
=======
	}

	@Override
	public void deleteKaleoDefinitionKaleoTasks(long kaleoDefinitionId) {

		// Kaleo tasks

		kaleoTaskPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
>>>>>>> compatible

		// Kaleo task assignments

		kaleoTaskAssignmentLocalService.
<<<<<<< HEAD
			deleteKaleoDefinitionVersionKaleoTaskAssignments(
				kaleoDefinitionVersionId);

		// Kaleo task forms

		kaleoTaskFormLocalService.deleteKaleoDefinitionVersionKaleoTaskForms(
			kaleoDefinitionVersionId);
=======
			deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
>>>>>>> compatible
	}

	@Override
	public KaleoTask getKaleoNodeKaleoTask(long kaleoNodeId)
		throws PortalException {

		return kaleoTaskPersistence.findByKaleoNodeId(kaleoNodeId);
	}

}