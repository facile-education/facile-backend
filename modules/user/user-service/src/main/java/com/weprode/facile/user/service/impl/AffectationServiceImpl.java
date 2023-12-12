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
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.AffectationLocalServiceUtil;
import com.weprode.facile.user.service.AffectationService;
import com.weprode.facile.user.service.base.AffectationServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=Affectation"
        },
        service = AffectationService.class
)
public class AffectationServiceImpl extends AffectationServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AffectationServiceImpl.class);

    @JSONWebService(value = "get-affected-users", method = "GET")
    public JSONObject getAffectedUsers(long schoolId, String filter) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets affected users");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray jsonUsers = new JSONArray();
            List<User> affectedUsers = AffectationLocalServiceUtil.getAffectedUsers(schoolId, filter);
            for (User affectedUser : affectedUsers) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put(JSONConstants.USER_ID, affectedUser.getUserId());
                jsonUser.put(JSONConstants.LAST_NAME, affectedUser.getLastName());
                jsonUser.put(JSONConstants.FIRST_NAME, affectedUser.getFirstName());
                jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(affectedUser));
                jsonUser.put(JSONConstants.AFFECTATIONS, AffectationLocalServiceUtil.convertUserAffectations(affectedUser.getUserId(), schoolId));

                jsonUsers.put(jsonUser);
            }

            result.put(JSONConstants.USERS, jsonUsers);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-user-affectation", method = "GET")
    public JSONObject addUserAffectation(long userId, long orgId, String expirationDate) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds user affectation");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date expireDate = null;
            if (!expirationDate.equals("")) {
                expireDate = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).parse(expirationDate);
            }
            boolean success = AffectationLocalServiceUtil.addUserAffectation(userId, orgId, user.getUserId(), expireDate);
            result.put(JSONConstants.SUCCESS, success);
            long schoolId = UserOrgsLocalServiceUtil.getUserSchools(user).get(0).getOrganizationId();
            result.put(JSONConstants.AFFECTATIONS, AffectationLocalServiceUtil.convertUserAffectations(userId, schoolId));

        } catch (Exception e) {
            logger.error("Error adding affectation for user " + userId + " to orgId " + orgId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }


    @JSONWebService(value = "remove-user-affectation", method = "GET")
    public JSONObject removeUserAffectation(long userId, long orgId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " removes user affectation");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            boolean success = AffectationLocalServiceUtil.removeUserAffectation(userId, orgId);
            result.put(JSONConstants.SUCCESS, success);
            long schoolId = UserOrgsLocalServiceUtil.getUserSchools(user).get(0).getOrganizationId();
            result.put(JSONConstants.AFFECTATIONS, AffectationLocalServiceUtil.convertUserAffectations(userId, schoolId));
        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
    
}
