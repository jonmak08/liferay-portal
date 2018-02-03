create table Contacts_Entry (
	entryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fullName VARCHAR(75) null,
<<<<<<< HEAD
	emailAddress VARCHAR(254) null,
=======
	emailAddress VARCHAR(75) null,
>>>>>>> compatible
	comments STRING null
);