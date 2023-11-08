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
package com.weprode.facile.application.service.utils;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.service.ApplicationLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ExportUtils {

    private static final Log logger = LogFactoryUtil.getLog(ExportUtils.class.getName());

    private static final String ELEVE = "eleve";
    private static final String PARENT = "parent";
    private static final String PROF = "prof";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String IDENTIFIANT = "identifiant";

    public String exportFile(long userId, long applicationId, long schoolId, String roleName) throws PortalException, SystemException {
        JSONObject resultExport = new JSONObject();

        User currUser = UserLocalServiceUtil.getUser(userId);
        Application application = ApplicationLocalServiceUtil.getById(applicationId);
        String typeExport = application.getApplicationName();

        SimpleDateFormat nameFileDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");

        String file;

        Calendar today = Calendar.getInstance();

        String fileExtension = ".csv";

        logger.info("Start export for application " + typeExport + ", schoolId " + schoolId + " and role " + roleName);

        if (typeExport.equalsIgnoreCase("GRR")) {
            file = exportGRR(roleName, schoolId);

        } else {
            resultExport.put(JSONConstants.SUCCESS, false);
            resultExport.put(JSONConstants.MESSAGE, "Ce service n'a pas de type d'export");
            return resultExport.toString();
        }
        String type = roleName.equals("other") ? "ressource" : roleName;
        String fileName = "Export_" + typeExport + "_" + type + "_" + nameFileDateFormat.format(today.getTime()) + fileExtension;

        try {
            InputStream is = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8));
            FileEntry fileEntry = DLAppServiceUtil.addTempFileEntry(currUser.getGroupId(), FolderUtilsLocalServiceUtil.getTmpFolder(userId).getFolderId(), "folderName", fileName, is, "html/text");
            long noReplyUserId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
            String subject = "Export " + typeExport;
            String content = "Bonjour,<br><br>Veuillez trouver ci-joint l'export " + typeExport + " pour votre établissement.<br><br>Meilleurs messages,<br>L'équipe technique";
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(userId);
            List<Long> attachFileIds = new ArrayList<>();
            attachFileIds.add(fileEntry.getFileEntryId());
            MessageLocalServiceUtil.sendMessage(noReplyUserId, recipientList, subject, content, MessagingConstants.TYPE_REPORT, attachFileIds, 0, 0);
            resultExport.put(JSONConstants.SUCCESS, true);

            logger.info("End export for application " + typeExport + ", schoolId " + schoolId + " and role " + roleName);
            return resultExport.toString();

        } catch (Exception e) {
            logger.error("Error when sending message with export file", e);
        }


        resultExport.put(JSONConstants.SUCCESS, false);
        return resultExport.toString();
    }


    private String exportGRR(String userRole, Long schoolId) throws SystemException {

        List<Role> roles = getAssociatedRoles(userRole);
        List<User> userList = UserLocalServiceUtil.getOrganizationUsers(schoolId);

        if (roles.isEmpty()) {
            return StringPool.BLANK;
        }

        StringBuilder file = new StringBuilder();
        // -- Identifiant ; Nom ; Prenom ; Mot de passe ; Adresse email ; Type d'utilisateur ; Statut ; Type d'authentification ; --
        file.append(IDENTIFIANT).append(";").append(NOM)
                .append(";").append(PRENOM).append(";")
                .append("password").append(";").append("email_address")
                .append(";").append("user_type").append(";")
                .append("status").append(";").append("auth_type")
                .append(";").append("pass").append(";\n");

        // Type d'utilisateur : "visiteur", "utilisateur", "administrateur"
        // Statut : "actif" ou "inactif"
        // Type d'authentification : "local" ou "ext"

        for (User user : userList) {
            try {
                if (user.isActive() && !Collections.disjoint(user.getRoles(), roles)) {
                    String email = user.getEmailAddress();
                    if (email.isEmpty()) {
                        email = "-";
                    }

                    String userType = "utilisateur";
                    if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
                        userType = "administrateur";
                    }
                    file.append(user.getScreenName()).append(";").append(user.getLastName()).append(";")
                            .append(user.getFirstName().replace(",", "")).append(";;").append(email).append(";").append(userType)
                            .append(";actif;ext;0\n");
                }
            } catch (Exception e) {
                logger.error("GRR : Error exporting user "+user.getFullName(), e);
            }
        }

        return file.toString();
    }

    private List<Role> getAssociatedRoles(String userRole) {
        List<Role> roles = new ArrayList<>();

        if (userRole.equalsIgnoreCase(ELEVE)) {
            roles.add(RoleUtilsLocalServiceUtil.getStudentRole());
        } else if (userRole.equalsIgnoreCase(PARENT)) {
            roles.add(RoleUtilsLocalServiceUtil.getParentRole());
        } else if (userRole.equalsIgnoreCase(PROF)) {
            roles.add(RoleUtilsLocalServiceUtil.getTeacherRole());
            roles.add(RoleUtilsLocalServiceUtil.getDirectionRole());
        }

        return roles;
    }

}
