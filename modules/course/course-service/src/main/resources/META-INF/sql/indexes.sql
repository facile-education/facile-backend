create index IX_9EB6B1BB on Course_ContentBlock (courseItemId);

create index IX_F19006DD on Course_CourseFolder (courseId);
create index IX_46AD11C4 on Course_CourseFolder (parentFolderId, courseId);

create index IX_2285279A on Course_CourseItem (courseFolderId, courseId);
create index IX_577DD1C2 on Course_CourseItem (courseId);
create index IX_62EEFD17 on Course_CourseItem (homeworkId);

create index IX_4066B264 on Course_Homework (courseId);
create index IX_B8BB608C on Course_Homework (sourceSessionId);
create index IX_41BFF156 on Course_Homework (targetSessionId);

create index IX_27F25E1A on Course_ItemAttachedFile (courseItemId);

create index IX_55928845 on Course_ItemContent (courseItemId);

create index IX_4783238C on Course_StudentHomework (homeworkId);
create index IX_5366EAF8 on Course_StudentHomework (studentId, homeworkId);