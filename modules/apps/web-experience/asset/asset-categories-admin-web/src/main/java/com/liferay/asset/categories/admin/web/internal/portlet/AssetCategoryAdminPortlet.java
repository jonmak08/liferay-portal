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

package com.liferay.asset.categories.admin.web.internal.portlet;

<<<<<<< HEAD
import com.liferay.asset.categories.admin.web.configuration.AssetCategoriesAdminWebConfiguration;
import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminPortletKeys;
import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminWebKeys;
import com.liferay.asset.category.property.model.AssetCategoryProperty;
import com.liferay.asset.category.property.service.AssetCategoryPropertyLocalService;
=======
import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminPortletKeys;
>>>>>>> compatible
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.NoSuchClassTypeException;
import com.liferay.asset.kernel.exception.AssetCategoryNameException;
import com.liferay.asset.kernel.exception.CategoryPropertyKeyException;
import com.liferay.asset.kernel.exception.CategoryPropertyValueException;
import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.asset.kernel.exception.DuplicateCategoryPropertyException;
import com.liferay.asset.kernel.exception.DuplicateVocabularyException;
import com.liferay.asset.kernel.exception.NoSuchCategoryException;
<<<<<<< HEAD
import com.liferay.asset.kernel.exception.NoSuchEntryException;
=======
>>>>>>> compatible
import com.liferay.asset.kernel.exception.NoSuchVocabularyException;
import com.liferay.asset.kernel.exception.VocabularyNameException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetVocabularyService;
<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringBundler;
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.io.IOException;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> compatible
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

<<<<<<< HEAD
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
=======
import org.osgi.service.component.annotations.Component;
>>>>>>> compatible
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
<<<<<<< HEAD
	configurationPid = "com.liferay.asset.categories.admin.web.configuration.AssetCategoriesAdminWebConfiguration",
=======
>>>>>>> compatible
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-asset-category-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.icon=/icons/asset_category_admin.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Asset Category Admin",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class AssetCategoryAdminPortlet extends MVCPortlet {

	public void changeDisplayStyle(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		hideDefaultSuccessMessage(actionRequest);

		String displayStyle = ParamUtil.getString(
			actionRequest, "displayStyle");

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(actionRequest);

		portalPreferences.setValue(
			AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN,
			"display-style", displayStyle);
	}

	public void deleteCategory(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteCategoryIds = null;

		long categoryId = ParamUtil.getLong(actionRequest, "categoryId");

		if (categoryId > 0) {
			deleteCategoryIds = new long[] {categoryId};
		}
		else {
			deleteCategoryIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		_assetCategoryService.deleteCategories(deleteCategoryIds);
	}

	public void deleteVocabulary(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteVocabularyIds = null;

		long vocabularyId = ParamUtil.getLong(actionRequest, "vocabularyId");

		if (vocabularyId > 0) {
			deleteVocabularyIds = new long[] {vocabularyId};
		}
		else {
			deleteVocabularyIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		for (long deleteVocabularyId : deleteVocabularyIds) {
			_assetVocabularyService.deleteVocabulary(deleteVocabularyId);
		}
	}

	public void editCategory(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long categoryId = ParamUtil.getLong(actionRequest, "categoryId");

		long parentCategoryId = ParamUtil.getLong(
			actionRequest, "parentCategoryId");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		long vocabularyId = ParamUtil.getLong(actionRequest, "vocabularyId");
<<<<<<< HEAD
=======
		String[] categoryProperties = getCategoryProperties(actionRequest);
>>>>>>> compatible

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			AssetCategory.class.getName(), actionRequest);

		if (categoryId <= 0) {

			// Add category

			_assetCategoryService.addCategory(
				serviceContext.getScopeGroupId(), parentCategoryId, titleMap,
<<<<<<< HEAD
				descriptionMap, vocabularyId, null, serviceContext);
=======
				descriptionMap, vocabularyId, categoryProperties,
				serviceContext);
>>>>>>> compatible
		}
		else {

			// Update category

<<<<<<< HEAD
			List<AssetCategoryProperty> categoryProperties =
				_assetCategoryPropertyLocalService.getCategoryProperties(
					categoryId);

			String[] categoryPropertiesArray = getCategoryProperties(
				categoryProperties);

			_assetCategoryService.updateCategory(
				categoryId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryPropertiesArray, serviceContext);
		}
	}

	public void editProperties(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long categoryId = ParamUtil.getLong(actionRequest, "categoryId", 0);

		AssetCategory category = _assetCategoryService.fetchCategory(
			categoryId);

		String[] categoryProperties = getCategoryProperties(actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			AssetCategory.class.getName(), actionRequest);

		_assetCategoryService.updateCategory(
			categoryId, category.getParentCategoryId(), category.getTitleMap(),
			category.getDescriptionMap(), category.getVocabularyId(),
			categoryProperties, serviceContext);
	}

