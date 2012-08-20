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

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_input_localized_page") + StringPool.UNDERLINE;

String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-localized:cssClass"));
String defaultLanguageId = (String)request.getAttribute("liferay-ui:input-localized:defaultLanguageId");
boolean disabled = GetterUtil.getBoolean((String) request.getAttribute("liferay-ui:input-localized:disabled"));
String id = (String)request.getAttribute("liferay-ui:input-localized:id");
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("liferay-ui:input-localized:dynamicAttributes");
String formName = (String)request.getAttribute("liferay-ui:input-localized:formName");
boolean ignoreRequestValue = GetterUtil.getBoolean((String) request.getAttribute("liferay-ui:input-localized:ignoreRequestValue"));
String languageId = (String)request.getAttribute("liferay-ui:input-localized:languageId");
String maxLength = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-localized:maxLength"));
String name = (String)request.getAttribute("liferay-ui:input-localized:name");
String xml = (String)request.getAttribute("liferay-ui:input-localized:xml");
String type = (String)request.getAttribute("liferay-ui:input-localized:type");

Locale defaultLocale = null;

final String LOCALIZER_CLASS = "lfr-localizer";

final String LOCALIZER_NS = LOCALIZER_CLASS + "-";

final String LOCALIZER_CONTENT_BOX = randomNamespace + "languageSelections";

final String INPUT_ID_SUFFIX = StringPool.UNDERLINE + "display";

final String COUNT_CLASS = LOCALIZER_NS + "locale-count";
final Integer COUNT_OFFSEST = 1;

final String FLAG_CLASS = LOCALIZER_NS + "locale-flag";
final String FLAG_IMAGE_EXT = "png";
final String FLAG_IMAGES_PATH = themeDisplay.getPathThemeImages() + "/language/";

final String LOCALES_MAP = randomNamespace + "locales";

if (Validator.isNotNull(defaultLanguageId)) {
	defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);
}
else {
	defaultLocale = LocaleUtil.getDefault();
	defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);
}

Locale[] locales = LanguageUtil.getAvailableLocales();

boolean displayLocaleSelector = Validator.isNull(languageId) && (locales.length > 1);

String mainLanguageId = defaultLanguageId;

if (Validator.isNotNull(languageId)) {
	mainLanguageId = languageId;
}

String mainLanguageValue = LocalizationUtil.getLocalization(xml, mainLanguageId, false);

if (!ignoreRequestValue) {
	mainLanguageValue = ParamUtil.getString(request, name + StringPool.UNDERLINE + mainLanguageId, mainLanguageValue);
}

if (Validator.isNull(mainLanguageValue)) {
	mainLanguageValue = LocalizationUtil.getLocalization(xml, defaultLanguageId, true);
}
%>

