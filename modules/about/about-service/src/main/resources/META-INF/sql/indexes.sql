create index IX_113062F9 on About_EntVersion (isLast);

create index IX_7965D6E6 on About_EntVersionUser (entVersionId, userId);

create index IX_CB506AB5 on About_UserReadVersionNote (hasReadLastVersionNote);