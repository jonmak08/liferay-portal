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

package com.liferay.portal.repository.temporaryrepository;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutService;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.document.library.kernel.service.DLFileVersionService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderService;
<<<<<<< HEAD
import com.liferay.petra.function.UnsafeSupplier;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.RepositoryService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.UnsafeRunnable;
>>>>>>> compatible
import com.liferay.portal.repository.liferayrepository.LiferayRepository;

/**
 * @author Iván Zaera
 */
public class TemporaryFileEntryRepository extends LiferayRepository {

	public TemporaryFileEntryRepository(
		RepositoryLocalService repositoryLocalService,
		RepositoryService repositoryService,
		DLAppHelperLocalService dlAppHelperLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryService dlFileEntryService,
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService,
		DLFileShortcutLocalService dlFileShortcutLocalService,
		DLFileShortcutService dlFileShortcutService,
		DLFileVersionLocalService dlFileVersionLocalService,
		DLFileVersionService dlFileVersionService,
		DLFolderLocalService dlFolderLocalService,
		DLFolderService dlFolderService,
		ResourceLocalService resourceLocalService, long groupId,
		long repositoryId, long dlFolderId) {

		super(
			repositoryLocalService, repositoryService, dlAppHelperLocalService,
			dlFileEntryLocalService, dlFileEntryService,
			dlFileEntryTypeLocalService, dlFileShortcutLocalService,
			dlFileShortcutService, dlFileVersionLocalService,
			dlFileVersionService, dlFolderLocalService, dlFolderService,
			resourceLocalService, groupId, repositoryId, dlFolderId);
	}

	@Override
	public void deleteAll() {
<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteAll();

				return null;
			});
=======
		_runWithoutSystemEvents(super::deleteAll);
>>>>>>> compatible
	}

	@Override
	public void deleteFileEntry(long fileEntryId) throws PortalException {
<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFileEntry(fileEntryId);

				return null;
			});
=======
		_runWithoutSystemEvents(() -> super.deleteFileEntry(fileEntryId));
>>>>>>> compatible
	}

	@Override
	public void deleteFileEntry(long folderId, String title)
		throws PortalException {

<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFileEntry(folderId, title);

				return null;
			});
=======
		_runWithoutSystemEvents(() -> super.deleteFileEntry(folderId, title));
>>>>>>> compatible
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId) throws PortalException {
<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFileShortcut(fileShortcutId);

				return null;
			});
=======
		_runWithoutSystemEvents(() -> super.deleteFileShortcut(fileShortcutId));
>>>>>>> compatible
	}

	@Override
	public void deleteFileShortcuts(long toFileEntryId) throws PortalException {
<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFileShortcuts(toFileEntryId);

				return null;
			});
=======
		_runWithoutSystemEvents(() -> super.deleteFileShortcuts(toFileEntryId));
>>>>>>> compatible
	}

	@Override
	public void deleteFileVersion(long fileEntryId, String version)
		throws PortalException {

		_runWithoutSystemEvents(
<<<<<<< HEAD
			() -> {
				super.deleteFileVersion(fileEntryId, version);

				return null;
			});
=======
			() -> super.deleteFileVersion(fileEntryId, version));
>>>>>>> compatible
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFolder(folderId);

				return null;
			});
=======
		_runWithoutSystemEvents(() -> super.deleteFolder(folderId));
>>>>>>> compatible
	}

	@Override
	public void deleteFolder(long parentFolderId, String name)
		throws PortalException {

<<<<<<< HEAD
		_runWithoutSystemEvents(
			() -> {
				super.deleteFolder(parentFolderId, name);

				return null;
			});
	}

	private <T extends Throwable> void _runWithoutSystemEvents(
			UnsafeSupplier<Void, T> unsafeSupplier)
=======
		_runWithoutSystemEvents(() -> super.deleteFolder(parentFolderId, name));
	}

	private <T extends Throwable> void _runWithoutSystemEvents(
			UnsafeRunnable<T> unsafeRunnable)
>>>>>>> compatible
		throws T {

		SystemEventHierarchyEntryThreadLocal.push(DLFileEntry.class);

		try {
<<<<<<< HEAD
			unsafeSupplier.get();
=======
			unsafeRunnable.run();
>>>>>>> compatible
		}
		finally {
			SystemEventHierarchyEntryThreadLocal.pop(DLFileEntry.class);
		}
	}

}