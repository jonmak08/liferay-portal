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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergio Sanchez
 * @author Zsolt Berentey
 * @author Daniel Sanz
 */
public class UpgradeSocial extends UpgradeProcess {

	protected String createExtraData(
			ExtraDataFactory extraDataFactory, long companyId, long groupId,
			long userId, long classNameId, long classPK, int type,
			String extraData)
		throws Exception {

		if (extraDataFactory == null) {
			return null;
		}

		try (PreparedStatement ps = connection.prepareStatement(
				extraDataFactory.getSQL())) {

			extraDataFactory.setModelSQLParameters(
				ps, groupId, companyId, userId, classNameId, classPK, type,
				extraData);

			try (ResultSet rs = ps.executeQuery()) {
				JSONObject extraDataJSONObject = null;

				while (rs.next()) {
					extraDataJSONObject =
						extraDataFactory.createExtraDataJSONObject(
							rs, extraData);
				}

				return extraDataJSONObject.toString();
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateJournalActivities();
		updateSOSocialActivities();

		updateActivities();
	}

	protected Map<Long, String> createExtraDataMap(
			ExtraDataFactory extraDataFactory)
		throws Exception {

		Map<Long, String> extraDataMap = new HashMap<>();

		StringBundler sb = new StringBundler(4);

		sb.append("select activityId, groupId, companyId, userId, ");
		sb.append("classNameId, classPK, type_, extraData from ");
		sb.append("SocialActivity where ");
		sb.append(extraDataFactory.getActivitySQLWhereClause());

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			extraDataFactory.setActivitySQLParameters(ps);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long activityId = rs.getLong("activityId");
					long classNameId = rs.getLong("classNameId");
					long classPK = rs.getLong("classPK");
					long companyId = rs.getLong("companyId");
					String extraData = rs.getString("extraData");
					long groupId = rs.getLong("groupId");
					int type = rs.getInt("type_");
					long userId = rs.getLong("userId");

					String newExtraData = createExtraData(
						extraDataFactory, groupId, companyId, userId,
						classNameId, classPK, type, extraData);

					if (newExtraData != null) {
						extraDataMap.put(activityId, newExtraData);
					}
				}
			}
		}

