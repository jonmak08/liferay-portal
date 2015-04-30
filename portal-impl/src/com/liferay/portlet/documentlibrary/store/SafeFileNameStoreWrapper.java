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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public class SafeFileNameStoreWrapper extends BaseStoreWrapper {

	public SafeFileNameStoreWrapper(Store store) {
		super(store);
	}

	@Override
	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				store.move(dirName, safeDirName);
			}
			catch (Exception e) {
			}
		}

		store.addDirectory(companyId, repositoryId, safeDirName);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().addFile(companyId, repositoryId, safeFileName, bytes);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().addFile(companyId, repositoryId, safeFileName, file);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream is)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().addFile(companyId, repositoryId, safeFileName, is);
	}

	@Override
	public void checkRoot(long companyId) throws SystemException {
		getStore().checkRoot(companyId);
	}

	@Override
	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().copyFileVersion(
			companyId, repositoryId, safeFileName, fromVersionLabel,
			toVersionLabel);
	}

	@Override
	public void deleteDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				store.deleteDirectory(companyId, repositoryId, dirName);

				return;
			}
			catch (Exception e) {
			}
		}

		store.deleteDirectory(companyId, repositoryId, safeDirName);
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			store.deleteFile(companyId, repositoryId, fileName);

			return;
		}

		store.deleteFile(companyId, repositoryId, safeFileName);
	}

	@Override
	public void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName, versionLabel)) {

			store.deleteFile(companyId, repositoryId, fileName, versionLabel);

			return;
		}

		store.deleteFile(companyId, repositoryId, safeFileName, versionLabel);
	}

	@Override
	public File getFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			return store.getFile(companyId, repositoryId, fileName);
		}

		return store.getFile(companyId, repositoryId, safeFileName);
	}

	@Override
	public File getFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName, versionLabel)) {

			return store.getFile(
				companyId, repositoryId, fileName, versionLabel);
		}

		return store.getFile(
			companyId, repositoryId, safeFileName, versionLabel);
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			return store.getFileAsBytes(companyId, repositoryId, fileName);
		}

		return store.getFileAsBytes(companyId, repositoryId, safeFileName);
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName, versionLabel)) {

			return store.getFileAsBytes(
				companyId, repositoryId, fileName, versionLabel);
		}

		return store.getFileAsBytes(
			companyId, repositoryId, safeFileName, versionLabel);
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			return store.getFileAsStream(companyId, repositoryId, fileName);
		}

		return store.getFileAsStream(companyId, repositoryId, safeFileName);
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName, versionLabel)) {

			return store.getFileAsStream(
				companyId, repositoryId, fileName, versionLabel);
		}

		return store.getFileAsStream(
			companyId, repositoryId, safeFileName, versionLabel);
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId)
		throws SystemException {

		String[] fileNames = getStore().getFileNames(companyId, repositoryId);

		String[] decodedFileNames = new String[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {
			decodedFileNames[i] = FileUtil.decodeSafeFileName(fileNames[i]);
		}

		return decodedFileNames;
	}

	@Override
	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				store.move(dirName, safeDirName);
			}
			catch (Exception e) {
			}
		}

		String[] fileNames = store.getFileNames(
			companyId, repositoryId, safeDirName);

		String[] decodedFileNames = new String[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {
			decodedFileNames[i] = FileUtil.decodeSafeFileName(fileNames[i]);
		}

		return decodedFileNames;
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			return store.getFileSize(companyId, repositoryId, fileName);
		}

		return store.getFileSize(companyId, repositoryId, safeFileName);
	}

	@Override
	public boolean hasDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		return getStore().hasDirectory(companyId, repositoryId, safeDirName);
	}

	@Override
	public boolean hasFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName)) {

			return true;
		}

		return store.hasFile(companyId, repositoryId, safeFileName);
	}

	@Override
	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			store.hasFile(companyId, repositoryId, fileName, versionLabel)) {

			return true;
		}

		return store.hasFile(
			companyId, repositoryId, safeFileName, versionLabel);
	}

	@Override
	public void move(String srcDir, String destDir) throws SystemException {
		getStore().move(srcDir, destDir);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().updateFile(
			companyId, repositoryId, newRepositoryId, safeFileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws PortalException, SystemException {

		Store store = getStore();

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeNewFileName = FileUtil.encodeSafeFileName(newFileName);

		if (!safeFileName.equals(fileName)) {
			if (store.hasFile(
					companyId, repositoryId, fileName,
					DLFileEntryConstants.VERSION_DEFAULT)) {

				safeFileName = fileName;
			}
		}

		store.updateFile(
			companyId, repositoryId, safeFileName, safeNewFileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, byte[] bytes)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().updateFile(
			companyId, repositoryId, safeFileName, versionLabel, bytes);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, File file)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().updateFile(
			companyId, repositoryId, safeFileName, versionLabel, file);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream is)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().updateFile(
			companyId, repositoryId, safeFileName, versionLabel, is);
	}

	@Override
	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		getStore().updateFileVersion(
			companyId, repositoryId, safeFileName, fromVersionLabel,
			toVersionLabel);
	}

	protected void renameUnsafeFile(
			long companyId, long repositoryId, String fileName,
			String safeFileName)
		throws PortalException, SystemException {

		if (!safeFileName.equals(fileName)) {
			Store store = getStore();

			if (store.hasFile(
					companyId, repositoryId, fileName,
					DLFileEntryConstants.VERSION_DEFAULT)) {

				store.updateFile(
					companyId, repositoryId, fileName, safeFileName);
			}
		}
	}

}