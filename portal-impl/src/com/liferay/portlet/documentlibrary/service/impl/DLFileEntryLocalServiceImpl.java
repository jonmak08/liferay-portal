/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.FileSizeException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.util.MimeTypesUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFolderImpl;
import com.liferay.portlet.documentlibrary.service.base.DLFileEntryLocalServiceBaseImpl;
import com.liferay.util.MathUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="DLFileEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 *
 */
public class DLFileEntryLocalServiceImpl
	extends DLFileEntryLocalServiceBaseImpl {

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description, String[] tagsEntries, String extraSettings,
			File file, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		return addFileEntry(
			userId, folderId, name, title, description, tagsEntries,
			extraSettings, file, Boolean.valueOf(addCommunityPermissions),
			Boolean.valueOf(addGuestPermissions), null, null);
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description, String[] tagsEntries, String extraSettings,
			byte[] byteArray, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		return addFileEntry(
			userId, folderId, name, title, description, tagsEntries,
			extraSettings, byteArray, Boolean.valueOf(addCommunityPermissions),
			Boolean.valueOf(addGuestPermissions), null, null);
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description, String[] tagsEntries, String extraSettings,
			File file, String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		return addFileEntry(
			userId, folderId, name, title, description, tagsEntries,
			extraSettings, file, null, null, communityPermissions,
			guestPermissions);
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description, String[] tagsEntries, String extraSettings,
			byte[] byteArray, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		return addFileEntry(
			userId, folderId, name, title, description, tagsEntries,
			extraSettings, byteArray, null, null, communityPermissions,
			guestPermissions);
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description,	String[] tagsEntries, String extraSettings,
			File file, Boolean addCommunityPermissions,
			Boolean addGuestPermissions, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		if ((file == null) || (file.length() == 0)) {
			throw new FileSizeException();
		}

		InputStream is = null;

		try {
			is = new BufferedInputStream(new FileInputStream(file));

			return addFileEntry(
				userId, folderId, name, title, description, tagsEntries,
				extraSettings, is, file.length(), addCommunityPermissions,
				addGuestPermissions, communityPermissions, guestPermissions);
		}
		catch (FileNotFoundException fnfe) {
			throw new FileSizeException();
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			}
			catch (IOException ioe) {
				_log.error(ioe);
			}
		}
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description,	String[] tagsEntries, String extraSettings,
			byte[] byteArray, Boolean addCommunityPermissions,
			Boolean addGuestPermissions, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		if ((byteArray == null) || (byteArray.length == 0)) {
			throw new FileSizeException();
		}

		InputStream is = new ByteArrayInputStream(byteArray);

		return addFileEntry(
			userId, folderId, name, title, description, tagsEntries,
			extraSettings, is, byteArray.length, addCommunityPermissions,
			addGuestPermissions, communityPermissions, guestPermissions);
	}

	public DLFileEntry addFileEntry(
			long userId, long folderId, String name, String title,
			String description,	String[] tagsEntries, String extraSettings,
			InputStream is, long size, Boolean addCommunityPermissions,
			Boolean addGuestPermissions, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		// File entry

		User user = userPersistence.findByPrimaryKey(userId);
		folderId = getFolderId(user.getCompanyId(), folderId);
		DLFolder folder = dlFolderPersistence.findByPrimaryKey(folderId);
		Date now = new Date();

		if (Validator.isNull(title)) {
			title = name;
		}

		dlLocalService.validate(name, is);

		name = getName(name);

		if (dlLocalService.hasFile(user.getCompanyId(), folderId, name, 0)) {
			throw new DuplicateFileException(name);
		}

		long fileEntryId = counterLocalService.increment();

		DLFileEntry fileEntry = dlFileEntryPersistence.create(fileEntryId);

		fileEntry.setCompanyId(user.getCompanyId());
		fileEntry.setUserId(user.getUserId());
		fileEntry.setUserName(user.getFullName());
		fileEntry.setVersionUserId(user.getUserId());
		fileEntry.setVersionUserName(user.getFullName());
		fileEntry.setCreateDate(now);
		fileEntry.setModifiedDate(now);
		fileEntry.setFolderId(folderId);
		fileEntry.setName(name);
		fileEntry.setTitle(title);
		fileEntry.setDescription(description);
		fileEntry.setVersion(DLFileEntryImpl.DEFAULT_VERSION);
		fileEntry.setSize((int)size);
		fileEntry.setReadCount(DLFileEntryImpl.DEFAULT_READ_COUNT);
		fileEntry.setExtraSettings(extraSettings);

		dlFileEntryPersistence.update(fileEntry);

		// File

		dlLocalService.addFile(
			user.getCompanyId(), PortletKeys.DOCUMENT_LIBRARY,
			folder.getGroupId(), folderId, name,
			fileEntry.getLuceneProperties(), is);

		// Resources

		if ((addCommunityPermissions != null) &&
			(addGuestPermissions != null)) {

			addFileEntryResources(
				folder, fileEntry, addCommunityPermissions.booleanValue(),
				addGuestPermissions.booleanValue());
		}
		else {
			addFileEntryResources(
				folder, fileEntry, communityPermissions, guestPermissions);
		}

		// Tags

		updateTagsAsset(userId, fileEntry, tagsEntries);

		// Folder

		folder.setLastPostDate(fileEntry.getModifiedDate());

		dlFolderPersistence.update(folder);

		return fileEntry;
	}

	public void addFileEntryResources(
			long folderId, String name, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		DLFolder folder = dlFolderPersistence.findByPrimaryKey(folderId);
		DLFileEntry fileEntry = dlFileEntryPersistence.findByF_N(
			folderId, name);

		addFileEntryResources(
			folder, fileEntry, addCommunityPermissions, addGuestPermissions);
	}

	public void addFileEntryResources(
			DLFolder folder, DLFileEntry fileEntry,
			boolean addCommunityPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			fileEntry.getCompanyId(), folder.getGroupId(),
			fileEntry.getUserId(), DLFileEntry.class.getName(),
			fileEntry.getFileEntryId(), false, addCommunityPermissions,
			addGuestPermissions);
	}

	public void addFileEntryResources(
			long folderId, String name, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		DLFolder folder = dlFolderPersistence.findByPrimaryKey(folderId);
		DLFileEntry fileEntry = dlFileEntryPersistence.findByF_N(
			folderId, name);

		addFileEntryResources(
			folder, fileEntry, communityPermissions, guestPermissions);
	}

	public void addFileEntryResources(
			DLFolder folder, DLFileEntry fileEntry,
			String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			fileEntry.getCompanyId(), folder.getGroupId(),
			fileEntry.getUserId(), DLFileEntry.class.getName(),
			fileEntry.getFileEntryId(), communityPermissions, guestPermissions);
	}

	public void deleteFileEntries(long folderId)
		throws PortalException, SystemException {

		Iterator itr = dlFileEntryPersistence.findByFolderId(
			folderId).iterator();

		while (itr.hasNext()) {
			DLFileEntry fileEntry = (DLFileEntry)itr.next();

			deleteFileEntry(fileEntry);
		}
	}

	public void deleteFileEntry(long folderId, String name)
		throws PortalException, SystemException {

		deleteFileEntry(folderId, name, -1);
	}

	public void deleteFileEntry(long folderId, String name, double version)
		throws PortalException, SystemException {

		DLFileEntry fileEntry = dlFileEntryPersistence.findByF_N(
			folderId, name);

		if (version > 0) {
			try {
				dlService.deleteFile(
					fileEntry.getCompanyId(), PortletKeys.DOCUMENT_LIBRARY,
					fileEntry.getFolderId(), fileEntry.getName(), version);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}

			dlFileVersionPersistence.removeByF_N_V(folderId, name, version);
		}
		else {
			deleteFileEntry(fileEntry);
		}
	}

	public void deleteFileEntry(DLFileEntry fileEntry)
		throws PortalException, SystemException {

		// File

		try {
			dlService.deleteFile(
				fileEntry.getCompanyId(), PortletKeys.DOCUMENT_LIBRARY,
				fileEntry.getFolderId(), fileEntry.getName());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		// File ranks

		dlFileRankLocalService.deleteFileRanks(
			fileEntry.getFolderId(), fileEntry.getName());

		// File shortcuts

		dlFileShortcutLocalService.deleteFileShortcuts(
			fileEntry.getFolderId(), fileEntry.getName());

		// File versions

		Iterator itr = dlFileVersionPersistence.findByF_N(
			fileEntry.getFolderId(), fileEntry.getName()).iterator();

		while (itr.hasNext()) {
			DLFileVersion fileVersion = (DLFileVersion)itr.next();

			dlFileVersionPersistence.remove(fileVersion.getPrimaryKey());
		}

		// Tags

		tagsAssetLocalService.deleteAsset(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		// Resources

		resourceLocalService.deleteResource(
			fileEntry.getCompanyId(), DLFileEntry.class.getName(),
			ResourceImpl.SCOPE_INDIVIDUAL, fileEntry.getFileEntryId());

		// File entry

		dlFileEntryPersistence.remove(fileEntry.getPrimaryKey());
	}

	public InputStream getFileAsStream(
			long companyId, long userId, long folderId, String name)
		throws PortalException, SystemException {

		return getFileAsStream(companyId, userId, folderId, name, 0);
	}

	public InputStream getFileAsStream(
			long companyId, long userId, long folderId, String name,
			double version)
		throws PortalException, SystemException {

		if (userId > 0) {
			DLFolder folder = dlFolderPersistence.findByPrimaryKey(folderId);

			dlFileRankLocalService.updateFileRank(
				folder.getGroupId(), companyId, userId, folderId, name);
		}

		DLFileEntry fileEntry = dlFileEntryPersistence.findByF_N(
			folderId, name);

		fileEntry.setReadCount(fileEntry.getReadCount() + 1);

		dlFileEntryPersistence.update(fileEntry);

		if ((version > 0) && (fileEntry.getVersion() != version)) {
			return dlLocalService.getFileAsStream(
				companyId, folderId, name, version);
		}
		else {
			return dlLocalService.getFileAsStream(companyId, folderId, name);
		}
	}

	public DLFileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return dlFileEntryPersistence.findByPrimaryKey(fileEntryId);
	}

	public DLFileEntry getFileEntry(long folderId, String name)
		throws PortalException, SystemException {

		return dlFileEntryPersistence.findByF_N(folderId, name);
	}

	public List getFileEntries(long folderId) throws SystemException {
		return dlFileEntryPersistence.findByFolderId(folderId);
	}

	public List getFileEntries(long folderId, int begin, int end)
		throws SystemException {

		return dlFileEntryPersistence.findByFolderId(folderId, begin, end);
	}

	public List getFileEntriesAndShortcuts(long folderId, int begin, int end)
		throws SystemException {

		List folderIds = new ArrayList();

		folderIds.add(new Long(folderId));

		return dlFileEntryAndShortcutFinder.findByFolderIds(
			folderIds, begin, end);
	}

	public List getFileEntriesAndShortcuts(List folderIds, int begin, int end)
		throws SystemException {

		return dlFileEntryAndShortcutFinder.findByFolderIds(
			folderIds, begin, end);
	}

	public int getFileEntriesAndShortcutsCount(long folderId)
		throws SystemException {

		List folderIds = new ArrayList();

		folderIds.add(new Long(folderId));

		return dlFileEntryAndShortcutFinder.countByFolderIds(folderIds);
	}

	public int getFileEntriesAndShortcutsCount(List folderIds)
		throws SystemException {

		return dlFileEntryAndShortcutFinder.countByFolderIds(folderIds);
	}

	public int getFileEntriesCount(long folderId) throws SystemException {
		return dlFileEntryPersistence.countByFolderId(folderId);
	}

	public int getFoldersFileEntriesCount(List folderIds)
		throws SystemException {

		return dlFileEntryFinder.countByFolderIds(folderIds);
	}

	public List getGroupFileEntries(long groupId, int begin, int end)
		throws SystemException {

		return dlFileEntryFinder.findByGroupId(groupId, begin, end);
	}

	public List getGroupFileEntries(
			long groupId, long userId, int begin, int end)
		throws SystemException {

		if (userId <= 0) {
			return dlFileEntryFinder.findByGroupId(groupId, begin, end);
		}
		else {
			return dlFileEntryFinder.findByG_U(groupId, userId, begin, end);
		}
	}

	public int getGroupFileEntriesCount(long groupId) throws SystemException {
		return dlFileEntryFinder.countByGroupId(groupId);
	}

	public int getGroupFileEntriesCount(long groupId, long userId)
		throws SystemException {

		if (userId <= 0) {
			return dlFileEntryFinder.countByGroupId(groupId);
		}
		else {
			return dlFileEntryFinder.countByG_U(groupId, userId);
		}
	}

	public List getNoAssetFileEntries() throws SystemException {
		return dlFileEntryFinder.findByNoAssets();
	}

	public DLFileEntry updateFileEntry(
			long userId, long folderId, long newFolderId, String name,
			String sourceFileName, String title, String description,
			String[] tagsEntries, String extraSettings, File file)
		throws PortalException, SystemException {

		InputStream is = null;

		try {
			long size = 0;

			if ((file != null) && (file.length() > 0)) {
				is = new BufferedInputStream(new FileInputStream(file));
				size = file.length();
			}

			return updateFileEntry(
				userId, folderId, newFolderId, name, sourceFileName, title,
				description, tagsEntries, extraSettings, is, size);
		}
		catch (FileNotFoundException fnfe) {
			throw new NoSuchFileException();
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			}
			catch (IOException ioe) {
				_log.error(ioe);
			}
		}
	}

	public DLFileEntry updateFileEntry(
			long userId, long folderId, long newFolderId, String name,
			String sourceFileName, String title, String description,
			String[] tagsEntries, String extraSettings, byte[] byteArray)
		throws PortalException, SystemException {

		InputStream is = null;
		long size = 0;

		if ((byteArray != null) && (byteArray.length > 0)) {
			is = new ByteArrayInputStream(byteArray);
			size = byteArray.length;
		}

		return updateFileEntry(
			userId, folderId, newFolderId, name, sourceFileName, title,
			description, tagsEntries, extraSettings, is, size);
	}

	public DLFileEntry updateFileEntry(
			long userId, long folderId, long newFolderId, String name,
			String sourceFileName, String title, String description,
			String[] tagsEntries, String extraSettings, InputStream is,
			long size)
		throws PortalException, SystemException {

		// File entry

		User user = userPersistence.findByPrimaryKey(userId);
		DLFolder folder = dlFolderPersistence.findByPrimaryKey(folderId);

		if (Validator.isNull(title)) {
			title = name;
		}

		dlLocalService.validate(name, sourceFileName, is);

		DLFileEntry fileEntry = dlFileEntryPersistence.findByF_N(
			folderId, name);

		fileEntry.setTitle(title);
		fileEntry.setDescription(description);
		fileEntry.setExtraSettings(extraSettings);

		dlFileEntryPersistence.update(fileEntry);

		// Move file entry

		if ((newFolderId > 0) && (folderId != newFolderId)) {
			DLFolder newFolder = dlFolderPersistence.findByPrimaryKey(
				newFolderId);

			if (folder.getGroupId() != newFolder.getGroupId()) {
				throw new NoSuchFolderException();
			}

			if (dlLocalService.hasFile(
					user.getCompanyId(), newFolderId, name, 0)) {

				throw new DuplicateFileException(name);
			}

			long newFileEntryId = counterLocalService.increment();

			DLFileEntry newFileEntry = dlFileEntryPersistence.create(
				newFileEntryId);

			newFileEntry.setCompanyId(fileEntry.getCompanyId());
			newFileEntry.setUserId(fileEntry.getUserId());
			newFileEntry.setUserName(fileEntry.getUserName());
			newFileEntry.setVersionUserId(fileEntry.getVersionUserId());
			newFileEntry.setVersionUserName(fileEntry.getVersionUserName());
			newFileEntry.setCreateDate(fileEntry.getCreateDate());
			newFileEntry.setModifiedDate(fileEntry.getModifiedDate());
			newFileEntry.setFolderId(newFolderId);
			newFileEntry.setName(name);
			newFileEntry.setTitle(fileEntry.getTitle());
			newFileEntry.setDescription(fileEntry.getDescription());
			newFileEntry.setVersion(fileEntry.getVersion());
			newFileEntry.setSize(fileEntry.getSize());
			newFileEntry.setReadCount(fileEntry.getReadCount());
			newFileEntry.setExtraSettings(extraSettings);

			dlFileEntryPersistence.update(newFileEntry);

			dlFileEntryPersistence.remove(fileEntry);

			fileEntry = newFileEntry;

			Iterator itr = dlFileVersionPersistence.findByF_N(
				folderId, name).iterator();

			while (itr.hasNext()) {
				DLFileVersion fileVersion = (DLFileVersion)itr.next();

				long newFileVersionId = counterLocalService.increment();

				DLFileVersion newFileVersion = dlFileVersionPersistence.create(
					newFileVersionId);

				newFileVersion.setCompanyId(fileVersion.getCompanyId());
				newFileVersion.setUserId(fileVersion.getUserId());
				newFileVersion.setUserName(fileVersion.getUserName());
				newFileVersion.setCreateDate(fileVersion.getCreateDate());
				newFileVersion.setFolderId(newFolderId);
				newFileVersion.setName(name);
				newFileVersion.setVersion(fileVersion.getVersion());
				newFileVersion.setSize(fileVersion.getSize());

				dlFileVersionPersistence.update(newFileVersion);

				dlFileVersionPersistence.remove(fileVersion);
			}

			dlFileShortcutLocalService.updateFileShortcuts(
				folderId, name, newFolderId, name);

			try {
				dlService.updateFile(
					user.getCompanyId(), PortletKeys.DOCUMENT_LIBRARY,
					folder.getGroupId(), folderId, newFolderId, name);
			}
			catch (RemoteException re) {
				throw new SystemException(re);
			}

			folderId = newFolderId;
			folder = newFolder;
		}

		// Tags

		updateTagsAsset(userId, fileEntry, tagsEntries);

		// File version

		if (is == null) {
			return fileEntry;
		}

		double oldVersion = fileEntry.getVersion();
		double newVersion = MathUtil.format(oldVersion + 0.1, 1, 1);

		long fileVersionId = counterLocalService.increment();

		DLFileVersion fileVersion = dlFileVersionPersistence.create(
			fileVersionId);

		long versionUserId = fileEntry.getVersionUserId();

		if (versionUserId <= 0) {
			versionUserId = fileEntry.getUserId();
		}

		String versionUserName = GetterUtil.getString(
			fileEntry.getVersionUserName(), fileEntry.getUserName());

		fileVersion.setCompanyId(fileEntry.getCompanyId());
		fileVersion.setUserId(versionUserId);
		fileVersion.setUserName(versionUserName);
		fileVersion.setCreateDate(fileEntry.getModifiedDate());
		fileVersion.setFolderId(folderId);
		fileVersion.setName(name);
		fileVersion.setVersion(oldVersion);
		fileVersion.setSize(fileEntry.getSize());

		dlFileVersionPersistence.update(fileVersion);

		// File entry

		fileEntry.setVersionUserId(user.getUserId());
		fileEntry.setVersionUserName(user.getFullName());
		fileEntry.setModifiedDate(new Date());
		fileEntry.setVersion(newVersion);
		fileEntry.setSize((int)size);

		dlFileEntryPersistence.update(fileEntry);

		// File

		dlLocalService.updateFile(
			user.getCompanyId(), PortletKeys.DOCUMENT_LIBRARY,
			folder.getGroupId(), folderId, name, newVersion, sourceFileName,
			fileEntry.getLuceneProperties(), is);

		// Folder

		folder.setLastPostDate(fileEntry.getModifiedDate());

		dlFolderPersistence.update(folder);

		return fileEntry;
	}

	public void updateTagsAsset(
			long userId, DLFileEntry fileEntry, String[] tagsEntries)
		throws PortalException, SystemException {

		String mimeType = MimeTypesUtil.getContentType(fileEntry.getName());

		tagsAssetLocalService.updateAsset(
			userId, fileEntry.getFolder().getGroupId(),
			DLFileEntry.class.getName(), fileEntry.getFileEntryId(),
			tagsEntries, null, null, null, null, mimeType, fileEntry.getTitle(),
			fileEntry.getDescription(), fileEntry.getDescription(), null, 0, 0);
	}

	protected long getFolderId(long companyId, long folderId)
		throws PortalException, SystemException {

		if (folderId != DLFolderImpl.DEFAULT_PARENT_FOLDER_ID) {

			// Ensure folder exists and belongs to the proper company

			try {
				DLFolder folder = dlFolderPersistence.findByPrimaryKey(
					folderId);

				if (companyId != folder.getCompanyId()) {
					folderId = DLFolderImpl.DEFAULT_PARENT_FOLDER_ID;
				}
			}
			catch (NoSuchFolderException nsfe) {
				folderId = DLFolderImpl.DEFAULT_PARENT_FOLDER_ID;
			}
		}

		return folderId;
	}

	protected String getName(String name) throws SystemException {
		String extension = StringPool.BLANK;

		int pos = name.lastIndexOf(StringPool.PERIOD);

		if (pos != -1) {
			extension = name.substring(pos + 1, name.length()).toLowerCase();
		}

		name = String.valueOf(counterLocalService.increment(
			DLFileEntry.class.getName()));

		if (Validator.isNotNull(extension)) {
			name = "DLFE-" + name + StringPool.PERIOD + extension;
		}

		return name;
	}

	private static Log _log =
		LogFactory.getLog(DLFileEntryLocalServiceImpl.class);

}