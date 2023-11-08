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

package com.weprode.facile.organization.service.impl;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsService;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.organization.service.base.OrgUtilsServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonSchools = new JSONArray();
            List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);
            for (Organization userSchool : userSchools) {
                JSONObject jsonSchool = new JSONObject();
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isTeacher(user) &&
                !RoleUtilsLocalServiceUtil.isPersonal(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray jsonSchools = new JSONArray();
            List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
            for (Organization userSchool : schools) {
                JSONObject jsonSchool = new JSONObject();
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isPersonal(user)
                && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        if (schoolId == 0) {
            schoolId = UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId();
        }

        JSONArray jsonOrgs = new JSONArray();

        // Loop over schools
        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.CLASS_TYPE);
        if (includeCours) {
            types.add(OrgConstants.COURS_TYPE);
        }
        List<Organization> schoolOrganizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, false);

        // Loop over classes
        for (Organization org : schoolOrganizations) {
            if (org.getParentOrganizationId() == schoolId) {
                JSONObject jsonOrg = new JSONObject();
                jsonOrg.put(JSONConstants.ORG_ID, org.getOrganizationId());
                jsonOrg.put(JSONConstants.GROUP_ID, org.getGroupId());
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
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

        JSONArray voleeArray = new JSONArray();
        List<String> voleeNames = new ArrayList<>();

        // Get volees from user schools
        for (Organization school : userSchools) {
            List<Organization> schoolVolees = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);

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
