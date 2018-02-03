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
<<<<<<< HEAD
List<String> classNames = (List<String>)request.getAttribute("liferay-trash:undo:classNames");
String cmd = (String)request.getAttribute("liferay-trash:undo:cmd");
String portletURL = (String)request.getAttribute("liferay-trash:undo:portletURL");
List<Long> restoreTrashEntryIds = (List<Long>)request.getAttribute("liferay-trash:undo:restoreTrashEntryIds");
List<String> titles = (List<String>)request.getAttribute("liferay-trash:undo:titles");
int trashedEntriesCount = GetterUtil.getInteger(request.getAttribute("liferay-trash:undo:trashedEntriesCount"));
=======
String portletURL = (String)request.getAttribute("liferay-trash:undo:portletURL");

Map<String, String[]> data = (HashMap<String, String[]>)SessionMessages.get(portletRequest, portletDisplay.getId() + SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA);

int trashedEntriesCount = 0;

String[] primaryKeys = new String[0];

Set<String> keys = data.keySet();

for (String key : keys) {
	if (!key.endsWith("Ids")) {
		continue;
	}

	primaryKeys = ArrayUtil.append(primaryKeys, data.get(key));

	trashedEntriesCount = primaryKeys.length;
}
>>>>>>> compatible
%>

<liferay-util:buffer var="alertMessage">
	<aui:form action="<%= portletURL %>" cssClass="alert-trash-form" name="undoForm">
		<liferay-util:buffer var="trashLink">
			<c:choose>
				<c:when test="<%= themeDisplay.isShowSiteAdministrationIcon() %>">

					<%
<<<<<<< HEAD
					PortletURL trashURL = PortletProviderUtil.getPortletURL(request, TrashEntry.class.getName(), PortletProvider.Action.VIEW);
=======
					PortletURL trashURL = TrashUtil.getViewURL(request);
>>>>>>> compatible
					%>

					<aui:a cssClass="alert-link" href="<%= trashURL.toString() %>" label="the-recycle-bin" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="the-recycle-bin" />
				</c:otherwise>
			</c:choose>
		</liferay-util:buffer>

<<<<<<< HEAD
=======
		<%
		String cmd = MapUtil.getString(data, Constants.CMD);
		%>

>>>>>>> compatible
		<c:choose>
			<c:when test="<%= trashedEntriesCount > 1 %>">
				<c:choose>
					<c:when test="<%= Objects.equals(cmd, Constants.REMOVE) %>">
						<liferay-ui:message arguments="<%= new Object[] {trashedEntriesCount} %>" key="x-items-were-removed" translateArguments="<%= false %>" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message arguments="<%= new Object[] {trashedEntriesCount, trashLink.trim()} %>" key="x-items-were-moved-to-x" translateArguments="<%= false %>" />
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>

				<%
<<<<<<< HEAD
=======
				String[] classNames = data.get("deleteEntryClassName");

>>>>>>> compatible
				String className = null;

				String type = "selected-item";

<<<<<<< HEAD
				if (ListUtil.isNotEmpty(classNames)) {
					className = classNames.get(0);
=======
				if (ArrayUtil.isNotEmpty(classNames)) {
					className = classNames[0];
>>>>>>> compatible

					type = ResourceActionsUtil.getModelResource(request, className);
				}

<<<<<<< HEAD
				String title = StringPool.BLANK;

				if (ListUtil.isNotEmpty(titles)) {
					title = titles.get(0);
=======
				String[] titles = data.get("deleteEntryTitle");

				String title = StringPool.BLANK;

				if (ArrayUtil.isNotEmpty(titles)) {
					title = titles[0];
>>>>>>> compatible
				}
				%>

				<liferay-util:buffer var="trashEntityLink">
					<c:if test="<%= Validator.isNotNull(title) %>">
						<strong><em class="delete-entry-title"><%= HtmlUtil.escape(title) %></em></strong>
					</c:if>
				</liferay-util:buffer>

				<c:choose>
					<c:when test="<%= Objects.equals(cmd, Constants.REMOVE) %>">
						<liferay-ui:message arguments="<%= new Object[] {LanguageUtil.get(request, type), trashEntityLink} %>" key="the-x-x-was-removed" translateArguments="<%= false %>" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message arguments="<%= new Object[] {LanguageUtil.get(request, type), trashEntityLink, trashLink.trim()} %>" key="the-x-x-was-moved-to-x" translateArguments="<%= false %>" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>

<<<<<<< HEAD
		<aui:input name="restoreTrashEntryIds" type="hidden" value="<%= StringUtil.merge(restoreTrashEntryIds) %>" />
=======
		<%
		for (String key : keys) {
			if (!key.endsWith("Ids")) {
				continue;
			}

			primaryKeys = data.get(key);
		%>

			<aui:input name="<%= key %>" type="hidden" value="<%= StringUtil.merge(primaryKeys) %>" />

		<%
		}
		%>
>>>>>>> compatible

		<aui:button cssClass="alert-link btn-link trash-undo-button" type="submit" value="undo" />
	</aui:form>
</liferay-util:buffer>

<liferay-ui:alert
	icon="check"
	message="<%= alertMessage %>"
	timeout="<%= 0 %>"
	type="success"
/>