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

package com.liferay.portal.workflow.kaleo.definition;

<<<<<<< HEAD
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
=======
import java.util.Set;
>>>>>>> compatible

/**
 * @author Michael C. Han
 */
public class Task extends Node {

	public Task(String name, String description) {
		super(NodeType.TASK, name, description);
	}

<<<<<<< HEAD
	public void addTaskForm(TaskForm taskForm) {
		_taskForms.add(taskForm);
	}

	public void addTaskForms(Set<TaskForm> taskForms) {
		_taskForms.addAll(taskForms);
	}

=======
>>>>>>> compatible
	public Set<Assignment> getAssignments() {
		return _assignments;
	}

<<<<<<< HEAD
	public Set<TaskForm> getTaskForms() {
		return Collections.unmodifiableSet(_taskForms);
	}

=======
>>>>>>> compatible
	public void setAssignments(Set<Assignment> assignments) {
		_assignments = assignments;
	}

	private Set<Assignment> _assignments;
<<<<<<< HEAD
	private final Set<TaskForm> _taskForms = new TreeSet<>();
=======
>>>>>>> compatible

}