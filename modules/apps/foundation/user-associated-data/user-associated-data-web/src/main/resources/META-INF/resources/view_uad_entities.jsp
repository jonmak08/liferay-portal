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
ViewUADEntitiesDisplay viewUADEntitiesDisplay = (ViewUADEntitiesDisplay)request.getAttribute(UADWebKeys.VIEW_UAD_ENTITIES_DISPLAY);

UADEntityDisplay uadEntityDisplay = viewUADEntitiesDisplay.getUADEntityDisplay();

SearchContainer uadEntitySearchContainer = viewUADEntitiesDisplay.getSearchContainer();

PortletURL backURL = renderResponse.createRenderURL();

backURL.setParameter("mvcRenderCommandName", "/view_uad_applications_summary");
backURL.setParameter("p_u_i_d", String.valueOf(selectedUser.getUserId()));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure"), " - ", uadEntityDisplay.getUADEntityTypeName()));
%>

<clay:navigation-bar
	items="<%= viewUADEntitiesDisplay.getNavigationItems() %>"
/>

<liferay-frontend:management-bar
	searchContainerId="UADEntities"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-sidenav-toggler-button
			icon="info-circle"
			label="info"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<div class="closed container-fluid container-fluid-max-xl sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= true %>" id="/entity_type_sidebar" var="entityTypeSidebarURL" />

	<liferay-frontend:sidebar-panel
		resourceURL="<%= entityTypeSidebarURL %>"
		searchContainerId="UADEntities"
	>
		<%@ include file="/uad_entity_type_sidebar.jspf" %>
	</liferay-frontend:sidebar-panel>

	<div class="sidenav-content">
		<liferay-ui:search-container
			emptyResultsMessage="no-entities-remain-of-this-type"
			id="UADEntities"
			searchContainer="<%= viewUADEntitiesDisplay.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.user.associated.data.entity.UADEntity"
				escapedModel="<%= true %>"
				keyProperty="name"
				modelVar="uadEntity"
			>
				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					name="entity-id"
					property="UADEntityId"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					href="<%= uadEntityDisplay.getEditURL(uadEntity, liferayPortletRequest, liferayPortletResponse) %>"
					name="edit-url"
					value="<%= uadEntityDisplay.getEditURL(uadEntity, liferayPortletRequest, liferayPortletResponse) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					name="nonanonymizable-fields"
					value="<%= uadEntityDisplay.getUADEntityNonanonymizableFieldValues(uadEntity) %>"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="entry-action-column"
					path="/uad_entity_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>

<%@ include file="/action/confirm_action_js.jspf" %>