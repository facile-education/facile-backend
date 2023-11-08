create index IX_855B0C07 on Preference_MobileDevice (manufaturerDeviceId[$COLUMN_LENGTH:255$]);
create index IX_E155397C on Preference_MobileDevice (userId);

create index IX_8EC72A4D on Preference_MobileNotification (etabId, enable);
create index IX_BDB12C88 on Preference_MobileNotification (userId, enable);

create index IX_7C9D7E15 on Preference_NotifyConfig (activate, digestPeriod);
create index IX_8192400F on Preference_NotifyConfig (userId);

create index IX_E22E79F on Preference_UserMobileToken (userId, mobileToken[$COLUMN_LENGTH:75$]);

create index IX_6D954610 on Preference_UserProperties (etabId, manualAccount);