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

package com.liferay.portal.security.pacl;

import com.liferay.portal.deploy.hot.HookHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.DependencyManagementThreadLocal;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.kernel.test.TestContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalLifecycleUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.plugin.PluginPackageUtil;
import com.liferay.portal.spring.context.PortletContextLoaderListener;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.mock.AutoDeployMockServletContext;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Assert;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Raymond Augé
 */
public class PACLExecutionTestListener
	extends MainServletExecutionTestListener {

	@Override
	public void runAfterClass(TestContext testContext) {
		HotDeployEvent hotDeployEvent = _hotDeployEvents.remove(
			testContext.getClazz());

		if (hotDeployEvent == null) {
			return;
		}

		HotDeployUtil.fireUndeployEvent(hotDeployEvent);

		PortletContextLoaderListener portletContextLoaderListener =
			new PortletContextLoaderListener();

		try {
			PortletClassLoaderUtil.setClassLoader(
				hotDeployEvent.getContextClassLoader());
			PortletClassLoaderUtil.setServletContextName(
				hotDeployEvent.getServletContextName());

			portletContextLoaderListener.contextDestroyed(
				new ServletContextEvent(hotDeployEvent.getServletContext()));
		}
		finally {
			PortletClassLoaderUtil.setClassLoader(null);
			PortletClassLoaderUtil.setServletContextName(null);
		}
	}

	@Override
	public void runBeforeClass(TestContext testContext) {
		ServletContext servletContext = ServletContextPool.get(
			PortalUtil.getServletContextName());

		if (servletContext == null) {
			servletContext = new AutoDeployMockServletContext();

			servletContext.setAttribute(
				InvokerFilterHelper.class.getName(), new InvokerFilterHelper());

			ServletContextPool.put(PortalUtil.getPathContext(), servletContext);
		}

		HotDeployUtil.reset();

		HotDeployUtil.registerListener(new HookHotDeployListener());

		HotDeployUtil.setCapturePrematureEvents(false);

		PortalLifecycleUtil.flushInits();

		Class<?> clazz = testContext.getClazz();

		ClassLoader classLoader = clazz.getClassLoader();

		MockServletContext mockServletContext = new MockServletContext(
			new PACLResourceLoader(classLoader));

		mockServletContext.setServletContextName(_HOOK_DISPLAY_NAME);

		HotDeployEvent hotDeployEvent = getHotDeployEvent(
			mockServletContext, classLoader);

		URL resourceURL = classLoader.getResource(
			"WEB-INF/liferay-plugin-package.properties");

		Properties pluginPackageProperties = getPluginPackageProperties(
			resourceURL);

		Assert.assertNotNull(pluginPackageProperties);
		Assert.assertFalse(pluginPackageProperties.isEmpty());

		PluginPackage pluginPackage =
			PluginPackageUtil.readPluginPackageProperties(
				_HOOK_DISPLAY_NAME, pluginPackageProperties);

		hotDeployEvent.setPluginPackage(pluginPackage);

		HotDeployUtil.fireDeployEvent(hotDeployEvent);

		PortletContextLoaderListener portletContextLoaderListener =
			new PortletContextLoaderListener();

		try {
			PortletClassLoaderUtil.setClassLoader(
				hotDeployEvent.getContextClassLoader());
			PortletClassLoaderUtil.setServletContextName(
				hotDeployEvent.getServletContextName());

			portletContextLoaderListener.contextInitialized(
				new ServletContextEvent(mockServletContext));
		}
		finally {
			PortletClassLoaderUtil.setClassLoader(null);
			PortletClassLoaderUtil.setServletContextName(null);
		}

		_hotDeployEvents.put(clazz, hotDeployEvent);
	}

	protected Properties getPluginPackageProperties(URL resourceURL) {
		String filename = resourceURL.getPath();

		try {
			String propertiesString = FileUtil.read(filename);

			return PropertiesUtil.load(propertiesString);
		}
		catch (IOException ioe) {
			Assert.fail(ioe.getMessage());

			return null;
		}
	}

	protected HotDeployEvent getHotDeployEvent(
		ServletContext servletContext, ClassLoader classLoader) {

		boolean dependencyManagementEnabled =
			DependencyManagementThreadLocal.isEnabled();

		try {
			DependencyManagementThreadLocal.setEnabled(false);

			return new HotDeployEvent(servletContext, classLoader);
		}
		finally {
			DependencyManagementThreadLocal.setEnabled(
				dependencyManagementEnabled);
		}
	}

	private static final String _HOOK_DISPLAY_NAME = "a-test-hook";

	private static Map<Class<?>, HotDeployEvent> _hotDeployEvents =
		new HashMap<Class<?>, HotDeployEvent>();

	private static class PACLResourceLoader implements ResourceLoader {

		public PACLResourceLoader(ClassLoader classLoader) {
			_classLoader = classLoader;
		}

		@Override
		public Resource getResource(String location) {
			ClassLoader classLoader = getClassLoader();

			return new ClassPathResource(
				PACLIntegrationJUnitTestRunner.RESOURCE_PATH + location,
				classLoader);
		}

		@Override
		public ClassLoader getClassLoader() {
			return _classLoader;
		}

		private ClassLoader _classLoader;

	}

}