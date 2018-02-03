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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskAssignment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignment
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentCacheModel implements CacheModel<KaleoTaskAssignment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskAssignmentCacheModel)) {
			return false;
		}

		KaleoTaskAssignmentCacheModel kaleoTaskAssignmentCacheModel = (KaleoTaskAssignmentCacheModel)obj;

		if (kaleoTaskAssignmentId == kaleoTaskAssignmentCacheModel.kaleoTaskAssignmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoTaskAssignmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoTaskAssignmentId=");
		sb.append(kaleoTaskAssignmentId);
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
		sb.append(", kaleoClassName=");
		sb.append(kaleoClassName);
		sb.append(", kaleoClassPK=");
		sb.append(kaleoClassPK);
<<<<<<< HEAD
		sb.append(", kaleoDefinitionVersionId=");
		sb.append(kaleoDefinitionVersionId);
=======
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
>>>>>>> compatible
		sb.append(", kaleoNodeId=");
		sb.append(kaleoNodeId);
		sb.append(", assigneeClassName=");
		sb.append(assigneeClassName);
		sb.append(", assigneeClassPK=");
		sb.append(assigneeClassPK);
		sb.append(", assigneeActionId=");
		sb.append(assigneeActionId);
		sb.append(", assigneeScript=");
		sb.append(assigneeScript);
		sb.append(", assigneeScriptLanguage=");
		sb.append(assigneeScriptLanguage);
		sb.append(", assigneeScriptRequiredContexts=");
		sb.append(assigneeScriptRequiredContexts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoTaskAssignment toEntityModel() {
		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
		kaleoTaskAssignmentImpl.setGroupId(groupId);
		kaleoTaskAssignmentImpl.setCompanyId(companyId);
		kaleoTaskAssignmentImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setUserName("");
=======
			kaleoTaskAssignmentImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentImpl.setCreateDate(null);
		}
		else {
			kaleoTaskAssignmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskAssignmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setKaleoClassName("");
=======
			kaleoTaskAssignmentImpl.setKaleoClassName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoTaskAssignmentImpl.setKaleoClassPK(kaleoClassPK);
<<<<<<< HEAD
		kaleoTaskAssignmentImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoNodeId);

		if (assigneeClassName == null) {
			kaleoTaskAssignmentImpl.setAssigneeClassName("");
=======
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoNodeId);

		if (assigneeClassName == null) {
			kaleoTaskAssignmentImpl.setAssigneeClassName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeClassName(assigneeClassName);
		}

		kaleoTaskAssignmentImpl.setAssigneeClassPK(assigneeClassPK);

		if (assigneeActionId == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setAssigneeActionId("");
=======
			kaleoTaskAssignmentImpl.setAssigneeActionId(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeActionId(assigneeActionId);
		}

		if (assigneeScript == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setAssigneeScript("");
=======
			kaleoTaskAssignmentImpl.setAssigneeScript(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeScript(assigneeScript);
		}

		if (assigneeScriptLanguage == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setAssigneeScriptLanguage("");
=======
			kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(assigneeScriptLanguage);
		}

		if (assigneeScriptRequiredContexts == null) {
<<<<<<< HEAD
			kaleoTaskAssignmentImpl.setAssigneeScriptRequiredContexts("");
=======
			kaleoTaskAssignmentImpl.setAssigneeScriptRequiredContexts(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeScriptRequiredContexts(assigneeScriptRequiredContexts);
		}

		kaleoTaskAssignmentImpl.resetOriginalValues();

		return kaleoTaskAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoTaskAssignmentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoClassName = objectInput.readUTF();

		kaleoClassPK = objectInput.readLong();

<<<<<<< HEAD
		kaleoDefinitionVersionId = objectInput.readLong();
=======
		kaleoDefinitionId = objectInput.readLong();
>>>>>>> compatible

		kaleoNodeId = objectInput.readLong();
		assigneeClassName = objectInput.readUTF();

		assigneeClassPK = objectInput.readLong();
		assigneeActionId = objectInput.readUTF();
		assigneeScript = objectInput.readUTF();
		assigneeScriptLanguage = objectInput.readUTF();
		assigneeScriptRequiredContexts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoTaskAssignmentId);

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

		if (kaleoClassName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(kaleoClassName);
		}

		objectOutput.writeLong(kaleoClassPK);

<<<<<<< HEAD
		objectOutput.writeLong(kaleoDefinitionVersionId);
=======
		objectOutput.writeLong(kaleoDefinitionId);
>>>>>>> compatible

		objectOutput.writeLong(kaleoNodeId);

		if (assigneeClassName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(assigneeClassName);
		}

		objectOutput.writeLong(assigneeClassPK);

		if (assigneeActionId == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(assigneeActionId);
		}

		if (assigneeScript == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(assigneeScript);
		}

		if (assigneeScriptLanguage == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(assigneeScriptLanguage);
		}

		if (assigneeScriptRequiredContexts == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(assigneeScriptRequiredContexts);
		}
	}

	public long kaleoTaskAssignmentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String kaleoClassName;
	public long kaleoClassPK;
<<<<<<< HEAD
	public long kaleoDefinitionVersionId;
=======
	public long kaleoDefinitionId;
>>>>>>> compatible
	public long kaleoNodeId;
	public String assigneeClassName;
	public long assigneeClassPK;
	public String assigneeActionId;
	public String assigneeScript;
	public String assigneeScriptLanguage;
	public String assigneeScriptRequiredContexts;
}