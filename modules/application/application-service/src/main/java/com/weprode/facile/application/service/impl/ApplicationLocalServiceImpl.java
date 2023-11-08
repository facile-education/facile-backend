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

package com.weprode.facile.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.application.constants.AppManagerConstants;
import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.model.BroadcastRule;
import com.weprode.facile.application.service.AuthorizedSchoolLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastRuleLocalServiceUtil;
import com.weprode.facile.application.service.DefaultRoleLocalServiceUtil;
import com.weprode.facile.application.service.base.ApplicationLocalServiceBaseImpl;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.application.model.Application",
        service = AopService.class
)
public class ApplicationLocalServiceImpl extends ApplicationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(ApplicationLocalServiceImpl.class.getName());

    public List<Application> getAllApplications() throws SystemException {
        return applicationPersistence.findAll();
    }

    public Application getById(long applicationId) throws SystemException {
        return applicationPersistence.fetchByPrimaryKey(applicationId);
    }

    public Application addApplication(String applicationName, String applicationKey, String category, long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
                              boolean exportUser, boolean exportStudent, boolean exportParent, boolean exportTeacher, boolean exportOther,
                              String defaultRoles, String authorizedSchools) throws SystemException {

        Application application = applicationPersistence.create(counterLocalService.increment());
        application.setApplicationName(applicationName);
        application.setApplicationKey(applicationKey);
        application.setCategoryName(category);
        application.setMenuEntryId(menuEntryId);
        application.setImage(image);
        application.setHasCustomUrl(hasCustomUrl);
        application.setHasGlobalUrl(!globalUrl.equals(""));
        application.setGlobalUrl(globalUrl);
        application.setExportUser(exportUser);
        application.setExportStudent(exportStudent);
        application.setExportParent(exportParent);
        application.setExportTeacher(exportTeacher);
        application.setExportOther(exportOther);
        application = applicationPersistence.update(application);

        try {
            // defaultRoles is an array of roleIds
            if (!defaultRoles.equals("")) {
                JSONArray jsonRoles = new JSONArray(defaultRoles);
                for (int i = 0; i < jsonRoles.length(); i++) {
                    DefaultRoleLocalServiceUtil.addDefaultRole(jsonRoles.getLong(i), application.getApplicationId());
                }
            }

            // authorizedSchools is an array of schoolIds
            if (!authorizedSchools.equals("")) {
                JSONArray jsonSchools = new JSONArray(authorizedSchools);
                for (int i = 0; i < jsonSchools.length(); i++) {
                    AuthorizedSchoolLocalServiceUtil.addAuthorizedSchool(application.getApplicationId(), jsonSchools.getLong(i));
                }
            }
        } catch (Exception e) {
            logger.error("Error adding new application", e);
        }
        return application;
    }

    public Application editApplication(long applicationId, String applicationName, String applicationKey, String category, long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
                               boolean exportUser, boolean exportStudent, boolean exportParent, boolean exportTeacher, boolean exportOther,
                               String defaultRoles, String authorizedSchools) throws SystemException {

        Application application = applicationPersistence.fetchByPrimaryKey(applicationId);
        application.setApplicationName(applicationName);
        application.setApplicationKey(applicationKey);
        application.setCategoryName(category);
        application.setMenuEntryId(menuEntryId);
        application.setImage(image);
        application.setHasCustomUrl(hasCustomUrl);
        application.setHasGlobalUrl(!globalUrl.equals(""));
        application.setGlobalUrl(globalUrl);
        application.setExportUser(exportUser);
        application.setExportStudent(exportStudent);
        application.setExportParent(exportParent);
        application.setExportTeacher(exportTeacher);
        application.setExportOther(exportOther);
        application = applicationPersistence.update(application);

        try {
            // Delete roles and recreate them
            DefaultRoleLocalServiceUtil.deleteDefaultRoleByApplicationId(applicationId);
            if (!defaultRoles.equals("")) {
                JSONArray jsonRoles = new JSONArray(defaultRoles);
                for (int i = 0; i < jsonRoles.length(); i++) {
                    DefaultRoleLocalServiceUtil.addDefaultRole(jsonRoles.getLong(i), application.getApplicationId());
                }
            }

            // Detect removed authorizedSchools and remove all the stuff for them
            List<Long> existingAuthorizedSchoolIds = AuthorizedSchoolLocalServiceUtil.getAuthorizedSchoolIds(applicationId);
            JSONArray jsonSchools = new JSONArray();
            if (!authorizedSchools.equals("")) {
                jsonSchools = new JSONArray(authorizedSchools);
            }

            for (Long existingSchoolId : existingAuthorizedSchoolIds) {
                for (int i = 0; i < jsonSchools.length(); i++) {
                    if (existingSchoolId == jsonSchools.getLong(i)) {
                        removeSchoolApplicationAndDependencies(applicationId, jsonSchools.getLong(i));
                    }
                }
            }

            // Delete all school filters and recreate them
            AuthorizedSchoolLocalServiceUtil.deleteByApplicationId(applicationId);
            for (int i = 0; i < jsonSchools.length(); i++) {
                AuthorizedSchoolLocalServiceUtil.addAuthorizedSchool(application.getApplicationId(), jsonSchools.getLong(i));
            }

        } catch (Exception e) {
            logger.error("Error editing application " + applicationId, e);
        }
        return application;
    }

    public boolean removeApplication(long applicationId) throws SystemException, PortalException {
        BroadcastLocalServiceUtil.removeBroadcast(applicationId);
        defaultRolePersistence.removeByapplicationId(applicationId);
        AuthorizedSchoolLocalServiceUtil.deleteByApplicationId(applicationId);
        applicationPersistence.remove(applicationId);

        return true;
    }

    private void removeSchoolApplicationAndDependencies(long applicationId, long schoolId) throws SystemException {
        BroadcastLocalServiceUtil.deleteBroadcastForSchool(applicationId, schoolId);
        AuthorizedSchoolLocalServiceUtil.deleteByApplicationIdSchoolId(applicationId, schoolId);
        BroadcastRuleLocalServiceUtil.deleteSchoolRules(applicationId, schoolId);
    }

    public List<Application> getSchoolApplications(long schoolId) {
        // Applications that a school can see are the ones for which the school is authorized
        List<Application> schoolApplications = new ArrayList<>();

        try {
            List<Application> applications = applicationPersistence.findAll();
            for (Application application : applications) {
                if (!application.getCategoryName().equals(AppManagerConstants.COMMUN_CATEGORIE)
                        && !application.getCategoryName().equals(AppManagerConstants.PENTILA_CATEGORIE)
                        && AuthorizedSchoolLocalServiceUtil.isSchoolAuthorized(application.getApplicationId(), schoolId)) {
                    schoolApplications.add(application);
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching school applications for school " + schoolId, e);
        }
        return schoolApplications;
    }

    public JSONObject convertToJSON(Application application, boolean withDetails) {
        JSONObject applicationJson = new JSONObject();

        applicationJson.put(JSONConstants.APPLICATION_ID, application.getApplicationId());
        applicationJson.put(JSONConstants.APPLICATION_NAME, application.getApplicationName());
        applicationJson.put(JSONConstants.APPLICATION_KEY, application.getApplicationKey());
        applicationJson.put(JSONConstants.CATEGORY, application.getCategoryName());
        applicationJson.put(JSONConstants.IMAGE, application.getImage());
        applicationJson.put(JSONConstants.HAS_CUSTOM_URL, application.getHasCustomUrl());
        applicationJson.put(JSONConstants.EXPORT_USER, application.getExportUser());
        applicationJson.put(JSONConstants.EXPORT_PARENT, application.getExportParent());
        applicationJson.put(JSONConstants.EXPORT_STUDENT, application.getExportStudent());
        applicationJson.put(JSONConstants.EXPORT_TEACHER, application.getExportTeacher());
        applicationJson.put(JSONConstants.EXPORT_OTHER, application.getExportOther());

        if (withDetails) {
            applicationJson.put(JSONConstants.HAS_GLOBAL_URL, application.getHasGlobalUrl());
            applicationJson.put(JSONConstants.MENU_ENTRY_ID, application.getMenuEntryId());
            applicationJson.put(JSONConstants.GLOBAL_URL, application.getGlobalUrl());

            JSONArray jsonSchools = new JSONArray();
            try {
                List<Long> authorizedSchoolIds = AuthorizedSchoolLocalServiceUtil.getAuthorizedSchoolIds(application.getApplicationId());
                for (Long schoolId : authorizedSchoolIds) {
                    JSONObject jsonSchool = new JSONObject();
                    jsonSchool.put(JSONConstants.SCHOOL_NAME, OrganizationLocalServiceUtil.getOrganization(schoolId).getName());
                    jsonSchool.put(JSONConstants.SCHOOL_ID, schoolId);
                    jsonSchools.put(jsonSchool);
                }
            } catch (Exception e) {
                logger.error("Error when fetching filtered schools for applicationId " + application.getApplicationId(), e);
            }
            applicationJson.put(JSONConstants.AUTHORIZED_SCHOOLS, jsonSchools);

            JSONArray jsonDefaultRoles = new JSONArray();
            try {
                jsonDefaultRoles = DefaultRoleLocalServiceUtil.getDefaultRoleJson(application.getApplicationId());
            } catch (Exception e) {
                logger.error("Error when fetching default roles for applicationId " + application.getApplicationId(), e);
            }
            applicationJson.put(JSONConstants.DEFAULT_ROLES, jsonDefaultRoles);

        }
        return applicationJson;
    }

    public Application findApplicationByKey(String key) throws SystemException {
        return applicationPersistence.fetchByapplicationKey(key);
    }

    public String getApplicationURLByKey(long schoolId, String applicationKey) {
        try {
            Application application = applicationPersistence.fetchByapplicationKey(applicationKey);

            if (application != null) {
                if (application.isHasGlobalUrl()) {
                    return application.getGlobalUrl();
                } else {
                    // Get school-specific application url
                    Broadcast applicationBroadcast = BroadcastLocalServiceUtil.getByApplicationIdEtabId(application.getApplicationId(), schoolId);
                    if (applicationBroadcast != null) {
                        return applicationBroadcast.getApplicationUrl();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting application url for application key " + applicationKey, e);
        }

        return "";
    }

    public List<String> getCategories() throws SystemException {
        List<String> categories = new ArrayList<>();

        List<Application> applications = applicationLocalService.getAllApplications();
        for (Application application : applications) {
            if (!categories.contains(application.getCategoryName())) {
                categories.add(application.getCategoryName());
            }
        }

        return categories;
    }

    public List<Application> getUserApplications(User user) {
        List<Application> applicationList = new ArrayList<>();

        try {
            List<Organization> schools = UserOrgsLocalServiceUtil.getUserSchools(user);
            List<Long> schoolIds = new ArrayList<>();
            for (Organization school : schools) {
                schoolIds.add(school.getOrganizationId());
            }

            // Loop over all 'Pentila' applications and add them to administrator users
            List<Application> allApplications = applicationPersistence.findAll();
            for (Application application : allApplications) {
                // 'Pentila' applications are broadcasted to administrator users
                if (application.getCategoryName().equals(AppManagerConstants.PENTILA_CATEGORIE)) {
                    if (RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                        applicationList.add(application);
                    }
                } else if (application.getCategoryName().equals(AppManagerConstants.COMMUN_CATEGORIE)) {
                    // Must match authorized school
                    List<Long> authorizedSchoolIds = AuthorizedSchoolLocalServiceUtil.getAuthorizedSchoolIds(application.getApplicationId());
                    boolean schoolMatches = authorizedSchoolIds.isEmpty();
                    if (!schoolMatches) {
                        for (Long schoolId: schoolIds) {
                            schoolMatches = schoolMatches || authorizedSchoolIds.contains(schoolId);
                        }
                    }
                    // Must match role
                    boolean roleMatches = DefaultRoleLocalServiceUtil.hasUserRole(application.getApplicationId(), user.getUserId());
                    if (schoolMatches && roleMatches) {
                        applicationList.add(application);
                    }
                } else {
                    for (Long schoolId : schoolIds) {
                        Broadcast applicationBroadcast = BroadcastLocalServiceUtil.getByApplicationIdEtabId(application.getApplicationId(), schoolId);
                        if (applicationBroadcast == null || !applicationBroadcast.getIsBroadcasted()) {
                            continue;
                        }
                        // If application is broadcasted, analyze the rules
                        List<BroadcastRule> schoolRules = broadcastRulePersistence.findByapplicationId_schoolId(application.getApplicationId(), schoolId);
                        for (BroadcastRule schoolRule : schoolRules) {
                            if ((schoolRule.getRoleId() == 0 || RoleLocalServiceUtil.hasUserRole(user.getUserId(), schoolRule.getRoleId()))
                                    && ((schoolRule.getOrgId() == 0) || UserLocalServiceUtil.hasOrganizationUser(schoolRule.getOrgId(), user.getUserId()))) {
                                // TODO manage personal groups
                                applicationList.add(application);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error getting applications for user " + user.getFullName(), e);
        }

        return applicationList;
    }

}
