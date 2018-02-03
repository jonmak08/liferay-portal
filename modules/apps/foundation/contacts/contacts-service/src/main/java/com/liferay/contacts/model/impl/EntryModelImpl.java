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

package com.liferay.contacts.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.model.EntryModel;
<<<<<<< HEAD
=======
import com.liferay.contacts.model.EntrySoap;
>>>>>>> compatible

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.json.JSON;
>>>>>>> compatible
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import java.io.Serializable;

import java.sql.Types;

<<<<<<< HEAD
import java.util.Date;
import java.util.HashMap;
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
>>>>>>> compatible
import java.util.Map;

/**
 * The base model implementation for the Entry service. Represents a row in the &quot;Contacts_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link EntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryImpl
 * @see Entry
 * @see EntryModel
 * @generated
 */
<<<<<<< HEAD
=======
@JSON(strict = true)
>>>>>>> compatible
@ProviderType
public class EntryModelImpl extends BaseModelImpl<Entry> implements EntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a entry model instance should use the {@link Entry} interface instead.
	 */
	public static final String TABLE_NAME = "Contacts_Entry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "entryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "fullName", Types.VARCHAR },
			{ "emailAddress", Types.VARCHAR },
			{ "comments", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("entryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fullName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
	}

<<<<<<< HEAD
	public static final String TABLE_SQL_CREATE = "create table Contacts_Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,fullName VARCHAR(75) null,emailAddress VARCHAR(254) null,comments STRING null)";
=======
	public static final String TABLE_SQL_CREATE = "create table Contacts_Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,fullName VARCHAR(75) null,emailAddress VARCHAR(75) null,comments STRING null)";
>>>>>>> compatible
	public static final String TABLE_SQL_DROP = "drop table Contacts_Entry";
	public static final String ORDER_BY_JPQL = " ORDER BY entry.fullName ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Contacts_Entry.fullName ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.contacts.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.contacts.model.Entry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.contacts.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.contacts.model.Entry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.contacts.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.contacts.model.Entry"),
			true);
	public static final long EMAILADDRESS_COLUMN_BITMASK = 1L;
	public static final long USERID_COLUMN_BITMASK = 2L;
	public static final long FULLNAME_COLUMN_BITMASK = 4L;
<<<<<<< HEAD
=======

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Entry toModel(EntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Entry model = new EntryImpl();

		model.setEntryId(soapModel.getEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setFullName(soapModel.getFullName());
		model.setEmailAddress(soapModel.getEmailAddress());
		model.setComments(soapModel.getComments());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Entry> toModels(EntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Entry> models = new ArrayList<Entry>(soapModels.length);

		for (EntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

>>>>>>> compatible
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.contacts.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.contacts.model.Entry"));

	public EntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Entry.class;
	}

	@Override
	public String getModelClassName() {
		return Entry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fullName", getFullName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("comments", getComments());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
<<<<<<< HEAD
			return "";
=======
			return StringPool.BLANK;
>>>>>>> compatible
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

<<<<<<< HEAD
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
=======
	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
>>>>>>> compatible
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

<<<<<<< HEAD
=======
	@JSON
>>>>>>> compatible
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

<<<<<<< HEAD
	@Override
	public String getFullName() {
		if (_fullName == null) {
			return "";
=======
	@JSON
	@Override
	public String getFullName() {
		if (_fullName == null) {
			return StringPool.BLANK;
>>>>>>> compatible
		}
		else {
			return _fullName;
		}
	}

	@Override
	public void setFullName(String fullName) {
		_columnBitmask = -1L;

		_fullName = fullName;
	}

<<<<<<< HEAD
	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
			return "";
=======
	@JSON
	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
			return StringPool.BLANK;
>>>>>>> compatible
		}
		else {
			return _emailAddress;
		}
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		_columnBitmask |= EMAILADDRESS_COLUMN_BITMASK;

		if (_originalEmailAddress == null) {
			_originalEmailAddress = _emailAddress;
		}

		_emailAddress = emailAddress;
	}

	public String getOriginalEmailAddress() {
		return GetterUtil.getString(_originalEmailAddress);
	}

<<<<<<< HEAD
	@Override
	public String getComments() {
		if (_comments == null) {
			return "";
=======
	@JSON
	@Override
	public String getComments() {
		if (_comments == null) {
			return StringPool.BLANK;
>>>>>>> compatible
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Entry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Entry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Entry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setEntryId(getEntryId());
		entryImpl.setGroupId(getGroupId());
		entryImpl.setCompanyId(getCompanyId());
		entryImpl.setUserId(getUserId());
		entryImpl.setUserName(getUserName());
		entryImpl.setCreateDate(getCreateDate());
		entryImpl.setModifiedDate(getModifiedDate());
		entryImpl.setFullName(getFullName());
		entryImpl.setEmailAddress(getEmailAddress());
		entryImpl.setComments(getComments());

		entryImpl.resetOriginalValues();

		return entryImpl;
	}

	@Override
	public int compareTo(Entry entry) {
		int value = 0;

		value = getFullName().compareToIgnoreCase(entry.getFullName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Entry)) {
			return false;
		}

		Entry entry = (Entry)obj;

		long primaryKey = entry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		EntryModelImpl entryModelImpl = this;

		entryModelImpl._originalUserId = entryModelImpl._userId;

		entryModelImpl._setOriginalUserId = false;

		entryModelImpl._setModifiedDate = false;

		entryModelImpl._originalEmailAddress = entryModelImpl._emailAddress;

		entryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Entry> toCacheModel() {
		EntryCacheModel entryCacheModel = new EntryCacheModel();

		entryCacheModel.entryId = getEntryId();

		entryCacheModel.groupId = getGroupId();

		entryCacheModel.companyId = getCompanyId();

		entryCacheModel.userId = getUserId();

		entryCacheModel.userName = getUserName();

		String userName = entryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			entryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			entryCacheModel.createDate = createDate.getTime();
		}
		else {
			entryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			entryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			entryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		entryCacheModel.fullName = getFullName();

		String fullName = entryCacheModel.fullName;

		if ((fullName != null) && (fullName.length() == 0)) {
			entryCacheModel.fullName = null;
		}

		entryCacheModel.emailAddress = getEmailAddress();

		String emailAddress = entryCacheModel.emailAddress;

		if ((emailAddress != null) && (emailAddress.length() == 0)) {
			entryCacheModel.emailAddress = null;
		}

		entryCacheModel.comments = getComments();

		String comments = entryCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			entryCacheModel.comments = null;
		}

		return entryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", fullName=");
		sb.append(getFullName());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.contacts.model.Entry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fullName</column-name><column-value><![CDATA[");
		sb.append(getFullName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Entry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Entry.class
		};
	private long _entryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _fullName;
	private String _emailAddress;
	private String _originalEmailAddress;
	private String _comments;
	private long _columnBitmask;
	private Entry _escapedModel;
}