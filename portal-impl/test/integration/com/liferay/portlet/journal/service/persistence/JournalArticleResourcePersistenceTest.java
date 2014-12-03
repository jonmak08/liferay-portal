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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.journal.NoSuchArticleResourceException;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.model.impl.JournalArticleResourceModelImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class JournalArticleResourcePersistenceTest {
	@After
	public void tearDown() throws Exception {
		Iterator<JournalArticleResource> iterator = _journalArticleResources.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource journalArticleResource = _persistence.create(pk);

		Assert.assertNotNull(journalArticleResource);

		Assert.assertEquals(journalArticleResource.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		_persistence.remove(newJournalArticleResource);

		JournalArticleResource existingJournalArticleResource = _persistence.fetchByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertNull(existingJournalArticleResource);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addJournalArticleResource();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource newJournalArticleResource = _persistence.create(pk);

		newJournalArticleResource.setUuid(ServiceTestUtil.randomString());

		newJournalArticleResource.setGroupId(ServiceTestUtil.nextLong());

		newJournalArticleResource.setArticleId(ServiceTestUtil.randomString());

		_journalArticleResources.add(_persistence.update(
				newJournalArticleResource));

		JournalArticleResource existingJournalArticleResource = _persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource.getUuid(),
			newJournalArticleResource.getUuid());
		Assert.assertEquals(existingJournalArticleResource.getResourcePrimKey(),
			newJournalArticleResource.getResourcePrimKey());
		Assert.assertEquals(existingJournalArticleResource.getGroupId(),
			newJournalArticleResource.getGroupId());
		Assert.assertEquals(existingJournalArticleResource.getArticleId(),
			newJournalArticleResource.getArticleId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		JournalArticleResource existingJournalArticleResource = _persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchArticleResourceException");
		}
		catch (NoSuchArticleResourceException nsee) {
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
		return OrderByComparatorFactoryUtil.create("JournalArticleResource",
			"uuid", true, "resourcePrimKey", true, "groupId", true,
			"articleId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		JournalArticleResource existingJournalArticleResource = _persistence.fetchByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource missingJournalArticleResource = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingJournalArticleResource);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new JournalArticleResourceActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					JournalArticleResource journalArticleResource = (JournalArticleResource)object;

					Assert.assertNotNull(journalArticleResource);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("resourcePrimKey",
				newJournalArticleResource.getResourcePrimKey()));

		List<JournalArticleResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		JournalArticleResource existingJournalArticleResource = result.get(0);

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("resourcePrimKey",
				ServiceTestUtil.nextLong()));

		List<JournalArticleResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"resourcePrimKey"));

		Object newResourcePrimKey = newJournalArticleResource.getResourcePrimKey();

		dynamicQuery.add(RestrictionsFactoryUtil.in("resourcePrimKey",
				new Object[] { newResourcePrimKey }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingResourcePrimKey = result.get(0);

		Assert.assertEquals(existingResourcePrimKey, newResourcePrimKey);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"resourcePrimKey"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("resourcePrimKey",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		_persistence.clearCache();

		JournalArticleResourceModelImpl existingJournalArticleResourceModelImpl = (JournalArticleResourceModelImpl)_persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingJournalArticleResourceModelImpl.getUuid(),
				existingJournalArticleResourceModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingJournalArticleResourceModelImpl.getGroupId(),
			existingJournalArticleResourceModelImpl.getOriginalGroupId());

		Assert.assertEquals(existingJournalArticleResourceModelImpl.getGroupId(),
			existingJournalArticleResourceModelImpl.getOriginalGroupId());
		Assert.assertTrue(Validator.equals(
				existingJournalArticleResourceModelImpl.getArticleId(),
				existingJournalArticleResourceModelImpl.getOriginalArticleId()));
	}

	protected JournalArticleResource addJournalArticleResource()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource journalArticleResource = _persistence.create(pk);

		journalArticleResource.setUuid(ServiceTestUtil.randomString());

		journalArticleResource.setGroupId(ServiceTestUtil.nextLong());

		journalArticleResource.setArticleId(ServiceTestUtil.randomString());

		_journalArticleResources.add(_persistence.update(journalArticleResource));

		return journalArticleResource;
	}

	private static Log _log = LogFactoryUtil.getLog(JournalArticleResourcePersistenceTest.class);
	private List<JournalArticleResource> _journalArticleResources = new ArrayList<JournalArticleResource>();
	private JournalArticleResourcePersistence _persistence = (JournalArticleResourcePersistence)PortalBeanLocatorUtil.locate(JournalArticleResourcePersistence.class.getName());
}