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

create table Course_CourseFolder (
	courseFolderId LONG not null primary key,
	courseId LONG,
	parentFolderId LONG,
	folderName VARCHAR(75) null,
	order_ INTEGER
);

create table Course_CourseItem (
	courseItemId LONG not null primary key,
	companyId LONG,
	courseId LONG,
	courseFolderId LONG,
	homeworkId LONG,
	modificationDate DATE null,
	itemName VARCHAR(75) null,
	isHomework BOOLEAN,
	order_ INTEGER
);

create table Course_Homework (
	homeworkId LONG not null primary key,
	homeworkType INTEGER,
	courseId LONG,
	sourceSessionId LONG,
	fromDate DATE null,
	targetSessionId LONG,
	targetDate DATE null,
	teacherId LONG,
	isCustomStudentList BOOLEAN,
	publicationDate DATE null,
	isDraft BOOLEAN
);

create table Course_ItemAttachedFile (
	itemAttachedFileId LONG not null primary key,
	courseItemId LONG,
	fileEntryId LONG,
	isAudioRecording BOOLEAN,
	isToBeCompleted BOOLEAN
);

create table Course_ItemContent (
	contentId LONG not null primary key,
	courseItemId LONG,
	modifiedDate DATE null,
	contentName VARCHAR(75) null,
	contentValue VARCHAR(75) null,
	fileEntryId LONG,
	contentType INTEGER,
	order_ INTEGER
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