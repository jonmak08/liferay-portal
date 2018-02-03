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

package com.liferay.dynamic.data.mapping.verify;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
<<<<<<< HEAD
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
=======
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateLink;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.validator.DDMFormLayoutValidator;
import com.liferay.dynamic.data.mapping.validator.DDMFormValidator;
import com.liferay.dynamic.data.mapping.validator.DDMFormValuesValidator;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.verify.VerifyProcess;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = {"verify.process.name=com.liferay.dynamic.data.mapping.service"},
	service = VerifyProcess.class
)
public class DDMServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyStructures();
		verifyStructureLinks();
		verifyTemplateLinks();

		verifyContents();
	}

	protected DDMFormValues getDDMFormValues(
<<<<<<< HEAD
			DDMForm ddmForm, DDMContent ddmContent)
		throws PortalException {

		return _ddmFormValuesJSONDeserializer.deserialize(
			ddmForm, ddmContent.getData());
	}

	protected DDMFormValues getDDMFormValues(
			DDMStructure ddmStructure, DDMContent ddmContent)
		throws PortalException {

		return getDDMFormValues(ddmStructure.getDDMForm(), ddmContent);
=======
			DDMStructure structure, DDMContent content)
		throws PortalException {

		return _ddmFormValuesJSONDeserializer.deserialize(
			structure.getDDMForm(), content.getData());
>>>>>>> compatible
	}

	@Reference(unbind = "-")
	protected void setDDMContentLocalService(
		DDMContentLocalService ddmContentLocalService) {

		_ddmContentLocalService = ddmContentLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMFormLayoutValidator(
		DDMFormLayoutValidator ddmFormLayoutValidator) {

		_ddmFormLayoutValidator = ddmFormLayoutValidator;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValidator(DDMFormValidator ddmFormValidator) {
		_ddmFormValidator = ddmFormValidator;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONDeserializer(
		DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer) {

		_ddmFormValuesJSONDeserializer = ddmFormValuesJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesValidator(
		DDMFormValuesValidator ddmFormValuesValidator) {

		_ddmFormValuesValidator = ddmFormValuesValidator;
	}

	@Reference(unbind = "-")
	protected void setDDMStorageLinkLocalService(
		DDMStorageLinkLocalService ddmStorageLinkLocalService) {

		_ddmStorageLinkLocalService = ddmStorageLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLinkLocalService(
		DDMStructureLinkLocalService ddmStructureLinkLocalService) {

		_ddmStructureLinkLocalService = ddmStructureLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureVersionLocalService(
		DDMStructureVersionLocalService ddmStructureVersionLocalService) {
<<<<<<< HEAD

		_ddmStructureVersionLocalService = ddmStructureVersionLocalService;
=======
>>>>>>> compatible
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLinkLocalService(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

<<<<<<< HEAD
	protected void verifyContent(DDMContent ddmContent) throws PortalException {
		DDMStorageLink ddmStorageLink =
			_ddmStorageLinkLocalService.getClassStorageLink(
				ddmContent.getContentId());

		DDMStructureVersion ddmStructureVersion =
			_ddmStructureVersionLocalService.getStructureVersion(
				ddmStorageLink.getStructureVersionId());

		try {
			DDMFormValues ddmFormValues = getDDMFormValues(
				ddmStructureVersion.getDDMForm(), ddmContent);
=======
	protected void verifyContent(DDMContent content) throws PortalException {
		DDMStorageLink ddmStorageLink =
			_ddmStorageLinkLocalService.getClassStorageLink(
				content.getContentId());

		DDMStructure structure = _ddmStructureLocalService.getStructure(
			ddmStorageLink.getStructureId());

		try {
			DDMFormValues ddmFormValues = getDDMFormValues(structure, content);
>>>>>>> compatible

			_ddmFormValuesValidator.validate(ddmFormValues);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Stale or invalid data for DDM content %d  and " +
<<<<<<< HEAD
							"structure version %d causes: {%s}",
						ddmContent.getContentId(),
						ddmStructureVersion.getStructureId(), e.getMessage()),
=======
							"structure %d causes: {%s}",
						content.getContentId(), structure.getStructureId(),
						e.getMessage()),
>>>>>>> compatible
					e);
			}
		}
	}

	protected void verifyContents() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				_ddmContentLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod() {

					@Override
					public void performAction(Object object)
						throws PortalException {

<<<<<<< HEAD
						DDMContent ddmContent = (DDMContent)object;

						verifyContent(ddmContent);
=======
						DDMContent content = (DDMContent)object;

						verifyContent(content);
>>>>>>> compatible
					}

				});

			actionableDynamicQuery.performActions();
		}
	}

	protected void verifyDDMForm(DDMForm ddmForm) throws PortalException {
		_ddmFormValidator.validate(ddmForm);
	}

	protected void verifyDDMFormLayout(DDMFormLayout ddmFormLayout)
		throws PortalException {

		_ddmFormLayoutValidator.validate(ddmFormLayout);
	}

<<<<<<< HEAD
	protected void verifyStructure(DDMStructure ddmStructure)
		throws PortalException {

		verifyDDMForm(ddmStructure.getDDMForm());
		verifyDDMFormLayout(ddmStructure.getDDMFormLayout());
	}

	protected void verifyStructureLink(DDMStructureLink ddmStructureLink)
		throws PortalException {

		DDMStructure ddmStructure = _ddmStructureLocalService.fetchStructure(
			ddmStructureLink.getStructureId());

		if (ddmStructure == null) {
			_ddmStructureLinkLocalService.deleteStructureLink(
				ddmStructureLink.getStructureLinkId());
=======
	protected void verifyStructure(DDMStructure structure)
		throws PortalException {

		verifyDDMForm(structure.getDDMForm());
		verifyDDMFormLayout(structure.getDDMFormLayout());
	}

	protected void verifyStructureLink(DDMStructureLink structureLink)
		throws PortalException {

		DDMStructure structure = _ddmStructureLocalService.fetchStructure(
			structureLink.getStructureId());

		if (structure == null) {
			_ddmStructureLinkLocalService.deleteStructureLink(
				structureLink.getStructureLinkId());
>>>>>>> compatible
		}
	}

	protected void verifyStructureLinks() throws PortalException {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				_ddmStructureLinkLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod() {

					@Override
					public void performAction(Object object)
						throws PortalException {

<<<<<<< HEAD
						DDMStructureLink ddmStructureLink =
							(DDMStructureLink)object;

						verifyStructureLink(ddmStructureLink);
=======
						DDMStructureLink structureLink =
							(DDMStructureLink)object;

						verifyStructureLink(structureLink);
>>>>>>> compatible
					}

				});
		}
	}

	protected void verifyStructures() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				_ddmStructureLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod() {

					@Override
					public void performAction(Object object)
						throws PortalException {

<<<<<<< HEAD
						DDMStructure ddmStructure = (DDMStructure)object;

						try {
							verifyStructure(ddmStructure);
=======
						DDMStructure structure = (DDMStructure)object;

						try {
							verifyStructure(structure);
>>>>>>> compatible
						}
						catch (PortalException pe) {
							_log.error(
								String.format(
									"Invalid data for DDM structure %d " +
										"causes: {%s}",
<<<<<<< HEAD
									ddmStructure.getStructureId(),
=======
									structure.getStructureId(),
>>>>>>> compatible
									pe.getMessage()),
								pe);
						}
					}

				});

			actionableDynamicQuery.performActions();
		}
	}

<<<<<<< HEAD
	protected void verifyTemplateLink(DDMTemplateLink ddmTemplateLink)
		throws PortalException {

		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchTemplate(
			ddmTemplateLink.getTemplateId());

		if (ddmTemplate == null) {
			_ddmTemplateLinkLocalService.deleteTemplateLink(
				ddmTemplateLink.getTemplateId());
=======
	protected void verifyTemplateLink(DDMTemplateLink templateLink)
		throws PortalException {

		DDMTemplate template = _ddmTemplateLocalService.fetchTemplate(
			templateLink.getTemplateId());

		if (template == null) {
			_ddmTemplateLinkLocalService.deleteTemplateLink(
				templateLink.getTemplateId());
>>>>>>> compatible
		}
	}

	protected void verifyTemplateLinks() throws PortalException {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				_ddmTemplateLinkLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod() {

					@Override
					public void performAction(Object object)
						throws PortalException {

<<<<<<< HEAD
						DDMTemplateLink ddmTemplateLink =
							(DDMTemplateLink)object;

						verifyTemplateLink(ddmTemplateLink);
=======
						DDMTemplateLink templateLink = (DDMTemplateLink)object;

						verifyTemplateLink(templateLink);
>>>>>>> compatible
					}

				});
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMServiceVerifyProcess.class);

	private DDMContentLocalService _ddmContentLocalService;
	private DDMFormLayoutValidator _ddmFormLayoutValidator;
	private DDMFormValidator _ddmFormValidator;
	private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;
	private DDMFormValuesValidator _ddmFormValuesValidator;
	private DDMStorageLinkLocalService _ddmStorageLinkLocalService;
	private DDMStructureLinkLocalService _ddmStructureLinkLocalService;
	private DDMStructureLocalService _ddmStructureLocalService;
<<<<<<< HEAD
	private DDMStructureVersionLocalService _ddmStructureVersionLocalService;
=======
>>>>>>> compatible
	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;
	private DDMTemplateLocalService _ddmTemplateLocalService;

}