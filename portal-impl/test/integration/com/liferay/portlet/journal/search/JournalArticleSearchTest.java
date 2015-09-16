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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.test.IdempotentRetryAssert;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.ClassedModel;
import com.liferay.portal.model.Group;
import com.liferay.portal.search.BaseSearchTestCase;
import com.liferay.portal.search.TestOrderHelper;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.DDMIndexerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMStructureTestUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMTemplateTestUtil;
import com.liferay.portlet.journal.asset.JournalArticleAssetRenderer;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.model.JournalFolderConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.service.JournalFolderServiceUtil;
import com.liferay.portlet.journal.util.JournalConverterUtil;
import com.liferay.portlet.journal.util.JournalTestUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Juan Fernández
 * @author Tibor Lipusz
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class JournalArticleSearchTest extends BaseSearchTestCase {

	@Test
	public void testArticleIdCaseInsensitive() throws Exception {
		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		Map<Locale, String> keywordsMap = new HashMap<Locale, String>();

		String keywords = "keywords";

		keywordsMap.put(LocaleUtil.getDefault(), keywords);
		keywordsMap.put(LocaleUtil.GERMANY, keywords);
		keywordsMap.put(LocaleUtil.SPAIN, keywords);

		String articleId = "Article.Id";

		JournalArticle article = JournalTestUtil.addArticle(
			group.getGroupId(), JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, articleId, false,
			keywordsMap, keywordsMap, keywordsMap, LocaleUtil.getDefault(),
			null, true, true, serviceContext);

		updateBaseModel(article, keywords, serviceContext);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			group.getGroupId());

		int initialBaseModelsSearchCount = 1;

		assertBaseModelsCount(
			initialBaseModelsSearchCount, "ARTICLE.ID", searchContext);
		assertBaseModelsCount(
			initialBaseModelsSearchCount, "article.id", searchContext);
		assertBaseModelsCount(
			initialBaseModelsSearchCount, "ArtiCle.Id", searchContext);
	}

	@Test
	public void testMatchNotOnlyCompanyIdButAlsoQueryTerms() throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(TestPropsValues.getCompanyId());

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);

		query.addTerm("title", RandomTestUtil.randomString());

		assertEquals(0, query, searchContext);
	}

	@Test
	public void testOrderByDDMBooleanField() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMBooleanField();
	}

	@Test
	public void testOrderByDDMBooleanFieldRepeatable() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMBooleanFieldRepeatable();
	}

	@Test
	public void testOrderByDDMIntegerField() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMIntegerField();
	}

	@Test
	public void testOrderByDDMIntegerFieldRepeatable() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMIntegerFieldRepeatable();
	}

	@Test
	public void testOrderByDDMNumberField() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMNumberField();
	}

	@Test
	public void testOrderByDDMNumberFieldRepeatable() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMNumberFieldRepeatable();
	}

	@Test
	public void testOrderByDDMTextField() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMTextField();
	}

	@Test
	public void testOrderByDDMTextFieldKeyword() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMTextFieldKeyword();
	}

	@Test
	public void testOrderByDDMTextFieldRepeatable() throws Exception {
		TestOrderHelper testOrderHelper =
			new JournalArticleSearchTestOrderHelper(group);

		testOrderHelper.testOrderByDDMTextFieldRepeatable();
	}

	@Ignore()
	@Override
	@Test
	public void testSearchAttachments() throws Exception {
	}

	protected BaseModel<?> addArticleWithXmlContent(
			BaseModel<?> parentBaseModel, String content,
			DDMStructure ddmStructure, DDMTemplate ddmTemplate,
			ServiceContext serviceContext)
		throws Exception {

		_ddmStructure = ddmStructure;

		JournalFolder folder = (JournalFolder)parentBaseModel;

		return JournalTestUtil.addArticleWithXMLContent(
			folder.getFolderId(), content, ddmStructure.getStructureKey(),
			ddmTemplate.getTemplateKey(), serviceContext);
	}

	@Override
	protected BaseModel<?> addBaseModelWithDDMStructure(
			BaseModel<?> parentBaseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		String content = DDMStructureTestUtil.getSampleStructuredContent(
			"name", keywords);

		String xsd = DDMStructureTestUtil.getSampleStructureXSD("name");

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			serviceContext.getScopeGroupId(), JournalArticle.class.getName(),
			xsd);

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			serviceContext.getScopeGroupId(), ddmStructure.getStructureId());

		return addArticleWithXmlContent(
			parentBaseModel, content, ddmStructure, ddmTemplate,
			serviceContext);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved,
			Map<Locale, String> keywordsMap, ServiceContext serviceContext)
		throws Exception {

		return JournalTestUtil.addArticleWithWorkflow(
			group.getGroupId(), keywordsMap, null, keywordsMap, approved);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		JournalFolder folder = (JournalFolder)parentBaseModel;

		long folderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (folder != null) {
			folderId = folder.getFolderId();
		}

		return JournalTestUtil.addArticleWithWorkflow(
			folderId, keywords, approved, serviceContext);
	}

	protected void assertEquals(
			final long length, final BooleanQuery query,
			final SearchContext searchContext)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Hits hits = SearchEngineUtil.search(searchContext, query);

					Assert.assertEquals(length, hits.getLength());

					return null;
				}

			});
	}

	@Override
	protected void checkUserPermissionsBaseModelsSearchCount(
			boolean addBaseModelPermission, int initialBaseModelsSearchCount,
			int searchBaseModelsCount)
		throws Exception {

		if (addBaseModelPermission &&
			!PropsValues.JOURNAL_ARTICLE_VIEW_PERMISSION_CHECK_ENABLED) {

			initialBaseModelsSearchCount++;
		}

		Assert.assertEquals(
			initialBaseModelsSearchCount, searchBaseModelsCount);
	}

	@Override
	protected void expireBaseModelVersions(
			BaseModel<?> baseModel, boolean expireAllVersions,
			ServiceContext serviceContext)
		throws Exception {

		JournalArticle article = (JournalArticle)baseModel;

		if (expireAllVersions) {
			JournalArticleLocalServiceUtil.expireArticle(
				article.getUserId(), article.getGroupId(),
				article.getArticleId(), article.getUrlTitle(), serviceContext);
		}
		else {
			JournalArticleLocalServiceUtil.expireArticle(
				article.getUserId(), article.getGroupId(),
				article.getArticleId(), article.getVersion(),
				article.getUrlTitle(), serviceContext);
		}
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return JournalArticle.class;
	}

	@Override
	protected Long getBaseModelClassPK(ClassedModel classedModel) {
		return JournalArticleAssetRenderer.getClassPK(
			(JournalArticle)classedModel);
	}

	@Override
	protected String getDDMStructureFieldName() {
		return DDMIndexerUtil.encodeName(
			_ddmStructure.getStructureId(), "name",
			LocaleUtil.getSiteDefault());
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		return JournalTestUtil.addFolder(
			(Long)parentBaseModel.getPrimaryKeyObj(),
			ServiceTestUtil.randomString(), serviceContext);
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return JournalTestUtil.addFolder(
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString(), serviceContext);
	}

	@Override
	protected String getParentBaseModelClassName() {
		return JournalFolder.class.getName();
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected boolean isExpirableAllVersions() {
		return PropsValues.JOURNAL_ARTICLE_EXPIRE_ALL_VERSIONS;
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(
			primaryKey);

		JournalArticleLocalServiceUtil.moveArticleToTrash(
			TestPropsValues.getUserId(), article);
	}

	@Override
	protected void moveParentBaseModelToTrash(long primaryKey)
		throws Exception {

		JournalFolderServiceUtil.moveFolderToTrash(primaryKey);
	}

	@Override
	protected long searchGroupEntriesCount(long groupId, long creatorUserId)
		throws Exception {

		Hits hits =  JournalArticleServiceUtil.search(
			groupId, creatorUserId, WorkflowConstants.STATUS_APPROVED,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return hits.getLength();
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		JournalArticle article = (JournalArticle)baseModel;

		return JournalTestUtil.updateArticle(
			article, keywords, article.getContent(), serviceContext);
	}

	@Override
	protected void updateDDMStructure(ServiceContext serviceContext)
		throws Exception {

		String xsd = DDMStructureTestUtil.getSampleStructureXSD("title");

		DDMStructureLocalServiceUtil.updateStructure(
			_ddmStructure.getStructureId(),
			_ddmStructure.getParentStructureId(), _ddmStructure.getNameMap(),
			_ddmStructure.getDescriptionMap(), xsd, serviceContext);
	}

	protected class JournalArticleSearchTestOrderHelper
		extends TestOrderHelper {

		protected JournalArticleSearchTestOrderHelper(Group group)
			throws Exception {

			super(group);
		}

		@Override
		protected BaseModel<?> addSearchableAssetEntry(
				String fieldValue, BaseModel<?> parentBaseModel,
				DDMStructure ddmStructure, DDMTemplate ddmTemplate,
				ServiceContext serviceContext)
			throws Exception {

			String content = DDMStructureTestUtil.getSampleStructuredContent(
				fieldValue);

			return addArticleWithXmlContent(
				parentBaseModel, content, ddmStructure, ddmTemplate,
				serviceContext);
		}

		@Override
		protected BaseModel<?> addSearchableAssetEntryRepeatable(
				String[] fieldValues, BaseModel<?> parentBaseModel,
				DDMStructure ddmStructure, DDMTemplate ddmTemplate,
				ServiceContext serviceContext)
			throws Exception {

			ArrayList<Map<Locale, String>> contents =
				new ArrayList<Map<Locale, String>>(fieldValues.length);

			for (String fieldValue : fieldValues) {
				Map<Locale, String> map = new HashMap<Locale, String>();

				map.put(Locale.US, fieldValue);

				contents.add(map);
			}

			String content = DDMStructureTestUtil.getSampleStructuredContent(
				"name", contents, "en_US");

			return addArticleWithXmlContent(
				parentBaseModel, content, ddmStructure, ddmTemplate,
				serviceContext);
		}

		@Override
		protected String getValue(AssetRenderer assetRenderer)
			throws Exception {

			JournalArticleAssetRenderer journalArticleAssetRenderer =
				(JournalArticleAssetRenderer)assetRenderer;

			JournalArticle article = journalArticleAssetRenderer.getArticle();

			Fields fields = JournalConverterUtil.getDDMFields(
				_ddmStructure, article.getContent());

			Field field = fields.get("name");

			List<Serializable> values = field.getValues(
				LocaleUtil.getDefault());

			if (values.size() == 1) {
				return String.valueOf(values.get(0));
			}

			StringBundler sb = new StringBundler(values.size());

			for (Serializable value : values) {
				sb.append(value);
				sb.append(StringPool.PIPE);
			}

			sb.setIndex(sb.index() - 1);

			return sb.toString();
		}

		@Override
		protected String getSearchableAssetEntryClassName() {
			return getBaseModelClassName();
		}

		@Override
		protected BaseModel<?> getSearchableAssetEntryParentBaseModel(
				Group group, ServiceContext serviceContext)
			throws Exception {

			return getParentBaseModel(group, serviceContext);
		}

		@Override
		protected String getSearchableAssetEntryStructureClassName() {
			return getBaseModelClassName();
		}

	}

	private DDMStructure _ddmStructure;

}