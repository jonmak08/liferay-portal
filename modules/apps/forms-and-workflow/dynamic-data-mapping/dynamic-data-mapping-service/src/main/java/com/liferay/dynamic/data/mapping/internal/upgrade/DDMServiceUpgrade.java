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

package com.liferay.dynamic.data.mapping.internal.upgrade;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderTracker;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeCompanyId;
=======
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeCompanyId;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeDynamicDataMapping;
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeKernelPackage;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeLastPublishDate;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.UpgradeSchema;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_1.UpgradeResourcePermission;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_2.UpgradeDDMTemplateSmallImageURL;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_0.UpgradeCheckboxFieldToCheckboxMultipleField;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_1.UpgradeDDMFormFieldSettings;
=======
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormXSDDeserializer;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.expando.kernel.service.ExpandoRowLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
<<<<<<< HEAD
import com.liferay.portal.kernel.json.JSONFactory;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	service = {DDMServiceUpgrade.class, UpgradeStepRegistrator.class}
)
public class DDMServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.dynamic.data.mapping.service", "0.0.1", "0.0.2",
			new UpgradeSchema());

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "0.0.2", "0.0.3",
			new UpgradeKernelPackage());

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "0.0.3", "1.0.0",
			new UpgradeCompanyId(),
<<<<<<< HEAD
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.
				UpgradeDynamicDataMapping(
					_assetEntryLocalService, _ddm, _ddmFormJSONDeserializer,
					_ddmFormJSONSerializer, _ddmFormLayoutJSONSerializer,
					_ddmFormValuesJSONDeserializer,
					_ddmFormValuesJSONSerializer, _ddmFormXSDDeserializer,
					_dlFileEntryLocalService, _dlFileVersionLocalService,
					_dlFolderLocalService, _expandoRowLocalService,
					_expandoTableLocalService, _expandoValueLocalService,
					_resourceActions, _resourceLocalService,
					_resourcePermissionLocalService),
=======
			new UpgradeDynamicDataMapping(
				_assetEntryLocalService, _ddm, _ddmFormJSONDeserializer,
				_ddmFormJSONSerializer, _ddmFormLayoutJSONSerializer,
				_ddmFormValuesJSONDeserializer, _ddmFormValuesJSONSerializer,
				_ddmFormXSDDeserializer, _dlFileEntryLocalService,
				_dlFileVersionLocalService, _dlFolderLocalService,
				_expandoRowLocalService, _expandoTableLocalService,
				_expandoValueLocalService, _resourceActions,
				_resourceLocalService, _resourcePermissionLocalService),
>>>>>>> compatible
			new UpgradeLastPublishDate());

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.0.0", "1.0.1",
			new UpgradeResourcePermission(_resourceActions));

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.0.1", "1.0.2",
			new UpgradeDDMTemplateSmallImageURL());
<<<<<<< HEAD

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.0.2", "1.1.0",
			new UpgradeCheckboxFieldToCheckboxMultipleField(
				_ddmFormJSONDeserializer, _ddmFormValuesJSONDeserializer,
				_ddmFormValuesJSONSerializer, _jsonFactory),
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_0.
				UpgradeDDMStructure(
					_ddmExpressionFactory, _ddmFormJSONDeserializer,
					_ddmFormJSONSerializer),
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_0.
				UpgradeDataProviderInstance(_jsonFactory));

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.1.0", "1.1.1",
			new UpgradeDDMFormFieldSettings(
				_ddmFormJSONDeserializer, _ddmFormJSONSerializer),
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_1.
				UpgradeDataProviderInstance(
					_ddmDataProviderTracker, _ddmFormValuesJSONDeserializer,
					_ddmFormValuesJSONSerializer));

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.1.1", "1.1.2",
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_2.
				UpgradeDynamicDataMapping(
					_ddmFormJSONDeserializer, _ddmFormJSONSerializer,
					_ddmFormValuesJSONDeserializer,
					_ddmFormValuesJSONSerializer, _jsonFactory));

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.1.2", "1.1.3",
			new com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_3.
				UpgradeDDMStorageLink());

		registry.register(
			"com.liferay.dynamic.data.mapping.service", "1.1.3", "2.0.0",
			new com.liferay.dynamic.data.mapping.internal.upgrade.v2_0_0.
				UpgradeSchema());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private DDM _ddm;

	@Reference
	private DDMDataProviderTracker _ddmDataProviderTracker;

	@Reference
	private DDMExpressionFactory _ddmExpressionFactory;

	@Reference
	private DDMFormJSONDeserializer _ddmFormJSONDeserializer;

	@Reference
	private DDMFormJSONSerializer _ddmFormJSONSerializer;

	@Reference
	private DDMFormLayoutJSONSerializer _ddmFormLayoutJSONSerializer;

	@Reference
	private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;

	@Reference
	private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;

	@Reference
	private DDMFormXSDDeserializer _ddmFormXSDDeserializer;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileVersionLocalService _dlFileVersionLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

	@Reference
	private ExpandoRowLocalService _expandoRowLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ResourceActions _resourceActions;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
