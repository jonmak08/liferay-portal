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

package com.liferay.calendar.internal.exportimport.data.handler;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
<<<<<<< HEAD
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.Set;
import java.util.stream.Stream;
>>>>>>> compatible

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + CalendarPortletKeys.CALENDAR},
	service = StagedModelDataHandler.class
)
public class CalendarBookingStagedModelDataHandler
	extends BaseStagedModelDataHandler<CalendarBooking> {

	public static final String[] CLASS_NAMES =
		{CalendarBooking.class.getName()};

	@Override
	public void deleteStagedModel(CalendarBooking calendarBooking)
		throws PortalException {

		_calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		CalendarBooking calendarBooking = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (calendarBooking != null) {
			deleteStagedModel(calendarBooking);
		}
	}

	@Override
	public CalendarBooking fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _calendarBookingLocalService.
			fetchCalendarBookingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<CalendarBooking> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _calendarBookingLocalService.
			getCalendarBookingsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<CalendarBooking>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(CalendarBooking calendarBooking) {
		return calendarBooking.getTitleCurrentValue();
	}

	@Override
	public int[] getExportableStatuses() {
		return _EXPORTABLE_STATUSES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, calendarBooking, calendarBooking.getCalendar(),
			PortletDataContext.REFERENCE_TYPE_STRONG);

		if (!calendarBooking.isMasterBooking()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, calendarBooking,
				calendarBooking.getParentCalendarBooking(),
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

<<<<<<< HEAD
		Element calendarBookingElement =
			portletDataContext.getExportDataElement(calendarBooking);

		for (CalendarBooking childCalendarBooking :
				calendarBooking.getChildCalendarBookings()) {

			if (childCalendarBooking.isMasterBooking()) {
				continue;
			}

=======
		for (CalendarBooking childCalendarBooking :
				calendarBooking.getChildCalendarBookings()) {

>>>>>>> compatible
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, calendarBooking,
				childCalendarBooking.getCalendar(),
				PortletDataContext.REFERENCE_TYPE_STRONG);
<<<<<<< HEAD

			Element childElement = calendarBookingElement.addElement("child");

			childElement.addAttribute(
				"calendarId",
				String.valueOf(childCalendarBooking.getCalendarId()));
		}

=======
		}

		Element calendarBookingElement =
			portletDataContext.getExportDataElement(calendarBooking);

>>>>>>> compatible
		portletDataContext.addClassedModel(
			calendarBookingElement,
			ExportImportPathUtil.getModelPath(calendarBooking),
			calendarBooking);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long calendarBookingId)
		throws Exception {

		CalendarBooking existingCalendarBooking = fetchMissingReference(
			uuid, groupId);

		if (existingCalendarBooking == null) {
			return;
		}

		Map<Long, Long> calendarBookingIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarBooking.class);

		calendarBookingIds.put(
			calendarBookingId, existingCalendarBooking.getCalendarBookingId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		long calendarId = MapUtil.getLong(
			calendarIds, calendarBooking.getCalendarId(),
			calendarBooking.getCalendarId());

		long parentCalendarBookingId =
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT;

		long[] childCalendarIds = new long[0];

		if (!calendarBooking.isMasterBooking()) {
			Map<Long, Long> calendarBookingIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					CalendarBooking.class);

			parentCalendarBookingId = MapUtil.getLong(
				calendarBookingIds,
				calendarBooking.getParentCalendarBookingId(),
				calendarBooking.getParentCalendarBookingId());
		}
		else {
<<<<<<< HEAD
			childCalendarIds = _getChildCalendarIds(
				portletDataContext, calendarIds, calendarBooking);
		}

		long recurringCalendarBookingId =
			CalendarBookingConstants.RECURRING_CALENDAR_BOOKING_ID_DEFAULT;

		if (!calendarBooking.isMasterRecurringBooking()) {
			recurringCalendarBookingId =
				calendarBooking.getRecurringCalendarBookingId();
=======
			childCalendarIds = filterChildCalendarIds(
				calendarIds.keySet(), calendarBooking.getCalendarId());
>>>>>>> compatible
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarBooking);

		CalendarBooking importedCalendarBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarBooking existingCalendarBooking =
				fetchStagedModelByUuidAndGroupId(
					calendarBooking.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingCalendarBooking == null) {
				serviceContext.setUuid(calendarBooking.getUuid());

				importedCalendarBooking =
					_calendarBookingLocalService.addCalendarBooking(
						userId, calendarId, childCalendarIds,
<<<<<<< HEAD
						parentCalendarBookingId, recurringCalendarBookingId,
						calendarBooking.getTitleMap(),
=======
						parentCalendarBookingId, calendarBooking.getTitleMap(),
>>>>>>> compatible
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
			else {
				importedCalendarBooking =
					_calendarBookingLocalService.updateCalendarBooking(
						userId, existingCalendarBooking.getCalendarBookingId(),
						calendarId, childCalendarIds,
						calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
		}
		else {
			importedCalendarBooking =
				_calendarBookingLocalService.addCalendarBooking(
					userId, calendarId, childCalendarIds,
<<<<<<< HEAD
					parentCalendarBookingId, recurringCalendarBookingId,
					calendarBooking.getTitleMap(),
=======
					parentCalendarBookingId, calendarBooking.getTitleMap(),
>>>>>>> compatible
					calendarBooking.getDescriptionMap(),
					calendarBooking.getLocation(),
					calendarBooking.getStartTime(),
					calendarBooking.getEndTime(), calendarBooking.isAllDay(),
					calendarBooking.getRecurrence(),
					calendarBooking.getFirstReminder(),
					calendarBooking.getFirstReminderType(),
					calendarBooking.getSecondReminder(),
					calendarBooking.getSecondReminderType(), serviceContext);
		}

<<<<<<< HEAD
		_calendarBookingLocalService.updateStatus(
			userId, importedCalendarBooking, calendarBooking.getStatus(),
			serviceContext);

=======
>>>>>>> compatible
		// The root discussion message is not automatically imported when
		// importing a calendar booking

		List<Element> mbMessageElements =
			portletDataContext.getReferenceElements(
				calendarBooking, MBMessage.class);

		if (ListUtil.isNotEmpty(mbMessageElements)) {
			_mbMessageLocalService.addDiscussionMessage(
				userId, importedCalendarBooking.getUserName(),
				importedCalendarBooking.getGroupId(),
				CalendarBooking.class.getName(),
				importedCalendarBooking.getCalendarBookingId(),
				WorkflowConstants.ACTION_PUBLISH);
		}

		portletDataContext.importClassedModel(
			calendarBooking, importedCalendarBooking);
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		CalendarBooking existingBooking = fetchStagedModelByUuidAndGroupId(
			calendarBooking.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingBooking == null) || !existingBooking.isInTrash()) {
			return;
		}

<<<<<<< HEAD
		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			CalendarBooking.class.getName());
=======
		TrashHandler trashHandler = existingBooking.getTrashHandler();
>>>>>>> compatible

		if (trashHandler.isRestorable(existingBooking.getCalendarBookingId())) {
			trashHandler.restoreTrashEntry(
				userId, existingBooking.getCalendarBookingId());
		}
	}

