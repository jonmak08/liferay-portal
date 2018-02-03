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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the KaleoDefinition service. Represents a row in the &quot;KaleoDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinition
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl
 * @generated
 */
@ProviderType
public interface KaleoDefinitionModel extends BaseModel<KaleoDefinition>,
	GroupedModel, LocalizedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo definition model instance should use the {@link KaleoDefinition} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo definition.
	 *
	 * @return the primary key of this kaleo definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo definition.
	 *
	 * @param primaryKey the primary key of this kaleo definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo definition ID of this kaleo definition.
	 *
	 * @return the kaleo definition ID of this kaleo definition
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo definition.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo definition
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the group ID of this kaleo definition.
	 *
	 * @return the group ID of this kaleo definition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo definition.
	 *
	 * @param groupId the group ID of this kaleo definition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo definition.
	 *
	 * @return the company ID of this kaleo definition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo definition.
	 *
	 * @param companyId the company ID of this kaleo definition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo definition.
	 *
	 * @return the user ID of this kaleo definition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo definition.
	 *
	 * @param userId the user ID of this kaleo definition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo definition.
	 *
	 * @return the user uuid of this kaleo definition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo definition.
	 *
	 * @param userUuid the user uuid of this kaleo definition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo definition.
	 *
	 * @return the user name of this kaleo definition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo definition.
	 *
	 * @param userName the user name of this kaleo definition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo definition.
	 *
	 * @return the create date of this kaleo definition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo definition.
	 *
	 * @param createDate the create date of this kaleo definition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo definition.
	 *
	 * @return the modified date of this kaleo definition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo definition.
	 *
	 * @param modifiedDate the modified date of this kaleo definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this kaleo definition.
	 *
	 * @return the name of this kaleo definition
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kaleo definition.
	 *
	 * @param name the name of this kaleo definition
	 */
	public void setName(String name);

	/**
	 * Returns the title of this kaleo definition.
	 *
	 * @return the title of this kaleo definition
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this kaleo definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this kaleo definition
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this kaleo definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this kaleo definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this kaleo definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this kaleo definition
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this kaleo definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this kaleo definition
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this kaleo definition.
	 *
	 * @return the locales and localized titles of this kaleo definition
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this kaleo definition.
	 *
	 * @param title the title of this kaleo definition
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this kaleo definition in the language.
	 *
	 * @param title the localized title of this kaleo definition
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this kaleo definition in the language, and sets the default locale.
	 *
	 * @param title the localized title of this kaleo definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this kaleo definition from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this kaleo definition
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this kaleo definition from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this kaleo definition
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this kaleo definition.
	 *
	 * @return the description of this kaleo definition
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kaleo definition.
	 *
	 * @param description the description of this kaleo definition
	 */
	public void setDescription(String description);

	/**
	 * Returns the content of this kaleo definition.
	 *
	 * @return the content of this kaleo definition
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this kaleo definition.
	 *
	 * @param content the content of this kaleo definition
	 */
	public void setContent(String content);

	/**
	 * Returns the version of this kaleo definition.
	 *
	 * @return the version of this kaleo definition
	 */
	public int getVersion();

	/**
	 * Sets the version of this kaleo definition.
	 *
	 * @param version the version of this kaleo definition
	 */
	public void setVersion(int version);

	/**
	 * Returns the active of this kaleo definition.
	 *
	 * @return the active of this kaleo definition
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this kaleo definition is active.
	 *
	 * @return <code>true</code> if this kaleo definition is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this kaleo definition is active.
	 *
	 * @param active the active of this kaleo definition
	 */
	public void setActive(boolean active);

<<<<<<< HEAD
=======
	/**
	 * Returns the start kaleo node ID of this kaleo definition.
	 *
	 * @return the start kaleo node ID of this kaleo definition
	 */
	public long getStartKaleoNodeId();

	/**
	 * Sets the start kaleo node ID of this kaleo definition.
	 *
	 * @param startKaleoNodeId the start kaleo node ID of this kaleo definition
	 */
	public void setStartKaleoNodeId(long startKaleoNodeId);

>>>>>>> compatible
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
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(KaleoDefinition kaleoDefinition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<KaleoDefinition> toCacheModel();

	@Override
	public KaleoDefinition toEscapedModel();

	@Override
	public KaleoDefinition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}