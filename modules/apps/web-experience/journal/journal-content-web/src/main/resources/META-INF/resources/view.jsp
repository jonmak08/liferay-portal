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

<liferay-util:dynamic-include key="com.liferay.journal.content.web#/view.jsp#pre" />

<%
JournalArticle article = journalContentDisplayContext.getArticle();
JournalArticleDisplay articleDisplay = journalContentDisplayContext.getArticleDisplay();

journalContentDisplayContext.incrementViewCounter();

AssetRendererFactory<JournalArticle> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(JournalArticle.class);
%>

<c:choose>
	<c:when test="<%= article == null %>">
		<c:choose>
			<c:when test="<%= Validator.isNull(journalContentDisplayContext.getArticleId()) %>">
				<div class="alert alert-info text-center">
					<div>
						<liferay-ui:message key="this-application-is-not-visible-to-users-yet" />
					</div>

<<<<<<< HEAD
					<c:if test="<%= journalContentDisplayContext.isShowSelectArticleLink() %>">
=======
					<%
					Group scopeGroup = themeDisplay.getScopeGroup();
					%>

					<c:if test="<%= !scopeGroup.isStaged() || scopeGroup.isStagingGroup() %>">
>>>>>>> compatible
						<div>
							<aui:a href="javascript:;" onClick="<%= portletDisplay.getURLConfigurationJS() %>"><liferay-ui:message key="select-web-content-to-make-it-visible" /></aui:a>
						</div>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
<<<<<<< HEAD

				<%
				JournalArticle selectedArticle = journalContentDisplayContext.getSelectedArticle();
				%>

				<div class="alert alert-warning text-center">
					<c:choose>
						<c:when test="<%= (selectedArticle != null) && selectedArticle.isInTrash() %>">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(selectedArticle.getTitle(locale)) %>" key="the-web-content-article-x-was-moved-to-the-recycle-bin" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="the-selected-web-content-no-longer-exists" />
						</c:otherwise>
					</c:choose>

					<c:if test="<%= journalContentDisplayContext.isShowSelectArticleLink() %>">
						<liferay-util:buffer var="selectJournalArticleLink">
							<aui:a href="javascript:;" label="select-another" onClick="<%= portletDisplay.getURLConfigurationJS() %>" />
						</liferay-util:buffer>

						<div>
							<c:choose>
								<c:when test="<%= journalContentDisplayContext.hasRestorePermission() %>">
									<portlet:actionURL name="restoreJournalArticle" var="restoreJournalArticleURL">
										<portlet:param name="classPK" value="<%= String.valueOf(JournalArticleAssetRenderer.getClassPK(selectedArticle)) %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
									</portlet:actionURL>

									<liferay-util:buffer var="restoreJournalArticleLink">
										<aui:a href="<%= restoreJournalArticleURL %>" label="undo" />
									</liferay-util:buffer>

									<liferay-ui:message arguments="<%= new String[] {restoreJournalArticleLink, selectJournalArticleLink} %>" key="do-you-want-to-x-or-x-web-content" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message arguments="<%= selectJournalArticleLink %>" key="do-you-want-to-x-web-content" />
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
=======
				<div class="alert alert-danger">
					<liferay-ui:message key="the-selected-web-content-no-longer-exists" />
>>>>>>> compatible
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= !journalContentDisplayContext.hasViewPermission() %>">
				<div class="alert alert-danger">
					<liferay-ui:message key="you-do-not-have-the-roles-required-to-access-this-web-content-entry" />
				</div>
			</c:when>
			<c:when test="<%= Validator.isNotNull(journalContentDisplayContext.getArticleId()) %>">
				<c:choose>
					<c:when test="<%= journalContentDisplayContext.isExpired() %>">
						<div class="alert alert-warning">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(article.getTitle(locale)) %>" key="x-is-expired" />
						</div>
					</c:when>
					<c:when test="<%= article.isScheduled() %>">
						<div class="alert alert-warning">
							<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(article.getTitle(locale)), dateFormatDateTime.format(article.getDisplayDate())} %>" key="x-is-scheduled-and-will-be-displayed-on-x" />
						</div>
					</c:when>
					<c:when test="<%= !article.isApproved() %>">

						<%
						AssetRenderer<JournalArticle> assetRenderer = assetRendererFactory.getAssetRenderer(article.getResourcePrimKey());
						%>

						<c:choose>
							<c:when test="<%= assetRenderer.hasEditPermission(permissionChecker) %>">
								<div class="alert alert-warning">
									<a href="<%= assetRenderer.getURLEdit(liferayPortletRequest, liferayPortletResponse, WindowState.MAXIMIZED, currentURLObj) %>">
										<liferay-ui:message arguments="<%= HtmlUtil.escape(article.getTitle(locale)) %>" key="x-is-not-approved" />
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-warning">
									<liferay-ui:message arguments="<%= HtmlUtil.escape(article.getTitle(locale)) %>" key="x-is-not-approved" />
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="<%= articleDisplay != null %>">
						<div class="text-right user-tool-asset-addon-entries">
