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

package com.weprode.facile.user.service.impl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.comparator.UserLastNameCaseInsensitiveComparator;
import com.weprode.facile.user.service.UserManagementLocalServiceUtil;
import com.weprode.facile.user.service.UserManagementService;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import com.weprode.facile.user.service.base.UserManagementServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
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
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " creates manual user " + lastName + " " + firstName + " " + email);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            // Check if email is already used
            User existingUser = null;
            try {
                existingUser = UserLocalServiceUtil.getUserByEmailAddress(user.getCompanyId(), email);
            } catch (Exception e) {
                logger.debug(e);
            }

            if (existingUser != null) {
                result.put(JSONConstants.SUCCESS, false);
                result.put(JSONConstants.ERROR_CODE, JSONConstants.EMAIL);
                return result;
            }

            // Check that the given roleId is allowed (+ parents that are not in the list)
            boolean isRoleExisting = RoleUtilsLocalServiceUtil.getParentRole().getRoleId() == roleId;
            for (Role role : RoleUtilsLocalServiceUtil.getAvailableRolesForLocalUser()) {
                if (role.getRoleId() == roleId) {
                    isRoleExisting = true;
                }
            }
            if (!isRoleExisting) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " creates manual user " + lastName + " " + firstName + " " + email);
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
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
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " edits manual user " + lastName + " " + firstName + " " + email);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            // Check that the given roleId is allowed (+ parents that are not in the list)
            boolean isRoleExisting = RoleUtilsLocalServiceUtil.getParentRole().getRoleId() == roleId;
            for (Role role : RoleUtilsLocalServiceUtil.getAvailableRolesForLocalUser()) {
                if (role.getRoleId() == roleId) {
                    isRoleExisting = true;
                }
            }
            if (!isRoleExisting) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " edits manual user " + lastName + " " + firstName + " " + email);
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            // Check if email is already used
            User modifiedUser = UserLocalServiceUtil.getUser(userId);
            if (!modifiedUser.getEmailAddress().equals(email)) {
                User existingUser = null;
                try {
                    existingUser = UserLocalServiceUtil.getUserByEmailAddress(user.getCompanyId(), email);
                } catch (NoSuchUserException e) {
                    // User does not exist with such email -> ok for edit
                }
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

            // For parents do not update roles and orgs
            if (!RoleUtilsLocalServiceUtil.isParent(modifiedUser)) {

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
                UserManagementLocalServiceUtil.synchronizeUserSchool(modifiedUser.getUserId(), school.getOrganizationId());

            }

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
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes manual user");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            User manualUser = UserLocalServiceUtil.getUser(userId);
            if (!manualUser.getAgreedToTermsOfUse()) {
                logger.info("User " + user.getFullName() + " deletes manual user " + manualUser.getFullName() + " : he has never agreed terms of use, so purging it");
                UserLocalServiceUtil.deleteUser(manualUser);
            } else {
                User targetUser = UserLocalServiceUtil.getUser(userId);
                targetUser.setStatus(WorkflowConstants.STATUS_INACTIVE);
                UserLocalServiceUtil.updateUser(targetUser);
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
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets manual users");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            List<Long> organizationIds = new ArrayList<>();
            organizationIds.add(schoolId);

            OrderByComparator<User> obc = new UserLastNameCaseInsensitiveComparator(true);

            List<User> localUsers = UserSearchLocalServiceUtil.searchUsers(search, organizationIds, null, null, null, true, start, limit, obc);
            JSONArray jsonUsers = new JSONArray();
            for (User localUser : localUsers) {
                JSONObject jsonUser = convertUser(localUser);
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

    @JSONWebService(value = "update-password-by-manager", method = "GET")
    public JSONObject updatePasswordByManager(long userId, String password) {
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
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " updates password for user " + userId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
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
                result.put(JSONConstants.ERROR, errorMessage);
            }

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-password-after-reinit-by-manager", method = "GET")
    public JSONObject updatePasswordAfterReinitByManager(String password, String confirmPassword) {
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

        try {
            if (!password.isEmpty() && password.equals(confirmPassword)) {
                String errorMessage = UserUtilsLocalServiceUtil.updateUserPassword(user, password, password, false);
                if (errorMessage.equals("")) {
                    result.put(JSONConstants.SUCCESS, true);
                } else {
                    result.put(JSONConstants.SUCCESS, false);
                    result.put(JSONConstants.ERROR, errorMessage);
                }
            } else {
                result.put(JSONConstants.SUCCESS, false);
            }

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    // This webservice is public because called by a non-authenticated user
    @JSONWebService(value = "update-forgotten-password", method = "POST")
    public JSONObject updateForgottenPassword(String password, String confirmPassword, String ticketKey) {
        JSONObject result = new JSONObject();

        try {
            logger.info("Password update for ticketKey " + ticketKey);
            if (!password.isEmpty() && password.equals(confirmPassword)) {
                // Check ticket
                Ticket ticket = null;
                try {
                    ticket = TicketLocalServiceUtil.getTicket(ticketKey);
                } catch (Exception e) {
                    logger.error("Ticket " + ticketKey + " does not exist -> error");
                }
                if (ticket == null) {
                    result.put(JSONConstants.SUCCESS, false);
                } else {
                    User user = UserLocalServiceUtil.getUser(ticket.getClassPK());
                    String errorMessage = UserUtilsLocalServiceUtil.updateUserPassword(user, password, password, false);
                    if (errorMessage.equals("")) {
                        // Delete ticket so that it cannot be used again
                        TicketLocalServiceUtil.deleteTicket(ticket);
                        result.put(JSONConstants.SUCCESS, true);
                    } else {
                        result.put(JSONConstants.SUCCESS, false);
                        result.put(JSONConstants.ERROR, errorMessage);
                    }
                }
            } else {
                result.put(JSONConstants.SUCCESS, false);
            }
        } catch (Exception e) {
            logger.error("Could not update password for ticketKey " + ticketKey, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }


}
