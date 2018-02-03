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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringBundler;
=======
>>>>>>> compatible

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class ServletContextPool {

	public static void clear() {
		_servletContexts.clear();
	}

	public static boolean containsKey(String servletContextName) {
		if (servletContextName == null) {
			return false;
		}

		boolean value = _servletContexts.containsKey(servletContextName);

		if (_log.isDebugEnabled()) {
<<<<<<< HEAD
			_log.debug(
				StringBundler.concat(
					"Contains key ", servletContextName, " ",
					String.valueOf(value)));
=======
			_log.debug("Contains key " + servletContextName + " " + value);
>>>>>>> compatible
		}

		return value;
	}

	public static ServletContext get(String servletContextName) {
		ServletContext servletContext = _servletContexts.get(
			servletContextName);

		if (_log.isDebugEnabled()) {
<<<<<<< HEAD
			_log.debug(
				StringBundler.concat(
					"Get ", servletContextName, " ",
					String.valueOf(servletContext)));
=======
			_log.debug("Get " + servletContextName + " " + servletContext);
>>>>>>> compatible
		}

		return servletContext;
	}

	public static Set<String> keySet() {
		return _servletContexts.keySet();
	}

	public static void put(
		String servletContextName, ServletContext servletContext) {

		if (_log.isDebugEnabled()) {
<<<<<<< HEAD
			_log.debug(
				StringBundler.concat(
					"Put ", servletContextName, " ",
					String.valueOf(servletContext)));
=======
			_log.debug("Put " + servletContextName + " " + servletContext);
>>>>>>> compatible
		}

		_servletContexts.put(servletContextName, servletContext);
	}

	public static ServletContext remove(String servletContextName) {

		// We should never remove the portal context. See LPS-12683.

		String contextPath = PortalUtil.getPathContext();

		if (contextPath.equals(servletContextName)) {
			return null;
		}

		ServletContext servletContext = _servletContexts.remove(
			servletContextName);

		if (_log.isDebugEnabled()) {
<<<<<<< HEAD
			_log.debug(
				StringBundler.concat(
					"Remove ", servletContextName, " ",
					String.valueOf(servletContext)));
=======
			_log.debug("Remove " + servletContextName + " " + servletContext);
>>>>>>> compatible
		}

		return servletContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServletContextPool.class);

	private static final Map<String, ServletContext> _servletContexts =
		new ConcurrentHashMap<>();

}