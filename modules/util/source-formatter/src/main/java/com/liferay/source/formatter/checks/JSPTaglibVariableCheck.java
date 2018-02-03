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

package com.liferay.source.formatter.checks;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JSPTaglibVariableCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

<<<<<<< HEAD
		return _formatTaglibVariable(fileName, content);
=======
		content = _formatTaglibVariable(fileName, content);

		return content;
>>>>>>> compatible
	}

	private String _formatTaglibVariable(String fileName, String content) {
		Matcher matcher = _taglibVariablePattern.matcher(content);

		while (matcher.find()) {
<<<<<<< HEAD
			String nextTag = matcher.group(4);
			String taglibValue = matcher.group(3);
			String variableName = matcher.group(2);
=======
			String taglibValue = matcher.group(3);
>>>>>>> compatible

			if (taglibValue.contains("\\\"") ||
				(taglibValue.contains(StringPool.APOSTROPHE) &&
				 taglibValue.contains(StringPool.QUOTE))) {

<<<<<<< HEAD
				if (!variableName.startsWith("taglib") &&
					(StringUtil.count(content, variableName) == 2) &&
					nextTag.contains("=\"<%= " + variableName + " %>\"")) {

					addMessage(
						fileName,
						"Variable '" + variableName +
							"' should start with 'taglib'",
						getLineCount(content, matcher.start()));
				}

				continue;
			}

			if (!variableName.startsWith("taglib")) {
				continue;
			}

			if (!nextTag.contains("=\"<%= " + variableName + " %>\"")) {
				addMessage(
					fileName,
					"No need to specify taglib variable '" + variableName + "'",
=======
				continue;
			}

			String taglibName = matcher.group(2);
			String nextTag = matcher.group(4);

			if (!nextTag.contains(taglibName)) {
				addMessage(
					fileName,
					"No need to specify taglib variable '" + taglibName + "'",
>>>>>>> compatible
					getLineCount(content, matcher.start()));

				continue;
			}

			content = StringUtil.replaceFirst(
<<<<<<< HEAD
				content, variableName, taglibValue, matcher.start(4));
=======
				content, taglibName, taglibValue, matcher.start(4));
>>>>>>> compatible

			return content = StringUtil.replaceFirst(
				content, matcher.group(1), StringPool.BLANK, matcher.start());
		}

		return content;
	}

	private final Pattern _taglibVariablePattern = Pattern.compile(
<<<<<<< HEAD
		"(\t*String (\\w+) = (.*);)\n\\s*%>\\s+(<[\\S\\s]*?>)\n");
=======
		"(\n\t*String (taglib\\w+) = (.*);)\n\\s*%>\\s+(<[\\S\\s]*?>)\n");
>>>>>>> compatible

}