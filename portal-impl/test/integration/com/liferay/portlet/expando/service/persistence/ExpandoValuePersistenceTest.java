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

package com.liferay.portlet.expando.service.persistence;

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

import com.liferay.portlet.expando.NoSuchValueException;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.model.impl.ExpandoValueModelImpl;

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
public class ExpandoValuePersistenceTest {
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

		for (ModelListener<ExpandoValue> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ExpandoValue> iterator = _expandoValues.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<ExpandoValue> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ExpandoValue expandoValue = _persistence.create(pk);

		Assert.assertNotNull(expandoValue);

		Assert.assertEquals(expandoValue.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ExpandoValue newExpandoValue = addExpandoValue();

		_persistence.remove(newExpandoValue);

		ExpandoValue existingExpandoValue = _persistence.fetchByPrimaryKey(newExpandoValue.getPrimaryKey());

		Assert.assertNull(existingExpandoValue);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addExpandoValue();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ExpandoValue newExpandoValue = _persistence.create(pk);

		newExpandoValue.setCompanyId(ServiceTestUtil.nextLong());

		newExpandoValue.setTableId(ServiceTestUtil.nextLong());

		newExpandoValue.setColumnId(ServiceTestUtil.nextLong());

		newExpandoValue.setRowId(ServiceTestUtil.nextLong());

		newExpandoValue.setClassNameId(ServiceTestUtil.nextLong());

		newExpandoValue.setClassPK(ServiceTestUtil.nextLong());

		newExpandoValue.setData(ServiceTestUtil.randomString());

		_expandoValues.add(_persistence.update(newExpandoValue));

		ExpandoValue existingExpandoValue = _persistence.findByPrimaryKey(newExpandoValue.getPrimaryKey());

		Assert.assertEquals(existingExpandoValue.getValueId(),
			newExpandoValue.getValueId());
		Assert.assertEquals(existingExpandoValue.getCompanyId(),
			newExpandoValue.getCompanyId());
		Assert.assertEquals(existingExpandoValue.getTableId(),
			newExpandoValue.getTableId());
		Assert.assertEquals(existingExpandoValue.getColumnId(),
			newExpandoValue.getColumnId());
		Assert.assertEquals(existingExpandoValue.getRowId(),
			newExpandoValue.getRowId());
		Assert.assertEquals(existingExpandoValue.getClassNameId(),
			newExpandoValue.getClassNameId());
		Assert.assertEquals(existingExpandoValue.getClassPK(),
			newExpandoValue.getClassPK());
		Assert.assertEquals(existingExpandoValue.getData(),
			newExpandoValue.getData());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ExpandoValue newExpandoValue = addExpandoValue();

		ExpandoValue existingExpandoValue = _persistence.findByPrimaryKey(newExpandoValue.getPrimaryKey());

		Assert.assertEquals(existingExpandoValue, newExpandoValue);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchValueException");
		}
		catch (NoSuchValueException nsee) {
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
		return OrderByComparatorFactoryUtil.create("ExpandoValue", "valueId",
			true, "companyId", true, "tableId", true, "columnId", true,
			"rowId", true, "classNameId", true, "classPK", true, "data", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ExpandoValue newExpandoValue = addExpandoValue();

		ExpandoValue existingExpandoValue = _persistence.fetchByPrimaryKey(newExpandoValue.getPrimaryKey());

		Assert.assertEquals(existingExpandoValue, newExpandoValue);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ExpandoValue missingExpandoValue = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingExpandoValue);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new ExpandoValueActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					ExpandoValue expandoValue = (ExpandoValue)object;

					Assert.assertNotNull(expandoValue);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ExpandoValue newExpandoValue = addExpandoValue();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class,
				ExpandoValue.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("valueId",
				newExpandoValue.getValueId()));

		List<ExpandoValue> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ExpandoValue existingExpandoValue = result.get(0);

		Assert.assertEquals(existingExpandoValue, newExpandoValue);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class,
				ExpandoValue.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("valueId",
				ServiceTestUtil.nextLong()));

		List<ExpandoValue> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ExpandoValue newExpandoValue = addExpandoValue();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class,
				ExpandoValue.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("valueId"));

		Object newValueId = newExpandoValue.getValueId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("valueId",
				new Object[] { newValueId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingValueId = result.get(0);

		Assert.assertEquals(existingValueId, newValueId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class,
				ExpandoValue.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("valueId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("valueId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		ExpandoValue newExpandoValue = addExpandoValue();

		_persistence.clearCache();

		ExpandoValueModelImpl existingExpandoValueModelImpl = (ExpandoValueModelImpl)_persistence.findByPrimaryKey(newExpandoValue.getPrimaryKey());

		Assert.assertEquals(existingExpandoValueModelImpl.getColumnId(),
			existingExpandoValueModelImpl.getOriginalColumnId());
		Assert.assertEquals(existingExpandoValueModelImpl.getRowId(),
			existingExpandoValueModelImpl.getOriginalRowId());

		Assert.assertEquals(existingExpandoValueModelImpl.getTableId(),
			existingExpandoValueModelImpl.getOriginalTableId());
		Assert.assertEquals(existingExpandoValueModelImpl.getColumnId(),
			existingExpandoValueModelImpl.getOriginalColumnId());
		Assert.assertEquals(existingExpandoValueModelImpl.getClassPK(),
			existingExpandoValueModelImpl.getOriginalClassPK());
	}

	protected ExpandoValue addExpandoValue() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ExpandoValue expandoValue = _persistence.create(pk);

		expandoValue.setCompanyId(ServiceTestUtil.nextLong());

		expandoValue.setTableId(ServiceTestUtil.nextLong());

		expandoValue.setColumnId(ServiceTestUtil.nextLong());

		expandoValue.setRowId(ServiceTestUtil.nextLong());

		expandoValue.setClassNameId(ServiceTestUtil.nextLong());

		expandoValue.setClassPK(ServiceTestUtil.nextLong());

		expandoValue.setData(ServiceTestUtil.randomString());

		_expandoValues.add(_persistence.update(expandoValue));

		return expandoValue;
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoValuePersistenceTest.class);
	private List<ExpandoValue> _expandoValues = new ArrayList<ExpandoValue>();
	private ModelListener<ExpandoValue>[] _listeners;
	private ExpandoValuePersistence _persistence = (ExpandoValuePersistence)PortalBeanLocatorUtil.locate(ExpandoValuePersistence.class.getName());
}