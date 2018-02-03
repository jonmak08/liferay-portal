create table DDLRecord (
	uuid_ VARCHAR(75) null,
	recordId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	versionUserId LONG,
	versionUserName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	DDMStorageId LONG,
	recordSetId LONG,
<<<<<<< HEAD
	recordSetVersion VARCHAR(75) null,
=======
>>>>>>> compatible
	version VARCHAR(75) null,
	displayIndex INTEGER,
	lastPublishDate DATE null
);

create table DDLRecordSet (
	uuid_ VARCHAR(75) null,
	recordSetId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
<<<<<<< HEAD
	versionUserId LONG,
	versionUserName VARCHAR(75) null,
=======
>>>>>>> compatible
	createDate DATE null,
	modifiedDate DATE null,
	DDMStructureId LONG,
	recordSetKey VARCHAR(75) null,
<<<<<<< HEAD
	version VARCHAR(75) null,
=======
>>>>>>> compatible
	name STRING null,
	description STRING null,
	minDisplayRows INTEGER,
	scope INTEGER,
	settings_ TEXT null,
	lastPublishDate DATE null
);

<<<<<<< HEAD
create table DDLRecordSetVersion (
	recordSetVersionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	recordSetId LONG,
	DDMStructureVersionId LONG,
	name STRING null,
	description STRING null,
	settings_ TEXT null,
	version VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

=======
>>>>>>> compatible
create table DDLRecordVersion (
	recordVersionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	DDMStorageId LONG,
	recordSetId LONG,
<<<<<<< HEAD
	recordSetVersion VARCHAR(75) null,
=======
>>>>>>> compatible
	recordId LONG,
	version VARCHAR(75) null,
	displayIndex INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);