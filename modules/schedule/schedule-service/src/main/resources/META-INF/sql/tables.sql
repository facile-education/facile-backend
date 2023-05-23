create table Schedule_AttachFile (
	attachFileId LONG not null primary key,
	homeworkId LONG,
	sessionId LONG,
	fileName VARCHAR(75) null,
	size_ INTEGER,
	type_ INTEGER,
	isEditable BOOLEAN
);

create table Schedule_CDTSession (
	sessionId LONG not null primary key,
	sessionStart DATE null,
	sessionEnd DATE null,
	weekId LONG,
	published BOOLEAN,
	title VARCHAR(250) null,
	fullCoursName VARCHAR(75) null,
	description STRING null,
	room VARCHAR(75) null,
	subject VARCHAR(75) null,
	schoolId LONG,
	groupId LONG,
	isManual BOOLEAN
);

create table Schedule_DailySchedule (
	schoolId LONG not null,
	sessionId INTEGER not null,
	sessionStartHour VARCHAR(75) null,
	sessionEndHour VARCHAR(75) null,
	primary key (schoolId, sessionId)
);

create table Schedule_GroupColor (
	groupId LONG not null primary key,
	color VARCHAR(75) null
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

create table Schedule_HomeworkDropboxFolder (
	homeworkDropboxFolderId LONG not null primary key,
	homeworkId LONG,
	teacherId LONG,
	folderId LONG
);

create table Schedule_ScheduleConfiguration (
	schoolId LONG not null primary key,
	startDayTime VARCHAR(75) null,
	endDayTime VARCHAR(75) null,
	startSessionsDate DATE null,
	endSessionsDate DATE null
);

create table Schedule_SessionParentClass (
	sessionParentClassId LONG not null primary key,
	sessionId LONG,
	groupId LONG
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
	modificationDate DATE null
);

create table Schedule_StudentAttachFile (
	studentHomeworkAttachFileId LONG not null primary key,
	attachFileId LONG,
	studentId LONG,
	fileEntryId LONG,
	modifiedDate DATE null,
	isSent BOOLEAN,
	sentDate DATE null
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

create table Schedule_SubjectGroupColor (
	subjectGroupColorId LONG not null primary key,
	subject VARCHAR(75) null,
	groupId LONG,
	color VARCHAR(75) null
);

create table Schedule_TeacherGroupColor (
	teacherGroupColorId LONG not null primary key,
	teacherId LONG,
	groupId LONG,
	color VARCHAR(75) null
);

create table Schedule_WeeklySchedule (
	schoolId LONG not null,
	dayId INTEGER not null,
	primary key (schoolId, dayId)
);