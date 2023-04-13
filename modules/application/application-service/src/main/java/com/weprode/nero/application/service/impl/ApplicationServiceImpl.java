package com.weprode.nero.application.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.RolePermissionsException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.application.model.Broadcast;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.application.service.base.ApplicationServiceBaseImpl;
import com.weprode.nero.application.service.utils.ExportUtils;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.menu.enums.MenuEntry;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
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

    @JSONWebService(value = "get-portlets", method = "GET")
    public JSONObject getPortlets() {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            return ApplicationLocalServiceUtil.getPortlets(user);
        } catch (Exception e) {
            logger.error("Error fetching schools", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "add-application", method = "POST")
    public JSONObject addApplication(String applicationName, String applicationKey, String category, long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
                                 boolean exportUser, boolean exportStudent, boolean exportParent, boolean exportTeacher, boolean exportOther,
                                 String defaultRoles, String authorizedSchools) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)
                && !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonApplications = JSONFactoryUtil.createJSONArray();

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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            result = JSONFactoryUtil.createJSONObject(
                    ExportUtils.exportFile(user.getUserId(), applicationId, schoolId, roleName));

        } catch (Exception e) {
            logger.error("Error fetching applications for school " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-user-applications", method = "GET")
    public JSONObject getUserApplications() {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to fetch applications", e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonApplications = JSONFactoryUtil.createJSONArray();
            List<Application> userApplications = ApplicationLocalServiceUtil.getUserApplications(user);

            for (Application userApplication : userApplications) {
                JSONObject jsonApplication = ApplicationLocalServiceUtil.convertToJSON(userApplication, false);
                jsonApplications.put(jsonApplication);
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) || !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to fetch all applications", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonApplications = JSONFactoryUtil.createJSONArray();
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                throw new AuthException();
            }

            if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                    && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                    && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    && !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
                throw new RolePermissionsException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to fetch all applications", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        JSONArray applications = JSONFactoryUtil.createJSONArray();

        try {
            // TODO Add lool/cool to applications ?
            // TODO get school menu instead of full
            // New arraylist to avoid read only list
            for (MenuEntry entry : MenuEntry.getFullMenu()) {
                if (entry.getEntries().isEmpty()) {
                    applications.put(convertMenuEntryToJSON(entry));

                } else if (!entry.getKey().equals("administration")) {
                    for (MenuEntry subEntry : entry.getEntries()) {
                        applications.put(convertMenuEntryToJSON(subEntry));
                    }
                }
            }

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
        }

        result.put(JSONConstants.SERVICES, applications);

        return result;
    }

    private JSONObject convertMenuEntryToJSON (MenuEntry entry) {
        JSONObject jsonApplication = JSONFactoryUtil.createJSONObject();
        jsonApplication.put(JSONConstants.APPLICATION_ID, entry.getId());
        jsonApplication.put(JSONConstants.NAME, entry.getKey());

        return jsonApplication;
    }
    
}
