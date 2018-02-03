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

package com.liferay.opensocial.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.model.OAuthConsumer;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OAuthConsumer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumer
 * @generated
 */
@ProviderType
public class OAuthConsumerCacheModel implements CacheModel<OAuthConsumer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConsumerCacheModel)) {
			return false;
		}

		OAuthConsumerCacheModel oAuthConsumerCacheModel = (OAuthConsumerCacheModel)obj;

		if (oAuthConsumerId == oAuthConsumerCacheModel.oAuthConsumerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, oAuthConsumerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{oAuthConsumerId=");
		sb.append(oAuthConsumerId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", gadgetKey=");
		sb.append(gadgetKey);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", consumerKey=");
		sb.append(consumerKey);
		sb.append(", consumerSecret=");
		sb.append(consumerSecret);
		sb.append(", keyType=");
		sb.append(keyType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OAuthConsumer toEntityModel() {
		OAuthConsumerImpl oAuthConsumerImpl = new OAuthConsumerImpl();

		oAuthConsumerImpl.setOAuthConsumerId(oAuthConsumerId);
		oAuthConsumerImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			oAuthConsumerImpl.setCreateDate(null);
		}
		else {
			oAuthConsumerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthConsumerImpl.setModifiedDate(null);
		}
		else {
			oAuthConsumerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (gadgetKey == null) {
<<<<<<< HEAD
			oAuthConsumerImpl.setGadgetKey("");
=======
			oAuthConsumerImpl.setGadgetKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			oAuthConsumerImpl.setGadgetKey(gadgetKey);
		}

		if (serviceName == null) {
<<<<<<< HEAD
			oAuthConsumerImpl.setServiceName("");
=======
			oAuthConsumerImpl.setServiceName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			oAuthConsumerImpl.setServiceName(serviceName);
		}

		if (consumerKey == null) {
<<<<<<< HEAD
			oAuthConsumerImpl.setConsumerKey("");
=======
			oAuthConsumerImpl.setConsumerKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			oAuthConsumerImpl.setConsumerKey(consumerKey);
		}

		if (consumerSecret == null) {
<<<<<<< HEAD
			oAuthConsumerImpl.setConsumerSecret("");
=======
			oAuthConsumerImpl.setConsumerSecret(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			oAuthConsumerImpl.setConsumerSecret(consumerSecret);
		}

		if (keyType == null) {
<<<<<<< HEAD
			oAuthConsumerImpl.setKeyType("");
=======
			oAuthConsumerImpl.setKeyType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			oAuthConsumerImpl.setKeyType(keyType);
		}

		oAuthConsumerImpl.resetOriginalValues();

		return oAuthConsumerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		oAuthConsumerId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		gadgetKey = objectInput.readUTF();
		serviceName = objectInput.readUTF();
		consumerKey = objectInput.readUTF();
		consumerSecret = objectInput.readUTF();
		keyType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(oAuthConsumerId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (gadgetKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(gadgetKey);
		}

		if (serviceName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		if (consumerKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(consumerKey);
		}

		if (consumerSecret == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(consumerSecret);
		}

		if (keyType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(keyType);
		}
	}

	public long oAuthConsumerId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String gadgetKey;
	public String serviceName;
	public String consumerKey;
	public String consumerSecret;
	public String keyType;
}