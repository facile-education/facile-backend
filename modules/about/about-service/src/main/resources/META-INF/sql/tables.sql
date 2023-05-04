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
	description VARCHAR(75) null,
	modifyDate DATE null
);