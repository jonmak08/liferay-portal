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

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.LayoutTestUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.util.Collections;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

import org.testng.Assert;

/**
 * @author László Csontos
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class FriendlyURLServletTest {

	@Before
	public void setUp() throws Exception {
		ServiceContext serviceContext = ServiceTestUtil.getServiceContext();

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		_group = GroupTestUtil.addGroup();

		_layout = LayoutTestUtil.addLayout(
			_group.getGroupId(), ServiceTestUtil.randomString());
	}

	@After
	public void tearDown() throws Exception {
		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testGetRedirectWithExistentSite() throws Exception {
		testGetRedirect(
			getPath(_group, _layout), Portal.PATH_MAIN,
			new Object[] {getURL(_layout), false});
	}

	@Test
	public void testGetRedirectWithI18nPath() throws Exception {
		Locale[] availableLocales =
			new Locale[] {LocaleUtil.US, LocaleUtil.HUNGARY};

		_group = GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), availableLocales, LocaleUtil.US);

		testGetI18nRedirect("/fr", "/en");
		testGetI18nRedirect("/hu", "/hu");
	}

	@Test
	public void testGetRedirectWithInvalidPath() throws Exception {
		testGetRedirect(
			null, Portal.PATH_MAIN, new Object[] {Portal.PATH_MAIN, false});
		testGetRedirect(
			"test", Portal.PATH_MAIN, new Object[] {Portal.PATH_MAIN, false});
	}

	@Test(expected = NoSuchGroupException.class)
	public void testGetRedirectWithNonExistentSite() throws Exception {
		testGetRedirect("/non-existent-site/home", Portal.PATH_MAIN, null);
	}

	protected String getPath(Group group, Layout layout) {
		return group.getFriendlyURL() + layout.getFriendlyURL();
	}

	protected String getURL(Layout layout) {
		return "/c/portal/layout?p_l_id=" + layout.getPlid() +
			"&p_v_l_s_g_id=0";
	}

	protected void testGetI18nRedirect(String i18nPath, String expectedI18nPath)
		throws Exception {

		_mockHttpServletRequest.setAttribute(WebKeys.I18N_PATH, i18nPath);
		_mockHttpServletRequest.setPathInfo(StringPool.SLASH);

		String requestURI =
			PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING +
				getPath(_group, _layout);

		_mockHttpServletRequest.setRequestURI(requestURI);

		String actualURL = getURL(_layout);

		boolean redirect = false;

		if (!Validator.equals(i18nPath, expectedI18nPath)) {
			actualURL = expectedI18nPath + requestURI;

			redirect = true;
		}

		testGetRedirect(
			_group.getFriendlyURL(), Portal.PATH_MAIN,
			new Object[] {actualURL, redirect});
		testGetRedirect(
			getPath(_group, _layout), Portal.PATH_MAIN,
			new Object[] {actualURL, redirect});
	}

	protected void testGetRedirect(
			String path, String mainPath, Object[] expectedRedirectArray)
		throws Exception {

		Object[] actualRedirectArray = _friendlyURLServlet.getRedirect(
			_mockHttpServletRequest, path, mainPath,
			Collections.<String, String[]>emptyMap());

		Assert.assertEquals(actualRedirectArray, expectedRedirectArray);
	}

	private FriendlyURLServlet _friendlyURLServlet = new FriendlyURLServlet();

	private Group _group;

	private Layout _layout;

	private MockHttpServletRequest _mockHttpServletRequest =
		new MockHttpServletRequest();

}