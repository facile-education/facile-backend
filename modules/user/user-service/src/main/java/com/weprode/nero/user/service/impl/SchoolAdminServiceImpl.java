package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
import com.weprode.nero.user.service.SchoolAdminLocalServiceUtil;
import com.weprode.nero.user.service.SchoolAdminService;
import com.weprode.nero.user.service.base.SchoolAdminServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=SchoolAdmin"
        },
        service = SchoolAdminService.class
)
public class SchoolAdminServiceImpl extends SchoolAdminServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SchoolAdminServiceImpl.class);

    @JSONWebService(value = "get-school-delegates", method = "GET")
    public JSONObject getSchoolDelegates(long schoolId) {
        JSONObject result = new JSONObject();

        JSONArray jsonAdmins = new JSONArray();
        try {
            User user = getGuestOrUser();
            logger.info("User " + user.getUserId() + " fetches all admins and delegates for school " + schoolId);
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
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
            List<User> allAdmins = new ArrayList<>(SchoolAdminLocalServiceUtil.getSchoolAdmins(schoolId));

            List<User> schoolDelegates = NewsAdminLocalServiceUtil.getSchoolDelegates(schoolId);
            // Avoid duplicates
            for (User schoolDelegate : schoolDelegates) {
                if (!allAdmins.contains(schoolDelegate)) {
                    allAdmins.add(schoolDelegate);
                }
            }
            
            for (User admin : allAdmins) {
                JSONObject adminJSON = new JSONObject();
                adminJSON.put(JSONConstants.USER_ID, admin.getUserId());
                adminJSON.put(JSONConstants.LAST_NAME, admin.getLastName());
                adminJSON.put(JSONConstants.FIRST_NAME, admin.getFirstName());
                adminJSON.put(JSONConstants.DISPLAY_NAME, admin.getFullName());
                adminJSON.put(JSONConstants.IS_DIRECTION, RoleUtilsLocalServiceUtil.isDirectionMember(admin));
                adminJSON.put(JSONConstants.IS_SCHOOL_ADMIN, RoleUtilsLocalServiceUtil.isSchoolAdmin(admin, schoolId));
                adminJSON.put(JSONConstants.IS_NEWS_DELEGATE, NewsAdminLocalServiceUtil.isUserDelegate(admin));
                jsonAdmins.put(adminJSON);
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.ADMINS, jsonAdmins);
        } catch (Exception e) {
            logger.error("Error while fetching school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "get-delegation-candidates", method = "GET")
    public JSONObject getDelegationCandidates(long schoolId, String filter) {
        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getUserId() + " fetches all delegation candidates for school " + schoolId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
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
            // Returned list of the school delegation candidates
            // minus the existing school delegates
            // minus the school's admins
            List<User> delegationCandidates = NewsAdminLocalServiceUtil.getSchoolDelegationCandidates(schoolId, filter);
            List<User> schoolDelegates = NewsAdminLocalServiceUtil.getSchoolDelegates(schoolId);
            List<User> schoolAdmins = SchoolAdminLocalServiceUtil.getSchoolAdmins(schoolId);

            JSONArray jsonCandidates = new JSONArray();
            for (User candidate : delegationCandidates) {
                if (!schoolDelegates.contains(candidate) && !schoolAdmins.contains(candidate)) {
                    JSONObject jsonCandidate = new JSONObject();
                    jsonCandidate.put(JSONConstants.USER_ID, candidate.getUserId());
                    jsonCandidate.put(JSONConstants.LAST_NAME, candidate.getLastName());
                    jsonCandidate.put(JSONConstants.FIRST_NAME, candidate.getFirstName());
                    jsonCandidate.put(JSONConstants.FULL_NAME, candidate.getFullName());
                    jsonCandidates.put(jsonCandidate);
                }
            }
            result.put(JSONConstants.CANDIDATES, jsonCandidates);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "add-school-admin", method = "GET")
    public JSONObject addSchoolAdmin(long userId, long schoolId) {
        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getUserId() + " adds user " + userId + " as school admin for school " + schoolId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
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
            SchoolAdminLocalServiceUtil.addSchoolAdmin(schoolId, userId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }
    
    @JSONWebService(value = "remove-school-admin", method = "GET")
    public JSONObject removeSchoolAdmin(long userId, long schoolId) {
        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getUserId() + " removes user " + userId + " from school admin for school " + schoolId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
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
            SchoolAdminLocalServiceUtil.removeSchoolAdmin(schoolId, userId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while fetching school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

}
