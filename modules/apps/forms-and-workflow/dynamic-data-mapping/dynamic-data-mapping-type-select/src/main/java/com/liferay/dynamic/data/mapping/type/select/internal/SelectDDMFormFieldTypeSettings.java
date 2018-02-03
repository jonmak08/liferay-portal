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

package com.liferay.dynamic.data.mapping.type.select.internal;

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
				"call('getDataProviderInstanceOutputParameters', concat('dataProviderInstanceId=', getValue('ddmDataProviderInstanceId')), 'ddmDataProviderInstanceOutput=outputParameterNames')"
			},
			condition = "not(equals(getValue('ddmDataProviderInstanceId'), ''))"
		),
		@DDMFormRule(
			actions = {
				"setOptions('predefinedValue', getValue('options'))",
				"setRequired('ddmDataProviderInstanceId', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setRequired('ddmDataProviderInstanceOutput', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setRequired('options', equals(getValue('dataSourceType'), \"manual\"))",
				"setVisible('ddmDataProviderInstanceId', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setVisible('ddmDataProviderInstanceOutput', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setVisible('options', equals(getValue('dataSourceType'), \"manual\"))",
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
							value = {
								"label", "tip", "required", "dataSourceType",
<<<<<<< HEAD
								"options", "ddmDataProviderInstanceId",
								"ddmDataProviderInstanceOutput"
=======
								"options", "ddmDataProviderInstanceId"
>>>>>>> compatible
							}
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
								"name", "showLabel", "repeatable", "multiple"
							}
						)
					}
				)
			}
		)
	}
)
public interface SelectDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(
		label = "%create-list",
<<<<<<< HEAD
		optionLabels = {"%manually", "%from-data-provider", "%from-autofill"},
		optionValues = {"manual", "data-provider", "from-autofill"},
		predefinedValue = "manual", type = "radio"
=======
		optionLabels = {"%manually", "%from-data-provider"},
		optionValues = {"manual", "data-provider"}, predefinedValue = "manual",
		type = "radio"
>>>>>>> compatible
	)
	public String dataSourceType();

	@DDMFormField(
<<<<<<< HEAD
		label = "%choose-a-data-provider",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=getDataProviderInstances"
		},
		type = "select"
	)
	public long ddmDataProviderInstanceId();

	@DDMFormField(
		label = "%choose-an-output-parameter",
		properties = {
			"tooltip=%choose-an-output-parameter-for-a-data-provider-previously-created"
		},
		type = "select"
	)
	public String ddmDataProviderInstanceOutput();

	@DDMFormField(
		label = "%allow-multiple-selections",
		properties = {"showAsSwitcher=true"}
	)
=======
		label = "%choose-a-data-provider", type = "select",
		visibilityExpression = "equals(dataSourceType, \"data-provider\")"
	)
	public long ddmDataProviderInstanceId();

	@DDMFormField(label = "%multiple", properties = {"showAsSwitcher=true"})
>>>>>>> compatible
	public boolean multiple();

	@DDMFormField(
		dataType = "ddm-options", label = "%options",
<<<<<<< HEAD
		properties = {"showLabel=false"}, type = "options"
	)
	public DDMFormFieldOptions options();

	@DDMFormField(
		label = "%predefined-value",
		properties = {
			"placeholder=%enter-a-default-value",
			"tooltip=%enter-a-default-value-that-is-submitted-if-no-other-value-is-entered"
		},
		type = "select"
	)
	@Override
	public LocalizedValue predefinedValue();
=======
		properties = {"showLabel=false"}, required = true, type = "options",
		visibilityExpression = "equals(dataSourceType, \"manual\")"
	)
	public DDMFormFieldOptions options();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public DDMFormFieldValidation validation();
>>>>>>> compatible

}