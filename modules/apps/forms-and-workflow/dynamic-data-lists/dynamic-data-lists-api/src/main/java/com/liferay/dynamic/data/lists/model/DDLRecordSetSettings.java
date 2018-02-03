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

package com.liferay.dynamic.data.lists.model;

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

/**
 * @author Bruno Basto
 */
<<<<<<< HEAD
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setVisible('emailFromAddress', getValue('sendEmailNotification'))",
				"setVisible('emailFromName', getValue('sendEmailNotification'))",
				"setVisible('emailSubject', getValue('sendEmailNotification'))",
				"setVisible('emailToAddress', getValue('sendEmailNotification'))",
				"setVisible('published', FALSE)"
			},
			condition = "TRUE"
		)
	}
)
=======
@DDMForm
>>>>>>> compatible
@DDMFormLayout(
	{
		@DDMFormLayoutPage(
			title = "%form-options",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
<<<<<<< HEAD
								"requireAuthentication", "requireCaptcha",
								"redirectURL", "storageType",
=======
								"requireCaptcha", "redirectURL", "storageType",
>>>>>>> compatible
								"workflowDefinition"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%email-notifications",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"sendEmailNotification", "emailFromName",
								"emailFromAddress", "emailToAddress",
								"emailSubject", "published"
							}
						)
					}
				)
			}
		)
	}
)
public interface DDLRecordSetSettings {

	@DDMFormField(
		label = "%from-address",
		validationErrorMessage = "%please-enter-a-valid-email-address",
<<<<<<< HEAD
		validationExpression = "isEmailAddress(emailFromAddress)"
	)
	public String emailFromAddress();

	@DDMFormField(label = "%from-name")
	public String emailFromName();

	@DDMFormField(label = "%subject")
=======
		validationExpression = "isEmailAddress(emailFromAddress)",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailFromAddress();

	@DDMFormField(
		label = "%from-name",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailFromName();

	@DDMFormField(
		label = "%subject",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
>>>>>>> compatible
	public String emailSubject();

	@DDMFormField(
		label = "%to-address",
<<<<<<< HEAD
		validationErrorMessage = "%please-enter-valid-email-addresses-separated-by-commas",
		validationExpression = "isEmailAddress(emailToAddress)"
	)
	public String emailToAddress();

	@DDMFormField
=======
		validationErrorMessage = "%please-enter-a-valid-email-address",
		validationExpression = "isEmailAddress(emailToAddress)",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailToAddress();

	@DDMFormField(visibilityExpression = "FALSE")
>>>>>>> compatible
	public boolean published();

	@DDMFormField(
		label = "%redirect-url-on-success",
		properties = {"placeholder=%enter-a-valid-url"},
		validationErrorMessage = "%please-enter-a-valid-url",
		validationExpression = "isURL(redirectURL)"
	)
	public String redirectURL();

	@DDMFormField(
<<<<<<< HEAD
		label = "%require-user-authentication", predefinedValue = "false",
		properties = {"showAsSwitcher=true"}
	)
	public default boolean requireAuthentication() {
		return false;
	}

	@DDMFormField(
=======
>>>>>>> compatible
		label = "%require-captcha", properties = {"showAsSwitcher=true"},
		type = "checkbox"
	)
	public boolean requireCaptcha();

	@DDMFormField(
		label = "%send-an-email-notification-for-each-entry",
		properties = {"showAsSwitcher=true"}, type = "checkbox"
	)
	public boolean sendEmailNotification();

	@DDMFormField(
<<<<<<< HEAD
		label = "%select-a-storage-type", predefinedValue = "[\"json\"]",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=ddm-storage-types"
		},
		type = "select"
=======
		label = "%select-a-storage-type",
		properties = {"dataSourceType=manual"}, type = "select"
>>>>>>> compatible
	)
	public String storageType();

	@DDMFormField(
<<<<<<< HEAD
		label = "%select-a-workflow", predefinedValue = "[\"no-workflow\"]",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=workflow-definitions"
		},
=======
		label = "%select-a-workflow", properties = {"dataSourceType=manual"},
>>>>>>> compatible
		type = "select"
	)
	public String workflowDefinition();

}