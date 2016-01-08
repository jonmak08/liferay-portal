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

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">liferay-ui:quick-access-entry</span>
</div>

<liferay-ui:quick-access-entry label="skip-to-search" />
<liferay-ui:quick-access-entry label="skip-to-content" onClick="#" url="#" />

<%
out.println("Contents of quick access entries: <br />");

List<QuickAccessEntry> quickAccessEntries =
	(List<QuickAccessEntry>)request.getAttribute(
		WebKeys.PORTLET_QUICK_ACCESS_ENTRIES);

for (QuickAccessEntry entry : quickAccessEntries) {
	out.println(entry.getLabel() + "<br />");
}
%>