create index IX_447077B7 on Messaging_Message (folderId, isNew);
create index IX_F5996668 on Messaging_Message (folderId, threadId);
create index IX_63CD6678 on Messaging_Message (sendMessageId);
create index IX_B4DDBE4B on Messaging_Message (threadId);

create index IX_CAC8041 on Messaging_MessageFolder (userId, parentFolderId);
create index IX_8AEB69FB on Messaging_MessageFolder (userId, type_);