		return extraDataMap;
	}

	protected void updateActivities() throws Exception {
		ExtraDataFactory[] extraDataFactories = {
			new AddAssetCommentExtraDataFactory(),
			new AddMessageExtraDataFactory(), new BlogsEntryExtraDataFactory(),
			new BookmarksEntryExtraDataFactory(),
			new DLFileEntryExtraDataFactory(), new KBArticleExtraDataFactory(),
			new KBCommentExtraDataFactory(), new KBTemplateExtraDataFactory(),
			new WikiPageExtraDataFactory()
		};

		for (ExtraDataFactory extraDataFactory : extraDataFactories) {
			updateActivities(extraDataFactory);
		}
	}

	protected void updateActivities(ExtraDataFactory extraDataFactory)
		throws Exception {

		Map<Long, String> extraDataMap = createExtraDataMap(extraDataFactory);

		for (Map.Entry<Long, String> entry : extraDataMap.entrySet()) {
			long activityId = entry.getKey();
			String extraData = entry.getValue();

			try (PreparedStatement ps = connection.prepareStatement(
					"update SocialActivity set extraData = ? where " + 
						"activityId = ?")) {

				ps.setString(1, extraData);
				ps.setLong(2, activityId);

				ps.executeUpdate();
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to update activity " + activityId, e);
				}
			}
		}
	}

	protected void updateJournalActivities() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				"com.liferay.portlet.journal.model.JournalArticle");

			String[] tableNames = {"SocialActivity", "SocialActivityCounter"};

			for (String tableName : tableNames) {
				StringBundler sb = new StringBundler(7);

				sb.append("update ");
				sb.append(tableName);
				sb.append(" set classPK = (select resourcePrimKey ");
				sb.append("from JournalArticle where id_ = ");
				sb.append(tableName);
				sb.append(".classPK) where classNameId = ");
				sb.append(classNameId);

				runSQL(sb.toString());
			}
		}
	}

	protected void updateSOSocialActivities() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (!hasTable("SO_SocialActivity")) {
				return;
			}

			try (PreparedStatement ps = connection.prepareStatement(
					"select activityId, activitySetId from SO_SocialActivity");
				ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					long activityId = rs.getLong("activityId");
					long activitySetId = rs.getLong("activitySetId");

					StringBundler sb = new StringBundler(4);

					sb.append("update SocialActivity set activitySetId = ");
					sb.append(activitySetId);
					sb.append(" where activityId = ");
					sb.append(activityId);

					runSQL(sb.toString());
				}
			}

			runSQL("drop table SO_SocialActivity");
		}
	}

	protected interface ExtraDataFactory {

		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException;

		public String getActivityClassName();

		public String getActivitySQLWhereClause();

		public String getSQL();

		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException;

		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException;

	}

	private static final Log _log = LogFactoryUtil.getLog(UpgradeSocial.class);

	private class AddAssetCommentExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			long messageId = 0;

			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					extraData);

				messageId = jsonObject.getLong("messageId");
			}
			catch (JSONException jsone) {
			}

			extraDataJSONObject.put("messageId", messageId);

			extraDataJSONObject.put(
				"title", entityResultSet.getString("subject"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return StringPool.BLANK;
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "type_ = ?";
		}

		@Override
		public String getSQL() {
			return "select subject from MBMessage where messageId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setInt(1, _TYPE_ADD_COMMENT);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			long messageId = 0;

			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					extraData);

				messageId = jsonObject.getLong("messageId");
			}
			catch (JSONException jsone) {
			}

			ps.setLong(1, messageId);
		}

		private static final int _TYPE_ADD_COMMENT = 10005;

	};

	private class AddMessageExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("subject"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.portlet.messageboards.model.MBMessage";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select subject from MBMessage where messageId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_MESSAGE);
			ps.setInt(3, _REPLY_MESSAGE);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_MESSAGE = 1;

		private static final int _REPLY_MESSAGE = 2;

	};

	private class BlogsEntryExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("title"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.portlet.blogs.model.BlogsEntry";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select title from BlogsEntry where entryId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_ENTRY);
			ps.setInt(3, _UPDATE_ENTRY);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_ENTRY = 2;

		private static final int _UPDATE_ENTRY = 3;

	};

	private class BookmarksEntryExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put("title", entityResultSet.getString("name"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.portlet.bookmarks.model.BookmarksEntry";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select name from BookmarksEntry where entryId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_ENTRY);
			ps.setInt(3, _UPDATE_ENTRY);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_ENTRY = 1;

		private static final int _UPDATE_ENTRY = 2;

	};

	private class DLFileEntryExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("title"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.portlet.documentlibrary.model.DLFileEntry";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ?";
		}

		@Override
		public String getSQL() {
			return "select title from DLFileEntry where companyId = ? " +
				"and groupId = ? and fileEntryId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, companyId);
			ps.setLong(2, groupId);
			ps.setLong(3, classPK);
		}

	};

	private class KBArticleExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("title"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.knowledgebase.model.KBArticle";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select title from KBArticle where resourcePrimKey = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_KB_ARTICLE);
			ps.setInt(3, _UPDATE_KB_ARTICLE);
			ps.setInt(4, _MOVE_KB_ARTICLE);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_KB_ARTICLE = 1;

		private static final int _MOVE_KB_ARTICLE = 7;

		private static final int _UPDATE_KB_ARTICLE = 3;

	};

	private class KBCommentExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = null;

			long classnameId = entityResultSet.getLong("classNameId");
			long classpk = entityResultSet.getLong("classPK");

			ExtraDataFactory extraDataFactory = null;

			if (classnameId == PortalUtil.getClassNameId(
					_kbArticleExtraDataFactory.getActivityClassName())) {

				extraDataFactory = _kbArticleExtraDataFactory;
			}
			else if (classnameId == PortalUtil.getClassNameId(
						_kbTemplateExtraDataFactory.getActivityClassName())) {

				extraDataFactory = _kbTemplateExtraDataFactory;
			}

			if (extraDataFactory != null) {
				try (PreparedStatement ps = connection.prepareStatement(
						extraDataFactory.getSQL())) {

					ps.setLong(1, classpk);

					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							extraDataJSONObject =
								extraDataFactory.createExtraDataJSONObject(
									rs, StringPool.BLANK);
						}
					}
				}
			}

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.knowledgebase.model.KBComment";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select classNameId, classPK from KBComment where " +
				"kbCommentId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_KB_COMMENT);
			ps.setInt(3, _UPDATE_KB_COMMENT);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_KB_COMMENT = 5;

		private static final int _UPDATE_KB_COMMENT = 6;

		private final KBArticleExtraDataFactory _kbArticleExtraDataFactory =
			new KBArticleExtraDataFactory();
		private final KBTemplateExtraDataFactory _kbTemplateExtraDataFactory =
			new KBTemplateExtraDataFactory();

	};

	private class KBTemplateExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("title"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.knowledgebase.model.KBTemplate";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select title from KBTemplate where kbTemplateId = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_KB_TEMPLATE);
			ps.setInt(3, _UPDATE_KB_TEMPLATE);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, classPK);
		}

		private static final int _ADD_KB_TEMPLATE = 2;

		private static final int _UPDATE_KB_TEMPLATE = 4;

	};

	private class WikiPageExtraDataFactory implements ExtraDataFactory {

		@Override
		public JSONObject createExtraDataJSONObject(
				ResultSet entityResultSet, String extraData)
			throws SQLException {

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put(
				"title", entityResultSet.getString("title"));
			extraDataJSONObject.put(
				"version", entityResultSet.getDouble("version"));

			return extraDataJSONObject;
		}

		@Override
		public String getActivityClassName() {
			return "com.liferay.portlet.wiki.model.WikiPage";
		}

		@Override
		public String getActivitySQLWhereClause() {
			return "classNameId = ? and (type_ = ? or type_ = ?)";
		}

		@Override
		public String getSQL() {
			return "select title, version from WikiPage where companyId = ? " +
				"and groupId = ? and resourcePrimKey = ? and head = ?";
		}

		@Override
		public void setActivitySQLParameters(PreparedStatement ps)
			throws SQLException {

			ps.setLong(1, PortalUtil.getClassNameId(getActivityClassName()));
			ps.setInt(2, _ADD_PAGE);
			ps.setInt(3, _UPDATE_PAGE);
		}

		@Override
		public void setModelSQLParameters(
				PreparedStatement ps, long companyId, long groupId, long userId,
				long classNameId, long classPK, int type, String extraData)
			throws SQLException {

			ps.setLong(1, companyId);
			ps.setLong(2, groupId);
			ps.setLong(3, classPK);
			ps.setBoolean(4, true);
		}

		private static final int _ADD_PAGE = 1;

		private static final int _UPDATE_PAGE = 2;

	};

}