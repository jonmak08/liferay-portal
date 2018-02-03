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

import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_EXPORT_FAILED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_EXPORT_STARTED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_LAYOUT_EXPORT_SUCCEEDED;
<<<<<<< HEAD
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_EXPORT_IN_PROCESS;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.adapter.StagedAssetLink;
import com.liferay.exportimport.constants.ExportImportConstants;
=======
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_EXPORT_FAILED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_EXPORT_STARTED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.EVENT_PORTLET_EXPORT_SUCCEEDED;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_EXPORT_IN_PROCESS;
import static com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleConstants.PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;

>>>>>>> compatible
import com.liferay.exportimport.kernel.controller.ExportController;
import com.liferay.exportimport.kernel.controller.ExportImportController;
import com.liferay.exportimport.kernel.lar.ExportImportDateUtil;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
<<<<<<< HEAD
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataContextFactory;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
=======
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.ExportImportProcessCallbackRegistry;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataContextFactory;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerStatusMessageSender;
>>>>>>> compatible
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleManager;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.lar.DeletionSystemEventExporter;
import com.liferay.exportimport.lar.PermissionExporter;
<<<<<<< HEAD
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
=======
import com.liferay.exportimport.lar.ThemeExporter;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
>>>>>>> compatible
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
<<<<<<< HEAD
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.adapter.ModelAdapterUtil;
=======
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.LayoutStagingHandler;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
>>>>>>> compatible
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.LayoutRevisionLocalService;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.ArrayUtil;
=======
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
>>>>>>> compatible
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
<<<<<<< HEAD
import com.liferay.site.model.adapter.StagedGroup;
=======
import com.liferay.portal.model.impl.LayoutImpl;
>>>>>>> compatible

import java.io.File;
import java.io.Serializable;

<<<<<<< HEAD
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

=======
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;
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
 * @author Karthik Sudarshan
 * @author Zsigmond Rab
 * @author Douglas Wong
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.portal.kernel.model.Layout"},
	service = {ExportImportController.class, LayoutExportController.class}
)
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public class LayoutExportController implements ExportController {

	@Override
	public File export(ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		PortletDataContext portletDataContext = null;

		try {
			ExportImportThreadLocal.setLayoutExportInProcess(true);

<<<<<<< HEAD
=======
			Map<String, Serializable> settingsMap =
				exportImportConfiguration.getSettingsMap();

			long[] layoutIds = GetterUtil.getLongValues(
				settingsMap.get("layoutIds"));

>>>>>>> compatible
			portletDataContext = getPortletDataContext(
				exportImportConfiguration);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_EXPORT_STARTED, getProcessFlag(),
<<<<<<< HEAD
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext));

			File file = doExport(portletDataContext);
=======
				portletDataContext.getExportImportProcessId(),
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext));

			File file = doExport(portletDataContext, layoutIds);
>>>>>>> compatible

			ExportImportThreadLocal.setLayoutExportInProcess(false);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_EXPORT_SUCCEEDED, getProcessFlag(),
<<<<<<< HEAD
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
=======
				portletDataContext.getExportImportProcessId(),
>>>>>>> compatible
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext));

			return file;
		}
		catch (Throwable t) {
			ExportImportThreadLocal.setLayoutExportInProcess(false);

			_exportImportLifecycleManager.fireExportImportLifecycleEvent(
				EVENT_LAYOUT_EXPORT_FAILED, getProcessFlag(),
				String.valueOf(
					exportImportConfiguration.getExportImportConfigurationId()),
				_portletDataContextFactory.clonePortletDataContext(
					portletDataContext),
				t);

			throw t;
		}
	}

<<<<<<< HEAD
	protected File doExport(PortletDataContext portletDataContext)
=======
	protected File doExport(
			PortletDataContext portletDataContext, long[] layoutIds)
>>>>>>> compatible
		throws Exception {

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

		boolean ignoreLastPublishDate = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.IGNORE_LAST_PUBLISH_DATE);
<<<<<<< HEAD
=======
		boolean layoutSetPrototypeSettings = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_SETTINGS);
		boolean layoutSetSettings = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LAYOUT_SET_SETTINGS);
		boolean logo = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LOGO);
