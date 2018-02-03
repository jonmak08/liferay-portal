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

package com.liferay.layout.item.selector.web;

<<<<<<< HEAD
=======
import com.liferay.item.selector.ItemSelectorView;
>>>>>>> compatible
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

<<<<<<< HEAD
=======
import org.osgi.service.component.annotations.Component;
>>>>>>> compatible
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
<<<<<<< HEAD
 * @deprecated As of 1.1.0, replaced by {@link
 *             com.liferay.layout.item.selector.web.internal.PublicLayoutsItemSelectorView}
 */
@Deprecated
=======
 */
@Component(
	service = {ItemSelectorView.class, PublicLayoutsItemSelectorView.class}
)
>>>>>>> compatible
public class PublicLayoutsItemSelectorView extends BaseLayoutsItemSelectorView {

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = _portal.getResourceBundle(locale);

		return ResourceBundleUtil.getString(resourceBundle, "public-pages");
	}

	@Override
	public boolean isPrivateLayout() {
		return false;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		Group group = themeDisplay.getScopeGroup();

		if (group.getPublicLayoutsPageCount() <= 0) {
			return false;
		}

		return true;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.item.selector.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

}