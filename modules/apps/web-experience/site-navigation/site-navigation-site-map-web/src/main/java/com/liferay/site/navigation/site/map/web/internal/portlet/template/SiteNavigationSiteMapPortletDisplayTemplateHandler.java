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

package com.liferay.site.navigation.site.map.web.internal.portlet.template;

<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
<<<<<<< HEAD
import com.liferay.portlet.display.template.PortletDisplayTemplateConstants;
=======
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateConstants;
import com.liferay.site.navigation.site.map.web.configuration.SiteNavigationSiteMapWebConfigurationValues;
>>>>>>> compatible
import com.liferay.site.navigation.site.map.web.internal.constants.SiteNavigationSiteMapPortletKeys;
import com.liferay.site.navigation.site.map.web.internal.display.context.SiteNavigationSiteMapDisplayContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Juan Fernández
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP},
	service = TemplateHandler.class
)
public class SiteNavigationSiteMapPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return LayoutSet.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		String portletTitle = _portal.getPortletTitle(
			SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP,
			resourceBundle);

		return portletTitle.concat(StringPool.SPACE).concat(
			LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {
		return SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup templateVariableGroup =
			templateVariableGroups.get("fields");

		templateVariableGroup.empty();

		templateVariableGroup.addCollectionVariable(
			"pages", List.class, PortletDisplayTemplateConstants.ENTRIES,
			"page", Layout.class, "curPage", "getName(locale)");
		templateVariableGroup.addVariable(
			"site-map-display-context",
			SiteNavigationSiteMapDisplayContext.class, "siteMapDisplayContext");

		return templateVariableGroups;
	}

	@Override
	protected String getTemplatesConfigPath() {
<<<<<<< HEAD
		return "com/liferay/site/navigation/site/map/web/portlet/template" +
			"/dependencies/portlet-display-templates.xml";
=======
		return SiteNavigationSiteMapWebConfigurationValues.
			DISPLAY_TEMPLATES_CONFIG;
>>>>>>> compatible
	}

	@Reference
	private Portal _portal;

}