create index IX_9EB6B1BB on Course_ContentBlock (courseItemId);

create index IX_4066B264 on Course_Homework (courseId);
create index IX_B8BB608C on Course_Homework (sourceSessionId);
create index IX_41BFF156 on Course_Homework (targetSessionId);

create index IX_4783238C on Course_StudentHomework (homeworkId);
create index IX_5366EAF8 on Course_StudentHomework (studentId, homeworkId);