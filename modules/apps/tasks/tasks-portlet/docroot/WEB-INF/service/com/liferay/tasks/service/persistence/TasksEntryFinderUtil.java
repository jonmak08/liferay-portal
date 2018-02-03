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

package com.liferay.tasks.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Ryan Park
 * @generated
 */
@ProviderType
public class TasksEntryFinderUtil {
	public static int countByG_U_P_A_S_T_N(long groupId, long userId,
		int priority, long assigneeUserId, int status, long[] assetTagIds,
		long[] notAssetTagIds) {
		return getFinder()
				   .countByG_U_P_A_S_T_N(groupId, userId, priority,
			assigneeUserId, status, assetTagIds, notAssetTagIds);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {
		return getFinder()
				   .findByG_U_P_A_S_T_N(groupId, userId, priority,
			assigneeUserId, status, assetTagIds, notAssetTagIds, start, end);
	}

	public static TasksEntryFinder getFinder() {
		if (_finder == null) {
<<<<<<< HEAD
			_finder = (TasksEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.tasks.service.ServletContextUtil.getServletContextName(),
=======
			_finder = (TasksEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.tasks.service.ClpSerializer.getServletContextName(),
>>>>>>> compatible
					TasksEntryFinder.class.getName());

			ReferenceRegistry.registerReference(TasksEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TasksEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TasksEntryFinderUtil.class,
			"_finder");
	}

	private static TasksEntryFinder _finder;
}