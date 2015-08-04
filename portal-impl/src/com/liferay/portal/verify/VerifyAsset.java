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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Douglas Wong
 * @author Bryan Engler
 */
public class VerifyAsset extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		rebuildTree();

		updateAssetClassTypeId();
	}

	protected long getDDMStructureId(String structureKey) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select structureId from DDMStructure where structureKey = ?");

			ps.setString(1, structureKey);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("structureId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateAssetClassTypeId() throws Exception {
		long classNameId = PortalUtil.getClassNameId(JournalArticle.class);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select resourcePrimKey, structureId from JournalArticle " +
					"where structureId != ''");

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				String structureId = rs.getString("structureId");

				long ddmStructureId = getDDMStructureId(structureId);

				runSQL(
					"update AssetEntry set classTypeId = " + ddmStructureId +
						" where classNameId = " + classNameId +
							" and classPK = " + resourcePrimKey);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void rebuildTree() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select distinct groupId from AssetCategory where " +
					"(leftCategoryId is null) or (rightCategoryId is null)");

			rs = ps.executeQuery();

			while (rs.next()) {
				long groupId = rs.getLong("groupId");

				AssetCategoryLocalServiceUtil.rebuildTree(groupId, true);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}