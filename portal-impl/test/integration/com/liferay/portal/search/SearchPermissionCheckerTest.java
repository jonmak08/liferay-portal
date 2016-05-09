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

package com.liferay.portal.search;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.search.lucene.BooleanClauseImpl;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.OrganizationTestUtil;
import com.liferay.portal.util.RoleTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Preston Crary
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class SearchPermissionCheckerTest {

	@Before
	public void setUp() throws Exception {
		_searchPermissionChecker =
			SearchEngineUtil.getSearchPermissionChecker();

		_group = GroupTestUtil.addGroup();

		_organization = OrganizationTestUtil.addOrganization();
	}

	@After
	public void tearDown() throws Exception {
		if (_user != null) {
			UserLocalServiceUtil.deleteUser(_user);
		}

		if (_role != null) {
			RoleLocalServiceUtil.deleteRole(_role);
		}

		OrganizationLocalServiceUtil.deleteOrganization(_organization);

		GroupLocalServiceUtil.deleteGroup(_group);
	}

	@Test
	public void testAdministratorRolePermissionFilter() throws Exception {
		BooleanQuery booleanQuery = (BooleanQuery)getBooleanQuery(
			null, TestPropsValues.getUserId());

		Assert.assertFalse(booleanQuery.hasClauses());
	}

	@Test
	public void testCompanyPermissionFilter() throws Exception {
		_user = UserTestUtil.addUser();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		_role = RoleTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_REGULAR);

		UserLocalServiceUtil.addRoleUser(_role.getRoleId(), _user.getUserId());

		addViewPermission(
			ResourceConstants.SCOPE_COMPANY, TestPropsValues.getCompanyId(),
			_role.getRoleId());

		BooleanQuery booleanQuery = (BooleanQuery)getBooleanQuery(null);

		Assert.assertFalse(booleanQuery.hasClauses());
	}

	@Test
	public void testGroupIdsPermissionFilter() throws Exception {
		_user = UserTestUtil.addOrganizationAdminUser(_organization);

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(),
			RoleConstants.ORGANIZATION_ADMINISTRATOR);

		assertFieldValue(
			new long[] {_group.getGroupId()}, Field.GROUP_ROLE_ID,
			_group.getGroupId() + StringPool.DASH + role.getRoleId(), false);
	}

	@Test
	public void testGroupPermissionFilter() throws Exception {
		_user = UserTestUtil.addGroupAdminUser(_group);

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);

		addViewPermission(
			ResourceConstants.SCOPE_GROUP, _group.getGroupId(),
			role.getRoleId());

		assertFieldValue(
			null, Field.GROUP_ID, String.valueOf(_group.getGroupId()));
		assertFieldValue(null, Field.ROLE_ID, String.valueOf(role.getRoleId()));
	}

	@Test
	public void testGroupTemplatePermissionFilter() throws Exception {
		_user = UserTestUtil.addUser();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		_role = RoleTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_REGULAR);

		UserLocalServiceUtil.addRoleUser(_role.getRoleId(), _user.getUserId());

		addViewPermission(
			ResourceConstants.SCOPE_GROUP_TEMPLATE,
			GroupConstants.DEFAULT_PARENT_GROUP_ID, _role.getRoleId());

		BooleanQuery booleanQuery = (BooleanQuery)getBooleanQuery(null);

		Assert.assertFalse(booleanQuery.hasClauses());
	}

	@Test
	public void testGuestPermissionFilter() throws Exception {
		_user = UserTestUtil.addUser();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), RoleConstants.GUEST);

		addViewPermission(
			ResourceConstants.SCOPE_GROUP, _group.getGroupId(),
			role.getRoleId());

		assertFieldValue(
			new long[] {_group.getGroupId()}, Field.GROUP_ID,
			String.valueOf(_group.getGroupId()));
		assertFieldValue(
			new long[] {_group.getGroupId()}, Field.ROLE_ID,
			String.valueOf(role.getRoleId()));
	}

	@Test
	public void testOrganizationRolePermissionFilter() throws Exception {
		_user = UserTestUtil.addOrganizationAdminUser(_organization);

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(),
			RoleConstants.ORGANIZATION_ADMINISTRATOR);

		addViewPermission(
			ResourceConstants.SCOPE_GROUP, _organization.getGroupId(),
			role.getRoleId());

		assertFieldValue(
			null, Field.GROUP_ID, String.valueOf(_organization.getGroupId()));
		assertFieldValue(null, Field.ROLE_ID, String.valueOf(role.getRoleId()));
	}

	@Test
	public void testUserGroupRolePermissionFilter() throws Exception {
		_user = UserTestUtil.addUser();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		_role = RoleTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_SITE);

		RoleLocalServiceUtil.addGroupRole(
			_group.getGroupId(), _role.getRoleId());

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			_user.getUserId(), _group.getGroupId(),
			new long[] {_role.getRoleId()});

		assertFieldValue(
			null, Field.GROUP_ROLE_ID,
			_group.getGroupId() + StringPool.DASH + _role.getRoleId());
	}

	protected void addViewPermission(int scope, long primKey, long roleId)
		throws Exception {

		ResourcePermissionLocalServiceUtil.addResourcePermission(
			TestPropsValues.getCompanyId(), getClassName(), scope,
			String.valueOf(primKey), roleId, ActionKeys.VIEW);
	}

	protected void assertFieldValue(long[] groupIds, String field, String value)
		throws Exception {

		assertFieldValue(groupIds, field, value, true);
	}

	protected void assertFieldValue(
			long[] groupIds, String field, String value, boolean expected)
		throws Exception {

		BooleanQuery booleanQuery = (BooleanQuery)getBooleanQuery(groupIds);

		TestTermHelper testTermHelper = new TestTermHelper(field, value);

		Assert.assertEquals(
			expected, testTermHelper.isTermPresent(booleanQuery));
	}

	protected Query getBooleanQuery(long[] groupIds) throws Exception {
		return getBooleanQuery(groupIds, _user.getUserId());
	}

	protected Query getBooleanQuery(long[] groupIds, long userId)
		throws Exception {

		SearchContext searchContext = new SearchContext();

		return _searchPermissionChecker.getPermissionQuery(
			TestPropsValues.getCompanyId(), groupIds, userId, getClassName(),
			BooleanQueryFactoryUtil.create(searchContext), searchContext);
	}

	protected String getClassName() {
		return DLFileEntry.class.getName();
	}

	private Group _group;

	private Organization _organization;

	private Role _role;

	private SearchPermissionChecker _searchPermissionChecker;

	private User _user;

	private static class TestTermHelper {

		public TestTermHelper(String field, String value) {
			_field = field;
			_value = value;
		}

		public boolean isTermPresent(BooleanQuery booleanQuery) {
			for (BooleanClause booleanClause : booleanQuery.clauses()) {
				org.apache.lucene.search.BooleanClause clause =
					((BooleanClauseImpl)booleanClause).getBooleanClause();

				org.apache.lucene.search.Query query = clause.getQuery();

				Set<org.apache.lucene.index.Term> extractedTerms =
					new HashSet <org.apache.lucene.index.Term>();

				query.extractTerms(extractedTerms);

				for (org.apache.lucene.index.Term term : extractedTerms) {
					if (_field.equals(term.field()) &&
						_value.equals(term.text())) {

						return true;
					}
				}
			}

			return false;
		}

		private final String _field;
		private final String _value;

	}

}