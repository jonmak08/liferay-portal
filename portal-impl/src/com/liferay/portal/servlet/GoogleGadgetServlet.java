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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alberto Montero
 */
public class GoogleGadgetServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			String content = getContent(request);

			if (content == null) {
				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), request, response);
			}
			else {
				request.setAttribute(WebKeys.GOOGLE_GADGET, Boolean.TRUE);

				response.setContentType(ContentTypes.TEXT_XML);

				ServletResponseUtil.write(response, content);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}
	}

	protected String getContent(HttpServletRequest request) throws Exception {
		String path = GetterUtil.getString(request.getPathInfo());

		if (Validator.isNull(path)) {
			return null;
		}

		int pos = path.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		long companyId = PortalUtil.getCompanyId(request);

		String portletId = path.substring(
			pos + Portal.FRIENDLY_URL_SEPARATOR.length());

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			companyId, portletId);

		String title = portlet.getDisplayName();

<<<<<<< HEAD
=======
		String portalURL = PortalUtil.getPortalURL(request);

		String widgetJsURL = portalURL;

		widgetJsURL += PortalWebResourcesUtil.getContextPath(
			PortalWebResourceConstants.RESOURCE_TYPE_JS);
		widgetJsURL += "/liferay/widget.js";

>>>>>>> compatible
		String widgetURL = String.valueOf(request.getRequestURL());

		widgetURL = widgetURL.replaceFirst(
			PropsValues.GOOGLE_GADGET_SERVLET_MAPPING,
			PropsValues.WIDGET_SERVLET_MAPPING);

		StringBundler sb = new StringBundler(14);

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<Module>");
		sb.append("<ModulePrefs title=\"");
		sb.append(title);
		sb.append("\"/>");
		sb.append("<Content type=\"html\">");
		sb.append("<![CDATA[");
		sb.append("<iframe frameborder=\"0\" height=\"100%\" src=\"");
		sb.append(widgetURL);
		sb.append("\" width=\"100%\">");
		sb.append("</iframe>");
		sb.append("]]>");
		sb.append("</Content>");
		sb.append("</Module>");

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GoogleGadgetServlet.class);

}