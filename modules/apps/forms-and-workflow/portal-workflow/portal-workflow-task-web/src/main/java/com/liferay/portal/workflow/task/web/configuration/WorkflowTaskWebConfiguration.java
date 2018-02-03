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

package com.liferay.portal.workflow.task.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Inácio Nery
 */
@ExtendedObjectClassDefinition(category = "forms-and-workflow")
@Meta.OCD(
<<<<<<< HEAD
	id = "com.liferay.portal.workflow.task.web.configuration.WorkflowTaskWebConfiguration",
	localization = "content/Language",
	name = "workflow-task-web-configuration-name"
=======
	id = "com.liferay.portal.workflow.task.web.configuration.WorkflowTaskWebConfiguration"
>>>>>>> compatible
)
public interface WorkflowTaskWebConfiguration {

	@Meta.AD(
<<<<<<< HEAD
		deflt = "list", name = "default-display-view",
		optionLabels = {"Descriptive", "List"},
=======
		deflt = "list", optionLabels = {"Descriptive", "List"},
>>>>>>> compatible
		optionValues = {"descriptive", "list"}, required = false
	)
	public String defaultDisplayView();

}