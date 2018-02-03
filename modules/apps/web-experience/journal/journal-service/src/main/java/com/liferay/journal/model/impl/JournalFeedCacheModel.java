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

package com.liferay.journal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalFeed;

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
 * The cache model class for representing JournalFeed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeed
 * @generated
 */
@ProviderType
public class JournalFeedCacheModel implements CacheModel<JournalFeed>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JournalFeedCacheModel)) {
			return false;
		}

		JournalFeedCacheModel journalFeedCacheModel = (JournalFeedCacheModel)obj;

		if (id == journalFeedCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
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
		sb.append(", feedId=");
		sb.append(feedId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", DDMStructureKey=");
		sb.append(DDMStructureKey);
		sb.append(", DDMTemplateKey=");
		sb.append(DDMTemplateKey);
		sb.append(", DDMRendererTemplateKey=");
		sb.append(DDMRendererTemplateKey);
		sb.append(", delta=");
		sb.append(delta);
		sb.append(", orderByCol=");
		sb.append(orderByCol);
		sb.append(", orderByType=");
		sb.append(orderByType);
		sb.append(", targetLayoutFriendlyUrl=");
		sb.append(targetLayoutFriendlyUrl);
		sb.append(", targetPortletId=");
		sb.append(targetPortletId);
		sb.append(", contentField=");
		sb.append(contentField);
		sb.append(", feedFormat=");
		sb.append(feedFormat);
		sb.append(", feedVersion=");
		sb.append(feedVersion);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JournalFeed toEntityModel() {
		JournalFeedImpl journalFeedImpl = new JournalFeedImpl();

		if (uuid == null) {
<<<<<<< HEAD
			journalFeedImpl.setUuid("");
=======
			journalFeedImpl.setUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setUuid(uuid);
		}

		journalFeedImpl.setId(id);
		journalFeedImpl.setGroupId(groupId);
		journalFeedImpl.setCompanyId(companyId);
		journalFeedImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			journalFeedImpl.setUserName("");
=======
			journalFeedImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			journalFeedImpl.setCreateDate(null);
		}
		else {
			journalFeedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			journalFeedImpl.setModifiedDate(null);
		}
		else {
			journalFeedImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (feedId == null) {
<<<<<<< HEAD
			journalFeedImpl.setFeedId("");
=======
			journalFeedImpl.setFeedId(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setFeedId(feedId);
		}

		if (name == null) {
<<<<<<< HEAD
			journalFeedImpl.setName("");
=======
			journalFeedImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			journalFeedImpl.setDescription("");
=======
			journalFeedImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setDescription(description);
		}

		if (DDMStructureKey == null) {
<<<<<<< HEAD
			journalFeedImpl.setDDMStructureKey("");
=======
			journalFeedImpl.setDDMStructureKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setDDMStructureKey(DDMStructureKey);
		}

		if (DDMTemplateKey == null) {
<<<<<<< HEAD
			journalFeedImpl.setDDMTemplateKey("");
=======
			journalFeedImpl.setDDMTemplateKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setDDMTemplateKey(DDMTemplateKey);
		}

		if (DDMRendererTemplateKey == null) {
<<<<<<< HEAD
			journalFeedImpl.setDDMRendererTemplateKey("");
=======
			journalFeedImpl.setDDMRendererTemplateKey(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setDDMRendererTemplateKey(DDMRendererTemplateKey);
		}

		journalFeedImpl.setDelta(delta);

		if (orderByCol == null) {
<<<<<<< HEAD
			journalFeedImpl.setOrderByCol("");
=======
			journalFeedImpl.setOrderByCol(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setOrderByCol(orderByCol);
		}

		if (orderByType == null) {
<<<<<<< HEAD
			journalFeedImpl.setOrderByType("");
=======
			journalFeedImpl.setOrderByType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setOrderByType(orderByType);
		}

		if (targetLayoutFriendlyUrl == null) {
<<<<<<< HEAD
			journalFeedImpl.setTargetLayoutFriendlyUrl("");
=======
			journalFeedImpl.setTargetLayoutFriendlyUrl(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setTargetLayoutFriendlyUrl(targetLayoutFriendlyUrl);
		}

		if (targetPortletId == null) {
<<<<<<< HEAD
			journalFeedImpl.setTargetPortletId("");
=======
			journalFeedImpl.setTargetPortletId(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setTargetPortletId(targetPortletId);
		}

		if (contentField == null) {
<<<<<<< HEAD
			journalFeedImpl.setContentField("");
=======
			journalFeedImpl.setContentField(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setContentField(contentField);
		}

		if (feedFormat == null) {
<<<<<<< HEAD
			journalFeedImpl.setFeedFormat("");
=======
			journalFeedImpl.setFeedFormat(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			journalFeedImpl.setFeedFormat(feedFormat);
		}

		journalFeedImpl.setFeedVersion(feedVersion);

		if (lastPublishDate == Long.MIN_VALUE) {
			journalFeedImpl.setLastPublishDate(null);
		}
		else {
			journalFeedImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		journalFeedImpl.resetOriginalValues();

		return journalFeedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		feedId = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		DDMStructureKey = objectInput.readUTF();
		DDMTemplateKey = objectInput.readUTF();
		DDMRendererTemplateKey = objectInput.readUTF();

		delta = objectInput.readInt();
		orderByCol = objectInput.readUTF();
		orderByType = objectInput.readUTF();
		targetLayoutFriendlyUrl = objectInput.readUTF();
		targetPortletId = objectInput.readUTF();
		contentField = objectInput.readUTF();
		feedFormat = objectInput.readUTF();

		feedVersion = objectInput.readDouble();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(id);

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

		if (feedId == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(feedId);
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

		if (DDMStructureKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(DDMStructureKey);
		}

		if (DDMTemplateKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(DDMTemplateKey);
		}

		if (DDMRendererTemplateKey == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(DDMRendererTemplateKey);
		}

		objectOutput.writeInt(delta);

		if (orderByCol == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(orderByCol);
		}

		if (orderByType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(orderByType);
		}

		if (targetLayoutFriendlyUrl == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(targetLayoutFriendlyUrl);
		}

		if (targetPortletId == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(targetPortletId);
		}

		if (contentField == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(contentField);
		}

		if (feedFormat == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(feedFormat);
		}

		objectOutput.writeDouble(feedVersion);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long id;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String feedId;
	public String name;
	public String description;
	public String DDMStructureKey;
	public String DDMTemplateKey;
	public String DDMRendererTemplateKey;
	public int delta;
	public String orderByCol;
	public String orderByType;
	public String targetLayoutFriendlyUrl;
	public String targetPortletId;
	public String contentField;
	public String feedFormat;
	public double feedVersion;
	public long lastPublishDate;
}