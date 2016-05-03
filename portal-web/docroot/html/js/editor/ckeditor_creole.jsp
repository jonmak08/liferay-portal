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
	ckEditorVersion = StringPool.UNDERLINE + ckEditorVersion;

	float majorVersion = BrowserSnifferUtil.getMajorVersion(request);

	if (BrowserSnifferUtil.isChrome(request)) {
		ckEditorVersion = _setCkeditorVersion(PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_CHROME, majorVersion, ckEditorVersion);
	}
	else if (BrowserSnifferUtil.isFirefox(request)) {
		ckEditorVersion = _setCkeditorVersion(PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_FIREFOX, majorVersion, ckEditorVersion);
	}
	else if (BrowserSnifferUtil.isIe(request)) {
		ckEditorVersion = _setCkeditorVersion(PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_IE, majorVersion, ckEditorVersion);
	}
	else if (BrowserSnifferUtil.isSafari(request)) {
		ckEditorVersion = _setCkeditorVersion(PropsValues.EDITOR_CKEDITOR_VERSION_LATEST_SAFARI, majorVersion, ckEditorVersion);
	}
	else {
		ckEditorVersion = StringPool.BLANK;
	}
}
else {
	ckEditorVersion = StringPool.BLANK;
}
%>

<liferay-util:include page='<%= "/html/js/editor/ckeditor" + ckEditorVersion + ".jsp" %>'>
	<liferay-util:param name="ckEditorConfigFileName" value="ckconfig_creole.jsp" />
	<liferay-util:param name="hideImageResizing" value="<%= Boolean.TRUE.toString() %>" />
</liferay-util:include>

<%!
private String _setCkeditorVersion(float propertyValue, float majorVersion, String ckEditorVersion) {
	if (Validator.isNotNull(propertyValue)) {
		if (propertyValue > majorVersion) {
			ckEditorVersion = StringPool.BLANK;
		}
	}
	else {
		return StringPool.BLANK;
	}

	return ckEditorVersion;
}
%>