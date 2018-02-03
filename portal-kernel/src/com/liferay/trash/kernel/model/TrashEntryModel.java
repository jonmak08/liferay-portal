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

package com.liferay.trash.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the TrashEntry service. Represents a row in the &quot;TrashEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.trash.model.impl.TrashEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrashEntry
 * @see com.liferay.portlet.trash.model.impl.TrashEntryImpl
 * @see com.liferay.portlet.trash.model.impl.TrashEntryModelImpl
<<<<<<< HEAD
 * @deprecated As of 7.0.0, replaced by {@link
           com.liferay.trash.model.impl.TrashEntryImpl}
 * @generated
 */
@Deprecated
=======
 * @generated
 */
>>>>>>> compatible
@ProviderType
public interface TrashEntryModel extends AttachedModel, BaseModel<TrashEntry>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a trash entry model instance should use the {@link TrashEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this trash entry.
	 *
	 * @return the primary key of this trash entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this trash entry.
	 *
	 * @param primaryKey the primary key of this trash entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the entry ID of this trash entry.
	 *
	 * @return the entry ID of this trash entry
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this trash entry.
	 *
	 * @param entryId the entry ID of this trash entry
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the group ID of this trash entry.
	 *
	 * @return the group ID of this trash entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this trash entry.
	 *
	 * @param groupId the group ID of this trash entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this trash entry.
	 *
	 * @return the company ID of this trash entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this trash entry.
	 *
	 * @param companyId the company ID of this trash entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this trash entry.
	 *
	 * @return the user ID of this trash entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this trash entry.
	 *
	 * @param userId the user ID of this trash entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this trash entry.
	 *
	 * @return the user uuid of this trash entry
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this trash entry.
	 *
	 * @param userUuid the user uuid of this trash entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this trash entry.
	 *
	 * @return the user name of this trash entry
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this trash entry.
	 *
	 * @param userName the user name of this trash entry
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this trash entry.
	 *
	 * @return the create date of this trash entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this trash entry.
	 *
	 * @param createDate the create date of this trash entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the fully qualified class name of this trash entry.
	 *
	 * @return the fully qualified class name of this trash entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this trash entry.
	 *
	 * @return the class name ID of this trash entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this trash entry.
	 *
	 * @param classNameId the class name ID of this trash entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this trash entry.
	 *
	 * @return the class pk of this trash entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this trash entry.
	 *
	 * @param classPK the class pk of this trash entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the system event set key of this trash entry.
	 *
	 * @return the system event set key of this trash entry
	 */
	public long getSystemEventSetKey();

	/**
	 * Sets the system event set key of this trash entry.
	 *
	 * @param systemEventSetKey the system event set key of this trash entry
	 */
	public void setSystemEventSetKey(long systemEventSetKey);

	/**
	 * Returns the type settings of this trash entry.
	 *
	 * @return the type settings of this trash entry
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this trash entry.
	 *
	 * @param typeSettings the type settings of this trash entry
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the status of this trash entry.
	 *
	 * @return the status of this trash entry
	 */
	public int getStatus();

	/**
	 * Sets the status of this trash entry.
	 *
	 * @param status the status of this trash entry
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
	public int compareTo(TrashEntry trashEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<TrashEntry> toCacheModel();

	@Override
	public TrashEntry toEscapedModel();

	@Override
	public TrashEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}