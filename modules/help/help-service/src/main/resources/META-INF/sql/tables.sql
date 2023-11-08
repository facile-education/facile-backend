create table Help_HelpCategory (
	categoryId LONG not null primary key,
	categoryName VARCHAR(75) null,
	serviceId LONG,
	position INTEGER
);

create table Help_HelpItem (
	itemId LONG not null primary key,
	categoryId LONG,
	itemName VARCHAR(75) null,
	videoURL VARCHAR(75) null,
	videoDescription VARCHAR(75) null,
	manual VARCHAR(75) null,
	position INTEGER,
	language VARCHAR(75) null,
	isManagement BOOLEAN
);

create table Help_HelpItemRole (
	helpItemRoleId LONG not null primary key,
	itemId LONG,
	roleId LONG
);

create table Help_HelpLink (
	linkId LONG not null primary key,
	itemId LONG,
	linkName VARCHAR(75) null,
	linkUrl VARCHAR(75) null
);

create table Help_HelpQuestion (
	questionId LONG not null primary key,
	itemId LONG,
	question VARCHAR(75) null,
	answer VARCHAR(75) null
);

create table Help_HelpRelation (
	relationId LONG not null primary key,
	itemId LONG,
	relatedItemId LONG
);