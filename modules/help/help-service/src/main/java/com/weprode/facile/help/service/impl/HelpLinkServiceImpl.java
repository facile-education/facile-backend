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

package com.weprode.facile.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.help.service.HelpLinkLocalServiceUtil;
import com.weprode.facile.help.service.base.HelpLinkServiceBaseImpl;
import com.weprode.facile.help.utils.HelpUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpLink"
        },
        service = AopService.class
)
public class HelpLinkServiceImpl extends HelpLinkServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpLinkServiceImpl.class);

    @JSONWebService(value = "save-link", method = "POST")
    public JSONObject saveLink(String link) {

        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves help link");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONObject savedLink = HelpUtil.saveLink(new JSONObject(link));

            result.put(JSONConstants.LINK, savedLink);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-link", method = "GET")
    public JSONObject deleteLink(long linkId) {

        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes help link");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            HelpLinkLocalServiceUtil.deleteHelpLink(linkId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help link", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
