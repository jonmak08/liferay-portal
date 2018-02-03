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

<%@ include file="/message_boards/init.jsp" %>

<%
MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE_DISPLAY);

MBMessage message = messageDisplay.getMessage();

<<<<<<< HEAD
MBThread thread = messageDisplay.getThread();

if (layout.isTypeControlPanel()) {
	MBBreadcrumbUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
}

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(MBMessage.class.getName(), message.getMessageId());

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, layoutAssetEntry);

AssetEntryServiceUtil.incrementViewCounter(layoutAssetEntry);
=======
MBCategory category = messageDisplay.getCategory();

MBThread thread = messageDisplay.getThread();

if ((message != null) && layout.isTypeControlPanel()) {
	MBBreadcrumbUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
}

AssetEntryServiceUtil.incrementViewCounter(MBMessage.class.getName(), message.getMessageId());
>>>>>>> compatible

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

MBBreadcrumbUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
%>

<div <%= portletTitleBasedNavigation ? "class=\"container-fluid-1280\"" : StringPool.BLANK %>>
	<c:if test="<%= !portletTitleBasedNavigation %>">
		<liferay-util:include page="/message_boards/top_links.jsp" servletContext="<%= application %>" />
	</c:if>

<<<<<<< HEAD
	<liferay-util:include page="/message_boards/view_message_content.jsp" servletContext="<%= application %>" />
</div>

<aui:script>
	function <portlet:namespace />addReplyToMessage(messageId, quote) {
		var addQuickReplyContainer = AUI.$('#<portlet:namespace />addReplyToMessage' + messageId);

		addQuickReplyContainer.removeClass('hide');

		addQuickReplyContainer.find('#<portlet:namespace />parentMessageId').val(messageId);

		addQuickReplyContainer.scrollTop();

		var editorName = '<portlet:namespace />replyMessageBody' + messageId;

		if (!window[editorName].instanceReady) {
			window[editorName].create();
		}

		window[editorName].setHTML(quote);
		window[editorName].focus();

		Liferay.Util.toggleDisabled('#<portlet:namespace />replyMessageButton' + messageId, true);
	}

	function <portlet:namespace />hideReplyMessage(messageId) {
		AUI.$('#<portlet:namespace />addReplyToMessage' + messageId).addClass('hide');

		var editorName = '<portlet:namespace />replyMessageBody' + messageId;

		if (window[editorName]) {
			window[editorName].dispose();
=======
	<c:choose>
		<c:when test="<%= includeFormTag %>">
			<aui:form name="fm">
				<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= category.getCategoryId() %>" />
				<aui:input name="breadcrumbsMessageId" type="hidden" value="<%= message.getMessageId() %>" />
				<aui:input name="threadId" type="hidden" value="<%= message.getThreadId() %>" />

				<liferay-util:include page="/message_boards/view_message_content.jsp" servletContext="<%= application %>" />
			</aui:form>
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/message_boards/view_message_content.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, message.getCategoryId(), ActionKeys.REPLY_TO_MESSAGE) && !thread.isLocked() %>">
		<div class="hide" id="<portlet:namespace />addQuickReplyDiv">
			<%@ include file="/message_boards/edit_message_quick.jspf" %>
		</div>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace />addQuickReply(cmd, messageId) {
		var addQuickReplyDiv = AUI.$('#<portlet:namespace />addQuickReplyDiv');

		if (cmd == 'reply') {
			addQuickReplyDiv.removeClass('hide');

			addQuickReplyDiv.find('#<portlet:namespace />parentMessageId').val(messageId);

			var editorInput = addQuickReplyDiv.find('textarea');

			var editorInstance = window[editorInput.attr('id')];

			if (editorInstance) {
				setTimeout(AUI._.bind(editorInstance.focus, editorInstance), 50);
			}
		}
		else {
			addQuickReplyDiv.addClass('hide');
>>>>>>> compatible
		}
	}

	<c:if test="<%= thread.getRootMessageId() != message.getMessageId() %>">
		document.getElementById('<portlet:namespace />message_' + <%= message.getMessageId() %>).scrollIntoView(true);
	</c:if>
</aui:script>

<%
MBThreadFlagLocalServiceUtil.addThreadFlag(themeDisplay.getUserId(), thread, new ServiceContext());

<<<<<<< HEAD
=======
message = messageDisplay.getMessage();

>>>>>>> compatible
PortalUtil.setPageSubtitle(message.getSubject(), request);
PortalUtil.setPageDescription(message.getSubject(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(MBMessage.class.getName(), message.getMessageId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);
%>