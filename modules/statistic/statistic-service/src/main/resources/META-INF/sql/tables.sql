create table Statistics_LoolStat (
	statId LONG not null primary key,
	objectId LONG,
	userId LONG,
	saveAction BOOLEAN,
	actionDate DATE null,
	type_ INTEGER
);

create table Statistics_UserLogin (
	userLoginId LONG not null primary key,
	userId LONG,
	loginDate DATE null,
	role_ INTEGER,
	schoolId LONG,
	isMobileApp BOOLEAN
);