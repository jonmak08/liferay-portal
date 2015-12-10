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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class CategorizationFilterTag extends IncludeTag {

	public void setAssetType(String assetType) {
		_assetType = assetType;
	}

	public void setMarkupView(String markupView) {
		_markupView = markupView;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	@Override
	protected void cleanUp() {
		_assetType = null;
		_markupView = null;
		_portletURL = null;
	}

	@Override
	protected String getPage() {
		if (Validator.isNotNull(_markupView)) {
			return "/html/taglib/ui/categorization_filter/" + _markupView +
				"/page.jsp";
		}

		return "/html/taglib/ui/categorization_filter/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:categorization-filter:assetType", _assetType);
		request.setAttribute(
			"liferay-ui:categorization-filter:portletURL", _portletURL);
	}

	private String _assetType;
	private String _markupView;
	private PortletURL _portletURL;

}