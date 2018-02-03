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

package com.liferay.dynamic.data.mapping.type.key.value.internal;

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
=======
import java.util.Locale;
>>>>>>> compatible
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
<<<<<<< HEAD
import org.osgi.service.component.annotations.Reference;
=======
>>>>>>> compatible

/**
 * @author Bruno Basto
 */
@Component(
<<<<<<< HEAD
	immediate = true, property = "ddm.form.field.type.name=key_value",
=======
	immediate = true, property = "ddm.form.field.type.name=key-value",
>>>>>>> compatible
	service = DDMFormFieldRenderer.class
)
public class KeyValueDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
<<<<<<< HEAD
		return "DDMKeyValue.render";
=======
		return "ddm.key_value";
>>>>>>> compatible
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
<<<<<<< HEAD
			"/META-INF/resources/key-value.soy");
=======
			"/META-INF/resources/key_value.soy");
>>>>>>> compatible
	}

	@Deactivate
	protected void deactivate() {
		_templateResource = null;
	}

	@Override
	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

<<<<<<< HEAD
		Map<String, Object> parameters =
			keyValueDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	@Reference
	protected KeyValueDDMFormFieldTemplateContextContributor
		keyValueDDMFormFieldTemplateContextContributor;
=======
		template.put("displayStyle", ddmFormField.getProperty("displayStyle"));

		LocalizedValue placeholder = (LocalizedValue)ddmFormField.getProperty(
			"placeholder");

		Locale locale = ddmFormFieldRenderingContext.getLocale();

		template.put("placeholder", getValueString(placeholder, locale));

		LocalizedValue tooltip = (LocalizedValue)ddmFormField.getProperty(
			"tooltip");

		template.put("tooltip", getValueString(tooltip, locale));
	}
>>>>>>> compatible

	private TemplateResource _templateResource;

}