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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.DuplicateLockException;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.LockLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class SerialBackgroundTaskExecutor
	extends DelegatingBackgroundTaskExecutor {

	public SerialBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor) {

		super(backgroundTaskExecutor);
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Lock lock = null;

		String owner =
			backgroundTask.getName() + StringPool.POUND +
				backgroundTask.getBackgroundTaskId();

		try {
			if (isSerial()) {
				lock = acquireLock(backgroundTask, owner);
			}

			BackgroundTaskExecutor backgroundTaskExecutor =
				getBackgroundTaskExecutor();

			return backgroundTaskExecutor.execute(backgroundTask);
		}
		finally {
			if (lock != null) {
				LockLocalServiceUtil.unlock(
					BackgroundTaskExecutor.class.getName(),
					backgroundTask.getTaskExecutorClassName(), owner);
			}
		}
	}

	protected Lock acquireLock(BackgroundTask backgroundTask, String owner)
		throws DuplicateLockException {

		Lock lock = null;

		while (true) {
			try {
				lock = LockLocalServiceUtil.lock(
					BackgroundTaskExecutor.class.getName(),
					backgroundTask.getTaskExecutorClassName(), owner);

				break;
			}
			catch (ORMException e) {
				handleLockException(e);
			}
			catch (SystemException se) {
				handleLockException(se);
			}
		}

		if (!lock.isNew()) {
			throw new DuplicateLockException(lock);
		}

		return lock;
	}

	protected void handleLockException(Exception e) {
		if (_log.isDebugEnabled()) {
			_log.debug("Unable to acquire acquiring lock", e);
		}

		try {
			Thread.sleep(50);
		}
		catch (InterruptedException ie) {
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SerialBackgroundTaskExecutor.class);

}