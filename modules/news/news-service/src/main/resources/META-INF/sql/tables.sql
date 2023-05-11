create table News_News (
	newsId LONG not null primary key,
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
	newsId LONG not null,
	fileId LONG not null,
	fileName VARCHAR(75) null,
	primary key (newsId, fileId)
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