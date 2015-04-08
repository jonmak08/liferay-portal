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

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.persistence.JournalArticleResourceUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 */
public class UpgradeAssetPublisherManualEntries
	extends BaseUpgradePortletPreferences {

	public static void upgradeToAssetEntryIdElement(Element rootElement) {
		Element assetIdElement = rootElement.element("asset-id");

		if (Validator.isNotNull(assetIdElement)) {
			String assetEntryId = assetIdElement.getText();

			Element assetEntryIdElement = rootElement.addElement(
				"assetEntryId");

			assetEntryIdElement.addText(assetEntryId);

			rootElement.remove(assetIdElement);
		}
	}

	public static void upgradeToAssetEntryTypeElement(Element rootElement) {
		Element assetTypeElement = rootElement.element("asset-type");

		if (Validator.isNotNull(assetTypeElement)) {
			String assetEntryType = assetTypeElement.getText();

			Element assetEntryTypeElement = rootElement.addElement(
				"assetEntryType");

			assetEntryTypeElement.addText(assetEntryType);

			rootElement.remove(assetTypeElement);
		}
	}

	public static void upgradeToAssetEntryUuidElement(Element rootElement)
		throws Exception {

		Element assetEntryIdElement = rootElement.element("assetEntryId");

		long assetEntryId = GetterUtil.getLong(assetEntryIdElement.getText());

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select classUuid from AssetEntry where entryId = ?");

			ps.setLong(1, assetEntryId);

			rs = ps.executeQuery();

			if (rs.next()) {
				String classUuid = rs.getString("classUuid");

				Element assetEntryUuidElement = rootElement.addElement(
					"assetEntryUuid");

				assetEntryUuidElement.addText(classUuid);

				rootElement.remove(assetEntryIdElement);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected String[] getAssetEntryXmls(String[] manualEntries)
		throws Exception {

		String[] assetEntryXmls = new String[manualEntries.length];

		for (int i = 0; i < manualEntries.length; i++) {
			String manualEntry = manualEntries[i];

			Document document = SAXReaderUtil.read(manualEntry);

			Element rootElement = document.getRootElement();

			upgradeToAssetEntryIdElement(rootElement);

			upgradeToAssetEntryUuidElement(rootElement);

			upgradeToAssetEntryTypeElement(rootElement);

			assetEntryXmls[i] = document.formattedString(StringPool.BLANK);
		}

		return assetEntryXmls;
	}

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		StringBundler sb = new StringBundler(5);

		sb.append("(portletId like '101_INSTANCE_%') and ((preferences like ");
		sb.append("'%<preference><name>selection-style</name><value>manual");
		sb.append("</value></preference>%') OR (preferences like ");
		sb.append("'%<preference><name>selectionStyle</name><value>manual");
		sb.append("</value></preference>%'))");

		return sb.toString();
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		String[] assetEntryXmls = portletPreferences.getValues(
			"asset-entry-xml", new String[0]);

		if (ArrayUtil.isEmpty(assetEntryXmls)) {
			assetEntryXmls = portletPreferences.getValues(
				"assetEntryXml", new String[0]);
		}

		String[] manualEntries = portletPreferences.getValues(
			"manual-entries", new String[0]);

		if (ArrayUtil.isEmpty(manualEntries)) {
			manualEntries = portletPreferences.getValues(
				"manualEntries", new String[0]);
		}

		if (ArrayUtil.isEmpty(assetEntryXmls) &&
			ArrayUtil.isNotEmpty(manualEntries)) {

			assetEntryXmls = getAssetEntryXmls(manualEntries);

			portletPreferences.setValues("asset-entry-xml", assetEntryXmls);
		}

		if (ArrayUtil.isNotEmpty(assetEntryXmls)) {
			upgradeUuids(assetEntryXmls);

			portletPreferences.setValues("assetEntryXml", assetEntryXmls);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	protected void upgradeUuids(String[] assetEntryXmls) throws Exception {
		for (int i = 0; i < assetEntryXmls.length; i++) {
			String assetEntry = assetEntryXmls[i];

			Document document = SAXReaderUtil.read(assetEntry);

			Element rootElement = document.getRootElement();

			Element assetTypeElementUuid = rootElement.element(
				"asset-entry-uuid");

			String assetUuid = assetTypeElementUuid.getStringValue();

			List<JournalArticle> articles = JournalArticleUtil.findByUuid(
				assetUuid);

			if (articles.size() > 0) {
				JournalArticleResource resource =
					JournalArticleResourceUtil.findByPrimaryKey(
						articles.get(0).getResourcePrimKey());

				rootElement.remove(assetTypeElementUuid);

				assetTypeElementUuid.setText(resource.getUuid());

				rootElement.add(assetTypeElementUuid);

				document.setRootElement(rootElement);

				assetEntryXmls[i] = document.formattedString(StringPool.BLANK);
			}
		}
	}

}