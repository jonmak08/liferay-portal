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

        //Special-Handling for DB that do not support milliseconds
        if (!db.isSupportsDateMilliseconds()) {
            String dbType = db.getType();

            //MYSQL Date Handling
            if (dbType.equals(DB.TYPE_MYSQL)) {
                long modifiedDateTime = date.getTime();

                long milliseconds = modifiedDateTime % 1000;

                //Only need to handle if milliseconds != 0
                if (milliseconds > 0) {
                    Connection connection = null;

                    int dbMajorVersion = -1;
                    int dbMinorVersion = -1;

                    try {
                        connection = DataAccess.getConnection();

                        DatabaseMetaData databaseMetaData =
                                connection.getMetaData();

                        dbMajorVersion =
                                databaseMetaData.getDatabaseMajorVersion();
                        dbMinorVersion =
                                databaseMetaData.getDatabaseMinorVersion();
                    }
                    catch (SQLException e) {
                        _log.error(
                                "Error while retrieving database version.", e);
                    }
                    finally {
                        DataAccess.cleanUp(connection);
                    }

                    if ((dbMajorVersion != -1) && (dbMinorVersion != -1)) {
                        //Truncate if MYSQL version == 5.5
                        if ((dbMajorVersion == 5) && (dbMinorVersion == 5)) {
                            modifiedDateTime -= milliseconds;
                        }
                        //Round down milliseconds
                        else if (Math.round((double)milliseconds/1000) == 0) {
                            modifiedDateTime -= milliseconds;
                        }
                        //Round up milliseconds
                        else {
                            modifiedDateTime += (1000 - milliseconds);
                        }

                        return new Date(modifiedDateTime);
                    }
                }
            }
        }

        return date;
    }

    private static Log _log = LogFactoryUtil.getLog(
        MBMessageDateDigesterUtil.class);

}
