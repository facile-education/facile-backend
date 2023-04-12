package com.weprode.nero.eel.synchronization;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.ldap.util.LDAPUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.eel.synchronization.model.Subject;
import com.weprode.nero.eel.synchronization.service.SubjectLocalServiceUtil;
import com.weprode.nero.eel.synchronization.service.TeacherSubjectLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgMapping;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.model.LDAPMapping;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.*;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.File;
import java.io.*;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Main class for GVE synchronization process
 * @author Cedric Lecarpentier
 *
 */
public class GVESynchronizationManager {

    private static final Log logger = LogFactoryUtil.getLog(GVESynchronizationManager.class);

    // Used for obsolete users deactivation
    private final List<Long> processedSchoolIds = new ArrayList<>();
    private final List<Long> processedUserIds = new ArrayList<>();

    // Maps a given cn to a User in DB
    private Map<String, User> cnUserMap;

    // Used to synchronize user classes
    private Map<User, List<Long>> userOrgMap;

    // Used to store all personals and in the end get all administrative people
    private List<String> personalCnList;

    // Structure built for sessions creations
    private Map<String, Map<String, List<SlotData>>> coursMap;

    // For a given sessionName, list all students
    private Map<String, List<User>> studentMap;

    // Used to generate report / send welcome messages
    private List<User> createdUserList;

    private long companyId;
    private Organization rootOrg;

    private List<Integer> h1Weeks;
    private List<Integer> h2Weeks;
    private Map<Date, Date> vacations;

    Date schoolYearStartDate = null;
    Date schoolYearSemesterDate = null;
    Date schoolYearEndDate = null;

    ReportData reportData;

    /**
     * Run the synchronization
     */
    public void runSynchronization() {

        companyId = PortalUtil.getDefaultCompanyId();

        try {
            // Loop over schools
            String schoolsList = PropsUtil.get("gve.schools");
            logger.info("Synchronization START for GVE school list = " + schoolsList);
            if (schoolsList != null && !schoolsList.equals("")) {
                String[] schoolTab = schoolsList.split(",");
                for (String schoolId : schoolTab) {
                    processSchool(schoolId);
                }
            }

            //GVESynchronizationReport.generateReportForSchoolManagers();
            initCreatedAccounts();
            deactivateObsoleteUsers();

        } catch (NamingException e) {
            logger.error("Synchro has been interrupted by a LDAP error.");

            try {
                User administrator = UserLocalServiceUtil.getUserByScreenName(PortalUtil.getDefaultCompanyId(), "pentila");
                List<Long> recipientList = new ArrayList<>();
                recipientList.add(administrator.getUserId());
                String subject = "Echec de la synchronisation EEL";
                String content = "Une erreur LDAP est survenue lors de la synchro qui a donc été arrêtée.";
                // TODO Messaging
                /*InternalMessageLocalServiceUtil.sendInternalMessage(administrator, recipientList, subject, content,
                        JSONFactoryUtil.createJSONArray(), 0, 0);*/
            } catch (Exception e1) {
                logger.error("Couldn't find admin user", e1);
            }
        } catch (Exception e) {
            logger.error("Error while synchronizing : ", e);
        }

        logger.info("Synchronization END");
    }
    