>>>>>>> compatible
		boolean permissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);

		if (_log.isDebugEnabled()) {
			_log.debug("Export permissions " + permissions);
		}

<<<<<<< HEAD
		long companyId = portletDataContext.getCompanyId();
=======
		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

		long companyId = layoutSet.getCompanyId();
>>>>>>> compatible

		long defaultUserId = _userLocalService.getDefaultUserId(companyId);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.popServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		serviceContext.setCompanyId(companyId);
		serviceContext.setSignedIn(true);

		if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
			BackgroundTask backgroundTask =
				_backgroundTaskLocalService.getBackgroundTask(
					BackgroundTaskThreadLocal.getBackgroundTaskId());

			serviceContext.setUserId(backgroundTask.getUserId());
		}
		else {
			serviceContext.setUserId(defaultUserId);
		}

		serviceContext.setAttribute("exporting", Boolean.TRUE);

		long layoutSetBranchId = MapUtil.getLong(
			parameterMap, "layoutSetBranchId");

		serviceContext.setAttribute("layoutSetBranchId", layoutSetBranchId);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		if (ignoreLastPublishDate) {
			portletDataContext.setEndDate(null);
			portletDataContext.setStartDate(null);
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		portletDataContext.setExportDataRootElement(rootElement);

		Element headerElement = rootElement.addElement("header");

		headerElement.addAttribute(
			"available-locales",
			StringUtil.merge(
				LanguageUtil.getAvailableLocales(
					portletDataContext.getScopeGroupId())));

		headerElement.addAttribute(
			"build-number", String.valueOf(ReleaseInfo.getBuildNumber()));

<<<<<<< HEAD
		headerElement.addAttribute(
			"schema-version",
			ExportImportConstants.EXPORT_IMPORT_SCHEMA_VERSION);
=======
		Bundle bundle = FrameworkUtil.getBundle(LayoutExportController.class);

		Version version = bundle.getVersion();

		headerElement.addAttribute("bundle-version", String.valueOf(version));
>>>>>>> compatible

		headerElement.addAttribute("export-date", Time.getRFC822());

		if (portletDataContext.hasDateRange()) {
			headerElement.addAttribute(
				"start-date",
				String.valueOf(portletDataContext.getStartDate()));
			headerElement.addAttribute(
				"end-date", String.valueOf(portletDataContext.getEndDate()));
		}

		headerElement.addAttribute(
			"company-id", String.valueOf(portletDataContext.getCompanyId()));
		headerElement.addAttribute(
			"company-group-id",
			String.valueOf(portletDataContext.getCompanyGroupId()));
		headerElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getGroupId()));
		headerElement.addAttribute(
			"user-personal-site-group-id",
			String.valueOf(portletDataContext.getUserPersonalSiteGroupId()));
		headerElement.addAttribute(
			"private-layout",
			String.valueOf(portletDataContext.isPrivateLayout()));

<<<<<<< HEAD
		Group group = _groupLocalService.fetchGroup(
			portletDataContext.getGroupId());
=======
		Group group = layoutSet.getGroup();
>>>>>>> compatible

		String type = "layout-set";

		if (group.isLayoutPrototype()) {
			type = "layout-prototype";

			LayoutPrototype layoutPrototype =
				_layoutPrototypeLocalService.getLayoutPrototype(
					group.getClassPK());

			headerElement.addAttribute("type-uuid", layoutPrototype.getUuid());
<<<<<<< HEAD
=======

			layoutIds = _exportImportHelper.getAllLayoutIds(
				portletDataContext.getGroupId(),
				portletDataContext.isPrivateLayout());
>>>>>>> compatible
		}
		else if (group.isLayoutSetPrototype()) {
			type = "layout-set-prototype";

			LayoutSetPrototype layoutSetPrototype =
				_layoutSetPrototypeLocalService.getLayoutSetPrototype(
					group.getClassPK());

			headerElement.addAttribute(
				"type-uuid", layoutSetPrototype.getUuid());
		}

		headerElement.addAttribute("type", type);
