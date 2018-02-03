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

package com.liferay.exportimport.controller;

import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_IMPORT_FAILED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_IMPORT_STARTED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_IMPORT_SUCCEEDED;
<<<<<<< HEAD
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_IMPORT_IN_PROCESS;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.adapter.StagedAssetLink;
import com.liferay.exportimport.constants.ExportImportConstants;
=======
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_IMPORT_FAILED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_IMPORT_STARTED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_IMPORT_SUCCEEDED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_IMPORT_IN_PROCESS;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;

>>>>>>> compatible
import com.liferay.exportimport.kernel.controller.ExportImportController;
import com.liferay.exportimport.kernel.controller.ImportController;
import com.liferay.exportimport.kernel.exception.LARFileException;
import com.liferay.exportimport.kernel.exception.LARTypeException;
import com.liferay.exportimport.kernel.exception.LayoutImportException;
import com.liferay.exportimport.kernel.exception.MissingReferenceException;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.MissingReference;
import com.liferay.exportimport.kernel.lar.MissingReferences;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataContextFactory;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
<<<<<<< HEAD
=======
import com.liferay.exportimport.kernel.lar.PortletDataHandlerStatusMessageSender;
>>>>>>> compatible
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.lar.UserIdStrategy;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleManager;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.lar.DeletionSystemEventImporter;
import com.liferay.exportimport.lar.LayoutCache;
import com.liferay.exportimport.lar.PermissionImporter;
<<<<<<< HEAD
import com.liferay.exportimport.portlet.data.handler.provider.PortletDataHandlerProvider;
import com.liferay.portal.kernel.exception.LayoutPrototypeException;
import com.liferay.portal.kernel.exception.LocaleException;
=======
import com.liferay.exportimport.lar.ThemeImporter;
import com.liferay.exportimport.portlet.data.handler.provider.PortletDataHandlerProvider;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.exception.LayoutPrototypeException;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
>>>>>>> compatible
import com.liferay.portal.kernel.exception.NoSuchLayoutPrototypeException;
import com.liferay.portal.kernel.exception.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.plugin.Version;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.ArrayUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.site.model.adapter.StagedGroup;
=======
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.sites.kernel.util.Sites;
import com.liferay.sites.kernel.util.SitesUtil;
>>>>>>> compatible

import java.io.File;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.HashSet;
>>>>>>> compatible
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
<<<<<<< HEAD
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import org.apache.commons.lang.time.StopWatch;

=======
import java.util.Set;
import java.util.function.BiPredicate;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
>>>>>>> compatible
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Joel Kozikowski
 * @author Charles May
 * @author Raymond Augé
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Wesley Gong
 * @author Zsigmond Rab
 * @author Douglas Wong
 * @author Julio Camarero
 * @author Zsolt Berentey
 */
@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.portal.kernel.model.Layout"},
	service = {ExportImportController.class, LayoutImportController.class}
)
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public class LayoutImportController implements ImportController {

	@Override
	public void importDataDeletions(
			ExportImportConfiguration exportImportConfiguration, File file)
		throws Exception {

		ZipReader zipReader = null;

		try {

			// LAR validation

			ExportImportThreadLocal.setLayoutDataDeletionImportInProcess(true);

			Map<String, Serializable> settingsMap =
				exportImportConfiguration.getSettingsMap();

			long targetGroupId = MapUtil.getLong(settingsMap, "targetGroupId");
			boolean privateLayout = MapUtil.getBoolean(
				settingsMap, "privateLayout");
			Map<String, String[]> parameterMap =
				(Map<String, String[]>)settingsMap.get("parameterMap");

			LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
				targetGroupId, privateLayout);

			zipReader = ZipReaderFactoryUtil.getZipReader(file);

			validateFile(
				layoutSet.getCompanyId(), targetGroupId, parameterMap,
				zipReader);

			PortletDataContext portletDataContext = getPortletDataContext(
				exportImportConfiguration, file);

			boolean deletePortletData = MapUtil.getBoolean(
				parameterMap, PortletDataHandlerKeys.DELETE_PORTLET_DATA);

			// Portlet data deletion

			if (deletePortletData) {
				if (_log.isDebugEnabled()) {
					_log.debug("Deleting portlet data");
				}

				deletePortletData(portletDataContext);
			}

			// Deletion system events

			populateDeletionStagedModelTypes(portletDataContext);

			_deletionSystemEventImporter.importDeletionSystemEvents(
				portletDataContext);
		}
		finally {
			ExportImportThreadLocal.setLayoutDataDeletionImportInProcess(false);

			if (zipReader != null) {
				zipReader.close();
			}
		}
	}

	@Override
	public void importFile(
			ExportImportConfiguration exportImportConfiguration, File file)
		throws Exception {

		PortletDataContext portletDataContext = null;

		try {
			ExportImportThreadLocal.setLayoutImportInProcess(true);

			portletDataContext = getPortletDataContext(
				exportImportConfiguration, file);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_IMPORT_STARTED, getProcessFlag(),
<<<<<<< HEAD
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
=======
				portletDataContext.getExportImportProcessId(),
>>>>>>> compatible
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext));

			Map<String, Serializable> settingsMap =
				exportImportConfiguration.getSettingsMap();

			long userId = MapUtil.getLong(settingsMap, "userId");

			doImportFile(portletDataContext, userId);

			ExportImportThreadLocal.setLayoutImportInProcess(false);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_IMPORT_SUCCEEDED, getProcessFlag(),
<<<<<<< HEAD
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
=======
				portletDataContext.getExportImportProcessId(),
