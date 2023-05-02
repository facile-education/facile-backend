create table Group_CommunityInfos (
	communityInfosId LONG not null primary key,
	groupId LONG,
	status INTEGER,
	creatorId LONG,
	creationDate DATE null,
	expirationDate DATE null,
	isPedagogical BOOLEAN,
	isContactList BOOLEAN,
	color VARCHAR(75) null
);

create table Group_GroupMembership (
	membershipId LONG not null primary key,
	groupId LONG,
	userId LONG,
	startDate DATE null,
	endDate DATE null,
	fullYear BOOLEAN
);

create table Group_MembershipActivity (
	membershipActivityId LONG not null primary key,
	groupId LONG,
	actionUserId LONG,
	targetUserIds VARCHAR(300) null,
	incoming BOOLEAN,
	movementDate DATE null
);