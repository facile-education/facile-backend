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

package com.weprode.facile.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.application.constants.AppManagerConstants;
import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.model.BroadcastRule;
import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.service.AuthorizedSchoolLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastRuleLocalServiceUtil;
import com.weprode.facile.application.service.DefaultRoleLocalServiceUtil;
import com.weprode.facile.application.service.ApplicationLocalServiceUtil;
import com.weprode.facile.application.service.base.BroadcastLocalServiceBaseImpl;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.application.model.Broadcast",
        service = AopService.class
)
public class BroadcastLocalServiceImpl extends BroadcastLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(BroadcastLocalServiceImpl.class);

    public Broadcast getByApplicationIdEtabId(long applicationId, long schoolId) throws SystemException {
        return broadcastPersistence.fetchByapplicationId_schoolId(applicationId, schoolId);
    }

    public Broadcast createSchoolBroadcast(long applicationId, long schoolId) throws SystemException {
        Broadcast applicationBroadcast = broadcastPersistence.create(counterLocalService.increment());
        applicationBroadcast.setApplicationId(applicationId);
        applicationBroadcast.setSchoolId(schoolId);

        return broadcastPersistence.update(applicationBroadcast);
    }

    public Broadcast updateBroadcast(long applicationId, long schoolId, boolean isBroadcasted, String applicationUrl) throws SystemException {
        Broadcast applicationBroadcast = broadcastPersistence.fetchByapplicationId_schoolId(applicationId, schoolId);

        if (applicationBroadcast == null) {
            applicationBroadcast = createSchoolBroadcast(applicationId, schoolId);
        }
        applicationBroadcast.setIsBroadcasted(isBroadcasted);
        applicationBroadcast.setApplicationUrl(applicationUrl);

        return broadcastPersistence.update(applicationBroadcast);
    }

    public boolean isApplicationBroadcastedToUser(long userId, String applicationKey) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            Application application = ApplicationLocalServiceUtil.findApplicationByKey(applicationKey);

            if (application == null) {
                return false;
            }

            List<Organization> schools = UserOrgsLocalServiceUtil.getUserSchools(user);
            List<Long> schoolIds = new ArrayList<>();
            for (Organization school : schools) {
                schoolIds.add(school.getOrganizationId());
            }

            // First check 'Common' apps
            if (application.getCategoryName().equals(AppManagerConstants.COMMUN_CATEGORIE)) {
                // Check if the school is authorized and the roles
                List<Long> authorizedSchoolIds = AuthorizedSchoolLocalServiceUtil.getAuthorizedSchoolIds(application.getApplicationId());

                boolean schoolMatches = authorizedSchoolIds.isEmpty();
                if (!schoolMatches) {
                    for (Long schoolId: schoolIds) {
                        schoolMatches = schoolMatches || authorizedSchoolIds.contains(schoolId);
                    }
                }

                // Must match role
                boolean roleMatches = DefaultRoleLocalServiceUtil.hasUserRole(application.getApplicationId(), user.getUserId());
                if (schoolMatches && roleMatches) {
                    return true;
                }
            }

            // If application is not 'Common', it is broadcasted if it is for at least 1 of users' schools
            for (Long schoolId : schoolIds) {
                // If application is not broadcasted for the school, continue
                Broadcast applicationBroadcast = null;
                try {
                    applicationBroadcast = broadcastPersistence.fetchByapplicationId_schoolId(application.getApplicationId(), schoolId);
                } catch (Exception e) {
                    logger.debug(e);
                }

                if (applicationBroadcast == null || !applicationBroadcast.getIsBroadcasted()) {
                    continue;
                }

                // Application is broadcasted to school, now parse the broadcast rules
                List<BroadcastRule> rules = BroadcastRuleLocalServiceUtil.getSchoolRules(application.getApplicationId(), schoolId);
                for (BroadcastRule rule : rules) {
                    if ((rule.getRoleId() == 0 || RoleLocalServiceUtil.hasUserRole(user.getUserId(), rule.getRoleId())) && (
                            rule.getOrgId() == 0
                                    || UserLocalServiceUtil.hasOrganizationUser(rule.getOrgId(), userId)
                                    || rule.getGroupId() == 0
                                    || UserLocalServiceUtil.hasGroupUser(rule.getGroupId(), userId))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while checking is user " + userId + " has application " + applicationKey + " broadcasted", e);
        }

        return false;
    }

    public List<Long> getBroadcastedSchools(long applicationId) {
        List<Long> schoolIds = new ArrayList<>();

        try {
            List<Broadcast> applicationBroadcasts = broadcastPersistence.findByapplicationId(applicationId);
            if (applicationBroadcasts != null) {
                for (Broadcast applicationBroadcast : applicationBroadcasts) {
                    if (applicationBroadcast.getIsBroadcasted()) {
                        schoolIds.add(applicationBroadcast.getSchoolId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while fetching schools for which application " + applicationId + " is broadcasted", e);
        }

        return schoolIds;
    }

    public boolean removeBroadcast(Long applicationId) throws SystemException {
        broadcastPersistence.removeByapplicationId(applicationId);

        return true;
    }

    public boolean deleteBroadcastForSchool(long applicationId, long schoolId) {
        try {
            broadcastPersistence.removeByapplicationId_schoolId(applicationId, schoolId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting application " + applicationId + " broadcast for school " + schoolId);
        }

        return false;
    }

}