>>>>>>> compatible
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext),
				userId);
		}
		catch (Throwable t) {
			ExportImportThreadLocal.setLayoutImportInProcess(false);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_IMPORT_FAILED, getProcessFlag(),
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext),
				t);

			throw t;
		}
	}

	@Override
	public MissingReferences validateFile(
			ExportImportConfiguration exportImportConfiguration, File file)
		throws Exception {

		ZipReader zipReader = null;

		try {
			ExportImportThreadLocal.setLayoutValidationInProcess(true);

			Map<String, Serializable> settingsMap =
				exportImportConfiguration.getSettingsMap();

			long targetGroupId = MapUtil.getLong(settingsMap, "targetGroupId");
			boolean privateLayout = MapUtil.getBoolean(
				settingsMap, "privateLayout");
			Map<String, String[]> parameterMap =
				(Map<String, String[]>)settingsMap.get("parameterMap");

			LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
				targetGroupId, privateLayout);

			zipReader = ZipReaderFactoryUtil.getZipReader(file);

			validateFile(
				layoutSet.getCompanyId(), targetGroupId, parameterMap,
				zipReader);

			PortletDataContext portletDataContext = getPortletDataContext(
				exportImportConfiguration, file);

			portletDataContext.setPrivateLayout(privateLayout);

			MissingReferences missingReferences =
				_exportImportHelper.validateMissingReferences(
					portletDataContext);

			Map<String, MissingReference> dependencyMissingReferences =
				missingReferences.getDependencyMissingReferences();

			if (!dependencyMissingReferences.isEmpty()) {
				throw new MissingReferenceException(missingReferences);
			}

			return missingReferences;
		}
		finally {
			ExportImportThreadLocal.setLayoutValidationInProcess(false);

			if (zipReader != null) {
				zipReader.close();
			}
		}
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
=======
>>>>>>> compatible
	protected void deleteMissingLayouts(
			PortletDataContext portletDataContext,
			List<String> sourceLayoutUuids, List<Layout> previousLayouts,
			ServiceContext serviceContext)
		throws Exception {
<<<<<<< HEAD
=======

		if (_log.isDebugEnabled() && !sourceLayoutUuids.isEmpty()) {
			_log.debug("Delete missing layouts");
		}

		Map<Long, Long> layoutPlids =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class);

		for (Layout layout : previousLayouts) {
			if (!sourceLayoutUuids.contains(layout.getUuid()) &&
				!layoutPlids.containsValue(layout.getPlid())) {

				try {
					_layoutLocalService.deleteLayout(
						layout, false, serviceContext);
				}
				catch (NoSuchLayoutException nsle) {

					// LPS-52675

					if (_log.isDebugEnabled()) {
						_log.debug(nsle, nsle);
					}
				}
			}
		}
>>>>>>> compatible
	}

	protected void deletePortletData(PortletDataContext portletDataContext)
		throws Exception {

<<<<<<< HEAD
		List<Element> portletElements = fetchPortletElements(
			portletDataContext.getImportDataRootElement());
=======
		Element rootElement = portletDataContext.getImportDataRootElement();

		Element portletsElement = rootElement.element("portlets");

		List<Element> portletElements = portletsElement.elements("portlet");
>>>>>>> compatible

		Map<Long, Layout> layouts =
			(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class + ".layout");

		if (_log.isDebugEnabled()) {
			if (!portletElements.isEmpty()) {
				_log.debug("Deleting portlet data");
			}
		}

		for (Element portletElement : portletElements) {
			long layoutId = GetterUtil.getLong(
				portletElement.attributeValue("layout-id"));

			long plid = LayoutConstants.DEFAULT_PLID;

			Layout layout = layouts.get(layoutId);

			if (layout != null) {
				plid = layout.getPlid();
			}

			portletDataContext.setPlid(plid);
			portletDataContext.setPortletId(
				portletElement.attributeValue("portlet-id"));

			_portletImportController.deletePortletData(portletDataContext);
		}
	}

	protected void doImportFile(
			PortletDataContext portletDataContext, long userId)
		throws Exception {

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

<<<<<<< HEAD
		Group group = _groupLocalService.getGroup(
			portletDataContext.getGroupId());

		String layoutsImportMode = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID);
		boolean permissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);

		if (group.isLayoutSetPrototype()) {
			parameterMap.put(
				PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED,
				new String[] {Boolean.FALSE.toString()});
		}

=======
		boolean deleteMissingLayouts = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			Boolean.TRUE.booleanValue());

		boolean layoutSetPrototypeLinkEnabled = MapUtil.getBoolean(
			parameterMap,
			PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED);

		Group group = _groupLocalService.getGroup(
			portletDataContext.getGroupId());

		if (group.isLayoutSetPrototype()) {
			layoutSetPrototypeLinkEnabled = false;
		}

		boolean layoutSetPrototypeSettings = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_SETTINGS);
		boolean layoutSetSettings = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LAYOUT_SET_SETTINGS);
		String layoutsImportMode = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID);
		boolean logo = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LOGO);
		boolean permissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);

>>>>>>> compatible
		if (_log.isDebugEnabled()) {
			_log.debug("Import permissions " + permissions);
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		LayoutCache layoutCache = new LayoutCache();

<<<<<<< HEAD
		long companyId = portletDataContext.getCompanyId();
=======
		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

		long companyId = layoutSet.getCompanyId();
>>>>>>> compatible

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		serviceContext.setCompanyId(companyId);
		serviceContext.setSignedIn(false);
		serviceContext.setUserId(userId);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		// LAR validation

		validateFile(
			companyId, portletDataContext.getGroupId(), parameterMap,
			portletDataContext.getZipReader());

		// Source and target group id

		Map<Long, Long> groupIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Group.class);

		groupIds.put(
			portletDataContext.getSourceGroupId(),
			portletDataContext.getGroupId());

		// Manifest

		ManifestSummary manifestSummary =
			_exportImportHelper.getManifestSummary(portletDataContext);

		portletDataContext.setManifestSummary(manifestSummary);

		// Layout and layout set prototype

		Element rootElement = portletDataContext.getImportDataRootElement();

		Element headerElement = rootElement.element("header");

		String layoutSetPrototypeUuid = headerElement.attributeValue(
			"layout-set-prototype-uuid");

		String larType = headerElement.attributeValue("type");

