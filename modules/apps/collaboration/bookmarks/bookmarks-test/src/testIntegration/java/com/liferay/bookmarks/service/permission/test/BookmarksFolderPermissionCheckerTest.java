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

package com.liferay.bookmarks.service.permission.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
<<<<<<< HEAD
import com.liferay.bookmarks.constants.BookmarksConstants;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
=======
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.permission.BookmarksFolderPermissionChecker;
import com.liferay.bookmarks.service.permission.BookmarksResourcePermissionChecker;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
>>>>>>> compatible
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.permission.test.BasePermissionTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
<<<<<<< HEAD
import com.liferay.portal.test.rule.Inject;
=======
>>>>>>> compatible
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eric Chin
 * @author Shinn Lok
 */
@RunWith(Arquillian.class)
<<<<<<< HEAD
=======
@Sync
>>>>>>> compatible
public class BookmarksFolderPermissionCheckerTest
	extends BasePermissionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
<<<<<<< HEAD
		new LiferayIntegrationTestRule();
=======
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);
>>>>>>> compatible

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Test
	public void testContains() throws Exception {
		Assert.assertTrue(
<<<<<<< HEAD
			_bookmarksFolderModelResourcePermission.contains(
				permissionChecker, _folder, ActionKeys.VIEW));
		Assert.assertTrue(
			_bookmarksFolderModelResourcePermission.contains(
=======
			BookmarksFolderPermissionChecker.contains(
				permissionChecker, _folder, ActionKeys.VIEW));
		Assert.assertTrue(
			BookmarksFolderPermissionChecker.contains(
>>>>>>> compatible
				permissionChecker, _subfolder, ActionKeys.VIEW));

		removePortletModelViewPermission();

		Assert.assertFalse(
<<<<<<< HEAD
			_bookmarksFolderModelResourcePermission.contains(
				permissionChecker, _folder, ActionKeys.VIEW));
		Assert.assertFalse(
			_bookmarksFolderModelResourcePermission.contains(
=======
			BookmarksFolderPermissionChecker.contains(
				permissionChecker, _folder, ActionKeys.VIEW));
		Assert.assertFalse(
			BookmarksFolderPermissionChecker.contains(
>>>>>>> compatible
				permissionChecker, _subfolder, ActionKeys.VIEW));
	}

	@Override
	protected void doSetUp() throws Exception {
		_folder = BookmarksTestUtil.addFolder(
			group.getGroupId(), RandomTestUtil.randomString());

		_subfolder = BookmarksTestUtil.addFolder(
			_folder.getFolderId(), RandomTestUtil.randomString(),
			serviceContext);
	}

	@Override
	protected String getResourceName() {
<<<<<<< HEAD
		return BookmarksConstants.RESOURCE_NAME;
	}

	@Inject(
		filter = "model.class.name=com.liferay.bookmarks.model.BookmarksFolder"
	)
	private static ModelResourcePermission<BookmarksFolder>
		_bookmarksFolderModelResourcePermission;

=======
		return BookmarksResourcePermissionChecker.RESOURCE_NAME;
	}

>>>>>>> compatible
	private BookmarksFolder _folder;
	private BookmarksFolder _subfolder;

}