create table Calendar (
	uuid_ VARCHAR(75) null,
	calendarId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
<<<<<<< HEAD
=======
	resourceBlockId LONG,
>>>>>>> compatible
	calendarResourceId LONG,
	name STRING null,
	description STRING null,
	timeZoneId VARCHAR(75) null,
	color INTEGER,
	defaultCalendar BOOLEAN,
	enableComments BOOLEAN,
	enableRatings BOOLEAN,
	lastPublishDate DATE null
);

create table CalendarBooking (
	uuid_ VARCHAR(75) null,
	calendarBookingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
<<<<<<< HEAD
	calendarId LONG,
	calendarResourceId LONG,
	parentCalendarBookingId LONG,
	recurringCalendarBookingId LONG,
=======
	resourceBlockId LONG,
	calendarId LONG,
	calendarResourceId LONG,
	parentCalendarBookingId LONG,
>>>>>>> compatible
	vEventUid VARCHAR(255) null,
	title STRING null,
	description TEXT null,
	location STRING null,
	startTime LONG,
	endTime LONG,
	allDay BOOLEAN,
	recurrence STRING null,
	firstReminder LONG,
	firstReminderType VARCHAR(75) null,
	secondReminder LONG,
	secondReminderType VARCHAR(75) null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CalendarNotificationTemplate (
	uuid_ VARCHAR(75) null,
	calendarNotificationTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	calendarId LONG,
	notificationType VARCHAR(75) null,
	notificationTypeSettings VARCHAR(75) null,
	notificationTemplateType VARCHAR(75) null,
	subject VARCHAR(75) null,
	body TEXT null,
	lastPublishDate DATE null
);

create table CalendarResource (
	uuid_ VARCHAR(75) null,
	calendarResourceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
<<<<<<< HEAD
=======
	resourceBlockId LONG,
>>>>>>> compatible
	classNameId LONG,
	classPK LONG,
	classUuid VARCHAR(75) null,
	code_ VARCHAR(75) null,
	name STRING null,
	description STRING null,
	active_ BOOLEAN,
	lastPublishDate DATE null
);