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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.GroupModelImpl;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class GroupPersistenceTest {
	@BeforeClass
	public static void setUpClass() {
		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = false;
	}

	@AfterClass
	public static void tearDownClass() {
		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = true;
	}

	@Before
	public void setUp() {
		_listeners = _persistence.getListeners();

		for (ModelListener<Group> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Group> iterator = _groups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<Group> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Group group = _persistence.create(pk);

		Assert.assertNotNull(group);

		Assert.assertEquals(group.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Group newGroup = addGroup();

		_persistence.remove(newGroup);

		Group existingGroup = _persistence.fetchByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertNull(existingGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Group newGroup = _persistence.create(pk);

		newGroup.setUuid(ServiceTestUtil.randomString());

		newGroup.setCompanyId(ServiceTestUtil.nextLong());

		newGroup.setCreatorUserId(ServiceTestUtil.nextLong());

		newGroup.setClassNameId(ServiceTestUtil.nextLong());

		newGroup.setClassPK(ServiceTestUtil.nextLong());

		newGroup.setParentGroupId(ServiceTestUtil.nextLong());

		newGroup.setLiveGroupId(ServiceTestUtil.nextLong());

		newGroup.setTreePath(ServiceTestUtil.randomString());

		newGroup.setName(ServiceTestUtil.randomString());

		newGroup.setDescription(ServiceTestUtil.randomString());

		newGroup.setType(ServiceTestUtil.nextInt());

		newGroup.setTypeSettings(ServiceTestUtil.randomString());

		newGroup.setManualMembership(ServiceTestUtil.randomBoolean());

		newGroup.setMembershipRestriction(ServiceTestUtil.nextInt());

		newGroup.setFriendlyURL(ServiceTestUtil.randomString());

		newGroup.setSite(ServiceTestUtil.randomBoolean());

		newGroup.setRemoteStagingGroupCount(ServiceTestUtil.nextInt());

		newGroup.setActive(ServiceTestUtil.randomBoolean());

		_groups.add(_persistence.update(newGroup));

		Group existingGroup = _persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup.getUuid(), newGroup.getUuid());
		Assert.assertEquals(existingGroup.getGroupId(), newGroup.getGroupId());
		Assert.assertEquals(existingGroup.getCompanyId(),
			newGroup.getCompanyId());
		Assert.assertEquals(existingGroup.getCreatorUserId(),
			newGroup.getCreatorUserId());
		Assert.assertEquals(existingGroup.getClassNameId(),
			newGroup.getClassNameId());
		Assert.assertEquals(existingGroup.getClassPK(), newGroup.getClassPK());
		Assert.assertEquals(existingGroup.getParentGroupId(),
			newGroup.getParentGroupId());
		Assert.assertEquals(existingGroup.getLiveGroupId(),
			newGroup.getLiveGroupId());
		Assert.assertEquals(existingGroup.getTreePath(), newGroup.getTreePath());
		Assert.assertEquals(existingGroup.getName(), newGroup.getName());
		Assert.assertEquals(existingGroup.getDescription(),
			newGroup.getDescription());
		Assert.assertEquals(existingGroup.getType(), newGroup.getType());
		Assert.assertEquals(existingGroup.getTypeSettings(),
			newGroup.getTypeSettings());
		Assert.assertEquals(existingGroup.getManualMembership(),
			newGroup.getManualMembership());
		Assert.assertEquals(existingGroup.getMembershipRestriction(),
			newGroup.getMembershipRestriction());
		Assert.assertEquals(existingGroup.getFriendlyURL(),
			newGroup.getFriendlyURL());
		Assert.assertEquals(existingGroup.getSite(), newGroup.getSite());
		Assert.assertEquals(existingGroup.getRemoteStagingGroupCount(),
			newGroup.getRemoteStagingGroupCount());
		Assert.assertEquals(existingGroup.getActive(), newGroup.getActive());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Group newGroup = addGroup();

		Group existingGroup = _persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchGroupException");
		}
		catch (NoSuchGroupException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Group_", "uuid", true,
			"groupId", true, "companyId", true, "creatorUserId", true,
			"classNameId", true, "classPK", true, "parentGroupId", true,
			"liveGroupId", true, "treePath", true, "name", true, "description",
			true, "type", true, "typeSettings", true, "manualMembership", true,
			"membershipRestriction", true, "friendlyURL", true, "site", true,
			"remoteStagingGroupCount", true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Group newGroup = addGroup();

		Group existingGroup = _persistence.fetchByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Group missingGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGroup);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new GroupActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					Group group = (Group)object;

					Assert.assertNotNull(group);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Group newGroup = addGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				Group.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId",
				newGroup.getGroupId()));

		List<Group> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Group existingGroup = result.get(0);

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				Group.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId",
				ServiceTestUtil.nextLong()));

		List<Group> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Group newGroup = addGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				Group.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("groupId"));

		Object newGroupId = newGroup.getGroupId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("groupId",
				new Object[] { newGroupId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGroupId = result.get(0);

		Assert.assertEquals(existingGroupId, newGroupId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				Group.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("groupId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("groupId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		Group newGroup = addGroup();

		_persistence.clearCache();

		GroupModelImpl existingGroupModelImpl = (GroupModelImpl)_persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertTrue(Validator.equals(existingGroupModelImpl.getUuid(),
				existingGroupModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingGroupModelImpl.getGroupId(),
			existingGroupModelImpl.getOriginalGroupId());

		Assert.assertEquals(existingGroupModelImpl.getLiveGroupId(),
			existingGroupModelImpl.getOriginalLiveGroupId());

		Assert.assertEquals(existingGroupModelImpl.getCompanyId(),
			existingGroupModelImpl.getOriginalCompanyId());
		Assert.assertTrue(Validator.equals(existingGroupModelImpl.getName(),
				existingGroupModelImpl.getOriginalName()));

		Assert.assertEquals(existingGroupModelImpl.getCompanyId(),
			existingGroupModelImpl.getOriginalCompanyId());
		Assert.assertTrue(Validator.equals(
				existingGroupModelImpl.getFriendlyURL(),
				existingGroupModelImpl.getOriginalFriendlyURL()));

		Assert.assertEquals(existingGroupModelImpl.getCompanyId(),
			existingGroupModelImpl.getOriginalCompanyId());
		Assert.assertEquals(existingGroupModelImpl.getClassNameId(),
			existingGroupModelImpl.getOriginalClassNameId());
		Assert.assertEquals(existingGroupModelImpl.getClassPK(),
			existingGroupModelImpl.getOriginalClassPK());

		Assert.assertEquals(existingGroupModelImpl.getCompanyId(),
			existingGroupModelImpl.getOriginalCompanyId());
		Assert.assertEquals(existingGroupModelImpl.getLiveGroupId(),
			existingGroupModelImpl.getOriginalLiveGroupId());
		Assert.assertTrue(Validator.equals(existingGroupModelImpl.getName(),
				existingGroupModelImpl.getOriginalName()));

		Assert.assertEquals(existingGroupModelImpl.getCompanyId(),
			existingGroupModelImpl.getOriginalCompanyId());
		Assert.assertEquals(existingGroupModelImpl.getClassNameId(),
			existingGroupModelImpl.getOriginalClassNameId());
		Assert.assertEquals(existingGroupModelImpl.getLiveGroupId(),
			existingGroupModelImpl.getOriginalLiveGroupId());
		Assert.assertTrue(Validator.equals(existingGroupModelImpl.getName(),
				existingGroupModelImpl.getOriginalName()));
	}

	protected Group addGroup() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Group group = _persistence.create(pk);

		group.setUuid(ServiceTestUtil.randomString());

		group.setCompanyId(ServiceTestUtil.nextLong());

		group.setCreatorUserId(ServiceTestUtil.nextLong());

		group.setClassNameId(ServiceTestUtil.nextLong());

		group.setClassPK(ServiceTestUtil.nextLong());

		group.setParentGroupId(ServiceTestUtil.nextLong());

		group.setLiveGroupId(ServiceTestUtil.nextLong());

		group.setTreePath(ServiceTestUtil.randomString());

		group.setName(ServiceTestUtil.randomString());

		group.setDescription(ServiceTestUtil.randomString());

		group.setType(ServiceTestUtil.nextInt());

		group.setTypeSettings(ServiceTestUtil.randomString());

		group.setManualMembership(ServiceTestUtil.randomBoolean());

		group.setMembershipRestriction(ServiceTestUtil.nextInt());

		group.setFriendlyURL(ServiceTestUtil.randomString());

		group.setSite(ServiceTestUtil.randomBoolean());

		group.setRemoteStagingGroupCount(ServiceTestUtil.nextInt());

		group.setActive(ServiceTestUtil.randomBoolean());

		_groups.add(_persistence.update(group));

		return group;
	}

	private static Log _log = LogFactoryUtil.getLog(GroupPersistenceTest.class);
	private List<Group> _groups = new ArrayList<Group>();
	private ModelListener<Group>[] _listeners;
	private GroupPersistence _persistence = (GroupPersistence)PortalBeanLocatorUtil.locate(GroupPersistence.class.getName());
}