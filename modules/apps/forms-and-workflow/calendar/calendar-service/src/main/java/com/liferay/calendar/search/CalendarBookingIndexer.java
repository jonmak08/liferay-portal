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

package com.liferay.calendar.search;

import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
<<<<<<< HEAD
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
=======
import com.liferay.portal.kernel.search.Document;
>>>>>>> compatible
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.trash.TrashHelper;
=======
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.trash.kernel.util.TrashUtil;
>>>>>>> compatible

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Victor Brandizzi
 * @author Eduardo Lundgren
 */
@Component(immediate = true, service = Indexer.class)
public class CalendarBookingIndexer extends BaseIndexer<CalendarBooking> {

	public static final String CLASS_NAME = CalendarBooking.class.getName();

	public CalendarBookingIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID);
		setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.TITLE);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public BooleanFilter getFacetBooleanFilter(
			String className, SearchContext searchContext)
		throws Exception {

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addTerm(
			Field.ENTRY_CLASS_NAME, CalendarBooking.class.getName());

		if (searchContext.getUserId() > 0) {
			SearchPermissionChecker searchPermissionChecker =
				SearchEngineHelperUtil.getSearchPermissionChecker();

			booleanFilter = searchPermissionChecker.getPermissionBooleanFilter(
				searchContext.getCompanyId(), searchContext.getGroupIds(),
				searchContext.getUserId(), Calendar.class.getName(),
				booleanFilter, searchContext);
		}

		return booleanFilter;
	}

	/**
	 * @deprecated As of 2.1.0
	 */
	@Deprecated
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return super.hasPermission(
			permissionChecker, entryClassName, entryClassPK, actionId);
	}

	@Override
<<<<<<< HEAD
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		addStatus(contextBooleanFilter, searchContext);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
	}

	@Override
