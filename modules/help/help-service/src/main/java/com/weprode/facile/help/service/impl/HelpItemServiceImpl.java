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
import com.weprode.facile.help.service.HelpItemLocalServiceUtil;
import com.weprode.facile.help.service.base.HelpItemServiceBaseImpl;
import com.weprode.facile.help.utils.HelpUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpItem"
        },
        service = AopService.class
)
public class HelpItemServiceImpl extends HelpItemServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpItemServiceImpl.class);

    @JSONWebService(value = "get-help-item", method = "GET")
    public JSONObject getHelpItemDetails(long itemId) {
        logger.info("Getting item content for id = " + itemId);
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

        try {
            JSONObject jsonHelpItem = HelpUtil.getHelpItemDetails(itemId);

            if (jsonHelpItem != null) {
                result.put(JSONConstants.HELP_ITEM, jsonHelpItem);
                result.put(JSONConstants.SUCCESS, true);
            }
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-help-item-position", method = "POST")
    public JSONObject saveHelpItemPosition(long categoryId, String item) {
        logger.info("Moving item with content = " + item);
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
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONObject jsonHelpItem = new JSONObject(item);
            HelpUtil.saveHelpItemPosition(categoryId, jsonHelpItem);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-help-item", method = "POST")
    public JSONObject saveHelpItem (long categoryId, String item) {
        logger.info("Saving item with content = " + item);
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
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONObject jsonHelpItem = new JSONObject(item);
            JSONObject savedHelpItem = HelpUtil.saveHelpItem(categoryId, jsonHelpItem);

            result.put(JSONConstants.HELP_ITEM, savedHelpItem);
            result.put(JSONConstants.CATEGORY_ID, categoryId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when saving help item", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-item", method = "POST")
    public JSONObject deleteItem(long itemId) {
        logger.info("Deleting item with id = " + itemId);
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
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            HelpItemLocalServiceUtil.deleteItem(itemId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help item", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
