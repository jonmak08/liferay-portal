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

package com.liferay.dynamic.data.mapping.form.renderer.internal;

<<<<<<< HEAD
=======
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluationResult;
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluator;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
<<<<<<< HEAD
=======
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingException;
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormTemplateContextFactory;
import com.liferay.dynamic.data.mapping.io.DDMFormFieldTypesJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutJSONSerializer;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
=======
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.GetterUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
<<<<<<< HEAD
import com.liferay.portal.template.soy.utils.SoyHTMLContextValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
=======

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
>>>>>>> compatible
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
<<<<<<< HEAD
import java.util.stream.Collectors;
import java.util.stream.Stream;
=======
>>>>>>> compatible

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

<<<<<<< HEAD
import org.osgi.service.component.annotations.Activate;
=======
>>>>>>> compatible
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class DDMFormTemplateContextFactoryImpl
	implements DDMFormTemplateContextFactory {

<<<<<<< HEAD
	@Activate
	public void activate() {
		_ddmFormTemplateContextFactoryHelper =
			new DDMFormTemplateContextFactoryHelper(
				_ddmDataProviderInstanceService);
	}

=======
>>>>>>> compatible
	@Override
	public Map<String, Object> create(
			DDMForm ddmForm, DDMFormLayout ddmFormLayout,
			DDMFormRenderingContext ddmFormRenderingContext)
		throws PortalException {

		return doCreate(ddmForm, ddmFormLayout, ddmFormRenderingContext);
	}

	@Override
	public Map<String, Object> create(
			DDMForm ddmForm, DDMFormRenderingContext ddmFormRenderingContext)
		throws PortalException {

		return doCreate(
			ddmForm, _ddm.getDefaultDDMFormLayout(ddmForm),
			ddmFormRenderingContext);
	}

	protected void collectResourceBundles(
		Class<?> clazz, List<ResourceBundle> resourceBundles, Locale locale) {

		for (Class<?> interfaceClass : clazz.getInterfaces()) {
			collectResourceBundles(interfaceClass, resourceBundles, locale);
		}

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, clazz.getClassLoader());

		if (resourceBundle != null) {
			resourceBundles.add(resourceBundle);
		}
	}

	protected Map<String, Object> doCreate(
			DDMForm ddmForm, DDMFormLayout ddmFormLayout,
			DDMFormRenderingContext ddmFormRenderingContext)
		throws PortalException {

		Map<String, Object> templateContext = new HashMap<>();

		String containerId = ddmFormRenderingContext.getContainerId();

		if (Validator.isNull(containerId)) {
			containerId = StringUtil.randomId();
		}

		templateContext.put("containerId", containerId);
<<<<<<< HEAD

		templateContext.put(
			"dataProviderSettings",
			_ddmFormTemplateContextFactoryHelper.getDataProviderSettings(
				ddmForm));

		setDDMFormFieldsEvaluableProperty(ddmForm);

		templateContext.put(
			"evaluatorURL", getDDMFormContextProviderServletURL());
=======
		templateContext.put("dataProviderURL", getDDMDataProviderServletURL());
		templateContext.put(
			"definition", _ddmFormJSONSerializer.serialize(ddmForm));

		DDMFormValues ddmFormValues =
			ddmFormRenderingContext.getDDMFormValues();

		if (ddmFormValues != null) {
			removeStaleDDMFormFieldValues(
				ddmForm.getDDMFormFieldsMap(true),
				ddmFormValues.getDDMFormFieldValues());
		}

		Locale locale = ddmFormRenderingContext.getLocale();

		DDMFormEvaluationResult ddmFormEvaluationResult =
			_ddmFormEvaluator.evaluate(
				ddmForm, ddmFormRenderingContext.getDDMFormValues(), locale);

		JSONSerializer jsonSerializer = _jsonFactory.createJSONSerializer();

		templateContext.put(
			"evaluation",
			jsonSerializer.serializeDeep(ddmFormEvaluationResult));

		templateContext.put("evaluatorURL", getDDMFormEvaluatorServletURL());
>>>>>>> compatible

		List<DDMFormFieldType> ddmFormFieldTypes =
			_ddmFormFieldTypeServicesTracker.getDDMFormFieldTypes();

		templateContext.put(
			"fieldTypes",
			_ddmFormFieldTypesJSONSerializer.serialize(ddmFormFieldTypes));

<<<<<<< HEAD
		templateContext.put("groupId", ddmFormRenderingContext.getGroupId());
=======
		templateContext.put(
			"layout", _ddmFormLayoutJSONSerializer.serialize(ddmFormLayout));
>>>>>>> compatible

		List<Object> pages = getPages(
			ddmForm, ddmFormLayout, ddmFormRenderingContext);

		templateContext.put("pages", pages);

		templateContext.put(
			"portletNamespace", ddmFormRenderingContext.getPortletNamespace());
		templateContext.put("readOnly", ddmFormRenderingContext.isReadOnly());

<<<<<<< HEAD
		Locale locale = ddmFormRenderingContext.getLocale();

		if (locale == null) {
			locale = LocaleThreadLocal.getSiteDefaultLocale();
		}

		ResourceBundle resourceBundle = getResourceBundle(locale);

		SoyHTMLContextValue soyHTMLContextValue = new SoyHTMLContextValue(
			getRequiredFieldsWarningMessageHTML(resourceBundle));

		templateContext.put(
			"requiredFieldsWarningMessageHTML", soyHTMLContextValue.getValue());

		templateContext.put("rules", toObjectList(ddmForm.getDDMFormRules()));
=======
		JSONArray readOnlyFieldsJSONArray = getReadOnlyFieldsJSONArray(ddmForm);

		templateContext.put(
			"readOnlyFields", readOnlyFieldsJSONArray.toString());

		ResourceBundle resourceBundle = getResourceBundle(locale);

		templateContext.put(
			"requiredFieldsWarningMessageHTML",
			getRequiredFieldsWarningMessageHTML(resourceBundle));

>>>>>>> compatible
		templateContext.put(
			"showRequiredFieldsWarning",
			ddmFormRenderingContext.isShowRequiredFieldsWarning());

		boolean showSubmitButton = ddmFormRenderingContext.isShowSubmitButton();

		if (ddmFormRenderingContext.isReadOnly()) {
			showSubmitButton = false;
		}

		templateContext.put("showSubmitButton", showSubmitButton);
<<<<<<< HEAD
=======

>>>>>>> compatible
		templateContext.put("strings", getLanguageStringsMap(resourceBundle));

		String submitLabel = GetterUtil.getString(
			ddmFormRenderingContext.getSubmitLabel(),
			LanguageUtil.get(locale, "submit"));

		templateContext.put("submitLabel", submitLabel);

		templateContext.put(
			"templateNamespace", getTemplateNamespace(ddmFormLayout));

<<<<<<< HEAD
		return templateContext;
	}

	protected String getDDMFormContextProviderServletURL() {
		String servletContextPath = getServletContextPath();

		return servletContextPath.concat(
			"/dynamic-data-mapping-form-context-provider/");
=======
		if (ddmFormValues != null) {
			templateContext.put(
				"values",
				_ddmFormValuesJSONSerializer.serialize(ddmFormValues));
		}
		else {
			templateContext.put("values", JSONFactoryUtil.getNullJSON());
		}

		return templateContext;
	}

	protected String getDDMDataProviderServletURL() {
		String servletContextPath = getServletContextPath(
			_ddmDataProviderServlet);

		return servletContextPath.concat(
			"/dynamic-data-mapping-data-provider/");
	}

	protected String getDDMFormEvaluatorServletURL() {
		String servletContextPath = getServletContextPath(
			_ddmFormEvaluatorServlet);

		return servletContextPath.concat(
			"/dynamic-data-mapping-form-evaluator/");
>>>>>>> compatible
	}

	protected Map<String, String> getLanguageStringsMap(
		ResourceBundle resourceBundle) {

		Map<String, String> stringsMap = new HashMap<>();

		stringsMap.put("next", LanguageUtil.get(resourceBundle, "next"));
		stringsMap.put(
			"previous", LanguageUtil.get(resourceBundle, "previous"));

		return stringsMap;
	}

	protected List<Object> getPages(
<<<<<<< HEAD
		DDMForm ddmForm, DDMFormLayout ddmFormLayout,
		DDMFormRenderingContext ddmFormRenderingContext) {

		DDMFormPagesTemplateContextFactory ddmFormPagesTemplateContextFactory =
			new DDMFormPagesTemplateContextFactory(
				ddmForm, ddmFormLayout, ddmFormRenderingContext);

		ddmFormPagesTemplateContextFactory.setDDMFormEvaluator(
			_ddmFormEvaluator);
		ddmFormPagesTemplateContextFactory.setDDMFormFieldTypeServicesTracker(
			_ddmFormFieldTypeServicesTracker);
		ddmFormPagesTemplateContextFactory.setJSONFactory(_jsonFactory);

		return ddmFormPagesTemplateContextFactory.create();
=======
			DDMForm ddmForm, DDMFormLayout ddmFormLayout,
			DDMFormRenderingContext ddmFormRenderingContext)
		throws DDMFormRenderingException {

		Map<String, String> renderedDDMFormFieldsMap =
			getRenderedDDMFormFieldsMap(ddmForm, ddmFormRenderingContext);

		DDMFormLayoutTransformer ddmFormLayoutTransformer =
			new DDMFormLayoutTransformer(
				ddmForm, ddmFormLayout, renderedDDMFormFieldsMap,
				ddmFormRenderingContext.isShowRequiredFieldsWarning(),
				ddmFormRenderingContext.getLocale());

		return ddmFormLayoutTransformer.getPages();
	}

	protected JSONArray getReadOnlyFieldsJSONArray(DDMForm ddmForm) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();

		for (DDMFormField ddmFormField : ddmFormFields) {
			if (ddmFormField.isReadOnly()) {
				jsonArray.put(ddmFormField.getName());
			}
		}

		return jsonArray;
	}

	protected Map<String, String> getRenderedDDMFormFieldsMap(
			DDMForm ddmForm, DDMFormRenderingContext ddmFormRenderingContext)
		throws DDMFormRenderingException {

		DDMFormRendererHelper ddmFormRendererHelper = new DDMFormRendererHelper(
			ddmForm, ddmFormRenderingContext);

		ddmFormRendererHelper.setDDMFormFieldTypeServicesTracker(
			_ddmFormFieldTypeServicesTracker);
		ddmFormRendererHelper.setDDMFormEvaluator(_ddmFormEvaluator);

		return ddmFormRendererHelper.getRenderedDDMFormFieldsMap();
>>>>>>> compatible
	}

	protected String getRequiredFieldsWarningMessageHTML(
		ResourceBundle resourceBundle) {

		StringBundler sb = new StringBundler(3);

		sb.append("<label class=\"required-warning\">");
		sb.append(
			LanguageUtil.format(
				resourceBundle, "all-fields-marked-with-x-are-required",
				"<i class=\"icon-asterisk text-warning\"></i>", false));
		sb.append("</label>");

		return sb.toString();
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		List<ResourceBundle> resourceBundles = new ArrayList<>();

		ResourceBundle portalResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, PortalClassLoaderUtil.getClassLoader());

		resourceBundles.add(portalResourceBundle);

		collectResourceBundles(getClass(), resourceBundles, locale);

		ResourceBundle[] resourceBundlesArray = resourceBundles.toArray(
			new ResourceBundle[resourceBundles.size()]);

		return new AggregateResourceBundle(resourceBundlesArray);
	}

