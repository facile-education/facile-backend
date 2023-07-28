
create table Search_SearchHistory (
	searchHistoryId LONG not null primary key,
	userId LONG,
	query VARCHAR(75) null,
	queryDate DATE null
);