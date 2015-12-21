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
import com.liferay.taglib.servlet.PipingServletResponse;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PreviewTag extends IncludeTag {

	public static void doTag(
			String portletName, String queryString, boolean showBorders,
			String width, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		doTag(
			_PAGE, portletName, queryString, showBorders, width, servletContext,
			request, response);
	}

	public static void doTag(
			String page, String portletName, String queryString,
			boolean showBorders, String width, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		request.setAttribute(
			"liferay-site-navigation:preview:portletName", portletName);
		request.setAttribute(
			"liferay-site-navigation:preview:queryString", queryString);
		request.setAttribute(
			"liferay-site-navigation:preview:showBorders", String.valueOf(showBorders));
		request.setAttribute("liferay-site-navigation:preview:width", width);

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(page);

		requestDispatcher.include(request, response);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(
				getPage(), _portletName, _queryString, _showBorders, _width,
				servletContext, request,
				new PipingServletResponse(pageContext));

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPortletName(String portletName) {
		_portletName = portletName;
	}

	public void setQueryString(String queryString) {
		_queryString = queryString;
	}

	public void setShowBorders(boolean showBorders) {
		_showBorders = showBorders;
	}

	public void setWidth(String width) {
		_width = width;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/preview/page.jsp";

	private String _portletName;
	private String _queryString;
	private boolean _showBorders;
	private String _width;

}