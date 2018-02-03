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

package com.liferay.portal.workflow.kaleo.service.impl;

<<<<<<< HEAD
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.petra.string.StringPool;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
<<<<<<< HEAD
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
=======
import com.liferay.portal.workflow.kaleo.exception.NoSuchDefinitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.service.base.KaleoDefinitionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoDefinitionLocalServiceImpl
	extends KaleoDefinitionLocalServiceBaseImpl {

	@Override
	public void activateKaleoDefinition(
<<<<<<< HEAD
			long kaleoDefinitionId, long kaleoDefinitionVersionId,
			long startKaleoNodeId, ServiceContext serviceContext)
		throws PortalException {

		// Kaleo definition

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByPrimaryKey(kaleoDefinitionId);

		kaleoDefinition.setModifiedDate(new Date());
		kaleoDefinition.setActive(true);

		kaleoDefinitionPersistence.update(kaleoDefinition);

		// Kaleo definition version

		KaleoDefinitionVersion kaleoDefinitionVersion =
			kaleoDefinitionVersionPersistence.findByPrimaryKey(
				kaleoDefinitionVersionId);

		kaleoDefinitionVersion.setModifiedDate(new Date());
		kaleoDefinitionVersion.setStartKaleoNodeId(startKaleoNodeId);

		kaleoDefinitionVersionPersistence.update(kaleoDefinitionVersion);
=======
			long kaleoDefinitionId, long startKaleoNodeId,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByPrimaryKey(kaleoDefinitionId);

		try {
			KaleoDefinition previousKaleoDefinition = getLatestKaleoDefinition(
				kaleoDefinition.getName(), serviceContext);

			previousKaleoDefinition.setModifiedDate(new Date());
			previousKaleoDefinition.setActive(false);

			kaleoDefinitionPersistence.update(previousKaleoDefinition);
		}
		catch (NoSuchDefinitionException nsde) {
		}

		kaleoDefinition.setStartKaleoNodeId(startKaleoNodeId);
		kaleoDefinition.setModifiedDate(new Date());
		kaleoDefinition.setActive(true);

		kaleoDefinitionPersistence.update(kaleoDefinition);
>>>>>>> compatible
	}

	@Override
	public void activateKaleoDefinition(
			long kaleoDefinitionId, ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		// Kaleo definition

=======
>>>>>>> compatible
		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByPrimaryKey(kaleoDefinitionId);

		kaleoDefinition.setModifiedDate(new Date());
		kaleoDefinition.setActive(true);

		kaleoDefinitionPersistence.update(kaleoDefinition);
	}

	@Override
	public void activateKaleoDefinition(
			String name, int version, ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		// Kaleo definition

=======
>>>>>>> compatible
		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version);

		kaleoDefinition.setModifiedDate(new Date());
		kaleoDefinition.setActive(true);

		kaleoDefinitionPersistence.update(kaleoDefinition);
	}

	@Override
	public KaleoDefinition addKaleoDefinition(
			String name, String title, String description, String content,
			int version, ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		// Kaleo definition

=======
>>>>>>> compatible
		User user = userLocalService.getUser(serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoDefinitionId = counterLocalService.increment();

		KaleoDefinition kaleoDefinition = kaleoDefinitionPersistence.create(
			kaleoDefinitionId);

<<<<<<< HEAD
		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoDefinition.setGroupId(groupId);

=======
>>>>>>> compatible
		kaleoDefinition.setCompanyId(user.getCompanyId());
		kaleoDefinition.setUserId(user.getUserId());
		kaleoDefinition.setUserName(user.getFullName());
		kaleoDefinition.setCreateDate(now);
		kaleoDefinition.setModifiedDate(now);
		kaleoDefinition.setName(name);
		kaleoDefinition.setTitle(title);
		kaleoDefinition.setDescription(description);
		kaleoDefinition.setContent(content);
		kaleoDefinition.setVersion(version);
		kaleoDefinition.setActive(false);

		kaleoDefinitionPersistence.update(kaleoDefinition);

<<<<<<< HEAD
		// Kaleo definition version

		kaleoDefinitionVersionLocalService.addKaleoDefinitionVersion(
			name, title, description, content, getVersion(version),
			serviceContext);

=======
>>>>>>> compatible
		return kaleoDefinition;
	}

	@Override
	public void deactivateKaleoDefinition(
			String name, int version, ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version);

		kaleoDefinition.setModifiedDate(new Date());
		kaleoDefinition.setActive(false);

		kaleoDefinitionPersistence.update(kaleoDefinition);
	}

	@Override
	public void deleteCompanyKaleoDefinitions(long companyId) {

		// Kaleo definitions

		kaleoDefinitionPersistence.removeByCompanyId(companyId);

<<<<<<< HEAD
		// Kaleo definition version

		kaleoDefinitionVersionPersistence.removeByCompanyId(companyId);

=======
>>>>>>> compatible
		// Kaleo condition

		kaleoConditionLocalService.deleteCompanyKaleoConditions(companyId);

		// Kaleo instances

		kaleoInstanceLocalService.deleteCompanyKaleoInstances(companyId);

		// Kaleo nodes

		kaleoNodeLocalService.deleteCompanyKaleoNodes(companyId);

		// Kaleo tasks

		kaleoTaskLocalService.deleteCompanyKaleoTasks(companyId);

		// Kaleo transitions

		kaleoTransitionLocalService.deleteCompanyKaleoTransitions(companyId);
	}

	@Override
	public void deleteKaleoDefinition(
<<<<<<< HEAD
			String name, ServiceContext serviceContext)
