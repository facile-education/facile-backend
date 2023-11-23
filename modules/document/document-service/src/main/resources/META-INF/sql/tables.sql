create table Document_Activity (
	activityId LONG not null primary key,
	fileEntryId LONG,
	folderId LONG,
	userId LONG,
	groupId LONG,
	fileName VARCHAR(75) null,
	folderName VARCHAR(75) null,
	type_ INTEGER,
	modificationDate DATE null
);

create table Document_EditionLock (
	fileId LONG not null primary key,
	userId LONG,
	editionDate DATE null
);

create table Document_LoolToken (
	loolTokenId LONG not null primary key,
	userId LONG,
	token VARCHAR(75) null,
	editionDate DATE null
);

create table Document_Version (
	fileVersionId LONG not null primary key,
	dlFileEntryId LONG,
	versionNumber DOUBLE,
	comment_ VARCHAR(75) null,
	downloadCount LONG,
	viewCount LONG
);