=======
			_assetCategoryService.updateCategory(
				categoryId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext);
		}
	}

>>>>>>> compatible
	public void editVocabulary(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long vocabularyId = ParamUtil.getLong(actionRequest, "vocabularyId");

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			AssetVocabulary.class.getName(), actionRequest);

		if (vocabularyId <= 0) {

			// Add vocabulary

			_assetVocabularyService.addVocabulary(
				serviceContext.getScopeGroupId(), StringPool.BLANK, titleMap,
				descriptionMap, getSettings(actionRequest), serviceContext);
		}
		else {

			// Update vocabulary

			_assetVocabularyService.updateVocabulary(
				vocabularyId, StringPool.BLANK, titleMap, descriptionMap,
				getSettings(actionRequest), serviceContext);
		}
	}

	public void moveCategory(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long categoryId = ParamUtil.getLong(actionRequest, "categoryId");

		long parentCategoryId = ParamUtil.getLong(
			actionRequest, "parentCategoryId");
		long vocabularyId = ParamUtil.getLong(actionRequest, "vocabularyId");

<<<<<<< HEAD
		if ((vocabularyId <= 0) && (parentCategoryId <= 0)) {
			throw new NoSuchVocabularyException();
		}

		if (vocabularyId <= 0) {
			AssetCategory parentCategory = _assetCategoryService.fetchCategory(
				parentCategoryId);

			vocabularyId = parentCategory.getVocabularyId();
		}

=======
>>>>>>> compatible
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			AssetCategory.class.getName(), actionRequest);

		_assetCategoryService.moveCategory(
			categoryId, parentCategoryId, vocabularyId, serviceContext);
	}

<<<<<<< HEAD
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_assetCategoriesAdminWebConfiguration =
			ConfigurableUtil.createConfigurable(
				AssetCategoriesAdminWebConfiguration.class, properties);
	}

=======
>>>>>>> compatible
	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchCategoryException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchVocabularyException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include("/error.jsp", renderRequest, renderResponse);
<<<<<<< HEAD

			return;
		}

		renderRequest.setAttribute(
			AssetCategoriesAdminWebKeys.ASSET_CATEGORIES_ADMIN_CONFIGURATION,
			_assetCategoriesAdminWebConfiguration);

		super.doDispatch(renderRequest, renderResponse);
=======
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
>>>>>>> compatible
	}

	protected String[] getCategoryProperties(ActionRequest actionRequest) {
		int[] categoryPropertiesIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "categoryPropertiesIndexes"), 0);

		String[] categoryProperties =
			new String[categoryPropertiesIndexes.length];

		for (int i = 0; i < categoryPropertiesIndexes.length; i++) {
			int categoryPropertiesIndex = categoryPropertiesIndexes[i];

			String key = ParamUtil.getString(
				actionRequest, "key" + categoryPropertiesIndex);

			if (Validator.isNull(key)) {
				continue;
			}

			String value = ParamUtil.getString(
				actionRequest, "value" + categoryPropertiesIndex);

			categoryProperties[i] =
				key + AssetCategoryConstants.PROPERTY_KEY_VALUE_SEPARATOR +
					value;
		}

		return categoryProperties;
	}

