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

package com.liferay.portal.kernel.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Adolfo Pérez
 */
public class ClassResourceBundleLoader implements ResourceBundleLoader {

	public ClassResourceBundleLoader(String baseName, Class<?> clazz) {
		this(baseName, clazz.getClassLoader());
	}

	public ClassResourceBundleLoader(String baseName, ClassLoader classLoader) {
		_baseName = baseName;
		_classLoader = classLoader;
	}

	@Override
<<<<<<< HEAD
	public ResourceBundle loadResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(_baseName, locale, _classLoader);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #loadResourceBundle(Locale)}
	 */
	@Deprecated
	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return ResourceBundleLoader.super.loadResourceBundle(languageId);
=======
	public ResourceBundle loadResourceBundle(String languageId) {
		Locale locale = LocaleUtil.fromLanguageId(languageId);

		return ResourceBundleUtil.getBundle(_baseName, locale, _classLoader);
>>>>>>> compatible
	}

	private final String _baseName;
	private final ClassLoader _classLoader;

}