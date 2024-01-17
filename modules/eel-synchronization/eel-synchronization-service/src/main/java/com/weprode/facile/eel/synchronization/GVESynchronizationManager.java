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

package com.weprode.facile.eel.synchronization;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.ldap.util.LDAPUtil;
import com.weprode.facile.about.service.UserReadVersionNoteLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.model.MessagingConfig;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.messaging.service.MessagingConfigLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.model.OrgMapping;
import com.weprode.facile.organization.service.ClassCoursMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.preference.model.NotifyConfig;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.constants.NeroRoleConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.model.SlotConfiguration;
import com.weprode.facile.schedule.model.Subject;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.facile.schedule.service.HolidayLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.schedule.service.SlotConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.SubjectLocalServiceUtil;
import com.weprode.facile.schedule.service.TeacherSubjectLocalServiceUtil;
import com.weprode.facile.user.model.LDAPMapping;
import com.weprode.facile.user.service.AffectationLocalServiceUtil;
import com.weprode.facile.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
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

    // Structure built for sessions creations
    private Map<String, Map<String, List<SlotData>>> coursMap;

    // For a given sessionName, list all students
    private Map<String, List<User>> studentMap;

    // Used to generate report / send welcome messages
    private List<User> createdUserList;

    private long companyId;
    private Organization rootOrg;
    private boolean sendReport;

    private Map<String, String> startSlotMap;
    private Map<String, String> endSlotMap;

    private List<Integer> h1Weeks;
    private List<Integer> h2Weeks;
    private Map<Date, Date> vacations;

    ReportData reportData;

    /**
     * Run the synchronization
     */
    public void runSynchronization(boolean doSendReport) {

        companyId = PortalUtil.getDefaultCompanyId();
        sendReport = doSendReport;

        try {
            // Loop over schools
            String schoolsList = PropsUtil.get(NeroSystemProperties.SYNCHRO_SCHOOLS);
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
                MessageLocalServiceUtil.sendMessage(administrator.getUserId(), recipientList, subject, content);
            } catch (Exception e1) {
                logger.error("Couldn't find admin user", e1);
            }
        } catch (Exception e) {
            logger.error("Error while synchronizing : ", e);
        }

        logger.info("Synchronization END");
    }

    private void processSchool(String schoolId) throws NamingException, PortalException {

        initSchoolSynchronization();

        String schoolDn = "OU=" + schoolId + "," + PropsUtil.get(NeroSystemProperties.SYNCHRO_LDAP_BASE_DN_GROUPS);
        Organization school = synchronizeSchool(schoolId);
        if (school == null) {
            return;
        }
        processedSchoolIds.add(school.getOrganizationId());

        initSlotMaps(school.getOrganizationId());

        String classesDn = "OU=CLASSES," + schoolDn;
        synchronizeClasses(school, classesDn);
        synchronizeVolees(school, classesDn);

        String subjectsDn = "OU=DISCIPLINES,OU=ENSEIGNANTS," + schoolDn;
        synchronizeTeacherSubjects(school, subjectsDn);

        String sessionsDn = "OU=COURS," + schoolDn;
        synchronizeSessions(school, sessionsDn);

        // Guests
        synchronizeStudentGuests(school, schoolDn);
        synchronizeTeacherGuests(school, schoolDn);

        // Roles
        synchronizeRole(school, "CN=ENT-DIRECTEURS,OU=ENT-DIRECTEURS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.DIRECTION);
        synchronizeRole(school, "CN=ENT-DIRECTEURS,OU=ENT-DIRECTEURS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.SCHOOL_ADMIN);
        synchronizeRole(school, "CN=ENT-ADMINISTRATEURS,OU=ENT-DIRECTEURS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.DIRECTION);
        synchronizeRole(school, "CN=ENT-ADMINISTRATEURS,OU=ENT-DIRECTEURS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.SCHOOL_ADMIN);
        synchronizeRole(school, "CN=ENT-DOYENS,OU=ENT-DOYENS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.DOYEN);

        synchronizeRole(school, "CN=ENT-CONSEILLERS-ORIENTATIONS,OU=ENT-EMPS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CONSEILLER_ORIENTATION);
        synchronizeRole(school, "CN=ENT-CONSEILLERS-SOCIAUX,OU=ENT-EMPS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CONSEILLER_SOCIAL);
        synchronizeRole(school, "CN=ENT-INFIRMIERES,OU=ENT-EMPS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.INFIRMIERE);
        synchronizeRole(school, "CN=ENT-PSYCHOLOGUES,OU=ENT-EMPS,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.PSYCHOLOGUE);

        synchronizeRole(school, "CN=ENT-ASSISTANTS-TECHNIQUES,OU=ENT-PAT,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.ASSISTANT_TECHNIQUE);
        synchronizeRole(school, "CN=ENT-CAISSIERS-COMPTABLES,OU=ENT-PAT,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.CAISSIER_COMPTABLE);
        synchronizeRole(school, "CN=ENT-MEDIATHECAIRES,OU=ENT-PAT,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.BIBLIOTHECAIRE);
        synchronizeRole(school, "CN=ENT-SECRETAIRES,OU=ENT-PAT,OU=ESPACES-SCOLAIRES," + schoolDn, NeroRoleConstants.SECRETAIRE);

        if (synchronizeHoraires(school, schoolId)) {
            createHoraires(school);
        }

        synchronizeUserOrgs();

        sendReport(school.getOrganizationId());
    }

    private void initSchoolSynchronization () throws PortalException {

        cnUserMap = new HashMap<>();
        coursMap = new HashMap<>();
        createdUserList = new ArrayList<>();
        userOrgMap = new HashMap<>();
        studentMap = new HashMap<>();
        reportData = new ReportData();
        rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(companyId);
        h1Weeks = ScheduleConfigurationLocalServiceUtil.getH1Weeks();
        h2Weeks = ScheduleConfigurationLocalServiceUtil.getH2Weeks();

        // Vacations
        // First day is the first day of vacation period
        // Second day is the first day of work after the vacation period
        vacations = HolidayLocalServiceUtil.getHolidays();
    }

    private Organization synchronizeSchool (String schoolId) throws NamingException, PortalException {

        // Get school informations
        String schoolDn = "OU=" + schoolId + "," + PropsUtil.get(NeroSystemProperties.SYNCHRO_LDAP_BASE_DN_GROUPS);
        String[] schoolAttributes = {ATTRIBUTE_SCHOOL_DESCRIPTION};
        Organization school = null;
        try {
            Attributes schoolAttrs = getContext().getAttributes(schoolDn, schoolAttributes);
            if (schoolAttrs.get(ATTRIBUTE_SCHOOL_DESCRIPTION) != null) {
                String schoolName = schoolAttrs.get(ATTRIBUTE_SCHOOL_DESCRIPTION).get().toString();
                logger.info("SchoolName is -"+ schoolName + "-");
                schoolName = schoolName.replace("Cycle d'orientation", "CO");
                logger.info("Formatted SchoolName is -"+ schoolName + "-");

                // Create school and school-level orgs
                try {
                    school = OrgUtilsLocalServiceUtil.getOrCreateSchool(companyId, schoolName);
                    logger.info("Found school " + school.getOrganizationId());
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
            // Re-throw Exception to stop synchronization in case of LDAP exception
            throw e;
        } catch (Exception e) {
            logger.error("Error while synchronizing school " + schoolId, e);
            throw new PortalException();
        }

        return school;
    }


    private void synchronizeClasses(Organization school, String classesDn) throws NamingException {

        // For obsolete classes deletion
        List<Long> newClassOrgIds = new ArrayList<>();
        List<Organization> existingClasses = OrgUtilsLocalServiceUtil.getSchoolClasses(school.getOrganizationId(), false);

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over classes
            NamingEnumeration<SearchResult> classesEnum = getContext().search(classesDn, GVE_LDAP_CLASS_FILTER, cons);
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
                Attributes attrs = getContext().getAttributes(classDn, attributeIds);

                // Attribute ETATGEproprietaire is main teacher
                List<String> principalTeacherCns = new ArrayList<>();
                if (attrs.get(ETAT_GE_PROPRIETAIRE) != null) {
                    for (int i = 0 ; i < attrs.get(ETAT_GE_PROPRIETAIRE).size(); i++) {
                        String principalTeacherCn = attrs.get(ETAT_GE_PROPRIETAIRE).get(i).toString();

                        logger.info("Found main teacher " + principalTeacherCn.split(",")[0]);
                        try {
                            User mainTeacher = getUserFromCn(principalTeacherCn);
                            if (mainTeacher != null) {
                                principalTeacherCns.add(principalTeacherCn);
                                addTeacherRole(mainTeacher);

                                // Add main teacher role
                                Role mainTeacherRole = RoleLocalServiceUtil.getRole(mainTeacher.getCompanyId(), NeroRoleConstants.MAIN_TEACHER);
                                UserGroupRoleLocalServiceUtil.addUserGroupRoles(mainTeacher.getUserId(), classOrg.getGroup().getGroupId(), new long[]{mainTeacherRole.getRoleId()});
                                logger.info("Adding teacher " + mainTeacher.getFullName() + " main teacher of class " + classOrg.getName());

                                registerTeacherToSchool(mainTeacher, school);
                                setSynchroDate(mainTeacher);

                                // Add teacher to the class
                                addUserToOrgMap(mainTeacher, classOrg.getOrganizationId());
                            }
                        } catch (Exception e) {
                            logger.error("Error processing teacher " + principalTeacherCn);
                        }
                    }
                }

                // Fetch current members in LDAP
                attributeIds = new String[]{MEMBER};
                attrs = getContext().getAttributes(classDn, attributeIds);

                // All members are class students + the principal teacher
                if (attrs.get(MEMBER) != null) {
                    for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                        String member = attrs.get(MEMBER).get(i).toString();
                        if (!principalTeacherCns.contains(member)) {
                            logger.info("Found member " + member.split(",")[0]);
                            User user = getUserFromCn(member);

                            if (user != null) {

                                if (member.contains("OU=ELEVES") || member.contains("ou=ELEVES")) {

                                    addStudentRole(user);
                                    registerStudentToSchool(user, school);
                                    addUserToOrgMap(user, classOrg.getOrganizationId());
                                    setSynchroDate(user);
                                    newStudentIds.add(user.getUserId());

                                } else if (member.contains("OU=ENSEIGNANTS") || member.contains("ou=ENSEIGNANTS")) {

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
                logger.info("Should delete obsolete class " + toRemoveOrg.getName() + " for school " + school.getName() + " but not doing it for now");
                try {
                    // Comment this because dangerous (to be run after a real sync to have an idea of the number of obsolete organizations)
//                     Check if it has members
//                    long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(toRemoveOrg.getOrganizationId());
//                    if (userIds != null && userIds.length > 0) {
//                        logger.info("Removing "+userIds.length+" members from organizationId "+toRemoveOrg.getOrganizationId()+"...");
//                        UserLocalServiceUtil.unsetOrganizationUsers(toRemoveOrg.getOrganizationId(), userIds);
//                    }
//
//                    logger.info("Removing obsolete organization "+toRemoveOrg.getName()+" (with oganizationId "+toRemoveOrg.getOrganizationId());
//                    OrganizationLocalServiceUtil.deleteOrganization(toRemoveOrg.getOrganizationId());

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
            NamingEnumeration<SearchResult> classesEnum = getContext().search(classesDn, GVE_LDAP_CLASS_FILTER, cons);
            while (classesEnum.hasMoreElements()) {
                SearchResult classResult = classesEnum.nextElement();
                logger.info("Loop over class " + classResult.getName());

                if (classResult.getName().substring(3).equals("HORS-CLASSE") || classResult.getName().startsWith("CLI", 3)) {
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
                Attributes attrs = getContext().getAttributes(classDn, attributeIds);

                // Attribute ETATGEproprietaire is main teacher
                String principalTeacherCn = "";
                if (attrs.get(ETAT_GE_PROPRIETAIRE) != null) {
                    for (int i = 0 ; i < attrs.get(ETAT_GE_PROPRIETAIRE).size(); i++) {
                        principalTeacherCn = attrs.get(ETAT_GE_PROPRIETAIRE).get(i).toString();

                        try {
                            User teacher = getUserFromCn(principalTeacherCn);

                            if (teacher != null) {
                                // Add main teacher role in volee
                                long voleeGroupId = voleeOrg.getGroup().getGroupId();
                                Role mainTeacherRole = RoleLocalServiceUtil.getRole(teacher.getCompanyId(), NeroRoleConstants.MAIN_TEACHER);
                                UserGroupRoleLocalServiceUtil.addUserGroupRoles(teacher.getUserId(), voleeGroupId, new long[]{mainTeacherRole.getRoleId()});
                                logger.info("  Adding teacher " + teacher.getFullName() + " main teacher of volee " + voleeOrg.getName());
                            }
                        } catch (Exception e) {
                            logger.error("Error processing teacher " + principalTeacherCn);
                        }
                    }
                }

                // Fetch current members in LDAP
                attributeIds = new String[]{MEMBER};
                attrs = getContext().getAttributes(classDn, attributeIds);

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


    private void synchronizeTeacherSubjects(Organization school, String subjectsDn) throws NamingException {

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over classes
            NamingEnumeration<SearchResult> subjectsEnum = getContext().search(subjectsDn, GVE_LDAP_CLASS_FILTER, cons);
            while (subjectsEnum.hasMoreElements()) {
                SearchResult subjectResult = subjectsEnum.nextElement();
                String subjectName = subjectResult.getName().substring(3);
                if (subjectName.equals("TOUS")) {
                    continue;
                }

                // Commented out because subjects are not accented, we use the ones from the schedule file
                // Remove dashes
//                subjectName = subjectName.replace("-", " ");
//                // LowerCase + Capitalize subject
//                subjectName = subjectName.substring(0, 1).toUpperCase() + subjectName.substring(1).toLowerCase();
//
//                // Create subject if needed
//                Subject subject = SubjectLocalServiceUtil.getOrCreateSubject(subjectName);

                // Loop over teachers
                String[] attributeIds = {MEMBER};
                String subjectDn = subjectResult.getName() + "," + subjectsDn;
//                logger.info("SubjectDn = "+ subjectDn);
//
//                // Now manage subject groups
//                String subjectOrgName = OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true) + OrgConstants.SUBJECT_ORG_TEACHERS +
//                        (subjectName.startsWith("A") || subjectName.startsWith("E") || subjectName.startsWith("H") || subjectName.startsWith("I") ? " d'" : " de ") + subjectName;
//                Organization subjectOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(school.getCompanyId(), subjectOrgName, school.getOrganizationId(), OrgConstants.SUBJECT_TYPE);

                Attributes attrs = getContext().getAttributes(subjectDn, attributeIds);
                if (attrs.get(MEMBER) != null) {
                    for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                        String memberCn = attrs.get(MEMBER).get(i).toString();
                        logger.info("Found member " + memberCn);

                        // Create teacher if needed
                        try {
                            User teacher = getUserFromCn(memberCn);
                            if (teacher != null) {
                                logger.info("Found teacher : userId = "+ teacher.getUserId());

                                // Add teacher role
                                addTeacherRole(teacher);

                                // Add teacher to subject org
                                //addUserToOrgMap(teacher, subjectOrg.getOrganizationId());

                                setSynchroDate(teacher);
                                registerTeacherToSchool(teacher, school);

                                // Add him/her the subject
                                //TeacherSubjectLocalServiceUtil.addTeacherSubjectInSchool(teacher.getUserId(), subject.getSubjectId(), school.getOrganizationId());
                            }
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


    private void synchronizeStudentGuests(Organization school, String schoolDn) throws NamingException {

        logger.info("======================================================");
        logger.info("Start synchronizeStudentGuests");

        String studentGuestsCn = "CN=INVITES-ELEVES,OU=GLOBAL," + schoolDn;

        try {
            String[] attributeIds = {MEMBER};
            Attributes attrs = getContext().getAttributes(studentGuestsCn, attributeIds);

            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    logger.info("Found student guest " + memberCn);
                    User student = getUserFromCn(memberCn);
                    if (student != null) {
                        addStudentRole(student);
                        setSynchroDate(student);
                        registerStudentToSchool(student, school);
                        logger.info("Added student guest : "+student.getFullName());
                    }
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

        String teacherGuestsCn = "CN=INVITES-ENSEIGNANTS,OU=GLOBAL," + schoolDn;

        try {
            String[] attributeIds = {MEMBER};
            Attributes attrs = getContext().getAttributes(teacherGuestsCn, attributeIds);

            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    logger.info("Found teacher guest " + memberCn);

                    User teacher = getUserFromCn(memberCn);
                    if (teacher != null) {
                        addTeacherRole(teacher);
                        setSynchroDate(teacher);
                        registerTeacherToSchool(teacher, school);
                        logger.info("Added teacher guest : "+teacher.getFullName());
                    }
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
            String[] attributeIds = {ATTRIBUTE_CLASS_MEMBER};
            Attributes attrs = getContext().getAttributes(roleDn, attributeIds);
            Role personalRole = RoleUtilsLocalServiceUtil.getPersonalRole();

            if (attrs.get(ATTRIBUTE_CLASS_MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(ATTRIBUTE_CLASS_MEMBER).size(); i++) {
                    String memberCn = attrs.get(ATTRIBUTE_CLASS_MEMBER).get(i).toString();
                    User user = getUserFromCn(memberCn);
                    if (user != null) {
                        try {
                            Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
                            if (!RoleLocalServiceUtil.hasUserRole(user.getUserId(), role.getRoleId())) {
                                RoleLocalServiceUtil.addUserRoles(user.getUserId(), new long[]{role.getRoleId()});
                            }
                            logger.info("Added role " + roleName + " to member : "+user.getFullName());

                            // Personal role
                            if (!RoleLocalServiceUtil.hasUserRole(user.getUserId(), personalRole.getRoleId())) {
                                RoleLocalServiceUtil.addUserRoles(user.getUserId(), new long[]{personalRole.getRoleId()});
                                logger.info("Added role " + personalRole.getName() + " to member : "+user.getFullName());
                            }

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
            String[] attributeIds = {ATTRIBUTE_USER_SN, ATTRIBUTE_USER_GIVEN_NAME, ATTRIBUTE_USER_MAIL, ATTRIBUTE_USER_EMPLOYEE_ID};

            try {
                // Get specified LDAP attributes
                Attributes attrs = getContext().getAttributes(fullCn, attributeIds);

                String givenName    = LDAPUtil.getAttributeString(attrs, ATTRIBUTE_USER_GIVEN_NAME);
                String sn   		= LDAPUtil.getAttributeString(attrs, ATTRIBUTE_USER_SN);
                String mail   		= LDAPUtil.getAttributeString(attrs, ATTRIBUTE_USER_MAIL);
                String uid			= LDAPUtil.getAttributeString(attrs, ATTRIBUTE_USER_EMPLOYEE_ID);
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
                    logger.debug("Found a user with same mail in DB -> using it");
                    cnUserMap.put(fullCn, candidate);
                    return candidate;
                }

            } catch (Exception e) {
                logger.error("Error fetching user in LDAP");
            }
        }
        return null;
    }

    public User createUser (String shortCn, String lastName, String firstName, String mail, String uid) throws Exception {

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
                        0, true, 1, 1, 2000, StringPool.BLANK, UserConstants.TYPE_REGULAR, null,
                        null, null, null, false, serviceContext);

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

                createMessagingConfig(user);
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
        MessagingConfig messagingConfig = null;

        try {
            messagingConfig = MessagingConfigLocalServiceUtil.getMessagingConfig(user.getUserId());
        } catch (Exception e) {
            logger.debug(e);
        }

        if (messagingConfig == null) {
            try {
                MessagingConfigLocalServiceUtil.addDefaultMessagingConfig(user.getUserId());
            } catch (Exception e) {
                logger.error("Error while creating messaging config for user "+user.getFullName()+" ("+user.getUserId()+")");
            }
        }
    }

    private void createServiceNotifications(User user) {

        try {
            NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());

            userNotificationConfig.setNotifyCasier(true);
            userNotificationConfig.setNotifyActu(true);
            userNotificationConfig.setNotifyGrpDoc(true);
            userNotificationConfig.setNotifyAgenda(true);
            userNotificationConfig.setDigestPeriod(2);
            userNotificationConfig.setUserId(user.getUserId());

            userNotificationConfig.setActivate(true);

            NotifyConfigLocalServiceUtil.updateNotifyConfig(userNotificationConfig);

        } catch(Exception e) {
            logger.error("Error while updating user notify config for user "+user.getUserId());
        }
    }

    private void updateMapping(long userId, String uid) {

        //_log.info("Updating LDAP mapping for userId "+userId + " with UID="+uid);
        if (uid == null || uid.equals("")) {
            return;
        }
        LDAPMapping ldapMapping = null;
        try {
            ldapMapping = LDAPMappingLocalServiceUtil.getLDAPMapping(userId);
        } catch (Exception e) {
            logger.debug("Error fetching LDAPMapping for userId " + userId);
        }

        if (ldapMapping == null) {
            ldapMapping = LDAPMappingLocalServiceUtil.createLDAPMapping(userId);
        }
        try {
            ldapMapping.setUID(uid.replace("NBDS-", ""));
            LDAPMappingLocalServiceUtil.updateLDAPMapping(ldapMapping);
        } catch (Exception e) {
            logger.debug(e);
        }
    }

    private void synchronizeSessions(Organization school, String sessionsDn) throws NamingException {

        logger.info("Synchronize sessions "+sessionsDn);

        SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 0, 0, null, false, false);
        try {
            // Loop over degrees
            String[] degrees = {"OU=9", "OU=10", "OU=11", "OU=X"};
            for (String degree : degrees) {

                String degreeDn = degree + "," + sessionsDn;

                NamingEnumeration<SearchResult> sessionEnum = getContext().search(degreeDn, GVE_LDAP_CLASS_FILTER, cons);
                while (sessionEnum.hasMoreElements()) {
                    SearchResult sessionResult = sessionEnum.nextElement();
                    String sessionDn = sessionResult.getName() + "," + degreeDn;

                    synchronizeSession(school, sessionDn);
                }
            }

        } catch (NamingException e) {
            logger.error("LDAP error when synchronizing sessions", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error in synchronizing sessions", e);
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
                reportData.addSmogCours(sessionName);
                reportData.setDirectoryError(true);
                return;
            }
            String volee = getSessionName(sessionName).substring(2, 4);
            String volee2 = getSessionName(sessionName).substring(3, 5);

            // Check that the volee belongs to the authorized list
            if (!OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee) && !OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee2)) {
                logger.error("===REPORT=== Cours incorrect dans EEL : " + getSessionName(sessionName));
                reportData.addSmogCours(sessionName);
                reportData.setDirectoryError(true);
                return;
            }

            // Create associated group
            String orgName = school.getName() + " - " + getSessionName(sessionName);
            Organization coursOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(companyId, orgName, school.getOrganizationId(), OrgConstants.COURS_TYPE);
            // logger.info(sessionName);

            studentMap.put(sessionName, new ArrayList<>());

            String[] attributeIds = {MEMBER};
            int nbStudents = 0;
            Attributes attrs = getContext().getAttributes(sessionDn, attributeIds);
            if (attrs.get(MEMBER) != null) {
                for (int i = 0 ; i < attrs.get(MEMBER).size(); i++) {
                    String memberCn = attrs.get(MEMBER).get(i).toString();
                    logger.debug("Found member " + memberCn);

                    // Teachers are processed through csv files
                    // Here we take care of students
                    if (memberCn.contains("ELEVES")) {
                        User student = getUserFromCn(memberCn);

                        if (student != null) {
                            // Add student to the 'cours' org
                            addUserToOrgMap(student, coursOrg.getOrganizationId());

                            // Add student to session map
                            studentMap.get(sessionName).add(student);
                            nbStudents++;
                        }
                    } else {
                        User teacher = getUserFromCn(memberCn);

                        if (teacher != null) {
                            // Add teacher to the 'cours' org
                            addUserToOrgMap(teacher, coursOrg.getOrganizationId());
                        }
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
        File horairesDir = new File(PropsUtil.get(NeroSystemProperties.SYNCHRO_SCHEDULE_DROP_FOLDER));
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
                    Date maxDate = sdf.parse("01.01.2020"); // TODO: make something about that

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

                    assert latestFile != null;
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
        File archiveDir = new File(PropsUtil.get(NeroSystemProperties.SYNCHRO_SCHEDULE_DROP_FOLDER) + "archives/");
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

    private boolean synchronizeHoraires(Organization school, String schoolId) {

        logger.info("Start parsing access csv file");

        File csvFile = getLatestFile(schoolId);
        if (csvFile == null) {
            logger.info("Not found any file for schoolId " + schoolId);
            return false;
        }

        List<String> csvLines = GVESynchronizationUtils.getFileContent(csvFile);
        if (!isFileValid(csvLines)) {
            logger.error("File is not valid -> skipping");
            return false;
        }

        // Loop over the lines (no first line to skip)
        for (int i = 0 ; i < csvLines.size() ; i++) {
            String csvLine = csvLines.get(i);
            logger.info(i + "/" + csvLines.size() + " : " + csvLine);
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
            //  - Column 10 : teacher full name
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
                    reportData.addTeacher(teacherId + " - " + lastName + " " + firstName);
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
            slotData.addParentClassOrgId(classOrg.getOrganizationId());

            coursMap.get(shortSessionName).get(slot).add(slotData);
        }
        return true;
    }


    private String getSessionName(String sessionName) {

        String newSessionName = sessionName;

        // Replace all special (§ and +) chars by '_'
        newSessionName = newSessionName.replace("\u00A7", "_").replace("\\+", "_");
        logger.debug("getSessionName temp is " + newSessionName);

        // If session name ends with G1 or G2, keep the suffix
        // Else if 2 letters then 4 numerics, then shorten to 6 chars
        // Else if 2 letters then 1 special char then 4 numerics, then shorten to 7 chars
        // Else, keep the cours name

        String regex1 = "[A-Z]{2}\\d{4}.*";
        String regex2 = "[A-Z]{2}[_.+]\\d{4}.*";

        if (newSessionName.contains("G1") || newSessionName.contains("G2")) {
            logger.debug("======================= KEEP FULL NAME ===================");
            // Nothing so that we generate groups with complete session name
            // Just shorten to 8 chars
            newSessionName = newSessionName.substring(0, (Math.min(newSessionName.length(), 8)));
        }
        // If session is 2 letters and 4 numerics, shorten to 6 chars
        else if (Pattern.matches(regex1, newSessionName)) {
            logger.debug("======================= SHORTEN TO 6 ===================");
            newSessionName = newSessionName.substring(0, (Math.min(newSessionName.length(), 6)));
        }
        // If session is 2 letters, a special char and 4 numerics, shorten to 7 chars
        else if (Pattern.matches(regex2, newSessionName)) {
            logger.debug("======================= SHORTEN TO 7 ===================");
            newSessionName = newSessionName.substring(0, (Math.min(newSessionName.length(), 7)));
        } else {
            // Language or science groups -> keep full name
            logger.debug("--------- ELSE");
        }
        logger.debug("sessionName " + sessionName + " transformed into " + newSessionName);
        return newSessionName;

    }

    private void createHoraires(Organization school) {
        final DateFormat fullFormat = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);

        int classIndex = 0;
        logger.info("About to build CDT sessions for " + coursMap.size() + " cours");

        // For obsolete cours orgs deletion
        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.COURS_TYPE);
        List<Organization> existingCoursOrgs = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
        logger.info("There are " + existingCoursOrgs.size() + " existing cours organizations");
        List<Long> newCoursOrgIds = new ArrayList<>();

        for (Map.Entry<String, Map<String, List<SlotData>>> coursEntry : coursMap.entrySet()) {

            String coursName = coursEntry.getKey();
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
            coursCal.set(Calendar.HOUR_OF_DAY, 6);
            coursCal.set(Calendar.MINUTE, 0);
            coursCal.set(Calendar.SECOND, 0);
            Date coursStartDate = coursCal.getTime();
            Date schoolYearEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
            List<CDTSession> existingCoursSessions = CDTSessionLocalServiceUtil.getGroupSessions(coursOrg.getGroupId(), coursStartDate, schoolYearEndDate, false);
            logger.info("Found " + existingCoursSessions.size() + " sessions for cours " + coursOrg.getName());

            // Will contain all processed sessionIds
            List<Long> newSessionIds = new ArrayList<>();

            // Loop over slots
            int slotIndex = 0;
            Map<String, List<SlotData>> slotMap = coursEntry.getValue();
            for (Map.Entry<String, List<SlotData>> slotEntry : slotMap.entrySet()) {

                String slot = slotEntry.getKey();
                slotIndex++;

                for (SlotData slotData : slotEntry.getValue()) {

                    String room = slotData.getRoom();

                    // Map course and subject
                    String subjectName = slotData.getSubject();
                    Subject subject = SubjectLocalServiceUtil.getOrCreateSubject(subjectName);
                    CourseDetailsLocalServiceUtil.setCourseSubject(coursOrg.getGroupId(), subject.getSubjectId());

                    String subjectOrgName = OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true) + OrgConstants.SUBJECT_ORG_TEACHERS +
                            (subjectName.startsWith("A") || subjectName.startsWith("E") || subjectName.startsWith("H") || subjectName.startsWith("I") ? " d'" : " de ") + subjectName;
                    Organization subjectOrg = null;
                    try {
                        subjectOrg = OrgUtilsLocalServiceUtil.getOrCreateOrganization(school.getCompanyId(), subjectOrgName, school.getOrganizationId(), OrgConstants.SUBJECT_TYPE);
                    } catch (Exception e) {
                    }

                    // Convert slot to startTime/endTime
                    List<SessionInfos> sessionInfosList = getSessionInfos(slot, slotData);

                    logger.info("");
                    logger.info("===========");
                    logger.info("Building CDT sessions for class " + coursOrg.getName() + " (" +classIndex+ "/" + coursMap.size()+ ")"
                            + ", slot " + slot + " (" +slotIndex+ "/" +slotMap.size()+"), room " + room + ", sessionName = " + coursName
                            + " with subject " + slotData.getSubject()
                            + " and parentClassOrgIds = " + slotData.getParentClassOrgIds().toString());

                    // Add teachers into cours + parent classes + volees + school + school-level
                    List<Long> allSlotTeacherIds = getTeacherIds(slotData);
                    for (Long teacherId : allSlotTeacherIds) {
                        try {
                            User teacher = UserLocalServiceUtil.getUser(teacherId);
                            addUserToOrgMap(teacher, school.getOrganizationId());
                            addUserToOrgMap(teacher, coursOrg.getOrganizationId());
                            logger.info("Adding teacher " + teacherId + " to cours " + coursOrg.getName());
                            if (subjectOrg != null) {
                                addUserToOrgMap(teacher, subjectOrg.getOrganizationId());
                                TeacherSubjectLocalServiceUtil.addTeacherSubjectInSchool(teacher.getUserId(), subject.getSubjectId(), school.getOrganizationId());
                                logger.info("Adding teacher " + teacherId + " to subject org " + subjectOrg.getName());
                            }

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

                    logger.info("There is " + sessionInfosList.size() + " session infos to process");
                    for (SessionInfos sessionInfos : sessionInfosList) {

                        List<Long> teacherIdList = sessionInfos.getTeacherIds();

                        // tmp
                        StringBuilder teacherIdsStr = new StringBuilder();
                        for (Long teacherId : teacherIdList) {
                            teacherIdsStr.append(teacherId).append(",");
                        }
                        logger.info("-----------------------");
                        logger.info("Loop over sessionInfos with fullCoursName " + sessionInfos.getFullCoursName() + " and teachers " + teacherIdsStr);
                        //end tmp


                        CDTSession existingSession = getExistingSession(existingCoursSessions, sessionInfos.getStartSessionDate(), sessionInfos.getEndSessionDate(), sessionInfos.getFullCoursName(), room);

                        if (existingSession != null) {

                            // EXISTING SESSION
                            logger.info("Found existing session at " + fullFormat.format(sessionInfos.getStartSessionDate()) + " - " + fullFormat.format(sessionInfos.getEndSessionDate()));
                            newSessionIds.add(existingSession.getSessionId());

                            // Update room if needed
                            if (!existingSession.getRoom().equals(room)) {
                                existingSession.setRoom(room);
                                logger.info(" > UPDATE room to " + room);
                                try {
                                    CDTSessionLocalServiceUtil.updateCDTSession(existingSession);
                                } catch (Exception e) {
                                    logger.debug(e);
                                }
                            }

                            // Update teachers if needed, add only if more than 1 sessionInfo (to preserve successive remove/add)
                            updateTeachers(existingSession, teacherIdList);

                            // Update parentGroupIds if needed
                            ClassCoursMappingLocalServiceUtil.updateClassCoursMapping(coursOrg.getOrganizationId(), slotData.getParentClassOrgIds());

                            // Add fullCoursName if needed
                            if (!existingSession.getFullCoursName().contains(sessionInfos.getFullCoursName())) {
                                existingSession.setFullCoursName(existingSession.getFullCoursName() + "," + sessionInfos.getFullCoursName());
                                logger.debug(" > ADD fullCoursName " + sessionInfos.getFullCoursName());
                                try {
                                    CDTSessionLocalServiceUtil.updateCDTSession(existingSession);
                                } catch (Exception e) {
                                    logger.debug(e);
                                }
                            }

                        } else {
                            // Create CDT Session
                            try {
                                CDTSession createdSession = CDTSessionLocalServiceUtil.createSession(coursOrg.getGroupId(), slotData.getSubject(),
                                        sessionInfos.getStartSessionDate(), sessionInfos.getEndSessionDate(), getSlotNumber(slot), teacherIdList, room, sessionInfos.getFullCoursName(), false);
                                logger.info("CREATED SESSION " + createdSession.getSessionId() + " for coursName = " + coursName + " and from " + fullFormat.format(sessionInfos.getStartSessionDate()) + " to " + fullFormat.format(sessionInfos.getEndSessionDate()));
                                newSessionIds.add(createdSession.getSessionId());
                                existingCoursSessions.add(createdSession);

                                // Add parentGroupIds
                                ClassCoursMappingLocalServiceUtil.updateClassCoursMapping(coursOrg.getOrganizationId(), slotData.getParentClassOrgIds());

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
                        logger.info(" >>> CANDIDATE OBSOLETE SESSION " + toDeleteSession.getSessionId() + " from " + toDeleteSession.getStart().toString() + " to " + toDeleteSession.getEnd());
                        if (toDeleteSession.getIsManual()) {
                            logger.info("Session "+toDeleteSession.getSessionId()+" not deleted because manually created");
                        } else {
                            if (!SessionContentLocalServiceUtil.hasSessionContent(toDeleteSession.getSessionId())
                                    && !HomeworkLocalServiceUtil.hasHomeworksGivenDuringSession(toDeleteSession.getSessionId())
                                    && !HomeworkLocalServiceUtil.hasHomeworksToDoForSession(toDeleteSession.getSessionId())) {

                                CDTSessionLocalServiceUtil.deleteSessionAndDependencies(toDeleteSession.getSessionId());
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
        String tmpGroupIdList = "";
        for (Organization toRemoveOrg : existingCoursOrgs) {
            if (!newCoursOrgIds.contains(toRemoveOrg.getOrganizationId())) {
                tmpGroupIdList += "," + toRemoveOrg.getGroupId();
                logger.info("Should delete obsolete cours org " + toRemoveOrg.getName() + " for school " + school.getName() + " (But for safety reason we keep it for now)");
//				logger.info("Deleting obsolete cours org " + toRemoveOrg.getName() + " for school " + school.getName());
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
        logger.info("toRemoveGroupIds = " + tmpGroupIdList);
    }

    private static int getSlotNumber(String slot) {
        // Slot is for example 205, 2 being the day number and 5 being the slot
        return Integer.parseInt(slot.substring(1));
    }

    private String extractVolee (String coursName) {

        if (Pattern.matches("[A-Z]{2}[._]\\d{4}.*", coursName)) {
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

    private void updateTeachers (CDTSession existingCdtSession, List<Long> newTeacherIdList) {
        try {
            boolean isDiff = false;
            List<Long> existingTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(existingCdtSession.getSessionId());
            List<Long> teacherIdListToUpdate = new ArrayList<>(existingTeacherIds);
            for (int teacherIdx = 0 ; teacherIdx < existingTeacherIds.size() ; teacherIdx++) {
                long existingTeacherId = existingTeacherIds.get(teacherIdx);
                if (!newTeacherIdList.contains(existingTeacherId)) {
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
    }

    private List<Long> getTeacherIds (SlotData slotData) {

        List<Long> slotTeacherIds = new ArrayList<>();
        for (TeacherFrequency teacherFrequency : slotData.getTeacherFrequencyList()) {
            if (!slotTeacherIds.contains(teacherFrequency.getTeacherId())) {
                slotTeacherIds.add(teacherFrequency.getTeacherId());
            }
        }
        return slotTeacherIds;
    }

    private CDTSession getExistingSession(List<CDTSession> existingSessions, Date startDate, Date endDate, String fullCoursName, String room) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        logger.info("getExistingSession for fullCoursName="+fullCoursName + ", startDate " + sdf.format(startDate) + " and endDate " + sdf.format(endDate));

        // First filter on startDate and endDate only
        List<CDTSession> sessionCandidates = new ArrayList<>();
        for (CDTSession existingSession : existingSessions) {
            logger.debug("Compare session " + sdf.format(startDate) + " / " + sdf.format(existingSession.getStart()) + " and " + sdf.format(endDate) + " / " + sdf.format(existingSession.getEnd()) + " and " + room + " / " + existingSession.getRoom());
            if (isSameDayAndHour(existingSession.getStart(), startDate)
                    && isSameDayAndHour(existingSession.getEnd(), endDate)) {
                logger.info("Candidate session is " + existingSession.getSessionId());
                sessionCandidates.add(existingSession);
            }
        }

        if (sessionCandidates.isEmpty()) {
            return null;
        } else if (sessionCandidates.size() == 1) {
            // If only 1 result, check that fullCoursName OR room are the same
            logger.info("1 session candidate");
            //if (sessionCandidates.get(0).getFullCoursName().contains(fullCoursName) || sessionCandidates.get(0).getRoom().equals(room)) {
            if (sessionCandidates.get(0).getRoom().equals(room)) {
                logger.info(" -> room is the same -> returning this 1 session");
                return sessionCandidates.get(0);
            }
        } else {
            // Else, filter on the room + fullCoursName
            logger.info("More than 1 candidate -> filtering on room and fullCoursName");
            for (CDTSession sessionCandidate : sessionCandidates) {
                if (sessionCandidate.getRoom().equals(room) && sessionCandidate.getFullCoursName().contains(fullCoursName)) {
                    logger.info("Found room and fullCoursName -> returning it");
                    return sessionCandidate;
                }
            }
        }

        logger.info("No session candidate");

        return null;
    }

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

        logger.info(" >> slot = " + slot);

        // Convert slot to day number and hour number
        int slotDay = getSlotDay(slot);
        String startTimeStr = startSlotMap.get(slot.substring(1, 3));
        logger.debug(" >> startTimeStr = " + startTimeStr);
        int slotStartHour = Integer.parseInt(startTimeStr.substring(0, 2));
        int slotStartMinute = Integer.parseInt(startTimeStr.substring(3, 5));
        String endTimeStr = endSlotMap.get(slot.substring(1, 3));
        int slotEndHour = Integer.parseInt(endTimeStr.substring(0, 2));
        int slotEndMinute = Integer.parseInt(endTimeStr.substring(3, 5));

        // First loop over TeacherFrequencies and calculate a list of dates for each frequency
        for (TeacherFrequency teacherFrequency : slotData.getTeacherFrequencyList()) {
            String frequency = teacherFrequency.getFrequency();
            logger.info("Loop1 : frequency = " + frequency);
            try {
                Date rangeStartDate;
                Date rangeEndDate;
                Date now = new Date();
                Calendar cal = Calendar.getInstance(Locale.FRANCE);
                switch (frequency) {
                    case "1S":
                        rangeStartDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();
                        rangeEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearSemesterDate();
                        break;
                    case "2S":
                        rangeStartDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearSemesterDate();
                        rangeEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
                        break;
                    case "Annuel":
                    case "1H":
                    case "2H":
                        rangeStartDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();
                        rangeEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
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
                cal.set(Calendar.SECOND, 0);

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
        for (int i = 0 ; i < slotData.getTeacherFrequencyList().size() ; i++) {

            TeacherFrequency teacherFrequency = slotData.getTeacherFrequencyList().get(i);
            logger.info("Loop2 : teacherFrequency with teacherId " + teacherFrequency.getTeacherId() + " and frequency " + teacherFrequency.getFrequency());

            for (Date date : teacherFrequency.getDates()) {

                List<Long> teacherIds = new ArrayList<>();
                teacherIds.add(teacherFrequency.getTeacherId());

                // Check if this date belongs to other TeacherFrequencies's range, with same short-CoursName
                if (slotData.getTeacherFrequencyList().size() > 1) {
                    for (int j = 0 ; j <  slotData.getTeacherFrequencyList().size() ; j++) {
                        TeacherFrequency otherTeacherFrequency = slotData.getTeacherFrequencyList().get(j);
                        if (j > i && getSessionName(teacherFrequency.getFullCoursName()).equals(getSessionName(otherTeacherFrequency.getFullCoursName()))) {
                            logger.info("Date " + date.toString() + " : comparing with teacherFrequency with teacherId " + otherTeacherFrequency.getTeacherId() + " and frequency " + otherTeacherFrequency.getFrequency());
                            // Loop over other teacherfrequency dates
                            List<Date> otherDateList = new ArrayList<>(otherTeacherFrequency.getDates());
                            for (int dateIdx = 0 ; dateIdx < otherDateList.size() ; dateIdx++) {
                                Date otherDate = otherDateList.get(dateIdx);
                                if (isSameDayAndHour(date, otherDate)) {
                                    logger.info("  is same day : " + otherDate.toString());

                                    // Add teacher in the list
                                    if (!teacherIds.contains(otherTeacherFrequency.getTeacherId())) {
                                        teacherIds.add(otherTeacherFrequency.getTeacherId());
                                    }

                                    // Remove date from the other's date list so that it will not be processed after
                                    slotData.getTeacherFrequencyList().get(j).getDates().remove(dateIdx);
                                    logger.info("other TeacherFrequency now has " + slotData.getTeacherFrequencyList().get(j).getDates().size() + " dates left");
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
                cal.set(Calendar.SECOND, 0);
                Date startDate = cal.getTime();

                // End date
                cal.set(Calendar.HOUR_OF_DAY, slotEndHour);
                cal.set(Calendar.MINUTE, slotEndMinute);
                Date endDate = cal.getTime();

                logger.info("Create sessionInfos from " + startDate.toString() + " to " + endDate.toString() + ", coursName= " + teacherFrequency.getFullCoursName() + " and " + teacherIds.size() + " teachers");
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
            logger.debug("date " + cal.getTime() +" is 1H. Week of year is " + cal.get(Calendar.WEEK_OF_YEAR));
            return true;
        }

        return false;
    }

    private boolean is2H(Calendar cal) {
        if (h2Weeks.contains(cal.get(Calendar.WEEK_OF_YEAR))) {
            logger.debug("date " + cal.getTime() +" is 2H. Week of year is " + cal.get(Calendar.WEEK_OF_YEAR));
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

        // Personal role
        Role personalRole = RoleUtilsLocalServiceUtil.getPersonalRole();
        if (!RoleLocalServiceUtil.hasUserRole(teacher.getUserId(), personalRole.getRoleId())) {
            try {
                RoleLocalServiceUtil.addUserRoles(teacher.getUserId(), new long[]{personalRole.getRoleId()});
                logger.info("Added role " + personalRole.getName() + " to teacher : " + teacher.getFullName());
            } catch (Exception e) {
                logger.error("Error giving personal role to teacher " + teacher.getFullName(), e);
            }
        }
    }

    private static class SlotData {
        String room;
        String subject;
        List<TeacherFrequency> teacherFrequencyList;
        List<Long> parentClassOrgIds;	// For registering teachers to parent classes

        public SlotData() {
            teacherFrequencyList = new ArrayList<>();
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


    private void initSlotMaps(long schoolId) {

        // Build the String/String map
        List<SlotConfiguration> schoolSlots = SlotConfigurationLocalServiceUtil.getSchoolSlots(schoolId);
        startSlotMap = new HashMap<>();
        endSlotMap = new HashMap<>();
        for (SlotConfiguration slot : schoolSlots) {
            // Slot must be 2-char length
            String slotStr;
            if (slot.getSlotNumber() < 10) {
                slotStr = "0" + slot.getSlotNumber();
            } else {
                slotStr = "" + slot.getSlotNumber();
            }
            startSlotMap.put(slotStr, slot.getSessionStartHour());
            endSlotMap.put(slotStr, slot.getSessionEndHour());
        }

    }

    public static class SessionInfos {
        Date startSessionDate;
        Date endSessionDate;
        String fullCoursName;
        List<Long> teacherIds;

        public SessionInfos(Date startDate, Date endDate, String fullCoursName, List<Long> teacherIds) {
            this.startSessionDate = startDate;
            this.endSessionDate = endDate;
            this.fullCoursName = fullCoursName;
            this.teacherIds = teacherIds;
        }

        public Date getStartSessionDate() {
            return startSessionDate;
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

    public LdapContext getContext() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException {
        if (ctx != null) {
            return ctx;
        }

        String baseProviderURL 	= PropsUtil.get(NeroSystemProperties.SYNCHRO_LDAP_BASE_PROVIDER_URL);
        String principal 		= PropsUtil.get(NeroSystemProperties.SYNCHRO_LDAP_SECURITY_PRINCIPAL);
        String credentials 		= PropsUtil.get(NeroSystemProperties.SYNCHRO_LDAP_SECURITY_CREDENTIALS);

        Properties environmentProperties = new Properties();

        // Get truststore containing certification chain
        String storePassword = System.getProperty("javax.net.ssl.trustStorePassword");
        KeyStore trustStore;
        try (InputStream trustStream = new FileInputStream(System.getProperty("javax.net.ssl.trustStore"))) {
            char[] trustPassword = storePassword.toCharArray();

            trustStore = KeyStore.getInstance("JKS");
            trustStore.load(trustStream, trustPassword);
        }

        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustStore);
        TrustManager[] trustManagers = trustFactory.getTrustManagers();

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, trustManagers, null);

        SSLSocketFactory sslsocketfactory = sslContext.getSocketFactory();

        environmentProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.ldap.LdapCtxFactory");
        environmentProperties.put(Context.SECURITY_AUTHENTICATION, "simple");
        environmentProperties.put(Context.SECURITY_PROTOCOL, "ssl");
        environmentProperties.put(Context.PROVIDER_URL, baseProviderURL);
        environmentProperties.put(Context.SECURITY_PRINCIPAL, principal);
        environmentProperties.put(Context.SECURITY_CREDENTIALS, credentials);
        environmentProperties.put("java.naming.ldap.factory.socket", sslsocketfactory.getClass().getName());
        environmentProperties.put("java.naming.ldap.version", "3");

        try {
            ctx = new InitialLdapContext(environmentProperties, null);
        } catch (Exception e) {
            logger.warn("Failed to bind to the LDAP server", e);
        }

        return ctx;
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
//			logger.info("There is a total of " + allAgents.size() + " agents");
//			for (User agent : allAgents) {
//				if (processedUserIds.contains(agent.getUserId())) {
//					continue;
//				}
//				UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(agent.getUserId());
//				if (userProperties.getManualAccount()) {
//					continue;
//				}
//
//				// Update last synchro date to avoid purging the user the next day
//				userProperties.setLastSynchroDate(new Date());
//				UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);
//
//				UserLocalServiceUtil.updateStatus(agent.getUserId(), WorkflowConstants.STATUS_INACTIVE, new ServiceContext());
//				logger.info("Deactivated agent " + agent.getFullName() + " (userId " + agent.getUserId() + ")");
//			}
//		} catch (Exception e) {
//			logger.error("Error processing obsolete students deactivation", e);
//		}

    }

    private void initCreatedAccounts() {

        logger.info("Init created accounts");

        for (User createdUser : createdUserList) {

            try {
                logger.info("Init new user " + createdUser.getFullName());

                // TODO Send welcome message
                // MessageLocalServiceUtil.sendWelcomeMessage(createdUser);

                // Mark latest ent news as read so that it does not pop at first connection
                UserReadVersionNoteLocalServiceUtil.setLastVersionNoteAsReadForUser(createdUser.getUserId());

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
            return !this.getSmogCours().isEmpty() || isDirectoryError() || !unknownSirh.isEmpty() || !unknownRoles.isEmpty();
        }
    }

    public void sendReport(long schoolId) {

        if (!sendReport) {
            logger.info("Manual run -> no sending any report");
            return;
        }
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

            long noReplyUserId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
            List<Long> recipientList = new ArrayList<>();
            for (User director : directors) {
                if (!recipientList.contains(director.getUserId())) {
                    logger.info("Destinataire du rapport : " + director.getFullName());
                    recipientList.add(director.getUserId());
                }
            }
            String subject = "Anomalies lors de la synchronisation des profils des agents et des horaires";
            StringBuilder content = new StringBuilder("Bonjour,<br><br>"
                    + "Des erreurs ont \u00e9t\u00e9 d\u00e9tect\u00e9es lors de la synchronisation des profils des agents et des horaires pour votre \u00e9tablissement, en date du " + new SimpleDateFormat(JSONConstants.FRENCH_FORMAT).format(new Date()) + ".<br><br>");

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

            content.append("Meilleurs messages,<br>L'\u00e9quipe technique");

            MessageLocalServiceUtil.sendMessage(noReplyUserId, recipientList, subject, content.toString(), MessagingConstants.TYPE_REPORT);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi du rapport de synchronisation pour schoolId " + schoolId, e);
        }
    }

    private static final String GVE_LDAP_CLASS_FILTER = "(objectClass=eTATGEgroup)";
    private static final String ETAT_GE_PROPRIETAIRE = "eTATGEproprietaire";
    private static final String MEMBER = "member";

    private static final String ATTRIBUTE_SCHOOL_DESCRIPTION = "description";
    private static final String ATTRIBUTE_CLASS_MEMBER = "member";
    private static final String ATTRIBUTE_USER_SN = "sn";
    private static final String ATTRIBUTE_USER_GIVEN_NAME = "givenName";
    private static final String ATTRIBUTE_USER_MAIL = "mail";
    private static final String ATTRIBUTE_USER_EMPLOYEE_ID = "employeeId";

    private static final String CSV_SEPARATOR = ",";

    static LdapContext ctx = null;

}