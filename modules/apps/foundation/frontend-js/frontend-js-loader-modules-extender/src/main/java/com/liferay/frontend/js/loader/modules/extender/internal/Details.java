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

package com.liferay.frontend.js.loader.modules.extender.internal;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

<<<<<<< HEAD
=======
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

>>>>>>> compatible
/**
 * @author Raymond Augé
 */
@ExtendedObjectClassDefinition(category = "foundation")
<<<<<<< HEAD
@Meta.OCD(
	id = "com.liferay.frontend.js.loader.modules.extender.internal.Details",
	localization = "content/Language", name = "details-configuration-name"
=======
@ObjectClassDefinition(
	id = "com.liferay.frontend.js.loader.modules.extender.internal.Details",
	localization = "content/Language", name = "details.configuration.name"
>>>>>>> compatible
)
public @interface Details {

	public static final String CONFIG_JSON = "META-INF/config.json";

	public static final String CONTENT_TYPE = "text/javascript; charset=UTF-8";

	public static final int MAX_VALUE_LESS_1K = Integer.MAX_VALUE - 1000;

	public static final String OSGI_WEBRESOURCE = "osgi.webresource";

<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "apply-versioning", required = false)
	public boolean applyVersioning();

	@Meta.AD(deflt = "false", name = "explain-resolutions", required = false)
	public boolean explainResolutions();

	@Meta.AD(deflt = "false", name = "expose-global", required = false)
	public boolean exposeGlobal();
=======
	@Meta.AD(deflt = "true")
	public boolean applyVersioning() default true;

	@Meta.AD(deflt = "true")
	public boolean exposeGlobal() default true;
>>>>>>> compatible

}