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

package com.liferay.dynamic.data.lists.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;

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
 * The cache model class for representing DDLRecordSet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSet
 * @generated
 */
@ProviderType
public class DDLRecordSetCacheModel implements CacheModel<DDLRecordSet>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDLRecordSetCacheModel)) {
			return false;
		}

		DDLRecordSetCacheModel ddlRecordSetCacheModel = (DDLRecordSetCacheModel)obj;

		if (recordSetId == ddlRecordSetCacheModel.recordSetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, recordSetId);
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		StringBundler sb = new StringBundler(39);
=======
		StringBundler sb = new StringBundler(33);
>>>>>>> compatible

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", recordSetId=");
		sb.append(recordSetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
<<<<<<< HEAD
		sb.append(", versionUserId=");
		sb.append(versionUserId);
		sb.append(", versionUserName=");
		sb.append(versionUserName);
=======
>>>>>>> compatible
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", DDMStructureId=");
		sb.append(DDMStructureId);
		sb.append(", recordSetKey=");
		sb.append(recordSetKey);
<<<<<<< HEAD
		sb.append(", version=");
		sb.append(version);
=======
>>>>>>> compatible
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", minDisplayRows=");
		sb.append(minDisplayRows);
		sb.append(", scope=");
		sb.append(scope);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDLRecordSet toEntityModel() {
		DDLRecordSetImpl ddlRecordSetImpl = new DDLRecordSetImpl();

		if (uuid == null) {
<<<<<<< HEAD
			ddlRecordSetImpl.setUuid("");
=======
			ddlRecordSetImpl.setUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setUuid(uuid);
		}

		ddlRecordSetImpl.setRecordSetId(recordSetId);
		ddlRecordSetImpl.setGroupId(groupId);
		ddlRecordSetImpl.setCompanyId(companyId);
		ddlRecordSetImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			ddlRecordSetImpl.setUserName("");
=======
			ddlRecordSetImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setUserName(userName);
		}

<<<<<<< HEAD
		ddlRecordSetImpl.setVersionUserId(versionUserId);

		if (versionUserName == null) {
			ddlRecordSetImpl.setVersionUserName("");
		}
		else {
			ddlRecordSetImpl.setVersionUserName(versionUserName);
		}

=======
>>>>>>> compatible
		if (createDate == Long.MIN_VALUE) {
			ddlRecordSetImpl.setCreateDate(null);
		}
		else {
			ddlRecordSetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddlRecordSetImpl.setModifiedDate(null);
		}
		else {
			ddlRecordSetImpl.setModifiedDate(new Date(modifiedDate));
		}

		ddlRecordSetImpl.setDDMStructureId(DDMStructureId);

		if (recordSetKey == null) {
<<<<<<< HEAD
			ddlRecordSetImpl.setRecordSetKey("");
=======
			ddlRecordSetImpl.setRecordSetKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setRecordSetKey(recordSetKey);
		}

<<<<<<< HEAD
		if (version == null) {
			ddlRecordSetImpl.setVersion("");
		}
		else {
			ddlRecordSetImpl.setVersion(version);
		}

		if (name == null) {
			ddlRecordSetImpl.setName("");
=======
		if (name == null) {
			ddlRecordSetImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			ddlRecordSetImpl.setDescription("");
=======
			ddlRecordSetImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setDescription(description);
		}

		ddlRecordSetImpl.setMinDisplayRows(minDisplayRows);
		ddlRecordSetImpl.setScope(scope);

		if (settings == null) {
<<<<<<< HEAD
			ddlRecordSetImpl.setSettings("");
=======
			ddlRecordSetImpl.setSettings(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			ddlRecordSetImpl.setSettings(settings);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			ddlRecordSetImpl.setLastPublishDate(null);
		}
		else {
			ddlRecordSetImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		ddlRecordSetImpl.resetOriginalValues();

		ddlRecordSetImpl.setDDMFormValues(_ddmFormValues);

		return ddlRecordSetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {
		uuid = objectInput.readUTF();

		recordSetId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
<<<<<<< HEAD

		versionUserId = objectInput.readLong();
		versionUserName = objectInput.readUTF();
=======
>>>>>>> compatible
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		DDMStructureId = objectInput.readLong();
		recordSetKey = objectInput.readUTF();
<<<<<<< HEAD
		version = objectInput.readUTF();
=======
>>>>>>> compatible
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		minDisplayRows = objectInput.readInt();

		scope = objectInput.readInt();
		settings = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		_ddmFormValues = (com.liferay.dynamic.data.mapping.storage.DDMFormValues)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(recordSetId);

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

<<<<<<< HEAD
		objectOutput.writeLong(versionUserId);

		if (versionUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(versionUserName);
		}

=======
>>>>>>> compatible
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(DDMStructureId);

		if (recordSetKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recordSetKey);
		}

<<<<<<< HEAD
		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (name == null) {
			objectOutput.writeUTF("");
=======
		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(name);
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

		objectOutput.writeInt(minDisplayRows);

		objectOutput.writeInt(scope);

		if (settings == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(settings);
		}

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeObject(_ddmFormValues);
	}

	public String uuid;
	public long recordSetId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
<<<<<<< HEAD
	public long versionUserId;
	public String versionUserName;
=======
>>>>>>> compatible
	public long createDate;
	public long modifiedDate;
	public long DDMStructureId;
	public String recordSetKey;
<<<<<<< HEAD
	public String version;
=======
>>>>>>> compatible
	public String name;
	public String description;
	public int minDisplayRows;
	public int scope;
	public String settings;
	public long lastPublishDate;
	public com.liferay.dynamic.data.mapping.storage.DDMFormValues _ddmFormValues;
}