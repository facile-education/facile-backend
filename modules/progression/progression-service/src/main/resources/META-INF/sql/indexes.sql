create index IX_B71629A9 on Progression_ItemAssignment (homeworkId);
create index IX_B29F88EE on Progression_ItemAssignment (progressionItemId, homeworkId);
create index IX_23E99037 on Progression_ItemAssignment (sessionId);

create index IX_2E44F33C on Progression_ItemAttachedFile (progressionItemId);

create index IX_6565B331 on Progression_ItemContent (progressionItemId);

create index IX_715B73F0 on Progression_Progression (teacherId);

create index IX_F360B5B4 on Progression_ProgressionFolder (parentFolderId, progressionId);

create index IX_8D7EBD4F on Progression_ProgressionItem (homeworkId);
create index IX_4F2466A2 on Progression_ProgressionItem (progressionFolderId, progressionId);
create index IX_8DECEFD1 on Progression_ProgressionItem (sessionId);