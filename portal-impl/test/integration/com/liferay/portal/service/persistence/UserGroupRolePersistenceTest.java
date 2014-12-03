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

import com.liferay.portal.NoSuchUserGroupRoleException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.UserGroupRole;
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
public class UserGroupRolePersistenceTest {
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

		for (ModelListener<UserGroupRole> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserGroupRole> iterator = _userGroupRoles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<UserGroupRole> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		UserGroupRolePK pk = new UserGroupRolePK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		UserGroupRole userGroupRole = _persistence.create(pk);

		Assert.assertNotNull(userGroupRole);

		Assert.assertEquals(userGroupRole.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserGroupRole newUserGroupRole = addUserGroupRole();

		_persistence.remove(newUserGroupRole);

		UserGroupRole existingUserGroupRole = _persistence.fetchByPrimaryKey(newUserGroupRole.getPrimaryKey());

		Assert.assertNull(existingUserGroupRole);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserGroupRole();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		UserGroupRolePK pk = new UserGroupRolePK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		UserGroupRole newUserGroupRole = _persistence.create(pk);

		_userGroupRoles.add(_persistence.update(newUserGroupRole));

		UserGroupRole existingUserGroupRole = _persistence.findByPrimaryKey(newUserGroupRole.getPrimaryKey());

		Assert.assertEquals(existingUserGroupRole.getUserId(),
			newUserGroupRole.getUserId());
		Assert.assertEquals(existingUserGroupRole.getGroupId(),
			newUserGroupRole.getGroupId());
		Assert.assertEquals(existingUserGroupRole.getRoleId(),
			newUserGroupRole.getRoleId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserGroupRole newUserGroupRole = addUserGroupRole();

		UserGroupRole existingUserGroupRole = _persistence.findByPrimaryKey(newUserGroupRole.getPrimaryKey());

		Assert.assertEquals(existingUserGroupRole, newUserGroupRole);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		UserGroupRolePK pk = new UserGroupRolePK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchUserGroupRoleException");
		}
		catch (NoSuchUserGroupRoleException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserGroupRole newUserGroupRole = addUserGroupRole();

		UserGroupRole existingUserGroupRole = _persistence.fetchByPrimaryKey(newUserGroupRole.getPrimaryKey());

		Assert.assertEquals(existingUserGroupRole, newUserGroupRole);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		UserGroupRolePK pk = new UserGroupRolePK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		UserGroupRole missingUserGroupRole = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserGroupRole);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new UserGroupRoleActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					UserGroupRole userGroupRole = (UserGroupRole)object;

					Assert.assertNotNull(userGroupRole);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UserGroupRole newUserGroupRole = addUserGroupRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserGroupRole.class,
				UserGroupRole.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newUserGroupRole.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.groupId",
				newUserGroupRole.getGroupId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.roleId",
				newUserGroupRole.getRoleId()));

		List<UserGroupRole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserGroupRole existingUserGroupRole = result.get(0);

		Assert.assertEquals(existingUserGroupRole, newUserGroupRole);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserGroupRole.class,
				UserGroupRole.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				ServiceTestUtil.nextLong()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.groupId",
				ServiceTestUtil.nextLong()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.roleId",
				ServiceTestUtil.nextLong()));

		List<UserGroupRole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UserGroupRole newUserGroupRole = addUserGroupRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserGroupRole.class,
				UserGroupRole.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newUserGroupRole.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserGroupRole.class,
				UserGroupRole.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UserGroupRole addUserGroupRole() throws Exception {
		UserGroupRolePK pk = new UserGroupRolePK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		UserGroupRole userGroupRole = _persistence.create(pk);

		_userGroupRoles.add(_persistence.update(userGroupRole));

		return userGroupRole;
	}

	private static Log _log = LogFactoryUtil.getLog(UserGroupRolePersistenceTest.class);
	private List<UserGroupRole> _userGroupRoles = new ArrayList<UserGroupRole>();
	private ModelListener<UserGroupRole>[] _listeners;
	private UserGroupRolePersistence _persistence = (UserGroupRolePersistence)PortalBeanLocatorUtil.locate(UserGroupRolePersistence.class.getName());
}