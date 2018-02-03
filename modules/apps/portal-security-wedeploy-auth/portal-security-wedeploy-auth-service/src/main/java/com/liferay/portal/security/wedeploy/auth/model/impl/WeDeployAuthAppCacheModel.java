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

package com.liferay.portal.security.wedeploy.auth.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WeDeployAuthApp in entity cache.
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthApp
 * @generated
 */
@ProviderType
public class WeDeployAuthAppCacheModel implements CacheModel<WeDeployAuthApp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeDeployAuthAppCacheModel)) {
			return false;
		}

		WeDeployAuthAppCacheModel weDeployAuthAppCacheModel = (WeDeployAuthAppCacheModel)obj;

		if (weDeployAuthAppId == weDeployAuthAppCacheModel.weDeployAuthAppId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, weDeployAuthAppId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{weDeployAuthAppId=");
		sb.append(weDeployAuthAppId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", redirectURI=");
		sb.append(redirectURI);
		sb.append(", clientId=");
		sb.append(clientId);
		sb.append(", clientSecret=");
		sb.append(clientSecret);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WeDeployAuthApp toEntityModel() {
		WeDeployAuthAppImpl weDeployAuthAppImpl = new WeDeployAuthAppImpl();

		weDeployAuthAppImpl.setWeDeployAuthAppId(weDeployAuthAppId);
		weDeployAuthAppImpl.setCompanyId(companyId);
		weDeployAuthAppImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			weDeployAuthAppImpl.setUserName("");
=======
			weDeployAuthAppImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			weDeployAuthAppImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			weDeployAuthAppImpl.setCreateDate(null);
		}
		else {
			weDeployAuthAppImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			weDeployAuthAppImpl.setModifiedDate(null);
		}
		else {
			weDeployAuthAppImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
<<<<<<< HEAD
			weDeployAuthAppImpl.setName("");
=======
			weDeployAuthAppImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			weDeployAuthAppImpl.setName(name);
		}

		if (redirectURI == null) {
<<<<<<< HEAD
			weDeployAuthAppImpl.setRedirectURI("");
=======
			weDeployAuthAppImpl.setRedirectURI(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			weDeployAuthAppImpl.setRedirectURI(redirectURI);
		}

		if (clientId == null) {
<<<<<<< HEAD
			weDeployAuthAppImpl.setClientId("");
=======
			weDeployAuthAppImpl.setClientId(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			weDeployAuthAppImpl.setClientId(clientId);
		}

		if (clientSecret == null) {
<<<<<<< HEAD
			weDeployAuthAppImpl.setClientSecret("");
=======
			weDeployAuthAppImpl.setClientSecret(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			weDeployAuthAppImpl.setClientSecret(clientSecret);
		}

		weDeployAuthAppImpl.resetOriginalValues();

		return weDeployAuthAppImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		weDeployAuthAppId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		redirectURI = objectInput.readUTF();
		clientId = objectInput.readUTF();
		clientSecret = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(weDeployAuthAppId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (redirectURI == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(redirectURI);
		}

		if (clientId == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(clientId);
		}

		if (clientSecret == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(clientSecret);
		}
	}

	public long weDeployAuthAppId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String redirectURI;
	public String clientId;
	public String clientSecret;
}