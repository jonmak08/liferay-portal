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
String id = (String)request.getAttribute("liferay-frontend:toggle:id");
String showImage = (String)request.getAttribute("liferay-frontend:toggle:showImage");
String hideImage = (String)request.getAttribute("liferay-frontend:toggle:hideImage");
String showMessage = (String)request.getAttribute("liferay-frontend:toggle:showMessage");
String hideMessage = (String)request.getAttribute("liferay-frontend:toggle:hideMessage");
String stateVar = (String)request.getAttribute("liferay-frontend:toggle:stateVar");
String defaultStateValue = (String)request.getAttribute("liferay-frontend:toggle:defaultStateValue");
String defaultImage = (String)request.getAttribute("liferay-frontend:toggle:defaultImage");
String defaultMessage = (String)request.getAttribute("liferay-frontend:toggle:defaultMessage");
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(showMessage) %>">
		<a href="javascript:<%= stateVar %>Toggle();" id="<%= id %>_message"><%= defaultMessage %></a>
	</c:when>
	<c:otherwise>
		<div class="form-group" id="<%= id %>_image" title='<liferay-ui:message escapeAttribute="<%= true %>" key="toggle" />'>
			<label>
				<input class="toggle-switch" onclick="<%= stateVar %>Toggle();" type="checkbox" />

				<span aria-hidden="true" class="toggle-switch-bar">
					<span class="toggle-switch-handle"></span>
				</span>
			</label>
		</div>
	</c:otherwise>
</c:choose>

<aui:script>
	var <%= stateVar %> = '<%= defaultStateValue %>';

	Liferay.provide(
		window,
		'<%= stateVar %>Toggle',
		function(state, saveState) {
			if (state == null) {
				state = <%= stateVar %>;
			}

			if (state == '') {
				<%= stateVar %> = 'none';

				document.getElementById('<%= id %>').style.display = 'none';

				<c:choose>
					<c:when test="<%= Validator.isNotNull(showMessage) %>">
						document.getElementById('<%= id %>_message').innerHTML = '<%= showMessage %>';
					</c:when>
					<c:otherwise>
						document.getElementById('<%= id %>_image').src = '<%= showImage %>';
					</c:otherwise>
				</c:choose>

				if ((saveState == null) || saveState) {
					Liferay.Store('<%= id %>', 'none');
				}

				Liferay.fire(
					'toggle:stateChange',
					{
						id: '<%= id %>',
						state: 0
					}
				);
			}
			else {
				<%= stateVar %> = '';

				document.getElementById('<%= id %>').style.display = '';

				<c:choose>
					<c:when test="<%= Validator.isNotNull(showMessage) %>">
						document.getElementById('<%= id %>_message').innerHTML = '<%= hideMessage %>';
					</c:when>
					<c:otherwise>
						document.getElementById('<%= id %>_image').src = '<%= hideImage %>';
					</c:otherwise>
				</c:choose>

				if ((saveState == null) || saveState) {
					Liferay.Store('<%= id %>', 'block');
				}

				Liferay.fire(
					'toggle:stateChange',
					{
						id: '<%= id %>',
						state: 1
					}
				);
			}
		},
		['liferay-store']
	);
</aui:script>