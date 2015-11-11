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

<%@ include file="/html/common/init.jsp" %>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%
String ckEditorVersion = PropsUtil.get(PropsKeys.EDITOR_CKEDITOR_VERSION);

if (Validator.equals(ckEditorVersion, "latest")) {
	float majorVersion = BrowserSnifferUtil.getMajorVersion(request);

	if (BrowserSnifferUtil.isChrome(request)) {
		if (PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_CHROME < majorVersion) {
			ckEditorVersion = "default";
		}
	}
	else if (BrowserSnifferUtil.isFirefox(request)) {
		if (PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_FIREFOX < majorVersion) {
			ckEditorVersion = "default";
		}
	}
	else if (BrowserSnifferUtil.isIe(request)) {
		if (PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_IE < majorVersion) {
			ckEditorVersion = "default";
		}
	}
	else {
		ckEditorVersion = "default";
	}
}

if (Validator.equals(ckEditorVersion, "latest")) {
	ckEditorVersion = StringPool.UNDERLINE + ckEditorVersion;
}
else {
	ckEditorVersion = StringPool.BLANK;
}
%>

<liferay-util:include page='<%= "/html/js/editor/ckeditor" + ckEditorVersion + ".jsp" %>'>
	<liferay-util:param name="ckEditorConfigFileName" value="ckconfig_bbcode.jsp" />
</liferay-util:include>