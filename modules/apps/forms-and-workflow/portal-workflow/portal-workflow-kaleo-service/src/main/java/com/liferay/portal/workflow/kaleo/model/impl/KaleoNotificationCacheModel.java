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
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoNotification in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotification
 * @generated
 */
@ProviderType
public class KaleoNotificationCacheModel implements CacheModel<KaleoNotification>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNotificationCacheModel)) {
			return false;
		}

		KaleoNotificationCacheModel kaleoNotificationCacheModel = (KaleoNotificationCacheModel)obj;

		if (kaleoNotificationId == kaleoNotificationCacheModel.kaleoNotificationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoNotificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationId=");
		sb.append(kaleoNotificationId);
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
		sb.append(", kaleoNodeName=");
		sb.append(kaleoNodeName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", executionType=");
		sb.append(executionType);
		sb.append(", template=");
		sb.append(template);
		sb.append(", templateLanguage=");
		sb.append(templateLanguage);
		sb.append(", notificationTypes=");
		sb.append(notificationTypes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoNotification toEntityModel() {
		KaleoNotificationImpl kaleoNotificationImpl = new KaleoNotificationImpl();

		kaleoNotificationImpl.setKaleoNotificationId(kaleoNotificationId);
		kaleoNotificationImpl.setGroupId(groupId);
		kaleoNotificationImpl.setCompanyId(companyId);
		kaleoNotificationImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setUserName("");
=======
			kaleoNotificationImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoNotificationImpl.setCreateDate(null);
		}
		else {
			kaleoNotificationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoNotificationImpl.setModifiedDate(null);
		}
		else {
			kaleoNotificationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setKaleoClassName("");
=======
			kaleoNotificationImpl.setKaleoClassName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoNotificationImpl.setKaleoClassPK(kaleoClassPK);
<<<<<<< HEAD
		kaleoNotificationImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);

		if (kaleoNodeName == null) {
			kaleoNotificationImpl.setKaleoNodeName("");
=======
		kaleoNotificationImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (kaleoNodeName == null) {
			kaleoNotificationImpl.setKaleoNodeName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setKaleoNodeName(kaleoNodeName);
		}

		if (name == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setName("");
=======
			kaleoNotificationImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setDescription("");
=======
			kaleoNotificationImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setDescription(description);
		}

		if (executionType == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setExecutionType("");
=======
			kaleoNotificationImpl.setExecutionType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setExecutionType(executionType);
		}

		if (template == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setTemplate("");
=======
			kaleoNotificationImpl.setTemplate(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setTemplate(template);
		}

		if (templateLanguage == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setTemplateLanguage("");
=======
			kaleoNotificationImpl.setTemplateLanguage(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setTemplateLanguage(templateLanguage);
		}

		if (notificationTypes == null) {
<<<<<<< HEAD
			kaleoNotificationImpl.setNotificationTypes("");
=======
			kaleoNotificationImpl.setNotificationTypes(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			kaleoNotificationImpl.setNotificationTypes(notificationTypes);
		}

		kaleoNotificationImpl.resetOriginalValues();

		return kaleoNotificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoNotificationId = objectInput.readLong();

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
		kaleoNodeName = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		executionType = objectInput.readUTF();
		template = objectInput.readUTF();
		templateLanguage = objectInput.readUTF();
		notificationTypes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoNotificationId);

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

		if (kaleoNodeName == null) {
			objectOutput.writeUTF("");
=======
		objectOutput.writeLong(kaleoDefinitionId);

		if (kaleoNodeName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(kaleoNodeName);
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

		if (executionType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(executionType);
		}

		if (template == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(template);
		}

		if (templateLanguage == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(templateLanguage);
		}

		if (notificationTypes == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(notificationTypes);
		}
	}

	public long kaleoNotificationId;
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
	public String kaleoNodeName;
	public String name;
	public String description;
	public String executionType;
	public String template;
	public String templateLanguage;
	public String notificationTypes;
}