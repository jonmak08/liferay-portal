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

package com.liferay.dynamic.data.mapping.type.text.internal;

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
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
=======
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Lino Alves
 */
<<<<<<< HEAD
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"call('getDataProviderInstanceOutputParameters', concat('dataProviderInstanceId=', getValue('ddmDataProviderInstanceId')), 'ddmDataProviderInstanceOutput=outputParameterNames')"
			},
			condition = "not(equals(getValue('ddmDataProviderInstanceId'), ''))"
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
							value = {"label", "tip", "displayStyle", "required"}
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
<<<<<<< HEAD
								"predefinedValue", "dataSourceType",
								"ddmDataProviderInstanceId",
								"ddmDataProviderInstanceOutput", "options",
								"placeholder", "visibilityExpression",
								"validation", "fieldNamespace", "indexType",
								"localizable", "readOnly", "dataType", "type",
								"name", "showLabel", "repeatable", "tooltip"
=======
								"predefinedValue", "placeholder",
								"visibilityExpression", "validation",
								"fieldNamespace", "indexType", "localizable",
								"readOnly", "dataType", "type", "name",
								"showLabel", "repeatable", "tooltip"
>>>>>>> compatible
							}
						)
					}
				)
			}
		)
	}
)
public interface TextDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(
<<<<<<< HEAD
		label = "%create-list",
		optionLabels = {"%manually", "%from-data-provider"},
		optionValues = {"manual", "data-provider"},
		properties = {"showLabel=false"}, type = "radio",
		visibilityExpression = "TRUE"
	)
	public String dataSourceType();

	@DDMFormField(
		label = "%choose-a-data-provider",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=getDataProviderInstances"
		},
		type = "select",
		visibilityExpression = "equals(dataSourceType, \"data-provider\")"
	)
	public long ddmDataProviderInstanceId();

	@DDMFormField(
		label = "%choose-an-output-parameter",
		properties = {
			"tooltip=%choose-an-output-parameter-for-a-data-provider-previously-created"
		},
		type = "select",
		visibilityExpression = "equals(dataSourceType, \"data-provider\")"
	)
	public String ddmDataProviderInstanceOutput();

	@DDMFormField(
		label = "%my-text-field-has",
		optionLabels = {"%a-single-line", "%multiple-lines"},
		optionValues = {"singleline", "multiline"},
		predefinedValue = "singleline", properties = {"inline=true"},
		type = "radio"
=======
		label = "%my-text-field-has",
		optionLabels = {"%a-single-line", "%multiple-lines"},
		optionValues = {"singleline", "multiline"},
		properties = {"inline=true"}, type = "radio"
>>>>>>> compatible
	)
	public String displayStyle();

	@DDMFormField(
<<<<<<< HEAD
		dataType = "ddm-options", label = "%options",
		properties = {"showLabel=false", "allowEmptyOptions=true"},
		required = false, type = "options",
		visibilityExpression = "equals(dataSourceType, \"manual\")"
	)
	public DDMFormFieldOptions options();

	@DDMFormField(
=======
>>>>>>> compatible
		dataType = "string", label = "%placeholder-text",
		properties = {
			"placeholder=%enter-placeholder-text",
			"tooltip=%enter-text-that-assists-the-user-but-is-not-submitted-as-a-field-value"
		},
		type = "text"
	)
	public LocalizedValue placeholder();

	@DDMFormField(visibilityExpression = "FALSE")
	public LocalizedValue tooltip();

<<<<<<< HEAD
	@DDMFormField(
		dataType = "string", label = "%validation", type = "validation"
	)
	@Override
	public DDMFormFieldValidation validation();

=======
>>>>>>> compatible
}