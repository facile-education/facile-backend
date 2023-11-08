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

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import com.weprode.facile.user.service.SchoolAdminLocalServiceUtil;
import com.weprode.facile.user.service.SchoolAdminService;
import com.weprode.facile.user.service.base.SchoolAdminServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=SchoolAdmin"
        },
        service = SchoolAdminService.class
)
public class SchoolAdminServiceImpl extends SchoolAdminServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SchoolAdminServiceImpl.class);

    @JSONWebService(value = "get-school-delegates", method = "GET")
    public JSONObject getSchoolDelegates(long schoolId) {
        JSONObject result = new JSONObject();

        JSONArray jsonAdmins = new JSONArray();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " fetches all admins and delegates for school " + schoolId);
            List<User> allAdmins = new ArrayList<>(SchoolAdminLocalServiceUtil.getSchoolAdmins(schoolId));

            List<User> schoolDelegates = NewsAdminLocalServiceUtil.getSchoolDelegates(schoolId);
            // Avoid duplicates
            for (User schoolDelegate : schoolDelegates) {
                if (!allAdmins.contains(schoolDelegate)) {
                    allAdmins.add(schoolDelegate);
                }
            }
            
            for (User admin : allAdmins) {
                JSONObject adminJSON = new JSONObject();
                adminJSON.put(JSONConstants.USER_ID, admin.getUserId());
                adminJSON.put(JSONConstants.LAST_NAME, admin.getLastName());
                adminJSON.put(JSONConstants.FIRST_NAME, admin.getFirstName());
                adminJSON.put(JSONConstants.DISPLAY_NAME, admin.getFullName());
                adminJSON.put(JSONConstants.IS_DIRECTION, RoleUtilsLocalServiceUtil.isDirectionMember(admin));
                adminJSON.put(JSONConstants.IS_SCHOOL_ADMIN, RoleUtilsLocalServiceUtil.isSchoolAdmin(admin, schoolId));
                adminJSON.put(JSONConstants.IS_NEWS_DELEGATE, NewsAdminLocalServiceUtil.isUserDelegate(admin));
                jsonAdmins.put(adminJSON);
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.ADMINS, jsonAdmins);
        } catch (Exception e) {
            logger.error("Error while fetching school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "get-delegation-candidates", method = "GET")
    public JSONObject getDelegationCandidates(long schoolId, String filter) {
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
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " fetches all delegation candidates for school " + schoolId);
            // Returned list of the school delegation candidates
            // minus the existing school delegates
            // minus the school's admins
            List<User> delegationCandidates = NewsAdminLocalServiceUtil.getSchoolDelegationCandidates(schoolId, filter);
            List<User> schoolDelegates = NewsAdminLocalServiceUtil.getSchoolDelegates(schoolId);
            List<User> schoolAdmins = SchoolAdminLocalServiceUtil.getSchoolAdmins(schoolId);

            JSONArray jsonCandidates = new JSONArray();
            for (User candidate : delegationCandidates) {
                if (!schoolDelegates.contains(candidate) && !schoolAdmins.contains(candidate)) {
                    JSONObject jsonCandidate = new JSONObject();
                    jsonCandidate.put(JSONConstants.USER_ID, candidate.getUserId());
                    jsonCandidate.put(JSONConstants.LAST_NAME, candidate.getLastName());
                    jsonCandidate.put(JSONConstants.FIRST_NAME, candidate.getFirstName());
                    jsonCandidate.put(JSONConstants.FULL_NAME, candidate.getFullName());
                    jsonCandidates.put(jsonCandidate);
                }
            }
            result.put(JSONConstants.CANDIDATES, jsonCandidates);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "add-school-admin", method = "GET")
    public JSONObject addSchoolAdmin(long userId, long schoolId) {
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
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        try {
            logger.info("User " + user.getUserId() + " adds user " + userId + " as school admin for school " + schoolId);
            SchoolAdminLocalServiceUtil.addSchoolAdmin(schoolId, userId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }
    
    @JSONWebService(value = "remove-school-admin", method = "GET")
    public JSONObject removeSchoolAdmin(long userId, long schoolId) {
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
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        try {
            logger.info("User " + user.getUserId() + " removes user " + userId + " from school admin for school " + schoolId);
            SchoolAdminLocalServiceUtil.removeSchoolAdmin(schoolId, userId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while fetching school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

}
