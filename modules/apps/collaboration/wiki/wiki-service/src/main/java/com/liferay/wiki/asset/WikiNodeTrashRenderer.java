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

package com.liferay.wiki.asset;

import com.liferay.portal.kernel.trash.BaseTrashRenderer;
import com.liferay.portal.kernel.util.HtmlUtil;
<<<<<<< HEAD
import com.liferay.trash.TrashHelper;
=======
import com.liferay.trash.kernel.util.TrashUtil;
>>>>>>> compatible
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
<<<<<<< HEAD
 * @deprecated As of 1.7.0, with no direct replacement
 */
@Deprecated
=======
 */
>>>>>>> compatible
public class WikiNodeTrashRenderer extends BaseTrashRenderer {

	public static final String TYPE = "wiki_node";

<<<<<<< HEAD
	/**
	 * @deprecated As of 1.6.0, replaced by {@link
	 *             #WikiNodeTrashRenderer(WikiNode, TrashHelper)}
	 */
	@Deprecated
	public WikiNodeTrashRenderer(WikiNode node) {
		this(node, null);
	}

	public WikiNodeTrashRenderer(WikiNode node, TrashHelper trashHelper) {
		_node = node;
		_trashHelper = trashHelper;
=======
	public WikiNodeTrashRenderer(WikiNode node) {
		_node = node;
>>>>>>> compatible
	}

	@Override
	public String getClassName() {
		return WikiNode.class.getName();
	}

	@Override
	public long getClassPK() {
		return _node.getPrimaryKey();
	}

	@Override
	public String getIconCssClass() {
		return "folder";
	}

	@Override
	public String getPortletId() {
		return WikiPortletKeys.WIKI;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return HtmlUtil.stripHtml(_node.getDescription());
	}

	@Override
	public String getTitle(Locale locale) {
		if (!_node.isInTrash()) {
			return _node.getName();
		}

<<<<<<< HEAD
		if (_trashHelper == null) {
			return _node.getName();
		}

		return _trashHelper.getOriginalTitle(_node.getName());
=======
		return TrashUtil.getOriginalTitle(_node.getName());
>>>>>>> compatible
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response,
			String template)
		throws Exception {

		return false;
	}

	private final WikiNode _node;
<<<<<<< HEAD
	private final TrashHelper _trashHelper;
=======
>>>>>>> compatible

}