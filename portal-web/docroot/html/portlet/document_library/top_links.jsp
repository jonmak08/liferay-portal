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

<%@ include file="/html/portlet/document_library_display/init.jsp" %>

<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) || portletName.equals(PortletKeys.MEDIA_GALLERY_DISPLAY) %>">

		<%
		String topLink = ParamUtil.getString(request, "topLink", "home");

		long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

		long defaultFolderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-defaultFolderId"));

		long repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));

		boolean viewFolder = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewFolder"));

		boolean useAssetEntryQuery = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-useAssetEntryQuery"));
		%>

		<div class="top-links-container">
			<div class="top-links">
				<c:if test="<%= showTabs %>">
					<ul class="top-links-navigation">
						<li class="top-link first">

							<%
							String homeMessage = "home";
							String recentMessage = "recent";
							String mineMessage = "mine";

							PortletURL portletURL = renderResponse.createRenderURL();

							portletURL.setParameter("categoryId", StringPool.BLANK);
							portletURL.setParameter("tag", StringPool.BLANK);
							portletURL.setParameter("topLink", homeMessage);
							%>

							<liferay-ui:icon
								image="../aui/home"
								label="<%= true %>"
								message="<%= homeMessage %>"
								url="<%= (topLink.equals(homeMessage) && (folderId == defaultFolderId) && viewFolder && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>"
							/>
						</li>

						<li class="top-link <%= (themeDisplay.isSignedIn() ? StringPool.BLANK : " last") %>">

							<%
							portletURL.setParameter("topLink", recentMessage);
							%>

							<liferay-ui:icon
								image="../aui/clock"
								label="<%= true %>"
								message="<%= recentMessage %>"
								url="<%= (topLink.equals(recentMessage) && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>"
							/>
						</li>

						<c:if test="<%= themeDisplay.isSignedIn() %>">
							<li class="top-link last">

								<%
								portletURL.setParameter("topLink", mineMessage);
								%>

								<liferay-ui:icon
									image="../aui/person"
									label="<%= true %>"
									message="<%= mineMessage %>"
									url="<%= (topLink.equals(mineMessage) && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>"
								/>
							</li>
						</c:if>
					</ul>
				</c:if>

				<c:if test="<%= showFoldersSearch %>">
					<liferay-portlet:renderURL varImpl="searchURL">
						<portlet:param name="struts_action" value="/document_library_display/search" />
					</liferay-portlet:renderURL>

					<div class="folder-search">
						<aui:form action="<%= searchURL %>" method="get" name="searchFm">
							<liferay-portlet:renderURLParams varImpl="searchURL" />
							<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
							<aui:input name="repositoryId" type="hidden" value="<%= repositoryId %>" />
							<aui:input name="folderId" type="hidden" value="<%= folderId %>" />
							<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
							<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

							<aui:search-bar formName="searchFm" inputId="keywords1" inputTitle="search-documents" useAutoFocus="<%= true %>" />
						</aui:form>
					</div>
				</c:if>
			</div>
		</div>
	</c:when>
	<c:when test="<%= (showTabs || showFoldersSearch) && portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) %>">
		<liferay-ui:header
			title="home"
		/>
	</c:when>
</c:choose>