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

package com.liferay.bookmarks.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.bookmarks.model.BookmarksFolder;

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
 * The cache model class for representing BookmarksFolder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolder
 * @generated
 */
@ProviderType
public class BookmarksFolderCacheModel implements CacheModel<BookmarksFolder>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookmarksFolderCacheModel)) {
			return false;
		}

		BookmarksFolderCacheModel bookmarksFolderCacheModel = (BookmarksFolderCacheModel)obj;

		if (folderId == bookmarksFolderCacheModel.folderId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, folderId);
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		StringBundler sb = new StringBundler(35);
=======
		StringBundler sb = new StringBundler(37);
>>>>>>> compatible

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", folderId=");
		sb.append(folderId);
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
=======
		sb.append(", resourceBlockId=");
		sb.append(resourceBlockId);
>>>>>>> compatible
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append(", treePath=");
		sb.append(treePath);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BookmarksFolder toEntityModel() {
		BookmarksFolderImpl bookmarksFolderImpl = new BookmarksFolderImpl();

		if (uuid == null) {
<<<<<<< HEAD
			bookmarksFolderImpl.setUuid("");
=======
			bookmarksFolderImpl.setUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setUuid(uuid);
		}

		bookmarksFolderImpl.setFolderId(folderId);
		bookmarksFolderImpl.setGroupId(groupId);
		bookmarksFolderImpl.setCompanyId(companyId);
		bookmarksFolderImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			bookmarksFolderImpl.setUserName("");
=======
			bookmarksFolderImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bookmarksFolderImpl.setCreateDate(null);
		}
		else {
			bookmarksFolderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bookmarksFolderImpl.setModifiedDate(null);
		}
		else {
			bookmarksFolderImpl.setModifiedDate(new Date(modifiedDate));
		}

<<<<<<< HEAD
		bookmarksFolderImpl.setParentFolderId(parentFolderId);

		if (treePath == null) {
			bookmarksFolderImpl.setTreePath("");
=======
		bookmarksFolderImpl.setResourceBlockId(resourceBlockId);
		bookmarksFolderImpl.setParentFolderId(parentFolderId);

		if (treePath == null) {
			bookmarksFolderImpl.setTreePath(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setTreePath(treePath);
		}

		if (name == null) {
<<<<<<< HEAD
			bookmarksFolderImpl.setName("");
=======
			bookmarksFolderImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			bookmarksFolderImpl.setDescription("");
=======
			bookmarksFolderImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setDescription(description);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			bookmarksFolderImpl.setLastPublishDate(null);
		}
		else {
			bookmarksFolderImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		bookmarksFolderImpl.setStatus(status);
		bookmarksFolderImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
<<<<<<< HEAD
			bookmarksFolderImpl.setStatusByUserName("");
=======
			bookmarksFolderImpl.setStatusByUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			bookmarksFolderImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			bookmarksFolderImpl.setStatusDate(null);
		}
		else {
			bookmarksFolderImpl.setStatusDate(new Date(statusDate));
		}

		bookmarksFolderImpl.resetOriginalValues();

		return bookmarksFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		folderId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

<<<<<<< HEAD
=======
		resourceBlockId = objectInput.readLong();

>>>>>>> compatible
		parentFolderId = objectInput.readLong();
		treePath = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(folderId);

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
		objectOutput.writeLong(parentFolderId);

		if (treePath == null) {
			objectOutput.writeUTF("");
=======
		objectOutput.writeLong(resourceBlockId);

		objectOutput.writeLong(parentFolderId);

		if (treePath == null) {
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

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long folderId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
<<<<<<< HEAD
=======
	public long resourceBlockId;
>>>>>>> compatible
	public long parentFolderId;
	public String treePath;
	public String name;
	public String description;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}