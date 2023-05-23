create index IX_29FA94DA on Organization_ClassCoursMapping (classOrgId, coursOrgId);
create index IX_22A8691F on Organization_ClassCoursMapping (coursOrgId);

create index IX_C278DE3E on Organization_OrgCiteScolaire (parentENTStructureUAI[$COLUMN_LENGTH:75$]);

create index IX_717D2295 on Organization_OrgDetails (schoolId, isArchive);
create index IX_C69415B1 on Organization_OrgDetails (schoolId, type_, role_, isArchive);
create index IX_5A9D8D45 on Organization_OrgDetails (type_);

create index IX_2415D9BE on Organization_OrgMapping (organizationId);