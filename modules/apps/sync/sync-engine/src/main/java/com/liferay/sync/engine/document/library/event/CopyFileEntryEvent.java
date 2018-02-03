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

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.CopyFileEntryHandler;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
<<<<<<< HEAD
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Paths;
=======
>>>>>>> compatible

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class CopyFileEntryEvent extends BaseEvent {

	public CopyFileEntryEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.COPY_FILE_ENTRY, parameters);

		_handler = new CopyFileEntryHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

<<<<<<< HEAD
		syncFile.setPreviousModifiedTime(
			FileUtil.getLastModifiedTime(
				Paths.get(syncFile.getFilePathName())));
=======
>>>>>>> compatible
		syncFile.setState(SyncFile.STATE_IN_PROGRESS);
		syncFile.setUiEvent(SyncFile.UI_EVENT_UPLOADING);

		SyncFileService.update(syncFile);

		processAsynchronousRequest();
	}

	private final Handler<Void> _handler;

}