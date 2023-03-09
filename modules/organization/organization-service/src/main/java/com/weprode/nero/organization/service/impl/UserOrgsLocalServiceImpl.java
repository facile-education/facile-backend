package com.weprode.nero.organization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgCiteScolaireLocalServiceUtil;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.organization.service.base.UserOrgsLocalServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.organization.model.UserOrgs",
        service = AopService.class
)
public class UserOrgsLocalServiceImpl extends UserOrgsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserOrgsLocalServiceImpl.class);

    public Organization getEtabRatachement(User user) {
        // TODO Preferences
//        try {
//            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
//            if (userProperties != null && userProperties.getEtabId() != 0) {
//                return OrganizationLocalServiceUtil.getOrganization(userProperties.getEtabId());
//            }
//
//            // Else: some parents do not have the ENTPersonStructRattach attribute
//            // Get rattach school from their first child
//            List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
//            if (children != null) {
//                for (User child : children) {
//                    UserProperties childProperties = UserPropertiesLocalServiceUtil.getUserProperties(child.getUserId());
//                    if (childProperties != null && childProperties.getEtabId() != 0) {
//                        return OrganizationLocalServiceUtil.getOrganization(childProperties.getEtabId());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Error fetching rattach school for user " + user.getFullName() + e.getMessage());
//        }

        return null;
    }


    public List<Organization> getUserClassesAndCours(User user, boolean withArchive) {
        return getUserClassesAndCours(user, withArchive, OrgConstants.ALL_SCHOOLS_ID);
    }

    public List<Organization> getUserClassesAndCours(User user, boolean withArchive, long schoolId) {
        List<Integer> roles = new ArrayList<>();

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.CLASS_TYPE);
        types.add(OrgConstants.COURS_TYPE);

        return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, withArchive, schoolId);
    }

    /**
     * Returns all classes and groups the user is member of, for a given school
     */
    public List<Organization> getUserCours(User user, boolean withArchive, long schoolId) {
        List<Integer> roles = new ArrayList<>();
        roles.add(OrgConstants.NO_ROLE);

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.COURS_TYPE);

        return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, withArchive, schoolId);
    }

    public List<Organization> getUserClasses(User user, boolean withArchive) {
        return getUserClasses(user, withArchive, OrgConstants.ALL_SCHOOLS_ID);
    }

    /**
     * Returns all classes and groups the user is member of
     */
    public List<Organization> getUserClasses(User user, boolean withArchive, long schoolId) {
        List<Integer> roles = new ArrayList<>();
        roles.add(OrgConstants.NO_ROLE);

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.CLASS_TYPE);

        return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, withArchive, schoolId);
    }

    public List<Organization> getUserVolees(User user, boolean withArchive, long schoolId) {
        List<Integer> roles = new ArrayList<>();
        roles.add(OrgConstants.NO_ROLE);

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.VOLEE_TYPE);

        return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, withArchive, schoolId);
    }

    public List<Organization> getUserSchools(User user) {
        List<Integer> roles = new ArrayList<>();

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.SCHOOL_TYPE);

        return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, false,
                OrgConstants.ALL_SCHOOLS_ID);
    }

    /**
     * Return all schools that can see a user (In admin services for example)
     */
    public List<Organization> getUserVisibilitySchools(User user) {
        List<Organization> schools;

        if (RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            schools = OrgUtilsLocalServiceUtil.getAllSchools();
        } else if (RoleUtilsLocalServiceUtil.isPersonal(user)) {
            schools = getAllUserSchoolsIncludingSchoolComplex(user);
        } else {
            schools = getUserSchools(user);
        }

        return schools;
    }

    /**
     * Returns the list of user's schools including school complex
     */
    public List<Organization> getAllUserSchoolsIncludingSchoolComplex(User user){
        List<Organization> schoolsWithSchoolarComplex = new ArrayList<>();

        try{
            List<Organization> userSchools = getUserSchools(user);

            for (Organization school : userSchools) {
                List<Organization> linkedSchools = OrgCiteScolaireLocalServiceUtil.getSchoolComplexSchools(school);
                for (Organization linkedSchool : linkedSchools) {
                    if (!schoolsWithSchoolarComplex.contains(linkedSchool)) {
                        schoolsWithSchoolarComplex.add(linkedSchool);
                    }
                }
            }
        }
        catch (Exception e){
            logger.error("Error while getting all schools for user " + user.getFullName(), e);
        }

        return schoolsWithSchoolarComplex;
    }

    /**
     * Returns all subject organizations the user is member of
     */
    public List<Organization> getUserSubjectOrganizations(User user, long schoolId, boolean withArchive) {
        List<Integer> roles = new ArrayList<>();
        roles.add(OrgConstants.NO_ROLE);

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.SUBJECT_TYPE);

        // Personals see all of these
        // Teachers see only the ones they belong to
        if (RoleUtilsLocalServiceUtil.isPersonal(user)) {
            return OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, roles, false);
        } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, roles, withArchive, schoolId);
        }

        return new ArrayList<>();
    }

    public String getStudentClassName(User student) {
        List<Organization> userClasses = getUserClasses(student, false);

        if (userClasses != null && userClasses.size() == 1) {
            return OrgUtilsLocalServiceUtil.formatOrgName(userClasses.get(0).getName(), false);
        }

        return "";
    }

    // Returns the organizations where the given user has the given role
    // Used for doyen, psychologue and conseiller social
    public List<Organization> getAffectedClasses(User user, long roleId) {
        List<Organization> orgs = new ArrayList<>();

        try {
            List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());
            if (userGroupRoles != null) {
                for (UserGroupRole userGroupRole : userGroupRoles) {
                    if (userGroupRole.getRoleId() == roleId) {
                        try {
                            Organization org = OrganizationLocalServiceUtil.getOrganization(
                                    GroupLocalServiceUtil.getGroup(userGroupRole.getGroupId()).getClassPK());

                            if (!OrgDetailsLocalServiceUtil.isArchived(org.getOrganizationId())) {
                                orgs.add(org);
                            }
                        } catch (Exception e) {
                            logger.error("Error fetching organization where user " + user.getFullName() +
                                    " has roleId " + roleId + " for groupId = " + userGroupRole.getGroupId());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while getting organizations where user " + user.getFullName() + " is doyen", e);
        }
        return orgs;
    }

    // Returns the organizations where the given user has one of the 3 roles doyen, psychologue or conseiller social
    public List<Organization> getRoleAffectedClasses(User user) {
        List<Organization> orgs = new ArrayList<>();

        try {
            List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());
            Role doyenRole = RoleUtilsLocalServiceUtil.getDoyenRole();
            Role psychologueRole = RoleUtilsLocalServiceUtil.getPsychologueRole();
            Role conseillerSocialRole = RoleUtilsLocalServiceUtil.getConseillerSocialRole();

            if (userGroupRoles != null) {
                for (UserGroupRole userGroupRole : userGroupRoles) {
                    if (userGroupRole.getRoleId() == doyenRole.getRoleId()
                            || userGroupRole.getRoleId() == psychologueRole.getRoleId()
                            || userGroupRole.getRoleId() == conseillerSocialRole.getRoleId()) {
                        try {
                            Organization org = OrganizationLocalServiceUtil.getOrganization(
                                    GroupLocalServiceUtil.getGroup(userGroupRole.getGroupId()).getClassPK());

                            if (!OrgDetailsLocalServiceUtil.isArchived(org.getOrganizationId())) {
                                orgs.add(org);
                            }
                        } catch (Exception e) {
                            logger.error("Error fetching organization where user " + user.getFullName() + " has special role");
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while getting organizations where user " + user.getFullName() + " has special role", e);
        }
        return orgs;
    }

    public boolean affectManuallyUserToOrg(long userId, Organization org) {

        try {
            if (OrganizationLocalServiceUtil.hasUserOrganization(userId, org.getOrganizationId())) {
                // User is already in org => do nothing
                return false;
            }

            Role orgUserRole = RoleUtilsLocalServiceUtil.getOrganizationUserRole();

            // Add user into organization (Table users_orgs)
            // This might be deleted by the synchronization process
            UserLocalServiceUtil.addOrganizationUsers(org.getOrganizationId(), new long[]{userId});

            // Give user the role (Table UserGroupRole)
            UserGroupRoleLocalServiceUtil.addUserGroupRoles(userId, org.getGroup().getGroupId(),
                    new long[]{orgUserRole.getRoleId()});

            return true;
        } catch (Exception e) {
            logger.error("Error while manually affecting user " + userId + " to org " + org.getOrganizationId());
        }
        return false;
    }

    public List<Organization> getUserOrganizations(long userId, List<Integer> types, List<Integer> roles,
                                                   Boolean withArchives, long schoolId) {
        return orgUtilsFinder.findUserOrganizations(userId, types, roles, withArchives,
                schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<User> getSchoolTeachers(long schoolId) {
        try {
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(schoolId);

            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());

            return UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null,
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
        } catch (Exception e) {
            logger.error("Error fetching teachers for school " + schoolId, e);
        }

        return new ArrayList<>();
    }

    public Long countOrgMembers(long orgId) {
        try {
            // Do not count parents in class and cours org
            boolean isClassOrCours = OrgDetailsLocalServiceUtil.isClass(orgId) || OrgDetailsLocalServiceUtil.isCours(orgId);
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(orgId);

            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
            roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());

            if (!isClassOrCours) {
                roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
            }

            return UserSearchLocalServiceUtil.countUsers("", orgIds, null, roleIds, null);
        } catch (Exception e) {
            logger.error("Error counting members for orgId " + orgId, e);
        }

        return 0L;
    }

}
