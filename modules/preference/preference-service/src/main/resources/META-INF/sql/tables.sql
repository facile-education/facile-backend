create table Preference_MobileDevice (
	mobileDeviceId LONG not null primary key,
	manufaturerDeviceId VARCHAR(255) null,
	userId LONG,
	deviceModel VARCHAR(75) null,
	manufacturer VARCHAR(75) null,
	operatingSystem VARCHAR(75) null,
	operatingSystemVersion VARCHAR(75) null,
	browserUA VARCHAR(500) null
);

create table Preference_MobileNotification (
	mobileNotificationId LONG not null primary key,
	userId LONG,
	etabId LONG,
	enable BOOLEAN,
	token VARCHAR(75) null,
	device VARCHAR(75) null
);

create table Preference_NotifyConfig (
	notifyConfigId LONG not null primary key,
	userId LONG,
	activate BOOLEAN,
	notifyCasier BOOLEAN,
	notifyActu BOOLEAN,
	notifyGrpDoc BOOLEAN,
	notifyAgenda BOOLEAN,
	notifySync BOOLEAN,
	digestPeriod INTEGER
);

create table Preference_UserMobileToken (
	userMobileTokenId LONG not null primary key,
	userId LONG,
	mobileToken VARCHAR(75) null
);

create table Preference_UserProperties (
	userId LONG not null primary key,
	manualAccount BOOLEAN,
	hideMenu BOOLEAN,
	themeColor VARCHAR(75) null,
	etabId LONG,
	preferedSchoolId LONG,
	webdavActivated BOOLEAN,
	termsOfUseAgreedDate DATE null,
	lastSynchroDate DATE null,
	lastDashboardAccessDate DATE null
);