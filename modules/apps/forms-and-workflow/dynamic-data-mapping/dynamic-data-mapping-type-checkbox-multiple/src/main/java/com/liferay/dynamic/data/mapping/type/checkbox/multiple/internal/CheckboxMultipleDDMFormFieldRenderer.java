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

package com.liferay.dynamic.data.mapping.type.checkbox.multiple.internal;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
<<<<<<< HEAD
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;

=======
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
>>>>>>> compatible
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
<<<<<<< HEAD
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
=======
import org.osgi.service.component.annotations.Reference;

/**
 * @author  Dylan Rebelak
>>>>>>> compatible
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox_multiple",
	service = DDMFormFieldRenderer.class
)
public class CheckboxMultipleDDMFormFieldRenderer
	extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
<<<<<<< HEAD
		return "DDMCheckboxMultiple.render";
=======
		return "ddm.checkbox_multiple";
>>>>>>> compatible
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/checkbox-multiple.soy");
	}

<<<<<<< HEAD
	@Deactivate
	protected void deactivate() {
		_templateResource = null;
=======
	protected List<Object> getOptions(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		CheckboxMultipleDDMFormFieldContextHelper
			checkboxMultipleDDMFormFieldContextHelper =
				new CheckboxMultipleDDMFormFieldContextHelper(
					jsonFactory, ddmFormField.getDDMFormFieldOptions(),
					ddmFormFieldRenderingContext.getValue(),
					ddmFormField.getPredefinedValue(),
					ddmFormFieldRenderingContext.getLocale());

		return checkboxMultipleDDMFormFieldContextHelper.getOptions();
>>>>>>> compatible
	}

	@Override
	protected void populateRequiredContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		super.populateRequiredContext(
			template, ddmFormField, ddmFormFieldRenderingContext);

<<<<<<< HEAD
		Map<String, Object> parameters =
			checkboxMultipleDDMFormFieldTemplateContextContributor.
				getParameters(ddmFormField, ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	@Reference
	protected CheckboxMultipleDDMFormFieldTemplateContextContributor
		checkboxMultipleDDMFormFieldTemplateContextContributor;
=======
		template.put(
			"inline",
			GetterUtil.getBoolean(ddmFormField.getProperty("inline")));

		template.put(
			"options", getOptions(ddmFormField, ddmFormFieldRenderingContext));

		template.put(
			"showAsSwitcher",
			GetterUtil.getBoolean(ddmFormField.getProperty("showAsSwitcher")));
	}

	@Reference
	protected JSONFactory jsonFactory;
>>>>>>> compatible

	private TemplateResource _templateResource;

}