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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.school.life.constants.SchoollifeConstants;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.model.SessionStudent;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.school.life.service.base.SessionStudentServiceBaseImpl;
import com.weprode.facile.school.life.service.persistence.SessionStudentPK;
import com.weprode.facile.school.life.utils.NotificationUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=schoollife",
                "json.web.service.context.path=SessionStudent"
        },
        service = AopService.class
)
public class SessionStudentServiceImpl extends SessionStudentServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SessionStudentServiceImpl.class);

    @JSONWebService(value = "get-session-members", method = "GET")
    public JSONObject getSessionMembers(long schoollifeSessionId) {
        JSONObject result = new JSONObject();
        JSONArray members = new JSONArray();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            List<SessionStudent> schoollifeSessionStudents = SessionStudentLocalServiceUtil.getSessionMembers(schoollifeSessionId);
            for (SessionStudent schoollifeSessionStudent : schoollifeSessionStudents) {
                try {
                    JSONObject jsonMember = new JSONObject();
                    jsonMember.put(JSONConstants.STUDENT_ID, schoollifeSessionStudent.getStudentId());
                    User student = UserLocalServiceUtil.getUser(schoollifeSessionStudent.getStudentId());
                    jsonMember.put(JSONConstants.REGISTERER_ID, schoollifeSessionStudent.getSourceTeacherId());
                    jsonMember.put(JSONConstants.FIRST_NAME, student.getFirstName());
                    jsonMember.put(JSONConstants.LAST_NAME, student.getLastName());
                    jsonMember.put(JSONConstants.IS_PRESENT, schoollifeSessionStudent.getIsPresent());
                    jsonMember.put(JSONConstants.COMMENT, schoollifeSessionStudent.getComment());
                    jsonMember.put(JSONConstants.SUBJECT, schoollifeSessionStudent.getSubject());
                    jsonMember.put(JSONConstants.CLASS_NAME, UserOrgsLocalServiceUtil.getStudentClassName(student));

                    members.put(jsonMember);
                } catch (Exception e) {
                    logger.error("Error fetching student " + schoollifeSessionStudent.getStudentId() + " in schoollife session " + schoollifeSessionId, e);
                }
            }
            result.put(JSONConstants.MEMBERS, members);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching student members for schoollife session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "register-student", method = "POST")
    public JSONObject registerStudent(long studentId, long schoollifeSessionId, String comment, String replayTestSubject, boolean notifyParents) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            User teacher = getGuestOrUser();
            boolean success = SessionStudentLocalServiceUtil.registerStudentToSession(teacher.getUserId(), studentId, schoollifeSessionId, comment, replayTestSubject, notifyParents);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
                NotificationUtil.notifyRetenueRegistration(teacher.getUserId(), studentId, schoollifeSessionId, comment, notifyParents);

            } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
                NotificationUtil.notifyTravauxRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);

            } else if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
                NotificationUtil.notifyEtudeRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);

            } else if (session.getType() == SchoollifeConstants.TYPE_DEPANNAGE) {
                NotificationUtil.notifyDepannageRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
            }
            result.put(JSONConstants.SUCCESS, success);

        } catch (Exception e) {
            logger.error("Error registering student " + studentId + " in session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "register-class", method = "POST")
    public JSONObject registerClass(long classId, long schoollifeSessionId, String comment, String replayTestSubject, boolean notifyParents) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            // Only handle classes for etude type
            if (session.getType() == SchoollifeConstants.TYPE_ETUDE && classId > 0) {
                int nbRegistered = 0;
                JSONArray notRegisteredArray = new JSONArray();

                List<Long> organizationIds = new ArrayList<>();
                organizationIds.add(classId);

                Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
                List<Long> roleIds = new ArrayList<>();
                roleIds.add(studentRole.getRoleId());
                List<User> studentList = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null,
                        roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

                for (User student: studentList) {
                    boolean registered = SessionStudentLocalServiceUtil.registerStudentToSession(user.getUserId(),
                            student.getUserId(), schoollifeSessionId, comment, replayTestSubject, notifyParents);

                    if (registered) {
                        ++ nbRegistered;
                        NotificationUtil.notifyEtudeRegistration(user.getUserId(), student.getUserId(), schoollifeSessionId, notifyParents);
                    } else {
                        notRegisteredArray.put(student.getFullName());
                    }
                }

                result.put(JSONConstants.SUCCESS, true);
                result.put(JSONConstants.NB_REGISTERED, nbRegistered);
                result.put(JSONConstants.NOT_REGISTERED_ARRAY, notRegisteredArray);
            }

        } catch (Exception e) {
            logger.error("Error registering class " + classId + " in session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "unregister-student", method = "GET")
    public JSONObject unregisterStudent(long studentId, long schoollifeSessionId, boolean allSessions) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            User teacher = getGuestOrUser();

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            boolean notifyParents = SessionStudentLocalServiceUtil.getSessionStudent(pk).getNotifyParents();
            SessionStudent studentSession = SessionStudentLocalServiceUtil.fetchSessionStudent(pk);
            boolean success = SessionStudentLocalServiceUtil.unregisterStudentToSession(studentId, schoollifeSessionId, allSessions);
            if (success) {
                if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
                    NotificationUtil.notifyEtudeUnregistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents, allSessions);
                } else if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
                    NotificationUtil.notifyRetenueUnregistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
                } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
                    NotificationUtil.notifyTravauxUnregistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents, studentSession);
                } else if (session.getType() == SchoollifeConstants.TYPE_DEPANNAGE) {
                    NotificationUtil.notifyDepannageUnregistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
                }
            }
            result.put(JSONConstants.SUCCESS, success);

        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "mark-students-present", method = "POST")
    public JSONObject markStudentsPresent(long schoollifeSessionId, String studentsPresence) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray studentsPresenceJson = new JSONArray(studentsPresence);
            for (int i = 0 ; i < studentsPresenceJson.length() ; i++) {
                JSONObject studentJson = studentsPresenceJson.getJSONObject(i);
                long studentId = studentJson.getLong(JSONConstants.STUDENT_ID);
                boolean isPresent = studentJson.getBoolean(JSONConstants.IS_PRESENT);
                SessionStudentLocalServiceUtil.markStudentPresent(schoollifeSessionId, studentId, isPresent);
                if (!isPresent) {
                    // If student is absent, send absence notification
                    SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
                    if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
                        NotificationUtil.notifyRetenueAbsence(studentId, schoollifeSessionId);
                    } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
                        NotificationUtil.notifyTravauxAbsence(studentId, schoollifeSessionId);
                    } else if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
                        NotificationUtil.notifyEtudeAbsence(studentId, schoollifeSessionId);
                    }
                }
            }

            // Set roll called and absence notif sent
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            session.setRollCalled(true);
            session.setAbsenceNotificationSent(true);
            SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(session);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error marking student absence for schoollifeSessionId " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
