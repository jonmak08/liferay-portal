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

package com.liferay.wiki.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Iván Zaera
 */
@ExtendedObjectClassDefinition(
	category = "collaboration",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.wiki.configuration.WikiPortletInstanceConfiguration",
	localization = "content/Language",
	name = "wiki-portlet-instance-configuration-name"
)
public interface WikiPortletInstanceConfiguration {

	/**
	 * Set a DDM template ID that starts with the prefix "ddmTemplate_" (i.e.
	 * ddmTemplate_06cd7b42-e8a4-4b5e-8d5a-b4f4dbba5618) to use as the display
	 * style.
	 */
<<<<<<< HEAD
	@Meta.AD(deflt = "", name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
=======
	@Meta.AD(deflt = "", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", required = false)
>>>>>>> compatible
	public long displayStyleGroupId();

	/**
	 * Set this to <code>true</code> to enable ratings in Wiki comments.
	 */
<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-comment-ratings", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean enableCommentRatings();

	/**
	 * Set this to <code>true</code> to enable comments for Wiki pages.
	 */
<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-comments", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean enableComments();

	/**
	 * Set this to <code>true</code> to enable highlighting of search results in
	 * the Wiki portlet.
	 */
<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-highlighting", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean enableHighlighting();

	/**
	 * Set this to <code>true</code> to enable ratings for Wiki pages.
	 */
<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "enable-page-ratings", required = false)
	public boolean enablePageRatings();

	@Meta.AD(deflt = "true", name = "enable-related-assets", required = false)
	public boolean enableRelatedAssets();

	@Meta.AD(deflt = "", name = "hidden-nodes", required = false)
	public String[] hiddenNodes();

	@Meta.AD(deflt = "", name = "visible-nodes", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
	public boolean enablePageRatings();

	@Meta.AD(deflt = "true", required = false)
	public boolean enableRelatedAssets();

	@Meta.AD(deflt = "", required = false)
	public String[] hiddenNodes();

	@Meta.AD(deflt = "", required = false)
>>>>>>> compatible
	public String[] visibleNodes();

}