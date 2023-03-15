create table EELSynchro_ParentSynchro (
	schoolId LONG not null primary key,
	startDate DATE null,
	endDate DATE null,
	fileName VARCHAR(75) null,
	lineCount LONG,
	errorCount LONG
);

create table EELSynchro_Subject (
	subjectId LONG not null primary key,
	name VARCHAR(75) null
);

create table EELSynchro_TeacherSubject (
	teacherSubjectId LONG not null primary key,
	teacherId LONG,
	subjectId LONG,
	schoolId LONG
);