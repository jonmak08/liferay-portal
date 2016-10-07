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

<%@ include file="/html/js/editor/init.jsp" %>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%
CustomCKEditorOutputData customCKEditorOutputData = new CustomCKEditorOutputData(request);

customCKEditorOutputData.wrap();

CustomCKEditorScriptData customCKEditorScriptData = new CustomCKEditorScriptData(request);

customCKEditorScriptData.wrap();
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/js/editor/ckeditor.jsp" />
</liferay-util:buffer>

<%
customCKEditorOutputData.unwrap();

customCKEditorScriptData.unwrap();
%>

<%= _replaceVariations(html) %>

<%!
private static class CustomCKEditorScriptData extends ScriptData {

	public CustomCKEditorScriptData(HttpServletRequest request) {
		_request = request;
	}

	@Override
	public void append(String portletId, StringBundler contentSB, String use) {
		_replaceVariations(contentSB);

		_scriptData.append(portletId, contentSB, use);
	}

	public void unwrap() {
		_request.setAttribute(WebKeys.AUI_SCRIPT_DATA, _scriptData);
	}

	public void wrap() {
		_scriptData = (ScriptData)_request.getAttribute(WebKeys.AUI_SCRIPT_DATA);

		if (_scriptData == null) {
			_scriptData = new ScriptData();
		}

		_request.setAttribute(WebKeys.AUI_SCRIPT_DATA, this);
	}

	private HttpServletRequest _request;
	private ScriptData _scriptData;

}

private static class CustomCKEditorOutputData extends OutputData {

	public CustomCKEditorOutputData(HttpServletRequest request) {
		_request = request;
	}

	@Override
	public void addData(String outputKey, String webKey, StringBundler sb) {
		_replaceVariations(sb);

		_outputData.addData(outputKey, webKey, sb);
	}

	public void unwrap() {
		_request.setAttribute(WebKeys.OUTPUT_DATA, _outputData);
	}

	public void wrap() {
		_outputData = (OutputData)_request.getAttribute(WebKeys.OUTPUT_DATA);

		if (_outputData == null) {
			_outputData = new OutputData();
		}

		_request.setAttribute(WebKeys.OUTPUT_DATA, this);
	}

	private OutputData _outputData;
	private HttpServletRequest _request;

}

private static String _replaceVariations(String content) {
	String ckEditor = "/ckeditor/";
	String ckEditorLatest = "/ckeditor_latest/";

	if (!content.contains(ckEditor)) {
		ckEditor = HtmlUtil.escapeAttribute(ckEditor);
		ckEditorLatest = HtmlUtil.escapeAttribute(ckEditorLatest);
	}

	content = StringUtil.replace(content, ckEditor, ckEditorLatest);

	content = StringUtil.replace(content, "CKEDITOR.env.isCompatible = true;", "");

	return content;
}

private static void _replaceVariations(StringBundler sb) {
	String content = sb.toString();

	content = _replaceVariations(content);

	sb.setIndex(0);

	sb.append(content);
}
%>