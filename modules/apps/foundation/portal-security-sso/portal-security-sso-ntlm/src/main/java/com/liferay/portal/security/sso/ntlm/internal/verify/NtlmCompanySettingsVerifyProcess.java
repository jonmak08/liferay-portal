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

package com.liferay.portal.security.sso.ntlm.internal.verify;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.SetUtil;
=======
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.security.sso.ntlm.constants.LegacyNtlmPropsKeys;
import com.liferay.portal.security.sso.ntlm.constants.NtlmConfigurationKeys;
import com.liferay.portal.security.sso.ntlm.constants.NtlmConstants;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

<<<<<<< HEAD
=======
import java.util.Dictionary;
>>>>>>> compatible
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 */
@Component(
	immediate = true,
	property = {"verify.process.name=com.liferay.portal.security.sso.ntlm"},
	service = VerifyProcess.class
)
public class NtlmCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(LegacyNtlmPropsKeys.NTLM_AUTH_KEYS);
	}

	@Override
<<<<<<< HEAD
	protected String[][] getRenamePropertyKeysArray() {
		return new String[][] {
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN,
				NtlmConfigurationKeys.AUTH_DOMAIN
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER,
				NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER_NAME,
				NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER_NAME
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_ENABLED,
				NtlmConfigurationKeys.AUTH_ENABLED
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_NEGOTIATE_FLAGS,
				NtlmConfigurationKeys.AUTH_NEGOTIATE_FLAGS
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_ACCOUNT,
				NtlmConfigurationKeys.AUTH_SERVICE_ACCOUNT
			},
			new String[] {
				LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_PASSWORD,
				NtlmConfigurationKeys.AUTH_SERVICE_PASSWORD
			}
		};
=======
	protected Dictionary<String, String> getPropertyValues(long companyId) {
		Dictionary<String, String> dictionary = new HashMapDictionary<>();

		dictionary.put(
			NtlmConfigurationKeys.AUTH_DOMAIN,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN, "EXAMPLE"));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER,
				"127.0.0.1"));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER_NAME,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER_NAME,
				"EXAMPLE"));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_ENABLED,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_ENABLED,
				StringPool.FALSE));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_NEGOTIATE_FLAGS,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_NEGOTIATE_FLAGS,
				"0x600FFFFF"));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_SERVICE_ACCOUNT,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_ACCOUNT,
				"LIFERAY$@EXAMPLE.COM"));
		dictionary.put(
			NtlmConfigurationKeys.AUTH_SERVICE_PASSWORD,
			_prefsProps.getString(
				companyId, LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_PASSWORD,
				"test"));

		return dictionary;
>>>>>>> compatible
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return NtlmConstants.SERVICE_NAME;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 3.0.0, with no direct replacement
	 */
	@Deprecated
=======
>>>>>>> compatible
	@Reference(unbind = "-")
	protected void setPrefsProps(PrefsProps prefsProps) {
		_prefsProps = prefsProps;
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	private CompanyLocalService _companyLocalService;
	private PrefsProps _prefsProps;
	private SettingsFactory _settingsFactory;

}