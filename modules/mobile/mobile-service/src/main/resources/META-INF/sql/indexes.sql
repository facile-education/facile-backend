create index IX_46EEA2D1 on Mobile_MobileDevice (manufacturerDeviceId[$COLUMN_LENGTH:75$]);
create index IX_A25B05D5 on Mobile_MobileDevice (userId);

create index IX_B8FFE6E6 on Mobile_MobileNotification (etabId, enable);
create index IX_E7E9E921 on Mobile_MobileNotification (userId, enable);

create index IX_8D1EE1F8 on Mobile_UserMobileToken (userId, mobileToken[$COLUMN_LENGTH:75$]);