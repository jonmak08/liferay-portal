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

<<<<<<< HEAD
=======
import java.util.Hashtable;

>>>>>>> compatible
import org.dom4j.Element;

/**
 * @author Peter Yoo
 */
public class GenericFailureMessageGenerator
	extends BaseFailureMessageGenerator {

	@Override
<<<<<<< HEAD
	public Element getMessageElement(Build build) {
		return getMessageElement(build.getConsoleText());
	}

	public Element getMessageElement(String consoleText) {
=======
	public String getMessage(
		String buildURL, String consoleOutput, Hashtable<?, ?> properties) {

		String message = getExceptionSnippet(consoleOutput);

		if (message != null) {
			return message;
		}

		message = getMergeTestResultsSnippet(consoleOutput);

		if (message != null) {
			return message;
		}

		message = getBuildFailedSnippet(consoleOutput);

		if (message != null) {
			return message;
		}

		return getConsoleOutputSnippet(consoleOutput, true, -1);
	}

	@Override
	public Element getMessageElement(Build build) {
		String consoleText = build.getConsoleText();

>>>>>>> compatible
		Element message = getExceptionSnippetElement(consoleText);

		if (message != null) {
			return message;
		}

		message = getMergeTestResultsSnippetElement(consoleText);

		if (message != null) {
			return message;
		}

		message = getBuildFailedSnippetElement(consoleText);

		if (message != null) {
			return message;
		}

<<<<<<< HEAD
		return getConsoleTextSnippetElement(consoleText, true, -1);
	}

	protected String getBuildFailedSnippet(String consoleText) {
		int end = consoleText.indexOf("BUILD FAILED");
=======
		return getConsoleOutputSnippetElement(consoleText, true, -1);
	}

	protected String getBuildFailedSnippet(String consoleOutput) {
		int end = consoleOutput.indexOf("BUILD FAILED");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		end = consoleText.indexOf("Total time:", end);

		return getConsoleTextSnippet(consoleText, true, end);
	}

	protected Element getBuildFailedSnippetElement(String consoleText) {
		int end = consoleText.indexOf("BUILD FAILED");
=======
		end = consoleOutput.indexOf("Total time:", end);

		return getConsoleOutputSnippet(consoleOutput, true, end);
	}

	protected Element getBuildFailedSnippetElement(String consoleOutput) {
		int end = consoleOutput.indexOf("BUILD FAILED");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		end = consoleText.indexOf("Total time:", end);

		return getConsoleTextSnippetElement(consoleText, true, end);
	}

	protected String getExceptionSnippet(String consoleText) {
		int end = consoleText.indexOf("[exec] * Exception is:");
=======
		end = consoleOutput.indexOf("Total time:", end);

		return getConsoleOutputSnippetElement(consoleOutput, true, end);
	}

	protected String getExceptionSnippet(String consoleOutput) {
		int end = consoleOutput.indexOf("[exec] * Exception is:");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		end = consoleText.indexOf("\n", end + 500);

		return getConsoleTextSnippet(consoleText, true, end);
	}

	protected Element getExceptionSnippetElement(String consoleText) {
		int end = consoleText.indexOf("[exec] * Exception is:");
=======
		end = consoleOutput.indexOf("\n", end + 500);

		return getConsoleOutputSnippet(consoleOutput, true, end);
	}

	protected Element getExceptionSnippetElement(String consoleOutput) {
		int end = consoleOutput.indexOf("[exec] * Exception is:");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		end = consoleText.indexOf("\n", end + 500);

		return getConsoleTextSnippetElement(consoleText, true, end);
	}

	protected String getMergeTestResultsSnippet(String consoleText) {
		int end = consoleText.indexOf("merge-test-results:");
=======
		end = consoleOutput.indexOf("\n", end + 500);

		return getConsoleOutputSnippetElement(consoleOutput, true, end);
	}

	protected String getMergeTestResultsSnippet(String consoleOutput) {
		int end = consoleOutput.indexOf("merge-test-results:");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		return getConsoleTextSnippet(consoleText, true, end);
	}

	protected Element getMergeTestResultsSnippetElement(String consoleText) {
		int end = consoleText.indexOf("merge-test-results:");
=======
		return getConsoleOutputSnippet(consoleOutput, true, end);
	}

	protected Element getMergeTestResultsSnippetElement(String consoleOutput) {
		int end = consoleOutput.indexOf("merge-test-results:");
>>>>>>> compatible

		if (end == -1) {
			return null;
		}

<<<<<<< HEAD
		return getConsoleTextSnippetElement(consoleText, true, end);
=======
		return getConsoleOutputSnippetElement(consoleOutput, true, end);
>>>>>>> compatible
	}

}