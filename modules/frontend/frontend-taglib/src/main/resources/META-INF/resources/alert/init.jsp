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

@generated
--%>

<%@ include file="/init.jsp" %>

<%
boolean animated = GetterUtil.getBoolean(String.valueOf(request.getAttribute("frontend:alert:animated")), false);
boolean closeable = GetterUtil.getBoolean(String.valueOf(request.getAttribute("frontend:alert:closeable")), true);
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("frontend:alert:cssClass"));
boolean destroyOnHide = GetterUtil.getBoolean(String.valueOf(request.getAttribute("frontend:alert:destroyOnHide")), false);
java.lang.Number duration = GetterUtil.getNumber(String.valueOf(request.getAttribute("frontend:alert:duration")), 0.15);
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("frontend:alert:id"));
java.lang.String type = GetterUtil.getString((java.lang.String)request.getAttribute("frontend:alert:type"), "info");
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("frontend:alert:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("frontend:alert:scopedAttributes");
%>

<%@ include file="/alert/init-ext.jspf" %>