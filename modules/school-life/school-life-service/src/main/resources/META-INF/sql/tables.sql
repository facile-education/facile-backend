create table Schoollife_Notification (
	schoollifeSessionId LONG not null,
	userId LONG not null,
	primary key (schoollifeSessionId, userId)
);

create table Schoollife_Renvoi (
	schoollifeSessionId LONG not null,
	studentId LONG not null,
	orgId LONG,
	schoolId LONG,
	renvoiDate DATE null,
	teacherId LONG,
	sourceSessionId LONG,
	sourceSchoollifeSessionId LONG,
	sourceTeacherId LONG,
	reason STRING null,
	status INTEGER,
	primary key (schoollifeSessionId, studentId)
);

create table Schoollife_SchoollifeSession (
	schoollifeSessionId LONG not null primary key,
	schoollifeSlotId LONG,
	schoolId LONG,
	type_ INTEGER,
	weekNb INTEGER,
	startDate DATE null,
	endDate DATE null,
	rollCalled BOOLEAN,
	absenceNotificationSent BOOLEAN
);

create table Schoollife_SchoollifeSlot (
	schoollifeSlotId LONG not null primary key,
	schoolId LONG,
	day INTEGER,
	startHour VARCHAR(75) null,
	endHour VARCHAR(75) null,
	teacherId LONG,
	type_ INTEGER,
	room VARCHAR(75) null,
	capacity INTEGER
);

create table Schoollife_SessionStudent (
	schoollifeSessionId LONG not null,
	studentId LONG not null,
	sourceTeacherId LONG,
	isPresent BOOLEAN,
	notifyParents BOOLEAN,
	comment_ STRING null,
	subject VARCHAR(300) null,
	primary key (schoollifeSessionId, studentId)
);