<<<<<<< HEAD
		portletDataContext.setType(type);

		Element missingReferencesElement = rootElement.addElement(
			"missing-references");

		portletDataContext.setMissingReferencesElement(
			missingReferencesElement);

		rootElement.addElement("site-portlets");
		rootElement.addElement("site-services");

		// Export the group

		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

		String layoutSetPrototypeUuid = layoutSet.getLayoutSetPrototypeUuid();

		boolean layoutSetPrototypeSettings = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_SETTINGS);

		if (!group.isStaged() && Validator.isNotNull(layoutSetPrototypeUuid) &&
			layoutSetPrototypeSettings) {
=======

		LayoutSetBranch layoutSetBranch =
			_layoutSetBranchLocalService.fetchLayoutSetBranch(
				layoutSetBranchId);

		if (logo) {
			Image image = null;

			if (layoutSetBranch != null) {
				image = _imageLocalService.getImage(
					layoutSetBranch.getLogoId());
			}
			else {
				image = _imageLocalService.getImage(layoutSet.getLogoId());
			}

			if ((image != null) && (image.getTextObj() != null)) {
				String logoPath = ExportImportPathUtil.getRootPath(
					portletDataContext);

				logoPath += "/logo";

				headerElement.addAttribute("logo-path", logoPath);

				portletDataContext.addZipEntry(logoPath, image.getTextObj());
			}
		}

		String layoutSetPrototypeUuid = layoutSet.getLayoutSetPrototypeUuid();

		if (layoutSetPrototypeSettings &&
			Validator.isNotNull(layoutSetPrototypeUuid)) {
>>>>>>> compatible

			LayoutSetPrototype layoutSetPrototype =
				_layoutSetPrototypeLocalService.
					getLayoutSetPrototypeByUuidAndCompanyId(
						layoutSetPrototypeUuid, companyId);

			headerElement.addAttribute(
				"layout-set-prototype-uuid", layoutSetPrototypeUuid);
<<<<<<< HEAD

=======
>>>>>>> compatible
			headerElement.addAttribute(
				"layout-set-prototype-name",
				layoutSetPrototype.getName(LocaleUtil.getDefault()));
		}

<<<<<<< HEAD
		StagedGroup stagedGroup = ModelAdapterUtil.adapt(
			group, Group.class, StagedGroup.class);

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, stagedGroup);

		// Export other models
