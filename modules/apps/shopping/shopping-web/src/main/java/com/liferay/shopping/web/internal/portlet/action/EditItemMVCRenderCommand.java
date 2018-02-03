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

package com.liferay.shopping.web.internal.portlet.action;

<<<<<<< HEAD
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.shopping.configuration.ShoppingFileUploadsConfiguration;
import com.liferay.shopping.constants.ShoppingPortletKeys;
import com.liferay.shopping.exception.NoSuchItemException;

import java.util.Map;

=======
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.shopping.constants.ShoppingPortletKeys;
import com.liferay.shopping.exception.NoSuchItemException;

>>>>>>> compatible
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

<<<<<<< HEAD
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
=======
import org.osgi.service.component.annotations.Component;
>>>>>>> compatible

/**
 * @author Brian Wing Shun Chan
 * @author Peter Fellwock
 */
@Component(
<<<<<<< HEAD
	configurationPid = "com.liferay.shopping.configuration.ShoppingFileUploadsConfiguration",
=======
>>>>>>> compatible
	immediate = true,
	property = {
		"javax.portlet.name=" + ShoppingPortletKeys.SHOPPING,
		"javax.portlet.name=" + ShoppingPortletKeys.SHOPPING_ADMIN,
		"mvc.command.name=/shopping/edit_item"
	},
	service = MVCRenderCommand.class
)
public class EditItemMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			ActionUtil.getItem(renderRequest);
<<<<<<< HEAD

			renderRequest.setAttribute(
				ShoppingFileUploadsConfiguration.class.getName(),
				_shoppingFileUploadsConfiguration);
=======
>>>>>>> compatible
		}
		catch (Exception e) {
			if (e instanceof NoSuchItemException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/error.jsp";
			}
			else {
				throw new PortletException(e);
			}
		}

		return "/edit_item.jsp";
	}

<<<<<<< HEAD
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_shoppingFileUploadsConfiguration = ConfigurableUtil.createConfigurable(
			ShoppingFileUploadsConfiguration.class, properties);
	}

	private volatile ShoppingFileUploadsConfiguration
		_shoppingFileUploadsConfiguration;

=======
>>>>>>> compatible
}