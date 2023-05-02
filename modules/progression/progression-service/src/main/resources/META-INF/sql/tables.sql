create table Progression_ItemAssignment (
	progressionItemId LONG not null,
	sessionId LONG not null,
	homeworkId LONG,
	assignedDate DATE null,
	modifiedDate DATE null,
	primary key (progressionItemId, sessionId)
);

create table Progression_ItemAttachedFile (
	itemAttachedFileId LONG not null primary key,
	progressionItemId LONG,
	fileEntryId LONG,
	isAudioRecording BOOLEAN,
	isToBeCompleted BOOLEAN
);

create table Progression_ItemContent (
	contentId LONG not null primary key,
	progressionItemId LONG,
	modifiedDate DATE null,
	contentName VARCHAR(75) null,
	contentValue STRING null,
	fileEntryId LONG,
	contentType INTEGER,
	order_ INTEGER,
	isToBeCompleted BOOLEAN
);

create table Progression_Progression (
	progressionId LONG not null primary key,
	teacherId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	volee VARCHAR(75) null,
	subjectId LONG,
	color VARCHAR(75) null
);

create table Progression_ProgressionFolder (
	progressionFolderId LONG not null primary key,
	progressionId LONG,
	parentFolderId LONG,
	folderName VARCHAR(75) null,
	order_ INTEGER
);

create table Progression_ProgressionItem (
	progressionItemId LONG not null primary key,
	progressionId LONG,
	sessionId LONG,
	homeworkId LONG,
	progressionFolderId LONG,
	modifiedDate DATE null,
	itemName VARCHAR(75) null,
	isHomework BOOLEAN,
	duration VARCHAR(75) null,
	type_ INTEGER,
	order_ INTEGER
);