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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Kenneth Chang
 */
public class VerifyLayout extends VerifyProcess {

	protected void deleteOrphanedLayouts() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select plid from Layout where layoutPrototypeUuid != ''");

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");

				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				LayoutPrototype layoutPrototype =
					LayoutPrototypeLocalServiceUtil.
						fetchLayoutPrototypeByUuidAndCompanyId(
							layout.getLayoutPrototypeUuid(),
							layout.getCompanyId());

				if (layoutPrototype == null) {
					String name = layout.getName(LocaleUtil.getDefault());

					if (layout.isLayoutPrototypeLinkEnabled()) {
						if (_log.isInfoEnabled()) {
							_log.info(
								"Deleting page \"" + name + "\" because it " +
									"is propagated from a deleted page " +
										"template");
						}

						LayoutLocalServiceUtil.deleteLayout(layout);
					}
					else {
						if (_log.isInfoEnabled()) {
							_log.info(
								"Removing reference to deleted page template " +
									"from page \"" + name + "\"");
						}

						removeLayoutPrototypeUuid(plid);
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	@Override
	protected void doVerify() throws Exception {
		deleteOrphanedLayouts();
		verifyFriendlyURL();
		verifyUuid();
	}

	protected void removeLayoutPrototypeUuid(long plid) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update layout set layoutPrototypeUuid = '' where plid = " +
					plid);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void verifyFriendlyURL() throws Exception {
		List<Layout> layouts =
			LayoutLocalServiceUtil.getNullFriendlyURLLayouts();

		for (Layout layout : layouts) {
			List<LayoutFriendlyURL> layoutFriendlyURLs =
				LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
					layout.getPlid());

			for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
				String friendlyURL = StringPool.SLASH + layout.getLayoutId();

				LayoutLocalServiceUtil.updateFriendlyURL(
					layout.getPlid(), friendlyURL,
					layoutFriendlyURL.getLanguageId());
			}
		}
	}

	protected void verifyUuid() throws Exception {
		verifyUuid("AssetEntry");
		verifyUuid("JournalArticle");

		StringBundler sb = new StringBundler(3);

		sb.append("update Layout set uuid_ = sourcePrototypeLayoutUuid where ");
		sb.append("sourcePrototypeLayoutUuid != '' and ");
		sb.append("uuid_ != sourcePrototypeLayoutUuid");

		runSQL(sb.toString());
	}

	protected void verifyUuid(String tableName) throws Exception {
		StringBundler sb = new StringBundler(12);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set layoutUuid = (select distinct ");
		sb.append("sourcePrototypeLayoutUuid from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid) where exists (select 1 from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid and Layout.uuid_ != ");
		sb.append("Layout.sourcePrototypeLayoutUuid and ");
		sb.append("Layout.sourcePrototypeLayoutUuid != '')");

		runSQL(sb.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyLayout.class);

}