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

package com.liferay.portal.security.ldap.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(
	category = "foundation", factoryInstanceLabelAttribute = "companyId",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.security.ldap.configuration.LDAPServerConfiguration",
	localization = "content/Language", name = "ldap-server-configuration-name"
)
public interface LDAPServerConfiguration {

	public static final long LDAP_SERVER_ID_DEFAULT = 0;

<<<<<<< HEAD
	@Meta.AD(deflt = "0", name = "company-id", required = false)
=======
	@Meta.AD(deflt = "0", required = false)
>>>>>>> compatible
	public long companyId();

	@Meta.AD(deflt = "0", name = "ldap-server-id", required = false)
	public long ldapServerId();

<<<<<<< HEAD
	@Meta.AD(deflt = "", name = "server-name", required = false)
	public String serverName();

	@Meta.AD(
		deflt = "ldap://localhost:10389", name = "base-provider-url",
		required = false
	)
	public String baseProviderURL();

	@Meta.AD(
		deflt = "", description = "base-dn-help", name = "base-dn",
		required = false
	)
	public String baseDN();

	@Meta.AD(deflt = "", name = "security-principal", required = false)
	public String securityPrincipal();

	@Meta.AD(
		deflt = "secret", name = "security-credential", required = false,
		type = Meta.Type.Password
	)
=======
	@Meta.AD(deflt = "", required = false)
	public String serverName();

	@Meta.AD(deflt = "ldap://localhost:10389", required = false)
	public String baseProviderURL();

	@Meta.AD(deflt = "", description = "base-dn-help", required = false)
	public String baseDN();

	@Meta.AD(deflt = "", required = false)
	public String securityPrincipal();

	@Meta.AD(deflt = "secret", required = false)
>>>>>>> compatible
	public String securityCredential();

	@Meta.AD(
		deflt = "(mail=@email_address@)",
		description = "authentication-search-filter-help",
		name = "authentication-search-filter", required = false
	)
	public String authSearchFilter();

	@Meta.AD(
		deflt = "(objectClass=inetOrgPerson)",
<<<<<<< HEAD
		description = "user-search-filter-help", name = "user-search-filter",
		required = false
=======
		description = "user-search-filter-help", required = false
>>>>>>> compatible
	)
	public String userSearchFilter();

	@Meta.AD(
		deflt = "emailAddress=mail|firstName=givenName|group=groupMembership|jobTitle=title|lastName=sn|password=userPassword|screenName=cn|uuid=uuid",
<<<<<<< HEAD
		description = "user-mappings-help", name = "user-mappings",
		required = false
=======
		description = "user-mappings-help", required = false
>>>>>>> compatible
	)
	public String[] userMappings();

	@Meta.AD(
<<<<<<< HEAD
		deflt = "", description = "user-custom-mappings-help",
		name = "user-custom-mappings", required = false
=======
		deflt = "", description = "user-custom-mappings-help", required = false
>>>>>>> compatible
	)
	public String[] userCustomMappings();

	@Meta.AD(
		deflt = "", description = "user-ignore-attributes-help",
<<<<<<< HEAD
		name = "user-ignore-attributes", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String[] userIgnoreAttributes();

	@Meta.AD(
		deflt = "birthday=|facebookSn=|jabberSn=|skypeSn=|smsSn=|twitterSn=",
<<<<<<< HEAD
		description = "contact-mappings-help", name = "contact-mappings",
		required = false
=======
		description = "contact-mappings-help", required = false
>>>>>>> compatible
	)
	public String[] contactMappings();

	@Meta.AD(
		deflt = "", description = "contact-custom-mappings-help",
<<<<<<< HEAD
		name = "contact-custom-mappings", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String[] contactCustomMappings();

	@Meta.AD(
		deflt = "true", description = "group-search-filter-enabled-help",
<<<<<<< HEAD
		name = "group-search-filter-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean groupSearchFilterEnabled();

	@Meta.AD(
		deflt = "(objectClass=groupOfUniqueNames)",
<<<<<<< HEAD
		description = "group-search-filter-help", name = "group-search-filter",
		required = false
=======
		description = "group-search-filter-help", required = false
>>>>>>> compatible
	)
	public String groupSearchFilter();

	@Meta.AD(
		deflt = "description=description|groupName=cn|user=uniqueMember",
<<<<<<< HEAD
		description = "group-mappings-help", name = "group-mappings",
		required = false
	)
	public String[] groupMappings();

	@Meta.AD(
		deflt = "", description = "users-dn-help", name = "users-dn",
		required = false
	)
=======
		description = "group-mappings-help", required = false
	)
	public String[] groupMappings();

	@Meta.AD(deflt = "", description = "users-dn-help", required = false)
>>>>>>> compatible
	public String usersDN();

	@Meta.AD(
		deflt = "top|person|inetOrgPerson|organizationalPerson",
<<<<<<< HEAD
		description = "user-default-object-classes-help",
		name = "user-default-object-classes", required = false
	)
	public String[] userDefaultObjectClasses();

	@Meta.AD(
		deflt = "", description = "groups-dn-help", name = "groups-dn",
		required = false
	)
=======
		description = "user-default-object-classes-help", required = false
	)
	public String[] userDefaultObjectClasses();

	@Meta.AD(deflt = "", description = "groups-dn-help", required = false)
>>>>>>> compatible
	public String groupsDN();

	@Meta.AD(
		deflt = "top|groupOfUniqueNames",
<<<<<<< HEAD
		description = "group-default-object-classes-help",
		name = "group-default-object-classes", required = false
=======
		description = "group-default-object-classes-help", required = false
>>>>>>> compatible
	)
	public String[] groupDefaultObjectClasses();

}