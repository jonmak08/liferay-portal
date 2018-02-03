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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.source.formatter.BNDSettings;
import com.liferay.source.formatter.checks.util.JavaSourceUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaClassParser;
import com.liferay.source.formatter.parser.JavaConstructor;
import com.liferay.source.formatter.parser.JavaMethod;
import com.liferay.source.formatter.parser.JavaTerm;
import com.liferay.source.formatter.util.FileUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaOSGiReferenceCheck extends BaseFileCheck {

	@Override
<<<<<<< HEAD
=======
	public void init() throws Exception {
		_moduleFileNamesMap = _getModuleFileNamesMap();
		_serviceProxyFactoryUtilClassNames =
			_getServiceProxyFactoryUtilClassNames();
	}

	@Override
>>>>>>> compatible
	public boolean isModulesCheck() {
		return true;
	}

<<<<<<< HEAD
	public void setServiceReferenceUtilClassName(
		String serviceReferenceUtilClassName) {

		_serviceReferenceUtilClassNames.add(serviceReferenceUtilClassName);
=======
	public void setServiceReferenceUtilClassNames(
		String serviceReferenceUtilClassNames) {

		_serviceReferenceUtilClassNames = StringUtil.split(
			serviceReferenceUtilClassNames);

		for (int i = 0; i < _serviceReferenceUtilClassNames.length; i++) {
			_serviceReferenceUtilClassNames[i] = StringUtil.trim(
				_serviceReferenceUtilClassNames[i]);
		}
>>>>>>> compatible
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (!content.contains("@Component")) {
			return content;
		}

<<<<<<< HEAD
		String packageName = JavaSourceUtil.getPackageName(content);

		if (!packageName.startsWith("com.liferay")) {
=======
		String packagePath = JavaSourceUtil.getPackagePath(content);

		if (!packagePath.startsWith("com.liferay")) {
>>>>>>> compatible
			return content;
		}

		_checkMissingReference(fileName, content);

		String className = JavaSourceUtil.getClassName(fileName);

		String moduleSuperClassContent = _getModuleSuperClassContent(
<<<<<<< HEAD
			content, className, packageName);
=======
			content, className, packagePath);
>>>>>>> compatible

		content = _formatDuplicateReferenceMethods(
			fileName, content, moduleSuperClassContent);

		if (!isExcludedPath("service.reference.util.excludes", absolutePath)) {
			for (String serviceProxyFactoryUtilClassName :
<<<<<<< HEAD
					_getServiceProxyFactoryUtilClassNames()) {
=======
					_serviceProxyFactoryUtilClassNames) {
>>>>>>> compatible

				_checkUtilUsage(
					fileName, content, serviceProxyFactoryUtilClassName,
					moduleSuperClassContent);
			}
		}

		for (String serviceReferenceUtilClassName :
				_serviceReferenceUtilClassNames) {

			_checkUtilUsage(
				fileName, content, serviceReferenceUtilClassName,
				moduleSuperClassContent);
		}

		Matcher matcher = _referenceMethodPattern.matcher(content);

		while (matcher.find()) {
			String methodName = matcher.group(4);

			if (!methodName.startsWith("set")) {
				continue;
			}

			String annotationParameters = matcher.group(1);
			String methodContent = matcher.group();

			content = _formatMissingUnbindAnnotation(
				content, methodName, methodContent, annotationParameters);

			String methodBody = matcher.group(6);
			String typeName = matcher.group(5);

			content = _formatVolatileReferenceVariable(
				content, methodBody, typeName);
		}

		return content;
	}

	private void _checkMissingReference(String fileName, String content) {
<<<<<<< HEAD
		String moduleServicePackageName = null;
=======
		String moduleServicePackagePath = null;
>>>>>>> compatible

		Matcher matcher = _serviceUtilImportPattern.matcher(content);

		while (matcher.find()) {
			String serviceUtilClassName = matcher.group(2);

<<<<<<< HEAD
			if (moduleServicePackageName == null) {
				moduleServicePackageName = _getModuleServicePackageName(
					fileName);
			}

			if (Validator.isNotNull(moduleServicePackageName)) {
				String serviceUtilClassPackageName = matcher.group(1);

				if (serviceUtilClassPackageName.startsWith(
						moduleServicePackageName)) {
=======
			if (moduleServicePackagePath == null) {
				moduleServicePackagePath = _getModuleServicePackagePath(
					fileName);
			}

			if (Validator.isNotNull(moduleServicePackagePath)) {
				String serviceUtilClassPackagePath = matcher.group(1);

				if (serviceUtilClassPackagePath.startsWith(
						moduleServicePackagePath)) {
>>>>>>> compatible

					continue;
				}
			}

			addMessage(
				fileName,
				"Use @Reference instead of calling " + serviceUtilClassName +
<<<<<<< HEAD
					" directly",
				"osgi_components.markdown");
=======
					" directly, see LPS-59076");
>>>>>>> compatible
		}
	}

	private void _checkUtilUsage(
			String fileName, String content,
			String serviceReferenceUtilClassName,
			String moduleSuperClassContent)
		throws Exception {

		if (!content.contains(serviceReferenceUtilClassName) ||
			(Validator.isNotNull(moduleSuperClassContent) &&
			 moduleSuperClassContent.contains("@Component"))) {

			return;
		}

		int pos = serviceReferenceUtilClassName.lastIndexOf(StringPool.PERIOD);

		String shortClassName = serviceReferenceUtilClassName.substring(
			pos + 1);

		JavaClass javaClass = JavaClassParser.parseJavaClass(fileName, content);

		for (JavaTerm javaTerm : javaClass.getChildJavaTerms()) {
			if (!javaTerm.isStatic() &&
				(javaTerm instanceof JavaConstructor ||
				 javaTerm instanceof JavaMethod) &&
				!javaTerm.hasAnnotation("Reference")) {

				String javaTermContent = javaTerm.getContent();

				if (javaTermContent.contains(
						shortClassName + StringPool.PERIOD)) {

					addMessage(
						fileName,
						"Use portal service reference instead of '" +
<<<<<<< HEAD
							serviceReferenceUtilClassName + "' in modules",
						"osgi_components.markdown");
=======
							serviceReferenceUtilClassName +
								"' in modules, see LPS-69661");
>>>>>>> compatible

					return;
				}
			}
		}
	}

	private String _formatDuplicateReferenceMethods(
			String fileName, String content, String moduleSuperClassContent)
		throws Exception {

		if (Validator.isNull(moduleSuperClassContent) ||
			!moduleSuperClassContent.contains("@Component") ||
			!moduleSuperClassContent.contains("@Reference")) {

			return content;
		}

		Matcher matcher = _referenceMethodPattern.matcher(
			moduleSuperClassContent);

		boolean bndInheritRequired = false;

		while (matcher.find()) {
			String referenceMethod = matcher.group();

			int pos = content.indexOf(referenceMethod);

			if (pos != -1) {
				String referenceMethodContent = matcher.group(6);

				Matcher referenceMethodContentMatcher =
					_referenceMethodContentPattern.matcher(
						referenceMethodContent);

				if (referenceMethodContentMatcher.find()) {
					String variableName = referenceMethodContentMatcher.group(
						1);

					if (StringUtil.count(content, variableName) > 1) {
						continue;
					}
				}

				int x = content.lastIndexOf("\n\n", pos);
				int y = pos + referenceMethod.length();

				String entireMethod = content.substring(x + 1, y);

				content = StringUtil.replace(
					content, entireMethod, StringPool.BLANK);

				bndInheritRequired = true;
			}
			else {
				String referenceMethodModifierAndName = matcher.group(2);

				Pattern duplicateReferenceMethodPattern = Pattern.compile(
					referenceMethodModifierAndName +
						"\\(\\s*([ ,<>\\w]+)\\s+\\w+\\) \\{\\s+([\\s\\S]*?)" +
							"\\s*?\n\t\\}\n");

				Matcher duplicateReferenceMethodMatcher =
					duplicateReferenceMethodPattern.matcher(content);

				if (!duplicateReferenceMethodMatcher.find()) {
					bndInheritRequired = true;

					continue;
				}

				String methodContent = duplicateReferenceMethodMatcher.group(2);
				String referenceMethodName = matcher.group(4);

				if (methodContent.startsWith("super." + referenceMethodName)) {
					int x = content.lastIndexOf(
						"\n\n", duplicateReferenceMethodMatcher.start());
					int y = duplicateReferenceMethodMatcher.end();

					String entireMethod = content.substring(x + 1, y);

					content = StringUtil.replace(
						content, entireMethod, StringPool.BLANK);

					bndInheritRequired = true;
				}
			}
		}

		if (bndInheritRequired) {
			BNDSettings bndSettings = getBNDSettings(fileName);

			String bndSettingsContent = bndSettings.getContent();

			if (!bndSettingsContent.contains(
					"-dsannotations-options: inherit") &&
				_bndFileNames.add(bndSettings.getFileName())) {

				addMessage(
					fileName,
					"Add '-dsannotations-options: inherit' to '" +
<<<<<<< HEAD
						bndSettings.getFileName(),
					"osgi_components_inheritance.markdown");
=======
						bndSettings.getFileName());
>>>>>>> compatible
			}
		}

		return content;
	}

	private String _formatMissingUnbindAnnotation(
		String content, String methodName, String methodContent,
		String annotationParameters) {

		if (annotationParameters.contains("unbind =") ||
			content.contains("un" + methodName + "(")) {

			return content;
		}

		if (Validator.isNull(annotationParameters)) {
			String newMethodContent = StringUtil.replaceFirst(
				methodContent, "@Reference", "@Reference(unbind = \"-\")");

			return StringUtil.replace(content, methodContent, newMethodContent);
		}

		if (!annotationParameters.contains(StringPool.NEW_LINE)) {
			String newAnnotationParameters = StringUtil.replaceLast(
				annotationParameters, CharPool.CLOSE_PARENTHESIS,
				", unbind = \"-\")");

			String newMethodContent = StringUtil.replaceFirst(
				methodContent, annotationParameters, newAnnotationParameters);

			return StringUtil.replace(content, methodContent, newMethodContent);
		}

		if (!annotationParameters.contains("\n\n")) {
			String newAnnotationParameters = StringUtil.replaceLast(
				annotationParameters, "\n", ",\n\t\tunbind = \"-\"\n");

			String newMethodContent = StringUtil.replaceFirst(
				methodContent, annotationParameters, newAnnotationParameters);

			return StringUtil.replace(content, methodContent, newMethodContent);
		}

		return content;
	}

	private String _formatVolatileReferenceVariable(
		String content, String methodBody, String typeName) {

		Matcher matcher = _referenceMethodContentPattern.matcher(methodBody);

		if (!matcher.find()) {
			return content;
		}

		String variableName = matcher.group(1);

		StringBundler sb = new StringBundler(5);

		sb.append("private volatile ");
		sb.append(typeName);
		sb.append("\\s+");
		sb.append(variableName);
		sb.append(StringPool.SEMICOLON);

		Pattern privateVarPattern = Pattern.compile(sb.toString());

		matcher = privateVarPattern.matcher(content);

		if (matcher.find()) {
			String match = matcher.group();

			String replacement = StringUtil.replace(
				match, "private volatile ", "private ");

			return StringUtil.replace(content, match, replacement);
		}

		return content;
	}

	private String _getModuleClassContent(String fullClassName)
		throws Exception {

		String classContent = _moduleFileContentsMap.get(fullClassName);

		if (classContent != null) {
			return classContent;
		}

<<<<<<< HEAD
		Map<String, String> moduleFileNamesMap = _getModuleFileNamesMap();

		String moduleFileName = moduleFileNamesMap.get(fullClassName);
=======
		String moduleFileName = _moduleFileNamesMap.get(fullClassName);
>>>>>>> compatible

		if (moduleFileName == null) {
			_moduleFileContentsMap.put(fullClassName, StringPool.BLANK);

			return StringPool.BLANK;
		}

		File file = new File(moduleFileName);

		classContent = FileUtil.read(file);

		if (classContent != null) {
			_moduleFileContentsMap.put(fullClassName, classContent);
		}

		return classContent;
	}

<<<<<<< HEAD
	private synchronized Map<String, String> _getModuleFileNamesMap()
		throws Exception {

		if (_moduleFileNamesMap != null) {
			return _moduleFileNamesMap;
		}

		_moduleFileNamesMap = new HashMap<>();
=======
	private Map<String, String> _getModuleFileNamesMap() throws Exception {
		Map<String, String> moduleFileNamesMap = new HashMap<>();
>>>>>>> compatible

		List<String> fileNames = new ArrayList<>();

		String moduleRootDirLocation = "modules/";

		for (int i = 0; i < 6; i++) {
			File file = new File(getBaseDirName() + moduleRootDirLocation);

			if (file.exists()) {
				fileNames = getFileNames(
					getBaseDirName() + moduleRootDirLocation, new String[0],
					new String[] {"**/*.java"});

				break;
			}

			moduleRootDirLocation = "../" + moduleRootDirLocation;
		}

		for (String fileName : fileNames) {
			fileName = StringUtil.replace(
				fileName, CharPool.BACK_SLASH, CharPool.SLASH);

			String className = StringUtil.replace(
				fileName, CharPool.SLASH, CharPool.PERIOD);

			int pos = className.lastIndexOf(".com.liferay.");

			className = className.substring(pos + 1, fileName.length() - 5);

<<<<<<< HEAD
			_moduleFileNamesMap.put(className, fileName);
		}

		return _moduleFileNamesMap;
	}

	private String _getModuleServicePackageName(String fileName) {
=======
			moduleFileNamesMap.put(className, fileName);
		}

		return moduleFileNamesMap;
	}

	private String _getModuleServicePackagePath(String fileName) {
>>>>>>> compatible
		String serviceDirLocation = fileName;

		while (true) {
			int pos = serviceDirLocation.lastIndexOf(StringPool.SLASH);

			if (pos == -1) {
				return StringPool.BLANK;
			}

			serviceDirLocation = serviceDirLocation.substring(0, pos + 1);

			File file = new File(serviceDirLocation + "service");

			if (file.exists()) {
				serviceDirLocation = serviceDirLocation + "service";

				break;
			}

			file = new File(serviceDirLocation + "liferay");

			if (file.exists()) {
				return StringPool.BLANK;
			}

			serviceDirLocation = StringUtil.replaceLast(
				serviceDirLocation, CharPool.SLASH, StringPool.BLANK);
		}

		serviceDirLocation = StringUtil.replace(
			serviceDirLocation, CharPool.SLASH, CharPool.PERIOD);

		int pos = serviceDirLocation.lastIndexOf(".com.");

		return serviceDirLocation.substring(pos + 1);
	}

	private String _getModuleSuperClassContent(
<<<<<<< HEAD
			String content, String className, String packageName)
=======
			String content, String className, String packagePath)
