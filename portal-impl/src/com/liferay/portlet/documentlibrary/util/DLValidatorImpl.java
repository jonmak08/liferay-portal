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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.FolderNameException;
import com.liferay.document.library.kernel.exception.InvalidFileVersionException;
import com.liferay.document.library.kernel.exception.SourceFileNameException;
<<<<<<< HEAD
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
=======
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.DLValidator;
>>>>>>> compatible
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
<<<<<<< HEAD
=======
import com.liferay.portlet.documentlibrary.webdav.DLWebDAVUtil;
>>>>>>> compatible

import java.io.File;
import java.io.InputStream;

/**
 * @author     Adolfo Pérez
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.document.library.internal.util.DLValidatorImpl}
 */
@Deprecated
public final class DLValidatorImpl implements DLValidator {

	@Override
	public String fixName(String name) {
<<<<<<< HEAD
		return DLValidatorUtil.fixName(name);
	}
=======
		if (Validator.isNull(name)) {
			return StringPool.UNDERLINE;
		}

		for (String blacklistChar : PropsValues.DL_CHAR_BLACKLIST) {
			name = StringUtil.replace(
				name, blacklistChar, StringPool.UNDERLINE);
		}

		name = replaceDLCharLastBlacklist(name);

		return replaceDLNameBlacklist(name);
	}

	@Override
	public boolean isValidName(String name) {
		if (Validator.isNull(name)) {
			return false;
		}

		for (String blacklistChar : PropsValues.DL_CHAR_BLACKLIST) {
			if (name.contains(blacklistChar)) {
				return false;
			}
		}

		for (String blacklistLastChar : PropsValues.DL_CHAR_LAST_BLACKLIST) {
			if (blacklistLastChar.startsWith(UnicodeFormatter.UNICODE_PREFIX)) {
				blacklistLastChar = UnicodeFormatter.parseString(
					blacklistLastChar);
			}

			if (name.endsWith(blacklistLastChar)) {
				return false;
			}
		}

		String nameWithoutExtension = FileUtil.stripExtension(name);

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {
>>>>>>> compatible

	@Override
	public long getMaxAllowableSize() {
		return DLValidatorUtil.getMaxAllowableSize();
	}

	@Override
	public boolean isValidName(String name) {
		return DLValidatorUtil.isValidName(name);
	}

	@Override
	public void validateDirectoryName(String directoryName)
		throws FolderNameException {

		DLValidatorUtil.validateDirectoryName(directoryName);
	}

	@Override
	public void validateFileExtension(String fileName)
		throws FileExtensionException {

		DLValidatorUtil.validateFileExtension(fileName);
	}

	@Override
	public void validateFileName(String fileName) throws FileNameException {
<<<<<<< HEAD
		DLValidatorUtil.validateFileName(fileName);
=======
		if (!isValidName(fileName)) {
			throw new FileNameException(fileName);
		}

		if (!DLWebDAVUtil.isRepresentableTitle(fileName)) {
			throw new FileNameException(
				"Unrepresentable WebDAV title for file name " + fileName);
		}
>>>>>>> compatible
	}

	@Override
	public void validateFileSize(String fileName, byte[] bytes)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, bytes);
	}

	@Override
	public void validateFileSize(String fileName, File file)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, file);
	}

	@Override
	public void validateFileSize(String fileName, InputStream is)
		throws FileSizeException {

<<<<<<< HEAD
		DLValidatorUtil.validateFileSize(fileName, is);
=======
		try {
			if (is == null) {
				throw new FileSizeException(fileName);
			}

			validateFileSize(fileName, is.available());
		}
		catch (IOException ioe) {
			throw new FileSizeException(ioe);
		}
>>>>>>> compatible
	}

	@Override
	public void validateFileSize(String fileName, long size)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, size);
	}

	@Override
	public void validateSourceFileExtension(
			String fileExtension, String sourceFileName)
		throws SourceFileNameException {

		DLValidatorUtil.validateSourceFileExtension(
			fileExtension, sourceFileName);
	}

	@Override
	public void validateVersionLabel(String versionLabel)
		throws InvalidFileVersionException {

		DLValidatorUtil.validateVersionLabel(versionLabel);
	}

	protected String replaceDLCharLastBlacklist(String title) {
		String previousTitle = null;

<<<<<<< HEAD
		while (!title.equals(previousTitle)) {
			previousTitle = title;

			for (String blacklistLastChar :
					PropsValues.DL_CHAR_LAST_BLACKLIST) {

				if (blacklistLastChar.startsWith(
						UnicodeFormatter.UNICODE_PREFIX)) {

					blacklistLastChar = UnicodeFormatter.parseString(
						blacklistLastChar);
				}

				if (title.endsWith(blacklistLastChar)) {
					title = StringUtil.replaceLast(
						title, blacklistLastChar, StringPool.BLANK);
				}
			}
=======
		if (!DLUtil.isValidVersion(versionLabel)) {
			throw new InvalidFileVersionException(
				"Invalid version label " + versionLabel);
>>>>>>> compatible
		}

		return title;
	}

<<<<<<< HEAD
=======
	protected String replaceDLCharLastBlacklist(String title) {
		String previousTitle = null;

		while (!title.equals(previousTitle)) {
			previousTitle = title;

			for (String blacklistLastChar :
					PropsValues.DL_CHAR_LAST_BLACKLIST) {

				if (blacklistLastChar.startsWith(
						UnicodeFormatter.UNICODE_PREFIX)) {

					blacklistLastChar = UnicodeFormatter.parseString(
						blacklistLastChar);
				}

				if (title.endsWith(blacklistLastChar)) {
					title = StringUtil.replaceLast(
						title, blacklistLastChar, StringPool.BLANK);
				}
			}
		}

		return title;
	}

>>>>>>> compatible
	protected String replaceDLNameBlacklist(String title) {
		String extension = FileUtil.getExtension(title);
		String nameWithoutExtension = FileUtil.stripExtension(title);

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {

				if (Validator.isNull(extension)) {
					return nameWithoutExtension + StringPool.UNDERLINE;
				}

<<<<<<< HEAD
				return StringBundler.concat(
					nameWithoutExtension, StringPool.UNDERLINE,
					StringPool.PERIOD, extension);
=======
				return nameWithoutExtension + StringPool.UNDERLINE +
					StringPool.PERIOD + extension;
>>>>>>> compatible
			}
		}

		return title;
	}

}