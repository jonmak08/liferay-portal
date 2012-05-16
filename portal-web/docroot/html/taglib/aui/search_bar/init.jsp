<%--
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

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:search-bar:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:search-bar:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String buttonValue = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:buttonValue"), "search");
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:cssClass"));
java.lang.String formAction = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:formAction"));
java.lang.String formMethod = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:formMethod"), "get");
java.lang.String formName = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:formName"), "fm");
java.lang.String formRedirectURL = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:formRedirectURL"));
java.lang.String formHeading = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:formHeading"), "search");
java.lang.String inputId = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:inputId"));
boolean inputInlineField = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:search-bar:inputInlineField")), true);
java.lang.String inputLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:inputLabel"));
java.lang.String inputName = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:inputName"), "keywords");
java.lang.String inputSize = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:inputSize"), "30");
java.lang.String inputTitle = GetterUtil.getString((java.lang.String)request.getAttribute("aui:search-bar:inputTitle"), "search");
java.lang.Object inputValue = (java.lang.Object)request.getAttribute("aui:search-bar:inputValue");
boolean useAutoFocus = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:search-bar:useAutoFocus")), false);
boolean useNamespace = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:search-bar:useNamespace")), true);

_updateOptions(_options, "buttonValue", buttonValue);
_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "formAction", formAction);
_updateOptions(_options, "formMethod", formMethod);
_updateOptions(_options, "formName", formName);
_updateOptions(_options, "formRedirectURL", formRedirectURL);
_updateOptions(_options, "formHeading", formHeading);
_updateOptions(_options, "inputId", inputId);
_updateOptions(_options, "inputInlineField", inputInlineField);
_updateOptions(_options, "inputLabel", inputLabel);
_updateOptions(_options, "inputName", inputName);
_updateOptions(_options, "inputSize", inputSize);
_updateOptions(_options, "inputTitle", inputTitle);
_updateOptions(_options, "inputValue", inputValue);
_updateOptions(_options, "useAutoFocus", useAutoFocus);
_updateOptions(_options, "useNamespace", useNamespace);
%>

<%@ include file="/html/taglib/aui/search_bar/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:search-bar:";
%>