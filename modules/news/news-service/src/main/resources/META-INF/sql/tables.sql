create table News_News (
	newsId LONG not null primary key,
	companyId LONG,
	title VARCHAR(75) null,
	content VARCHAR(75) null,
	authorId LONG,
	isSchoolNews BOOLEAN,
	isImportant BOOLEAN,
	expirationDate DATE null,
	publicationDate DATE null,
	modificationDate DATE null,
	imageId LONG
);

create table News_NewsAttachedFile (
	newsFileId LONG not null primary key,
	newsId LONG,
	groupId LONG,
	fileId LONG
);

create table News_NewsPopulation (
	newsId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	primary key (newsId, groupId, roleId)
);

create table News_NewsRead (
	newsId LONG not null,
	userId LONG not null,
	readDate DATE null,
	primary key (newsId, userId)
);