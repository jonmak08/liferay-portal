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

package com.liferay.web.form.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	category = "forms-and-workflow",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.web.form.web.configuration.WebFormServiceConfiguration",
	localization = "content/Language",
	name = "web-form-service-configuration-name"
)
public interface WebFormServiceConfiguration {

<<<<<<< HEAD
	@Meta.AD(deflt = ";", name = "csv-separator", required = false)
	public String csvSeparator();

	@Meta.AD(deflt = "data/web_form", name = "data-root-dir", required = false)
=======
	@Meta.AD(deflt = ";", required = false)
	public String csvSeparator();

	@Meta.AD(deflt = "data/web_form", required = false)
>>>>>>> compatible
	public String dataRootDir();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.address}",
<<<<<<< HEAD
		name = "email-from-address", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String emailFromAddress();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.name}",
<<<<<<< HEAD
		name = "email-from-name", required = false
	)
	public String emailFromName();

	@Meta.AD(
		deflt = "false", name = "validation-script-enable", required = false
	)
=======
		required = false
	)
	public String emailFromName();

	@Meta.AD(deflt = "false", required = false)
>>>>>>> compatible
	public boolean validationScriptEnable();

}