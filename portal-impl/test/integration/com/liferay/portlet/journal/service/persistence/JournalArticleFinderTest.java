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

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.util.DDMStructureTestUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMTemplateTestUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.util.JournalTestUtil;
import com.liferay.portlet.journal.util.comparator.ArticleCreateDateComparator;
import com.liferay.portlet.journal.util.comparator.ArticleDisplayDateComparator;
import com.liferay.portlet.journal.util.comparator.ArticleIDComparator;
import com.liferay.portlet.journal.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portlet.journal.util.comparator.ArticleReviewDateComparator;
import com.liferay.portlet.journal.util.comparator.ArticleTitleComparator;
import com.liferay.portlet.journal.util.comparator.ArticleVersionComparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 * @author Laszlo Csontos
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class JournalArticleFinderTest {

	@Before
	public void setUp() throws Exception {
		_articles.clear();

		_group = GroupTestUtil.addGroup();

		_ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		_folder = JournalTestUtil.addFolder(_group.getGroupId(), "Folder 1");

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(), _folder.getFolderId(), "Article 1",
			StringPool.BLANK);

		_articles.add(article);

		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), "Folder 2");

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			_group.getGroupId(), _ddmStructure.getStructureId());

		article = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), folder.getFolderId(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			"<title>Article 2</title>", _ddmStructure.getStructureKey(),
			ddmTemplate.getTemplateKey());

		_articles.add(article);

		article = JournalTestUtil.addArticle(
			_group.getGroupId(), folder.getFolderId(), "Article 3",
			StringPool.BLANK);

		_articles.add(article);

		article.setUserId(_USER_ID);

		Calendar calendar = new GregorianCalendar();

		calendar.add(Calendar.DATE, -1);

		article.setExpirationDate(calendar.getTime());
		article.setReviewDate(calendar.getTime());

		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		JournalArticleLocalServiceUtil.moveArticleToTrash(
			TestPropsValues.getUserId(), article);

		article = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), folder.getFolderId(),
			PortalUtil.getClassNameId(JournalStructure.class),
			"<title>Article 4</title>", _ddmStructure.getStructureKey(),
			ddmTemplate.getTemplateKey());

		_articles.add(article);

		_folderIds.clear();

		_folderIds.add(_folder.getFolderId());
		_folderIds.add(folder.getFolderId());

		_article = _articles.get(0);
	}

	@Test
	public void testDraftArticles() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition(
			WorkflowConstants.STATUS_ANY);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 2);

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
				_group.getGroupId(), _ddmStructure.getStructureId());

		queryDefinition.setUserId(TestPropsValues.getUserId());

		JournalArticle article = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), _folder.getFolderId(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			"<title>Article 1</title>",
			_ddmStructure.getStructureKey(),
			ddmTemplate.getTemplateKey());

		article.setUserId(_USER_ID);
		article.setStatus(WorkflowConstants.STATUS_DRAFT);

		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		_articles.add(article);

		queryDefinition.setIncludeOwner(true);
		queryDefinition.setUserId(_USER_ID);
		queryDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 3);

		queryDefinition.setIncludeOwner(false);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 0);
	}

	@Test
	public void testFindByExpirationDate() throws Exception {

		// Status any

		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		List<JournalArticle> articles =
			JournalArticleFinderUtil.findByExpirationDate(
				JournalArticleConstants.CLASSNAME_ID_DEFAULT, new Date(),
				queryDefinition);

		Assert.assertEquals(1, articles.size());

		JournalArticle article = articles.get(0);

		Assert.assertEquals(_USER_ID, article.getUserId());

		// Status in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		articles = JournalArticleFinderUtil.findByExpirationDate(
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, new Date(),
			queryDefinition);

		Assert.assertEquals(1, articles.size());

		article = articles.get(0);

		Assert.assertEquals(_USER_ID, article.getUserId());

		// Status not in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		articles = JournalArticleFinderUtil.findByExpirationDate(
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, new Date(),
			queryDefinition);

		Assert.assertEquals(0, articles.size());
	}

	@Test
	public void testFindByR_D() throws Exception {
		JournalArticle article = JournalArticleFinderUtil.findByR_D(
			_article.getResourcePrimKey(), new Date());

		Assert.assertNotNull(article);

		Assert.assertEquals(_folder.getFolderId(), article.getFolderId());
	}

	@Test
	public void testFindByReviewDate() throws Exception {
		Calendar calendar = new GregorianCalendar();

		calendar.add(Calendar.DATE, -2);

		List<JournalArticle> articles =
			JournalArticleFinderUtil.findByReviewDate(
				JournalArticleConstants.CLASSNAME_ID_DEFAULT, new Date(),
				calendar.getTime());

		Assert.assertEquals(1, articles.size());

		JournalArticle article = articles.get(0);

		Assert.assertEquals(_USER_ID, article.getUserId());
	}

	@Test
	public void testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R() throws Exception {

		// Status any

		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
			_group.getCompanyId(), _group.getGroupId(), _folderIds,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, null, null, "Article",
			null, null, null, (String)null, null, null, null, null, true,
			queryDefinition, 3);
		testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
			_group.getCompanyId(), _group.getGroupId(), _folderIds,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, null, null, null,
			null, null, null, _ddmStructure.getStructureKey(), null, null, null,
			null, true, queryDefinition, 1);

		// Status in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
			_group.getCompanyId(), _group.getGroupId(), _folderIds,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, null, null, "Article",
			null, null, null, (String)null, null, null, null, null, true,
			queryDefinition, 1);

		// Status not in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
			_group.getCompanyId(), _group.getGroupId(), _folderIds,
			PortalUtil.getClassNameId(JournalStructure.class), null, null,
			"Article", null, null, null, (String)null, null, null, null, null,
			true, queryDefinition, 1);
	}

	@Test
	public void testQueryByG_C_S() throws Exception {

		// Status any

		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			_ddmStructure.getStructureKey(), queryDefinition, 1);
		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			StringPool.BLANK, queryDefinition, 2);

		// Status in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			_ddmStructure.getStructureKey(), queryDefinition, 0);
		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			StringPool.BLANK, queryDefinition, 1);

		// Status not in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			_ddmStructure.getStructureKey(), queryDefinition, 1);
		testQueryByG_C_S(
			_group.getGroupId(), JournalArticleConstants.CLASSNAME_ID_DEFAULT,
			StringPool.BLANK, queryDefinition, 1);
	}

	@Test
	public void testQueryByG_F() throws Exception {

		// Status any

		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		testQueryByG_F(_group.getGroupId(), _folderIds, queryDefinition, 4);

		// Status in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		testQueryByG_F(_group.getGroupId(), _folderIds, queryDefinition, 1);

		// Status not in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		testQueryByG_F(_group.getGroupId(), _folderIds, queryDefinition, 3);

		// Comparators

		testQueryByG_F(new ArticleCreateDateComparator(true));
		testQueryByG_F(new ArticleCreateDateComparator(false));
		testQueryByG_F(new ArticleDisplayDateComparator(true));
		testQueryByG_F(new ArticleDisplayDateComparator(false));
		testQueryByG_F(new ArticleIDComparator(true));
		testQueryByG_F(new ArticleIDComparator(false));
		testQueryByG_F(new ArticleModifiedDateComparator(true));
		testQueryByG_F(new ArticleModifiedDateComparator(false));
		testQueryByG_F(new ArticleReviewDateComparator(true));
		testQueryByG_F(new ArticleReviewDateComparator(false));
		testQueryByG_F(new ArticleTitleComparator(true));
		testQueryByG_F(new ArticleTitleComparator(false));
		testQueryByG_F(new ArticleVersionComparator(true));
		testQueryByG_F(new ArticleVersionComparator(false));
	}

	@Test
	public void testQueryByG_F_C() throws Exception {

		// Status any (constructor), which is status not in trash

		QueryDefinition queryDefinition = new QueryDefinition(
			WorkflowConstants.STATUS_ANY);
		queryDefinition.setUserId(TestPropsValues.getUserId());

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 2);

		// Status any

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);
		queryDefinition.setUserId(_USER_ID);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 1);

		// Status in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);
		queryDefinition.setUserId(_USER_ID);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 1);

		// Status not in trash

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);
		queryDefinition.setUserId(_USER_ID);

		testQueryByG_C(
			_group.getGroupId(), Collections.<Long>emptyList(),
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, queryDefinition, 0);
	}

	protected void prepareSortedArticles() throws Exception {
		Calendar calendar = new GregorianCalendar();

		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.YEAR, 2014);

		for (int i = 0; i < _articles.size(); ++i) {
			calendar.add(Calendar.DATE, 1);

			JournalArticle article = _articles.get(i);

			article.setCreateDate(calendar.getTime());
			article.setModifiedDate(calendar.getTime());
			article.setArticleId("a" + i);
			article.setVersion(i);
			article.setTitle("a" + i);
			article.setDisplayDate(calendar.getTime());
			article.setReviewDate(calendar.getTime());

			JournalArticleLocalServiceUtil.updateJournalArticle(article);
		}
	}

	protected void testQueryByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
			long companyId, long groupId, List<Long> folderIds,
			long classNameId, String articleId, Double version, String title,
			String description, String content, String type,
			String ddmStructureKey, String ddmTemplateKey, Date displayDateGT,
			Date displayDateLT, Date reviewDate, boolean andOperator,
			QueryDefinition queryDefinition, int expectedCount)
		throws Exception {

		int actualCount =
			JournalArticleFinderUtil.countByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
				companyId, groupId, folderIds, classNameId, articleId, version,
				title, description, content, type, ddmStructureKey,
				ddmTemplateKey, displayDateGT, displayDateLT, reviewDate,
				andOperator, queryDefinition);

		Assert.assertEquals(expectedCount, actualCount);

		List<JournalArticle> articles =
			JournalArticleFinderUtil.findByC_G_F_C_A_V_T_D_C_T_S_T_D_R(
				companyId, groupId, folderIds, classNameId, articleId, version,
				title, description, content, type, ddmStructureKey,
				ddmTemplateKey, displayDateGT, displayDateLT, reviewDate,
				andOperator, queryDefinition);

		actualCount = articles.size();

		Assert.assertEquals(expectedCount, actualCount);
	}

	protected void testQueryByG_C(
			long groupId, List<Long> folderIds, long classNameId,
			QueryDefinition queryDefinition, int expectedCount)
		throws Exception {

		int actualCount = JournalArticleFinderUtil.countByG_F_C(
			groupId, folderIds, classNameId, queryDefinition);

		List<JournalArticle> articles = JournalArticleFinderUtil.findByG_F_C(
			groupId, folderIds, classNameId, queryDefinition);

		Assert.assertEquals(expectedCount, actualCount);

		actualCount = articles.size();

		Assert.assertEquals(expectedCount, actualCount);
	}

	protected void testQueryByG_C_S(
			long groupId, long classNameId, String ddmStructureKey,
			QueryDefinition queryDefinition, int expectedCount)
		throws Exception {

		int actualCount = JournalArticleFinderUtil.countByG_C_S(
			groupId, classNameId, ddmStructureKey, queryDefinition);

		Assert.assertEquals(expectedCount, actualCount);

		List<JournalArticle> articles = JournalArticleFinderUtil.findByG_C_S(
			groupId, classNameId, ddmStructureKey, queryDefinition);

		actualCount = articles.size();

		Assert.assertEquals(expectedCount, actualCount);
	}

	protected void testQueryByG_F(
			long groupId, List<Long> folderIds, QueryDefinition queryDefinition,
			int expectedCount)
		throws Exception {

		int actualCount = JournalArticleFinderUtil.countByG_F(
			groupId, folderIds, queryDefinition);

		Assert.assertEquals(expectedCount, actualCount);

		List<JournalArticle> articles = JournalArticleFinderUtil.findByG_F(
			groupId, folderIds, queryDefinition);

		actualCount = articles.size();

		Assert.assertEquals(expectedCount, actualCount);
	}

	protected void testQueryByG_F(OrderByComparator orderByComparator)
		throws Exception {

		prepareSortedArticles();

		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setOrderByComparator(orderByComparator);

		List<JournalArticle> expectedArticles = null;

		if (orderByComparator.isAscending()) {
			expectedArticles = _articles;
		}
		else {
			expectedArticles = new ArrayList<JournalArticle>(_articles);

			Collections.reverse(expectedArticles);
		}

		List<JournalArticle> actualArticles =
			JournalArticleFinderUtil.findByG_F(
				_group.getGroupId(), _folderIds, queryDefinition);

		Assert.assertEquals(expectedArticles, actualArticles);
	}

	private static final long _USER_ID = 1234L;

	private JournalArticle _article;
	private List<JournalArticle> _articles = new ArrayList<JournalArticle>();
	private DDMStructure _ddmStructure;
	private JournalFolder _folder;
	private List<Long> _folderIds = new ArrayList<Long>();
	private Group _group;

}