=======
			String name, int version, ServiceContext serviceContext)
>>>>>>> compatible
		throws PortalException {

		// Kaleo definition

		KaleoDefinition kaleoDefinition = getKaleoDefinition(
<<<<<<< HEAD
			name, serviceContext);
=======
			name, version, serviceContext);
>>>>>>> compatible

		if (kaleoDefinition.isActive()) {
			throw new WorkflowException(
				"Cannot delete active workflow definition " +
					kaleoDefinition.getKaleoDefinitionId());
		}

<<<<<<< HEAD
		kaleoDefinitionPersistence.remove(kaleoDefinition);

		// Kaleo definition version

		kaleoDefinitionVersionLocalService.deleteKaleoDefinitionVersions(
			kaleoDefinition.getKaleoDefinitionVersions());
	}

	@Override
	public KaleoDefinition fetchKaleoDefinition(
			String name, ServiceContext serviceContext)
		throws PortalException {

		return kaleoDefinitionPersistence.fetchByC_N(
			serviceContext.getCompanyId(), name);
=======
		if (kaleoDefinition.hasIncompleteKaleoInstances()) {
			throw new WorkflowException(
				"Cannot delete incomplete workflow definition " +
					kaleoDefinition.getKaleoDefinitionId());
		}

		kaleoDefinitionPersistence.remove(kaleoDefinition);

		// Kaleo condition

		kaleoConditionLocalService.deleteKaleoDefinitionKaleoCondition(
			kaleoDefinition.getKaleoDefinitionId());

		// Kaleo instances

		kaleoInstanceLocalService.deleteKaleoDefinitionKaleoInstances(
			kaleoDefinition.getKaleoDefinitionId());

		// Kaleo nodes

		kaleoNodeLocalService.deleteKaleoDefinitionKaleoNodes(
			kaleoDefinition.getKaleoDefinitionId());

		// Kaleo tasks

		kaleoTaskLocalService.deleteKaleoDefinitionKaleoTasks(
			kaleoDefinition.getKaleoDefinitionId());

		// Kaleo transitions

		kaleoTransitionLocalService.deleteKaleoDefinitionKaleoTransitions(
			kaleoDefinition.getKaleoDefinitionId());
	}

	@Override
	public KaleoDefinition fetchLatestKaleoDefinition(
			String name, ServiceContext serviceContext)
		throws PortalException {

		List<KaleoDefinition> kaleoDefinitions =
			kaleoDefinitionPersistence.findByC_N(
				serviceContext.getCompanyId(), name, 0, 1);

		if (kaleoDefinitions.isEmpty()) {
			return null;
		}

		return kaleoDefinitions.get(0);
>>>>>>> compatible
	}

	@Override
	public KaleoDefinition getKaleoDefinition(
<<<<<<< HEAD
			String name, ServiceContext serviceContext)
		throws PortalException {

		return kaleoDefinitionPersistence.findByC_N(
			serviceContext.getCompanyId(), name);
=======
			String name, int version, ServiceContext serviceContext)
		throws PortalException {

		return kaleoDefinitionPersistence.findByC_N_V(
			serviceContext.getCompanyId(), name, version);
>>>>>>> compatible
	}

	@Override
	public List<KaleoDefinition> getKaleoDefinitions(
		boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.findByC_A(
			serviceContext.getCompanyId(), active, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoDefinition> getKaleoDefinitions(
		int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.findByCompanyId(
			serviceContext.getCompanyId(), start, end, orderByComparator);
	}

	@Override
<<<<<<< HEAD
=======
	public List<KaleoDefinition> getKaleoDefinitions(
		String name, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.findByC_N_A(
			serviceContext.getCompanyId(), name, active, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoDefinition> getKaleoDefinitions(
		String name, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.findByC_N(
			serviceContext.getCompanyId(), name, start, end, orderByComparator);
	}

	@Override
>>>>>>> compatible
	public int getKaleoDefinitionsCount(
		boolean active, ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.countByC_A(
			serviceContext.getCompanyId(), active);
	}

	@Override
	public int getKaleoDefinitionsCount(ServiceContext serviceContext) {
		return kaleoDefinitionPersistence.countByCompanyId(
			serviceContext.getCompanyId());
	}

	@Override
	public int getKaleoDefinitionsCount(
		String name, boolean active, ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.countByC_N_A(
			serviceContext.getCompanyId(), name, active);
	}

	@Override
	public int getKaleoDefinitionsCount(
		String name, ServiceContext serviceContext) {

		return kaleoDefinitionPersistence.countByC_N(
			serviceContext.getCompanyId(), name);
	}

	@Override
<<<<<<< HEAD
	public KaleoDefinition incrementKaleoDefinition(
			Definition definition, String name, String title,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition = getKaleoDefinition(
			name, serviceContext);

		return updatedKaleoDefinition(
			kaleoDefinition.getKaleoDefinitionId(), title,
			definition.getDescription(), definition.getContent(),
			kaleoDefinition.getVersion() + 1, serviceContext);
	}

	public KaleoDefinition updatedKaleoDefinition(
			long kaleoDefinitionId, String title, String description,
			String content, int version, ServiceContext serviceContext)
		throws PortalException {

		// Kaleo definition

		User user = userLocalService.getUser(serviceContext.getGuestOrUserId());
		Date now = new Date();

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByPrimaryKey(kaleoDefinitionId);

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoDefinition.setGroupId(groupId);

		kaleoDefinition.setUserId(user.getUserId());
		kaleoDefinition.setUserName(user.getFullName());
		kaleoDefinition.setCreateDate(now);
		kaleoDefinition.setModifiedDate(now);
		kaleoDefinition.setTitle(title);
		kaleoDefinition.setDescription(description);
		kaleoDefinition.setContent(content);
		kaleoDefinition.setVersion(version);
		kaleoDefinition.setActive(false);

		kaleoDefinitionPersistence.update(kaleoDefinition);

		// Kaleo definition version

		kaleoDefinitionVersionLocalService.addKaleoDefinitionVersion(
			kaleoDefinition.getName(), title, description, content,
			getVersion(version), serviceContext);

		return kaleoDefinition;
	}

	protected String getVersion(int version) {
		return version + StringPool.PERIOD + 0;
	}

=======
	public KaleoDefinition getLatestKaleoDefinition(
			String name, ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition = fetchLatestKaleoDefinition(
			name, serviceContext);

		if (kaleoDefinition == null) {
			throw new NoSuchDefinitionException();
		}

		return kaleoDefinition;
	}

	@Override
	public KaleoDefinition incrementKaleoDefinition(
			Definition definition, String title, ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition = getLatestKaleoDefinition(
			definition.getName(), serviceContext);

		return addKaleoDefinition(
			definition.getName(), title, definition.getDescription(),
			definition.getContent(), kaleoDefinition.getVersion() + 1,
			serviceContext);
	}

	@Override
	public KaleoDefinition updateTitle(
			String name, int version, String title,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version);

		kaleoDefinition.setTitle(title);

		kaleoDefinitionPersistence.update(kaleoDefinition);

		return kaleoDefinition;
	}

>>>>>>> compatible
}