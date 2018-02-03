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

package com.liferay.poshi.runner.elements;

import org.dom4j.Element;

/**
 * @author Kenji Heigel
 */
public class DescriptionElement extends PoshiElement {

	public DescriptionElement(Element element) {
		super("description", element);
	}

	public DescriptionElement(String readableSyntax) {
		super("description", readableSyntax);
	}

	@Override
	public String getBlockName() {
		return "description";
	}

	@Override
	public void parseReadableSyntax(String readableSyntax) {
		String message = getQuotedContent(readableSyntax);

		addAttribute("message", message);
	}

}