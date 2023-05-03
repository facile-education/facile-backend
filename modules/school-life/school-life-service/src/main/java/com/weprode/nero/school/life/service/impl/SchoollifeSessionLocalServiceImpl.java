package com.weprode.nero.school.life.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.service.TeacherGroupColorLocalServiceUtil;
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSessionLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        property = "model.class.name=com.weprode.nero.school.life.model.SchoollifeSession",
        service = AopService.class
)
public class SchoollifeSessionLocalServiceImpl extends SchoollifeSessionLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SchoollifeSessionLocalServiceImpl.class);

    public SchoollifeSession addSession(long schoollifeSlotId, long schoolId, Date startDate, Date endDate, int type) {
        try {
            long schoollifeSessionId = counterLocalService.increment();

            SchoollifeSession schoollifeSession = createSchoollifeSession(schoollifeSessionId);
            schoollifeSession.setSchoollifeSlotId(schoollifeSlotId);
            schoollifeSession.setSchoolId(schoolId);
            schoollifeSession.setStartDate(startDate);	// TODO save hour (atm it stores only the day)
            schoollifeSession.setEndDate(endDate);
            schoollifeSession.setAbsenceNotificationSent(false);
            schoollifeSession.setRollCalled(false);

            // Force locale to fr to always have same week number
            Locale locale = new Locale("fr", "FR");
            // Get week number from current date
            Calendar cal = Calendar.getInstance(locale);

            cal.setTime(startDate);
            int weekNb = cal.get(Calendar.WEEK_OF_YEAR);
            schoollifeSession.setWeekNb(weekNb);

            schoollifeSession.setType(type);
            schoollifeSession = schoollifeSessionPersistence.update(schoollifeSession);

            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            logger.info("Created schoollife session for slotId " + schoollifeSlotId + ", type="+type + " from " + df.format(startDate) + " to " + df.format(endDate));

            return schoollifeSession;
        } catch (Exception e) {
            logger.error("Error creating a schoollife session", e);
            return null;
        }
    }

    public List<SchoollifeSession> getSlotSessions(long schoollifeSlotId) {
        try {
            return schoollifeSessionPersistence.findByschoollifeSlotId(schoollifeSlotId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<SchoollifeSession> getWeekSessions(long schoolId, int type, Date fromDate) {
        List<SchoollifeSession> weekSessions = new ArrayList<>();

        try {
            // Force locale to fr to always have mondays as start of week
            Locale locale = new Locale("fr", "FR");
            // Get week number from current date
            Calendar cal = Calendar.getInstance(locale);

            cal.setTime(fromDate);
            int weekNb = cal.get(Calendar.WEEK_OF_YEAR);

            List<SchoollifeSession> sessions = schoollifeSessionPersistence.findByschoolId_type(schoolId, type);
            if (sessions != null) {
                for (SchoollifeSession session : sessions) {
                    if (weekNb == session.getWeekNb()) {
                        weekSessions.add(session);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting schoollife sessions at date "+fromDate.toString(), e);
        }

        return weekSessions;
    }

    // Get sessions of given type, in the given date range, for which the roll has been called and the absence notification has not yet run
    public List<SchoollifeSession> getUnnotifiedSessions(int type, Date startDate, Date endDate) {
        List<SchoollifeSession> daySessions = new ArrayList<>();

        try {
            List<SchoollifeSession> sessions = schoollifeSessionPersistence.findBytype(type);
            if (sessions != null) {
                for (SchoollifeSession session : sessions) {
                    if (session.isRollCalled() && !session.isAbsenceNotificationSent() && session.getStartDate().after(startDate) && session.getEndDate().before(endDate)) {
                        daySessions.add(session);
                    }
                }
            }
        } catch (Exception e) {
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            logger.error("Error getting unnotified schoollife sessions from " + df.format(startDate) + " to " + df.format(endDate), e);
        }

        return daySessions;
    }

    public boolean deleteSlotSessions(long schoollifeSlotId, Date limitDate) {
        logger.info("Deleting schoollife sessions with schoollifeSlotId=" + schoollifeSlotId);

        boolean isAllSlotSessionAreDeleted = true;
        try {
            List<SchoollifeSession> sessionsToDelete = schoollifeSessionPersistence.findByschoollifeSlotId(schoollifeSlotId);
            if (sessionsToDelete != null) {
                for (SchoollifeSession sessionToDelete : sessionsToDelete) {
                    if (limitDate == null || !sessionToDelete.getStartDate().before(limitDate)) { // !before = after or equals
                        deleteSession(sessionToDelete.getSchoollifeSessionId());
                    } else {
                        isAllSlotSessionAreDeleted = false;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error deleting schoollife sessions with schoollifeSlotId=" + schoollifeSlotId, e);
            return false;
        }

        return isAllSlotSessionAreDeleted;
    }

    public boolean deleteSession(long schoollifeSessionId) {
        // Delete all students
        SessionStudentLocalServiceUtil.deleteSession(schoollifeSessionId);

        try {
            schoollifeSessionPersistence.remove(schoollifeSessionId);
            logger.info("Deleted schoollife session " + schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error deleting schoollife session with schoollifeSessionId=" + schoollifeSessionId, e);
            return false;
        }

        return true;
    }

    public String getSessionName(long schoollifeSessionId) {
        try {
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            if (session.getType() == SchoollifeConstants.TYPE_RENVOI) {
                return SchoollifeConstants.RENVOI;
            } else if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
                return SchoollifeConstants.RETENUE;
            } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
                return SchoollifeConstants.EPREUVE;
            } else if (session.getType() == SchoollifeConstants.TYPE_DEPANNAGE) {
                return SchoollifeConstants.DEPANNAGE;
            } else if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
                return SchoollifeConstants.ETUDE;
            }
        } catch (Exception e) {
            logger.error("Error getting session name schoollifeSessionId=" + schoollifeSessionId, e);
        }

        return StringPool.BLANK;
    }

    public JSONArray getTeacherSessions(long teacherId, Date minDate, Date maxDate) {
        JSONArray jsonSessions = new JSONArray();

        try {
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            List<SchoollifeSlot> teacherSlots = schoollifeSlotPersistence.findByteacherId(teacherId);

            if (teacherSlots != null) {
                for (SchoollifeSlot teacherSlot : teacherSlots) {
                    List<SchoollifeSession> slotSessions = SchoollifeSessionLocalServiceUtil.getSlotSessions(teacherSlot.getSchoollifeSlotId());
                    for (SchoollifeSession slotSession : slotSessions) {
                        if (!slotSession.getStartDate().before(minDate) && !slotSession.getEndDate().after(maxDate)) {

                            // Convert schoollife session to JSON
                            JSONObject jsonSession = new JSONObject();
                            jsonSession.put(JSONConstants.SCHOOLLIFE_SESSION_ID, slotSession.getSchoollifeSessionId());
                            jsonSession.put(JSONConstants.START_DATE, df.format(slotSession.getStartDate()));
                            jsonSession.put(JSONConstants.END_DATE, df.format(slotSession.getEndDate()));
                            jsonSession.put(JSONConstants.ROOM, teacherSlot.getRoom());
                            jsonSession.put(JSONConstants.TITLE, getSessionName(slotSession.getSchoollifeSessionId()));
                            jsonSession.put(JSONConstants.SUBJECT, getSessionName(slotSession.getSchoollifeSessionId()));

                            // Color is picked in the color pool, the groupId is replaced by the schoollife type
                            String color = TeacherGroupColorLocalServiceUtil.getTeacherGroupColor(teacherId, slotSession.getType());
                            jsonSession.put(JSONConstants.COLOR, color);

                            jsonSessions.put(jsonSession);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching schoollife sessions for teacher " + teacherId, e);
        }

        return jsonSessions;
    }

    public boolean setRollCalled(long schoollifeSessionId) {
        try {
            SchoollifeSession session = schoollifeSessionPersistence.findByPrimaryKey(schoollifeSessionId);
            session.setRollCalled(true);

            schoollifeSessionPersistence.update(session);
            logger.info("Setting roll called for session " + schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error setting roll called for schoollifeSessionId=" + schoollifeSessionId, e);
            return false;
        }

        return true;
    }
}
