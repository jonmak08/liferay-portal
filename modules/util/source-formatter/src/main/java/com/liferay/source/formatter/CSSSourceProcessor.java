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

package com.liferay.source.formatter;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class CSSSourceProcessor extends BaseSourceProcessor {

	@Override
	protected List<String> doGetFileNames() throws Exception {
		String[] excludes = {
			"**/.ivy/**", "**/.sass-cache/**", "**/__MACOSX/**",
<<<<<<< HEAD
			"**/_partial.scss", "**/_styled/css/compat/third_party/**",
			"**/_unstyled/css/**", "**/aui/**", "**/aui_deprecated.css",
			"**/bourbon/**", "**/converter/dependencies/**", "**/expected/**",
=======
			"**/_partial.scss", "**/_unstyled/css/**", "**/aui/**",
			"**/aui_deprecated.css", "**/bourbon/**",
			"**/converter/dependencies/**", "**/expected/**",
>>>>>>> compatible
			"**/frontend-editors-web/**", "**/tools/node**"
		};

		return getFileNames(excludes, getIncludes());
	}

	@Override
	protected String[] doGetIncludes() {
		return _INCLUDES;
	}

	private static final String[] _INCLUDES = {"**/*.css", "**/*.scss"};

}