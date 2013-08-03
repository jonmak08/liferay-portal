<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/roles_admin/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/roles_admin/view");

pageContext.setAttribute("portletURL", portletURL);

String portletURLString = portletURL.toString();
%>

<liferay-ui:error exception="<%= RequiredRoleException.class %>" message="you-cannot-delete-a-system-role" />

<aui:form action="<%= portletURLString %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<liferay-util:include page="/html/portlet/roles_admin/toolbar.jsp" />

	<%
	RoleSearch searchContainer = new RoleSearch(renderRequest, portletURL);

	List headerNames = searchContainer.getHeaderNames();

	headerNames.add(StringPool.BLANK);
	%>

	<liferay-ui:search-form
		page="/html/portlet/roles_admin/role_search.jsp"
		searchContainer="<%= searchContainer %>"
	/>

	<%
	RoleSearchTerms searchTerms = (RoleSearchTerms)searchContainer.getSearchTerms();

	int total = RoleLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getTypesObj());

	searchContainer.setTotal(total);

	List results = RoleLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getTypesObj(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

	searchContainer.setResults(results);

	portletURL.setParameter(searchContainer.getCurParam(), String.valueOf(searchContainer.getCur()));
	%>

	<aui:input name="rolesRedirect" type="hidden" value="<%= portletURL.toString() %>" />

	<div class="separator"><!-- --></div>

	<%
	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		Role role = (Role)results.get(i);

		role = role.toEscapedModel();

		ResultRow row = new ResultRow(role, role.getRoleId(), i);

		PortletURL rowURL = null;

		if (RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.UPDATE)) {
			rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/roles_admin/edit_role");
			rowURL.setParameter("redirect", searchContainer.getIteratorURL().toString());
			rowURL.setParameter("roleId", String.valueOf(role.getRoleId()));
		}

		// Name

		row.addText(role.getTitle(locale), rowURL);

		// Type

		row.addText(LanguageUtil.get(pageContext, role.getTypeLabel()), rowURL);

		// Subtype

		if ((PropsValues.ROLES_ORGANIZATION_SUBTYPES.length > 0) ||
			(PropsValues.ROLES_REGULAR_SUBTYPES.length > 0) ||
			(PropsValues.ROLES_SITE_SUBTYPES.length > 0)) {

			row.addText(LanguageUtil.get(pageContext, role.getSubtype()), rowURL);
		}

		// Description

		row.addText(role.getDescription(locale), rowURL);

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/roles_admin/role_action.jsp");

		// CSS

		row.setClassName(RolesAdminUtil.getCssClassName(role));
		row.setClassHoverName(RolesAdminUtil.getCssClassName(role));

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>