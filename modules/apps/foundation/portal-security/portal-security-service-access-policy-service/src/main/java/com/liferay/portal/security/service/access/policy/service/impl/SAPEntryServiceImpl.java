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

package com.liferay.portal.security.service.access.policy.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
<<<<<<< HEAD
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.service.access.policy.constants.SAPActionKeys;
import com.liferay.portal.security.service.access.policy.constants.SAPConstants;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.base.SAPEntryServiceBaseImpl;
=======
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.service.access.policy.constants.SAPActionKeys;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.base.SAPEntryServiceBaseImpl;
import com.liferay.portal.security.service.access.policy.service.permission.SAPEntryPermission;
import com.liferay.portal.security.service.access.policy.service.permission.SAPPermission;
>>>>>>> compatible

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SAPEntryServiceImpl extends SAPEntryServiceBaseImpl {

	@Override
	public SAPEntry addSAPEntry(
			String allowedServiceSignatures, boolean defaultSAPEntry,
			boolean enabled, String name, Map<Locale, String> titleMap,
			ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		_portletResourcePermission.check(
			getPermissionChecker(), null, SAPActionKeys.ACTION_ADD_SAP_ENTRY);
=======
		SAPPermission.check(
			getPermissionChecker(), SAPActionKeys.ACTION_ADD_SAP_ENTRY);
>>>>>>> compatible

		return sapEntryLocalService.addSAPEntry(
			getUserId(), allowedServiceSignatures, defaultSAPEntry, enabled,
			name, titleMap, serviceContext);
	}

	@Override
	public SAPEntry deleteSAPEntry(long sapEntryId) throws PortalException {
<<<<<<< HEAD
		_sapEntryFolderModelResourcePermission.check(
=======
		SAPEntryPermission.check(
>>>>>>> compatible
			getPermissionChecker(), sapEntryId, ActionKeys.DELETE);

		return sapEntryLocalService.deleteSAPEntry(sapEntryId);
	}

	@Override
	public SAPEntry deleteSAPEntry(SAPEntry sapEntry) throws PortalException {
<<<<<<< HEAD
		_sapEntryFolderModelResourcePermission.check(
=======
		SAPEntryPermission.check(
>>>>>>> compatible
			getPermissionChecker(), sapEntry, ActionKeys.DELETE);

		return sapEntryLocalService.deleteSAPEntry(sapEntry);
	}

	@Override
	public SAPEntry fetchSAPEntry(long companyId, String name)
		throws PortalException {

		SAPEntry sapEntry = sapEntryPersistence.fetchByC_N(companyId, name);

		if (sapEntry != null) {
<<<<<<< HEAD
			_sapEntryFolderModelResourcePermission.check(
=======
			SAPEntryPermission.check(
>>>>>>> compatible
				getPermissionChecker(), sapEntry, ActionKeys.VIEW);
		}

		return sapEntry;
	}

	@Override
	public List<SAPEntry> getCompanySAPEntries(
		long companyId, int start, int end) {

		return sapEntryPersistence.filterFindByCompanyId(companyId, start, end);
	}

	@Override
	public List<SAPEntry> getCompanySAPEntries(
		long companyId, int start, int end, OrderByComparator<SAPEntry> obc) {

		return sapEntryPersistence.filterFindByCompanyId(
			companyId, start, end, obc);
	}

	@Override
	public int getCompanySAPEntriesCount(long companyId) {
		return sapEntryPersistence.filterCountByCompanyId(companyId);
	}

	@Override
	public SAPEntry getSAPEntry(long sapEntryId) throws PortalException {
<<<<<<< HEAD
		_sapEntryFolderModelResourcePermission.check(
=======
		SAPEntryPermission.check(
>>>>>>> compatible
			getPermissionChecker(), sapEntryId, ActionKeys.VIEW);

		return sapEntryPersistence.findByPrimaryKey(sapEntryId);
	}

	@Override
	public SAPEntry getSAPEntry(long companyId, String name)
		throws PortalException {

		SAPEntry sapEntry = sapEntryPersistence.findByC_N(companyId, name);

<<<<<<< HEAD
		_sapEntryFolderModelResourcePermission.check(
=======
		SAPEntryPermission.check(
>>>>>>> compatible
			getPermissionChecker(), sapEntry, ActionKeys.VIEW);

		return sapEntry;
	}

	@Override
	public SAPEntry updateSAPEntry(
			long sapEntryId, String allowedServiceSignatures,
			boolean defaultSAPEntry, boolean enabled, String name,
			Map<Locale, String> titleMap, ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		_sapEntryFolderModelResourcePermission.check(
=======
		SAPEntryPermission.check(
>>>>>>> compatible
			getPermissionChecker(), sapEntryId, ActionKeys.UPDATE);

		return sapEntryLocalService.updateSAPEntry(
			sapEntryId, allowedServiceSignatures, defaultSAPEntry, enabled,
			name, titleMap, serviceContext);
	}

<<<<<<< HEAD
	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				SAPEntryServiceImpl.class, "_portletResourcePermission",
				SAPConstants.RESOURCE_NAME);
	private static volatile ModelResourcePermission<SAPEntry>
		_sapEntryFolderModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				SAPEntryServiceImpl.class,
				"_sapEntryFolderModelResourcePermission", SAPEntry.class);

=======
>>>>>>> compatible
}