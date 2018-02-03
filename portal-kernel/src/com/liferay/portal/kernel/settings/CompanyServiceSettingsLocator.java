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

package com.liferay.portal.kernel.settings;

/**
 * @author Ivan Zaera
 * @author Jorge Ferrer
<<<<<<< HEAD
 * @author Drew Brokke
=======
>>>>>>> compatible
 */
public class CompanyServiceSettingsLocator implements SettingsLocator {

	public CompanyServiceSettingsLocator(long companyId, String settingsId) {
<<<<<<< HEAD
		this(companyId, settingsId, settingsId);
=======
		_companyId = companyId;
		_settingsId = settingsId;

		_configurationPid = settingsId;
>>>>>>> compatible
	}

	public CompanyServiceSettingsLocator(
		long companyId, String settingsId, String configurationPid) {

		_companyId = companyId;
		_settingsId = settingsId;
		_configurationPid = configurationPid;
	}

	@Override
<<<<<<< HEAD
	public Settings getSettings() throws SettingsException {
		SystemSettingsLocator systemSettingsLocator = new SystemSettingsLocator(
			_configurationPid);

		Settings portalPreferencesSettings =
			_settingsLocatorHelper.getPortalPreferencesSettings(
				_companyId, systemSettingsLocator.getSettings());

		Settings companyConfigurationBeanSettings =
			_settingsLocatorHelper.getCompanyConfigurationBeanSettings(
				_companyId, _configurationPid, portalPreferencesSettings);

		return _settingsLocatorHelper.getCompanyPortletPreferencesSettings(
			_companyId, _settingsId, companyConfigurationBeanSettings);
=======
	public Settings getSettings() {
		Settings configurationBeanSettings =
			_settingsLocatorHelper.getConfigurationBeanSettings(
				_configurationPid);

		Settings portalPreferencesSettings =
			_settingsLocatorHelper.getPortalPreferencesSettings(
				_companyId, configurationBeanSettings);

		return _settingsLocatorHelper.getCompanyPortletPreferencesSettings(
			_companyId, _settingsId, portalPreferencesSettings);
>>>>>>> compatible
	}

	@Override
	public String getSettingsId() {
		return _settingsId;
	}

	private final long _companyId;
	private final String _configurationPid;
	private final String _settingsId;
	private final SettingsLocatorHelper _settingsLocatorHelper =
		SettingsLocatorHelperUtil.getSettingsLocatorHelper();

}