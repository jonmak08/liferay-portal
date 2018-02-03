<%--
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
--%>

<%@ include file="/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.calendar.web#/view_calendar_booking.jsp#pre" />

<%
String backURL = ParamUtil.getString(request, "backURL");

int instanceIndex = BeanParamUtil.getInteger(calendarBooking, request, "instanceIndex");

calendarBooking = RecurrenceUtil.getCalendarBookingInstance(calendarBooking, instanceIndex);

Calendar calendar = calendarBooking.getCalendar();

long startTime = calendarBooking.getStartTime();

java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, userTimeZone);

long endTime = calendarBooking.getEndTime();

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, userTimeZone);

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());
%>

<div class="container-fluid-1280">
	<div class="panel panel-default">
		<liferay-ui:header
			backURL="<%= backURL %>"
			cssClass="panel-heading"
			title="<%= calendarBooking.getTitle(locale) %>"
		/>

		<aui:fieldset markupView="lexicon">
			<dl class="property-list">
				<dt>
<<<<<<< HEAD
					<liferay-ui:message key="status" />:
				</dt>
				<dd>
					<aui:workflow-status markupView="lexicon" model="<%= CalendarBooking.class %>" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= calendarBooking.getStatus() %>" />
				</dd>
				<dt>
=======
>>>>>>> compatible
					<liferay-ui:message key="starts" />:
				</dt>
				<dd>
					<%= dateFormatLongDate.format(startTimeJCalendar.getTime()) + ", " + dateFormatTime.format(startTimeJCalendar.getTime()) %>
				</dd>
				<dt>
					<liferay-ui:message key="ends" />:
				</dt>
				<dd>
					<%= dateFormatLongDate.format(endTimeJCalendar.getTime()) + ", " + dateFormatTime.format(endTimeJCalendar.getTime()) %>
				</dd>

				<%
<<<<<<< HEAD
				List<CalendarBooking> childCalendarBookings = calendarDisplayContext.getChildCalendarBookings(calendarBooking);
=======
				List<CalendarBooking> childCalendarBookings = calendarBooking.getChildCalendarBookings();
>>>>>>> compatible
				%>

				<c:if test="<%= !childCalendarBookings.isEmpty() %>">
					<dt>
						<liferay-ui:message key="resources" />:
					</dt>
					<dd>

						<%
						List<String> calendarResourcesNames = new ArrayList<String>();

						for (CalendarBooking childCalendarBooking : childCalendarBookings) {
							CalendarResource calendarResource = childCalendarBooking.getCalendarResource();

							calendarResourcesNames.add(calendarResource.getName(locale));
						}
						%>

						<%= HtmlUtil.escape(StringUtil.merge(calendarResourcesNames, ", ")) %>
					</dd>
				</c:if>

				<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
					<dt>
						<liferay-ui:message key="location" />:
					</dt>
					<dd>
						<span class="location"><%= HtmlUtil.escape(calendarBooking.getLocation()) %></span>
					</dd>
				</c:if>

<<<<<<< HEAD
				<c:if test="<%= Validator.isNotNull(calendarBooking.getRecurrence()) %>">
=======
				<c:if test="<%= calendarBooking.isRecurring() %>">
>>>>>>> compatible
					<dt>
						<liferay-ui:message key="repeat" />:
					</dt>
					<dd>
						<span id="<portlet:namespace />recurrenceSummary"></span>
					</dd>
				</c:if>
			</dl>

			<liferay-expando:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
				<liferay-expando:custom-attribute-list
					className="<%= CalendarBooking.class.getName() %>"
					classPK="<%= calendarBooking.getCalendarBookingId() %>"
					editable="<%= false %>"
					label="<%= true %>"
				/>
			</liferay-expando:custom-attributes-available>

			<p>
				<%= calendarBooking.getDescription(locale) %>
			</p>

			<div class="entry-categories">
<<<<<<< HEAD
				<liferay-asset:asset-categories-summary
=======
				<liferay-ui:asset-categories-summary
>>>>>>> compatible
					className="<%= CalendarBooking.class.getName() %>"
					classPK="<%= calendarBooking.getCalendarBookingId() %>"
				/>
			</div>

			<div class="entry-tags">
<<<<<<< HEAD
				<liferay-asset:asset-tags-summary
=======
				<liferay-ui:asset-tags-summary
>>>>>>> compatible
					className="<%= CalendarBooking.class.getName() %>"
					classPK="<%= calendarBooking.getCalendarBookingId() %>"
					message="tags"
				/>
			</div>

			<div class="entry-links">
<<<<<<< HEAD
				<liferay-asset:asset-links
=======
				<liferay-ui:asset-links
>>>>>>> compatible
					assetEntryId="<%= layoutAssetEntry.getEntryId() %>"
				/>
			</div>

			<c:if test="<%= calendar.isEnableRatings() %>">
				<div class="entry-ratings">
					<liferay-ui:ratings
						className="<%= CalendarBooking.class.getName() %>"
						classPK="<%= calendarBooking.getCalendarBookingId() %>"
						inTrash="<%= calendarBooking.isInTrash() %>"
					/>
				</div>
			</c:if>
		</aui:fieldset>

		<c:if test="<%= calendar.isEnableComments() %>">
			<liferay-ui:panel-container extended="<%= false %>" id="calendarBookingPanelContainer" persistState="<%= true %>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="calendarBookingCommentsPanel" persistState="<%= true %>" title="comments">
<<<<<<< HEAD
					<liferay-comment:discussion
=======
					<liferay-ui:discussion
