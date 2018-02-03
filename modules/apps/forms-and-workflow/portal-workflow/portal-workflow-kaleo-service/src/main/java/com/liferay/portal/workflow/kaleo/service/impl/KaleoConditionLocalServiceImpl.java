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
import com.liferay.portal.workflow.kaleo.definition.Condition;
<<<<<<< HEAD
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
=======
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.service.base.KaleoConditionLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class KaleoConditionLocalServiceImpl
	extends KaleoConditionLocalServiceBaseImpl {

	@Override
	public KaleoCondition addKaleoCondition(
<<<<<<< HEAD
			long kaleoDefinitionVersionId, long kaleoNodeId,
			Condition condition, ServiceContext serviceContext)
=======
			long kaleoDefinitionId, long kaleoNodeId, Condition condition,
			ServiceContext serviceContext)
>>>>>>> compatible
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoConditionId = counterLocalService.increment();

		KaleoCondition kaleoCondition = kaleoConditionPersistence.create(
			kaleoConditionId);

		kaleoCondition.setCompanyId(user.getCompanyId());
		kaleoCondition.setUserId(user.getUserId());
		kaleoCondition.setUserName(user.getFullName());
		kaleoCondition.setCreateDate(now);
		kaleoCondition.setModifiedDate(now);
<<<<<<< HEAD
		kaleoCondition.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
		kaleoCondition.setKaleoNodeId(kaleoNodeId);
		kaleoCondition.setScript(condition.getScript());

		ScriptLanguage scriptLanguage = condition.getScriptLanguage();

		kaleoCondition.setScriptLanguage(scriptLanguage.getValue());

=======
		kaleoCondition.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoCondition.setKaleoNodeId(kaleoNodeId);
		kaleoCondition.setScript(condition.getScript());
		kaleoCondition.setScriptLanguage(
			condition.getScriptLanguage().getValue());
>>>>>>> compatible
		kaleoCondition.setScriptRequiredContexts(
			condition.getScriptRequiredContexts());

		kaleoConditionPersistence.update(kaleoCondition);

		return kaleoCondition;
	}

	@Override
	public void deleteCompanyKaleoConditions(long companyId) {
		kaleoConditionPersistence.removeByCompanyId(companyId);
	}

	@Override
<<<<<<< HEAD
	public void deleteKaleoDefinitionVersionKaleoCondition(
		long kaleoDefinitionVersionId) {

		kaleoConditionPersistence.removeByKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);
=======
	public void deleteKaleoDefinitionKaleoCondition(long kaleoDefinitionId) {
		kaleoConditionPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
>>>>>>> compatible
	}

	@Override
	public KaleoCondition getKaleoNodeKaleoCondition(long kaleoNodeId)
		throws PortalException {

		return kaleoConditionPersistence.findByKaleoNodeId(kaleoNodeId);
	}

}