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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
String[] configurationSections = PropsValues.COMPANY_SETTINGS_FORM_CONFIGURATION;
String[] identificationSections = PropsValues.COMPANY_SETTINGS_FORM_IDENTIFICATION;
String[] miscellaneousSections = PropsValues.COMPANY_SETTINGS_FORM_MISCELLANEOUS;

String[][] categorySections = {configurationSections, identificationSections, miscellaneousSections};

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/portal_settings/view");

request.setAttribute("addresses.className", Account.class.getName());
request.setAttribute("emailAddresses.className", Account.class.getName());
request.setAttribute("phones.className", Account.class.getName());
request.setAttribute("websites.className", Account.class.getName());

request.setAttribute("addresses.classPK", company.getAccountId());
request.setAttribute("emailAddresses.classPK", company.getAccountId());
request.setAttribute("phones.classPK", company.getAccountId());
request.setAttribute("websites.classPK", company.getAccountId());
%>

<portlet:actionURL var="editCompanyURL">
	<portlet:param name="struts_action" value="/portal_settings/edit_company" />
</portlet:actionURL>

<aui:form action="<%= editCompanyURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCompany();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" />

	<liferay-util:buffer var="htmlTop">
		<div class="company-info">
			<p class="float-container">
				<img alt="<liferay-ui:message key="logo" />" class="company-logo" src="<%= themeDisplay.getPathImage() %>/company_logo?img_id=<%= company.getLogoId() %>&t=<%= WebServerServletTokenUtil.getToken(company.getLogoId()) %>" /><br />

				<span class="company-name"><%= HtmlUtil.escape(company.getName()) %></span>
			</p>
		</div>
	</liferay-util:buffer>

	<liferay-ui:form-navigator
		categoryNames="<%= _CATEGORY_NAMES %>"
		categorySections="<%= categorySections %>"
		htmlTop="<%= htmlTop %>"
		jspPath="/html/portlet/portal_settings/"
		showButtons="<%= RoleLocalServiceUtil.hasUserRole(user.getUserId(), company.getCompanyId(), RoleConstants.ADMINISTRATOR, true) %>"
	/>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCompany() {
		document.<portlet:namespace />fm.method = 'post';
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.UPDATE %>';

		<portlet:namespace />saveEmails();
		<portlet:namespace />saveLdap();
		<portlet:namespace />saveLocales();

		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/portal_settings/edit_company" /></portlet:actionURL>");
	}

	function <portlet:namespace />saveEmails() {
		try {
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.ADMIN_EMAIL_USER_ADDED_BODY %>--'].value = window['<portlet:namespace />emailUserAddedBody'].getHTML();
		}
		catch (e) {
		}

		try {
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.ADMIN_EMAIL_USER_ADDED_NO_PASSWORD_BODY %>--'].value = window['<portlet:namespace />emailUserAddedNoPasswordBody'].getHTML();
		}
		catch (e) {
		}

		try {
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_BODY %>--'].value = window['<portlet:namespace />emailPasswordSentBody'].getHTML();
		}
		catch (e) {
		}

		try {
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_BODY %>--'].value = window['<portlet:namespace />emailPasswordResetBody'].getHTML();
		}
		catch (e) {
		}

		try {
			document.<portlet:namespace />fm['<portlet:namespace />settings--<%= PropsKeys.ADMIN_EMAIL_VERIFICATION_BODY %>--'].value = window['<portlet:namespace />emailVerificationBody'].getHTML();
		}
		catch (e) {
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />saveLdap',
		function() {
			var A = AUI();

			var ldapServerIds = [];

			A.all('.ldap-servers .table-data tr').each(
				function(item, index, collection) {
					ldapServerIds.push(item.getAttribute('data-ldapServerId'));
				}
			);

			var ldapServerId = document.<portlet:namespace />fm['<portlet:namespace />settings--ldap.server.ids--'];

			if (ldapServerId) {
				ldapServerId.value = ldapServerIds.join(',');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />saveLocales',
		function() {
			var locales = document.<portlet:namespace />fm.<portlet:namespace /><%= PropsKeys.LOCALES %>;

			if (locales) {
				locales.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentLanguageIds);
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>

<%!
private static final String[] _CATEGORY_NAMES = {"configuration", "identification", "miscellaneous"};
%>