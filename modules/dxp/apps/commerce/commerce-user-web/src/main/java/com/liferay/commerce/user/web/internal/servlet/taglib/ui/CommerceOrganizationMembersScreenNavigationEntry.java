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

package com.liferay.commerce.user.web.internal.servlet.taglib.ui;

import com.liferay.commerce.user.service.CommerceUserService;
import com.liferay.commerce.user.web.internal.display.context.CommerceOrganizationMembersDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Marco Leo
 */
@Component(
	property = {
		"screen.navigation.entry.order:Integer=20"
	},
	service = { ScreenNavigationEntry.class}
)
public class CommerceOrganizationMembersScreenNavigationEntry
	implements ScreenNavigationEntry<Organization> {

	@Override
	public String getCategoryKey() {
		return CommerceUserScreenNavigationConstants.CATEGORY_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return CommerceUserScreenNavigationConstants.ENTRY_KEY_ORGANIZATION_MEMBERS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "members");
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceUserScreenNavigationConstants.SCREEN_NAVIGATION_KEY;
	}

	@Override
	public boolean isVisible(User user, Organization organization) {

		if(organization == null){
			return false;
		}

		try{
			PermissionChecker permissionChecker =
					PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker == null) {
				throw  new PrincipalException("PermissionChecker not initialized");
			}

			return OrganizationPermissionUtil.contains(
					permissionChecker, organization, ActionKeys.VIEW_MEMBERS);
		}
		catch(PortalException pe){
			_log.error(pe, pe);
		}

		return false;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		CommerceOrganizationMembersDisplayContext commerceOrganizationMembersDisplayContext =
			new CommerceOrganizationMembersDisplayContext(
				httpServletRequest, _organizationService, _userLocalService);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceOrganizationMembersDisplayContext);

		_jspRenderer.renderJSP(
			 httpServletRequest, httpServletResponse,
			"/view_organization_members.jsp");
	}

	@Reference
	private Portal _portal;

	@Reference
	private CommerceUserService _commerceUserService;

	@Reference
	private OrganizationService _organizationService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private UserLocalService _userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
			CommerceOrganizationMembersScreenNavigationEntry.class);



}