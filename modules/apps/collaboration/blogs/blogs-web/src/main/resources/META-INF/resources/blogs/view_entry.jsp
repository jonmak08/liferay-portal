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

<%@ include file="/blogs/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

	redirect = portletURL.toString();
}

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

long entryId = ParamUtil.getLong(request, "entryId", entry.getEntryId());

AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

AssetEntryServiceUtil.incrementViewCounter(assetEntry);

<<<<<<< HEAD
assetHelper.addLayoutTags(request, AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId()));

RatingsEntry ratingsEntry = null;
RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.fetchStats(BlogsEntry.class.getName(), entry.getEntryId());

if (ratingsStats != null) {
	ratingsEntry = RatingsEntryLocalServiceUtil.fetchEntry(themeDisplay.getUserId(), BlogsEntry.class.getName(), entry.getEntryId());
}
=======
AssetUtil.addLayoutTags(request, AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId()));
>>>>>>> compatible

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);

request.setAttribute("view_entry_content.jsp-entry", entry);

request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);

<<<<<<< HEAD
request.setAttribute("view_entry_content.jsp-ratingsEntry", ratingsEntry);
request.setAttribute("view_entry_content.jsp-ratingsStats", ratingsStats);

=======
>>>>>>> compatible
portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
<<<<<<< HEAD
	renderResponse.setTitle(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry));
=======
	renderResponse.setTitle(entry.getTitle());
>>>>>>> compatible
}
%>

<portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

<aui:form action="<%= editEntryURL %>" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="entryId" type="hidden" value="<%= String.valueOf(entryId) %>" />

	<liferay-util:include page="/blogs/view_entry_content.jsp" servletContext="<%= application %>" />
</aui:form>

<div class="container-fluid">
	<c:if test="<%= PropsValues.BLOGS_ENTRY_PREVIOUS_AND_NEXT_NAVIGATION_ENABLED %>">

		<%
		BlogsEntry[] prevAndNext = BlogsEntryLocalServiceUtil.getEntriesPrevAndNext(entryId);

		BlogsEntry previousEntry = prevAndNext[0];
		BlogsEntry nextEntry = prevAndNext[2];
		%>

		<c:if test="<%= (previousEntry != null) || (nextEntry != null) %>">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 entry-navigation">
					<h2><strong><liferay-ui:message key="more-blog-entries" /></strong></h2>

					<div class="row">
						<c:if test="<%= previousEntry != null %>">
							<aui:col cssClass="entry-navigation-item" md="4" sm="6">
								<portlet:renderURL var="previousEntryURL">
									<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
<<<<<<< HEAD
									<portlet:param name="redirect" value="<%= redirect %>" />
=======
>>>>>>> compatible
									<portlet:param name="urlTitle" value="<%= previousEntry.getUrlTitle() %>" />
								</portlet:renderURL>

								<liferay-util:html-top outputKey="blogs_previous_entry_link">
									<link href="<%= previousEntryURL.toString() %>" rel="prev" />
								</liferay-util:html-top>

								<%
								String smallImageURL = previousEntry.getSmallImageURL(themeDisplay);
								%>

								<c:if test="<%= Validator.isNotNull(smallImageURL) %>">
<<<<<<< HEAD
									<aui:a href="<%= previousEntryURL %>" title="<%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, previousEntry)) %>">
=======
									<aui:a href="<%= previousEntryURL %>" title="<%= HtmlUtil.escape(previousEntry.getTitle()) %>">
>>>>>>> compatible
										<span class="small-image visible-lg-block visible-md-block" style="background-image: url(<%= HtmlUtil.escape(smallImageURL) %>)"></span>
									</aui:a>
								</c:if>

								<div class="entry-info text-muted">
									<small>
										<strong><%= HtmlUtil.escape(previousEntry.getUserName()) %></strong>
										<span> - </span>
										<span class="hide-accessible"><liferay-ui:message key="published-date" /></span>
										<%= dateFormatDate.format(previousEntry.getDisplayDate()) %>
<<<<<<< HEAD

										<c:if test="<%= blogsPortletInstanceConfiguration.enableReadingTime() %>">
											<liferay-reading-time:reading-time model="<%= previousEntry %>" />
										</c:if>
=======
>>>>>>> compatible
									</small>
								</div>

								<div class="entry-content">
									<h4>
<<<<<<< HEAD
										<aui:a href="<%= previousEntryURL %>" title="<%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, previousEntry)) %>"><%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, previousEntry)) %></aui:a>
=======
										<aui:a href="<%= previousEntryURL %>" title="<%= HtmlUtil.escape(previousEntry.getTitle()) %>"><%= HtmlUtil.escape(previousEntry.getTitle()) %></aui:a>
>>>>>>> compatible
									</h4>

									<p class="entry-content-body visible-lg-block">
										<%= StringUtil.shorten(HtmlUtil.stripHtml(previousEntry.getContent()), 100) %>
									</p>
								</div>
							</aui:col>
						</c:if>

						<c:if test="<%= nextEntry != null %>">
							<aui:col cssClass="entry-navigation-item" md="4" sm="6">
								<portlet:renderURL var="nextEntryURL">
									<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
<<<<<<< HEAD
									<portlet:param name="redirect" value="<%= redirect %>" />
