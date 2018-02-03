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
String redirect = ParamUtil.getString(request, "redirect");

DDMDataProviderInstance ddmDataProviderInstance = ddmDataProviderDisplayContext.fetchDataProviderInstance();

long dataProviderInstanceId = BeanParamUtil.getLong(ddmDataProviderInstance, request, "dataProviderInstanceId");

long groupId = BeanParamUtil.getLong(ddmDataProviderInstance, request, "groupId", scopeGroupId);
String name = BeanParamUtil.getString(ddmDataProviderInstance, request, "name");
String description = BeanParamUtil.getString(ddmDataProviderInstance, request, "description");
String type = BeanParamUtil.getString(ddmDataProviderInstance, request, "type");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((ddmDataProviderInstance == null) ? LanguageUtil.get(request, type) : ddmDataProviderInstance.getName(locale));
%>

<portlet:actionURL name="addDataProvider" var="addDataProviderURL">
	<portlet:param name="mvcPath" value="/edit_data_provider.jsp" />
</portlet:actionURL>

<portlet:actionURL name="updateDataProvider" var="updateDataProviderURL">
	<portlet:param name="mvcPath" value="/edit_data_provider.jsp" />
</portlet:actionURL>

<aui:form action="<%= (ddmDataProviderInstance == null) ? addDataProviderURL : updateDataProviderURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(groupId) %>" />
	<aui:input name="dataProviderInstanceId" type="hidden" value="<%= String.valueOf(dataProviderInstanceId) %>" />
	<aui:input name="type" type="hidden" value="<%= type %>" />
	<aui:input name="languageId" type="hidden" value="<%= String.valueOf(themeDisplay.getLanguageId()) %>" />

<<<<<<< HEAD
	<div class="container-fluid-1280">
		<aui:fieldset-group markupView="lexicon">
=======
	<aui:fieldset-group markupView="lexicon">
		<div class="container-fluid-1280">
>>>>>>> compatible
			<aui:fieldset>
				<liferay-util:buffer var="requiredMark">
					<span class="icon-asterisk text-warning">
						<span class="hide-accessible"><liferay-ui:message key="required" /></span>
					</span>
				</liferay-util:buffer>

				<label class="required-warning">
					<liferay-ui:message arguments="<%= requiredMark %>" key="all-fields-marked-with-x-are-required" translateArguments="<%= false %>" />
				</label>

				<aui:input name="name" placeholder="enter-the-data-provider-name" required="<%= true %>" type="text" value="<%= ddmDataProviderDisplayContext.getDataProviderInstanceName() %>" />

				<aui:input name="description" placeholder="enter-a-short-description" type="textarea" value="<%= ddmDataProviderDisplayContext.getDataProviderInstanceDescription() %>" />
			</aui:fieldset>

			<aui:fieldset>
				<%= ddmDataProviderDisplayContext.getDataProviderInstanceDDMFormHTML() %>
			</aui:fieldset>
<<<<<<< HEAD

			<c:if test="<%= ddmDataProviderInstance == null %>">
				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="permissions">
					<liferay-ui:input-permissions
						modelName="<%= DDMDataProviderInstance.class.getName() %>"
					/>
				</aui:fieldset>
			</c:if>
		</aui:fieldset-group>
	</div>
=======
		</div>
	</aui:fieldset-group>
>>>>>>> compatible

	<c:if test="<%= !windowState.equals(LiferayWindowState.POP_UP) %>">
		<div class="container-fluid-1280">
			<aui:button-row>
<<<<<<< HEAD
				<aui:button id="submit" label="save" type="submit" />

				<aui:button href="<%= redirect %>" name="cancelButton" type="cancel" />
=======
				<aui:button cssClass="btn-lg" id="submit" label="save" type="submit" />

				<aui:button cssClass="btn-lg" href="<%= redirect %>" name="cancelButton" type="cancel" />
>>>>>>> compatible
			</aui:button-row>
		</div>
	</c:if>

	<aui:button cssClass="hide" type="submit" />
</aui:form>

<portlet:renderURL var="viewDataProviderURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<c:if test="<%= windowState.equals(LiferayWindowState.POP_UP) %>">
	<aui:script>
		var dialog = Liferay.Util.getWindow();

		if (dialog) {
			dialog.addToolbar(
				[
					{
<<<<<<< HEAD
						cssClass: 'btn-primary',
=======
						cssClass: 'btn-lg btn-primary',
>>>>>>> compatible
						label: '<liferay-ui:message key="save" />',
						on: {
							click: function() {
								document.<portlet:namespace />fm.submit();
							}
						}
					},
					{
<<<<<<< HEAD
						cssClass: 'btn-link',
=======
						cssClass: 'btn-lg btn-link',
>>>>>>> compatible
						label: '<liferay-ui:message key="cancel" />',
						on: {
							click: function() {
								location.href = '<%= viewDataProviderURL.toString() %>';
							}
						}
					}
				],
				'footer'
			);
		}
	</aui:script>
</c:if>