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

package com.liferay.source.formatter.checkstyle.checks;

<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.checks.util.JavaSourceUtil;
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaClassParser;
import com.liferay.source.formatter.parser.JavaTerm;
import com.liferay.source.formatter.parser.JavaVariable;
import com.liferay.source.formatter.util.FileUtil;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.io.File;

import java.util.HashMap;
=======
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.checkstyle.util.DetailASTUtil;
import com.liferay.source.formatter.util.ThreadSafeSortedClassLibraryBuilder;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.parser.ParseException;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
>>>>>>> compatible
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
<<<<<<< HEAD
public class PersistenceCallCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.CLASS_DEF, TokenTypes.ENUM_DEF, TokenTypes.INTERFACE_DEF
		};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
=======
public class PersistenceCallCheck extends AbstractCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CLASS_DEF};
	}

	@Override
	public void visitToken(DetailAST detailAST) {
>>>>>>> compatible
		DetailAST parent = detailAST.getParent();

		if (parent != null) {
			return;
		}

		FileContents fileContents = getFileContents();

		String fileName = StringUtil.replace(
			fileContents.getFileName(), '\\', '/');

		if (!fileName.contains("/modules/")) {
			return;
		}

		FileText fileText = fileContents.getText();

		String content = (String)fileText.getFullText();

<<<<<<< HEAD
		JavaClass javaClass = null;

		try {
			javaClass = JavaClassParser.parseJavaClass(fileName, content);
		}
		catch (Exception e) {
			return;
		}

		Map<String, String> variablesMap = _getVariablesMap(javaClass);

		variablesMap.putAll(
			_getVariablesMap(_getExtendedJavaClass(fileName, content)));
=======
		JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder(
			new ThreadSafeSortedClassLibraryBuilder());

		try {
			javaProjectBuilder.addSource(new UnsyncStringReader(content));
		}
		catch (ParseException pe) {
			return;
		}

		JavaClass javaClass = _getJavaClass(javaProjectBuilder, fileName);

		javaProjectBuilder = _addExtendedClassSource(
			javaProjectBuilder, javaClass, content, fileName);

		List<String> importNames = _getImportNames(detailAST);
		Map<String, String> variablesMap = _getVariablesMap(javaProjectBuilder);
>>>>>>> compatible

		List<DetailAST> methodCallASTList = DetailASTUtil.getAllChildTokens(
			detailAST, true, TokenTypes.METHOD_CALL);

		for (DetailAST methodCallAST : methodCallASTList) {
			_checkMethodCall(
<<<<<<< HEAD
				methodCallAST, javaClass.getImports(), variablesMap,
=======
				methodCallAST, importNames, variablesMap,
>>>>>>> compatible
				javaClass.getPackageName());
		}
	}

<<<<<<< HEAD
=======
	private JavaProjectBuilder _addExtendedClassSource(
		JavaProjectBuilder javaProjectBuilder, JavaClass javaClass,
		String content, String fileName) {

		Pattern pattern = Pattern.compile(
			"\\s" + javaClass.getName() + "\\s+extends\\s+(\\S+)\\s");

		Matcher matcher = pattern.matcher(content);

		if (!matcher.find()) {
			return javaProjectBuilder;
		}

		String extendedClassName = matcher.group(1);

		String fullyQualifiedName = _getFullyQualifiedName(
			extendedClassName, javaProjectBuilder);

		if ((fullyQualifiedName == null) ||
			!fullyQualifiedName.startsWith("com.liferay")) {

			return javaProjectBuilder;
		}

		int pos = fileName.lastIndexOf("/com/liferay/");

		String extendedClassFileName =
			fileName.substring(0, pos + 1) +
				StringUtil.replace(fullyQualifiedName, '.', '/') + ".java";

		try {
			javaProjectBuilder.addSource(new File(extendedClassFileName));
		}
		catch (Exception e) {
		}

		return javaProjectBuilder;
	}

>>>>>>> compatible
	private void _checkClass(
		String className, List<String> importNames, String packageName,
		int lineNo) {

		for (String importName : importNames) {
			if (!importName.endsWith("." + className)) {
				continue;
			}

			int pos = importName.indexOf(".service.persistence.");

			if (pos == -1) {
				return;
			}

			if (!packageName.startsWith(importName.substring(0, pos))) {
				log(lineNo, _MSG_ILLEGAL_PERSISTENCE_CALL, importName);
			}
		}
	}

	private void _checkMethodCall(
		DetailAST methodCallAST, List<String> importNames,
		Map<String, String> variablesMap, String packageName) {

		DetailAST childAST = methodCallAST.getFirstChild();

		if (childAST.getType() != TokenTypes.DOT) {
			return;
		}

		childAST = childAST.getFirstChild();

		if (childAST.getType() != TokenTypes.IDENT) {
			return;
		}

		DetailAST siblingAST = childAST.getNextSibling();

		if (siblingAST.getType() == TokenTypes.IDENT) {
			String methodName = siblingAST.getText();

			if (methodName.equals("clearCache") ||
				methodName.startsWith("create")) {

				return;
			}
		}

		String fieldName = childAST.getText();

		if (fieldName.matches("[A-Z].*")) {
			_checkClass(
				fieldName, importNames, packageName, methodCallAST.getLineNo());
		}
		else {
			_checkVariable(
				fieldName, variablesMap, packageName,
				methodCallAST.getLineNo());
		}
	}

	private void _checkVariable(
		String variableName, Map<String, String> variablesMap,
		String packageName, int lineNo) {

		String fullyQualifiedTypeName = variablesMap.get(variableName);

		if (fullyQualifiedTypeName == null) {
			return;
		}

		int pos = fullyQualifiedTypeName.indexOf(".service.persistence.");

		if (pos == -1) {
			return;
		}

		if (!packageName.startsWith(fullyQualifiedTypeName.substring(0, pos))) {
			log(lineNo, _MSG_ILLEGAL_PERSISTENCE_CALL, fullyQualifiedTypeName);
		}
	}

