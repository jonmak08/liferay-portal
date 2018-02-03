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

package com.liferay.exportimport.internal.lifecycle;

import com.liferay.exportimport.kernel.lar.ExportImportClassedModelUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
<<<<<<< HEAD
import com.liferay.exportimport.kernel.lifecycle.EventAwareExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.petra.string.StringPool;
=======
import com.liferay.exportimport.kernel.lifecycle.BaseExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
>>>>>>> compatible
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = ExportImportLifecycleListener.class)
public class LoggerExportImportLifecycleListener
<<<<<<< HEAD
	implements EventAwareExportImportLifecycleListener {

	public String getStagedModelLogFragment(StagedModel stagedModel) {
=======
	extends BaseExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	@Override
	public void onExportImportLifecycleEvent(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		super.onExportImportLifecycleEvent(exportImportLifecycleEvent);
	}

	protected String getStagedModelLogFragment(StagedModel stagedModel) {
>>>>>>> compatible
		StringBundler sb = new StringBundler(8);

		sb.append(StringPool.OPEN_CURLY_BRACE);
		sb.append("class: ");
		sb.append(ExportImportClassedModelUtil.getClassName(stagedModel));

		if (stagedModel instanceof StagedGroupedModel) {
			StagedGroupedModel stagedGroupedModel =
				(StagedGroupedModel)stagedModel;

			sb.append(", groupId: ");
			sb.append(stagedGroupedModel.getGroupId());
		}

		sb.append(", uuid: ");
		sb.append(stagedModel.getUuid());
		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}

	@Override
<<<<<<< HEAD
	public boolean isParallel() {
		return false;
	}

	@Override
	public void onExportImportLifecycleEvent(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}
	}

	@Override
	public void onLayoutExportFailed(
=======
	protected void onLayoutExportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout export failed for group " + portletDataContext.getGroupId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onLayoutExportStarted(PortletDataContext portletDataContext)
=======
	protected void onLayoutExportStarted(PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout export started for group " +
				portletDataContext.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutExportSucceeded(PortletDataContext portletDataContext)
=======
	protected void onLayoutExportSucceeded(
			PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout export succeeded for group " +
				portletDataContext.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutImportFailed(
=======
	protected void onLayoutImportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout import failed for group " + portletDataContext.getGroupId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onLayoutImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {
	}

	@Override
	public void onLayoutImportStarted(PortletDataContext portletDataContext)
=======
	protected void onLayoutImportStarted(PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout import started for group " +
				portletDataContext.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutImportSucceeded(PortletDataContext portletDataContext)
=======
	protected void onLayoutImportSucceeded(
			PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout import succeeded for group " +
				portletDataContext.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutLocalPublicationFailed(
=======
	protected void onLayoutLocalPublicationFailed(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration,
			Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout publication failed for group " +
				exportImportConfiguration.getGroupId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onLayoutLocalPublicationStarted(
=======
	protected void onLayoutLocalPublicationStarted(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout publication started for group " +
				exportImportConfiguration.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutLocalPublicationSucceeded(
=======
	protected void onLayoutLocalPublicationSucceeded(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout publication succeeded for group " +
				exportImportConfiguration.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutRemotePublicationFailed(
=======
	protected void onLayoutRemotePublicationFailed(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration,
			Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout remote publication failed for group " +
				exportImportConfiguration.getGroupId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onLayoutRemotePublicationStarted(
=======
	protected void onLayoutRemotePublicationStarted(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout publication started for group " +
				exportImportConfiguration.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onLayoutRemotePublicationSucceeded(
=======
	protected void onLayoutRemotePublicationSucceeded(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Layout remote publication succeeded for group " +
				exportImportConfiguration.getGroupId());
	}

	@Override
<<<<<<< HEAD
	public void onPortletExportFailed(
=======
	protected void onPortletExportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet export failed for portlet " +
				portletDataContext.getPortletId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onPortletExportStarted(PortletDataContext portletDataContext)
=======
	protected void onPortletExportStarted(PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet export started for portlet " +
				portletDataContext.getPortletId());
	}

	@Override
<<<<<<< HEAD
	public void onPortletExportSucceeded(PortletDataContext portletDataContext)
=======
	protected void onPortletExportSucceeded(
			PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet export succeeded for portlet " +
				portletDataContext.getPortletId());
	}

	@Override
<<<<<<< HEAD
	public void onPortletImportFailed(
=======
	protected void onPortletImportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet import failed for portlet " +
				portletDataContext.getPortletId(),
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onPortletImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {
	}

	@Override
	public void onPortletImportStarted(PortletDataContext portletDataContext)
=======
	protected void onPortletImportStarted(PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet import started for portlet " +
				portletDataContext.getPortletId());
	}

	@Override
<<<<<<< HEAD
	public void onPortletImportSucceeded(PortletDataContext portletDataContext)
=======
	protected void onPortletImportSucceeded(
			PortletDataContext portletDataContext)
>>>>>>> compatible
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Portlet import succeeded for portlet " +
				portletDataContext.getPortletId());
	}

	@Override
<<<<<<< HEAD
	public void onPortletPublicationFailed(
=======
	protected void onPortletPublicationFailed(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration,
			Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		String portletId = MapUtil.getString(settingsMap, "portletId");

		_log.debug(
			"Portlet publication failed for portlet " + portletId, throwable);
	}

	@Override
<<<<<<< HEAD
	public void onPortletPublicationStarted(
=======
	protected void onPortletPublicationStarted(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		String portletId = MapUtil.getString(settingsMap, "portletId");

		_log.debug("Portlet publication started for portlet " + portletId);
	}

	@Override
<<<<<<< HEAD
	public void onPortletPublicationSucceeded(
=======
	protected void onPortletPublicationSucceeded(
>>>>>>> compatible
			ExportImportConfiguration exportImportConfiguration)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		String portletId = MapUtil.getString(settingsMap, "portletId");

		_log.debug("Portlet publication succeeded for portlet " + portletId);
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelExportFailed(
=======
	protected void onStagedModelExportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel,
			Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" export failed",
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelExportStarted(
=======
	protected void onStagedModelExportStarted(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" export started");
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelExportSucceeded(
=======
	protected void onStagedModelExportSucceeded(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" export succeeded");
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelImportFailed(
=======
	protected void onStagedModelImportFailed(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel,
			Throwable throwable)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" import failed",
			throwable);
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelImportStarted(
=======
	protected void onStagedModelImportStarted(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" import started");
	}

	@Override
<<<<<<< HEAD
	public void onStagedModelImportSucceeded(
=======
	protected void onStagedModelImportSucceeded(
>>>>>>> compatible
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Staged model " + getStagedModelLogFragment(stagedModel) +
				" import succeeded");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoggerExportImportLifecycleListener.class);

}