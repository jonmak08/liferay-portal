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

package com.liferay.portal.util;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jonathan McCann
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class PortalImplTest {

	@Test
	public void testIsValidResourceId() {
		Assert.assertTrue(PortalUtil.isValidResourceId("/view.jsp"));
		Assert.assertFalse(
			PortalUtil.isValidResourceId("/META-INF/MANIFEST.MF"));
		Assert.assertFalse(
			PortalUtil.isValidResourceId("/META-INF\\MANIFEST.MF"));
		Assert.assertFalse(
			PortalUtil.isValidResourceId("\\META-INF/MANIFEST.MF"));
		Assert.assertFalse(
			PortalUtil.isValidResourceId("\\META-INF\\MANIFEST.MF"));
		Assert.assertFalse(PortalUtil.isValidResourceId("/WEB-INF/web.xml"));
		Assert.assertFalse(PortalUtil.isValidResourceId("/WEB-INF\\web.xml"));
		Assert.assertFalse(PortalUtil.isValidResourceId("\\WEB-INF/web.xml"));
		Assert.assertFalse(PortalUtil.isValidResourceId("\\WEB-INF\\web.xml"));
	}

}