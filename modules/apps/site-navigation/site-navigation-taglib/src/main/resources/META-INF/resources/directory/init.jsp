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

<%@ page import="com.liferay.portal.util.comparator.GroupNameComparator" %><%@
page import="com.liferay.site.navigation.taglib.servlet.taglib.DirectoryTag" %>

<%
String displayStyle = (String)request.getAttribute("liferay-site-navigation:directory:displayStyle");
String sites = (String)request.getAttribute("liferay-site-navigation:directory:sites");

Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);
%>