=======
		Element missingReferencesElement = rootElement.addElement(
			"missing-references");

		portletDataContext.setMissingReferencesElement(
			missingReferencesElement);

		if (layoutSetBranch != null) {
			_themeExporter.exportTheme(portletDataContext, layoutSetBranch);
		}
		else {
			_themeExporter.exportTheme(portletDataContext, layoutSet);
		}

		if (layoutSetSettings) {
			Element settingsElement = headerElement.addElement("settings");

			if (layoutSetBranch != null) {
				settingsElement.addCDATA(layoutSetBranch.getSettings());
			}
			else {
				settingsElement.addCDATA(layoutSet.getSettings());
			}
		}

		Map<String, Object[]> portletIds = new LinkedHashMap<>();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			portletDataContext.getGroupId(),
			portletDataContext.isPrivateLayout());

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		// Collect data portlets

		for (Portlet portlet :
				_exportImportHelper.getDataSiteLevelPortlets(companyId)) {

			String portletId = portlet.getRootPortletId();

			if (ExportImportThreadLocal.isStagingInProcess() &&
				!group.isStagedPortlet(portletId)) {

				continue;
			}

			// Calculate the amount of exported data

			if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
				PortletDataHandler portletDataHandler =
					portlet.getPortletDataHandlerInstance();

				portletDataHandler.prepareManifestSummary(portletDataContext);
			}

			// Add portlet ID to exportable portlets list

			portletIds.put(
				PortletPermissionUtil.getPrimaryKey(0, portletId),
				new Object[] {
					portletId, LayoutConstants.DEFAULT_PLID,
					portletDataContext.getGroupId(), StringPool.BLANK,
					StringPool.BLANK
				});

			if (!portlet.isScopeable()) {
				continue;
			}

			// Scoped data

			for (Layout layout : layouts) {
				if (!ArrayUtil.contains(layoutIds, layout.getLayoutId()) ||
					!layout.isTypePortlet() || !layout.hasScopeGroup()) {

					continue;
				}

				LayoutTypePortlet layoutTypePortlet =
					(LayoutTypePortlet)layout.getLayoutType();

				if (!layoutTypePortlet.hasPortletId(portletId)) {
					continue;
				}

				Group scopeGroup = layout.getScopeGroup();

				portletIds.put(
					PortletPermissionUtil.getPrimaryKey(
						layout.getPlid(), portlet.getPortletId()),
					new Object[] {
						portlet.getPortletId(), layout.getPlid(),
						scopeGroup.getGroupId(), StringPool.BLANK,
						layout.getUuid()
					});
			}
		}

		// Collect layout portlets

		for (Layout layout : layouts) {
			getLayoutPortlets(
				portletDataContext, layoutIds, portletIds, layout);
		}

		if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
			ManifestSummary manifestSummary =
				portletDataContext.getManifestSummary();

			_portletDataHandlerStatusMessageSender.sendStatusMessage(
				"layout", ArrayUtil.toStringArray(portletIds.keySet()),
				manifestSummary);

			manifestSummary.resetCounters();
		}

		// Export actual data

		portletDataContext.addDeletionSystemEventStagedModelTypes(
			new StagedModelType(Layout.class));

		// Force to always have a layout group element

		portletDataContext.getExportDataGroupElement(Layout.class);

		for (Layout layout : layouts) {
			exportLayout(portletDataContext, layoutIds, layout);
		}

		Element portletsElement = rootElement.addElement("portlets");

		Element servicesElement = rootElement.addElement("services");

		long previousScopeGroupId = portletDataContext.getScopeGroupId();

		for (Map.Entry<String, Object[]> portletIdsEntry :
				portletIds.entrySet()) {

			Object[] portletObjects = portletIdsEntry.getValue();

			String portletId = null;
			long plid = LayoutConstants.DEFAULT_PLID;
			long scopeGroupId = 0;
			String scopeType = StringPool.BLANK;
			String scopeLayoutUuid = null;

			if (portletObjects.length == 4) {
				portletId = (String)portletIdsEntry.getValue()[0];
				plid = (Long)portletIdsEntry.getValue()[1];
				scopeGroupId = (Long)portletIdsEntry.getValue()[2];
				scopeLayoutUuid = (String)portletIdsEntry.getValue()[3];
			}
			else {
				portletId = (String)portletIdsEntry.getValue()[0];
				plid = (Long)portletIdsEntry.getValue()[1];
				scopeGroupId = (Long)portletIdsEntry.getValue()[2];
				scopeType = (String)portletIdsEntry.getValue()[3];
				scopeLayoutUuid = (String)portletIdsEntry.getValue()[4];
			}

			Layout layout = _layoutLocalService.fetchLayout(plid);

			if (layout == null) {
				layout = new LayoutImpl();

				layout.setCompanyId(companyId);
				layout.setGroupId(portletDataContext.getGroupId());
			}

			portletDataContext.setPlid(plid);
			portletDataContext.setOldPlid(plid);
			portletDataContext.setPortletId(portletId);
			portletDataContext.setScopeGroupId(scopeGroupId);
			portletDataContext.setScopeType(scopeType);
			portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);

			Map<String, Boolean> exportPortletControlsMap =
				_exportImportHelper.getExportPortletControlsMap(
					companyId, portletId, parameterMap, type);

			try {
				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_EXPORT_STARTED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext));

				_portletExportController.exportPortlet(
					portletDataContext, layout, portletsElement, permissions,
					exportPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS),
					exportPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_DATA),
					exportPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_SETUP),
					exportPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_USER_PREFERENCES));
				_portletExportController.exportService(
					portletDataContext, servicesElement,
					exportPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_SETUP));

				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_EXPORT_SUCCEEDED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext));
			}
			catch (Throwable t) {
				_exportImportLifecycleManager.fireExportImportLifecycleEvent(
					EVENT_PORTLET_EXPORT_FAILED, getProcessFlag(),
					portletDataContext.getExportImportProcessId(),
					_portletDataContextFactory.clonePortletDataContext(
						portletDataContext),
					t);

				throw t;
			}
		}

		portletDataContext.setScopeGroupId(previousScopeGroupId);
