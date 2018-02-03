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

package com.liferay.dynamic.data.mapping.type.paragraph.internal;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;
=======
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Bruno Basto
 */
<<<<<<< HEAD
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setRequired('text', true)", "setVisible('dataType', false)",
				"setVisible('predefinedValue', false)",
				"setVisible('repeatable', false)",
				"setVisible('required', false)",
				"setVisible('showLabel', false)", "setVisible('tip', false)",
				"setVisible('validation', false)"
			},
			condition = "TRUE"
		)
	}
)
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "%basic",
=======
@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.SETTINGS_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "basic",
>>>>>>> compatible
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {"label", "text", "tip", "required"}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
<<<<<<< HEAD
			title = "%properties",
=======
			title = "advanced",
>>>>>>> compatible
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"validation", "showLabel", "repeatable",
								"predefinedValue", "visibilityExpression",
								"fieldNamespace", "indexType", "localizable",
								"readOnly", "dataType", "type", "name"
							}
						)
					}
				)
			}
		)
	}
)
public interface ParagraphDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

<<<<<<< HEAD
	@DDMFormField(predefinedValue = "")
	public String dataType();

	@DDMFormField(
		label = "%title", properties = {"placeholder=%enter-a-title"},
		type = "key_value"
=======
	@DDMFormField(
		label = "%title", properties = {"placeholder=%enter-a-title"},
		required = true, type = "key-value"
>>>>>>> compatible
	)
	@Override
	public LocalizedValue label();

<<<<<<< HEAD
	@DDMFormField(
		dataType = "string", label = "%body-text",
		properties = {"placeholder=%enter-body-text"}, type = "editor"
	)
	public String text();

	@DDMFormField(dataType = "string", type = "validation")
=======
	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public LocalizedValue predefinedValue();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public boolean repeatable();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public boolean required();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public boolean showLabel();

	@DDMFormField(
		dataType = "string", label = "%body-text",
		properties = {"placeholder=%enter-body-text"}, required = true,
		type = "editor"
	)
	public String text();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public LocalizedValue tip();

	@DDMFormField(
		dataType = "ddm-validation", type = "validation",
		visibilityExpression = "FALSE"
	)
>>>>>>> compatible
	@Override
	public DDMFormFieldValidation validation();

}