<<<<<<< HEAD
		portletDataContext.setType(larType);

		if (group.isLayoutPrototype() && larType.equals("layout-prototype")) {
			parameterMap.put(
				PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
				new String[] {Boolean.FALSE.toString()});
=======
		if (group.isLayoutPrototype() && larType.equals("layout-prototype")) {
			deleteMissingLayouts = false;
>>>>>>> compatible

			LayoutPrototype layoutPrototype =
				_layoutPrototypeLocalService.getLayoutPrototype(
					group.getClassPK());

			String layoutPrototypeUuid = GetterUtil.getString(
				headerElement.attributeValue("type-uuid"));

			LayoutPrototype existingLayoutPrototype = null;

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				try {
					existingLayoutPrototype =
						_layoutPrototypeLocalService.
							getLayoutPrototypeByUuidAndCompanyId(
								layoutPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutPrototypeException nslpe) {

					// LPS-52675

					if (_log.isDebugEnabled()) {
						_log.debug(nslpe, nslpe);
					}
				}
			}

			if (existingLayoutPrototype == null) {
				List<Layout> layouts =
					_layoutLocalService.getLayoutsByLayoutPrototypeUuid(
						layoutPrototype.getUuid());

				layoutPrototype.setUuid(layoutPrototypeUuid);

				_layoutPrototypeLocalService.updateLayoutPrototype(
					layoutPrototype);

				for (Layout layout : layouts) {
					layout.setLayoutPrototypeUuid(layoutPrototypeUuid);

					_layoutLocalService.updateLayout(layout);
				}
			}
		}
		else if (group.isLayoutSetPrototype() &&
				 larType.equals("layout-set-prototype")) {

<<<<<<< HEAD
			parameterMap.put(
				PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_SETTINGS,
				new String[] {Boolean.TRUE.toString()});

=======
>>>>>>> compatible
			LayoutSetPrototype layoutSetPrototype =
				_layoutSetPrototypeLocalService.getLayoutSetPrototype(
					group.getClassPK());

			String importedLayoutSetPrototypeUuid = GetterUtil.getString(
				headerElement.attributeValue("type-uuid"));

			LayoutSetPrototype existingLayoutSetPrototype = null;

			if (Validator.isNotNull(importedLayoutSetPrototypeUuid)) {
				try {
					existingLayoutSetPrototype =
						_layoutSetPrototypeLocalService.
							getLayoutSetPrototypeByUuidAndCompanyId(
								importedLayoutSetPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutSetPrototypeException nslspe) {

					// LPS-52675

					if (_log.isDebugEnabled()) {
						_log.debug(nslspe, nslspe);
					}
				}
			}

			if (existingLayoutSetPrototype == null) {
				List<LayoutSet> layoutSets =
					_layoutSetLocalService.
						getLayoutSetsByLayoutSetPrototypeUuid(
							layoutSetPrototype.getUuid());

				layoutSetPrototype.setUuid(importedLayoutSetPrototypeUuid);

				_layoutSetPrototypeLocalService.updateLayoutSetPrototype(
					layoutSetPrototype);

				for (LayoutSet curLayoutSet : layoutSets) {
					curLayoutSet.setLayoutSetPrototypeUuid(
						importedLayoutSetPrototypeUuid);

					_layoutSetLocalService.updateLayoutSet(curLayoutSet);
				}
			}
		}
		else if (larType.equals("layout-set-prototype")) {
<<<<<<< HEAD
			parameterMap.put(
				PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_SETTINGS,
				new String[] {Boolean.TRUE.toString()});
=======
			layoutSetPrototypeSettings = true;
>>>>>>> compatible

			layoutSetPrototypeUuid = GetterUtil.getString(
				headerElement.attributeValue("type-uuid"));
		}

<<<<<<< HEAD
		if (Validator.isNotNull(layoutSetPrototypeUuid)) {
			portletDataContext.setLayoutSetPrototypeUuid(
				layoutSetPrototypeUuid);
		}

		List<Element> portletElements = fetchPortletElements(rootElement);
=======
		if (layoutSetPrototypeSettings &&
			Validator.isNotNull(layoutSetPrototypeUuid)) {

			layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
			layoutSet.setLayoutSetPrototypeLinkEnabled(
				layoutSetPrototypeLinkEnabled);

			_layoutSetLocalService.updateLayoutSet(layoutSet);
		}

		// Look and feel

		if (logo) {
			String logoPath = headerElement.attributeValue("logo-path");

			byte[] iconBytes = portletDataContext.getZipEntryAsByteArray(
				logoPath);

			if (ArrayUtil.isNotEmpty(iconBytes)) {
				_layoutSetLocalService.updateLogo(
					portletDataContext.getGroupId(),
					portletDataContext.isPrivateLayout(), true, iconBytes);
			}
			else {
				_layoutSetLocalService.updateLogo(
					portletDataContext.getGroupId(),
					portletDataContext.isPrivateLayout(), false, (File)null);
			}
		}

		_themeImporter.importTheme(portletDataContext, layoutSet);

		if (layoutSetSettings) {
			String settings = GetterUtil.getString(
				headerElement.elementText("settings"));

			_layoutSetLocalService.updateSettings(
				portletDataContext.getGroupId(),
				portletDataContext.isPrivateLayout(), settings);
		}

		Element portletsElement = rootElement.element("portlets");

		List<Element> portletElements = portletsElement.elements("portlet");

		if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
			List<String> portletIds = new ArrayList<>();

			for (Element portletElement : portletElements) {
				String portletId = portletElement.attributeValue("portlet-id");

				Portlet portlet = _portletLocalService.getPortletById(
					portletDataContext.getCompanyId(), portletId);

				if (!portlet.isActive() || portlet.isUndeployedPortlet()) {
					continue;
				}

				portletIds.add(portletId);
			}

			_portletDataHandlerStatusMessageSender.sendStatusMessage(
				"layout", ArrayUtil.toStringArray(portletIds), manifestSummary);
		}

		// Read expando tables, locks, and permissions to make them
		// available to the data handlers through the portlet data context
>>>>>>> compatible

		if (permissions) {
			for (Element portletElement : portletElements) {
				String portletPath = portletElement.attributeValue("path");

				Document portletDocument = SAXReaderUtil.read(
					portletDataContext.getZipEntryAsString(portletPath));

				_permissionImporter.checkRoles(
					layoutCache, companyId, portletDataContext.getGroupId(),
					userId, portletDocument.getRootElement());
			}

			_permissionImporter.readPortletDataPermissions(portletDataContext);
		}

		if (!layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			_portletImportController.readExpandoTables(portletDataContext);
		}

		_portletImportController.readLocks(portletDataContext);

<<<<<<< HEAD
		// Import the group

		Element groupsElement = portletDataContext.getImportDataGroupElement(
			StagedGroup.class);

		for (Element groupElement : groupsElement.elements()) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, groupElement);
