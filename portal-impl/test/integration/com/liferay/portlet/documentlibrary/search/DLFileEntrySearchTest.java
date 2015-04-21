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

package com.liferay.portlet.documentlibrary.search;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Group;
import com.liferay.portal.search.BaseSearchTestCase;
import com.liferay.portal.search.TestOrderHelper;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMIndexerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMStructureTestUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class DLFileEntrySearchTest extends BaseSearchTestCase {

	@Ignore()
	@Override
	@Test
	public void testLocalizedSearch() throws Exception {
	}

	@Test
	public void testOrderByDDMBooleanField() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMBooleanField();
	}

	@Test
	public void testOrderByDDMBooleanFieldRepeatable() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMBooleanFieldRepeatable();
	}

	@Test
	public void testOrderByDDMIntegerField() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMIntegerField();
	}

	@Test
	public void testOrderByDDMIntegerFieldRepeatable() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMIntegerFieldRepeatable();
	}

	@Test
	public void testOrderByDDMNumberField() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMNumberField();
	}

	@Test
	public void testOrderByDDMNumberFieldRepeatable() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMNumberFieldRepeatable();
	}

	@Test
	public void testOrderByDDMTextField() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMTextField();
	}

	@Test
	public void testOrderByDDMTextFieldRepeatable() throws Exception {
		TestOrderHelper orderTestHelper = new DLFileEntrySearchTestOrderHelper(
			group);

		orderTestHelper.testOrderByDDMTextFieldRepeatable();
	}

	@Ignore()
	@Override
	@Test
	public void testSearchAttachments() throws Exception {
	}

	protected BaseModel<?> addBaseModel(
			String keywords, DDMStructure ddmStructure,
			ServiceContext serviceContext)
		throws Exception {

		return addBaseModel(
			new String[] {keywords}, ddmStructure, serviceContext);
	}

	protected BaseModel<?> addBaseModel(
			String[] keywords, DDMStructure ddmStructure,
			ServiceContext serviceContext)
		throws Exception {

		_ddmStructure = ddmStructure;

		DLFileEntryType dlFileEntryType =
			DLFileEntryTypeLocalServiceUtil.addFileEntryType(
				TestPropsValues.getUserId(), serviceContext.getScopeGroupId(),
				"Structure", StringPool.BLANK,
				new long[] {ddmStructure.getStructureId()}, serviceContext);

		String content = "Content: Enterprise. Open Source. For Life.";

		Fields fields = new Fields();

		List<Serializable> values = new ArrayList<Serializable>(
			keywords.length);

		for (String keyword : keywords) {
			values.add(keyword);
		}

		Field textField = new Field(
			ddmStructure.getStructureId(), "name", values, Locale.getDefault());

		fields.put(textField);

		serviceContext.setAttribute(
			"fileEntryTypeId", dlFileEntryType.getFileEntryTypeId());
		serviceContext.setAttribute(
			Fields.class.getName() + ddmStructure.getStructureId(), fields);

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Text.txt",
			ContentTypes.TEXT_PLAIN, RandomTestUtil.randomString(),
			content.getBytes(), WorkflowConstants.ACTION_PUBLISH,
			serviceContext);

		return (DLFileEntry)fileEntry.getModel();
	}

	@Override
	protected BaseModel<?> addBaseModelWithDDMStructure(
			BaseModel<?> parentBaseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		String xsd = DDMStructureTestUtil.getSampleStructureXSD("name");

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			serviceContext.getScopeGroupId(), DLFileEntry.class.getName(), xsd);

		return addBaseModel(keywords, ddmStructure, serviceContext);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		DLFolder dlFolder = (DLFolder)parentBaseModel;

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (dlFolder != null) {
			folderId = dlFolder.getFolderId();
		}

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			folderId, keywords + ".txt", keywords, approved, serviceContext);

		return (DLFileEntry)fileEntry.getModel();
	}

	@Override
	protected void expireBaseModelVersions(
			BaseModel<?> baseModel, boolean expireAllVersions,
			ServiceContext serviceContext)
		throws Exception {

		DLFileEntry dlFileEntry = (DLFileEntry)baseModel;

		if (expireAllVersions) {
			DLAppServiceUtil.deleteFileEntry(dlFileEntry.getFileEntryId());
		}
		else {
			DLAppServiceUtil.deleteFileVersion(
				dlFileEntry.getFileEntryId(), dlFileEntry.getVersion());
		}
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return DLFileEntry.class;
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

		Folder folder = DLAppTestUtil.addFolder(
			(Long)parentBaseModel.getPrimaryKeyObj(),
			ServiceTestUtil.randomString(_FOLDER_NAME_MAX_LENGTH),
			serviceContext);

		return (DLFolder)folder.getModel();
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		Folder folder = DLAppTestUtil.addFolder(
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString(_FOLDER_NAME_MAX_LENGTH),
			serviceContext);

		return (DLFolder)folder.getModel();
	}

	@Override
	protected String getParentBaseModelClassName() {
		return DLFolderConstants.getClassName();
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected boolean isExpirableAllVersions() {
		return true;
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		DLAppServiceUtil.moveFileEntryToTrash(primaryKey);
	}

	@Override
	protected void moveParentBaseModelToTrash(long primaryKey)
		throws Exception {

		DLAppServiceUtil.moveFolderToTrash(primaryKey);
	}

	@Override
	protected long searchGroupEntriesCount(long groupId, long creatorUserId)
		throws Exception {

		Hits hits =  DLAppServiceUtil.search(
			groupId, creatorUserId, WorkflowConstants.STATUS_APPROVED,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return hits.getLength();
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		DLFileEntry dlFileEntry = (DLFileEntry)baseModel;

		FileEntry fileEntry = DLAppTestUtil.updateFileEntry(
			serviceContext.getScopeGroupId(), dlFileEntry.getFileEntryId(),
			null, dlFileEntry.getMimeType(), keywords, true, serviceContext);

		return (DLFileEntry)fileEntry.getModel();
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

	protected class DLFileEntrySearchTestOrderHelper extends TestOrderHelper {

		protected DLFileEntrySearchTestOrderHelper(Group group)
			throws Exception {

			super(group);
		}

		@Override
		protected BaseModel<?> addSearchableAssetEntry(
				String fieldValue, BaseModel<?> parentBaseModel,
				DDMStructure ddmStructure, DDMTemplate ddmTemplate,
				ServiceContext serviceContext)
			throws Exception {

			return addBaseModel(fieldValue, ddmStructure, serviceContext);
		}

		@Override
		protected BaseModel<?> addSearchableAssetEntryRepeatable(
				String[] fieldValues, BaseModel<?> parentBaseModel,
				DDMStructure ddmStructure, DDMTemplate ddmTemplate,
				ServiceContext serviceContext)
			throws Exception {

			return addBaseModel(fieldValues, ddmStructure, serviceContext);
		}

		@Override
		protected String getValue(AssetRenderer assetRenderer)
			throws Exception {

			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.getDLFileEntry(
					assetRenderer.getClassPK());

			DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

			DLFileEntryMetadata dlFileEntryMetadata =
				DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(
					_ddmStructure.getStructureId(),
					dlFileVersion.getFileVersionId());

			Fields fields = StorageEngineUtil.getFields(
				dlFileEntryMetadata.getDDMStorageId());

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
			return DLFileEntryMetadata.class.getName();
		}

	}

	private static final int _FOLDER_NAME_MAX_LENGTH = 100;

	private DDMStructure _ddmStructure;

}