/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.school.life.constants.SchoollifeConstants;
import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.school.life.service.base.RenvoiServiceBaseImpl;
import com.weprode.facile.school.life.service.persistence.RenvoiPK;
import com.weprode.facile.school.life.utils.NotificationUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets pending renvois");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        JSONArray jsonRenvois = new JSONArray();
        try {
            User teacher = getGuestOrUser();
            DateFormat df = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);

            List<Renvoi> pendingRenvois = RenvoiLocalServiceUtil.getTeacherPendingRenvois(teacher.getUserId());
            for (Renvoi pendingRenvoi : pendingRenvois) {
                if (pendingRenvoi.getSourceSchoollifeSessionId() != 0 || SessionTeacherLocalServiceUtil.hasTeacherSession(teacher.getUserId(), pendingRenvoi.getSourceSessionId())) {

                    JSONObject jsonRenvoi = new JSONObject();
                    jsonRenvoi.put(JSONConstants.SESSION_ID, pendingRenvoi.getSchoollifeSessionId());
                    if (pendingRenvoi.getSourceSessionId() != 0) {
                        jsonRenvoi.put(JSONConstants.SOURCE_SESSION_ID, pendingRenvoi.getSourceSessionId());
                        jsonRenvoi.put(JSONConstants.SOURCE_SCHOOLLIFE_SESSION_ID, 0);
                        CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(pendingRenvoi.getSourceSessionId());
                        jsonRenvoi.put(JSONConstants.SUBJECT, cdtSession.getSubject());
                        jsonRenvoi.put(JSONConstants.SESSION_DATE, df.format(cdtSession.getStart()));
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

    @JSONWebService(value = "register-student-renvoi", method = "POST")
    public JSONObject registerStudentRenvoi(long schoollifeSessionId, long sourceTeacherId, long studentId, long sourceSessionId, long sourceSchoollifeSessionId, String registrationDate) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " registers student renvoi");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            User teacher = getGuestOrUser();
            DateFormat df = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);
            
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

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " sets renvoi reason");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

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

    @JSONWebService(value = "unregister-student-renvoi", method = "POST")
    public JSONObject unregisterStudentRenvoi(long schoollifeSessionId, long studentId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " unregisters student renvoi");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

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

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets candidate sessions");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            DateFormat df = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);

            // minDate is 7AM of the session's day
            // maxDate is the schoollife session's end date
            SchoollifeSession schoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(schoollifeSession.getStartDate());
            cal.set(Calendar.HOUR_OF_DAY, 7);
            cal.set(Calendar.MINUTE, 0);
            Date minDate = cal.getTime();

            // 1. SchoolLife sessions
            JSONArray candidateSessionsJson = SessionStudentLocalServiceUtil.getStudentSessions(user, studentId, minDate, schoollifeSession.getEndDate(), false);

            // 2. CDT sessions
            List<CDTSession> studentDaySessions = CDTSessionLocalServiceUtil.getStudentSessions(studentId, minDate, schoollifeSession.getEndDate());
            for (CDTSession studentDaySession : studentDaySessions) {
                JSONObject sessionJson = new JSONObject();
                sessionJson.put(JSONConstants.SESSION_ID, studentDaySession.getSessionId());
                sessionJson.put(JSONConstants.SUBJECT, studentDaySession.getSubject());
                sessionJson.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(studentDaySession.getGroupId()));
                sessionJson.put(JSONConstants.START_DATE, df.format(studentDaySession.getStart()));
                sessionJson.put(JSONConstants.END_DATE, df.format(studentDaySession.getEnd()));
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
