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
OrganizationScreenNavigationDisplayContext organizationScreenNavigationDisplayContext = (OrganizationScreenNavigationDisplayContext)request.getAttribute(UsersAdminWebKeys.ORGANIZATION_SCREEN_NAVIGATION_DISPLAY_CONTEXT);
%>

<aui:form action="<%= organizationScreenNavigationDisplayContext.getEditOrganizationActionURL() %>" cssClass="container-fluid container-fluid-max-xl" data-senna-off="true" method="post" name="fm">
	<aui:fieldset-group markupView="lexicon">
		<div class="sheet">
			<h2 class="sheet-title"><%= organizationScreenNavigationDisplayContext.getFormLabel() %></h2>

			<div class="sheet-section">
				<liferay-util:include page="<%= organizationScreenNavigationDisplayContext.getJspPath() %>" servletContext="<%= application %>" />
			</div>

			<c:if test="<%= organizationScreenNavigationDisplayContext.isShowControls() %>">
				<div class="sheet-footer">
					<aui:button primary="<%= true %>" type="submit" />

					<aui:button href="<%= organizationScreenNavigationDisplayContext.getBackURL() %>" type="cancel" />
				</div>
			</c:if>
		</div>
	</aui:fieldset-group>
</aui:form>