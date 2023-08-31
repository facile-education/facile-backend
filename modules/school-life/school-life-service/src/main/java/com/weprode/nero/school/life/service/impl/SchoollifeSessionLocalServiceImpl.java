package com.weprode.nero.school.life.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSessionLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Comparator;


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
                    // Filter on week number and limit to current school year
                    if (weekNb == session.getWeekNb() &&
                            session.getStartDate().after(ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate()) &&
                            session.getStartDate().before(ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate())) {
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

    public boolean deleteSlotSessions(long schoollifeSlotId, Date startDate, Date endDate) {
        logger.info("Deleting schoollife sessions with schoollifeSlotId=" + schoollifeSlotId);

        boolean isAllSlotSessionAreDeleted = true;
        try {
            List<SchoollifeSession> sessionsToDelete = schoollifeSessionPersistence.findByschoollifeSlotId(schoollifeSlotId);
            if (sessionsToDelete != null) {
                for (SchoollifeSession sessionToDelete : sessionsToDelete) {
                    if ((startDate == null || !sessionToDelete.getStartDate().before(startDate)) &&
                        (endDate == null || !sessionToDelete.getStartDate().after(endDate))) { // !before = after or equals
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

    public SchoollifeSession getLastSession(long schoollifeSlotId) {
        List<SchoollifeSession> sessionsList = schoollifeSessionPersistence.findByschoollifeSlotId(schoollifeSlotId);

        // Sort by startDate
        // Create a new modifiable list in order to sort elements
        List<SchoollifeSession> modifiableSessionList = new ArrayList<>(sessionsList);
        Collections.sort(modifiableSessionList, new Comparator<>() {
            @Override
            public int compare(SchoollifeSession session1, SchoollifeSession session2) {
                return session1.getStartDate().compareTo(session2.getStartDate());
            }
        });

        return modifiableSessionList.get(sessionsList.size() - 1);
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
                            jsonSession.put(JSONConstants.SESSION_ID, slotSession.getSchoollifeSessionId());
                            jsonSession.put(JSONConstants.START_DATE, df.format(slotSession.getStartDate()));
                            jsonSession.put(JSONConstants.END_DATE, df.format(slotSession.getEndDate()));
                            jsonSession.put(JSONConstants.ROOM, teacherSlot.getRoom());
                            jsonSession.put(JSONConstants.TITLE, getSessionName(slotSession.getSchoollifeSessionId()));
                            jsonSession.put(JSONConstants.SUBJECT, getSessionName(slotSession.getSchoollifeSessionId()));
                            jsonSession.put(JSONConstants.COLOR, getColorFromSchoollifeType(slotSession.getType()));
                            jsonSession.put(JSONConstants.TYPE, slotSession.getType());

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

    public JSONObject formatSchoollifeSession(SchoollifeSession session, User user) throws PortalException {
        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        JSONObject jsonSession = new JSONObject();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        jsonSession.put(JSONConstants.SESSION_ID, session.getSchoollifeSessionId());
        jsonSession.put(JSONConstants.START_DATE, df.format(session.getStartDate()));
        jsonSession.put(JSONConstants.END_DATE, df.format(session.getEndDate()));
        jsonSession.put(JSONConstants.ROOM, slot.getRoom());
        jsonSession.put(JSONConstants.TYPE, session.getType());
        jsonSession.put(JSONConstants.SUBJECT, SchoollifeSessionLocalServiceUtil.getSessionName(session.getSchoollifeSessionId()));
        jsonSession.put(JSONConstants.TITLE, SchoollifeSessionLocalServiceUtil.getSessionName(session.getSchoollifeSessionId()));
        jsonSession.put(JSONConstants.COLOR, SchoollifeSessionLocalServiceUtil.getColorFromSchoollifeType(session.getType()));

        JSONArray jsonTeachers = new JSONArray();
        User teacher = UserLocalServiceUtil.getUser(slot.getTeacherId());
        JSONObject jsonTeacher = new JSONObject();
        jsonTeacher.put(JSONConstants.TEACHER_ID, teacher.getUserId());
        jsonTeacher.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
        jsonTeacher.put(JSONConstants.LAST_NAME, teacher.getLastName());
        jsonTeachers.put(jsonTeacher);
        jsonSession.put(JSONConstants.TEACHERS, jsonTeachers);

        int nbRegisteredStudents = SessionStudentLocalServiceUtil.getNbRegisteredStudents(session.getSchoollifeSessionId());
        jsonSession.put(JSONConstants.CAPACITY, slot.getCapacity());
        jsonSession.put(JSONConstants.NB_REGISTERED_STUDENTS, nbRegisteredStudents);

        int nbInscriptionLeft = slot.getCapacity() - nbRegisteredStudents;

        if (session.getType() == SchoollifeConstants.TYPE_RENVOI) {
            jsonSession.put(JSONConstants.CAN_REGISTER_STUDENT, nbInscriptionLeft > 0 &&
                    (user.getUserId() == teacher.getUserId() ||
                            RoleUtilsLocalServiceUtil.isDirectionMember(user) ||
                            RoleUtilsLocalServiceUtil.isSecretariat(user) ||
                            RoleUtilsLocalServiceUtil.isDoyen(user))
            );
        } else if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
            jsonSession.put(JSONConstants.CAN_REGISTER_STUDENT, nbInscriptionLeft > 0 &&
                    (RoleUtilsLocalServiceUtil.isTeacher(user) ||
                            RoleUtilsLocalServiceUtil.isDirectionMember(user) ||
                            RoleUtilsLocalServiceUtil.isSecretariat(user) ||
                            RoleUtilsLocalServiceUtil.isDoyen(user))
            );
        } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
            jsonSession.put(JSONConstants.CAN_REGISTER_STUDENT, nbInscriptionLeft > 0 &&
                    (RoleUtilsLocalServiceUtil.isTeacher(user) ||
                            RoleUtilsLocalServiceUtil.isDirectionMember(user) ||
                            RoleUtilsLocalServiceUtil.isSecretariat(user) ||
                            RoleUtilsLocalServiceUtil.isDoyen(user))
            );
        } else if (session.getType() == SchoollifeConstants.TYPE_DEPANNAGE) {
            jsonSession.put(JSONConstants.CAN_REGISTER_STUDENT, nbInscriptionLeft > 0 && user.getUserId() == teacher.getUserId());
        } else if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
            jsonSession.put(JSONConstants.CAN_REGISTER_STUDENT, nbInscriptionLeft > 0 &&
                    (RoleUtilsLocalServiceUtil.isTeacher(user) ||
                            RoleUtilsLocalServiceUtil.isDirectionMember(user) ||
                            RoleUtilsLocalServiceUtil.isSecretariat(user) ||
                            RoleUtilsLocalServiceUtil.isDoyen(user))
            );
        }

        jsonSession.put(JSONConstants.CAN_UPDATE_SLOT,
            RoleUtilsLocalServiceUtil.isDirectionMember(user) ||
                    RoleUtilsLocalServiceUtil.isSecretariat(user) ||
                    RoleUtilsLocalServiceUtil.isDoyen(user)
        );

        return jsonSession;
    }


    public String getColorFromSchoollifeType(int schoollifeType) {
        switch (schoollifeType) {
            case SchoollifeConstants.TYPE_RENVOI: return SchoollifeConstants.RENVOI_COLOR;
            case SchoollifeConstants.TYPE_RETENUE: return SchoollifeConstants.RETENUE_COLOR;
            case SchoollifeConstants.TYPE_TRAVAUX: return SchoollifeConstants.EPREUVE_COLOR;
            case SchoollifeConstants.TYPE_DEPANNAGE: return SchoollifeConstants.DEPANNAGE_COLOR;
            case SchoollifeConstants.TYPE_ETUDE: return SchoollifeConstants.ETUDE_COLOR;
            default: return "#000";
        }
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
