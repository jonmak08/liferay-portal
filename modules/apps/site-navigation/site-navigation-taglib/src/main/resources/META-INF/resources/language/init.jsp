<%--
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
--%>

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry" %><%@
page import="com.liferay.portlet.dynamicdatamapping.DDMTemplate" %>

<%
String formName = (String)request.getAttribute("liferay-ui:language:formName");
String formAction = (String)request.getAttribute("liferay-ui:language:formAction");
String displayStyle = GetterUtil.getString((String)request.getAttribute("liferay-ui:language:displayStyle"));
long displayStyleGroupId = GetterUtil.getLong(request.getAttribute("liferay-ui:language:displayStyleGroupId"), scopeGroupId);
List<LanguageEntry> languageEntries = (List<LanguageEntry>)request.getAttribute("liferay-ui:language:languageEntries");
String name = (String)request.getAttribute("liferay-ui:language:name");
%>