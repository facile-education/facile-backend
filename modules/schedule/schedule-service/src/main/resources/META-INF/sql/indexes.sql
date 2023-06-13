create index IX_BD14000E on Schedule_CDTSession (courseItemId);
create index IX_A4B1B939 on Schedule_CDTSession (groupId);

create index IX_49043908 on Schedule_Homework (sourceSessionId);
create index IX_D208C9D2 on Schedule_Homework (targetSessionId);
create index IX_80B37D72 on Schedule_Homework (targetWeekId);

create index IX_F5DF2971 on Schedule_Session (courseItemId);
create index IX_F5D4FDF6 on Schedule_Session (groupId);

create index IX_CB2E0838 on Schedule_SessionStudent (sessionId, studentId);
create index IX_DF74F713 on Schedule_SessionStudent (studentId);

create index IX_CA181770 on Schedule_SessionTeacher (sessionId, substituteId);
create index IX_1470AB06 on Schedule_SessionTeacher (sessionId, teacherId);
create index IX_9CE6F2F3 on Schedule_SessionTeacher (teacherId);

create index IX_E8F7CD08 on Schedule_StudentHomework (homeworkId);
create index IX_1AABBFC on Schedule_StudentHomework (studentId, homeworkId);

create index IX_355D0689 on Schedule_Subject (name[$COLUMN_LENGTH:75$]);

create index IX_F7DA6900 on Schedule_SubjectGroupColor (groupId, subject[$COLUMN_LENGTH:75$]);

create index IX_9A7579BB on Schedule_TeacherSubject (schoolId);
create index IX_C93CFD35 on Schedule_TeacherSubject (teacherId);