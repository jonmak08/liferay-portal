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

package com.liferay.portlet.messageboards.util;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import java.util.Date;

/**
 * @author Eric Yan
 */
public class MBMessageDateDigesterUtil {

	public static Date digest(Date date) {
		DB db = DBFactoryUtil.getDB();

		if (db.isSupportsDateMilliseconds()) {
			return date;
		}

		String dbType = db.getType();

		if (dbType.equals(DB.TYPE_MYSQL)) {
			return _digestMySQLDate(date);
		}

		return date;
	}

	private static Date _digestMySQLDate(Date date) {
		long time = date.getTime();

		long milliseconds = time % 1000;

		if (milliseconds > 0) {
			Connection connection = null;

			try {
				connection = DataAccess.getConnection();

				DatabaseMetaData databaseMetaData = connection.getMetaData();

				int dbMajorVersion = databaseMetaData.getDatabaseMajorVersion();
				int dbMinorVersion = databaseMetaData.getDatabaseMinorVersion();

				if (((dbMajorVersion == 5) && (dbMinorVersion == 5)) ||
					(Math.round((double)milliseconds / 1000) == 0)) {

					time -= milliseconds;
				}
				else {
					time += (1000 - milliseconds);
				}

				return new Date(time);
			}
			catch (SQLException se) {
				_log.error("Unable to to get database version", se);
			}
			finally {
				DataAccess.cleanUp(connection);
			}
		}

		return date;
	}

	private static Log _log = LogFactoryUtil.getLog(
		MBMessageDateDigesterUtil.class);

}