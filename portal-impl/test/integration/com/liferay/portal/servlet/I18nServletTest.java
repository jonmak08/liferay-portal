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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.CompanyTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Juan Gonzalez
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class I18nServletTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_availableLocales = LanguageUtil.getAvailableLocales();

		CompanyTestUtil.resetCompanyLocales(
			PortalUtil.getDefaultCompanyId(),
			new Locale[] {
				LocaleUtil.CANADA_FRENCH, LocaleUtil.SPAIN, LocaleUtil.US
			});
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		CompanyTestUtil.resetCompanyLocales(
			PortalUtil.getDefaultCompanyId(), _availableLocales);
	}

	@Before
	public void setUp() {
		_originalLocaleUseDefaultIfNotAvailable =
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE;
	}

	@After
	public void tearDown() {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
			_originalLocaleUseDefaultIfNotAvailable;
	}

	@Test
	public void testI18nNotUseDefaultExistentLocale() throws Exception {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

		Locale expectedLocale = LocaleUtil.getDefault();

		testGetI18nData(expectedLocale, getI18nData(expectedLocale));
	}

	@Test
	public void testI18nNotUseDefaultNondefaultLocale() throws Exception {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

		Locale expectedLocale = LocaleUtil.SPAIN;

		testGetI18nData(expectedLocale, getI18nData(expectedLocale));
	}

	@Test
	public void testI18nNotUseDefaultNonexistentLocale() throws Exception {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

		Locale expectedLocale = LocaleUtil.CHINA;

		testGetI18nData(expectedLocale, null);
	}

	@Test
	public void testI18nUseDefault() throws Exception {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = true;

		Locale expectedLocale = LocaleUtil.getDefault();

		testGetI18nData(expectedLocale, getI18nData(expectedLocale));
	}

	@Test
	public void testI18nUseDefaultNonexistentLocale() throws Exception {
		PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = true;

		Locale expectedLocale = LocaleUtil.CHINA;

		testGetI18nData(expectedLocale, getI18nData(expectedLocale));
	}

	protected String[] getI18nData(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String i18nPath = StringPool.SLASH + languageId;
		String path = StringPool.SLASH;
		String languageCode = locale.getLanguage();

		return new String[] {languageId, i18nPath, path, languageCode};
	}

	protected void testGetI18nData(Locale locale, String[] expectedI18nData)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setPathInfo(StringPool.SLASH);
		mockHttpServletRequest.setServletPath(
			StringPool.SLASH + LocaleUtil.toLanguageId(locale));

		String[] actualI18nData = _i18nServlet.getI18nData(
			mockHttpServletRequest);

		Assert.assertEquals(expectedI18nData, actualI18nData);
	}

	private static Locale[] _availableLocales;

	private final I18nServlet _i18nServlet = new I18nServlet();
	private boolean _originalLocaleUseDefaultIfNotAvailable;

}