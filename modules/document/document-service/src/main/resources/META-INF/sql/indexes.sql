create index IX_806ACC11 on Document_Activity (groupId);

create index IX_3618E210 on Document_EditionLock (fileId, userId);

create index IX_AD44066E on Document_LoolToken (token[$COLUMN_LENGTH:75$]);

create index IX_187567B2 on Document_Version (dlFileEntryId, versionNumber);