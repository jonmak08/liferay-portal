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

<%@ page import="com.liferay.portal.theme.NavItem" %><%@
page import="com.liferay.portlet.dynamicdatamapping.DDMTemplate" %>

<%
String bulletStyle = StringUtil.toLowerCase(((String)request.getAttribute("liferay-site-navigation:navigation:bulletStyle")));
String displayStyle = GetterUtil.getString((String)request.getAttribute("liferay-site-navigation:navigation:displayStyle"));
long displayStyleGroupId = GetterUtil.getLong((String)request.getAttribute("liferay-site-navigation:navigation:displayStyleGroupId"));
String headerType = (String)request.getAttribute("liferay-site-navigation:navigation:headerType");
String includedLayouts = (String)request.getAttribute("liferay-site-navigation:navigation:includedLayouts");
List<NavItem> navItems = (List)request.getAttribute("liferay-site-navigation:navigation:navItems");
boolean nestedChildren = GetterUtil.getBoolean((String)request.getAttribute("liferay-site-navigation:navigation:nestedChildren"));
boolean preview = GetterUtil.getBoolean((String)request.getAttribute("liferay-site-navigation:navigation:preview"));
int rootLayoutLevel = GetterUtil.getInteger((String)request.getAttribute("liferay-site-navigation:navigation:rootLayoutLevel"));
String rootLayoutType = (String)request.getAttribute("liferay-site-navigation:navigation:rootLayoutType");
%>