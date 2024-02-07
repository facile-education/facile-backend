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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.facile.about.service.UserReadVersionNoteLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.eel.synchronization.model.ParentSynchro;
import com.weprode.facile.eel.synchronization.service.ParentSynchroLocalServiceUtil;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.model.MessagingConfig;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.messaging.service.MessagingConfigLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.AffectationLocalServiceUtil;
import com.weprode.facile.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.facile.user.service.UserManagementLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class for GVE synchronization process
 *
 * @author Cedric Lecarpentier
 *
 */
public class GVEParentSynchronizationManager {

    private static final Log logger = LogFactoryUtil.getLog(GVEParentSynchronizationManager.class);

    private long companyId;
    private Organization rootOrg;
    private final Map<String, List<ParentPassword>> passwordMap = new HashMap<>();
    private final Map<Long, String> existingPasswords = new HashMap<>();

    private static final String ARCHIVE_DIR = "archives";
    private static final String JUNK_DIR = "errors";

    public void runParentSynchronization(boolean sendReport) {
        companyId = PortalUtil.getDefaultCompanyId();
        rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(companyId);

        try {
            Date startDate = new Date();

            // For error management
            List<String> errorLines = new ArrayList<>();

            // Parse ftp folder for files uploaded
            String synchroDirectoryStr = PropsUtil.get(NeroSystemProperties.SYNCHRO_PARENT_DROP_FOLDER);

            // FileName parents_CO_UAI_YYYYMMDD_HHMM.csv
            File exportDirectory = new File(synchroDirectoryStr);
            if (!exportDirectory.exists()) {
                logger.error("Parents export files directory does not exist : " + exportDirectory);
                return;
            }

            // Parse file
            File parentsFile = getFileToProcess(exportDirectory);
            if (parentsFile == null) {
                return;
            }

            List<String> csvLines = GVESynchronizationUtils.getFileContent(parentsFile);

            // Get School UAI from fileName
            String uai = extractUaiFromFileName(parentsFile.getName());
            long schoolId;
            try {
                schoolId = OrgMappingLocalServiceUtil.getOrgMapping(uai).getOrganizationId();
            } catch (Exception e) {
                String content = "Aucun \u00e9tablissement n'a \u00e9t\u00e9 trouv\u00e9 pour l'UAI " + uai + ".";
                sendFailureMessageToAdministrator(companyId, content);

                sendToJunk(parentsFile, exportDirectory);
                return;
            }

            ParentSynchro lastSchoolSynchro = null;
            try {
                lastSchoolSynchro = ParentSynchroLocalServiceUtil.getParentSynchro(schoolId);
            } catch (Exception e) {
                logger.warn("No parent sync found for schoolId : " + schoolId);
            }

//			long diff = (lastSchoolSynchro == null) ? 0 : lastSchoolSynchro.getLineCount() - csvLines.size();
//			if (lastSchoolSynchro != null && diff >= (long) lastSchoolSynchro.getLineCount() / 10) {
//				_log.error("Delta with previous parent file is too big -> skipping");
//				// Send message to admin if diff is too important
//				String content = "Le delta entre le fichier re\u00e7u (" + parentsFile.getName() + ") et le pr\u00e9c\u00e9dent (" +
//						lastSchoolSynchro.getFileName() + ") est trop importante.";
//				sendFailureMessageToAdministrator(companyId, content);
//
//				sendToJunk(parentsFile, exportDirectory);
//				return;
//			}

            for (int i = 1; i < csvLines.size(); i++) {
                String csvLine = csvLines.get(i);
                if (csvLine.equals("")) {
                    continue;
                }
                try {
                    logger.info("=================== PROCESSING LINE " + i + " / " + csvLines.size() + "===================================================");
                    logger.info("Parent csvLine = " + csvLine);
                    String[] csvLineTab = csvLine.split(CSV_SEPARATOR);

                    //  - Column 0  : NBDS number
                    //  - Column 1  : parent 1 link : 'PERE', 'MERE'
                    //  - Column 2  : parent 1 lastName
                    //  - Column 3  : parent 1 firstName
                    //  - Column 4  : parent 1 phone priv
                    //  - Column 5  : parent 1 phone pro
                    //  - Column 6  : parent 1 phone mob
                    //  - Column 7  : parent 1 email
                    //  - Column 8  : parent 2 link : 'PERE', 'MERE'
                    //  - Column 9  : parent 2 lastName
                    //  - Column 10 : parent 2 firstName
                    //  - Column 11 : parent 2 phone priv
                    //  - Column 12 : parent 2 phone pro
                    //  - Column 13 : parent 2 phone mob
                    //  - Column 14 : parent 2 email
                    //  - Column 15 : resp legal link : 'TANTE', 'TUTEUR'
                    //  - Column 16 : resp legal lastName
                    //  - Column 17 : resp legal firstName
                    //  - Column 18 : resp legal phone priv
                    //  - Column 19 : resp legal phone pro
                    //  - Column 20 : resp legal phone mob
                    //  - Column 21 : resp legal email

                    String uid = extractColumn(csvLineTab, 0, true);

                    // Starting 0 may have been deleted by Excel manipulation
                    if (uid.length() == 7) {
                        uid = "0" + uid;
                    }

                    User student = LDAPMappingLocalServiceUtil.getUserFromUID(uid);
                    if (student == null) {
                        logger.error("Error finding student with NBDS " + uid);
                        errorLines.add("Eleve manquant," + csvLine);
                        continue;
                    }
                    if (!student.isActive()) {
                        logger.error("Found student is inactive");
                        errorLines.add("Eleve desactive," + csvLine);
                        continue;
                    }

                    // Parent 1
                    String parent1Link = extractColumn(csvLineTab, 1, false);
                    String parent1LastName = extractColumn(csvLineTab, 2, false);
                    String parent1FirstName = extractColumn(csvLineTab, 3, false);
                    String parent1Phone = extractColumn(csvLineTab, 6, true);
                    String parent1Mail = extractColumn(csvLineTab, 7, true);

                    GVEParent gveParent1 = null;
                    if (!parent1LastName.equals("") && !parent1FirstName.equals("")) {
                        gveParent1 = new GVEParent(parent1LastName, parent1FirstName, parent1Phone, parent1Mail, parent1Link);
                    }

                    // Parent 2
                    GVEParent gveParent2 = null;

                    if (csvLineTab.length >= 9) {
                        String parent2Link = extractColumn(csvLineTab, 8, false);
                        String parent2LastName = extractColumn(csvLineTab, 9, false);
                        String parent2FirstName = extractColumn(csvLineTab, 10, false);
                        String parent2Phone = extractColumn(csvLineTab, 13, true);
                        String parent2Mail = extractColumn(csvLineTab, 14, true);
                        if (!parent2LastName.equals("") && !parent2FirstName.equals("")) {
                            gveParent2 = new GVEParent(parent2LastName, parent2FirstName, parent2Phone, parent2Mail, parent2Link);
                        }
                    }

                    // RespLegal
                    GVEParent gveParent3 = null;

                    if (csvLineTab.length >= 14) {
                        String parent3Link = extractColumn(csvLineTab, 15, false);
                        String parent3LastName = extractColumn(csvLineTab, 16, false);
                        String parent3FirstName = extractColumn(csvLineTab, 17, false);
                        String parent3Phone = extractColumn(csvLineTab, 20, true);
                        String parent3Mail = extractColumn(csvLineTab, 21, true);
                        if (!parent3LastName.isEmpty() && !parent3FirstName.isEmpty()) {
                            gveParent3 = new GVEParent(parent3LastName, parent3FirstName, parent3Phone, parent3Mail, parent3Link);
                        }
                    }

                    String success = synchronizeStudentParents(student, uid, gveParent1, gveParent2, gveParent3);
                    if (!success.isEmpty()) {
                        errorLines.add(success + "," + csvLine);
                    }
                } catch (Exception e) {
                    logger.error("ERROR PROCESSING LINE " + csvLine, e);
                    errorLines.add(csvLine);
                }
            }

            logger.info("--------------------------------------");
            logger.error("There are " + errorLines.size() + " errors :");
            for (String errorLine : errorLines) {
                logger.error(errorLine);
            }

            // Update database
            if (lastSchoolSynchro == null) {
                ParentSynchroLocalServiceUtil.addParentSynchro(schoolId, startDate, new Date(), parentsFile.getName(),
                        csvLines.size(), errorLines.size());
            } else {
                // Update row
                lastSchoolSynchro.setStartDate(startDate);
                lastSchoolSynchro.setEndDate(new Date());
                lastSchoolSynchro.setFileName(parentsFile.getName());
                lastSchoolSynchro.setLineCount(csvLines.size());
                lastSchoolSynchro.setErrorCount(errorLines.size());

                ParentSynchroLocalServiceUtil.updateParentSynchro(lastSchoolSynchro);
            }

            // Keep file so that it is processed every day
            //archiveFile(parentsFile, exportDirectory);

            if (sendReport) {
                // Generate password file
                byte[] report = createCsvReport(errorLines);

                // Send it to direction members
                List<Long> organizationIds = new ArrayList<>();
                organizationIds.add(schoolId);
                List<Long> roleIds = new ArrayList<>();
                roleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());

                List<Long> recipientList = new ArrayList<>();
                List<User> directionMembers = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                for (User directionMember : directionMembers) {
                    logger.info("Send report to " + directionMember.getFullName());
                    recipientList.add(directionMember.getUserId());
                }

                String fileName = "Rapport_synchronization_parents_" + new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date()) + ".csv";
                try (InputStream is = new ByteArrayInputStream(report)) {
                    long noReplyUserId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
                    User noReplyUser = UserLocalServiceUtil.getUser(noReplyUserId);
                    FileEntry fileEntry = DLAppServiceUtil.addTempFileEntry(noReplyUser.getGroupId(), FolderUtilsLocalServiceUtil.getUserTmpFolder(noReplyUserId).getFolderId(), "folderName", fileName, is, "html/text");
                    String subject = "Synchronisation des parents";
                    String content = "Bonjour,<br><br>Veuillez trouver ci-joint le rapport de synchronisation des parents pour votre établissement.<br><br>Meilleurs messages,<br>L'équipe technique";
                    List<Long> attachFileIds = new ArrayList<>();
                    attachFileIds.add(fileEntry.getFileEntryId());
                    MessageLocalServiceUtil.sendMessage(noReplyUserId, recipientList, subject, content, MessagingConstants.TYPE_REPORT, attachFileIds, 0, 0);
                }
            }

        } catch (Exception e) {
            logger.error("Error while synchronizing parents : ", e);
        }
    }

    private String extractColumn(String[] csvLineTab, int index, boolean doRemoveSpaces) {
        String value = "";

        if (csvLineTab.length >= index + 1) {
            value =  csvLineTab[index];
        }

        // Value can be surrounded by "
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        // dash means nothing
        if (value.equals("-") || value.equals("--")) {
            value = "";
        }

        // Remove spaces if needed
        if (doRemoveSpaces) {
            value = value.replace(" ", "");
        }
        
        return value;
    }

    private String synchronizeStudentParents(User student, String uid, GVEParent gveParent1, GVEParent gveParent2, GVEParent gveParent3) {
        String success = StringPool.BLANK;

        // Get existing student's parents
        List<User> existingParents = UserRelationshipLocalServiceUtil.getParents(student.getUserId());
        logger.info("Student " + student.getFullName() + " has " + existingParents.size() + " existing parents");

        // Loop over existing parents to detect the obsolete ones
        for (User existingParent : existingParents) {
            if ((gveParent1 == null	|| (!existingParent.getLastName().equalsIgnoreCase(gveParent1.getLastName()) && !existingParent.getFirstName().equalsIgnoreCase(gveParent1.getFirstName())))
                    &&
                    (gveParent2 == null	|| (!existingParent.getLastName().equalsIgnoreCase(gveParent2.getLastName()) && !existingParent.getFirstName().equalsIgnoreCase(gveParent2.getFirstName())))
                    &&
                    (gveParent3 == null	|| (!existingParent.getLastName().equalsIgnoreCase(gveParent3.getLastName()) && !existingParent.getFirstName().equalsIgnoreCase(gveParent3.getFirstName())))) {
                logger.info("Remove obsolete parent " + existingParent.getFullName());
                UserRelationshipLocalServiceUtil.deleteParent(existingParent.getUserId());
            }
        }

        // Fetch parents in existing student's parents
        User parent1 = null;
        User parent2 = null;
        User parent3 = null;
        for (User existingParent : existingParents) {
            if (gveParent1 != null && existingParent.getLastName().equalsIgnoreCase(gveParent1.getLastName())
                    && existingParent.getFirstName().equalsIgnoreCase(gveParent1.getFirstName())) {
                parent1 = existingParent;
                logger.info("Found existing parent1 " + parent1.getFullName());
            }
            if (gveParent2 != null && existingParent.getLastName().equalsIgnoreCase(gveParent2.getLastName())
                    && existingParent.getFirstName().equalsIgnoreCase(gveParent2.getFirstName())) {
                parent2 = existingParent;
                logger.info("Found existing parent2 " + parent2.getFullName());
            }
            if (gveParent3 != null && existingParent.getLastName().equalsIgnoreCase(gveParent3.getLastName())
                    && existingParent.getFirstName().equalsIgnoreCase(gveParent3.getFirstName())) {
                parent3 = existingParent;
                logger.info("Found existing parent3 " + parent3.getFullName());
            }
        }

        if (parent1 == null && gveParent1 != null) {

            // Fetch parents among all DB parents
            try {
                parent1 = getParent(gveParent1.getLastName(), gveParent1.getFirstName(), gveParent1.getEmail());
                String password1;
                if (parent1 == null) {
                    logger.info("Create new parent1");
                    password1 = UserManagementLocalServiceUtil.generatePassword();

                    parent1 = createParent(gveParent1.getLastName(), GVESynchronizationUtils.formatName(gveParent1.getFirstName()), gveParent1.getEmail(), gveParent1.getPhone(), password1, gveParent1.getLink());
                } else {
                    logger.info("Found matching parent " + parent1.getFullName() + " among existing parents");
                    password1 = existingPasswords.get(parent1.getUserId());
                }

                if (parent1 != null) {
                    addParentToPasswordMap(student, parent1, password1);
                    logger.info("Adding child/parent relationship between " + student.getFullName() + " and " + parent1.getFullName());
                    UserRelationshipLocalServiceUtil.createUserRelationship(student.getUserId(), parent1.getUserId());
                }
            } catch (EmailAddressException e) {
                logger.error("Error creating parent " + gveParent1.getFirstName() + " " + gveParent1.getLastName() + " " + gveParent1.getEmail(), e);
                success += " - E-mail existant Parent_1";
            } catch (Exception e) {
                logger.error("Error creating parent " + gveParent1.getFirstName() + " " + gveParent1.getLastName(), e);
                success += " - Informations invalides Parent_1";
            }
        }
        
        if (parent1 != null) {
            updateParentOrgs(student, parent1);
        }

        if (parent2 == null && gveParent2 != null) {
            // Fetch parent among existing parents
            try {
                parent2 = getParent(gveParent2.getLastName(), gveParent2.getFirstName(), gveParent2.getEmail());
                String password2;
                if (parent2 == null) {
                    logger.info("Create new parent2");

                    password2 = UserManagementLocalServiceUtil.generatePassword();
                    parent2 = createParent(gveParent2.getLastName(), GVESynchronizationUtils.formatName(gveParent2.getFirstName()), gveParent2.getEmail(), gveParent2.getPhone(), password2, gveParent2.getLink());

                } else {
                    logger.info("Found matching parent " + parent2.getFullName() + " among existing parents");
                    password2 = existingPasswords.get(parent2.getUserId());
                }
                if (parent2 != null) {
                    addParentToPasswordMap(student, parent2, password2);
                    logger.info("Adding child/parent relationship between " + student.getFullName() + " and " + parent2.getFullName());
                    UserRelationshipLocalServiceUtil.createUserRelationship(student.getUserId(), parent2.getUserId());
                }
            } catch (EmailAddressException e) {
                logger.error("Error creating parent " + gveParent2.getFirstName() + " " + gveParent2.getLastName() + " " + gveParent2.getEmail(), e);
                success += " - E-mail existant Parent_2";
            } catch (Exception e) {
                logger.error("Error creating parent " + gveParent2.getFirstName() + " " + gveParent2.getLastName(), e);
                success += " - Informations invalides Parent_2";
            }
        }
        if (parent2 != null) {
            updateParentOrgs(student, parent2);
        }

        if (parent3 == null && gveParent3 != null) {
            // Fetch parent among existing parents
            try {
                parent3 = getParent(gveParent3.getLastName(), gveParent3.getFirstName(), gveParent3.getEmail());
                String password3 = "";
                if (parent3 == null) {
                    logger.info("Create new parent3");

                    password3 = UserManagementLocalServiceUtil.generatePassword();
                    parent3 = createParent(gveParent3.getLastName(), GVESynchronizationUtils.formatName(gveParent3.getFirstName()), gveParent3.getEmail(), gveParent3.getPhone(), password3, gveParent3.getLink());

                } else {
                    logger.info("Found matching parent " + parent3.getFullName() + " among existing parents");
                    password3 = existingPasswords.get(parent3.getUserId());
                }

                if (parent3 != null) {
                    addParentToPasswordMap(student, parent3, password3);
                    logger.info("Adding child/parent relationship between " + student.getFullName() + " and " + parent3.getFullName());
                    UserRelationshipLocalServiceUtil.createUserRelationship(student.getUserId(), parent3.getUserId());
                }
            } catch (EmailAddressException e) {
                logger.error("Error creating parent " + gveParent3.getFirstName() + " " + gveParent3.getLastName() + " " + gveParent3.getEmail(), e);
                success += " - E-mail existant Parent_3";
            } catch (Exception e) {
                logger.error("Error creating parent " + gveParent3.getFirstName() + " " + gveParent3.getLastName(), e);
                success += " - Informations invalides Parent_3";
            }
        }
        if (parent3 != null) {
            updateParentOrgs(student, parent3);
        }
        
        return success;
    }

    private void addParentToPasswordMap(User student, User parent, String password) {

        logger.info("Adding to passwordMap : student=" + student.getFullName() + ", parent = " + parent.getFullName());

        // Get student main class
        List<Organization> studentOrgs = UserOrgsLocalServiceUtil.getUserClasses(student, false);
        if (studentOrgs == null || studentOrgs.size() != 1) {
            logger.error("Error : student " + student.getFullName() + " has not 1 and only 1 class");
        } else {
            String className = OrgUtilsLocalServiceUtil.formatOrgName(studentOrgs.get(0).getName(), false);
            passwordMap.computeIfAbsent(className, k -> new ArrayList<>());

            // Loop over existing parent password to get the student
            boolean hasFound = false;
            for (ParentPassword parentPassword : passwordMap.get(className)) {
                if (parentPassword.getStudent().getUserId() == student.getUserId()) {
                    hasFound = true;
                    parentPassword.setParent2(parent);
                    parentPassword.setPassword2(password);
                }
            }
            if (!hasFound) {
                ParentPassword parentPassword = new ParentPassword(student, parent, password);
                passwordMap.get(className).add(parentPassword);
            }
        }
        // Add to existing passwords map (for parents having multiple children)
        if (!existingPasswords.containsKey(parent.getUserId())) {
            existingPasswords.put(parent.getUserId(), password);
        }
    }

    private void updateParentOrgs(User student, User parent) {

        logger.info("Synchronizing classes for parent " + parent.getFullName() + " through student " + student.getFullName());
        try {
            // Set rattach school
            Organization school = null;
            try {
                long childRattachSchoolId = UserPropertiesLocalServiceUtil.getUserProperties(student.getUserId()).getEtabId();
                school = OrganizationLocalServiceUtil.getOrganization(childRattachSchoolId);
                UserProperties parentProps = UserPropertiesLocalServiceUtil.getUserProperties(parent.getUserId());
                parentProps.setEtabId(childRattachSchoolId);
                UserPropertiesLocalServiceUtil.updateUserProperties(parentProps);
            } catch (Exception e) {
                logger.debug(e);
            }

            // Classes + Volees + Cours
            List<Organization> existingParentOrgs = new ArrayList<>(UserOrgsLocalServiceUtil.getUserClasses(parent, false));
            existingParentOrgs.addAll(UserOrgsLocalServiceUtil.getUserVolees(parent, false, OrgConstants.ALL_SCHOOLS_ID));
            existingParentOrgs.addAll(UserOrgsLocalServiceUtil.getUserCours(parent, false, OrgConstants.ALL_SCHOOLS_ID));

            // Get orgs for all children
            List<Organization> existingChildrenOrgs = new ArrayList<>();
            for (User child : UserRelationshipLocalServiceUtil.getChildren(parent.getUserId())) {
                existingChildrenOrgs.addAll(UserOrgsLocalServiceUtil.getUserClasses(child, false));
                existingChildrenOrgs.addAll(UserOrgsLocalServiceUtil.getUserVolees(child, false, OrgConstants.ALL_SCHOOLS_ID));
                existingChildrenOrgs.addAll(UserOrgsLocalServiceUtil.getUserCours(child, false, OrgConstants.ALL_SCHOOLS_ID));
            }

            List<Long> affectedOrgIds = AffectationLocalServiceUtil.getUserAffectedOrgs(parent.getUserId());

            // First remove the parent from all non-manual organizations
            // Manage multi-children parents
            for (Organization parentOrg : existingParentOrgs) {

                if (affectedOrgIds.contains(parentOrg.getOrganizationId())) {
                    continue;
                }

                // Remove remaining orgs
                try {
                    if (!existingChildrenOrgs.contains(parentOrg)) {
                        UserLocalServiceUtil.unsetOrganizationUsers(parentOrg.getOrganizationId(), new long[] { parent.getUserId() });
                        logger.info("Removed parent " + parent.getUserId() + " from organization " + parentOrg.getName());
                    }
                } catch (Exception e) {
                    logger.error("Error removing parent " + parent.getUserId() + " from organization " + parentOrg.getName(), e);
                }
            }

            // Now add new orgs
            for (Organization existingChildOrg : existingChildrenOrgs) {

                try {
                    if (affectedOrgIds.contains(existingChildOrg.getOrganizationId())) {
                        logger.info("Do not add org " + existingChildOrg.getName() + " to user " + parent.getFullName() + " because was manually added");
                        continue;
                    }
                    try {
                        if (!OrganizationLocalServiceUtil.hasUserOrganization(parent.getUserId(), existingChildOrg.getOrganizationId())) {
                            UserLocalServiceUtil.addOrganizationUsers(existingChildOrg.getOrganizationId(), new long[]{parent.getUserId()});
                            logger.info("Add new organization " + existingChildOrg.getName() + " to parent " + parent.getFullName());
                        }
                    } catch (Exception e) {
                        logger.error("Error adding parent " + parent.getUserId() + " into organization " + existingChildOrg.getName(), e);
                    }
                } catch (Exception e) {
                    logger.debug(e);
                }
            }

            // Add parent to rootOrg if needed
            if (!OrganizationLocalServiceUtil.hasUserOrganization(parent.getUserId(), rootOrg.getOrganizationId())) {
                UserLocalServiceUtil.addOrganizationUsers(rootOrg.getOrganizationId(), new long[] { parent.getUserId() });
            }

            // Add parent to school if needed
            if (!OrganizationLocalServiceUtil.hasUserOrganization(parent.getUserId(), school.getOrganizationId())) {
                UserLocalServiceUtil.addOrganizationUsers(school.getOrganizationId(), new long[] { parent.getUserId() });
            }

        } catch (Exception e) {
            logger.error("Error when synchronizing orgs for parent " + parent.getUserId(), e);
        }
    }


    private User getParent(String lastName, String firstName, String mail) {
        try {
            // First fetch by lastName and firstName only
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
            String query = lastName + " " + firstName;

            List<User> candidates = UserSearchLocalServiceUtil.searchUsers(query, null, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            if (candidates == null || candidates.isEmpty()) {

                // Check if e-mail exists in DB with the same identity (could be a deactivated account)
                User sameMailParent = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, mail);
                if (sameMailParent != null &&
                        lastName.equalsIgnoreCase(sameMailParent.getLastName()) &&
                        firstName.equalsIgnoreCase(sameMailParent.getFirstName())) {

                    logger.info("Found deactivated parent with mail " + mail + ", lastName=" + lastName + ", firstName="	+ firstName);
                    sameMailParent.setStatus(WorkflowConstants.STATUS_APPROVED);
                    UserLocalServiceUtil.updateUser(sameMailParent);

                    return sameMailParent;
                }

                return null;
            } else {

                logger.info("Found " + candidates.size() + " candidates");
                if (!mail.isEmpty()) {
                    for (User candidate : candidates) {
                        logger.info("Candidate email is " + candidate.getEmailAddress());
                        if (candidate.getEmailAddress().equals(mail)) {
                            logger.info("Found existing parent with mail " + mail + ", lastName=" + lastName + ", firstName="	+ firstName);
                            return candidate;
                        } else {
                            logger.info("Candidate was found but did not match e-mail address with screenName " + candidate.getScreenName() + "and userId " + candidate.getUserId());
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error while identifying parent with mail " + mail + ", lastName=" + lastName + " and firstName = " + firstName, e);
        }

        return null;
    }

    /**
     * Import a user
     */
    private User createParent (String lastName, String firstName, String mail, String phone, String password, String link) throws EmailAddressException {

        if (Validator.isNull(lastName) || Validator.isNull(firstName) || mail.isEmpty()) {
            logger.warn("Cannot add user because lastName (" + lastName + "), firstName (" + firstName + ") or mail (" + mail + ") is empty");
            return null;
        }

        if (!Validator.isNull(mail)) {
            User sameMailParent = null;

            try {
                sameMailParent = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, mail);
            } catch (Exception e) {
                logger.debug(e);
            }

            if (sameMailParent != null) {
                logger.warn("Cannot add user because the e-mail (" + mail + ") is already used by an other user : " + sameMailParent.getFullName());
                throw new EmailAddressException();
            }
        }

        User user;

        // User creation
        try {
            logger.info("Adding parent "+firstName+" "+lastName+ " to portal");

            Role parentRole = RoleUtilsLocalServiceUtil.getParentRole();
            user = UserManagementLocalServiceUtil.createManualUser(lastName, firstName, mail, null, parentRole.getRoleId(),
                    0, false, password);

        } catch (Exception e) {
            logger.error("Problem adding user with name " + lastName + " " + firstName + " and email address " + mail, e);
            return null;
        }

        if (user != null) {
            try {
                // Deactivate mail forward
                MessagingConfig messagingConfig = MessagingConfigLocalServiceUtil.getOrCreateMessagingConfig(user.getUserId());
                messagingConfig.setIsForwardActive(false);
                messagingConfig.setForwardMail(StringPool.BLANK);
                MessagingConfigLocalServiceUtil.updateMessagingConfig(messagingConfig);
            } catch (Exception e) {
                logger.warn("Could not deactivate mail forward for userId "+user.getUserId());
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

            // TODO add the link in the Relationship table

            // Send welcome message
            // List<Long> recipientList = new ArrayList<>();
            // recipientList.add(user.getUserId());

            // String subject = "Bienvenue dans votre environnement digital \u00e9ducatif";
            // String content = "Ch\u00e8re Madame, cher Monsieur,</br></br>" +
            //         "La plateforme FACILE assure le prolongement num\u00e9rique de l'\u00e9tablissement scolaire de votre•vos enfant•s.</br>" +
            //         "Il vous propose un espace priv\u00e9 et s\u00e9curis\u00e9 o\u00f9 vous pourrez suivre la vie de l'\u00e9tablissement et de la classe de votre•vos enfant•s.</br>" +
            //         "Il repr\u00e9sente surtout votre moyen de communication privil\u00e9gi\u00e9 avec l'\u00e9cole, les enseignant•e•s et le personnel encadrant.</br>" +
            //         "N'h\u00e9sitez pas \u00e0 consulter le service d'aide accessible en haut \u00e0 droite de votre \u00e9cran sous le \" ? \" pour r\u00e9pondre \u00e0 vos \u00e9ventuelles questions.</br></br>" +
            //         "Meilleurs messages,</br>L'\u00e9quipe projet";

            // long noReplyUserId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
            // MessageLocalServiceUtil.sendMessage(noReplyUserId, recipientList, subject, content, MessagingConstants.TYPE_OTHER);

            // Mark latest ent news as read so that it does not pop at first connection
            UserReadVersionNoteLocalServiceUtil.setLastVersionNoteAsReadForUser(user.getUserId());
        }

        return user;
    }

    /**
     * Send file to archive dir
     */
    private void archiveFile(File sourceFile, File sourceDir) {
        // Create archive directory if it does not exist
        File destDir = new File(sourceDir, ARCHIVE_DIR);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        if (moveFile(sourceFile, destDir)) {
            logger.info("File " + sourceFile.getAbsolutePath() + " is successfully archived");
        } else {
            logger.info("File " + sourceFile.getAbsolutePath() + " is not archived");
        }
    }

    /**
     * Send file to junk dir
     */
    private void sendToJunk(File sourceFile, File sourceDir) {
        // Create junk directory if it does not exist
        File destDir = new File(sourceDir, JUNK_DIR);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        if (moveFile(sourceFile, destDir)) {
            logger.info("File " + sourceFile.getAbsolutePath() + " is successfully junked");
        } else {
            logger.info("File " + sourceFile.getAbsolutePath() + " is not junked");
        }
    }

    /**
     * Move sourceFile to destDir
     */
    private boolean moveFile(File sourceFile, File destDir) {
        File destinationFile = new File(destDir, sourceFile.getName());

        try (OutputStream outStream = new FileOutputStream(destinationFile);
             InputStream inStream = new FileInputStream(sourceFile)) {

            byte[] buffer = new byte[10240];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            // Delete the original file
            return sourceFile.delete();
        } catch (IOException e) {
            logger.error("Error when moving file " + sourceFile.getAbsolutePath() + " to the directory "
                    + destDir.getAbsolutePath(), e);
        }

        return false;
    }

    /**
     * Get last file to proceed
     */
    private File getFileToProcess(File sourceDirectory) {

        // Sort files by ascending date
        File[] fileList = sourceDirectory.listFiles();
        assert fileList != null;
        Arrays.sort(fileList, (f1, f2) -> extractDateFromFileName(f1.getName()).compareTo(extractDateFromFileName(f2.getName())));

        if (fileList.length >= 1) {
            // Get first file in the directory (the oldest) and process it
            int index = 0;
            File processedFile = fileList[index];

            // Skip archive and junk directory
            while (processedFile.getAbsolutePath().endsWith(ARCHIVE_DIR) || processedFile.getAbsolutePath().endsWith(JUNK_DIR)) {
                try {
                    processedFile = fileList[++index];
                } catch (Exception e) {
                    // Nothing
                }
            }

            String processedUai = extractUaiFromFileName(processedFile.getName());
            logger.info("Found candidate file for UAI " + processedUai + " : " + processedFile.getName());

            // Get files with same uai
            List<File> sameUaiFileList = new ArrayList<>();

            for (File file : fileList) {
                String uai = extractUaiFromFileName(file.getName());

                if (uai.equals(processedUai) && !uai.equals("")) {
                    logger.info("Found an other file with same UAI " + processedUai + " : " + file.getName());
                    sameUaiFileList.add(file);
                }
            }

            // Sort this list by descending date
            sameUaiFileList.sort((f1, f2) -> -1 * extractDateFromFileName(f1.getName()).compareTo(extractDateFromFileName(f2.getName())));

            // Process the newest
            processedFile = sameUaiFileList.get(0);
            logger.info("Found file " + processedFile.getName() + " to process");

            // Delete the older ones
            for (int i = 1; i < sameUaiFileList.size(); i++) {

                File fileToDelete = sameUaiFileList.get(i);
                String fileToDeleteName = fileToDelete.getName();

                boolean isDeleted = fileToDelete.delete();
                if (isDeleted) {
                    logger.info("Deleted older file " + fileToDeleteName);
                } else {
                    logger.error("Failed to delete older file " + fileToDeleteName);
                }
            }

            return processedFile;
        }

        return null;
    }

    /**
     * Return date string from fileName
     */
    private String extractDateFromFileName(String fileName) {
        String[] fileNameTab = fileName.split("_");

        if (fileNameTab.length == 5) {
            return fileNameTab[3] + "_" + fileNameTab[4];
        }

        return "";
    }

    /**
     * Return UAI from file name
     */
    private String extractUaiFromFileName(String fileName) {
        String[] fileNameTab = fileName.split("_");

        if (fileNameTab.length == 5) {
            return fileNameTab[2];
        }

        return "";
    }

    private void sendFailureMessageToAdministrator (long companyId, String content) throws PortalException, SystemException {
        User administrator = UserLocalServiceUtil.getUserByScreenName(companyId, "pentila");
        List<Long> recipientList = new ArrayList<>();
        recipientList.add(administrator.getUserId());
        String subject = "Echec de la synchronisation des parents";
        MessageLocalServiceUtil.sendMessage(administrator.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_REPORT);
    }

    private static class GVEParent {
        String lastName;
        String firstName;
        String phone;
        String email;
        String link;

        GVEParent(String lastName, String firstName, String phone, String email, String link) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.phone = phone;
            this.email = email;
            this.link = link;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getLink() {
            return link;
        }
    }

    private static class ParentPassword {
        User student;
        User parent1;
        User parent2;
        String password1;
        String password2;

        ParentPassword(User student, User parent1, String password1) {
            this.student = student;
            this.parent1 = parent1;
            this.password1 = password1;
            this.parent2 = null;
        }

        public User getStudent() {
            return student;
        }

        public User getParent1() {
            return parent1;
        }

        public User getParent2() {
            return parent2;
        }

        public void setParent2(User parent2) {
            this.parent2 = parent2;
        }

        public String getPassword1() {
            return password1;
        }

        public String getPassword2() {
            return password2;
        }

        public void setPassword2(String password2) {
            this.password2 = password2;
        }
    }

    private byte[] createCsvReport(List<String> errorLines) {
        DateFormat sdf = new SimpleDateFormat(JSONConstants.FRENCH_FORMAT);

        StringBuilder result = new StringBuilder("Creation des comptes parents du " + sdf.format(new Date()) + "\n\n");

        // Sort the class list
        List<String> sortedClassList = new ArrayList<>(passwordMap.keySet());
        Collections.sort(sortedClassList);

        for (String className : sortedClassList) {

            result.append(className).append("\n\n");
            result.append("Eleve" + "," + " Parent 1" + "," + "Login" + "," + "Mot de passe" + "," + "Email" + "," + "Parent 2" + "," + "Login" + "," + "Mot de passe" + "," + "Email" + "\n");

            // Sort the list by student's lastname
            List<ParentPassword> sortedNameList = new ArrayList<>(passwordMap.get(className));
            sortedNameList.sort(Comparator.comparing(pp -> pp.getStudent().getLastName()));

            for (ParentPassword parentPassword : sortedNameList) {

                User student = parentPassword.getStudent();
                User parent1 = parentPassword.getParent1();
                User parent2 = parentPassword.getParent2();

                if (parent1 != null || parent2 != null) {

                    // Column1 : student
                    if (parent1 != null) {
                        result.append(student.getLastName()).append(" ").append(student.getFirstName()).append(        // Column 1 : student
                                ",").append(parent1.getLastName()).append(" ").append(parent1.getFirstName()).append(    // Column 2 : parent1 lastname and firstname
                                ",").append(parent1.getScreenName()).append(                                    // Column 3 : parent1 screenname
                                ",").append(parentPassword.getPassword1()).append(                            // Column 4 : parent1 password
                                ",").append(parent1.getEmailAddress());                                // Column 5 : parent1 email
                    }

                    if (parent2 != null) {
                        result.append(",").append(parent2.getLastName()).append(" ").append(parent2.getFirstName()).append(    // Column 6 : parent2 lastname and firstname
                                ",").append(parent2.getScreenName()).append(                                    // Column 7 : parent2 screenname
                                ",").append(parentPassword.getPassword2()).append(                            // Column 8 : parent2 password
                                ",").append(parent2.getEmailAddress());								// Column 9 : parent2 email
                    }
                    result.append("\n");
                }
            }

            result.append("\n");
        }

        // Errors
        result.append("\nLignes en erreur:\n\n");
        for (String errorLine : errorLines) {
            result.append(errorLine).append("\n");
        }

        try {
            return result.toString().getBytes();
        } catch (Exception e) {
            logger.error("Error while building result byte array", e);
        }

        return new byte[0];
    }

    private static final String CSV_SEPARATOR = ",";

}
