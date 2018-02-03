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

package com.liferay.portal.comment.display.context.util;

import com.liferay.portal.kernel.util.GetterUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.Validator;
=======
>>>>>>> compatible

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class DiscussionTaglibHelper {

	public DiscussionTaglibHelper(HttpServletRequest request) {
		_request = request;
	}

	public String getClassName() {
		if (_className == null) {
<<<<<<< HEAD
			_className = _getAttribute("className");
=======
			HttpServletRequest request = getRequest();

			_className = (String)request.getAttribute(
				"liferay-ui:discussion:className");
>>>>>>> compatible
		}

		return _className;
	}

	public long getClassPK() {
		if (_classPK == null) {
<<<<<<< HEAD
			_classPK = GetterUtil.getLong(_getAttribute("classPK"));
=======
			HttpServletRequest request = getRequest();

			_classPK = GetterUtil.getLong(
				(String)request.getAttribute("liferay-ui:discussion:classPK"));
>>>>>>> compatible
		}

		return _classPK;
	}

	public String getFormAction() {
		if (_formAction == null) {
<<<<<<< HEAD
			_formAction = _getAttribute("formAction");
=======
			HttpServletRequest request = getRequest();

			_formAction = (String)request.getAttribute(
				"liferay-ui:discussion:formAction");
>>>>>>> compatible
		}

		return _formAction;
	}

	public String getFormName() {
		if (_formName == null) {
<<<<<<< HEAD
			_formName = _getAttribute("formName");
=======
			HttpServletRequest request = getRequest();

			_formName = (String)request.getAttribute(
				"liferay-ui:discussion:formName");
>>>>>>> compatible
		}

		return _formName;
	}

	public String getPaginationURL() {
		if (_paginationURL == null) {
<<<<<<< HEAD
			_paginationURL = _getAttribute("paginationURL");
=======
			HttpServletRequest request = getRequest();

			_paginationURL = (String)request.getAttribute(
				"liferay-ui:discussion:paginationURL");
>>>>>>> compatible
		}

		return _paginationURL;
	}

	public String getRedirect() {
		if (_redirect == null) {
<<<<<<< HEAD
			_redirect = _getAttribute("redirect");
=======
			HttpServletRequest request = getRequest();

			_redirect = (String)request.getAttribute(
				"liferay-ui:discussion:redirect");
>>>>>>> compatible
		}

		return _redirect;
	}

	public long getUserId() {
		if (_userId == null) {
<<<<<<< HEAD
			_userId = GetterUtil.getLong(_getAttribute("userId"));
=======
			HttpServletRequest request = getRequest();

			_userId = GetterUtil.getLong(
				(String)request.getAttribute("liferay-ui:discussion:userId"));
>>>>>>> compatible
		}

		return _userId;
	}

	public boolean isAssetEntryVisible() {
		if (_assetEntryVisible == null) {
<<<<<<< HEAD
			_assetEntryVisible = GetterUtil.getBoolean(
				_getAttribute("assetEntryVisible"));
=======
			HttpServletRequest request = getRequest();

			_assetEntryVisible = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:assetEntryVisible"));
>>>>>>> compatible
		}

		return _assetEntryVisible;
	}

	public boolean isHideControls() {
		if (_hideControls == null) {
<<<<<<< HEAD
			_hideControls = GetterUtil.getBoolean(
				_getAttribute("hideControls"));
=======
			HttpServletRequest request = getRequest();

			_hideControls = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:hideControls"));
>>>>>>> compatible
		}

		return _hideControls;
	}

	public boolean isRatingsEnabled() {
		if (_ratingsEnabled == null) {
<<<<<<< HEAD
			_ratingsEnabled = GetterUtil.getBoolean(
				_getAttribute("ratingsEnabled"));
=======
			HttpServletRequest request = getRequest();

			_ratingsEnabled = GetterUtil.getBoolean(
				(String)request.getAttribute(
					"liferay-ui:discussion:ratingsEnabled"));
>>>>>>> compatible
		}

		return _ratingsEnabled;
	}

	protected HttpServletRequest getRequest() {
		return _request;
	}

<<<<<<< HEAD
	private String _getAttribute(String name) {
		HttpServletRequest request = getRequest();

		String value = (String)request.getAttribute(_LEGACY_PREFIX + name);

		if (Validator.isNotNull(value)) {
			return value;
		}

		return (String)request.getAttribute(_PREFIX + name);
	}

	private static final String _LEGACY_PREFIX = "liferay-ui:discussion:";

	private static final String _PREFIX = "liferay-comment:discussion:";

=======
>>>>>>> compatible
	private Boolean _assetEntryVisible;
	private String _className;
	private Long _classPK;
	private String _formAction;
	private String _formName;
	private Boolean _hideControls;
	private String _paginationURL;
	private Boolean _ratingsEnabled;
	private String _redirect;
	private final HttpServletRequest _request;
	private Long _userId;

}