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

package com.liferay.change.tracking.service.impl;

import com.liferay.change.tracking.exception.DuplicateCTEntryException;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.base.CTEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Daniel Kocsis
 */
public class CTEntryLocalServiceImpl extends CTEntryLocalServiceBaseImpl {

	@Override
	public CTEntry addCTEntry(
			long userId, long classNameId, long classPK, long resourcePrimKey,
			long ctCollectionId, ServiceContext serviceContext)
		throws PortalException {

		_validate(classNameId, classPK, ctCollectionId);

		long ctEntryId = counterLocalService.increment();

		CTEntry ctEntry = ctEntryPersistence.create(ctEntryId);

		User user = userLocalService.getUser(userId);

		ctEntry.setCompanyId(user.getCompanyId());
		ctEntry.setUserId(user.getUserId());
		ctEntry.setUserName(user.getFullName());

		Date now = new Date();

		ctEntry.setCreateDate(serviceContext.getCreateDate(now));
		ctEntry.setModifiedDate(serviceContext.getModifiedDate(now));

		ctEntry.setClassNameId(classNameId);
		ctEntry.setClassPK(classPK);
		ctEntry.setResourcePrimKey(resourcePrimKey);

		ctEntry = ctEntryPersistence.update(ctEntry);

		ctCollectionLocalService.addCTEntryCTCollection(
			ctEntry.getCtEntryId(), ctCollectionId);

		return ctEntry;
	}

	@Override
	public List<CTEntry> fetchCTEntries(
		long collectionId, long resourcePrimKey,
		QueryDefinition<CTEntry> queryDefinition) {

		return ctEntryFinder.findByCTCollectionId(
			collectionId, resourcePrimKey, queryDefinition);
	}

	@Override
	public List<CTEntry> fetchCTEntries(
		long collectionId, QueryDefinition<CTEntry> queryDefinition) {

		return ctEntryFinder.findByCTCollectionId(
			collectionId, 0, queryDefinition);
	}

	@Override
	public CTEntry fetchCTEntry(long classNameId, long classPK) {
		return ctEntryPersistence.fetchByC_C(classNameId, classPK);
	}

	private void _validate(long classNameId, long classPK, long ctCollectionId)
		throws PortalException {

		CTEntry ctEntry = ctEntryPersistence.fetchByC_C(classNameId, classPK);

		if (ctEntry != null) {
			throw new DuplicateCTEntryException();
		}

		ctCollectionLocalService.getCTCollection(ctCollectionId);
	}

}