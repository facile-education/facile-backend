create table User_Affectation (
	affectationId LONG not null primary key,
	userId LONG,
	orgId LONG,
	schoolId LONG,
	type_ INTEGER,
	adminUserId LONG,
	affectationDate DATE null,
	expirationDate DATE null
);

create table User_LDAPMapping (
	userId LONG not null primary key,
	UID VARCHAR(75) null
);

create table User_NewsAdmin (
	newsAdminId LONG not null primary key,
	userId LONG,
	schoolId LONG
);

create table User_UserContact (
	userId LONG not null primary key,
	address VARCHAR(75) null,
	mobilePhone VARCHAR(75) null,
	homePhone VARCHAR(75) null,
	proPhone VARCHAR(75) null
);

create table User_UserRelationship (
	childUserId LONG not null,
	parentUserId LONG not null,
	primary key (childUserId, parentUserId)
);