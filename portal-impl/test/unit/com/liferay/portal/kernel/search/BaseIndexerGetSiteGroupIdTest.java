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

package com.liferay.portal.kernel.search;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.RandomTestUtil;

import java.text.SimpleDateFormat;

import java.util.Locale;

import javax.portlet.PortletURL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Angelo Caldas Gallindo
 * @author André de Oliveira
 */
@PrepareOnlyThisForTest( {
	GroupLocalServiceUtil.class
})
@RunWith(PowerMockRunner.class)
public class BaseIndexerGetSiteGroupIdTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpFastDateFormatFactoryUtil();
		setUpGroupLocalServiceUtil();
		setUpPropsUtil();

		_indexer = new TestIndexer();
	}

	@Test
	public void testGetSiteGroupId() throws Exception {
		long groupId = RandomTestUtil.randomLong();

		setUpGroup(groupId);

		Assert.assertEquals(groupId, _indexer.getSiteGroupId(groupId));
	}

	@Test
	public void testGetSiteGroupIdLayout() throws Exception {
		long groupId = RandomTestUtil.randomLong();
		long parentGroupId = RandomTestUtil.randomLong();

		setUpLayoutGroup(groupId, parentGroupId);

		Assert.assertEquals(parentGroupId, _indexer.getSiteGroupId(groupId));
	}

	@Test
	public void testGetSiteGroupIdNonexistent() throws Exception {
		long groupId = RandomTestUtil.randomLong();

		setUpNonexistentGroup(groupId);

		Assert.assertEquals(groupId, _indexer.getSiteGroupId(groupId));
	}

	@Test
	public void testIsStagingGroup() throws Exception {
		long groupId = RandomTestUtil.randomLong();

		Group group = setUpGroup(groupId);

		Mockito.when(
			group.isStagingGroup()
		).thenReturn(
			true
		);

		Assert.assertEquals(true, _indexer.isStagingGroup(groupId));
	}

	@Test
	public void testIsStagingGroupLayout() throws Exception {
		long groupId = RandomTestUtil.randomLong();
		long parentGroupId = RandomTestUtil.randomLong();

		Group parentGroup = setUpLayoutGroup(groupId, parentGroupId);

		Mockito.when(
			parentGroup.isStagingGroup()
		).thenReturn(
			true
		);

		Assert.assertEquals(true, _indexer.isStagingGroup(groupId));
	}

	@Test
	public void testIsStagingGroupNonexistent() throws Exception {
		long groupId = RandomTestUtil.randomLong();

		setUpNonexistentGroup(groupId);

		Assert.assertEquals(false, _indexer.isStagingGroup(groupId));
	}

	protected void setUpFastDateFormatFactoryUtil() {
		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		FastDateFormatFactory fastDateFormatFactory = Mockito.mock(
			FastDateFormatFactory.class);

		Mockito.when(
			fastDateFormatFactory.getSimpleDateFormat("yyyyMMddHHmmss")
		).thenReturn(
			new SimpleDateFormat("yyyyMMddHHmmss")
		);

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			fastDateFormatFactory);
	}

	protected Group setUpGroup(long groupId) throws Exception {
		Group group = Mockito.mock(Group.class);

		Mockito.when(
			group.getGroupId()
		).thenReturn(
			groupId
		);

		Mockito.when(
			_groupLocalService.getGroup(groupId)
		).thenReturn(
			group
		);

		return group;
	}

	protected void setUpGroupLocalServiceUtil() {
		mockStatic(GroupLocalServiceUtil.class, Mockito.CALLS_REAL_METHODS);

		stub(
			method(GroupLocalServiceUtil.class, "getService")
		).toReturn(
			_groupLocalService
		);
	}

	protected Group setUpLayoutGroup(long groupId, long parentGroupId)
		throws Exception {

		Group group = Mockito.mock(Group.class);

		Group parentGroup = Mockito.mock(Group.class);

		Mockito.when(
			parentGroup.getGroupId()
		).thenReturn(
			parentGroupId
		);

		Mockito.when(
			group.getParentGroup()
		).thenReturn(
			parentGroup
		);

		Mockito.when(
			group.getParentGroupId()
		).thenReturn(
			parentGroupId
		);

		Mockito.when(
			group.isLayout()
		).thenReturn(
			true
		);

		Mockito.when(
			_groupLocalService.getGroup(groupId)
		).thenReturn(
			group
		);

		Mockito.when(
			_groupLocalService.getGroup(parentGroupId)
		).thenReturn(
			parentGroup
		);

		return parentGroup;
	}

	protected void setUpNonexistentGroup(long groupId) throws Exception {
		Mockito.doThrow(
			new NoSuchGroupException()
		).when(
			_groupLocalService
		).getGroup(groupId);
	}

	protected void setUpPropsUtil() {
		Props props = mock(Props.class);

		PropsUtil.setProps(props);
	}

	@Mock
	private GroupLocalService _groupLocalService;

	private BaseIndexer _indexer;

	private static class TestIndexer extends BaseIndexer {

		@Override
		public String[] getClassNames() {
			return null;
		}

		@Override
		public String getPortletId() {
			return null;
		}

		@Override
		protected void doDelete(Object obj) throws Exception {
		}

		@Override
		protected Document doGetDocument(Object obj) throws Exception {
			return null;
		}

		@Override
		protected Summary doGetSummary(
				Document document, Locale locale, String snippet,
				PortletURL portletURL)
			throws Exception {

			return null;
		}

		@Override
		protected void doReindex(Object obj) throws Exception {
		}

		@Override
		protected void doReindex(String className, long classPK)
			throws Exception {
		}

		@Override
		protected void doReindex(String[] ids) throws Exception {
		}

		@Override
		protected String getPortletId(SearchContext searchContext) {
			return null;
		}

	}

}