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

package com.liferay.asset.taglib.internal.servlet;

<<<<<<< HEAD
import com.liferay.asset.util.AssetHelper;

=======
>>>>>>> compatible
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael Bradford
 */
@Component(immediate = true)
public class ServletContextUtil {

<<<<<<< HEAD
	public static final AssetHelper getAssetHelper() {
		return _instance._getAssetHelper();
	}

=======
>>>>>>> compatible
	public static final ServletContext getServletContext() {
		return _instance._getServletContext();
	}

	@Activate
	protected void activate() {
		_instance = this;
	}

	@Deactivate
	protected void deactivate() {
		_instance = null;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

<<<<<<< HEAD
	private AssetHelper _getAssetHelper() {
		return _assetHelper;
	}

=======
>>>>>>> compatible
	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

<<<<<<< HEAD
	@Reference
	private AssetHelper _assetHelper;

=======
>>>>>>> compatible
	private ServletContext _servletContext;

}