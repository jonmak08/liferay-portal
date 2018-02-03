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

package com.liferay.gradle.plugins.internal;

import com.liferay.gradle.plugins.BaseDefaultsPlugin;
import com.liferay.gradle.plugins.internal.util.FileUtil;
import com.liferay.gradle.plugins.internal.util.GradleUtil;
<<<<<<< HEAD
import com.liferay.gradle.plugins.node.NodePlugin;
import com.liferay.gradle.plugins.node.tasks.NpmInstallTask;
=======
>>>>>>> compatible

import groovy.lang.Closure;

import java.io.File;

import java.util.Set;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.XmlProvider;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetOutput;
import org.gradle.plugins.ide.idea.IdeaPlugin;
import org.gradle.plugins.ide.idea.model.IdeaModel;
import org.gradle.plugins.ide.idea.model.IdeaModule;
import org.gradle.plugins.ide.idea.model.IdeaModuleIml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Andrea Di Giorgi
 */
public class IdeaDefaultsPlugin extends BaseDefaultsPlugin<IdeaPlugin> {

	public static final Plugin<Project> INSTANCE = new IdeaDefaultsPlugin();

	@Override
	protected void configureDefaults(
		Project project, final IdeaPlugin ideaPlugin) {

		_configureIdeaModuleIml(project, ideaPlugin);
		_configureTaskIdea(ideaPlugin);

		project.afterEvaluate(
			new Action<Project>() {

				@Override
				public void execute(Project project) {
					_configureIdeaModuleExcludeDirs(project, ideaPlugin);
				}

			});
	}

	@Override
	protected Class<IdeaPlugin> getPluginClass() {
		return IdeaPlugin.class;
	}

	private IdeaDefaultsPlugin() {
	}

	private void _configureIdeaModuleExcludeDirs(
		Project project, IdeaPlugin ideaPlugin) {

<<<<<<< HEAD
=======
		if (!GradleUtil.hasPlugin(project, JavaPlugin.class)) {
			return;
		}

>>>>>>> compatible
		IdeaModule ideaModule = _getIdeaModule(ideaPlugin);

		Set<File> excludeDirs = ideaModule.getExcludeDirs();

<<<<<<< HEAD
		if (GradleUtil.hasPlugin(project, JavaPlugin.class)) {
			SourceSet sourceSet = GradleUtil.getSourceSet(
				project, SourceSet.MAIN_SOURCE_SET_NAME);

			SourceSetOutput sourceSetOutput = sourceSet.getOutput();

			File classesDir = sourceSetOutput.getClassesDir();

			if (!FileUtil.isChild(classesDir, project.getBuildDir())) {
				excludeDirs.add(classesDir);
			}

			File resourcesDir = sourceSetOutput.getResourcesDir();

			if (!FileUtil.isChild(resourcesDir, project.getBuildDir())) {
				excludeDirs.add(resourcesDir);
			}
		}

		if (GradleUtil.hasPlugin(project, NodePlugin.class)) {
			NpmInstallTask npmInstallTask = (NpmInstallTask)GradleUtil.getTask(
				project, NodePlugin.NPM_INSTALL_TASK_NAME);

			excludeDirs.add(npmInstallTask.getNodeModulesDir());
=======
		SourceSet sourceSet = GradleUtil.getSourceSet(
			project, SourceSet.MAIN_SOURCE_SET_NAME);

		SourceSetOutput sourceSetOutput = sourceSet.getOutput();

		File classesDir = sourceSetOutput.getClassesDir();

		if (!FileUtil.isChild(classesDir, project.getBuildDir())) {
			excludeDirs.add(classesDir);
		}

		File resourcesDir = sourceSetOutput.getResourcesDir();

		if (!FileUtil.isChild(resourcesDir, project.getBuildDir())) {
			excludeDirs.add(resourcesDir);
>>>>>>> compatible
		}

		ideaModule.setExcludeDirs(excludeDirs);
	}

	private void _configureIdeaModuleIml(
		final Project project, IdeaPlugin ideaPlugin) {

		IdeaModule ideaModule = _getIdeaModule(ideaPlugin);

		IdeaModuleIml ideaModuleIml = ideaModule.getIml();

		Closure<Void> closure = new Closure<Void>(project) {

			@SuppressWarnings("unused")
			public void doCall(XmlProvider xmlProvider) throws Exception {
				if (!GradleUtil.hasPlugin(project, JavaPlugin.class)) {
					return;
				}

				SourceSet sourceSet = GradleUtil.getSourceSet(
					project, SourceSet.MAIN_SOURCE_SET_NAME);

				File resourcesDir = new File(
					GradleUtil.getSrcDir(sourceSet.getResources()),
					"META-INF/resources");

				if (!resourcesDir.exists()) {
					return;
				}

				Element moduleElement = xmlProvider.asElement();

				Document document = moduleElement.getOwnerDocument();

				Element componentElement = document.createElement("component");

				componentElement.setAttribute("name", "FacetManager");

				NodeList componentElements = moduleElement.getElementsByTagName(
					"component");

				moduleElement.insertBefore(
					componentElement,
					componentElements.item(componentElements.getLength() - 1));

				Element facetElement = document.createElement("facet");

				facetElement.setAttribute("name", "Web");
				facetElement.setAttribute("type", "web");

				componentElement.appendChild(facetElement);

				Element configurationElement = document.createElement(
					"configuration");

				facetElement.appendChild(configurationElement);

				Element webrootsElement = document.createElement("webroots");

				configurationElement.appendChild(webrootsElement);

				Element rootElement = document.createElement("root");

				rootElement.setAttribute("relative", "/");

				String url = project.relativePath(resourcesDir);

				rootElement.setAttribute(
					"url", "file://$MODULE_DIR$/" + url.replace('\\', '/'));

				webrootsElement.appendChild(rootElement);
			}

		};

		ideaModuleIml.withXml(closure);
	}

	private void _configureTaskIdea(IdeaPlugin ideaPlugin) {
		Task task = ideaPlugin.getLifecycleTask();

		task.dependsOn(ideaPlugin.getCleanTask());
	}

	private IdeaModule _getIdeaModule(IdeaPlugin ideaPlugin) {
		IdeaModel ideaModel = ideaPlugin.getModel();

		IdeaModule ideaModule = ideaModel.getModule();

		return ideaModule;
	}

}