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

package com.liferay.portal.scripting.ruby.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.scripting.ruby.configuration.RubyScriptingConfiguration",
	localization = "content/Language",
<<<<<<< HEAD
	name = "ruby-scripting-configuration-name"
=======
	name = "ruby.scripting.configuration.name"

>>>>>>> compatible
)
public interface RubyScriptingConfiguration {

	@Meta.AD(
<<<<<<< HEAD
		deflt = "jit", name = "compile-mode",
		optionValues = {"force", "jit", "none"}, required = false
	)
	public String compileMode();

	@Meta.AD(deflt = "5", name = "compile-threshold", required = false)
=======
		deflt = "jit", optionValues = {"force", "jit", "none"}, required = false
	)
	public String compileMode();

	@Meta.AD(deflt = "5", required = false)
>>>>>>> compatible
	public int compileThreshold();

	@Meta.AD(
		deflt = "classpath:/META-INF/jruby.home/lib/ruby/2.0|classpath:/META-INF/jruby.home/lib/ruby/shared",
<<<<<<< HEAD
		name = "load-paths", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String[] loadPaths();

}