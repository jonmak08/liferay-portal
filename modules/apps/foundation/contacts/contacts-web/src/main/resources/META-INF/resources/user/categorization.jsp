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
User selUser = (User)request.getAttribute("user.selUser");
%>

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<<<<<<< HEAD
<liferay-asset:asset-categories-error />

<liferay-asset:asset-tags-error />
=======
<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />
>>>>>>> compatible

<h3><liferay-ui:message key="categorization" /></h3>

<aui:fieldset>
<<<<<<< HEAD
	<liferay-asset:asset-categories-selector className="<%= User.class.getName() %>" classPK="<%= (selUser != null) ? selUser.getPrimaryKey() : 0 %>" />

	<liferay-asset:asset-tags-selector className="<%= User.class.getName() %>" classPK="<%= (selUser != null) ? selUser.getPrimaryKey() : 0 %>" />
=======
	<aui:input name="categories" type="assetCategories" />

	<aui:input name="tags" type="assetTags" />
>>>>>>> compatible
</aui:fieldset>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {

		<%
		StringBundler sb = new StringBundler();

		if (selUser.getComments() != null) {
			sb.append(selUser.getComments());
		}

		if (selUser.getJobTitle() != null) {
			sb.append(StringPool.SPACE);
			sb.append(selUser.getJobTitle());
		}
		%>

		return '<%= HtmlUtil.escape(HtmlUtil.replaceNewLine(sb.toString())) %>'
	}
</aui:script>