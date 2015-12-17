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

package com.liferay.portlet.bookmarks.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.ResourceBlockServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.permission.BookmarksFolderPermission;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 * @author Roberto Díaz
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class BookmarksFolderLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void testAddSubfolderPermission() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		Role role = RoleLocalServiceUtil.addRole(
			TestPropsValues.getUserId(), null, 0, StringUtil.randomString(),
			null, null, RoleConstants.TYPE_SITE, null, serviceContext);

		ResourceBlockServiceUtil.addCompanyScopePermission(
			_group.getGroupId(), _group.getCompanyId(),
			BookmarksFolder.class.getName(), role.getRoleId(),
			ActionKeys.ADD_SUBFOLDER);

		User user = UserTestUtil.addGroupUser(_group, role.getName());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		Assert.assertTrue(
			BookmarksFolderPermission.contains(
				permissionChecker, _group.getGroupId(), folder.getFolderId(),
				ActionKeys.ADD_FOLDER));
	}

	@Test
	public void testMoveFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), ServiceTestUtil.randomString());

		long initialParentFolderId = folder.getParentFolderId();

		BookmarksFolder destinationFolder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), ServiceTestUtil.randomString());

		folder = BookmarksFolderLocalServiceUtil.moveFolder(
			folder.getFolderId(), destinationFolder.getFolderId());

		Assert.assertNotEquals(
			initialParentFolderId, folder.getParentFolderId());
		Assert.assertEquals(
			destinationFolder.getFolderId(), folder.getParentFolderId());
	}

	@Test
	public void testSubscribeFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), ServiceTestUtil.randomString());

		testSubscribeFolder(folder.getFolderId(), folder.getFolderId());
	}

	@Test
	public void testSubscribeRootFolder() throws Exception {
		testSubscribeFolder(
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_group.getGroupId());
	}

	@Test
	public void testUnsubscribeFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), ServiceTestUtil.randomString());

		testUnsubscribeFolder(folder.getFolderId(), folder.getFolderId());
	}

	@Test
	public void testUnsubscribeRootFolder() throws Exception {
		testUnsubscribeFolder(
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_group.getGroupId());
	}

	protected void testSubscribeFolder(
			long folderId, long expectedSubscriptionClassPK)
		throws Exception {

		int initialUserSubscriptionsCount =
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId());

		BookmarksFolderLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		Assert.assertEquals(
			initialUserSubscriptionsCount + 1,
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId()));

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				TestPropsValues.getUserId(), BookmarksFolder.class.getName());

		for (Subscription subscription : subscriptions) {
			if (subscription.getClassName().equals(
					BookmarksFolder.class.getName()) &&
				(subscription.getClassPK() == expectedSubscriptionClassPK)) {

				return;
			}
		}

		Assert.fail("Subscription does not exist");
	}

	protected void testUnsubscribeFolder(
			long folderId, long expectedSubscriptionClassPK)
		throws Exception {

		int initialUserSubscriptionsCount =
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId());

		BookmarksFolderLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		BookmarksFolderLocalServiceUtil.unsubscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		Assert.assertEquals(
			initialUserSubscriptionsCount,
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId()));

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				TestPropsValues.getUserId(), BookmarksFolder.class.getName());

		for (Subscription subscription : subscriptions) {
			if (subscription.getClassName().equals(
					BookmarksFolder.class.getName()) &&
				(subscription.getClassPK() == expectedSubscriptionClassPK)) {

				Assert.fail("Subscription exists");
			}
		}
	}

	private Group _group;

}