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

package com.liferay.css.builder.ant;

<<<<<<< HEAD
import com.liferay.css.builder.CSSBuilder;
import com.liferay.css.builder.CSSBuilderArgs;

import java.io.File;

import org.apache.tools.ant.BuildException;
=======
import com.liferay.css.builder.CSSBuilderArgs;
import com.liferay.css.builder.CSSBuilderInvoker;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
>>>>>>> compatible
import org.apache.tools.ant.Task;

/**
 * @author Andrea Di Giorgi
 */
public class BuildCSSTask extends Task {

	@Override
	public void execute() throws BuildException {
<<<<<<< HEAD
=======
		Project project = getProject();

>>>>>>> compatible
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(
			BuildCSSTask.class.getClassLoader());

<<<<<<< HEAD
		try (CSSBuilder cssBuilder = new CSSBuilder(_cssBuilderArgs)) {
			cssBuilder.execute();
=======
		try {
			CSSBuilderInvoker.invoke(project.getBaseDir(), _cssBuilderArgs);
>>>>>>> compatible
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void setAppendCssImportTimestamps(
		boolean appendCssImportTimestamps) {

		_cssBuilderArgs.setAppendCssImportTimestamps(appendCssImportTimestamps);
	}

<<<<<<< HEAD
	public void setBaseDir(File baseDir) {
		_cssBuilderArgs.setBaseDir(baseDir);
	}

=======
>>>>>>> compatible
	public void setDirNames(String dirNames) {
		_cssBuilderArgs.setDirNames(dirNames);
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.1.0, replaced by {@link #setBaseDir(File)}
	 */
	@Deprecated
	public void setDocrootDir(File docrootDir) {
		setBaseDir(docrootDir);
=======
	public void setDocrootDirName(String docrootDirName) {
		_cssBuilderArgs.setDocrootDirName(docrootDirName);
>>>>>>> compatible
	}

	public void setGenerateSourceMap(boolean generateSourceMap) {
		_cssBuilderArgs.setGenerateSourceMap(generateSourceMap);
	}

<<<<<<< HEAD
	public void setImportDir(File importDir) {
		_cssBuilderArgs.setImportDir(importDir);
	}

=======
>>>>>>> compatible
	public void setOutputDirName(String outputDirName) {
		_cssBuilderArgs.setOutputDirName(outputDirName);
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.1.0, replaced by {@link #setImportDir(File)}
	 */
	@Deprecated
	public void setPortalCommonPath(File portalCommonPath) {
		setImportDir(portalCommonPath);
=======
	public void setPortalCommonPath(String portalCommonPath) {
		_cssBuilderArgs.setPortalCommonPath(portalCommonPath);
>>>>>>> compatible
	}

	public void setPrecision(int precision) {
		_cssBuilderArgs.setPrecision(precision);
	}

	public void setRtlExcludedPathRegexps(String rtlExcludedPathRegexps) {
		_cssBuilderArgs.setRtlExcludedPathRegexps(rtlExcludedPathRegexps);
	}

	public void setSassCompilerClassName(String sassCompilerClassName) {
		_cssBuilderArgs.setSassCompilerClassName(sassCompilerClassName);
	}

	private final CSSBuilderArgs _cssBuilderArgs = new CSSBuilderArgs();

}