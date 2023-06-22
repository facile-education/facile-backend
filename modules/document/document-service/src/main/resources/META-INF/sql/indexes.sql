create index IX_2A9C07E8 on Document_Activity (fileEntryId);
create index IX_105B8C56 on Document_Activity (folderId);
create index IX_806ACC11 on Document_Activity (groupId);

create index IX_3618E210 on Document_EditionLock (fileId, userId);

create index IX_AD44066E on Document_LoolToken (token[$COLUMN_LENGTH:75$]);

create index IX_187567B2 on Document_Version (dlFileEntryId, versionNumber);