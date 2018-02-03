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

package com.liferay.portal.kernel.captcha;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.ServiceProxyFactory;
=======
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
>>>>>>> compatible
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.io.IOException;

<<<<<<< HEAD
=======
import javax.portlet.PortletPreferences;
>>>>>>> compatible
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class CaptchaUtil {

	public static void check(HttpServletRequest request)
		throws CaptchaException {

		getCaptcha().check(request);
	}

	public static void check(PortletRequest portletRequest)
		throws CaptchaException {

		getCaptcha().check(portletRequest);
	}

	public static Captcha getCaptcha() {
		PortalRuntimePermission.checkGetBeanProperty(CaptchaUtil.class);

		if (_serviceTrackerMap == null) {
			return null;
		}

<<<<<<< HEAD
		String captchaClassName = _captchaSettings.getCaptchaEngine();
=======
		String captchaClassName = PrefsPropsUtil.getString(
			PropsKeys.CAPTCHA_ENGINE_IMPL, _CAPTCHA_ENGINE_IMPL);
>>>>>>> compatible

		return _serviceTrackerMap.getService(captchaClassName);
	}

	public static String getTaglibPath() {
		return getCaptcha().getTaglibPath();
	}

	public static boolean isEnabled(HttpServletRequest request) {
		return getCaptcha().isEnabled(request);
	}

	public static boolean isEnabled(PortletRequest portletRequest) {
		return getCaptcha().isEnabled(portletRequest);
	}

	public static void serveImage(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		getCaptcha().serveImage(request, response);
	}

	public static void serveImage(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		getCaptcha().serveImage(resourceRequest, resourceResponse);
	}

	public void setCaptcha(Captcha captcha) throws Exception {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

<<<<<<< HEAD
		Class<?> clazz = captcha.getClass();

		_captchaSettings.setCaptchaEngine(clazz.getName());
	}

	private static volatile CaptchaSettings _captchaSettings =
		ServiceProxyFactory.newServiceTrackedInstance(
			CaptchaSettings.class, CaptchaUtil.class, "_captchaSettings",
			false);
=======
		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences();

		Class<?> clazz = captcha.getClass();

		portletPreferences.setValue(
			PropsKeys.CAPTCHA_ENGINE_IMPL, clazz.getName());

		portletPreferences.store();
	}

	private static final String _CAPTCHA_ENGINE_IMPL = PropsUtil.get(
		PropsKeys.CAPTCHA_ENGINE_IMPL);

>>>>>>> compatible
	private static final ServiceTrackerMap<String, Captcha> _serviceTrackerMap =
		ServiceTrackerCollections.openSingleValueMap(
			Captcha.class, "captcha.engine.impl");

}