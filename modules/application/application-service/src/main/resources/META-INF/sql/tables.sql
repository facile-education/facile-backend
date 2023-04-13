create table Application_Application (
	applicationId LONG not null primary key,
	applicationName VARCHAR(75) null,
	applicationKey VARCHAR(75) null,
	categoryName VARCHAR(75) null,
	image STRING null,
	hasCustomUrl BOOLEAN,
	hasGlobalUrl BOOLEAN,
	globalUrl VARCHAR(200) null,
	exportUser BOOLEAN,
	exportParent BOOLEAN,
	exportStudent BOOLEAN,
	exportTeacher BOOLEAN,
	exportOther BOOLEAN,
	menuEntryId LONG
);

create table Application_AuthorizedSchool (
	authorizedSchoolId LONG not null primary key,
	applicationId LONG,
	schoolId LONG
);

create table Application_Broadcast (
	broadcastId LONG not null primary key,
	schoolId LONG,
	applicationId LONG,
	isBroadcasted BOOLEAN,
	applicationUrl VARCHAR(75) null
);

create table Application_BroadcastRule (
	broadcastRuleId LONG not null primary key,
	applicationId LONG,
	schoolId LONG,
	roleId LONG,
	orgId LONG,
	groupId LONG
);

create table Application_DefaultRole (
	defaultRoleId LONG not null primary key,
	roleId LONG,
	applicationId LONG
);