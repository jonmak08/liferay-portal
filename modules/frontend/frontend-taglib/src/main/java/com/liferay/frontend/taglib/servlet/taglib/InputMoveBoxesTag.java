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
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class InputMoveBoxesTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setLeftBoxName(String leftBoxName) {
		_leftBoxName = leftBoxName;
	}

	public void setLeftList(List<KeyValuePair> leftList) {
		_leftList = leftList;
	}

	public void setLeftOnChange(String leftOnChange) {
		_leftOnChange = leftOnChange;
	}

	public void setLeftReorder(String leftReorder) {
		_leftReorder = leftReorder;
	}

	public void setLeftTitle(String leftTitle) {
		_leftTitle = leftTitle;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setRightBoxName(String rightBoxName) {
		_rightBoxName = rightBoxName;
	}

	public void setRightList(List<KeyValuePair> rightList) {
		_rightList = rightList;
	}

	public void setRightOnChange(String rightOnChange) {
		_rightOnChange = rightOnChange;
	}

	public void setRightReorder(String rightReorder) {
		_rightReorder = rightReorder;
	}

	public void setRightTitle(String rightTitle) {
		_rightTitle = rightTitle;
	}

	@Override
	protected void cleanUp() {
		_cssClass = null;
		_leftBoxName = null;
		_leftList = null;
		_leftOnChange = null;
		_leftReorder = null;
		_leftTitle = null;
		_rightBoxName = null;
		_rightList = null;
		_rightOnChange = null;
		_rightReorder = null;
		_rightTitle = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-frontend:input-move-boxes:cssClass", _cssClass);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:leftBoxName", _leftBoxName);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:leftList", _leftList);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:leftOnChange", _leftOnChange);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:leftReorder", _leftReorder);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:leftTitle", _leftTitle);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:rightBoxName", _rightBoxName);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:rightList", _rightList);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:rightOnChange", _rightOnChange);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:rightReorder", _rightReorder);
		request.setAttribute(
			"liferay-frontend:input-move-boxes:rightTitle", _rightTitle);
	}

	private static final String _PAGE = "/input_move_boxes/page.jsp";

	private String _cssClass;
	private String _leftBoxName;
	private List<KeyValuePair> _leftList;
	private String _leftOnChange;
	private String _leftReorder;
	private String _leftTitle;
	private String _rightBoxName;
	private List<KeyValuePair> _rightList;
	private String _rightOnChange;
	private String _rightReorder;
	private String _rightTitle;

}