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

package com.liferay.dynamic.data.mapping.service.permission;

import com.liferay.dynamic.data.mapping.constants.DDMActionKeys;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Lundgren
 * @author Levente Hudák
 */
@Component(immediate = true, service = DDMTemplatePermission.class)
public class DDMTemplatePermission extends BaseResourcePermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, DDMTemplate template,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, template, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				getTemplateModelResourceName(template.getResourceClassName()),
				template.getTemplateId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			DDMTemplate template, String portletId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, groupId, template, portletId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				getTemplateModelResourceName(template.getResourceClassName()),
				template.getTemplateId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long templateId,
			String portletId, String actionId)
		throws PortalException {

		DDMTemplate template = _ddmTemplateLocalService.getTemplate(templateId);

		if (!contains(
				permissionChecker, groupId, template, portletId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				getTemplateModelResourceName(template.getResourceClassName()),
				templateId, actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException {

		DDMTemplate template = _ddmTemplateLocalService.getTemplate(templateId);

		if (!contains(permissionChecker, template, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				getTemplateModelResourceName(template.getResourceClassName()),
				templateId, actionId);
		}
	}

	public static void checkAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			long resourceClassNameId)
		throws PortalException {

		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper =
				_ddmPermissionSupportTracker.
					getDDMTemplatePermissionSupportServiceWrapper(
						resourceClassNameId);

		_checkAddTemplatePermission(
			permissionChecker, groupId, classNameId,
			templatePermissionSupportServiceWrapper);
	}

	public static void checkAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			String resourceClassName)
		throws PortalException {

		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper =
				_ddmPermissionSupportTracker.
					getDDMTemplatePermissionSupportServiceWrapper(
						resourceClassName);

		_checkAddTemplatePermission(
			permissionChecker, groupId, classNameId,
			templatePermissionSupportServiceWrapper);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DDMTemplate template,
			String actionId)
		throws PortalException {

		String templateModelResourceName = getTemplateModelResourceName(
			template.getResourceClassName());

		if (permissionChecker.hasOwnerPermission(
				template.getCompanyId(), templateModelResourceName,
				template.getTemplateId(), template.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			template.getGroupId(), templateModelResourceName,
			template.getTemplateId(), actionId);
	}

	/**
	 * @deprecated As of 2.1.0, replaced by {@link #contains(PermissionChecker,
	 *             DDMTemplate, String)}
	 */
	@Deprecated
	public static boolean contains(
			PermissionChecker permissionChecker, DDMTemplate template,
			String portletId, String actionId)
		throws PortalException {

		return contains(permissionChecker, template, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			DDMTemplate template, String portletId, String actionId)
		throws PortalException {

		if (Validator.isNotNull(portletId)) {
			Boolean hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, groupId,
				getTemplateModelResourceName(template.getResourceClassName()),
				template.getTemplateId(), portletId, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		return contains(permissionChecker, template, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long templateId,
			String portletId, String actionId)
		throws PortalException {

		DDMTemplate template = _ddmTemplateLocalService.getTemplate(templateId);

		return contains(
			permissionChecker, groupId, template, portletId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException {

		DDMTemplate template = _ddmTemplateLocalService.getTemplate(templateId);

		return contains(permissionChecker, template, actionId);
	}

	/**
	 * @deprecated As of 2.1.0, replaced by {@link #contains(PermissionChecker,
	 *             long, String)}
	 */
	@Deprecated
	public static boolean contains(
			PermissionChecker permissionChecker, long templateId,
			String portletId, String actionId)
		throws PortalException {

		return contains(permissionChecker, templateId, actionId);
	}

	public static boolean containsAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			long resourceClassNameId)
		throws PortalException {

		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper =
				_ddmPermissionSupportTracker.
					getDDMTemplatePermissionSupportServiceWrapper(
						resourceClassNameId);

		return _containsAddTemplatePermission(
			permissionChecker, groupId, classNameId,
			templatePermissionSupportServiceWrapper);
	}

	public static boolean containsAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			String resourceClassName)
		throws PortalException {

		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper =
				_ddmPermissionSupportTracker.
					getDDMTemplatePermissionSupportServiceWrapper(
						resourceClassName);

		return _containsAddTemplatePermission(
			permissionChecker, groupId, classNameId,
			templatePermissionSupportServiceWrapper);
	}

	public static String getTemplateModelResourceName(long resourceClassNameId)
		throws PortalException {

		String resourceClassName = PortalUtil.getClassName(resourceClassNameId);

		return getTemplateModelResourceName(resourceClassName);
	}

	public static String getTemplateModelResourceName(String resourceClassName)
		throws PortalException {

		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper =
				_ddmPermissionSupportTracker.
					getDDMTemplatePermissionSupportServiceWrapper(
						resourceClassName);

		return _getTemplateModelResourceName(
			resourceClassName, templatePermissionSupportServiceWrapper);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		try {
			return contains(permissionChecker, classPK, actionId);
		}
		catch (PortalException pe) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return false;
		}
	}

	protected static String getAddTemplateActionId(
		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper) {

		Map<String, Object> properties =
			templatePermissionSupportServiceWrapper.getProperties();

		return MapUtil.getString(
			properties, "add.template.action.id", DDMActionKeys.ADD_TEMPLATE);
	}

	protected static String getResourceName(
		ServiceWrapper<DDMTemplatePermissionSupport>
			templatePermissionSupportServiceWrapper, long classNameId) {

		DDMTemplatePermissionSupport templatePermissionSupport =
			templatePermissionSupportServiceWrapper.getService();

		return templatePermissionSupport.getResourceName(classNameId);
	}

	@Reference(unbind = "-")
	protected void setDDMPermissionSupportTracker(
		DDMPermissionSupportTracker ddmPermissionSupportTracker) {

		_ddmPermissionSupportTracker = ddmPermissionSupportTracker;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	private static void _checkAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			ServiceWrapper<DDMTemplatePermissionSupport>
				templatePermissionSupportServiceWrapper)
		throws PortalException {

		if (!_containsAddTemplatePermission(
				permissionChecker, groupId, classNameId,
				templatePermissionSupportServiceWrapper)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				getResourceName(
					templatePermissionSupportServiceWrapper, classNameId),
				groupId,
				getAddTemplateActionId(
					templatePermissionSupportServiceWrapper));
		}
	}

	private static boolean _containsAddTemplatePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId,
			ServiceWrapper<DDMTemplatePermissionSupport>
				templatePermissionSupportServiceWrapper)
		throws PortalException {

		String resourceName = getResourceName(
			templatePermissionSupportServiceWrapper, classNameId);

		List<String> portletNames = ResourceActionsUtil.getPortletNames();

		if (portletNames.contains(resourceName)) {
			return PortletPermissionUtil.contains(
				permissionChecker, groupId, null, resourceName,
				getAddTemplateActionId(
					templatePermissionSupportServiceWrapper));
		}

		return contains(
			permissionChecker, resourceName, groupId,
			getAddTemplateActionId(templatePermissionSupportServiceWrapper));
	}

	private static String _getTemplateModelResourceName(
			String resourceClassName,
			ServiceWrapper<DDMTemplatePermissionSupport>
				templatePermissionSupportServiceWrapper)
		throws PortalException {

		Map<String, Object> properties =
			templatePermissionSupportServiceWrapper.getProperties();

		boolean defaultModelResourceName = MapUtil.getBoolean(
			properties, "default.model.resource.name");

		if (defaultModelResourceName) {
			return DDMTemplate.class.getName();
		}

		return ResourceActionsUtil.getCompositeModelName(
			resourceClassName, DDMTemplate.class.getName());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMTemplatePermission.class);

	private static DDMPermissionSupportTracker _ddmPermissionSupportTracker;
	private static DDMTemplateLocalService _ddmTemplateLocalService;

}