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
String redirect = ParamUtil.getString(request, "redirect");
%>

<div class="container-fluid-1280">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>

			<%
			PortletURL redirectURL = renderResponse.createRenderURL();

			redirectURL.setParameter("hideDefaultSuccessMessage", Boolean.TRUE.toString());
			redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp");
			redirectURL.setParameter("redirect", redirect);
			redirectURL.setWindowState(LiferayWindowState.POP_UP);

<<<<<<< HEAD
			long[] groupIds = assetPublisherDisplayContext.getGroupIds();

			Map<Long, List<AssetPublisherAddItemHolder>> scopeAssetPublisherAddItemHolders = assetPublisherDisplayContext.getScopeAssetPublisherAddItemHolders(groupIds.length);
=======
			List<Long> addPortletURLsGroupIds = new ArrayList();
>>>>>>> compatible
			%>

			<aui:select label="scope" name="selectScope">

				<%
<<<<<<< HEAD
				for (Long groupId : scopeAssetPublisherAddItemHolders.keySet()) {
				%>

					<aui:option label="<%= HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale)) %>" selected="<%= groupId == scopeGroupId %>" value="<%= groupId %>" />

				<%
=======
				long[] groupIds = assetPublisherDisplayContext.getGroupIds();

				for (long groupId : groupIds) {
					Map<String, PortletURL> addPortletURLs = AssetUtil.getAddPortletURLs(liferayPortletRequest, liferayPortletResponse, groupId, assetPublisherDisplayContext.getClassNameIds(), assetPublisherDisplayContext.getClassTypeIds(), assetPublisherDisplayContext.getAllAssetCategoryIds(), assetPublisherDisplayContext.getAllAssetTagNames(), redirectURL.toString());

					if ((addPortletURLs != null) && !addPortletURLs.isEmpty()) {
						addPortletURLsGroupIds.add(groupId);
				%>

						<aui:option label="<%= HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale)) %>" selected="<%= groupId == scopeGroupId %>" value="<%= groupId %>" />

				<%
					}
>>>>>>> compatible
				}
				%>

			</aui:select>

			<%
<<<<<<< HEAD
			for (Map.Entry<Long, List<AssetPublisherAddItemHolder>> entry : scopeAssetPublisherAddItemHolders.entrySet()) {
				Long groupId = entry.getKey();
				List<AssetPublisherAddItemHolder> assetPublisherAddItemHolders = entry.getValue();
=======
			for (Long groupId : addPortletURLsGroupIds) {
				Map<String, PortletURL> addPortletURLs = AssetUtil.getAddPortletURLs(liferayPortletRequest, liferayPortletResponse, groupId, assetPublisherDisplayContext.getClassNameIds(), assetPublisherDisplayContext.getClassTypeIds(), assetPublisherDisplayContext.getAllAssetCategoryIds(), assetPublisherDisplayContext.getAllAssetTagNames(), redirectURL.toString());
>>>>>>> compatible
			%>

				<div class="asset-entry-type <%= (groupId == scopeGroupId) ? StringPool.BLANK : "hide" %>" id="<%= liferayPortletResponse.getNamespace() + groupId %>">
					<aui:select cssClass="asset-entry-type-select" label="asset-entry-type" name="selectAssetEntryType">

						<%
<<<<<<< HEAD
						for (AssetPublisherAddItemHolder assetPublisherAddItemHolder : assetPublisherAddItemHolders) {
							String message = assetPublisherAddItemHolder.getModelResource();
=======
						for (Map.Entry<String, PortletURL> entry : addPortletURLs.entrySet()) {
							AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(AssetUtil.getClassName(entry.getKey()));

							String message = AssetUtil.getClassNameMessage(entry.getKey(), locale);
>>>>>>> compatible

							long curGroupId = groupId;

							Group group = GroupLocalServiceUtil.fetchGroup(groupId);

<<<<<<< HEAD
							if (!group.isStagedPortlet(assetPublisherAddItemHolder.getPortletId()) && !group.isStagedRemotely()) {
=======
							if (!group.isStagedPortlet(assetRendererFactory.getPortletId()) && !group.isStagedRemotely()) {
>>>>>>> compatible
								curGroupId = group.getLiveGroupId();
							}

							Map<String, Object> data = new HashMap<String, Object>();

							data.put("title", LanguageUtil.format((HttpServletRequest)pageContext.getRequest(), "new-x", HtmlUtil.escape(message), false));
<<<<<<< HEAD
							data.put("url", assetHelper.getAddURLPopUp(curGroupId, plid, assetPublisherAddItemHolder.getPortletURL(), false, null));
=======
							data.put("url", AssetUtil.getAddURLPopUp(curGroupId, plid, entry.getValue(), assetRendererFactory.getPortletId(), false, null));
>>>>>>> compatible
						%>

							<aui:option data="<%= data %>" label="<%= HtmlUtil.escape(message) %>" />

						<%
						}
						%>

					</aui:select>
				</div>

				<aui:script>
					Liferay.Util.toggleSelectBox('<portlet:namespace />selectScope', '<%= groupId %>', '<portlet:namespace /><%= groupId %>');
				</aui:script>

			<%
			}
			%>

		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
<<<<<<< HEAD
		<aui:button onClick='<%= renderResponse.getNamespace() + "addAssetEntry();" %>' primary="<%= true %>" value="add" />
=======
		<aui:button cssClass="btn-lg" onClick='<%= renderResponse.getNamespace() + "addAssetEntry();" %>' primary="<%= true %>" value="add" />
>>>>>>> compatible
	</aui:button-row>
</div>

<aui:script>
	function <portlet:namespace />addAssetEntry() {
		var A = AUI();

		var visibleItem = A.one('.asset-entry-type:not(.hide)');

		var assetEntryTypeSelector = visibleItem.one('.asset-entry-type-select');

		var index = assetEntryTypeSelector.get('selectedIndex');

		var selectedOption = assetEntryTypeSelector.get('options').item(index);

		var title = selectedOption.attr('data-title');
		var url = selectedOption.attr('data-url');

		var dialog = Liferay.Util.getWindow();

		dialog.iframe.set('uri', url);
		dialog.titleNode.html(title);
	}
</aui:script>