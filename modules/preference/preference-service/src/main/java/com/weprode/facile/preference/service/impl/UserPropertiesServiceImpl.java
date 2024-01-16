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

package com.weprode.facile.preference.service.impl;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.preference.model.NotifyConfig;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesService;
import com.weprode.facile.preference.service.base.UserPropertiesServiceBaseImpl;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        property = {
                "json.web.service.context.name=preference",
                "json.web.service.context.path=UserProperties"
        },
        service = UserPropertiesService.class
)
public class UserPropertiesServiceImpl extends UserPropertiesServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserPropertiesServiceImpl.class);

    private static final int NONE = 0;

    @JSONWebService(value = "update-side-menu-state", method = "POST")
    public JSONObject updateSideMenuState(boolean isExpanded) {
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
            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            userProperties.setHideMenu(!isExpanded);
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not update menu state for userId " + user.getUserId(), e);
        }

        return result;
    }

    @JSONWebService(value = "update-user-picture", method = "POST")
    public JSONObject updateUserPicture(File picture) {
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
            if (picture.length() == 0) {	// Add condition to avoid nullPointerException
                UserServiceUtil.deletePortrait(user.getUserId());

                // Manually set the portraitId to 0
                user.setPortraitId(0);
                UserLocalServiceUtil.updateUser(user);
            } else {
                user = UserServiceUtil.updatePortrait(user.getUserId(), Files.readAllBytes(picture.toPath()));
            }

            result.put(JSONConstants.IMAGE_URL, UserUtilsLocalServiceUtil.getUserPicture(user));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not update picture for userId " + user.getUserId(), e);
        }

        return result;
    }

    @JSONWebService(value = "update-theme-color", method = "POST")
    public JSONObject updateThemeColor(String color) {
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
            // Check color format
            Pattern pattern = Pattern.compile("([0-9a-f]{3}|[0-9a-f]{6})"); // ([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8}) if transparency allowed
            Matcher matcher = pattern.matcher(color);
            if (matcher.matches()) {
                UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
                userProperties.setThemeColor(color);
                UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

                result.put(JSONConstants.SUCCESS, true);
            } else {
                result.put(JSONConstants.ERROR, "FORMAT_EXCEPTION");
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

        } catch (Exception e) {
            logger.error("Could not update theme color for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-report-frequency", method = "POST")
    public JSONObject updateReportFrequency(int frequency) {
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
            // Frequency : NONE = 0, DAILY = 1, WEEKLY = 2
            NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());

            userNotificationConfig.setActivate(frequency != NONE);
            userNotificationConfig.setDigestPeriod(frequency);
            userNotificationConfig.setUserId(user.getUserId());

            NotifyConfigLocalServiceUtil.updateNotifyConfig(userNotificationConfig);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Could not update notification frequency for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

}
