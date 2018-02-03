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
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoNode in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNode
 * @generated
 */
@ProviderType
public class KaleoNodeCacheModel implements CacheModel<KaleoNode>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNodeCacheModel)) {
			return false;
		}

		KaleoNodeCacheModel kaleoNodeCacheModel = (KaleoNodeCacheModel)obj;

		if (kaleoNodeId == kaleoNodeCacheModel.kaleoNodeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoNodeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{kaleoNodeId=");
		sb.append(kaleoNodeId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", metadata=");
		sb.append(metadata);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", initial=");
		sb.append(initial);
		sb.append(", terminal=");
		sb.append(terminal);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoNode toEntityModel() {
		KaleoNodeImpl kaleoNodeImpl = new KaleoNodeImpl();

		kaleoNodeImpl.setKaleoNodeId(kaleoNodeId);
		kaleoNodeImpl.setGroupId(groupId);
		kaleoNodeImpl.setCompanyId(companyId);
		kaleoNodeImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoNodeImpl.setUserName("");
=======
			kaleoNodeImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNodeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoNodeImpl.setCreateDate(null);
		}
		else {
			kaleoNodeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoNodeImpl.setModifiedDate(null);
		}
		else {
			kaleoNodeImpl.setModifiedDate(new Date(modifiedDate));
		}

<<<<<<< HEAD
		kaleoNodeImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);

		if (name == null) {
			kaleoNodeImpl.setName("");
=======
		kaleoNodeImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (name == null) {
			kaleoNodeImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNodeImpl.setName(name);
		}

		if (metadata == null) {
<<<<<<< HEAD
			kaleoNodeImpl.setMetadata("");
=======
			kaleoNodeImpl.setMetadata(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNodeImpl.setMetadata(metadata);
		}

		if (description == null) {
<<<<<<< HEAD
			kaleoNodeImpl.setDescription("");
=======
			kaleoNodeImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNodeImpl.setDescription(description);
		}

		if (type == null) {
<<<<<<< HEAD
			kaleoNodeImpl.setType("");
=======
			kaleoNodeImpl.setType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNodeImpl.setType(type);
		}

		kaleoNodeImpl.setInitial(initial);
		kaleoNodeImpl.setTerminal(terminal);

		kaleoNodeImpl.resetOriginalValues();

		return kaleoNodeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoNodeId = objectInput.readLong();

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
		name = objectInput.readUTF();
		metadata = objectInput.readUTF();
		description = objectInput.readUTF();
		type = objectInput.readUTF();

		initial = objectInput.readBoolean();

		terminal = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoNodeId);

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

		if (name == null) {
			objectOutput.writeUTF("");
=======
		objectOutput.writeLong(kaleoDefinitionId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (metadata == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(metadata);
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

		objectOutput.writeBoolean(initial);

		objectOutput.writeBoolean(terminal);
	}

	public long kaleoNodeId;
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
	public String name;
	public String metadata;
	public String description;
	public String type;
	public boolean initial;
	public boolean terminal;
}