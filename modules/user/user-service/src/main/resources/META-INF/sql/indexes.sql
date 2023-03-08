create index IX_856D3BB5 on User_Affectation (schoolId);
create index IX_F25F5D8C on User_Affectation (userId);

create index IX_A7CBDCF2 on User_LDAPMapping (EntEleveStructRattachId[$COLUMN_LENGTH:75$]);
create index IX_B3324675 on User_LDAPMapping (EntPersonJointure[$COLUMN_LENGTH:75$]);
create index IX_E530627 on User_LDAPMapping (UID[$COLUMN_LENGTH:75$]);

create index IX_9F481421 on User_UserContact (userId);

create index IX_B6EE3C61 on User_UserRelationship (parentUserId);