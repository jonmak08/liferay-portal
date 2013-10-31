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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LayoutBranch service. Represents a row in the &quot;LayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.LayoutBranchModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.LayoutBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranch
 * @see com.liferay.portal.model.impl.LayoutBranchImpl
 * @see com.liferay.portal.model.impl.LayoutBranchModelImpl
 * @generated
 */
@ProviderType
public interface LayoutBranchModel extends BaseModel<LayoutBranch> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout branch model instance should use the {@link LayoutBranch} interface instead.
	 */

	/**
	 * Returns the primary key of this layout branch.
	 *
	 * @return the primary key of this layout branch
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout branch.
	 *
	 * @param primaryKey the primary key of this layout branch
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the layout branch ID of this layout branch.
	 *
	 * @return the layout branch ID of this layout branch
	 */
	public long getLayoutBranchId();

	/**
	 * Sets the layout branch ID of this layout branch.
	 *
	 * @param LayoutBranchId the layout branch ID of this layout branch
	 */
	public void setLayoutBranchId(long LayoutBranchId);

	/**
	 * Returns the group ID of this layout branch.
	 *
	 * @return the group ID of this layout branch
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this layout branch.
	 *
	 * @param groupId the group ID of this layout branch
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout branch.
	 *
	 * @return the company ID of this layout branch
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout branch.
	 *
	 * @param companyId the company ID of this layout branch
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout branch.
	 *
	 * @return the user ID of this layout branch
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this layout branch.
	 *
	 * @param userId the user ID of this layout branch
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout branch.
	 *
	 * @return the user uuid of this layout branch
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this layout branch.
	 *
	 * @param userUuid the user uuid of this layout branch
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout branch.
	 *
	 * @return the user name of this layout branch
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this layout branch.
	 *
	 * @param userName the user name of this layout branch
	 */
	public void setUserName(String userName);

	/**
	 * Returns the layout set branch ID of this layout branch.
	 *
	 * @return the layout set branch ID of this layout branch
	 */
	public long getLayoutSetBranchId();

	/**
	 * Sets the layout set branch ID of this layout branch.
	 *
	 * @param layoutSetBranchId the layout set branch ID of this layout branch
	 */
	public void setLayoutSetBranchId(long layoutSetBranchId);

	/**
	 * Returns the plid of this layout branch.
	 *
	 * @return the plid of this layout branch
	 */
	public long getPlid();

	/**
	 * Sets the plid of this layout branch.
	 *
	 * @param plid the plid of this layout branch
	 */
	public void setPlid(long plid);

	/**
	 * Returns the name of this layout branch.
	 *
	 * @return the name of this layout branch
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this layout branch.
	 *
	 * @param name the name of this layout branch
	 */
	public void setName(String name);

	/**
	 * Returns the description of this layout branch.
	 *
	 * @return the description of this layout branch
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this layout branch.
	 *
	 * @param description the description of this layout branch
	 */
	public void setDescription(String description);

	/**
	 * Returns the master of this layout branch.
	 *
	 * @return the master of this layout branch
	 */
	public boolean getMaster();

	/**
	 * Returns <code>true</code> if this layout branch is master.
	 *
	 * @return <code>true</code> if this layout branch is master; <code>false</code> otherwise
	 */
	public boolean isMaster();

	/**
	 * Sets whether this layout branch is master.
	 *
	 * @param master the master of this layout branch
	 */
	public void setMaster(boolean master);

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
	public int compareTo(LayoutBranch layoutBranch);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LayoutBranch> toCacheModel();

	@Override
	public LayoutBranch toEscapedModel();

	@Override
	public LayoutBranch toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}