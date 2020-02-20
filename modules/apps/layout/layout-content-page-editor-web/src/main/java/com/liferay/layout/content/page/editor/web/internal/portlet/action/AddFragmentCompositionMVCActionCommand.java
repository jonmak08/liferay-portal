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

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.exception.StorageFieldValueException;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.contributor.FragmentCollectionContributorTracker;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentComposition;
import com.liferay.fragment.renderer.FragmentRendererTracker;
import com.liferay.fragment.service.FragmentCollectionService;
import com.liferay.fragment.service.FragmentCompositionService;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.util.layout.structure.LayoutStructureUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.net.URL;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/add_fragment_composition"
	},
	service = {AopService.class, MVCActionCommand.class}
)
public class AddFragmentCompositionMVCActionCommand
	extends BaseMVCActionCommand implements AopService, MVCActionCommand {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		return super.processAction(actionRequest, actionResponse);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = _processAddFragmentComposition(actionRequest);

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private FileEntry _addPreviewImage(
			long fragmentCompositionId, String url,
			ServiceContext serviceContext, ThemeDisplay themeDisplay)
		throws PortalException {

		byte[] bytes = {};

		try {
			if (url.startsWith("data:image/")) {
				String[] urlParts = url.split(";base64,");

				bytes = Base64.decode(urlParts[1]);
			}
			else if (Validator.isUrl(url, true)) {
				if (StringUtil.startsWith(url, StringPool.SLASH)) {
					url = _portal.getPortalURL(themeDisplay) + url;
				}

				URL imageURL = new URL(url);

				bytes = FileUtil.getBytes(imageURL.openStream());
			}

			Repository repository =
				PortletFileRepositoryUtil.fetchPortletRepository(
					themeDisplay.getScopeGroupId(),
					FragmentPortletKeys.FRAGMENT);

			if (repository == null) {
				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);

				repository = PortletFileRepositoryUtil.addPortletRepository(
					themeDisplay.getScopeGroupId(),
					FragmentPortletKeys.FRAGMENT, serviceContext);
			}

			return _portletFileRepository.addPortletFileEntry(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				FragmentComposition.class.getName(), fragmentCompositionId,
				FragmentPortletKeys.FRAGMENT, repository.getDlFolderId(), bytes,
				fragmentCompositionId + "_preview",
				MimeTypesUtil.getContentType(url), false);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}

			throw new StorageFieldValueException(
				LanguageUtil.format(
					themeDisplay.getRequest(), "the-file-x-cannot-be-saved",
					url));
		}
	}

	private JSONObject _processAddFragmentComposition(
			ActionRequest actionRequest)
		throws Exception {

		long fragmentCollectionId = ParamUtil.getLong(
			actionRequest, "fragmentCollectionId",
			SegmentsExperienceConstants.ID_DEFAULT);
		String itemId = ParamUtil.getString(actionRequest, "itemId");
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String previewImageURL = ParamUtil.getString(
			actionRequest, "previewImageURL");
		long segmentsExperienceId = ParamUtil.getLong(
			actionRequest, "segmentsExperienceId",
			SegmentsExperienceConstants.ID_DEFAULT);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String layoutStructureJSON =
			LayoutStructureUtil.getLayoutStructureItemJSON(
				_fragmentCollectionContributorTracker,
				_fragmentEntryConfigurationParser, _fragmentRendererTracker,
				themeDisplay.getScopeGroupId(), itemId, themeDisplay.getPlid(),
				segmentsExperienceId);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		FragmentCollection fragmentCollection =
			_fragmentCollectionService.fetchFragmentCollection(
				fragmentCollectionId);

		if (fragmentCollection == null) {
			String fragmentCollectionName = LanguageUtil.get(
				themeDisplay.getRequest(), "saved-fragments");

			fragmentCollection =
				_fragmentCollectionService.addFragmentCollection(
					themeDisplay.getScopeGroupId(), fragmentCollectionName,
					fragmentCollectionName, serviceContext);
		}

		FragmentComposition fragmentComposition =
			_fragmentCompositionService.addFragmentComposition(
				themeDisplay.getScopeGroupId(),
				fragmentCollection.getFragmentCollectionId(), null, name,
				description, layoutStructureJSON, 0,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		if (Validator.isNotNull(previewImageURL)) {
			FileEntry previewFileEntry = _addPreviewImage(
				fragmentComposition.getFragmentCompositionId(), previewImageURL,
				serviceContext, themeDisplay);

			_fragmentCompositionService.updateFragmentComposition(
				fragmentComposition.getFragmentCompositionId(),
				previewFileEntry.getFileEntryId());
		}

		return JSONUtil.put(
			"fragmentCompositionKey",
			fragmentComposition.getFragmentCompositionKey());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddFragmentCompositionMVCActionCommand.class);

	@Reference
	private FragmentCollectionContributorTracker
		_fragmentCollectionContributorTracker;

	@Reference
	private FragmentCollectionService _fragmentCollectionService;

	@Reference
	private FragmentCompositionService _fragmentCompositionService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private FragmentRendererTracker _fragmentRendererTracker;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletFileRepository _portletFileRepository;

}