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

package com.liferay.document.library.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
<<<<<<< HEAD
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
=======
>>>>>>> compatible
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
<<<<<<< HEAD
=======
import com.liferay.exportimport.lar.BaseStagedModelDataHandler;
>>>>>>> compatible
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.repository.model.Folder;
<<<<<<< HEAD
import com.liferay.portal.kernel.service.ClassNameLocalService;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.service.RepositoryEntryLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mate Thurzo
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class RepositoryStagedModelDataHandler
	extends BaseStagedModelDataHandler<Repository> {

	public static final String[] CLASS_NAMES = {Repository.class.getName()};

	@Override
	public void deleteStagedModel(Repository repository)
		throws PortalException {

		_repositoryLocalService.deleteRepository(repository.getRepositoryId());
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Repository repository = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (repository != null) {
			deleteStagedModel(repository);
		}
	}

	@Override
	public Repository fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _repositoryLocalService.fetchRepositoryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<Repository> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _repositoryLocalService.getRepositoriesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Repository>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Repository repository) {
		return repository.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Repository repository)
		throws Exception {

		Element repositoryElement = portletDataContext.getExportDataElement(
			repository);

		Folder folder = _dlAppLocalService.getFolder(
			repository.getDlFolderId());

		if (folder.getModel() instanceof DLFolder) {
			DLFolder dlFolder = (DLFolder)folder.getModel();

			repositoryElement.addAttribute(
				"hidden", String.valueOf(dlFolder.isHidden()));
		}

<<<<<<< HEAD
		repositoryElement.addAttribute(
			"repositoryClassName", repository.getClassName());

=======
>>>>>>> compatible
		portletDataContext.addClassedModel(
			repositoryElement, ExportImportPathUtil.getModelPath(repository),
			repository);

		List<RepositoryEntry> repositoryEntries =
			_repositoryEntryLocalService.getRepositoryEntries(
				repository.getRepositoryId());

		for (RepositoryEntry repositoryEntry : repositoryEntries) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, repository, repositoryEntry,
				PortletDataContext.REFERENCE_TYPE_CHILD);
		}
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Repository repository)
		throws Exception {

		long userId = portletDataContext.getUserId(repository.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			repository);

		Repository importedRepository = null;

		Element repositoryElement =
			portletDataContext.getImportDataStagedModelElement(repository);

		try {
			boolean hidden = GetterUtil.getBoolean(
				repositoryElement.attributeValue("hidden"));

<<<<<<< HEAD
			Repository existingRepository = fetchStagedModelByUuidAndGroupId(
				repository.getUuid(), portletDataContext.getScopeGroupId());

			if (existingRepository == null) {
				existingRepository = _repositoryLocalService.fetchRepository(
					portletDataContext.getScopeGroupId(), repository.getName());
			}

			if (portletDataContext.isDataStrategyMirror()) {
				if (existingRepository == null) {
					serviceContext.setUuid(repository.getUuid());

					long repositoryClassNameId = _getRepositoryClassNameId(
						repositoryElement, repository.getClassNameId());

					importedRepository = _repositoryLocalService.addRepository(
						userId, portletDataContext.getScopeGroupId(),
						repositoryClassNameId,
=======
			if (portletDataContext.isDataStrategyMirror()) {
				Repository existingRepository =
					fetchStagedModelByUuidAndGroupId(
						repository.getUuid(),
						portletDataContext.getScopeGroupId());

				if (existingRepository == null) {
					existingRepository =
						_repositoryLocalService.fetchRepository(
							portletDataContext.getScopeGroupId(),
							repository.getName());
				}

				if (existingRepository == null) {
					serviceContext.setUuid(repository.getUuid());

					importedRepository = _repositoryLocalService.addRepository(
						userId, portletDataContext.getScopeGroupId(),
						repository.getClassNameId(),
>>>>>>> compatible
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
						repository.getName(), repository.getDescription(),
						repository.getPortletId(),
						repository.getTypeSettingsProperties(), hidden,
						serviceContext);
				}
				else {
					_repositoryLocalService.updateRepository(
						existingRepository.getRepositoryId(),
						repository.getName(), repository.getDescription());

					importedRepository = existingRepository;
				}
			}
<<<<<<< HEAD
			else if (existingRepository == null) {
				long repositoryClassNameId = _getRepositoryClassNameId(
					repositoryElement, repository.getClassNameId());

				importedRepository = _repositoryLocalService.addRepository(
					userId, portletDataContext.getScopeGroupId(),
					repositoryClassNameId,
=======
			else {
				importedRepository = _repositoryLocalService.addRepository(
					userId, portletDataContext.getScopeGroupId(),
					repository.getClassNameId(),
>>>>>>> compatible
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					repository.getName(), repository.getDescription(),
					repository.getPortletId(),
					repository.getTypeSettingsProperties(), hidden,
					serviceContext);
			}
<<<<<<< HEAD
			else {
				importedRepository = existingRepository;
			}
=======
>>>>>>> compatible
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
<<<<<<< HEAD
					StringBundler.concat(
						"Unable to connect to repository {name=",
						repository.getName(), ", typeSettings=",
						String.valueOf(repository.getTypeSettingsProperties()),
						"}"),
=======
					"Unable to connect to repository {name=" +
						repository.getName() + ", typeSettings=" +
							repository.getTypeSettingsProperties() + "}",
>>>>>>> compatible
					e);
			}
		}

		portletDataContext.importClassedModel(repository, importedRepository);

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, repository, RepositoryEntry.class);
	}

	@Override
	protected void importReferenceStagedModels(
		PortletDataContext portletDataContext, Repository stagedModel) {
	}

<<<<<<< HEAD
	private long _getRepositoryClassNameId(
		Element repositoryElement, long defaultValue) {

		String repositoryClassName = repositoryElement.attributeValue(
			"repositoryClassName");

		if (Validator.isNull(repositoryClassName)) {
			return defaultValue;
		}

		return _classNameLocalService.getClassNameId(repositoryClassName);
=======
	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setRepositoryEntryLocalService(
		RepositoryEntryLocalService repositoryEntryLocalService) {

		_repositoryEntryLocalService = repositoryEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setRepositoryLocalService(
		RepositoryLocalService repositoryLocalService) {

		_repositoryLocalService = repositoryLocalService;
>>>>>>> compatible
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RepositoryStagedModelDataHandler.class);

<<<<<<< HEAD
	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private RepositoryEntryLocalService _repositoryEntryLocalService;

	@Reference
=======
	private DLAppLocalService _dlAppLocalService;
	private RepositoryEntryLocalService _repositoryEntryLocalService;
>>>>>>> compatible
	private RepositoryLocalService _repositoryLocalService;

}