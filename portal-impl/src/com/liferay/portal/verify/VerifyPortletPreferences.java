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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.persistence.PortletPreferencesActionableDynamicQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Betts
 */
public class VerifyPortletPreferences extends VerifyProcess {

	public static void cleanUpLayoutRevisionPortletPreferences()
		throws Exception {

		ActionableDynamicQuery actionableDynamicQuery =
			getPortletPreferencesActionableDynamicQuery();

		actionableDynamicQuery.performActions();
	}

	protected static ActionableDynamicQuery
		getPortletPreferencesActionableDynamicQuery() throws SystemException {

		return new PortletPreferencesActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property plidProperty = PropertyFactoryUtil.forName("plid");

				DynamicQuery layoutRevisionDynamicQuery =
					LayoutRevisionLocalServiceUtil.dynamicQuery();

				layoutRevisionDynamicQuery.setProjection(
					ProjectionFactoryUtil.property("layoutRevisionId"));

				dynamicQuery.add(
					plidProperty.in(layoutRevisionDynamicQuery));
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				PortletPreferences portletPreferences =
					(PortletPreferences)object;

				long layoutRevisionId = portletPreferences.getPlid();

				LayoutRevision layoutRevision =
					LayoutRevisionLocalServiceUtil.getLayoutRevision(
						layoutRevisionId);

				Layout layout = LayoutLocalServiceUtil.getLayout(
					layoutRevision.getPlid());

				if (!layout.isTypePortlet()) {
					return;
				}

				LayoutTypePortlet layoutTypePortlet =
					(LayoutTypePortlet)layout.getLayoutType();

				List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

				List<String> portletIds = new ArrayList<String>(
					portlets.size());

				for (Portlet portlet : portlets) {
					portletIds.add(Portlet.PORTLET_ID_ACCESSOR.get(portlet));
				}

				if (portletIds.contains(
						portletPreferences.getPortletId())) {

					return;
				}

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Removing portlet preferences " +
							portletPreferences.getPortletPreferencesId());
				}

				PortletPreferencesLocalServiceUtil.deletePortletPreferences(
					portletPreferences);
			}

		};
	}

	@Override
	protected void doVerify() throws Exception {
		cleanUpLayoutRevisionPortletPreferences();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		VerifyPortletPreferences.class);

}