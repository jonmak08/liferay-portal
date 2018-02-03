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

package com.liferay.dynamic.data.mapping.type.checkbox.internal;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
<<<<<<< HEAD
=======
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
<<<<<<< HEAD

import java.util.Map;
=======
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
>>>>>>> compatible

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
<<<<<<< HEAD
import org.osgi.service.component.annotations.Reference;
=======
>>>>>>> compatible

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox",
	service = DDMFormFieldRenderer.class
)
public class CheckboxDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
<<<<<<< HEAD
		return "DDMCheckbox.render";
=======
		return "ddm.checkbox";
>>>>>>> compatible
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/checkbox.soy");
	}

	@Deactivate
	protected void deactivate() {
		_templateResource = null;
	}

<<<<<<< HEAD
=======
	protected String getStatus(String value, String predefinedValue) {
		String status = StringPool.BLANK;

		if (Objects.equals(value, "true")) {
			status = "checked";
		}
		else if (Objects.equals(predefinedValue, "true")) {
			status = "checked";
		}

		return status;
	}

>>>>>>> compatible
	@Override
	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

<<<<<<< HEAD
		Map<String, Object> parameters =
			checkboxDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	@Reference
	protected CheckboxDDMFormFieldTemplateContextContributor
		checkboxDDMFormFieldTemplateContextContributor;
=======
		template.put(
			"showAsSwitcher", ddmFormField.getProperty("showAsSwitcher"));

		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		Locale locale = ddmFormFieldRenderingContext.getLocale();

		String status = getStatus(
			ddmFormFieldRenderingContext.getValue(),
			predefinedValue.getString(locale));

		template.put("status", status);
	}
>>>>>>> compatible

	private TemplateResource _templateResource;

}