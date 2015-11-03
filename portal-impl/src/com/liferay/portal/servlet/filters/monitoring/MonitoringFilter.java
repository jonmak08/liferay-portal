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

package com.liferay.portal.servlet.filters.monitoring;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleThreadLocal;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.monitoring.statistics.portal.PortalRequestDataSample;
import com.liferay.portal.monitoring.statistics.service.ServiceMonitorAdvice;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.MonitoringPortlet;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 */
public class MonitoringFilter extends BasePortalFilter {

	public static boolean isMonitoringPortalRequest() {
		return _monitoringPortalRequest;
	}

	public static void setMonitoringPortalRequest(
		boolean monitoringPortalRequest) {

		_monitoringPortalRequest = monitoringPortalRequest;
	}

	@Override
	public boolean isFilterEnabled() {
		if (!super.isFilterEnabled()) {
			return false;
		}

		if (!_monitoringPortalRequest &&
			!MonitoringPortlet.isMonitoringPortletActionRequest() &&
			!MonitoringPortlet.isMonitoringPortletEventRequest() &&
			!MonitoringPortlet.isMonitoringPortletRenderRequest() &&
			!MonitoringPortlet.isMonitoringPortletResourceRequest() &&
			!ServiceMonitorAdvice.isActive()) {

			return false;
		}

		return true;
	}

	protected int decrementProcessFilterCount() {
		AtomicInteger processFilterCount = _processFilterCount.get();

		return processFilterCount.decrementAndGet();
	}

	protected long getGroupId(HttpServletRequest request) {
		long groupId = ParamUtil.getLong(request, "groupId");

		if (groupId > 0) {
			return groupId;
		}

		Layout layout = (Layout)request.getAttribute(WebKeys.LAYOUT);

		if (layout != null) {
			return layout.getGroupId();
		}

		long plid = ParamUtil.getLong(request, "p_l_id");

		if (plid > 0) {
			try {
				layout = LayoutLocalServiceUtil.getLayout(plid);

				groupId = layout.getGroupId();
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to retrieve layout " + plid, e);
				}
			}
		}

		return groupId;
	}

	protected String getRemoteUser(HttpServletRequest request) {
		String remoteUser = request.getRemoteUser();

		if (remoteUser == null) {
			remoteUser = String.valueOf(request.getAttribute(WebKeys.USER_ID));
		}

		return remoteUser;
	}

	protected void incrementProcessFilterCount() {
		AtomicInteger processFilterCount = _processFilterCount.get();

		processFilterCount.incrementAndGet();
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws IOException, ServletException {

		long companyId = PortalUtil.getCompanyId(request);
		long groupId = getGroupId(request);
		String remoteUser = getRemoteUser(request);

		PortalRequestDataSample portalRequestDataSample = null;

		incrementProcessFilterCount();

		if (_monitoringPortalRequest) {
			portalRequestDataSample = new PortalRequestDataSample(
				companyId, groupId, request.getHeader(HttpHeaders.REFERER),
				request.getRemoteAddr(), remoteUser, request.getRequestURI(),
				GetterUtil.getString(request.getRequestURL()),
				request.getHeader(HttpHeaders.USER_AGENT));

			DataSampleThreadLocal.initialize();
		}

		try {
			if (portalRequestDataSample != null) {
				portalRequestDataSample.prepare();
			}

			processFilter(
				MonitoringFilter.class, request, response, filterChain);

			if (portalRequestDataSample != null) {
				portalRequestDataSample.capture(RequestStatus.SUCCESS);

				portalRequestDataSample.setGroupId(getGroupId(request));
				portalRequestDataSample.setStatusCode(response.getStatus());
			}
		}
		catch (Exception e) {
			if (portalRequestDataSample != null) {
				portalRequestDataSample.capture(RequestStatus.ERROR);
			}

			if (e instanceof IOException) {
				throw (IOException)e;
			}
			else if (e instanceof ServletException) {
				throw (ServletException)e;
			}
			else {
				throw new ServletException("Unable to execute request", e);
			}
		}
		finally {
			if (portalRequestDataSample != null) {
				DataSampleThreadLocal.addDataSample(portalRequestDataSample);
			}

			if (decrementProcessFilterCount() == 0) {
				MessageBusUtil.sendMessage(
						DestinationNames.MONITORING,
						DataSampleThreadLocal.getDataSamples());

				_processFilterCount.remove();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MonitoringFilter.class);

	private static final ThreadLocal<AtomicInteger> _processFilterCount =
		new AutoResetThreadLocal<AtomicInteger>(
			MonitoringFilter.class + "._processFilterCount",
			new AtomicInteger(0));

	private static boolean _monitoringPortalRequest =
		PropsValues.MONITORING_PORTAL_REQUEST;

}