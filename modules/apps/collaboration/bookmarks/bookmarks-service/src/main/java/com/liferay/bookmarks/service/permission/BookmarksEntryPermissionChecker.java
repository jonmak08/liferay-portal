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

package com.liferay.bookmarks.service.permission;

<<<<<<< HEAD
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
=======
import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.exception.NoSuchFolderException;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.util.PropsValues;
>>>>>>> compatible

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
<<<<<<< HEAD
 * @deprecated As of 1.2.0, with no direct replacement
=======
>>>>>>> compatible
 */
@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.bookmarks.model.BookmarksEntry"}
)
<<<<<<< HEAD
@Deprecated
=======
>>>>>>> compatible
public class BookmarksEntryPermissionChecker
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, BookmarksEntry entry,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_bookmarksEntryModelResourcePermission.check(
			permissionChecker, entry, actionId);
=======
		if (!contains(permissionChecker, entry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksEntry.class.getName(),
				entry.getEntryId(), actionId);
		}
>>>>>>> compatible
	}

	public static void check(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		_bookmarksEntryModelResourcePermission.check(
			permissionChecker, entryId, actionId);
=======
		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksEntry.class.getName(), entryId,
				actionId);
		}
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, BookmarksEntry entry,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _bookmarksEntryModelResourcePermission.contains(
			permissionChecker, entry, actionId);
=======
		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, entry.getGroupId(),
			BookmarksEntry.class.getName(), entry.getEntryId(),
			BookmarksPortletKeys.BOOKMARKS, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			long folderId = entry.getFolderId();

			if (folderId == BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				if (!BookmarksResourcePermissionChecker.contains(
						permissionChecker, entry.getGroupId(), actionId)) {

					return false;
				}
			}
			else {
				try {
					BookmarksFolder folder =
						_bookmarksFolderLocalService.getFolder(folderId);

					if (!BookmarksFolderPermissionChecker.contains(
							permissionChecker, folder, ActionKeys.ACCESS) &&
						!BookmarksFolderPermissionChecker.contains(
							permissionChecker, folder, ActionKeys.VIEW)) {

						return false;
					}
				}
				catch (NoSuchFolderException nsfe) {
					if (!entry.isInTrash()) {
						throw nsfe;
					}
				}
			}
		}

		if (permissionChecker.hasOwnerPermission(
				entry.getCompanyId(), BookmarksEntry.class.getName(),
				entry.getEntryId(), entry.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			entry.getGroupId(), BookmarksEntry.class.getName(),
			entry.getEntryId(), actionId);
>>>>>>> compatible
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

<<<<<<< HEAD
		return _bookmarksEntryModelResourcePermission.contains(
			permissionChecker, entryId, actionId);
=======
		BookmarksEntry entry = _bookmarksEntryLocalService.getEntry(entryId);

		return contains(permissionChecker, entry, actionId);
>>>>>>> compatible
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

<<<<<<< HEAD
		_bookmarksEntryModelResourcePermission.check(
			permissionChecker, primaryKey, actionId);
	}

	protected void setBookmarksEntryLocalService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {
	}

	protected void setBookmarksFolderLocalService(
		BookmarksFolderLocalService bookmarksFolderLocalService) {
	}

	@Reference(
		target = "(model.class.name=com.liferay.bookmarks.model.BookmarksEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<BookmarksEntry> modelResourcePermission) {

		_bookmarksEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<BookmarksEntry>
		_bookmarksEntryModelResourcePermission;
=======
		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setBookmarksEntryLocalService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {

		_bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setBookmarksFolderLocalService(
		BookmarksFolderLocalService bookmarksFolderLocalService) {

		_bookmarksFolderLocalService = bookmarksFolderLocalService;
	}

	private static BookmarksEntryLocalService _bookmarksEntryLocalService;
	private static BookmarksFolderLocalService _bookmarksFolderLocalService;
>>>>>>> compatible

}