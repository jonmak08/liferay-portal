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

package com.liferay.dynamic.data.mapping.render;

import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.MapUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
=======

import java.util.Locale;
>>>>>>> compatible

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pablo Carvalho
 */
public class DDMFormFieldRenderingContext {

<<<<<<< HEAD
	/**
	 * @deprecated As of 3.5.0, with no direct replacement
	 */
	@Deprecated
=======
>>>>>>> compatible
	public String getChildElementsHTML() {
		return _childElementsHTML;
	}

	public Fields getFields() {
		return _fields;
	}

	public HttpServletRequest getHttpServletRequest() {
		return _httpServletRequest;
	}

	public HttpServletResponse getHttpServletResponse() {
		return _httpServletResponse;
	}

	public String getLabel() {
<<<<<<< HEAD
		return MapUtil.getString(_properties, "label");
=======
		return _label;
>>>>>>> compatible
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getMode() {
		return _mode;
	}

	public String getName() {
<<<<<<< HEAD
		return MapUtil.getString(_properties, "name");
=======
		return _name;
>>>>>>> compatible
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getPortletNamespace() {
		return _portletNamespace;
	}

<<<<<<< HEAD
	public Map<String, Object> getProperties() {
		return _properties;
	}

	public Object getProperty(String name) {
		return _properties.get(name);
	}

	public String getTip() {
		return MapUtil.getString(_properties, "tip");
	}

	public String getValue() {
		return MapUtil.getString(_properties, "value");
	}

	public boolean isReadOnly() {
		return MapUtil.getBoolean(_properties, "readOnly");
	}

	public boolean isRequired() {
		return MapUtil.getBoolean(_properties, "required");
=======
	public String getTip() {
		return _tip;
	}

	public String getValue() {
		return _value;
	}

	public boolean isReadOnly() {
		return _readOnly;
	}

	public boolean isRequired() {
		return _required;
>>>>>>> compatible
	}

	public boolean isShowEmptyFieldLabel() {
		return _showEmptyFieldLabel;
	}

	public boolean isVisible() {
<<<<<<< HEAD
		return MapUtil.getBoolean(_properties, "visible");
	}

	/**
	 * @deprecated As of 3.5.0, with no direct replacement
	 */
	@Deprecated
=======
		return _visible;
	}

>>>>>>> compatible
	public void setChildElementsHTML(String childElementsHTML) {
		_childElementsHTML = childElementsHTML;
	}

	public void setField(Field field) {
		Fields fields = new Fields();

		fields.put(field);

		_fields = fields;
	}

	public void setFields(Fields fields) {
		_fields = fields;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;
	}

	public void setHttpServletResponse(
		HttpServletResponse httpServletResponse) {

		_httpServletResponse = httpServletResponse;
	}

	public void setLabel(String label) {
<<<<<<< HEAD
		_properties.put("label", label);
=======
		_label = label;
>>>>>>> compatible
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setMode(String mode) {
		_mode = mode;
	}

	public void setName(String name) {
<<<<<<< HEAD
		_properties.put("name", name);
=======
		_name = name;
>>>>>>> compatible
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public void setPortletNamespace(String portletNamespace) {
		_portletNamespace = portletNamespace;
	}

<<<<<<< HEAD
	public void setProperties(Map<String, Object> properties) {
		_properties.putAll(properties);
	}

	public void setProperty(String name, Object value) {
		_properties.put(name, value);
	}

	public void setReadOnly(boolean readOnly) {
		_properties.put("readOnly", readOnly);
	}

	public void setRequired(boolean required) {
		_properties.put("required", required);
=======
	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;
	}

	public void setRequired(boolean required) {
		_required = required;
>>>>>>> compatible
	}

	public void setShowEmptyFieldLabel(boolean showEmptyFieldLabel) {
		_showEmptyFieldLabel = showEmptyFieldLabel;
	}

	public void setTip(String tip) {
<<<<<<< HEAD
		_properties.put("tip", tip);
	}

	public void setValue(String value) {
		_properties.put("value", value);
	}

	public void setVisible(boolean visible) {
		_properties.put("visible", visible);
=======
		_tip = tip;
	}

	public void setValue(String value) {
		_value = value;
	}

	public void setVisible(boolean visible) {
		_visible = visible;
>>>>>>> compatible
	}

	private String _childElementsHTML;
	private Fields _fields;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
<<<<<<< HEAD
	private Locale _locale;
	private String _mode;
	private String _namespace;
	private String _portletNamespace;
	private final Map<String, Object> _properties = new HashMap<>();
	private boolean _showEmptyFieldLabel;
=======
	private String _label;
	private Locale _locale;
	private String _mode;
	private String _name;
	private String _namespace;
	private String _portletNamespace;
	private boolean _readOnly;
	private boolean _required;
	private boolean _showEmptyFieldLabel;
	private String _tip;
	private String _value;
	private boolean _visible;
>>>>>>> compatible

}