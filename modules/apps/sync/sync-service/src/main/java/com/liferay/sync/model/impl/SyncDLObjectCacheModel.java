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

package com.liferay.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import com.liferay.sync.model.SyncDLObject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncDLObject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObject
 * @generated
 */
@ProviderType
public class SyncDLObjectCacheModel implements CacheModel<SyncDLObject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLObjectCacheModel)) {
			return false;
		}

		SyncDLObjectCacheModel syncDLObjectCacheModel = (SyncDLObjectCacheModel)obj;

		if (syncDLObjectId == syncDLObjectCacheModel.syncDLObjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncDLObjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{syncDLObjectId=");
		sb.append(syncDLObjectId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", modifiedTime=");
		sb.append(modifiedTime);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append(", treePath=");
		sb.append(treePath);
		sb.append(", name=");
		sb.append(name);
		sb.append(", extension=");
		sb.append(extension);
		sb.append(", mimeType=");
		sb.append(mimeType);
		sb.append(", description=");
		sb.append(description);
		sb.append(", changeLog=");
		sb.append(changeLog);
		sb.append(", extraSettings=");
		sb.append(extraSettings);
		sb.append(", version=");
		sb.append(version);
		sb.append(", versionId=");
		sb.append(versionId);
		sb.append(", size=");
		sb.append(size);
		sb.append(", checksum=");
		sb.append(checksum);
		sb.append(", event=");
		sb.append(event);
		sb.append(", lanTokenKey=");
		sb.append(lanTokenKey);
		sb.append(", lastPermissionChangeDate=");
		sb.append(lastPermissionChangeDate);
		sb.append(", lockExpirationDate=");
		sb.append(lockExpirationDate);
		sb.append(", lockUserId=");
		sb.append(lockUserId);
		sb.append(", lockUserName=");
		sb.append(lockUserName);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typePK=");
		sb.append(typePK);
		sb.append(", typeUuid=");
		sb.append(typeUuid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncDLObject toEntityModel() {
		SyncDLObjectImpl syncDLObjectImpl = new SyncDLObjectImpl();

		syncDLObjectImpl.setSyncDLObjectId(syncDLObjectId);
		syncDLObjectImpl.setCompanyId(companyId);
		syncDLObjectImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setUserName("");
=======
			syncDLObjectImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setUserName(userName);
		}

		syncDLObjectImpl.setCreateTime(createTime);
		syncDLObjectImpl.setModifiedTime(modifiedTime);
		syncDLObjectImpl.setRepositoryId(repositoryId);
		syncDLObjectImpl.setParentFolderId(parentFolderId);

		if (treePath == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setTreePath("");
=======
			syncDLObjectImpl.setTreePath(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setTreePath(treePath);
		}

		if (name == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setName("");
=======
			syncDLObjectImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setName(name);
		}

		if (extension == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setExtension("");
=======
			syncDLObjectImpl.setExtension(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setExtension(extension);
		}

		if (mimeType == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setMimeType("");
=======
			syncDLObjectImpl.setMimeType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setMimeType(mimeType);
		}

		if (description == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setDescription("");
=======
			syncDLObjectImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setDescription(description);
		}

		if (changeLog == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setChangeLog("");
=======
			syncDLObjectImpl.setChangeLog(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setChangeLog(changeLog);
		}

		if (extraSettings == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setExtraSettings("");
=======
			syncDLObjectImpl.setExtraSettings(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setExtraSettings(extraSettings);
		}

		if (version == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setVersion("");
=======
			syncDLObjectImpl.setVersion(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setVersion(version);
		}

		syncDLObjectImpl.setVersionId(versionId);
		syncDLObjectImpl.setSize(size);

		if (checksum == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setChecksum("");
=======
			syncDLObjectImpl.setChecksum(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setChecksum(checksum);
		}

		if (event == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setEvent("");
=======
			syncDLObjectImpl.setEvent(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setEvent(event);
		}

		if (lanTokenKey == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setLanTokenKey("");
=======
			syncDLObjectImpl.setLanTokenKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setLanTokenKey(lanTokenKey);
		}

		if (lastPermissionChangeDate == Long.MIN_VALUE) {
			syncDLObjectImpl.setLastPermissionChangeDate(null);
		}
		else {
			syncDLObjectImpl.setLastPermissionChangeDate(new Date(
					lastPermissionChangeDate));
		}

		if (lockExpirationDate == Long.MIN_VALUE) {
			syncDLObjectImpl.setLockExpirationDate(null);
		}
		else {
			syncDLObjectImpl.setLockExpirationDate(new Date(lockExpirationDate));
		}

		syncDLObjectImpl.setLockUserId(lockUserId);

		if (lockUserName == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setLockUserName("");
=======
			syncDLObjectImpl.setLockUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setLockUserName(lockUserName);
		}

		if (type == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setType("");
=======
			syncDLObjectImpl.setType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setType(type);
		}

		syncDLObjectImpl.setTypePK(typePK);

		if (typeUuid == null) {
<<<<<<< HEAD
			syncDLObjectImpl.setTypeUuid("");
=======
			syncDLObjectImpl.setTypeUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			syncDLObjectImpl.setTypeUuid(typeUuid);
		}

		syncDLObjectImpl.resetOriginalValues();

		return syncDLObjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		syncDLObjectId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		createTime = objectInput.readLong();

		modifiedTime = objectInput.readLong();

		repositoryId = objectInput.readLong();

		parentFolderId = objectInput.readLong();
		treePath = objectInput.readUTF();
		name = objectInput.readUTF();
		extension = objectInput.readUTF();
		mimeType = objectInput.readUTF();
		description = objectInput.readUTF();
		changeLog = objectInput.readUTF();
		extraSettings = objectInput.readUTF();
		version = objectInput.readUTF();

		versionId = objectInput.readLong();

		size = objectInput.readLong();
		checksum = objectInput.readUTF();
		event = objectInput.readUTF();
		lanTokenKey = objectInput.readUTF();
		lastPermissionChangeDate = objectInput.readLong();
		lockExpirationDate = objectInput.readLong();

		lockUserId = objectInput.readLong();
		lockUserName = objectInput.readUTF();
		type = objectInput.readUTF();

		typePK = objectInput.readLong();
		typeUuid = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(syncDLObjectId);

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

		objectOutput.writeLong(createTime);

		objectOutput.writeLong(modifiedTime);

		objectOutput.writeLong(repositoryId);

		objectOutput.writeLong(parentFolderId);

		if (treePath == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(treePath);
		}

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

		if (extension == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(extension);
		}

		if (mimeType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(mimeType);
		}

		if (description == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (changeLog == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(changeLog);
		}

		if (extraSettings == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(extraSettings);
		}

		if (version == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(version);
		}

		objectOutput.writeLong(versionId);

		objectOutput.writeLong(size);

		if (checksum == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(checksum);
		}

		if (event == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(event);
		}

		if (lanTokenKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(lanTokenKey);
		}

		objectOutput.writeLong(lastPermissionChangeDate);
		objectOutput.writeLong(lockExpirationDate);

		objectOutput.writeLong(lockUserId);

		if (lockUserName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(lockUserName);
		}

		if (type == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(typePK);

		if (typeUuid == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(typeUuid);
		}
	}

	public long syncDLObjectId;
	public long companyId;
	public long userId;
	public String userName;
	public long createTime;
	public long modifiedTime;
	public long repositoryId;
	public long parentFolderId;
	public String treePath;
	public String name;
	public String extension;
	public String mimeType;
	public String description;
	public String changeLog;
	public String extraSettings;
	public String version;
	public long versionId;
	public long size;
	public String checksum;
	public String event;
	public String lanTokenKey;
	public long lastPermissionChangeDate;
	public long lockExpirationDate;
	public long lockUserId;
	public String lockUserName;
	public String type;
	public long typePK;
	public String typeUuid;
}