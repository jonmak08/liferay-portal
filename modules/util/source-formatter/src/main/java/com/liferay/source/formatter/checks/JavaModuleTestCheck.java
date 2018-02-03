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

import com.liferay.source.formatter.checks.util.JavaSourceUtil;

/**
 * @author Hugo Huijser
 */
public class JavaModuleTestCheck extends BaseFileCheck {

	@Override
	public boolean isModulesCheck() {
		return true;
	}

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		if (!fileName.endsWith("Test.java")) {
			return content;
		}

<<<<<<< HEAD
		String packageName = JavaSourceUtil.getPackageName(content);

		if (!packageName.startsWith("com.liferay")) {
			return content;
		}

		_checkTestPackage(fileName, absolutePath, content, packageName);
=======
		String packagePath = JavaSourceUtil.getPackagePath(content);

		if (!packagePath.startsWith("com.liferay")) {
			return content;
		}

		_checkTestPackage(fileName, absolutePath, content, packagePath);
>>>>>>> compatible

		return content;
	}

	private void _checkTestPackage(
		String fileName, String absolutePath, String content,
<<<<<<< HEAD
		String packageName) {
=======
		String packagePath) {
>>>>>>> compatible

		if (absolutePath.contains("/src/testIntegration/java/") ||
			absolutePath.contains("/test/integration/")) {

			if (content.contains("@RunWith(Arquillian.class)") &&
				content.contains("import org.powermock.")) {

				addMessage(
<<<<<<< HEAD
					fileName, "Do not use PowerMock inside Arquillian tests",
					"power_mock.markdown");
			}

			if (!packageName.endsWith(".test")) {
				addMessage(
					fileName,
					"Module integration test must be under a test subpackage",
					"modules_tests.markdown");
=======
					fileName,
					"Do not use PowerMock inside Arquillian tests, see " +
						"LPS-56706");
			}

			if (!packagePath.endsWith(".test")) {
				addMessage(
					fileName,
					"Module integration test must be under a test " +
						"subpackage, see LPS-57722");
>>>>>>> compatible
			}
		}
		else if ((absolutePath.contains("/test/unit/") ||
				  absolutePath.contains("/src/test/java/")) &&
<<<<<<< HEAD
				 packageName.endsWith(".test")) {

			addMessage(
				fileName,
				"Module unit test should not be under a test subpackage",
				"modules_tests.markdown");
=======
				 packagePath.endsWith(".test")) {

			addMessage(
				fileName,
				"Module unit test should not be under a test subpackage, see " +
					"LPS-57722");
>>>>>>> compatible
		}
	}

}