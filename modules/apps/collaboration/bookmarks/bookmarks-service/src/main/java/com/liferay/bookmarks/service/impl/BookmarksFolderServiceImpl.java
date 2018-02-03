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

package com.liferay.bookmarks.service.impl;

import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.base.BookmarksFolderServiceBaseImpl;
<<<<<<< HEAD
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
=======
import com.liferay.bookmarks.service.permission.BookmarksFolderPermissionChecker;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
>>>>>>> compatible
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class BookmarksFolderServiceImpl extends BookmarksFolderServiceBaseImpl {

	@Override
	public BookmarksFolder addFolder(
			long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		ModelResourcePermissionHelper.check(
			_bookmarksFolderModelResourcePermission, getPermissionChecker(),
			serviceContext.getScopeGroupId(), parentFolderId,
			ActionKeys.ADD_FOLDER);
=======
		BookmarksFolderPermissionChecker.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			parentFolderId, ActionKeys.ADD_FOLDER);
>>>>>>> compatible

		return bookmarksFolderLocalService.addFolder(
			getUserId(), parentFolderId, name, description, serviceContext);
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.DELETE);

		bookmarksFolderLocalService.deleteFolder(folderId);
	}

	@Override
	public void deleteFolder(long folderId, boolean includeTrashedEntries)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.DELETE);

		bookmarksFolderLocalService.deleteFolder(
			folderId, includeTrashedEntries);
	}

	@Override
	public BookmarksFolder getFolder(long folderId) throws PortalException {
		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.VIEW);

		return folder;
	}

	@Override
	public List<Long> getFolderIds(long groupId, long folderId)
		throws PortalException {

<<<<<<< HEAD
		ModelResourcePermissionHelper.check(
			_bookmarksFolderModelResourcePermission, getPermissionChecker(),
			groupId, folderId, ActionKeys.VIEW);
=======
		BookmarksFolderPermissionChecker.check(
			getPermissionChecker(), groupId, folderId, ActionKeys.VIEW);
>>>>>>> compatible

		List<Long> folderIds = getSubfolderIds(groupId, folderId, true);

		folderIds.add(0, folderId);

		return folderIds;
	}

	@Override
	public List<BookmarksFolder> getFolders(long groupId) {
		return bookmarksFolderPersistence.filterFindByGroupId(groupId);
	}

	@Override
	public List<BookmarksFolder> getFolders(long groupId, long parentFolderId) {
		return bookmarksFolderPersistence.filterFindByG_P_S(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public List<BookmarksFolder> getFolders(
		long groupId, long parentFolderId, int start, int end) {

		return getFolders(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED, start,
			end);
	}

	@Override
	public List<BookmarksFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return bookmarksFolderPersistence.filterFindByG_P(
				groupId, parentFolderId, start, end);
		}
		else {
			return bookmarksFolderPersistence.filterFindByG_P_S(
				groupId, parentFolderId, status, start, end);
		}
	}

	@Override
	public List<Object> getFoldersAndEntries(long groupId, long folderId) {
		return getFoldersAndEntries(
			groupId, folderId, WorkflowConstants.STATUS_ANY);
	}

	@Override
	public List<Object> getFoldersAndEntries(
		long groupId, long folderId, int status) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(status);

		return bookmarksFolderFinder.filterFindBF_E_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public List<Object> getFoldersAndEntries(
		long groupId, long folderId, int status, int start, int end) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(
			status, start, end, null);

		return bookmarksFolderFinder.filterFindBF_E_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public int getFoldersAndEntriesCount(long groupId, long folderId) {
		return getFoldersAndEntriesCount(
			groupId, folderId, WorkflowConstants.STATUS_ANY);
	}

	@Override
	public int getFoldersAndEntriesCount(
		long groupId, long folderId, int status) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(status);

		return bookmarksFolderFinder.filterCountF_E_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId) {
		return getFoldersCount(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return bookmarksFolderPersistence.filterCountByG_P_NotS(
				groupId, parentFolderId, WorkflowConstants.STATUS_IN_TRASH);
		}
		else {
			return bookmarksFolderPersistence.filterCountByG_P_S(
				groupId, parentFolderId, status);
		}
	}

	/**
	 * @deprecated As of 1.0.0, replaced by {@link #getSubfolderIds(List, long,
	 *             long, boolean)}
	 */
	@Deprecated
	@Override
	public void getSubfolderIds(
		List<Long> folderIds, long groupId, long folderId) {

		getSubfolderIds(folderIds, groupId, folderId, true);
	}

	@Override
	public void getSubfolderIds(
		List<Long> folderIds, long groupId, long folderId, boolean recurse) {

		List<BookmarksFolder> folders =
			bookmarksFolderPersistence.filterFindByG_P_S(
				groupId, folderId, WorkflowConstants.STATUS_APPROVED);

		for (BookmarksFolder folder : folders) {
			folderIds.add(folder.getFolderId());

			if (recurse) {
				getSubfolderIds(
					folderIds, folder.getGroupId(), folder.getFolderId(),
					recurse);
			}
		}
	}

	@Override
	public List<Long> getSubfolderIds(
		long groupId, long folderId, boolean recurse) {

		List<Long> folderIds = new ArrayList<>();

		getSubfolderIds(folderIds, groupId, folderId, recurse);

		return folderIds;
	}

	@Override
	public void mergeFolders(long folderId, long parentFolderId)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		bookmarksFolderLocalService.mergeFolders(folderId, parentFolderId);
	}

	@Override
	public BookmarksFolder moveFolder(long folderId, long parentFolderId)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		return bookmarksFolderLocalService.moveFolder(folderId, parentFolderId);
	}

	@Override
	public BookmarksFolder moveFolderFromTrash(
			long folderId, long parentFolderId)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		return bookmarksFolderLocalService.moveFolderFromTrash(
			getUserId(), folderId, parentFolderId);
	}

	@Override
	public BookmarksFolder moveFolderToTrash(long folderId)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.DELETE);

		return bookmarksFolderLocalService.moveFolderToTrash(
			getUserId(), folderId);
	}

	@Override
	public void restoreFolderFromTrash(long folderId) throws PortalException {
		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		bookmarksFolderLocalService.restoreFolderFromTrash(
			getUserId(), folderId);
	}

	@Override
	public void subscribeFolder(long groupId, long folderId)
		throws PortalException {

<<<<<<< HEAD
		ModelResourcePermissionHelper.check(
			_bookmarksFolderModelResourcePermission, getPermissionChecker(),
			groupId, folderId, ActionKeys.SUBSCRIBE);
=======
		BookmarksFolderPermissionChecker.check(
			getPermissionChecker(), groupId, folderId, ActionKeys.SUBSCRIBE);
>>>>>>> compatible

		bookmarksFolderLocalService.subscribeFolder(
			getUserId(), groupId, folderId);
	}

	@Override
	public void unsubscribeFolder(long groupId, long folderId)
		throws PortalException {

<<<<<<< HEAD
		ModelResourcePermissionHelper.check(
			_bookmarksFolderModelResourcePermission, getPermissionChecker(),
			groupId, folderId, ActionKeys.SUBSCRIBE);
=======
		BookmarksFolderPermissionChecker.check(
			getPermissionChecker(), groupId, folderId, ActionKeys.SUBSCRIBE);
>>>>>>> compatible

		bookmarksFolderLocalService.unsubscribeFolder(
			getUserId(), groupId, folderId);
	}

	/**
	 * @deprecated As of 1.0.0, replaced by {@link #updateFolder(long, long,
	 *             String, String, ServiceContext)} and {@link
	 *             #mergeFolders(long, long)}
	 */
	@Deprecated
	@Override
	public BookmarksFolder updateFolder(
			long folderId, long parentFolderId, String name, String description,
			boolean mergeWithParentFolder, ServiceContext serviceContext)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		return bookmarksFolderLocalService.updateFolder(
			getUserId(), folderId, parentFolderId, name, description,
			mergeWithParentFolder, serviceContext);
	}

	@Override
	public BookmarksFolder updateFolder(
			long folderId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		BookmarksFolder folder = bookmarksFolderLocalService.getFolder(
			folderId);

<<<<<<< HEAD
		_bookmarksFolderModelResourcePermission.check(
=======
		BookmarksFolderPermissionChecker.check(
>>>>>>> compatible
			getPermissionChecker(), folder, ActionKeys.UPDATE);

		return bookmarksFolderLocalService.updateFolder(
			getUserId(), folderId, parentFolderId, name, description,
			serviceContext);
	}

<<<<<<< HEAD
	private static volatile ModelResourcePermission<BookmarksFolder>
		_bookmarksFolderModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				BookmarksFolderServiceImpl.class,
				"_bookmarksFolderModelResourcePermission",
				BookmarksFolder.class);

=======
>>>>>>> compatible
}