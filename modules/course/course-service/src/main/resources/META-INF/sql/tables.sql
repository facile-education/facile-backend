create table Course_ContentBlock (
	blockId LONG not null primary key,
	courseItemId LONG,
	modificationDate DATE null,
	blockName VARCHAR(75) null,
	blockValue VARCHAR(75) null,
	fileEntryId LONG,
	blockType INTEGER,
	order_ INTEGER
);

create table Course_Homework (
	homeworkId LONG not null primary key,
	homeworkType INTEGER,
	courseId LONG,
	teacherId LONG,
	title VARCHAR(75) null,
	modificationDate DATE null,
	sourceSessionId LONG,
	targetSessionId LONG,
	targetDate DATE null,
	isCustomStudentList BOOLEAN,
	estimatedTime INTEGER,
	publicationDate DATE null,
	isDraft BOOLEAN,
	isCorrectionSent BOOLEAN
);

create table Course_SessionContent (
	sessionId LONG not null primary key,
	companyId LONG,
	teacherId LONG,
	title VARCHAR(75) null,
	modificationDate DATE null,
	publicationDate DATE null,
	isDraft BOOLEAN
);

create table Course_StudentHomework (
	studentHomeworkId LONG not null primary key,
	homeworkId LONG,
	studentId LONG,
	isDone BOOLEAN,
	isSent BOOLEAN,
	sentDate DATE null,
	sentFileId LONG,
	isCorrected BOOLEAN,
	comment_ VARCHAR(75) null,
	correctionDate DATE null
);