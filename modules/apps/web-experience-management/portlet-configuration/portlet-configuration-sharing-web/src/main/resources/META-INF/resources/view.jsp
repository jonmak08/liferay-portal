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

<%
String netvibesURL = ParamUtil.getString(request, "netvibesURL");
String widgetURL = ParamUtil.getString(request, "widgetURL");
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(widgetURL) %>">
		<p>
			<liferay-ui:message key="share-this-application-on-any-website" />
		</p>

		<textarea class="lfr-textarea" onClick="this.select();">&lt;script src=&quot;<%= themeDisplay.getPortalURL() %><%= PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_JS) %>/liferay/widget.js&quot;&gt;&lt;/script&gt;
&lt;script&gt;
Liferay.Widget({ url: &#x27;<%= HtmlUtil.escape(widgetURL) %>&#x27;});
&lt;/script&gt;</textarea>
	</c:when>
	<c:when test="<%= Validator.isNotNull(netvibesURL) %>">
		<p>
			<aui:a href="http://eco.netvibes.com/apps/submit" target="_blank"><liferay-ui:message key="add-this-application-to-netvibes" /></aui:a>
		</p>

		<aui:input label="" name="netvibesURL" type="resource" value="<%= netvibesURL %>" />
	</c:when>
</c:choose>