package com.weprode.nero.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.base.UserUtilsLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.UserUtils",
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

    public List<User> getUserTeachers(User user) {
        List<User> teachers = new ArrayList<User>();
        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            try {
                // Loop over user's classes
                List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClassesAndCours(user, false);
                List<Long> orgIds = new ArrayList<Long>();
                for (Organization userClass : userClasses) {
                    orgIds.add(userClass.getOrganizationId());
                }
                List<Long> roleIds = new ArrayList<Long>();
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
            List<Organization> studentclasses = UserOrgsLocalServiceUtil.getUserClasses(student, false);

            if (studentclasses != null && !studentclasses.isEmpty()) {
                Organization studentclass = studentclasses.get(0);
                List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
                        studentclass.getGroupId(), RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId());

                if (userGroupRoles != null && !userGroupRoles.isEmpty()) {
                    mainTeachers.add(UserLocalServiceUtil.getUser(userGroupRoles.get(0).getUserId()));
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

        String preLogin = "";

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
            // DB update -> performs all validation checks
            UserLocalServiceUtil.updatePassword(user.getUserId(), newPassword, confirmPassword, resetPassword);
            logger.info("Password updated in DB for user "+user.getFullName());

            return "";
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
     * @return the list of purged userIds
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
                UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
                if (userProperties.getManualAccount()) {
                    continue;
                }
                if (!user.isActive() && userProperties.getLastSynchroDate() != null
                        && userProperties.getLastSynchroDate().before(threeMonthInPast)) {
                    logger.info("Purging user account " + user.getFullName() + " - " + user.getUserId());
                    UserLocalServiceUtil.deleteUser(user);
                    count ++;
                }
            }
        } catch (Exception e) {
            logger.error("Error in purging obsolete users", e);
        }
        logger.info("END user purge : deleted "+count+ " users");

        return true;
    }

}
