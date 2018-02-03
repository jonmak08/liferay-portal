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

package com.liferay.powwow.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
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

import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.model.PowwowParticipantModel;
import com.liferay.powwow.model.PowwowParticipantSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the PowwowParticipant service. Represents a row in the &quot;PowwowParticipant&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link PowwowParticipantModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PowwowParticipantImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowParticipantImpl
 * @see PowwowParticipant
 * @see PowwowParticipantModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class PowwowParticipantModelImpl extends BaseModelImpl<PowwowParticipant>
	implements PowwowParticipantModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a powwow participant model instance should use the {@link PowwowParticipant} interface instead.
	 */
	public static final String TABLE_NAME = "PowwowParticipant";
	public static final Object[][] TABLE_COLUMNS = {
			{ "powwowParticipantId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "powwowMeetingId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "participantUserId", Types.BIGINT },
			{ "emailAddress", Types.VARCHAR },
			{ "type_", Types.INTEGER },
			{ "status", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("powwowParticipantId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("powwowMeetingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("participantUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("emailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table PowwowParticipant (powwowParticipantId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,powwowMeetingId LONG,name VARCHAR(75) null,participantUserId LONG,emailAddress VARCHAR(75) null,type_ INTEGER,status INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table PowwowParticipant";
	public static final String ORDER_BY_JPQL = " ORDER BY powwowParticipant.powwowParticipantId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY PowwowParticipant.powwowParticipantId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.powwow.model.PowwowParticipant"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.powwow.model.PowwowParticipant"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.powwow.model.PowwowParticipant"),
			true);
	public static final long EMAILADDRESS_COLUMN_BITMASK = 1L;
	public static final long PARTICIPANTUSERID_COLUMN_BITMASK = 2L;
	public static final long POWWOWMEETINGID_COLUMN_BITMASK = 4L;
	public static final long TYPE_COLUMN_BITMASK = 8L;
	public static final long POWWOWPARTICIPANTID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static PowwowParticipant toModel(PowwowParticipantSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PowwowParticipant model = new PowwowParticipantImpl();

		model.setPowwowParticipantId(soapModel.getPowwowParticipantId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setPowwowMeetingId(soapModel.getPowwowMeetingId());
		model.setName(soapModel.getName());
		model.setParticipantUserId(soapModel.getParticipantUserId());
		model.setEmailAddress(soapModel.getEmailAddress());
		model.setType(soapModel.getType());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<PowwowParticipant> toModels(
		PowwowParticipantSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<PowwowParticipant> models = new ArrayList<PowwowParticipant>(soapModels.length);

		for (PowwowParticipantSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.powwow.model.PowwowParticipant"));

	public PowwowParticipantModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _powwowParticipantId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPowwowParticipantId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _powwowParticipantId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PowwowParticipant.class;
	}

	@Override
	public String getModelClassName() {
		return PowwowParticipant.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("powwowParticipantId", getPowwowParticipantId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("powwowMeetingId", getPowwowMeetingId());
		attributes.put("name", getName());
		attributes.put("participantUserId", getParticipantUserId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long powwowParticipantId = (Long)attributes.get("powwowParticipantId");

		if (powwowParticipantId != null) {
			setPowwowParticipantId(powwowParticipantId);
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

		Long powwowMeetingId = (Long)attributes.get("powwowMeetingId");

		if (powwowMeetingId != null) {
			setPowwowMeetingId(powwowMeetingId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long participantUserId = (Long)attributes.get("participantUserId");

		if (participantUserId != null) {
			setParticipantUserId(participantUserId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@JSON
	@Override
	public long getPowwowParticipantId() {
		return _powwowParticipantId;
	}

	@Override
	public void setPowwowParticipantId(long powwowParticipantId) {
		_powwowParticipantId = powwowParticipantId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
<<<<<<< HEAD
			return "";
=======
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
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

	@JSON
	@Override
	public long getPowwowMeetingId() {
		return _powwowMeetingId;
	}

	@Override
	public void setPowwowMeetingId(long powwowMeetingId) {
		_columnBitmask |= POWWOWMEETINGID_COLUMN_BITMASK;

		if (!_setOriginalPowwowMeetingId) {
			_setOriginalPowwowMeetingId = true;

			_originalPowwowMeetingId = _powwowMeetingId;
		}

		_powwowMeetingId = powwowMeetingId;
	}

	public long getOriginalPowwowMeetingId() {
		return _originalPowwowMeetingId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
<<<<<<< HEAD
			return "";
=======
			return StringPool.BLANK;
>>>>>>> compatible
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public long getParticipantUserId() {
		return _participantUserId;
	}

	@Override
	public void setParticipantUserId(long participantUserId) {
		_columnBitmask |= PARTICIPANTUSERID_COLUMN_BITMASK;

		if (!_setOriginalParticipantUserId) {
			_setOriginalParticipantUserId = true;

			_originalParticipantUserId = _participantUserId;
		}

		_participantUserId = participantUserId;
	}

	@Override
	public String getParticipantUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getParticipantUserId());

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
	public void setParticipantUserUuid(String participantUserUuid) {
	}

	public long getOriginalParticipantUserId() {
		return _originalParticipantUserId;
	}

	@JSON
	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
<<<<<<< HEAD
			return "";
=======
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

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			PowwowParticipant.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PowwowParticipant toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (PowwowParticipant)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PowwowParticipantImpl powwowParticipantImpl = new PowwowParticipantImpl();

		powwowParticipantImpl.setPowwowParticipantId(getPowwowParticipantId());
		powwowParticipantImpl.setGroupId(getGroupId());
		powwowParticipantImpl.setCompanyId(getCompanyId());
		powwowParticipantImpl.setUserId(getUserId());
		powwowParticipantImpl.setUserName(getUserName());
		powwowParticipantImpl.setCreateDate(getCreateDate());
		powwowParticipantImpl.setModifiedDate(getModifiedDate());
		powwowParticipantImpl.setPowwowMeetingId(getPowwowMeetingId());
		powwowParticipantImpl.setName(getName());
		powwowParticipantImpl.setParticipantUserId(getParticipantUserId());
		powwowParticipantImpl.setEmailAddress(getEmailAddress());
		powwowParticipantImpl.setType(getType());
		powwowParticipantImpl.setStatus(getStatus());

		powwowParticipantImpl.resetOriginalValues();

		return powwowParticipantImpl;
	}

	@Override
	public int compareTo(PowwowParticipant powwowParticipant) {
		long primaryKey = powwowParticipant.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PowwowParticipant)) {
			return false;
		}

		PowwowParticipant powwowParticipant = (PowwowParticipant)obj;

		long primaryKey = powwowParticipant.getPrimaryKey();

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
		PowwowParticipantModelImpl powwowParticipantModelImpl = this;

		powwowParticipantModelImpl._setModifiedDate = false;

		powwowParticipantModelImpl._originalPowwowMeetingId = powwowParticipantModelImpl._powwowMeetingId;

		powwowParticipantModelImpl._setOriginalPowwowMeetingId = false;

		powwowParticipantModelImpl._originalParticipantUserId = powwowParticipantModelImpl._participantUserId;

		powwowParticipantModelImpl._setOriginalParticipantUserId = false;

		powwowParticipantModelImpl._originalEmailAddress = powwowParticipantModelImpl._emailAddress;

		powwowParticipantModelImpl._originalType = powwowParticipantModelImpl._type;

		powwowParticipantModelImpl._setOriginalType = false;

		powwowParticipantModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PowwowParticipant> toCacheModel() {
		PowwowParticipantCacheModel powwowParticipantCacheModel = new PowwowParticipantCacheModel();

		powwowParticipantCacheModel.powwowParticipantId = getPowwowParticipantId();

		powwowParticipantCacheModel.groupId = getGroupId();

		powwowParticipantCacheModel.companyId = getCompanyId();

		powwowParticipantCacheModel.userId = getUserId();

		powwowParticipantCacheModel.userName = getUserName();

		String userName = powwowParticipantCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			powwowParticipantCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			powwowParticipantCacheModel.createDate = createDate.getTime();
		}
		else {
			powwowParticipantCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			powwowParticipantCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			powwowParticipantCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		powwowParticipantCacheModel.powwowMeetingId = getPowwowMeetingId();

		powwowParticipantCacheModel.name = getName();

		String name = powwowParticipantCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			powwowParticipantCacheModel.name = null;
		}

		powwowParticipantCacheModel.participantUserId = getParticipantUserId();

		powwowParticipantCacheModel.emailAddress = getEmailAddress();

		String emailAddress = powwowParticipantCacheModel.emailAddress;

		if ((emailAddress != null) && (emailAddress.length() == 0)) {
			powwowParticipantCacheModel.emailAddress = null;
		}

		powwowParticipantCacheModel.type = getType();

		powwowParticipantCacheModel.status = getStatus();

		return powwowParticipantCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{powwowParticipantId=");
		sb.append(getPowwowParticipantId());
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
		sb.append(", powwowMeetingId=");
		sb.append(getPowwowMeetingId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", participantUserId=");
		sb.append(getParticipantUserId());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.powwow.model.PowwowParticipant");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>powwowParticipantId</column-name><column-value><![CDATA[");
		sb.append(getPowwowParticipantId());
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
			"<column><column-name>powwowMeetingId</column-name><column-value><![CDATA[");
		sb.append(getPowwowMeetingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>participantUserId</column-name><column-value><![CDATA[");
		sb.append(getParticipantUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = PowwowParticipant.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			PowwowParticipant.class
		};
	private long _powwowParticipantId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _powwowMeetingId;
	private long _originalPowwowMeetingId;
	private boolean _setOriginalPowwowMeetingId;
	private String _name;
	private long _participantUserId;
	private long _originalParticipantUserId;
	private boolean _setOriginalParticipantUserId;
	private String _emailAddress;
	private String _originalEmailAddress;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private int _status;
	private long _columnBitmask;
	private PowwowParticipant _escapedModel;
}