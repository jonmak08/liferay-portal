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

package com.liferay.wiki.web.internal.portlet.configuration.icon;

<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
<<<<<<< HEAD
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiPage;
=======
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.kernel.util.TrashUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.permission.WikiPagePermissionChecker;
>>>>>>> compatible
import com.liferay.wiki.web.internal.portlet.action.ActionUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN, "path=/wiki/view"
	},
	service = PortletConfigurationIcon.class
)
public class DeletePagePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		String key = "delete";

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (isTrashEnabled(themeDisplay.getScopeGroupId())) {
			key = "move-to-the-recycle-bin";
		}

		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), key);
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			WikiPage page = ActionUtil.getPage(portletRequest);

			PortletURL portletURL = _portal.getControlPanelPortletURL(
				portletRequest, WikiPortletKeys.WIKI_ADMIN,
				PortletRequest.ACTION_PHASE);

			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "/wiki/edit_page");
			portletURL.setParameter(Constants.CMD, Constants.DELETE);

			if (!page.isDraft() &&
				isTrashEnabled(themeDisplay.getScopeGroupId())) {

				portletURL.setParameter(Constants.CMD, Constants.MOVE_TO_TRASH);
			}
			else {
				portletURL.setParameter(
					"version", String.valueOf(page.getVersion()));
			}

			PortletURL redirectURL = _portal.getControlPanelPortletURL(
				portletRequest, WikiPortletKeys.WIKI_ADMIN,
				PortletRequest.ACTION_PHASE);

			redirectURL.setParameter(
				"mvcRenderCommandName", "/wiki/view_pages");
			redirectURL.setParameter("navigation", "all-pages");
			redirectURL.setParameter(
				"nodeId", String.valueOf(page.getNodeId()));

			portletURL.setParameter("redirect", redirectURL.toString());

			portletURL.setParameter("nodeId", String.valueOf(page.getNodeId()));
			portletURL.setParameter("title", page.getTitle());

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 100;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			WikiPage page = ActionUtil.getPage(portletRequest);

<<<<<<< HEAD
			return _wikiPageModelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), page, ActionKeys.DELETE);
=======
			if (!page.isDraft() &&
				WikiPagePermissionChecker.contains(
					themeDisplay.getPermissionChecker(), page.getNodeId(),
					HtmlUtil.unescape(page.getTitle()), ActionKeys.DELETE)) {

				return true;
			}
			else if (page.isDraft() &&
					 WikiPagePermissionChecker.contains(
						 themeDisplay.getPermissionChecker(), page,
						 ActionKeys.DELETE)) {

				return true;
			}
>>>>>>> compatible
		}
		catch (Exception e) {
		}

		return false;
	}

	protected boolean isTrashEnabled(long groupId) {
		try {
<<<<<<< HEAD
			if (_trashHelper.isTrashEnabled(groupId)) {
=======
			if (TrashUtil.isTrashEnabled(groupId)) {
>>>>>>> compatible
				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Reference
	private Portal _portal;

<<<<<<< HEAD
	@Reference
	private TrashHelper _trashHelper;

	@Reference(target = "(model.class.name=com.liferay.wiki.model.WikiPage)")
	private ModelResourcePermission<WikiPage> _wikiPageModelResourcePermission;

=======
>>>>>>> compatible
}