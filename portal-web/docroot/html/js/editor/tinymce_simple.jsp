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

<%@ include file="/html/taglib/init.jsp" %>

<%
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:cssClass"));
String editorImpl = (String)request.getAttribute("liferay-ui:input-editor:editorImpl");
String initMethod = (String)request.getAttribute("liferay-ui:input-editor:initMethod");
String name = namespace + GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:name"));

String onChangeMethod = (String)request.getAttribute("liferay-ui:input-editor:onChangeMethod");

if (Validator.isNotNull(onChangeMethod)) {
	onChangeMethod = namespace + onChangeMethod;
}

boolean resizable = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-editor:resizable"));
boolean skipEditorLoading = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-editor:skipEditorLoading"));
%>

<c:if test="<%= !skipEditorLoading %>">
	<liferay-util:html-top outputKey="js_editor_tinymce">

		<%
		long javaScriptLastModified = ServletContextUtil.getLastModified(application, "/html/js/", true);
		%>

		<script src="<%= HtmlUtil.escape(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNHost() + themeDisplay.getPathJavaScript() + "/editor/tiny_mce/tiny_mce.js", javaScriptLastModified)) %>" type="text/javascript"></script>

		<script type="text/javascript">
			Liferay.namespace('EDITORS')['<%= editorImpl %>'] = true;
		</script>
	</liferay-util:html-top>
</c:if>

<div class="<%= cssClass %>">
	<textarea id="<%= name %>" name="<%= name %>" style="height: 100%; width: 100%;"></textarea>
</div>

<aui:script>
	window['<%= name %>'] = {
		onChangeCallbackCounter: 0,

		destroy: function() {
			tinyMCE.editors['<%= name %>'].destroy();

			window['<%= name %>'] = null;
		},

		focus: function() {
			tinyMCE.editors['<%= name %>'].focus();
		},

		fileBrowserCallback: function(field_name, url, type) {
		},

		getHTML: function() {
			return tinyMCE.editors['<%= name %>'].getContent();
		},

		getLanguage: function() {
			var language = '<%= HtmlUtil.escape(locale.getLanguage()) %>';
			var country = '<%= HtmlUtil.escape(locale.getCountry()) %>';

			var convertLanguage = new Array();

			if (tinyMCE.majorVersion == '3') {
				convertLanguage = ['zh-CN', 'zh-TW'];

				var languageCountry = language + '-' + country;

				for (var i = 0; i < convertLanguage.length; i++) {
					if (languageCountry == convertLanguage[i]) {
						return convertLanguage[i].toLowerCase();
					}
				}
			}
			else if (tinyMCE.majorVersion == '4') {
				convertLanguage = ['bg_BG', 'zh_CN', 'zh_TW', 'fr_FR', 'ka_GE', 'de_AT', 'he_IL', 'hu_HU', 'ko_KR', 'nb_NO', 'pt_BR', 'pt_PT', 'si_LK', 'sl_SI', 'sv_SE', 'ta_IN', 'th_TH', 'tr_TR', 'uk_UA', 'vi_VN'];

				var languageCountry = language + '_' + country;

				for (var i = 0; i < convertLanguage.length; i++) {
					if (languageCountry == convertLanguage[i]) {
						return convertLanguage[i];
					}
				}
			}

			return language;
		},

		init: function(value) {
			if (typeof value != 'string') {
				value = '';
			}

			window['<%= name %>'].setHTML(value);
		},

		initInstanceCallback: function() {
			<c:if test="<%= Validator.isNotNull(initMethod) %>">
				window['<%= name %>'].init(<%= HtmlUtil.escape(namespace + initMethod) %>());
			</c:if>
		},

		<%
		if (Validator.isNotNull(onChangeMethod)) {
		%>

			onChangeCallback: function(tinyMCE) {

				// This purposely ignores the first callback event because each call
				// to setContent triggers an undo level which fires the callback
				// when no changes have yet been made.

				// setContent is not really the correct way of initializing this
				// editor with content. The content should be placed statically
				// (from the editor's perspective) within the textarea. This is a
				// problem from the portal's perspective because it's passing the
				// content via a javascript method (initMethod).

				var onChangeCallbackCounter = window['<%= name %>'].onChangeCallbackCounter;

				if (onChangeCallbackCounter > 0) {

					<%= HtmlUtil.escapeJS(onChangeMethod) %>(window['<%= name %>'].getHTML());

				}

				onChangeCallbackCounter++;
			},

		<%
		}
		%>

		setHTML: function(value) {
			tinyMCE.editors['<%= name %>'].setContent(value);
		}
	};

	tinyMCE.init(
		{
			convert_urls: false,
			elements: '<%= name %>',
			extended_valid_elements: 'a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name|usemap],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]',
			file_browser_callback: window['<%= name %>'].fileBrowserCallback,
			init_instance_callback: window['<%= name %>'].initInstanceCallback,
			invalid_elements: 'script',
			language: window['<%= name %>'].getLanguage(),
			mode: 'textareas',

			<%
			if (Validator.isNotNull(onChangeMethod)) {
			%>

				onchange_callback: window['<%= name %>'].onChangeCallback,

			<%
			}
			%>

			plugins: 'preview,print,contextmenu',
			relative_urls: false,
			remove_script_host: false,
			theme: 'advanced',
			theme_advanced_buttons1_add: 'code,preview,print',
			theme_advanced_buttons2: '',
			theme_advanced_buttons3: '',
			theme_advanced_disable: 'formatselect,styleselect,help,strikethrough',
			theme_advanced_resize_horizontal: '<%= resizable %>',
			theme_advanced_toolbar_align: 'left',
			theme_advanced_toolbar_location: 'top'
		}
	);
</aui:script>