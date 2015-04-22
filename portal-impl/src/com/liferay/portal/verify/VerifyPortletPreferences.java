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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.persistence.PortletPreferencesActionableDynamicQuery;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.assetpublisher.util.AssetPublisher;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.ReadOnlyException;

/**
 * @author Andrew Betts
 * @author Christopher Kian
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

				dynamicQuery.add(plidProperty.in(layoutRevisionDynamicQuery));
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

				if (portletIds.contains(portletPreferences.getPortletId())) {
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

	public static void cleanUpPortletPreferences() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
				getPortletPreferencesWithLayoutsActionableDynamicQuery();

			actionableDynamicQuery.performActions();
	}

	protected static ActionableDynamicQuery
		getPortletPreferencesWithLayoutsActionableDynamicQuery()
			throws SystemException {

		return new PortletPreferencesActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property plidProperty = PropertyFactoryUtil.forName("plid");

				DynamicQuery layoutDynamicQuery =
					LayoutLocalServiceUtil.dynamicQuery();

				layoutDynamicQuery.setProjection(
					ProjectionFactoryUtil.property("plid"));

				dynamicQuery.add(plidProperty.in(layoutDynamicQuery));
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				PortletPreferences portletPreferences =
					(PortletPreferences)object;

				long portletPlid = portletPreferences.getPlid();

				Layout portletLayout = LayoutLocalServiceUtil.getLayout(
						portletPlid);

				if (!portletLayout.isTypePortlet()) {
					return;
				}

				javax.portlet.PortletPreferences strictPortletPreferences =
						PortletPreferencesFactoryUtil.strictFromXML(
								portletLayout.getCompanyId(),
								portletPreferences.getOwnerId(),
								portletPreferences.getOwnerType(), portletPlid,
								portletPreferences.getPortletId(),
								portletPreferences.getPreferences());

				String[] scopeIds = strictPortletPreferences.getValues(
						"scopeIds", null);

				if (ArrayUtil.isNotEmpty(scopeIds)) {
					long[] groupIds = getGroupIds(
							scopeIds, portletLayout.getGroupId());

					ClassName journalClass =
							ClassNameLocalServiceUtil.getClassName(
									journalClassName);

					List<DDMStructure> assetAvailableStructures =
							DDMStructureLocalServiceUtil.getStructures(
									groupIds, journalClass.getClassNameId());

					long[] assetAvailableStructureIds = new long[0];

					for (DDMStructure structure : assetAvailableStructures) {
						assetAvailableStructureIds = ArrayUtil.append(
								assetAvailableStructureIds,
								structure.getStructureId());
					}

					if (ArrayUtil.isNotEmpty(assetAvailableStructureIds)) {
						String formattedFromArray = StringUtil.strip(
								Arrays.toString(assetAvailableStructureIds),
								charsToRemove);

						try {
							strictPortletPreferences.setValue(
									journalFactoryName, formattedFromArray);
						}
						catch (ReadOnlyException roe) {
						}
					}
					else {
						try {
							strictPortletPreferences.reset(journalFactoryName);
						}
						catch (ReadOnlyException roe) {
						}
					}
				}
				else {
					try {
						strictPortletPreferences.reset(journalFactoryName);
					}
					catch (ReadOnlyException roe) {
					}
				}

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Updating Portlet Preferences " +
							portletPreferences.getPortletPreferencesId());
				}

				PortletPreferencesLocalServiceUtil.updatePreferences(
					portletPreferences.getOwnerId(),
					portletPreferences.getOwnerType(), portletPlid,
					portletPreferences.getPortletId(),
					strictPortletPreferences);
			}

		};
	}

	private static long[] getGroupIds(String[] scopeIds, long defaultGroupId) {
		long[] groupIds = new long[0];
		long siteGroupId;

		for (String scopeId : scopeIds) {
			if (scopeId.startsWith(AssetPublisher.SCOPE_ID_GROUP_PREFIX)) {
				String scopeIdSuffix = scopeId.substring(
						AssetPublisher.SCOPE_ID_GROUP_PREFIX.length());

				if (scopeIdSuffix.equals(GroupConstants.DEFAULT)) {
					siteGroupId = defaultGroupId;
				}
				else {
					siteGroupId = Long.valueOf(scopeIdSuffix).longValue();
				}

				groupIds = ArrayUtil.append(groupIds, siteGroupId);
			}
		}

		return groupIds;
	}

	@Override
	protected void doVerify() throws Exception {
		cleanUpLayoutRevisionPortletPreferences();
		cleanUpPortletPreferences();
	}

	private final static String journalClassName =
			"com.liferay.portlet.journal.model.JournalArticle";

	private final static String journalFactoryName =
			"classTypeIdsJournalArticleAssetRendererFactory";

	private final static char[] charsToRemove =
			{CharPool.OPEN_BRACKET, CharPool.CLOSE_BRACKET, CharPool.SPACE};

	private static Log _log = LogFactoryUtil.getLog(
		VerifyPortletPreferences.class);

}