>>>>>>> compatible

		_portletExportController.exportAssetLinks(portletDataContext);
		_portletExportController.exportExpandoTables(portletDataContext);
		_portletExportController.exportLocks(portletDataContext);

<<<<<<< HEAD
		portletDataContext.addDeletionSystemEventStagedModelTypes(
			new StagedModelType(StagedAssetLink.class));

=======
>>>>>>> compatible
		_deletionSystemEventExporter.exportDeletionSystemEvents(
			portletDataContext);

		if (permissions) {
			_permissionExporter.exportPortletDataPermissions(
				portletDataContext);
		}

		_exportImportHelper.writeManifestSummary(
			document, portletDataContext.getManifestSummary());

		if (_log.isInfoEnabled()) {
			_log.info("Exporting layouts takes " + stopWatch.getTime() + " ms");
		}

<<<<<<< HEAD
=======
		boolean updateLastPublishDate = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE);

		if (ExportImportThreadLocal.isStagingInProcess() &&
			updateLastPublishDate) {

			_exportImportProcessCallbackRegistry.registerCallback(
				portletDataContext.getExportImportProcessId(),
				new UpdateLayoutSetLastPublishDateCallable(
					portletDataContext.getDateRange(),
					portletDataContext.getGroupId(),
					portletDataContext.isPrivateLayout()));
		}

