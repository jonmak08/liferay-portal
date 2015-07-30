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

package com.liferay.portlet.bookmarks.permission;

import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.search.SearchPermissionCheckerImpl;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.RoleTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Preston Crary
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class BookmarksEntryPermissionTest {

	@After
	public void tearDown() throws Exception {
		BookmarksEntryLocalServiceUtil.deleteEntry(_bookmarksEntry1);
		BookmarksEntryLocalServiceUtil.deleteEntry(_bookmarksEntry2);

		RoleLocalServiceUtil.deleteRole(_role1);
		RoleLocalServiceUtil.deleteRole(_role2);

		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_bookmarksEntry1 = BookmarksTestUtil.addEntry(
			_group.getGroupId(), true);
		_bookmarksEntry2 = BookmarksTestUtil.addEntry(
			_group.getGroupId(), true);

		_role1 = RoleTestUtil.addRole(
			RandomTestUtil.randomString(), RoleConstants.TYPE_REGULAR);

		ResourceBlockLocalServiceUtil.addIndividualScopePermission(
			_bookmarksEntry1.getCompanyId(), _bookmarksEntry1.getGroupId(),
			BookmarksEntry.class.getName(), _bookmarksEntry1,
			_role1.getRoleId(), ActionKeys.VIEW);

		ResourceBlockLocalServiceUtil.addIndividualScopePermission(
			_bookmarksEntry2.getCompanyId(), _bookmarksEntry2.getGroupId(),
			BookmarksEntry.class.getName(), _bookmarksEntry2,
			_role1.getRoleId(), ActionKeys.VIEW);

		_role2 = RoleTestUtil.addRole(
			RandomTestUtil.randomString(), RoleConstants.TYPE_REGULAR);

		ResourceBlockLocalServiceUtil.addIndividualScopePermission(
			_bookmarksEntry1.getCompanyId(), _bookmarksEntry1.getGroupId(),
			BookmarksEntry.class.getName(), _bookmarksEntry1,
			_role2.getRoleId(), ActionKeys.VIEW);
	}

	@Test
	public void testGetRolesWithPermission() throws Exception {
		TestDocument testDocument = new TestDocument(
			_bookmarksEntry1.getGroupId(), _bookmarksEntry1.getEntryId());

		_searchPermissionChecker.addPermissionFields(
			TestPropsValues.getCompanyId(), testDocument);

		Assert.assertTrue(
			ArrayUtil.contains(testDocument.getRoleIds(), _role1.getRoleId()));
		Assert.assertTrue(
			ArrayUtil.contains(testDocument.getRoleIds(), _role2.getRoleId()));

		testDocument = new TestDocument(
			_bookmarksEntry2.getGroupId(), _bookmarksEntry2.getEntryId());

		_searchPermissionChecker.addPermissionFields(
			TestPropsValues.getCompanyId(), testDocument);

		Assert.assertTrue(
			ArrayUtil.contains(testDocument.getRoleIds(), _role1.getRoleId()));
		Assert.assertFalse(
			ArrayUtil.contains(testDocument.getRoleIds(), _role2.getRoleId()));
	}

	private class TestDocument extends DocumentImpl {

		public TestDocument(long groupId, long classPK) {
			_fields.put(Field.GROUP_ID, String.valueOf(groupId));

			_fields.put(Field.ENTRY_CLASS_NAME, BookmarksEntry.class.getName());
			_fields.put(Field.ENTRY_CLASS_PK, String.valueOf(classPK));
		}

		@Override
		public void addKeyword(String key, Long[] values) {
			if (key.equals(Field.ROLE_ID)) {
				roleIds = values;
			}
		}

		@Override
		public String get(String key) {
			return _fields.get(key);
		}

		public Long[] getRoleIds() {
			return roleIds;
		}

		private Map<String, String> _fields = new HashMap<String, String>(3);
		private Long[] roleIds;

	}

	private SearchPermissionChecker _searchPermissionChecker =
		new SearchPermissionCheckerImpl();
	private BookmarksEntry _bookmarksEntry1;
	private BookmarksEntry _bookmarksEntry2;
	private Group _group;
	private Role _role1;
	private Role _role2;

}