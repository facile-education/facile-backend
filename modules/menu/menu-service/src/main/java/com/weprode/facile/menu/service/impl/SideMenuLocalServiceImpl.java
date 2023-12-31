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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.facile.application.constants.AppManagerConstants;
import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.service.ApplicationLocalServiceUtil;
import com.weprode.facile.application.service.AuthorizedSchoolLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastLocalServiceUtil;
import com.weprode.facile.menu.enums.MenuEntry;
import com.weprode.facile.menu.service.base.SideMenuLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        service = AopService.class
)
public class SideMenuLocalServiceImpl extends SideMenuLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SideMenuLocalServiceImpl.class);

    public List<MenuEntry> getUserMenu(User user) {
        List<Long> applicationIds = new ArrayList<>();

        List<Application> userApplications = ApplicationLocalServiceUtil.getUserApplications(user);
        for (Application application : userApplications) {
            applicationIds.add(application.getMenuEntryId());
        }

        return getMatchingMenu(applicationIds);
    }

    public List<MenuEntry> getSchoolMenu(long schoolId) {
        List<Long> applicationIds = new ArrayList<>();

        for (Application application : ApplicationLocalServiceUtil.getAllApplications()) {
            Broadcast appBroadcast = BroadcastLocalServiceUtil.getByApplicationIdEtabId(application.getApplicationId(), schoolId);
            boolean isSchoolAuthorized = AuthorizedSchoolLocalServiceUtil.isSchoolAuthorized(application.getApplicationId(), schoolId);

            if (application.getCategoryName().equals(AppManagerConstants.COMMUN_CATEGORIE)
                    || (isSchoolAuthorized && appBroadcast != null && appBroadcast.isIsBroadcasted())) {
                applicationIds.add(application.getMenuEntryId());
            }
        }

        return getMatchingMenu(applicationIds);
    }

    private List<MenuEntry> getMatchingMenu(List<Long> applicationIds) {
        List<MenuEntry> menu = new ArrayList<>();

        for (MenuEntry entry: MenuEntry.getFullMenu()) {
            if (entry.getEntries() != null && !entry.getEntries().isEmpty()) {
                List<MenuEntry> subEntries = new ArrayList<>();
                for (MenuEntry subEntry : entry.getEntries()) {
                    if (applicationIds.contains(subEntry.getId())) {
                        subEntries.add(subEntry);
                    }
                }
                if (!subEntries.isEmpty()) {
                    entry.setEntries(subEntries);
                    menu.add(entry);
                }
            } else if (applicationIds.contains(entry.getId())) {
                menu.add(entry);
            }
        }

        return menu;
    }
}