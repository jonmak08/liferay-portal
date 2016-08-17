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
import com.liferay.portal.security.ldap.LDAPOperation;
import com.liferay.portal.security.ldap.LDAPUserTransactionThreadLocal;
import com.liferay.portal.security.ldap.PortalLDAPExporterUtil;

/**
 * @author Marcellus Tavares
 */
public class UserGroupListener extends BaseModelListener<UserGroup> {

	@Override
	public void onAfterAddAssociation(
		Object userGroupId, String associationClassName,
		Object associationClassPK) {

		try {
			if (associationClassName.equals(User.class.getName())) {
				exportToLDAP(
					(Long)associationClassPK, (Long)userGroupId,
					LDAPOperation.ADD);
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to export user group with user ID " +
					associationClassPK + " to LDAP on after add association",
				e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
		Object userGroupId, String associationClassName,
		Object associationClassPK) {

		try {
			if (associationClassName.equals(User.class.getName())) {
				exportToLDAP(
					(Long)associationClassPK, (Long)userGroupId,
					LDAPOperation.REMOVE);
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to export user group with user ID " +
					associationClassPK +
						" to LDAP on after remove association",
				e);
		}
	}

	protected void exportToLDAP(
			long userId, long userGroupId, LDAPOperation ldapOperation)
		throws Exception {

		if (LDAPUserTransactionThreadLocal.isOriginatesFromLDAP()) {
			return;
		}

		PortalLDAPExporterUtil.exportToLDAP(userId, userGroupId, ldapOperation);
	}

	private static Log _log = LogFactoryUtil.getLog(UserGroupListener.class);

}