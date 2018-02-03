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

package com.liferay.users.admin.web.servlet.taglib.ui;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
<<<<<<< HEAD

/**
 * @author Pei-Jung Lan
 * @deprecated As of 2.5.0, replaced by {@link
 * 		   com.liferay.users.admin.web.internal.servlet.taglib.ui.navigation.user.entry.UserAlertsAndAnnouncementsDeliveryScreenNavigationEntry}
 */
@Deprecated
=======
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = {"form.navigator.entry.order:Integer=40"},
	service = FormNavigatorEntry.class
)
>>>>>>> compatible
public class UserAnnouncementsFormNavigatorEntry
	extends BaseUserFormNavigatorEntry {

	@Override
	public String getCategoryKey() {
		return FormNavigatorConstants.CATEGORY_KEY_USER_MISCELLANEOUS;
	}

	@Override
	public String getKey() {
		return "announcements";
	}

	@Override
	public boolean isVisible(User user, User selUser) {
		if (selUser == null) {
			return false;
		}

		return true;
	}

	@Override
	protected String getJspPath() {
		return "/user/announcements.jsp";
	}

}