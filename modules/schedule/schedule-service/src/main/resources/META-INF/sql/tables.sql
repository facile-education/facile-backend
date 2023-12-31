create table Schedule_CDTSession (
	sessionId LONG not null primary key,
	start_ DATE null,
	end_ DATE null,
	slot INTEGER,
	fullCoursName VARCHAR(75) null,
	room VARCHAR(75) null,
	subject VARCHAR(75) null,
	groupId LONG,
	isManual BOOLEAN
);

create table Schedule_CourseDetails (
	courseGroupId LONG not null primary key,
	color VARCHAR(75) null,
	subjectId LONG
);

create table Schedule_GroupColor (
	groupId LONG not null primary key,
	color VARCHAR(75) null
);

create table Schedule_Holiday (
	holidayId LONG not null primary key,
	name VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null
);

create table Schedule_Homework (
	homeworkId LONG not null primary key,
	type_ LONG,
	sourceSessionId LONG,
	targetSessionId LONG,
	targetWeekId INTEGER,
	targetDate DATE null,
	groupId LONG,
	teacherId LONG,
	description STRING null,
	estimatedTime LONG,
	fromDate DATE null,
	isCustomStudentList BOOLEAN
);

create table Schedule_ScheduleConfiguration (
	configId LONG not null primary key,
	projectStartDate DATE null,
	schoolYearStartDate DATE null,
	schoolYearSemesterDate DATE null,
	schoolYearEndDate DATE null,
	h1Weeks VARCHAR(75) null,
	h2Weeks VARCHAR(75) null
);

create table Schedule_Session (
	sessionId LONG not null primary key,
	start_ DATE null,
	end_ DATE null,
	weekId LONG,
	fullCoursName VARCHAR(75) null,
	room VARCHAR(75) null,
	subject VARCHAR(75) null,
	schoolId LONG,
	groupId LONG,
	courseItemId LONG,
	isManual BOOLEAN
);

create table Schedule_SessionStudent (
	sessionStudentId LONG not null primary key,
	sessionId LONG,
	studentId LONG
);

create table Schedule_SessionTeacher (
	sessionTeacherId LONG not null primary key,
	sessionId LONG,
	teacherId LONG,
	status INTEGER,
	substituteId LONG,
	modificationDate DATE null,
	privateNotes VARCHAR(75) null
);

create table Schedule_SlotConfiguration (
	schoolId LONG not null,
	slotNumber INTEGER not null,
	sessionStartHour VARCHAR(75) null,
	sessionEndHour VARCHAR(75) null,
	primary key (schoolId, slotNumber)
);

create table Schedule_StudentHomework (
	studentHomeworkId LONG not null primary key,
	homeworkId LONG,
	studentId LONG,
	isDone BOOLEAN,
	isSent BOOLEAN,
	sentDate DATE null,
	sentFileId LONG
);

create table Schedule_Subject (
	subjectId LONG not null primary key,
	name VARCHAR(75) null
);

create table Schedule_SubjectGroupColor (
	subjectGroupColorId LONG not null primary key,
	subject VARCHAR(75) null,
	groupId LONG,
	color VARCHAR(75) null
);

create table Schedule_TeacherSubject (
	teacherSubjectId LONG not null primary key,
	teacherId LONG,
	subjectId LONG,
	schoolId LONG
);