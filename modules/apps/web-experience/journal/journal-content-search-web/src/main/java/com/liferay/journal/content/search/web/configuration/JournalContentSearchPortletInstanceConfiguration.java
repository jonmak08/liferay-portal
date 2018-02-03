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

package com.liferay.journal.content.search.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.journal.content.search.web.configuration.JournalContentSearchPortletInstanceConfiguration",
	localization = "content/Language",
	name = "journal-content-search-portlet-instance-configuration-name"
)
public interface JournalContentSearchPortletInstanceConfiguration {

<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-highlighting", required = false)
	public boolean enableHighlighting();

	@Meta.AD(deflt = "true", name = "show-listed", required = false)
	public boolean showListed();

	@Meta.AD(name = "target-portlet-id", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
	public boolean showListed();

	@Meta.AD(required = false)
>>>>>>> compatible
	public String targetPortletId();

}