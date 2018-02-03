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

package com.liferay.dynamic.data.mapping.type.radio.internal;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
=======
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
>>>>>>> compatible

/**
 * @author Marcellus Tavares
 */
<<<<<<< HEAD
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setOptions('predefinedValue', getValue('options'))",
				"setRequired('options', true)",
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
							value = {"label", "tip", "required", "options"}
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
								"predefinedValue", "visibilityExpression",
								"validation", "fieldNamespace", "indexType",
								"localizable", "readOnly", "dataType", "type",
								"name", "showLabel", "repeatable", "inline"
							}
						)
					}
				)
			}
		)
	}
)
public interface RadioDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

<<<<<<< HEAD
	@DDMFormField(
		label = "%inline", predefinedValue = "true",
		properties = {"showAsSwitcher=true"}
	)
	public boolean inline();

	@DDMFormField(
		dataType = "ddm-options", label = "%options", type = "options"
	)
	public DDMFormFieldOptions options();

	@DDMFormField(
		label = "%predefined-value",
		properties = {
			"inline=true", "placeholder=%enter-a-default-value",
			"tooltip=%enter-a-default-value-that-is-submitted-if-no-other-value-is-entered"
		},
		type = "radio"
	)
	@Override
	public LocalizedValue predefinedValue();
=======
	@DDMFormField(label = "%inline", properties = {"showAsSwitcher=true"})
	public boolean inline();

	@DDMFormField(
		dataType = "ddm-options", label = "%options", required = true,
		type = "options"
	)
	public DDMFormFieldOptions options();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public DDMFormFieldValidation validation();
>>>>>>> compatible

}