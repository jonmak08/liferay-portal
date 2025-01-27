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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SchedulerResponse schedulerResponse = (SchedulerResponse)row.getObject();
%>

<portlet:actionURL name="/dispatch/edit_scheduler_response" var="editSchedulerResponseURL">
	<portlet:param name="<%= Constants.CMD %>" value="runScheduledTask" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="jobName" value="<%= schedulerResponse.getJobName() %>" />
	<portlet:param name="groupName" value="<%= schedulerResponse.getGroupName() %>" />
	<portlet:param name="storageType" value="<%= schedulerResponse.getStorageType().toString() %>" />
</portlet:actionURL>

<aui:button href="<%= editSchedulerResponseURL %>" name='<%= "runNowButton" + row.getRowId() %>' value="run-now" />