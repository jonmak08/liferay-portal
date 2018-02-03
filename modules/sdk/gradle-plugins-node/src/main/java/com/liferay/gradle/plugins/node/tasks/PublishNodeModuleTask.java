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

package com.liferay.gradle.plugins.node.tasks;

import com.liferay.gradle.plugins.node.internal.util.FileUtil;
import com.liferay.gradle.plugins.node.internal.util.GradleUtil;
import com.liferay.gradle.util.Validator;

import groovy.json.JsonOutput;
<<<<<<< HEAD
import groovy.json.JsonSlurper;
=======
>>>>>>> compatible

import groovy.lang.Writable;

import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
<<<<<<< HEAD
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
=======
>>>>>>> compatible

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
=======
import java.util.List;
import java.util.Map;
>>>>>>> compatible

import org.codehaus.groovy.runtime.EncodingGroovyMethods;

import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.Optional;
<<<<<<< HEAD
import org.gradle.util.GUtil;
=======
>>>>>>> compatible

/**
 * @author Andrea Di Giorgi
 */
public class PublishNodeModuleTask extends ExecuteNpmTask {

	@Override
	public void executeNode() throws Exception {
<<<<<<< HEAD
		Project project = getProject();

		File npmrcFile = _getNpmrcFile();
		File packageJsonFile = new File(getWorkingDir(), "package.json");

		Path packageJsonPath = packageJsonFile.toPath();

		Path packageJsonBackupPath = null;

		if (Files.exists(packageJsonPath)) {
			File packageJsonBackupFile = new File(
				getTemporaryDir(), "package.json.backup");

			packageJsonBackupPath = packageJsonBackupFile.toPath();

			Files.copy(
				packageJsonPath, packageJsonBackupPath,
				StandardCopyOption.REPLACE_EXISTING);
		}
		else {
			File rootPackageJsonFile = project.file("package.json");

			if (rootPackageJsonFile.exists()) {
				Files.copy(rootPackageJsonFile.toPath(), packageJsonPath);
			}
		}

		try {
			_createNpmrcFile(npmrcFile);
			_updatePackageJsonFile(packageJsonPath);
=======
		File packageJsonFile = _getPackageJsonFile();

		boolean packageJsonExists = packageJsonFile.exists();

		try {
			_createNpmrcFile();

			if (!packageJsonExists) {
				_createPackageJsonFile();
			}
>>>>>>> compatible

			super.executeNode();
		}
		finally {
<<<<<<< HEAD
			project.delete(npmrcFile);

			if (packageJsonBackupPath != null) {
				Files.move(
					packageJsonBackupPath, packageJsonPath,
					StandardCopyOption.REPLACE_EXISTING);
			}
			else {
				Files.delete(packageJsonPath);
=======
			Project project = getProject();

			project.delete(_getNpmrcFile());

			if (!packageJsonExists) {
				project.delete(packageJsonFile);
>>>>>>> compatible
			}
		}
	}

	@Input
	@Optional
	public String getModuleAuthor() {
		return GradleUtil.toString(_moduleAuthor);
	}

	public String getModuleBugsUrl() {
		return GradleUtil.toString(_moduleBugsUrl);
	}

	@Input
	@Optional
	public String getModuleDescription() {
		return GradleUtil.toString(_moduleDescription);
	}

	@Input
	public List<String> getModuleKeywords() {
		return GradleUtil.toStringList(_moduleKeywords);
	}

	@Input
	@Optional
	public String getModuleLicense() {
		return GradleUtil.toString(_moduleLicense);
	}

	@Input
	@Optional
	public String getModuleMain() {
		return GradleUtil.toString(_moduleMain);
	}

	@Input
	public String getModuleName() {
		return GradleUtil.toString(_moduleName);
	}

	@Input
	@Optional
	public String getModuleRepository() {
		return GradleUtil.toString(_moduleRepository);
	}

	@Input
	public String getModuleVersion() {
		return GradleUtil.toString(_moduleVersion);
	}

	@Input
	public String getNpmEmailAddress() {
		return GradleUtil.toString(_npmEmailAddress);
	}

	@Input
	public String getNpmPassword() {
		return GradleUtil.toString(_npmPassword);
	}

	@Input
	public String getNpmUserName() {
		return GradleUtil.toString(_npmUserName);
	}

<<<<<<< HEAD
	@Input
	public Set<String> getOverriddenPackageJsonKeys() {
		return _overriddenPackageJsonKeys;
	}

=======
>>>>>>> compatible
	@InputDirectory
	@Override
	public File getWorkingDir() {
		return super.getWorkingDir();
	}

<<<<<<< HEAD
	public PublishNodeModuleTask overriddenPackageJsonKeys(
		Iterable<String> overriddenPackageJsonKeys) {

		GUtil.addToCollection(
			_overriddenPackageJsonKeys, overriddenPackageJsonKeys);

		return this;
	}

	public PublishNodeModuleTask overriddenPackageJsonKeys(
		String... overriddenPackageJsonKeys) {

		return overriddenPackageJsonKeys(
			Arrays.asList(overriddenPackageJsonKeys));
	}

=======
>>>>>>> compatible
	public void setModuleAuthor(Object moduleAuthor) {
		_moduleAuthor = moduleAuthor;
	}

	public void setModuleBugsUrl(Object moduleBugsUrl) {
		_moduleBugsUrl = moduleBugsUrl;
	}

	public void setModuleDescription(Object moduleDescription) {
		_moduleDescription = moduleDescription;
	}

	public void setModuleKeywords(Iterable<?> moduleKeywords) {
		_moduleKeywords.clear();
	}

	public void setModuleKeywords(Object... moduleKeywords) {
		setModuleKeywords(Arrays.asList(moduleKeywords));
	}

	public void setModuleLicense(Object moduleLicense) {
		_moduleLicense = moduleLicense;
	}

	public void setModuleMain(Object moduleMain) {
		_moduleMain = moduleMain;
	}

	public void setModuleName(Object moduleName) {
		_moduleName = moduleName;
	}

	public void setModuleRepository(Object moduleRepository) {
		_moduleRepository = moduleRepository;
	}

	public void setModuleVersion(Object moduleVersion) {
		_moduleVersion = moduleVersion;
	}

	public void setNpmEmailAddress(Object npmEmailAddress) {
		_npmEmailAddress = npmEmailAddress;
	}

	public void setNpmPassword(Object npmPassword) {
		_npmPassword = npmPassword;
	}

	public void setNpmUserName(Object npmUserName) {
		_npmUserName = npmUserName;
	}

<<<<<<< HEAD
	public void setOverriddenPackageJsonKeys(
		Iterable<String> overriddenPackageJsonKeys) {

		_overriddenPackageJsonKeys.clear();

		overriddenPackageJsonKeys(overriddenPackageJsonKeys);
	}

	public void setOverriddenPackageJsonKeys(
		String... overriddenPackageJsonKeys) {

		setOverriddenPackageJsonKeys(Arrays.asList(overriddenPackageJsonKeys));
	}

=======
>>>>>>> compatible
	@Override
	protected List<String> getCompleteArgs() {
		List<String> completeArgs = super.getCompleteArgs();

		completeArgs.add("publish");

		completeArgs.add("--userconfig");
		completeArgs.add(FileUtil.getAbsolutePath(_getNpmrcFile()));

		return completeArgs;
	}

<<<<<<< HEAD
	private void _createNpmrcFile(File npmrcFile) throws IOException {
=======
	private void _createNpmrcFile() throws IOException {
>>>>>>> compatible
		List<String> npmrcContents = new ArrayList<>(2);

		npmrcContents.add("_auth = " + _getNpmAuth());
		npmrcContents.add("email = " + getNpmEmailAddress());
		npmrcContents.add("username = " + getNpmUserName());

<<<<<<< HEAD
		FileUtil.write(npmrcFile, npmrcContents);
	}

	private String _getNpmAuth() {
		String auth = getNpmUserName() + ":" + getNpmPassword();

		Writable writable = EncodingGroovyMethods.encodeBase64(auth.getBytes());

		return writable.toString();
	}

	private File _getNpmrcFile() {
		return new File(getTemporaryDir(), "npmrc");
	}

	private void _updatePackageJsonFile(Path packageJsonPath)
		throws IOException {

		Logger logger = getLogger();

		Map<String, Object> map = null;

		if (Files.exists(packageJsonPath)) {
			JsonSlurper jsonSlurper = new JsonSlurper();

			map = (Map<String, Object>)jsonSlurper.parse(
				packageJsonPath.toFile());
		}
		else {
			map = new HashMap<>();
		}

		_updatePackageJsonValue(map, "author", getModuleAuthor());
		_updatePackageJsonValue(map, "bugs", getModuleBugsUrl());
		_updatePackageJsonValue(map, "description", getModuleDescription());
		_updatePackageJsonValue(map, "keywords", getModuleKeywords());
		_updatePackageJsonValue(map, "license", getModuleLicense());
		_updatePackageJsonValue(map, "main", getModuleMain());
		_updatePackageJsonValue(map, "name", getModuleName());
		_updatePackageJsonValue(map, "repository", getModuleRepository());
		_updatePackageJsonValue(map, "version", getModuleVersion());
=======
		FileUtil.write(_getNpmrcFile(), npmrcContents);
	}

	private void _createPackageJsonFile() throws IOException {
		Logger logger = getLogger();

		Map<String, Object> map = new HashMap<>();

		String author = getModuleAuthor();

		if (Validator.isNotNull(author)) {
			map.put("author", author);
		}

		String bugsUrl = getModuleBugsUrl();

		if (Validator.isNotNull(bugsUrl)) {
			map.put("bugs", bugsUrl);
		}

		String description = getModuleDescription();

		if (Validator.isNotNull(description)) {
			map.put("description", description);
		}

		List<String> keywords = getModuleKeywords();

		if (!keywords.isEmpty()) {
			map.put("keywords", keywords);
		}

		String license = getModuleLicense();

		if (Validator.isNotNull(license)) {
			map.put("license", license);
		}

		String main = getModuleMain();

		if (Validator.isNotNull(main)) {
			map.put("main", main);
		}

		map.put("name", getModuleName());

		String repository = getModuleRepository();

		if (Validator.isNotNull(repository)) {
			map.put("repository", repository);
		}

		map.put("version", getModuleVersion());
>>>>>>> compatible

		String json = JsonOutput.toJson(map);

		if (logger.isInfoEnabled()) {
			logger.info(json);
		}

<<<<<<< HEAD
		Files.write(packageJsonPath, json.getBytes(StandardCharsets.UTF_8));
	}

	private void _updatePackageJsonValue(
		Map<String, Object> map, String key, Object value) {

		if ((value == null) ||
			((value instanceof String) && Validator.isNull((String)value))) {

			return;
		}

		Set<String> overriddenPackageJsonKeys = getOverriddenPackageJsonKeys();

		if (!map.containsKey(key) || overriddenPackageJsonKeys.contains(key)) {
			map.put(key, value);
		}
=======
		File packageJsonFile = _getPackageJsonFile();

		Files.write(
			packageJsonFile.toPath(), json.getBytes(StandardCharsets.UTF_8));
	}

	private String _getNpmAuth() {
		String auth = getNpmUserName() + ":" + getNpmPassword();

		Writable writable = EncodingGroovyMethods.encodeBase64(auth.getBytes());

		return writable.toString();
	}

	private File _getNpmrcFile() {
		return new File(getTemporaryDir(), "npmrc");
	}

	private File _getPackageJsonFile() {
		return new File(getWorkingDir(), "package.json");
>>>>>>> compatible
	}

	private Object _moduleAuthor;
	private Object _moduleBugsUrl;
	private Object _moduleDescription;
	private final List<Object> _moduleKeywords = new ArrayList<>();
	private Object _moduleLicense;
	private Object _moduleMain;
	private Object _moduleName;
	private Object _moduleRepository;
	private Object _moduleVersion;
	private Object _npmEmailAddress;
	private Object _npmPassword;
	private Object _npmUserName;
<<<<<<< HEAD
	private final Set<String> _overriddenPackageJsonKeys = new HashSet<>();
=======
>>>>>>> compatible

}