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

<<<<<<< HEAD
import com.liferay.petra.string.CharPool;
=======
import com.liferay.portal.kernel.util.CharPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.BNDSettings;
import com.liferay.source.formatter.checks.util.BNDSourceUtil;
import com.liferay.source.formatter.checks.util.JavaSourceUtil;

/**
 * @author Hugo Huijser
 */
public class JavaPackagePathCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

<<<<<<< HEAD
		String packageName = JavaSourceUtil.getPackageName(content);

		if (Validator.isNull(packageName)) {
=======
		String packagePath = JavaSourceUtil.getPackagePath(content);

		if (Validator.isNull(packagePath)) {
>>>>>>> compatible
			addMessage(fileName, "Missing package");

			return content;
		}

<<<<<<< HEAD
		_checkPackageName(fileName, packageName);

		if (isModulesFile(absolutePath) && !isModulesApp(absolutePath, true)) {
			_checkModulePackageName(fileName, packageName);
=======
		_checkPackagePath(fileName, packagePath);

		if (!absolutePath.contains("/modules/private/apps/") &&
			isModulesFile(absolutePath)) {

			_checkModulePackagePath(fileName, packagePath);
>>>>>>> compatible
		}

		return content;
	}

<<<<<<< HEAD
	private void _checkModulePackageName(String fileName, String packageName)
		throws Exception {

		if (!packageName.startsWith("com.liferay")) {
=======
	private void _checkModulePackagePath(String fileName, String packagePath)
		throws Exception {

		if (!packagePath.startsWith("com.liferay")) {
>>>>>>> compatible
			return;
		}

		BNDSettings bndSettings = getBNDSettings(fileName);

		if (bndSettings == null) {
			return;
		}

		String bundleSymbolicName = BNDSourceUtil.getDefinitionValue(
			bndSettings.getContent(), "Bundle-SymbolicName");

		if (!bundleSymbolicName.startsWith("com.liferay")) {
			return;
		}

		bundleSymbolicName = bundleSymbolicName.replaceAll(
			"\\.(api|service|test)$", StringPool.BLANK);

<<<<<<< HEAD
		if (packageName.contains(bundleSymbolicName)) {
=======
		if (packagePath.contains(bundleSymbolicName)) {
>>>>>>> compatible
			return;
		}

		bundleSymbolicName = bundleSymbolicName.replaceAll(
			"\\.impl$", ".internal");

<<<<<<< HEAD
		if (!packageName.contains(bundleSymbolicName)) {
=======
		if (!packagePath.contains(bundleSymbolicName)) {
>>>>>>> compatible
			addMessage(
				fileName,
				"Package should follow Bundle-SymbolicName specified in " +
					bndSettings.getFileName(),
				"package.markdown");
		}
	}

<<<<<<< HEAD
	private void _checkPackageName(String fileName, String packageName) {
=======
	private void _checkPackagePath(String fileName, String packagePath) {
>>>>>>> compatible
		int pos = fileName.lastIndexOf(CharPool.SLASH);

		String filePath = StringUtil.replace(
			fileName.substring(0, pos), CharPool.SLASH, CharPool.PERIOD);

<<<<<<< HEAD
		if (!filePath.endsWith(packageName)) {
			addMessage(
				fileName,
				"The declared package '" + packageName +
=======
		if (!filePath.endsWith(packagePath)) {
			addMessage(
				fileName,
				"The declared package '" + packagePath +
>>>>>>> compatible
					"' does not match the expected package",
				"package.markdown");

			return;
		}

<<<<<<< HEAD
		if (packageName.matches(".*\\.internal\\.([\\w.]+\\.)?impl")) {
			addMessage(
				fileName, "Do not use 'impl' inside 'internal'",
				"package.markdown");
=======
		if (packagePath.matches(".*\\.internal\\.([\\w.]+\\.)?impl")) {
			addMessage(
				fileName, "Do not use 'impl' inside 'internal', see LPS-70113");
>>>>>>> compatible
		}
	}

}