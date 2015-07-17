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

package com.liferay.portal.patcher;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatchInconsistencyException;
import com.liferay.portal.kernel.patcher.Patcher;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 * @author Zoltán Takács
 */
@DoPrivileged
public class PatcherImpl implements Patcher {

	@Override
	public boolean applyPatch(File patchFile) {
		File patchDirectory = getPatchDirectory();

		if (patchDirectory == null) {
			return false;
		}

		try {
			FileUtil.copyFile(
				patchFile,
				new File(
					patchDirectory + StringPool.SLASH + patchFile.getName()));

			return true;
		}
		catch (Exception e) {
			_log.error(
				"Unable to copy " + patchFile.getAbsolutePath() + " to " +
					patchDirectory.getAbsolutePath());

			return false;
		}
	}

	@Override
	public String[] getFixedIssues() {
		if (_fixedIssueKeys != null) {
			return _fixedIssueKeys;
		}

		Properties properties = getProperties();

		_fixedIssueKeys = StringUtil.split(
			properties.getProperty(PROPERTY_FIXED_ISSUES));

		return _fixedIssueKeys;
	}

	@Override
	public String[] getInstalledPatches() {
		if (_installedPatchNames != null) {
			return _installedPatchNames;
		}

		return _getInstalledPatches(null);
	}

	@Override
	public File getPatchDirectory() {
		if (_patchDirectory != null) {
			return _patchDirectory;
		}

		Properties properties = getProperties();

		String patchDirectoryName = properties.getProperty(
			PROPERTY_PATCH_DIRECTORY);

		if (Validator.isNotNull(patchDirectoryName)) {
			_patchDirectory = new File(patchDirectoryName);

			if (!_patchDirectory.exists()) {
				_log.error("The patch directory does not exist");
			}
		}
		else {
			_log.error("The patch directory is not specified");
		}

		return _patchDirectory;
	}

	@Override
	public String[] getPatchLevels() {
		if (_patchLevels != null) {
			return _patchLevels;
		}

		Properties properties = getProperties();

		_patchLevels = StringUtil.split(
			properties.getProperty(PROPERTY_PATCH_LEVELS));

		return _patchLevels;
	}

	@Override
	public Properties getProperties() {
		if (_properties != null) {
			return _properties;
		}

		return _getProperties(PATCHER_PROPERTIES);
	}

	@Override
	public boolean isConfigured() {
		return _configured;
	}

	@Override
	public void verifyPatchLevels() throws PatchInconsistencyException {
		Properties implJarProperty = _getProperties(PATCHER_PROPERTIES);
		Properties serviceJarProperty = _getProperties(
			PATCHER_SERVICE_PROPERTIES);

		String[] implJarPatches = _getInstalledPatches(implJarProperty);
		String[] serviceJarPatches = _getInstalledPatches(serviceJarProperty);

		Arrays.sort(implJarPatches);
		Arrays.sort(serviceJarPatches);

		if (!Arrays.equals(implJarPatches, serviceJarPatches)) {
			_log.error("Different patch level detected");

			if (_log.isWarnEnabled()) {
				if (ArrayUtil.isEmpty(implJarPatches)) {
					_log.warn(
						"There are no patches installed on portal-impl.jar");
				}
				else {
					_log.warn(
						"Patch level on portal-impl.jar: " +
							Arrays.toString(implJarPatches));
				}

				if (ArrayUtil.isEmpty(serviceJarPatches)) {
					_log.warn(
						"There are no patches installed on portal-service.jar");
				}
				else {
					_log.warn(
						"Patch level on portal-service.jar: " +
							Arrays.toString(serviceJarPatches));
				}
			}

			throw new PatchInconsistencyException();
		}
	}

	private String[] _getInstalledPatches(Properties properties) {
		if (properties == null) {
			properties = getProperties();
		}

		_installedPatchNames = StringUtil.split(
			properties.getProperty(PROPERTY_INSTALLED_PATCHES));

		return _installedPatchNames;
	}

	private Properties _getProperties(String propertyFileName) {
		if (Validator.isNull(propertyFileName)) {
			propertyFileName = PATCHER_PROPERTIES;
		}

		Properties properties = new Properties();

		Class<?> clazz = getClass();

		if (Validator.equals(propertyFileName, PATCHER_SERVICE_PROPERTIES)) {
			clazz = getClass().getInterfaces()[0];
		}

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			propertyFileName);

		if (inputStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to load " + propertyFileName);
			}
		}
		else {
			try {
				properties.load(inputStream);

				_configured = true;
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		_properties = properties;

		return _properties;
	}

	private static Log _log = LogFactoryUtil.getLog(PatcherImpl.class);

	private static boolean _configured;
	private static String[] _fixedIssueKeys;
	private static String[] _installedPatchNames;
	private static File _patchDirectory;
	private static String[] _patchLevels;
	private static Properties _properties;

}