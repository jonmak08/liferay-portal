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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Timer;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTransitionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTransitionLocalServiceImpl
	extends KaleoTransitionLocalServiceBaseImpl {

	@Override
	public KaleoTransition addKaleoTransition(
<<<<<<< HEAD
			long kaleoDefinitionVersionId, long kaleoNodeId,
			Transition transition, KaleoNode sourceKaleoNode,
			KaleoNode targetKaleoNode, ServiceContext serviceContext)
=======
			long kaleoDefinitionId, long kaleoNodeId, Transition transition,
			KaleoNode sourceKaleoNode, KaleoNode targetKaleoNode,
			ServiceContext serviceContext)
>>>>>>> compatible
		throws PortalException {

		// Kaleo transition

		User user = userLocalService.getUser(serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTransitionId = counterLocalService.increment();

		KaleoTransition kaleoTransition = kaleoTransitionPersistence.create(
			kaleoTransitionId);

		kaleoTransition.setCompanyId(user.getCompanyId());
		kaleoTransition.setUserId(user.getUserId());
		kaleoTransition.setUserName(user.getFullName());
		kaleoTransition.setCreateDate(now);
		kaleoTransition.setModifiedDate(now);
<<<<<<< HEAD
		kaleoTransition.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
=======
		kaleoTransition.setKaleoDefinitionId(kaleoDefinitionId);
>>>>>>> compatible
		kaleoTransition.setKaleoNodeId(kaleoNodeId);
		kaleoTransition.setName(transition.getName());
		kaleoTransition.setSourceKaleoNodeId(sourceKaleoNode.getKaleoNodeId());
		kaleoTransition.setSourceKaleoNodeName(sourceKaleoNode.getName());
		kaleoTransition.setTargetKaleoNodeId(targetKaleoNode.getKaleoNodeId());
		kaleoTransition.setTargetKaleoNodeName(targetKaleoNode.getName());
		kaleoTransition.setDefaultTransition(transition.isDefault());

		kaleoTransitionPersistence.update(kaleoTransition);

		// Kaleo timer

		Timer timer = transition.getTimer();

		if (timer != null) {
			kaleoTimerLocalService.addKaleoTimer(
				KaleoTransition.class.getName(), kaleoTransitionId,
<<<<<<< HEAD
				kaleoDefinitionVersionId, timer, serviceContext);
=======
				kaleoDefinitionId, timer, serviceContext);
>>>>>>> compatible
		}

		return kaleoTransition;
	}

	@Override
	public void deleteCompanyKaleoTransitions(long companyId) {
		kaleoTransitionPersistence.removeByCompanyId(companyId);
	}

	@Override
<<<<<<< HEAD
	public void deleteKaleoDefinitionVersionKaleoTransitions(
		long kaleoDefinitionVersionId) {

		kaleoTransitionPersistence.removeByKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);
=======
	public void deleteKaleoDefinitionKaleoTransitions(long kaleoDefinitionId) {
		kaleoTransitionPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
>>>>>>> compatible
	}

	@Override
	public KaleoTransition getDefaultKaleoTransition(long kaleoNodeId)
		throws PortalException {

		return kaleoTransitionPersistence.findByKNI_DT(kaleoNodeId, true);
	}

	@Override
<<<<<<< HEAD
	public List<KaleoTransition> getKaleoDefinitionVersionKaleoTransitions(
		long kaleoDefinitionVersionId) {

		return kaleoTransitionPersistence.findByKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);
=======
	public List<KaleoTransition> getKaleoDefinitionKaleoTransitions(
		long kaleoDefinitionId) {

		return kaleoTransitionPersistence.findByKaleoDefinitionId(
			kaleoDefinitionId);
>>>>>>> compatible
	}

	@Override
	public KaleoTransition getKaleoTransition(long kaleoNodeId, String name)
		throws PortalException {

		return kaleoTransitionPersistence.findByKNI_N(kaleoNodeId, name);
	}

	@Override
	public List<KaleoTransition> getKaleoTransitions(long kaleoNodeId) {
		return kaleoTransitionPersistence.findByKaleoNodeId(kaleoNodeId);
	}

	@Override
	public int getKaleoTransitionsCount(long kaleoNodeId) {
		return kaleoTransitionPersistence.countByKaleoNodeId(kaleoNodeId);
	}

}