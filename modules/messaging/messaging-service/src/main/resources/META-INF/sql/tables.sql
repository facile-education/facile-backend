create table Messaging_Message (
	messageId LONG not null primary key,
	folderId LONG,
	threadId LONG,
	sendMessageId LONG,
	senderId LONG,
	sendDate DATE null,
	senderName VARCHAR(75) null,
	messageSubject STRING null,
	messageContent STRING null,
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
	messageContent STRING null
);

create table Messaging_MessageFolder (
	folderId LONG not null primary key,
	userId LONG,
	folderName VARCHAR(300) null,
	type_ INTEGER,
	parentFolderId LONG
);

create table Messaging_MessageRecipients (
	messageId LONG not null primary key,
	recipients STRING null
);

create table Messaging_MessagingConfig (
	userId LONG not null primary key,
	isForwardActive BOOLEAN,
	forwardMail VARCHAR(300) null,
	isSignatureActive BOOLEAN,
	signature STRING null,
	isAutoReplyActive BOOLEAN,
	autoReplyContent STRING null
);