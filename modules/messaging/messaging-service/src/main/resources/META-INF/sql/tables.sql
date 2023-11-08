create table Messaging_Message (
	messageId LONG not null primary key,
	companyId LONG,
	folderId LONG,
	threadId LONG,
	sendMessageId LONG,
	senderId LONG,
	sendDate DATE null,
	senderName VARCHAR(75) null,
	messageSubject VARCHAR(75) null,
	messageContent VARCHAR(75) null,
	isNew BOOLEAN,
	readDate DATE null,
	isAnswered BOOLEAN,
	isForwarded BOOLEAN,
	type_ INTEGER
);

create table Messaging_MessageAttachFile (
	messageId LONG not null,
	fileId LONG not null,
	primary key (messageId, fileId)
);

create table Messaging_MessageContent (
	messageId LONG not null primary key,
	messageContent VARCHAR(75) null
);

create table Messaging_MessageFolder (
	folderId LONG not null primary key,
	userId LONG,
	folderName VARCHAR(75) null,
	type_ INTEGER,
	parentFolderId LONG
);

create table Messaging_MessageRecipients (
	messageId LONG not null primary key,
	recipients VARCHAR(75) null
);

create table Messaging_MessagingConfig (
	userId LONG not null primary key,
	isForwardActive BOOLEAN,
	forwardMail VARCHAR(75) null,
	isSignatureActive BOOLEAN,
	signature VARCHAR(75) null,
	isAutoReplyActive BOOLEAN,
	autoReplyContent VARCHAR(75) null
);