>>>>>>> compatible
						className="<%= CalendarBooking.class.getName() %>"
						classPK="<%= calendarBooking.getCalendarBookingId() %>"
						formName="fm2"
						ratingsEnabled="<%= true %>"
						redirect="<%= currentURL %>"
						userId="<%= calendarBooking.getUserId() %>"
					/>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</c:if>

		<portlet:actionURL name="invokeTransition" var="invokeTransitionURL" />

<<<<<<< HEAD
		<c:if test="<%= Validator.isNotNull(calendarBooking.getRecurrence()) %>">
=======
		<c:if test="<%= calendarBooking.isRecurring() %>">
>>>>>>> compatible
			<%@ include file="/calendar_booking_recurrence_language_keys.jspf" %>

			<aui:script use="liferay-calendar-recurrence-util">
				var summaryNode = A.one('#<portlet:namespace />recurrenceSummary');

				var endValue = 'never';
				var untilDate = null;

				<%
				Recurrence recurrence = calendarBooking.getRecurrenceObj();

				java.util.Calendar untilJCalendar = recurrence.getUntilJCalendar();
				%>

				<c:choose>
					<c:when test="<%= untilJCalendar != null %>">
						endValue = 'on';

						untilDate = new Date(<%= untilJCalendar.get(java.util.Calendar.YEAR) %>, <%= untilJCalendar.get(java.util.Calendar.MONTH) %>, <%= untilJCalendar.get(java.util.Calendar.DATE) %>);
					</c:when>
					<c:when test="<%= recurrence.getCount() > 0 %>">
						endValue = 'after';
					</c:when>
				</c:choose>

				<%
				Frequency frequency = recurrence.getFrequency();

				PositionalWeekday positionalWeekday = recurrence.getPositionalWeekday();

				JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

				List<String> weekdayValues = new ArrayList<>();

				for (Weekday weekday : recurrence.getWeekdays()) {
					weekdayValues.add(weekday.getValue());
				}
				%>

				var positionalWeekday = null;

				<c:if test="<%= (frequency.equals(Frequency.MONTHLY) || frequency.equals(Frequency.YEARLY)) && (positionalWeekday != null) %>">
					positionalWeekday = {
						month: <%= startTimeJCalendar.get(java.util.Calendar.MONTH) %>,
						position: <%= positionalWeekday.getPosition() %>,
						weekday: '<%= positionalWeekday.getWeekday() %>'
					};
				</c:if>

				var recurrence = {
					count: <%= recurrence.getCount() %>,
					endValue: endValue,
					frequency: '<%= String.valueOf(frequency) %>',
					interval: <%= recurrence.getInterval() %>,
					positionalWeekday: positionalWeekday,
					untilDate: untilDate,
					weekdays: <%= jsonSerializer.serialize(weekdayValues) %>
				};

				var recurrenceSummary = Liferay.RecurrenceUtil.getSummary(recurrence);

				summaryNode.html(recurrenceSummary);
			</aui:script>
		</c:if>

		<liferay-util:dynamic-include key="com.liferay.calendar.web#/view_calendar_booking.jsp#post" />
	</div>

	<aui:form action="<%= invokeTransitionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBooking.getCalendarBookingId() %>" />
<<<<<<< HEAD
		<aui:input name="startTime" type="hidden" value="<%= calendarBooking.getStartTime() %>" />
		<aui:input name="status" type="hidden" />
		<aui:input name="updateInstance" type="hidden" value="false" />
		<aui:input name="allFollowing" type="hidden" value="false" />
=======
		<aui:input name="status" type="hidden" />
>>>>>>> compatible

		<aui:button-row>

			<%
			boolean hasManageBookingsPermission = CalendarPermission.contains(permissionChecker, calendar, CalendarActionKeys.MANAGE_BOOKINGS) && !calendarBooking.isDraft();
			boolean hasWorkflowInstanceLink = WorkflowInstanceLinkLocalServiceUtil.hasWorkflowInstanceLink(themeDisplay.getCompanyId(), calendarBooking.getGroupId(), CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());
			%>

			<c:if test="<%= hasManageBookingsPermission && !hasWorkflowInstanceLink %>">
				<c:if test="<%= calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_APPROVED %>">
					<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_APPROVED + ");" %>' value="accept" />
				</c:if>

				<c:if test="<%= calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_MAYBE %>">
					<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_MAYBE + ");" %>' value="maybe" />
				</c:if>

				<c:if test="<%= calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_DENIED %>">
					<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_DENIED + ");" %>' value="decline" />
				</c:if>
			</c:if>
		</aui:button-row>
	</aui:form>

	<aui:script>
		function <portlet:namespace />invokeTransition(status) {
			document.<portlet:namespace />fm.<portlet:namespace />status.value = status;

<<<<<<< HEAD
			if (<%= calendarBooking.isRecurring() %>) {
				Liferay.RecurrenceUtil.openConfirmationPanel(
					'invokeTransition',
					function() {
						document.<portlet:namespace />fm.<portlet:namespace />updateInstance.value = 'true';
						document.<portlet:namespace />fm.<portlet:namespace />allFollowing.value = 'false';
						submitForm(document.<portlet:namespace />fm);
					},
					function() {
						document.<portlet:namespace />fm.<portlet:namespace />updateInstance.value = 'true';
						document.<portlet:namespace />fm.<portlet:namespace />allFollowing.value = 'true';
						submitForm(document.<portlet:namespace />fm);
					},
					function() {
						submitForm(document.<portlet:namespace />fm);
					}
				);
			}
			else {
				submitForm(document.<portlet:namespace />fm);
			}
=======
			submitForm(document.<portlet:namespace />fm);
>>>>>>> compatible
		}
	</aui:script>
</div>