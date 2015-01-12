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

package com.liferay.portlet.journal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolderConstants;
import com.liferay.portlet.journal.util.JournalTestUtil;
import com.liferay.portlet.journal.util.JournalUtil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class JournalArticleIndexVersionsTest {

	@Before
	public void setUp() throws Exception {
		PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS = false;

		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws PortalException, SystemException {
		PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS));

		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Test
	public void testDeleteAllArticleVersions() throws Exception {
		long initialSearchCount = searchCount();

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID, "test-title",
			"test-content");

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalArticle updateArticle = JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(),
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalArticleLocalServiceUtil.deleteArticle(
			_group.getGroupId(), updateArticle.getArticleId(),
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount, searchCount());
	}

	@Test
	public void testDeleteArticleVersion() throws Exception {
		long initialSearchCount = searchCount();

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		JournalArticle updateArticle = JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(), serviceContext);

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalArticleLocalServiceUtil.deleteArticle(
			updateArticle, updateArticle.getUrlTitle(), serviceContext);

		List<JournalArticle> articles = search();

		Assert.assertEquals(initialSearchCount + 1, articles.size());

		JournalArticle searchArticle = articles.get(0);

		Assert.assertEquals(article.getId(), searchArticle.getId());
	}

	@Test
	public void testExpireAllArticleVersions() throws Exception {
		long initialSearchCount = searchCount();

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalArticle updateArticle = JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(),
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalTestUtil.expireArticle(_group.getGroupId(), updateArticle);

		Assert.assertEquals(initialSearchCount, searchCount());
	}

	@Test
	public void testExpireArticleVersion() throws Exception {
		long initialSearchCount = searchCount();

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalArticle updateArticle = JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(),
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		JournalTestUtil.expireArticle(
			_group.getGroupId(), updateArticle, updateArticle.getVersion());

		List<JournalArticle> articles = search();

		Assert.assertEquals(initialSearchCount + 1, articles.size());

		JournalArticle searchArticle = articles.get(0);

		Assert.assertEquals(article.getId(), searchArticle.getId());
	}

	@Test
	public void testIndexableArticle() throws Exception {
		long initialSearchCount = searchCount();

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertEquals(initialSearchCount + 1, searchCount());

		article.setIndexable(false);

		article = JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(), true, true,
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount, searchCount());

		article.setIndexable(true);

		JournalTestUtil.updateArticle(
			article, article.getTitle(), article.getContent(), true, true,
			ServiceContextTestUtil.getServiceContext());

		Assert.assertEquals(initialSearchCount + 1, searchCount());
	}

	protected List<JournalArticle> search() throws Exception {
		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setGroupIds(new long[] {_group.getGroupId()});

		Hits results = indexer.search(searchContext);

		return (List<JournalArticle>)
			JournalUtil.getArticles(results).getObject(0);
	}

	protected long searchCount() throws Exception {
		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			_group.getGroupId());

		searchContext.setGroupIds(new long[] {_group.getGroupId()});

		Hits results = indexer.search(searchContext);

		return results.getLength();
	}

	Group _group;

}