=======
		// Layouts

		Set<Layout> modifiedLayouts = new HashSet<>();
		List<Layout> previousLayouts = _layoutLocalService.getLayouts(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

		// Remove layouts that were deleted from the layout set prototype

		if (Validator.isNotNull(layoutSetPrototypeUuid) &&
			layoutSetPrototypeLinkEnabled) {

			LayoutSetPrototype layoutSetPrototype =
				_layoutSetPrototypeLocalService.
					getLayoutSetPrototypeByUuidAndCompanyId(
						layoutSetPrototypeUuid, companyId);

			for (Layout layout : previousLayouts) {
				String sourcePrototypeLayoutUuid =
					layout.getSourcePrototypeLayoutUuid();

				if (Validator.isNull(layout.getSourcePrototypeLayoutUuid())) {
					continue;
				}

				if (SitesUtil.isLayoutModifiedSinceLastMerge(layout)) {
					modifiedLayouts.add(layout);

					continue;
				}

				Layout sourcePrototypeLayout = _layoutLocalService.fetchLayout(
					sourcePrototypeLayoutUuid, layoutSetPrototype.getGroupId(),
					true);

				if (sourcePrototypeLayout == null) {
					_layoutLocalService.deleteLayout(
						layout, false, serviceContext);
				}
			}
		}

		Element layoutsElement = portletDataContext.getImportDataGroupElement(
			Layout.class);

		List<Element> layoutElements = layoutsElement.elements();

		if (_log.isDebugEnabled()) {
			if (!layoutElements.isEmpty()) {
				_log.debug("Importing layouts");
			}
		}

		List<String> sourceLayoutsUuids = new ArrayList<>();

		for (Element layoutElement : layoutElements) {
			importLayout(portletDataContext, sourceLayoutsUuids, layoutElement);
		}

		// Import portlets

		if (_log.isDebugEnabled()) {
			if (!portletElements.isEmpty()) {
				_log.debug("Importing portlets");
			}
		}

		Map<Long, Layout> layouts =
			(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class + ".layout");

		for (Element portletElement : portletElements) {
			String portletPath = portletElement.attributeValue("path");
			String portletId = portletElement.attributeValue("portlet-id");
			long layoutId = GetterUtil.getLong(
				portletElement.attributeValue("layout-id"));
			long oldPlid = GetterUtil.getLong(
				portletElement.attributeValue("old-plid"));

			Portlet portlet = _portletLocalService.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			if (!portlet.isActive() || portlet.isUndeployedPortlet()) {
				continue;
			}

			Layout layout = layouts.get(layoutId);

			long plid = LayoutConstants.DEFAULT_PLID;

			if (layout != null) {
				plid = layout.getPlid();

				if (modifiedLayouts.contains(layout)) {
					continue;
				}
			}

			portletDataContext.setPlid(plid);
			portletDataContext.setOldPlid(oldPlid);
			portletDataContext.setPortletId(portletId);

			if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
				_portletDataHandlerStatusMessageSender.sendStatusMessage(
					"portlet", portletId, manifestSummary);
			}

			Document portletDocument = SAXReaderUtil.read(
				portletDataContext.getZipEntryAsString(portletPath));

			portletElement = portletDocument.getRootElement();

			// The order of the import is important. You must always import the
			// portlet preferences first, then the portlet data, then the
			// portlet permissions. The import of the portlet data assumes that
			// portlet preferences already exist.

			setPortletScope(portletDataContext, portletElement);

			long portletPreferencesGroupId = portletDataContext.getGroupId();

			Element portletDataElement = portletElement.element("portlet-data");

			Map<String, Boolean> importPortletControlsMap =
				_exportImportHelper.getImportPortletControlsMap(
					companyId, portletId, parameterMap, portletDataElement,
					manifestSummary);

			if (layout != null) {
				portletPreferencesGroupId = layout.getGroupId();
			}

			try {
				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_IMPORT_STARTED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext));

				// Portlet preferences

				_portletImportController.importPortletPreferences(
					portletDataContext, layoutSet.getCompanyId(),
					portletPreferencesGroupId, layout, portletElement, false,
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_DATA),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_SETUP),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_USER_PREFERENCES));

				// Portlet data

				if (importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_DATA)) {

					_portletImportController.importPortletData(
						portletDataContext, portletDataElement);
				}

				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_IMPORT_SUCCEEDED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext));
			}
			catch (Throwable t) {
				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_IMPORT_FAILED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext),
					t);

				throw t;
			}
			finally {
				_portletImportController.resetPortletScope(
					portletDataContext, portletPreferencesGroupId);
			}

			// Portlet permissions

			if (permissions) {
				_permissionImporter.importPortletPermissions(
					layoutCache, companyId, portletDataContext.getGroupId(),
					userId, layout, portletElement, portletId);
			}

			// Archived setups

			_portletImportController.importPortletPreferences(
				portletDataContext, layoutSet.getCompanyId(),
				portletDataContext.getGroupId(), null, portletElement, false,
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_DATA),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_SETUP),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_USER_PREFERENCES));
		}

		// Import services

		if (_log.isDebugEnabled() && !portletElements.isEmpty()) {
			_log.debug("Importing services");
		}

		Element servicesElement = rootElement.element("services");

		List<Element> serviceElements = servicesElement.elements("service");

		for (Element serviceElement : serviceElements) {
			String path = serviceElement.attributeValue("path");

			Document serviceDocument = SAXReaderUtil.read(
				portletDataContext.getZipEntryAsString(path));

			serviceElement = serviceDocument.getRootElement();

			_portletImportController.importServicePortletPreferences(
				portletDataContext, serviceElement);
>>>>>>> compatible
		}

		// Asset links

		_portletImportController.importAssetLinks(portletDataContext);

