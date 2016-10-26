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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.liferay.portal.model.Group;
import com.liferay.portal.util.comparator.GroupNameComparator;

/**
 * @author Eduardo Garcia
 */
@RunWith(PowerMockRunner.class)
public class GroupNameComparatorTest extends PowerMockito {

	@Before
	public void setUp() {
		setUpGroups();
	}

	@Test
	public void testCompareLocalized() {
		GroupNameComparator groupNameComparator = new GroupNameComparator(
			true, LocaleUtil.SPAIN);

		int value = groupNameComparator.compare(_group1, _group2);

		Assert.assertTrue(value < 0);
	}

	protected void setUpGroups() {
		_group1 = mock(Group.class);

		when(
			_group1.getName()
		).thenReturn(
			"Área"
		);

		_group2 = mock(Group.class);

		when(
			_group2.getName()
		).thenReturn(
			"Zona"
		);
	}

	@Mock
	private Group _group1;

	@Mock
	private Group _group2;

}