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

package com.liferay.roles.admin.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
<<<<<<< HEAD
import com.liferay.exportimport.test.util.lar.BaseStagedModelDataHandlerTestCase;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
<<<<<<< HEAD
import com.liferay.portal.kernel.test.util.RoleTestUtil;
=======
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.lar.test.BaseStagedModelDataHandlerTestCase;
>>>>>>> compatible
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author David Mendez Gonzalez
 */
@RunWith(Arquillian.class)
<<<<<<< HEAD
=======
@Sync
>>>>>>> compatible
public class RoleStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
<<<<<<< HEAD
		new LiferayIntegrationTestRule();
=======
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);
>>>>>>> compatible

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		return RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return RoleLocalServiceUtil.fetchRoleByUuidAndCompanyId(
				uuid, group.getCompanyId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return Role.class;
	}

	@Override
	protected void initExport() throws Exception {
		super.initExport();

		Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
			portletDataContext.getCompanyId());

		rootElement.addAttribute(
			"company-group-id", String.valueOf(companyGroup.getGroupId()));

		Group userPersonalSiteGroup =
			GroupLocalServiceUtil.getUserPersonalSiteGroup(
				portletDataContext.getCompanyId());

		rootElement.addAttribute(
			"user-personal-site-group-id",
			String.valueOf(userPersonalSiteGroup.getGroupId()));
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

<<<<<<< HEAD
		// super.validateImportedStagedModel(stagedModel, importedStagedModel);
=======
		super.validateImportedStagedModel(stagedModel, importedStagedModel);
>>>>>>> compatible

		Role role = (Role)stagedModel;
		Role importedRole = (Role)importedStagedModel;

		Assert.assertEquals(role.getName(), importedRole.getName());
		Assert.assertEquals(
			role.getDescription(), importedRole.getDescription());
		Assert.assertEquals(role.getType(), importedRole.getType());
		Assert.assertEquals(role.getSubtype(), importedRole.getSubtype());
	}

}