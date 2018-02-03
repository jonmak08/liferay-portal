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

package com.liferay.asset.publisher.web.display.context;

<<<<<<< HEAD
import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.action.AssetEntryAction;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeField;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.publisher.web.configuration.AssetPublisherPortletInstanceConfiguration;
import com.liferay.asset.publisher.web.configuration.AssetPublisherWebConfiguration;
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.constants.AssetPublisherWebKeys;
import com.liferay.asset.publisher.web.internal.action.AssetEntryActionRegistry;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherWebUtil;
import com.liferay.asset.publisher.web.util.AssetPublisherCustomizer;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.asset.util.AssetHelper;
import com.liferay.asset.util.AssetPublisherAddItemHolder;
import com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
=======
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeField;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.internal.configuration.AssetPublisherWebConfigurationValues;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
>>>>>>> compatible
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
<<<<<<< HEAD
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.PrefsParamUtil;
=======
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.RSSUtil;
import com.liferay.portal.kernel.util.StringComparator;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
<<<<<<< HEAD
=======
import com.liferay.portlet.asset.util.AssetUtil;
>>>>>>> compatible

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
=======
import java.util.Locale;
import java.util.Map;
>>>>>>> compatible
import java.util.TimeZone;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides utility methods moved from the Asset Publisher portlet's JSP files
 * to reduce the complexity of the views.
 *
 * @author Eudaldo Alonso
 */
public class AssetPublisherDisplayContext {

	public static final String PAGINATION_TYPE_NONE = "none";

	public static final String PAGINATION_TYPE_REGULAR = "regular";

	public static final String PAGINATION_TYPE_SIMPLE = "simple";

	public static final String[] PAGINATION_TYPES =
		{PAGINATION_TYPE_NONE, PAGINATION_TYPE_REGULAR, PAGINATION_TYPE_SIMPLE};

<<<<<<< HEAD
	public AssetPublisherDisplayContext(
			AssetPublisherCustomizer assetPublisherCustomizer,
			PortletRequest portletRequest, PortletResponse portletResponse,
			PortletPreferences portletPreferences)
		throws ConfigurationException {

		_assetPublisherCustomizer = assetPublisherCustomizer;
		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_portletPreferences = portletPreferences;

		_assetEntryActionRegistry =
			(AssetEntryActionRegistry)portletRequest.getAttribute(
				AssetPublisherWebKeys.ASSET_ENTRY_ACTION_REGISTRY);
		_assetHelper = (AssetHelper)portletRequest.getAttribute(
			AssetWebKeys.ASSET_HELPER);
		_assetPublisherWebUtil =
			(AssetPublisherWebUtil)portletRequest.getAttribute(
				AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_UTIL);
		_assetPublisherPortletInstanceConfiguration =
			(AssetPublisherPortletInstanceConfiguration)
				portletRequest.getAttribute(
					AssetPublisherWebKeys.
						ASSET_PUBLISHER_PORTLET_INSTANCE_CONFIGURATION);
		_assetPublisherWebConfiguration =
			(AssetPublisherWebConfiguration)portletRequest.getAttribute(
				AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_CONFIGURATION);
		_request = PortalUtil.getHttpServletRequest(portletRequest);
	}

	/**
	 * @deprecated As of 2.0.0, replaced by {@link
	 *             #AssetPublisherDisplayContext(AssetPublisherCustomizer,
	 *             PortletRequest,PortletResponse, PortletPreferences)}
	 */
	@Deprecated
	public AssetPublisherDisplayContext(
		PortletRequest portletRequest, PortletPreferences portletPreferences) {

		throw new UnsupportedOperationException(
			"This constructor is deprecated and replaced by " +
				"#AssetPublisherDisplayContext(PortletRequest, " +
					"PortletResponse, PortletPreferences)");
	}

	public int getAbstractLength() {
		if (_abstractLength != null) {
			return _abstractLength;
		}

		_abstractLength = GetterUtil.getInteger(
			_portletPreferences.getValue("abstractLength", null),
			AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH);

=======
	/**
	 * @deprecated As of 1.3.0, replaced by {@link
	 *             #AssetPublisherDisplayContext(PortletRequest,
	 *             PortletResponse, PortletPreferences)}
	 */
	@Deprecated
	public AssetPublisherDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		_request = request;
		_portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		_portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		_portletPreferences = portletPreferences;
	}

	public AssetPublisherDisplayContext(
		PortletRequest portletRequest, PortletResponse portletResponse,
		PortletPreferences portletPreferences) {

		_request = PortalUtil.getHttpServletRequest(portletRequest);
		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_portletPreferences = portletPreferences;
	}

	public int getAbstractLength() {
		if (_abstractLength == null) {
			_abstractLength = GetterUtil.getInteger(
				_portletPreferences.getValue("abstractLength", null),
				AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH);
		}

>>>>>>> compatible
		return _abstractLength;
	}

	public long[] getAllAssetCategoryIds() throws Exception {
		if (_allAssetCategoryIds != null) {
			return _allAssetCategoryIds;
		}

		_allAssetCategoryIds = new long[0];

		long assetCategoryId = ParamUtil.getLong(_request, "categoryId");

		String selectionStyle = getSelectionStyle();

		if (selectionStyle.equals("dynamic")) {
			_allAssetCategoryIds = AssetPublisherUtil.getAssetCategoryIds(
				_portletPreferences);
		}

		if ((assetCategoryId > 0) &&
			!ArrayUtil.contains(_allAssetCategoryIds, assetCategoryId)) {

			_allAssetCategoryIds = ArrayUtil.append(
				_allAssetCategoryIds, assetCategoryId);
		}

		return _allAssetCategoryIds;
	}

