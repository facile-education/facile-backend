create index IX_419F9BFF on News_News (authorId);

create index IX_8B3796A3 on News_NewsAttachedFile (newsId, groupId);

create index IX_637D161D on News_NewsPopulation (groupId, roleId);