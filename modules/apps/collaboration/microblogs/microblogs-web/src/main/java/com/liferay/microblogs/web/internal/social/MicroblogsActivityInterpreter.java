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

package com.liferay.microblogs.web.internal.social;

import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalService;
<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
=======
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
>>>>>>> compatible
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityInterpreter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + MicroblogsPortletKeys.MICROBLOGS},
	service = SocialActivityInterpreter.class
)
public class MicroblogsActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
		SocialActivity activity, ServiceContext serviceContext) {

		return getUserName(activity.getUserId(), serviceContext);
	}

	@Override
	protected String getLink(
		SocialActivity activity, ServiceContext serviceContext) {

		return StringPool.BLANK;
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		MicroblogsEntry microblogsEntry =
			_microblogsEntryLocalService.getMicroblogsEntry(
				activity.getClassPK());

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		if (activity.getReceiverUserId() > 0) {
			if (microblogsEntry.getType() ==
					MicroblogsEntryConstants.TYPE_REPLY) {

				sb.append("@");
				sb.append(receiverUserName);
				sb.append(": ");
			}
			else if (microblogsEntry.getType() ==
						MicroblogsEntryConstants.TYPE_REPOST) {

				sb.append(serviceContext.translate("reposted-from"));
				sb.append(" ");
				sb.append(receiverUserName);
				sb.append(": ");
			}
		}

		sb.append(HtmlUtil.escape(microblogsEntry.getContent()));

		return sb.toString();
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

<<<<<<< HEAD
		return _microblogsEntryModelResourcePermission.contains(
			permissionChecker, activity.getClassPK(), ActionKeys.VIEW);
=======
		MicroblogsEntry microblogsEntry =
			_microblogsEntryLocalService.getMicroblogsEntry(
				activity.getClassPK());

		return MicroblogsEntryPermission.contains(
			permissionChecker, microblogsEntry, ActionKeys.VIEW);
>>>>>>> compatible
	}

	@Reference(unbind = "-")
	protected void setMicroblogsEntryLocalService(
		MicroblogsEntryLocalService microblogsEntryLocalService) {

		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.microblogs.web)",
		unbind = "-"
	)
	protected void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			resourceBundleLoader,
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader());
	}

	private static final String[] _CLASS_NAMES =
		{MicroblogsEntry.class.getName()};

	private MicroblogsEntryLocalService _microblogsEntryLocalService;
<<<<<<< HEAD

	@Reference(
		target = "(model.class.name=com.liferay.microblogs.model.MicroblogsEntry)"
	)
	private ModelResourcePermission<MicroblogsEntry>
		_microblogsEntryModelResourcePermission;

=======
>>>>>>> compatible
	private ResourceBundleLoader _resourceBundleLoader;

}