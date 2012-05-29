<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "home");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

boolean viewFolder = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewFolder"));

boolean useAssetEntryQuery = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-useAssetEntryQuery"));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("categoryId", StringPool.BLANK);
portletURL.setParameter("tag", StringPool.BLANK);
%>

<div class="top-links-container">
	<div class="top-links">
		<ul class="top-links-navigation">
			<li class="top-link first">

				<%
				portletURL.setParameter("topLink", "home");
				%>

				<liferay-ui:icon
					image="../aui/home"
					label="<%= true %>"
					message="home"
					url='<%= (topLink.equals("home") && folderId == 0 && viewFolder && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
				/>
			</li>

			<li class="top-link <%= (themeDisplay.isSignedIn() ? StringPool.BLANK : " last") %>">

				<%
				portletURL.setParameter("topLink", "recent");
				%>

				<liferay-ui:icon
					image="../aui/clock"
					label="<%= true %>"
					message="recent"
					url='<%= (topLink.equals("recent") && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
				/>
			</li>

			<c:if test="<%= themeDisplay.isSignedIn() %>">
				<li class="top-link last">

					<%
					portletURL.setParameter("topLink", "mine");
					%>

					<liferay-ui:icon
						image="../aui/person"
						label="<%= true %>"
						message="mine"
						url='<%= (topLink.equals("mine") && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
					/>
				</li>
			</c:if>
		</ul>

		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="struts_action" value="/bookmarks/search" />
		</liferay-portlet:renderURL>

		<c:if test="<%= showFoldersSearch %>">
			<div class="folder-search">
				<aui:form action="<%= searchURL %>" method="get" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
					<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

					<aui:search-bar formName="searchFm" inputId="keywords1" inputTitle="search-bookmarks" useAutoFocus="<%= true %>" />
				</aui:form>
			</div>
		</c:if>
	</div>
</div>