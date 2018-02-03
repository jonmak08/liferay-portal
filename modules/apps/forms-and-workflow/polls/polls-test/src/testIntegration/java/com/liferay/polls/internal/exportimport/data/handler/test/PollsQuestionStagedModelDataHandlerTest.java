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

package com.liferay.polls.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
<<<<<<< HEAD
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.test.util.lar.BaseStagedModelDataHandlerTestCase;
=======
>>>>>>> compatible
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.polls.util.test.PollsTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
<<<<<<< HEAD
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Date;
=======
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.lar.test.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

>>>>>>> compatible
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
<<<<<<< HEAD
import org.junit.Test;
=======
>>>>>>> compatible
import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@RunWith(Arquillian.class)
<<<<<<< HEAD
=======
@Sync
>>>>>>> compatible
public class PollsQuestionStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
<<<<<<< HEAD
		new LiferayIntegrationTestRule();

	@Test
	public void testImportedWithExpiredDate() throws Exception {
		initExport();

		PollsQuestion pollsQuestion = PollsTestUtil.addQuestion(
			stagingGroup.getGroupId());

		pollsQuestion.setExpirationDate(new Date());

		pollsQuestion = PollsQuestionLocalServiceUtil.updatePollsQuestion(
			pollsQuestion);

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, pollsQuestion);

		initImport();

		PollsQuestion exportedQuestion = (PollsQuestion)readExportedStagedModel(
			pollsQuestion);

		Assert.assertNotNull(exportedQuestion);

		ExportImportThreadLocal.setPortletImportInProcess(true);

		try {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, exportedQuestion);
		}
		finally {
			ExportImportThreadLocal.setPortletImportInProcess(false);
		}

		PollsQuestion importedQuestion = (PollsQuestion)getStagedModel(
			pollsQuestion.getUuid(), liveGroup);

		Assert.assertNotNull(importedQuestion);
	}
=======
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);
>>>>>>> compatible

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		return PollsTestUtil.addQuestion(group.getGroupId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return PollsQuestionLocalServiceUtil.
				getPollsQuestionByUuidAndGroupId(uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return PollsQuestion.class;
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		super.validateImportedStagedModel(stagedModel, importedStagedModel);

		PollsQuestion question = (PollsQuestion)stagedModel;
		PollsQuestion importedQuestion = (PollsQuestion)importedStagedModel;

		Assert.assertEquals(question.getTitle(), importedQuestion.getTitle());
		Assert.assertEquals(
			question.getDescription(), importedQuestion.getDescription());
		Assert.assertEquals(
			question.getExpirationDate(), importedQuestion.getExpirationDate());
		Assert.assertEquals(
			question.getLastVoteDate(), importedQuestion.getLastVoteDate());
	}

}