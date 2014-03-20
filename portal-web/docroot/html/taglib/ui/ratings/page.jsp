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

<%@ include file="/html/taglib/ui/ratings/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_page") + StringPool.UNDERLINE;

String className = (String)request.getAttribute("liferay-ui:ratings:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:ratings:classPK"));
int numberOfStars = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:ratings:numberOfStars"));
RatingsEntry ratingsEntry = (RatingsEntry)request.getAttribute("liferay-ui:ratings:ratingsEntry");
RatingsStats ratingsStats = (RatingsStats)request.getAttribute("liferay-ui:ratings:ratingsStats");
boolean setRatingsEntry = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:ratings:setRatingsEntry"));
boolean setRatingsStats = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:ratings:setRatingsStats"));
String type = GetterUtil.getString((String)request.getAttribute("liferay-ui:ratings:type"));
String url = (String)request.getAttribute("liferay-ui:ratings:url");

if (numberOfStars < 1) {
	numberOfStars = 1;
}

if (!setRatingsEntry) {
	ratingsEntry = RatingsEntryLocalServiceUtil.fetchEntry(themeDisplay.getUserId(), className, classPK);
}

if (!setRatingsStats) {
	ratingsStats = RatingsStatsLocalServiceUtil.getStats(className, classPK);
}

if (Validator.isNull(url)) {
	url = themeDisplay.getPathMain() + "/portal/rate_entry";
}

double yourScore = 0.0;

if (ratingsEntry != null) {
	yourScore = ratingsEntry.getScore();
}
%>

