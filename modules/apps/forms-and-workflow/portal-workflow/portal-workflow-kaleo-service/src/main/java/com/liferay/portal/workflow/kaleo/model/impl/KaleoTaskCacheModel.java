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
import com.liferay.portal.workflow.kaleo.model.KaleoTask;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTask in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTask
 * @generated
 */
@ProviderType
public class KaleoTaskCacheModel implements CacheModel<KaleoTask>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskCacheModel)) {
			return false;
		}

		KaleoTaskCacheModel kaleoTaskCacheModel = (KaleoTaskCacheModel)obj;

		if (kaleoTaskId == kaleoTaskCacheModel.kaleoTaskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoTaskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{kaleoTaskId=");
		sb.append(kaleoTaskId);
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
		sb.append(", kaleoNodeId=");
		sb.append(kaleoNodeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoTask toEntityModel() {
		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setKaleoTaskId(kaleoTaskId);
		kaleoTaskImpl.setGroupId(groupId);
		kaleoTaskImpl.setCompanyId(companyId);
		kaleoTaskImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoTaskImpl.setUserName("");
=======
			kaleoTaskImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskImpl.setCreateDate(null);
		}
		else {
			kaleoTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

<<<<<<< HEAD
		kaleoTaskImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
		kaleoTaskImpl.setKaleoNodeId(kaleoNodeId);

		if (name == null) {
			kaleoTaskImpl.setName("");
=======
		kaleoTaskImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskImpl.setKaleoNodeId(kaleoNodeId);

		if (name == null) {
			kaleoTaskImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			kaleoTaskImpl.setDescription("");
=======
			kaleoTaskImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskImpl.setDescription(description);
		}

		kaleoTaskImpl.resetOriginalValues();

		return kaleoTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoTaskId = objectInput.readLong();

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

		kaleoNodeId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoTaskId);

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

		objectOutput.writeLong(kaleoNodeId);

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
	}

	public long kaleoTaskId;
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
	public long kaleoNodeId;
	public String name;
	public String description;
}