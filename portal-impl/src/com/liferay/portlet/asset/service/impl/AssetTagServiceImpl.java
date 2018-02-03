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

package com.liferay.portlet.asset.service.impl;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetTagDisplay;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
<<<<<<< HEAD
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Autocomplete;
=======
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
>>>>>>> compatible
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.service.base.AssetTagServiceBaseImpl;
<<<<<<< HEAD
=======
import com.liferay.portlet.asset.service.permission.AssetTagPermission;
>>>>>>> compatible
import com.liferay.portlet.asset.service.permission.AssetTagsPermission;
import com.liferay.portlet.asset.util.comparator.AssetTagNameComparator;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Provides the remote service for accessing, adding, checking, deleting,
 * merging, and updating asset tags. Its methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Bruno Farache
 * @author Juan Fernández
 */
public class AssetTagServiceImpl extends AssetTagServiceBaseImpl {

	@Override
	public AssetTag addTag(
			long groupId, String name, ServiceContext serviceContext)
		throws PortalException {

		AssetTagsPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_TAG);

		return assetTagLocalService.addTag(
			getUserId(), groupId, name, serviceContext);
	}

	@Override
	public void deleteTag(long tagId) throws PortalException {
		deleteTags(new long[] {tagId});
	}

	@Override
	public void deleteTags(long[] tagIds) throws PortalException {
		for (long tagId : tagIds) {
			assetTagLocalService.deleteTag(tagId);
		}
	}

	@Override
	public List<AssetTag> getGroupsTags(long[] groupIds) {
		Set<AssetTag> groupsTags = new TreeSet<>(new AssetTagNameComparator());

		for (long groupId : groupIds) {
			List<AssetTag> groupTags = getGroupTags(groupId);

			groupsTags.addAll(groupTags);
		}

		return new ArrayList<>(groupsTags);
	}

	@Override
	public List<AssetTag> getGroupTags(long groupId) {
<<<<<<< HEAD
		return sanitize(assetTagPersistence.findByGroupId(groupId));
=======
		return assetTagPersistence.findByGroupId(groupId);
>>>>>>> compatible
	}

	@Override
	public List<AssetTag> getGroupTags(
		long groupId, int start, int end, OrderByComparator<AssetTag> obc) {

<<<<<<< HEAD
		return sanitize(
			assetTagPersistence.findByGroupId(groupId, start, end, obc));
=======
		return assetTagPersistence.findByGroupId(groupId, start, end, obc);
>>>>>>> compatible
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		return assetTagPersistence.countByGroupId(groupId);
	}

	@Override
	public AssetTagDisplay getGroupTagsDisplay(
		long groupId, String name, int start, int end) {

		List<AssetTag> tags = null;
		int total = 0;

		if (Validator.isNotNull(name)) {
			name = (CustomSQLUtil.keywords(name))[0];

			tags = getTags(groupId, name, start, end);
			total = getTagsCount(groupId, name);
		}
		else {
			tags = getGroupTags(groupId, start, end, null);
			total = getGroupTagsCount(groupId);
		}

		return new AssetTagDisplay(tags, total, start, end);
	}

	@Override
	public AssetTag getTag(long tagId) throws PortalException {
<<<<<<< HEAD
		return sanitize(assetTagLocalService.getTag(tagId));
=======
		return assetTagLocalService.getTag(tagId);
>>>>>>> compatible
	}

	@Override
	public List<AssetTag> getTags(long groupId, long classNameId, String name) {
<<<<<<< HEAD
		return sanitize(
			assetTagFinder.findByG_C_N(
				groupId, classNameId, name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null));
=======
		return assetTagFinder.findByG_C_N(
			groupId, classNameId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
>>>>>>> compatible
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

<<<<<<< HEAD
		return sanitize(
			assetTagFinder.findByG_C_N(
				groupId, classNameId, name, start, end, obc));
=======
		return assetTagFinder.findByG_C_N(
			groupId, classNameId, name, start, end, obc);
>>>>>>> compatible
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end) {

		return getTags(new long[] {groupId}, name, start, end);
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		return getTags(new long[] {groupId}, name, start, end, obc);
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end) {

		return getTags(
			groupIds, name, start, end, new AssetTagNameComparator());
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		if (Validator.isNull(name)) {
<<<<<<< HEAD
			return sanitize(
				assetTagPersistence.findByGroupId(groupIds, start, end, obc));
		}

		return sanitize(
			assetTagPersistence.findByG_LikeN(groupIds, name, start, end, obc));
=======
			return assetTagPersistence.findByGroupId(groupIds, start, end, obc);
		}

		return assetTagPersistence.findByG_LikeN(
			groupIds, name, start, end, obc);
>>>>>>> compatible
	}

	@Override
	public List<AssetTag> getTags(String className, long classPK) {
<<<<<<< HEAD
		return sanitize(assetTagLocalService.getTags(className, classPK));
=======
		return assetTagLocalService.getTags(className, classPK);
>>>>>>> compatible
	}

	@Override
	public int getTagsCount(long groupId, String name) {
		if (Validator.isNull(name)) {
			return assetTagPersistence.countByGroupId(groupId);
		}

		return assetTagPersistence.countByG_LikeN(groupId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name) {

		return assetTagFinder.countByG_C_N(groupId, classNameId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(long groupId, String name) {
		return assetTagFinder.countByG_N(groupId, name);
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
<<<<<<< HEAD
=======
		AssetTagPermission.check(
			getPermissionChecker(), toTagId, ActionKeys.UPDATE);

>>>>>>> compatible
		assetTagLocalService.mergeTags(fromTagId, toTagId);
	}

	@Override
	public void mergeTags(long[] fromTagIds, long toTagId)
		throws PortalException {

		for (long fromTagId : fromTagIds) {
			mergeTags(fromTagId, toTagId);
		}
	}

	@Override
	public JSONArray search(long groupId, String name, int start, int end) {
		return search(new long[] {groupId}, name, start, end);
	}

	@Override
	public JSONArray search(long[] groupIds, String name, int start, int end) {
		List<AssetTag> tags = getTags(groupIds, name, start, end);

		return Autocomplete.arrayToJSONArray(tags, "name", "name");
	}

	@Override
	public AssetTag updateTag(
			long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		return assetTagLocalService.updateTag(
			getUserId(), tagId, name, serviceContext);
	}

<<<<<<< HEAD
	protected AssetTag sanitize(AssetTag tag) {
		if (tag == null) {
			return null;
		}

		try {
			PermissionChecker permissionChecker = getPermissionChecker();

			if (permissionChecker.isCompanyAdmin(tag.getCompanyId()) ||
				(tag.getUserId() == permissionChecker.getUserId())) {

				return tag;
			}
		}
		catch (PrincipalException pe) {
			_log.error(pe);
		}

		tag.setUserId(0);
		tag.setUserName(StringPool.BLANK);
		tag.setUserUuid(StringPool.BLANK);

		return tag;
	}

	protected List<AssetTag> sanitize(List<AssetTag> tags) {
		for (AssetTag tag : tags) {
			sanitize(tag);
		}

		return tags;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetTagServiceImpl.class);

=======
>>>>>>> compatible
}