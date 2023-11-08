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

package com.weprode.facile.group.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.weprode.facile.group.exception.NoSuchCommunityInfosException;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.service.base.CommunityInfosLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.nero.group.model.CommunityInfos",
        service = AopService.class
)
public class CommunityInfosLocalServiceImpl extends CommunityInfosLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(CommunityInfosLocalServiceImpl.class);

    public CommunityInfos createCommunity(long groupId, long userId) throws SystemException {
        final long communityInfosId = counterLocalService.increment();

        CommunityInfos ci = communityInfosPersistence.create(communityInfosId);
        ci.setGroupId(groupId);
        ci.setCreatorId(userId);
        ci.setIsPedagogical(true);
        communityInfosPersistence.update(ci);

        return ci;
    }

    public CommunityInfos getCommunityInfosByGroupId(long groupId) throws SystemException, NoSuchCommunityInfosException {
        return communityInfosPersistence.findBygroupId(groupId);
    }

    public List<Group> getUserCommunities(long userId, boolean pedagogicalOnly, boolean activeOnly) {
        List<Group> userCommunities = new ArrayList<>();

        try {
            userCommunities = groupUtilsFinder.findUserGroups(userId, pedagogicalOnly, activeOnly, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error fetching communities for user " + userId, e);
        }

        return userCommunities;
    }

    public List<Long> getUserCommunitiesIds(long userId, boolean pedagogicalOnly, boolean activeOnly) {
        List<Long> userCommunitiesIds = new ArrayList<>();

        try {
            List<Group> userCommunities = groupUtilsFinder.findUserGroups(userId, pedagogicalOnly, activeOnly, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (Group userCommunity : userCommunities) {
                userCommunitiesIds.add(userCommunity.getGroupId());
            }
        } catch (Exception e) {
            logger.error("Error fetching communities for user " + userId, e);
        }

        return userCommunitiesIds;
    }

    // Returns all communities created by a member of the given school
    public List<Group> getSchoolCommunities(long schoolId, boolean pedagogicalOnly, boolean activeOnly) {
        return groupUtilsFinder.findSchoolGroups(schoolId, pedagogicalOnly, activeOnly, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Get the communities which have expired from more than 3 months
     */
    public List<CommunityInfos> getExpireCommunityToRemove() {
        return communityInfosFinder.findExpiredCommunityToRemove();
    }
}
