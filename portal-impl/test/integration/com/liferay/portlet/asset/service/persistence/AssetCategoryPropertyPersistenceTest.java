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

package com.liferay.portlet.asset.service.persistence;

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

import com.liferay.portlet.asset.NoSuchCategoryPropertyException;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl;

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
public class AssetCategoryPropertyPersistenceTest {
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

		for (ModelListener<AssetCategoryProperty> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetCategoryProperty> iterator = _assetCategoryProperties.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<AssetCategoryProperty> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetCategoryProperty assetCategoryProperty = _persistence.create(pk);

		Assert.assertNotNull(assetCategoryProperty);

		Assert.assertEquals(assetCategoryProperty.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		_persistence.remove(newAssetCategoryProperty);

		AssetCategoryProperty existingAssetCategoryProperty = _persistence.fetchByPrimaryKey(newAssetCategoryProperty.getPrimaryKey());

		Assert.assertNull(existingAssetCategoryProperty);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetCategoryProperty();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetCategoryProperty newAssetCategoryProperty = _persistence.create(pk);

		newAssetCategoryProperty.setCompanyId(ServiceTestUtil.nextLong());

		newAssetCategoryProperty.setUserId(ServiceTestUtil.nextLong());

		newAssetCategoryProperty.setUserName(ServiceTestUtil.randomString());

		newAssetCategoryProperty.setCreateDate(ServiceTestUtil.nextDate());

		newAssetCategoryProperty.setModifiedDate(ServiceTestUtil.nextDate());

		newAssetCategoryProperty.setCategoryId(ServiceTestUtil.nextLong());

		newAssetCategoryProperty.setKey(ServiceTestUtil.randomString());

		newAssetCategoryProperty.setValue(ServiceTestUtil.randomString());

		_assetCategoryProperties.add(_persistence.update(
				newAssetCategoryProperty));

		AssetCategoryProperty existingAssetCategoryProperty = _persistence.findByPrimaryKey(newAssetCategoryProperty.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryProperty.getCategoryPropertyId(),
			newAssetCategoryProperty.getCategoryPropertyId());
		Assert.assertEquals(existingAssetCategoryProperty.getCompanyId(),
			newAssetCategoryProperty.getCompanyId());
		Assert.assertEquals(existingAssetCategoryProperty.getUserId(),
			newAssetCategoryProperty.getUserId());
		Assert.assertEquals(existingAssetCategoryProperty.getUserName(),
			newAssetCategoryProperty.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategoryProperty.getCreateDate()),
			Time.getShortTimestamp(newAssetCategoryProperty.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategoryProperty.getModifiedDate()),
			Time.getShortTimestamp(newAssetCategoryProperty.getModifiedDate()));
		Assert.assertEquals(existingAssetCategoryProperty.getCategoryId(),
			newAssetCategoryProperty.getCategoryId());
		Assert.assertEquals(existingAssetCategoryProperty.getKey(),
			newAssetCategoryProperty.getKey());
		Assert.assertEquals(existingAssetCategoryProperty.getValue(),
			newAssetCategoryProperty.getValue());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		AssetCategoryProperty existingAssetCategoryProperty = _persistence.findByPrimaryKey(newAssetCategoryProperty.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryProperty,
			newAssetCategoryProperty);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchCategoryPropertyException");
		}
		catch (NoSuchCategoryPropertyException nsee) {
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
		return OrderByComparatorFactoryUtil.create("AssetCategoryProperty",
			"categoryPropertyId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"categoryId", true, "key", true, "value", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		AssetCategoryProperty existingAssetCategoryProperty = _persistence.fetchByPrimaryKey(newAssetCategoryProperty.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryProperty,
			newAssetCategoryProperty);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetCategoryProperty missingAssetCategoryProperty = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetCategoryProperty);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new AssetCategoryPropertyActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					AssetCategoryProperty assetCategoryProperty = (AssetCategoryProperty)object;

					Assert.assertNotNull(assetCategoryProperty);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryProperty.class,
				AssetCategoryProperty.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryPropertyId",
				newAssetCategoryProperty.getCategoryPropertyId()));

		List<AssetCategoryProperty> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetCategoryProperty existingAssetCategoryProperty = result.get(0);

		Assert.assertEquals(existingAssetCategoryProperty,
			newAssetCategoryProperty);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryProperty.class,
				AssetCategoryProperty.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryPropertyId",
				ServiceTestUtil.nextLong()));

		List<AssetCategoryProperty> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryProperty.class,
				AssetCategoryProperty.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"categoryPropertyId"));

		Object newCategoryPropertyId = newAssetCategoryProperty.getCategoryPropertyId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryPropertyId",
				new Object[] { newCategoryPropertyId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCategoryPropertyId = result.get(0);

		Assert.assertEquals(existingCategoryPropertyId, newCategoryPropertyId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryProperty.class,
				AssetCategoryProperty.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"categoryPropertyId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryPropertyId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		AssetCategoryProperty newAssetCategoryProperty = addAssetCategoryProperty();

		_persistence.clearCache();

		AssetCategoryPropertyModelImpl existingAssetCategoryPropertyModelImpl = (AssetCategoryPropertyModelImpl)_persistence.findByPrimaryKey(newAssetCategoryProperty.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryPropertyModelImpl.getCategoryId(),
			existingAssetCategoryPropertyModelImpl.getOriginalCategoryId());
		Assert.assertTrue(Validator.equals(
				existingAssetCategoryPropertyModelImpl.getKey(),
				existingAssetCategoryPropertyModelImpl.getOriginalKey()));
	}

	protected AssetCategoryProperty addAssetCategoryProperty()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetCategoryProperty assetCategoryProperty = _persistence.create(pk);

		assetCategoryProperty.setCompanyId(ServiceTestUtil.nextLong());

		assetCategoryProperty.setUserId(ServiceTestUtil.nextLong());

		assetCategoryProperty.setUserName(ServiceTestUtil.randomString());

		assetCategoryProperty.setCreateDate(ServiceTestUtil.nextDate());

		assetCategoryProperty.setModifiedDate(ServiceTestUtil.nextDate());

		assetCategoryProperty.setCategoryId(ServiceTestUtil.nextLong());

		assetCategoryProperty.setKey(ServiceTestUtil.randomString());

		assetCategoryProperty.setValue(ServiceTestUtil.randomString());

		_assetCategoryProperties.add(_persistence.update(assetCategoryProperty));

		return assetCategoryProperty;
	}

	private static Log _log = LogFactoryUtil.getLog(AssetCategoryPropertyPersistenceTest.class);
	private List<AssetCategoryProperty> _assetCategoryProperties = new ArrayList<AssetCategoryProperty>();
	private ModelListener<AssetCategoryProperty>[] _listeners;
	private AssetCategoryPropertyPersistence _persistence = (AssetCategoryPropertyPersistence)PortalBeanLocatorUtil.locate(AssetCategoryPropertyPersistence.class.getName());
}