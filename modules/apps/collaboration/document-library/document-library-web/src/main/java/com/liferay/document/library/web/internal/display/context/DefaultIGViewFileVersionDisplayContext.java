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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.web.internal.display.context.logic.DLPortletInstanceSettingsHelper;
import com.liferay.document.library.web.internal.display.context.logic.UIItemsBuilder;
import com.liferay.document.library.web.internal.display.context.util.IGRequestHelper;
<<<<<<< HEAD
import com.liferay.document.library.web.internal.util.DLTrashUtil;
=======
>>>>>>> compatible
import com.liferay.image.gallery.display.kernel.display.context.IGViewFileVersionDisplayContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.ResourceBundle;
=======
>>>>>>> compatible
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class DefaultIGViewFileVersionDisplayContext
	implements IGViewFileVersionDisplayContext {

	public DefaultIGViewFileVersionDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
<<<<<<< HEAD
			FileShortcut fileShortcut, ResourceBundle resourceBundle,
			DLTrashUtil dlTrashUtil)
		throws PortalException {

		this(
			request, response, fileShortcut.getFileVersion(), fileShortcut,
			resourceBundle, dlTrashUtil);
=======
			FileShortcut fileShortcut)
		throws PortalException {

		this(request, response, fileShortcut.getFileVersion(), fileShortcut);
>>>>>>> compatible
	}

	public DefaultIGViewFileVersionDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
<<<<<<< HEAD
			FileVersion fileVersion, FileShortcut fileShortcut,
			ResourceBundle resourceBundle, DLTrashUtil dlTrashUtil)
=======
			FileVersion fileVersion)
		throws PortalException {

		this(request, response, fileVersion, null);
	}

	public DefaultIGViewFileVersionDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
			FileVersion fileVersion, FileShortcut fileShortcut)
>>>>>>> compatible
		throws PortalException {

		_igRequestHelper = new IGRequestHelper(request);

		_dlPorletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(
			_igRequestHelper);

		if (fileShortcut == null) {
<<<<<<< HEAD
			_uiItemsBuilder = new UIItemsBuilder(
				request, fileVersion, resourceBundle, dlTrashUtil);
		}
		else {
			_uiItemsBuilder = new UIItemsBuilder(
				request, fileShortcut, resourceBundle, dlTrashUtil);
		}
	}

	public DefaultIGViewFileVersionDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
			FileVersion fileVersion, ResourceBundle resourceBundle,
			DLTrashUtil dlTrashUtil)
		throws PortalException {

		this(request, response, fileVersion, null, resourceBundle, dlTrashUtil);
	}

=======
			_uiItemsBuilder = new UIItemsBuilder(request, fileVersion);
		}
		else {
			_uiItemsBuilder = new UIItemsBuilder(request, fileShortcut);
		}
	}

>>>>>>> compatible
	@Override
	public Menu getMenu() throws PortalException {
		Menu menu = new Menu();

		menu.setDirection("left-side");
		menu.setMarkupView("lexicon");
		menu.setMenuItems(getMenuItems());
		menu.setScroll(false);
		menu.setShowWhenSingleIcon(true);

		return menu;
	}

	@Override
	public List<MenuItem> getMenuItems() throws PortalException {
		List<MenuItem> menuItems = new ArrayList<>();

		if (_dlPorletInstanceSettingsHelper.isShowActions()) {
			_uiItemsBuilder.addDownloadMenuItem(menuItems);

			_uiItemsBuilder.addViewOriginalFileMenuItem(menuItems);

			_uiItemsBuilder.addEditMenuItem(menuItems);

			_uiItemsBuilder.addPermissionsMenuItem(menuItems);

			_uiItemsBuilder.addDeleteMenuItem(menuItems);
		}

		return menuItems;
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	private static final UUID _UUID = UUID.fromString(
		"C04528F9-C005-4E21-A926-F068750B99DB");

	private final DLPortletInstanceSettingsHelper
		_dlPorletInstanceSettingsHelper;
	private final IGRequestHelper _igRequestHelper;
	private final UIItemsBuilder _uiItemsBuilder;

}