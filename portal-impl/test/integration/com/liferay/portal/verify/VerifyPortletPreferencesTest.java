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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portal.service.LayoutBranchLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.portal.verify.test.BaseVerifyProcessTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Andrew Betts
 */
public class VerifyPortletPreferencesTest extends BaseVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testCleanUpLayoutRevisionPortletPreferences() throws Exception {
		LayoutRevision layoutRevision = getLayoutRevision();

		// Add incorrect portletPreferences entry

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.addPortletPreferences(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(), 0,
				layoutRevision.getLayoutRevisionId(), "testPortlet",
				new PortletImpl(), StringPool.BLANK);

		PortletPreferences incorrectPortletPreferences =
			PortletPreferencesLocalServiceUtil.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId());

		Assert.assertNotNull(incorrectPortletPreferences);

		doVerify();

		incorrectPortletPreferences =
			PortletPreferencesLocalServiceUtil.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId());

		Assert.assertNull(incorrectPortletPreferences);
	}

	protected LayoutRevision getLayoutRevision() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(_group, false);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(TestPropsValues.getUserId());

		LayoutSetBranch layoutSetBranch =
			LayoutSetBranchLocalServiceUtil.addLayoutSetBranch(
				TestPropsValues.getUserId(), _group.getGroupId(), false, "test",
				"description", true, 0, serviceContext);

		LayoutBranch layoutBranch =
			LayoutBranchLocalServiceUtil.getMasterLayoutBranch(
				layoutSetBranch.getLayoutSetBranchId(), layout.getPlid());

		return LayoutRevisionLocalServiceUtil.getLayoutRevision(
			layoutSetBranch.getLayoutSetBranchId(),
			layoutBranch.getLayoutBranchId(), layout.getPlid());
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyPortletPreferences();
	}

	@DeleteAfterTestRun
	private Group _group;

}