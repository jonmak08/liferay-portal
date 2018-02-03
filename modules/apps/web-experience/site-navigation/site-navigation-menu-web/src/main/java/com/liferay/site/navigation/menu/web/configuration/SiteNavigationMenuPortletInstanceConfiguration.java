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

package com.liferay.site.navigation.menu.web.configuration;

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
	id = "com.liferay.site.navigation.menu.web.configuration.SiteNavigationMenuPortletInstanceConfiguration",
	localization = "content/Language",
	name = "site-navigation-menu-portlet-instance-configuration-name"
)
public interface SiteNavigationMenuPortletInstanceConfiguration {

<<<<<<< HEAD
	@Meta.AD(name = "bullet-style", required = false)
	public String bulletStyle();

	@Meta.AD(deflt = "0", name = "display-depth", required = false)
	public int displayDepth();

	@Meta.AD(name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(deflt = "current", name = "included-layouts", required = false)
	public String includedLayouts();

	@Meta.AD(deflt = "preview", name = "preview", required = false)
	public boolean preview();

	@Meta.AD(deflt = "0", name = "root-layout-level", required = false)
	public int rootLayoutLevel();

	@Meta.AD(deflt = "absolute", name = "root-layout-type", required = false)
	public String rootLayoutType();

	@Meta.AD(name = "root-layout-uuid", required = false)
=======
	@Meta.AD(required = false)
	public String bulletStyle();

	@Meta.AD(deflt = "0", required = false)
	public int displayDepth();

	@Meta.AD(required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", required = false)
	public long displayStyleGroupId();

	@Meta.AD(deflt = "current", required = false)
	public String includedLayouts();

	@Meta.AD(deflt = "preview", required = false)
	public boolean preview();

	@Meta.AD(deflt = "0", required = false)
	public int rootLayoutLevel();

	@Meta.AD(deflt = "absolute", required = false)
	public String rootLayoutType();

	@Meta.AD(required = false)
>>>>>>> compatible
	public String rootLayoutUuid();

}