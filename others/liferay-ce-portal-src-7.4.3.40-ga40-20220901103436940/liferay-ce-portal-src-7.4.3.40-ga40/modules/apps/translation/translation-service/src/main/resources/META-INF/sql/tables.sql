create table TranslationEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	translationEntryId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	content TEXT null,
	contentType VARCHAR(75) null,
	languageId VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (translationEntryId, ctCollectionId)
);