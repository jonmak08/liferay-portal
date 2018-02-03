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

package com.liferay.document.library.web.internal.dynamic.data.mapping.util;

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.BaseDDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.util.DDMDisplayTabItem;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortletKeys;

import java.util.Arrays;
import java.util.List;
=======
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.PortletKeys;

>>>>>>> compatible
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
<<<<<<< HEAD
import org.osgi.service.component.annotations.Reference;
=======
>>>>>>> compatible

/**
 * @author Eduardo Garcia
 * @author Roberto Díaz
 */
@Component(
	property = {"javax.portlet.name=" + PortletKeys.DOCUMENT_LIBRARY},
	service = DDMDisplay.class
)
public class DLDDMDisplay extends BaseDDMDisplay {

	@Override
<<<<<<< HEAD
	public DDMDisplayTabItem getDefaultTabItem() {
		return _defaultTabItem;
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = getResourceBundle(locale);

		return LanguageUtil.get(
			resourceBundle,
			JavaConstants.JAVAX_PORTLET_DESCRIPTION.concat(
				StringPool.PERIOD).concat(PortletKeys.DOCUMENT_LIBRARY_ADMIN));
	}

	@Override
=======
>>>>>>> compatible
	public String getPortletId() {
		return PortletKeys.DOCUMENT_LIBRARY;
	}

	@Override
	public String getStorageType() {
		return StorageType.JSON.toString();
	}

	@Override
	public String getStructureName(Locale locale) {
		ResourceBundle resourceBundle = getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "metadata-set");
	}

	@Override
	public String getStructureType() {
		return DLFileEntryMetadata.class.getName();
	}

	@Override
<<<<<<< HEAD
	public List<DDMDisplayTabItem> getTabItems() {
		return Arrays.asList(
			_documentsAndMediaDDMDisplayTabItem,
			_documentTypesDDMDisplayTabItem, getDefaultTabItem());
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "documents-and-media");
=======
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "metadata-sets");
>>>>>>> compatible
	}

	@Override
	public boolean isShowBackURLInTitleBar() {
<<<<<<< HEAD
		return false;
	}

	private final DDMDisplayTabItem _defaultTabItem =
		(liferayPortletRequest, liferayPortletResponse) -> {
			ResourceBundle resourceBundle = getResourceBundle(
				liferayPortletRequest.getLocale());

			return LanguageUtil.get(resourceBundle, "metadata-sets");
		};

	@Reference
	private DocumentsAndMediaDDMDisplayTabItem
		_documentsAndMediaDDMDisplayTabItem;

	@Reference
	private DocumentTypesDDMDisplayTabItem _documentTypesDDMDisplayTabItem;

=======
		return true;
	}

>>>>>>> compatible
}