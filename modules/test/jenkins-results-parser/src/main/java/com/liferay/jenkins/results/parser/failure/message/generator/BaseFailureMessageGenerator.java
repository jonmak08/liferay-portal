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
import com.liferay.jenkins.results.parser.TopLevelBuild;

<<<<<<< HEAD
=======
import java.util.Hashtable;
>>>>>>> compatible
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

/**
 * @author Peter Yoo
 */
public abstract class BaseFailureMessageGenerator
	implements FailureMessageGenerator {

	@Override
<<<<<<< HEAD
=======
	public abstract String getMessage(
		String buildURL, String consoleOutput, Hashtable<?, ?> properties);

	@Override
>>>>>>> compatible
	public abstract Element getMessageElement(Build build);

	protected Element getBaseBranchAnchorElement(TopLevelBuild topLevelBuild) {
		StringBuilder sb = new StringBuilder();

		sb.append("https://github.com/");

		String baseRepositoryName = topLevelBuild.getBaseRepositoryName();

		Map<String, String> baseRepositoryGitDetailsTempMap =
			topLevelBuild.getBaseGitRepositoryDetailsTempMap();

		sb.append(baseRepositoryGitDetailsTempMap.get("github.origin.name"));

		sb.append("/");
		sb.append(baseRepositoryName);
		sb.append("/tree/");
		sb.append(
			baseRepositoryGitDetailsTempMap.get("github.sender.branch.name"));

		String url = sb.toString();

		sb = new StringBuilder();

		sb.append(baseRepositoryGitDetailsTempMap.get("github.origin.name"));
		sb.append("/");
		sb.append(
			baseRepositoryGitDetailsTempMap.get("github.sender.branch.name"));

		return Dom4JUtil.getNewAnchorElement(url, sb.toString());
	}

<<<<<<< HEAD
	protected String getConsoleTextSnippet(
		String consoleText, boolean truncateTop, int end) {

		if (end == -1) {
			end = consoleText.length();
		}

		int start = getSnippetStart(consoleText, end);

		return getConsoleTextSnippet(consoleText, truncateTop, start, end);
	}

	protected String getConsoleTextSnippet(
		String consoleText, boolean truncateTop, int start, int end) {

		return "<pre><code>" +
			_getConsoleTextSnippet(consoleText, truncateTop, start, end) +
				"</code></pre>";
	}

	protected Element getConsoleTextSnippetElement(
		String consoleText, boolean truncateTop, int end) {

		if (end == -1) {
			end = consoleText.length();
		}

		int start = getSnippetStart(consoleText, end);

		return getConsoleTextSnippetElement(
			consoleText, truncateTop, start, end);
	}

	protected Element getConsoleTextSnippetElement(
		String consoleText, boolean truncateTop, int start, int end) {

		return Dom4JUtil.toCodeSnippetElement(
			_getConsoleTextSnippet(consoleText, truncateTop, start, end));
=======
	protected String getConsoleOutputSnippet(
		String consoleOutput, boolean truncateTop, int end) {

		if (end == -1) {
			end = consoleOutput.length();
		}

		int start = getSnippetStart(consoleOutput, end);

		return getConsoleOutputSnippet(consoleOutput, truncateTop, start, end);
	}

	protected String getConsoleOutputSnippet(
		String consoleOutput, boolean truncateTop, int start, int end) {

		return "<pre><code>" +
			_getConsoleOutputSnippet(consoleOutput, truncateTop, start, end) +
				"</code></pre>";
	}

	protected Element getConsoleOutputSnippetElement(
		String consoleOutput, boolean truncateTop, int end) {

		if (end == -1) {
			end = consoleOutput.length();
		}

		int start = getSnippetStart(consoleOutput, end);

		return getConsoleOutputSnippetElement(
			consoleOutput, truncateTop, start, end);
	}

	protected Element getConsoleOutputSnippetElement(
		String consoleOutput, boolean truncateTop, int start, int end) {

		return Dom4JUtil.toCodeSnippetElement(
			_getConsoleOutputSnippet(consoleOutput, truncateTop, start, end));
>>>>>>> compatible
	}

	protected Element getGitCommitPluginsAnchorElement(
		TopLevelBuild topLevelBuild) {

		String repositoryName = topLevelBuild.getBaseRepositoryName();

		Map<String, String> portalRepositoryGitDetailsTempMap =
			topLevelBuild.getBaseGitRepositoryDetailsTempMap();

		Element gitCommitPluginsAnchorElement = Dom4JUtil.getNewElement("a");

		StringBuilder sb = new StringBuilder();

		sb.append("https://github.com/");
		sb.append(portalRepositoryGitDetailsTempMap.get("github.origin.name"));
		sb.append("/");
		sb.append(repositoryName);
		sb.append("/blob/");
		sb.append(
			portalRepositoryGitDetailsTempMap.get("github.sender.branch.name"));
		sb.append("/git-commit-plugins");

		gitCommitPluginsAnchorElement.addAttribute("href", sb.toString());

		gitCommitPluginsAnchorElement.addText("git-commit-plugins");

		return gitCommitPluginsAnchorElement;
	}

<<<<<<< HEAD
	protected int getSnippetStart(String consoleText, int end) {
		int start = 0;

		Matcher matcher = _pattern.matcher(consoleText);
=======
	protected int getSnippetStart(String consoleOutput, int end) {
		int start = 0;

		Matcher matcher = _pattern.matcher(consoleOutput);
>>>>>>> compatible

		while (matcher.find()) {
			int x = matcher.start() + 1;

			if (x >= end) {
				return start;
			}

			start = x;
		}

		return start;
	}

<<<<<<< HEAD
	private String _getConsoleTextSnippet(
		String consoleText, boolean truncateTop, int start, int end) {
=======
	private String _getConsoleOutputSnippet(
		String consoleOutput, boolean truncateTop, int start, int end) {
>>>>>>> compatible

		if ((end - start) > 2500) {
			if (truncateTop) {
				start = end - 2500;

<<<<<<< HEAD
				start = consoleText.indexOf("\n", start);
=======
				start = consoleOutput.indexOf("\n", start);
>>>>>>> compatible
			}
			else {
				end = start + 2500;

<<<<<<< HEAD
				end = consoleText.lastIndexOf("\n", end);
			}
		}

		consoleText = consoleText.substring(start, end);

		consoleText = consoleText.replaceFirst("^\\s*\\n", "");
		consoleText = consoleText.replaceFirst("\\n\\s*$", "");

		return consoleText;
=======
				end = consoleOutput.lastIndexOf("\n", end);
			}
		}

		consoleOutput = consoleOutput.substring(start, end);

		consoleOutput = consoleOutput.replaceFirst("^\\s*\\n", "");
		consoleOutput = consoleOutput.replaceFirst("\\n\\s*$", "");

		return consoleOutput;
>>>>>>> compatible
	}

	private static final Pattern _pattern = Pattern.compile(
		"\\n[a-z\\-\\.]+\\:\\n");

}