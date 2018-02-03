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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
<<<<<<< HEAD
taglib uri="http://liferay.com/tld/soy" prefix="soy" %><%@
=======
>>>>>>> compatible
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<<<<<<< HEAD
<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.PortletDecorator" %><%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.util.LayoutDescription" %><%@
page import="com.liferay.portlet.configuration.css.web.internal.constants.PortletConfigurationCSSConstants" %><%@
page import="com.liferay.portlet.configuration.css.web.internal.display.context.PortletConfigurationCSSPortletDisplayContext" %>

<%@ page import="java.text.DecimalFormat" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %><%@
page import="java.util.Objects" %>
=======
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.PortletDecorator" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.util.LayoutDescription" %><%@
page import="com.liferay.portal.util.LayoutListUtil" %>

<%@ page import="java.text.DecimalFormat" %><%@
page import="java.text.DecimalFormatSymbols" %>

<%@ page import="java.util.List" %><%@
page import="java.util.Locale" %>

<%@ page import="javax.portlet.PortletURL" %>
>>>>>>> compatible

<liferay-theme:defineObjects />

<portlet:defineObjects />

<<<<<<< HEAD
<%
String currentURL = PortalUtil.getCurrentURL(request);

PortletConfigurationCSSPortletDisplayContext portletConfigurationCSSPortletDisplayContext = new PortletConfigurationCSSPortletDisplayContext(renderRequest);
%>

=======
>>>>>>> compatible
<%@ include file="/init-ext.jsp" %>