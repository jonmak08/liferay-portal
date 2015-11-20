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
import com.liferay.portal.kernel.servlet.DirectRequestDispatcherFactoryUtil;
import com.liferay.portal.kernel.util.DeterminateKeyGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.SessionClicks;
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
public class ToggleTag extends IncludeTag {

	public static void doTag(
			String id, String showImage, String hideImage, String showMessage,
			String hideMessage, boolean defaultShowContent, String stateVar,
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		doTag(
			_PAGE, id, showImage, hideImage, showMessage, hideMessage,
			defaultShowContent, stateVar, servletContext, request, response);
	}

	public static void doTag(
			String page, String id, String showImage, String hideImage,
			String showMessage, String hideMessage, boolean defaultShowContent,
			String stateVar, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (Validator.isNull(showImage) && Validator.isNull(showMessage)) {
			showImage =
				themeDisplay.getPathThemeImages() + "/arrows/01_down.png";
		}

		if (Validator.isNull(hideImage) && Validator.isNull(hideImage)) {
			hideImage =
				themeDisplay.getPathThemeImages() + "/arrows/01_right.png";
		}

		String defaultStateValue =
			defaultShowContent ? StringPool.BLANK : "none";
		String defaultImage = defaultShowContent ? hideImage : showImage;
		String defaultMessage = defaultShowContent ? hideMessage : showMessage;

		String clickValue = SessionClicks.get(request, id, null);

		if (defaultShowContent) {
			if ((clickValue != null) && clickValue.equals("none")) {
				defaultStateValue = "none";
				defaultImage = showImage;
				defaultMessage = showMessage;
			}
			else {
				defaultStateValue = "";
				defaultImage = hideImage;
				defaultMessage = hideMessage;
			}
		}
		else {
			if ((clickValue == null) || clickValue.equals("none")) {
				defaultStateValue = "none";
				defaultImage = showImage;
				defaultMessage = showMessage;
			}
			else {
				defaultStateValue = "";
				defaultImage = hideImage;
				defaultMessage = hideMessage;
			}
		}

		if (stateVar == null) {
			stateVar = DeterminateKeyGenerator.generate(
				ToggleTag.class.getName());
		}

		request.setAttribute("liferay-frontend:toggle:id", id);
		request.setAttribute("liferay-frontend:toggle:showImage", showImage);
		request.setAttribute("liferay-frontend:toggle:hideImage", hideImage);
		request.setAttribute(
			"liferay-frontend:toggle:showMessage", showMessage);
		request.setAttribute(
			"liferay-frontend:toggle:hideMessage", hideMessage);
		request.setAttribute("liferay-frontend:toggle:stateVar", stateVar);
		request.setAttribute(
			"liferay-frontend:toggle:defaultStateValue", defaultStateValue);
		request.setAttribute(
			"liferay-frontend:toggle:defaultImage", defaultImage);
		request.setAttribute(
			"liferay-frontend:toggle:defaultMessage", defaultMessage);

		RequestDispatcher requestDispatcher =
			DirectRequestDispatcherFactoryUtil.getRequestDispatcher(
				servletContext, page);

		requestDispatcher.include(request, response);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(
				getPage(), _id, _showImage, _hideImage, _showMessage,
				_hideMessage, _defaultShowContent, _stateVar, servletContext,
				request, new PipingServletResponse(pageContext));

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setDefaultShowContent(boolean defaultShowContent) {
		_defaultShowContent = defaultShowContent;
	}

	public void setHideImage(String hideImage) {
		_hideImage = hideImage;
	}

	public void setHideMessage(String hideMessage) {
		_hideMessage = hideMessage;
	}

	public void setId(String id) {
		_id = id;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowImage(String showImage) {
		_showImage = showImage;
	}

	public void setShowMessage(String showMessage) {
		_showMessage = showMessage;
	}

	public void setStateVar(String stateVar) {
		_stateVar = stateVar;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/toggle/page.jsp";

	private boolean _defaultShowContent = true;
	private String _hideImage;
	private String _hideMessage;
	private String _id;
	private String _showImage;
	private String _showMessage;
	private String _stateVar;

}