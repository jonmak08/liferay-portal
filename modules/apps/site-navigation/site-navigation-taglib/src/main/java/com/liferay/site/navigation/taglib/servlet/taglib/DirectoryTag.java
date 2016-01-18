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

package com.liferay.site.navigation.taglib.servlet.taglib;

import com.liferay.site.navigation.taglib.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Sergio González
 */
public class DirectoryTag extends IncludeTag {

	public static final String SITES_CHILDREN = "children";

	public static final String SITES_PARENT_LEVEL = "parent-level";

	public static final String SITES_SIBLINGS = "siblings";

	public static final String SITES_TOP_LEVEL = "top-level";

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSites(String sites) {
		_sites = sites;
	}

	@Override
	protected void cleanUp() {
		_displayStyle = "descriptive";
		_sites = SITES_TOP_LEVEL;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-site-navigation:directory:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-site-navigation:directory:sites", String.valueOf(_sites));
	}

	private static final String _PAGE = "/directory/page.jsp";

	private String _displayStyle = "descriptive";
	private String _sites = SITES_TOP_LEVEL;

}