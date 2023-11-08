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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.group.constants.ActivityConstants;
import com.weprode.facile.group.model.MembershipActivity;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.facile.group.service.base.MembershipActivityLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.group.model.MembershipActivity",
        service = AopService.class
)
public class MembershipActivityLocalServiceImpl extends MembershipActivityLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MembershipActivityLocalServiceImpl.class);

    public MembershipActivity addMembershipActivity(long groupId, long actionUserId, List<Long> targetUserIds, boolean incoming) {
        // First get activities for the same groupId in the last 10 minutes
        List<MembershipActivity> sameGroupRecentActivities = getRecentGroupActivities(groupId);

        if (sameGroupRecentActivities.isEmpty()) {

            // If no recent activity => add the new activity
            return createMembershipActivity(groupId, actionUserId, targetUserIds, incoming);
        } else {
            // If there is recent activities, parse these activities to build 2 lists:
            // - the incoming users
            // - the outgoing users
            List<Long> incomingUserIds = new ArrayList<>();
            List<Long> outgoingUserIds = new ArrayList<>();
            for (MembershipActivity sameGroupRecentActivity : sameGroupRecentActivities) {
                List<Long> recentActivityTargetUserIds = convertUsersFromStringToList(sameGroupRecentActivity.getTargetUserIds());
                if (sameGroupRecentActivity.getIncoming()) {
                    incomingUserIds.addAll(recentActivityTargetUserIds);
                } else {
                    outgoingUserIds.addAll(recentActivityTargetUserIds);
                }
            }

            // Loop over the current activity users
            for (Long targetUserId : targetUserIds) {
                // If the newly processed activity is incoming, then:
                // - remove these users from the outgoing list if they belong to it
                // - add the remaining ones to the incoming list
                if (incoming) {
                    if (outgoingUserIds.contains(targetUserId)) {
                        outgoingUserIds.remove(targetUserId);
                    } else if (!incomingUserIds.contains(targetUserId)) {
                        incomingUserIds.add(targetUserId);
                    }
                }

                // If the newly processed activity is outgoing, then:
                // - remove these users from the incoming list if they belong to it
                // - add the remaining ones to the outgoing list
                else {
                    if (incomingUserIds.contains(targetUserId)) {
                        incomingUserIds.remove(targetUserId);
                    } else if (!outgoingUserIds.contains(targetUserId)) {
                        outgoingUserIds.add(targetUserId);
                    }
                }
            }

            // Delete old activities
            for (MembershipActivity sameGroupRecentActivity : sameGroupRecentActivities) {
                try {
                    membershipActivityPersistence.remove(sameGroupRecentActivity);
                } catch (Exception e) {
                    logger.debug(e);
                }
            }

            // Rebuild 2 activities : one for incoming users and one for outgoing users
            if (!incomingUserIds.isEmpty()) {
                createMembershipActivity(groupId, actionUserId, incomingUserIds, true);
            }
            if (!outgoingUserIds.isEmpty()) {
                createMembershipActivity(groupId, actionUserId, outgoingUserIds, false);
            }
        }

        return null;
    }

    private MembershipActivity createMembershipActivity(long groupId, long actionUserId, List<Long> targetUserIds, boolean incoming) {
        MembershipActivity membershipActivity = null;

        try {
            long membershipActivityId = counterLocalService.increment();
            membershipActivity = membershipActivityPersistence.create(membershipActivityId);
            membershipActivity.setGroupId(groupId);
            membershipActivity.setActionUserId(actionUserId);
            String targetUserIdsStr = convertUserListToString(targetUserIds);
            membershipActivity.setTargetUserIds(targetUserIdsStr);
            membershipActivity.setIncoming(incoming);
            membershipActivity.setMovementDate(new Date());
            membershipActivity = membershipActivityPersistence.update(membershipActivity);

            return membershipActivity;
        } catch (Exception e) {
            logger.debug(e);
        }

        return membershipActivity;
    }

    public List<MembershipActivity> getMembershipActivity (long userId, List<Long> groupIdList, Date minDate, Date maxDate,
                                                           boolean includeUserActions, boolean onlyWithUserBeingTarget, boolean withAdd, boolean withRemovals) {
        if (groupIdList == null || groupIdList.isEmpty() || (!withAdd && !withRemovals)) {
            return new ArrayList<>();
        }

        DynamicQuery dynamicQuery = MembershipActivityLocalServiceUtil.dynamicQuery();

        if (!includeUserActions) {
            dynamicQuery.add(PropertyFactoryUtil.forName("actionUserId").ne(userId));
        }

        // If we want only actions where the user is a target
        if (onlyWithUserBeingTarget) {
            dynamicQuery.add(RestrictionsFactoryUtil.like("targetUserIds", "%" + userId + "%"));
        }

        // Add only
        if (withAdd && !withRemovals) {
            dynamicQuery.add(PropertyFactoryUtil.forName("incoming").eq(true));
        }

        // Removals only
        if (!withAdd && withRemovals) {
            dynamicQuery.add(PropertyFactoryUtil.forName("incoming").eq(false));
        }

        Long[] groupIds = groupIdList.toArray(new Long[0]);
        dynamicQuery.add(PropertyFactoryUtil.forName("groupId").in(groupIds));
        dynamicQuery.add(RestrictionsFactoryUtil.between("movementDate", minDate, maxDate));

        // Order by modification date
        Order movementDateOrder = OrderFactoryUtil.desc("movementDate");
        dynamicQuery.addOrder(movementDateOrder);

        List<MembershipActivity> membershipActivityList = new ArrayList<>();
        try {
            // Execute the query
            membershipActivityList = MembershipActivityLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (Exception e) {
            logger.error("Error running Membership activity dynamicQuery", e);
        }

        return membershipActivityList;
    }

    private List<MembershipActivity> getRecentGroupActivities(long groupId) {
        List<MembershipActivity> sameGroupRecentActivities = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -10);
        Date limitDate = cal.getTime();

        List<MembershipActivity> sameGroupActivities = new ArrayList<>();
        try {
            sameGroupActivities = membershipActivityPersistence.findBygroupId(groupId);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (sameGroupActivities != null) {
            for (MembershipActivity sameGroupActivity : sameGroupActivities) {
                if (sameGroupActivity.getMovementDate().after(limitDate)) {
                    sameGroupRecentActivities.add(sameGroupActivity);
                }
            }
        }

        return sameGroupRecentActivities;
    }

    private List<Long> convertUsersFromStringToList(String userListStr) {
        List<Long> userIdList = new ArrayList<>();

        String[] userTab = userListStr.split(",");
        if (userTab.length > 0) {
            for (String userStr : userTab) {
                if (!userStr.equals("")) {
                    try {
                        userIdList.add(Long.parseLong(userStr));
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }
            }
        }

        return userIdList;
    }

    private String convertUserListToString(List<Long> userIds) {
        StringBuilder userIdsStr = new StringBuilder();

        if (userIds != null && !userIds.isEmpty()) {
            for (Long userId : userIds) {
                userIdsStr.append(userId).append(",");
            }
            userIdsStr = new StringBuilder(userIdsStr.substring(0, userIdsStr.length() - 1));
        }

        return userIdsStr.toString();
    }

    public boolean deleteGroupActivity(long groupId) {
        try {
            membershipActivityPersistence.removeBygroupId(groupId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting group activities for groupId " + groupId, e);
        }

        return false;
    }

    public JSONObject convertMembershipActivityToJson(MembershipActivity membershipActivity) {
        JSONObject jsonMembershipActivity = new JSONObject();

        DateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        try {
            jsonMembershipActivity.put(JSONConstants.ACTIVITY_ID, membershipActivity.getMembershipActivityId());
            jsonMembershipActivity.put(JSONConstants.GROUP_ID, membershipActivity.getGroupId());
            jsonMembershipActivity.put(JSONConstants.ACTION_USER_ID, membershipActivity.getActionUserId());

            // Action user name
            User actionUser = UserLocalServiceUtil.getUser(membershipActivity.getActionUserId());
            jsonMembershipActivity.put(JSONConstants.ACTION_USER_NAME, actionUser.getFullName());
            jsonMembershipActivity.put(JSONConstants.AUTHOR, actionUser.getFullName());

            // Target users
            String targetUserIds = membershipActivity.getTargetUserIds();
            String[] targetUserIdsTab = targetUserIds.split(",");

            JSONArray targetUsers = new JSONArray();
            for (String targetUserIdStr : targetUserIdsTab) {
                try {
                    long targetUserId = Long.parseLong(targetUserIdStr);
                    User targetUser = UserLocalServiceUtil.getUser(targetUserId);
                    JSONObject jsonUser = new JSONObject();
                    jsonUser.put(JSONConstants.USER_ID, targetUser.getUserId());
                    jsonUser.put(JSONConstants.FIRST_NAME, targetUser.getFirstName());
                    jsonUser.put(JSONConstants.LAST_NAME, targetUser.getLastName());

                    targetUsers.put(jsonUser);
                } catch (Exception e) {
                    logger.debug(e);
                }
            }

            jsonMembershipActivity.put(JSONConstants.USERS, targetUsers);
            jsonMembershipActivity.put(JSONConstants.TYPE, membershipActivity.getIncoming() ? ActivityConstants.TYPE_ADD_MEMBERSHIP : ActivityConstants.TYPE_REMOVE_MEMBERSHIP);
            jsonMembershipActivity.put(JSONConstants.MODIFICATION_DATE, sdf.format(membershipActivity.getMovementDate()));
            jsonMembershipActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(membershipActivity.getGroupId()));

            Group group = GroupLocalServiceUtil.getGroup(membershipActivity.getGroupId());
            jsonMembershipActivity.put(JSONConstants.GROUP_LINK, "#/group"+ group.getFriendlyURL());

        } catch (Exception e) {
            logger.error("Error while converting membership activity to json");
            return null;
        }

        return jsonMembershipActivity;
    }

}
