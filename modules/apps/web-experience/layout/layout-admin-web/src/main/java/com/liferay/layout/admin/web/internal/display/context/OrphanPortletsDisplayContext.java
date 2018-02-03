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

package com.liferay.layout.admin.web.internal.display.context;

<<<<<<< HEAD
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
=======
import com.liferay.portal.kernel.exception.PortalException;
>>>>>>> compatible
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
<<<<<<< HEAD
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.PortletTitleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
import javax.portlet.PortletURL;
=======
import javax.portlet.PortletRequest;
>>>>>>> compatible

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class OrphanPortletsDisplayContext {

<<<<<<< HEAD
	public OrphanPortletsDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
=======
	public OrphanPortletsDisplayContext(PortletRequest portletRequest)
		throws PortalException {

		_portletRequest = portletRequest;
>>>>>>> compatible
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(
<<<<<<< HEAD
			_liferayPortletRequest, "displayStyle", "list");
=======
			_portletRequest, "displayStyle", "list");
>>>>>>> compatible

		return _displayStyle;
	}

<<<<<<< HEAD
	public List<NavigationItem> getNavigationItems() {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			_liferayPortletRequest);

		List<NavigationItem> navigationItems = new ArrayList<>();

		NavigationItem entriesNavigationItem = new NavigationItem();

		entriesNavigationItem.setActive(true);

		PortletURL mainURL = _liferayPortletResponse.createRenderURL();

		entriesNavigationItem.setHref(mainURL.toString());

		entriesNavigationItem.setLabel(
			LanguageUtil.get(request, "orphan-portlets"));

		navigationItems.add(entriesNavigationItem);

		return navigationItems;
	}

=======
>>>>>>> compatible
	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
<<<<<<< HEAD
			_liferayPortletRequest, "orderByCol", "modified-date");
=======
			_portletRequest, "orderByCol", "modified-date");
>>>>>>> compatible

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
<<<<<<< HEAD
			_liferayPortletRequest, "orderByType", "asc");
=======
			_portletRequest, "orderByType", "asc");
>>>>>>> compatible

		return _orderByType;
	}

	public List<Portlet> getOrphanPortlets() {
		Layout selLayout = getSelLayout();

<<<<<<< HEAD
		return getOrphanPortlets(selLayout);
	}

	public List<Portlet> getOrphanPortlets(Layout layout) {
		if (!layout.isSupportsEmbeddedPortlets()) {
			return Collections.emptyList();
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		LayoutTypePortlet selLayoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();
=======
		if (!selLayout.isSupportsEmbeddedPortlets()) {
			return Collections.emptyList();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		LayoutTypePortlet selLayoutTypePortlet =
			(LayoutTypePortlet)selLayout.getLayoutType();
>>>>>>> compatible

		List<Portlet> explicitlyAddedPortlets =
			selLayoutTypePortlet.getExplicitlyAddedPortlets();

		List<String> explicitlyAddedPortletIds = new ArrayList<>();

		for (Portlet explicitlyAddedPortlet : explicitlyAddedPortlets) {
			explicitlyAddedPortletIds.add(
				explicitlyAddedPortlet.getPortletId());
		}

		List<Portlet> orphanPortlets = new ArrayList<>();

		List<PortletPreferences> portletPreferences =
			PortletPreferencesLocalServiceUtil.getPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, getSelPlid());

		for (PortletPreferences portletPreference : portletPreferences) {
			String portletId = portletPreference.getPortletId();

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletId);

			if (portlet.isSystem()) {
				continue;
			}

			if (explicitlyAddedPortletIds.contains(portletId)) {
				continue;
			}

			orphanPortlets.add(portlet);
		}

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
<<<<<<< HEAD
			_liferayPortletRequest);
=======
			_portletRequest);
>>>>>>> compatible

		PortletTitleComparator portletTitleComparator =
			new PortletTitleComparator(
				request.getServletContext(), themeDisplay.getLocale());

		orphanPortlets = ListUtil.sort(orphanPortlets, portletTitleComparator);

		return orphanPortlets;
	}

	public Layout getSelLayout() {
		if (_selLayout != null) {
			return _selLayout;
		}

		if (getSelPlid() != LayoutConstants.DEFAULT_PLID) {
			_selLayout = LayoutLocalServiceUtil.fetchLayout(getSelPlid());
		}

		return _selLayout;
	}

	public Long getSelPlid() {
		if (_selPlid != null) {
			return _selPlid;
		}

		_selPlid = ParamUtil.getLong(
<<<<<<< HEAD
			_liferayPortletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);
=======
			_portletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);
>>>>>>> compatible

		return _selPlid;
	}

	public String getStatus(Portlet portlet) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
<<<<<<< HEAD
			_liferayPortletRequest);
=======
			_portletRequest);
>>>>>>> compatible

		if (!portlet.isActive()) {
			return LanguageUtil.get(request, "inactive");
		}
		else if (!portlet.isReady()) {
			return LanguageUtil.format(request, "is-not-ready", "portlet");
		}
		else if (portlet.isUndeployedPortlet()) {
			return LanguageUtil.get(request, "undeployed");
		}

		return LanguageUtil.get(request, "active");
	}

	private String _displayStyle;
<<<<<<< HEAD
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
=======
	private String _orderByCol;
	private String _orderByType;
	private final PortletRequest _portletRequest;
>>>>>>> compatible
	private Layout _selLayout;
	private Long _selPlid;

}