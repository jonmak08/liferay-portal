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

package com.liferay.dynamic.data.mapping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLinkModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DDMDataProviderInstanceLink service. Represents a row in the &quot;DDMDataProviderInstanceLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DDMDataProviderInstanceLinkModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMDataProviderInstanceLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstanceLinkImpl
 * @see DDMDataProviderInstanceLink
 * @see DDMDataProviderInstanceLinkModel
 * @generated
 */
@ProviderType
public class DDMDataProviderInstanceLinkModelImpl extends BaseModelImpl<DDMDataProviderInstanceLink>
	implements DDMDataProviderInstanceLinkModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a d d m data provider instance link model instance should use the {@link DDMDataProviderInstanceLink} interface instead.
	 */
	public static final String TABLE_NAME = "DDMDataProviderInstanceLink";
	public static final Object[][] TABLE_COLUMNS = {
			{ "dataProviderInstanceLinkId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "dataProviderInstanceId", Types.BIGINT },
			{ "structureId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("dataProviderInstanceLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataProviderInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table DDMDataProviderInstanceLink (dataProviderInstanceLinkId LONG not null primary key,companyId LONG,dataProviderInstanceId LONG,structureId LONG)";
	public static final String TABLE_SQL_DROP = "drop table DDMDataProviderInstanceLink";
	public static final String ORDER_BY_JPQL = " ORDER BY ddmDataProviderInstanceLink.dataProviderInstanceLinkId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DDMDataProviderInstanceLink.dataProviderInstanceLinkId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink"),
			true);
	public static final long DATAPROVIDERINSTANCEID_COLUMN_BITMASK = 1L;
	public static final long STRUCTUREID_COLUMN_BITMASK = 2L;
	public static final long DATAPROVIDERINSTANCELINKID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink"));

	public DDMDataProviderInstanceLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDataProviderInstanceLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMDataProviderInstanceLink.class;
	}

	@Override
	public String getModelClassName() {
		return DDMDataProviderInstanceLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dataProviderInstanceLinkId",
			getDataProviderInstanceLinkId());
		attributes.put("companyId", getCompanyId());
		attributes.put("dataProviderInstanceId", getDataProviderInstanceId());
		attributes.put("structureId", getStructureId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dataProviderInstanceLinkId = (Long)attributes.get(
				"dataProviderInstanceLinkId");

		if (dataProviderInstanceLinkId != null) {
			setDataProviderInstanceLinkId(dataProviderInstanceLinkId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long dataProviderInstanceId = (Long)attributes.get(
				"dataProviderInstanceId");

		if (dataProviderInstanceId != null) {
			setDataProviderInstanceId(dataProviderInstanceId);
		}

		Long structureId = (Long)attributes.get("structureId");

		if (structureId != null) {
			setStructureId(structureId);
		}
	}

	@Override
	public long getDataProviderInstanceLinkId() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setDataProviderInstanceLinkId(long dataProviderInstanceLinkId) {
		_dataProviderInstanceLinkId = dataProviderInstanceLinkId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getDataProviderInstanceId() {
		return _dataProviderInstanceId;
	}

	@Override
	public void setDataProviderInstanceId(long dataProviderInstanceId) {
		_columnBitmask |= DATAPROVIDERINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalDataProviderInstanceId) {
			_setOriginalDataProviderInstanceId = true;

			_originalDataProviderInstanceId = _dataProviderInstanceId;
		}

		_dataProviderInstanceId = dataProviderInstanceId;
	}

	public long getOriginalDataProviderInstanceId() {
		return _originalDataProviderInstanceId;
	}

	@Override
	public long getStructureId() {
		return _structureId;
	}

	@Override
	public void setStructureId(long structureId) {
		_columnBitmask |= STRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalStructureId) {
			_setOriginalStructureId = true;

			_originalStructureId = _structureId;
		}

		_structureId = structureId;
	}

	public long getOriginalStructureId() {
		return _originalStructureId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DDMDataProviderInstanceLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMDataProviderInstanceLink toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DDMDataProviderInstanceLink)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DDMDataProviderInstanceLinkImpl ddmDataProviderInstanceLinkImpl = new DDMDataProviderInstanceLinkImpl();

		ddmDataProviderInstanceLinkImpl.setDataProviderInstanceLinkId(getDataProviderInstanceLinkId());
		ddmDataProviderInstanceLinkImpl.setCompanyId(getCompanyId());
		ddmDataProviderInstanceLinkImpl.setDataProviderInstanceId(getDataProviderInstanceId());
		ddmDataProviderInstanceLinkImpl.setStructureId(getStructureId());

		ddmDataProviderInstanceLinkImpl.resetOriginalValues();

		return ddmDataProviderInstanceLinkImpl;
	}

	@Override
	public int compareTo(
		DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {
		long primaryKey = ddmDataProviderInstanceLink.getPrimaryKey();

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

		if (!(obj instanceof DDMDataProviderInstanceLink)) {
			return false;
		}

		DDMDataProviderInstanceLink ddmDataProviderInstanceLink = (DDMDataProviderInstanceLink)obj;

		long primaryKey = ddmDataProviderInstanceLink.getPrimaryKey();

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
		DDMDataProviderInstanceLinkModelImpl ddmDataProviderInstanceLinkModelImpl =
			this;

		ddmDataProviderInstanceLinkModelImpl._originalDataProviderInstanceId = ddmDataProviderInstanceLinkModelImpl._dataProviderInstanceId;

		ddmDataProviderInstanceLinkModelImpl._setOriginalDataProviderInstanceId = false;

		ddmDataProviderInstanceLinkModelImpl._originalStructureId = ddmDataProviderInstanceLinkModelImpl._structureId;

		ddmDataProviderInstanceLinkModelImpl._setOriginalStructureId = false;

		ddmDataProviderInstanceLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMDataProviderInstanceLink> toCacheModel() {
		DDMDataProviderInstanceLinkCacheModel ddmDataProviderInstanceLinkCacheModel =
			new DDMDataProviderInstanceLinkCacheModel();

		ddmDataProviderInstanceLinkCacheModel.dataProviderInstanceLinkId = getDataProviderInstanceLinkId();

		ddmDataProviderInstanceLinkCacheModel.companyId = getCompanyId();

		ddmDataProviderInstanceLinkCacheModel.dataProviderInstanceId = getDataProviderInstanceId();

		ddmDataProviderInstanceLinkCacheModel.structureId = getStructureId();

		return ddmDataProviderInstanceLinkCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{dataProviderInstanceLinkId=");
		sb.append(getDataProviderInstanceLinkId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", dataProviderInstanceId=");
		sb.append(getDataProviderInstanceId());
		sb.append(", structureId=");
		sb.append(getStructureId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>dataProviderInstanceLinkId</column-name><column-value><![CDATA[");
		sb.append(getDataProviderInstanceLinkId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dataProviderInstanceId</column-name><column-value><![CDATA[");
		sb.append(getDataProviderInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>structureId</column-name><column-value><![CDATA[");
		sb.append(getStructureId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DDMDataProviderInstanceLink.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DDMDataProviderInstanceLink.class
		};
	private long _dataProviderInstanceLinkId;
	private long _companyId;
	private long _dataProviderInstanceId;
	private long _originalDataProviderInstanceId;
	private boolean _setOriginalDataProviderInstanceId;
	private long _structureId;
	private long _originalStructureId;
	private boolean _setOriginalStructureId;
	private long _columnBitmask;
	private DDMDataProviderInstanceLink _escapedModel;
}