	public String[] getAllAssetTagNames() throws Exception {
		if (_allAssetTagNames != null) {
			return _allAssetTagNames;
		}

		_allAssetTagNames = new String[0];

		String assetTagName = ParamUtil.getString(_request, "tag");

		String selectionStyle = getSelectionStyle();

		if (selectionStyle.equals("dynamic")) {
			_allAssetTagNames = AssetPublisherUtil.getAssetTagNames(
				_portletPreferences);
		}

		if (Validator.isNotNull(assetTagName) &&
			!ArrayUtil.contains(_allAssetTagNames, assetTagName)) {

			_allAssetTagNames = ArrayUtil.append(
				_allAssetTagNames, assetTagName);
		}

		if (isMergeURLTags()) {
			_allAssetTagNames = ArrayUtil.append(
				_allAssetTagNames, getCompilerTagNames());
		}

		_allAssetTagNames = ArrayUtil.distinct(
			_allAssetTagNames, new StringComparator());

		return _allAssetTagNames;
	}

<<<<<<< HEAD
	public List<AssetEntryAction> getAssetEntryActions(String className) {
		return _assetEntryActionRegistry.getAssetEntryActions(className);
	}

=======
>>>>>>> compatible
	public AssetEntryQuery getAssetEntryQuery() throws Exception {
		if (_assetEntryQuery != null) {
			return _assetEntryQuery;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_assetEntryQuery = AssetPublisherUtil.getAssetEntryQuery(
<<<<<<< HEAD
			_portletPreferences, themeDisplay.getScopeGroupId(),
			themeDisplay.getLayout(), getAllAssetCategoryIds(),
			getAllAssetTagNames());

		_assetEntryQuery.setEnablePermissions(isEnablePermissions());

		configureSubtypeFieldFilter(_assetEntryQuery, themeDisplay.getLocale());

		_assetEntryQuery.setPaginationType(getPaginationType());

		_assetPublisherWebUtil.processAssetEntryQuery(
			themeDisplay.getUser(), _portletPreferences, _assetEntryQuery);

		_assetPublisherCustomizer.setAssetEntryQueryOptions(
			_assetEntryQuery, _request);

=======
			_portletPreferences, getGroupIds(), getAllAssetCategoryIds(),
			getAllAssetTagNames());

		String portletName = getPortletName();

		if (!portletName.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {
			_assetEntryQuery.setGroupIds(getGroupIds());
		}

		_assetEntryQuery.setClassTypeIds(getClassTypeIds());
		_assetEntryQuery.setEnablePermissions(isEnablePermissions());
		_assetEntryQuery.setExcludeZeroViewCount(isExcludeZeroViewCount());

		configureSubtypeFieldFilter(_assetEntryQuery, themeDisplay.getLocale());

		if (isShowOnlyLayoutAssets()) {
			_assetEntryQuery.setLayout(themeDisplay.getLayout());
		}

		if (portletName.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {
			AssetEntry layoutAssetEntry = (AssetEntry)_request.getAttribute(
				WebKeys.LAYOUT_ASSET_ENTRY);

			if (layoutAssetEntry != null) {
				_assetEntryQuery.setLinkedAssetEntryId(
					layoutAssetEntry.getEntryId());
			}
		}

		_assetEntryQuery.setPaginationType(getPaginationType());
		_assetEntryQuery.setOrderByCol1(getOrderByColumn1());
		_assetEntryQuery.setOrderByCol2(getOrderByColumn2());
		_assetEntryQuery.setOrderByType1(getOrderByType1());
		_assetEntryQuery.setOrderByType2(getOrderByType2());

		AssetPublisherUtil.processAssetEntryQuery(
			themeDisplay.getUser(), _portletPreferences, _assetEntryQuery);

>>>>>>> compatible
		return _assetEntryQuery;
	}

	public String getAssetLinkBehavior() {
<<<<<<< HEAD
		if (_assetLinkBehavior != null) {
			return _assetLinkBehavior;
		}

		_assetLinkBehavior = GetterUtil.getString(
			_portletPreferences.getValue(
				"assetLinkBehavior", "showFullContent"));

		return _assetLinkBehavior;
	}

	public AssetPublisherPortletInstanceConfiguration
		getAssetPublisherPortletInstanceConfiguration() {

		return _assetPublisherPortletInstanceConfiguration;
	}

=======
		if (_assetLinkBehavior == null) {
			_assetLinkBehavior = GetterUtil.getString(
				_portletPreferences.getValue(
					"assetLinkBehavior", "showFullContent"));
		}

		return _assetLinkBehavior;
	}

>>>>>>> compatible
	public Map<String, Serializable> getAttributes() {
		if (_attributes != null) {
			return _attributes;
		}

		_attributes = new HashMap<>();

		Map<String, String[]> parameters = _request.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();

			if (ArrayUtil.isNotEmpty(values)) {
				if (values.length == 1) {
					_attributes.put(name, values[0]);
				}
				else {
					_attributes.put(name, values);
				}
			}
		}

		return _attributes;
	}

<<<<<<< HEAD
	public JSONArray getAutoFieldRulesJSONArray() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String queryLogicIndexesParam = ParamUtil.getString(
			_request, "queryLogicIndexes");

		int[] queryLogicIndexes = null;

		if (Validator.isNotNull(queryLogicIndexesParam)) {
			queryLogicIndexes = StringUtil.split(queryLogicIndexesParam, 0);
		}
		else {
			queryLogicIndexes = new int[0];

			for (int i = 0; true; i++) {
				String queryValues = PrefsParamUtil.getString(
					_portletPreferences, _request, "queryValues" + i);

				if (Validator.isNull(queryValues)) {
					break;
				}

				queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, i);
			}