>>>>>>> compatible
		throws Exception {

		Pattern pattern = Pattern.compile(
			" class " + className + "\\s+extends\\s+([\\w.]+) ");

		Matcher matcher = pattern.matcher(content);

		if (!matcher.find()) {
			return null;
		}

		String superClassName = matcher.group(1);

		if (superClassName.contains(StringPool.PERIOD)) {
			if (!superClassName.startsWith("com.liferay")) {
				return null;
			}

			return _getModuleClassContent(superClassName);
		}

<<<<<<< HEAD
		String superClassPackageName = packageName;
=======
		String superClassPackagePath = packagePath;
>>>>>>> compatible

		pattern = Pattern.compile("\nimport (.+?)\\." + superClassName + ";");

		matcher = pattern.matcher(content);

		if (matcher.find()) {
<<<<<<< HEAD
			superClassPackageName = matcher.group(1);
		}

		if (!superClassPackageName.startsWith("com.liferay")) {
=======
			superClassPackagePath = matcher.group(1);
		}

		if (!superClassPackagePath.startsWith("com.liferay")) {
>>>>>>> compatible
			return null;
		}

		String superClassFullClassName =
<<<<<<< HEAD
			superClassPackageName + StringPool.PERIOD + superClassName;
=======
			superClassPackagePath + StringPool.PERIOD + superClassName;
>>>>>>> compatible

		return _getModuleClassContent(superClassFullClassName);
	}

