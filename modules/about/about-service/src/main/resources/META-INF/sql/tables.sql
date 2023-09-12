create table About_EntVersion (
	entVersionId LONG not null primary key,
	version VARCHAR(75) null,
	details STRING null,
	versionDate DATE null,
	isLast BOOLEAN
);

create table About_EntVersionUser (
	versionUserId LONG not null primary key,
	entVersionId LONG,
	userId LONG
);

create table About_UpdateInformation (
	updateInfoId LONG not null primary key,
	description STRING null,
	modifyDate DATE null
);

create table About_UserReadVersionNote (
	userId LONG not null primary key,
	hasReadLastVersionNote BOOLEAN
);

create table About_VersionNote (
	versionNoteId LONG not null primary key,
	title VARCHAR(75) null,
	content STRING null,
	versionNoteDate DATE null
);