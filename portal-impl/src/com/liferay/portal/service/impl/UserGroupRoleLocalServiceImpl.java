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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchUserGroupRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.base.UserGroupRoleLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.UserGroupRolePK;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class UserGroupRoleLocalServiceImpl
	extends UserGroupRoleLocalServiceBaseImpl {

	@Override
	public List<UserGroupRole> addUserGroupRoles(
			long userId, long groupId, long[] roleIds)
		throws SystemException {

		List<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

		for (long roleId : roleIds) {
			UserGroupRole userGroupRole = addUserGroupRole(
				userId, groupId, roleId);

			userGroupRoles.add(userGroupRole);
		}

		Group group = groupPersistence.fetchByPrimaryKey(groupId);

		if (group.isRegularSite()) {
			groupPersistence.addUser(groupId, userId);
		}

		return userGroupRoles;
	}

	@Override
	public List<UserGroupRole> addUserGroupRoles(
			long[] userIds, long groupId, long roleId)
		throws SystemException {

		List<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

		for (long userId : userIds) {
			UserGroupRole userGroupRole = addUserGroupRole(
				userId, groupId, roleId);

			userGroupRoles.add(userGroupRole);
		}

		Group group = groupPersistence.fetchByPrimaryKey(groupId);

		if (group.isRegularSite()) {
			groupPersistence.addUsers(groupId, userIds);
		}

		return userGroupRoles;
	}

	@Override
	public void deleteUserGroupRoles(long userId, long groupId, long[] roleIds)
		throws SystemException {

		for (long roleId : roleIds) {
			UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
				userId, groupId, roleId);

			try {
				userGroupRolePersistence.remove(userGroupRolePK);
			}
			catch (NoSuchUserGroupRoleException nsugre) {
			}
		}
	}

	@Override
	public void deleteUserGroupRoles(long userId, long[] groupIds)
		throws SystemException {

		for (long groupId : groupIds) {
			userGroupRolePersistence.removeByU_G(userId, groupId);
		}
	}

	@Override
	public void deleteUserGroupRoles(long[] userIds, long groupId)
		throws SystemException {

		for (long userId : userIds) {
			userGroupRolePersistence.removeByU_G(userId, groupId);
		}
	}

	@Override
	public void deleteUserGroupRoles(long[] userIds, long groupId, int roleType)
		throws SystemException {

		List<Role> roles = rolePersistence.findByT_S(
			roleType, StringPool.BLANK);

		for (long userId : userIds) {
			for (Role role : roles) {
				UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
					userId, groupId, role.getRoleId());

				try {
					userGroupRolePersistence.remove(userGroupRolePK);
				}
				catch (NoSuchUserGroupRoleException nsugre) {
				}
			}
		}
	}

	@Override
	public void deleteUserGroupRoles(long[] userIds, long groupId, long roleId)
		throws SystemException {

		for (long userId : userIds) {
			UserGroupRolePK pk = new UserGroupRolePK(userId, groupId, roleId);

			try {
				userGroupRolePersistence.remove(pk);
			}
			catch (NoSuchUserGroupRoleException nsugre) {
			}
		}
	}

	@Override
	public void deleteUserGroupRolesByGroupId(long groupId)
		throws SystemException {

		userGroupRolePersistence.removeByGroupId(groupId);
	}

	@Override
	public void deleteUserGroupRolesByRoleId(long roleId)
		throws SystemException {

		userGroupRolePersistence.removeByRoleId(roleId);
	}

	@Override
	public void deleteUserGroupRolesByUserId(long userId)
		throws SystemException {

		userGroupRolePersistence.removeByUserId(userId);
	}

	@Override
	public List<UserGroupRole> getUserGroupRoles(long userId)
		throws SystemException {

		return userGroupRolePersistence.findByUserId(userId);
	}

	@Override
	public List<UserGroupRole> getUserGroupRoles(long userId, long groupId)
		throws SystemException {

		return userGroupRolePersistence.findByU_G(userId, groupId);
	}

	@Override
	public List<UserGroupRole> getUserGroupRoles(
			long userId, long groupId, int start, int end)
		throws SystemException {

		return userGroupRolePersistence.findByU_G(userId, groupId, start, end);
	}

	@Override
	public List<UserGroupRole> getUserGroupRolesByGroup(long groupId)
		throws SystemException {

		return userGroupRolePersistence.findByGroupId(groupId);
	}

	@Override
	public List<UserGroupRole> getUserGroupRolesByGroupAndRole(
			long groupId, long roleId)
		throws SystemException {

		return userGroupRolePersistence.findByG_R(groupId, roleId);
	}

	@Override
	public List<UserGroupRole> getUserGroupRolesByUserUserGroupAndGroup(
			long userId, long groupId)
		throws SystemException {

		return userGroupRoleFinder.findByUserUserGroupGroupRole(
			userId, groupId);
	}

	@Override
	public int getUserGroupRolesCount(long userId, long groupId)
		throws SystemException {

		return userGroupRolePersistence.countByU_G(userId, groupId);
	}

	@Override
	public boolean hasUserGroupRole(long userId, long groupId, long roleId)
		throws SystemException {

		return hasUserGroupRole(userId, groupId, roleId, false);
	}

	@Override
	public boolean hasUserGroupRole(
			long userId, long groupId, long roleId, boolean inherit)
		throws SystemException {

		UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
			userId, groupId, roleId);

		UserGroupRole userGroupRole =
			userGroupRolePersistence.fetchByPrimaryKey(userGroupRolePK);

		if (userGroupRole != null) {
			return true;
		}

		if (inherit) {
			if (roleFinder.countByU_G_R(userId, groupId, roleId) > 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasUserGroupRole(long userId, long groupId, String roleName)
		throws PortalException, SystemException {

		return hasUserGroupRole(userId, groupId, roleName, false);
	}

	@Override
	public boolean hasUserGroupRole(
			long userId, long groupId, String roleName, boolean inherit)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		long companyId = user.getCompanyId();

		Role role = rolePersistence.fetchByC_N(companyId, roleName);

		if (role == null) {
			return false;
		}

		return hasUserGroupRole(userId, groupId, role.getRoleId(), inherit);
	}

	protected UserGroupRole addUserGroupRole(
			long userId, long groupId, long roleId)
		throws SystemException {

		UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
			userId, groupId, roleId);

		UserGroupRole userGroupRole =
			userGroupRolePersistence.fetchByPrimaryKey(userGroupRolePK);

		if (userGroupRole == null) {
			userGroupRole = userGroupRolePersistence.create(userGroupRolePK);

			userGroupRolePersistence.update(userGroupRole);
		}

		return userGroupRole;
	}

}