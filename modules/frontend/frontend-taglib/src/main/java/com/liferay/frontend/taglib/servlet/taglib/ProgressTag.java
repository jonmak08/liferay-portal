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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author     Iliyan Peychev
 * @author     Sergio González
 */
public class ProgressTag extends IncludeTag {

	public void setHeight(int height) {
		_height = height;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setMessage(String message) {
		_message = message;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSessionKey(String sessionKey) {
		_sessionKey = sessionKey;
	}

	public void setUpdatePeriod(Integer updatePeriod) {
		_updatePeriod = updatePeriod;
	}

	@Override
	protected void cleanUp() {
		_height = 25;
		_id = null;
		_message = null;
		_sessionKey = null;
		_updatePeriod = 1000;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-frontend:progress:id", _id);
		request.setAttribute("liferay-frontend:progress:height", _height);
		request.setAttribute("liferay-frontend:progress:message", _message);
		request.setAttribute("liferay-frontend:progress:sessionKey", _sessionKey);
		request.setAttribute("liferay-frontend:progress:updatePeriod", _updatePeriod);
	}

	private static final String _PAGE = "/progress/page.jsp";

	private Integer _height;
	private String _id;
	private String _message;
	private String _sessionKey;
	private Integer _updatePeriod;

}