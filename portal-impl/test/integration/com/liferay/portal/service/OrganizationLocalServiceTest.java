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

package com.liferay.portal.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.OrganizationTestUtil;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class OrganizationLocalServiceTest {

	@Test
	public void testAddOrganization() throws Exception {
		User user = TestPropsValues.getUser();

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				user.getUserId(),
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
				"Organization", false);

		List<Organization> organizations = user.getOrganizations(true);

		Assert.assertTrue(organizations.contains(organization));

		Assert.assertFalse(
			OrganizationLocalServiceUtil.hasUserOrganization(
				user.getUserId(), organization.getOrganizationId()));
	}

	@Test
	public void testAddOrganizationWithoutSiteToParentOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization B", false);

		Assert.assertEquals(
			organizationA.getOrganizationId(),
			organizationB.getParentOrganizationId());

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupB.getParentGroupId());
	}

	@Test
	public void testAddOrganizationWithoutSiteToParentOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
				organizationA.getOrganizationId(), "Organization B", false);

		Assert.assertEquals(
			organizationA.getOrganizationId(),
			organizationB.getParentOrganizationId());

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupB.getParentGroupId());
	}

	@Test
	public void testAddOrganizationWithSiteToParentOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationB = OrganizationTestUtil.addOrganization(
				organizationA.getOrganizationId(), "Organization B", true);

		Assert.assertEquals(
			organizationA.getOrganizationId(),
			organizationB.getParentOrganizationId());

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupB.getParentGroupId());
	}

	@Test
	public void testAddOrganizationWithSiteToParentOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
				organizationA.getOrganizationId(), "Organization B", true);

		Assert.assertEquals(
			organizationA.getOrganizationId(),
			organizationB.getParentOrganizationId());

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			organizationA.getGroupId(), groupB.getParentGroupId());
	}

	@Test
	public void testAddSiteToOrganizationWithChildOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization B", false);

		_organizations.add(organizationB);
		_organizations.add(organizationA);

		organizationA = OrganizationTestUtil.addSite(organizationA);

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupB.getParentGroupId());
	}

	@Test
	public void testAddSiteToOrganizationWithChildOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization B", true);

		_organizations.add(organizationB);
		_organizations.add(organizationA);

		organizationA = OrganizationTestUtil.addSite(organizationA);

		Group groupA = organizationA.getGroup();
		Group groupB = organizationB.getGroup();

		Assert.assertEquals(groupA.getGroupId(), groupB.getParentGroupId());
	}

	@Test
	public void testAddSiteToOrganizationWithParentOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization B", false);

		_organizations.add(organizationB);
		_organizations.add(organizationA);

		organizationB = OrganizationTestUtil.addSite(organizationB);

		Group groupB = organizationB.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupB.getParentGroupId());
	}

	@Test
	public void testAddSiteToOrganizationWithParentOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization B", false);

		_organizations.add(organizationB);
		_organizations.add(organizationA);

		organizationB = OrganizationTestUtil.addSite(organizationB);

		Group groupA = organizationA.getGroup();
		Group groupB = organizationB.getGroup();

		Assert.assertEquals(groupA.getGroupId(), groupB.getParentGroupId());
	}

	@Test
	public void testGetNoAssetOrganizations() throws Exception {
		OrganizationLocalServiceUtil.addOrganization(
			TestPropsValues.getUserId(),
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			RandomTestUtil.randomString(),
			OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, new ServiceContext());

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				TestPropsValues.getUserId(),
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, "Test2",
				OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
				false, new ServiceContext());

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			Organization.class.getName(), organization.getOrganizationId());

		Assert.assertNotNull(assetEntry);

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getNoAssetOrganizations();

		Assert.assertEquals(1, organizations.size());
		Assert.assertEquals(organization, organizations.get(0));
	}

	@Test
	public void testMoveOrganizationWithoutSiteToParentOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationAA = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization AA", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization B", false);

		organizationAA = OrganizationLocalServiceUtil.updateOrganization(
			organizationAA.getCompanyId(), organizationAA.getOrganizationId(),
			organizationB.getOrganizationId(), organizationAA.getName(),
			organizationAA.getType(), organizationAA.getRegionId(),
			organizationAA.getCountryId(), organizationAA.getStatusId(),
			organizationAA.getComments(), true, null);

		Assert.assertEquals(
			organizationB.getOrganizationId(),
			organizationAA.getParentOrganizationId());

		Group groupAA = organizationAA.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupAA.getParentGroupId());
	}

	@Test
	public void testMoveOrganizationWithoutSiteToParentOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", false);

		Organization organizationAA = OrganizationTestUtil.addOrganization(
				organizationA.getOrganizationId(), "Organization AA", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization B", true);

		organizationAA = OrganizationLocalServiceUtil.updateOrganization(
			organizationAA.getCompanyId(), organizationAA.getOrganizationId(),
			organizationB.getOrganizationId(), organizationAA.getName(),
			organizationAA.getType(), organizationAA.getRegionId(),
			organizationAA.getCountryId(), organizationAA.getStatusId(),
			organizationAA.getComments(), true, null);

		Assert.assertEquals(
			organizationB.getOrganizationId(),
			organizationAA.getParentOrganizationId());

		Group groupAA = organizationAA.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupAA.getParentGroupId());
	}

	@Test
	public void testMoveOrganizationWithSiteToParentOrganizationWithoutSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", true);

		Organization organizationAA = OrganizationTestUtil.addOrganization(
				organizationA.getOrganizationId(), "Organization AA", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization B", false);

		organizationAA = OrganizationLocalServiceUtil.updateOrganization(
			organizationAA.getCompanyId(), organizationAA.getOrganizationId(),
			organizationB.getOrganizationId(), organizationAA.getName(),
			organizationAA.getType(), organizationAA.getRegionId(),
			organizationAA.getCountryId(), organizationAA.getStatusId(),
			organizationAA.getComments(), true, null);

		Assert.assertEquals(
			organizationB.getOrganizationId(),
			organizationAA.getParentOrganizationId());

		Group groupAA = organizationAA.getGroup();

		Assert.assertEquals(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, groupAA.getParentGroupId());
	}

	@Test
	public void testMoveOrganizationWithSiteToParentOrganizationWithSite()
		throws Exception {

		Organization organizationA = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization A", true);

		Organization organizationAA = OrganizationTestUtil.addOrganization(
			organizationA.getOrganizationId(), "Organization AA", true);

		Organization organizationB = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			"Organization B", true);

		organizationAA = OrganizationLocalServiceUtil.updateOrganization(
			organizationAA.getCompanyId(), organizationAA.getOrganizationId(),
			organizationB.getOrganizationId(), organizationAA.getName(),
			organizationAA.getType(), organizationAA.getRegionId(),
			organizationAA.getCountryId(), organizationAA.getStatusId(),
			organizationAA.getComments(), true, null);

		Assert.assertEquals(
			organizationB.getOrganizationId(),
			organizationAA.getParentOrganizationId());

		Group groupAA = organizationAA.getGroup();

		Assert.assertEquals(
			organizationB.getGroupId(), groupAA.getParentGroupId());
	}

}