<c:if test="<%= !themeDisplay.isFacebook() %>">
	<div class="taglib-ratings <%= type %>" id="<%= randomNamespace %>ratingContainer">
		<c:choose>
			<c:when test='<%= type.equals("stars") %>'>
				<c:choose>
					<c:when test="<%= themeDisplay.isSignedIn() && !TrashUtil.isInTrash(className, classPK) %>">
						<div class="liferay-rating-vote" id="<%= randomNamespace %>ratingStar">
							<div id="<%= randomNamespace %>ratingStarContent">
								<div class="rating-label"><liferay-ui:message key="your-rating" /></div>

								<%
								StringBundler starsSB = new StringBundler(5 * numberOfStars);

								for (int i = 1; i <= numberOfStars; i++) {
									String ratingId = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_page_rating");

									starsSB.append("<a class="rating-element <%= (i <= yourScore) ? "icon-star" : "icon-star-empty" %>" href="javascript:;"></a>");
									starsSB.append("<div class="rating-input-container">");
									starsSB.append("<label for="<%= ratingId %>"><liferay-ui:message arguments="<%= new Object[] {i, numberOfStars} %>" key='<%= (yourScore == i) ? "you-have-rated-this-x-stars-out-of-x" : "rate-this-x-stars-out-of-x" %>' translateArguments="<%= false %>" /></label>");
									starsSB.append("<input checked="<%= i == yourScore %>" class="rating-input" id="<%= ratingId %>" name="<portlet:namespace />rating" type="radio" value="<%= i %>">");
									starsSB.append("</div>");

								}

								String starsRating = starsSB.toString();
								%>

								<%= starsRating %>
							</div>
						</div>
					</c:when>
				</c:choose>

				<div class="liferay-rating-score" id="<%= randomNamespace %>ratingScore">
					<div id="<%= randomNamespace %>ratingScoreContent">
						<div class="rating-label">
							<liferay-ui:message key="average" />

							(<%= ratingsStats.getTotalEntries() %> <liferay-ui:message key='<%= (ratingsStats.getTotalEntries() == 1) ? "vote" : "votes" %>' />)
						</div>

						<%
						StringBundler avgStarsSB = new StringBundler(numberOfStars);

						for (int i = 1; i <= numberOfStars; i++) {

							avgStarsSB.append("<span class="rating-element <%= (i <= ratingsStats.getAverageScore()) ? "icon-star" : "icon-star-empty" %>" title="<%= TrashUtil.isInTrash(className, classPK) ? LanguageUtil.get(pageContext, "ratings-are-disabled-because-this-entry-is-in-the-recycle-bin") : ((i == 1) ? LanguageUtil.format(pageContext, "the-average-rating-is-x-stars-out-of-x", new Object[] {ratingsStats.getAverageScore(), numberOfStars}, false) : StringPool.BLANK) %>"></span>");

						}

						String averageStarsRating = avgStarsSB.toString();
						%>

						<%= averageStarsRating %>
					</div>
				</div>
			</c:when>
			<c:when test='<%= type.equals("thumbs") %>'>
				<c:choose>
					<c:when test="<%= themeDisplay.isSignedIn() %>">
						<div class="thumbrating liferay-rating-vote" id="<%= randomNamespace %>ratingThumb">
							<div class="helper-clearfix rating-content thumbrating-content" id="<%= randomNamespace %>ratingThumbContent">
								
								<%
								StringBundler thumbSB = new StringBundler();

								thumbSB.append("<div class="rating-label">");

								if (ratingsStats.getAverageScore() * ratingsStats.getTotalEntries() == 0) {
									thumbSB.append("0");
								}
								else {
									thumbSB.append("<%= (ratingsStats.getAverageScore() > 0) ? "+" : StringPool.BLANK %><%= (int)(ratingsStats.getAverageScore() * ratingsStats.getTotalEntries()) %>");
								}

								thumbSB.append("(<%= ratingsStats.getTotalEntries() %> <liferay-ui:message key='<%= (ratingsStats.getTotalEntries() == 1) ? "vote" : "votes" %>' />)
								thumbSB.append("</div>");

								if (TrashUtil.isInTrash(className, classPK)) {
									thumbSB.append("<span class="rating-element rating-<%= (yourScore > 0) ? "on" : "off" %> rating-thumb-up" title="<liferay-ui:message key="ratings-are-disabled-because-this-entry-is-in-the-recycle-bin" />"></span>");

									thumbSB.append("<span class="rating-element rating-<%= (yourScore < 0) ? "on" : "off" %> rating-thumb-down" title="<liferay-ui:message key="ratings-are-disabled-because-this-entry-is-in-the-recycle-bin" />"></span>");
								}
								else {
									thumbSB.append("<a class="rating-element rating-<%= (yourScore > 0) ? "on" : "off" %> rating-thumb-up icon-thumbs-up" href="javascript:;"></a>");
									thumbSB.append("<a class="rating-element rating-<%= (yourScore < 0) ? "on" : "off" %> rating-thumb-down icon-thumbs-down" href="javascript:;"></a>");
									thumbSB.append("<div class="rating-input-container">");

									<%
									String ratingId = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_page_rating");
									%>

									thumbSB.append("<label for="<%= ratingId %>"><liferay-ui:message key='<%= (yourScore > 0) ? "you-have-rated-this-as-good" : "rate-this-as-good" %>' /></label>");
									thumbSB.append("<input class="rating-input" id="<%= ratingId %>" name="<portlet:namespace />ratingThumb" type="radio" value="up">");

									<%
									ratingId = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_page_rating");
									%>

									thumbSB.append("<label for="<%= ratingId %>"><liferay-ui:message key='<%= (yourScore > 0) ? "you-have-rated-this-as-bad" : "rate-this-as-bad" %>' /></label>");
									thumbSB.append("<input class="rating-input" id="<%= ratingId %>" name="<portlet:namespace />ratingThumb" type="radio" value="down">");
									thumbSB.append("</div>");
								}

								String thumbRating = thumbSB.toString();
								%>

								<%= thumbRating %>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<a href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="sign-in-to-vote" /></a>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</div>

	<c:if test="<%= !TrashUtil.isInTrash(className, classPK) %>">
		<aui:script use="liferay-ratings">
			Liferay.Ratings.register(
				{
					averageScore: <%= ratingsStats.getAverageScore() %>,
					className: '<%= HtmlUtil.escapeJS(className) %>',
					classPK: '<%= classPK %>',
					containerId: '<%= randomNamespace %>ratingContainer',
					namespace: '<%= randomNamespace %>',
					size: <%= numberOfStars %>,
					totalEntries: <%= ratingsStats.getTotalEntries() %>,
					totalScore: <%= ratingsStats.getTotalScore() %>,
					type: '<%= type %>',
					uri: '<%= url %>',
					yourScore: <%= yourScore %>
				}
			);
		</aui:script>
	</c:if>
</c:if>