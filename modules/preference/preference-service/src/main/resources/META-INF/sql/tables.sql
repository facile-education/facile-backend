create table Preference_UserProperties (
	userId LONG not null primary key,
	manualAccount BOOLEAN,
	hideMenu BOOLEAN,
	themeColor VARCHAR(75) null,
	etabId LONG,
	preferedSchoolId LONG,
	webdavActivated BOOLEAN,
	termsOfUseAgreedDate DATE null,
	lastSynchroDate DATE null
);