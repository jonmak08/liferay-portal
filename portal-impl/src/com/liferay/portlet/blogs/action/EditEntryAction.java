/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.blogs.action;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.ActionRequestImpl;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.portlet.blogs.EntryContentException;
import com.liferay.portlet.blogs.EntryDisplayDateException;
import com.liferay.portlet.blogs.EntrySmallImageNameException;
import com.liferay.portlet.blogs.EntrySmallImageSizeException;
import com.liferay.portlet.blogs.EntryTitleException;
import com.liferay.portlet.blogs.NoSuchEntryException;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.BlogsEntryServiceUtil;
import com.liferay.portlet.blogs.service.permission.BlogsPermission;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.File;
import java.io.InputStream;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson S. Man
 * @author Thiago Moreira
 * @author Juan Fernández
 */
public class EditEntryAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			BlogsEntry entry = null;
			String oldUrlTitle = StringPool.BLANK;

			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				Object[] returnValue = updateEntry(actionRequest);

				entry = (BlogsEntry)returnValue[0];
				oldUrlTitle = ((String)returnValue[1]);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteEntries(actionRequest);
			}
			else if (cmd.equals(Constants.SUBSCRIBE)) {
				subscribe(actionRequest);
			}
			else if (cmd.equals(Constants.UNSUBSCRIBE)) {
				unsubscribe(actionRequest);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");
			boolean updateRedirect = false;

			if (redirect.indexOf(
					"/blogs/" + oldUrlTitle + "/maximized") != -1) {

				oldUrlTitle += "/maximized";
			}

			if ((entry != null) && (Validator.isNotNull(oldUrlTitle)) &&
				(redirect.endsWith("/blogs/" + oldUrlTitle) ||
				 redirect.indexOf("/blogs/" + oldUrlTitle + "?") != -1)) {

				int pos = redirect.indexOf("?");

				if (pos == -1) {
					pos = redirect.length();
				}

				String newRedirect = redirect.substring(
					0, pos - oldUrlTitle.length());

				newRedirect += entry.getUrlTitle();

				if (oldUrlTitle.indexOf("/maximized") != -1) {
					newRedirect += "/maximized";
				}

				if (pos < redirect.length()) {
					newRedirect +=
						"?" + redirect.substring(pos + 1, redirect.length());
				}

				redirect = newRedirect;
				updateRedirect = true;
			}

			if (entry != null) {
				int workflowAction = ParamUtil.getInteger(
					actionRequest, "workflowAction",
					WorkflowConstants.ACTION_SAVE_DRAFT);

				if (themeDisplay.isStateExclusive()) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put("entryId", entry.getEntryId());
					jsonObject.put("redirect", redirect);
					jsonObject.put("updateRedirect", updateRedirect);

					HttpServletRequest request =
						PortalUtil.getHttpServletRequest(actionRequest);
					HttpServletResponse response =
						PortalUtil.getHttpServletResponse(actionResponse);
					InputStream inputStream = new UnsyncByteArrayInputStream(
						jsonObject.toString().getBytes());
					String contentType = ContentTypes.TEXT_JAVASCRIPT;

					ServletResponseUtil.sendFile(
						request, response, null, inputStream, contentType);

					setForward(actionRequest, ActionConstants.COMMON_NULL);
				}
				else if (workflowAction ==
							WorkflowConstants.ACTION_SAVE_DRAFT) {

					redirect = getSaveAndContinueRedirect(
						portletConfig, actionRequest, entry, redirect);

					sendRedirect(actionRequest, actionResponse, redirect);
				}
				else {
					LayoutTypePortlet layoutTypePortlet =
						themeDisplay.getLayoutTypePortlet();

					if (layoutTypePortlet.hasPortletId(
							portletConfig.getPortletName())) {

						sendRedirect(actionRequest, actionResponse, redirect);
					}
					else {
						actionResponse.sendRedirect(redirect);
					}
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(actionRequest, "portlet.blogs.error");
			}
			else if (e instanceof EntryContentException ||
					 e instanceof EntryDisplayDateException ||
					 e instanceof EntrySmallImageNameException ||
					 e instanceof EntrySmallImageSizeException ||
					 e instanceof EntryTitleException) {

				SessionErrors.add(actionRequest, e.getClass().getName());
			}
			else if (e instanceof AssetTagException) {
				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw e;
			}
		}
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getEntry(renderRequest);

			if (PropsValues.BLOGS_PINGBACK_ENABLED) {
				BlogsEntry entry = (BlogsEntry)renderRequest.getAttribute(
					WebKeys.BLOGS_ENTRY);

				if ((entry != null) && entry.isAllowPingbacks()) {
					HttpServletResponse response =
						PortalUtil.getHttpServletResponse(renderResponse);

					response.addHeader(
						"X-Pingback",
						PortalUtil.getPortalURL(renderRequest) +
							"/xmlrpc/pingback");
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass().getName());

				return mapping.findForward("portlet.blogs.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(
			getForward(renderRequest, "portlet.blogs.edit_entry"));
	}

	protected void deleteEntries(ActionRequest actionRequest) throws Exception {
		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		if (entryId > 0) {
			BlogsEntryServiceUtil.deleteEntry(entryId);
		}
		else {
			long[] deleteEntryIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteEntryIds"), 0L);

			for (int i = 0; i < deleteEntryIds.length; i++) {
				BlogsEntryServiceUtil.deleteEntry(deleteEntryIds[i]);
			}
		}
	}

	protected String getSaveAndContinueRedirect(
			PortletConfig portletConfig, ActionRequest actionRequest,
			BlogsEntry entry, String redirect)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURLImpl portletURL = new PortletURLImpl(
			(ActionRequestImpl)actionRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(WindowState.MAXIMIZED);

		portletURL.setParameter("struts_action", "/blogs_admin/edit_entry");
		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter(
			"groupId", String.valueOf(entry.getGroupId()), false);
		portletURL.setParameter(
			"entryId", String.valueOf(entry.getEntryId()), false);

		return portletURL.toString();
	}

	protected void subscribe(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (BlogsPermission.contains(
				permissionChecker, themeDisplay.getScopeGroupId(),
				ActionKeys.SUBSCRIBE)) {

			SubscriptionLocalServiceUtil.addSubscription(
				themeDisplay.getUserId(), BlogsEntry.class.getName(),
				themeDisplay.getScopeGroupId());
		}
	}

	protected void unsubscribe(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (BlogsPermission.contains(
				permissionChecker, themeDisplay.getScopeGroupId(),
				ActionKeys.SUBSCRIBE)) {

			SubscriptionLocalServiceUtil.deleteSubscription(
				themeDisplay.getUserId(), BlogsEntry.class.getName(),
				themeDisplay.getScopeGroupId());
		}
	}

	protected Object[] updateEntry(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(uploadRequest, "entryId");

		String title = ParamUtil.getString(uploadRequest, "title");
		String description = ParamUtil.getString(uploadRequest, "description");
		String content = ParamUtil.getString(uploadRequest, "content");

		int displayDateMonth = ParamUtil.getInteger(
			uploadRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			uploadRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			uploadRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			uploadRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			uploadRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			uploadRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		boolean allowPingbacks = ParamUtil.getBoolean(
			uploadRequest, "allowPingbacks");
		boolean allowTrackbacks = ParamUtil.getBoolean(
			uploadRequest, "allowTrackbacks");
		String[] trackbacks = StringUtil.split(
			ParamUtil.getString(uploadRequest, "trackbacks"));

		boolean	smallImage = false;
		String smallImageURL = null;
		File smallFile = null;

		if (!themeDisplay.isStateExclusive()) {
			smallImage = ParamUtil.getBoolean(uploadRequest, "smallImage");
			smallImageURL = ParamUtil.getString(
				uploadRequest, "smallImageURL");
			smallFile = uploadRequest.getFile("smallFile");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			BlogsEntry.class.getName(), actionRequest);

		BlogsEntry entry = null;
		String oldUrlTitle = StringPool.BLANK;

		if (entryId <= 0) {

			// Add entry

			entry = BlogsEntryServiceUtil.addEntry(
				title, description, content, displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute,
				allowPingbacks, allowTrackbacks, trackbacks, smallImage,
				smallImageURL, smallFile, serviceContext);

			AssetPublisherUtil.addAndStoreSelection(
				actionRequest, BlogsEntry.class.getName(), entry.getEntryId(),
				-1);
		}
		else {

			// Update entry

			entry = BlogsEntryLocalServiceUtil.getEntry(entryId);

			String tempOldUrlTitle = entry.getUrlTitle();

			entry = BlogsEntryServiceUtil.updateEntry(
				entryId, title, description, content, displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, allowPingbacks, allowTrackbacks, trackbacks,
				smallImage, smallImageURL, smallFile, serviceContext);

			if (!tempOldUrlTitle.equals(entry.getUrlTitle())) {
				oldUrlTitle = tempOldUrlTitle;
			}

			AssetPublisherUtil.addAndStoreSelection(
				actionRequest, BlogsEntry.class.getName(), entry.getEntryId(),
				-1);
		}

		return new Object[] {entry, oldUrlTitle};
	}

}