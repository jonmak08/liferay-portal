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

package com.liferay.frontend.js.bundle.config.extender.internal;

<<<<<<< HEAD
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResources;
=======
>>>>>>> compatible
import com.liferay.portal.servlet.delegate.ServletContextDelegate;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 * @author Chema Balsas
 */
@Component(enabled = false, immediate = true)
public class JSBundleConfigPortalWebResources {

	@Activate
	protected void activate(BundleContext bundleContext) {
		try {
<<<<<<< HEAD
			PortalWebResources portalWebResources =
				new InternalPortalWebResources(
					_jsBundleConfigServlet.getServletContext());

			_serviceRegistration = bundleContext.registerService(
				PortalWebResources.class, portalWebResources, null);
=======
			com.liferay.portal.kernel.servlet.PortalWebResources
				portalWebResources = new InternalPortalWebResources(
					_jsBundleConfigServlet.getServletContext());

			_serviceRegistration = bundleContext.registerService(
				com.liferay.portal.kernel.servlet.PortalWebResources.class,
				portalWebResources, null);
>>>>>>> compatible
		}
		catch (NoClassDefFoundError ncdfe) {
			throw new RuntimeException(ncdfe);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Reference
	private JSBundleConfigServlet _jsBundleConfigServlet;

	@Reference
	private JSBundleConfigTracker _jsBundleConfigTracker;

	private ServiceRegistration<?> _serviceRegistration;

<<<<<<< HEAD
	private class InternalPortalWebResources implements PortalWebResources {
=======
	private class InternalPortalWebResources
		implements com.liferay.portal.kernel.servlet.PortalWebResources {
>>>>>>> compatible

		@Override
		public String getContextPath() {
			return _servletContext.getContextPath();
		}

		@Override
		public long getLastModified() {
			return _jsBundleConfigTracker.getLastModified();
		}

		@Override
		public String getResourceType() {
<<<<<<< HEAD
			return PortalWebResourceConstants.RESOURCE_TYPE_JS_BUNDLE_CONFIG;
=======
			return com.liferay.portal.kernel.servlet.PortalWebResourceConstants.
				RESOURCE_TYPE_JS_BUNDLE_CONFIG;
>>>>>>> compatible
		}

		@Override
		public ServletContext getServletContext() {
			return _servletContext;
		}

		private InternalPortalWebResources(ServletContext servletContext) {
			_servletContext = ServletContextDelegate.create(servletContext);
		}

		private final ServletContext _servletContext;

	}

}