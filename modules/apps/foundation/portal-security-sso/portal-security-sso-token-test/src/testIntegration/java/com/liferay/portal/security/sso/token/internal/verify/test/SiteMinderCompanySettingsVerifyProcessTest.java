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

package com.liferay.portal.security.sso.token.internal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
=======
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.token.constants.LegacyTokenPropsKeys;
import com.liferay.portal.security.sso.token.constants.TokenConfigurationKeys;
import com.liferay.portal.security.sso.token.constants.TokenConstants;
>>>>>>> compatible
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.test.BaseCompanySettingsVerifyProcessTestCase;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Brian Greenwald
 */
@RunWith(Arquillian.class)
public class SiteMinderCompanySettingsVerifyProcessTest
	extends BaseCompanySettingsVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void doVerify(
		PortletPreferences portletPreferences, Settings settings) {

<<<<<<< HEAD
		String[] shibbolethKeys = {
			"shibboleth.auth.enabled", "shibboleth.import.from.ldap",
			"shibboleth.logout.url", "shibboleth.user.header"
		};

		for (String key : shibbolethKeys) {
=======
		for (String key : LegacyTokenPropsKeys.SHIBBOLETH_KEYS) {
>>>>>>> compatible
			Assert.assertTrue(
				Validator.isNull(
					portletPreferences.getValue(key, StringPool.BLANK)));
		}

		Assert.assertTrue(
			GetterUtil.getBoolean(
<<<<<<< HEAD
				settings.getValue("enabled", StringPool.FALSE)));
		Assert.assertFalse(
			GetterUtil.getBoolean(
				settings.getValue("importFromLDAP", StringPool.TRUE)));
		Assert.assertEquals(
			"testSiteminder",
			settings.getValue("userTokenName", StringPool.BLANK));
=======
				settings.getValue(
					TokenConfigurationKeys.AUTH_ENABLED, StringPool.FALSE)));
		Assert.assertFalse(
			GetterUtil.getBoolean(
				settings.getValue(
					TokenConfigurationKeys.IMPORT_FROM_LDAP, StringPool.TRUE)));
		Assert.assertEquals(
			"testSiteminder",
			settings.getValue(
				TokenConfigurationKeys.USER_HEADER, StringPool.BLANK));
>>>>>>> compatible
	}

	@Override
	protected String getSettingsId() {
<<<<<<< HEAD
		return "com.liferay.portal.security.sso.token";
=======
		return TokenConstants.SERVICE_NAME;
>>>>>>> compatible
	}

	@Override
	protected String getVerifyProcessName() {
		return "com.liferay.portal.security.sso.token.siteminder";
	}

	@Override
	protected void populateLegacyProperties(UnicodeProperties properties) {
<<<<<<< HEAD
		properties.put("siteminder.auth.enabled", "true");
		properties.put("siteminder.import.from.ldap", "false");
		properties.put("siteminder.user.header", "testSiteminder");
=======
		properties.put(LegacyTokenPropsKeys.SITEMINDER_AUTH_ENABLED, "true");
		properties.put(
			LegacyTokenPropsKeys.SITEMINDER_IMPORT_FROM_LDAP, "false");
		properties.put(
			LegacyTokenPropsKeys.SITEMINDER_USER_HEADER, "testSiteminder");
>>>>>>> compatible
	}

}