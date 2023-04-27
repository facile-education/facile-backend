package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSlotLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.school.life.model.SchoollifeSlot",
        service = AopService.class
)
public class SchoollifeSlotLocalServiceImpl extends SchoollifeSlotLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SchoollifeSlotLocalServiceImpl.class);

    public SchoollifeSlot addSlot(long schoolId, Date startDate, int day, String startHour, String endHour, long teacherId, int type, String room, int capacity) {
        logger.info("About to create a schoollife slot for schoolId " + schoolId + ", day=" + day + ", startHour=" + startHour +", endHour=" + endHour + ", teacherId=" + teacherId + ", type=" + type + ", room = " + room + ", capacity = " + capacity);

        try {
            long schoollifeSlotId = counterLocalService.increment();
            SchoollifeSlot schoollifeSlot = createSchoollifeSlot(schoollifeSlotId);
            schoollifeSlot.setSchoolId(schoolId);
            schoollifeSlot.setDay(day);
            schoollifeSlot.setStartHour(startHour);
            schoollifeSlot.setEndHour(endHour);
            schoollifeSlot.setTeacherId(teacherId);
            schoollifeSlot.setType(type);
            schoollifeSlot.setRoom(room);
            schoollifeSlot.setCapacity(capacity);
            schoollifeSlot = schoollifeSlotPersistence.update(schoollifeSlot);

            // Create sessions from now to the end of the school year
            ScheduleConfiguration schoolConfig = ScheduleConfigurationLocalServiceUtil.getSchoolConfiguration(schoolId);
            if (schoolConfig == null) {
                logger.error("No configuration is set for schoolId " + schoolId);
            } else {
                if (startDate.before(schoolConfig.getStartSessionsDate()) || startDate.after(schoolConfig.getEndSessionsDate())) {
                    logger.error("Error when creating a schoollife slot : school configuration start and end dates are not properly configured");
                } else {
                    // Current week
                    Calendar startCal = Calendar.getInstance();
                    startCal.setTime(startDate);

                    // Because Calendar.MONDAY = 2
                    startCal.set(Calendar.DAY_OF_WEEK, day + 1);

                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(startCal.getTime());

                    // startHour and endHour are formatted HH:mm
                    int startH;
                    int startM;
                    int endH;
                    int endM;
                    try {
                        startH = Integer.parseInt(startHour.substring(0, 2));
                        startM = Integer.parseInt(startHour.substring(3, 5));
                        endH   = Integer.parseInt(endHour.substring(0, 2));
                        endM   = Integer.parseInt(endHour.substring(3, 5));
                    } catch (Exception e) {
                        logger.error("Error parsing startHour and endHour", e);
                        return null;
                    }

                    startCal.set(Calendar.HOUR_OF_DAY, startH);
                    startCal.set(Calendar.MINUTE, startM);

                    endCal.set(Calendar.HOUR_OF_DAY, endH);
                    endCal.set(Calendar.MINUTE, endM);

                    while (endCal.getTime().before(schoolConfig.getEndSessionsDate())) {
                        SchoollifeSessionLocalServiceUtil.addSession(schoollifeSlotId, schoolId, startCal.getTime(), endCal.getTime(), type);
                        startCal.add(Calendar.DATE, 7);
                        endCal.add(Calendar.DATE, 7);
                    }

                    logger.info("Created schoollife slot for schoolId " + schoolId + ", day=" + day + ", startHour=" + startHour +", endHour=" + endHour + ", teacherId=" + teacherId + ", type=" + type + ", capacity = " + capacity);
                }
            }

            return schoollifeSlot;
        } catch (Exception e) {
            logger.error("Error creating a schoollife slot", e);
            return null;
        }
    }

    public SchoollifeSlot editSlot(long schoollifeSlotId, Date currentDate, int newDay, String newStartHour, String newEndHour, long newTeacherId, String newRoom, int newCapacity) {
        logger.info("About to edit schoollife " + schoollifeSlotId + ", newDay=" + newDay + ", newStartHour=" + newStartHour +", newEndHour=" + newEndHour + ", newTeacherId=" + newTeacherId + ", newRoom = " + newRoom + ", newCapacity = " + newCapacity);

        try {
            SchoollifeSlot schoollifeSlot = schoollifeSlotPersistence.findByPrimaryKey(schoollifeSlotId);
            schoollifeSlot.setTeacherId(newTeacherId);
            schoollifeSlot.setRoom(newRoom);
            schoollifeSlot.setCapacity(newCapacity);
            schoollifeSlot = schoollifeSlotPersistence.update(schoollifeSlot);

            // Are day or hours changed ?
            if (schoollifeSlot.getDay() != newDay || !schoollifeSlot.getStartHour().equals(newStartHour) || !schoollifeSlot.getEndHour().equals(newEndHour)) {

                logger.info("Day and/or hours have changed -> editing future sessions for this slot");
                // Change all future sessions
                List<SchoollifeSession> slotSessions = SchoollifeSessionLocalServiceUtil.getSlotSessions(schoollifeSlotId);
                if (slotSessions != null) {
                    for (SchoollifeSession slotSession : slotSessions) {
                        if (!slotSession.getStartDate().before(currentDate)) {

                            // Init calendar to old startDate
                            Calendar startCal = Calendar.getInstance();
                            startCal.setTime(slotSession.getStartDate());

                            // Because Calendar.MONDAY = 2
                            startCal.set(Calendar.DAY_OF_WEEK, newDay + 1);

                            Calendar endCal = Calendar.getInstance();
                            endCal.setTime(startCal.getTime());

                            // startHour and endHour are formatted HH:mm
                            int startH;
                            int startM;
                            int endH ;
                            int endM;
                            try {
                                startH = Integer.parseInt(newStartHour.substring(0, 2));
                                startM = Integer.parseInt(newStartHour.substring(3, 5));
                                endH   = Integer.parseInt(newEndHour.substring(0, 2));
                                endM   = Integer.parseInt(newEndHour.substring(3, 5));
                            } catch (Exception e) {
                                logger.error("Error parsing startHour and endHour", e);
                                return null;
                            }

                            startCal.set(Calendar.HOUR_OF_DAY, startH);
                            startCal.set(Calendar.MINUTE, startM);

                            endCal.set(Calendar.HOUR_OF_DAY, endH);
                            endCal.set(Calendar.MINUTE, endM);

                            slotSession.setStartDate(startCal.getTime());
                            slotSession.setEndDate(endCal.getTime());
                            SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(slotSession);
                        }
                    }
                }

                // Update slot with new dates
                schoollifeSlot.setDay(newDay);
                schoollifeSlot.setStartHour(newStartHour);
                schoollifeSlot.setEndHour(newEndHour);
                schoollifeSlot = schoollifeSlotPersistence.update(schoollifeSlot);
            }

            logger.info("Edited schoollife " + schoollifeSlotId + ", newDay=" + newDay + ", newStartHour=" + newStartHour +", newEndHour=" + newEndHour + ", newTeacherId=" + newTeacherId + ", newRoom = " + newRoom + ", newCapacity = " + newCapacity);
            return schoollifeSlot;
        } catch (Exception e) {
            logger.error("Error creating a schoollife slot", e);
            return null;
        }
    }

    public List<SchoollifeSlot> getWeekSlots(long schoolId, int type) {
        try {
            return schoollifeSlotPersistence.findByschoolId_type(schoolId, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean deleteSlot(long schoollifeSlotId, Date limitDate) {
        // Delete associated future sessions
        boolean isAllSlotSessionAreDeleted = SchoollifeSessionLocalServiceUtil.deleteSlotSessions(schoollifeSlotId, limitDate);

        try {
            if (isAllSlotSessionAreDeleted) {
                schoollifeSlotPersistence.remove(schoollifeSlotId);
                logger.info("Deleted schoollife slot for schoolId " + schoollifeSlotId);
            }
        } catch (Exception e) {
            logger.error("Error deleting schoollifeSlotId " + schoollifeSlotId, e);
            return false;
        }

        return true;
    }
}