<<<<<<< HEAD
							<liferay-asset:asset-addon-entry-display assetAddonEntries="<%= journalContentDisplayContext.getSelectedUserToolAssetAddonEntries() %>" />
						</div>

						<div class="pull-right visible-interaction">
							<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
								<c:if test="<%= journalContentDisplayContext.isShowEditArticleIcon() %>">

									<%
									JournalArticle latestArticle = journalContentDisplayContext.getLatestArticle();

									Map<String, Object> data = new HashMap<String, Object>();

									data.put("destroyOnHide", true);
									data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
									data.put("title", HtmlUtil.escape(latestArticle.getTitle(locale)));
									%>

									<liferay-ui:icon
										data="<%= data %>"
										id="editWebContentIcon"
										message="edit-web-content"
										url="<%= journalContentDisplayContext.getURLEdit() %>"
										useDialog="<%= true %>"
									/>
								</c:if>

								<c:if test="<%= journalContentDisplayContext.isShowEditTemplateIcon() %>">

									<%
									DDMTemplate ddmTemplate = journalContentDisplayContext.getDDMTemplate();

									Map<String, Object> data = new HashMap<String, Object>();

									data.put("destroyOnHide", true);
									data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
									data.put("title", HtmlUtil.escape(ddmTemplate.getName(locale)));
									%>

									<liferay-ui:icon
										data="<%= data %>"
										id="editTemplateIcon"
										message="edit-template"
										url="<%= journalContentDisplayContext.getURLEditTemplate() %>"
										useDialog="<%= true %>"
									/>
								</c:if>

								<c:if test="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) %>">
									<liferay-security:permissionsURL
										modelResource="<%= JournalArticle.class.getName() %>"
										modelResourceDescription="<%= HtmlUtil.escape(article.getTitle(locale)) %>"
										resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>"
										var="permissionsURL"
										windowState="<%= LiferayWindowState.POP_UP.toString() %>"
									/>

									<liferay-ui:icon
										message="permissions"
										method="get"
										url="<%= permissionsURL %>"
										useDialog="<%= true %>"
									/>
								</c:if>
							</liferay-ui:icon-menu>
=======
							<liferay-ui:asset-addon-entry-display assetAddonEntries="<%= journalContentDisplayContext.getSelectedUserToolAssetAddonEntries() %>" />
>>>>>>> compatible
						</div>

						<div class="journal-content-article">
							<%= articleDisplay.getContent() %>
						</div>

						<c:if test="<%= articleDisplay.isPaginate() %>">

							<%
							PortletURL portletURL = renderResponse.createRenderURL();
							%>

							<liferay-ui:page-iterator
								cur="<%= articleDisplay.getCurrentPage() %>"
								curParam='<%= "page" %>'
								delta="<%= 1 %>"
								id="articleDisplayPages"
								maxPages="<%= 25 %>"
								portletURL="<%= portletURL %>"
								total="<%= articleDisplay.getNumberOfPages() %>"
								type="article"
							/>

							<br />
						</c:if>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>

<c:if test="<%= (articleDisplay != null) && journalContentDisplayContext.hasViewPermission() %>">
	<div class="content-metadata-asset-addon-entries">
<<<<<<< HEAD
		<liferay-asset:asset-addon-entry-display assetAddonEntries="<%= journalContentDisplayContext.getSelectedContentMetadataAssetAddonEntries() %>" />
=======
		<liferay-ui:asset-addon-entry-display assetAddonEntries="<%= journalContentDisplayContext.getSelectedContentMetadataAssetAddonEntries() %>" />
>>>>>>> compatible
	</div>
</c:if>

<liferay-util:dynamic-include key="com.liferay.journal.content.web#/view.jsp#post" />