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

package com.liferay.document.library.kernel.antivirus;

import com.liferay.portal.kernel.exception.SystemException;
<<<<<<< HEAD
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
=======
import com.liferay.portal.kernel.util.StreamUtil;
>>>>>>> compatible

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> compatible
import java.io.InputStream;

/**
 * @author Michael C. Han
 */
public abstract class BaseInputStreamAntivirusScanner
	implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return _ACTIVE;
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
<<<<<<< HEAD
		try (InputStream inputStream = new FileInputStream(file)) {
=======
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);

>>>>>>> compatible
			scan(inputStream);
		}
		catch (FileNotFoundException fnfe) {
			throw new SystemException("Unable to scan file", fnfe);
		}
<<<<<<< HEAD
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioe, ioe);
			}
=======
		finally {
			StreamUtil.cleanUp(inputStream);
>>>>>>> compatible
		}
	}

	private static final boolean _ACTIVE = true;

<<<<<<< HEAD
	private static final Log _log = LogFactoryUtil.getLog(
		BaseInputStreamAntivirusScanner.class);

=======
>>>>>>> compatible
}