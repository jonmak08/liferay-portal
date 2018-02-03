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

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Locale;
=======
>>>>>>> compatible
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Carlos Sierra Andrés
 */
public class AggregateResourceBundleLoader implements ResourceBundleLoader {

	public AggregateResourceBundleLoader(
		ResourceBundleLoader... resourceBundleLoaders) {

		for (int i = 0; i < resourceBundleLoaders.length; i++) {
			if (resourceBundleLoaders[i] == null) {
				throw new NullPointerException(
					"Null resource bundle loader at index " + i);
			}
		}

		_resourceBundleLoaders = resourceBundleLoaders;
	}

	@Override
<<<<<<< HEAD
	public ResourceBundle loadResourceBundle(Locale locale) {
=======
	public ResourceBundle loadResourceBundle(String languageId) {
>>>>>>> compatible
		List<ResourceBundle> resourceBundles = new ArrayList<>();

		for (ResourceBundleLoader resourceBundleLoader :
				_resourceBundleLoaders) {

			try {
				ResourceBundle resourceBundle =
<<<<<<< HEAD
					resourceBundleLoader.loadResourceBundle(locale);
=======
					resourceBundleLoader.loadResourceBundle(languageId);
>>>>>>> compatible

				if (resourceBundle != null) {
					resourceBundles.add(resourceBundle);
				}
			}
			catch (Exception e) {
			}
		}

		if (resourceBundles.isEmpty()) {
<<<<<<< HEAD
			String languageId = LocaleUtil.toLanguageId(locale);

			throw new MissingResourceException(
				StringBundler.concat(
					"Resource bundle loader ", String.valueOf(this),
					" was unable to load resource bundle for ", languageId),
=======
			throw new MissingResourceException(
				"Resource bundle loader " + this + " was unable to load " +
					"resource bundle for " + languageId,
>>>>>>> compatible
				StringPool.BLANK, languageId);
		}

		if (resourceBundles.size() == 1) {
			return resourceBundles.get(0);
		}

		return new AggregateResourceBundle(
			resourceBundles.toArray(
				new ResourceBundle[resourceBundles.size()]));
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 7.0.0, replaced by {@link #loadResourceBundle(Locale)}
	 */
	@Deprecated
	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return ResourceBundleLoader.super.loadResourceBundle(languageId);
	}

=======
>>>>>>> compatible
	private final ResourceBundleLoader[] _resourceBundleLoaders;

}