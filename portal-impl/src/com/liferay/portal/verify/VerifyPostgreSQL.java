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

import com.liferay.portal.dao.db.PostgreSQLDB;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Bowerman
 */
public class VerifyPostgreSQL extends VerifyProcess {

	protected void deleteOrphanedLargeObjects(
			HashMap<String, String> oidColumnNames)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler();

			sb.append("select lo_unlink(l.oid) from pg_largeobject_metadata ");
			sb.append("l where ");

			int i = 1;

			for (Map.Entry<String, String> column : oidColumnNames.entrySet()) {
				String tableName = column.getKey();
				String columnName = column.getValue();

				sb.append("(not exists (select 1 from ");
				sb.append(tableName);
				sb.append(" t where t.");
				sb.append(columnName);
				sb.append(" = l.oid))");

				if (i < oidColumnNames.size()) {
					sb.append(" and ");
				}

				i++;
			}

			ps = con.prepareStatement(sb.toString());

			ps.executeQuery();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doVerify() throws Exception {
		DB db = DBFactoryUtil.getDB();

		String dbType = db.getType();

		if (!dbType.equals(DB.TYPE_POSTGRESQL)) {
			return;
		}

		HashMap<String, String> oidColumnNames = getOidColumnNames();

		verifyRules(oidColumnNames);
		deleteOrphanedLargeObjects(oidColumnNames);
	}

	protected HashMap<String, String> getOidColumnNames() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		HashMap<String, String> oidColumnNames = new HashMap<String, String>();

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(3);

			sb.append("select table_name, column_name from ");
			sb.append("information_schema.columns where ");
			sb.append("table_schema='public' and data_type='oid';");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				String tableName = (String) rs.getObject("table_name");
				String columnName = (String) rs.getObject("column_name");

				oidColumnNames.put(tableName, columnName);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return oidColumnNames;
	}

	protected void verifyRules(HashMap<String, String> oidColumnNames)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		for (Map.Entry<String, String> column : oidColumnNames.entrySet()) {
			String tableName = column.getKey();
			String columnName = column.getValue();

			try {
				con = DataAccess.getUpgradeOptimizedConnection();

				ps = con.prepareStatement(
					PostgreSQLDB.getCreateRulesSql(tableName, columnName));

				ps.executeUpdate();
			}
			finally {
				DataAccess.cleanUp(con, ps);
			}
		}
	}

}