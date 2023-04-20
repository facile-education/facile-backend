package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchService;
import com.weprode.nero.user.service.base.UserSearchServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=UserSearch"
        },
        service = UserSearchService.class
)
public class UserSearchServiceImpl extends UserSearchServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserSearchServiceImpl.class);

    @JSONWebService(value = "get-school-student-teacher-list", method = "GET")
    public JSONObject getSchoolStudentTeacherList (long schoolId, String search) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray jsonUsers = JSONFactoryUtil.createJSONArray();
        User currentUser;
        try {
            currentUser = getGuestOrUser();

            if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            } else if (!RoleUtilsLocalServiceUtil.isAdministrator(currentUser) &&
                    !RoleUtilsLocalServiceUtil.isPersonal(currentUser) &&
                    !RoleUtilsLocalServiceUtil.isTeacher(currentUser)) {
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
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);

            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());

            List<User> users = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null,
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            for (User user : users) {
                JSONObject jsonUser = JSONFactoryUtil.createJSONObject();
                jsonUser.put(JSONConstants.USER_ID, user.getUserId());

                if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    jsonUser.put(JSONConstants.IS_TEACHER, true);
                } else if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                    jsonUser.put(JSONConstants.IS_STUDENT, true);
                    jsonUser.put(JSONConstants.CLASS_NAME, UserOrgsLocalServiceUtil.getStudentClassName(user));
                }
                jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
                jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
                jsonUser.put(JSONConstants.FULL_NAME, user.getFullName());
                jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(user));

                jsonUsers.put(jsonUser);
            }
            result.put(JSONConstants.USERS, jsonUsers);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching school teachers and students for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-school-members", method = "GET")
    public JSONObject getSchoolMembers (long schoolId, String search) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray jsonUsers = JSONFactoryUtil.createJSONArray();
        User currentUser;
        try {
            currentUser = getGuestOrUser();

            if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            } else if (!RoleUtilsLocalServiceUtil.isPersonal(currentUser) && !RoleUtilsLocalServiceUtil.isTeacher(currentUser)) {
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
            List<Long> orgIds = new ArrayList<>();
            if (schoolId != OrgConstants.ALL_SCHOOLS_ID) {
                orgIds.add(schoolId);
            }

            List<User> users = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, null, null, 
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            for (User user : users) {
                JSONObject jsonUser = JSONFactoryUtil.createJSONObject();
                jsonUser.put(JSONConstants.USER_ID, user.getUserId());
                jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
                jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
                jsonUser.put(JSONConstants.FULL_NAME, user.getFullName());
                jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(user));

                jsonUsers.put(jsonUser);
            }
            result.put(JSONConstants.USERS, jsonUsers);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching school teachers and students for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-school-students", method = "GET")
    public JSONObject getSchoolStudents(String search, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray jsonStudents = JSONFactoryUtil.createJSONArray();
        try {
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);

            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());

            List<User> students = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, 
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            for (User student : students) {
                JSONObject jsonStudent = JSONFactoryUtil.createJSONObject();
                // TODO remove studentId
                jsonStudent.put(JSONConstants.STUDENT_ID, student.getUserId());
                jsonStudent.put(JSONConstants.USER_ID, student.getUserId());
                jsonStudent.put(JSONConstants.FIRST_NAME, student.getFirstName());
                jsonStudent.put(JSONConstants.LAST_NAME, student.getLastName());
                jsonStudent.put(JSONConstants.CLASS_NAME, UserOrgsLocalServiceUtil.getStudentClassName(student));

                jsonStudents.put(jsonStudent);
            }
            result.put(JSONConstants.STUDENTS, jsonStudents);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching school students for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-school-teachers", method = "GET")
    public JSONObject getSchoolTeachers(long schoolId, String search) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
            List<User> schoolTeachers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, 
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            JSONArray jsonTeachers = JSONFactoryUtil.createJSONArray();
            for (User teacher : schoolTeachers) {
                JSONObject jsonUser = JSONFactoryUtil.createJSONObject();
                jsonUser.put(JSONConstants.USER_ID, teacher.getUserId());
                jsonUser.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
                jsonUser.put(JSONConstants.LAST_NAME, teacher.getLastName());
                jsonUser.put(JSONConstants.FULL_NAME, teacher.getLastName() + " " + teacher.getFirstName());
                jsonTeachers.put(jsonUser);
            }

            result.put(JSONConstants.TEACHERS, jsonTeachers);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching school teachers for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    // Returns all watchers (teacher/secretaires/directeur) for schoollife slot creation/edition
    @JSONWebService(value = "get-schoolife-agents", method = "GET")
    public JSONObject getSchoollifeAgents(String search, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        JSONArray members = JSONFactoryUtil.createJSONArray();
        try {
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId());
            List<User> schoolAgents = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, 
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            for (User teacher : schoolAgents) {
                JSONObject jsonTeacher = JSONFactoryUtil.createJSONObject();
                // TODO remove teacherId
                jsonTeacher.put(JSONConstants.TEACHER_ID, teacher.getUserId());
                jsonTeacher.put(JSONConstants.USER_ID, teacher.getUserId());
                jsonTeacher.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
                jsonTeacher.put(JSONConstants.LAST_NAME, teacher.getLastName());

                members.put(jsonTeacher);
            }
            result.put(JSONConstants.MEMBERS, members);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching schoollife agents for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

}
