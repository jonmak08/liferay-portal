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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoNotificationRecipient in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipient
 * @generated
 */
@ProviderType
public class KaleoNotificationRecipientCacheModel implements CacheModel<KaleoNotificationRecipient>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNotificationRecipientCacheModel)) {
			return false;
		}

		KaleoNotificationRecipientCacheModel kaleoNotificationRecipientCacheModel =
			(KaleoNotificationRecipientCacheModel)obj;

		if (kaleoNotificationRecipientId == kaleoNotificationRecipientCacheModel.kaleoNotificationRecipientId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoNotificationRecipientId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationRecipientId=");
		sb.append(kaleoNotificationRecipientId);
		sb.append(", groupId=");
		sb.append(groupId);
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
<<<<<<< HEAD
		sb.append(", kaleoDefinitionVersionId=");
		sb.append(kaleoDefinitionVersionId);
=======
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
>>>>>>> compatible
		sb.append(", kaleoNotificationId=");
		sb.append(kaleoNotificationId);
		sb.append(", recipientClassName=");
		sb.append(recipientClassName);
		sb.append(", recipientClassPK=");
		sb.append(recipientClassPK);
		sb.append(", recipientRoleType=");
		sb.append(recipientRoleType);
		sb.append(", recipientScript=");
		sb.append(recipientScript);
		sb.append(", recipientScriptLanguage=");
		sb.append(recipientScriptLanguage);
		sb.append(", recipientScriptContexts=");
		sb.append(recipientScriptContexts);
		sb.append(", address=");
		sb.append(address);
		sb.append(", notificationReceptionType=");
		sb.append(notificationReceptionType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoNotificationRecipient toEntityModel() {
		KaleoNotificationRecipientImpl kaleoNotificationRecipientImpl = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipientImpl.setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
		kaleoNotificationRecipientImpl.setGroupId(groupId);
		kaleoNotificationRecipientImpl.setCompanyId(companyId);
		kaleoNotificationRecipientImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setUserName("");
=======
			kaleoNotificationRecipientImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoNotificationRecipientImpl.setCreateDate(null);
		}
		else {
			kaleoNotificationRecipientImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoNotificationRecipientImpl.setModifiedDate(null);
		}
		else {
			kaleoNotificationRecipientImpl.setModifiedDate(new Date(
					modifiedDate));
		}

<<<<<<< HEAD
		kaleoNotificationRecipientImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
		kaleoNotificationRecipientImpl.setKaleoNotificationId(kaleoNotificationId);

		if (recipientClassName == null) {
			kaleoNotificationRecipientImpl.setRecipientClassName("");
=======
		kaleoNotificationRecipientImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoNotificationRecipientImpl.setKaleoNotificationId(kaleoNotificationId);

		if (recipientClassName == null) {
			kaleoNotificationRecipientImpl.setRecipientClassName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientClassName(recipientClassName);
		}

		kaleoNotificationRecipientImpl.setRecipientClassPK(recipientClassPK);
		kaleoNotificationRecipientImpl.setRecipientRoleType(recipientRoleType);

		if (recipientScript == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setRecipientScript("");
=======
			kaleoNotificationRecipientImpl.setRecipientScript(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScript(recipientScript);
		}

		if (recipientScriptLanguage == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setRecipientScriptLanguage("");
=======
			kaleoNotificationRecipientImpl.setRecipientScriptLanguage(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScriptLanguage(recipientScriptLanguage);
		}

		if (recipientScriptContexts == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setRecipientScriptContexts("");
=======
			kaleoNotificationRecipientImpl.setRecipientScriptContexts(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScriptContexts(recipientScriptContexts);
		}

		if (address == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setAddress("");
=======
			kaleoNotificationRecipientImpl.setAddress(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setAddress(address);
		}

		if (notificationReceptionType == null) {
<<<<<<< HEAD
			kaleoNotificationRecipientImpl.setNotificationReceptionType("");
=======
			kaleoNotificationRecipientImpl.setNotificationReceptionType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationRecipientImpl.setNotificationReceptionType(notificationReceptionType);
		}

		kaleoNotificationRecipientImpl.resetOriginalValues();

		return kaleoNotificationRecipientImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoNotificationRecipientId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

<<<<<<< HEAD
		kaleoDefinitionVersionId = objectInput.readLong();
=======
		kaleoDefinitionId = objectInput.readLong();
>>>>>>> compatible

		kaleoNotificationId = objectInput.readLong();
		recipientClassName = objectInput.readUTF();

		recipientClassPK = objectInput.readLong();

		recipientRoleType = objectInput.readInt();
		recipientScript = objectInput.readUTF();
		recipientScriptLanguage = objectInput.readUTF();
		recipientScriptContexts = objectInput.readUTF();
		address = objectInput.readUTF();
		notificationReceptionType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoNotificationRecipientId);

		objectOutput.writeLong(groupId);

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

<<<<<<< HEAD
		objectOutput.writeLong(kaleoDefinitionVersionId);
=======
		objectOutput.writeLong(kaleoDefinitionId);
>>>>>>> compatible

		objectOutput.writeLong(kaleoNotificationId);

		if (recipientClassName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recipientClassName);
		}

		objectOutput.writeLong(recipientClassPK);

		objectOutput.writeInt(recipientRoleType);

		if (recipientScript == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recipientScript);
		}

		if (recipientScriptLanguage == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recipientScriptLanguage);
		}

		if (recipientScriptContexts == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recipientScriptContexts);
		}

		if (address == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (notificationReceptionType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(notificationReceptionType);
		}
	}

	public long kaleoNotificationRecipientId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
<<<<<<< HEAD
	public long kaleoDefinitionVersionId;
=======
	public long kaleoDefinitionId;
>>>>>>> compatible
	public long kaleoNotificationId;
	public String recipientClassName;
	public long recipientClassPK;
	public int recipientRoleType;
	public String recipientScript;
	public String recipientScriptLanguage;
	public String recipientScriptContexts;
	public String address;
	public String notificationReceptionType;
}