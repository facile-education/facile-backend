create index IX_AB46DF1B on Group_CommunityInfos (creatorId, status);
create index IX_F7C4C313 on Group_CommunityInfos (expirationDate);
create index IX_A457E108 on Group_CommunityInfos (groupId);

create index IX_4F971ECF on Group_GroupMembership (groupId);
create index IX_A09A72D5 on Group_GroupMembership (userId, groupId);

create index IX_DE72377F on Group_MembershipActivity (groupId);