<<<<<<< HEAD
	protected String getServletContextPath() {
		ServletConfig servletConfig =
			_ddmFormContextProviderServlet.getServletConfig();
=======
	protected String getServletContextPath(Servlet servlet) {
		ServletConfig servletConfig = servlet.getServletConfig();
>>>>>>> compatible

		ServletContext servletContext = servletConfig.getServletContext();

		return servletContext.getContextPath();
	}

	protected String getTemplateNamespace(DDMFormLayout ddmFormLayout) {
		String paginationMode = ddmFormLayout.getPaginationMode();

<<<<<<< HEAD
		if (Objects.equals(paginationMode, DDMFormLayout.SETTINGS_MODE)) {
			return "ddm.settings_form";
		}

=======
>>>>>>> compatible
		if (Objects.equals(paginationMode, DDMFormLayout.SINGLE_PAGE_MODE)) {
			return "ddm.simple_form";
		}
		else if (Objects.equals(paginationMode, DDMFormLayout.TABBED_MODE)) {
			return "ddm.tabbed_form";
		}
<<<<<<< HEAD
		else if (Objects.equals(paginationMode, DDMFormLayout.WIZARD_MODE)) {
			return "ddm.wizard_form";
		}
=======
>>>>>>> compatible

		return "ddm.paginated_form";
	}

<<<<<<< HEAD
	protected void setDDMFormFieldsEvaluableProperty(DDMForm ddmForm) {
		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		for (String evaluableDDMFormFieldName :
				_ddmFormTemplateContextFactoryHelper.
					getEvaluableDDMFormFieldNames(ddmForm)) {

			DDMFormField ddmFormField = ddmFormFieldsMap.get(
				evaluableDDMFormFieldName);

			ddmFormField.setProperty("evaluable", true);
		}
	}

	protected Map<String, Object> toMap(DDMFormRule ddmFormRule) {
		Map<String, Object> map = new HashMap<>();

		map.put("actions", ddmFormRule.getActions());
		map.put("condition", ddmFormRule.getCondition());
		map.put("enable", ddmFormRule.isEnabled());

		return map;
	}

	protected List<Object> toObjectList(List<DDMFormRule> ddmFormRules) {
		if (ddmFormRules == null) {
			return Collections.emptyList();
		}

		Stream<DDMFormRule> stream = ddmFormRules.stream();

		return stream.map(
			this::toMap
		).collect(
			Collectors.toList()
		);
=======
	protected void removeStaleDDMFormFieldValues(
		Map<String, DDMFormField> ddmFormFieldsMap,
		List<DDMFormFieldValue> ddmFormFieldValues) {

		Iterator<DDMFormFieldValue> iterator = ddmFormFieldValues.iterator();

		while (iterator.hasNext()) {
			DDMFormFieldValue ddmFormFieldValue = iterator.next();

			if (!ddmFormFieldsMap.containsKey(ddmFormFieldValue.getName())) {
				iterator.remove();
			}

			removeStaleDDMFormFieldValues(
				ddmFormFieldsMap,
				ddmFormFieldValue.getNestedDDMFormFieldValues());
		}
>>>>>>> compatible
	}

	@Reference
	private DDM _ddm;

<<<<<<< HEAD
	@Reference
	private DDMDataProviderInstanceService _ddmDataProviderInstanceService;

	@Reference(
		target = "(osgi.http.whiteboard.servlet.name=com.liferay.dynamic.data.mapping.form.renderer.internal.servlet.DDMFormContextProviderServlet)"
	)
	private Servlet _ddmFormContextProviderServlet;
=======
	@Reference(
		target = "(osgi.http.whiteboard.servlet.name=com.liferay.dynamic.data.mapping.data.provider.internal.servlet.DDMDataProviderServlet)",
		unbind = "-"
	)
	private Servlet _ddmDataProviderServlet;
>>>>>>> compatible

	@Reference
	private DDMFormEvaluator _ddmFormEvaluator;

<<<<<<< HEAD
=======
	@Reference(
		target = "(osgi.http.whiteboard.servlet.name=com.liferay.dynamic.data.mapping.form.evaluator.internal.servlet.DDMFormEvaluatorServlet)",
		unbind = "-"
	)
	private Servlet _ddmFormEvaluatorServlet;

>>>>>>> compatible
	@Reference
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	@Reference
	private DDMFormFieldTypesJSONSerializer _ddmFormFieldTypesJSONSerializer;

	@Reference
	private DDMFormJSONSerializer _ddmFormJSONSerializer;

	@Reference
	private DDMFormLayoutJSONSerializer _ddmFormLayoutJSONSerializer;

<<<<<<< HEAD
	private DDMFormTemplateContextFactoryHelper
		_ddmFormTemplateContextFactoryHelper;
=======
	@Reference
	private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;
>>>>>>> compatible

	@Reference
	private JSONFactory _jsonFactory;

}