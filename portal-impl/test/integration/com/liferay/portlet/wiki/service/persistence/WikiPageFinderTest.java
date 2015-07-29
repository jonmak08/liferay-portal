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

package com.liferay.portlet.wiki.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;
import com.liferay.portlet.wiki.util.WikiTestUtil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Andrew Betts
 */
@ExecutionTestListeners(
		listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class WikiPageFinderTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addGroupUser(_group, RoleConstants.USER);

		_node = WikiTestUtil.addNode(
			TestPropsValues.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(50));

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			RandomTestUtil.randomString(), true);

		WikiTestUtil.addPage(
			_user.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"test approved", true);

		WikiPage userDraft = WikiTestUtil.addPage(
			_user.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"test draft", false);

		userDraft.setHead(true);

		WikiPageLocalServiceUtil.updateWikiPage(userDraft);
	}
	
	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group);
		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testFindByG_N_H_SApprovedStatus() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		int count = WikiPageServiceUtil.getPagesCount(
			_group.getGroupId(), _node.getNodeId(), true, _user.getUserId(),
			false, WorkflowConstants.STATUS_APPROVED);

		List<WikiPage> pages = WikiPageServiceUtil.getPages(
			_group.getGroupId(), _node.getNodeId(), true, _user.getUserId(),
			false, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, count);
		Assert.assertEquals(count, pages.size());
	}

	@Test
	public void testFindByG_N_H_SApprovedStatusIncludeOwner() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		int count = WikiPageServiceUtil.getPagesCount(
			_group.getGroupId(), _node.getNodeId(), true,
			TestPropsValues.getUserId(), true,
			WorkflowConstants.STATUS_APPROVED);

		List<WikiPage> pages = WikiPageServiceUtil.getPages(
			_group.getGroupId(), _node.getNodeId(), true,
			TestPropsValues.getUserId(), true,
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, count);
		Assert.assertEquals(count, pages.size());
	}

	@Test
	public void testFindByG_N_H_SDraftStatusIncludeOwner() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		int count = WikiPageServiceUtil.getPagesCount(
			_group.getGroupId(), _node.getNodeId(), true, _user.getUserId(),
			true, WorkflowConstants.STATUS_DRAFT);

		List<WikiPage> pages = WikiPageServiceUtil.getPages(
			_group.getGroupId(), _node.getNodeId(), true, _user.getUserId(),
			true, WorkflowConstants.STATUS_DRAFT, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, count);
		Assert.assertEquals(count, pages.size());
	}

	private Group _group;
	private WikiNode _node;
	private User _user;

}