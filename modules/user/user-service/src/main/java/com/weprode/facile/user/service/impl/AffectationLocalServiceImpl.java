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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.model.OrgDetails;
import com.weprode.facile.organization.service.ClassCoursMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.model.Affectation;
import com.weprode.facile.user.service.AffectationLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.base.AffectationLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.Affectation",
        service = AopService.class
)
public class AffectationLocalServiceImpl extends AffectationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AffectationLocalServiceImpl.class);

    public List<User> getAffectedUsers(long schoolId, String filter) {
        List<User> users = new ArrayList<>();
        
        try {
            List<Affectation> schoolAffectations = affectationPersistence.findByschoolId(schoolId);
            for (Affectation affectation : schoolAffectations) {
                try {
                    User affectedUser = UserLocalServiceUtil.getUser(affectation.getUserId());
                    if (!users.contains(affectedUser) && (affectedUser.getLastName().toLowerCase().contains(filter.toLowerCase())
                            || affectedUser.getFirstName().toLowerCase().contains(filter.toLowerCase()))) {

                        users.add(affectedUser);

                    }
                } catch (Exception e) {
                    logger.error("Error on affectation : user " + affectation.getUserId() +" might not exist anymore ... " + e.getMessage());
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
            List<Long> affectedUserIds = new ArrayList<>();
            affectedUserIds.add(userId);

            User user = UserLocalServiceUtil.getUser(userId);
            if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                for (User parent : UserRelationshipLocalServiceUtil.getParents(user.getUserId())) {
                    logger.info("Affected user is a student, adding affectation for parentId = " + parent.getUserId());
                    affectedUserIds.add(parent.getUserId());
                }
            }

            List<Long> orgIdsToAffect = new ArrayList<>();
            orgIdsToAffect.add(orgId);
            // If org is a class, affect user to the classes's cours
            if (OrgDetailsLocalServiceUtil.isClass(orgId)) {
                orgIdsToAffect.addAll(ClassCoursMappingLocalServiceUtil.getClassCours(orgId));
            }

            // Loop over orgIds to affect
            for (Long orgIdToAffect : orgIdsToAffect) {
                OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(orgIdToAffect);

                for (long affectedUserId : affectedUserIds) {

                    // Add the user to the organization
                    if (!OrganizationLocalServiceUtil.hasUserOrganization(affectedUserId, orgIdToAffect)) {
                        UserLocalServiceUtil.addOrganizationUsers(orgIdToAffect, new long[]{affectedUserId});
                    }

                    // Create the affectation
                    Affectation newAffectation = affectationPersistence.create(counterLocalService.increment());
                    newAffectation.setUserId(affectedUserId);
                    newAffectation.setOrgId(orgIdToAffect);
                    newAffectation.setType(orgDetails.getType());
                    newAffectation.setSchoolId(orgDetails.getSchoolId());
                    newAffectation.setAdminUserId(adminUserId);
                    newAffectation.setAffectationDate(new Date());
                    newAffectation.setExpirationDate(expirationDate);
                    affectationPersistence.update(newAffectation);
                    logger.info("User " + adminUserId + " has affected user " + affectedUserId + " to org " + orgIdToAffect);
                }
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
            List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(userId, 0);

            for (Affectation userAffectation : userAffectations) {
                orgIds.add(userAffectation.getOrgId());
            }
        } catch (Exception e) {
            logger.error("Error fetching affected for user " + userId, e);
        }
        return orgIds;
    }

    public boolean removeUserAffectation(long userId, long orgId) {
        try {
            List<Long> orgIdsToUnaffect = new ArrayList<>();
            orgIdsToUnaffect.add(orgId);
            // If org is a class, affect user to the classes's cours
            if (OrgDetailsLocalServiceUtil.isClass(orgId)) {
                orgIdsToUnaffect.addAll(ClassCoursMappingLocalServiceUtil.getClassCours(orgId));
            }

            List<Long> affectedUserIds = new ArrayList<>();
            affectedUserIds.add(userId);

            User user = UserLocalServiceUtil.getUser(userId);
            if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                for (User parent : UserRelationshipLocalServiceUtil.getParents(user.getUserId())) {
                    affectedUserIds.add(parent.getUserId());
                }
            }

            // Loop over organizations to un-affect
            for (Long orgIdToUnaffect : orgIdsToUnaffect) {
                for (long affectedUserId : affectedUserIds) {

                    // Remove user from organization
                    UserLocalServiceUtil.unsetOrganizationUsers(orgIdToUnaffect, new long[]{affectedUserId});

                    // Delete the affectation
                    affectationPersistence.removeByuserIdOrgId(affectedUserId, orgIdToUnaffect);
                    logger.info("Removed manual affectation for user " + affectedUserId + " from org " + orgIdToUnaffect);
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Error removing affected for user " + userId, e);
        }

        return false;
    }

    // Used by the purge
    public void removeByUserId(long userId) {

        List<Long> affectedOrgIds = getUserAffectedOrgs(userId);
        for (Long affectedOrgId : affectedOrgIds) {
            removeUserAffectation(userId, affectedOrgId);
        }
    }

    public JSONArray convertUserAffectations(long userId, long schoolId) {

        // Get affectations
        List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(userId, schoolId);
        JSONArray jsonAffectations = new JSONArray();
        for (Affectation userAffectation : userAffectations) {
            try {
                Organization org = OrganizationLocalServiceUtil.getOrganization(userAffectation.getOrgId());
                OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(userAffectation.getOrgId());

                // Authorized types are : school, class and cours
                if (orgDetails.getType() == OrgConstants.SCHOOL_TYPE || orgDetails.getType() == OrgConstants.CLASS_TYPE
                        || orgDetails.getType() == OrgConstants.COURS_TYPE) {

                    JSONObject jsonAffectation = new JSONObject();
                    jsonAffectation.put(JSONConstants.ORG_ID, userAffectation.getOrgId());
                    jsonAffectation.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                    jsonAffectation.put(JSONConstants.TYPE, orgDetails.getType());
                    jsonAffectation.put(JSONConstants.ADMIN_NAME, UserLocalServiceUtil.getUser(userAffectation.getAdminUserId()).getFullName());
                    jsonAffectation.put(JSONConstants.AFFECTATION_DATE, new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(userAffectation.getAffectationDate()));
                    jsonAffectations.put(jsonAffectation);
                }
            } catch (Exception e) {
                logger.error("Error processing affectation for user " + userId + " : org " + userAffectation.getOrgId() + " might not exist anymore ... " + e.getMessage());
            }
        }
        return jsonAffectations;
    }
}
