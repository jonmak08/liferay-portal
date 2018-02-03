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

package com.liferay.blogs.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Sergio González
 */
@ExtendedObjectClassDefinition(
	category = "collaboration",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.blogs.configuration.BlogsGroupServiceConfiguration",
	localization = "content/Language",
	name = "blogs-group-service-configuration-name"
)
public interface BlogsGroupServiceConfiguration {

<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-rss", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean enableRss();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/search.container.page.default.delta}",
<<<<<<< HEAD
		name = "rss-delta", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String rssDelta();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/rss.feed.display.style.default}",
<<<<<<< HEAD
		name = "rss-display-style", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String rssDisplayStyle();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/rss.feed.type.default}",
<<<<<<< HEAD
		name = "rss-feed-type", required = false
	)
	public String rssFeedType();

	@Meta.AD(deflt = "300", name = "small-image-width", required = false)
=======
		required = false
	)
	public String rssFeedType();

	@Meta.AD(deflt = "300", required = false)
>>>>>>> compatible
	public int smallImageWidth();

}