<<<<<<< HEAD
	protected String[] getCategoryProperties(
		List<AssetCategoryProperty> categoryProperties) {

		String[] categoryPropertiesArray =
			new String[categoryProperties.size()];

		for (int i = 0; i < categoryProperties.size(); i++) {
			AssetCategoryProperty categoryProperty = categoryProperties.get(i);

			categoryPropertiesArray[i] =
				categoryProperty.getKey() +
					AssetCategoryConstants.PROPERTY_KEY_VALUE_SEPARATOR +
						categoryProperty.getValue();
		}

		return categoryPropertiesArray;
	}

=======
>>>>>>> compatible
	protected String getSettings(ActionRequest actionRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetVocabularySettingsHelper vocabularySettingsHelper =
			new AssetVocabularySettingsHelper();

		int[] indexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "indexes"), 0);

		long[] classNameIds = new long[indexes.length];
		long[] classTypePKs = new long[indexes.length];
		boolean[] requireds = new boolean[indexes.length];

		for (int i = 0; i < indexes.length; i++) {
			int index = indexes[i];

			classNameIds[i] = ParamUtil.getLong(
				actionRequest, "classNameId" + index);

			classTypePKs[i] = ParamUtil.getLong(
				actionRequest,
<<<<<<< HEAD
				StringBundler.concat(
					"subtype", String.valueOf(classNameIds[i]), "-classNameId",
					String.valueOf(index)),
=======
				"subtype" + classNameIds[i] + "-classNameId" + index,
>>>>>>> compatible
				AssetCategoryConstants.ALL_CLASS_TYPE_PK);

			if (classTypePKs[i] != -1) {
				AssetRendererFactory<?> assetRendererFactory =
					AssetRendererFactoryRegistryUtil.
						getAssetRendererFactoryByClassNameId(classNameIds[i]);

				ClassTypeReader classTypeReader =
					assetRendererFactory.getClassTypeReader();

				try {
					classTypeReader.getClassType(
						classTypePKs[i], themeDisplay.getLocale());
				}
				catch (NoSuchModelException nsme) {
					throw new NoSuchClassTypeException(nsme);
				}
			}

			requireds[i] = ParamUtil.getBoolean(
				actionRequest, "required" + index);
		}

		vocabularySettingsHelper.setClassNameIdsAndClassTypePKs(
			classNameIds, classTypePKs, requireds);

		boolean multiValued = ParamUtil.getBoolean(
			actionRequest, "multiValued");

		vocabularySettingsHelper.setMultiValued(multiValued);

		return vocabularySettingsHelper.toString();
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AssetCategoryNameException ||
			cause instanceof CategoryPropertyKeyException ||
			cause instanceof CategoryPropertyValueException ||
			cause instanceof DuplicateCategoryException ||
			cause instanceof DuplicateCategoryPropertyException ||
			cause instanceof DuplicateVocabularyException ||
			cause instanceof NoSuchCategoryException ||
			cause instanceof NoSuchClassTypeException ||
<<<<<<< HEAD
			cause instanceof NoSuchEntryException ||
=======
>>>>>>> compatible
			cause instanceof NoSuchVocabularyException ||
			cause instanceof PrincipalException ||
			cause instanceof VocabularyNameException) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setAssetCategoryService(
		AssetCategoryService assetCategoryService) {

		_assetCategoryService = assetCategoryService;
	}

	@Reference(unbind = "-")
	protected void setAssetVocabularyService(
		AssetVocabularyService assetVocabularyService) {

		_assetVocabularyService = assetVocabularyService;
	}

<<<<<<< HEAD
	private AssetCategoriesAdminWebConfiguration
		_assetCategoriesAdminWebConfiguration;

	@Reference
	private AssetCategoryPropertyLocalService
		_assetCategoryPropertyLocalService;

=======
>>>>>>> compatible
	private AssetCategoryService _assetCategoryService;
	private AssetVocabularyService _assetVocabularyService;

}