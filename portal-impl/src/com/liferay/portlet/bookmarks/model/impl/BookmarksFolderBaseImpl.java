/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.bookmarks.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model base implementation for the BookmarksFolder service. Represents a row in the &quot;BookmarksFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BookmarksFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderImpl
 * @see com.liferay.portlet.bookmarks.model.BookmarksFolder
 * @generated
 */
public abstract class BookmarksFolderBaseImpl extends BookmarksFolderModelImpl
	implements BookmarksFolder {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a bookmarks folder model instance should use the {@link BookmarksFolder} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			BookmarksFolderLocalServiceUtil.addBookmarksFolder(this);
		}
		else {
			BookmarksFolderLocalServiceUtil.updateBookmarksFolder(this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public String buildTreePath() throws PortalException, SystemException {
		List<BookmarksFolder> bookmarksFolders = new ArrayList<BookmarksFolder>();

		BookmarksFolder bookmarksFolder = this;

		while (bookmarksFolder != null) {
			bookmarksFolders.add(bookmarksFolder);

			bookmarksFolder = BookmarksFolderLocalServiceUtil.fetchBookmarksFolder(bookmarksFolder.getParentFolderId());
		}

		StringBundler sb = new StringBundler((bookmarksFolders.size() * 2) + 1);

		sb.append(StringPool.SLASH);

		for (int i = bookmarksFolders.size() - 1; i >= 0; i--) {
			bookmarksFolder = bookmarksFolders.get(i);

			sb.append(bookmarksFolder.getFolderId());
			sb.append(StringPool.SLASH);
		}

		return sb.toString();
	}

	@Override
	public void updateTreePath(String treePath) throws SystemException {
		BookmarksFolder bookmarksFolder = this;

		bookmarksFolder.setTreePath(treePath);

		BookmarksFolderLocalServiceUtil.updateBookmarksFolder(bookmarksFolder);
	}
}