<<<<<<< HEAD
=======
		// Delete missing layouts

		if (deleteMissingLayouts) {
			deleteMissingLayouts(
				portletDataContext, sourceLayoutsUuids, previousLayouts,
				serviceContext);
		}

		// Page count

		layoutSet = _layoutSetLocalService.updatePageCount(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

>>>>>>> compatible
		// Site

		_groupLocalService.updateSite(portletDataContext.getGroupId(), true);

<<<<<<< HEAD
		if (_log.isInfoEnabled()) {
			_log.info("Importing layouts takes " + stopWatch.getTime() + " ms");
		}

		ZipReader zipReader = portletDataContext.getZipReader();

		zipReader.close();
	}

	protected List<Element> fetchPortletElements(Element rootElement) {
		List<Element> portletElements = new ArrayList<>();

		// Site portlets

		Element sitePortletsElement = rootElement.element("site-portlets");

		// LAR compatibility

		if (sitePortletsElement == null) {
			sitePortletsElement = rootElement.element("portlets");
		}

		portletElements.addAll(sitePortletsElement.elements("portlet"));

		// Layout portlets

		XPath xPath = SAXReaderUtil.createXPath(
			"staged-model/portlets/portlet");

		Element layoutsElement = rootElement.element(
			Layout.class.getSimpleName());

		List<Node> nodes = xPath.selectNodes(layoutsElement);

		Stream<Node> nodesStream = nodes.stream();

		nodesStream.map(
			node -> (Element)node
		).forEach(
			portletElements::add
		);

		return portletElements;
=======
		// Page priorities

		updateLayoutPriorities(
			portletDataContext, layoutElements,
			portletDataContext.isPrivateLayout());

		// Last merge time is updated only if there aren not any modified
		// layouts

		if (layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			long lastMergeTime = System.currentTimeMillis();

			for (Layout layout : layouts.values()) {
				layout = _layoutLocalService.getLayout(layout.getPlid());

				if (modifiedLayouts.contains(layout)) {
					continue;
				}

				UnicodeProperties typeSettingsProperties =
					layout.getTypeSettingsProperties();

				typeSettingsProperties.setProperty(
					Sites.LAST_MERGE_TIME, String.valueOf(lastMergeTime));

				_layoutLocalService.updateLayout(layout);
			}

			// The layout set may be stale because LayoutUtil#update(layout)
			// triggers LayoutSetPrototypeLayoutModelListener and that may have
			// updated this layout set

			layoutSet = _layoutSetLocalService.getLayoutSet(
				layoutSet.getLayoutSetId());

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			String mergeFailFriendlyURLLayouts = settingsProperties.getProperty(
				Sites.MERGE_FAIL_FRIENDLY_URL_LAYOUTS);

			if (Validator.isNull(mergeFailFriendlyURLLayouts)) {
				settingsProperties.setProperty(
					Sites.LAST_MERGE_TIME, String.valueOf(lastMergeTime));

				_layoutSetLocalService.updateLayoutSet(layoutSet);
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Importing layouts takes " + stopWatch.getTime() + " ms");
		}

		ZipReader zipReader = portletDataContext.getZipReader();

		zipReader.close();
>>>>>>> compatible
	}

	protected PortletDataContext getPortletDataContext(
			ExportImportConfiguration exportImportConfiguration, File file)
		throws PortalException {

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		long userId = MapUtil.getLong(settingsMap, "userId");
		long targetGroupId = MapUtil.getLong(settingsMap, "targetGroupId");
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");

		Group group = _groupLocalService.getGroup(targetGroupId);

		String userIdStrategyString = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.USER_ID_STRATEGY);

		UserIdStrategy userIdStrategy = _exportImportHelper.getUserIdStrategy(
			userId, userIdStrategyString);

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(file);

		PortletDataContext portletDataContext =
			_portletDataContextFactory.createImportPortletDataContext(
				group.getCompanyId(), targetGroupId, parameterMap,
				userIdStrategy, zipReader);

		portletDataContext.setExportImportProcessId(
			String.valueOf(
				exportImportConfiguration.getExportImportConfigurationId()));
		portletDataContext.setPrivateLayout(privateLayout);

		return portletDataContext;
	}

	protected int getProcessFlag() {
		if (ExportImportThreadLocal.isLayoutStagingInProcess()) {
			return PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;
		}

		return PROCESS_FLAG_LAYOUT_IMPORT_IN_PROCESS;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
=======
>>>>>>> compatible
	protected void importLayout(
			PortletDataContext portletDataContext,
			List<String> sourceLayoutsUuids, Element layoutElement)
		throws Exception {
<<<<<<< HEAD
=======

		String action = layoutElement.attributeValue(Constants.ACTION);

		if (!action.equals(Constants.SKIP)) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, layoutElement);
		}

		if (!action.equals(Constants.DELETE)) {
			sourceLayoutsUuids.add(layoutElement.attributeValue("uuid"));
		}