=======
>>>>>>> compatible
	protected void doDelete(CalendarBooking calendarBooking) throws Exception {
		deleteDocument(
			calendarBooking.getCompanyId(),
			calendarBooking.getCalendarBookingId());
	}

	@Override
	protected Document doGetDocument(CalendarBooking calendarBooking)
		throws Exception {

		Document document = getBaseModelDocument(CLASS_NAME, calendarBooking);

		document.addKeyword(
			Field.CLASS_NAME_ID,
			_classNameLocalService.getClassNameId(Calendar.class));
		document.addKeyword(Field.CLASS_PK, calendarBooking.getCalendarId());

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

<<<<<<< HEAD
=======
		String descriptionDefaultLanguageId =
			LocalizationUtil.getDefaultLanguageId(
				calendarBooking.getDescription());

>>>>>>> compatible
		String[] descriptionLanguageIds = getLanguageIds(
			defaultLanguageId, calendarBooking.getDescription());

		for (String descriptionLanguageId : descriptionLanguageIds) {
			String description = calendarBooking.getDescription(
				descriptionLanguageId);

<<<<<<< HEAD
			document.addText(
				LocalizationUtil.getLocalizedName(
					Field.DESCRIPTION, descriptionLanguageId),
=======
			if (descriptionLanguageId.equals(descriptionDefaultLanguageId)) {
				document.addText(Field.DESCRIPTION, description);
			}

			document.addText(
				Field.DESCRIPTION.concat(StringPool.UNDERLINE).concat(
					descriptionLanguageId),
>>>>>>> compatible
				description);
		}

		document.addKeyword(Field.RELATED_ENTRY, true);

<<<<<<< HEAD
=======
		String titleDefaultLanguageId = LocalizationUtil.getDefaultLanguageId(
			calendarBooking.getTitle());

>>>>>>> compatible
		String[] titleLanguageIds = getLanguageIds(
			defaultLanguageId, calendarBooking.getTitle());

		for (String titleLanguageId : titleLanguageIds) {
			String title = calendarBooking.getTitle(titleLanguageId);

<<<<<<< HEAD
			document.addText(
				LocalizationUtil.getLocalizedName(Field.TITLE, titleLanguageId),
=======
			if (titleLanguageId.equals(titleDefaultLanguageId)) {
				document.addText(Field.TITLE, title);
			}

			document.addText(
				Field.TITLE.concat(StringPool.UNDERLINE).concat(
					titleLanguageId),
>>>>>>> compatible
				title);
		}

		document.addKeyword(
			Field.VIEW_ACTION_ID, CalendarActionKeys.VIEW_BOOKING_DETAILS);

		String calendarBookingId = String.valueOf(
			calendarBooking.getCalendarBookingId());

		if (calendarBooking.isInTrash()) {
<<<<<<< HEAD
			calendarBookingId = _trashHelper.getOriginalTitle(
				calendarBookingId);
=======
			calendarBookingId = TrashUtil.getOriginalTitle(calendarBookingId);
>>>>>>> compatible
		}

		document.addKeyword("calendarBookingId", calendarBookingId);

		document.addText("defaultLanguageId", defaultLanguageId);
		document.addNumber("endTime", calendarBooking.getEndTime());
		document.addText("location", calendarBooking.getLocation());
		document.addNumber("startTime", calendarBooking.getStartTime());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

<<<<<<< HEAD
		Locale snippetLocale = getSnippetLocale(document, locale);

		Locale defaultLocale = LocaleUtil.fromLanguageId(
			document.get("defaultLanguageId"));

		String localizedTitleName = DocumentImpl.getLocalizedName(
			locale, Field.TITLE);

		if ((snippetLocale == null) &&
			(document.getField(localizedTitleName) == null)) {

			snippetLocale = defaultLocale;
		}
		else {
			snippetLocale = locale;
		}

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(
			snippetLocale, prefix + Field.TITLE, Field.TITLE);

		if (Validator.isNull(title) && !snippetLocale.equals(defaultLocale)) {
			title = document.get(
				defaultLocale, prefix + Field.TITLE, Field.TITLE);
		}

		String description = document.get(
			snippetLocale, prefix + Field.DESCRIPTION, Field.DESCRIPTION);

		if (Validator.isNull(description) &&
			!snippetLocale.equals(defaultLocale)) {

			description = document.get(
				defaultLocale, prefix + Field.DESCRIPTION, Field.DESCRIPTION);
		}

		description = HtmlUtil.extractText(description);

		Summary summary = new Summary(snippetLocale, title, description);
=======
		Summary summary = createSummary(
			document, Field.TITLE, Field.DESCRIPTION);
>>>>>>> compatible

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(CalendarBooking calendarBooking) throws Exception {
		int status = calendarBooking.getStatus();

		if ((status == CalendarBookingWorkflowConstants.STATUS_APPROVED) ||
<<<<<<< HEAD
			(status == CalendarBookingWorkflowConstants.STATUS_MAYBE) ||
			(status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH)) {
=======
			(status == CalendarBookingWorkflowConstants.STATUS_MAYBE)) {
>>>>>>> compatible

			Document document = getDocument(calendarBooking);

			_indexWriterHelper.updateDocument(
				getSearchEngineId(), calendarBooking.getCompanyId(), document,
				isCommitImmediately());
		}
<<<<<<< HEAD
		else if (status == CalendarBookingWorkflowConstants.STATUS_DENIED) {
=======
		else if ((status == CalendarBookingWorkflowConstants.STATUS_DENIED) ||
				 (status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH)) {

>>>>>>> compatible
			doDelete(calendarBooking);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CalendarBooking calendarBooking =
			_calendarBookingLocalService.getCalendarBooking(classPK);

		doReindex(calendarBooking);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCalendarBookings(companyId);
	}

	protected String[] getLanguageIds(
		String defaultLanguageId, String content) {

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			content);

		if (languageIds.length == 0) {
			languageIds = new String[] {defaultLanguageId};
		}

		return languageIds;
	}

	protected void reindexCalendarBookings(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_calendarBookingLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property statusProperty = PropertyFactoryUtil.forName(
						"status");

					int[] statuses = {
						CalendarBookingWorkflowConstants.STATUS_APPROVED,
<<<<<<< HEAD
						CalendarBookingWorkflowConstants.STATUS_MAYBE,
						CalendarBookingWorkflowConstants.STATUS_IN_TRASH
=======
						CalendarBookingWorkflowConstants.STATUS_MAYBE
>>>>>>> compatible
					};

					dynamicQuery.add(statusProperty.in(statuses));
				}

			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CalendarBooking>() {

				@Override
				public void performAction(CalendarBooking calendarBooking) {
					try {
						Document document = getDocument(calendarBooking);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index calendar booking " +
									calendarBooking.getCalendarBookingId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Reference(unbind = "-")
	protected void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {

		_calendarBookingLocalService = calendarBookingLocalService;
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarBookingIndexer.class);

	private CalendarBookingLocalService _calendarBookingLocalService;
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

<<<<<<< HEAD
	@Reference
	private TrashHelper _trashHelper;

=======
>>>>>>> compatible
}