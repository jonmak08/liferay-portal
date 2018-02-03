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

package com.liferay.upload.web.internal;

<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.upload.UniqueFileNameProvider;

import java.util.function.Predicate;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
>>>>>>> compatible

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component
public class DefaultUniqueFileNameProvider implements UniqueFileNameProvider {

	@Override
	public String provide(String fileName, Predicate<String> predicate)
		throws PortalException {

<<<<<<< HEAD
		fileName = _removeParentheticalSuffix(fileName);

		String uniqueFileName = fileName;

		int tries = 0;
=======
		String uniqueFileName = fileName;

		int tries = 1;
>>>>>>> compatible

		while (predicate.test(uniqueFileName)) {
			if (tries >= _UNIQUE_FILE_NAME_TRIES) {
				throw new PortalException(
					"Unable to get a unique file name for " + fileName);
			}

			tries++;

			uniqueFileName = FileUtil.appendParentheticalSuffix(
				fileName, String.valueOf(tries));
		}

		return uniqueFileName;
	}

<<<<<<< HEAD
	private String _removeParentheticalSuffix(String fileName) {
		Matcher matcher = _pattern.matcher(fileName);

		if (matcher.matches()) {
			String name = matcher.group("name");
			String extension = matcher.group("extension");

			fileName = name;

			if (extension != null) {
				fileName += StringPool.PERIOD + extension;
			}
		}

		return fileName;
	}

	private static final int _UNIQUE_FILE_NAME_TRIES = 50;

	private static final Pattern _pattern = Pattern.compile(
		"(?<name>.+) \\(\\d+\\)(\\.(?<extension>[^.]+))?");

=======
	private static final int _UNIQUE_FILE_NAME_TRIES = 50;

>>>>>>> compatible
}