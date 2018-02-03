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

package com.liferay.bookmarks.social.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.bookmarks.model.BookmarksFolder;
<<<<<<< HEAD
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.social.activity.test.util.BaseSocialActivityInterpreterTestCase;
=======
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.bookmarks.social.BookmarksFolderActivityInterpreter;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.social.test.BaseSocialActivityInterpreterTestCase;
>>>>>>> compatible
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivityInterpreter;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@RunWith(Arquillian.class)
<<<<<<< HEAD
=======
@Sync
>>>>>>> compatible
public class BookmarksFolderActivityInterpreterTest
	extends BaseSocialActivityInterpreterTestCase {

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

	@Override
	protected void addActivities() throws Exception {
		_folder = BookmarksTestUtil.addFolder(group.getGroupId(), "Folder");
	}

	@Override
	protected SocialActivityInterpreter getActivityInterpreter() {
<<<<<<< HEAD
		return _socialActivityInterpreter;
=======
		return new BookmarksFolderActivityInterpreter();
>>>>>>> compatible
	}

	@Override
	protected int[] getActivityTypes() {
		return new int[] {
			SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			SocialActivityConstants.TYPE_RESTORE_FROM_TRASH
		};
	}

	@Override
	protected boolean isSupportsRename(String className) {
		return false;
	}

	@Override
	protected void moveModelsToTrash() throws Exception {
<<<<<<< HEAD
		_bookmarksFolderLocalService.moveFolderToTrash(
=======
		BookmarksFolderLocalServiceUtil.moveFolderToTrash(
>>>>>>> compatible
			TestPropsValues.getUserId(), _folder.getFolderId());
	}

	@Override
	protected void renameModels() {
	}

	@Override
	protected void restoreModelsFromTrash() throws Exception {
<<<<<<< HEAD
		_bookmarksFolderLocalService.restoreFolderFromTrash(
			TestPropsValues.getUserId(), _folder.getFolderId());
	}

	@Inject
	private static BookmarksFolderLocalService _bookmarksFolderLocalService;

	@Inject(
		filter = "model.class.name=com.liferay.bookmarks.model.BookmarksFolder"
	)
	private static SocialActivityInterpreter _socialActivityInterpreter;

=======
		BookmarksFolderLocalServiceUtil.restoreFolderFromTrash(
			TestPropsValues.getUserId(), _folder.getFolderId());
	}

>>>>>>> compatible
	private BookmarksFolder _folder;

}