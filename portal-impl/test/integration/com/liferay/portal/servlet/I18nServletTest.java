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

import org.junit.AfterClass;
import org.junit.Assert;
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
			new Locale[] {LocaleUtil.SPAIN, LocaleUtil.US});
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		CompanyTestUtil.resetCompanyLocales(
			PortalUtil.getDefaultCompanyId(), _availableLocales);
	}

	@Test
	public void testI18nNotUseDefaultExistentLocale() throws Exception {
		boolean originalLocaleUseDefault =
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE;

		try {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

			Locale expectedLocale = _defaultLocale;

			String[] expectedArray = getI18nDataExpectedArray(expectedLocale);

			testGetI18nData(expectedLocale, expectedArray);
		}
		finally {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
				originalLocaleUseDefault;
		}
	}

	@Test
	public void testI18nNotUseDefaultNonDefaultLocale() throws Exception {
		boolean originalLocaleUseDefault =
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE;

		try {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

			Locale expectedLocale = _nonDefaultLocale;

			String[] expectedArray = getI18nDataExpectedArray(expectedLocale);

			testGetI18nData(expectedLocale, expectedArray);
		}
		finally {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
				originalLocaleUseDefault;
		}
	}

	@Test
	public void testI18nNotUseDefaultNonExistentLocale() throws Exception {
		boolean originalLocaleUseDefault =
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE;

		try {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = false;

			Locale expectedLocale = _nonExistentLocale;

			testGetI18nData(expectedLocale, null);
		}
		finally {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
				originalLocaleUseDefault;
		}
	}

	@Test
	public void testI18nUseDefault() throws Exception {
		boolean originalLocaleUseDefault =
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE;

		try {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE = true;

			Locale expectedLocale = _defaultLocale;

			String[] expectedArray = getI18nDataExpectedArray(expectedLocale);

			testGetI18nData(expectedLocale, expectedArray);
		}
		finally {
			PropsValues.LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
				originalLocaleUseDefault;
		}
	}

	protected String[] getI18nDataExpectedArray(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String i18nPath = StringPool.SLASH + languageId;
		String path = StringPool.SLASH;

		return new String[] {languageId, i18nPath, path};
	}

	protected void testGetI18nData(Locale locale, String[] expectedDataArray)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		StringBuilder sb = new StringBuilder(2);

		sb.append(StringPool.SLASH);
		sb.append(LocaleUtil.toLanguageId(locale));

		mockHttpServletRequest.setServletPath(sb.toString());

		mockHttpServletRequest.setPathInfo(StringPool.SLASH);

		String[] actualI18NDataArray = _i18nServlet.getI18nData(
			mockHttpServletRequest);

		Assert.assertArrayEquals(expectedDataArray, actualI18NDataArray);
	}

	private final Locale _defaultLocale = LocaleUtil.US;
	private final I18nServlet _i18nServlet = new I18nServlet();
	private final Locale _nonDefaultLocale = LocaleUtil.SPAIN;
	private final Locale _nonExistentLocale = LocaleUtil.CHINA;
	private static Locale[] _availableLocales;

}