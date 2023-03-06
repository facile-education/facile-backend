create table Organization_OrgCiteScolaire (
	parentENTStructureUAI VARCHAR(75) null,
	childENTStructureUAI VARCHAR(75) not null primary key
);

create table Organization_OrgDetails (
	orgId LONG not null primary key,
	schoolId LONG,
	orgName VARCHAR(75) null,
	eduLevel VARCHAR(75) null,
	role_ INTEGER,
	type_ INTEGER,
	isArchive BOOLEAN
);

create table Organization_OrgMapping (
	organizationId LONG,
	entStructureUAI VARCHAR(75) not null primary key
);

create table Organization_OrgMembership (
	orgMemberId LONG not null primary key,
	groupId LONG,
	userId LONG,
	startDate DATE null,
	endDate DATE null,
	fullYear BOOLEAN
);