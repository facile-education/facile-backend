create table Mobile_MobileDevice (
	mobileDeviceId LONG not null primary key,
	userId LONG,
	manufacturerDeviceId VARCHAR(75) null,
	deviceModel VARCHAR(75) null,
	manufacturer VARCHAR(75) null,
	operatingSystem VARCHAR(75) null,
	operatingSystemVersion VARCHAR(75) null,
	browserUA VARCHAR(75) null
);

create table Mobile_MobileNotification (
	mobileNotificationId LONG not null primary key,
	userId LONG,
	etabId LONG,
	enable BOOLEAN,
	token VARCHAR(75) null,
	device VARCHAR(75) null
);

create table Mobile_UserMobileToken (
	userMobileTokenId LONG not null primary key,
	userId LONG,
	mobileToken VARCHAR(75) null
);