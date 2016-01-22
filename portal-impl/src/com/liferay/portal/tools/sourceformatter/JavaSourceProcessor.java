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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaSourceProcessor extends BaseSourceProcessor {

	public static String stripJavaImports(
			String content, String packageDir, String className)
		throws IOException {

		Matcher matcher = _importsPattern.matcher(content);

		if (!matcher.find()) {
			return content;
		}

		String imports = matcher.group();

		Set<String> classes = ClassUtil.getClasses(
			new UnsyncStringReader(content), className);

		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(imports));

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			if (!line.contains("import ")) {
				continue;
			}

			int importX = line.indexOf(" ");
			int importY = line.lastIndexOf(".");

			String importPackage = line.substring(importX + 1, importY);

			if (importPackage.equals(packageDir) ||
				importPackage.equals("java.lang")) {

				continue;
			}

			String importClass = line.substring(importY + 1, line.length() - 1);

			if (importClass.equals("*") || classes.contains(importClass)) {
				sb.append(line);
				sb.append("\n");
			}
		}

		ImportsFormatter importsFormatter = new JavaImportsFormatter();

		imports = importsFormatter.format(sb.toString());

		content =
			content.substring(0, matcher.start()) + imports +
				content.substring(matcher.end());

		// Ensure a blank line exists between the package and the first import

		content = content.replaceFirst(
			"(?m)^[ \t]*(package .*;)\\s*^[ \t]*import", "$1\n\nimport");

		// Ensure a blank line exists between the last import (or package if
		// there are no imports) and the class comment

		content = content.replaceFirst(
			"(?m)^[ \t]*((?:package|import) .*;)\\s*^[ \t]*/\\*\\*",
			"$1\n\n/**");

		return content;
	}

	protected static int getLeadingTabCount(String line) {
		int leadingTabCount = 0;

		while (line.startsWith(StringPool.TAB)) {
			line = line.substring(1);

			leadingTabCount++;
		}

		return leadingTabCount;
	}

	protected static String sortAnnotations(String content, String indent)
		throws IOException {

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(content));

		String line = null;

		String annotation = StringPool.BLANK;
		String previousAnnotation = StringPool.BLANK;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			if (line.equals(indent + StringPool.CLOSE_CURLY_BRACE)) {
				return content;
			}

			if (StringUtil.count(line, StringPool.TAB) == indent.length()) {
				if (Validator.isNotNull(previousAnnotation) &&
					(previousAnnotation.compareTo(annotation) > 0)) {

					content = StringUtil.replaceFirst(
						content, previousAnnotation, annotation);
					content = StringUtil.replaceLast(
						content, annotation, previousAnnotation);

					return sortAnnotations(content, indent);
				}

				if (line.startsWith(indent + StringPool.AT)) {
					if (Validator.isNotNull(annotation)) {
						previousAnnotation = annotation;
					}

					annotation = line + "\n";
				}
				else {
					annotation = StringPool.BLANK;
					previousAnnotation = StringPool.BLANK;
				}
			}
			else {
				if (Validator.isNull(annotation)) {
					return content;
				}

				annotation += line + "\n";
			}
		}

		return content;
	}

	protected String checkIfClause(
			String ifClause, String fileName, int lineCount)
		throws IOException {

		String ifClauseSingleLine = StringUtil.replace(
			ifClause,
			new String[] {
				StringPool.TAB + StringPool.SPACE, StringPool.TAB,
				StringPool.OPEN_PARENTHESIS + StringPool.NEW_LINE,
				StringPool.NEW_LINE
			},
			new String[] {
				StringPool.TAB, StringPool.BLANK, StringPool.OPEN_PARENTHESIS,
				StringPool.SPACE
			});

		checkIfClauseParentheses(ifClauseSingleLine, fileName, lineCount);

		return checkIfClauseTabsAndSpaces(ifClause);
	}

	protected String checkIfClauseTabsAndSpaces(String ifClause)
		throws IOException {

		if (ifClause.contains("!(") ||
			ifClause.contains(StringPool.TAB + "//")) {

			return ifClause;
		}

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(ifClause));

		String line = null;

		String previousLine = null;
		int previousLineLeadingWhiteSpace = 0;

		int lastCriteriumLineLeadingWhiteSpace = 0;

		int closeParenthesesCount = 0;
		int openParenthesesCount = 0;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			String originalLine = line;

			line = StringUtil.replace(
				line, StringPool.TAB, StringPool.FOUR_SPACES);

			int leadingWhiteSpace =
				line.length() - StringUtil.trimLeading(line).length();

			if (Validator.isNull(previousLine)) {
				lastCriteriumLineLeadingWhiteSpace = line.indexOf(
					StringPool.OPEN_PARENTHESIS);
			}
			else if (previousLine.endsWith("|") || previousLine.endsWith("&") ||
					 previousLine.endsWith("^")) {

				int expectedLeadingWhiteSpace =
					lastCriteriumLineLeadingWhiteSpace +
						openParenthesesCount - closeParenthesesCount;

				if (leadingWhiteSpace != expectedLeadingWhiteSpace) {
					return fixIfClause(
						ifClause, originalLine,
						leadingWhiteSpace - expectedLeadingWhiteSpace);
				}

				lastCriteriumLineLeadingWhiteSpace = leadingWhiteSpace;

				closeParenthesesCount = 0;
				openParenthesesCount = 0;
			}
			else {
				int expectedLeadingWhiteSpace = 0;

				if (previousLine.contains(StringPool.TAB + "if (")) {
					expectedLeadingWhiteSpace =
						previousLineLeadingWhiteSpace + 8;
				}
				else if (previousLine.contains(StringPool.TAB + "else if (") ||
						 previousLine.contains(StringPool.TAB + "while (")) {

					expectedLeadingWhiteSpace =
						previousLineLeadingWhiteSpace + 12;
				}

				if ((expectedLeadingWhiteSpace != 0) &&
					(leadingWhiteSpace != expectedLeadingWhiteSpace)) {

					return fixIfClause(
						ifClause, originalLine,
						leadingWhiteSpace - expectedLeadingWhiteSpace);
				}
			}

			if (line.endsWith(") {")) {
				return ifClause;
			}

			line = stripQuotes(line, CharPool.QUOTE);
			line = stripQuotes(line, CharPool.APOSTROPHE);

			closeParenthesesCount += StringUtil.count(
				line, StringPool.CLOSE_PARENTHESIS);
			openParenthesesCount += StringUtil.count(
				line, StringPool.OPEN_PARENTHESIS);

			previousLine = originalLine;
			previousLineLeadingWhiteSpace = leadingWhiteSpace;
		}

		return ifClause;
	}

	protected void checkDeserializationSecurity(
			String fileName, String content, boolean isRunOutsidePortalExclusion) {

		for (Pattern vulnerabilityPattern :
				_javaSerializationVulnerabilityPatterns) {

			Matcher matcher = vulnerabilityPattern.matcher(content);

			if (!matcher.matches()) {
				continue;
			}

			StringBundler sb = new StringBundler(5);

			if (isRunOutsidePortalExclusion) {
				sb.append("Possible Java Serialization Remote Code Execution ");
				sb.append("vulnerability using ");
			}
			else {
				sb.append("Use ProtectedObjectInputStream instead of ");
			}

			sb.append(matcher.group(1));
			sb.append(": ");
			sb.append(fileName);

			processErrorMessage(fileName, sb.toString());
		}

	}

	protected void checkLogLevel(
		String content, String fileName, String logLevel) {

		if (fileName.contains("Log")) {
			return;
		}

		Pattern pattern = Pattern.compile("\n(\t+)_log." + logLevel + "\\(");

		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			int pos = matcher.start();

			while (true) {
				pos = content.lastIndexOf(
					StringPool.NEW_LINE + StringPool.TAB, pos - 1);

				char c = content.charAt(pos + 2);

				if (c != CharPool.TAB) {
					break;
				}
			}

			String codeBlock = content.substring(pos, matcher.start());
			String s =
				"_log.is" + StringUtil.upperCaseFirstLetter(logLevel) +
					"Enabled()";

			if (!codeBlock.contains(s)) {
				int lineCount = StringUtil.count(
					content.substring(0, matcher.start(1)),
					StringPool.NEW_LINE);

				lineCount += 1;

				processErrorMessage(
					fileName, "Use " + s + ": " + fileName + " " + lineCount);
			}
		}

		return;
	}

	protected void checkRegexPattern(
		String regexPattern, String fileName, int lineCount) {

		if (portalSource) {
			return;
		}

		int i = regexPattern.indexOf("Pattern.compile(");

		if (i == -1) {
			return;
		}

		regexPattern = regexPattern.substring(i + 16);

		regexPattern = stripQuotes(regexPattern, CharPool.QUOTE);

		i = regexPattern.indexOf(StringPool.COMMA);

		if (i != -1) {
			regexPattern = regexPattern.substring(0, i);
		}
		else {
			regexPattern = StringUtil.replaceLast(
				regexPattern, ");", StringPool.BLANK);
		}

		regexPattern = StringUtil.replace(
			regexPattern, StringPool.PLUS, StringPool.BLANK);

		if (Validator.isNull(regexPattern)) {
			processErrorMessage(
				fileName,
				"create pattern as global var: " + fileName + " " + lineCount);
		}
	}

	protected String fixIfClause(String ifClause, String line, int delta) {
		String newLine = line;

		String whiteSpace = StringPool.BLANK;
		int whiteSpaceLength = Math.abs(delta);

		while (whiteSpaceLength > 0) {
			if (whiteSpaceLength >= 4) {
				whiteSpace += StringPool.TAB;

				whiteSpaceLength -= 4;
			}
			else {
				whiteSpace += StringPool.SPACE;

				whiteSpaceLength -= 1;
			}
		}

		if (delta > 0) {
			if (!line.contains(StringPool.TAB + whiteSpace)) {
				newLine = StringUtil.replaceLast(
					newLine, StringPool.TAB, StringPool.FOUR_SPACES);
			}

			newLine = StringUtil.replaceLast(
				newLine, StringPool.TAB + whiteSpace, StringPool.TAB);
		}
		else {
			newLine = StringUtil.replaceLast(
				newLine, StringPool.TAB, StringPool.TAB + whiteSpace);
		}

		newLine = StringUtil.replaceLast(
			newLine, StringPool.FOUR_SPACES, StringPool.TAB);

		return StringUtil.replace(ifClause, line, newLine);
	}

	protected String fixIncorrectEmptyLineBeforeCloseCurlyBrace(
		String content, String fileName) {

		if (fileName.endsWith("AnnotationLocatorTest.java")) {
			return content;
		}

		Matcher matcher = _incorrectCloseCurlyBracePattern.matcher(content);

		while (matcher.find()) {
			String lastLine = StringUtil.trimLeading(matcher.group(1));

			if (lastLine.startsWith("// ")) {
				continue;
			}

			String tabs = matcher.group(2);
			int tabCount = tabs.length();

			int pos = matcher.start();

			while (true) {
				pos = content.lastIndexOf("\n" + tabs, pos - 1);

				if (content.charAt(pos + tabCount + 1) == CharPool.TAB) {
					continue;
				}

				String codeBlock = content.substring(
					pos + tabCount + 1, matcher.end());

				String firstLine = codeBlock.substring(
					0, codeBlock.indexOf("\n"));

				if (firstLine.contains(" class ") ||
					firstLine.contains(" enum ") ||
					firstLine.contains(" interface ") ||
					firstLine.startsWith("new ") ||
					firstLine.contains(" new ")) {

					break;
				}

				return StringUtil.replaceFirst(
					content, "\n\n" + tabs + "}\n", "\n" + tabs + "}\n", pos);
			}
		}

		return content;
	}

	@Override
	protected void format() throws Exception {
		Collection<String> fileNames = null;

		if (portalSource) {
			fileNames = getPortalJavaFiles();
		}
		else {
			fileNames = getPluginJavaFiles();
		}

		_addMissingDeprecationReleaseVersion = GetterUtil.getBoolean(
			getProperty("add.missing.deprecation.release.version"));
		_allowUseServiceUtilInServiceImpl = GetterUtil.getBoolean(
			getProperty("allow.use.service.util.in.service.impl"));
		_fitOnSingleLineExclusions = getPropertyList(
			"fit.on.single.line.exludes");
		_javaTermSortExclusions = getPropertyList("javaterm.sort.excludes");
		_lineLengthExclusions = getPropertyList("line.length.excludes");
		_proxyExclusions = getPropertyList("proxy.excludes");
		_secureDeserializationExclusionFiles = getPropertyList(
			"secure.deserialization.excluded.files");
		_secureRandomExclusions = getPropertyList("secure.random.excludes");
		_staticLogVariableExclusions = getPropertyList("static.log.excludes");
		_testAnnotationsExclusions = getPropertyList(
			"test.annotations.excludes");
		_upgradeServiceUtilExclusions = getPropertyList(
			"upgrade.service.util.excludes");

		for (String fileName : fileNames) {
			format(fileName);
		}
	}

	@Override
	protected String format(String fileName) throws Exception {
		File file = new File(BASEDIR + fileName);

		fileName = StringUtil.replace(
			fileName, StringPool.BACK_SLASH, StringPool.SLASH);

		String absolutePath = fileUtil.getAbsolutePath(file);

		String content = fileUtil.read(file);

		if (isGenerated(content)) {
			return null;
		}

		String newContent = format(file, fileName, absolutePath, content);

		compareAndAutoFixContent(file, fileName, content, newContent);

		return newContent;
	}

	protected String format(
			File file, String fileName, String absolutePath, String content)
		throws Exception {

		String className = file.getName();

		int pos = className.lastIndexOf(StringPool.PERIOD);

		className = className.substring(0, pos);

		String packagePath = fileName;

		int packagePathX = packagePath.indexOf("/src/");
		int packagePathY = packagePath.lastIndexOf(StringPool.SLASH);

		if ((packagePathX + 5) >= packagePathY) {
			packagePath = StringPool.BLANK;
		}
		else {
			packagePath = packagePath.substring(packagePathX + 5, packagePathY);
		}

		packagePath = StringUtil.replace(
			packagePath, StringPool.SLASH, StringPool.PERIOD);

		if (packagePath.endsWith(".model")) {
			if (content.contains("extends " + className + "Model")) {
				return null;
			}
		}

		String newContent = content;

		if (newContent.contains("$\n */")) {
			processErrorMessage(fileName, "*: " + fileName);

			newContent = StringUtil.replace(newContent, "$\n */", "$\n *\n */");
		}

		newContent = fixCopyright(newContent, absolutePath, fileName);

		if (newContent.contains(className + ".java.html")) {
			processErrorMessage(fileName, "Java2HTML: " + fileName);
		}

		if (newContent.contains(" * @author Raymond Aug") &&
			!newContent.contains(" * @author Raymond Aug\u00e9")) {

			newContent = newContent.replaceFirst(
				"Raymond Aug.++", "Raymond Aug\u00e9");

			processErrorMessage(fileName, "UTF-8: " + fileName);
		}

		newContent = fixSessionKey(fileName, newContent, sessionKeyPattern);

		newContent = StringUtil.replace(
			newContent,
			new String[] {
				"com.liferay.portal.PortalException",
				"com.liferay.portal.SystemException",
				"com.liferay.util.LocalizationUtil",
				"private static final Log _log"
			},
			new String[] {
				"com.liferay.portal.kernel.exception.PortalException",
				"com.liferay.portal.kernel.exception.SystemException",
				"com.liferay.portal.kernel.util.LocalizationUtil",
				"private static Log _log"
			});

		newContent = fixCompatClassImports(absolutePath, newContent);

		newContent = stripJavaImports(newContent, packagePath, className);

		newContent = StringUtil.replace(
			newContent,
			new String[] {
				StringPool.TAB + "catch(", StringPool.TAB + "else{",
				StringPool.TAB + "if(", StringPool.TAB + "for(",
				StringPool.TAB + "while(", ";\n/**", "\t/*\n\t *", "List <",
				"){\n", "]{\n"
			},
			new String[] {
				StringPool.TAB + "catch (", StringPool.TAB + "else {",
				StringPool.TAB + "if (", StringPool.TAB + "for (",
				StringPool.TAB + "while (", ";\n\n/**", "\t/**\n\t *", "List<",
				") {\n", "] {\n"
			});

		while (true) {
			Matcher matcher = _incorrectLineBreakPattern.matcher(newContent);

			if (!matcher.find()) {
				break;
			}

			newContent = StringUtil.replaceFirst(
				newContent, StringPool.NEW_LINE, StringPool.BLANK,
				matcher.start());
		}

		if (!portalSource) {
			newContent = sortAnnotations(newContent, StringPool.BLANK);
		}

		Matcher matcher = _logPattern.matcher(newContent);

		if (matcher.find()) {
			String logClassName = matcher.group(1);

			if (!logClassName.equals(className)) {
				newContent = StringUtil.replaceLast(
					newContent, logClassName + ".class)",
					className + ".class)");
			}
		}

		if (!isExcluded(_staticLogVariableExclusions, fileName)) {
			newContent = StringUtil.replace(
				newContent, "private Log _log", "private static Log _log");
		}

		if (newContent.contains("*/\npackage ")) {
			processErrorMessage(fileName, "package: " + fileName);
		}

		if (!newContent.endsWith("\n\n}") && !newContent.endsWith("{\n}")) {
			processErrorMessage(fileName, "}: " + fileName);
		}

		if (portalSource &&
			!_allowUseServiceUtilInServiceImpl &&
			!className.equals("BaseServiceImpl") &&
			className.endsWith("ServiceImpl") &&
			newContent.contains("ServiceUtil.")) {

			processErrorMessage(fileName, "ServiceUtil: " + fileName);
		}

		// LPS-34911

		if (portalSource &&
			!isExcluded(_upgradeServiceUtilExclusions, fileName) &&
			fileName.contains("/portal/upgrade/") &&
			!fileName.contains("/test/") &&
			newContent.contains("ServiceUtil.")) {

			processErrorMessage(fileName, "ServiceUtil: " + fileName);
		}

		if (!isRunsOutsidePortal(absolutePath) &&
			!isExcluded(_proxyExclusions, fileName) &&
			newContent.contains("import java.lang.reflect.Proxy;")) {

			processErrorMessage(fileName, "Proxy: " + fileName);
		}

		if (newContent.contains("import edu.emory.mathcs.backport.java")) {
			processErrorMessage(
				fileName, "edu.emory.mathcs.backport.java: " + fileName);
		}

		if (newContent.contains("import jodd.util.StringPool")) {
			processErrorMessage(fileName, "jodd.util.StringPool: " + fileName);
		}

		// LPS-45027

		if (!portalSource &&
			newContent.contains(
				"com.liferay.portal.kernel.util.UnmodifiableList")) {

			processErrorMessage(
				fileName,
				"Use java.util.Collections.unmodifiableList instead of " +
					"com.liferay.portal.kernel.util.UnmodifiableList: " +
						fileName);
		}

		// LPS-28266

		for (int pos1 = -1;;) {
			pos1 = newContent.indexOf(StringPool.TAB + "try {", pos1 + 1);

			if (pos1 == -1) {
				break;
			}

			int pos2 = newContent.indexOf(StringPool.TAB + "try {", pos1 + 1);
			int pos3 = newContent.indexOf("\"select count(", pos1);

			if ((pos2 != -1) && (pos3 != -1) && (pos2 < pos3)) {
				continue;
			}

			int pos4 = newContent.indexOf("rs.getLong(1)", pos1);
			int pos5 = newContent.indexOf(StringPool.TAB + "finally {", pos1);

			if ((pos3 == -1) || (pos4 == -1) || (pos5 == -1)) {
				break;
			}

			if ((pos3 < pos4) && (pos4 < pos5)) {
				processErrorMessage(
					fileName, "Use getInt(1) for count: " + fileName);
			}
		}

		// LPS-33070

		if (content.contains("implements ProcessCallable") &&
			!content.contains("private static final long serialVersionUID")) {

			processErrorMessage(
				fileName,
				"Assign ProcessCallable implementation a serialVersionUID: " +
					fileName);
		}

		checkLanguageKeys(fileName, newContent, languageKeyPattern);

		newContent = StringUtil.replace(
			newContent, StringPool.TAB + "for (;;) {",
			StringPool.TAB + "while (true) {");

		// LPS-39508

		if (!isExcluded(_secureRandomExclusions, fileName) &&
			!isRunsOutsidePortal(absolutePath) &&
			content.contains("java.security.SecureRandom") &&
			!content.contains("javax.crypto.KeyGenerator")) {

			processErrorMessage(
				fileName,
				"Use SecureRandomUtil or com.liferay.portal.kernel.security." +
					"SecureRandom instead of java.security.SecureRandom: " +
						fileName);
		}

		// LPS-41315

		checkLogLevel(newContent, fileName, "debug");
		checkLogLevel(newContent, fileName, "info");
		checkLogLevel(newContent, fileName, "trace");
		checkLogLevel(newContent, fileName, "warn");

		// LPS-41205

		if (fileName.contains("/upgrade/") &&
			newContent.contains("LocaleUtil.getDefault()")) {

			processErrorMessage(
				fileName,
				"Use UpgradeProcessUtil.getDefaultLanguageId(companyId) " +
					"instead of LocaleUtil.getDefault(): " + fileName);
		}

		// LPS-46017

		if (!portalSource) {
			newContent = StringUtil.replace(
				newContent, " static interface ", " interface ");
		}

		// LPS-47682

		newContent = fixIncorrectParameterTypeForLanguageUtil(
			newContent, false, fileName);

		if (portalSource && fileName.contains("/portal-service/") &&
			content.contains("import javax.servlet.jsp.")) {

			processErrorMessage(
				fileName,
				"Never import javax.servlet.jsp.* from portal-service " +
					fileName);
		}

		// LPS-60358

		if (!fileName.contains("/test/") &&
			!fileName.contains("/testIntegration/") &&
			!isExcluded(
				_secureDeserializationExclusionFiles, fileName)) {

			checkDeserializationSecurity(
				fileName, content, isRunsOutsidePortal(absolutePath));
		}

		newContent = fixIncorrectEmptyLineBeforeCloseCurlyBrace(
			newContent, fileName);

		if (!portalSource) {
			pos = newContent.indexOf("\npublic ");

			if (pos != -1) {
				String javaClassContent = newContent.substring(pos);

				newContent = formatJavaTerms(
					fileName, newContent, javaClassContent,
					_javaTermSortExclusions, _testAnnotationsExclusions);
			}
		}

		newContent = formatJava(fileName, absolutePath, newContent);

		newContent = StringUtil.replace(newContent, "\n\n\n", "\n\n");

		if (content.equals(newContent)) {
			return newContent;
		}

		return format(file, fileName, absolutePath, newContent);
	}

	protected String formatJava(
			String fileName, String absolutePath, String content)
		throws Exception {

		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(content));

		int lineCount = 0;

		String line = null;

		String previousLine = StringPool.BLANK;

		int lineToSkipIfEmpty = 0;

		String ifClause = StringPool.BLANK;

		String regexPattern = StringPool.BLANK;

		String packageName = StringPool.BLANK;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			lineCount++;

			line = trimLine(line, false);

			if (line.startsWith("package ")) {
				packageName = line.substring(8, line.length() - 1);
			}

			if (line.startsWith("import ")) {
				if (line.endsWith(".*;")) {
					processErrorMessage(
						fileName, "import: " + fileName + " " + lineCount);
				}

				int pos = line.lastIndexOf(StringPool.PERIOD);

				if (pos != -1) {
					String importPackageName = line.substring(7, pos);

					if (importPackageName.equals(packageName)) {
						continue;
					}
				}
			}

			if (line.contains(StringPool.TAB + "for (") && line.contains(":") &&
				!line.contains(" :")) {

				line = StringUtil.replace(line, ":" , " :");
			}

			// LPS-42924

			if (!portalSource && line.contains("PortalUtil.getClassNameId(") &&
				fileName.endsWith("ServiceImpl.java")) {

				processErrorMessage(
					fileName,
					"Use classNameLocalService.getClassNameId: " + fileName +
						" " + lineCount);
			}

			line = replacePrimitiveWrapperInstantiation(
				fileName, line, lineCount);

			String trimmedLine = StringUtil.trimLeading(line);

			// LPS-45649

			if (!portalSource &&
				trimmedLine.startsWith("throw new IOException(") &&
				line.contains("e.getMessage()")) {

				line = StringUtil.replace(
					line, ".getMessage()", StringPool.BLANK);
			}

			// LPS-45492

			if (!portalSource &&
				trimmedLine.contains("StopWatch stopWatch = null;")) {

				processErrorMessage(
					fileName,
					"Do not set stopwatch to null: " + fileName + " " +
						lineCount);
			}

			checkStringBundler(trimmedLine, fileName, lineCount);

			checkEmptyCollection(trimmedLine, fileName, lineCount);

			if (trimmedLine.startsWith("* @deprecated") &&
				_addMissingDeprecationReleaseVersion) {

				if (!trimmedLine.startsWith("* @deprecated As of ")) {
					line = StringUtil.replace(
						line, "* @deprecated",
						"* @deprecated As of " + getMainReleaseVersion());
				}
				else {
					String version = trimmedLine.substring(20);

					version = StringUtil.split(version, StringPool.SPACE)[0];

					version = StringUtil.replace(
						version, StringPool.COMMA, StringPool.BLANK);

					if (StringUtil.count(version, StringPool.PERIOD) == 1) {
						line = StringUtil.replaceFirst(
							line, version, version + ".0");
					}
				}
			}

			if (!portalSource && trimmedLine.startsWith("* @see ") &&
				(StringUtil.count(trimmedLine, StringPool.AT) > 1)) {

				processErrorMessage(
					fileName,
					"Do not use @see with another annotation: " + fileName +
						" " + lineCount);
			}

			checkInefficientStringMethods(
				line, fileName, absolutePath, lineCount);

			if (trimmedLine.startsWith(StringPool.EQUAL)) {
				processErrorMessage(
					fileName, "line break: " + fileName + " " + lineCount);
			}

			if (line.contains("ActionForm form")) {
				processErrorMessage(
					fileName,
					"Rename form to actionForm: " + fileName + " " + lineCount);
			}

			if (line.contains("ActionMapping mapping")) {
				processErrorMessage(
					fileName,
					"Rename mapping to ActionMapping: " + fileName + " " +
						lineCount);
			}

			if (fileName.contains("/upgrade/") &&
				line.contains("rs.getDate(")) {

				processErrorMessage(
					fileName,
					"Use rs.getTimeStamp: " + fileName + " " + lineCount);
			}

			if (!trimmedLine.equals("{") && line.endsWith("{") &&
				!line.endsWith(" {")) {

				line = StringUtil.replaceLast(line, "{", " {");
			}

			line = sortExceptions(line);

			if (trimmedLine.startsWith("if (") ||
				trimmedLine.startsWith("else if (") ||
				trimmedLine.startsWith("while (") ||
				Validator.isNotNull(ifClause)) {

				ifClause = ifClause + line + StringPool.NEW_LINE;

				if (line.endsWith(") {")) {
					String newIfClause = checkIfClause(
						ifClause, fileName, lineCount);

					if (!ifClause.equals(newIfClause) &&
						content.contains(ifClause)) {

						return StringUtil.replace(
							content, ifClause, newIfClause);
					}

					ifClause = StringPool.BLANK;
				}
				else if (line.endsWith(StringPool.SEMICOLON)) {
					ifClause = StringPool.BLANK;
				}
			}

			if (trimmedLine.startsWith("Pattern ") ||
				Validator.isNotNull(regexPattern)) {

				regexPattern = regexPattern + trimmedLine;

				if (trimmedLine.endsWith(");")) {

					// LPS-41084

					checkRegexPattern(regexPattern, fileName, lineCount);

					regexPattern = StringPool.BLANK;
				}
			}

			if (!trimmedLine.contains(StringPool.DOUBLE_SLASH) &&
				!trimmedLine.startsWith(StringPool.STAR)) {

				String strippedQuotesLine = stripQuotes(
					trimmedLine, CharPool.QUOTE);

				for (int x = 0;;) {
					x = strippedQuotesLine.indexOf(StringPool.EQUAL, x + 1);

					if (x == -1) {
						break;
					}

					char c = strippedQuotesLine.charAt(x - 1);

					if (Character.isLetterOrDigit(c)) {
						line = StringUtil.replace(line, c + "=", c + " =");

						break;
					}

					if (x == (strippedQuotesLine.length() - 1)) {
						break;
					}

					c = strippedQuotesLine.charAt(x + 1);

					if (Character.isLetterOrDigit(c)) {
						line = StringUtil.replace(line, "=" + c, "= " + c);

						break;
					}
				}

				while (trimmedLine.contains(StringPool.TAB)) {
					line = StringUtil.replaceLast(
						line, StringPool.TAB, StringPool.SPACE);

					trimmedLine = StringUtil.replaceLast(
						trimmedLine, StringPool.TAB, StringPool.SPACE);
				}

				if (line.contains(StringPool.TAB + StringPool.SPACE) &&
					!previousLine.endsWith("&&") &&
					!previousLine.endsWith("||") &&
					!previousLine.contains(StringPool.TAB + "((") &&
					!previousLine.contains(
						StringPool.TAB + StringPool.LESS_THAN) &&
					!previousLine.contains(StringPool.TAB + StringPool.SPACE) &&
					!previousLine.contains(StringPool.TAB + "implements ") &&
					!previousLine.contains(StringPool.TAB + "throws ")) {

					line = StringUtil.replace(
						line, StringPool.TAB + StringPool.SPACE,
						StringPool.TAB);
				}

				while (trimmedLine.contains(StringPool.DOUBLE_SPACE) &&
					   !trimmedLine.contains(
						   StringPool.QUOTE + StringPool.DOUBLE_SPACE) &&
					   !fileName.contains("Test")) {

					line = StringUtil.replaceLast(
						line, StringPool.DOUBLE_SPACE, StringPool.SPACE);

					trimmedLine = StringUtil.replaceLast(
						trimmedLine, StringPool.DOUBLE_SPACE, StringPool.SPACE);
				}

				if (!line.contains(StringPool.QUOTE)) {
					int pos = line.indexOf(") ");

					if (pos != -1) {
						String linePart = line.substring(pos + 2);

						if (Character.isLetter(linePart.charAt(0)) &&
							!linePart.startsWith("default") &&
							!linePart.startsWith("instanceof") &&
							!linePart.startsWith("throws")) {

							line = StringUtil.replaceLast(
								line, StringPool.SPACE + linePart, linePart);
						}
					}

					if ((trimmedLine.startsWith("private ") ||
						 trimmedLine.startsWith("protected ") ||
						 trimmedLine.startsWith("public ")) &&
						!line.contains(StringPool.EQUAL) &&
						line.contains(" (")) {

						line = StringUtil.replace(line, " (", "(");
					}

					if (line.contains(" [")) {
						line = StringUtil.replace(line, " [", "[");
					}

					for (int x = -1;;) {
						int posComma = line.indexOf(StringPool.COMMA, x + 1);
						int posSemicolon = line.indexOf(
							StringPool.SEMICOLON, x + 1);

						if ((posComma == -1) && (posSemicolon == -1)) {
							break;
						}

						x = Math.min(posComma, posSemicolon);

						if (x == -1) {
							x = Math.max(posComma, posSemicolon);
						}

						if (line.length() > (x + 1)) {
							char nextChar = line.charAt(x + 1);

							if ((nextChar != CharPool.APOSTROPHE) &&
								(nextChar != CharPool.CLOSE_PARENTHESIS) &&
								(nextChar != CharPool.SPACE) &&
								(nextChar != CharPool.STAR)) {

								line = StringUtil.insert(
									line, StringPool.SPACE, x + 1);
							}
						}

						if (x > 0) {
							char previousChar = line.charAt(x - 1);

							if (previousChar == CharPool.SPACE) {
								line = line.substring(0, x - 1).concat(
									line.substring(x));
							}
						}
					}
				}

				if ((line.contains(" && ") || line.contains(" || ")) &&
					line.endsWith(StringPool.OPEN_PARENTHESIS)) {

					processErrorMessage(
						fileName, "line break: " + fileName + " " + lineCount);
				}

				if (trimmedLine.endsWith(StringPool.PLUS) &&
					!trimmedLine.startsWith(StringPool.OPEN_PARENTHESIS)) {

					int closeParenthesisCount = StringUtil.count(
						strippedQuotesLine, StringPool.CLOSE_PARENTHESIS);
					int openParenthesisCount = StringUtil.count(
						strippedQuotesLine, StringPool.OPEN_PARENTHESIS);

					if (openParenthesisCount > closeParenthesisCount) {
						processErrorMessage(
							fileName,
							"line break: " + fileName + " " + lineCount);
					}
				}

				int x = strippedQuotesLine.indexOf(", ");

				if (!portalSource && (x != -1)) {
					String linePart = strippedQuotesLine.substring(0, x);

					int closeParenthesisCount = StringUtil.count(
						linePart, StringPool.CLOSE_PARENTHESIS);
					int openParenthesisCount = StringUtil.count(
						linePart, StringPool.OPEN_PARENTHESIS);

					if (closeParenthesisCount > openParenthesisCount) {
						processErrorMessage(
							fileName,
							"line break: " + fileName + " " + lineCount);
					}
				}

				if (line.contains(StringPool.COMMA) &&
					!line.contains(StringPool.CLOSE_PARENTHESIS) &&
					!line.contains(StringPool.GREATER_THAN) &&
					!line.contains(StringPool.QUOTE) &&
					line.endsWith(StringPool.OPEN_PARENTHESIS)) {

					processErrorMessage(
						fileName, "line break: " + fileName + " " + lineCount);
				}

				if (line.endsWith(" +") || line.endsWith(" -") ||
					line.endsWith(" *") || line.endsWith(" /")) {

					x = line.indexOf(" = ");

					if (x != -1) {
						int y = line.indexOf(StringPool.QUOTE);

						if ((y == -1) || (x < y)) {
							processErrorMessage(
								fileName,
								"line break: " + fileName + " " + lineCount);
						}
					}
				}

				if (line.endsWith(" throws") ||
					(previousLine.endsWith(
						StringPool.OPEN_PARENTHESIS) &&
					 line.contains(" throws " ) &&
					 line.endsWith(StringPool.OPEN_CURLY_BRACE))) {

					processErrorMessage(
						fileName, "line break: " + fileName + " " + lineCount);
				}

				if (trimmedLine.startsWith(StringPool.PERIOD) ||
					(line.endsWith(StringPool.PERIOD) &&
					 line.contains(StringPool.EQUAL))) {

					processErrorMessage(
						fileName, "line break: " + fileName + " " + lineCount);
				}

				if (trimmedLine.startsWith(StringPool.CLOSE_CURLY_BRACE) &&
					line.endsWith(StringPool.OPEN_CURLY_BRACE)) {

					processErrorMessage(
						fileName, "line break: " + fileName + " " + lineCount);
				}
			}

			if (line.contains("    ") && !line.matches("\\s*\\*.*")) {
				if (!fileName.endsWith("StringPool.java")) {
					processErrorMessage(
						fileName, "tab: " + fileName + " " + lineCount);
				}
			}

			if (line.contains("  {") && !line.matches("\\s*\\*.*")) {
				processErrorMessage(
					fileName, "{:" + fileName + " " + lineCount);
			}

			Tuple combinedLines = null;
			int lineLength = getLineLength(line);

			if (!isExcluded(_lineLengthExclusions, fileName, lineCount) &&
				!line.startsWith("import ") && !line.startsWith("package ") &&
				!line.matches("\\s*\\*.*")) {

				if (fileName.endsWith("Table.java") &&
					line.contains("String TABLE_SQL_CREATE = ")) {
				}
				else if (fileName.endsWith("Table.java") &&
						 line.contains("String TABLE_SQL_DROP = ")) {
				}
				else if (fileName.endsWith("Table.java") &&
						 line.contains(" index IX_")) {
				}
				else if (lineLength > 80) {
					processErrorMessage(
						fileName, "> 80: " + fileName + " " + lineCount);
				}
				else {
					int lineLeadingTabCount = getLeadingTabCount(line);
					int previousLineLeadingTabCount = getLeadingTabCount(
						previousLine);

					if (!trimmedLine.startsWith("//")) {
						if (previousLine.endsWith(StringPool.COMMA) &&
							previousLine.contains(
								StringPool.OPEN_PARENTHESIS) &&
							!previousLine.contains("for (") &&
							(lineLeadingTabCount >
								previousLineLeadingTabCount)) {

							processErrorMessage(
								fileName,
								"line break: " + fileName + " " + lineCount);
						}

						if ((lineLeadingTabCount ==
								previousLineLeadingTabCount) &&
							(previousLine.endsWith(StringPool.EQUAL) ||
							 previousLine.endsWith(
								 StringPool.OPEN_PARENTHESIS))) {

							if (!portalSource) {
								processErrorMessage(
									fileName,
									"tab: " + fileName + " " + lineCount);
							}
						}

						if (Validator.isNotNull(trimmedLine)) {
							if (((previousLine.endsWith(StringPool.COLON) &&
								  previousLine.contains(
									  StringPool.TAB + "for ")) ||
								 (previousLine.endsWith(
									 StringPool.OPEN_PARENTHESIS) &&
								  previousLine.contains(
									  StringPool.TAB + "if "))) &&
								((previousLineLeadingTabCount + 2) !=
									lineLeadingTabCount)) {

								processErrorMessage(
									fileName,
									"line break: " + fileName + " " +
										lineCount);
							}

							if (previousLine.endsWith(
									StringPool.OPEN_CURLY_BRACE) &&
								!trimmedLine.startsWith(
									StringPool.CLOSE_CURLY_BRACE) &&
								((previousLineLeadingTabCount + 1) !=
									lineLeadingTabCount)) {

								processErrorMessage(
									fileName,
									"tab: " + fileName + " " + lineCount);
							}
						}

						if (previousLine.endsWith(StringPool.PERIOD)) {
							int x = trimmedLine.indexOf(
								StringPool.OPEN_PARENTHESIS);

							if ((x != -1) &&
								((getLineLength(previousLine) + x) < 80) &&
								(trimmedLine.endsWith(
									StringPool.OPEN_PARENTHESIS) ||
								 (trimmedLine.charAt(x + 1) !=
									 CharPool.CLOSE_PARENTHESIS))) {

								processErrorMessage(
									fileName,
									"line break: " + fileName + " " +
										lineCount);
							}
						}

						if (trimmedLine.startsWith("throws ")) {
							int diff =
								lineLeadingTabCount -
									previousLineLeadingTabCount;

							if ((diff == 0) || (diff > 1)) {
								processErrorMessage(
									fileName,
									"tab: " + fileName + " " + lineCount);
							}
						}

						if ((previousLine.contains(" class " ) ||
							 previousLine.contains(" enum ")) &&
							previousLine.endsWith(
								StringPool.OPEN_CURLY_BRACE) &&
							Validator.isNotNull(line) &&
							!trimmedLine.startsWith(
								StringPool.CLOSE_CURLY_BRACE)) {

							processErrorMessage(
								fileName,
								"line break: " + fileName + " " + lineCount);
						}
					}

					if (!isExcluded(
							_fitOnSingleLineExclusions, fileName, lineCount)) {

						combinedLines = getCombinedLines(
							trimmedLine, previousLine, lineLeadingTabCount,
							previousLineLeadingTabCount);
					}
				}
			}

			if (combinedLines != null) {
				previousLine = (String)combinedLines.getObject(0);

				if (combinedLines.getSize() > 1) {
					String linePart = (String)combinedLines.getObject(1);
					boolean addToPreviousLine =
						(Boolean)combinedLines.getObject(2);

					if (addToPreviousLine) {
						previousLine = previousLine + linePart;
						line = StringUtil.replaceFirst(
							line, linePart, StringPool.BLANK);
					}
					else {
						if (((linePart.length() + lineLength) <= 80) &&
							(line.endsWith(StringPool.OPEN_CURLY_BRACE) ||
							 line.endsWith(StringPool.SEMICOLON))) {

							previousLine = StringUtil.replaceLast(
								previousLine, StringUtil.trim(linePart),
								StringPool.BLANK);

							line = StringUtil.replaceLast(
								line, StringPool.TAB,
								StringPool.TAB + linePart);
						}
						else {
							processErrorMessage(
								fileName,
								"line break: " + fileName + " " + lineCount);
						}
					}

					sb.append(previousLine);
					sb.append("\n");

					previousLine = line;
				}
				else if (line.endsWith(StringPool.OPEN_CURLY_BRACE) &&
						 !previousLine.contains(" class ")) {

					lineToSkipIfEmpty = lineCount + 1;
				}
			}
			else {
				if ((lineCount > 1) &&
					(Validator.isNotNull(previousLine) ||
					 (lineToSkipIfEmpty != (lineCount - 1)))) {

					sb.append(previousLine);

					if (Validator.isNotNull(previousLine) &&
						Validator.isNotNull(trimmedLine) &&
						!previousLine.contains("/*") &&
						!previousLine.endsWith("*/")) {

						String trimmedPreviousLine = StringUtil.trimLeading(
							previousLine);

						if ((trimmedPreviousLine.startsWith("// ") &&
							 !trimmedLine.startsWith("// ")) ||
							(!trimmedPreviousLine.startsWith("// ") &&
							 trimmedLine.startsWith("// "))) {

							sb.append("\n");
						}
						else if (!trimmedPreviousLine.endsWith(
									StringPool.OPEN_CURLY_BRACE) &&
								 !trimmedPreviousLine.endsWith(
									StringPool.COLON) &&
								 (trimmedLine.startsWith("for (") ||
								  trimmedLine.startsWith("if ("))) {

							sb.append("\n");
						}
						else if (previousLine.endsWith(
									StringPool.TAB +
										StringPool.CLOSE_CURLY_BRACE) &&
								 !trimmedLine.startsWith(
									 StringPool.CLOSE_CURLY_BRACE) &&
								 !trimmedLine.startsWith(
									 StringPool.CLOSE_PARENTHESIS) &&
								 !trimmedLine.startsWith(
									 StringPool.DOUBLE_SLASH) &&
								 !trimmedLine.equals("*/") &&
								 !trimmedLine.startsWith("catch ") &&
								 !trimmedLine.startsWith("else ") &&
								 !trimmedLine.startsWith("finally ") &&
								 !trimmedLine.startsWith("while ")) {

							sb.append("\n");
						}
					}

					sb.append("\n");
				}

				previousLine = line;
			}
		}

		sb.append(previousLine);

		unsyncBufferedReader.close();

		String newContent = sb.toString();

		if (newContent.endsWith("\n")) {
			newContent = newContent.substring(0, newContent.length() - 1);
		}

		return newContent;
	}

	protected Tuple getCombinedLines(
		String line, String previousLine, int lineTabCount,
		int previousLineTabCount) {

		if (Validator.isNull(line) || Validator.isNull(previousLine)) {
			return null;
		}

		String trimmedPreviousLine = StringUtil.trimLeading(previousLine);

		if (line.startsWith("// ") || trimmedPreviousLine.startsWith("// ")) {
			return null;
		}

		if (previousLine.endsWith(" extends")) {
			return new Tuple(previousLine, "extends ", false);
		}

		if (previousLine.endsWith(" implements")) {
			return new Tuple(previousLine, "implements ", false);
		}

		if (line.startsWith("+ ") || line.startsWith("- ") ||
			line.startsWith("|| ") || line.startsWith("&& ")) {

			int pos = line.indexOf(StringPool.SPACE);

			String linePart = line.substring(0, pos);

			return new Tuple(previousLine + StringPool.SPACE, linePart, true);
		}

		int previousLineLength = getLineLength(previousLine);

		if ((line.length() + previousLineLength) < 80) {
			if (trimmedPreviousLine.startsWith("for ") &&
				previousLine.endsWith(StringPool.COLON) &&
				line.endsWith(StringPool.OPEN_CURLY_BRACE)) {

				return new Tuple(previousLine + StringPool.SPACE + line);
			}

			if ((previousLine.endsWith(StringPool.EQUAL) ||
				 previousLine.endsWith(StringPool.PERIOD) ||
				 trimmedPreviousLine.equals("return")) &&
				line.endsWith(StringPool.SEMICOLON)) {

				return new Tuple(previousLine + StringPool.SPACE + line);
			}

			if ((trimmedPreviousLine.startsWith("if ") ||
				 trimmedPreviousLine.startsWith("else ")) &&
				(previousLine.endsWith("||") || previousLine.endsWith("&&")) &&
				line.endsWith(StringPool.OPEN_CURLY_BRACE)) {

				return new Tuple(previousLine + StringPool.SPACE + line);
			}

			if ((line.startsWith("extends ") ||
				 line.startsWith("implements ") ||
				 line.startsWith("throws")) &&
				line.endsWith(StringPool.OPEN_CURLY_BRACE) &&
				(lineTabCount == (previousLineTabCount + 1))) {

				return new Tuple(previousLine + StringPool.SPACE + line);
			}
		}

		if (previousLine.endsWith(StringPool.EQUAL) &&
			line.endsWith(StringPool.SEMICOLON)) {

			String tempLine = line;

			for (int pos = 0;;) {
				pos = tempLine.indexOf(StringPool.DASH);

				if (pos == -1) {
					pos = tempLine.indexOf(StringPool.PLUS);
				}

				if (pos == -1) {
					pos = tempLine.indexOf(StringPool.SLASH);
				}

				if (pos == -1) {
					pos = tempLine.indexOf(StringPool.STAR);
				}

				if (pos == -1) {
					pos = tempLine.indexOf("||");
				}

				if (pos == -1) {
					pos = tempLine.indexOf("&&");
				}

				if (pos == -1) {
					break;
				}

				String linePart = tempLine.substring(0, pos);

				int openParenthesisCount = StringUtil.count(
					linePart, StringPool.OPEN_PARENTHESIS);
				int closeParenthesisCount = StringUtil.count(
					linePart, StringPool.CLOSE_PARENTHESIS);

				if (openParenthesisCount == closeParenthesisCount) {
					return null;
				}

				tempLine =
					tempLine.substring(0, pos) + tempLine.substring(pos + 1);
			}

			int x = line.indexOf(StringPool.OPEN_PARENTHESIS);

			if (x == 0) {
				x = line.indexOf(StringPool.OPEN_PARENTHESIS, 1);
			}

			if (x != -1) {
				int y = line.indexOf(StringPool.CLOSE_PARENTHESIS, x);
				int z = line.indexOf(StringPool.QUOTE);

				if (((x + 1) != y) && ((z == -1) || (z > x))) {
					char previousChar = line.charAt(x - 1);

					if ((previousChar != CharPool.CLOSE_PARENTHESIS) &&
						(previousChar != CharPool.OPEN_PARENTHESIS) &&
						(previousChar != CharPool.SPACE) &&
						(previousLineLength + 1 + x) < 80) {

						String linePart = line.substring(0, x + 1);

						if (linePart.startsWith(StringPool.OPEN_PARENTHESIS) &&
							!linePart.contains(
								StringPool.CLOSE_PARENTHESIS)) {

							return null;
						}

						return new Tuple(
							previousLine + StringPool.SPACE, linePart, true);
					}
				}
			}
		}

		if (previousLine.endsWith(StringPool.COMMA) &&
			(previousLineTabCount == lineTabCount) &&
			!previousLine.contains(StringPool.CLOSE_CURLY_BRACE)) {

			int x = line.indexOf(StringPool.COMMA);

			if (x != -1) {
				while ((previousLineLength + 1 + x) < 80) {
					String linePart = line.substring(0, x + 1);

					if (isValidJavaParameter(linePart)) {
						if (line.equals(linePart)) {
							return new Tuple(
								previousLine + StringPool.SPACE + linePart);
						}
						else {
							return new Tuple(
								previousLine + StringPool.SPACE,
								linePart + StringPool.SPACE, true);
						}
					}

					String partAfterComma = line.substring(x + 1);

					int pos = partAfterComma.indexOf(StringPool.COMMA);

					if (pos == -1) {
						break;
					}

					x = x + pos + 1;
				}
			}
			else if (!line.endsWith(StringPool.OPEN_PARENTHESIS) &&
					 !line.endsWith(StringPool.PLUS) &&
					 !line.endsWith(StringPool.PERIOD) &&
					 (!line.startsWith("new ") ||
					  !line.endsWith(StringPool.OPEN_CURLY_BRACE)) &&
					 ((line.length() + previousLineLength) < 80)) {

				return new Tuple(previousLine + StringPool.SPACE + line);
			}
		}

		if (!previousLine.endsWith(StringPool.OPEN_PARENTHESIS)) {
			return null;
		}

		if (StringUtil.count(previousLine, StringPool.OPEN_PARENTHESIS) > 1) {
			int pos = trimmedPreviousLine.lastIndexOf(
				StringPool.OPEN_PARENTHESIS, trimmedPreviousLine.length() - 2);

			if ((pos > 0) &&
				Character.isLetterOrDigit(
					trimmedPreviousLine.charAt(pos -1 ))) {

				String filePart = trimmedPreviousLine.substring(pos + 1);

				if (!filePart.contains(StringPool.CLOSE_PARENTHESIS) &&
					!filePart.contains(StringPool.QUOTE)) {

					return new Tuple(previousLine, filePart, false);
				}
			}
		}

		if ((line.length() + previousLineLength) > 80) {
			return null;
		}

		if (line.endsWith(StringPool.SEMICOLON)) {
			return new Tuple(previousLine + line);
		}

		if (line.endsWith(StringPool.COMMA)) {
			String strippedQuotesLine = stripQuotes(line, CharPool.QUOTE);

			int openParenthesisCount = StringUtil.count(
				strippedQuotesLine, StringPool.OPEN_PARENTHESIS);
			int closeParenthesisCount = StringUtil.count(
				strippedQuotesLine, StringPool.CLOSE_PARENTHESIS);

			if (closeParenthesisCount > openParenthesisCount) {
				return new Tuple(previousLine + line);
			}
		}

		if (((line.endsWith(StringPool.OPEN_CURLY_BRACE) &&
			  !line.startsWith("new ")) ||
			 line.endsWith(StringPool.CLOSE_PARENTHESIS)) &&
			(trimmedPreviousLine.startsWith("else ") ||
			 trimmedPreviousLine.startsWith("if ") ||
			 trimmedPreviousLine.startsWith("private ") ||
			 trimmedPreviousLine.startsWith("protected ") ||
			 trimmedPreviousLine.startsWith("public "))) {

			return new Tuple(previousLine + line);
		}

		return null;
	}

	protected int getLineLength(String line) {
		int lineLength = 0;

		int tabLength = 4;

		for (char c : line.toCharArray()) {
			if (c == CharPool.TAB) {
				for (int i = 0; i < tabLength; i++) {
					lineLength++;
				}

				tabLength = 4;
			}
			else {
				lineLength++;

				tabLength--;

				if (tabLength <= 0) {
					tabLength = 4;
				}
			}
		}

		return lineLength;
	}

	protected Collection<String> getPluginJavaFiles() {
		Collection<String> fileNames = new TreeSet<String>();

		String[] excludes = new String[] {
			"**\\model\\*Clp.java", "**\\model\\impl\\*BaseImpl.java",
			"**\\model\\impl\\*Model.java", "**\\model\\impl\\*ModelImpl.java",
			"**\\service\\**\\service\\*Service.java",
			"**\\service\\**\\service\\*ServiceClp.java",
			"**\\service\\**\\service\\*ServiceFactory.java",
			"**\\service\\**\\service\\*ServiceUtil.java",
			"**\\service\\**\\service\\*ServiceWrapper.java",
			"**\\service\\**\\service\\ClpSerializer.java",
			"**\\service\\**\\service\\messaging\\*ClpMessageListener.java",
			"**\\service\\**\\service\\persistence\\*Finder.java",
			"**\\service\\**\\service\\persistence\\*Util.java",
			"**\\service\\base\\*ServiceBaseImpl.java",
			"**\\service\\base\\*ServiceClpInvoker.java",
			"**\\service\\http\\*JSONSerializer.java",
			"**\\service\\http\\*ServiceHttp.java",
			"**\\service\\http\\*ServiceJSON.java",
			"**\\service\\http\\*ServiceSoap.java"
		};
		String[] includes = new String[] {"**\\*.java"};

		fileNames.addAll(getFileNames(excludes, includes));

		return fileNames;
	}

	protected Collection<String> getPortalJavaFiles() {
		Collection<String> fileNames = new TreeSet<String>();

		String[] excludes = new String[] {
			"**\\*_IW.java", "**\\PropsValues.java", "**\\counter\\service\\**",
			"**\\jsp\\*", "**\\model\\impl\\*BaseImpl.java",
			"**\\model\\impl\\*Model.java", "**\\model\\impl\\*ModelImpl.java",
			"**\\portal\\service\\**", "**\\portal-client\\**",
			"**\\portal-web\\test\\**\\*Test.java",
			"**\\portlet\\**\\service\\**", "**\\test\\*-generated\\**",
			"**\\tools\\sdk\\**", "**\\tools\\sourceformatter\\**"
		};
		String[] includes = new String[] {"**\\*.java"};

		fileNames.addAll(getFileNames(excludes, includes));

		excludes = new String[] {
			"**\\JavaDocFormatter.java", "**\\portal-client\\**",
			"**\\tools\\ext_tmpl\\**", "**\\*_IW.java",
			"**\\test\\**\\*PersistenceTest.java",
			"**\\tools\\sourceformatter\\**"
		};
		includes = new String[] {
			"**\\com\\liferay\\portal\\service\\ServiceContext*.java",
			"**\\model\\BaseModel.java", "**\\model\\impl\\BaseModelImpl.java",
			"**\\portal-test\\**\\portal\\service\\**\\*.java",
			"**\\service\\Base*.java",
			"**\\service\\PersistedModelLocalService*.java",
			"**\\service\\base\\PrincipalBean.java",
			"**\\service\\http\\*HttpTest.java",
			"**\\service\\http\\*SoapTest.java",
			"**\\service\\http\\TunnelUtil.java", "**\\service\\impl\\*.java",
			"**\\service\\jms\\*.java", "**\\service\\permission\\*.java",
			"**\\service\\persistence\\BasePersistence.java",
			"**\\service\\persistence\\BatchSession*.java",
			"**\\service\\persistence\\*FinderImpl.java",
			"**\\service\\persistence\\*Query.java",
			"**\\service\\persistence\\impl\\*.java",
			"**\\portal-impl\\test\\**\\*.java",
			"**\\util-bridges\\**\\*.java"
		};

		fileNames.addAll(getFileNames(excludes, includes));

		return fileNames;
	}

	protected boolean isGenerated(String content) {
		if (content.contains("* @generated") || content.contains("$ANTLR")) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isValidJavaParameter(String javaParameter) {
		int quoteCount = StringUtil.count(javaParameter, StringPool.QUOTE);

		if ((quoteCount % 2) == 1) {
			return false;
		}

		javaParameter = stripQuotes(javaParameter, CharPool.QUOTE);

		int openParenthesisCount = StringUtil.count(
			javaParameter, StringPool.OPEN_PARENTHESIS);
		int closeParenthesisCount = StringUtil.count(
			javaParameter, StringPool.CLOSE_PARENTHESIS);
		int lessThanCount = StringUtil.count(
			javaParameter, StringPool.LESS_THAN);
		int greaterThanCount = StringUtil.count(
			javaParameter, StringPool.GREATER_THAN);
		int openCurlyBraceCount = StringUtil.count(
			javaParameter, StringPool.OPEN_CURLY_BRACE);
		int closeCurlyBraceCount = StringUtil.count(
			javaParameter, StringPool.CLOSE_CURLY_BRACE);

		if ((openParenthesisCount == closeParenthesisCount) &&
			(lessThanCount == greaterThanCount) &&
			(openCurlyBraceCount == closeCurlyBraceCount)) {

			return true;
		}

		return false;
	}

	protected String sortExceptions(String line) {
		if (!line.endsWith(StringPool.OPEN_CURLY_BRACE) &&
			!line.endsWith(StringPool.SEMICOLON)) {

			return line;
		}

		int x = line.indexOf("throws ");

		if (x == -1) {
			return line;
		}

		String previousException = StringPool.BLANK;

		String[] exceptions = StringUtil.split(
			line.substring(x), CharPool.SPACE);

		for (int i = 1; i < exceptions.length; i++) {
			String exception = exceptions[i];

			if (exception.equals(StringPool.OPEN_CURLY_BRACE)) {
				break;
			}

			if (exception.endsWith(StringPool.COMMA) ||
				exception.endsWith(StringPool.SEMICOLON)) {

				exception = exception.substring(0, exception.length() - 1);
			}

			if (Validator.isNotNull(previousException) &&
				(previousException.compareToIgnoreCase(exception) > 0)) {

				line = StringUtil.replace(
					line, previousException + ", " + exception,
					exception + ", " + previousException);

				return sortExceptions(line);
			}

			previousException = exception;
		}

		return line;
	}

	private static Pattern _importsPattern = Pattern.compile(
		"(^[ \t]*import\\s+.*;\n+)+", Pattern.MULTILINE);

	private boolean _addMissingDeprecationReleaseVersion;
	private boolean _allowUseServiceUtilInServiceImpl;
	private List<String> _fitOnSingleLineExclusions;
	private Pattern _incorrectCloseCurlyBracePattern = Pattern.compile(
		"\n(.+)\n\n(\t+)}\n");
	private Pattern _incorrectLineBreakPattern = Pattern.compile(
		"\t(catch |else |finally |for |if |try |while ).*\\{\n\n\t+\\w");
	private Pattern[] _javaSerializationVulnerabilityPatterns = new Pattern[] {
		Pattern.compile(
			".*(new [a-z\\.\\s]*ObjectInputStream).*", Pattern.DOTALL),
		Pattern.compile(
			".*(extends [a-z\\.\\s]*ObjectInputStream).*", Pattern.DOTALL)
	};
	private List<String> _javaTermSortExclusions;
	private List<String> _lineLengthExclusions;
	private Pattern _logPattern = Pattern.compile(
		"\n\tprivate static Log _log = LogFactoryUtil.getLog\\(\n*" +
			"\t*(.+)\\.class\\)");
	private List<String> _proxyExclusions;
	private List<String> _secureDeserializationExclusionFiles;
	private List<String> _secureRandomExclusions;
	private List<String> _staticLogVariableExclusions;
	private List<String> _testAnnotationsExclusions;
	private List<String> _upgradeServiceUtilExclusions;

}