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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.service.ApplicationLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastLocalServiceUtil;
import com.weprode.facile.application.service.base.ApplicationServiceBaseImpl;
import com.weprode.facile.application.service.utils.ExportUtils;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.menu.enums.MenuEntry;
import com.weprode.facile.menu.service.SideMenuLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=application",
                "json.web.service.context.path=Application"
        },
        service = AopService.class
)
public class ApplicationServiceImpl extends ApplicationServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(ApplicationServiceImpl.class);

    @JSONWebService(value = "add-application", method = "POST")
    public JSONObject addApplication(String applicationName, String applicationKey, String category, long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
                                 boolean exportUser, boolean exportStudent, boolean exportParent, boolean exportTeacher, boolean exportOther,
                                 String defaultRoles, String authorizedSchools) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getFullName() + " adds a new application " + applicationName);
            Application createdApplication = ApplicationLocalServiceUtil.addApplication(applicationName, applicationKey, category, menuEntryId, image, hasCustomUrl, globalUrl,
                    exportUser, exportStudent, exportParent, exportTeacher, exportOther, defaultRoles, authorizedSchools);
            result.put(JSONConstants.SERVICE, ApplicationLocalServiceUtil.convertToJSON(createdApplication, true));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error creating new application", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "edit-application", method = "POST")
    public JSONObject editApplication(long applicationId, String applicationName, String applicationKey, String category, long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
                                  boolean exportUser, boolean exportStudent, boolean exportParent, boolean exportTeacher, boolean exportOther,
                                  String defaultRoles, String authorizedSchools) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getFullName() + " edits application " + applicationId + " with name " + applicationName);
            Application editedApplication = ApplicationLocalServiceUtil.editApplication(applicationId, applicationName, applicationKey, category, menuEntryId, image, hasCustomUrl, globalUrl,
                    exportUser, exportStudent, exportParent, exportTeacher, exportOther, defaultRoles, authorizedSchools);
            result.put(JSONConstants.SERVICE, ApplicationLocalServiceUtil.convertToJSON(editedApplication, true));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error editing application " + applicationId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "remove-application", method = "GET")
    public JSONObject removeApplication(long applicationId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            boolean res = ApplicationLocalServiceUtil.removeApplication(applicationId);
            result.put(JSONConstants.SUCCESS, res);
        } catch (Exception e) {
            logger.error("Error editing application " + applicationId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "get-school-applications", method = "GET")
    public JSONObject getSchoolApplications(long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)
                && !RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray jsonApplications = new JSONArray();

            if (schoolId == 0 && RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                List<Application> schoolApplications = ApplicationLocalServiceUtil.getAllApplications();
                for (Application schoolApplication : schoolApplications) {
                    jsonApplications.put(ApplicationLocalServiceUtil.convertToJSON(schoolApplication, RoleUtilsLocalServiceUtil.isAdministrator(user)));
                }
            } else {
                List<Application> schoolApplications = ApplicationLocalServiceUtil.getSchoolApplications(schoolId);
                for (Application schoolApplication : schoolApplications) {
                    JSONObject jsonApplication = ApplicationLocalServiceUtil.convertToJSON(schoolApplication, false);
                    Broadcast applicationBroadcast = BroadcastLocalServiceUtil.getByApplicationIdEtabId(schoolApplication.getApplicationId(), schoolId);
                    if (applicationBroadcast != null) {
                        jsonApplication.put(JSONConstants.IS_BROADCASTED, applicationBroadcast.getIsBroadcasted());
                        jsonApplication.put(JSONConstants.APPLICATION_URL, applicationBroadcast.getApplicationUrl());
                    } else {
                        jsonApplication.put(JSONConstants.IS_BROADCASTED, false);
                        jsonApplication.put(JSONConstants.APPLICATION_URL, StringPool.BLANK);
                    }
                    jsonApplications.put(jsonApplication);
                }
            }
            result.put(JSONConstants.SERVICES, jsonApplications);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching applications for school " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "export", method = "GET")
    public JSONObject export(long applicationId, long schoolId, String roleName) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            result = new JSONObject(
                    new ExportUtils().exportFile(user.getUserId(), applicationId, schoolId, roleName));

        } catch (Exception e) {
            logger.error("Error fetching applications for school " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-user-applications", method = "GET")
    public JSONObject getUserApplications() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonApplications = new JSONArray();
            List<Application> userApplications = ApplicationLocalServiceUtil.getUserApplications(user);

            for (Application userApplication : userApplications) {
                // Hide content creation apps (geogebra, etc)
                if (!userApplication.getCategoryName().equals("Création de contenu")) {
                    JSONObject jsonApplication = ApplicationLocalServiceUtil.convertToJSON(userApplication, false);
                    jsonApplications.put(jsonApplication);
                }
            }

            result.put(JSONConstants.SERVICES, jsonApplications);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when fetching user applications", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-all-applications", method = "GET")
    public JSONObject getAllApplications() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) || !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        try {
            JSONArray jsonApplications = new JSONArray();
            List<Application> entApplications = ApplicationLocalServiceUtil.getAllApplications();

            if (entApplications != null) {
                for (Application entApplication : entApplications) {
                    JSONObject jsonApplication = ApplicationLocalServiceUtil.convertToJSON(entApplication, false);
                    jsonApplications.put(jsonApplication);
                }
            }

            result.put(JSONConstants.SERVICES, jsonApplications);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when fetching all ENT applications", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    /* Get all menu entries except for admin ones. */
    @JSONWebService(value = "get-stat-applications", method = "GET")
    public JSONObject getStatApplications (long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                && !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }


        try {
            JSONArray applications = new JSONArray();
            List<MenuEntry> entries;
            if (schoolId != 0) {
                entries = SideMenuLocalServiceUtil.getSchoolMenu(schoolId);
            } else {
                entries = MenuEntry.getFullMenu();
            }

            for (MenuEntry entry : entries) {
                if (entry.getEntries() == null || entry.getEntries().isEmpty()) {
                    applications.put(convertMenuEntryToJSON(entry));

                } else if (!entry.getKey().equals("administration")) {
                    for (MenuEntry subEntry : entry.getEntries()) {
                        applications.put(convertMenuEntryToJSON(subEntry));
                    }
                }
            }
            result.put(JSONConstants.SERVICES, applications);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-resource-urls", method = "GET")
    public JSONObject getResourceUrls(long menuEntryId) {
        JSONObject result = new JSONObject();

        logger.info("User get resource urls.");

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        Application currentApp = null;

        List<Application> userApplications = ApplicationLocalServiceUtil.getUserApplications(user);
        for (Application application : userApplications) {
            if (menuEntryId == application.getMenuEntryId()) {
                currentApp = application;
                break;
            }
        }

        if (currentApp == null) {
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        if (currentApp.getHasGlobalUrl()) {
            result.put(JSONConstants.URL, currentApp.getGlobalUrl());
        } else {
            List<Organization> schools = UserOrgsLocalServiceUtil.getUserSchools(user);
            JSONArray array = new JSONArray();
            for (Organization school : schools) {
                String url = ApplicationLocalServiceUtil.getApplicationURLByKey(school.getOrganizationId(), currentApp.getApplicationKey());

                JSONObject schoolUrl = new JSONObject();
                schoolUrl.put(JSONConstants.SCHOOL_ID, school.getOrganizationId());
                schoolUrl.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true));
                schoolUrl.put(JSONConstants.URL, url);
                array.put(schoolUrl);
            }
            result.put(JSONConstants.URL, array);
        }
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    private JSONObject convertMenuEntryToJSON (MenuEntry entry) {
        JSONObject jsonApplication = new JSONObject();
        jsonApplication.put(JSONConstants.APPLICATION_ID, entry.getId());
        jsonApplication.put(JSONConstants.NAME, entry.getKey());
        return jsonApplication;
    }
    
}
