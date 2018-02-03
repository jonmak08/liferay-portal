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
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinition
 * @generated
 */
@ProviderType
public class KaleoDefinitionCacheModel implements CacheModel<KaleoDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoDefinitionCacheModel)) {
			return false;
		}

		KaleoDefinitionCacheModel kaleoDefinitionCacheModel = (KaleoDefinitionCacheModel)obj;

		if (kaleoDefinitionId == kaleoDefinitionCacheModel.kaleoDefinitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoDefinitionId);
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		StringBundler sb = new StringBundler(27);
=======
		StringBundler sb = new StringBundler(29);
>>>>>>> compatible

		sb.append("{kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", content=");
		sb.append(content);
		sb.append(", version=");
		sb.append(version);
		sb.append(", active=");
		sb.append(active);
<<<<<<< HEAD
=======
		sb.append(", startKaleoNodeId=");
		sb.append(startKaleoNodeId);
>>>>>>> compatible
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoDefinition toEntityModel() {
		KaleoDefinitionImpl kaleoDefinitionImpl = new KaleoDefinitionImpl();

		kaleoDefinitionImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoDefinitionImpl.setGroupId(groupId);
		kaleoDefinitionImpl.setCompanyId(companyId);
		kaleoDefinitionImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoDefinitionImpl.setUserName("");
=======
			kaleoDefinitionImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoDefinitionImpl.setCreateDate(null);
		}
		else {
			kaleoDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoDefinitionImpl.setModifiedDate(null);
		}
		else {
			kaleoDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
<<<<<<< HEAD
			kaleoDefinitionImpl.setName("");
=======
			kaleoDefinitionImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoDefinitionImpl.setName(name);
		}

		if (title == null) {
<<<<<<< HEAD
			kaleoDefinitionImpl.setTitle("");
=======
			kaleoDefinitionImpl.setTitle(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoDefinitionImpl.setTitle(title);
		}

		if (description == null) {
<<<<<<< HEAD
			kaleoDefinitionImpl.setDescription("");
=======
			kaleoDefinitionImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoDefinitionImpl.setDescription(description);
		}

		if (content == null) {
<<<<<<< HEAD
			kaleoDefinitionImpl.setContent("");
=======
			kaleoDefinitionImpl.setContent(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoDefinitionImpl.setContent(content);
		}

		kaleoDefinitionImpl.setVersion(version);
		kaleoDefinitionImpl.setActive(active);
<<<<<<< HEAD
=======
		kaleoDefinitionImpl.setStartKaleoNodeId(startKaleoNodeId);
>>>>>>> compatible

		kaleoDefinitionImpl.resetOriginalValues();

		return kaleoDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoDefinitionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		content = objectInput.readUTF();

		version = objectInput.readInt();

		active = objectInput.readBoolean();
<<<<<<< HEAD
=======

		startKaleoNodeId = objectInput.readLong();
>>>>>>> compatible
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoDefinitionId);

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

		if (title == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(title);
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

		if (content == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(version);

		objectOutput.writeBoolean(active);
<<<<<<< HEAD
=======

		objectOutput.writeLong(startKaleoNodeId);
>>>>>>> compatible
	}

	public long kaleoDefinitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String title;
	public String description;
	public String content;
	public int version;
	public boolean active;
<<<<<<< HEAD
=======
	public long startKaleoNodeId;
>>>>>>> compatible
}