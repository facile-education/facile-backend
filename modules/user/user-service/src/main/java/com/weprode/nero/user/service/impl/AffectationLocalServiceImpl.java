package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.model.Affectation;
import com.weprode.nero.user.service.AffectationLocalServiceUtil;
import com.weprode.nero.user.service.UserManagementLocalServiceUtil;
import com.weprode.nero.user.service.base.AffectationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AffectationLocalServiceImpl extends AffectationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AffectationLocalServiceImpl.class);

    public List<User> getAffectedUsers(long schoolId, String filter) {
        List<User> users = new ArrayList<>();
        
        try {
            List<Affectation> schoolAffectations = affectationPersistence.findByschoolId(schoolId);
            for (Affectation affectation : schoolAffectations) {
                User affectedUser = UserLocalServiceUtil.getUser(affectation.getUserId());
                if (!users.contains(affectedUser) && (affectedUser.getLastName().toLowerCase().contains(filter.toLowerCase())
                        || affectedUser.getFirstName().toLowerCase().contains(filter.toLowerCase()))) {

                    users.add(affectedUser);
                }
            }

        } catch (Exception e) {
            logger.error("Error when fetching users affected to schoolId " + schoolId + " with filter " + filter, e);
        }
        
        return users;
    }

    public List<Affectation> getUserAffectations(long userId, long schoolId) {
        List<Affectation> affectations = new ArrayList<>();

        try {
            List<Affectation> userAffectations = affectationPersistence.findByuserId(userId);
            if (userAffectations != null) {
                for (Affectation userAffectation : userAffectations) {
                    if (schoolId == 0 || userAffectation.getSchoolId() == schoolId) {
                        affectations.add(userAffectation);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching affectations for user " + userId, e);
        }
        
        return affectations;
    }

    public boolean addUserAffectation(long userId, long orgId, long adminUserId, Date expirationDate) {
        try {
            OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(orgId);

            Affectation newAffectation = affectationPersistence.create(counterLocalService.increment());
            newAffectation.setUserId(userId);
            newAffectation.setOrgId(orgId);
            newAffectation.setType(orgDetails.getType());
            newAffectation.setSchoolId(orgDetails.getSchoolId());
            newAffectation.setAdminUserId(adminUserId);
            newAffectation.setAffectationDate(new Date());
            newAffectation.setExpirationDate(expirationDate);
            affectationPersistence.update(newAffectation);

            // Add the user to the organization
            if (!OrganizationLocalServiceUtil.hasUserOrganization(userId, orgId)) {
                UserLocalServiceUtil.addOrganizationUsers(orgId, new long[]{userId});
            }

            // If this is a school, add the user to the school-level orgs
            if (orgDetails.getType() == OrgConstants.SCHOOL_TYPE) {
                UserManagementLocalServiceUtil.synchronizeSchoolLevelOrganizations(userId, orgId);
            }

            return true;
        } catch (Exception e) {
            logger.error("Error adding new affectations for user " + userId + " to orgId " + orgId, e);
        }

        return false;
    }

    public List<Long> getUserAffectedOrgs(long userId) {
        List<Long> orgIds = new ArrayList<>();

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(userId, 0);

            for (Affectation userAffectation : userAffectations) {
                orgIds.add(userAffectation.getOrgId());
                // If this is a school, add the school-level orgs
                OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(userAffectation.getOrgId());
                if (orgDetails.getType() == OrgConstants.SCHOOL_TYPE) {
                    if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                        orgIds.add(OrgUtilsLocalServiceUtil.getSchoolTeachersOrganization(userAffectation.getOrgId()).getOrganizationId());
                    }
                    if (RoleUtilsLocalServiceUtil.isPersonal(user)) {
                        orgIds.add(OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(userAffectation.getOrgId()).getOrganizationId());
                    }
                    if (RoleUtilsLocalServiceUtil.isPat(user)) {
                        orgIds.add(OrgUtilsLocalServiceUtil.getSchoolPATsOrganization(userAffectation.getOrgId()).getOrganizationId());
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Error fetching affected for user " + userId, e);
        }
        return orgIds;
    }

    public boolean removeUserAffectation(long userId, long orgId) {
        try {
            List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(userId, 0);

            for (Affectation userAffectation : userAffectations) {
                if (userAffectation.getOrgId() == orgId) {
                    affectationPersistence.remove(userAffectation.getAffectationId());
                }
            }

            // Remove user from organization
            UserLocalServiceUtil.unsetOrganizationUsers(orgId, new long[]{userId});

            // If this is a school, remove user from the school-level organizations
            OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(orgId);
            if (orgDetails.getType() == OrgConstants.SCHOOL_TYPE) {
                // School teachers
                User user = UserLocalServiceUtil.getUser(userId);
                if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    Organization teacherSchoolLevelOrg = OrgUtilsLocalServiceUtil.getSchoolTeachersOrganization(orgId);
                    if (!UserLocalServiceUtil.hasOrganizationUser(teacherSchoolLevelOrg.getOrganizationId(), user.getUserId()) ) {
                        UserLocalServiceUtil.addOrganizationUsers(teacherSchoolLevelOrg.getOrganizationId(), new long[]{user.getUserId()});
                    }
                }

                // School personals
                if (RoleUtilsLocalServiceUtil.isPersonal(user)) {
                    Organization personalSchoolLevelOrg = OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(orgId);
                    if (!UserLocalServiceUtil.hasOrganizationUser(personalSchoolLevelOrg.getOrganizationId(), user.getUserId()) ) {
                        UserLocalServiceUtil.addOrganizationUsers(personalSchoolLevelOrg.getOrganizationId(), new long[]{user.getUserId()});
                    }
                }

                // School PATs
                if (RoleUtilsLocalServiceUtil.isPat(user)) {
                    Organization patSchoolLevelOrg = OrgUtilsLocalServiceUtil.getSchoolPATsOrganization(orgId);
                    if (!UserLocalServiceUtil.hasOrganizationUser(patSchoolLevelOrg.getOrganizationId(), user.getUserId()) ) {
                        UserLocalServiceUtil.addOrganizationUsers(patSchoolLevelOrg.getOrganizationId(), new long[]{user.getUserId()});
                    }
                }
            }

            return true;
        } catch (Exception e) {
            logger.error("Error fetching affected for user " + userId, e);
        }

        return false;
    }
}
