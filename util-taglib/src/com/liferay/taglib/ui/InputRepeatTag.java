/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bruno Farache
 * @author Julio Camarero
 */
public class InputRepeatTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setEvent(CalEvent event) {
		_event = event;
	}

	@Override
	protected void cleanUp() {
		_cssClass = StringPool.BLANK;
		_event = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:input-repeat:cssClass", _cssClass);
		request.setAttribute("liferay-ui:input-repeat:event", _event);
	}

	private static final String _PAGE = "/html/taglib/ui/input_repeat/page.jsp";

	private String _cssClass = StringPool.BLANK;
	private CalEvent _event;

}