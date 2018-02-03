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

ConfigurationModel configurationModel = (ConfigurationModel)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_MODEL);
String ddmFormHTML = (String)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_MODEL_FORM_HTML);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

ResourceBundleLoaderProvider resourceBundleLoaderProvider = (ResourceBundleLoaderProvider)request.getAttribute(ConfigurationAdminWebKeys.RESOURCE_BUNDLE_LOADER_PROVIDER);

ResourceBundleLoader resourceBundleLoader = resourceBundleLoaderProvider.getResourceBundleLoader(configurationModel.getBundleSymbolicName());

<<<<<<< HEAD
ResourceBundle componentResourceBundle = resourceBundleLoader.loadResourceBundle(PortalUtil.getLocale(request));
=======
ResourceBundle componentResourceBundle = resourceBundleLoader.loadResourceBundle(LanguageUtil.getLanguageId(request));
>>>>>>> compatible

String configurationModelName = (componentResourceBundle != null) ? LanguageUtil.get(componentResourceBundle, configurationModel.getName()) : configurationModel.getName();

renderResponse.setTitle(configurationModelName);
%>

<liferay-ui:error exception="<%= ConfigurationModelListenerException.class %>">

	<%
	ConfigurationModelListenerException cmle = (ConfigurationModelListenerException)errorException;
	%>

	<liferay-ui:message key="<%= cmle.causeMessage %>" localizeKey="<%= false %>" />
</liferay-ui:error>

<portlet:actionURL name="bindConfiguration" var="bindConfigurationActionURL" />
<portlet:actionURL name="deleteConfiguration" var="deleteConfigurationActionURL" />

<div class="container-fluid-1280">
	<aui:form action="<%= bindConfigurationActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="factoryPid" type="hidden" value="<%= configurationModel.getFactoryPid() %>" />
		<aui:input name="pid" type="hidden" value="<%= configurationModel.getID() %>" />
<<<<<<< HEAD
=======
		<aui:input name="availableLanguageIds" type="hidden" value="<%= themeDisplay.getLanguageId() %>" />
		<aui:input name="defaultLanguageId" type="hidden" value="<%= themeDisplay.getLanguageId() %>" />
>>>>>>> compatible

		<div class="lfr-ddm-container" id="lfr-ddm-container">
			<aui:fieldset-group>
				<c:if test="<%= !configurationModel.hasConfiguration() %>">
					<aui:alert closeable="<%= false %>" id="errorAlert" type="info">
						<liferay-ui:message key="this-configuration-was-not-saved-yet" />
					</aui:alert>
				</c:if>

				<%
				String configurationModelDescription = (componentResourceBundle != null) ? LanguageUtil.get(componentResourceBundle, configurationModel.getDescription()) : configurationModel.getDescription();
				%>

				<c:if test="<%= !Validator.isBlank(configurationModelDescription) %>">
					<p class="text-default">
						<strong><%= configurationModelDescription %></strong>
					</p>
				</c:if>

				<%= ddmFormHTML %>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<c:choose>
				<c:when test="<%= configurationModel.hasConfiguration() %>">
<<<<<<< HEAD
					<aui:button name="update" type="submit" value="update" />
				</c:when>
				<c:otherwise>
					<aui:button name="save" type="submit" value="save" />
				</c:otherwise>
			</c:choose>

			<aui:button href="<%= redirect %>" name="cancel" type="cancel" />
=======
					<aui:button cssClass="btn-lg" name="update" type="submit" value="update" />
				</c:when>
				<c:otherwise>
					<aui:button cssClass="btn-lg" name="save" type="submit" value="save" />
				</c:otherwise>
			</c:choose>

			<aui:button cssClass="btn-lg" href="<%= redirect %>" name="cancel" type="cancel" />
>>>>>>> compatible
		</aui:button-row>
	</aui:form>
</div>