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

package com.liferay.portal.kernel.util;

import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Balogh
 */

@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class StringUtilHighlightTest {

	@Test
	public void testHighlight() throws Exception {
		Assert.assertEquals(
			"<span class=\"highlight\">Hello</span> World <span " +
				"class=\"highlight\">Liferay</span>",
			StringUtil.highlight(
				"Hello World Liferay", new String[] {"Hello","Liferay"}));
	}

}