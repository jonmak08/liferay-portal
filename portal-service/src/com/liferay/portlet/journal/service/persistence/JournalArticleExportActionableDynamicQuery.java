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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.journal.model.JournalArticle;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JournalArticleExportActionableDynamicQuery
	extends JournalArticleActionableDynamicQuery {
	public JournalArticleExportActionableDynamicQuery(
		PortletDataContext portletDataContext) throws SystemException {
		_portletDataContext = portletDataContext;

		setCompanyId(_portletDataContext.getCompanyId());

		setGroupId(_portletDataContext.getScopeGroupId());
	}

	@Override
	public long performCount() throws PortalException, SystemException {
		ManifestSummary manifestSummary = _portletDataContext.getManifestSummary();

		StagedModelType stagedModelType = getStagedModelType();

		long modelAdditionCount = super.performCount();

		manifestSummary.addModelAdditionCount(stagedModelType.toString(),
			modelAdditionCount);

		long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(_portletDataContext,
				stagedModelType);

		manifestSummary.addModelDeletionCount(stagedModelType.toString(),
			modelDeletionCount);

		return modelAdditionCount;
	}

	@Override
	protected void addCriteria(DynamicQuery dynamicQuery) {
		Criterion modifiedDateCriterion = _portletDataContext.getDateRangeCriteria(
				"modifiedDate");
		Criterion statusDateCriterion = _portletDataContext.getDateRangeCriteria(
				"statusDate");

		if ((modifiedDateCriterion != null) && (statusDateCriterion != null)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			disjunction.add(modifiedDateCriterion);
			disjunction.add(statusDateCriterion);

			dynamicQuery.add(disjunction);
		}

		if (getStagedModelType().getReferrerClassNameId() >= 0) {
			Property classNameIdProperty = PropertyFactoryUtil.forName(
					"classNameId");

			dynamicQuery.add(classNameIdProperty.eq(getStagedModelType()
														.getReferrerClassNameId()));
		}

		Property workflowStatusProperty = PropertyFactoryUtil.forName("status");

		if (_portletDataContext.isInitialPublication()) {
			dynamicQuery.add(workflowStatusProperty.ne(
					WorkflowConstants.STATUS_IN_TRASH));
		}
		else {
			StagedModelDataHandler<?> stagedModelDataHandler = StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(JournalArticle.class.getName());

			dynamicQuery.add(workflowStatusProperty.in(
					stagedModelDataHandler.getExportableStatuses()));
		}
	}

	@Override
	protected Projection getCountProjection() {
		return ProjectionFactoryUtil.countDistinct("resourcePrimKey");
	}

	protected StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				JournalArticle.class.getName()));
	}

	@Override
	@SuppressWarnings("unused")
	protected void performAction(Object object)
		throws PortalException, SystemException {
		JournalArticle stagedModel = (JournalArticle)object;

		StagedModelDataHandlerUtil.exportStagedModel(_portletDataContext,
			stagedModel);
	}

	private PortletDataContext _portletDataContext;
}