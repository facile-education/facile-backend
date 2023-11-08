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
import com.weprode.facile.help.service.HelpCategoryLocalServiceUtil;
import com.weprode.facile.help.service.base.HelpCategoryServiceBaseImpl;
import com.weprode.facile.help.utils.HelpUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpCategory"
        },
        service = AopService.class
)
public class HelpCategoryServiceImpl extends HelpCategoryServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpCategoryServiceImpl.class);

    @JSONWebService(value = "get-help-menu", method = "GET")
    public JSONObject getHelpMenu(String search) {
        logger.info("Getting help menu with search = " + search);
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
            JSONArray helpTree = HelpUtil.getHelpTree(user, search);
            result.put(JSONConstants.HELP_TREE, helpTree);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-category", method = "POST")
    public JSONObject saveHelpCategory(String categoryName, long serviceId) {
        logger.info("Updating category with name = " + categoryName);
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
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONObject jsonCategory = HelpUtil.saveHelpCategory(categoryName, serviceId);

            result.put(JSONConstants.CATEGORY, jsonCategory);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when updating help category", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-category", method = "GET")
    public JSONObject deleteCategory(long categoryId) {
        logger.info("Deleting category with id = " + categoryId);
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
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            HelpCategoryLocalServiceUtil.removeCategory(categoryId);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help category", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
