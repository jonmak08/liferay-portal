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

package com.liferay.jenkins.results.parser.failure.message.generator;

import com.liferay.jenkins.results.parser.Build;
import com.liferay.jenkins.results.parser.Dom4JUtil;

<<<<<<< HEAD
=======
import java.util.Hashtable;

>>>>>>> compatible
import org.dom4j.Element;

/**
 * @author Peter Yoo
 * @author Michael Hashimoto
 * @author Yi-Chen Tsai
 */
public class SemanticVersioningFailureMessageGenerator
	extends BaseFailureMessageGenerator {

	@Override
<<<<<<< HEAD
=======
	public String getMessage(
		String buildURL, String consoleOutput, Hashtable<?, ?> properties) {

		if (!consoleOutput.contains(_TOKEN_SEMVER_INCORRECT) ||
			!consoleOutput.contains(_TOKEN_SEMVER_PACKAGE)) {

			return null;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<p>Please fix <strong>semantic versioning</strong> on ");
		sb.append("<strong><a href=\"https://github.com/");
		sb.append(properties.get("github.origin.name"));
		sb.append("/");
		sb.append(properties.get("repository"));
		sb.append("/tree/");
		sb.append(properties.get("github.sender.branch.name"));
		sb.append("\">");
		sb.append(properties.get("github.origin.name"));
		sb.append("/");
		sb.append(properties.get("github.sender.branch.name"));
		sb.append("</a></strong>.</p>");

		int end = consoleOutput.indexOf(_TOKEN_SEMVER_INCORRECT);

		end = consoleOutput.indexOf("\n", end);

		int start = consoleOutput.lastIndexOf(_TOKEN_BASELINE_CHECK, end);

		start = consoleOutput.indexOf(_TOKEN_SEMVER_PACKAGE, start);

		start = consoleOutput.lastIndexOf("\n", start);

		sb.append(getConsoleOutputSnippet(consoleOutput, true, start, end));

		return sb.toString();
	}

	@Override
>>>>>>> compatible
	public Element getMessageElement(Build build) {
		String consoleText = build.getConsoleText();

		if (!consoleText.contains(_TOKEN_SEMVER_INCORRECT) ||
			!consoleText.contains(_TOKEN_SEMVER_PACKAGE)) {

			return null;
		}

		int end = consoleText.indexOf(_TOKEN_SEMVER_INCORRECT);

		end = consoleText.indexOf("\n", end);

		int start = consoleText.lastIndexOf(_TOKEN_BASELINE_CHECK, end);

		start = consoleText.indexOf(_TOKEN_SEMVER_PACKAGE, start);

		start = consoleText.lastIndexOf("\n", start);

		return Dom4JUtil.getNewElement(
			"div", null,
			Dom4JUtil.getNewElement(
				"p", null, "Please fix ",
				Dom4JUtil.getNewElement("strong", null, "semantic versioning"),
				" on ",
				Dom4JUtil.getNewElement(
					"strong", null,
					getBaseBranchAnchorElement(build.getTopLevelBuild())),
<<<<<<< HEAD
				getConsoleTextSnippetElement(consoleText, true, start, end)));
=======
				getConsoleOutputSnippetElement(consoleText, true, start, end)));
>>>>>>> compatible
	}

	private static final String _TOKEN_BASELINE_CHECK =
		"Checking for baseline log files";

	private static final String _TOKEN_SEMVER_INCORRECT =
		"Semantic versioning is incorrect";

	private static final String _TOKEN_SEMVER_PACKAGE = "PACKAGE_NAME";

}