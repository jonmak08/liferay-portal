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

package com.liferay.asset.publisher.web;

<<<<<<< HEAD
import com.liferay.asset.publisher.web.internal.util.AssetPublisherWebUtil;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
=======
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.journal.service.JournalArticleLocalService;
>>>>>>> compatible
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletLayoutListenerException;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.util.PortletKeys;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.UnicodeProperties;
=======
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.asset.util.AssetUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
>>>>>>> compatible

/**
 * Provides the implementation of <code>PortletLayoutListener</code> (in
 * <code>com.liferay.portal.kernel</code>) for the Asset Publisher portlet so
 * email subscriptions can be removed when the Asset Publisher is removed from
 * the page.
 *
<<<<<<< HEAD
 * @author     Zsolt Berentey
 * @deprecated As of 2.0.0, with not direct replacement
 */
@Deprecated
=======
 * @author Zsolt Berentey
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AssetPublisherPortletKeys.ASSET_PUBLISHER
	},
	service = PortletLayoutListener.class
)
>>>>>>> compatible
public class AssetPublisherPortletLayoutListener
	implements PortletLayoutListener {

	@Override
	public void onAddToLayout(String portletId, long plid) {
	}

	@Override
	public void onMoveInLayout(String portletId, long plid) {
	}

	@Override
	public void onRemoveFromLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		try {
			Layout layout = _layoutLocalService.getLayout(plid);

<<<<<<< HEAD
			if (_assetPublisherWebUtil.isDefaultAssetPublisher(
=======
			if (AssetUtil.isDefaultAssetPublisher(
>>>>>>> compatible
					layout, portletId, StringPool.BLANK)) {

				_journalArticleLocalService.deleteLayoutArticleReferences(
					layout.getGroupId(), layout.getUuid());
			}

			long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

			if (PortletIdCodec.hasUserId(portletId)) {
				ownerType = PortletKeys.PREFS_OWNER_TYPE_USER;
				ownerId = PortletIdCodec.decodeUserId(portletId);
			}

			_subscriptionLocalService.deleteSubscriptions(
				layout.getCompanyId(), PortletPreferences.class.getName(),
<<<<<<< HEAD
				_assetPublisherWebUtil.getSubscriptionClassPK(
=======
				AssetPublisherUtil.getSubscriptionClassPK(
>>>>>>> compatible
					ownerId, ownerType, plid, portletId));
		}
		catch (Exception e) {
			throw new PortletLayoutListenerException(e);
		}
	}

	@Override
	public void onSetup(String portletId, long plid) {
	}

	@Override
	public void updatePropertiesOnRemoveFromLayout(
			String portletId, UnicodeProperties typeSettingsProperties)
		throws PortletLayoutListenerException {

		String defaultAssetPublisherPortletId =
			typeSettingsProperties.getProperty(
				LayoutTypePortletConstants.DEFAULT_ASSET_PUBLISHER_PORTLET_ID);

		if (portletId.equals(defaultAssetPublisherPortletId)) {
			typeSettingsProperties.setProperty(
				LayoutTypePortletConstants.DEFAULT_ASSET_PUBLISHER_PORTLET_ID,
				StringPool.BLANK);
		}
	}

<<<<<<< HEAD
	protected void setAssetPublisherWebUtil(
		AssetPublisherWebUtil assetPublisherWebUtil) {

		_assetPublisherWebUtil = assetPublisherWebUtil;
	}

=======
	@Reference(unbind = "-")
>>>>>>> compatible
	protected void setJournalArticleLocalService(
		JournalArticleLocalService journalArticleLocalService) {

		_journalArticleLocalService = journalArticleLocalService;
	}

<<<<<<< HEAD
=======
	@Reference(unbind = "-")
>>>>>>> compatible
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

<<<<<<< HEAD
=======
	@Reference(unbind = "-")
>>>>>>> compatible
	protected void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

<<<<<<< HEAD
	private AssetPublisherWebUtil _assetPublisherWebUtil;
=======
>>>>>>> compatible
	private JournalArticleLocalService _journalArticleLocalService;
	private LayoutLocalService _layoutLocalService;
	private SubscriptionLocalService _subscriptionLocalService;

}