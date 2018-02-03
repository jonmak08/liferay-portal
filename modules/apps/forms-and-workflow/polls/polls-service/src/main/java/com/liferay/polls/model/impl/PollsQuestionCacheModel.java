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

package com.liferay.polls.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.polls.model.PollsQuestion;

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
 * The cache model class for representing PollsQuestion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestion
 * @generated
 */
@ProviderType
public class PollsQuestionCacheModel implements CacheModel<PollsQuestion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PollsQuestionCacheModel)) {
			return false;
		}

		PollsQuestionCacheModel pollsQuestionCacheModel = (PollsQuestionCacheModel)obj;

		if (questionId == pollsQuestionCacheModel.questionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, questionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", questionId=");
		sb.append(questionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", lastVoteDate=");
		sb.append(lastVoteDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PollsQuestion toEntityModel() {
		PollsQuestionImpl pollsQuestionImpl = new PollsQuestionImpl();

		if (uuid == null) {
<<<<<<< HEAD
			pollsQuestionImpl.setUuid("");
=======
			pollsQuestionImpl.setUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			pollsQuestionImpl.setUuid(uuid);
		}

		pollsQuestionImpl.setQuestionId(questionId);
		pollsQuestionImpl.setGroupId(groupId);
		pollsQuestionImpl.setCompanyId(companyId);
		pollsQuestionImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			pollsQuestionImpl.setUserName("");
=======
			pollsQuestionImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			pollsQuestionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setCreateDate(null);
		}
		else {
			pollsQuestionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setModifiedDate(null);
		}
		else {
			pollsQuestionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
<<<<<<< HEAD
			pollsQuestionImpl.setTitle("");
=======
			pollsQuestionImpl.setTitle(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			pollsQuestionImpl.setTitle(title);
		}

		if (description == null) {
<<<<<<< HEAD
			pollsQuestionImpl.setDescription("");
=======
			pollsQuestionImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			pollsQuestionImpl.setDescription(description);
		}

		if (expirationDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setExpirationDate(null);
		}
		else {
			pollsQuestionImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setLastPublishDate(null);
		}
		else {
			pollsQuestionImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		if (lastVoteDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setLastVoteDate(null);
		}
		else {
			pollsQuestionImpl.setLastVoteDate(new Date(lastVoteDate));
		}

		pollsQuestionImpl.resetOriginalValues();

		return pollsQuestionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		questionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		expirationDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
		lastVoteDate = objectInput.readLong();
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

		objectOutput.writeLong(questionId);

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

		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(lastPublishDate);
		objectOutput.writeLong(lastVoteDate);
	}

	public String uuid;
	public long questionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long expirationDate;
	public long lastPublishDate;
	public long lastVoteDate;
}