=======
	}

	@Reference(unbind = "-")
	public void setExpandoRowLocalService(
		ExpandoRowLocalService expandoRowLocalService) {

		_expandoRowLocalService = expandoRowLocalService;
	}

	@Reference(unbind = "-")
	public void setExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		_expandoTableLocalService = expandoTableLocalService;
	}

	@Reference(unbind = "-")
	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		_expandoValueLocalService = expandoValueLocalService;
	}

	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDM(DDM ddm) {
		_ddm = ddm;
	}

	@Reference(unbind = "-")
	protected void setDDMFormJSONDeserializer(
		DDMFormJSONDeserializer ddmFormJSONDeserializer) {

		_ddmFormJSONDeserializer = ddmFormJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormJSONSerializer(
		DDMFormJSONSerializer ddmFormJSONSerializer) {

		_ddmFormJSONSerializer = ddmFormJSONSerializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormLayoutJSONSerializer(
		DDMFormLayoutJSONSerializer ddmFormLayoutJSONSerializer) {

		_ddmFormLayoutJSONSerializer = ddmFormLayoutJSONSerializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONDeserializer(
		DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer) {

		_ddmFormValuesJSONDeserializer = ddmFormValuesJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONSerializer(
		DDMFormValuesJSONSerializer ddmFormValuesJSONSerializer) {

		_ddmFormValuesJSONSerializer = ddmFormValuesJSONSerializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormXSDDeserializer(
		DDMFormXSDDeserializer ddmFormXSDDeserializer) {

		_ddmFormXSDDeserializer = ddmFormXSDDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(
		DLFileEntryLocalService dlFileEntryLocalService) {

		_dlFileEntryLocalService = dlFileEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setDlFileVersionLocalService(
		DLFileVersionLocalService dlFileVersionLocalService) {

		_dlFileVersionLocalService = dlFileVersionLocalService;
	}

	@Reference(unbind = "-")
	protected void setDlFolderLocalService(
		DLFolderLocalService dlFolderLocalService) {

		_dlFolderLocalService = dlFolderLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourceActions(ResourceActions resourceActions) {
		_resourceActions = resourceActions;
	}

	@Reference(unbind = "-")
	protected void setResourceLocalService(
		ResourceLocalService resourceLocalService) {

		_resourceLocalService = resourceLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	private AssetEntryLocalService _assetEntryLocalService;
	private DDM _ddm;
	private DDMFormJSONDeserializer _ddmFormJSONDeserializer;
	private DDMFormJSONSerializer _ddmFormJSONSerializer;
	private DDMFormLayoutJSONSerializer _ddmFormLayoutJSONSerializer;
	private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;
	private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;
	private DDMFormXSDDeserializer _ddmFormXSDDeserializer;
	private DLFileEntryLocalService _dlFileEntryLocalService;
	private DLFileVersionLocalService _dlFileVersionLocalService;
	private DLFolderLocalService _dlFolderLocalService;
	private ExpandoRowLocalService _expandoRowLocalService;
	private ExpandoTableLocalService _expandoTableLocalService;
	private ExpandoValueLocalService _expandoValueLocalService;
	private ResourceActions _resourceActions;
	private ResourceLocalService _resourceLocalService;
>>>>>>> compatible
	private ResourcePermissionLocalService _resourcePermissionLocalService;

}