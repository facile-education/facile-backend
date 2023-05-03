package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.Renvoi;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.school.life.service.base.RenvoiServiceBaseImpl;
import com.weprode.nero.school.life.service.persistence.RenvoiPK;
import com.weprode.nero.school.life.utils.NotificationUtil;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=schoollife",
                "json.web.service.context.path=Renvoi"
        },
        service = AopService.class
)
public class RenvoiServiceImpl extends RenvoiServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(RenvoiServiceImpl.class);

    @JSONWebService(value = "get-pending-renvois", method = "GET")
    public JSONObject getPendingRenvois() {
        JSONObject result = new JSONObject();

        JSONArray jsonRenvois = new JSONArray();
        try {
            User teacher = getGuestOrUser();
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

            List<Renvoi> pendingRenvois = RenvoiLocalServiceUtil.getTeacherPendingRenvois(teacher.getUserId());
            for (Renvoi pendingRenvoi : pendingRenvois) {
                if (pendingRenvoi.getSourceSchoollifeSessionId() != 0 || SessionTeacherLocalServiceUtil.hasTeacherSession(teacher.getUserId(), pendingRenvoi.getSourceSessionId())) {

                    JSONObject jsonRenvoi = new JSONObject();
                    jsonRenvoi.put(JSONConstants.SCHOOLLIFE_SESSION_ID, pendingRenvoi.getSchoollifeSessionId());
                    if (pendingRenvoi.getSourceSessionId() != 0) {
                        jsonRenvoi.put(JSONConstants.SOURCE_SESSION_ID, pendingRenvoi.getSourceSessionId());
                        jsonRenvoi.put(JSONConstants.SOURCE_SCHOOLLIFE_SESSION_ID, 0);
                        CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(pendingRenvoi.getSourceSessionId());
                        jsonRenvoi.put(JSONConstants.SUBJECT, cdtSession.getSubject());
                        jsonRenvoi.put(JSONConstants.SESSION_DATE, df.format(cdtSession.getSessionStart()));
                    } else {
                        jsonRenvoi.put(JSONConstants.SOURCE_SESSION_ID, 0);
                        jsonRenvoi.put(JSONConstants.SOURCE_SCHOOLLIFE_SESSION_ID, pendingRenvoi.getSourceSchoollifeSessionId());
                        SchoollifeSession schoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(pendingRenvoi.getSourceSchoollifeSessionId());
                        jsonRenvoi.put(JSONConstants.SUBJECT, SchoollifeSessionLocalServiceUtil.getSessionName(pendingRenvoi.getSourceSchoollifeSessionId()));
                        jsonRenvoi.put(JSONConstants.SESSION_DATE, df.format(schoollifeSession.getStartDate()));
                    }
                    jsonRenvoi.put(JSONConstants.SOURCE_TEACHER_ID, pendingRenvoi.getSourceTeacherId());
                    jsonRenvoi.put(JSONConstants.TEACHER_ID, pendingRenvoi.getTeacherId());
                    jsonRenvoi.put(JSONConstants.STUDENT_ID, pendingRenvoi.getStudentId());
                    User student = UserLocalServiceUtil.getUser(pendingRenvoi.getStudentId());
                    jsonRenvoi.put(JSONConstants.STUDENT, student.getFullName());
                    jsonRenvoi.put(JSONConstants.CLASS_NAME, UserOrgsLocalServiceUtil.getStudentClassName(student));
                    jsonRenvoi.put(JSONConstants.RENVOI_DATE, df.format(pendingRenvoi.getRenvoiDate()));

                    jsonRenvois.put(jsonRenvoi);
                }
            }

            result.put(JSONConstants.PENDING_RENVOIS, jsonRenvois);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching pending renvois for teacher", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "register-student-renvoi", method = "GET")
    public JSONObject registerStudentRenvoi(long schoollifeSessionId, long sourceTeacherId, long studentId, long sourceSessionId, long sourceSchoollifeSessionId, String registrationDate) {
        JSONObject result = new JSONObject();

        try {
            User teacher = getGuestOrUser();
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            
            logger.info("Teacher " + teacher.getUserId() + " registers student " + studentId + " in schoollifeSessionId " + schoollifeSessionId + " with sourceTeacherId " + sourceTeacherId + ", sourceSessionId=" + sourceSessionId + ", sourceSchoollifeSessionId=" + sourceSchoollifeSessionId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            boolean success;

            if (session.getType() == SchoollifeConstants.TYPE_RENVOI) {
                success = SessionStudentLocalServiceUtil.registerStudentToSession(sourceTeacherId, studentId, schoollifeSessionId, "", "", false);
                RenvoiLocalServiceUtil.addRenvoi(schoollifeSessionId, teacher.getUserId(), sourceTeacherId, studentId, sourceSessionId, sourceSchoollifeSessionId, df.parse(registrationDate));
                NotificationUtil.notifyRenvoiRegistration(teacher.getUserId(), sourceTeacherId, sourceSessionId, sourceSchoollifeSessionId, studentId, schoollifeSessionId);

            } else {
                success = SessionStudentLocalServiceUtil.registerStudentToSession(teacher.getUserId(), studentId, schoollifeSessionId, "", "", false);
            }
            result.put(JSONConstants.SUCCESS, success);

        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "set-renvoi-reason", method = "POST")
    public JSONObject setRenvoiReason(long schoollifeSessionId, long studentId, String reason) {
        JSONObject result = new JSONObject();

        try {
            User teacher = getGuestOrUser();
            RenvoiLocalServiceUtil.setReason(schoollifeSessionId, studentId, reason);
            NotificationUtil.notifyRenvoiReason(teacher.getUserId(), studentId, schoollifeSessionId, reason);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "unregister-student-renvoi", method = "GET")
    public JSONObject unregisterStudentRenvoi(long schoollifeSessionId, long studentId) {
        JSONObject result = new JSONObject();

        try {
            User teacher = getGuestOrUser();
            RenvoiPK renvoiPK = new RenvoiPK(schoollifeSessionId, studentId);
            Renvoi renvoi = RenvoiLocalServiceUtil.getRenvoi(renvoiPK);

            boolean success = SessionStudentLocalServiceUtil.unregisterStudentToSession(studentId, schoollifeSessionId, false);
            success = success && RenvoiLocalServiceUtil.removeRenvoi(schoollifeSessionId, studentId);

            if (success) {
                NotificationUtil.notifyRenvoiUnregistration(teacher.getUserId(), renvoi.getSourceTeacherId(), renvoi.getSourceSessionId(), renvoi.getSourceSchoollifeSessionId(), studentId, schoollifeSessionId);
            }
            result.put(JSONConstants.SUCCESS, success);

        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-candidate-sessions", method = "GET")
    public JSONObject getCandidateSessions(long schoollifeSessionId, long studentId) {
        JSONObject result = new JSONObject();

        try {
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

            // minDate is 7AM of the session's day
            // maxDate is the schoollife session's end date
            SchoollifeSession schoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(schoollifeSession.getStartDate());
            cal.set(Calendar.HOUR_OF_DAY, 7);
            cal.set(Calendar.MINUTE, 0);
            Date minDate = cal.getTime();

            // 1. SchoolLife sessions
            JSONArray candidateSessionsJson = SessionStudentLocalServiceUtil.getStudentSessions(studentId, minDate, schoollifeSession.getEndDate(), false);

            // 2. CDT sessions
            List<CDTSession> studentDaySessions = CDTSessionLocalServiceUtil.getStudentSessions(studentId, minDate, schoollifeSession.getEndDate());
            for (CDTSession studentDaySession : studentDaySessions) {
                JSONObject sessionJson = new JSONObject();
                sessionJson.put(JSONConstants.SESSION_ID, studentDaySession.getSessionId());
                sessionJson.put(JSONConstants.TITLE, studentDaySession.getTitle());
                sessionJson.put(JSONConstants.SUBJECT, studentDaySession.getSubject());
                sessionJson.put(JSONConstants.START_DATE, df.format(studentDaySession.getSessionStart()));
                sessionJson.put(JSONConstants.END_DATE, df.format(studentDaySession.getSessionEnd()));
                sessionJson.put(JSONConstants.ROOM, studentDaySession.getRoom());

                // Teachers
                JSONArray teachersJson = new JSONArray();
                List<User> teachers = SessionTeacherLocalServiceUtil.getTeachers(studentDaySession.getSessionId());
                boolean thereIsSubstituteTeacherInList = false;
                for (User teacher : teachers) {
                    if (SessionTeacherLocalServiceUtil.isSubstituted(teacher.getUserId(), studentDaySession.getSessionId())) {
                        thereIsSubstituteTeacherInList = true;
                    }
                }

                for (User teacher : teachers) {
                    if (thereIsSubstituteTeacherInList) { // Only add the substitute Teacher
                        if (SessionTeacherLocalServiceUtil.isSubstituted(teacher.getUserId(), studentDaySession.getSessionId())) {
                            JSONObject teacherJson = new JSONObject();
                            teacherJson.put(JSONConstants.TEACHER_ID, teacher.getUserId());
                            teacherJson.put(JSONConstants.NAME, teacher.getFullName());
                            teachersJson.put(teacherJson);
                        }
                    } else {
                        JSONObject teacherJson = new JSONObject();
                        teacherJson.put(JSONConstants.TEACHER_ID, teacher.getUserId());
                        teacherJson.put(JSONConstants.NAME, teacher.getFullName());
                        teachersJson.put(teacherJson);
                    }
                }
                sessionJson.put(JSONConstants.TEACHERS, teachersJson);
                candidateSessionsJson.put(sessionJson);
            }
            result.put(JSONConstants.CANDIDATE_SESSIONS, candidateSessionsJson);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
