create index IX_FCDB42CA on Application_Application (applicationKey[$COLUMN_LENGTH:75$]);

create index IX_329B759A on Application_AuthorizedSchool (applicationId, schoolId);

create index IX_7B187106 on Application_Broadcast (applicationId, schoolId);

create index IX_4A996D6A on Application_BroadcastRule (applicationId, schoolId);
create index IX_73261897 on Application_BroadcastRule (schoolId);

create index IX_534ECDB2 on Application_DefaultRole (applicationId, roleId);