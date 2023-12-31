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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.base.UserUtilsLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.UserUtils",
        service = AopService.class
)
public class UserUtilsLocalServiceImpl extends UserUtilsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserUtilsLocalServiceImpl.class);

    private static final Random rand;

    static {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserPicture (User user) throws PortalException {
        if (user.getPortraitId() != 0) {
            return UserConstants.getPortraitURL(
                    PortalUtil.getPathImage(),
                    user.isMale(),
                    user.getPortraitId(),
                    user.getUserUuid()
            );
        } else {
            return ""; // To avoid liferay to return liferay's default picture
        }
    }

    public List<Long> getUserGroupIds (long userId) {
        List<Long> groupIds = new ArrayList<>();

        List<Group> userCommunities = CommunityInfosLocalServiceUtil.getUserCommunities(userId, false, true);
        for (Group userCommunity : userCommunities) {
            groupIds.add(userCommunity.getGroupId());
        }

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.SCHOOL_TYPE);
        types.add(OrgConstants.CLASS_TYPE);
        types.add(OrgConstants.COURS_TYPE);
        types.add(OrgConstants.VOLEE_TYPE);
        types.add(OrgConstants.SUBJECT_TYPE);

        List<Organization> userOrgs = UserOrgsLocalServiceUtil.getUserOrganizations(userId, types, false, OrgConstants.ALL_SCHOOLS_ID);
        for (Organization userOrg : userOrgs) {
            groupIds.add(userOrg.getGroupId());
        }

        // Add doyen classes
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            if (RoleUtilsLocalServiceUtil.isDoyen(user)) {
                List<Organization> affectedClasses = UserOrgsLocalServiceUtil.getAffectedClasses(user, RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());
                for (Organization affectedClass : affectedClasses) {
                    groupIds.add(affectedClass.getGroupId());
                }
            }
        } catch (Exception e) {
            // Nothing
        }
        return groupIds;
    }


    public List<User> getUserTeachers(User user) {
        List<User> teachers = new ArrayList<>();
        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            try {
                // Loop over user's classes
                List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClassesAndCours(user, false);
                List<Long> orgIds = new ArrayList<>();
                for (Organization userClass : userClasses) {
                    orgIds.add(userClass.getOrganizationId());
                }
                List<Long> roleIds = new ArrayList<>();
                roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
                teachers = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            } catch (Exception e) {
                logger.error("Error fetching teachers for user " + user.getUserId());
            }
        }
        return teachers;
    }

    public List<User> getUserDoyens(User user) {
        List<User> doyens = new ArrayList<>();

        // Loop over user's classes
        List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false);
        for (Organization userClass : userClasses) {
            try {
                List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
                        userClass.getGroupId(), RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());

                if (userGroupRoles != null) {
                    for (UserGroupRole userGroupRole : userGroupRoles) {
                        doyens.add(UserLocalServiceUtil.getUser(userGroupRole.getUserId()));
                    }
                }
            } catch (Exception e) {
                logger.error("Error fetching doyen for user " + user.getFullName() + " in org " + userClass.getName(), e);
            }
        }

        return doyens;
    }

    public List<User> getUserPsychologues(User user) {
        List<User> psychologues = new ArrayList<>();

        // Loop over user's classes
        List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false);
        for (Organization userClass : userClasses) {

            try {
                List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
                        userClass.getGroupId(), RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId());

                if (userGroupRoles != null) {
                    for (UserGroupRole userGroupRole : userGroupRoles) {
                        psychologues.add(UserLocalServiceUtil.getUser(userGroupRole.getUserId()));
                    }
                }
            } catch (Exception e) {
                logger.error("Error fetching psychologue for user " + user.getFullName() + " in org " + userClass.getName(), e);
            }
        }

        return psychologues;
    }

    public List<User> getUserConseillersSociaux(User user) {
        List<User> conseillers = new ArrayList<>();

        // Loop over user's classes
        List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false);
        for (Organization userClass : userClasses) {

            try {
                List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
                        userClass.getGroupId(), RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId());

                if (userGroupRoles != null) {
                    for (UserGroupRole userGroupRole : userGroupRoles) {
                        conseillers.add(UserLocalServiceUtil.getUser(userGroupRole.getUserId()));
                    }
                }
            } catch (Exception e) {
                logger.error("Error fetching doyens for user " + user.getFullName() + " in org " + userClass.getName(), e);
            }
        }

        return conseillers;
    }

    public List<User> getStudentMainTeachers(User student) {
        List<User> mainTeachers = new ArrayList<>();

        try {
            List<Organization> studentClasses = UserOrgsLocalServiceUtil.getUserClasses(student, false);

            if (studentClasses != null) {
                for (Organization studentClass : studentClasses) {
                    List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
                            studentClass.getGroupId(), RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId());

                    if (userGroupRoles != null) {
                        for (UserGroupRole userGroupRole : userGroupRoles) {
                            mainTeachers.add(UserLocalServiceUtil.getUser(userGroupRole.getUserId()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching main teacher for student " + student.getFullName(), e);
        }

        return mainTeachers;
    }

    /**
     * This method a list a user based on a list of userId
     * @return List<User> the users object , empty array in case of error
     */
    public List<User> getUserListFromUserIdList(List<Long> userIds) {
        return userUtilsFinder.findUsersFromIdList(userIds);
    }

    public String generateLogin(String lastName, String firstName) {

        String p = quoteString(firstName);
        String n = quoteString(lastName);

        if (n.length() > 5) {
            n = n.substring(0, 5);
        }

        String preLogin;

        if (p.length() > 0) {
            char firstLetter = p.toCharArray()[0];
            preLogin = firstLetter + n;
        } else {
            preLogin = n;
        }

        preLogin = preLogin.toLowerCase();
        String finalLogin = preLogin;

        boolean found;

        while (true) {
            User existingUser = null;
            try {
                existingUser = UserLocalServiceUtil.getUserByScreenName(PortalUtil.getDefaultCompanyId(), finalLogin);
            } catch (Exception e) {
                logger.debug(e);
            }

            found = existingUser != null;
            if (!found) {
                break;
            }
            finalLogin = preLogin + rand.nextInt(99);
        }

        logger.info("Generated login "+finalLogin);
        return finalLogin;
    }

    private String quoteString(final String s){
        StringBuilder res = new StringBuilder();
        final char[] chars = s.toCharArray();
        for (final char c : chars) {
            if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
                res.append(c);
            }
        }
        return res.toString();
    }

    // Update the password in database
    // Returns empty string in case of success, else the error message
    public String updateUserPassword(User user, String newPassword, String confirmPassword, boolean resetPassword) {
        ResourceBundle messages = ResourceBundle.getBundle("content.Language", user.getLocale());

        try {
            String errorMsg =  checkPasswordAgainstPolicy(newPassword, messages);

            if (errorMsg.isBlank()) {
                // DB update -> performs all validation checks
                UserLocalServiceUtil.updatePassword(user.getUserId(), newPassword, confirmPassword, resetPassword);
                logger.info("Password updated in DB for user " + user.getFullName());

                return "";
            } else {
                return errorMsg;
            }
        } catch (UserPasswordException.MustNotBeTrivial e) { // PASSWORD_CONTAINS_TRIVIAL_WORDS:
            return messages.getString("mdp-trop-trivial");
        } catch (UserPasswordException.MustComplyWithModelListeners e) { // PASSWORD_INVALID
            return messages.getString("mdp-non-valide");
        } catch (UserPasswordException.MustBeLonger e) { //PASSWORD_LENGTH:
            return messages.getString("mdp-trop-court");
        } catch (UserPasswordException.MustNotBeChanged e) { // PASSWORD_NOT_CHANGEABLE:
            return messages.getString("mdp-non-modifiable");
        } catch (UserPasswordException.MustNotBeEqualToCurrent e) { //PASSWORD_SAME_AS_CURRENT:
            return messages.getString("mdp-identique-ancien");
        } catch (UserPasswordException.MustNotBeChangedYet e) { // PASSWORD_TOO_YOUNG:
            return messages.getString("mdp-ne-peut-etre-changer");
        } catch (UserPasswordException.MustMatch e) { // PASSWORDS_DO_NOT_MATCH:
            return messages.getString("mdp-confirmation-non-valide");
        } catch (Exception e) {
            logger.error("Error while changing user's password for userId " + user.getUserId(), e);
        }

        return "Error";
    }

    /**
     * Purges all expired users
     */
    public boolean purgeExpiredUsers () {
        logger.info("Start purgeExpiredUsers");

        int count = 0;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DATE, -90);
        Date threeMonthInPast = cal.getTime();

        // Get non-manual users desactivated since more than 3 months
        try {
            List<User> allUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            for (User user : allUsers) {
                try {
                    UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
                    if (userProperties.getManualAccount()) {
                        continue;
                    }
                    if (!user.isActive() && userProperties.getLastSynchroDate() != null
                            && userProperties.getLastSynchroDate().before(threeMonthInPast)) {
                        logger.info("Purging user account " + user.getFullName() + " - " + user.getUserId());
                        UserLocalServiceUtil.deleteUser(user);
                        count++;
                    }
                } catch (Exception e) {
                    logger.error("An error happened when purging deactivated users, on user " + user.getFullName());
                }
            }
        } catch (Exception e) {
            logger.error("Error in purging obsolete users", e);
        }
        logger.info("END user purge : deleted "+count+ " users");

        return true;
    }

    private String checkPasswordAgainstPolicy(String password, ResourceBundle messages) throws PortalException {
        String errorMessage = messages.getString("mdp-exigences");
        int nbError = 0;
        PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(PortalUtil.getDefaultCompanyId());

        // Check minimum password length
        int minPasswordLength = passwordPolicy.getMinLength();
        if (password.length() < minPasswordLength) {
            ++nbError;
            errorMessage += "<br/>" + messages.getString("mdp-taille-requise").replace("{n}", String.valueOf(minPasswordLength));
        }

        // Check lowercase requirements
        int minLowerCase = passwordPolicy.getMinLowerCase();
        if (minLowerCase > 0 && countLowerCase(password) < minLowerCase) {
            ++nbError;
            errorMessage += "<br/>" + messages.getString("mdp-minuscule-requise").replace("{n}", String.valueOf(minLowerCase));
        }

        // Check uppercase requirements
        int minUpperCase = passwordPolicy.getMinUpperCase();
        if (minUpperCase > 0 && countUpperCase(password) < minUpperCase) {
            ++nbError;
            errorMessage += "<br/>" + messages.getString("mdp-majuscule-requise").replace("{n}", String.valueOf(minUpperCase));
        }

        // Check digit requirements
        int minDigits = passwordPolicy.getMinNumbers();
        if (minDigits > 0 && countDigits(password) < minDigits) {
            ++nbError;
            errorMessage += "<br/>" + messages.getString("mdp-nombre-requis").replace("{n}", String.valueOf(minDigits));
        }

        // Check special character requirements
        int minSymbols = passwordPolicy.getMinSymbols();
        if (minSymbols > 0 && countSymbols(password) < minSymbols) {
            ++nbError;
            errorMessage += "<br/>" + messages.getString("mdp-symbole-requis").replace("{n}", String.valueOf(minSymbols));
        }

        if (nbError > 0) {
            return errorMessage;
        }

        return StringPool.BLANK;
    }

    private int countLowerCase(String password) {
        // Count the number of lowercase characters in the password
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }

    private int countUpperCase(String password) {
        // Count the number of uppercase characters in the password
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    private int countDigits(String password) {
        // Count the number of digits in the password
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private int countSymbols(String password) {
        // Count the number of special characters in the password
        int count = 0;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public JSONArray convertUsersToJson(List<User> userList) {

        JSONArray userListAsJSON = new JSONArray();
        for (User aUser : userList) {
            JSONObject aUserAsJson = convertUserToJson(aUser);
            userListAsJSON.put(aUserAsJson);
        }

        return userListAsJSON;
    }

    public JSONObject convertUserToJson(User user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put(JSONConstants.USER_ID, user.getUserId());
        jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
        jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
        return jsonUser;
    }

}
