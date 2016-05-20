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

package com.liferay.portlet.messageboards.service;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.util.MBTestUtil;
import com.liferay.portlet.trash.util.TrashUtil;

import java.io.InputStream;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jonathan McCann
 * @author Sergio González
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MBMessageLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Test
	public void testAddXSSMessageWithInvalidFormat() throws Exception {
		String subject = "<script>alert(1)</script>";
		String body = "<script>alert(2)</script>";
		String format = "text/plain";
		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		MBMessage message = MBMessageLocalServiceUtil.addMessage(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			_group.getGroupId(), MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
			subject, body, format, inputStreamOVPs, false, 0.0, false,
			serviceContext);

		Assert.assertEquals(subject, message.getSubject());
		Assert.assertEquals(StringPool.BLANK, message.getBody());
		Assert.assertEquals("html", message.getFormat());
	}

	@Test
	public void testDeleteAttachmentsWhenUpdatingMessageAndTrashDisabled()
		throws Exception {

		TrashUtil.disableTrash(_group);

		ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

		User user = TestPropsValues.getUser();
		List<ObjectValuePair<String, InputStream>> objectValuePairs =
			MBTestUtil.getInputStreamOVPs(
				"attachment.txt", getClass(), StringPool.BLANK);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		MBMessage message = MBMessageLocalServiceUtil.addMessage(
			user.getUserId(), user.getFullName(), _group.getGroupId(),
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			MBMessageConstants.DEFAULT_FORMAT, objectValuePairs, false, 0,
			false, serviceContext);

		MBMessageLocalServiceUtil.updateMessage(
			user.getUserId(), message.getMessageId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			Collections.<ObjectValuePair<String, InputStream>>emptyList(),
			Collections.<String>emptyList(), 0, false, serviceContext);

		Assert.assertEquals(
			0,
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				message.getGroupId(), message.getAttachmentsFolderId()));

		MBTestUtil.addMessage(_group.getGroupId());
	}

	@Test
	public void testDeleteAttachmentsWhenUpdatingMessageAndTrashEnabled()
		throws Exception {

		User user = TestPropsValues.getUser();
		List<ObjectValuePair<String, InputStream>> objectValuePairs =
			MBTestUtil.getInputStreamOVPs(
				"attachment.txt", getClass(), StringPool.BLANK);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		MBMessage message = MBMessageLocalServiceUtil.addMessage(
			user.getUserId(), user.getFullName(), _group.getGroupId(),
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			MBMessageConstants.DEFAULT_FORMAT, objectValuePairs, false, 0,
			false, serviceContext);

		MBMessageLocalServiceUtil.updateMessage(
			user.getUserId(), message.getMessageId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			Collections.<ObjectValuePair<String, InputStream>>emptyList(),
			Collections.<String>emptyList(), 0, false, serviceContext);

		List<FileEntry> fileEntries =
			PortletFileRepositoryUtil.getPortletFileEntries(
				message.getGroupId(), message.getAttachmentsFolderId());

		Assert.assertEquals(1, fileEntries.size());

		FileEntry fileEntry = fileEntries.get(0);

		DLFileEntry dlFileEntry = ((DLFileEntry)fileEntry.getModel());

		Assert.assertEquals(
			WorkflowConstants.STATUS_IN_TRASH, dlFileEntry.getStatus());
	}

	@Test
	public void testGetNoAssetMessages() throws Exception {
		MBTestUtil.addMessage(_group.getGroupId());

		MBMessage message = MBTestUtil.addMessage(_group.getGroupId());

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			MBMessage.class.getName(), message.getMessageId());

		Assert.assertNotNull(assetEntry);

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		List<MBMessage> messages =
			MBMessageLocalServiceUtil.getNoAssetMessages();

		Assert.assertEquals(1, messages.size());
		Assert.assertEquals(message, messages.get(0));
	}

	private Group _group;

}