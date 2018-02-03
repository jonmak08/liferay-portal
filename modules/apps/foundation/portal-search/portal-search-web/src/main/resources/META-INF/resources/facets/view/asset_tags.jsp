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

<%@ include file="/facets/init.jsp" %>

<%
<<<<<<< HEAD
com.liferay.portal.search.web.internal.facet.display.builder.AssetTagsSearchFacetDisplayBuilder assetTagsSearchFacetDisplayBuilder = new com.liferay.portal.search.web.internal.facet.display.builder.AssetTagsSearchFacetDisplayBuilder();

assetTagsSearchFacetDisplayBuilder.setDisplayStyle(dataJSONObject.getString("displayStyle", "cloud"));
assetTagsSearchFacetDisplayBuilder.setFacet(facet);
assetTagsSearchFacetDisplayBuilder.setFrequenciesVisible(dataJSONObject.getBoolean("showAssetCount", true));
assetTagsSearchFacetDisplayBuilder.setFrequencyThreshold(dataJSONObject.getInt("frequencyThreshold"));
assetTagsSearchFacetDisplayBuilder.setMaxTerms(dataJSONObject.getInt("maxTerms", 10));
assetTagsSearchFacetDisplayBuilder.setParameterName(facet.getFieldId());
assetTagsSearchFacetDisplayBuilder.setParameterValue(fieldParam);

com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext = assetTagsSearchFacetDisplayBuilder.build();
%>

<c:choose>
	<c:when test="<%= assetTagsSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getParameterValue() %>" />
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<liferay-ui:message key="tags" />
				</div>
			</div>

			<div class="panel-body">
				<div class="asset-tags <%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
					<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getParameterValue() %>" />

					<ul class="<%= assetTagsSearchFacetDisplayContext.isCloudWithCount() ? "tag-cloud" : "tag-list" %> list-unstyled">
						<li class="default facet-value">
							<a class="<%= assetTagsSearchFacetDisplayContext.isNothingSelected() ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
						</li>

						<%
						java.util.List<com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext> assetTagsSearchFacetTermDisplayContexts = assetTagsSearchFacetDisplayContext.getTermDisplayContexts();

						for (com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext assetTagsSearchFacetTermDisplayContext : assetTagsSearchFacetTermDisplayContexts) {
						%>

							<li class="facet-value tag-popularity-<%= assetTagsSearchFacetTermDisplayContext.getPopularity() %>">
								<a class="<%= assetTagsSearchFacetTermDisplayContext.isSelected() ? "text-primary" : "text-default" %>" data-value="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetTermDisplayContext.getValue()) %>" href="javascript:;">
									<%= HtmlUtil.escape(assetTagsSearchFacetTermDisplayContext.getDisplayName()) %>

									<c:if test="<%= assetTagsSearchFacetTermDisplayContext.isFrequencyVisible() %>">
										<span class="frequency">(<%= assetTagsSearchFacetTermDisplayContext.getFrequency() %>)</span>
									</c:if>
								</a>
							</li>

						<%
						}
						%>

					</ul>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
=======
if (termCollectors.isEmpty()) {
	return;
}

String displayStyle = dataJSONObject.getString("displayStyle", "cloud");
int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
int maxTerms = dataJSONObject.getInt("maxTerms", 10);
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<liferay-ui:message key="tags" />
		</div>
	</div>

	<div class="panel-body">
		<div class="asset-tags <%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" type="hidden" value="<%= fieldParam %>" />

			<ul class="<%= (showAssetCount && displayStyle.equals("cloud")) ? "tag-cloud" : "tag-list" %> list-unstyled">
				<li class="default facet-value">
					<a class="<%= Validator.isNull(fieldParam) ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
				</li>

				<%
				int maxCount = 1;
				int minCount = 1;

				if (showAssetCount && displayStyle.equals("cloud")) {

					// The cloud style may not list tags in the order of frequency,
					// so keep looking through the results until we reach the maximum
					// number of terms or we run out of terms.

					for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
						if (j >= maxTerms) {
							break;
						}

						TermCollector termCollector = termCollectors.get(i);

						int frequency = termCollector.getFrequency();

						if (frequencyThreshold > frequency) {
							j--;

							continue;
						}

						maxCount = Math.max(maxCount, frequency);
						minCount = Math.min(minCount, frequency);
					}
				}

				double multiplier = 1;

				if (maxCount != minCount) {
					multiplier = (double)5 / (maxCount - minCount);
				}

				for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
					if (j >= maxTerms) {
						break;
					}

					TermCollector termCollector = termCollectors.get(i);

					int popularity = (int)(1 + ((maxCount - (maxCount - (termCollector.getFrequency() - minCount))) * multiplier));

					if (frequencyThreshold > termCollector.getFrequency()) {
						j--;

						continue;
					}
				%>

					<li class="facet-value tag-popularity-<%= popularity %>">
						<a class="<%= fieldParam.equals(termCollector.getTerm()) ? "text-primary" : "text-default" %>" data-value="<%= HtmlUtil.escapeAttribute(termCollector.getTerm()) %>" href="javascript:;">
							<%= HtmlUtil.escape(termCollector.getTerm()) %>

							<c:if test="<%= showAssetCount %>">
								<span class="frequency">(<%= termCollector.getFrequency() %>)</span>
							</c:if>
						</a>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</div>
</div>
>>>>>>> compatible
