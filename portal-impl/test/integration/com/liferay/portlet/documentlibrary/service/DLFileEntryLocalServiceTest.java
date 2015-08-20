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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.impl.DLFileEntryLocalServiceImpl;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Michael C. Han
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class DLFileEntryLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Test
	public void testDeleteFileEntries() throws Exception {

		ServiceContext serviceContext =
				ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		Folder folder = DLAppTestUtil.addFolder(
						_group.getGroupId(),
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
						RandomTestUtil.randomString());

		for (int i = 0; i < 20; i++) {
			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
					TestPropsValues.getUserId(), _group.getGroupId(),
					folder.getFolderId(), RandomTestUtil.randomString(),
					ContentTypes.TEXT_PLAIN, RandomTestUtil.randomString(),
					StringPool.BLANK, StringPool.BLANK,
					RandomTestUtil.randomBytes(), serviceContext);

			DLAppLocalServiceUtil.moveFileEntryToTrash(
				TestPropsValues.getUserId(), fileEntry.getFileEntryId());
		}

		for (int i = 0; i < DLFileEntryLocalServiceImpl.DELETE_INTERVAL; i++) {
			DLAppLocalServiceUtil.addFileEntry(
					TestPropsValues.getUserId(), _group.getGroupId(),
					folder.getFolderId(), RandomTestUtil.randomString(),
					ContentTypes.TEXT_PLAIN, RandomTestUtil.randomString(),
					StringPool.BLANK, StringPool.BLANK,
					RandomTestUtil.randomBytes(), serviceContext);
		}

		DLFileEntryLocalServiceUtil.deleteFileEntries(
			_group.getGroupId(), folder.getFolderId(), false);

		int fileEntriesCount = DLFileEntryLocalServiceUtil.getFileEntriesCount(
			_group.getGroupId(), folder.getFolderId());

		Assert.assertEquals(20, fileEntriesCount);
	}

	@Test
	public void testGetMisversionedFileEntries() throws Exception {
		byte[] bytes = RandomTestUtil.randomBytes();

		InputStream is = new ByteArrayInputStream(bytes);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			is, bytes.length, serviceContext);

		DLFileEntry dlFileEntry = null;

		DLFileVersion dlFileVersion = null;

		try {
			dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
				fileEntry.getFileEntryId());

			dlFileVersion = dlFileEntry.getFileVersion();

			dlFileVersion.setFileEntryId(12345l);

			DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);

			List<DLFileEntry> dlFileEntries =
				DLFileEntryLocalServiceUtil.getMisversionedFileEntries();

			Assert.assertEquals(1, dlFileEntries.size());
			Assert.assertEquals(dlFileEntry, dlFileEntries.get(0));
		}
		finally {
			if (dlFileEntry != null) {
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(
					dlFileEntry.getFileEntryId());
			}

			if (dlFileVersion != null) {
				DLFileVersionLocalServiceUtil.deleteDLFileVersion(
					dlFileVersion.getFileVersionId());
			}
		}
	}

	@Test
	public void testGetNoAssetEntries() throws Exception {
		byte[] bytes = RandomTestUtil.randomBytes();

		InputStream is = new ByteArrayInputStream(bytes);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			is, bytes.length, serviceContext);

		is = new ByteArrayInputStream(bytes);

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			is, bytes.length, serviceContext);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		Assert.assertNotNull(assetEntry);

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		List<DLFileEntry> dlFileEntries =
			DLFileEntryLocalServiceUtil.getNoAssetFileEntries();

		Assert.assertEquals(1, dlFileEntries.size());

		DLFileEntry dlFileEntry = dlFileEntries.get(0);

		Assert.assertEquals(
			fileEntry.getFileEntryId(), dlFileEntry.getFileEntryId());
	}

	@Test
	public void testGetOrphanedFileEntries() throws Exception {
		byte[] bytes = RandomTestUtil.randomBytes();

		InputStream is = new ByteArrayInputStream(bytes);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			is, bytes.length, serviceContext);

		boolean indexReadOnly = SearchEngineUtil.isIndexReadOnly();

		DLFileEntry dlFileEntry = null;

		try {
			SearchEngineUtil.setIndexReadOnly(true);

			dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
				fileEntry.getFileEntryId());

			dlFileEntry.setGroupId(10000l);

			DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);

			List<DLFileEntry> dlFileEntries =
				DLFileEntryLocalServiceUtil.getOrphanedFileEntries();

			Assert.assertEquals(1, dlFileEntries.size());
			Assert.assertEquals(dlFileEntry, dlFileEntries.get(0));
		}
		finally {
			if (dlFileEntry != null) {
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(
					dlFileEntry.getFileEntryId());
			}

			SearchEngineUtil.setIndexReadOnly(indexReadOnly);
		}
	}

	private Group _group;

}