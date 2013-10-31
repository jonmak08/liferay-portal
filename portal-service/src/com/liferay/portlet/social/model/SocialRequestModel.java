/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.social.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SocialRequest service. Represents a row in the &quot;SocialRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.social.model.impl.SocialRequestModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.social.model.impl.SocialRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequest
 * @see com.liferay.portlet.social.model.impl.SocialRequestImpl
 * @see com.liferay.portlet.social.model.impl.SocialRequestModelImpl
 * @generated
 */
@ProviderType
public interface SocialRequestModel extends AttachedModel,
	BaseModel<SocialRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social request model instance should use the {@link SocialRequest} interface instead.
	 */

	/**
	 * Returns the primary key of this social request.
	 *
	 * @return the primary key of this social request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social request.
	 *
	 * @param primaryKey the primary key of this social request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this social request.
	 *
	 * @return the uuid of this social request
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this social request.
	 *
	 * @param uuid the uuid of this social request
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the request ID of this social request.
	 *
	 * @return the request ID of this social request
	 */
	public long getRequestId();

	/**
	 * Sets the request ID of this social request.
	 *
	 * @param requestId the request ID of this social request
	 */
	public void setRequestId(long requestId);

	/**
	 * Returns the group ID of this social request.
	 *
	 * @return the group ID of this social request
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social request.
	 *
	 * @param groupId the group ID of this social request
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social request.
	 *
	 * @return the company ID of this social request
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this social request.
	 *
	 * @param companyId the company ID of this social request
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this social request.
	 *
	 * @return the user ID of this social request
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this social request.
	 *
	 * @param userId the user ID of this social request
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this social request.
	 *
	 * @return the user uuid of this social request
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this social request.
	 *
	 * @param userUuid the user uuid of this social request
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this social request.
	 *
	 * @return the create date of this social request
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this social request.
	 *
	 * @param createDate the create date of this social request
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the modified date of this social request.
	 *
	 * @return the modified date of this social request
	 */
	public long getModifiedDate();

	/**
	 * Sets the modified date of this social request.
	 *
	 * @param modifiedDate the modified date of this social request
	 */
	public void setModifiedDate(long modifiedDate);

	/**
	 * Returns the fully qualified class name of this social request.
	 *
	 * @return the fully qualified class name of this social request
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this social request.
	 *
	 * @return the class name ID of this social request
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social request.
	 *
	 * @param classNameId the class name ID of this social request
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this social request.
	 *
	 * @return the class p k of this social request
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this social request.
	 *
	 * @param classPK the class p k of this social request
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the type of this social request.
	 *
	 * @return the type of this social request
	 */
	public int getType();

	/**
	 * Sets the type of this social request.
	 *
	 * @param type the type of this social request
	 */
	public void setType(int type);

	/**
	 * Returns the extra data of this social request.
	 *
	 * @return the extra data of this social request
	 */
	@AutoEscape
	public String getExtraData();

	/**
	 * Sets the extra data of this social request.
	 *
	 * @param extraData the extra data of this social request
	 */
	public void setExtraData(String extraData);

	/**
	 * Returns the receiver user ID of this social request.
	 *
	 * @return the receiver user ID of this social request
	 */
	public long getReceiverUserId();

	/**
	 * Sets the receiver user ID of this social request.
	 *
	 * @param receiverUserId the receiver user ID of this social request
	 */
	public void setReceiverUserId(long receiverUserId);

	/**
	 * Returns the receiver user uuid of this social request.
	 *
	 * @return the receiver user uuid of this social request
	 * @throws SystemException if a system exception occurred
	 */
	public String getReceiverUserUuid() throws SystemException;

	/**
	 * Sets the receiver user uuid of this social request.
	 *
	 * @param receiverUserUuid the receiver user uuid of this social request
	 */
	public void setReceiverUserUuid(String receiverUserUuid);

	/**
	 * Returns the status of this social request.
	 *
	 * @return the status of this social request
	 */
	public int getStatus();

	/**
	 * Sets the status of this social request.
	 *
	 * @param status the status of this social request
	 */
	public void setStatus(int status);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(SocialRequest socialRequest);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SocialRequest> toCacheModel();

	@Override
	public SocialRequest toEscapedModel();

	@Override
	public SocialRequest toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}