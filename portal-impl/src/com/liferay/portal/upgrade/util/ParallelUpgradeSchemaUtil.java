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

package com.liferay.portal.upgrade.util;

<<<<<<< HEAD
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
=======
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.executor.PortalExecutorManagerUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
>>>>>>> compatible

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
<<<<<<< HEAD
import java.util.concurrent.ExecutorService;
=======
>>>>>>> compatible
import java.util.concurrent.Future;

/**
 * @author Miguel Pastor
 */
public class ParallelUpgradeSchemaUtil {

	public static void execute(String... sqlFileNames) throws Exception {
<<<<<<< HEAD
		ExecutorService executorService =
			_portalExecutorManager.getPortalExecutor(
=======
		ThreadPoolExecutor threadPoolExecutor =
			PortalExecutorManagerUtil.getPortalExecutor(
>>>>>>> compatible
				ParallelUpgradeSchemaUtil.class.getName());

		List<Future<Void>> futures = new ArrayList<>(sqlFileNames.length);

		try {
			for (String sqlFileName : sqlFileNames) {
				futures.add(
<<<<<<< HEAD
					executorService.submit(
=======
					threadPoolExecutor.submit(
>>>>>>> compatible
						new CallableSQLExecutor(sqlFileName)));
			}

			for (Future<Void> future : futures) {
				future.get();
			}
		}
		finally {
<<<<<<< HEAD
			executorService.shutdown();
		}
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, ParallelUpgradeSchemaUtil.class,
			"_portalExecutorManager", true);

=======
			threadPoolExecutor.shutdown();
		}
	}

>>>>>>> compatible
	private static class CallableSQLExecutor implements Callable<Void> {

		@Override
		public Void call() throws Exception {
			DB db = DBManagerUtil.getDB();

			try (LoggingTimer loggingTimer = new LoggingTimer(_sqlFileName)) {
				db.runSQLTemplate(_sqlFileName, false);
			}

			return null;
		}

		private CallableSQLExecutor(String sqlFileName) {
			_sqlFileName = sqlFileName;
		}

		private final String _sqlFileName;

	}

}