    private void processSchool(String schoolId) throws NamingException {

        initSlotMaps(schoolId);
        initSynchronization();

        // Build schoolDn
        String schoolDn = "ou=" + schoolId + "," + PropsUtil.get(GVE_LDAP_BASE_DN_GROUPS);

        Organization school = synchronizeSchool(schoolId);
        processedSchoolIds.add(school.getOrganizationId());

        String classesDn = "ou=CLASSES," + schoolDn;
        synchronizeClasses(school, classesDn);
        synchronizeVolees(school, classesDn);

        String subjectsDn = "ou=DISCIPLINES,ou=ENSEIGNANTS," + schoolDn;
        synchronizeTeacherSubjects(school, subjectsDn);

        String sessionsDn = "ou=COURS," + schoolDn;
        synchronizeSessions(school, sessionsDn);

        // Guests
        synchronizeStudentGuests(school, schoolDn);
        synchronizeTeacherGuests(school, schoolDn);

        // Roles
        synchronizeRole(school, "cn=ENT-DIRECTEURS,ou=ENT-DIRECTEURS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.NATIONAL_4);
        synchronizeRole(school, "cn=ENT-DIRECTEURS,ou=ENT-DIRECTEURS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.GROUP_ADMIN);
        synchronizeRole(school, "cn=ENT-DOYENS,ou=ENT-DOYENS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.DOYEN);

        synchronizeRole(school, "cn=ENT-CONSEILLERS-ORIENTATIONS,ou=ENT-EMPS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CONSEILLER_ORIENTATION);
        synchronizeRole(school, "cn=ENT-CONSEILLERS-SOCIAUX,ou=ENT-EMPS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CONSEILLER_SOCIAL);
        synchronizeRole(school, "cn=ENT-INFIRMIERES,ou=ENT-EMPS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.INFIRMIERE);
        synchronizeRole(school, "cn=ENT-PSYCHOLOGUES,ou=ENT-EMPS,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.PSYCHOLOGUE);

        synchronizeRole(school, "cn=ENT-ASSISTANTS-TECHNIQUES,ou=ENT-PAT,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.ASSISTANT_TECHNIQUE);
        synchronizeRole(school, "cn=ENT-CAISSIERS-COMPTABLES,ou=ENT-PAT,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CAISSIER_COMPTABLE);
        synchronizeRole(school, "cn=ENT-MEDIATHECAIRES,ou=ENT-PAT,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.BIBLIOTHECAIRE);
        synchronizeRole(school, "cn=ENT-SECRETAIRES,ou=ENT-PAT,ou=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.SECRETAIRE);

        synchronizeHoraires(school, schoolId);
        // TODO Cdt
        // createHoraires(school);

        synchronizeUserOrgs();

        sendReport(school.getOrganizationId());
    }
    
    private void synchronizeClasses(Organization school, String classesDn) throws NamingException {

        // For obsolete classes deletion
        List<Long> newClassOrgIds = new ArrayList<>();
        List<Organization> existingClasses = OrgUtilsLocalServiceUtil.getSchoolClasses(school.getOrganizationId(), false);

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over classes
            NamingEnumeration<SearchResult> classesEnum = getContext(companyId).search(classesDn, GVE_LDAP_CLASS_FILTER, cons);
            while (classesEnum.hasMoreElements()) {
                SearchResult classResult = classesEnum.nextElement();
                logger.info("Loop over class " + classResult.getName());

                if (classResult.getName().substring(3).equals("HORS-CLASSE")) {
                    continue;
                }

                String className = school.getName() + " - " + classResult.getName().substring(3);
                Organization classOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(companyId, className, school.getOrganizationId(), OrgConstants.CLASS_TYPE);

                newClassOrgIds.add(classOrg.getOrganizationId());

                // Get existing class members in DB
                Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
                List<Long> organizationIds = new ArrayList<>();
                organizationIds.add(classOrg.getOrganizationId());
                List<Long> roleIds = new ArrayList<>();
                roleIds.add(studentRole.getRoleId());
                List<User> existingStudentMembers = new ArrayList<>();
                try {
                    existingStudentMembers = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                } catch (Exception e) {
                    logger.debug(e);
                }

                // The new student class's members
                List<Long> newStudentIds = new ArrayList<>();
                String classDn = classResult.getName() + "," + classesDn;

                // Fetch current members in LDAP
                String[] attributeIds = {ETAT_GE_PROPRIETAIRE};
                Attributes attrs = getContext(companyId).getAttributes(classDn, attributeIds);

                // Attribute ETATGEproprietaire is main teacher
                List<String> principalTeacherCns = new ArrayList<>();
                if (attrs.get(ETAT_GE_PROPRIETAIRE) != null) {
                    for (int i = 0 ; i < attrs.get(ETAT_GE_PROPRIETAIRE).size(); i++) {
                        String principalTeacherCn = attrs.get(ETAT_GE_PROPRIETAIRE).get(i).toString();

                        logger.info("Found main teacher " + principalTeacherCn);
                        try {
                            User mainTeacher = getUserFromCn(principalTeacherCn);
                            principalTeacherCns.add(principalTeacherCn);
                            addTeacherRole(mainTeacher);

                            // Add main teacher role
                            Role mainTeacherRole = RoleLocalServiceUtil.getRole(mainTeacher.getCompanyId(), NeroRoleConstants.MAIN_TEACHER);
                            UserGroupRoleLocalServiceUtil.addUserGroupRoles(mainTeacher.getUserId(), classOrg.getGroup().getGroupId(), new long[] {mainTeacherRole.getRoleId()});
                            logger.info("  Adding teacher "+mainTeacher.getFullName()+" main teacher of class "+classOrg.getName());

                            registerTeacherToSchool(mainTeacher, school);
                            setSynchroDate(mainTeacher);

                            // Add teacher to the class
                            addUserToOrgMap(mainTeacher, classOrg.getOrganizationId());
                        } catch (Exception e) {
                            logger.error("Error processing teacher " + principalTeacherCn);
                        }
                    }
                }

                // Fetch current members in LDAP
                attributeIds = new String[]{MEMBER};
                attrs = getContext(companyId).getAttributes(classDn, attributeIds);

                // All members are class students + the principal teacher
                if (attrs.get(MEMBER) != null) {
                    for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                        String member = attrs.get(MEMBER).get(i).toString();
                        if (!principalTeacherCns.contains(member)) {
                            logger.info("Found member " + member);
                            User user = getUserFromCn(member);

                            if (user != null) {

                                if (member.contains("ou=ELEVES")) {

                                    addStudentRole(user);
                                    registerStudentToSchool(user, school);
                                    addUserToOrgMap(user, classOrg.getOrganizationId());
                                    setSynchroDate(user);
                                    newStudentIds.add(user.getUserId());

                                } else if (member.contains("ou=ENSEIGNANTS")) {

                                    addTeacherRole(user);
                                    registerTeacherToSchool(user, school);
                                    addUserToOrgMap(user, classOrg.getOrganizationId());
                                    setSynchroDate(user);
                                }
                            }
                        }
                    }
                }

                logger.info("Class " + classOrg.getName() + " is processed -> " + existingStudentMembers.size() + " existing members and " + newStudentIds.size() + " new members");
                // Remove obsolete students from the class
                for (User existingStudentMember : existingStudentMembers) {
                    if (!newStudentIds.contains(existingStudentMember.getUserId())) {

                        List<Long> manuallyAddedOrgIds = getManuallyAddedOrganizations(existingStudentMember);
                        if (!manuallyAddedOrgIds.contains(classOrg.getOrganizationId())) {
                            logger.info(" > REMOVING student " + existingStudentMember.getFullName() + " from class " + OrgUtilsLocalServiceUtil.formatOrgName(classOrg.getName(), false));
                            try {
                                UserLocalServiceUtil.unsetOrganizationUsers(classOrg.getOrganizationId(), new long[]{existingStudentMember.getUserId()});
                            } catch (Exception e) {
                                logger.error("Error when removing student", e);
                            }
                        } else {
                            logger.info(" > Do not remove student " + existingStudentMember.getFullName() + " from class " + OrgUtilsLocalServiceUtil.formatOrgName(classOrg.getName(), false) + " because manually added");
                        }
                    }
                }

            }
            classesEnum.close();
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing classes", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error in synchronizing classes", e);
        }

        // Delete obsolete classes
        for (Organization toRemoveOrg : existingClasses) {
            if (!newClassOrgIds.contains(toRemoveOrg.getOrganizationId())) {
                logger.info("Deleting obsolete class " + toRemoveOrg.getName() + " for school " + school.getName());
                try {
                    // Check if it has members
                    long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(toRemoveOrg.getOrganizationId());
                    if (userIds != null && userIds.length > 0) {
                        logger.info("Removing "+userIds.length+" members from organizationId "+toRemoveOrg.getOrganizationId()+"...");
                        UserLocalServiceUtil.unsetOrganizationUsers(toRemoveOrg.getOrganizationId(), userIds);
                    }

                    logger.info("Removing obsolete organization "+toRemoveOrg.getName()+" (with oganizationId "+toRemoveOrg.getOrganizationId());
                    OrganizationLocalServiceUtil.deleteOrganization(toRemoveOrg.getOrganizationId());

                } catch (Exception e) {
                    logger.error("Error when deleting obsolete orgId "+toRemoveOrg.getOrganizationId(), e);
                }
            }
        }

    }

    private void synchronizeVolees(Organization school, String classesDn) throws NamingException {

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over classes
            NamingEnumeration<SearchResult> classesEnum = getContext(companyId).search(classesDn, GVE_LDAP_CLASS_FILTER, cons);
            while (classesEnum.hasMoreElements()) {
                SearchResult classResult = classesEnum.nextElement();
                logger.info("Loop over class " + classResult.getName());

                if (classResult.getName().substring(3).equals("HORS-CLASSE") || classResult.getName().substring(3).startsWith("CLI")) {
                    continue;
                }

                // Extract volee (school level) and create needed organization if needed
                String volee = classResult.getName().substring(3, 5);
                logger.info(" -> volee = " + volee);

                // Check that the volee belongs to the authorized list
                if (!OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee)) {
                    logger.error("===REPORT=== Non authorized volee : " + classResult.getName());
                    continue;
                }

                String voleeName = school.getName() + " - " + volee;
                Organization voleeOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(companyId, voleeName, school.getOrganizationId(), OrgConstants.VOLEE_TYPE);

                String classDn = classResult.getName() + "," + classesDn;

                // Fetch current members in LDAP
                String[] attributeIds = {ETAT_GE_PROPRIETAIRE};
                Attributes attrs = getContext(companyId).getAttributes(classDn, attributeIds);

                // Attribute ETATGEproprietaire is main teacher
                String principalTeacherCn = "";
                if (attrs.get(ETAT_GE_PROPRIETAIRE) != null) {
                    for (int i = 0 ; i < attrs.get(ETAT_GE_PROPRIETAIRE).size(); i++) {
                        principalTeacherCn = attrs.get(ETAT_GE_PROPRIETAIRE).get(i).toString();

                        try {
                            User teacher = getUserFromCn(principalTeacherCn);

                            // Add main teacher role in volee
                            long voleeGroupId = voleeOrg.getGroup().getGroupId();
                            Role mainTeacherRole = RoleLocalServiceUtil.getRole(teacher.getCompanyId(), NeroRoleConstants.MAIN_TEACHER);
                            UserGroupRoleLocalServiceUtil.addUserGroupRoles(teacher.getUserId(), voleeGroupId, new long[] {mainTeacherRole.getRoleId()});
                            logger.info("  Adding teacher "+teacher.getFullName()+" main teacher of volee "+voleeOrg.getName());

                        } catch (Exception e) {
                            logger.error("Error processing teacher " + principalTeacherCn);
                        }
                    }
                }

                // Fetch current members in LDAP
                attributeIds = new String[]{MEMBER};
                attrs = getContext(companyId).getAttributes(classDn, attributeIds);

                // Add members to the volee
                if (attrs.get(MEMBER) != null) {
                    for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                        String member = attrs.get(MEMBER).get(i).toString();
                        if (!member.equals(principalTeacherCn)) {
                            logger.info("Found member " + member);
                            User user = getUserFromCn(member);
                            if (user != null) {
                                addUserToOrgMap(user, voleeOrg.getOrganizationId());
                            }
                        }
                    }
                }
            }
            classesEnum.close();
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing volees", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error in synchronizing volees", e);
        }

    }

    private void registerStudentToSchool(User student, Organization school) {
        // Set rattach school
        try {
            UserProperties userProp = UserPropertiesLocalServiceUtil.getUserProperties(student.getUserId());
            userProp.setEtabId(school.getOrganizationId());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProp);
        } catch (Exception e) {
            logger.debug(e);
        }

        // Add student to the school
        addUserToOrgMap(student, school.getOrganizationId());

    }

    private void registerTeacherToSchool(User teacher, Organization school) {
        // Set rattach school
        try {
            UserProperties userProp = UserPropertiesLocalServiceUtil.getUserProperties(teacher.getUserId());
            userProp.setEtabId(school.getOrganizationId());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProp);
        } catch (Exception e) {
            logger.debug(e);
        }

        // Add teacher to the school
        addUserToOrgMap(teacher, school.getOrganizationId());

        // Add teacher to the school-level org
        Organization schoolLevelTeacherOrg = OrgUtilsLocalServiceUtil.getSchoolTeachersOrganization(school.getOrganizationId());
        Organization schoolLevelPersonalOrg = OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(school.getOrganizationId());
        addUserToOrgMap(teacher, schoolLevelTeacherOrg.getOrganizationId());
        addUserToOrgMap(teacher, schoolLevelPersonalOrg.getOrganizationId());

    }

    private void registerPersonalToSchool(User personal, Organization school, boolean isPAT) {

        // Set rattach school
        try {
            UserProperties userProp = UserPropertiesLocalServiceUtil.getUserProperties(personal.getUserId());
            userProp.setEtabId(school.getOrganizationId());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProp);
        } catch (Exception e) {
            logger.debug(e);
        }

        // Add teacher to the school
        addUserToOrgMap(personal, school.getOrganizationId());

        // Add personal to the school-level org
        Organization schoolLevelPersonalOrg = OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(school.getOrganizationId());
        addUserToOrgMap(personal, schoolLevelPersonalOrg.getOrganizationId());

        if (isPAT) {
            Organization schoolLevelPATOrg = OrgUtilsLocalServiceUtil.getSchoolPATsOrganization(school.getOrganizationId());
            addUserToOrgMap(personal, schoolLevelPATOrg.getOrganizationId());
        }
    }

    private void synchronizeUserOrgs() {

        // Loop over students
        for (Map.Entry<User, List<Long>> entry : userOrgMap.entrySet()) {
            User user = entry.getKey();
            logger.info("Synchronizing classes for user "+user.getFullName());
            try {
                List<Long> newUserOrgIds = entry.getValue();
                List<Organization> existingUserOrgs = OrganizationLocalServiceUtil.getUserOrganizations(user.getUserId());

                // This list contains all orgs we do want to keep during this synchronization : rootOrg, manually added orgs

                List<Long> manuallyAddedOrgIds = getManuallyAddedOrganizations(user);
                List<Long> organizationIdsToKeep = new ArrayList<>(manuallyAddedOrgIds);
                organizationIdsToKeep.add(rootOrg.getOrganizationId());

                // Loop over the existing child organizations to remove obsolete orgs
                for (Organization studentOrg : existingUserOrgs) {

                    //_log.info("Testing membership for user "+user.getFullName()+" : testing for removal class/group "+studentOrg.getName());

                    // Skip archived organizations
                    if (OrgDetailsLocalServiceUtil.isArchived(studentOrg.getOrganizationId())) {
                        continue;
                    }

                    // Skip organizations that we want to keep
                    if (organizationIdsToKeep.contains(studentOrg.getOrganizationId())) {
                        continue;
                    }

                    // If this org is not in the 'new' list, remove it
                    if (!newUserOrgIds.contains(studentOrg.getOrganizationId())) {
                        try {
                            UserLocalServiceUtil.unsetOrganizationUsers(studentOrg.getOrganizationId(), new long[]{user.getUserId()});
                            logger.info("Removed user "+user.getUserId()+" from organization "+studentOrg.getName());
                        } catch (Exception e) {
                            logger.error("Error removing user "+user.getUserId()+" from organization "+studentOrg.getName(), e);
                        }
                    } else {
                        newUserOrgIds.remove(studentOrg.getOrganizationId());
                    }
                }

                // Now add new orgs
                for (Long newUserOrgId : newUserOrgIds) {
                    if (manuallyAddedOrgIds.contains(newUserOrgId)) {
                        logger.info("Do not add org " + newUserOrgId +" to user "+user.getFullName()+" because was manually added");
                        continue;
                    }
                    try {
                        UserLocalServiceUtil.addOrganizationUsers(newUserOrgId, new long[]{user.getUserId()});
                        logger.info("Add new organization " + newUserOrgId + " to user "+user.getFullName());
                    } catch (Exception e) {
                        logger.error("Error adding user "+user.getUserId()+" into organization "+newUserOrgId, e);
                    }
                }
            } catch (Exception e) {
                logger.error("Error when synchronizing orgs for user "+user.getUserId(), e);
            }
        }

    }

    private List<Long> getManuallyAddedOrganizations(User user) {
        return AffectationLocalServiceUtil.getUserAffectedOrgs(user.getUserId());
    }

    // TO REMOVE WHEN NEW AFFECTATION MECHANISM IS OPERATIONAL
//	private List<Long> getManuallyAddedOrganizations(User user) {
//
//		List<Long> manuallyAddedOrgIds = new ArrayList<Long>();
//		try {
//			Role orgUserRole = RoleLocalServiceUtil.getRole(companyId, NeroRoleConstants.ORGANIZATION_USER);
//
//			List<UserGroupRole> userGroupRoleList = UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());
//			for (UserGroupRole userGroupRole : userGroupRoleList) {
//				if (userGroupRole.getRoleId() == orgUserRole.getRoleId()) {
//					Group group = GroupLocalServiceUtil.getGroup(userGroupRole.getGroupId());
//					Organization manualOrg = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
//
//					// Skip archived organizations
//					if (OrganizationDetailsLocalServiceUtil.isArchived(manualOrg.getOrganizationId())) {
//						continue;
//					}
//
//					_log.info("  Found manually added organization is "+manualOrg.getName());
//					manuallyAddedOrgIds.add(manualOrg.getOrganizationId());
//
//				}
//			}
//		} catch (Exception e) {
//			_log.error("Error when fetching manually added organizations for user "+user.getFullName());
//		}
//		return manuallyAddedOrgIds;
//	}
//

    private void synchronizeTeacherSubjects(Organization school, String subjectsDn) throws NamingException {

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over classes
            NamingEnumeration<SearchResult> subjectsEnum = getContext(companyId).search(subjectsDn, GVE_LDAP_CLASS_FILTER, cons);
            while (subjectsEnum.hasMoreElements()) {
                SearchResult subjectResult = subjectsEnum.nextElement();
                String subjectName = subjectResult.getName().substring(3);
                if (subjectName.equals("TOUS")) {
                    continue;
                }

                // Remove dashes
                subjectName = subjectName.replace("-", " ");
                // LowerCase + Capitalize subject
                subjectName = subjectName.substring(0, 1).toUpperCase() + subjectName.substring(1).toLowerCase();

                // Create subject if needed
                Subject subject = SubjectLocalServiceUtil.getOrCreateSubject(subjectName);

                // Loop over teachers
                String[] attributeIds = {MEMBER};
                String subjectDn = subjectResult.getName() + "," + subjectsDn;
                logger.info("SubjectDn = "+ subjectDn);

                // Now manage subject groups
                String subjectOrgName = OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true) + OrgConstants.ORG_SUFFIX_TEACHERS +
                        (subjectName.startsWith("A") || subjectName.startsWith("E") || subjectName.startsWith("H") || subjectName.startsWith("I") ? " d'" : " de ") + subjectName;
                Organization subjectOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(school.getCompanyId(), subjectOrgName, school.getOrganizationId(), OrgConstants.SUBJECT_TYPE);

                Attributes attrs = getContext(companyId).getAttributes(subjectDn, attributeIds);
                if (attrs.get(MEMBER) != null) {
                    for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                        String memberCn = attrs.get(MEMBER).get(i).toString();
                        logger.info("Found member " + memberCn);
                        personalCnList.add(memberCn);

                        // Create teacher if needed
                        try {
                            User teacher = getUserFromCn(memberCn);
                            logger.info("Found teacher : userId = "+ teacher.getUserId());

                            // Add teacher role
                            addTeacherRole(teacher);

                            // Add teacher to subject org
                            addUserToOrgMap(teacher, subjectOrg.getOrganizationId());

                            setSynchroDate(teacher);
                            registerTeacherToSchool(teacher, school);

                            // Add him/her the subject
                            TeacherSubjectLocalServiceUtil.addTeacherSubjectInSchool(teacher.getUserId(), subject.getSubjectId(), school.getOrganizationId());
                        } catch (Exception e) {
                            logger.error("Error processing teacher " + memberCn);
                        }
                    }
                }
            }
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing teachers and subjects", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error in synchronizing teachers and subjects", e);
        }
    }

    private Organization synchronizeSchool (String schoolId) {

        // Get school informations
        String schoolDn = "ou=" + schoolId + "," + PropsUtil.get(GVE_LDAP_BASE_DN_GROUPS);
        String[] schoolAttributes = {"description"};
        Organization school = null;
        try {
            Attributes schoolAttrs = getContext(companyId).getAttributes(schoolDn, schoolAttributes);
            if (schoolAttrs.get("description") != null) {
                String schoolName = schoolAttrs.get("description").get().toString();
                // Replace 'Collège' with 'CO'
                schoolName = Normalizer.normalize(schoolName, Normalizer.Form.NFD);
                schoolName = schoolName.replace("[^\\p{ASCII}]", "");
                schoolName = schoolName.replace("College", "CO");
                logger.info("School is "+ schoolName);

                // Create school and school-level orgs
                try {
                    school = OrgUtilsLocalServiceUtil.getOrCreateSchool(companyId, schoolName);
                    OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(school.getOrganizationId());
                    OrgUtilsLocalServiceUtil.getSchoolPATsOrganization(school.getOrganizationId());
                    OrgUtilsLocalServiceUtil.getSchoolTeachersOrganization(school.getOrganizationId());
                } catch (Exception e) {
                    logger.error("Error when creating school with name "+schoolName, e);
                }
                OrgMapping orgMapping = null;
                try {
                    orgMapping = OrgMappingLocalServiceUtil.getOrgMapping(schoolId);
                } catch (Exception e) {
                    logger.debug(e);
                }
                if (school != null && orgMapping == null) {
                    OrgMappingLocalServiceUtil.addOrgMapping(school, schoolId);
                }

            }
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing school " + schoolId, e);
            // throw e;
        } catch (Exception e) {
            logger.error("Error while synchronizing school " + schoolId, e);
        }
        return school;
    }


    private void synchronizeStudentGuests(Organization school, String schoolDn) throws NamingException {

        logger.info("======================================================");
        logger.info("Start synchronizeStudentGuests");

        String studentGuestsCn = "cn=INVITES-ELEVES,ou=GLOBAL," + schoolDn;

        try {
            String[] attributeIds = {MEMBER};
            Attributes attrs = getContext(companyId).getAttributes(studentGuestsCn, attributeIds);

            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    logger.info("Found student guest " + memberCn);
                    User student = getUserFromCn(memberCn);
                    addStudentRole(student);
                    setSynchroDate(student);
                    registerStudentToSchool(student, school);
                    logger.info("Added student guest : "+student.getFullName());
                }
            }
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing student guests", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error processing student guests", e);
        }
    }

    private void synchronizeTeacherGuests(Organization school, String schoolDn) throws NamingException {

        logger.info("======================================================");
        logger.info("Start synchronizeTeacherGuests");

        String teacherGuestsCn = "cn=INVITES-ENSEIGNANTS,ou=GLOBAL," + schoolDn;

        try {
            String[] attributeIds = {MEMBER};
            Attributes attrs = getContext(companyId).getAttributes(teacherGuestsCn, attributeIds);

            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    logger.info("Found teacher guest " + memberCn);
                    personalCnList.add(memberCn);

                    User teacher = getUserFromCn(memberCn);
                    addTeacherRole(teacher);
                    setSynchroDate(teacher);
                    registerTeacherToSchool(teacher, school);
                    logger.info("Added teacher guest : "+teacher.getFullName());
                }
            }
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing teacher guests", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error processing teacher guests", e);
        }
    }

    private void synchronizeRole(Organization school, String roleDn, String roleName) {

        logger.info("======================================================");
        logger.info("Start synchronize role " + roleName);

        try {
            String attributeName = MEMBER; // "member" or "owner" ?
            String[] attributeIds = {attributeName};
            Attributes attrs = getContext(companyId).getAttributes(roleDn, attributeIds);

            if (attrs.get(attributeName) != null) {
                for (int i = 0 ; i < attrs.get(attributeName).size(); i++) {
                    String memberCn = attrs.get(attributeName).get(i).toString();
                    User user = getUserFromCn(memberCn);
                    if (user != null) {
                        try {
                            Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
                            if (!RoleLocalServiceUtil.hasUserRole(user.getUserId(), role.getRoleId())) {
                                RoleLocalServiceUtil.addUserRoles(user.getUserId(), new long[]{role.getRoleId()});
                            }
                            logger.info("Added role " + roleName + " to member : "+user.getFullName());

                            // For school admin role -> add UserGroupRole
                            if (role.getRoleId() == RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId()) {
                                long[] roleIds = new long[1];
                                roleIds[0] = role.getRoleId();
                                if (!UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), school.getGroupId(), role.getRoleId())) {
                                    UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), school.getGroupId(), roleIds);
                                }
                            }
                        } catch (Exception e) {
                            logger.error("Error giving role " + roleName + " to user " + user.getFullName(), e);
                        }
                        registerPersonalToSchool(user, school, roleDn.contains("ENT-PAT"));
                        setSynchroDate(user);
                    } else {
                        logger.error("User " + memberCn + " is not found");
                    }
                }
            }
        } catch (NameNotFoundException e) {
            logger.error("===REPORT=== Role CN not existing in EEL : " + roleName);
            // Extract expected role name in EEL
            String expectedRoleName = roleDn.split(",")[0];
            expectedRoleName = expectedRoleName.substring(3);
            reportData.addRole(roleName + " (" + expectedRoleName + ")");
        } catch (Exception e) {
            logger.error("Error processing role " + roleName, e);
        }
    }


    private User getUserFromCn(String fullCn) throws NamingException {

        if (cnUserMap.containsKey(fullCn)) {
            return cnUserMap.get(fullCn);
        } else {
            String[] attributeIds = {"givenName", "mail", "sn", "description"};

            try {
                // Get specified LDAP attributes
                Attributes attrs = getContext(companyId).getAttributes(fullCn, attributeIds);

                String givenName    = LDAPUtil.getAttributeString(attrs, "givenName");
                String sn   		= LDAPUtil.getAttributeString(attrs, "sn");
                String mail   		= LDAPUtil.getAttributeString(attrs, "mail");
                String uid			= LDAPUtil.getAttributeString(attrs, "description");
                String shortCn = extractCn(fullCn);

                // First fetch by screenname
                User candidate = null;
                try {
                    candidate = UserLocalServiceUtil.fetchUserByScreenName(companyId, shortCn);
                    updateMapping(candidate.getUserId(), uid);

                } catch (Exception e) {
                    logger.debug(e);
                }

                // Then fetch by email, because account may have been manually created (then with different screenname), but with same email
                if (candidate == null) {
                    logger.info("Not found any user with screenName = " + shortCn + " -> fetching it by email " + mail);
                    try {
                        candidate = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, mail);

                        // TODO : update screenname ? It breaks external access and opens EEL access
                        //_log.info("Updating screenName from " + candidate.getScreenName() + " to " + shortCn);
                        //candidate = UserLocalServiceUtil.updateScreenName(candidate.getUserId(), shortCn);

                        // TODO : transform user to non-manual ?
                        updateMapping(candidate.getUserId(), uid);
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }

                // Then fetch by SIRH, because people may change name and email simultaneously
                if (candidate == null) {
                    logger.info("Not found any user with screenName = " + shortCn + " nor email " + mail + " -> fetching by SIRH");
                    try {
                        candidate = LDAPMappingLocalServiceUtil.getUserFromUID(uid);
                        if (candidate != null) {
                            logger.info("SIRH " + uid + " found among existing SIRHs -> this is a name change" );

                            // Update screenName
                            logger.info("Updating screenName from " + candidate.getScreenName() + " to " + shortCn);
                            candidate = UserLocalServiceUtil.updateScreenName(candidate.getUserId(), shortCn);

                            // Update email
                            logger.info("Updating email from " + candidate.getEmailAddress() + " to " + mail);
                            candidate = UserLocalServiceUtil.updateEmailAddress(candidate.getUserId(), "", mail, mail);

                            // Update firstName and/or lastName
                            logger.info("Updating firstName from " + candidate.getFirstName() + " to " + givenName + " and lastName from " + candidate.getLastName() + " to " + sn);
                            candidate.setFirstName(givenName);
                            candidate.setLastName(sn);
                            UserLocalServiceUtil.updateUser(candidate);
                        }
                    } catch (Exception e) {
                        logger.debug(e);
                    }

                }

                // Fetch by LDAP UID in case the user changing name is a student
                if (candidate == null) {
                    logger.info("Not found any user with screenName = " + shortCn + " nor email " + mail + " -> fetching by LDAP UID");
                    try {
                        candidate = LDAPMappingLocalServiceUtil.getUserFromUID(uid.replace("NBDS-", ""));

                        // Update screenName
                        logger.info("Updating screenName from " + candidate.getScreenName() + " to " + shortCn);
                        candidate = UserLocalServiceUtil.updateScreenName(candidate.getUserId(), shortCn);

                        // Update email
                        logger.info("Updating email from " + candidate.getEmailAddress() + " to " + mail);
                        candidate = UserLocalServiceUtil.updateEmailAddress(candidate.getUserId(), "", mail, mail);

                        // Update firstName and/or lastName
                        logger.info("Updating firstName from " + candidate.getFirstName() + " to " + givenName + " and lastName from " + candidate.getLastName() + " to " + sn);
                        candidate.setFirstName(givenName);
                        candidate.setLastName(sn);
                        UserLocalServiceUtil.updateUser(candidate);
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }

                if (candidate == null) {
                    logger.info("Not found any user with mail = " + mail + " -> creating it");
                    try {
                        if (mail.equals("")) {
                            mail = shortCn + "@nomail.com";
                        }
                        String formattedFirstName = GVESynchronizationUtils.formatName(givenName);
                        User createdUser = createUser(shortCn, sn.toUpperCase(), formattedFirstName, mail, uid);
                        cnUserMap.put(fullCn, createdUser);
                        return createdUser;
                    } catch (Exception e) {
                        logger.error("Error while creating user from fullCn="+fullCn+", sn="+sn+", givenName="+givenName+", mail="+mail, e);
                    }
                } else {
                    //_log.info("Found a user with same mail in DB -> using it");
                    cnUserMap.put(fullCn, candidate);
                    return candidate;
                }

            } catch (NamingException e) {
                logger.error("LDAP error when fetching user in LDAP", e);
                throw e;
            } catch (Exception e) {
                logger.error("Error fetching user in LDAP");
            }
        }

        return null;
    }

    /**
     * Import a user
     */
    public User createUser (String shortCn, String lastName, String firstName, String mail, String uid) throws Exception {
        User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

        boolean autoPassword = true;
        boolean autoScreenName = false;
        ServiceContext serviceContext = new ServiceContext();

        if (Validator.isNull(lastName) || Validator.isNull(firstName) || Validator.isNull(mail)) {
            logger.warn("Cannot add user because lastName (" + lastName + ") or firstName ("+firstName+") or mail ("+mail+") is empty");
            return null;
        }

        User user = null;

        try {
            user = UserLocalServiceUtil.getUserByScreenName(companyId, shortCn);

            // Skip if default user
            if (user.isDefaultUser()) {
                return user;
            }

            logger.info("User "+firstName+" "+lastName+" exists in DB");

        } catch (NoSuchUserException nsue) {
            logger.error("User does not exist so create it");

        } catch (Exception e) {
            logger.error("Error getting user with screen name " + shortCn	+ " and email address " + mail, e);
            return null;
        }


        if (user == null) {

            // User creation
            try {
                logger.info("===== Adding user "+firstName+" "+lastName+ " to portal =====");
                user = UserLocalServiceUtil.addUser(0, companyId, autoPassword, StringPool.BLANK, StringPool.BLANK, autoScreenName,
                        shortCn, mail, LocaleUtil.getDefault(), firstName, StringPool.BLANK, lastName, 0,
                        0, true, 1, 1, 2000, StringPool.BLANK, null,
                        null, null, null, false, serviceContext);

                if (createdUserList == null) {
                    createdUserList = new ArrayList<>();
                }
                createdUserList.add(user);

                Date today = new Date();
                user.setLastLoginDate(today);
                user.setModifiedDate(today);
                user.setNew(false);
                user.setPasswordModified(true);
                user.setPasswordReset(false);
                user.setReminderQueryQuestion("@new@");
                user.setReminderQueryAnswer("@new@");
                UserLocalServiceUtil.updateUser(user);

                // Create default user notifications
                // TODO Preferences
                /*NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());
                logger.debug("create user notification config : " + userNotificationConfig.getNotifyConfigId() + " for user " + user.getUserId());*/

                createMessagingConfig(user);
                createContactProperties(user);
                createServiceNotifications(user);

                // Add UId mapping
                LDAPMapping ldapMapping = LDAPMappingLocalServiceUtil.createLDAPMapping(user.getUserId());
                ldapMapping.setUID(uid.replace("NBDS-", ""));
                LDAPMappingLocalServiceUtil.updateLDAPMapping(ldapMapping);
            } catch (Exception e) {
                logger.error("Problem adding user with screen name " + shortCn + " and email address " + mail, e);
                return null;
            }
        }

        // Update email if changed
        try {
            if (!user.getEmailAddress().equals(mail)) {
                user.setEmailAddress(mail);
                UserLocalServiceUtil.updateUser(user);
                logger.info("Updated email for user "+user.getFullName()+" : "+mail);
            }
        } catch (Exception e) {
            logger.error("Error while updating user email from "+user.getEmailAddress()+" to "+mail+" (maybe duplicate account)");
        }

        // Update UId mapping
        updateMapping(user.getUserId(), uid);

        // Add user to root org
        if (!UserLocalServiceUtil.hasOrganizationUser(rootOrg.getOrganizationId(), user.getUserId()) ) {
            UserLocalServiceUtil.addOrganizationUsers(rootOrg.getOrganizationId(), new long[]{user.getUserId()});
        }

        return user;
    }


    /**
     * Update MessagingConfig table
     */
    private void createMessagingConfig(User user) {
        // TODO Messaging
        /*MessagingConfig messagingConfig = null;

        try {
            messagingConfig = MessagingConfigLocalServiceUtil.getMessagingConfig(user.getUserId());
        } catch (Exception e) {
            logger.debug(e);
        }

        if (messagingConfig == null) {
            try {
                MessagingConfigLocalServiceUtil.addMessagingConfig(user);
            } catch (Exception e) {
                logger.error("Error while creating messaging config for user "+user.getFullName()+" ("+user.getUserId()+")");
            }
        }*/
    }

    private void createContactProperties(User user) {
        try {
            String suffix = PropsUtil.get(NeroSystemProperties.DEFAUT_MAIL_SUFFIX);

            boolean isMailValid = !user.getEmailAddress().equals(user.getScreenName()+suffix);
            String mail = "";
            if (!isMailValid) {
                mail = "";
            }

            UserContact uc = UserContactLocalServiceUtil.getUserContactByUserId(user.getUserId());

            uc.setMail(mail);
            UserContactLocalServiceUtil.updateUserContact(uc);

        } catch(Exception e) {
            logger.error("Error while updating user contact properties for user "+user.getUserId());
        }
    }

    private void createServiceNotifications(User user) {

        try {
            // Force notifications to enable
            if (Boolean.parseBoolean(PropsUtil.get(NeroSystemProperties.NOTIFICATIONS_ENABLED))) {
                // TODO Preferences
                /*NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());

                userNotificationConfig.setNotifyCasier(true);
                userNotificationConfig.setNotifyActu(true);
                userNotificationConfig.setNotifyGrpDoc(true);
                userNotificationConfig.setNotifyAgenda(true);
                userNotificationConfig.setDigestPeriod(2);
                userNotificationConfig.setUserId(user.getUserId());

                userNotificationConfig.setActivate(true);

                NotifyConfigLocalServiceUtil.updateNotifyConfig(userNotificationConfig);*/
            }
        } catch(Exception e) {
            logger.error("Error while updating user notify config for user "+user.getUserId());
        }
    }

    private void updateMapping(long userId, String uid) {

        //_log.info("Updating LDAP mapping for userId "+userId + " with UID="+uid);
        LDAPMapping ldapMapping = null;
        try {
            ldapMapping = LDAPMappingLocalServiceUtil.getLDAPMapping(userId);
            ldapMapping.setUID(uid.replace("NBDS-", ""));
            LDAPMappingLocalServiceUtil.updateLDAPMapping(ldapMapping);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (ldapMapping == null) {
            try {
                ldapMapping = LDAPMappingLocalServiceUtil.createLDAPMapping(userId);
                ldapMapping.setUID(uid.replace("NBDS-", ""));
                LDAPMappingLocalServiceUtil.updateLDAPMapping(ldapMapping);
            } catch (Exception e) {
                logger.debug(e);
            }
        }
    }

    private void synchronizeSessions(Organization school, String sessionsDn) throws NamingException {

        logger.info("Synchronize sessions "+sessionsDn);

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over degrees
            String[] degrees = {"ou=9", "ou=10", "ou=11", "ou=X"};
            for (String degree : degrees) {

                String degreeDn = degree + "," + sessionsDn;

                NamingEnumeration<SearchResult> sessionEnum = getContext(companyId).search(degreeDn, GVE_LDAP_CLASS_FILTER, cons);
                while (sessionEnum.hasMoreElements()) {
                    SearchResult sessionResult = sessionEnum.nextElement();
                    String sessionDn = sessionResult.getName() + "," + degreeDn;

                    synchronizeSession(school, sessionDn);
                }
            }

        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing teachers and subjects", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error in synchronizing teachers and subjects", e);
        }
    }

    private void synchronizeSession(Organization school, String sessionDn) throws NamingException {

        // Skip last characters '-', '_', '=' and '.'
        if (sessionDn.endsWith("=") || sessionDn.endsWith("-") || sessionDn.endsWith("_") || sessionDn.endsWith(".")) {
            sessionDn = sessionDn.substring(0, sessionDn.length() - 1);
        }
        String sessionName = extractCn(sessionDn);

        logger.info("Synchronizing session " + sessionName);

        try {
            // Check that the cours name is well-formatted
            if (getSessionName(sessionName).length() < 5) {
                logger.error("===REPORT=== Cours incorrect dans EEL : " + getSessionName(sessionName));
                reportData.setDirectoryError(true);
                return;
            }
            String volee = getSessionName(sessionName).substring(2, 4);
            String volee2 = getSessionName(sessionName).substring(3, 5);

            // Check that the volee belongs to the authorized list
            if (!OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee) && !OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee2)) {
                logger.error("===REPORT=== Cours incorrect dans EEL : " + getSessionName(sessionName));
                reportData.setDirectoryError(true);
                return;
            }

            // Create associated group
            String orgName = school.getName() + " - " + getSessionName(sessionName);
            Organization coursOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(companyId, orgName, school.getOrganizationId(), OrgConstants.COURS_TYPE);

            studentMap.put(sessionName, new ArrayList<>());

            String[] attributeIds = {MEMBER};
            int nbStudents = 0;
            Attributes attrs = getContext(companyId).getAttributes(sessionDn, attributeIds);
            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    //_log.info("Found member " + memberCn);
                    // Teachers are processed through csv files
                    // Here we take care of students
                    if (memberCn.contains("ELEVES")) {
                        User student = getUserFromCn(memberCn);

                        // Add student to the 'cours' org
                        addUserToOrgMap(student, coursOrg.getOrganizationId());

                        // Add student to session map
                        studentMap.get(sessionName).add(student);
                        nbStudents++;
                    } else {
                        User teacher = getUserFromCn(memberCn);

                        // Add teacher to the 'cours' org
                        addUserToOrgMap(teacher, coursOrg.getOrganizationId());
                    }
                }
            }
            logger.info(" -> Found " + nbStudents + " students in session " + sessionName);
        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing sessions", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error while synchronizing session " + sessionName, e);
        }
    }

    private File getLatestFile(String schoolId) {

        // Parse input directory
        // If multiple files for given schoolId, archive old ones and return the latest
        File horairesDir = new File("/home/entnero/horaires");
        File[] filesTab = horairesDir.listFiles();
        if (filesTab != null && filesTab.length > 0) {

            // Filter on given schoolId
            List<File> filteredFiles = new ArrayList<>();
            for (File file : filesTab) {
                if (file.getName().contains(schoolId)) {
                    filteredFiles.add(file);
                }
            }

            if (filteredFiles.isEmpty()) {
                return null;
            } else if (filteredFiles.size() == 1) {
                return filteredFiles.get(0);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    File latestFile = null;
                    Date maxDate = sdf.parse("01.01.2020");

                    // First loop to get the latest date
                    String maxDateStr = "";
                    for (File file : filteredFiles) {
                        // Extract date in file Name. Format is UO0027_12.05.2022_ExportHorairesMaitre.csv
                        String fullFileName = file.getName();
                        logger.info("Existing file " + fullFileName);
                        String[] fileNameTab = fullFileName.split("_");
                        if (fileNameTab.length == 3) {
                            Date fileDate = sdf.parse(fileNameTab[1]);
                            if (fileDate.after(maxDate)) {
                                logger.info("This is latest !");
                                maxDate = fileDate;
                                maxDateStr = fileNameTab[1];
                                latestFile = file;
                            }
                        }
                    }

                    // Second loop to archive the oldest files
                    for (File file : filteredFiles) {
                        if (!file.getName().contains(maxDateStr)) {
                            archiveFile(file);
                        }
                    }

                    logger.info("Processing file " + latestFile.getName());
                    return latestFile;

                } catch (Exception e) {
                    logger.error("Error parsing input file");
                }
            }
        }
        return null;
    }

    private void archiveFile(File sourceFile) {
        logger.info("Archiving file " + sourceFile.getName());

        // Create archive directory if it does not exist
        File archiveDir = new File("/home/entnero/horaires/archives/");
        if (!archiveDir.exists()) {
            archiveDir.mkdir();
        }

        File destinationFile = new File(archiveDir, sourceFile.getName());

        try (InputStream inStream = new FileInputStream(sourceFile);
             OutputStream outStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[10240];

            int length;
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }

            // Delete the original file
            boolean isDeleted = sourceFile.delete();

            if (isDeleted) {
                logger.info("File "+sourceFile.getAbsolutePath()+" is successfully archived");
            } else {
                logger.info("File "+sourceFile.getAbsolutePath()+" is not archived");
            }
        } catch (IOException e) {
            logger.error("Error when moving EDT file " + sourceFile.getAbsolutePath() + "to the archive directory "+archiveDir.getAbsolutePath(), e);
        }

    }


    private boolean isFileValid (List<String> csvLines) {

        // File is not valid if it contains less than 100 lines
        if (csvLines == null || csvLines.size() < 100) {
            logger.info("File contains less than 100 lines");
            return false;
        }

        // File is not valid if it does not contain any 'é' character
        // -> used to check encoding is good
        boolean hasAccentedChar = false;
        boolean hasSeparatorChar = false;
        for (String line : csvLines) {
            if (line.contains("\u00e9")) {
                hasAccentedChar = true;
            }
            if (line.contains(CSV_SEPARATOR)) {
                hasSeparatorChar = true;
            }
        }
        if (!hasAccentedChar) {
            logger.info("File does not contain any 'é' char -> probably an encoding issue");
            return false;
        }
        if (!hasSeparatorChar) {
            logger.info("File does not contain any separator char (" + CSV_SEPARATOR + ")");
            return false;
        }

        // File is not valid if it does not contain the separator character

        return true;
    }

    private void synchronizeHoraires(Organization school, String schoolId) {

        logger.info("Start parsing access csv file");

        File csvFile = getLatestFile(schoolId);
        if (csvFile == null) {
            logger.info("Not found any file for schoolId " + schoolId);
            return;
        }

        List<String> csvLines = GVESynchronizationUtils.getFileContent(csvFile);
        if (!isFileValid(csvLines)) {
            logger.error("File is not valid -> skipping");
        }

        // 1st line contains the column names
        // Loop over other lines
        for (int i = 1 ; i < csvLines.size() ; i++) {
            String csvLine = csvLines.get(i);
            logger.info(csvLine);
            String[] csvLineTab = csvLine.split(CSV_SEPARATOR);

            //  - Column 0 : slot
            //  - Column 1 : sessionName
            //  - Column 2 : frequency number (unused)
            //  - Column 3 : frequency label
            //  - Column 4 : room
            //  - Column 5 : room type (unused)
            //  - Column 6 : school code (unused)
            //  - Column 7 : class name
            //  - Column 8 : teacher 4-letter name (unused)
            //  - Column 9 : teacher SIRH
            //  - Column 10 : teacher fullname
            //  - Column 11 : teacher name
            //  - Column 12 : teacher givenName
            //  - Column 13 : subject

            String slot = csvLineTab[0];
            String sessionName = csvLineTab[1];
            String frequency = csvLineTab[3];
            String room = csvLineTab[4];
            String className = csvLineTab[7];
            String teacherId = csvLineTab[9];
            String subject = "";
            if (csvLineTab.length >= 14) {
                subject = csvLineTab[13];
            }

            String shortSessionName = getSessionName(sessionName);

            User teacher = LDAPMappingLocalServiceUtil.getUserFromUID(teacherId);
            if (teacher == null) {
                String lastName = csvLineTab[11];
                String firstName = csvLineTab[12];
                logger.error("Unknown teacher SIRH " + teacherId + " -> identifying him by lastName " + lastName + " and firstName " + firstName);
                teacher = identifyTeacher(school.getOrganizationId(), lastName, firstName);
                if (teacher == null) {
                    logger.error("===REPORT=== Maitre absent de l'EEL : SIRH " + teacherId + ", lastName " + lastName + " and firstName " + firstName);
                    reportData.addTeacher(teacherId);
                    continue;
                }
            }

            Organization classOrg = null;
            try {
                String fullClassName = school.getName() + " - " + className;
                classOrg = OrganizationLocalServiceUtil.getOrganization(companyId, fullClassName);
            } catch (Exception e) {
                logger.error("===REPORT=== Unknown class " + className + " in EEL");
                continue;
            }


            // Build structure
            // Map<String shortSessionName, Map<String slot, SlotData>>

            // In each SlotData, add pairs of teacherId-frequency
            // in order to manage co-teaching on different frequencies

            boolean isSessionRegistered = false;
            for (Map.Entry<String, Map<String, List<GVESynchronizationManager.SlotData>>> entry : coursMap.entrySet()) {

                String currentShortSessionName = entry.getKey();
                if (currentShortSessionName.equals(shortSessionName)) {

                    // Loop over slots
                    for (String _slot : entry.getValue().keySet()) {
                        if (_slot.equals(slot)) {

                            // Loop over SlotData
                            for (SlotData slotData : coursMap.get(currentShortSessionName).get(_slot)) {

                                if (slotData.getRoom().equals(room)) {
                                    isSessionRegistered = true;

                                    // For logging purpose
                                    for (TeacherFrequency teacherFrequency : slotData.getTeacherFrequencyList()) {
                                        if (teacherFrequency.getTeacherId() != teacher.getUserId()) {
                                            logger.info(" >>> THIS IS CO-TEACHING");
                                        }
                                    }

                                    slotData.addTeacherFrequency(teacher.getUserId(), frequency, sessionName);
                                    slotData.addParentClassGroupId(classOrg.getGroupId());
                                    slotData.addParentClassOrgId(classOrg.getOrganizationId());
                                }
                            }
                        }
                    }
                }
            }

            if (isSessionRegistered) {
                continue;
            }

            if (!coursMap.containsKey(shortSessionName)) {
                coursMap.put(shortSessionName, new HashMap<>());
            }

            if (!coursMap.get(shortSessionName).containsKey(slot)) {
                coursMap.get(shortSessionName).put(slot, new ArrayList<>());
            }

            // Add SlotData
            SlotData slotData = new SlotData();
            slotData.setRoom(room);
            slotData.setSubject(subject);
            slotData.addTeacherFrequency(teacher.getUserId(), frequency, sessionName);

            slotData.addParentClassGroupId(classOrg.getGroupId());
            slotData.addParentClassOrgId(classOrg.getOrganizationId());

            coursMap.get(shortSessionName).get(slot).add(slotData);
        }
    }


    private String getSessionName(String sessionName) {

        String newSessionName = sessionName;

        // Replace all special (§ and +) chars by '_'
        newSessionName = newSessionName.replace("\u00A7", "_").replace("\\+", "_");
        logger.info("getSessionName temp is " + newSessionName);

        // If session name ends with G1 or G2, keep the suffix
        // Else if 2 letters then 4 numerics, then shorten to 6 chars
        // Else if 2 letters then 1 special char then 4 numerics, then shorten to 7 chars
        // Else, keep the cours name

        String regex1 = "[A-Z]{2}[0-9]{4}.*";
        String regex2 = "[A-Z]{2}[_.+]{1}[0-9]{4}.*";

        if (newSessionName.endsWith("G1") || newSessionName.endsWith("G2")) {
            logger.info("======================= KEEP FULL NAME ===================");
            // Nothing so that we generate groups with complete session name
        }
        // If session is 2 letters and 4 numerics, shorten to 6 chars
        else if (Pattern.matches(regex1, newSessionName)) {
            logger.info("======================= SHORTEN TO 6 ===================");
            newSessionName = newSessionName.substring(0, (Math.min(newSessionName.length(), 6)));
        }
        // If session is 2 letters, a special char and 4 numerics, shorten to 7 chars
        else if (Pattern.matches(regex2, newSessionName)) {
            logger.info("======================= SHORTEN TO 7 ===================");
            newSessionName = newSessionName.substring(0, (Math.min(newSessionName.length(), 7)));
        } else {
            // Language or science groups -> keep full name
            logger.error("--------- ELSE");
        }
        logger.info("sessionName " + sessionName + " transformed into " + newSessionName);
        return newSessionName;

    }

    // TODO Cdt
    /*private void createHoraires(Organization school) {

        final DateFormat fullFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        int classIndex = 0;
        logger.info("About to build CDT sessions for " + coursMap.size() + " cours");

        // For obsolete cours orgs deletion
        List<Integer> types = new ArrayList<Integer>();
        types.add(OrgConstants.COURS_TYPE);
        List<Organization> existingCoursOrgs = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, null, false);
        logger.info("There are " + existingCoursOrgs.size() + " existing cours organizations");
        List<Long> newCoursOrgIds = new ArrayList<Long>();

        for (String coursName : coursMap.keySet()) {

            String fullCoursName = school.getName() + " - " + coursName;

            Organization coursOrg = null;
            try {
                coursOrg = OrganizationLocalServiceUtil.getOrganization(companyId, fullCoursName);
                newCoursOrgIds.add(coursOrg.getOrganizationId());
            } catch (Exception e) {
                logger.error("===REPORT=== Org does not exist : " + fullCoursName);
                reportData.addSmogCours(coursName);
                continue;
            }

            logger.info("===========================================================================");
            logger.info("Building CDT sessions for cours " + coursOrg.getName() + " (id "+coursOrg.getOrganizationId()+")");
            classIndex++;

            // Get the existing sessions for this cours, from now at 6 in the morning to the schoolyear's end
            Calendar coursCal = Calendar.getInstance();
            coursCal.setTime(new Date());
            coursCal.set(Calendar.HOUR, 6);
            coursCal.set(Calendar.MINUTE, 0);
            coursCal.set(Calendar.SECOND, 0);
            Date coursStartDate = coursCal.getTime();
            List<CDTSession> existingCoursSessions = CDTSessionLocalServiceUtil.getGroupSessions(coursOrg.getGroupId(), coursStartDate, schoolYearEndDate, false);

            // Will contain all processed sessionIds
            List<Long> newSessionIds = new ArrayList<Long>();

            // Loop over slots
            int slotIndex = 0;
            Map<String, List<SlotData>> slotMap = coursMap.get(coursName);
            for (String slot : slotMap.keySet()) {

                slotIndex++;

                for (SlotData slotData : slotMap.get(slot)) {

                    String room = slotData.getRoom();

                    // Convert slot to startTime/endTime
                    List<SessionInfos> sessionInfosList = getSessionInfos(slot, slotData);

                    logger.info("");
                    logger.info("===================================================================================================");
                    logger.info("Building CDT sessions for class " + coursOrg.getName() + " (" +classIndex+ "/" + coursMap.size()+ ")"
                            + ", slot " + slot + " (" +slotIndex+ "/" +slotMap.size()+"), room " + room + ", sessionName = " + coursName
                            + " with subject " + slotData.getSubject()
                            + " and parentClassGroupIds = " + slotData.getParentClassGroupIds().toString());

                    // Add teachers into cours + parent classes + volees + school + school-level
                    List<Long> allSlotTeacherIds = getTeacherIds(slotData);
                    for (Long teacherId : allSlotTeacherIds) {
                        try {
                            User teacher = UserLocalServiceUtil.getUser(teacherId);
                            addUserToOrgMap(teacher, school.getOrganizationId());
                            Organization schoolPersonalsOrg = OrgUtilsLocalServiceUtil.getSchoolPersonalsOrganization(school.getOrganizationId());
                            addUserToOrgMap(teacher, schoolPersonalsOrg.getOrganizationId());
                            Organization schoolTeachersOrg = OrgUtilsLocalServiceUtil.getSchoolTeachersOrganization(school.getOrganizationId());
                            addUserToOrgMap(teacher, schoolTeachersOrg.getOrganizationId());
                            addUserToOrgMap(teacher, coursOrg.getOrganizationId());
                            logger.info("Adding teacher " + teacherId + " to cours " + coursOrg.getName());

                            for (long parentClassOrgId : slotData.getParentClassOrgIds()) {
                                addUserToOrgMap(teacher, parentClassOrgId);
                                logger.info("Adding teacher " + teacherId + " to parent orgId " + parentClassOrgId);
                            }

                            String volee = extractVolee(coursName);
                            if (!volee.equals("")) {
                                Organization voleeOrg = OrganizationLocalServiceUtil.getOrganization(companyId, school.getName() + " - " + volee);
                                addUserToOrgMap(teacher, voleeOrg.getOrganizationId());
                                logger.info("Adding teacher " + teacherId + " to volee " + voleeOrg.getName());
                            }

                            setSynchroDate(teacher);

                        } catch (Exception e) {
                            logger.info("Error adding teacher " + teacherId + " to session orgs ", e);
                        }
                    }


                    // Existing sessions for this cours org : from today at 6 in the morning to the schoolyear'end date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    cal.set(Calendar.HOUR_OF_DAY, 6);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Date startDate = cal.getTime();
                    List<CDTSession> existingSessions = new ArrayList<CDTSession>(CDTSessionLocalServiceUtil.getGroupSessions(coursOrg.getGroupId(), startDate, schoolYearEndDate, false));
                    logger.info("Found " + existingSessions.size() + " existing sessions");

                    logger.info("There is " + sessionInfosList.size() + " session infos to process");
                    for (SessionInfos sessionInfos : sessionInfosList) {

                        List<Long> teacherIdList = sessionInfos.getTeacherIds();

                        // tmp
                        String teacherIdsStr = "";
                        for (Long teacherId : teacherIdList) {
                            teacherIdsStr += teacherId + ",";
                        }
                        logger.info("-----------------------");
                        logger.info("Loop over sessionInfos with fullCoursName " + sessionInfos.getFullCoursName() + " and teachers " + teacherIdsStr);
                        //end tmp


                        CDTSession existingSession = getExistingSession(existingSessions, sessionInfos.getStartsessionDate(), sessionInfos.getEndSessionDate(), sessionInfos.getFullCoursName(), room);

                        if (existingSession != null) {

                            // EXISTING SESSION
                            logger.info("Found existing session at " + fullFormat.format(sessionInfos.getStartsessionDate()) + " - " + fullFormat.format(sessionInfos.getEndSessionDate()));
                            newSessionIds.add(existingSession.getSessionId());

                            // Update room if needed
                            if (!existingSession.getRoom().equals(room)) {
                                existingSession.setRoom(room);
                                logger.info(" > UPDATE room to " + room);
                                try {
                                    CDTSessionLocalServiceUtil.updateCDTSession(existingSession);
                                } catch (Exception e) {
                                }
                            }

                            // Update teachers if needed, add only if more than 1 sessionInfo (to preserve successive remove/add)
                            updateTeachers(existingSession, teacherIdList, sessionInfosList.size() > 1);

                            // Update parentGroupIds if needed
                            updateParentClassGroupIds(existingSession.getSessionId(), slotData.getParentClassGroupIds());

                            // Add fullCoursName if needed
                            if (!existingSession.getFullCoursName().contains(sessionInfos.getFullCoursName())) {
                                existingSession.setFullCoursName(existingSession.getFullCoursName() + "," + sessionInfos.getFullCoursName());
                                logger.info(" > ADD fullCoursName " + sessionInfos.getFullCoursName());
                                try {
                                    CDTSessionLocalServiceUtil.updateCDTSession(existingSession);
                                } catch (Exception e) {
                                }
                            }

                        } else {
                            // Create CDT Session
                            try {
                                CDTSession createdSession = CDTSessionLocalServiceUtil.createCDTSession(school.getOrganizationId(), coursOrg.getGroupId(), slotData.getSubject(),
                                        sessionInfos.getStartsessionDate(), sessionInfos.getEndSessionDate(), teacherIdList, room, coursName, sessionInfos.getFullCoursName(), "", true, false);
                                logger.info("CREATED SESSION " + createdSession.getSessionId() + " for coursName = " + coursName + " and from " + fullFormat.format(sessionInfos.getStartsessionDate()) + " to " + fullFormat.format(sessionInfos.getEndSessionDate()));
                                newSessionIds.add(createdSession.getSessionId());
                                existingSessions.add(createdSession);

                                // Add parentGroupIds
                                updateParentClassGroupIds(createdSession.getSessionId(), slotData.getParentClassGroupIds());

                            } catch (Exception e) {
                                logger.error("Error creating CDT session", e);
                            }
                        }
                    }

                }

            }

            // Delete obsolete sessions for current cours (if empty description and no homework related and not in the past)
            logger.info("END Processing class " + coursOrg.getName() + " : there was " + existingCoursSessions.size() + " sessions and there are now " + newSessionIds.size());

            for (CDTSession toDeleteSession : existingCoursSessions) {
                if (!newSessionIds.contains(toDeleteSession.getSessionId())) {
                    try {
                        logger.info(" >>> CANDIDATE OBSOLETE SESSION " + toDeleteSession.getSessionId() + " from " + toDeleteSession.getSessionStart().toString() + " to " + toDeleteSession.getSessionEnd());
                        if (toDeleteSession.getIsManual()) {
                            logger.info("Session "+toDeleteSession.getSessionId()+" not deleted because manually created");
                        } else {
                            if (toDeleteSession.getDescription().equals("")
                                    && !HomeworkLocalServiceUtil.hasHomeworksGivenInSession(toDeleteSession.getSessionId())
                                    && !HomeworkLocalServiceUtil.hasHomeworksToDoForSession(toDeleteSession.getSessionId())) {

                                CDTSessionLocalServiceUtil.deleteSessionAndDependancies(toDeleteSession.getSessionId());
                                logger.info(">>> Deleted OBSOLETE SESSION "+toDeleteSession.getSessionId());
                            } else {
                                logger.info("Session "+toDeleteSession.getSessionId()+" not deleted because description is not empty or homeworks are still related to it");
                            }
                        }
                    } catch (Exception e) {
                        logger.error("Error deleting obsolete session", e);
                    }
                }
            }

        }

        // Delete obsolete cours orgs
        // Be careful to not delete cours orgs for which the teacher has not been identified (ex. XXBUDE)
        for (Organization toRemoveOrg : existingCoursOrgs) {
            if (!newCoursOrgIds.contains(toRemoveOrg.getOrganizationId())) {
                logger.info("Should delete obsolete cours org " + toRemoveOrg.getName() + " for school " + school.getName() + " (But for safety reason we keep it for now)");
//				_log.info("Deleting obsolete cours org " + toRemoveOrg.getName() + " for school " + school.getName());
//				try {
//					// Check if it has members
//					long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(toRemoveOrg.getOrganizationId());
//					if (userIds != null && userIds.length > 0) {
//						_log.info("Removing "+userIds.length+" members from organizationId "+toRemoveOrg.getOrganizationId()+"...");
//						UserLocalServiceUtil.unsetOrganizationUsers(toRemoveOrg.getOrganizationId(), userIds);
//					}
//							
//					_log.info("Removing obsolete organization "+toRemoveOrg.getName()+" (with oganizationId "+toRemoveOrg.getOrganizationId());
//					OrganizationLocalServiceUtil.deleteOrganization(toRemoveOrg.getOrganizationId());
//
//				} catch (Exception e) {
//					_log.error("Error when deleting obsolete orgId "+toRemoveOrg.getOrganizationId(), e);
//				}
            }
        }
    }*/

    private String extractVolee (String coursName) {

        if (Pattern.matches("[A-Z]{2}[._][0-9]{4}.*", coursName)) {
            return coursName.substring(3,5);
        } else {
            String volee = coursName.substring(2,4);
            if (OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee)) {
                return volee;
            }
        }
        return "";
    }

    private User identifyTeacher(long schoolId, String lastName, String firstName) {
        List<Long> orgIds = new ArrayList<>();
        orgIds.add(schoolId);

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
        List<User> candidates = null;
        try {
            candidates = UserSearchLocalServiceUtil.searchUsers(lastName + " " + firstName, orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (candidates != null && !candidates.isEmpty()) {
            if (candidates.size() > 1) {
                logger.info("Teacher identification by lastName/firstName matches 2 results for " + lastName + " / " + firstName);
            }
            return candidates.get(0);
        }

        return null;
    }

    // TODO CDT
    /*private void updateTeachers (CDTSession existingCdtSession, List<Long> newTeacherIdList, boolean addOnly) {

        try {
            boolean isDiff = false;
            List<Long> existingTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(existingCdtSession.getSessionId());
            List<Long> teacherIdListToUpdate = new ArrayList<Long>(existingTeacherIds);
            for (Long existingTeacherId : existingTeacherIds) {
                if (!newTeacherIdList.contains(existingTeacherId) && !addOnly) {
                    logger.info("++++ TeacherId is to delete : "+ existingTeacherId);
                    isDiff = true;
                    teacherIdListToUpdate.remove(existingTeacherId);
                }
            }
            for (Long newTeacherId : newTeacherIdList) {
                if (!teacherIdListToUpdate.contains(newTeacherId)) {
                    logger.info("++++ TeacherId is to add : "+ newTeacherId);
                    isDiff = true;
                    teacherIdListToUpdate.add(newTeacherId);
                }
            }
            if (isDiff) {
                SessionTeacherLocalServiceUtil.updateTeacherListForSession(existingCdtSession.getSessionId(), teacherIdListToUpdate);
            }
        } catch (Exception e) {
            logger.error("Error while updating teachers", e);
        }
    }*/

    // TODO CDT
    /*private void updateParentClassGroupIds (Long sessionId, List<Long> newParentClassGroupIds) {
        List<Long> existingParentGroupIds = SessionParentClassLocalServiceUtil.getSessionParentGroupIds(sessionId);

        for (Long newParentGroupId : newParentClassGroupIds) {
            if (!existingParentGroupIds.contains(newParentGroupId)) {
                SessionParentClassLocalServiceUtil.addSessionParentClass(sessionId, newParentGroupId);
                //_log.info(">>> ADDING parentGroupId " + newParentGroupId + " to sessionId " + sessionId);
            }
        }
    }*/

    private List<Long> getTeacherIds (SlotData slotData) {

        List<Long> slotTeacherIds = new ArrayList<>();
        for (TeacherFrequency teacherFrequency : slotData.getTeacherFrequencyList()) {
            if (!slotTeacherIds.contains(teacherFrequency.getTeacherId())) {
                slotTeacherIds.add(teacherFrequency.getTeacherId());
            }
        }
        return slotTeacherIds;
    }

    // TODO CDT
    /*private CDTSession getExistingSession(List<CDTSession> existingSessions, Date startDate, Date endDate, String fullCoursName, String room) {

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        logger.info("getExistingSession for fullCoursName="+fullCoursName + ", startDate " + sdf.format(startDate) + " and endDate " + sdf.format(endDate));

        // First filter on startDate and endDate only
        List<CDTSession> sessionCandidates = new ArrayList<CDTSession>();
        for (CDTSession existingSession : existingSessions) {
            //_log.info("Compare session " + sdf.format(startDate) + " / " + sdf.format(existingSession.getSessionStart()) + " and " + sdf.format(endDate) + " / " + sdf.format(existingSession.getSessionEnd()) + " and " + room + " / " + existingSession.getRoom());
            if (isSameDayAndHour(existingSession.getSessionStart(), startDate)
                    && isSameDayAndHour(existingSession.getSessionEnd(), endDate)) {
                sessionCandidates.add(existingSession);
            }
        }

        if (sessionCandidates.size() == 0) {
            return null;

        } else if (sessionCandidates.size() == 1) {
            // If only 1 result, check that fullCoursName OR room are the same
            logger.info("1 session candidate");
            if (sessionCandidates.get(0).getFullCoursName().contains(fullCoursName) || sessionCandidates.get(0).getRoom().equals(room)) {
                logger.info(" -> fullCoursName or room are the same -> returning this 1 session");
                return sessionCandidates.get(0);
            }
        } else {
            // Else, filter on the fullCoursName
            logger.info("More than 1 candidate -> filtering on fullCoursName");
            for (CDTSession sessionCandidate : sessionCandidates) {
                if (sessionCandidate.getFullCoursName().contains(fullCoursName)) {
                    logger.info("Found fullCoursName -> returning it");
                    return sessionCandidate;
                }
            }
        }
        logger.info("No session candidate");
        return null;
    }*/

    /**
     * Returns true if both Date parameters are the same day and hour
     */
    public static boolean isSameDayAndHour(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
                && cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)
                && cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE));
    }


    // Returns all candidate pairs startDate / endDate / teacherIds
    private List<SessionInfos> getSessionInfos(String slot, SlotData slotData) {

        List<SessionInfos> sessionInfosList = new ArrayList<>();
        //final DateFormat fullFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        logger.info(" >> slot = " + slot);

        // Convert slot to day number and hour number
        int slotDay = getSlotDay(slot);
        String startTimeStr = startSlotMap.get(slot.substring(1, 3));
        logger.info(" >> startTimeStr = " + startTimeStr);
        int slotStartHour = Integer.parseInt(startTimeStr.substring(0, 2));
        int slotStartMinute = Integer.parseInt(startTimeStr.substring(3, 5));
        String endTimeStr = endSlotMap.get(slot.substring(1, 3));
        int slotEndHour = Integer.parseInt(endTimeStr.substring(0, 2));
        int slotEndMinute = Integer.parseInt(endTimeStr.substring(3, 5));


        // First loop over TeacherFrequencies and calculate a list of dates for each frequency
        for (TeacherFrequency teacherFrequency : slotData.getTeacherFrequencyList()) {
            String frequency = teacherFrequency.getFrequency();
            try {
                Date rangeStartDate;
                Date rangeEndDate;
                Date now = new Date();
                Calendar cal = Calendar.getInstance(Locale.FRANCE);
                switch (frequency) {
                    case "1S":
                        rangeStartDate = schoolYearStartDate;
                        rangeEndDate = schoolYearSemesterDate;
                        break;
                    case "2S":
                        rangeStartDate = schoolYearSemesterDate;
                        rangeEndDate = schoolYearEndDate;
                        break;
                    case "Annuel":
                    case "1H":
                    case "2H":
                        rangeStartDate = schoolYearStartDate;
                        rangeEndDate = schoolYearEndDate;
                        break;
                    default:
                        logger.error("Unknown frequency " + frequency);
                        continue;
                }
                if (now.after(rangeStartDate)) {
                    rangeStartDate = now;
                }
                cal.setTime(rangeStartDate);
                cal.set(Calendar.HOUR_OF_DAY, slotStartHour);
                cal.set(Calendar.MINUTE, slotStartMinute);

                // Get first matching day of week
                for (int i = 0 ; i<7 ; i++) {
                    if (cal.get(Calendar.DAY_OF_WEEK) == slotDay + 1) {
                        break;
                    }
                    cal.add(Calendar.DATE, 1);
                }

                while (cal.getTime().before(rangeEndDate)) {

                    if (!isVacation(cal) &&
                            (frequency.equals("Annuel")
                                    || frequency.equals("1S")
                                    || frequency.equals("2S")
                                    || (frequency.equals("1H") && is1H(cal))
                                    || (frequency.equals("2H") && is2H(cal)))) {

                        teacherFrequency.addDate(cal.getTime());
                    }

                    cal.add(Calendar.DATE, 7);
                }

            } catch (Exception e) {
                logger.error("Error processing school year candidate dates for slot " + slot + " and frequency " + frequency, e);
            }
        }


        // Re-loop over TeacherFrequencies and compare dates
        for (int i = 0 ; i <  slotData.getTeacherFrequencyList().size() ; i++) {

            TeacherFrequency teacherFrequency = slotData.getTeacherFrequencyList().get(i);

            for (Date date : teacherFrequency.getDates()) {

                List<Long> teacherIds = new ArrayList<>();
                teacherIds.add(teacherFrequency.getTeacherId());

                // Check if this date belongs to other TeacherFrequencies's range, with same fullCoursName
                if (slotData.getTeacherFrequencyList().size() > 1) {
                    for (int j = 0 ; j <  slotData.getTeacherFrequencyList().size() ; j++) {
                        TeacherFrequency otherTeacherFrequency = slotData.getTeacherFrequencyList().get(j);
                        if (j > i && teacherFrequency.getFullCoursName().equals(otherTeacherFrequency.getFullCoursName())) {
                            // Loop over other teacherfrequency dates
                            List<Date> otherDateList = new ArrayList<>(otherTeacherFrequency.getDates());
                            for (Date otherDate : otherDateList) {
                                if (isSameDayAndHour(date, otherDate)) {

                                    // Add teacher in the list
                                    if (!teacherIds.contains(otherTeacherFrequency.getTeacherId())) {
                                        teacherIds.add(otherTeacherFrequency.getTeacherId());
                                    }

                                    // Remove date from the other's date list so that it will not be processed after
                                    otherTeacherFrequency.getDates().remove(date);
                                }
                            }
                        }
                    }
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                // Start date
                cal.set(Calendar.HOUR_OF_DAY, slotStartHour);
                cal.set(Calendar.MINUTE, slotStartMinute);
                Date startDate = cal.getTime();

                // End date
                cal.set(Calendar.HOUR_OF_DAY, slotEndHour);
                cal.set(Calendar.MINUTE, slotEndMinute);
                Date endDate = cal.getTime();

                SessionInfos sessionInfos = new SessionInfos(startDate, endDate, teacherFrequency.getFullCoursName(), teacherIds);
                sessionInfosList.add(sessionInfos);
            }

        }

        return sessionInfosList;
    }

    private boolean isVacation(Calendar cal) {
        for (Map.Entry<Date, Date> entry : vacations.entrySet()) {
            if ((isSameDayAndHour(cal.getTime(), entry.getKey()) || cal.getTime().after(entry.getKey())) && cal.getTime().before(entry.getValue())) {
                return true;
            }
        }

        return false;
    }

    private boolean is1H(Calendar cal) {
        if (h1Weeks.contains(cal.get(Calendar.WEEK_OF_YEAR))) {
            logger.info("date " + cal.getTime() +" is 1H. Week of year is " + cal.get(Calendar.WEEK_OF_YEAR));
            return true;
        }

        return false;
    }

    private boolean is2H(Calendar cal) {
        if (h2Weeks.contains(cal.get(Calendar.WEEK_OF_YEAR))) {
            logger.info("date " + cal.getTime() +" is 2H. Week of year is " + cal.get(Calendar.WEEK_OF_YEAR));
            return true;
        }
        return false;
    }


    private int getSlotDay(String slot) {
        if (slot.length() != 3) {
            logger.error("Slot has not length 3 : "+slot);
            return -1;
        } else {
            return Integer.parseInt(slot.substring(0, 1));
        }
    }

    private void addStudentRole(User student) {
        if (!RoleUtilsLocalServiceUtil.isStudent(student)) {
            try {
                Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
                long[] studentRoles = new long[1];
                studentRoles[0] = studentRole.getRoleId();
                RoleLocalServiceUtil.addUserRoles(student.getUserId(), studentRoles);
            } catch (Exception e) {
                logger.error("Error giving student role to user " + student.getFullName(), e);
            }
        }
    }

    private void addTeacherRole(User teacher) {
        if (!RoleUtilsLocalServiceUtil.isTeacher(teacher)) {
            try {
                Role teacherRole = RoleUtilsLocalServiceUtil.getTeacherRole();
                long[] teacherRoles = new long[1];
                teacherRoles[0] = teacherRole.getRoleId();
                RoleLocalServiceUtil.addUserRoles(teacher.getUserId(), teacherRoles);
            } catch (Exception e) {
                logger.error("Error giving teacher role to user " + teacher.getFullName(), e);
            }
        }
    }

    private static class SlotData {
        String room;
        String subject;
        List<TeacherFrequency> teacherFrequencyList;
        List<Long> parentClassGroupIds;	// For CDT search
        List<Long> parentClassOrgIds;	// For registering teachers to parent classes

        public SlotData() {
            teacherFrequencyList = new ArrayList<>();
            parentClassGroupIds = new ArrayList<>();
            parentClassOrgIds = new ArrayList<>();
        }

        public void addTeacherFrequency(Long teacherId, String frequency, String fullCoursName) {
            TeacherFrequency teacherFrequency = new TeacherFrequency(teacherId, frequency, fullCoursName);
            this.teacherFrequencyList.add(teacherFrequency);
        }
        public List<TeacherFrequency> getTeacherFrequencyList() {
            return this.teacherFrequencyList;
        }

        public void setRoom(String room) {
            this.room = room;
        }
        public String getRoom() {
            return this.room;
        }

        public void addParentClassGroupId(Long groupId) {
            if (!this.parentClassGroupIds.contains(groupId)) {
                this.parentClassGroupIds.add(groupId);
            }
        }
        public List<Long> getParentClassGroupIds() {
            return parentClassGroupIds;
        }

        public void addParentClassOrgId(Long orgId) {
            if (!this.parentClassOrgIds.contains(orgId)) {
                this.parentClassOrgIds.add(orgId);
            }
        }
        public List<Long> getParentClassOrgIds() {
            return parentClassOrgIds;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
        public String getSubject() {
            return subject;
        }

    }

    private static class TeacherFrequency {
        long teacherId;
        String frequency;
        String fullCoursName;
        List<Date> dateList;

        TeacherFrequency (long teacherId, String frequency, String fullCoursName) {
            this.teacherId = teacherId;
            this.frequency = frequency;
            this.fullCoursName = fullCoursName;
            dateList = new ArrayList<>();
        }

        public long getTeacherId () {
            return this.teacherId;
        }

        public String getFrequency () {
            return this.frequency;
        }

        public String getFullCoursName () {
            return this.fullCoursName;
        }

        public void addDate(Date date) {
            this.dateList.add(date);
        }

        public List<Date> getDates() {
            return this.dateList;
        }
    }


    public Map<String, String> startSlotMap;
    public Map<String, String> endSlotMap;

    private void initSlotMaps(String schoolUAI) {
        if (schoolUAI.equals("UO0301")) { // La Golette
            startSlotMap = new HashMap<>();
            startSlotMap.put("01", "07h40");
            startSlotMap.put("02", "08h30");
            startSlotMap.put("03", "09h20");
            startSlotMap.put("04", "10h20");
            startSlotMap.put("05", "11h10");
            startSlotMap.put("06", "12h00");
            startSlotMap.put("07", "12h50");
            startSlotMap.put("08", "13h40");
            startSlotMap.put("09", "14h30");
            startSlotMap.put("10", "15h20");
            startSlotMap.put("11", "16h10");

            endSlotMap = new HashMap<>();
            endSlotMap.put("01", "08h25");
            endSlotMap.put("02", "09h15");
            endSlotMap.put("03", "10h05");
            endSlotMap.put("04", "11h05");
            endSlotMap.put("05", "11h55");
            endSlotMap.put("06", "12h45");
            endSlotMap.put("07", "13h35");
            endSlotMap.put("08", "14h25");
            endSlotMap.put("09", "15h15");
            endSlotMap.put("10", "16h05");
            endSlotMap.put("11", "16h55");
        } else if (schoolUAI.equals("UO0188")) { // Drize
            startSlotMap = new HashMap<>();
            startSlotMap.put("01", "08h30");
            startSlotMap.put("02", "09h20");
            startSlotMap.put("03", "10h20");
            startSlotMap.put("04", "11h10");
            startSlotMap.put("05", "12h00");
            startSlotMap.put("06", "12h55");
            startSlotMap.put("07", "13h45");
            startSlotMap.put("08", "14h35");
            startSlotMap.put("09", "15h25");
            startSlotMap.put("10", "16h20");

            endSlotMap = new HashMap<>();
            endSlotMap.put("01", "09h15");
            endSlotMap.put("02", "10h05");
            endSlotMap.put("03", "11h05");
            endSlotMap.put("04", "11h55");
            endSlotMap.put("05", "12h45");
            endSlotMap.put("06", "13h40");
            endSlotMap.put("07", "14h30");
            endSlotMap.put("08", "15h20");
            endSlotMap.put("09", "16h10");
            endSlotMap.put("10", "17h10");
        } else {
            startSlotMap = new HashMap<>();
            startSlotMap.put("01", "07h55");
            startSlotMap.put("02", "08h45");
            startSlotMap.put("03", "09h35");
            startSlotMap.put("04", "10h35");
            startSlotMap.put("05", "11h25");
            startSlotMap.put("06", "12h50");
            startSlotMap.put("07", "13h45");
            startSlotMap.put("08", "14h35");
            startSlotMap.put("09", "15h30");
            startSlotMap.put("10", "16h20");
            startSlotMap.put("11", "17h10");

            endSlotMap = new HashMap<>();
            endSlotMap.put("01", "08h40");
            endSlotMap.put("02", "09h30");
            endSlotMap.put("03", "10h20");
            endSlotMap.put("04", "11h20");
            endSlotMap.put("05", "12h10");
            endSlotMap.put("06", "13h35");
            endSlotMap.put("07", "14h30");
            endSlotMap.put("08", "15h15");
            endSlotMap.put("09", "16h15");
            endSlotMap.put("10", "17h05");
            endSlotMap.put("11", "17h55");
        }

    }

    public static class SessionInfos {
        Date startsessionDate;
        Date endSessionDate;
        String fullCoursName;
        List<Long> teacherIds;

        public SessionInfos(Date startDate, Date endDate, String fullCoursName, List<Long> teacherIds) {
            this.startsessionDate = startDate;
            this.endSessionDate = endDate;
            this.fullCoursName = fullCoursName;
            this.teacherIds = teacherIds;
        }

        public Date getStartsessionDate() {
            return startsessionDate;
        }

        public Date getEndSessionDate() {
            return endSessionDate;
        }

        public String getFullCoursName() {
            return fullCoursName;
        }

        public List<Long> getTeacherIds() {
            return teacherIds;
        }

    }

    public LdapContext getContext(long companyId) {

        if (ctx != null) {
            return ctx;
        }

        String baseProviderURL 	= PropsUtil.get(GVE_LDAP_BASE_PROVIDER_URL);
        String principal 		= PropsUtil.get(GVE_LDAP_SECURITY_PRINCIPAL);
        String credentials 		= PropsUtil.get(GVE_LDAP_SECURITY_CREDENTIALS);

        Properties environmentProperties = new Properties();

        environmentProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.ldap.LdapCtxFactory");
        environmentProperties.put(Context.PROVIDER_URL, baseProviderURL);
        environmentProperties.put(Context.SECURITY_PRINCIPAL, principal);
        environmentProperties.put(Context.SECURITY_CREDENTIALS, credentials);
        // environmentProperties.put(Context.REFERRAL, PropsUtil.get(LDAP_REFERRAL));

        try {
            ctx = new InitialLdapContext(environmentProperties, null);
        }
        catch (Exception e) {
            logger.warn("Failed to bind to the LDAP server", e);
        }

        return ctx;
    }

    /**
     * Initializes all variables for synchronization
     */
    private void initSynchronization () {

        cnUserMap = new HashMap<>();
        coursMap = new HashMap<>();
        createdUserList = new ArrayList<>();
        userOrgMap = new HashMap<>();
        studentMap = new HashMap<>();
        personalCnList = new ArrayList<>();
        reportData = new ReportData();

        try {
            rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(companyId);
        } catch (Exception e) {
            logger.error("Error while retrieving root org", e);
        }

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            schoolYearStartDate = sdf.parse("22/08/2022");
            schoolYearSemesterDate = sdf.parse("22/01/2023"); // The sunday before changing semester date
            schoolYearEndDate = sdf.parse("01/07/2023");
        } catch (ParseException e1) {
            logger.debug(e1);
        }

        h1Weeks = new ArrayList<>();
        h1Weeks.add(34);
        h1Weeks.add(36);
        h1Weeks.add(38);
        h1Weeks.add(40);
        h1Weeks.add(42);
        h1Weeks.add(45);
        h1Weeks.add(47);
        h1Weeks.add(49);
        h1Weeks.add(51);
        h1Weeks.add(3);
        h1Weeks.add(5);
        h1Weeks.add(7);
        h1Weeks.add(10);
        h1Weeks.add(12);
        h1Weeks.add(14);
        h1Weeks.add(18);
        h1Weeks.add(20);
        h1Weeks.add(22);
        h1Weeks.add(24);
        h1Weeks.add(26);

        h2Weeks = new ArrayList<>();
        h2Weeks.add(35);
        h2Weeks.add(37);
        h2Weeks.add(39);
        h2Weeks.add(41);
        h2Weeks.add(44);
        h2Weeks.add(46);
        h2Weeks.add(48);
        h2Weeks.add(50);
        h2Weeks.add(2);
        h2Weeks.add(4);
        h2Weeks.add(6);
        h2Weeks.add(9);
        h2Weeks.add(11);
        h2Weeks.add(13);
        h2Weeks.add(17);
        h2Weeks.add(19);
        h2Weeks.add(21);
        h2Weeks.add(23);
        h2Weeks.add(25);

        // Vacations
        // First day is the first day of vacation period
        // Second day is the first day of work after the vacation period
        vacations = new HashMap<>();
        try {
            vacations.put(sdf.parse("08/09/2022"), sdf.parse("09/09/2022"));
            vacations.put(sdf.parse("24/10/2022"), sdf.parse("31/10/2022"));
            vacations.put(sdf.parse("25/12/2022"), sdf.parse("09/01/2023"));
            vacations.put(sdf.parse("20/02/2023"), sdf.parse("27/02/2023"));
            vacations.put(sdf.parse("07/04/2023"), sdf.parse("24/04/2023"));
            vacations.put(sdf.parse("01/05/2023"), sdf.parse("02/05/2023"));
            vacations.put(sdf.parse("18/05/2023"), sdf.parse("22/05/2023"));
            vacations.put(sdf.parse("29/05/2023"), sdf.parse("30/05/2023"));
        } catch (Exception e) {
            logger.error("Error parsing vacations", e);
        }
    }

    private void addUserToOrgMap(User user, long orgId) {

        if (user == null) {
            return;
        }
        if (userOrgMap.containsKey(user)) {
            if (!userOrgMap.get(user).contains(orgId)) {
                userOrgMap.get(user).add(orgId);
            }
        } else {
            List<Long> orgIdList = new ArrayList<>();
            orgIdList.add(orgId);
            userOrgMap.put(user, orgIdList);
        }
    }

    private String extractCn (String fullCn) {
        // fullCn is 'cn=AAA,ou=BBB,o=CCC,dc=EEL'
        String[] cnTab = fullCn.split(",");
        return cnTab[0].substring(3);
    }

    private void setSynchroDate(User user) {
        // Add user in the 'done' list
        processedUserIds.add(user.getUserId());

        try {
            if (user.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
                UserLocalServiceUtil.updateStatus(user.getUserId(), WorkflowConstants.STATUS_APPROVED, new ServiceContext());
                logger.info("Reactivating user " + user.getFullName());
            }
            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            userProperties.setLastSynchroDate(new Date());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);
        } catch (Exception e) {
            logger.error("Error setting synchro date to user " + user.getUserId(), e);
        }
    }

    // Once all schools have been processed,
    // - deactivate students (+ their parents) that dot belong anymore to these schools
    // - deactivate agents that dot belong anymore to these schools
    private void deactivateObsoleteUsers() {

        logger.info("Start obsolete users deactivation for " + processedSchoolIds.size() + " schools");
        logger.info("The whole sync has processed " + processedUserIds.size() + " users");

        // Loop over active students and deactivate the ones that were not previously processed
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
        try {
            List<User> allStudents = UserSearchLocalServiceUtil.searchUsers("", processedSchoolIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            logger.info("There is a total of " + allStudents.size() + " students");
            for (User student : allStudents) {
                if (processedUserIds.contains(student.getUserId())) {
                    continue;
                }
                logger.info("Student " + student.getFullName() + " may be deactivated");
                UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(student.getUserId());
                if (userProperties.getManualAccount()) {
                    continue;
                }
                UserLocalServiceUtil.updateStatus(student.getUserId(), WorkflowConstants.STATUS_INACTIVE, new ServiceContext());
                logger.info("Deactivated student " + student.getUserId());
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(student.getUserId());
                for (User parent : parents) {
                    // Deactivate parent if not other active child
                    if (UserRelationshipLocalServiceUtil.getChildren(parent.getUserId()).isEmpty()) {
                        UserLocalServiceUtil.updateStatus(parent.getUserId(), WorkflowConstants.STATUS_INACTIVE, new ServiceContext());
                        logger.info(" -> Deactivated his/her parent " + parent.getFullName() + "(id " + parent.getUserId() + ")");
                    } else {
                        logger.info("Do not deactivate parent " + parent.getFullName() + " because other active child");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error processing obsolete students deactivation", e);
        }

        // Loop over agents and deactivate the ones that were not previously processed
//		roleIds = RoleUtilsLocalServiceUtil.getAgentsRoleIds();
//		try {
//			List<User> allAgents = UserSearchLocalServiceUtil.searchUsers("", processedSchoolIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
//			_log.info("There is a total of " + allAgents.size() + " agents");
//			for (User agent : allAgents) {
//				if (processedUserIds.contains(agent.getUserId())) {
//					continue;
//				}
//				UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(agent.getUserId());
//				if (userProperties.getManualAccount()) {
//					continue;
//				}
//				UserLocalServiceUtil.updateStatus(agent.getUserId(), WorkflowConstants.STATUS_INACTIVE);
//				_log.info("Deactivated agent " + agent.getFullName() + " (userId " + agent.getUserId() + ")");
//			}
//		} catch (Exception e) {
//			_log.error("Error processing obsolete students deactivation", e);
//		}

    }

    private void initCreatedAccounts() {

        logger.info("Init created accounts");

        for (User createdUser : createdUserList) {

            try {
                logger.info("Init new user " + createdUser.getFullName());

                // Send welcome message
                //InternalMessageLocalServiceUtil.sendWelcomeMessage(createdUser);

                // Mark latest ent news as read so that it does not pop at first connection
                // TODO Update info
                // EntVersionUserLocalServiceUtil.markLastVersionAsRead(createdUser.getUserId());

            } catch (Exception e) {
                logger.error("Error when initiating created account", e);
            }
        }
    }

    private static class ReportData {
        List<String> smogCours;
        boolean isDirectoryCoursError;
        List<String> unknownSirh;
        List<String> unknownRoles;

        public ReportData() {
            this.smogCours = new ArrayList<>();
            this.isDirectoryCoursError = false;
            this.unknownSirh = new ArrayList<>();
            this.unknownRoles = new ArrayList<>();
        }

        public List<String> getSmogCours() {
            return smogCours;
        }

        public boolean isDirectoryError() {
            return this.isDirectoryCoursError;
        }

        public List<String> getUnknownSirh() {
            return unknownSirh;
        }

        public List<String> getUnknownRoles() {
            return unknownRoles;
        }

        public void addSmogCours(String cours) {
            if (!this.smogCours.contains(cours)) {
                this.smogCours.add(cours);
            }
        }

        public void setDirectoryError(boolean isError) {
            this.isDirectoryCoursError = isError;
        }

        public void addTeacher(String sirh) {
            if (!this.unknownSirh.contains(sirh)) {
                this.unknownSirh.add(sirh);
            }
        }

        public void addRole(String role) {
            this.unknownRoles.add(role);
        }

        public boolean hasErrors() {
            return !this.getSmogCours().isEmpty() || (this.getSmogCours().isEmpty() && isDirectoryError()) || !unknownSirh.isEmpty() || !unknownRoles.isEmpty();
        }
    }
    
    public void sendReport(long schoolId) {

        if (!reportData.hasErrors()) {
            logger.info("No error for school " + schoolId + " -> no sending any report");
            return;
        }

        try {
            // Get school directors
            List<Long> organizationIds = new ArrayList<>();
            organizationIds.add(schoolId);
            Role directorRole = RoleUtilsLocalServiceUtil.getDirectionRole();
            Role schoolAdminRole = RoleUtilsLocalServiceUtil.getSchoolAdminRole();
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(directorRole.getRoleId());
            roleIds.add(schoolAdminRole.getRoleId());
            List<User> directors = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

            User noReplyUser = UserLocalServiceUtil.getUser(Long.parseLong(PropsUtil.get(NeroSystemProperties.MAIL_NO_REPLY_USER_ID)));
            List<Long> recipientList = new ArrayList<>();
            for (User director : directors) {
                if (!recipientList.contains(director.getUserId())) {
                    logger.info("Destinataire du rapport : " + director.getFullName());
                    recipientList.add(director.getUserId());
                }
            }
            String subject = "Anomalies lors de la synchronisation des profils des agents et des horaires";
            StringBuilder content = new StringBuilder("Bonjour,<br><br>"
                    + "Des erreurs ont \u00e9t\u00e9 d\u00e9tect\u00e9es lors de la synchronisation des profils des agents et des horaires pour votre \u00e9tablissement, en date du " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ".<br><br>");

            // Unknown teachers
            if (!reportData.getUnknownSirh().isEmpty()) {
                content.append("Liste d'enseignants pr\u00e9sents dans SMOG et non reconnus dans l'ENTA : ");
                for (String sirh : reportData.getUnknownSirh()) {
                    content.append(sirh).append(", ");
                }
                content = new StringBuilder(content.substring(0, content.length() - 2));
                content.append("<br><br>");
            }

            // Unknown roles
            if (!reportData.getUnknownRoles().isEmpty()) {
                content.append("Liste des \"Espaces scolaires / activit\u00e9s scolaires\" absents ou mal orthographi\u00e9s dans Gina Manager : ");
                for (String role : reportData.getUnknownRoles()) {
                    content.append(role).append(", ");
                }
                content = new StringBuilder(content.substring(0, content.length() - 2));
                content.append("<br><br>");
            }

            // Badly formatted cours in SMOG
            if (!reportData.getSmogCours().isEmpty()) {
                content.append("Liste des noms de cours ne respectant pas la nomenclature dans la base SMOG : <br><br>");
                int nbCours = 0;
                for (String cours : reportData.getSmogCours()) {
                    content.append(cours).append(", ");
                    nbCours++;
                    if (nbCours == 10) {
                        content.append("<br>");
                        nbCours = 0;
                    }
                }
                content = new StringBuilder(content.substring(0, content.length() - 2));
                content.append("<br><br>");
            }

            // If no cours error in SMOG but error in EEL, print message
            if (reportData.getSmogCours().isEmpty() && reportData.isDirectoryError()) {
                content.append("Les modifications que vous avez r\u00e9alis\u00e9es dans SMOG ne sont pas encore prises en charge par l'annuaire EEL. Pour rappel <b>un d\u00e9lai de 48h</b> est n\u00e9cessaire.<br><br>");
            }

            content.append("Bien cordialement,<br>L'\u00e9quipe technique");

            // TODO Messaging
            // InternalMessageLocalServiceUtil.sendInternalMessage(noReplyUser, recipientList, subject, content, null, 0, 0);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi du rapport de synchronisation pour schoolId " + schoolId, e);
        }
    }

    private static final String GVE_LDAP_BASE_PROVIDER_URL = "gve.ldap.base.provider.url";
    private static final String GVE_LDAP_SECURITY_PRINCIPAL = "gve.ldap.security.principal";
    private static final String GVE_LDAP_SECURITY_CREDENTIALS = "gve.ldap.security.credentials";

    private static final String GVE_LDAP_BASE_DN_GROUPS = "gve.ldap.base.dn.groups";

    private static final String GVE_LDAP_CLASS_FILTER = "(objectClass=ETATGEgroupOfNames)";

    private static final String ETAT_GE_PROPRIETAIRE = "ETATGEproprietaire";
    private static final String MEMBER = "member";

    private static final String CSV_SEPARATOR = ",";

    static LdapContext ctx = null;
    
}