			if (queryLogicIndexes.length == 0) {
				queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, -1);
			}
		}

		JSONArray rulesJSONArray = JSONFactoryUtil.createJSONArray();

		for (int queryLogicIndex : queryLogicIndexes) {
			JSONObject ruleJSONObject = JSONFactoryUtil.createJSONObject();

			boolean queryAndOperator = PrefsParamUtil.getBoolean(
				_portletPreferences, _request,
				"queryAndOperator" + queryLogicIndex);

			ruleJSONObject.put("queryAndOperator", queryAndOperator);

			boolean queryContains = PrefsParamUtil.getBoolean(
				_portletPreferences, _request,
				"queryContains" + queryLogicIndex, true);

			ruleJSONObject.put("queryContains", queryContains);

			String queryValues = StringUtil.merge(
				_portletPreferences.getValues(
					"queryValues" + queryLogicIndex, new String[0]));

			String queryName = PrefsParamUtil.getString(
				_portletPreferences, _request, "queryName" + queryLogicIndex,
				"assetTags");

			if (Objects.equals(queryName, "assetTags")) {
				queryValues = ParamUtil.getString(
					_request, "queryTagNames" + queryLogicIndex, queryValues);

				queryValues = _assetPublisherWebUtil.filterAssetTagNames(
					themeDisplay.getScopeGroupId(), queryValues);
			}
			else {
				queryValues = ParamUtil.getString(
					_request, "queryCategoryIds" + queryLogicIndex,
					queryValues);

				JSONArray categoryIdsTitles = JSONFactoryUtil.createJSONArray();

				long[] categoryIds = GetterUtil.getLongValues(
					queryValues.split(","));

				for (long categoryId : categoryIds) {
					AssetCategory category =
						AssetCategoryLocalServiceUtil.fetchAssetCategory(
							categoryId);

					categoryIdsTitles.put(
						category.getTitle(themeDisplay.getLocale()));
				}

				ruleJSONObject.put("categoryIdsTitles", categoryIdsTitles);
			}

			ruleJSONObject.put("queryValues", queryValues);
			ruleJSONObject.put("type", queryName);

			rulesJSONArray.put(ruleJSONObject);
		}

		return rulesJSONArray;
	}

	public long[] getAvailableClassNameIds() {
		if (_availableClassNameIds != null) {
			return _availableClassNameIds;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_availableClassNameIds =
			AssetRendererFactoryRegistryUtil.getClassNameIds(
				themeDisplay.getCompanyId(), true);

		return _availableClassNameIds;
	}

	public String getCategorySelectorURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				_request, AssetCategory.class.getName(),
				PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			portletURL.setParameter(
				"eventName",
				_portletResponse.getNamespace() + "selectCategory");
			portletURL.setParameter(
				"selectedCategories", "{selectedCategories}");
			portletURL.setParameter("singleSelect", "{singleSelect}");
			portletURL.setParameter("vocabularyIds", "{vocabularyIds}");

			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	public long[] getClassNameIds() {
		if (_classNameIds != null) {
			return _classNameIds;
		}

		_classNameIds = AssetPublisherUtil.getClassNameIds(
			_portletPreferences, getAvailableClassNameIds());

=======
	public long[] getAvailableClassNameIds() {
		if (_availableClassNameIds == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_availableClassNameIds =
				AssetRendererFactoryRegistryUtil.getClassNameIds(
					themeDisplay.getCompanyId(), true);
		}

		return _availableClassNameIds;
	}

	public long[] getClassNameIds() {
		if (_classNameIds == null) {
			_classNameIds = AssetPublisherUtil.getClassNameIds(
				_portletPreferences, getAvailableClassNameIds());
		}

>>>>>>> compatible
		return _classNameIds;
	}

	public long[] getClassTypeIds() {
<<<<<<< HEAD
		if (_classTypeIds != null) {
			return _classTypeIds;
		}

		_classTypeIds = GetterUtil.getLongValues(
			_portletPreferences.getValues("classTypeIds", null));

=======
		if (_classTypeIds == null) {
			_classTypeIds = GetterUtil.getLongValues(
				_portletPreferences.getValues("classTypeIds", null));
		}

>>>>>>> compatible
		return _classTypeIds;
	}

	public Long getCompanyId() {
		if (_companyId != null) {
			return _companyId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_companyId = themeDisplay.getCompanyId();

		return _companyId;
	}

	public String[] getCompilerTagNames() {
		if (_compilerTagNames != null) {
			return _compilerTagNames;
		}

		_compilerTagNames = new String[0];

		if (isMergeURLTags()) {
			_compilerTagNames = ParamUtil.getParameterValues(_request, "tags");
		}

		return _compilerTagNames;
	}

	public String getDDMStructureDisplayFieldValue() throws Exception {
<<<<<<< HEAD
		if (_ddmStructureDisplayFieldValue != null) {
			return _ddmStructureDisplayFieldValue;
		}

		setDDMStructure();

=======
		if (_ddmStructureDisplayFieldValue == null) {
			setDDMStructure();
		}

>>>>>>> compatible
		return _ddmStructureDisplayFieldValue;
	}

	public String getDDMStructureFieldLabel() throws Exception {
<<<<<<< HEAD
		if (_ddmStructureFieldLabel != null) {
			return _ddmStructureFieldLabel;
		}

		setDDMStructure();

=======
		if (_ddmStructureFieldLabel == null) {
			setDDMStructure();
		}

>>>>>>> compatible
		return _ddmStructureFieldLabel;
	}

	public String getDDMStructureFieldName() throws Exception {
<<<<<<< HEAD
		if (_ddmStructureFieldName != null) {
			return _ddmStructureFieldName;
		}

		setDDMStructure();

=======
		if (_ddmStructureFieldName == null) {
			setDDMStructure();
		}

>>>>>>> compatible
		return _ddmStructureFieldName;
	}

	public String getDDMStructureFieldValue() throws Exception {
<<<<<<< HEAD
		if (_ddmStructureFieldValue != null) {
			return _ddmStructureFieldValue;
		}

		setDDMStructure();

=======
		if (_ddmStructureFieldValue == null) {
			setDDMStructure();
		}

>>>>>>> compatible
		return _ddmStructureFieldValue;
	}

	public Integer getDelta() {
<<<<<<< HEAD
		return _assetPublisherCustomizer.getDelta(_request);
	}

	public String getDisplayStyle() {
		if (_displayStyle != null) {
			return _displayStyle;
		}

		_displayStyle = GetterUtil.getString(
			_portletPreferences.getValue(
				"displayStyle",
				_assetPublisherPortletInstanceConfiguration.
					defaultDisplayStyle()));

=======
		if (_delta != null) {
			return _delta;
		}

		_delta = GetterUtil.getInteger(
			_portletPreferences.getValue("delta", null),
			SearchContainer.DEFAULT_DELTA);

		String portletName = getPortletName();

		if (portletName.equals(AssetPublisherPortletKeys.RECENT_CONTENT)) {
			_delta = PropsValues.RECENT_CONTENT_MAX_DISPLAY_ITEMS;
		}

		return _delta;
	}

	public String getDisplayStyle() {
		if (_displayStyle == null) {
			_displayStyle = GetterUtil.getString(
				_portletPreferences.getValue(
					"displayStyle",
					AssetPublisherWebConfigurationValues.
						DISPLAY_STYLE_DEFAULT));
		}

>>>>>>> compatible
		return _displayStyle;
	}

	public long getDisplayStyleGroupId() {
<<<<<<< HEAD
		if (_displayStyleGroupId != null) {
			return _displayStyleGroupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_displayStyleGroupId = GetterUtil.getLong(
			_portletPreferences.getValue("displayStyleGroupId", null),
			themeDisplay.getScopeGroupId());
=======
		if (_displayStyleGroupId == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = GetterUtil.getLong(
				_portletPreferences.getValue("displayStyleGroupId", null),
				themeDisplay.getScopeGroupId());
		}
>>>>>>> compatible

		return _displayStyleGroupId;
	}

	public String[] getExtensions() {
<<<<<<< HEAD
		if (_extensions != null) {
			return _extensions;
		}

		_extensions = _portletPreferences.getValues(
			"extensions", new String[0]);

=======
		if (_extensions == null) {
			_extensions = _portletPreferences.getValues(
				"extensions", new String[0]);
		}

>>>>>>> compatible
		return _extensions;
	}

	public String[] getExtensions(AssetRenderer<?> assetRenderer) {
		final String[] supportedConversions =
			assetRenderer.getSupportedConversions();

		if (supportedConversions == null) {
			return getExtensions();
		}

		return ArrayUtil.filter(
			getExtensions(),
			new PredicateFilter<String>() {

				@Override
				public boolean filter(String extension) {
					return ArrayUtil.contains(supportedConversions, extension);
				}

			});
	}

	public long[] getGroupIds() {
<<<<<<< HEAD
		if (_groupIds != null) {
			return _groupIds;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_groupIds = AssetPublisherUtil.getGroupIds(
			_portletPreferences, themeDisplay.getScopeGroupId(),
			themeDisplay.getLayout());
=======
		if (_groupIds == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_groupIds = AssetPublisherUtil.getGroupIds(
				_portletPreferences, themeDisplay.getScopeGroupId(),
				themeDisplay.getLayout());
		}
>>>>>>> compatible

		return _groupIds;
	}

	public Layout getLayout() {
		if (_layout != null) {
			return _layout;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_layout = themeDisplay.getLayout();

		return _layout;
	}

	public Locale getLocale() {
		if (_locale != null) {
			return _locale;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_locale = themeDisplay.getLocale();

		return _locale;
	}

	public String[] getMetadataFields() {
<<<<<<< HEAD
		if (_metadataFields != null) {
			return _metadataFields;
		}

		_metadataFields = StringUtil.split(
			_portletPreferences.getValue("metadataFields", StringPool.BLANK));

=======
		if (_metadataFields == null) {
			_metadataFields = StringUtil.split(
				_portletPreferences.getValue(
					"metadataFields", StringPool.BLANK));
		}

>>>>>>> compatible
		return _metadataFields;
	}

	public String getOrderByColumn1() {
<<<<<<< HEAD
		if (_orderByColumn1 != null) {
			return _orderByColumn1;
		}

		_orderByColumn1 = GetterUtil.getString(
			_portletPreferences.getValue("orderByColumn1", "modifiedDate"));

=======
		if (_orderByColumn1 == null) {
			_orderByColumn1 = GetterUtil.getString(
				_portletPreferences.getValue("orderByColumn1", "modifiedDate"));
		}

>>>>>>> compatible
		return _orderByColumn1;
	}

	public String getOrderByColumn2() {
<<<<<<< HEAD
		if (_orderByColumn2 != null) {
			return _orderByColumn2;
		}

		_orderByColumn2 = GetterUtil.getString(
			_portletPreferences.getValue("orderByColumn2", "title"));

=======
		if (_orderByColumn2 == null) {
			_orderByColumn2 = GetterUtil.getString(
				_portletPreferences.getValue("orderByColumn2", "title"));
		}

>>>>>>> compatible
		return _orderByColumn2;
	}

	public String getOrderByType1() {
<<<<<<< HEAD
		if (_orderByType1 != null) {
			return _orderByType1;
		}

		_orderByType1 = GetterUtil.getString(
			_portletPreferences.getValue("orderByType1", "DESC"));

=======
		if (_orderByType1 == null) {
			_orderByType1 = GetterUtil.getString(
				_portletPreferences.getValue("orderByType1", "DESC"));
		}

>>>>>>> compatible
		return _orderByType1;
	}

	public String getOrderByType2() {
<<<<<<< HEAD
		if (_orderByType2 != null) {
			return _orderByType2;
		}

		_orderByType2 = GetterUtil.getString(
			_portletPreferences.getValue("orderByType2", "ASC"));

=======
		if (_orderByType2 == null) {
			_orderByType2 = GetterUtil.getString(
				_portletPreferences.getValue("orderByType2", "ASC"));
		}

>>>>>>> compatible
		return _orderByType2;
	}

	public String getPaginationType() {
<<<<<<< HEAD
		if (_paginationType != null) {
			return _paginationType;
		}

		_paginationType = GetterUtil.getString(
			_portletPreferences.getValue("paginationType", "none"));

		if (!ArrayUtil.contains(PAGINATION_TYPES, _paginationType)) {
			_paginationType = PAGINATION_TYPE_NONE;
=======
		if (_paginationType == null) {
			_paginationType = GetterUtil.getString(
				_portletPreferences.getValue("paginationType", "none"));

			if (!ArrayUtil.contains(PAGINATION_TYPES, _paginationType)) {
				_paginationType = PAGINATION_TYPE_NONE;
			}
>>>>>>> compatible
		}

		return _paginationType;
	}

	public String getPortletName() {
		PortletConfig portletConfig = (PortletConfig)_request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		if (portletConfig == null) {
			return StringPool.BLANK;
		}

		return portletConfig.getPortletName();
	}

	public String getPortletResource() {
<<<<<<< HEAD
		if (_portletResource != null) {
			return _portletResource;
		}

		_portletResource = ParamUtil.getString(_request, "portletResource");

=======
		if (_portletResource == null) {
			_portletResource = ParamUtil.getString(_request, "portletResource");
		}

>>>>>>> compatible
		return _portletResource;
	}

	public long[] getReferencedModelsGroupIds() throws PortalException {

		// Referenced models are asset subtypes, tags or categories that
		// are used to filter assets and can belong to a different scope of
		// the asset they are associated to

<<<<<<< HEAD
		if (_referencedModelsGroupIds != null) {
			return _referencedModelsGroupIds;
		}

		_referencedModelsGroupIds =
			PortalUtil.getCurrentAndAncestorSiteGroupIds(getGroupIds(), true);

=======
		if (_referencedModelsGroupIds == null) {
			_referencedModelsGroupIds =
				PortalUtil.getCurrentAndAncestorSiteGroupIds(
					getGroupIds(), true);
		}

>>>>>>> compatible
		return _referencedModelsGroupIds;
	}

	public String getRootPortletId() {
<<<<<<< HEAD
		if (_rootPortletId != null) {
			return _rootPortletId;
		}

		_rootPortletId = PortletIdCodec.decodePortletName(getPortletResource());

=======
		if (_rootPortletId == null) {
			_rootPortletId = PortletIdCodec.decodePortletName(
				getPortletResource());
		}

>>>>>>> compatible
		return _rootPortletId;
	}

	public int getRSSDelta() {
<<<<<<< HEAD
		if (_rssDelta != null) {
			return _rssDelta;
		}

		_rssDelta = GetterUtil.getInteger(
			_portletPreferences.getValue("rssDelta", StringPool.BLANK),
			SearchContainer.DEFAULT_DELTA);

=======
		if (_rssDelta == null) {
			_rssDelta = GetterUtil.getInteger(
				_portletPreferences.getValue("rssDelta", StringPool.BLANK),
				SearchContainer.DEFAULT_DELTA);
		}

>>>>>>> compatible
		return _rssDelta;
	}

	public String getRSSDisplayStyle() {
<<<<<<< HEAD
		if (_rssDisplayStyle != null) {
			return _rssDisplayStyle;
		}

		_rssDisplayStyle = _portletPreferences.getValue(
			"rssDisplayStyle", RSSUtil.DISPLAY_STYLE_ABSTRACT);

=======
		if (_rssDisplayStyle == null) {
			_rssDisplayStyle = _portletPreferences.getValue(
				"rssDisplayStyle", RSSUtil.DISPLAY_STYLE_ABSTRACT);
		}

>>>>>>> compatible
		return _rssDisplayStyle;
	}

	public String getRSSFeedType() {
<<<<<<< HEAD
		if (_rssFeedType != null) {
			return _rssFeedType;
		}

		_rssFeedType = _portletPreferences.getValue(
			"rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);

=======
		if (_rssFeedType == null) {
			_rssFeedType = _portletPreferences.getValue(
				"rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);
		}

>>>>>>> compatible
		return _rssFeedType;
	}

	public String getRSSName() {
		if (_rssName != null) {
			return _rssName;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_rssName = _portletPreferences.getValue(
			"rssName", portletDisplay.getTitle());

		return _rssName;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.0.0, replaced by {@link
	 *             #getScopeAssetPublisherAddItemHolders(int)}
	 */
	@Deprecated
=======
>>>>>>> compatible
	public Map<Long, Map<String, PortletURL>> getScopeAddPortletURLs(int max)
		throws Exception {

		long[] groupIds = getGroupIds();

		if (groupIds.length == 0) {
			return Collections.emptyMap();
		}

<<<<<<< HEAD
		Map<Long, Map<String, PortletURL>> scopeAddPortletURLs =
			new HashMap<>();

		LiferayPortletRequest liferayPortletRequest =
			PortalUtil.getLiferayPortletRequest(_portletRequest);
		LiferayPortletResponse liferayPortletResponse =
			PortalUtil.getLiferayPortletResponse(_portletResponse);

		String redirect = _getScopeAssetPortletRedirect(
			liferayPortletRequest, liferayPortletResponse);

		for (long groupId : groupIds) {
			List<AssetPublisherAddItemHolder> assetPublisherAddItemHolders =
				_assetHelper.getAssetPublisherAddItemHolders(
					liferayPortletRequest, liferayPortletResponse, groupId,
					getClassNameIds(), getClassTypeIds(),
					getAllAssetCategoryIds(), getAllAssetTagNames(), redirect);

			if (ListUtil.isNotEmpty(assetPublisherAddItemHolders)) {
				Map<String, PortletURL> addPortletURLs = new HashMap<>();

				for (AssetPublisherAddItemHolder assetPublisherAddItemHolder :
						assetPublisherAddItemHolders) {

					addPortletURLs.put(
						assetPublisherAddItemHolder.getName(),
						assetPublisherAddItemHolder.getPortletURL());
				}

				scopeAddPortletURLs.put(groupId, addPortletURLs);
			}

			if (scopeAddPortletURLs.size() > max) {
				break;
			}
		}

		return scopeAddPortletURLs;
	}

	public Map<Long, List<AssetPublisherAddItemHolder>>
			getScopeAssetPublisherAddItemHolders(int max)
		throws Exception {

		long[] groupIds = getGroupIds();

		if (groupIds.length == 0) {
			return Collections.emptyMap();
		}

		Map<Long, List<AssetPublisherAddItemHolder>>
			scopeAssetPublisherAddItemHolders = new HashMap<>();

		LiferayPortletRequest liferayPortletRequest =
			PortalUtil.getLiferayPortletRequest(_portletRequest);
		LiferayPortletResponse liferayPortletResponse =
			PortalUtil.getLiferayPortletResponse(_portletResponse);

		String redirect = _getScopeAssetPortletRedirect(
			liferayPortletRequest, liferayPortletResponse);

		for (long groupId : groupIds) {
			List<AssetPublisherAddItemHolder> assetPublisherAddItemHolders =
				_assetHelper.getAssetPublisherAddItemHolders(
=======
		Map<Long, Map<String, PortletURL>> scopeAddPortletURLs = new HashMap();

		LiferayPortletResponse liferayPortletResponse =
			PortalUtil.getLiferayPortletResponse(_portletResponse);

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		redirectURL.setParameter(
			"hideDefaultSuccessMessage", Boolean.TRUE.toString());
		redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp");

		LiferayPortletRequest liferayPortletRequest =
			PortalUtil.getLiferayPortletRequest(_portletRequest);

		PortletURL currentURLObj = PortletURLUtil.getCurrent(
			liferayPortletRequest, liferayPortletResponse);

		redirectURL.setParameter("redirect", currentURLObj.toString());

		redirectURL.setWindowState(LiferayWindowState.POP_UP);

		String redirect = redirectURL.toString();

		for (long groupId : groupIds) {
			Map<String, PortletURL> addPortletURLs =
				AssetUtil.getAddPortletURLs(
>>>>>>> compatible
					liferayPortletRequest, liferayPortletResponse, groupId,
					getClassNameIds(), getClassTypeIds(),
					getAllAssetCategoryIds(), getAllAssetTagNames(), redirect);

<<<<<<< HEAD
			if (ListUtil.isNotEmpty(assetPublisherAddItemHolders)) {
				scopeAssetPublisherAddItemHolders.put(
					groupId, assetPublisherAddItemHolders);
			}

			if (scopeAssetPublisherAddItemHolders.size() > max) {
=======
			if (MapUtil.isNotEmpty(addPortletURLs)) {
				scopeAddPortletURLs.put(groupId, addPortletURLs);
			}

			if (scopeAddPortletURLs.size() > max) {
>>>>>>> compatible
				break;
			}
		}

<<<<<<< HEAD
		return scopeAssetPublisherAddItemHolders;
=======
		return scopeAddPortletURLs;
>>>>>>> compatible
	}

	public Long getScopeGroupId() {
		if (_scopeGroupId != null) {
			return _scopeGroupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_scopeGroupId = themeDisplay.getScopeGroupId();

		return _scopeGroupId;
	}

	public String getSelectionStyle() {
<<<<<<< HEAD
		if (_selectionStyle != null) {
			return _selectionStyle;
		}

		_selectionStyle = GetterUtil.getString(
			_portletPreferences.getValue("selectionStyle", null), "dynamic");

		return _selectionStyle;
	}

	/**
	 * @deprecated As of 2.0.0, with no direct replacement
	 */
	@Deprecated
	public String getSocialBookmarksDisplayPosition() {
		return "bottom";
=======
		if (_selectionStyle == null) {
			_selectionStyle = GetterUtil.getString(
				_portletPreferences.getValue("selectionStyle", null),
				"dynamic");
		}

		return _selectionStyle;
	}

	public String getSocialBookmarksDisplayPosition() {
		if (_socialBookmarksDisplayPosition == null) {
			_socialBookmarksDisplayPosition = _portletPreferences.getValue(
				"socialBookmarksDisplayPosition", "bottom");
		}

		return _socialBookmarksDisplayPosition;
>>>>>>> compatible
	}

	public String getSocialBookmarksDisplayStyle() {
		if (_socialBookmarksDisplayStyle != null) {
			return _socialBookmarksDisplayStyle;
		}

		_socialBookmarksDisplayStyle = _portletPreferences.getValue(
			"socialBookmarksDisplayStyle", null);

		if (Validator.isNull(_socialBookmarksDisplayStyle)) {
			String[] socialBookmarksDisplayStyles = PropsUtil.getArray(
				PropsKeys.SOCIAL_BOOKMARK_DISPLAY_STYLES);

			_socialBookmarksDisplayStyle = socialBookmarksDisplayStyles[0];
		}

		return _socialBookmarksDisplayStyle;
	}

<<<<<<< HEAD
	public String getSocialBookmarksTypes() {
		if (_socialBookmarksTypes != null) {
			return _socialBookmarksTypes;
		}

		_socialBookmarksTypes = _portletPreferences.getValue(
			"socialBookmarksTypes", null);

		if (_socialBookmarksTypes == null) {
			_socialBookmarksTypes = PropsUtil.get(
				PropsKeys.SOCIAL_BOOKMARK_TYPES);
		}

		return _socialBookmarksTypes;
	}

	public String getTagSelectorURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				_request, AssetTag.class.getName(),
				PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			portletURL.setParameter(
				"eventName", _portletResponse.getNamespace() + "selectTag");
			portletURL.setParameter(
				"groupIds", StringUtil.merge(getGroupIds()));
			portletURL.setParameter("selectedTags", "{selectedTags}");
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

=======
>>>>>>> compatible
	public TimeZone getTimeZone() {
		if (_timeZone != null) {
			return _timeZone;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_timeZone = themeDisplay.getTimeZone();

		return _timeZone;
	}

	public Long getUserId() {
		if (_userId != null) {
			return _userId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_userId = themeDisplay.getUserId();

		return _userId;
	}

<<<<<<< HEAD
	public String getVocabularyIds() throws Exception {
		List<AssetVocabulary> vocabularies =
			AssetVocabularyServiceUtil.getGroupsVocabularies(getGroupIds());

		return ListUtil.toString(
			vocabularies, AssetVocabulary.VOCABULARY_ID_ACCESSOR);
	}

=======
>>>>>>> compatible
	public AssetEntry incrementViewCounter(AssetEntry assetEntry)
		throws PortalException {

		// Dynamically created asset entries are never persisted so incrementing
		// the view counter breaks

		if ((assetEntry == null) || assetEntry.isNew() ||
			!assetEntry.isVisible() || !isEnableViewCountIncrement()) {

			return assetEntry;
		}

		if (isEnablePermissions()) {
			return AssetEntryServiceUtil.incrementViewCounter(
				assetEntry.getClassName(), assetEntry.getClassPK());
		}
		else {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			return AssetEntryLocalServiceUtil.incrementViewCounter(
				themeDisplay.getUserId(), assetEntry.getClassName(),
				assetEntry.getClassPK());
		}
	}

	public Boolean isAnyAssetType() {
<<<<<<< HEAD
		if (_anyAssetType != null) {
			return _anyAssetType;
		}

		_anyAssetType = GetterUtil.getBoolean(
			_portletPreferences.getValue("anyAssetType", null), true);

=======
		if (_anyAssetType == null) {
			_anyAssetType = GetterUtil.getBoolean(
				_portletPreferences.getValue("anyAssetType", null), true);
		}

>>>>>>> compatible
		return _anyAssetType;
	}

	public boolean isAssetLinkBehaviorShowFullContent() {
		String assetLinkBehavior = getAssetLinkBehavior();

		return assetLinkBehavior.equals("showFullContent");
	}

	public boolean isAssetLinkBehaviorViewInPortlet() {
		String assetLinkBehavior = getAssetLinkBehavior();

		return assetLinkBehavior.equals("viewInPortlet");
	}

	public boolean isDefaultAssetPublisher() {
		if (_defaultAssetPublisher != null) {
			return _defaultAssetPublisher;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

<<<<<<< HEAD
		_defaultAssetPublisher = _assetPublisherWebUtil.isDefaultAssetPublisher(
=======
		_defaultAssetPublisher = AssetUtil.isDefaultAssetPublisher(
>>>>>>> compatible
			themeDisplay.getLayout(), portletDisplay.getId(),
			getPortletResource());

		return _defaultAssetPublisher;
	}

	public boolean isEnableCommentRatings() {
<<<<<<< HEAD
		if (_enableCommentRatings != null) {
			return _enableCommentRatings;
		}

		_enableCommentRatings = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableCommentRatings", null));

=======
		if (_enableCommentRatings == null) {
			_enableCommentRatings = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableCommentRatings", null));
		}

>>>>>>> compatible
		return _enableCommentRatings;
	}

	public boolean isEnableComments() {
<<<<<<< HEAD
		if (_enableComments != null) {
			return _enableComments;
		}

		_enableComments = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableComments", null));

=======
		if (_enableComments == null) {
			_enableComments = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableComments", null));
		}

>>>>>>> compatible
		return _enableComments;
	}

	public Boolean isEnableConversions() throws Exception {
<<<<<<< HEAD
		if (_enableConversions != null) {
			return _enableConversions;
		}

		_enableConversions =
			isOpenOfficeServerEnabled() &&
			ArrayUtil.isNotEmpty(getExtensions());

=======
		if (_enableConversions == null) {
			_enableConversions =
				isOpenOfficeServerEnabled() &&
				ArrayUtil.isNotEmpty(getExtensions());
		}

>>>>>>> compatible
		return _enableConversions;
	}

	public boolean isEnableFlags() {
<<<<<<< HEAD
		if (_enableFlags != null) {
			return _enableFlags;
		}

		_enableFlags = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableFlags", null));

=======
		if (_enableFlags == null) {
			_enableFlags = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableFlags", null));
		}

>>>>>>> compatible
		return _enableFlags;
	}

	public Boolean isEnablePermissions() {
<<<<<<< HEAD
		return _assetPublisherCustomizer.isEnablePermissions(_request);
	}

	public boolean isEnablePrint() {
		if (_enablePrint != null) {
			return _enablePrint;
		}

		_enablePrint = GetterUtil.getBoolean(
			_portletPreferences.getValue("enablePrint", null));

=======
		if (_enablePermissions != null) {
			return _enablePermissions;
		}

		String portletName = getPortletName();

		if (!portletName.equals(
				AssetPublisherPortletKeys.HIGHEST_RATED_ASSETS) &&
			!portletName.equals(AssetPublisherPortletKeys.MOST_VIEWED_ASSETS) &&
			AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX) {

			_enablePermissions = true;

			return _enablePermissions;
		}

		if (!AssetPublisherWebConfigurationValues.
				PERMISSION_CHECKING_CONFIGURABLE) {

			_enablePermissions = true;

			return _enablePermissions;
		}

		_enablePermissions = GetterUtil.getBoolean(
			_portletPreferences.getValue("enablePermissions", null), true);

		return _enablePermissions;
	}

	public boolean isEnablePrint() {
		if (_enablePrint == null) {
			_enablePrint = GetterUtil.getBoolean(
				_portletPreferences.getValue("enablePrint", null));
		}

>>>>>>> compatible
		return _enablePrint;
	}

	public boolean isEnableRatings() {
<<<<<<< HEAD
		if (_enableRatings != null) {
			return _enableRatings;
		}

		_enableRatings = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableRatings", null));

=======
		if (_enableRatings == null) {
			_enableRatings = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableRatings", null));
		}

>>>>>>> compatible
		return _enableRatings;
	}

	public boolean isEnableRelatedAssets() {
<<<<<<< HEAD
		if (_enableRelatedAssets != null) {
			return _enableRelatedAssets;
		}

		_enableRelatedAssets = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableRelatedAssets", null), true);

=======
		if (_enableRelatedAssets == null) {
			_enableRelatedAssets = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableRelatedAssets", null),
				true);
		}

>>>>>>> compatible
		return _enableRelatedAssets;
	}

	public boolean isEnableRSS() {
<<<<<<< HEAD
		if (_enableRSS != null) {
			return _enableRSS;
		}

		_enableRSS = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableRss", null));

=======
		if (_enableRSS == null) {
			_enableRSS = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableRss", null));
		}

>>>>>>> compatible
		return _enableRSS;
	}

	public boolean isEnableSetAsDefaultAssetPublisher() {
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(AssetPublisherPortletKeys.ASSET_PUBLISHER)) {
			return true;
		}

		return false;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isEnableSocialBookmarks() {
		return true;
	}

	public boolean isEnableTagBasedNavigation() {
		if (_enableTagBasedNavigation != null) {
			return _enableTagBasedNavigation;
		}

		_enableTagBasedNavigation = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableTagBasedNavigation", null));

=======
	public boolean isEnableSocialBookmarks() {
		if (_enableSocialBookmarks == null) {
			_enableSocialBookmarks = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableSocialBookmarks", null),
				true);
		}

		return _enableSocialBookmarks;
	}

	public boolean isEnableTagBasedNavigation() {
		if (_enableTagBasedNavigation == null) {
			_enableTagBasedNavigation = GetterUtil.getBoolean(
				_portletPreferences.getValue("enableTagBasedNavigation", null));
		}

>>>>>>> compatible
		return _enableTagBasedNavigation;
	}

	public boolean isEnableViewCountIncrement() {
		if (_enableViewCountIncrement != null) {
			return _enableViewCountIncrement;
		}

		_enableViewCountIncrement = GetterUtil.getBoolean(
			_portletPreferences.getValue("enableViewCountIncrement", null),
			PropsValues.ASSET_ENTRY_BUFFERED_INCREMENT_ENABLED);

		return _enableViewCountIncrement;
	}

	public boolean isExcludeZeroViewCount() {
<<<<<<< HEAD
		if (_excludeZeroViewCount != null) {
			return _excludeZeroViewCount;
		}

		_excludeZeroViewCount = GetterUtil.getBoolean(
			_portletPreferences.getValue("excludeZeroViewCount", null));

=======
		if (_excludeZeroViewCount == null) {
			_excludeZeroViewCount = GetterUtil.getBoolean(
				_portletPreferences.getValue("excludeZeroViewCount", null));
		}

>>>>>>> compatible
		return _excludeZeroViewCount;
	}

	public boolean isMergeURLTags() {
<<<<<<< HEAD
		if (_mergeURLTags != null) {
			return _mergeURLTags;
		}

		_mergeURLTags = GetterUtil.getBoolean(
			_portletPreferences.getValue("mergeUrlTags", null), true);

=======
		if (_mergeURLTags == null) {
			_mergeURLTags = GetterUtil.getBoolean(
				_portletPreferences.getValue("mergeUrlTags", null), true);
		}

>>>>>>> compatible
		return _mergeURLTags;
	}

	public boolean isOpenOfficeServerEnabled() {
<<<<<<< HEAD
		return DocumentConversionUtil.isEnabled();
	}

	public boolean isOrderingAndGroupingEnabled() {
		return _assetPublisherCustomizer.isOrderingAndGroupingEnabled(_request);
	}

	public boolean isOrderingByTitleEnabled() {
		return _assetPublisherCustomizer.isOrderingByTitleEnabled(_request);
=======
		if (_openOfficeServerEnabled == null) {
			_openOfficeServerEnabled = PrefsPropsUtil.getBoolean(
				PropsKeys.OPENOFFICE_SERVER_ENABLED,
				PropsValues.OPENOFFICE_SERVER_ENABLED);
		}

		return _openOfficeServerEnabled;
	}

	public boolean isOrderingAndGroupingEnabled() {
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(
				AssetPublisherPortletKeys.HIGHEST_RATED_ASSETS) ||
			rootPortletId.equals(
				AssetPublisherPortletKeys.MOST_VIEWED_ASSETS)) {

			return false;
		}

		return true;
	}

	public boolean isOrderingByTitleEnabled() {
		String rootPortletId = getRootPortletId();

		if (!AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX ||
			rootPortletId.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {

			return false;
		}

		return true;
>>>>>>> compatible
	}

	public boolean isPaginationTypeNone() {
		String paginationType = getPaginationType();

		return paginationType.equals(PAGINATION_TYPE_NONE);
	}

	public boolean isPaginationTypeSelected(String paginationType) {
		String curPaginationType = getPaginationType();

		return curPaginationType.equals(paginationType);
	}

	public boolean isSelectionStyleDynamic() {
		String selectionStyle = getSelectionStyle();

		return selectionStyle.equals("dynamic");
	}

	public boolean isSelectionStyleEnabled() {
<<<<<<< HEAD
		return _assetPublisherCustomizer.isSelectionStyleEnabled(_request);
=======
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(
				AssetPublisherPortletKeys.HIGHEST_RATED_ASSETS) ||
			rootPortletId.equals(
				AssetPublisherPortletKeys.MOST_VIEWED_ASSETS) ||
			rootPortletId.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {

			return false;
		}

		return true;
>>>>>>> compatible
	}

	public boolean isSelectionStyleManual() {
		String selectionStyle = getSelectionStyle();

		return selectionStyle.equals("manual");
	}

	public boolean isShowAddContentButton() {
<<<<<<< HEAD
		if (_showAddContentButton != null) {
			return _showAddContentButton;
		}

		_showAddContentButton = GetterUtil.getBoolean(
			_portletPreferences.getValue("showAddContentButton", null), true);

=======
		if (_showAddContentButton == null) {
			_showAddContentButton = GetterUtil.getBoolean(
				_portletPreferences.getValue("showAddContentButton", null),
				true);
		}

>>>>>>> compatible
		return _showAddContentButton;
	}

	public Boolean isShowAssetTitle() {
<<<<<<< HEAD
		if (_showAssetTitle != null) {
			return _showAssetTitle;
		}

		_showAssetTitle = GetterUtil.getBoolean(
			_portletPreferences.getValue("showAssetTitle", null), true);

=======
		if (_showAssetTitle == null) {
			_showAssetTitle = GetterUtil.getBoolean(
				_portletPreferences.getValue("showAssetTitle", null), true);
		}

>>>>>>> compatible
		return _showAssetTitle;
	}

	public Boolean isShowAvailableLocales() {
<<<<<<< HEAD
		if (_showAvailableLocales != null) {
			return _showAvailableLocales;
		}

		_showAvailableLocales = GetterUtil.getBoolean(
			_portletPreferences.getValue("showAvailableLocales", null));

=======
		if (_showAvailableLocales == null) {
			_showAvailableLocales = GetterUtil.getBoolean(
				_portletPreferences.getValue("showAvailableLocales", null));
		}

>>>>>>> compatible
		return _showAvailableLocales;
	}

	public Boolean isShowContextLink() {
<<<<<<< HEAD
		if (_showContextLink != null) {
			return _showContextLink;
		}

		_showContextLink = GetterUtil.getBoolean(
			_portletPreferences.getValue("showContextLink", null), true);

=======
		if (_showContextLink == null) {
			_showContextLink = GetterUtil.getBoolean(
				_portletPreferences.getValue("showContextLink", null), true);
		}

>>>>>>> compatible
		return _showContextLink;
	}

	public Boolean isShowContextLink(long groupId, String portletId)
		throws PortalException {

<<<<<<< HEAD
		if (_showContextLink != null) {
			return _showContextLink;
		}

		_showContextLink = isShowContextLink();

		if (_showContextLink &&
			(PortalUtil.getPlidFromPortletId(groupId, portletId) == 0)) {

			_showContextLink = false;
=======
		if (_showContextLink == null) {
			_showContextLink = isShowContextLink();

			if (_showContextLink) {
				if (PortalUtil.getPlidFromPortletId(groupId, portletId) == 0) {
					_showContextLink = false;
				}
			}
>>>>>>> compatible
		}

		return _showContextLink;
	}

	public boolean isShowEnableAddContentButton() {
<<<<<<< HEAD
		return _assetPublisherCustomizer.isShowEnableAddContentButton(_request);
	}

	public Boolean isShowEnablePermissions() {
		if (_assetPublisherWebConfiguration.searchWithIndex()) {
			return false;
		}

		return _assetPublisherWebConfiguration.permissionCheckingConfigurable();
	}

	public boolean isShowEnableRelatedAssets() {
		return _assetPublisherCustomizer.isShowEnableRelatedAssets(_request);
	}

	public boolean isShowExtraInfo() {
		if (_showExtraInfo != null) {
			return _showExtraInfo;
		}

		_showExtraInfo = GetterUtil.getBoolean(
			_portletPreferences.getValue("showExtraInfo", null), true);

=======
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(
				AssetPublisherPortletKeys.HIGHEST_RATED_ASSETS) ||
			rootPortletId.equals(
				AssetPublisherPortletKeys.MOST_VIEWED_ASSETS)) {

			return false;
		}

		return true;
	}

	public Boolean isShowEnablePermissions() {
		if (AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX) {
			return false;
		}

		return AssetPublisherWebConfigurationValues.
			PERMISSION_CHECKING_CONFIGURABLE;
	}

	public boolean isShowEnableRelatedAssets() {
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {
			return false;
		}

		return true;
	}

	public boolean isShowExtraInfo() {
		if (_showExtraInfo == null) {
			_showExtraInfo = GetterUtil.getBoolean(
				_portletPreferences.getValue("showExtraInfo", null), true);
		}

>>>>>>> compatible
		return _showExtraInfo;
	}

	public boolean isShowMetadataDescriptions() {
<<<<<<< HEAD
		if (_showMetadataDescriptions != null) {
			return _showMetadataDescriptions;
		}

		_showMetadataDescriptions = GetterUtil.getBoolean(
			_portletPreferences.getValue("showMetadataDescriptions", null),
			true);

=======
		if (_showMetadataDescriptions == null) {
			_showMetadataDescriptions = GetterUtil.getBoolean(
				_portletPreferences.getValue("showMetadataDescriptions", null),
				true);
		}

>>>>>>> compatible
		return _showMetadataDescriptions;
	}

	public boolean isShowOnlyLayoutAssets() {
<<<<<<< HEAD
		if (_showOnlyLayoutAssets != null) {
			return _showOnlyLayoutAssets;
		}

		_showOnlyLayoutAssets = GetterUtil.getBoolean(
			_portletPreferences.getValue("showOnlyLayoutAssets", null));

=======
		if (_showOnlyLayoutAssets == null) {
			_showOnlyLayoutAssets = GetterUtil.getBoolean(
				_portletPreferences.getValue("showOnlyLayoutAssets", null));
		}

>>>>>>> compatible
		return _showOnlyLayoutAssets;
	}

	public boolean isShowScopeSelector() {
		String rootPortletId = getRootPortletId();

		if (rootPortletId.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {
			return false;
		}

		return true;
	}

	public boolean isShowSubtypeFieldsFilter() {
<<<<<<< HEAD
		return _assetPublisherCustomizer.isShowSubtypeFieldsFilter(_request);
	}

	public boolean isSubtypeFieldsFilterEnabled() {
		if (_subtypeFieldsFilterEnabled != null) {
			return _subtypeFieldsFilterEnabled;
		}

		_subtypeFieldsFilterEnabled = GetterUtil.getBoolean(
			_portletPreferences.getValue(
				"subtypeFieldsFilterEnabled", Boolean.FALSE.toString()));

=======
		String rootPortletId = getRootPortletId();

		if (!AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX ||
			rootPortletId.equals(AssetPublisherPortletKeys.RELATED_ASSETS)) {

			return false;
		}

		return true;
	}

	public boolean isSubtypeFieldsFilterEnabled() {
		if (_subtypeFieldsFilterEnabled == null) {
			_subtypeFieldsFilterEnabled = GetterUtil.getBoolean(
				_portletPreferences.getValue(
					"subtypeFieldsFilterEnabled", Boolean.FALSE.toString()));
		}

>>>>>>> compatible
		return _subtypeFieldsFilterEnabled;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setLayoutAssetEntry(AssetEntry assetEntry)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String defaultAssetPublisherPortletId =
<<<<<<< HEAD
			_assetPublisherWebUtil.getDefaultAssetPublisherId(
				themeDisplay.getLayout());
=======
			AssetUtil.getDefaultAssetPublisherId(themeDisplay.getLayout());
>>>>>>> compatible

		if (isDefaultAssetPublisher() ||
			Validator.isNull(defaultAssetPublisherPortletId) ||
			!PortletPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), themeDisplay.getLayout(),
				defaultAssetPublisherPortletId, ActionKeys.VIEW)) {

			_request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);
		}
	}

	public void setSelectionStyle(String selectionStyle) {
		_selectionStyle = selectionStyle;
	}

	public void setShowContextLink(Boolean showContextLink) {
		_showContextLink = showContextLink;
	}

	protected void configureSubtypeFieldFilter(
			AssetEntryQuery assetEntryQuery, Locale locale)
		throws Exception {

		long[] classNameIds = getClassNameIds();
		long[] classTypeIds = getClassTypeIds();

		if (!isSubtypeFieldsFilterEnabled() || (classNameIds.length != 1) ||
			(classTypeIds.length != 1) ||
			Validator.isNull(getDDMStructureFieldName()) ||
			Validator.isNull(getDDMStructureFieldValue())) {

			return;
		}

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassNameId(classNameIds[0]);

		ClassTypeReader classTypeReader =
			assetRendererFactory.getClassTypeReader();

		ClassType classType = classTypeReader.getClassType(
			classTypeIds[0], locale);

		ClassTypeField classTypeField = classType.getClassTypeField(
			getDDMStructureFieldName());

		assetEntryQuery.setAttribute(
			"ddmStructureFieldName",
<<<<<<< HEAD
			_assetPublisherWebUtil.encodeName(
=======
			AssetPublisherUtil.encodeName(
>>>>>>> compatible
				classTypeField.getClassTypeId(), getDDMStructureFieldName(),
				locale));

		assetEntryQuery.setAttribute(
			"ddmStructureFieldValue", getDDMStructureFieldValue());
	}

	protected void setDDMStructure() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_ddmStructureDisplayFieldValue = StringPool.BLANK;
		_ddmStructureFieldLabel = StringPool.BLANK;
		_ddmStructureFieldName = StringPool.BLANK;
		_ddmStructureFieldValue = null;

		long[] classNameIds = getClassNameIds();
		long[] classTypeIds = getClassTypeIds();

		if (!isSubtypeFieldsFilterEnabled() || (classNameIds.length != 1) ||
			(classTypeIds.length != 1)) {

			return;
		}

		_ddmStructureDisplayFieldValue = ParamUtil.getString(
			_request, "ddmStructureDisplayFieldValue",
			_portletPreferences.getValue(
				"ddmStructureDisplayFieldValue", StringPool.BLANK));
		_ddmStructureFieldName = ParamUtil.getString(
			_request, "ddmStructureFieldName",
			_portletPreferences.getValue(
				"ddmStructureFieldName", StringPool.BLANK));
		_ddmStructureFieldValue = ParamUtil.getString(
			_request, "ddmStructureFieldValue",
			_portletPreferences.getValue(
				"ddmStructureFieldValue", StringPool.BLANK));

		if (Validator.isNotNull(_ddmStructureFieldName) &&
			Validator.isNotNull(_ddmStructureFieldValue)) {

			AssetRendererFactory<?> assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassNameId(classNameIds[0]);

			ClassTypeReader classTypeReader =
				assetRendererFactory.getClassTypeReader();

			ClassType classType = classTypeReader.getClassType(
				classTypeIds[0], themeDisplay.getLocale());

			ClassTypeField classTypeField = classType.getClassTypeField(
				_ddmStructureFieldName);

			_ddmStructureFieldLabel = classTypeField.getLabel();
		}
	}

<<<<<<< HEAD
	private String _getScopeAssetPortletRedirect(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		redirectURL.setParameter(
			"hideDefaultSuccessMessage", Boolean.TRUE.toString());
		redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp");

		PortletURL currentURLObj = PortletURLUtil.getCurrent(
			liferayPortletRequest, liferayPortletResponse);

		redirectURL.setParameter("redirect", currentURLObj.toString());

		redirectURL.setWindowState(LiferayWindowState.POP_UP);

		return redirectURL.toString();
	}

=======
>>>>>>> compatible
	private Integer _abstractLength;
	private long[] _allAssetCategoryIds;
	private String[] _allAssetTagNames;
	private Boolean _anyAssetType;
<<<<<<< HEAD
	private final AssetEntryActionRegistry _assetEntryActionRegistry;
	private AssetEntryQuery _assetEntryQuery;
	private AssetHelper _assetHelper;
	private String _assetLinkBehavior;
	private final AssetPublisherCustomizer _assetPublisherCustomizer;
	private final AssetPublisherPortletInstanceConfiguration
		_assetPublisherPortletInstanceConfiguration;
	private final AssetPublisherWebConfiguration
		_assetPublisherWebConfiguration;
	private final AssetPublisherWebUtil _assetPublisherWebUtil;
=======
	private AssetEntryQuery _assetEntryQuery;
	private String _assetLinkBehavior;
>>>>>>> compatible
	private Map<String, Serializable> _attributes;
	private long[] _availableClassNameIds;
	private long[] _classNameIds;
	private long[] _classTypeIds;
	private Long _companyId;
	private String[] _compilerTagNames;
	private String _ddmStructureDisplayFieldValue;
	private String _ddmStructureFieldLabel;
	private String _ddmStructureFieldName;
	private String _ddmStructureFieldValue;
	private Boolean _defaultAssetPublisher;
<<<<<<< HEAD
=======
	private Integer _delta;
>>>>>>> compatible
	private String _displayStyle;
	private Long _displayStyleGroupId;
	private Boolean _enableCommentRatings;
	private Boolean _enableComments;
	private Boolean _enableConversions;
	private Boolean _enableFlags;
<<<<<<< HEAD
=======
	private Boolean _enablePermissions;
>>>>>>> compatible
	private Boolean _enablePrint;
	private Boolean _enableRatings;
	private Boolean _enableRelatedAssets;
	private Boolean _enableRSS;
<<<<<<< HEAD
=======
	private Boolean _enableSocialBookmarks;
>>>>>>> compatible
	private Boolean _enableTagBasedNavigation;
	private Boolean _enableViewCountIncrement;
	private Boolean _excludeZeroViewCount;
	private String[] _extensions;
	private long[] _groupIds;
	private Layout _layout;
	private Locale _locale;
	private Boolean _mergeURLTags;
	private String[] _metadataFields;
<<<<<<< HEAD
=======
	private Boolean _openOfficeServerEnabled;
>>>>>>> compatible
	private String _orderByColumn1;
	private String _orderByColumn2;
	private String _orderByType1;
	private String _orderByType2;
	private String _paginationType;
	private final PortletPreferences _portletPreferences;
	private final PortletRequest _portletRequest;
	private String _portletResource;
	private final PortletResponse _portletResponse;
	private long[] _referencedModelsGroupIds;
	private final HttpServletRequest _request;
	private String _rootPortletId;
	private Integer _rssDelta;
	private String _rssDisplayStyle;
	private String _rssFeedType;
	private String _rssName;
	private Long _scopeGroupId;
	private String _selectionStyle;
	private Boolean _showAddContentButton;
	private Boolean _showAssetTitle;
	private Boolean _showAvailableLocales;
	private Boolean _showContextLink;
	private Boolean _showExtraInfo;
	private Boolean _showMetadataDescriptions;
	private Boolean _showOnlyLayoutAssets;
<<<<<<< HEAD
	private String _socialBookmarksDisplayStyle;
	private String _socialBookmarksTypes;
=======
	private String _socialBookmarksDisplayPosition;
	private String _socialBookmarksDisplayStyle;
>>>>>>> compatible
	private Boolean _subtypeFieldsFilterEnabled;
	private TimeZone _timeZone;
	private Long _userId;

}