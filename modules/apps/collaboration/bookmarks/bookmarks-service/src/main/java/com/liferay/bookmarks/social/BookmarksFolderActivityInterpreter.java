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

package com.liferay.bookmarks.social;

import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.model.BookmarksFolder;
<<<<<<< HEAD
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
=======
import com.liferay.bookmarks.service.permission.BookmarksFolderPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
>>>>>>> compatible
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivityInterpreter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zsolt Berentey
 */
@Component(
<<<<<<< HEAD
	property = {
		"javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS,
		"model.class.name=com.liferay.bookmarks.model.BookmarksFolder"
	},
=======
	property = {"javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS},
>>>>>>> compatible
	service = SocialActivityInterpreter.class
)
public class BookmarksFolderActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getPath(
		SocialActivity activity, ServiceContext serviceContext) {

		return "/bookmarks/find_folder?folderId=" + activity.getClassPK();
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == SocialActivityConstants.TYPE_MOVE_TO_TRASH) {
			if (Validator.isNull(groupName)) {
				return "activity-bookmarks-folder-move-to-trash";
			}
			else {
				return "activity-bookmarks-folder-move-to-trash-in";
			}
		}
		else if (activityType ==
					SocialActivityConstants.TYPE_RESTORE_FROM_TRASH) {

			if (Validator.isNull(groupName)) {
				return "activity-bookmarks-folder-restore-from-trash";
			}
			else {
				return "activity-bookmarks-folder-restore-from-trash-in";
			}
		}

		return null;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

<<<<<<< HEAD
		return ModelResourcePermissionHelper.contains(
			_bookmarksFolderModelResourcePermission, permissionChecker,
			activity.getGroupId(), activity.getClassPK(), actionId);
=======
		return BookmarksFolderPermissionChecker.contains(
			permissionChecker, activity.getGroupId(), activity.getClassPK(),
			actionId);
>>>>>>> compatible
	}

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.bookmarks.web)",
		unbind = "-"
	)
	protected void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			resourceBundleLoader,
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader());
	}

	private static final String[] _CLASS_NAMES =
		{BookmarksFolder.class.getName()};

<<<<<<< HEAD
	@Reference(
		target = "(model.class.name=com.liferay.bookmarks.model.BookmarksFolder)"
	)
	private ModelResourcePermission<BookmarksFolder>
		_bookmarksFolderModelResourcePermission;

=======
>>>>>>> compatible
	private ResourceBundleLoader _resourceBundleLoader;

}