=======
>>>>>>> compatible
									<portlet:param name="urlTitle" value="<%= nextEntry.getUrlTitle() %>" />
								</portlet:renderURL>

								<liferay-util:html-top outputKey="blogs_next_entry_link">
									<link href="<%= nextEntryURL.toString() %>" rel="next" />
								</liferay-util:html-top>

								<%
								String smallImageURL = nextEntry.getSmallImageURL(themeDisplay);
								%>

								<c:if test="<%= Validator.isNotNull(smallImageURL) %>">
<<<<<<< HEAD
									<aui:a href="<%= nextEntryURL %>" title="<%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, nextEntry)) %>">
=======
									<aui:a href="<%= nextEntryURL %>" title="<%= HtmlUtil.escape(nextEntry.getTitle()) %>">
>>>>>>> compatible
										<span class="small-image visible-lg-block visible-md-block" style="background-image: url(<%= HtmlUtil.escape(smallImageURL) %>)"></span>
									</aui:a>
								</c:if>

								<div class="entry-info text-muted">
									<small>
										<strong><%= HtmlUtil.escape(nextEntry.getUserName()) %></strong>
										<span> - </span>
										<span class="hide-accessible"><liferay-ui:message key="published-date" /></span>
										<%= dateFormatDate.format(nextEntry.getDisplayDate()) %>
<<<<<<< HEAD

										<c:if test="<%= blogsPortletInstanceConfiguration.enableReadingTime() %>">
											<liferay-reading-time:reading-time model="<%= nextEntry %>" />
										</c:if>
=======
>>>>>>> compatible
									</small>
								</div>

								<div class="entry-content">
									<h4>
<<<<<<< HEAD
										<aui:a href="<%= nextEntryURL %>" title="<%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, nextEntry)) %>"><%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, nextEntry)) %></aui:a>
=======
										<aui:a href="<%= nextEntryURL %>" title="<%= HtmlUtil.escape(nextEntry.getTitle()) %>"><%= HtmlUtil.escape(nextEntry.getTitle()) %></aui:a>
>>>>>>> compatible
									</h4>

									<p class="visible-lg-block">
										<%= StringUtil.shorten(HtmlUtil.stripHtml(nextEntry.getContent()), 100) %>
									</p>
								</div>
							</aui:col>
						</c:if>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<c:if test="<%= blogsPortletInstanceConfiguration.enableComments() %>">

				<%
				Discussion discussion = CommentManagerUtil.getDiscussion(user.getUserId(), scopeGroupId, BlogsEntry.class.getName(), entry.getEntryId(), new ServiceContextFunction(request));
				%>

				<c:if test="<%= discussion != null %>">
					<h2>
<<<<<<< HEAD
						<strong><liferay-ui:message arguments="<%= discussion.getDiscussionCommentsCount() %>" key='<%= discussion.getDiscussionCommentsCount() == 1 ? "x-comment" : "x-comments" %>' /></strong>
					</h2>

					<c:if test="<%= PropsValues.BLOGS_TRACKBACK_ENABLED && entry.isAllowTrackbacks() && Validator.isNotNull(entry.getUrlTitle()) %>">
						<aui:input inlineLabel="left" name="trackbackURL" type="resource" value='<%= PortalUtil.getLayoutFullURL(themeDisplay.getLayout(), themeDisplay, false) + Portal.FRIENDLY_URL_SEPARATOR + "blogs/trackback/" + entry.getUrlTitle() %>' />
					</c:if>

					<liferay-comment:discussion
=======
						<strong><liferay-ui:message arguments="<%= CommentManagerUtil.getCommentsCount(BlogsEntry.class.getName(), entry.getEntryId()) %>" key="x-comments" /></strong>
					</h2>

					<c:if test="<%= PropsValues.BLOGS_TRACKBACK_ENABLED && entry.isAllowTrackbacks() %>">
						<aui:input inlineLabel="left" name="trackbackURL" type="resource" value='<%= PortalUtil.getLayoutFullURL(themeDisplay.getLayout(), themeDisplay, false) + Portal.FRIENDLY_URL_SEPARATOR + "blogs/trackback/" + entry.getUrlTitle() %>' />
					</c:if>

					<liferay-ui:discussion
>>>>>>> compatible
						className="<%= BlogsEntry.class.getName() %>"
						classPK="<%= entry.getEntryId() %>"
						discussion="<%= discussion %>"
						formName="fm2"
						ratingsEnabled="<%= blogsPortletInstanceConfiguration.enableCommentRatings() %>"
						redirect="<%= currentURL %>"
						userId="<%= entry.getUserId() %>"
					/>
				</c:if>
			</c:if>
		</div>
	</div>
</div>

<%
<<<<<<< HEAD
PortalUtil.setPageTitle(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry), request);
PortalUtil.setPageSubtitle(entry.getSubtitle(), request);

String description = entry.getDescription();

if (Validator.isNull(description)) {
	description = HtmlUtil.stripHtml(StringUtil.shorten(entry.getContent(), pageAbstractLength));
}

PortalUtil.setPageDescription(description, request);
=======
PortalUtil.setPageTitle(entry.getTitle(), request);
PortalUtil.setPageSubtitle(entry.getSubtitle(), request);
PortalUtil.setPageDescription(entry.getDescription(), request);
>>>>>>> compatible

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);

<<<<<<< HEAD
PortalUtil.addPortletBreadcrumbEntry(request, BlogsEntryUtil.getDisplayTitle(resourceBundle, entry), currentURL);
=======
PortalUtil.addPortletBreadcrumbEntry(request, entry.getTitle(), currentURL);
>>>>>>> compatible
%>

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#post" />