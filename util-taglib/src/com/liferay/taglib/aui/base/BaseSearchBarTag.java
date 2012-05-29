/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseSearchBarTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getButtonValue() {
		return _buttonValue;
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public java.lang.String getFormAction() {
		return _formAction;
	}

	public java.lang.String getFormMethod() {
		return _formMethod;
	}

	public java.lang.String getFormName() {
		return _formName;
	}

	public java.lang.String getFormRedirectURL() {
		return _formRedirectURL;
	}

	public java.lang.String getFormHeading() {
		return _formHeading;
	}

	public java.lang.String getInputId() {
		return _inputId;
	}

	public boolean getInputInlineField() {
		return _inputInlineField;
	}

	public java.lang.String getInputLabel() {
		return _inputLabel;
	}

	public java.lang.String getInputName() {
		return _inputName;
	}

	public java.lang.String getInputSize() {
		return _inputSize;
	}

	public java.lang.String getInputTitle() {
		return _inputTitle;
	}

	public java.lang.Object getInputValue() {
		return _inputValue;
	}

	public boolean getUseAutoFocus() {
		return _useAutoFocus;
	}

	public boolean getUseNamespace() {
		return _useNamespace;
	}

	public void setButtonValue(java.lang.String buttonValue) {
		_buttonValue = buttonValue;

		setScopedAttribute("buttonValue", buttonValue);
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setFormAction(java.lang.String formAction) {
		_formAction = formAction;

		setScopedAttribute("formAction", formAction);
	}

	public void setFormMethod(java.lang.String formMethod) {
		_formMethod = formMethod;

		setScopedAttribute("formMethod", formMethod);
	}

	public void setFormName(java.lang.String formName) {
		_formName = formName;

		setScopedAttribute("formName", formName);
	}

	public void setFormRedirectURL(java.lang.String formRedirectURL) {
		_formRedirectURL = formRedirectURL;

		setScopedAttribute("formRedirectURL", formRedirectURL);
	}

	public void setFormHeading(java.lang.String formHeading) {
		_formHeading = formHeading;

		setScopedAttribute("formHeading", formHeading);
	}

	public void setInputId(java.lang.String inputId) {
		_inputId = inputId;

		setScopedAttribute("inputId", inputId);
	}

	public void setInputInlineField(boolean inputInlineField) {
		_inputInlineField = inputInlineField;

		setScopedAttribute("inputInlineField", inputInlineField);
	}

	public void setInputLabel(java.lang.String inputLabel) {
		_inputLabel = inputLabel;

		setScopedAttribute("inputLabel", inputLabel);
	}

	public void setInputName(java.lang.String inputName) {
		_inputName = inputName;

		setScopedAttribute("inputName", inputName);
	}

	public void setInputSize(java.lang.String inputSize) {
		_inputSize = inputSize;

		setScopedAttribute("inputSize", inputSize);
	}

	public void setInputTitle(java.lang.String inputTitle) {
		_inputTitle = inputTitle;

		setScopedAttribute("inputTitle", inputTitle);
	}

	public void setInputValue(java.lang.Object inputValue) {
		_inputValue = inputValue;

		setScopedAttribute("inputValue", inputValue);
	}

	public void setUseAutoFocus(boolean useAutoFocus) {
		_useAutoFocus = useAutoFocus;

		setScopedAttribute("useAutoFocus", useAutoFocus);
	}

	public void setUseNamespace(boolean useNamespace) {
		_useNamespace = useNamespace;

		setScopedAttribute("useNamespace", useNamespace);
	}

	@Override
	protected void cleanUp() {
		_buttonValue = "search";
		_cssClass = null;
		_formAction = null;
		_formMethod = "get";
		_formName = "fm";
		_formRedirectURL = null;
		_formHeading = "search";
		_inputId = null;
		_inputInlineField = true;
		_inputLabel = null;
		_inputName = "keywords";
		_inputSize = "30";
		_inputTitle = "search";
		_inputValue = null;
		_useAutoFocus = false;
		_useNamespace = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "buttonValue", _buttonValue);
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "formAction", _formAction);
		setNamespacedAttribute(request, "formMethod", _formMethod);
		setNamespacedAttribute(request, "formName", _formName);
		setNamespacedAttribute(request, "formRedirectURL", _formRedirectURL);
		setNamespacedAttribute(request, "formHeading", _formHeading);
		setNamespacedAttribute(request, "inputId", _inputId);
		setNamespacedAttribute(request, "inputInlineField", _inputInlineField);
		setNamespacedAttribute(request, "inputLabel", _inputLabel);
		setNamespacedAttribute(request, "inputName", _inputName);
		setNamespacedAttribute(request, "inputSize", _inputSize);
		setNamespacedAttribute(request, "inputTitle", _inputTitle);
		setNamespacedAttribute(request, "inputValue", _inputValue);
		setNamespacedAttribute(request, "useAutoFocus", _useAutoFocus);
		setNamespacedAttribute(request, "useNamespace", _useNamespace);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:search-bar:";

	private static final String _PAGE =
		"/html/taglib/aui/search_bar/page.jsp";

	private java.lang.String _buttonValue = "search";
	private java.lang.String _cssClass = null;
	private java.lang.String _formAction = null;
	private java.lang.String _formMethod = "get";
	private java.lang.String _formName = "fm";
	private java.lang.String _formRedirectURL = null;
	private java.lang.String _formHeading = "search";
	private java.lang.String _inputId = null;
	private boolean _inputInlineField = true;
	private java.lang.String _inputLabel = null;
	private java.lang.String _inputName = "keywords";
	private java.lang.String _inputSize = "30";
	private java.lang.String _inputTitle = "search";
	private java.lang.Object _inputValue = null;
	private boolean _useAutoFocus = false;
	private boolean _useNamespace = true;

}