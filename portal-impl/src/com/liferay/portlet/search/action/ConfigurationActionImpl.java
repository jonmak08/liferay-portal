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

package com.liferay.portlet.search.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ContentUtil;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author Alexander Chow
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		boolean advancedConfiguration = GetterUtil.getBoolean(
			getParameter(actionRequest, "advancedConfiguration"));

		if (!advancedConfiguration) {
			updateBasicConfiguration(
				portletConfig, actionRequest, actionResponse);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void updateBasicConfiguration(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String searchConfiguration = portletPreferences.getValue(
			"searchConfiguration", StringPool.BLANK);

		if (Validator.isBlank(searchConfiguration)) {
			searchConfiguration = ContentUtil.get(
				PropsValues.SEARCH_FACET_CONFIGURATION);
		}

		JSONObject configurationJSONObject = JSONFactoryUtil.createJSONObject(
			searchConfiguration);

		JSONArray oldFacetsJSONArray = configurationJSONObject.getJSONArray(
			"facets");

		if (oldFacetsJSONArray == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"The resource " + PropsValues.SEARCH_FACET_CONFIGURATION +
						" is missing a valid facets JSON array");
			}
		}

		JSONArray newFacetsJSONArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < oldFacetsJSONArray.length(); i++) {
			JSONObject oldFacetJSONObject = oldFacetsJSONArray.getJSONObject(i);

			boolean displayFacet = true;

			String parameterName = _DISPLAY_FACET_PARAMETER_NAMES.get(
				oldFacetJSONObject.getString("fieldName"));

			if (parameterName != null) {
				displayFacet = GetterUtil.getBoolean(
					getParameter(actionRequest, parameterName));
			}

			if (displayFacet) {
				newFacetsJSONArray.put(oldFacetJSONObject);
			}
		}

		configurationJSONObject.put("facets", newFacetsJSONArray);

		searchConfiguration = configurationJSONObject.toString();

		setPreference(
			actionRequest, "searchConfiguration", searchConfiguration);
	}

	private static final Map<String, String> _DISPLAY_FACET_PARAMETER_NAMES =
		new HashMap<String, String>() {
			{
				put("assetCategoryIds", "displayAssetCategoriesFacet");
				put("assetTagNames", "displayAssetTagsFacet");
				put("entryClassName", "displayAssetTypeFacet");
				put("folderId", "displayFolderFacet");
				put("groupId", "displayScopeFacet");
				put("modified", "displayModifiedRangeFacet");
				put("userName", "displayUserFacet");
			}
		};

	private static Log _log = LogFactoryUtil.getLog(
		ConfigurationActionImpl.class);

}