>>>>>>> compatible
	}

	protected void populateDeletionStagedModelTypes(
			PortletDataContext portletDataContext)
		throws Exception {

<<<<<<< HEAD
		List<Element> portletElements = fetchPortletElements(
			portletDataContext.getImportDataRootElement());
=======
		Element rootElement = portletDataContext.getImportDataRootElement();

		Element portletsElement = rootElement.element("portlets");

		List<Element> portletElements = portletsElement.elements("portlet");
>>>>>>> compatible

		for (Element portletElement : portletElements) {
			String portletId = portletElement.attributeValue("portlet-id");

			Portlet portlet = _portletLocalService.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			if ((portlet == null) || !portlet.isActive() ||
				portlet.isUndeployedPortlet()) {

				continue;
			}

			PortletDataHandler portletDataHandler =
				_portletDataHandlerProvider.provide(
					portletDataContext.getCompanyId(), portletId);

			if (portletDataHandler == null) {
				continue;
			}

			portletDataContext.addDeletionSystemEventStagedModelTypes(
				portletDataHandler.getDeletionSystemEventStagedModelTypes());
		}

		portletDataContext.addDeletionSystemEventStagedModelTypes(
			new StagedModelType(Layout.class));
<<<<<<< HEAD
		portletDataContext.addDeletionSystemEventStagedModelTypes(
			new StagedModelType(StagedAssetLink.class));
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setExportImportLifecycleManager(
		ExportImportLifecycleManager exportImportLifecycleManager) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutPrototypeLocalService(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutSetPrototypeLocalService(
		LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setPortletImportController(
		PortletImportController portletImportController) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setPortletScope(
		PortletDataContext portletDataContext, Element portletElement) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void updateLayoutPriorities(
		PortletDataContext portletDataContext, List<Element> layoutElements,
		boolean privateLayout) {
=======
	}

	@Reference(unbind = "-")
	protected void setExportImportLifecycleManager(
		ExportImportLifecycleManager exportImportLifecycleManager) {

		_exportImportLifecycleManager = exportImportLifecycleManager;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutPrototypeLocalService(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {

		_layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {

		_layoutSetLocalService = layoutSetLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutSetPrototypeLocalService(
		LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {

		_layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortletImportController(
		PortletImportController portletImportController) {

		_portletImportController = portletImportController;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	protected void setPortletScope(
		PortletDataContext portletDataContext, Element portletElement) {

		// Portlet data scope

		String scopeLayoutUuid = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-uuid"));
		String scopeLayoutType = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-type"));

		portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);
		portletDataContext.setScopeType(scopeLayoutType);

		// Layout scope

		try {
			Group scopeGroup = null;

			if (scopeLayoutType.equals("company")) {
				scopeGroup = _groupLocalService.getCompanyGroup(
					portletDataContext.getCompanyId());
			}
			else if (Validator.isNotNull(scopeLayoutUuid)) {
				Layout scopeLayout =
					_layoutLocalService.getLayoutByUuidAndGroupId(
						scopeLayoutUuid, portletDataContext.getGroupId(),
						portletDataContext.isPrivateLayout());

				scopeGroup = _groupLocalService.checkScopeGroup(
					scopeLayout, portletDataContext.getUserId(null));

				Group group = scopeLayout.getGroup();

				if (group.isStaged() && !group.isStagedRemotely()) {
					try {
						boolean privateLayout = GetterUtil.getBoolean(
							portletElement.attributeValue("private-layout"));

						Layout oldLayout =
							_layoutLocalService.getLayoutByUuidAndGroupId(
								scopeLayoutUuid,
								portletDataContext.getSourceGroupId(),
								privateLayout);

						Group oldScopeGroup = oldLayout.getScopeGroup();

						if (group.isStagingGroup()) {
							scopeGroup.setLiveGroupId(
								oldScopeGroup.getGroupId());

							_groupLocalService.updateGroup(scopeGroup);
						}
						else {
							oldScopeGroup.setLiveGroupId(
								scopeGroup.getGroupId());

							_groupLocalService.updateGroup(oldScopeGroup);
						}
					}
					catch (NoSuchLayoutException nsle) {
						if (_log.isWarnEnabled()) {
							_log.warn(nsle);
						}
					}
				}
			}

			if (scopeGroup != null) {
				portletDataContext.setScopeGroupId(scopeGroup.getGroupId());

				Map<Long, Long> groupIds =
					(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
						Group.class);

				long oldScopeGroupId = GetterUtil.getLong(
					portletElement.attributeValue("scope-group-id"));

				groupIds.put(oldScopeGroupId, scopeGroup.getGroupId());
			}
		}
		catch (PortalException pe) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void updateLayoutPriorities(
		PortletDataContext portletDataContext, List<Element> layoutElements,
		boolean privateLayout) {

		Map<Long, Layout> layouts =
			(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class + ".layout");

		Map<Long, Integer> layoutPriorities = new HashMap<>();

		int maxPriority = Integer.MIN_VALUE;

		for (Element layoutElement : layoutElements) {
			String action = layoutElement.attributeValue(Constants.ACTION);

			if (action.equals(Constants.SKIP)) {

				// We only want to update priorites if there are no elements
				// with the SKIP action

				return;
			}

			if (action.equals(Constants.ADD)) {
				long layoutId = GetterUtil.getLong(
					layoutElement.attributeValue("layout-id"));

				Layout layout = layouts.get(layoutId);

				// Layout might have not been imported due to a controlled
				// error. See SitesImpl#addMergeFailFriendlyURLLayout.

				if (layout == null) {
					continue;
				}

				int layoutPriority = GetterUtil.getInteger(
					layoutElement.attributeValue("layout-priority"));

				layoutPriorities.put(layout.getPlid(), layoutPriority);

				if (maxPriority < layoutPriority) {
					maxPriority = layoutPriority;
				}
			}
		}

		List<Layout> layoutSetLayouts = _layoutLocalService.getLayouts(
			portletDataContext.getGroupId(), privateLayout);

		for (Layout layout : layoutSetLayouts) {
			if (layoutPriorities.containsKey(layout.getPlid())) {
				layout.setPriority(layoutPriorities.get(layout.getPlid()));
			}
			else {
				layout.setPriority(++maxPriority);
			}

			_layoutLocalService.updateLayout(layout);
		}
>>>>>>> compatible
	}

	protected void validateFile(
			long companyId, long groupId, Map<String, String[]> parameterMap,
			ZipReader zipReader)
		throws Exception {

		// XML

		String xml = zipReader.getEntryAsString("/manifest.xml");

		if (xml == null) {
<<<<<<< HEAD
			throw new LARFileException(LARFileException.TYPE_MISSING_MANIFEST);
=======
			throw new LARFileException("manifest.xml not found in the LAR");
>>>>>>> compatible
		}

		Element rootElement = null;

		try {
			Document document = SAXReaderUtil.read(xml);

			rootElement = document.getRootElement();
		}
		catch (Exception e) {
<<<<<<< HEAD
			throw new LARFileException(
				LARFileException.TYPE_INVALID_MANIFEST, e);
=======
			throw new LARFileException(e);
>>>>>>> compatible
		}

		// Bundle compatibility

		Element headerElement = rootElement.element("header");

		int importBuildNumber = GetterUtil.getInteger(
			headerElement.attributeValue("build-number"));

		if (importBuildNumber < ReleaseInfo.RELEASE_7_0_0_BUILD_NUMBER) {
			int buildNumber = ReleaseInfo.getBuildNumber();

			if (buildNumber != importBuildNumber) {
<<<<<<< HEAD
				throw new LayoutImportException(
					LayoutImportException.TYPE_WRONG_BUILD_NUMBER,
					new Object[] {importBuildNumber, buildNumber});
=======
				StringBundler sb = new StringBundler(4);

				sb.append("LAR build number ");
				sb.append(importBuildNumber);
				sb.append(" does not match portal build number ");
				sb.append(buildNumber);

				throw new LayoutImportException(sb.toString());
>>>>>>> compatible
			}
		}
		else {
			BiPredicate<Version, Version> majorVersionBiPredicate =
				(currentVersion, importVersion) -> Objects.equals(
					currentVersion.getMajor(), importVersion.getMajor());

			BiPredicate<Version, Version> minorVersionBiPredicate =
				(currentVersion, importVersion) -> {
					int currentMinorVersion = GetterUtil.getInteger(
						currentVersion.getMinor(), -1);
					int importedMinorVersion = GetterUtil.getInteger(
						importVersion.getMinor(), -1);

					if (((currentMinorVersion == -1) &&
						 (importedMinorVersion == -1)) ||
						(currentMinorVersion < importedMinorVersion)) {

						return false;
					}

					return true;
				};

			BiPredicate<Version, Version> manifestVersionBiPredicate =
				(currentVersion, importVersion) -> {
					BiPredicate<Version, Version> versionBiPredicate =
						majorVersionBiPredicate.and(minorVersionBiPredicate);

					return versionBiPredicate.test(
						currentVersion, importVersion);
				};

<<<<<<< HEAD
			String importSchemaVersion = GetterUtil.getString(
				headerElement.attributeValue("schema-version"), "1.0.0");

			if (!manifestVersionBiPredicate.test(
					Version.getInstance(
						ExportImportConstants.EXPORT_IMPORT_SCHEMA_VERSION),
					Version.getInstance(importSchemaVersion))) {

				throw new LayoutImportException(
					LayoutImportException.TYPE_WRONG_LAR_SCHEMA_VERSION,
					new Object[] {
						importSchemaVersion,
						ExportImportConstants.EXPORT_IMPORT_SCHEMA_VERSION
					});
=======
			Bundle bundle = FrameworkUtil.getBundle(
				LayoutImportController.class);

			String currentBundleVersion = String.valueOf(bundle.getVersion());

			String importBundleVersion = GetterUtil.getString(
				headerElement.attributeValue("bundle-version"), "3.0.0");

			if (!manifestVersionBiPredicate.test(
					Version.getInstance(currentBundleVersion),
					Version.getInstance(importBundleVersion))) {

				StringBundler sb = new StringBundler(4);

				sb.append("LAR bundle version ");
				sb.append(importBundleVersion);
				sb.append(
					" does not match deployed export/import bundle version ");
				sb.append(currentBundleVersion);

				throw new LayoutImportException(sb.toString());
>>>>>>> compatible
			}
		}

		// Type

		String larType = headerElement.attributeValue("type");

<<<<<<< HEAD
		String[] expectedLARTypes =
			{"layout-prototype", "layout-set", "layout-set-prototype"};

		Stream<String> stream = Stream.of(expectedLARTypes);

		if (stream.noneMatch(lt -> lt.equals(larType))) {
			throw new LARTypeException(larType, expectedLARTypes);
=======
		if (!larType.equals("layout-prototype") &&
			!larType.equals("layout-set") &&
			!larType.equals("layout-set-prototype")) {

			throw new LARTypeException(larType);
>>>>>>> compatible
		}

		Group group = _groupLocalService.fetchGroup(groupId);

		String layoutsImportMode = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE);

		if (larType.equals("layout-prototype") && !group.isLayoutPrototype() &&
			!layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

<<<<<<< HEAD
			throw new LARTypeException(LARTypeException.TYPE_LAYOUT_PROTOTYPE);
=======
			throw new LARTypeException(
				"A page template can only be imported to a page template");
>>>>>>> compatible
		}

		if (larType.equals("layout-set")) {
			if (group.isLayoutPrototype() || group.isLayoutSetPrototype()) {
<<<<<<< HEAD
				throw new LARTypeException(LARTypeException.TYPE_LAYOUT_SET);
=======
				throw new LARTypeException(
					"A site can only be imported to a site");
>>>>>>> compatible
			}

			long sourceCompanyGroupId = GetterUtil.getLong(
				headerElement.attributeValue("company-group-id"));
			long sourceGroupId = GetterUtil.getLong(
				headerElement.attributeValue("group-id"));

			boolean companySourceGroup = false;

			if (sourceCompanyGroupId == sourceGroupId) {
				companySourceGroup = true;
			}
<<<<<<< HEAD

			if (group.isCompany() ^ companySourceGroup) {
				throw new LARTypeException(LARTypeException.TYPE_COMPANY_GROUP);
=======
			else if ((group.isStaged() || group.hasStagingGroup()) &&
					 !(group.isStagedRemotely() &&
					   group.hasRemoteStagingGroup())) {

				Group sourceGroup = _groupLocalService.fetchGroup(
					sourceGroupId);

				companySourceGroup = sourceGroup.isCompany();
			}

			if (group.isCompany() ^ companySourceGroup) {
				throw new LARTypeException(
					"A company site can only be imported to a company site");
>>>>>>> compatible
			}
		}

		if (larType.equals("layout-set-prototype") &&
			!group.isLayoutSetPrototype() &&
			!layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			throw new LARTypeException(
<<<<<<< HEAD
				LARTypeException.TYPE_LAYOUT_SET_PROTOTYPE);
=======
				"A site template can only be imported to a site template");
>>>>>>> compatible
		}

		// Portlets compatibility

<<<<<<< HEAD
		List<Element> portletElements = fetchPortletElements(rootElement);
=======
		Element portletsElement = rootElement.element("portlets");

		List<Element> portletElements = portletsElement.elements("portlet");
>>>>>>> compatible

		for (Element portletElement : portletElements) {
			String portletId = GetterUtil.getString(
				portletElement.attributeValue("portlet-id"));

			if (Validator.isNull(portletId)) {
				continue;
			}

			String schemaVersion = GetterUtil.getString(
				portletElement.attributeValue("schema-version"));

			PortletDataHandler portletDataHandler =
				_portletDataHandlerProvider.provide(companyId, portletId);

			if (portletDataHandler == null) {
				continue;
			}

			if (!portletDataHandler.validateSchemaVersion(schemaVersion)) {
<<<<<<< HEAD
				throw new LayoutImportException(
					LayoutImportException.TYPE_WRONG_PORTLET_SCHEMA_VERSION,
					new Object[] {
						schemaVersion, portletId,
						portletDataHandler.getSchemaVersion()
					});
=======
				StringBundler sb = new StringBundler(6);

				sb.append("Portlet's schema version ");
				sb.append(schemaVersion);
				sb.append(" in the LAR is not valid for the deployed portlet ");
				sb.append(portletId);
				sb.append(" with schema version ");
				sb.append(portletDataHandler.getSchemaVersion());

				throw new LayoutImportException(sb.toString());
>>>>>>> compatible
			}
		}

		// Available locales

		List<Locale> sourceAvailableLocales = Arrays.asList(
			LocaleUtil.fromLanguageIds(
				StringUtil.split(
					headerElement.attributeValue("available-locales"))));

		for (Locale sourceAvailableLocale : sourceAvailableLocales) {
			if (!LanguageUtil.isAvailableLocale(
					groupId, sourceAvailableLocale)) {

				LocaleException le = new LocaleException(
					LocaleException.TYPE_EXPORT_IMPORT);

				le.setSourceAvailableLocales(sourceAvailableLocales);
				le.setTargetAvailableLocales(
					LanguageUtil.getAvailableLocales(groupId));

				throw le;
			}
		}

		// Layout prototypes validity

		Element layoutsElement = rootElement.element(
			Layout.class.getSimpleName());

		validateLayoutPrototypes(companyId, headerElement, layoutsElement);
	}

	/**
	 * @deprecated As of 3.0.0, replaced by {@link
	 *             #validateLayoutPrototypes(long, Element, Element)}
	 */
	@Deprecated
	protected void validateLayoutPrototypes(
			long companyId, Element layoutsElement)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method is deprecated and replaced by " +
				"#validateLayoutPrototypes(long, Element, Element)");
	}

	protected void validateLayoutPrototypes(
			long companyId, Element headerElement, Element layoutsElement)
		throws Exception {

		List<Tuple> missingLayoutPrototypes = new ArrayList<>();

		String layoutSetPrototypeUuid = headerElement.attributeValue(
			"layout-set-prototype-uuid");

		if (Validator.isNotNull(layoutSetPrototypeUuid)) {
			try {
				_layoutSetPrototypeLocalService.
					getLayoutSetPrototypeByUuidAndCompanyId(
						layoutSetPrototypeUuid, companyId);
			}
			catch (NoSuchLayoutSetPrototypeException nslspe) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(nslspe, nslspe);
				}

				String layoutSetPrototypeName = headerElement.attributeValue(
					"layout-set-prototype-name");

				missingLayoutPrototypes.add(
					new Tuple(
						LayoutSetPrototype.class.getName(),
						layoutSetPrototypeUuid, layoutSetPrototypeName));
			}
		}

		List<Element> layoutElements = layoutsElement.elements();

		for (Element layoutElement : layoutElements) {
			String action = layoutElement.attributeValue(Constants.ACTION);

			if (action.equals(Constants.SKIP)) {
				continue;
			}

			String layoutPrototypeUuid = GetterUtil.getString(
				layoutElement.attributeValue("layout-prototype-uuid"));

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				try {
					_layoutPrototypeLocalService.
						getLayoutPrototypeByUuidAndCompanyId(
							layoutPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutPrototypeException nslpe) {

					// LPS-52675

					if (_log.isDebugEnabled()) {
						_log.debug(nslpe, nslpe);
					}

					String layoutPrototypeName = GetterUtil.getString(
						layoutElement.attributeValue("layout-prototype-name"));

					missingLayoutPrototypes.add(
						new Tuple(
							LayoutPrototype.class.getName(),
							layoutPrototypeUuid, layoutPrototypeName));
				}
			}
		}

		if (!missingLayoutPrototypes.isEmpty()) {
			throw new LayoutPrototypeException(missingLayoutPrototypes);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutImportController.class);

	private final DeletionSystemEventImporter _deletionSystemEventImporter =
		DeletionSystemEventImporter.getInstance();

	@Reference
	private ExportImportHelper _exportImportHelper;

<<<<<<< HEAD
	@Reference
	private ExportImportLifecycleManager _exportImportLifecycleManager;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPrototypeLocalService _layoutPrototypeLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private LayoutSetPrototypeLocalService _layoutSetPrototypeLocalService;

=======
	private ExportImportLifecycleManager _exportImportLifecycleManager;
	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private LayoutPrototypeLocalService _layoutPrototypeLocalService;
	private LayoutSetLocalService _layoutSetLocalService;
	private LayoutSetPrototypeLocalService _layoutSetPrototypeLocalService;
>>>>>>> compatible
	private final PermissionImporter _permissionImporter =
		PermissionImporter.getInstance();

	@Reference
	private PortletDataContextFactory _portletDataContextFactory;

	@Reference
	private PortletDataHandlerProvider _portletDataHandlerProvider;

	@Reference
<<<<<<< HEAD
	private PortletImportController _portletImportController;

	@Reference
	private PortletLocalService _portletLocalService;
=======
	private PortletDataHandlerStatusMessageSender
		_portletDataHandlerStatusMessageSender;

	private PortletImportController _portletImportController;
	private PortletLocalService _portletLocalService;
	private final ThemeImporter _themeImporter = ThemeImporter.getInstance();
>>>>>>> compatible

}