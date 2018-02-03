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
<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="categorization" />

<liferay-asset:asset-categories-error />

<liferay-asset:asset-tags-error />

<aui:fieldset>
	<liferay-asset:asset-categories-selector className="<%= User.class.getName() %>" classPK="<%= (selUser != null) ? selUser.getPrimaryKey() : 0 %>" />

	<liferay-asset:asset-tags-selector className="<%= User.class.getName() %>" classPK="<%= (selUser != null) ? selUser.getPrimaryKey() : 0 %>" />
=======
<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<aui:fieldset>
	<aui:input name="categories" type="assetCategories" />

	<aui:input name="tags" type="assetTags" />
>>>>>>> compatible
</aui:fieldset>