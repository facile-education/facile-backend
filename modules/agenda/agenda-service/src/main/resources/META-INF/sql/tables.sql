create table Agenda_Event (
	eventId LONG not null primary key,
	companyId LONG,
	startDate DATE null,
	endDate DATE null,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	location VARCHAR(75) null,
	authorId LONG
);

create table Agenda_EventPopulation (
	eventId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	schoolId LONG,
	primary key (eventId, groupId, roleId)
);

create table Agenda_EventRead (
	eventId LONG not null,
	userId LONG not null,
	readDate DATE null,
	primary key (eventId, userId)
);