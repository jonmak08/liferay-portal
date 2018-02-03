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

package com.liferay.gradle.plugins.workspace.configurators;

import com.liferay.gradle.plugins.LiferayThemePlugin;
import com.liferay.gradle.plugins.extensions.LiferayExtension;
<<<<<<< HEAD
import com.liferay.gradle.plugins.theme.builder.BuildThemeTask;
import com.liferay.gradle.plugins.theme.builder.ThemeBuilderPlugin;
import com.liferay.gradle.plugins.workspace.ProjectConfigurator;
import com.liferay.gradle.plugins.workspace.WorkspaceExtension;
import com.liferay.gradle.plugins.workspace.WorkspacePlugin;
import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;

import groovy.json.JsonSlurper;

=======
import com.liferay.gradle.plugins.workspace.WorkspaceExtension;
import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;

>>>>>>> compatible
import groovy.lang.Closure;

import java.io.File;
import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashSet;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> compatible
import java.util.Set;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.CopySpec;
import org.gradle.api.initialization.Settings;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.BasePluginConvention;
import org.gradle.api.plugins.ExtensionAware;
<<<<<<< HEAD
import org.gradle.api.plugins.WarPluginConvention;
=======
>>>>>>> compatible
import org.gradle.api.tasks.Copy;

/**
 * @author Andrea Di Giorgi
 * @author David Truong
 */
public class ThemesProjectConfigurator extends BaseProjectConfigurator {

	public ThemesProjectConfigurator(Settings settings) {
		super(settings);
<<<<<<< HEAD

		_javaBuild = GradleUtil.getProperty(
			settings, WorkspacePlugin.PROPERTY_PREFIX + NAME + ".java.build",
			_JAVA_BUILD);
=======
>>>>>>> compatible
	}

	@Override
	public void apply(Project project) {
		WorkspaceExtension workspaceExtension = GradleUtil.getExtension(
			(ExtensionAware)project.getGradle(), WorkspaceExtension.class);

<<<<<<< HEAD
		if (isJavaBuild()) {
			ProjectConfigurator projectConfigurator =
				workspaceExtension.propertyMissing(
					WarsProjectConfigurator.NAME);

			projectConfigurator.apply(project);

			GradleUtil.applyPlugin(project, ThemeBuilderPlugin.class);

			_configureTaskBuildTheme(project);
			_configureWar(project);
		}
		else {
			GradleUtil.applyPlugin(project, LiferayThemePlugin.class);

			_configureLiferay(project, workspaceExtension);

			Task assembleTask = GradleUtil.getTask(
				project, BasePlugin.ASSEMBLE_TASK_NAME);

			_configureRootTaskDistBundle(assembleTask);
		}
=======
		GradleUtil.applyPlugin(project, LiferayThemePlugin.class);

		_configureLiferay(project, workspaceExtension);

		Task assembleTask = GradleUtil.getTask(
			project, BasePlugin.ASSEMBLE_TASK_NAME);

		_configureRootTaskDistBundle(assembleTask);
>>>>>>> compatible
	}

	@Override
	public String getName() {
<<<<<<< HEAD
		return NAME;
	}

	public boolean isJavaBuild() {
		return _javaBuild;
	}

	public void setJavaBuild(boolean javaBuild) {
		_javaBuild = javaBuild;
=======
		return _NAME;
>>>>>>> compatible
	}

	@Override
	protected Iterable<File> doGetProjectDirs(File rootDir) throws Exception {
		final Set<File> projectDirs = new HashSet<>();

		Files.walkFileTree(
			rootDir.toPath(),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path dirPath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path dirNamePath = dirPath.getFileName();

					String dirName = dirNamePath.toString();

					if (dirName.equals("build") ||
						dirName.equals("build_gradle") ||
						dirName.equals("node_modules")) {

						return FileVisitResult.SKIP_SUBTREE;
					}

					if (Files.exists(dirPath.resolve("package.json"))) {
						projectDirs.add(dirPath.toFile());

						return FileVisitResult.SKIP_SUBTREE;
					}

					return FileVisitResult.CONTINUE;
				}

			});

		return projectDirs;
	}

<<<<<<< HEAD
	protected static final String NAME = "themes";

=======
>>>>>>> compatible
	private void _configureLiferay(
		Project project, WorkspaceExtension workspaceExtension) {

		LiferayExtension liferayExtension = GradleUtil.getExtension(
			project, LiferayExtension.class);

		liferayExtension.setAppServerParentDir(workspaceExtension.getHomeDir());
	}

	private void _configureRootTaskDistBundle(final Task assembleTask) {
		Project project = assembleTask.getProject();

		Copy copy = (Copy)GradleUtil.getTask(
			project.getRootProject(),
			RootProjectConfigurator.DIST_BUNDLE_TASK_NAME);

		copy.dependsOn(assembleTask);

		copy.into(
			"osgi/modules",
			new Closure<Void>(project) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					Project project = assembleTask.getProject();

					ConfigurableFileCollection configurableFileCollection =
						project.files(_getWarFile(project));

					configurableFileCollection.builtBy(assembleTask);

					copySpec.from(_getWarFile(project));
				}

			});
	}

<<<<<<< HEAD
	private void _configureTaskBuildTheme(Project project) {
		File packageJsonFile = project.file("package.json");

		if (!packageJsonFile.exists()) {
			return;
		}

		BuildThemeTask buildThemeTask = (BuildThemeTask)GradleUtil.getTask(
			project, ThemeBuilderPlugin.BUILD_THEME_TASK_NAME);

		JsonSlurper jsonSlurper = new JsonSlurper();

		Map<String, Object> packageJsonMap =
			(Map<String, Object>)jsonSlurper.parse(packageJsonFile);

		Map<String, String> liferayThemeMap =
			(Map<String, String>)packageJsonMap.get("liferayTheme");

		String baseTheme = liferayThemeMap.get("baseTheme");

		if (baseTheme.equals("styled") || baseTheme.equals("unstyled")) {
			baseTheme = "_" + baseTheme;
		}

		String templateLanguage = liferayThemeMap.get("templateLanguage");

		buildThemeTask.setParentName(baseTheme);
		buildThemeTask.setTemplateExtension(templateLanguage);
	}

	private void _configureWar(Project project) {
		WarPluginConvention warPluginConvention = GradleUtil.getConvention(
			project, WarPluginConvention.class);

		warPluginConvention.setWebAppDirName("src");
	}

=======
>>>>>>> compatible
	private File _getWarFile(Project project) {
		BasePluginConvention basePluginConvention = GradleUtil.getConvention(
			project, BasePluginConvention.class);

		return project.file(
			"dist/" + basePluginConvention.getArchivesBaseName() + ".war");
	}

<<<<<<< HEAD
	private static final boolean _JAVA_BUILD = false;

	private boolean _javaBuild;
=======
	private static final String _NAME = "themes";
>>>>>>> compatible

}