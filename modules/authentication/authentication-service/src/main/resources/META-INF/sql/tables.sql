create table Authentication_LoginLock (
	login VARCHAR(75) not null primary key,
	failedLoginAttempts INTEGER,
	isLocked BOOLEAN,
	lockEndDate DATE null
);