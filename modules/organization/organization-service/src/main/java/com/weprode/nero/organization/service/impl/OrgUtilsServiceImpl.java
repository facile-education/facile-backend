package com.weprode.nero.organization.service.impl;

import com.liferay.portal.kernel.exception.RolePermissionsException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsService;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.organization.service.base.OrgUtilsServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=organization",
                "json.web.service.context.path=OrgUtils"
        },
        service = OrgUtilsService.class
)
public class OrgUtilsServiceImpl extends OrgUtilsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(OrgUtilsServiceImpl.class);

    @JSONWebService(value = "get-visibility-schools", method = "GET")
    public JSONObject getVisibilitySchools() {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonSchools = JSONFactoryUtil.createJSONArray();
            List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);
            for (Organization userSchool : userSchools) {
                JSONObject jsonSchool = JSONFactoryUtil.createJSONObject();
                jsonSchool.put(JSONConstants.SCHOOL_ID, userSchool.getOrganizationId());
                jsonSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                jsonSchools.put(jsonSchool);
            }

            result.put(JSONConstants.SCHOOLS, jsonSchools);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-all-schools", method = "GET")
    public JSONObject getAllSchools() {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User agent;
        try {
            agent = getGuestOrUser();

            if (agent.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
            if (!RoleUtilsLocalServiceUtil.isTeacher(agent) && !RoleUtilsLocalServiceUtil.isPersonal(agent)) {
                throw new RolePermissionsException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonSchools = JSONFactoryUtil.createJSONArray();
            List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
            for (Organization userSchool : schools) {
                JSONObject jsonSchool = JSONFactoryUtil.createJSONObject();
                jsonSchool.put(JSONConstants.SCHOOL_ID, userSchool.getOrganizationId());
                jsonSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                jsonSchools.put(jsonSchool);
            }

            result.put(JSONConstants.SCHOOLS, jsonSchools);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-school-classes", method = "GET")
    public JSONObject getSchoolClasses(long schoolId, boolean includeCours) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            } else if (!RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        if (schoolId == 0) {
            schoolId = UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId();
        }

        JSONArray jsonOrgs = JSONFactoryUtil.createJSONArray();

        // Loop over schools
        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.CLASS_TYPE);
        if (includeCours) {
            types.add(OrgConstants.COURS_TYPE);
        }
        List<Organization> schoolOrganizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId,
                types, null, false);

        // Loop over classes
        for (Organization org : schoolOrganizations) {
            if (org.getParentOrganizationId() == schoolId) {
                JSONObject jsonOrg = JSONFactoryUtil.createJSONObject();
                jsonOrg.put(JSONConstants.ORG_ID, org.getOrganizationId());
                jsonOrg.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                jsonOrgs.put(jsonOrg);
            }
        }

        result.put(JSONConstants.SUCCESS, true);
        result.put(JSONConstants.ORGS, jsonOrgs);

        return result;
    }

    /**
     * Return the volee list for given schoolId or all the volees in user school list
     * if schoolId is 0
     */
    @JSONWebService(value = "get-school-volees", method = "GET")
    public JSONObject getSchoolVolees(long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            } else if (!RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        List<Organization> userSchools = new ArrayList<>();
        if (schoolId == 0) {
            // Get Volees for all user schools
            userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
        } else {
            try {
                userSchools.add(OrganizationLocalServiceUtil.getOrganization(schoolId));
            } catch (Exception e) {
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        }

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.VOLEE_TYPE);

        JSONArray voleeArray = JSONFactoryUtil.createJSONArray();
        List<String> voleeNames = new ArrayList<>();

        // Get volees from user schools
        for (Organization school : userSchools) {
            List<Organization> schoolVolees = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, null, false);

            for (Organization volee: schoolVolees) {
                String name = OrgUtilsLocalServiceUtil.formatOrgName(volee.getName(), false);

                if (!voleeNames.contains(name)) {
                    voleeArray.put(name);
                    voleeNames.add(name);
                }
            }
        }

        result.put(JSONConstants.SUCCESS, true);
        result.put(JSONConstants.VOLEES, voleeArray);

        return result;
    }

}
