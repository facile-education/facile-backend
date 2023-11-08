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
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.model.OrgDetails;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.model.Affectation;
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
            JSONArray jsonUsers = new JSONArray();
            List<User> affectedUsers = AffectationLocalServiceUtil.getAffectedUsers(schoolId, filter);
            for (User affectedUser : affectedUsers) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put(JSONConstants.USER_ID, affectedUser.getUserId());
                jsonUser.put(JSONConstants.LAST_NAME, affectedUser.getLastName());
                jsonUser.put(JSONConstants.FIRST_NAME, affectedUser.getFirstName());
                jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(affectedUser));

                // Get affectations
                List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(affectedUser.getUserId(), schoolId);
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
                            jsonAffectation.put(JSONConstants.AFFECTATION_DATE,
                                    new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(userAffectation.getAffectationDate()));
                            jsonAffectations.put(jsonAffectation);
                        }
                    } catch (Exception e) {
                        logger.error("Error processing affectation for user " + affectedUser.getUserId(), e);
                    }
                }
                jsonUser.put(JSONConstants.AFFECTATIONS, jsonAffectations);

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
            Date expireDate = null;
            if (!expirationDate.equals("")) {
                expireDate = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).parse(expirationDate);
            }
            boolean success = AffectationLocalServiceUtil.addUserAffectation(userId, orgId, user.getUserId(), expireDate);
            result.put(JSONConstants.SUCCESS, success);

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
            boolean success = AffectationLocalServiceUtil.removeUserAffectation(userId, orgId);
            result.put(JSONConstants.SUCCESS, success);
        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
    
}
