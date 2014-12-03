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
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.asset.NoSuchTagStatsException;
import com.liferay.portlet.asset.model.AssetTagStats;
import com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl;

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
public class AssetTagStatsPersistenceTest {
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

		for (ModelListener<AssetTagStats> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetTagStats> iterator = _assetTagStatses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<AssetTagStats> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetTagStats assetTagStats = _persistence.create(pk);

		Assert.assertNotNull(assetTagStats);

		Assert.assertEquals(assetTagStats.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetTagStats newAssetTagStats = addAssetTagStats();

		_persistence.remove(newAssetTagStats);

		AssetTagStats existingAssetTagStats = _persistence.fetchByPrimaryKey(newAssetTagStats.getPrimaryKey());

		Assert.assertNull(existingAssetTagStats);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetTagStats();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetTagStats newAssetTagStats = _persistence.create(pk);

		newAssetTagStats.setTagId(ServiceTestUtil.nextLong());

		newAssetTagStats.setClassNameId(ServiceTestUtil.nextLong());

		newAssetTagStats.setAssetCount(ServiceTestUtil.nextInt());

		_assetTagStatses.add(_persistence.update(newAssetTagStats));

		AssetTagStats existingAssetTagStats = _persistence.findByPrimaryKey(newAssetTagStats.getPrimaryKey());

		Assert.assertEquals(existingAssetTagStats.getTagStatsId(),
			newAssetTagStats.getTagStatsId());
		Assert.assertEquals(existingAssetTagStats.getTagId(),
			newAssetTagStats.getTagId());
		Assert.assertEquals(existingAssetTagStats.getClassNameId(),
			newAssetTagStats.getClassNameId());
		Assert.assertEquals(existingAssetTagStats.getAssetCount(),
			newAssetTagStats.getAssetCount());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetTagStats newAssetTagStats = addAssetTagStats();

		AssetTagStats existingAssetTagStats = _persistence.findByPrimaryKey(newAssetTagStats.getPrimaryKey());

		Assert.assertEquals(existingAssetTagStats, newAssetTagStats);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchTagStatsException");
		}
		catch (NoSuchTagStatsException nsee) {
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
		return OrderByComparatorFactoryUtil.create("AssetTagStats",
			"tagStatsId", true, "tagId", true, "classNameId", true,
			"assetCount", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetTagStats newAssetTagStats = addAssetTagStats();

		AssetTagStats existingAssetTagStats = _persistence.fetchByPrimaryKey(newAssetTagStats.getPrimaryKey());

		Assert.assertEquals(existingAssetTagStats, newAssetTagStats);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetTagStats missingAssetTagStats = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetTagStats);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new AssetTagStatsActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					AssetTagStats assetTagStats = (AssetTagStats)object;

					Assert.assertNotNull(assetTagStats);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetTagStats newAssetTagStats = addAssetTagStats();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTagStats.class,
				AssetTagStats.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("tagStatsId",
				newAssetTagStats.getTagStatsId()));

		List<AssetTagStats> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetTagStats existingAssetTagStats = result.get(0);

		Assert.assertEquals(existingAssetTagStats, newAssetTagStats);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTagStats.class,
				AssetTagStats.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("tagStatsId",
				ServiceTestUtil.nextLong()));

		List<AssetTagStats> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetTagStats newAssetTagStats = addAssetTagStats();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTagStats.class,
				AssetTagStats.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tagStatsId"));

		Object newTagStatsId = newAssetTagStats.getTagStatsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("tagStatsId",
				new Object[] { newTagStatsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTagStatsId = result.get(0);

		Assert.assertEquals(existingTagStatsId, newTagStatsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTagStats.class,
				AssetTagStats.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tagStatsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("tagStatsId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		AssetTagStats newAssetTagStats = addAssetTagStats();

		_persistence.clearCache();

		AssetTagStatsModelImpl existingAssetTagStatsModelImpl = (AssetTagStatsModelImpl)_persistence.findByPrimaryKey(newAssetTagStats.getPrimaryKey());

		Assert.assertEquals(existingAssetTagStatsModelImpl.getTagId(),
			existingAssetTagStatsModelImpl.getOriginalTagId());
		Assert.assertEquals(existingAssetTagStatsModelImpl.getClassNameId(),
			existingAssetTagStatsModelImpl.getOriginalClassNameId());
	}

	protected AssetTagStats addAssetTagStats() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		AssetTagStats assetTagStats = _persistence.create(pk);

		assetTagStats.setTagId(ServiceTestUtil.nextLong());

		assetTagStats.setClassNameId(ServiceTestUtil.nextLong());

		assetTagStats.setAssetCount(ServiceTestUtil.nextInt());

		_assetTagStatses.add(_persistence.update(assetTagStats));

		return assetTagStats;
	}

	private static Log _log = LogFactoryUtil.getLog(AssetTagStatsPersistenceTest.class);
	private List<AssetTagStats> _assetTagStatses = new ArrayList<AssetTagStats>();
	private ModelListener<AssetTagStats>[] _listeners;
	private AssetTagStatsPersistence _persistence = (AssetTagStatsPersistence)PortalBeanLocatorUtil.locate(AssetTagStatsPersistence.class.getName());
}