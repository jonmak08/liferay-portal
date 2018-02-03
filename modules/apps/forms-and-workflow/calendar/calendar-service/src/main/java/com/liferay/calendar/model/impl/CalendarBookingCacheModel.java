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

package com.liferay.calendar.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.model.CalendarBooking;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CalendarBooking in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarBooking
 * @generated
 */
@ProviderType
public class CalendarBookingCacheModel implements CacheModel<CalendarBooking>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarBookingCacheModel)) {
			return false;
		}

		CalendarBookingCacheModel calendarBookingCacheModel = (CalendarBookingCacheModel)obj;

		if (calendarBookingId == calendarBookingCacheModel.calendarBookingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, calendarBookingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(59);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
<<<<<<< HEAD
=======
		sb.append(", resourceBlockId=");
		sb.append(resourceBlockId);
>>>>>>> compatible
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
		sb.append(", parentCalendarBookingId=");
		sb.append(parentCalendarBookingId);
<<<<<<< HEAD
		sb.append(", recurringCalendarBookingId=");
		sb.append(recurringCalendarBookingId);
=======
>>>>>>> compatible
		sb.append(", vEventUid=");
		sb.append(vEventUid);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", firstReminderType=");
		sb.append(firstReminderType);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append(", secondReminderType=");
		sb.append(secondReminderType);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalendarBooking toEntityModel() {
		CalendarBookingImpl calendarBookingImpl = new CalendarBookingImpl();

		if (uuid == null) {
<<<<<<< HEAD
			calendarBookingImpl.setUuid("");
=======
			calendarBookingImpl.setUuid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setUuid(uuid);
		}

		calendarBookingImpl.setCalendarBookingId(calendarBookingId);
		calendarBookingImpl.setGroupId(groupId);
		calendarBookingImpl.setCompanyId(companyId);
		calendarBookingImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			calendarBookingImpl.setUserName("");
=======
			calendarBookingImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarBookingImpl.setCreateDate(null);
		}
		else {
			calendarBookingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarBookingImpl.setModifiedDate(null);
		}
		else {
			calendarBookingImpl.setModifiedDate(new Date(modifiedDate));
		}

<<<<<<< HEAD
		calendarBookingImpl.setCalendarId(calendarId);
		calendarBookingImpl.setCalendarResourceId(calendarResourceId);
		calendarBookingImpl.setParentCalendarBookingId(parentCalendarBookingId);
		calendarBookingImpl.setRecurringCalendarBookingId(recurringCalendarBookingId);

		if (vEventUid == null) {
			calendarBookingImpl.setVEventUid("");
=======
		calendarBookingImpl.setResourceBlockId(resourceBlockId);
		calendarBookingImpl.setCalendarId(calendarId);
		calendarBookingImpl.setCalendarResourceId(calendarResourceId);
		calendarBookingImpl.setParentCalendarBookingId(parentCalendarBookingId);

		if (vEventUid == null) {
			calendarBookingImpl.setVEventUid(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setVEventUid(vEventUid);
		}

		if (title == null) {
<<<<<<< HEAD
			calendarBookingImpl.setTitle("");
=======
			calendarBookingImpl.setTitle(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setTitle(title);
		}

		if (description == null) {
<<<<<<< HEAD
			calendarBookingImpl.setDescription("");
=======
			calendarBookingImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setDescription(description);
		}

		if (location == null) {
<<<<<<< HEAD
			calendarBookingImpl.setLocation("");
=======
			calendarBookingImpl.setLocation(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setLocation(location);
		}

		calendarBookingImpl.setStartTime(startTime);
		calendarBookingImpl.setEndTime(endTime);
		calendarBookingImpl.setAllDay(allDay);

		if (recurrence == null) {
<<<<<<< HEAD
			calendarBookingImpl.setRecurrence("");
=======
			calendarBookingImpl.setRecurrence(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setRecurrence(recurrence);
		}

		calendarBookingImpl.setFirstReminder(firstReminder);

		if (firstReminderType == null) {
<<<<<<< HEAD
			calendarBookingImpl.setFirstReminderType("");
=======
			calendarBookingImpl.setFirstReminderType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setFirstReminderType(firstReminderType);
		}

		calendarBookingImpl.setSecondReminder(secondReminder);

		if (secondReminderType == null) {
<<<<<<< HEAD
			calendarBookingImpl.setSecondReminderType("");
=======
			calendarBookingImpl.setSecondReminderType(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setSecondReminderType(secondReminderType);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			calendarBookingImpl.setLastPublishDate(null);
		}
		else {
			calendarBookingImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		calendarBookingImpl.setStatus(status);
		calendarBookingImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
<<<<<<< HEAD
			calendarBookingImpl.setStatusByUserName("");
=======
			calendarBookingImpl.setStatusByUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			calendarBookingImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			calendarBookingImpl.setStatusDate(null);
		}
		else {
			calendarBookingImpl.setStatusDate(new Date(statusDate));
		}

		calendarBookingImpl.resetOriginalValues();

		return calendarBookingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		calendarBookingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

<<<<<<< HEAD
=======
		resourceBlockId = objectInput.readLong();

>>>>>>> compatible
		calendarId = objectInput.readLong();

		calendarResourceId = objectInput.readLong();

		parentCalendarBookingId = objectInput.readLong();
<<<<<<< HEAD

		recurringCalendarBookingId = objectInput.readLong();
=======
>>>>>>> compatible
		vEventUid = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		location = objectInput.readUTF();

		startTime = objectInput.readLong();

		endTime = objectInput.readLong();

		allDay = objectInput.readBoolean();
		recurrence = objectInput.readUTF();

		firstReminder = objectInput.readLong();
		firstReminderType = objectInput.readUTF();

		secondReminder = objectInput.readLong();
		secondReminderType = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(calendarBookingId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

<<<<<<< HEAD
=======
		objectOutput.writeLong(resourceBlockId);

>>>>>>> compatible
		objectOutput.writeLong(calendarId);

		objectOutput.writeLong(calendarResourceId);

		objectOutput.writeLong(parentCalendarBookingId);

<<<<<<< HEAD
		objectOutput.writeLong(recurringCalendarBookingId);

		if (vEventUid == null) {
			objectOutput.writeUTF("");
=======
		if (vEventUid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(vEventUid);
		}

		if (title == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (location == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeLong(startTime);

		objectOutput.writeLong(endTime);

		objectOutput.writeBoolean(allDay);

		if (recurrence == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(recurrence);
		}

		objectOutput.writeLong(firstReminder);

		if (firstReminderType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(firstReminderType);
		}

		objectOutput.writeLong(secondReminder);

		if (secondReminderType == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(secondReminderType);
		}

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long calendarBookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
<<<<<<< HEAD
	public long calendarId;
	public long calendarResourceId;
	public long parentCalendarBookingId;
	public long recurringCalendarBookingId;
=======
	public long resourceBlockId;
	public long calendarId;
	public long calendarResourceId;
	public long parentCalendarBookingId;
>>>>>>> compatible
	public String vEventUid;
	public String title;
	public String description;
	public String location;
	public long startTime;
	public long endTime;
	public boolean allDay;
	public String recurrence;
	public long firstReminder;
	public String firstReminderType;
	public long secondReminder;
	public String secondReminderType;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}