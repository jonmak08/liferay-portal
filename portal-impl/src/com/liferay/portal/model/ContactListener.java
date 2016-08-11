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

package com.liferay.portal.model;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.ldap.LDAPUserTransactionThreadLocal;
import com.liferay.portal.security.ldap.PortalLDAPExporterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Scott Lee
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ContactListener extends BaseModelListener<Contact> {

	@Override
	public void onAfterCreate(Contact contact) {
		try {
			exportToLDAP(contact);
		}
		catch (Exception e) {
			_log.error(
				"Error exporting contact to LDAP for userId = " +
					contact.getUserId(),
				e);
		}
	}

	@Override
	public void onAfterUpdate(Contact contact) {
		try {
			exportToLDAP(contact);
		}
		catch (Exception e) {
			_log.error(
				"Error exporting contact to LDAP for userId = " +
					contact.getUserId(),
				e);
		}
	}

	protected void exportToLDAP(Contact contact) throws Exception {
		if (LDAPUserTransactionThreadLocal.isOriginatesFromLDAP()) {
			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Map<String, Serializable> expandoBridgeAttributes = null;

		if (serviceContext != null) {
			expandoBridgeAttributes =
				serviceContext.getExpandoBridgeAttributes();
		}

		PortalLDAPExporterUtil.exportToLDAP(contact, expandoBridgeAttributes);
	}

	private static Log _log = LogFactoryUtil.getLog(ContactListener.class);

}