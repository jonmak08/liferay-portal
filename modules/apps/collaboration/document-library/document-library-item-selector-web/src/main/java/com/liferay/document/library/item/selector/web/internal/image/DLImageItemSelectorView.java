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

package com.liferay.document.library.item.selector.web.internal.image;

import com.liferay.document.library.item.selector.web.internal.BaseDLItemSelectorView;
<<<<<<< HEAD
import com.liferay.document.library.item.selector.web.internal.configuration.DLImageItemSelectorViewConfiguration;
=======
>>>>>>> compatible
import com.liferay.document.library.item.selector.web.internal.constants.DLItemSelectorViewConstants;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
<<<<<<< HEAD
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Collections;
import java.util.List;
<<<<<<< HEAD
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
=======

import org.osgi.service.component.annotations.Component;
>>>>>>> compatible

/**
 * @author Roberto Díaz
 */
@Component(
<<<<<<< HEAD
	configurationPid = "com.liferay.document.library.item.selector.web.internal.configuration.DLImageItemSelectorViewConfiguration",
=======
>>>>>>> compatible
	property = {
		"item.selector.view.key=" + DLItemSelectorViewConstants.DL_IMAGE_ITEM_SELECTOR_VIEW_KEY,
		"item.selector.view.order:Integer=100"
	},
	service = ItemSelectorView.class
)
public class DLImageItemSelectorView
	extends BaseDLItemSelectorView<ImageItemSelectorCriterion> {

	@Override
<<<<<<< HEAD
	public String[] getExtensions() {
		return _dlImageItemSelectorViewConfiguration.validExtensions();
	}

	@Override
=======
>>>>>>> compatible
	public Class<ImageItemSelectorCriterion> getItemSelectorCriterionClass() {
		return ImageItemSelectorCriterion.class;
	}

	@Override
	public String[] getMimeTypes() {
		return PropsValues.DL_FILE_ENTRY_PREVIEW_IMAGE_MIME_TYPES;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

<<<<<<< HEAD
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_dlImageItemSelectorViewConfiguration =
			ConfigurableUtil.createConfigurable(
				DLImageItemSelectorViewConfiguration.class, properties);
	}

=======
>>>>>>> compatible
	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new FileEntryItemSelectorReturnType(),
					new URLItemSelectorReturnType()
				}));

<<<<<<< HEAD
	private volatile DLImageItemSelectorViewConfiguration
		_dlImageItemSelectorViewConfiguration;

=======
>>>>>>> compatible
}