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

<%@ include file="/drop_here_info/init.jsp" %>

<%
String message = GetterUtil.getString((String)request.getAttribute("liferay-item-selector:drop-here-info:message"));
%>

<liferay-util:html-top>
	<link href="<%= ServletContextUtil.getContextPath() + "/drop_here_info/css/main.css" %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<div class="drop-here-info">
	<div class="drop-here-indicator">
		<div class="drop-icons">
			<span aria-hidden="true" class="glyphicon glyphicon-picture"></span>

			<span aria-hidden="true" class="glyphicon glyphicon-picture"></span>

			<span aria-hidden="true" class="glyphicon glyphicon-picture"></span>
		</div>

		<div class="drop-text">
			<liferay-ui:message key="<%= message %>" />
		</div>
	</div>
</div>