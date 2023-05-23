package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SessionStudent;
import com.weprode.nero.school.life.service.NotificationLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SessionStudentServiceBaseImpl;
import com.weprode.nero.school.life.service.persistence.SessionStudentPK;
import com.weprode.nero.school.life.utils.NotificationUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @JSONWebService(value = "get-sessions", method = "GET")
    public JSONObject getSessions(long studentId, long classId, String minDateStr, String maxDateStr) {
        JSONObject result = new JSONObject();
        
        if (studentId > 0) {
            logger.info("Get sessions for student " + studentId + " between " + minDateStr + " and " + maxDateStr);
        } else if (classId > 0) {
            logger.info("Get sessions for class " + classId + " between " + minDateStr + " and " + maxDateStr);
        }

        try {
            User user = getGuestOrUser();
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date minDate = df.parse(minDateStr);
            Date maxDate = df.parse(maxDateStr);

            // 1. SchoolLife sessions
            // TODO return class HHC ?
            JSONArray jsonSessions = new JSONArray();
            if (studentId > 0) {
                jsonSessions = SessionStudentLocalServiceUtil.getStudentSessions(studentId, minDate, maxDate);
            }

            // 2. CDT sessions
            List<CDTSession> sessions = new ArrayList<>();
            if (studentId > 0) {
                sessions = CDTSessionLocalServiceUtil.getStudentSessions(studentId, minDate, maxDate);
            } else if (classId > 0) {
                Organization classOrg = OrganizationLocalServiceUtil.getOrganization(classId);
                sessions = CDTSessionLocalServiceUtil.getGroupSessions(classOrg.getGroupId(), minDate, maxDate, true);
            }

            for (CDTSession cdtSession : sessions) {

                JSONObject cdtSessionJson = new JSONObject();
                cdtSessionJson.put(JSONConstants.CDT_SESSION_ID, cdtSession.getSessionId());
                cdtSessionJson.put(JSONConstants.START_DATE, df.format(cdtSession.getSessionStart()));
                cdtSessionJson.put(JSONConstants.END_DATE, df.format(cdtSession.getSessionEnd()));
                cdtSessionJson.put(JSONConstants.TITLE, cdtSession.getTitle());
                cdtSessionJson.put(JSONConstants.ROOM, cdtSession.getRoom());
                cdtSessionJson.put(JSONConstants.SUBJECT, cdtSession.getSubject());

                // Teacher
                // cdt sessions can have many teachers, unlike notUsualSessions
                List<User> teacherList = SessionTeacherLocalServiceUtil.getTeachers(cdtSession.getSessionId());
                JSONArray teacherArray = new JSONArray();

                for (User teacher : teacherList) {
                    JSONObject jsonTeacher = new JSONObject();
                    jsonTeacher.put(JSONConstants.TEACHER_ID, teacher.getUserId());
                    jsonTeacher.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
                    jsonTeacher.put(JSONConstants.LAST_NAME, teacher.getLastName());

                    teacherArray.put(jsonTeacher);
                }
                cdtSessionJson.put(JSONConstants.TEACHERS, teacherArray);

                // Color
                cdtSessionJson.put(JSONConstants.COLOR, CDTSessionLocalServiceUtil.getSessionColor(cdtSession.getSessionId(), user.getUserId()));

                jsonSessions.put(cdtSessionJson);
            }

            result.put(JSONConstants.SESSIONS, jsonSessions);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching sessions for student " + studentId + ", class "+ classId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "register-student", method = "POST")
    public JSONObject registerStudent(long studentId, long schoollifeSessionId, String comment, String replayTestSubject, boolean notifyParents) {
        JSONObject result = new JSONObject();

        try {
            User teacher = getGuestOrUser();
            boolean success = SessionStudentLocalServiceUtil.registerStudentToSession(teacher.getUserId(), studentId, schoollifeSessionId, comment, replayTestSubject, notifyParents);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            if (session.getType() == SchoollifeConstants.TYPE_RETENUE) {
                NotificationUtil.notifyRetenueRegistration(teacher.getUserId(), studentId, schoollifeSessionId, comment, notifyParents);
                NotificationLocalServiceUtil.addStudentAndParentsNotification(studentId, schoollifeSessionId);

            } else if (session.getType() == SchoollifeConstants.TYPE_TRAVAUX) {
                NotificationUtil.notifyTravauxRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
                NotificationLocalServiceUtil.addStudentAndParentsNotification(studentId, schoollifeSessionId);

            } else if (session.getType() == SchoollifeConstants.TYPE_ETUDE) {
                NotificationUtil.notifyEtudeRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
                // TODO : clarify
                // SchoollifeNotificationLocalServiceUtil.addStudentAndParentsNotification(studentId, schoollifeSessionId);

            } else if (session.getType() == SchoollifeConstants.TYPE_DEPANNAGE) {
                NotificationUtil.notifyDepannageRegistration(teacher.getUserId(), studentId, schoollifeSessionId, notifyParents);
                NotificationLocalServiceUtil.addStudentAndParentsNotification(studentId, schoollifeSessionId);
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

        User agent;
        try {
            agent = getGuestOrUser();

            if (agent.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            } else if (!RoleUtilsLocalServiceUtil.isPersonal(agent) && !RoleUtilsLocalServiceUtil.isTeacher(agent)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
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
                    boolean registered = SessionStudentLocalServiceUtil.registerStudentToSession(agent.getUserId(),
                            student.getUserId(), schoollifeSessionId, comment, replayTestSubject, notifyParents);

                    if (registered) {
                        ++ nbRegistered;
                        NotificationUtil.notifyEtudeRegistration(agent.getUserId(), student.getUserId(), schoollifeSessionId, notifyParents);
                        // TODO : clarify
                        // SchoollifeNotificationLocalServiceUtil.addStudentAndParentsNotification(student.getUserId(), schoollifeSessionId);
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

        try {
            JSONArray studentsPresenceJson = new JSONArray(studentsPresence);
            for (int i = 0 ; i < studentsPresenceJson.length() ; i++) {
                JSONObject studentJson = studentsPresenceJson.getJSONObject(i);
                long studentId = studentJson.getLong(JSONConstants.STUDENT_ID);
                boolean isPresent = studentJson.getBoolean(JSONConstants.IS_PRESENT);
                SessionStudentLocalServiceUtil.markStudentPresent(schoollifeSessionId, studentId, isPresent);
            }

            // Set roll called
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            session.setRollCalled(true);
            SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(session);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error marking student absence for schoollifeSessionId " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
