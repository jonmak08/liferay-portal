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

package com.liferay.blogs.recent.bloggers.web.internal.exportimport.portlet.preferences.processor;

<<<<<<< HEAD
import com.liferay.blogs.recent.bloggers.constants.RecentBloggersPortletKeys;
=======
import com.liferay.blogs.recent.bloggers.web.constants.RecentBloggersPortletKeys;
>>>>>>> compatible
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
<<<<<<< HEAD
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessorHelper;
=======
import com.liferay.exportimport.portlet.preferences.processor.base.BaseExportImportPortletPreferencesProcessor;
>>>>>>> compatible
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
<<<<<<< HEAD

import java.util.List;
import java.util.Map;
import java.util.function.Function;
=======
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;
>>>>>>> compatible

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + RecentBloggersPortletKeys.RECENT_BLOGGERS
	},
	service = ExportImportPortletPreferencesProcessor.class
)
public class RecentBloggersExportImportPortletPreferencesProcessor
<<<<<<< HEAD
	implements ExportImportPortletPreferencesProcessor {
=======
	extends BaseExportImportPortletPreferencesProcessor {
>>>>>>> compatible

	@Override
	public List<Capability> getExportCapabilities() {
		return null;
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return null;
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			return updateExportPortletPreferences(
				portletDataContext, portletDataContext.getPortletId(),
				portletPreferences);
		}
		catch (Exception e) {
			throw new PortletDataException(
				"Unable to update portlet preferences during export", e);
		}
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			return updateImportPortletPreferences(
				portletDataContext, portletPreferences);
		}
		catch (Exception e) {
			throw new PortletDataException(
				"Unable to update portlet preferences during import", e);
		}
	}

<<<<<<< HEAD
=======
	@Override
	protected String getExportPortletPreferencesValue(
			PortletDataContext portletDataContext, Portlet portlet,
			String className, long primaryKeyLong)
		throws Exception {

		String uuid = null;

		Element rootElement = portletDataContext.getExportDataRootElement();

		if (className.equals(Organization.class.getName())) {
			Organization organization =
				_organizationLocalService.fetchOrganization(primaryKeyLong);

			if (organization != null) {
				uuid = organization.getUuid();

				portletDataContext.addReferenceElement(
					portlet, rootElement, organization,
					PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
			}
		}

		return uuid;
	}

	@Override
	protected Long getImportPortletPreferencesNewValue(
			PortletDataContext portletDataContext, Class<?> clazz,
			long companyGroupId, Map<Long, Long> primaryKeys,
			String portletPreferencesOldValue)
		throws Exception {

		if (Validator.isNumber(portletPreferencesOldValue)) {
			long oldPrimaryKey = GetterUtil.getLong(portletPreferencesOldValue);

			return MapUtil.getLong(primaryKeys, oldPrimaryKey, oldPrimaryKey);
		}

		String className = clazz.getName();

		if (className.equals(Organization.class.getName())) {
			Organization organization =
				_organizationLocalService.fetchOrganizationByUuidAndCompanyId(
					portletPreferencesOldValue,
					portletDataContext.getCompanyId());

			if (organization != null) {
				return organization.getOrganizationId();
			}
		}

		return null;
	}

>>>>>>> compatible
	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	protected PortletPreferences updateExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long organizationId = GetterUtil.getLong(
			portletPreferences.getValue("organizationId", null));

		if (organizationId > 0) {
			Portlet portlet = _portletLocalService.getPortletById(
				portletDataContext.getCompanyId(), portletId);

<<<<<<< HEAD
			Function<String, String> exportPortletPreferencesNewValueFunction =
				primaryKey -> {
					long primaryKeyLong = GetterUtil.getLong(primaryKey);

					Organization organization =
						_organizationLocalService.fetchOrganization(
							primaryKeyLong);

					if (organization != null) {
						portletDataContext.addReferenceElement(
							portlet,
							portletDataContext.getExportDataRootElement(),
							organization,
							PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);

						return organization.getUuid();
					}

					return null;
				};

			_exportImportPortletPreferencesProcessorHelper.
				updateExportPortletPreferencesClassPKs(
					portletDataContext, portlet, portletPreferences,
					"organizationId", Organization.class.getName(),
					exportPortletPreferencesNewValueFunction);
=======
			updateExportPortletPreferencesClassPKs(
				portletDataContext, portlet, portletPreferences,
				"organizationId", Organization.class.getName());
>>>>>>> compatible
		}

		return portletPreferences;
	}

	protected PortletPreferences updateImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

<<<<<<< HEAD
		Company company = _companyLocalService.getCompanyById(
			portletDataContext.getCompanyId());

		Group companyGroup = company.getGroup();

		Map<Long, Long> primaryKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Organization.class);

		Function<String, Long> importPortletPreferencesNewValueFunction =
			portletPreferencesOldValue -> {
				if (Validator.isNumber(portletPreferencesOldValue)) {
					long oldPrimaryKey = GetterUtil.getLong(
						portletPreferencesOldValue);

					return MapUtil.getLong(
						primaryKeys, oldPrimaryKey, oldPrimaryKey);
				}

				Organization organization =
					_organizationLocalService.
						fetchOrganizationByUuidAndCompanyId(
							portletPreferencesOldValue,
							portletDataContext.getCompanyId());

				if (organization != null) {
					return organization.getOrganizationId();
				}

				return null;
			};

		_exportImportPortletPreferencesProcessorHelper.
			updateImportPortletPreferencesClassPKs(
				portletDataContext, portletPreferences, "organizationId",
				companyGroup.getGroupId(),
				importPortletPreferencesNewValueFunction);
=======
		long organizationId = GetterUtil.getLong(
			portletPreferences.getValue("organizationId", null));

		if (organizationId > 0) {
			Company company = _companyLocalService.getCompanyById(
				portletDataContext.getCompanyId());

			Group companyGroup = company.getGroup();

			updateImportPortletPreferencesClassPKs(
				portletDataContext, portletPreferences, "organizationId",
				Organization.class, companyGroup.getGroupId());
		}
>>>>>>> compatible

		return portletPreferences;
	}

	private CompanyLocalService _companyLocalService;
<<<<<<< HEAD

	@Reference
	private ExportImportPortletPreferencesProcessorHelper
		_exportImportPortletPreferencesProcessorHelper;

=======
>>>>>>> compatible
	private OrganizationLocalService _organizationLocalService;
	private PortletLocalService _portletLocalService;

}