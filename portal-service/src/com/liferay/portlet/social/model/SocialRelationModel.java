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

package com.liferay.portlet.social.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SocialRelation service. Represents a row in the &quot;SocialRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.social.model.impl.SocialRelationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.social.model.impl.SocialRelationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialRelation
 * @see com.liferay.portlet.social.model.impl.SocialRelationImpl
 * @see com.liferay.portlet.social.model.impl.SocialRelationModelImpl
 * @generated
 */
@ProviderType
public interface SocialRelationModel extends BaseModel<SocialRelation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social relation model instance should use the {@link SocialRelation} interface instead.
	 */

	/**
	 * Returns the primary key of this social relation.
	 *
	 * @return the primary key of this social relation
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social relation.
	 *
	 * @param primaryKey the primary key of this social relation
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this social relation.
	 *
	 * @return the uuid of this social relation
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this social relation.
	 *
	 * @param uuid the uuid of this social relation
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the relation ID of this social relation.
	 *
	 * @return the relation ID of this social relation
	 */
	public long getRelationId();

	/**
	 * Sets the relation ID of this social relation.
	 *
	 * @param relationId the relation ID of this social relation
	 */
	public void setRelationId(long relationId);

	/**
	 * Returns the company ID of this social relation.
	 *
	 * @return the company ID of this social relation
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this social relation.
	 *
	 * @param companyId the company ID of this social relation
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this social relation.
	 *
	 * @return the create date of this social relation
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this social relation.
	 *
	 * @param createDate the create date of this social relation
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the user id1 of this social relation.
	 *
	 * @return the user id1 of this social relation
	 */
	public long getUserId1();

	/**
	 * Sets the user id1 of this social relation.
	 *
	 * @param userId1 the user id1 of this social relation
	 */
	public void setUserId1(long userId1);

	/**
	 * Returns the user id2 of this social relation.
	 *
	 * @return the user id2 of this social relation
	 */
	public long getUserId2();

	/**
	 * Sets the user id2 of this social relation.
	 *
	 * @param userId2 the user id2 of this social relation
	 */
	public void setUserId2(long userId2);

	/**
	 * Returns the type of this social relation.
	 *
	 * @return the type of this social relation
	 */
	public int getType();

	/**
	 * Sets the type of this social relation.
	 *
	 * @param type the type of this social relation
	 */
	public void setType(int type);

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
	public int compareTo(
		com.liferay.portlet.social.model.SocialRelation socialRelation);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portlet.social.model.SocialRelation> toCacheModel();

	@Override
	public com.liferay.portlet.social.model.SocialRelation toEscapedModel();

	@Override
	public com.liferay.portlet.social.model.SocialRelation toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}