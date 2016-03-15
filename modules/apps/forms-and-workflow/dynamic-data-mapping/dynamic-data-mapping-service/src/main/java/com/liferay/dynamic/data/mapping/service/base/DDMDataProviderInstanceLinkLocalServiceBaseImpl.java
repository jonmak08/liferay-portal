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

package com.liferay.dynamic.data.mapping.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d d m data provider instance link local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLinkLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLinkLocalServiceImpl
 * @see com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DDMDataProviderInstanceLinkLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements DDMDataProviderInstanceLinkLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil} to access the d d m data provider instance link local service.
	 */

	/**
	 * Adds the d d m data provider instance link to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstanceLink the d d m data provider instance link
	 * @return the d d m data provider instance link that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMDataProviderInstanceLink addDDMDataProviderInstanceLink(
		DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {
		ddmDataProviderInstanceLink.setNew(true);

		return ddmDataProviderInstanceLinkPersistence.update(ddmDataProviderInstanceLink);
	}

	/**
	 * Creates a new d d m data provider instance link with the primary key. Does not add the d d m data provider instance link to the database.
	 *
	 * @param dataProviderInstanceLinkId the primary key for the new d d m data provider instance link
	 * @return the new d d m data provider instance link
	 */
	@Override
	public DDMDataProviderInstanceLink createDDMDataProviderInstanceLink(
		long dataProviderInstanceLinkId) {
		return ddmDataProviderInstanceLinkPersistence.create(dataProviderInstanceLinkId);
	}

	/**
	 * Deletes the d d m data provider instance link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataProviderInstanceLinkId the primary key of the d d m data provider instance link
	 * @return the d d m data provider instance link that was removed
	 * @throws PortalException if a d d m data provider instance link with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMDataProviderInstanceLink deleteDDMDataProviderInstanceLink(
		long dataProviderInstanceLinkId) throws PortalException {
		return ddmDataProviderInstanceLinkPersistence.remove(dataProviderInstanceLinkId);
	}

	/**
	 * Deletes the d d m data provider instance link from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstanceLink the d d m data provider instance link
	 * @return the d d m data provider instance link that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMDataProviderInstanceLink deleteDDMDataProviderInstanceLink(
		DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {
		return ddmDataProviderInstanceLinkPersistence.remove(ddmDataProviderInstanceLink);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DDMDataProviderInstanceLink.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmDataProviderInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return ddmDataProviderInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return ddmDataProviderInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ddmDataProviderInstanceLinkPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return ddmDataProviderInstanceLinkPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DDMDataProviderInstanceLink fetchDDMDataProviderInstanceLink(
		long dataProviderInstanceLinkId) {
		return ddmDataProviderInstanceLinkPersistence.fetchByPrimaryKey(dataProviderInstanceLinkId);
	}

	/**
	 * Returns the d d m data provider instance link with the primary key.
	 *
	 * @param dataProviderInstanceLinkId the primary key of the d d m data provider instance link
	 * @return the d d m data provider instance link
	 * @throws PortalException if a d d m data provider instance link with the primary key could not be found
	 */
	@Override
	public DDMDataProviderInstanceLink getDDMDataProviderInstanceLink(
		long dataProviderInstanceLinkId) throws PortalException {
		return ddmDataProviderInstanceLinkPersistence.findByPrimaryKey(dataProviderInstanceLinkId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMDataProviderInstanceLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceLinkId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMDataProviderInstanceLink.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceLinkId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMDataProviderInstanceLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceLinkId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ddmDataProviderInstanceLinkLocalService.deleteDDMDataProviderInstanceLink((DDMDataProviderInstanceLink)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return ddmDataProviderInstanceLinkPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the d d m data provider instance links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m data provider instance links
	 * @param end the upper bound of the range of d d m data provider instance links (not inclusive)
	 * @return the range of d d m data provider instance links
	 */
	@Override
	public List<DDMDataProviderInstanceLink> getDDMDataProviderInstanceLinks(
		int start, int end) {
		return ddmDataProviderInstanceLinkPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m data provider instance links.
	 *
	 * @return the number of d d m data provider instance links
	 */
	@Override
	public int getDDMDataProviderInstanceLinksCount() {
		return ddmDataProviderInstanceLinkPersistence.countAll();
	}

	/**
	 * Updates the d d m data provider instance link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstanceLink the d d m data provider instance link
	 * @return the d d m data provider instance link that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMDataProviderInstanceLink updateDDMDataProviderInstanceLink(
		DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {
		return ddmDataProviderInstanceLinkPersistence.update(ddmDataProviderInstanceLink);
	}

	/**
	 * Returns the d d m data provider instance link local service.
	 *
	 * @return the d d m data provider instance link local service
	 */
	public DDMDataProviderInstanceLinkLocalService getDDMDataProviderInstanceLinkLocalService() {
		return ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Sets the d d m data provider instance link local service.
	 *
	 * @param ddmDataProviderInstanceLinkLocalService the d d m data provider instance link local service
	 */
	public void setDDMDataProviderInstanceLinkLocalService(
		DDMDataProviderInstanceLinkLocalService ddmDataProviderInstanceLinkLocalService) {
		this.ddmDataProviderInstanceLinkLocalService = ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Returns the d d m data provider instance link persistence.
	 *
	 * @return the d d m data provider instance link persistence
	 */
	public DDMDataProviderInstanceLinkPersistence getDDMDataProviderInstanceLinkPersistence() {
		return ddmDataProviderInstanceLinkPersistence;
	}

	/**
	 * Sets the d d m data provider instance link persistence.
	 *
	 * @param ddmDataProviderInstanceLinkPersistence the d d m data provider instance link persistence
	 */
	public void setDDMDataProviderInstanceLinkPersistence(
		DDMDataProviderInstanceLinkPersistence ddmDataProviderInstanceLinkPersistence) {
		this.ddmDataProviderInstanceLinkPersistence = ddmDataProviderInstanceLinkPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink",
			ddmDataProviderInstanceLinkLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMDataProviderInstanceLinkLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMDataProviderInstanceLink.class;
	}

	protected String getModelClassName() {
		return DDMDataProviderInstanceLink.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmDataProviderInstanceLinkPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService.class)
	protected DDMDataProviderInstanceLinkLocalService ddmDataProviderInstanceLinkLocalService;
	@BeanReference(type = DDMDataProviderInstanceLinkPersistence.class)
	protected DDMDataProviderInstanceLinkPersistence ddmDataProviderInstanceLinkPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}