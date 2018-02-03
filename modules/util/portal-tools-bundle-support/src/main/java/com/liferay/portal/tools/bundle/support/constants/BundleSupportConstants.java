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

package com.liferay.portal.tools.bundle.support.constants;

import java.io.File;

<<<<<<< HEAD
import java.net.MalformedURLException;
import java.net.URL;

=======
>>>>>>> compatible
/**
 * @author Andrea Di Giorgi
 */
public class BundleSupportConstants {

	public static final String DEFAULT_BUNDLE_CACHE_DIR_NAME =
		".liferay/bundles";

	public static final String DEFAULT_BUNDLE_FORMAT = "zip";

	public static final String DEFAULT_BUNDLE_URL =
<<<<<<< HEAD
		"https://cdn.lfrs.sl/releases.liferay.com/portal/7.0.4-ga5" +
			"/liferay-ce-portal-tomcat-7.0-ga5-20171018150113838.zip";

	public static final URL DEFAULT_BUNDLE_URL_OBJECT;
=======
		"https://cdn.lfrs.sl/releases.liferay.com/portal/7.0.3-ga4" +
			"/liferay-ce-portal-tomcat-7.0-ga4-20170613175008905.zip";
>>>>>>> compatible

	public static final String DEFAULT_CONFIGS_DIR_NAME = "configs";

	public static final String DEFAULT_ENVIRONMENT = "local";

	public static final boolean DEFAULT_INCLUDE_FOLDER = true;

	public static final String DEFAULT_LIFERAY_HOME_DIR_NAME = "bundles";

	public static final int DEFAULT_STRIP_COMPONENTS = 1;

	public static final File DEFAULT_TOKEN_FILE;

	public static final String DEFAULT_TOKEN_FILE_NAME = ".liferay/token";

	public static final String DEFAULT_TOKEN_URL =
		"https://web.liferay.com/token-auth-portlet/api/secure/jsonws" +
			"/tokenauthentry/add-token-auth-entry";

	static {
<<<<<<< HEAD
		try {
			DEFAULT_BUNDLE_URL_OBJECT = new URL(DEFAULT_BUNDLE_URL);
		}
		catch (MalformedURLException murle) {
			throw new ExceptionInInitializerError(murle);
		}

=======
>>>>>>> compatible
		DEFAULT_TOKEN_FILE = new File(
			System.getProperty("user.home"), DEFAULT_TOKEN_FILE_NAME);
	}

}