<span class="taglib-input-localized" id="<%= LOCALIZER_CONTENT_BOX %>">
	<c:choose>
		<c:when test='<%= type.equals("input") %>'>

			<input
				class="<%= cssClass %> lfr-input-text <%= displayLocaleSelector ? "lfr-localizer-selector-displayed" : "" %>"
				<%= disabled ? "disabled=\"disabled\"" : "" %>
				id="<portlet:namespace /><%= id + StringPool.UNDERLINE + INPUT_ID_SUFFIX %>"
				name="<portlet:namespace /><%= name + StringPool.UNDERLINE + HtmlUtil.escape(mainLanguageId) %>"
				type="text"
				value="<%= HtmlUtil.escape(mainLanguageValue) %>"
				<%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>
			>

		</c:when>
		<c:when test='<%= type.equals("textarea") %>'>

			<textarea
				class="<%= cssClass %> lfr-textarea <%= displayLocaleSelector ? "lfr-localizer-selector-displayed" : "" %>"
				<%= disabled ? "disabled=\"disabled\"" : "" %>
				id="<portlet:namespace /><%= id + StringPool.UNDERLINE + INPUT_ID_SUFFIX %>"
				name="<portlet:namespace /><%= name + StringPool.UNDERLINE + HtmlUtil.escape(mainLanguageId) %>"
				<%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>
			><%= HtmlUtil.escape(mainLanguageValue) %></textarea>

		</c:when>
	</c:choose>

	<c:if test="<%= Validator.isNotNull(maxLength) %>">

		<aui:script use="aui-char-counter">
			new A.CharCounter(
				{
					input: '<%= StringPool.POUND %><portlet:namespace /><%= id + StringPool.UNDERLINE + INPUT_ID_SUFFIX %>',
					maxLength: <%= maxLength %>
				}
			);
		</aui:script>

	</c:if>

	<c:if test="<%= displayLocaleSelector %>">
		<%
		List<String> languageIds = new ArrayList<String>();

		if (Validator.isNotNull(xml)) {
			for (int i = 0; i < locales.length; i++) {
				if (locales[i].equals(defaultLocale)) {
					continue;
				}

				String selLanguageId = LocaleUtil.toLanguageId(locales[i]);
				String languageValue = LocalizationUtil.getLocalization(xml, selLanguageId, false);

				if (Validator.isNotNull(languageValue) || (!ignoreRequestValue && (request.getParameter(name + StringPool.UNDERLINE + selLanguageId) != null))) {
					languageIds.add(selLanguageId);
				}
			}
		}

		for (int i = 0; i < languageIds.size(); i++) {
			String curLanguageId = languageIds.get(i);
			String languageValue = StringPool.BLANK;

			if (Validator.isNotNull(xml)) {
				languageValue = LocalizationUtil.getLocalization(xml, curLanguageId, false);
			}

			if (!ignoreRequestValue) {
				languageValue = ParamUtil.getString(request, name + StringPool.UNDERLINE + curLanguageId, languageValue);
			}
			%>

			<input
				id="<portlet:namespace /><%= id + StringPool.UNDERLINE + HtmlUtil.escape(curLanguageId) %>"
				name="<portlet:namespace /><%= name + StringPool.UNDERLINE + HtmlUtil.escape(curLanguageId) %>"
				type="hidden"
				value="<%= HtmlUtil.escape(languageValue) %>"
			>

			<c:if test="<%= Validator.isNotNull(maxLength) %>">
				<aui:script use="aui-char-counter">
					new A.CharCounter(
						{
							input: '<%= StringPool.POUND %><portlet:namespace /><%= id + StringPool.UNDERLINE + curLanguageId %>',
							maxLength: <%= maxLength %>
						}
					);
				</aui:script>
			</c:if>
		<%
		}
		%>

		<liferay-util:buffer var="localizerLocaleStatus">
			<span class="<%= COUNT_CLASS %>"><%= languageIds.size() + COUNT_OFFSEST %></span>
				<img
					alt="<%= defaultLocale.getDisplayName() %>"
					class="<%= FLAG_CLASS %>"
					src="<%= FLAG_IMAGES_PATH %><%= mainLanguageId %>.<%= FLAG_IMAGE_EXT %>"
					title="<%= defaultLocale.getDisplayName() %>">
		</liferay-util:buffer>

		<c:choose>
			<c:when test='<%= type.equals("input") %>'>
				<span class="aui-text-button <%= LOCALIZER_CLASS %>">
					<%= localizerLocaleStatus %>
				</span>
			</c:when>
			<c:when test='<%= type.equals("textarea") %>'>
				<div class="aui-textarea-ft">
					<span class="aui-textarea-button <%= LOCALIZER_CLASS %>">
						<%= localizerLocaleStatus %>
					</span>
				</div>
			</c:when>
		</c:choose>
	</c:if>
</span>

<c:if test="<%= displayLocaleSelector %>">
	<aui:script use="liferay-localizer">
		var <%= LOCALES_MAP %> = [
			<%
			for (int i = 0; i < locales.length; i++) {
				String selLanguageId = LocaleUtil.toLanguageId(locales[i]);
				String selLanguageName = locales[i].getDisplayName(locale);
				boolean needsComma = ((i + 1) < locales.length);
			%>
				{
					id: '<%= selLanguageId %>',
					name: '<%= selLanguageName %>'
				}<c:if test="<%= needsComma %>">,</c:if>
			<%
			}
			%>
		]

		new Liferay.Localizer(
			{
				contentBox: '<%= StringPool.POUND %><%= LOCALIZER_CONTENT_BOX %>',
				flagImagesPath: '<%= FLAG_IMAGES_PATH %>',
				locales: <%= LOCALES_MAP %>,
				namespace: '<portlet:namespace /><%= name + StringPool.UNDERLINE %>'
			}
		).render();
	</aui:script>
</c:if>