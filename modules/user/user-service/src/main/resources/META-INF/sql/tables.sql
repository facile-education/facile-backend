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
	UserId LONG not null primary key,
	EntPersonJointure VARCHAR(75) null,
	UID VARCHAR(75) null,
	INE VARCHAR(75) null,
	EntEleveStructRattachId VARCHAR(75) null
);

create table User_UserContact (
	contactId LONG not null primary key,
	userId LONG,
	middleNames VARCHAR(75) null,
	birthName VARCHAR(75) null,
	address VARCHAR(200) null,
	isAddressAuthorized BOOLEAN,
	mail VARCHAR(150) null,
	isMailAuthorized BOOLEAN,
	mobilePhone VARCHAR(75) null,
	mobilePhoneSMS VARCHAR(75) null,
	homePhone VARCHAR(75) null,
	proPhone VARCHAR(75) null,
	familyLink VARCHAR(75) null
);

create table User_UserRelationship (
	childUserId LONG not null,
	parentUserId LONG not null,
	primary key (childUserId, parentUserId)
);