>>>>>>> compatible
		portletDataContext.addZipEntry(
			"/manifest.xml", document.formattedString());

		ZipWriter zipWriter = portletDataContext.getZipWriter();

		return zipWriter.getFile();
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected File doExport(
			PortletDataContext portletDataContext, long[] layoutIds)
		throws Exception {

		return doExport(portletDataContext);
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
=======
>>>>>>> compatible
	protected void exportLayout(
			PortletDataContext portletDataContext, long[] layoutIds,
			Layout layout)
		throws Exception {

<<<<<<< HEAD
=======
		if (!ArrayUtil.contains(layoutIds, layout.getLayoutId())) {
			Element layoutElement = portletDataContext.getExportDataElement(
				layout);

			layoutElement.addAttribute(Constants.ACTION, Constants.SKIP);

			return;
		}

		if (!prepareLayoutStagingHandler(portletDataContext, layout)) {
			return;
		}

>>>>>>> compatible
		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, layout);
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
=======
>>>>>>> compatible
	protected void getLayoutPortlets(
			PortletDataContext portletDataContext, long[] layoutIds,
			Map<String, Object[]> portletIds, Layout layout)
		throws Exception {
<<<<<<< HEAD
=======

		if (!ArrayUtil.contains(layoutIds, layout.getLayoutId())) {
			return;
		}

		if (!prepareLayoutStagingHandler(portletDataContext, layout) ||
			!layout.isSupportsEmbeddedPortlets()) {

			// Only portlet type layouts support page scoping

			return;
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		// The getAllPortlets method returns all effective nonsystem portlets
		// for any layout type, including embedded portlets, or in the case of
		// panel type layout, selected portlets

		for (Portlet portlet : layoutTypePortlet.getAllPortlets(false)) {
			String portletId = portlet.getPortletId();

			Settings portletInstanceSettings = SettingsFactoryUtil.getSettings(
				new PortletInstanceSettingsLocator(layout, portletId));

			String scopeType = portletInstanceSettings.getValue(
				"lfrScopeType", null);
			String scopeLayoutUuid = portletInstanceSettings.getValue(
				"lfrScopeLayoutUuid", null);

			long scopeGroupId = portletDataContext.getScopeGroupId();

			if (Validator.isNotNull(scopeType)) {
				Group scopeGroup = null;

				if (scopeType.equals("company")) {
					scopeGroup = _groupLocalService.getCompanyGroup(
						layout.getCompanyId());
				}
				else if (scopeType.equals("layout")) {
					Layout scopeLayout = null;

					scopeLayout =
						_layoutLocalService.fetchLayoutByUuidAndGroupId(
							scopeLayoutUuid, portletDataContext.getGroupId(),
							portletDataContext.isPrivateLayout());

					if (scopeLayout == null) {
						continue;
					}

					scopeGroup = scopeLayout.getScopeGroup();
				}
				else {
					throw new IllegalArgumentException(
						"Scope type " + scopeType + " is invalid");
				}

				if (scopeGroup != null) {
					scopeGroupId = scopeGroup.getGroupId();
				}
			}

			String key = PortletPermissionUtil.getPrimaryKey(
				layout.getPlid(), portletId);

			portletIds.put(
				key,
				new Object[] {
					portletId, layout.getPlid(), scopeGroupId, scopeType,
					scopeLayoutUuid
				});
		}
>>>>>>> compatible
	}

	protected PortletDataContext getPortletDataContext(
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		long sourceGroupId = MapUtil.getLong(settingsMap, "sourceGroupId");
<<<<<<< HEAD

		Group group = _groupLocalService.getGroup(sourceGroupId);

		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");
		DateRange dateRange = ExportImportDateUtil.getDateRange(
			exportImportConfiguration);
=======
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");

		DateRange dateRange = ExportImportDateUtil.getDateRange(
			exportImportConfiguration);

		Group group = _groupLocalService.getGroup(sourceGroupId);
>>>>>>> compatible
		ZipWriter zipWriter = _exportImportHelper.getLayoutSetZipWriter(
			sourceGroupId);

		PortletDataContext portletDataContext =
			_portletDataContextFactory.createExportPortletDataContext(
				group.getCompanyId(), sourceGroupId, parameterMap,
				dateRange.getStartDate(), dateRange.getEndDate(), zipWriter);

<<<<<<< HEAD
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		long[] layoutIds = GetterUtil.getLongValues(
			settingsMap.get("layoutIds"));

		if (ArrayUtil.contains(layoutIds, 0)) {
			layoutIds = _exportImportHelper.getAllLayoutIds(
				sourceGroupId, privateLayout);
		}

=======
>>>>>>> compatible
		portletDataContext.setExportImportProcessId(
			String.valueOf(
				exportImportConfiguration.getExportImportConfigurationId()));
		portletDataContext.setPrivateLayout(privateLayout);
<<<<<<< HEAD
		portletDataContext.setLayoutIds(layoutIds);
=======
>>>>>>> compatible

		return portletDataContext;
	}

	protected int getProcessFlag() {
		if (ExportImportThreadLocal.isLayoutStagingInProcess()) {
			return PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS;
		}

		return PROCESS_FLAG_LAYOUT_EXPORT_IN_PROCESS;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected boolean prepareLayoutStagingHandler(
		PortletDataContext portletDataContext, Layout layout) {

		return LayoutStagingUtil.prepareLayoutStagingHandler(
			portletDataContext, layout);
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setBackgroundTaskLocalService(
		BackgroundTaskLocalService backgroundTaskLocalService) {
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
	protected void setImageLocalService(ImageLocalService imageLocalService) {
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
=======
	protected boolean prepareLayoutStagingHandler(
		PortletDataContext portletDataContext, Layout layout) {

		boolean exportLAR = MapUtil.getBoolean(
			portletDataContext.getParameterMap(), "exportLAR");

		if (exportLAR || !LayoutStagingUtil.isBranchingLayout(layout)) {
			return true;
		}

		long layoutSetBranchId = MapUtil.getLong(
			portletDataContext.getParameterMap(), "layoutSetBranchId");

		if (layoutSetBranchId <= 0) {
			return false;
		}

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision != null) {
			layoutRevision = _layoutRevisionLocalService.fetchLayoutRevision(
				layoutSetBranchId, layoutRevision.getLayoutBranchId(), true,
				layout.getPlid());
		}
		else {
			List<LayoutRevision> layoutRevisions =
				_layoutRevisionLocalService.getLayoutRevisions(
					layoutSetBranchId, layout.getPlid(), true);

			if (!layoutRevisions.isEmpty()) {
				layoutRevision = layoutRevisions.get(0);
			}
		}

		if (layoutRevision == null) {
			return false;
		}

		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		layoutStagingHandler.setLayoutRevision(layoutRevision);

		return true;
	}

	@Reference(unbind = "-")
	protected void setBackgroundTaskLocalService(
		BackgroundTaskLocalService backgroundTaskLocalService) {

		_backgroundTaskLocalService = backgroundTaskLocalService;
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
	protected void setImageLocalService(ImageLocalService imageLocalService) {
		_imageLocalService = imageLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
>>>>>>> compatible
	protected void setLayoutPrototypeLocalService(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {

		_layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutRevisionLocalService(
		LayoutRevisionLocalService layoutRevisionLocalService) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setLayoutSetBranchLocalService(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {
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
	protected void setPortletExportController(
		PortletExportController portletExportController) {
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	protected void setUserLocalService(UserLocalService userLocalService) {
=======
	@Reference(unbind = "-")
	protected void setLayoutRevisionLocalService(
		LayoutRevisionLocalService layoutRevisionLocalService) {

		_layoutRevisionLocalService = layoutRevisionLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutSetBranchLocalService(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {

		_layoutSetBranchLocalService = layoutSetBranchLocalService;
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
	protected void setPortletExportController(
		PortletExportController portletExportController) {

		_portletExportController = portletExportController;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
>>>>>>> compatible
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutExportController.class);

<<<<<<< HEAD
	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

=======
	private BackgroundTaskLocalService _backgroundTaskLocalService;
>>>>>>> compatible
	private final DeletionSystemEventExporter _deletionSystemEventExporter =
		DeletionSystemEventExporter.getInstance();

	@Reference
	private ExportImportHelper _exportImportHelper;

<<<<<<< HEAD
	@Reference
	private ExportImportLifecycleManager _exportImportLifecycleManager;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutPrototypeLocalService _layoutPrototypeLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private LayoutSetPrototypeLocalService _layoutSetPrototypeLocalService;

=======
	private ExportImportLifecycleManager _exportImportLifecycleManager;

	@Reference
	private ExportImportProcessCallbackRegistry
		_exportImportProcessCallbackRegistry;

	private GroupLocalService _groupLocalService;
	private ImageLocalService _imageLocalService;
	private LayoutLocalService _layoutLocalService;
	private LayoutPrototypeLocalService _layoutPrototypeLocalService;
	private LayoutRevisionLocalService _layoutRevisionLocalService;
	private LayoutSetBranchLocalService _layoutSetBranchLocalService;
	private LayoutSetLocalService _layoutSetLocalService;
	private LayoutSetPrototypeLocalService _layoutSetPrototypeLocalService;
>>>>>>> compatible
	private final PermissionExporter _permissionExporter =
		PermissionExporter.getInstance();

	@Reference
	private PortletDataContextFactory _portletDataContextFactory;

	@Reference
<<<<<<< HEAD
	private PortletExportController _portletExportController;

	@Reference
	private UserLocalService _userLocalService;

=======
	private PortletDataHandlerStatusMessageSender
		_portletDataHandlerStatusMessageSender;

	private PortletExportController _portletExportController;
	private final ThemeExporter _themeExporter = ThemeExporter.getInstance();
	private UserLocalService _userLocalService;

	private class UpdateLayoutSetLastPublishDateCallable
		implements Callable<Void> {

		public UpdateLayoutSetLastPublishDateCallable(
			DateRange dateRange, long groupId, boolean privateLayout) {

			_dateRange = dateRange;
			_groupId = groupId;
			_privateLayout = privateLayout;
		}

		@Override
		public Void call() throws PortalException {
			Group group = _groupLocalService.getGroup(_groupId);

			Date endDate = null;

			if (_dateRange != null) {
				endDate = _dateRange.getEndDate();
			}

			if (group.hasStagingGroup()) {
				Group stagingGroup = group.getStagingGroup();

				ExportImportDateUtil.updateLastPublishDate(
					stagingGroup.getGroupId(), _privateLayout, _dateRange,
					endDate);
			}
			else {
				ExportImportDateUtil.updateLastPublishDate(
					_groupId, _privateLayout, _dateRange, endDate);
			}

			return null;
		}

		private final DateRange _dateRange;
		private final long _groupId;
		private final boolean _privateLayout;

	}

>>>>>>> compatible
}