create index IX_E69931DA on Schoollife_Notification (userId);

create index IX_89F5822D on Schoollife_Renvoi (schoolId);
create index IX_23E363CE on Schoollife_Renvoi (sourceTeacherId, status);

create index IX_A0A3EB35 on Schoollife_SchoollifeSession (schoolId, type_);
create index IX_A40DB9BA on Schoollife_SchoollifeSession (schoollifeSlotId);
create index IX_9E8C338 on Schoollife_SchoollifeSession (type_);

create index IX_4421752F on Schoollife_SchoollifeSlot (schoolId, type_);
create index IX_900A566A on Schoollife_SchoollifeSlot (teacherId);

create index IX_C1CE3D6C on Schoollife_SessionStudent (studentId);