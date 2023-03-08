package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.base.SchoolAdminLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

public class SchoolAdminLocalServiceImpl extends SchoolAdminLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SchoolAdminLocalServiceImpl.class);

    public List<User> getSchoolAdmins (long schoolId) {
        List<User> schoolAdmins = new ArrayList<>();

        try {
            Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
            long schoolAdminRoleId = RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId();
            List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(school.getGroupId(), schoolAdminRoleId);

            for (UserGroupRole userGroupRole : userGroupRoles) {
                User admin = UserLocalServiceUtil.getUser(userGroupRole.getUserId());
                schoolAdmins.add(admin);
            }
        } catch (Exception e) {
            logger.error("Error while fetching school admins for schoolId " + schoolId, e);
        }

        return schoolAdmins;
    }

    public void addSchoolAdmin(long schoolId, long userId) throws PortalException, SystemException {
        Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
        long schoolAdminRoleId = RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId();

        User user = UserLocalServiceUtil.getUser(userId);
        boolean isSchoolAdmin = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, school.getGroupId(), schoolAdminRoleId);

        // If user is not already school admin
        // OR if he is not an ENT admin
        if (!isSchoolAdmin && !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            long[] userIdTab = new long[1];
            userIdTab[0] = userId;

            logger.info("Add user " + userId + " as admin for schoolId " + school.getOrganizationId());
            UserGroupRoleLocalServiceUtil.addUserGroupRoles(userIdTab, school.getGroupId(), schoolAdminRoleId);

            // Add classic role
            UserLocalServiceUtil.addRoleUsers(schoolAdminRoleId, userIdTab);
        }
    }

    public void removeSchoolAdmin(long schoolId, long userId) throws PortalException, SystemException {
        Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
        long schoolAdminRoleId = RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId();

        User user = UserLocalServiceUtil.getUser(userId);
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user)) {

            long[] userIdTab = new long[1];
            userIdTab[0] = userId;
            logger.info("Remove user " + userId + " as admin for schoolId " + school.getOrganizationId());
            UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(userIdTab, school.getGroupId(), schoolAdminRoleId);
            UserLocalServiceUtil.deleteRoleUser(schoolAdminRoleId, userId);
        } else {
            logger.info("Do not remove " + userId + " from admins for schoolId " + school.getOrganizationId());
        }
    }

}
