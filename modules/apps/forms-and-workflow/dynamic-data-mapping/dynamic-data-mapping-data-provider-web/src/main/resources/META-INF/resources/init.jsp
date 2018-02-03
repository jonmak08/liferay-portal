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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.dynamic.data.mapping.data.provider.web.internal.display.context.DDMDataProviderDisplayContext" %><%@
page import="com.liferay.dynamic.data.mapping.data.provider.web.internal.search.DDMDataProviderDisplayTerms" %><%@
page import="com.liferay.dynamic.data.mapping.data.provider.web.internal.search.DDMDataProviderSearch" %><%@
page import="com.liferay.dynamic.data.mapping.data.provider.web.internal.util.DDMDataProviderPortletUtil" %><%@
page import="com.liferay.dynamic.data.mapping.exception.RequiredDataProviderInstanceException" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance" %><%@
<<<<<<< HEAD
page import="com.liferay.petra.string.StringPool" %><%@
=======
>>>>>>> compatible
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.OrderByComparator" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
<<<<<<< HEAD
=======
page import="com.liferay.portal.kernel.util.StringPool" %><%@
>>>>>>> compatible
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
DDMDataProviderDisplayContext ddmDataProviderDisplayContext = (DDMDataProviderDisplayContext)renderRequest.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<%@ include file="/init-ext.jsp" %>