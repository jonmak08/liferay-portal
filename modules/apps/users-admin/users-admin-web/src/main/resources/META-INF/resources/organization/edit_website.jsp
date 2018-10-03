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
String redirect = ParamUtil.getString(request, "redirect");
long websiteId = ParamUtil.getLong(request, "websiteId", 0L);

Website website = null;

if (websiteId > 0L) {
	website = WebsiteServiceUtil.getWebsite(websiteId);
}
%>

<div class="card-horizontal main-content-card">
	<aui:form action="<%= redirect %>" cssClass="container-fluid container-fluid-max-xl container-form-lg" method="post" name="websiteFm">
		<aui:model-context bean="<%= website %>" model="<%= Website.class %>" />

		<aui:input name="websiteId" type="hidden" value="<%= websiteId %>" />

		<aui:input checked="<%= (website != null)? website.isPrimary() : false %>" id="websitePrimary" label="make-primary" name="websitePrimary" type="checkbox" />

		<aui:select inlineField="<%= true %>" label="type" listType="<%= Organization.class.getName() + ListTypeConstants.WEBSITE %>" name="websiteTypeId" />

		<aui:input fieldParam="websiteUrl" id="websiteUrl" name="url" required="<%= true %>" />
	</aui:form>
</div>