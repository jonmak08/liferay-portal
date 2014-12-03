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

package com.liferay.counter.service.persistence;

import com.liferay.counter.NoSuchCounterException;
import com.liferay.counter.model.Counter;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.model.ModelListener;
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
public class CounterPersistenceTest {
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

		for (ModelListener<Counter> modelListener : _listeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Counter> iterator = _counters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<Counter> modelListener : _listeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		String pk = ServiceTestUtil.randomString();

		Counter counter = _persistence.create(pk);

		Assert.assertNotNull(counter);

		Assert.assertEquals(counter.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Counter newCounter = addCounter();

		_persistence.remove(newCounter);

		Counter existingCounter = _persistence.fetchByPrimaryKey(newCounter.getPrimaryKey());

		Assert.assertNull(existingCounter);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCounter();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		String pk = ServiceTestUtil.randomString();

		Counter newCounter = _persistence.create(pk);

		newCounter.setCurrentId(ServiceTestUtil.nextLong());

		_counters.add(_persistence.update(newCounter));

		Counter existingCounter = _persistence.findByPrimaryKey(newCounter.getPrimaryKey());

		Assert.assertEquals(existingCounter.getName(), newCounter.getName());
		Assert.assertEquals(existingCounter.getCurrentId(),
			newCounter.getCurrentId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Counter newCounter = addCounter();

		Counter existingCounter = _persistence.findByPrimaryKey(newCounter.getPrimaryKey());

		Assert.assertEquals(existingCounter, newCounter);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = ServiceTestUtil.randomString();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchCounterException");
		}
		catch (NoSuchCounterException nsee) {
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
		return OrderByComparatorFactoryUtil.create("Counter", "name", true,
			"currentId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Counter newCounter = addCounter();

		Counter existingCounter = _persistence.fetchByPrimaryKey(newCounter.getPrimaryKey());

		Assert.assertEquals(existingCounter, newCounter);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = ServiceTestUtil.randomString();

		Counter missingCounter = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCounter);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Counter newCounter = addCounter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Counter.class,
				Counter.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("name", newCounter.getName()));

		List<Counter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Counter existingCounter = result.get(0);

		Assert.assertEquals(existingCounter, newCounter);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Counter.class,
				Counter.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("name",
				ServiceTestUtil.randomString()));

		List<Counter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Counter newCounter = addCounter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Counter.class,
				Counter.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("name"));

		Object newName = newCounter.getName();

		dynamicQuery.add(RestrictionsFactoryUtil.in("name",
				new Object[] { newName }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingName = result.get(0);

		Assert.assertEquals(existingName, newName);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Counter.class,
				Counter.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("name"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("name",
				new Object[] { ServiceTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Counter addCounter() throws Exception {
		String pk = ServiceTestUtil.randomString();

		Counter counter = _persistence.create(pk);

		counter.setCurrentId(ServiceTestUtil.nextLong());

		_counters.add(_persistence.update(counter));

		return counter;
	}

	private static Log _log = LogFactoryUtil.getLog(CounterPersistenceTest.class);
	private List<Counter> _counters = new ArrayList<Counter>();
	private ModelListener<Counter>[] _listeners;
	private CounterPersistence _persistence = (CounterPersistence)PortalBeanLocatorUtil.locate(CounterPersistence.class.getName());
}