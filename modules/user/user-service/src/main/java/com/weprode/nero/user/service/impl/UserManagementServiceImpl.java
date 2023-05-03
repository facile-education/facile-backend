package com.weprode.nero.user.service.impl;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.comparator.UserLastNameCaseInsensitiveComparator;
import com.weprode.nero.user.service.UserManagementLocalServiceUtil;
import com.weprode.nero.user.service.UserManagementService;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import com.weprode.nero.user.service.base.UserManagementServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=UserManagement"
        },
        service = UserManagementService.class
)
public class UserManagementServiceImpl extends UserManagementServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserManagementServiceImpl.class);   

    @JSONWebService(value = "create-manual-user", method = "POST")
    public JSONObject createManualUser(String lastName, String firstName, String email, long roleId, long schoolId) {

        JSONObject result = new JSONObject();
        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            // Check if email is already used
            User existingUser = null;
            try {
                existingUser = UserLocalServiceUtil.getUserByEmailAddress(adminUser.getCompanyId(), email);
            } catch (Exception e) {
                logger.debug(e);
            }

            if (existingUser != null) {
                result.put(JSONConstants.SUCCESS, false);
                result.put(JSONConstants.ERROR_CODE, JSONConstants.EMAIL);
                return result;
            }

            User createdUser = UserManagementLocalServiceUtil.createManualUser(lastName, firstName, email, null, roleId, schoolId);
            JSONObject jsonUser = convertUser(createdUser);
            result.put(JSONConstants.USER, jsonUser);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while creating user with lastName="+lastName+", firstName="+firstName+" and email="+email, e);
        }
        
        return result;
    }

    @JSONWebService(value = "edit-manual-user", method = "POST")
    public JSONObject editManualUser(long userId, String lastName, String firstName, String email, long roleId, long schoolId) {
        JSONObject result = new JSONObject();
        
        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            // Check if email is already used
            User modifiedUser = UserLocalServiceUtil.getUser(userId);
            if (!modifiedUser.getEmailAddress().equals(email)) {
                User existingUser = UserLocalServiceUtil.getUserByEmailAddress(adminUser.getCompanyId(), email);
                if (existingUser != null) {
                    result.put(JSONConstants.SUCCESS, false);
                    result.put(JSONConstants.ERROR_CODE, JSONConstants.EMAIL);
                    return result;
                }
            }

            modifiedUser.setLastName(lastName);
            modifiedUser.setFirstName(firstName);
            modifiedUser.setEmailAddress(email);
            UserLocalServiceUtil.updateUser(modifiedUser);

            List<Role> oldRoles = RoleLocalServiceUtil.getUserRoles(userId);
            for (Role oldRole : oldRoles) {
                if (!oldRole.getName().equals("Power User") && !oldRole.getName().equals("User")) {
                    logger.info("Removing role " + oldRole.getName() + " to user " + modifiedUser.getFullName());
                    RoleLocalServiceUtil.unsetUserRoles(modifiedUser.getUserId(), new long[]{oldRole.getRoleId()});
                }
            }
            long[] roleIds = new long[1];
            roleIds[0] = roleId;
            RoleLocalServiceUtil.addUserRoles(modifiedUser.getUserId(), roleIds);

            List<Organization> existingOrgs = OrganizationLocalServiceUtil.getUserOrganizations(modifiedUser.getUserId());
            for (Organization existingOrg : existingOrgs) {
                logger.info("Removing user from org " + existingOrg.getName());
                UserLocalServiceUtil.unsetOrganizationUsers(existingOrg.getOrganizationId(), new long[]{modifiedUser.getUserId()});
            }
            Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
            logger.info("Adding user to school " + school.getName());
            UserManagementLocalServiceUtil.synchronizeSchoolLevelOrganizations(modifiedUser.getUserId(), school.getOrganizationId());

            JSONObject jsonUser = convertUser(modifiedUser);

            result.put(JSONConstants.USER, jsonUser);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while editing user " + userId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-manual-user", method = "GET")
    public JSONObject deleteManualUser(long userId) {
        JSONObject result = new JSONObject();
        
        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            User manualUser = UserLocalServiceUtil.getUser(userId);
            if (!manualUser.getAgreedToTermsOfUse()) {
                logger.info("User " + adminUser.getFullName() + " deletes manual user " + manualUser.getFullName() + " : he has never agreed terms of use, so purging it");
                UserLocalServiceUtil.deleteUser(manualUser);
            } else {
                User user = UserLocalServiceUtil.getUser(userId);
                user.setStatus(WorkflowConstants.STATUS_INACTIVE);
                UserLocalServiceUtil.updateUser(user);
            }

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while editing user " + userId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "get-manual-users", method = "GET")
    public JSONObject getManualUsers(long schoolId, String search, int start, int limit) {
        JSONObject result = new JSONObject();
        
        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
            logger.info("User " + user.getUserId() + " fetches all manual users for school " + schoolId);

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
            List<Long> organizationIds = new ArrayList<>();
            organizationIds.add(schoolId);

            OrderByComparator<User> obc = new UserLastNameCaseInsensitiveComparator(true);

            List<User> users = UserSearchLocalServiceUtil.searchUsers(search, organizationIds, null, null, null, true, start, limit, obc);
            JSONArray jsonUsers = new JSONArray();
            for (User user : users) {
                JSONObject jsonUser = convertUser(user);
                jsonUsers.put(jsonUser);
            }

            // Count the total number of users only on first page
            if (start == 0) {
                long nbTotalUsers = UserSearchLocalServiceUtil.countUsers(search, organizationIds, null, null, null, true);
                result.put(JSONConstants.NB_TOTAL_USERS, nbTotalUsers);
            }

            result.put(JSONConstants.USERS, jsonUsers);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching manual users for schoolId " + schoolId + " and search " + search, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    private JSONObject convertUser(User user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put(JSONConstants.USER_ID, user.getUserId());
        jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
        jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
        jsonUser.put(JSONConstants.EMAIL, user.getEmailAddress());
        jsonUser.put(JSONConstants.SCREEN_NAME, user.getScreenName());
        try {
            jsonUser.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(user));
            jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(user));
            jsonUser.put(JSONConstants.ROLE_ID, RoleUtilsLocalServiceUtil.getUserEntRoles(user).get(0).getRoleId());  // Return the first entRole we find

            // Manually users have only 1 school
            List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
            jsonUser.put(JSONConstants.SCHOOL_ID, userSchools.get(0).getOrganizationId());
            jsonUser.put(JSONConstants.SCHOOL_NAME, userSchools.get(0).getName());

        } catch (Exception e) {
            logger.error("Error converting manual userId " + user.getUserId(), e);
        }
        return jsonUser;
    }

    @JSONWebService(value = "update-password", method = "GET")
    public JSONObject updatePassword(long userId, String password) {
        JSONObject result = new JSONObject();

        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Update in DB
            // Ask password change at next user's connexion
            User targetUser = UserLocalServiceUtil.getUser(userId);
            String errorMessage = UserUtilsLocalServiceUtil.updateUserPassword(targetUser, password, password, true);
            if (errorMessage.equals("")) {
                result.put(JSONConstants.SUCCESS, true);
            } else {
                result.put(JSONConstants.SUCCESS, false);
                result.put("portal_message", errorMessage);
            }

            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

}
