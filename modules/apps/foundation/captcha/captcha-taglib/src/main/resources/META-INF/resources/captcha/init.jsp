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

<<<<<<< HEAD
<%@ page import="com.liferay.captcha.configuration.CaptchaConfiguration" %><%@
page import="com.liferay.captcha.util.CaptchaUtil" %><%@
page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil" %>

<%
CaptchaConfiguration captchaConfiguration = (CaptchaConfiguration)ConfigurationProviderUtil.getSystemConfiguration(CaptchaConfiguration.class);

=======
<%@ page import="com.liferay.portal.kernel.captcha.CaptchaUtil" %>

<%
>>>>>>> compatible
boolean captchaEnabled = false;

if (portletRequest != null) {
	captchaEnabled = CaptchaUtil.isEnabled(portletRequest);
}
else {
	captchaEnabled = CaptchaUtil.isEnabled(request);
}
%>