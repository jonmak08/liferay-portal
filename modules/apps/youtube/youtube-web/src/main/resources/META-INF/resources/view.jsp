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

<c:choose>
	<c:when test="<%= Validator.isNotNull(youTubeDisplayContext.getURL()) %>">
		<c:choose>
			<c:when test="<%= youTubeDisplayContext.isShowThumbnail() %>">
				<aui:a href="<%= youTubeDisplayContext.getWatchURL() %>" rel="external" title='<%= HtmlUtil.escapeAttribute(LanguageUtil.get(request, "watch-this-video-at-youtube")) %>'>
					<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="youtube-video" />" height="<%= youTubeDisplayContext.getHeight() %>" src="<%= youTubeDisplayContext.getImageURL() %>" width="<%= youTubeDisplayContext.getWidth() %>" />
				</aui:a>
			</c:when>
			<c:otherwise>
				<iframe allowfullscreen frameborder="0" id="<portlet:namespace />iframe" src="<%= youTubeDisplayContext.getEmbedURL() %>" wmode="Opaque" /></iframe>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />resizeIFrame() {
		var iFrame = document.getElementById('<portlet:namespace />iframe');

		if (iFrame != null) {
			var displayContextHeight = <%= youTubeDisplayContext.getHeight() %>;
			var displayContextWidth = <%= youTubeDisplayContext.getWidth() %>;

			iFrame.setAttribute('height', displayContextHeight);

			var parent = iFrame.parentElement;

			var parentWidth = parent.offsetWidth;

			if (displayContextWidth > parentWidth) {
				iFrame.setAttribute('width', parentWidth);
			}
			else {
				iFrame.setAttribute('width', displayContextWidth);
			}
		}
	};

	function <portlet:namespace />addDragAndDropListener() {
		const TIMEOUT_LENGTH = 10;

		if (!Liferay.Layout) {
			setTimeout(
				function() {
					<portlet:namespace />addDragAndDropListener();
				},
				TIMEOUT_LENGTH
			);
		}
		else {
			Liferay.Layout.on(
				[ 'drag:end', 'drag:start' ],
				function(event) {
					setTimeout(
						function() {
							$(window).trigger('resize');
						},
						TIMEOUT_LENGTH
					);
				}
			);
		}
	}

	Liferay.on(
		'portletReady', function(event) {
			<portlet:namespace />resizeIFrame();
		}
	);

	<portlet:namespace />addDragAndDropListener();

	$(window).on(
		'resize', function() {
			<portlet:namespace />resizeIFrame();
		}
	);
</aui:script>