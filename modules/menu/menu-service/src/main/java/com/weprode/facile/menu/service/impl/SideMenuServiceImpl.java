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

package com.weprode.facile.menu.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.menu.enums.MenuEntry;
import com.weprode.facile.menu.service.SideMenuLocalServiceUtil;
import com.weprode.facile.menu.service.SideMenuService;
import com.weprode.facile.menu.service.base.SideMenuServiceBaseImpl;
import com.weprode.facile.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.school.life.service.RenvoiLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=menu",
                "json.web.service.context.path=SideMenu"
        },
        service = SideMenuService.class
)
public class SideMenuServiceImpl extends SideMenuServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SideMenuServiceImpl.class);

    @JSONWebService(value = "get-side-menu", method = "GET")
    public JSONObject getSideMenu() {
        JSONObject result = new JSONObject();

        logger.debug("User fetching side menu.");

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
            List<MenuEntry> userMenu = SideMenuLocalServiceUtil.getUserMenu(user);

            // Notifications
            JSONObject jsonNotifications = new JSONObject();
            jsonNotifications.put(JSONConstants.MESSAGING, MessageLocalServiceUtil.countUnreadMessages(MessageFolderLocalServiceUtil.getUserInboxFolder(user.getUserId()).getFolderId()));
            if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                jsonNotifications.put(JSONConstants.COURSES, HomeworkLocalServiceUtil.countUndoneHomeworks(user.getUserId()));
            } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                jsonNotifications.put(JSONConstants.COURSES, HomeworkLocalServiceUtil.countHomeworksToCorrect(user.getUserId()));
            }
            jsonNotifications.put(JSONConstants.SCHOOLLIFE, RenvoiLocalServiceUtil.getTeacherPendingRenvois(user.getUserId()).size());
            result.put(JSONConstants.NOTIFICATIONS, jsonNotifications);

            result.put(JSONConstants.MENU, getMenuAsJSON(userMenu));
            boolean isMenuExpanded = false;
            try {
                isMenuExpanded = !UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getHideMenu();
            } catch (PortalException e) {
                logger.error(e);
            }
            result.put(JSONConstants.EXPANDED, isMenuExpanded);

            // Timeouts
            result.put(JSONConstants.SESSION_TIMEOUT, PrefsPropsUtil.getInteger(PropsKeys.SESSION_TIMEOUT) * 60000); // From tomcat/conf/web.xml or tomcat/lfr/WEB-INF/web.xml
            result.put(JSONConstants.SESSION_TIMEOUT_WARNING, PrefsPropsUtil.getInteger(PropsKeys.SESSION_TIMEOUT_WARNING) * 60000); // From portal-ext.properties

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error building menu", e);
        }

        return result;
    }

    private JSONArray getMenuAsJSON(List<MenuEntry> menuEntries) {
        JSONArray menu = new JSONArray();

        for (MenuEntry menuEntry : menuEntries) {
            menu.put(getEntryAsJSON(menuEntry));
        }

        return menu;
    }

    private JSONObject getEntryAsJSON(MenuEntry menuEntry) {
        JSONObject entry = new JSONObject();

        entry.put(JSONConstants.ID, menuEntry.getId());
        entry.put(JSONConstants.ICON, menuEntry.getIcon());
        entry.put(JSONConstants.I18N_KEY, menuEntry.getKey());
        entry.put(JSONConstants.POSITION, menuEntry.getPosition());
        entry.put(JSONConstants.PARAM, menuEntry.getParam());
        entry.put(JSONConstants.COMPONENT, menuEntry.getComponent());
        if (menuEntry.isLandingPage()) {
            entry.put(JSONConstants.IS_LANDING_PAGE, menuEntry.isLandingPage());
        }
        // Recursive
        if (menuEntry.getEntries() != null) {
            entry.put(JSONConstants.MENU, getMenuAsJSON(menuEntry.getEntries()));
        }

        return entry;
    }
}