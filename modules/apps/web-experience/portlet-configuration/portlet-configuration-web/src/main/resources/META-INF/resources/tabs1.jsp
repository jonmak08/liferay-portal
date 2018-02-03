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
String tabs1 = ParamUtil.getString(request, "tabs1");

String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

PortalUtil.addPortletBreadcrumbEntry(request, PortalUtil.getPortletTitle(renderResponse), null);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "configuration"), null);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, tabs1), currentURL);
<<<<<<< HEAD

List<NavigationItem> navigationItems = new ArrayList<>();

if (selPortlet.getConfigurationActionInstance() != null) {
	NavigationItem navigationItem = new NavigationItem();

	navigationItem.setActive(tabs1.equals("setup"));

	PortletURL configurationURL = renderResponse.createRenderURL();

	configurationURL.setParameter("mvcPath", "/edit_configuration.jsp");
	configurationURL.setParameter("redirect", redirect);
	configurationURL.setParameter("returnToFullPageURL", returnToFullPageURL);
	configurationURL.setParameter("portletConfiguration", Boolean.TRUE.toString());
	configurationURL.setParameter("portletResource", portletResource);

	navigationItem.setHref(configurationURL.toString());

	navigationItem.setLabel(LanguageUtil.get(request, "setup"));

	navigationItems.add(navigationItem);
}

if (selPortlet.hasMultipleMimeTypes()) {
	NavigationItem navigationItem = new NavigationItem();

	navigationItem.setActive(tabs1.equals("supported-clients"));

	PortletURL supportedClientsURL = renderResponse.createRenderURL();

	supportedClientsURL.setParameter("mvcPath", "/edit_supported_clients.jsp");
	supportedClientsURL.setParameter("redirect", redirect);
	supportedClientsURL.setParameter("returnToFullPageURL", returnToFullPageURL);
	supportedClientsURL.setParameter("portletConfiguration", Boolean.TRUE.toString());
	supportedClientsURL.setParameter("portletResource", portletResource);

	navigationItem.setHref(supportedClientsURL.toString());

	navigationItem.setLabel(LanguageUtil.get(request, "supported-clients"));

	navigationItems.add(navigationItem);
}

Set<PublicRenderParameter> publicRenderParameters = selPortlet.getPublicRenderParameters();

if (!publicRenderParameters.isEmpty()) {
	NavigationItem navigationItem = new NavigationItem();

	navigationItem.setActive(tabs1.equals("communication"));

	PortletURL publicRenderParametersURL = renderResponse.createRenderURL();

	publicRenderParametersURL.setParameter("mvcPath", "/edit_public_render_parameters.jsp");
	publicRenderParametersURL.setParameter("redirect", redirect);
	publicRenderParametersURL.setParameter("returnToFullPageURL", returnToFullPageURL);
	publicRenderParametersURL.setParameter("portletConfiguration", Boolean.TRUE.toString());
	publicRenderParametersURL.setParameter("portletResource", portletResource);

	navigationItem.setHref(publicRenderParametersURL.toString());

	navigationItem.setLabel(LanguageUtil.get(request, "communication"));

	navigationItems.add(navigationItem);
}

NavigationItem navigationItem = new NavigationItem();

navigationItem.setActive(tabs1.equals("sharing"));

PortletURL sharingURL = renderResponse.createRenderURL();

sharingURL.setParameter("mvcPath", "/edit_sharing.jsp");
sharingURL.setParameter("redirect", redirect);
sharingURL.setParameter("returnToFullPageURL", returnToFullPageURL);
sharingURL.setParameter("portletConfiguration", Boolean.TRUE.toString());
sharingURL.setParameter("portletResource", portletResource);

navigationItem.setHref(sharingURL.toString());

navigationItem.setLabel(LanguageUtil.get(request, "sharing"));

navigationItems.add(navigationItem);

if (selPortlet.isScopeable()) {
	navigationItem = new NavigationItem();

	navigationItem.setActive(tabs1.equals("scope"));

	PortletURL scopeURL = renderResponse.createRenderURL();

	scopeURL.setParameter("mvcPath", "/edit_scope.jsp");
	scopeURL.setParameter("redirect", redirect);
	scopeURL.setParameter("returnToFullPageURL", returnToFullPageURL);
	scopeURL.setParameter("portletConfiguration", Boolean.TRUE.toString());
	scopeURL.setParameter("portletResource", portletResource);

	navigationItem.setHref(scopeURL.toString());

	navigationItem.setLabel(LanguageUtil.get(request, "scope"));

	navigationItems.add(navigationItem);
}
%>

<clay:navigation-bar
	items="<%= navigationItems %>"
/>
=======
%>

<aui:nav-bar cssClass="navbar-collapse-absolute" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<c:if test="<%= selPortlet.getConfigurationActionInstance() != null %>">
			<portlet:renderURL var="configurationURL">
				<portlet:param name="mvcPath" value="/edit_configuration.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
				<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="portletResource" value="<%= portletResource %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= configurationURL %>" label="setup" selected='<%= tabs1.equals("setup") %>' />
		</c:if>

		<c:if test="<%= selPortlet.hasMultipleMimeTypes() %>">
			<portlet:renderURL var="supportedClientsURL">
				<portlet:param name="mvcPath" value="/edit_supported_clients.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
				<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="portletResource" value="<%= portletResource %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= supportedClientsURL %>" label="supported-clients" selected='<%= tabs1.equals("supported-clients") %>' />
		</c:if>

		<c:if test="<%= !selPortlet.getPublicRenderParameters().isEmpty() %>">
			<portlet:renderURL var="publicRenderParametersURL">
				<portlet:param name="mvcPath" value="/edit_public_render_parameters.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
				<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="portletResource" value="<%= portletResource %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= publicRenderParametersURL.toString() %>" label="communication" selected='<%= tabs1.equals("communication") %>' />
		</c:if>

		<portlet:renderURL var="sharingURL">
			<portlet:param name="mvcPath" value="/edit_sharing.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
			<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
			<portlet:param name="portletResource" value="<%= portletResource %>" />
		</portlet:renderURL>

		<aui:nav-item href="<%= sharingURL %>" label="sharing" selected='<%= tabs1.equals("sharing") %>' />

		<c:if test="<%= selPortlet.isScopeable() %>">
			<portlet:renderURL var="scopeURL">
				<portlet:param name="mvcPath" value="/edit_scope.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
				<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="portletResource" value="<%= portletResource %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= scopeURL %>" label="scope" selected='<%= tabs1.equals("scope") %>' />
		</c:if>
	</aui:nav>
</aui:nav-bar>
>>>>>>> compatible
