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
ScriptData oldScriptData = (ScriptData)request.getAttribute(WebKeys.AUI_SCRIPT_DATA);

ScriptData newScriptData = new ScriptData() {

	@Override
	public void append(String portletId, StringBundler contentSB, String use) {
		_switchToLatestCKEditor(contentSB);

		super.append(portletId, contentSB, use);
	}

};

request.setAttribute(WebKeys.AUI_SCRIPT_DATA, newScriptData);
%>

<liferay-util:include page="/html/js/editor/ckeditor.jsp" />

<%
OutputData outputData = (OutputData)request.getAttribute(WebKeys.OUTPUT_DATA);

StringBundler outputDataSB = outputData.getData("js_editor_ckeditor_skip_editor_loading", WebKeys.PAGE_TOP);

_switchToLatestCKEditor(outputDataSB);

oldScriptData.merge(newScriptData);

request.setAttribute(WebKeys.AUI_SCRIPT_DATA, oldScriptData);
%>

<%!
private static void _switchToLatestCKEditor(StringBundler sb) {
	String content = sb.toString();

	content = StringUtil.replace(content, "/ckeditor/", "/ckeditor_latest/");

	sb.setIndex(0);

	sb.append(content);
}
%>