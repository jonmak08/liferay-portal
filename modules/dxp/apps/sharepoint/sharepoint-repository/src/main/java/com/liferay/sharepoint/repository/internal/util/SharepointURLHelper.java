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

package com.liferay.sharepoint.repository.internal.util;

import com.liferay.document.library.repository.external.ExtRepositoryFileEntry;

/**
 * @author Adolfo Pérez
 */
public class SharepointURLHelper {

	public SharepointURLHelper(String siteAbsoluteUrl) {
		_siteAbsoluteUrl = siteAbsoluteUrl;
	}

	public String getAddFileURL(String extRepositoryFolderKey, String name) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Files/Add" +
				"(overwrite=true,url='%s')",
			_siteAbsoluteUrl, extRepositoryFolderKey, name);
	}

	public String getAddFolderURL(String extRepositoryFolderKey) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Folders",
			_siteAbsoluteUrl, extRepositoryFolderKey);
	}

	public String getFileEntryContentURL(
		ExtRepositoryFileEntry extRepositoryFileEntry) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/OpenBinaryStream",
			_siteAbsoluteUrl,
			extRepositoryFileEntry.getExtRepositoryModelKey());
	}

	private final String _siteAbsoluteUrl;

}