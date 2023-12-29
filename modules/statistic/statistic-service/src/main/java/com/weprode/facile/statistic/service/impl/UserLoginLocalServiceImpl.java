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

package com.weprode.facile.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.statistic.model.UserLogin;
import com.weprode.facile.statistic.service.UserLoginLocalServiceUtil;
import com.weprode.facile.statistic.service.base.UserLoginLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.statistic.model.UserLogin",
        service = AopService.class
)
public class UserLoginLocalServiceImpl extends UserLoginLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserLoginLocalServiceImpl.class);

    public boolean addUserLogin(User user, boolean isMobileApp) {
        long userLoginId;

        try {
            Date now = new Date();
            OrderByComparator<UserLogin> odc = OrderByComparatorFactoryUtil.create("Statistics_UserLogin", "loginDate", false);
            UserLogin lastLogin = userLoginPersistence.fetchByuserId_First(user.getUserId(), odc);

            // Do not register new login if less than 5 minutes have passed
            if (lastLogin == null || now.getTime() - lastLogin.getLoginDate().getTime() > 5*60*1000) {
                userLoginId = counterLocalService.increment();
                UserLogin userLogin = userLoginLocalService.createUserLogin(userLoginId);
                userLogin.setUserId(user.getUserId());
                userLogin.setLoginDate(now);
                userLogin.setIsMobileApp(isMobileApp);

                // Role
                int role = getUserRole(user);
                userLogin.setRole(role);

                // Rattach school
                Organization rattachSchool = UserOrgsLocalServiceUtil.getEtabRatachement(user);
                userLogin.setSchoolId(rattachSchool != null ? rattachSchool.getOrganizationId() : 0);
                UserLoginLocalServiceUtil.updateUserLogin(userLogin);
            }

            return true;
        } catch (Exception e) {
            logger.error("Error adding UserLogin for user " + user.getFullName() + " (id "+user.getUserId()+")", e);
        }

        return false;
    }

    public List<Long> getLoggedStudents(long schoolId, int nbDays) {
        List<Long> loggedUserIds = new ArrayList<>();

        // Threashold date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -nbDays);
        Date thresholdDate = cal.getTime();

        try {
            List<UserLogin> userLoginList = userLoginPersistence.findByschoolId_role(schoolId, 1);
            if (userLoginList != null) {
                for (UserLogin userLogin : userLoginList) {
                    if (userLogin.getLoginDate().after(thresholdDate)) {
                        loggedUserIds.add(userLogin.getUserId());
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return loggedUserIds;
    }

    private int getUserRole(User user) {
        if (RoleUtilsLocalServiceUtil.isStudent(user)) {
            return 1;
        } else if (RoleUtilsLocalServiceUtil.isParent(user)) {
            return 2;
        } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return 3;
        } else {
            return 4;
        }
    }
}
