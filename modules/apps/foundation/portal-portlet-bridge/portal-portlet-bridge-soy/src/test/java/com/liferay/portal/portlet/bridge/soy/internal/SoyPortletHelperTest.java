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

package com.liferay.portal.portlet.bridge.soy.internal;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCCommandCache;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.util.HtmlImpl;

import java.net.URL;
=======
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.HtmlImpl;

import java.io.Writer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
>>>>>>> compatible

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
<<<<<<< HEAD
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
=======

import org.osgi.framework.Bundle;
>>>>>>> compatible

/**
 * @author Marcellus Tavares
 */
<<<<<<< HEAD
@PrepareForTest(FrameworkUtil.class)
@RunWith(PowerMockRunner.class)
public class SoyPortletHelperTest {

	@Before
	public void setUpFrameworkUtil() {
		setUpJSONFactoryUtil();

		PowerMockito.spy(FrameworkUtil.class);
	}

	@Before
	public void setUpHtmlUtil() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());
	}

	@Before
	public void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Test(expected = Exception.class)
	public void testGetJavaScriptControllerModuleWithoutPackageJSON()
		throws Exception {

		Bundle bundle = _mockBundleWithoutPackage();
		MVCCommandCache mvcCommandCache = _mockEmptyMVCCommandCache();
		FriendlyURLMapper friendlyURLMapper = Mockito.mock(
			FriendlyURLMapper.class);

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(
			bundle, mvcCommandCache, friendlyURLMapper);

		soyPortletHelper.getJavaScriptLoaderModule("Path");
	}

	@Test
	public void testGetJavaScriptLoaderModule() throws Exception {
		Bundle bundle = _mockBundleWithPackageFile();
		MVCCommandCache mvcCommandCache = _mockEmptyMVCCommandCache();
		FriendlyURLMapper friendlyURLMapper = Mockito.mock(
			FriendlyURLMapper.class);

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(
			bundle, mvcCommandCache, friendlyURLMapper);

		String javaScriptLoaderModule =
			soyPortletHelper.getJavaScriptLoaderModule("JavaScriptCommand");

		Assert.assertEquals(
			"package-with-version@1.0.0/JavaScriptCommand",
			javaScriptLoaderModule);
	}

	@Test
	public void testGetJavaScriptLoaderModuleForES6() throws Exception {
		Bundle bundle = _mockBundleWithPackageFile();
		MVCCommandCache mvcCommandCache = _mockEmptyMVCCommandCache();
		FriendlyURLMapper friendlyURLMapper = Mockito.mock(
			FriendlyURLMapper.class);

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(
			bundle, mvcCommandCache, friendlyURLMapper);

		String javaScriptLoaderModule =
			soyPortletHelper.getJavaScriptLoaderModule("ES6Command");

		Assert.assertEquals(
			"package-with-version@1.0.0/ES6Command.es", javaScriptLoaderModule);
	}

	@Test
	public void testGetJavaScriptLoaderModuleForSoy() throws Exception {
		Bundle bundle = _mockBundleWithPackageFile();
		MVCCommandCache mvcCommandCache = _mockEmptyMVCCommandCache();
		FriendlyURLMapper friendlyURLMapper = Mockito.mock(
			FriendlyURLMapper.class);

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(
			bundle, mvcCommandCache, friendlyURLMapper);

		String javaScriptLoaderModule =
			soyPortletHelper.getJavaScriptLoaderModule("SoyCommand");

		Assert.assertEquals(
			"package-with-version@1.0.0/SoyCommand.soy",
			javaScriptLoaderModule);
	}

	private Bundle _mockBundleWithoutPackage() {
		Bundle bundle = Mockito.mock(Bundle.class);

		Mockito.when(
			bundle.getEntry("package.json")
		).then(
			new Answer<URL>() {

				public URL answer(InvocationOnMock invocationOnMock) {
					return null;
				}

			}
		);
=======
public class SoyPortletHelperTest {

	@Before
	public void setUp() {
		setUpHtmlUtil();
		setUpJSONFactoryUtil();
	}

	@Test
	public void testgetPortletJavaScriptWithBundleWithoutPackageFile()
		throws Exception {

		String portletJavaScript = getPortletJavaScript(_mockBundle, "View");

		Assert.assertEquals(StringPool.BLANK, portletJavaScript);
	}

	@Test
	public void testgetPortletJavaScriptWithBundleWithPackageFile()
		throws Exception {

		Bundle bundle = getBundleWithPackageFile();

		String portletJavaScript = getPortletJavaScript(bundle, "View");

		Assert.assertNotEquals(StringPool.BLANK, portletJavaScript);
	}

	@Test
	public void testgetRequiredModulesWithBundleWithPackageFile()
		throws Exception {

		Bundle bundle = getBundleWithPackageFile();

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(bundle);

		Set<String> expectedRequiredModules = new HashSet<>();

		expectedRequiredModules.add("SampleModuleName/View.soy");

		Set<String> actualRequiredModules = soyPortletHelper.getRequiredModules(
			"View", Collections.<String>emptySet());

		Assert.assertEquals(expectedRequiredModules, actualRequiredModules);
	}

	@Test
	public void testTemplateNamespace() throws Exception {
		String path = "View";

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(_mockBundle);

		Assert.assertEquals(
			path.concat(".render"),
			soyPortletHelper.getTemplateNamespace(path));
	}

	protected Bundle getBundleWithPackageFile() {
		Bundle bundle = (Bundle)ProxyUtil.newProxyInstance(
			Bundle.class.getClassLoader(), new Class<?>[] {Bundle.class},
			new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
					throws NoSuchMethodException {

					if (method.equals(
							Bundle.class.getMethod("getEntry", String.class)) &&
						"package.json".equals(args[0])) {

						return SoyPortletHelperTest.class.getResource(
							"dependencies/package.json");
					}

					return null;
				}

			});
>>>>>>> compatible

		return bundle;
	}

<<<<<<< HEAD
	private Bundle _mockBundleWithPackageFile() {
		Bundle bundle = Mockito.mock(Bundle.class);

		Mockito.when(
			bundle.getEntry(Matchers.endsWith("ES6Command.es.js"))
		).thenReturn(
			SoyPortletHelperTest.class.getResource(
				"dependencies/ES6Command.es.js")
		);

		Mockito.when(
			bundle.getEntry(Matchers.endsWith("JavaScriptCommand.js"))
		).thenReturn(
			SoyPortletHelperTest.class.getResource(
				"dependencies/JavaScriptCommand.js")
		);

		Mockito.when(
			bundle.getEntry(Matchers.endsWith("package.json"))
		).thenReturn(
			SoyPortletHelperTest.class.getResource(
				"dependencies/package-with-version.json")
		);

		Mockito.when(
			bundle.getEntry(Matchers.endsWith("SoyCommand.soy"))
		).thenReturn(
			SoyPortletHelperTest.class.getResource(
				"dependencies/SoyCommand.soy")
		);

		return bundle;
	}

	private MVCCommandCache _mockEmptyMVCCommandCache() {
		MVCCommandCache mvcCommandCache = Mockito.mock(MVCCommandCache.class);

		Mockito.when(
			mvcCommandCache.getMVCCommand(Matchers.anyString())
		).thenReturn(
			MVCRenderCommand.EMPTY
		);

		return mvcCommandCache;
=======
	protected String getPortletJavaScript(Bundle bundle, String path)
		throws Exception {

		SoyPortletHelper soyPortletHelper = new SoyPortletHelper(bundle);

		Template template = new MockTemplate();

		String portletJavaScript = soyPortletHelper.getPortletJavaScript(
			template, path, StringUtil.randomString(),
			Collections.<String>emptySet());

		return portletJavaScript;
	}

	protected void setUpHtmlUtil() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());
	}

	protected void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	private final Bundle _mockBundle = (Bundle)ProxyUtil.newProxyInstance(
		Bundle.class.getClassLoader(), new Class<?>[] {Bundle.class},
		new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				return null;
			}

		});

	private static class MockTemplate
		extends HashMap<String, Object> implements Template {

		@Override
		public void doProcessTemplate(Writer writer) {
		}

		@Override
		public Object get(String key) {
			return super.get(key);
		}

		@Override
		public String[] getKeys() {
			Set<String> keys = keySet();

			return keys.toArray(new String[keys.size()]);
		}

		@Override
		public void prepare(HttpServletRequest request) {
		}

		@Override
		public void processTemplate(Writer writer) {
		}

>>>>>>> compatible
	}

}