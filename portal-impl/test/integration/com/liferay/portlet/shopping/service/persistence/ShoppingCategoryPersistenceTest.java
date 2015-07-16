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

package com.liferay.portlet.shopping.service.persistence;

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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.shopping.NoSuchCategoryException;
import com.liferay.portlet.shopping.model.ShoppingCategory;
import com.liferay.portlet.shopping.model.impl.ShoppingCategoryModelImpl;

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
public class ShoppingCategoryPersistenceTest {
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

		for (ModelListener<ShoppingCategory> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ShoppingCategory> iterator = _shoppingCategories.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<ShoppingCategory> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingCategory shoppingCategory = _persistence.create(pk);

		Assert.assertNotNull(shoppingCategory);

		Assert.assertEquals(shoppingCategory.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ShoppingCategory newShoppingCategory = addShoppingCategory();

		_persistence.remove(newShoppingCategory);

		ShoppingCategory existingShoppingCategory = _persistence.fetchByPrimaryKey(newShoppingCategory.getPrimaryKey());

		Assert.assertNull(existingShoppingCategory);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addShoppingCategory();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingCategory newShoppingCategory = _persistence.create(pk);

		newShoppingCategory.setGroupId(ServiceTestUtil.nextLong());

		newShoppingCategory.setCompanyId(ServiceTestUtil.nextLong());

		newShoppingCategory.setUserId(ServiceTestUtil.nextLong());

		newShoppingCategory.setUserName(ServiceTestUtil.randomString());

		newShoppingCategory.setCreateDate(ServiceTestUtil.nextDate());

		newShoppingCategory.setModifiedDate(ServiceTestUtil.nextDate());

		newShoppingCategory.setParentCategoryId(ServiceTestUtil.nextLong());

		newShoppingCategory.setName(ServiceTestUtil.randomString());

		newShoppingCategory.setDescription(ServiceTestUtil.randomString());

		_shoppingCategories.add(_persistence.update(newShoppingCategory));

		ShoppingCategory existingShoppingCategory = _persistence.findByPrimaryKey(newShoppingCategory.getPrimaryKey());

		Assert.assertEquals(existingShoppingCategory.getCategoryId(),
			newShoppingCategory.getCategoryId());
		Assert.assertEquals(existingShoppingCategory.getGroupId(),
			newShoppingCategory.getGroupId());
		Assert.assertEquals(existingShoppingCategory.getCompanyId(),
			newShoppingCategory.getCompanyId());
		Assert.assertEquals(existingShoppingCategory.getUserId(),
			newShoppingCategory.getUserId());
		Assert.assertEquals(existingShoppingCategory.getUserName(),
			newShoppingCategory.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingShoppingCategory.getCreateDate()),
			Time.getShortTimestamp(newShoppingCategory.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingShoppingCategory.getModifiedDate()),
			Time.getShortTimestamp(newShoppingCategory.getModifiedDate()));
		Assert.assertEquals(existingShoppingCategory.getParentCategoryId(),
			newShoppingCategory.getParentCategoryId());
		Assert.assertEquals(existingShoppingCategory.getName(),
			newShoppingCategory.getName());
		Assert.assertEquals(existingShoppingCategory.getDescription(),
			newShoppingCategory.getDescription());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ShoppingCategory newShoppingCategory = addShoppingCategory();

		ShoppingCategory existingShoppingCategory = _persistence.findByPrimaryKey(newShoppingCategory.getPrimaryKey());

		Assert.assertEquals(existingShoppingCategory, newShoppingCategory);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchCategoryException");
		}
		catch (NoSuchCategoryException nsee) {
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

	@Test
	public void testFilterFindByGroupId() throws Exception {
		try {
			_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ShoppingCategory",
			"categoryId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"parentCategoryId", true, "name", true, "description", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ShoppingCategory newShoppingCategory = addShoppingCategory();

		ShoppingCategory existingShoppingCategory = _persistence.fetchByPrimaryKey(newShoppingCategory.getPrimaryKey());

		Assert.assertEquals(existingShoppingCategory, newShoppingCategory);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingCategory missingShoppingCategory = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingShoppingCategory);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new ShoppingCategoryActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					ShoppingCategory shoppingCategory = (ShoppingCategory)object;

					Assert.assertNotNull(shoppingCategory);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ShoppingCategory newShoppingCategory = addShoppingCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingCategory.class,
				ShoppingCategory.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryId",
				newShoppingCategory.getCategoryId()));

		List<ShoppingCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ShoppingCategory existingShoppingCategory = result.get(0);

		Assert.assertEquals(existingShoppingCategory, newShoppingCategory);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingCategory.class,
				ShoppingCategory.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryId",
				ServiceTestUtil.nextLong()));

		List<ShoppingCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ShoppingCategory newShoppingCategory = addShoppingCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingCategory.class,
				ShoppingCategory.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("categoryId"));

		Object newCategoryId = newShoppingCategory.getCategoryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryId",
				new Object[] { newCategoryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCategoryId = result.get(0);

		Assert.assertEquals(existingCategoryId, newCategoryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingCategory.class,
				ShoppingCategory.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("categoryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		ShoppingCategory newShoppingCategory = addShoppingCategory();

		_persistence.clearCache();

		ShoppingCategoryModelImpl existingShoppingCategoryModelImpl = (ShoppingCategoryModelImpl)_persistence.findByPrimaryKey(newShoppingCategory.getPrimaryKey());

		Assert.assertEquals(existingShoppingCategoryModelImpl.getGroupId(),
			existingShoppingCategoryModelImpl.getOriginalGroupId());
		Assert.assertTrue(Validator.equals(
				existingShoppingCategoryModelImpl.getName(),
				existingShoppingCategoryModelImpl.getOriginalName()));
	}

	protected ShoppingCategory addShoppingCategory() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingCategory shoppingCategory = _persistence.create(pk);

		shoppingCategory.setGroupId(ServiceTestUtil.nextLong());

		shoppingCategory.setCompanyId(ServiceTestUtil.nextLong());

		shoppingCategory.setUserId(ServiceTestUtil.nextLong());

		shoppingCategory.setUserName(ServiceTestUtil.randomString());

		shoppingCategory.setCreateDate(ServiceTestUtil.nextDate());

		shoppingCategory.setModifiedDate(ServiceTestUtil.nextDate());

		shoppingCategory.setParentCategoryId(ServiceTestUtil.nextLong());

		shoppingCategory.setName(ServiceTestUtil.randomString());

		shoppingCategory.setDescription(ServiceTestUtil.randomString());

		_shoppingCategories.add(_persistence.update(shoppingCategory));

		return shoppingCategory;
	}

	private static Log _log = LogFactoryUtil.getLog(ShoppingCategoryPersistenceTest.class);
	private List<ShoppingCategory> _shoppingCategories = new ArrayList<ShoppingCategory>();
	private ModelListener<ShoppingCategory>[] _listeners;
	private ShoppingCategoryPersistence _persistence = (ShoppingCategoryPersistence)PortalBeanLocatorUtil.locate(ShoppingCategoryPersistence.class.getName());
}