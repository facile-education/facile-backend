create table Access_Access (
	uuid_ VARCHAR(75) null,
	accessId LONG not null primary key,
	categoryId LONG,
	title VARCHAR(75) null,
	type_ INTEGER,
	externalUrl VARCHAR(75) null,
	folderId LONG,
	fileId LONG,
	thumbnail VARCHAR(75) null,
	position INTEGER
);

create table Access_AccessCategory (
	uuid_ VARCHAR(75) null,
	categoryId LONG not null primary key,
	schoolId LONG,
	categoryName VARCHAR(75) null,
	position INTEGER
);

create table Access_AccessProfile (
	uuid_ VARCHAR(75) null,
	accessId LONG not null,
	roleId LONG not null,
	primary key (accessId, roleId)
);