<<<<<<< HEAD
=======
	protected long[] filterChildCalendarIds(
		Set<Long> calendarIds, long masterCalendarId) {

		Stream<Long> calendarIdsStream = calendarIds.stream();

		long[] childCalendarIds = calendarIdsStream.filter(
			calendarId -> calendarId != masterCalendarId
		).mapToLong(
			Long::longValue
		).toArray();

		return childCalendarIds;
	}

>>>>>>> compatible
	@Reference(unbind = "-")
	protected void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {

		_calendarBookingLocalService = calendarBookingLocalService;
	}

	@Reference(unbind = "-")
	protected void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {

		_mbMessageLocalService = mbMessageLocalService;
	}

<<<<<<< HEAD
	private long[] _getChildCalendarIds(
		PortletDataContext portletDataContext, Map<Long, Long> calendarIds,
		CalendarBooking calendarBooking) {

		long[] childCalendarIds = new long[0];

		Element calendarBookingElement =
			portletDataContext.getImportDataElement(calendarBooking);

		List<Element> childElements = calendarBookingElement.elements("child");

		for (Element childElement : childElements) {
			long calendarId = Long.valueOf(
				childElement.attributeValue("calendarId"));

			calendarId = MapUtil.getLong(calendarIds, calendarId, calendarId);

			childCalendarIds = ArrayUtil.append(childCalendarIds, calendarId);
		}

		return childCalendarIds;
	}

	private static final int[] _EXPORTABLE_STATUSES = {
		CalendarBookingWorkflowConstants.STATUS_APPROVED,
		CalendarBookingWorkflowConstants.STATUS_DENIED,
		CalendarBookingWorkflowConstants.STATUS_MAYBE
=======
	private static final int[] _EXPORTABLE_STATUSES = {
		CalendarBookingWorkflowConstants.STATUS_APPROVED,
		CalendarBookingWorkflowConstants.STATUS_DENIED,
		CalendarBookingWorkflowConstants.STATUS_MAYBE,
		CalendarBookingWorkflowConstants.STATUS_PENDING
>>>>>>> compatible
	};

	private CalendarBookingLocalService _calendarBookingLocalService;
	private MBMessageLocalService _mbMessageLocalService;

}