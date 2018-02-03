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
import com.liferay.bookmarks.model.BookmarksEntry;
<<<<<<< HEAD
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.service.BookmarksEntryService;
import com.liferay.bookmarks.social.BookmarksActivityKeys;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
=======
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.bookmarks.service.BookmarksEntryServiceUtil;
import com.liferay.bookmarks.social.BookmarksActivityKeys;
import com.liferay.bookmarks.social.BookmarksEntryActivityInterpreter;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
>>>>>>> compatible
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.service.test.ServiceTestUtil;
<<<<<<< HEAD
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.social.activity.test.util.BaseSocialActivityInterpreterTestCase;
=======
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
public class BookmarksEntryActivityInterpreterTest
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
		_entry = BookmarksTestUtil.addEntry(group.getGroupId(), true);
	}

	@Override
	protected SocialActivityInterpreter getActivityInterpreter() {
<<<<<<< HEAD
		return _socialActivityInterpreter;
=======
		return new BookmarksEntryActivityInterpreter();
>>>>>>> compatible
	}

	@Override
	protected int[] getActivityTypes() {
		return new int[] {
			BookmarksActivityKeys.ADD_ENTRY, BookmarksActivityKeys.UPDATE_ENTRY,
			SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			SocialActivityConstants.TYPE_RESTORE_FROM_TRASH
		};
	}

	@Override
	protected void moveModelsToTrash() throws Exception {
<<<<<<< HEAD
		_bookmarksEntryLocalService.moveEntryToTrash(
=======
		BookmarksEntryLocalServiceUtil.moveEntryToTrash(
>>>>>>> compatible
			TestPropsValues.getUserId(), _entry.getEntryId());
	}

	@Override
	protected void renameModels() throws Exception {
		_entry.setName(RandomTestUtil.randomString());

		serviceContext.setCommand(Constants.UPDATE);

<<<<<<< HEAD
		_bookmarksEntryService.updateEntry(
=======
		BookmarksEntryServiceUtil.updateEntry(
>>>>>>> compatible
			_entry.getEntryId(), serviceContext.getScopeGroupId(),
			_entry.getFolderId(), _entry.getName(), _entry.getUrl(),
			_entry.getUrl(), serviceContext);
	}

	@Override
	protected void restoreModelsFromTrash() throws Exception {
<<<<<<< HEAD
		_bookmarksEntryLocalService.restoreEntryFromTrash(
			TestPropsValues.getUserId(), _entry.getEntryId());
	}

	@Inject
	private static BookmarksEntryLocalService _bookmarksEntryLocalService;

	@Inject
	private static BookmarksEntryService _bookmarksEntryService;

	@Inject(
		filter = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry"
	)
	private static SocialActivityInterpreter _socialActivityInterpreter;

=======
		BookmarksEntryLocalServiceUtil.restoreEntryFromTrash(
			TestPropsValues.getUserId(), _entry.getEntryId());
	}

>>>>>>> compatible
	private BookmarksEntry _entry;

}