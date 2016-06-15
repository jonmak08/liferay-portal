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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.documentlibrary.DuplicateFileException;

import java.io.File;
import java.io.InputStream;

/**
 * @author Adolfo Pérez
 */
public class IgnoreDuplicatesStoreWrapper extends BaseStoreWrapper {

	public IgnoreDuplicatesStoreWrapper(Store store) {
		super(store);
	}

	@Override
	public void addFile(
			final long companyId, final long repositoryId,
			final String fileName, final byte[] bytes)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, Store.VERSION_DEFAULT),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.addFile(companyId, repositoryId, fileName, bytes);
				}

			});
	}

	@Override
	public void addFile(
			final long companyId, final long repositoryId,
			final String fileName, final File file)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, Store.VERSION_DEFAULT),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.addFile(companyId, repositoryId, fileName, file);
				}

			});
	}

	@Override
	public void addFile(
			final long companyId, final long repositoryId,
			final String fileName, final InputStream is)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, Store.VERSION_DEFAULT),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.addFile(companyId, repositoryId, fileName, is);
				}

			});
	}

	@Override
	public void copyFileVersion(
			final long companyId, final long repositoryId,
			final String fileName, final String fromVersionLabel,
			final String toVersionLabel)
		throws PortalException, SystemException {

		if (fromVersionLabel.equals(toVersionLabel)) {
			return;
		}

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, toVersionLabel),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.copyFileVersion(
						companyId, repositoryId, fileName, fromVersionLabel,
						toVersionLabel);
				}

			});
	}

	@Override
	public void updateFile(
			final long companyId, final long repositoryId,
			final long newRepositoryId, final String fileName)
		throws PortalException, SystemException {

		if (repositoryId == newRepositoryId) {
			return;
		}

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(companyId, newRepositoryId, fileName),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFile(
						companyId, repositoryId, newRepositoryId, fileName);
				}

			});
	}

	@Override
	public void updateFile(
			final long companyId, final long repositoryId,
			final String fileName, final String newFileName)
		throws PortalException, SystemException {

		if (fileName.equals(newFileName)) {
			return;
		}

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(companyId, repositoryId, newFileName),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFile(
						companyId, repositoryId, fileName, newFileName);
				}

			});
	}

	@Override
	public void updateFile(
			final long companyId, final long repositoryId,
			final String fileName, final String versionLabel,
			final byte[] bytes)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, versionLabel),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel, bytes);
				}

			});
	}

	@Override
	public void updateFile(
			final long companyId, final long repositoryId,
			final String fileName, final String versionLabel, final File file)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, versionLabel),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel, file);
				}

			});
	}

	@Override
	public void updateFile(
			final long companyId, final long repositoryId,
			final String fileName, final String versionLabel,
			final InputStream is)
		throws PortalException, SystemException {

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, versionLabel),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel, is);
				}

			});
	}

	@Override
	public void updateFileVersion(
			final long companyId, final long repositoryId,
			final String fileName, final String fromVersionLabel,
			final String toVersionLabel)
		throws PortalException, SystemException {

		if (fromVersionLabel.equals(toVersionLabel)) {
			return;
		}

		recoverAndRetryOnFailure(
			createDeleteFileStoreAction(
				companyId, repositoryId, fileName, toVersionLabel),
			new StoreAction() {

				@Override
				public void execute() throws PortalException, SystemException {
					Store store = getStore();

					store.updateFileVersion(
						companyId, repositoryId, fileName, fromVersionLabel,
						toVersionLabel);
				}

			});
	}

	protected static void recoverAndRetryOnFailure(
			StoreAction recoverStoreAction, StoreAction storeAction)
		throws PortalException, SystemException {

		try {
			storeAction.execute();
		}
		catch (DuplicateFileException dfe) {
			recoverStoreAction.execute();

			storeAction.execute();
		}
	}

	protected StoreAction createDeleteFileStoreAction(
		final long companyId, final long repositoryId, final String fileName) {

		return new StoreAction() {

			@Override
			public void execute() throws PortalException, SystemException {
				Store store = getStore();

				store.deleteFile(companyId, repositoryId, fileName);
			}

		};
	}

	protected StoreAction createDeleteFileStoreAction(
		final long companyId, final long repositoryId, final String fileName,
		final String versionLabel) {

		return new StoreAction() {

			@Override
			public void execute() throws PortalException, SystemException {
				Store store = getStore();

				store.deleteFile(
					companyId, repositoryId, fileName, versionLabel);
			}

		};
	}

	private interface StoreAction {

		public void execute() throws PortalException, SystemException;

	}

}