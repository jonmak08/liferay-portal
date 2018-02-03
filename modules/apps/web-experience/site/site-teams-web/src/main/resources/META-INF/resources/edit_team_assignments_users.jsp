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
<<<<<<< HEAD
EditSiteTeamAssignmentsDisplayContext editSiteTeamAssignmentsDisplayContext = new EditSiteTeamAssignmentsDisplayContext(renderRequest, renderResponse, request);
=======
String tabs1 = (String)request.getAttribute("edit_team_assignments.jsp-tabs1");

Team team = (Team)request.getAttribute("edit_team_assignments.jsp-team");
>>>>>>> compatible

String displayStyle = portalPreferences.getValue(SiteTeamsPortletKeys.SITE_TEAMS, "display-style", "icon");
String orderByCol = ParamUtil.getString(request, "orderByCol", "first-name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

<<<<<<< HEAD
SearchContainer userSearchContainer = new UserSearch(renderRequest, editSiteTeamAssignmentsDisplayContext.getEditTeamAssignmentsURL());
=======
PortletURL portletURL = (PortletURL)request.getAttribute("edit_team_assignments.jsp-portletURL");

SearchContainer userSearchContainer = new UserSearch(renderRequest, portletURL);
>>>>>>> compatible

UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

userSearchContainer.setEmptyResultsMessageCssClass(searchTerms.isSearch() ? StringPool.BLANK : "taglib-empty-result-message-header-has-plus-btn");

LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

userParams.put("inherit", Boolean.TRUE);
<<<<<<< HEAD
userParams.put("usersTeams", editSiteTeamAssignmentsDisplayContext.getTeamId());
=======
userParams.put("usersTeams", team.getTeamId());
>>>>>>> compatible

int usersCount = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams);

userSearchContainer.setTotal(usersCount);

List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams, userSearchContainer.getStart(), userSearchContainer.getEnd(), userSearchContainer.getOrderByComparator());

userSearchContainer.setResults(users);

RowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);
%>

<<<<<<< HEAD
<clay:navigation-bar
	inverted="<%= true %>"
	items="<%= editSiteTeamAssignmentsDisplayContext.getNavigationItems() %>"
/>
=======
<liferay-util:include page="/navigation_bar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchEnabled" value="<%= String.valueOf((usersCount > 0) || searchTerms.isSearch()) %>" />
</liferay-util:include>
>>>>>>> compatible

<liferay-frontend:management-bar
	disabled="<%= usersCount <= 0 %>"
	includeCheckBox="<%= true %>"
	searchContainerId="users"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
<<<<<<< HEAD
			portletURL="<%= editSiteTeamAssignmentsDisplayContext.getEditTeamAssignmentsURL() %>"
=======
			portletURL="<%= portletURL %>"
>>>>>>> compatible
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"first-name", "screen-name"} %>'
<<<<<<< HEAD
			portletURL="<%= editSiteTeamAssignmentsDisplayContext.getEditTeamAssignmentsURL() %>"
		/>

		<c:if test="<%= (usersCount > 0) || searchTerms.isSearch() %>">
			<li>
				<aui:form action="<%= editSiteTeamAssignmentsDisplayContext.getEditTeamAssignmentsURL() %>" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="portletURL" />

					<liferay-ui:input-search markupView="lexicon" />
				</aui:form>
			</li>
		</c:if>
=======
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
		/>
>>>>>>> compatible
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-portlet:actionURL name="changeDisplayStyle" varImpl="changeDisplayStyleURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</liferay-portlet:actionURL>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= changeDisplayStyleURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button href="javascript:;" icon="trash" id="deleteUsers" label="delete" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="deleteTeamUsers" var="deleteTeamUsersURL" />

<aui:form action="<%= deleteTeamUsersURL %>" cssClass="container-fluid-1280 portlet-site-teams-users" method="post" name="fm">
<<<<<<< HEAD
	<aui:input name="tabs1" type="hidden" value="<%= editSiteTeamAssignmentsDisplayContext.getTabs1() %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="teamId" type="hidden" value="<%= String.valueOf(editSiteTeamAssignmentsDisplayContext.getTeamId()) %>" />
=======
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="teamId" type="hidden" value="<%= String.valueOf(team.getTeamId()) %>" />
>>>>>>> compatible

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-members.-you-can-add-a-member-by-clicking-the-plus-button-on-the-bottom-right-corner"
		id="users"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= userSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			cssClass="selectable"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>

			<%
			boolean showActions = true;
			%>

			<%@ include file="/user_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<portlet:actionURL name="addTeamUsers" var="addTeamUsersURL" />

<aui:form action="<%= addTeamUsersURL %>" cssClass="hide" name="addTeamUsersFm">
<<<<<<< HEAD
	<aui:input name="tabs1" type="hidden" value="<%= editSiteTeamAssignmentsDisplayContext.getTabs1() %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="teamId" type="hidden" value="<%= String.valueOf(editSiteTeamAssignmentsDisplayContext.getTeamId()) %>" />
=======
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="teamId" type="hidden" value="<%= String.valueOf(team.getTeamId()) %>" />
>>>>>>> compatible
</aui:form>

<liferay-frontend:add-menu>
	<liferay-frontend:add-menu-item id="addUsers" title='<%= LanguageUtil.get(request, "add-team-members") %>' url="javascript:;" />
</liferay-frontend:add-menu>

<aui:script use="liferay-item-selector-dialog">
	var Util = Liferay.Util;

	var form = $(document.<portlet:namespace />fm);

	<portlet:renderURL var="selectUserURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/select_users.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
<<<<<<< HEAD
		<portlet:param name="teamId" value="<%= String.valueOf(editSiteTeamAssignmentsDisplayContext.getTeamId()) %>" />
=======
		<portlet:param name="teamId" value="<%= String.valueOf(team.getTeamId()) %>" />
>>>>>>> compatible
	</portlet:renderURL>

	$('#<portlet:namespace />addUsers').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: '<portlet:namespace />selectUser',
					on: {
						selectedItemChange: function(event) {
							var selectedItem = event.newVal;

							if (selectedItem) {
								var addTeamUsersFm = $(document.<portlet:namespace />addTeamUsersFm);

								addTeamUsersFm.append(selectedItem);

								submitForm(addTeamUsersFm);
							}
						}
					},
<<<<<<< HEAD
					title: '<liferay-ui:message arguments="<%= editSiteTeamAssignmentsDisplayContext.getTeamName() %>" key="add-new-user-to-x" />',
=======
					title: '<liferay-ui:message arguments="<%= team.getName() %>" key="add-new-user-to-x" />',
>>>>>>> compatible
					url: '<%= selectUserURL %>'
				}
			);

			itemSelectorDialog.open();
		}
	);

	$('#<portlet:namespace />deleteUsers').on(
		'click',
		function() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
				submitForm(form);
			}
		}
	);
</aui:script>