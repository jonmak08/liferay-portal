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

import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.AdvancedPermissionChecker;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerBag;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.security.permission.ResourceBlockIdsBag;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockPermissionLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Allen Chiang
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Amos Fong
 */
public class SearchPermissionCheckerImpl implements SearchPermissionChecker {

	@Override
	public void addPermissionFields(long companyId, Document document) {
		try {
			long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

			String className = document.get(Field.ENTRY_CLASS_NAME);

			boolean relatedEntry = GetterUtil.getBoolean(
				document.get(Field.RELATED_ENTRY));

			if (relatedEntry) {
				long classNameId = GetterUtil.getLong(
					document.get(Field.CLASS_NAME_ID));

				className = PortalUtil.getClassName(classNameId);
			}

			if (Validator.isNull(className)) {
				return;
			}

			String classPK = document.get(Field.ROOT_ENTRY_CLASS_PK);

			if (Validator.isNull(classPK)) {
				classPK = document.get(Field.ENTRY_CLASS_PK);
			}

			if (relatedEntry) {
				classPK = document.get(Field.CLASS_PK);
			}

			if (Validator.isNull(classPK)) {
				return;
			}

			Indexer indexer = IndexerRegistryUtil.getIndexer(className);

			if (!indexer.isPermissionAware()) {
				return;
			}

			doAddPermissionFields_6(
				companyId, groupId, className, classPK, document);
		}
		catch (NoSuchResourceException nsre) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public Query getPermissionQuery(
		long companyId, long[] groupIds, long userId, String className,
		Query query, SearchContext searchContext) {

		try {
			query = doGetPermissionQuery(
				companyId, groupIds, userId, className, query, searchContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return query;
	}

	@Override
	public void updatePermissionFields(
		String resourceName, String resourceClassPK) {

		try {
			doUpdatePermissionFields(resourceName, resourceClassPK);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doAddPermissionFields_6(
			long companyId, long groupId, String className, String classPK,
			Document doc)
		throws Exception {

		Group group = null;

		if (groupId > 0) {
			group = GroupLocalServiceUtil.getGroup(groupId);
		}

		List<Role> roles = ListUtil.copy(
			ResourceActionsUtil.getRoles(companyId, group, className, null));

		if (groupId > 0) {
			List<Role> teamRoles = RoleLocalServiceUtil.getTeamRoles(groupId);

			roles.addAll(teamRoles);
		}

		long[] roleIdsArray = new long[roles.size()];

		for (int i = 0; i < roleIdsArray.length; i++) {
			Role role = roles.get(i);

			roleIdsArray[i] = role.getRoleId();
		}

		boolean[] hasResourcePermissions = null;

		if (ResourceBlockLocalServiceUtil.isSupported(className)) {
			ResourceBlockIdsBag resourceBlockIdsBag =
				ResourceBlockLocalServiceUtil.getResourceBlockIdsBag(
					companyId, groupId, className, roleIdsArray);

			long actionId = ResourceBlockLocalServiceUtil.getActionId(
				className, ActionKeys.VIEW);

			List<Long> resourceBlockIds =
				resourceBlockIdsBag.getResourceBlockIds(actionId);

			hasResourcePermissions = new boolean[roleIdsArray.length];

			for (long resourceBlockId : resourceBlockIds) {
				for (int i = 0; i < roleIdsArray.length; i++) {
					int count =
						ResourceBlockPermissionLocalServiceUtil.
							getResourceBlockPermissionsCount(
								resourceBlockId, roleIdsArray[i]);

					hasResourcePermissions[i] = (count > 0);
				}
			}
		}
		else {
			hasResourcePermissions =
				ResourcePermissionLocalServiceUtil.hasResourcePermissions(
					companyId, className, ResourceConstants.SCOPE_INDIVIDUAL,
					classPK, roleIdsArray, ActionKeys.VIEW);
		}

		List<Long> roleIds = new ArrayList<Long>();
		List<String> groupRoleIds = new ArrayList<String>();

		for (int i = 0; i < hasResourcePermissions.length; i++) {
			if (!hasResourcePermissions[i]) {
				continue;
			}

			Role role = roles.get(i);

			if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
				(role.getType() == RoleConstants.TYPE_SITE)) {

				groupRoleIds.add(groupId + StringPool.DASH + role.getRoleId());
			}
			else {
				roleIds.add(role.getRoleId());
			}
		}

		doc.addKeyword(
			Field.ROLE_ID, roleIds.toArray(new Long[roleIds.size()]));
		doc.addKeyword(
			Field.GROUP_ROLE_ID,
			groupRoleIds.toArray(new String[groupRoleIds.size()]));
	}

	protected Query doGetPermissionQuery(
			long companyId, long[] groupIds, long userId, String className,
			Query query, SearchContext searchContext)
		throws Exception {

		Indexer indexer = IndexerRegistryUtil.getIndexer(className);

		if (!indexer.isPermissionAware()) {
			return query;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		AdvancedPermissionChecker advancedPermissionChecker = null;

		if ((permissionChecker != null) &&
			(permissionChecker instanceof AdvancedPermissionChecker)) {

			advancedPermissionChecker =
				(AdvancedPermissionChecker)permissionChecker;
		}

		if (advancedPermissionChecker == null) {
			return query;
		}

		PermissionCheckerBag permissionCheckerBag = getPermissionCheckerBag(
			advancedPermissionChecker, userId);

		if (permissionCheckerBag == null) {
			return query;
		}

		if (permissionChecker.isCompanyAdmin(companyId)) {
			return query;
		}

		Set<Role> roles = new HashSet<Role>();
		Map<Long, List<Role>> groupIdsToRoles = new HashMap<Long, List<Role>>();

		roles.addAll(permissionCheckerBag.getRoles());

		if (advancedPermissionChecker.isSignedIn()) {
			roles.add(
				RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST));
		}
		else {
			Group guestGroup = GroupLocalServiceUtil.getGroup(
				companyId, GroupConstants.GUEST);

			roles.addAll(
				RoleLocalServiceUtil.getUserRelatedRoles(
					userId, Collections.singletonList(guestGroup)));
		}

		Role organizationUserRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ORGANIZATION_USER);
		Role siteMemberRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.SITE_MEMBER);

		for (Group group : permissionCheckerBag.getGroups()) {
			PermissionCheckerBag userBag = advancedPermissionChecker.getUserBag(
				userId, group.getGroupId());

			List<Role> groupRoles = userBag.getRoles();

			roles.addAll(groupRoles);

			Iterator<Role> iterator = groupRoles.iterator();

			while (iterator.hasNext()) {
				Role groupRole = iterator.next();

				if ((groupRole.getType() != RoleConstants.TYPE_ORGANIZATION) &&
					(groupRole.getType() != RoleConstants.TYPE_SITE)) {

					iterator.remove();
				}
			}

			if (group.isOrganization() &&
				!groupRoles.contains(organizationUserRole)) {

				groupRoles.add(organizationUserRole);
			}

			if (group.isSite() && !groupRoles.contains(siteMemberRole)) {
				groupRoles.add(siteMemberRole);
			}

			groupIdsToRoles.put(group.getGroupId(), groupRoles);
		}

		return doGetPermissionQuery_6(
			companyId, groupIds, userId, className, query, searchContext,
			advancedPermissionChecker, roles, groupIdsToRoles);
	}

	protected Query doGetPermissionQuery_6(
			long companyId, long[] groupIds, long userId, String className,
			Query query, SearchContext searchContext,
			AdvancedPermissionChecker advancedPermissionChecker,
			Set<Role> roles,
			Map<Long, List<Role>> groupIdsToRoles)
		throws Exception {

		BooleanQuery permissionQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		if (userId > 0) {
			permissionQuery.addTerm(Field.USER_ID, userId);
		}

		BooleanQuery groupsQuery = BooleanQueryFactoryUtil.create(
			searchContext);
		BooleanQuery rolesQuery = BooleanQueryFactoryUtil.create(searchContext);

		List<Long> roleIds = new ArrayList<Long>(roles.size());
		List<Long> regularRoleIds = new ArrayList<Long>();

		for (Role role : roles) {
			roleIds.add(role.getRoleId());

			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				regularRoleIds.add(role.getRoleId());
			}

			rolesQuery.addTerm(Field.ROLE_ID, String.valueOf(role.getRoleId()));
		}

		long[] roleIdsArray = ArrayUtil.toLongArray(roleIds);

		if (ResourcePermissionLocalServiceUtil.hasResourcePermission(
				companyId, className, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(companyId), roleIdsArray, ActionKeys.VIEW)) {

			return query;
		}

		if (ResourcePermissionLocalServiceUtil.hasResourcePermission(
				companyId, className, ResourceConstants.SCOPE_GROUP_TEMPLATE,
				String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
				ArrayUtil.toLongArray(regularRoleIds), ActionKeys.VIEW)) {

			return query;
		}

		for (Map.Entry<Long, List<Role>> entry : groupIdsToRoles.entrySet()) {
			long groupId = entry.getKey();
			List<Role> groupRoles = entry.getValue();

			if (advancedPermissionChecker.isGroupAdmin(groupId) ||
				ResourcePermissionLocalServiceUtil.hasResourcePermission(
					companyId, className, ResourceConstants.SCOPE_GROUP,
					String.valueOf(groupId), roleIdsArray, ActionKeys.VIEW) ||
				ResourcePermissionLocalServiceUtil.hasResourcePermission(
					companyId, className,
					ResourceConstants.SCOPE_GROUP_TEMPLATE,
					String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
					ListUtil.toLongArray(groupRoles, Role.ROLE_ID_ACCESSOR),
					ActionKeys.VIEW)) {

				groupsQuery.addTerm(Field.GROUP_ID, String.valueOf(groupId));
			}

			for (Role groupRole : groupRoles) {
				rolesQuery.addTerm(
					Field.GROUP_ROLE_ID,
					groupId + StringPool.DASH + groupRole.getRoleId());
			}
		}

		if (ArrayUtil.isNotEmpty(groupIds)) {
			for (long groupId : groupIds) {
				if (ResourcePermissionLocalServiceUtil.hasResourcePermission(
						companyId, className, ResourceConstants.SCOPE_GROUP,
						String.valueOf(groupId), roleIdsArray,
						ActionKeys.VIEW)) {

					groupsQuery.addTerm(
						Field.GROUP_ID, String.valueOf(groupId));
				}
			}
		}

		if (groupsQuery.hasClauses()) {
			permissionQuery.add(groupsQuery, BooleanClauseOccur.SHOULD);
		}

		if (rolesQuery.hasClauses()) {
			permissionQuery.add(rolesQuery, BooleanClauseOccur.SHOULD);
		}

		BooleanQuery fullQuery = BooleanQueryFactoryUtil.create(searchContext);

		fullQuery.add(query, BooleanClauseOccur.MUST);
		fullQuery.add(permissionQuery, BooleanClauseOccur.MUST);

		return fullQuery;
	}

	protected void doUpdatePermissionFields(
			String resourceName, String resourceClassPK)
		throws Exception {

		Indexer indexer = IndexerRegistryUtil.getIndexer(resourceName);

		if (indexer != null) {
			indexer.reindex(resourceName, GetterUtil.getLong(resourceClassPK));
		}
	}

	protected PermissionCheckerBag getPermissionCheckerBag(
			AdvancedPermissionChecker advancedPermissionChecker, long userId)
		throws Exception {

		if (!advancedPermissionChecker.isSignedIn()) {
			return advancedPermissionChecker.getGuestUserBag();
		}
		else {
			return advancedPermissionChecker.getUserBag(userId, 0);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SearchPermissionCheckerImpl.class);

}