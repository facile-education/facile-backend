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
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminService;
import com.weprode.facile.user.service.base.NewsAdminServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=NewsAdmin"
        },
        service = NewsAdminService.class
)
public class NewsAdminServiceImpl extends NewsAdminServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsAdminServiceImpl.class);

    @JSONWebService(value = "add-news-delegate", method = "GET")
    public JSONObject addNewsDelegate(long userId, long schoolId) {
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
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds news delegate");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            result.put(JSONConstants.SUCCESS, NewsAdminLocalServiceUtil.addSchoolDelegate(userId, schoolId));
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "remove-news-delegate", method = "GET")
    public JSONObject removeNewsDelegate(long userId, long schoolId) {

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
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " removes news delegate");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            result.put(JSONConstants.SUCCESS, NewsAdminLocalServiceUtil.removeSchoolDelegate(userId, schoolId));
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
