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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portlet.dynamicdatamapping.BaseDDMTestCase;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
@PrepareForTest({DDMStructureLocalServiceUtil.class})
public class DDMStructureImplTest extends BaseDDMTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpDDMStructureLocalServiceUtil();
	}

	@Test
	public final void testHasField() throws Exception {

		DDMStructure parentStructure = createStructure(
			"parent", "field01", "field02");

		Assert.assertTrue(parentStructure.hasField("field01"));
		Assert.assertTrue(parentStructure.hasField("field02"));

		DDMStructure childStructure = createStructure(
			"child", "field03", "field04");

		childStructure.setParentStructureId(parentStructure.getStructureId());

		Assert.assertTrue(childStructure.hasField("field01"));
		Assert.assertTrue(childStructure.hasField("field02"));
		Assert.assertTrue(childStructure.hasField("field03"));
		Assert.assertTrue(childStructure.hasField("field04"));
		Assert.assertFalse(childStructure.hasField("fieldNotFound"));
	}

}