<<<<<<< HEAD
	private JavaClass _getExtendedJavaClass(String fileName, String content) {
		Matcher matcher = _extendedClassPattern.matcher(content);

		if (!matcher.find()) {
			return null;
		}

		String extendedClassName = matcher.group(1);

		Pattern pattern = Pattern.compile(
			"\nimport (.*\\." + extendedClassName + ");");

		matcher = pattern.matcher(content);

		if (matcher.find()) {
			extendedClassName = matcher.group(1);

			if (!extendedClassName.startsWith("com.liferay.")) {
				return null;
			}
		}

		if (!extendedClassName.contains(StringPool.PERIOD)) {
			extendedClassName =
				JavaSourceUtil.getPackageName(content) + StringPool.PERIOD +
					extendedClassName;
		}

		int pos = fileName.lastIndexOf("/com/liferay/");

		String extendedClassFileName =
			fileName.substring(0, pos + 1) +
				StringUtil.replace(extendedClassName, '.', '/') + ".java";

		try {
			return JavaClassParser.parseJavaClass(
				extendedClassFileName,
				FileUtil.read(new File(extendedClassFileName)));
		}
		catch (Exception e) {
			return null;
		}
	}

	private String _getFullyQualifiedName(
		String className, JavaClass javaClass) {

		for (String importName : javaClass.getImports()) {
			if (importName.endsWith(StringPool.PERIOD + className)) {
				return importName;
			}
		}

		return javaClass.getPackageName() + StringPool.PERIOD + className;
	}

	private Map<String, String> _getVariablesMap(JavaClass javaClass) {
		Map<String, String> variablesMap = new HashMap<>();

		if (javaClass == null) {
			return variablesMap;
		}

		for (JavaTerm javaTerm : javaClass.getChildJavaTerms()) {
			if (!(javaTerm instanceof JavaVariable)) {
				continue;
			}

			Pattern pattern = Pattern.compile(
				"\\s(\\S+)\\s+(\\S+\\.)?" + javaTerm.getName());

			Matcher matcher = pattern.matcher(javaTerm.getContent());

			if (!matcher.find()) {
				continue;
			}

			String fieldTypeClassName = matcher.group(1);

			if (!fieldTypeClassName.contains(StringPool.PERIOD)) {
				fieldTypeClassName = _getFullyQualifiedName(
					fieldTypeClassName, javaClass);
			}

			variablesMap.put(javaTerm.getName(), fieldTypeClassName);
=======
	private String _getFullyQualifiedName(
		String className, JavaProjectBuilder javaProjectBuilder) {

		Collection<JavaSource> sources = javaProjectBuilder.getSources();

		Iterator<JavaSource> iterator = sources.iterator();

		String packageName = null;

		while (iterator.hasNext()) {
			JavaSource javaSource = iterator.next();

			List<String> imports = javaSource.getImports();

			for (String importString : imports) {
				if (importString.endsWith(StringPool.PERIOD + className)) {
					return importString;
				}
			}

			if (packageName == null) {
				packageName = javaSource.getPackageName();
			}
		}

		return packageName + StringPool.PERIOD + className;
	}

	private List<String> _getImportNames(DetailAST detailAST) {
		List<String> importASTList = new ArrayList<>();

		DetailAST sibling = detailAST.getPreviousSibling();

		while (true) {
			if (sibling.getType() == TokenTypes.IMPORT) {
				FullIdent importIdent = FullIdent.createFullIdentBelow(sibling);

				importASTList.add(importIdent.getText());
			}
			else {
				break;
			}

			sibling = sibling.getPreviousSibling();
		}

		return importASTList;
	}

	private JavaClass _getJavaClass(
		JavaProjectBuilder javaProjectBuilder, String fileName) {

		int pos = fileName.lastIndexOf("/");

		String className = fileName.substring(pos + 1, fileName.length() - 5);

		for (JavaClass javaClass : javaProjectBuilder.getClasses()) {
			if (className.equals(javaClass.getName())) {
				return javaClass;
			}
		}

		return null;
	}

	private Map<String, String> _getVariablesMap(
		JavaProjectBuilder javaProjectBuilder) {

		Map<String, String> variablesMap = new HashMap<>();

		for (JavaClass javaClass : javaProjectBuilder.getClasses()) {
			for (JavaField javaField : javaClass.getFields()) {
				String fieldName = javaField.getName();

				JavaClass fieldTypeClass = javaField.getType();

				String fieldTypeClassName = null;

				try {
					fieldTypeClassName = fieldTypeClass.getName();
				}
				catch (Throwable t) {
					Pattern pattern = Pattern.compile(
						"\\s(\\S+)\\s+(\\S+\\.)?" + fieldName);

					Matcher matcher = pattern.matcher(javaField.toString());

					if (matcher.find()) {
						fieldTypeClassName = matcher.group(1);
					}
				}

				if (fieldTypeClassName != null) {
					if (!fieldTypeClassName.contains(StringPool.PERIOD)) {
						fieldTypeClassName = _getFullyQualifiedName(
							fieldTypeClassName, javaProjectBuilder);
					}

					variablesMap.put(fieldName, fieldTypeClassName);
				}
			}
>>>>>>> compatible
		}

		return variablesMap;
	}

	private static final String _MSG_ILLEGAL_PERSISTENCE_CALL =
		"persistence.call.illegal";

<<<<<<< HEAD
	private final Pattern _extendedClassPattern = Pattern.compile(
		"\\sextends\\s+(\\w+)\\W");

=======
>>>>>>> compatible
}