<<<<<<< HEAD
	private synchronized List<String> _getServiceProxyFactoryUtilClassNames()
		throws Exception {

		if (_serviceProxyFactoryUtilClassNames != null) {
			return _serviceProxyFactoryUtilClassNames;
		}

		if (!isPortalSource()) {
			_serviceProxyFactoryUtilClassNames = Collections.emptyList();

			return _serviceProxyFactoryUtilClassNames;
=======
	private List<String> _getServiceProxyFactoryUtilClassNames()
		throws Exception {

		if (!isPortalSource()) {
			return Collections.emptyList();
>>>>>>> compatible
		}

		String portalKernelLocation = "portal-kernel/";

		for (int i = 0; i < ToolsUtil.PORTAL_MAX_DIR_LEVEL - 1; i++) {
			File file = new File(getBaseDirName() + portalKernelLocation);

			if (!file.exists()) {
				portalKernelLocation = "../" + portalKernelLocation;

				continue;
			}

<<<<<<< HEAD
			_serviceProxyFactoryUtilClassNames = new ArrayList<>();
=======
			List<String> serviceProxyFactoryUtilClassNames = new ArrayList<>();
>>>>>>> compatible

			List<String> utilFileNames = getFileNames(
				getBaseDirName() + portalKernelLocation, new String[0],
				new String[] {"**/*Util.java"});

			for (String fileName : utilFileNames) {
				fileName = StringUtil.replace(
					fileName, CharPool.BACK_SLASH, CharPool.SLASH);

				String content = FileUtil.read(new File(fileName));

				if (content.contains(
						"com.liferay.portal.kernel.util.ServiceProxyFactory")) {

<<<<<<< HEAD
					_serviceProxyFactoryUtilClassNames.add(
						JavaSourceUtil.getPackageName(content) + "." +
=======
					serviceProxyFactoryUtilClassNames.add(
						JavaSourceUtil.getPackagePath(content) + "." +
>>>>>>> compatible
							JavaSourceUtil.getClassName(fileName));
				}
			}

<<<<<<< HEAD
			return _serviceProxyFactoryUtilClassNames;
		}

		_serviceProxyFactoryUtilClassNames = Collections.emptyList();

		return _serviceProxyFactoryUtilClassNames;
=======
			return serviceProxyFactoryUtilClassNames;
		}

		return Collections.emptyList();
>>>>>>> compatible
	}

	private final Set<String> _bndFileNames = new CopyOnWriteArraySet<>();
	private final Map<String, String> _moduleFileContentsMap =
		new ConcurrentHashMap<>();
	private Map<String, String> _moduleFileNamesMap;
	private final Pattern _referenceMethodContentPattern = Pattern.compile(
		"^(\\w+) =\\s+\\w+;$");
	private final Pattern _referenceMethodPattern = Pattern.compile(
		"\n\t@Reference([\\s\\S]*?)\\s+((protected|public) void (\\w+?))\\(" +
			"\\s*([ ,<>\\w]+)\\s+\\w+\\) \\{\\s+([\\s\\S]*?)\\s*?\n\t\\}\n");
	private List<String> _serviceProxyFactoryUtilClassNames;
<<<<<<< HEAD
	private final List<String> _serviceReferenceUtilClassNames =
		new ArrayList<>();
=======
	private String[] _serviceReferenceUtilClassNames = new String[0];
>>>>>>> compatible
	private final Pattern _serviceUtilImportPattern = Pattern.compile(
		"\nimport ([A-Za-z1-9\\.]*)\\.([A-Za-z1-9]*ServiceUtil);");

}