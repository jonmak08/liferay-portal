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

<<<<<<< HEAD
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
=======
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
>>>>>>> compatible

<%@ page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.model.JournalArticleDisplay" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);
%>

<div class="content-metadata-asset-addon-entry content-metadata-asset-addon-entry-links">
<<<<<<< HEAD
	<liferay-asset:asset-links
=======
	<liferay-ui:asset-links
>>>>>>> compatible
		className="<%= JournalArticle.class.getName() %>"
		classPK="<%= articleDisplay.getResourcePrimKey() %>"
	/>
</div>