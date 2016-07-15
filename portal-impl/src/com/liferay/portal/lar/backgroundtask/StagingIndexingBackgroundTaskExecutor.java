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

package com.liferay.portal.lar.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.persistence.JournalArticleActionableDynamicQuery;
import com.liferay.portlet.journal.util.JournalArticleIndexer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mate Thurzo
 */
public class StagingIndexingBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor {

	public StagingIndexingBackgroundTaskExecutor() {
		setSerial(true);
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		PortletDataContext portletDataContext =
			(PortletDataContext)taskContextMap.get("portletDataContext");

		try {
			deleteDocuments(portletDataContext);
		}
		catch (Exception e) {
			_log.error("Unable to delete documents", e);
		}

		boolean importPermissions = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.PERMISSIONS);

		if (importPermissions) {
			long userId = MapUtil.getLong(taskContextMap, "userId");

			if (userId > 0) {
				Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
					User.class);

				indexer.reindex(userId);
			}
		}

		Map<String, Map<?, ?>> newPrimaryKeysMaps =
			portletDataContext.getNewPrimaryKeysMaps();

		for (Map.Entry<String, Map<?, ?>> newPrimaryKeysMapsEntry :
				newPrimaryKeysMaps.entrySet()) {

			String className = newPrimaryKeysMapsEntry.getKey();

			Indexer indexer = IndexerRegistryUtil.getIndexer(className);

			if ((indexer == null) &&
				!className.equals(DDMStructure.class.getName())) {

				continue;
			}

			Map<?, ?> newPrimaryKeysMap = newPrimaryKeysMapsEntry.getValue();

			List<Long> newPrimaryKeys = new ArrayList<Long>();

			for (Object object : newPrimaryKeysMap.values()) {
				long classPK = GetterUtil.getLong(object);

				if (classPK > 0) {
					newPrimaryKeys.add(classPK);
				}
			}

			if (className.equals(DDMStructure.class.getName())) {
				reindexDDMStructures(
					newPrimaryKeys, newPrimaryKeysMaps,
					portletDataContext.getGroupId());
			}
			else {
				for (Long classPK : newPrimaryKeys) {
					indexer.reindex(className, classPK);
				}
			}
		}

		return BackgroundTaskResult.SUCCESS;
	}

	protected void deleteDocuments(PortletDataContext portletDataContext) {
		Map<String, List<String>> deletionKeysMap =
			portletDataContext.getDeletionKeysMap();

		for (String className : deletionKeysMap.keySet()) {
			Indexer indexer = IndexerRegistryUtil.getIndexer(className);

			List<String> uids = deletionKeysMap.get(className);

			for (String uid : uids) {
				try {
					indexer.delete(portletDataContext.getCompanyId(), uid);
				}
				catch (SearchException se) {
					_log.error(
						"Unable to delete document with uid: " + uid, se);
				}
			}
		}
	}

	protected ActionableDynamicQuery getJournalArticleActionableDynamicQuery(
			long groupId, final Map<?, ?> journalArticleIds,
			final String[] ddmStructureKeys)
		throws Exception {

		ActionableDynamicQuery journalArticleActionableDynamicQuery =
			new JournalArticleActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property structureIdProperty = PropertyFactoryUtil.forName(
					"structureId");

				dynamicQuery.add(structureIdProperty.in(ddmStructureKeys));
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				JournalArticle article = (JournalArticle)object;

				if (containsValue(
						journalArticleIds, article.getResourcePrimKey())) {

					return;
				}

				try {
					_journalArticleIndexer.doReindex(article, false);
				}
				catch (Exception e) {
					throw new PortalException(e);
				}
			}

			private final JournalArticleIndexer _journalArticleIndexer =
				(JournalArticleIndexer)IndexerRegistryUtil.getIndexer(
					JournalArticle.class);

		};

		journalArticleActionableDynamicQuery.setGroupId(groupId);

		return journalArticleActionableDynamicQuery;
	}

	protected void reindexDDMStructures(
			List<Long> ddmStructureIds,
			final Map<String, Map<?, ?>> newPrimaryKeysMaps, long groupId)
		throws Exception {

		if ((ddmStructureIds == null) || ddmStructureIds.isEmpty()) {
			return;
		}

		Set<Long> descendantDDMStructureIds = new HashSet<Long>();

		for (long structureId : ddmStructureIds) {
			DDMStructure ddmStructure =
				DDMStructureLocalServiceUtil.getDDMStructure(structureId);

			descendantDDMStructureIds.addAll(
				getDescendantDDMStructureIds(
					ddmStructure.getGroupId(), ddmStructure.getStructureId()));
		}

		int i = 0;

		String[] ddmStructureKeys =
			new String[descendantDDMStructureIds.size()];

		for (long ddmStructureId : descendantDDMStructureIds) {
			DDMStructure ddmStructure =
				DDMStructureLocalServiceUtil.getDDMStructure(ddmStructureId);

			ddmStructureKeys[i++] = ddmStructure.getStructureKey();
		}

		// Journal

		Map<?, ?> articleIds = (Map<?, ?>)newPrimaryKeysMaps.get(
			JournalArticle.class.getName());

		ActionableDynamicQuery journalArticleActionableDynamicQuery =
			getJournalArticleActionableDynamicQuery(
				groupId, articleIds, ddmStructureKeys);

		journalArticleActionableDynamicQuery.performActions();

		// Document library

		List<DLFileEntry> dlFileEntries = new ArrayList<DLFileEntry>();

		try {
			Method method = ReflectionUtil.getDeclaredMethod(
				DLFileEntryLocalServiceUtil.class, "getDDMStructureFileEntries",
				long.class, long[].class);

			Object object = method.invoke(
				DLFileEntryLocalServiceUtil.class, groupId,
				ArrayUtil.toLongArray(descendantDDMStructureIds));

			if (object != null) {
				dlFileEntries = (List<DLFileEntry>)object;
			}
		}
		catch (Exception e) {
			List<DLFileEntry> allDlFileEntries =
				DLFileEntryLocalServiceUtil.getDDMStructureFileEntries(
					ArrayUtil.toLongArray(descendantDDMStructureIds));

			for (DLFileEntry dlFileEntry : allDlFileEntries) {
				if (groupId == dlFileEntry.getGroupId()) {
					dlFileEntries.add(dlFileEntry);
				}
			}
		}

		Map<?, ?> dlFileEntryPrimaryKeysMap = newPrimaryKeysMaps.get(
			DLFileEntry.class.getName());

		Indexer dlFileEntryIndexer = IndexerRegistryUtil.getIndexer(
			DLFileEntry.class);

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			if (containsValue(
					dlFileEntryPrimaryKeysMap, dlFileEntry.getFileEntryId())) {

				continue;
			}

			dlFileEntryIndexer.reindex(dlFileEntry);
		}
	}

	protected boolean containsValue(Map<?, ?> map, long value) {
		if ((map == null) || map.isEmpty() || (value <= 0)) {
			return false;
		}

		for (Object object : map.values()) {
			if (GetterUtil.getLong(object) == value) {
				return true;
			}
		}

		return false;
	}

	protected void getDescendantDDMStructureIds(
			List<Long> ddmStructureIds, long groupId, long parentDDMStructureId)
		throws PortalException, SystemException {

		DynamicQuery query = DDMStructureLocalServiceUtil.dynamicQuery();

		Property groupProperty = PropertyFactoryUtil.forName("groupId");

		query.add(groupProperty.eq(groupId));

		Property parentStructureIdProperty = PropertyFactoryUtil.forName(
			"parentStructureId");

		query.add(parentStructureIdProperty.eq(parentDDMStructureId));

		List<DDMStructure> ddmStructures =
				DDMStructureLocalServiceUtil.dynamicQuery(query);

		for (DDMStructure ddmStructure : ddmStructures) {
			ddmStructureIds.add(ddmStructure.getStructureId());

			getDescendantDDMStructureIds(
				ddmStructureIds, ddmStructure.getGroupId(),
				ddmStructure.getStructureId());
		}
	}

	protected List<Long> getDescendantDDMStructureIds(
			long groupId, long ddmStructureId)
		throws PortalException, SystemException {

		List<Long> ddmStructureIds = new ArrayList<Long>();

		getDescendantDDMStructureIds(ddmStructureIds, groupId, ddmStructureId);

		ddmStructureIds.add(0, ddmStructureId);

		return ddmStructureIds;
	}

	@Override
	public String handleException(BackgroundTask backgroundTask, Exception e) {
		StringBundler sb = new StringBundler(4);

		sb.append("Indexing failed after importing with the following error: ");
		sb.append(e.getMessage());
		sb.append(StringPool.PERIOD);
		sb.append(StringPool.SPACE);
		sb.append("Please reindex site manually.");

		String message = sb.toString();

		if (_log.isInfoEnabled()) {
			_log.info(message);
		}

		return message;
	}

	private static Log _log = LogFactoryUtil.getLog(
		StagingIndexingBackgroundTaskExecutor.class);

}