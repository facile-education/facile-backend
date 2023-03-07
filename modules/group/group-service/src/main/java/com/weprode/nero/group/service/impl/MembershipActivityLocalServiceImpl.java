package com.weprode.nero.group.service.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.group.model.MembershipActivity;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.nero.group.service.base.MembershipActivityLocalServiceBaseImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MembershipActivityLocalServiceImpl extends MembershipActivityLocalServiceBaseImpl {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private static final Log logger = LogFactoryUtil.getLog(MembershipActivityLocalServiceImpl.class);

    public MembershipActivity addMembershipActivity(long groupId, long actionUserId, List<Long> targetUserIds, boolean incoming) {
        // First get activities for the same groupId in the last 10 minutes
        List<MembershipActivity> sameGroupRecentActivities = getRecentGroupActivities(groupId);

        if (sameGroupRecentActivities.size() == 0) {

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

    public List<MembershipActivity> getMembershipActivities(List<Long> groupIdList, long actionUserId, int start, int end) {
        if (groupIdList == null || groupIdList.isEmpty()) {
            return new ArrayList<>();
        }

        DynamicQuery dynamicQuery = MembershipActivityLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(PropertyFactoryUtil.forName("actionUserId").ne(actionUserId));

        // Using 'in' is much faster than disjunctions
        Long[] groupIds = new Long[groupIdList.size()];
        int idx = 0;
        for (Long groupId : groupIdList) {
            groupIds[idx] = groupId;
            idx++;
        }
        dynamicQuery.add(PropertyFactoryUtil.forName("groupId").in(groupIds));

        // Order by modification date
        Order movementDateOrder = OrderFactoryUtil.desc("movementDate");
        dynamicQuery.addOrder(movementDateOrder);

        dynamicQuery.setLimit(start, end);

        List<MembershipActivity> membershipActivityList = new ArrayList<>();
        try {
            // Execute the query
            membershipActivityList = MembershipActivityLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (Exception e) {
            logger.error(e);
        }

        return membershipActivityList;
    }

    public List<MembershipActivity> getMembershipHistory (long userId, List<Long> groupIdList, Date minDate, Date maxDate) {
        return getMembershipActivity(userId, groupIdList, minDate, maxDate, true);
    }

    public List<MembershipActivity> getMembershipActivity (long userId, List<Long> groupIdList, Date minDate, Date maxDate, boolean includeSelf) {
        if (groupIdList == null || groupIdList.isEmpty()) {
            return new ArrayList<>();
        }

        DynamicQuery dynamicQuery = MembershipActivityLocalServiceUtil.dynamicQuery();

        if (!includeSelf) {
            dynamicQuery.add(PropertyFactoryUtil.forName("actionUserId").ne(userId));
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
        JSONObject jsonMembershipActivity = JSONFactoryUtil.createJSONObject();

        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try {
            jsonMembershipActivity.put("activityId", membershipActivity.getMembershipActivityId());
            jsonMembershipActivity.put("actionUserId", membershipActivity.getActionUserId());

            // Action user name
            User actionUser = UserLocalServiceUtil.getUser(membershipActivity.getActionUserId());
            jsonMembershipActivity.put("actionUserName", actionUser.getFullName());
            jsonMembershipActivity.put("author", actionUser.getFullName());

            // Target user names
            StringBuilder targetUserNames = new StringBuilder();
            String shortTargetUserNames = "";
            String targetUserIds = membershipActivity.getTargetUserIds();
            String[] targetUserIdsTab = targetUserIds.split(",");

            if (targetUserIdsTab.length > 0) {
                if (targetUserIdsTab.length > 1) {
                    shortTargetUserNames = targetUserIdsTab.length + " personnes";
                }

                for (int i = 0 ; i < targetUserIdsTab.length ; i++) {
                    String targetUserIdStr = targetUserIdsTab[i];

                    try {
                        long targetUserId = Long.parseLong(targetUserIdStr);
                        User targetUser = UserLocalServiceUtil.getUser(targetUserId);
                        targetUserNames.append(targetUser.getFullName());

                        if (targetUserIdsTab.length >= 2 && i == targetUserIdsTab.length - 2) {
                            targetUserNames.append(" et ");
                        }
                        if (i < targetUserIdsTab.length - 2) {
                            targetUserNames.append(", ");
                        }
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }
            }
            jsonMembershipActivity.put("targetUserNames", targetUserNames.toString());
            jsonMembershipActivity.put("shortTargetUserNames", shortTargetUserNames);
            jsonMembershipActivity.put("target", shortTargetUserNames.isEmpty() ? targetUserNames.toString() : shortTargetUserNames);
            jsonMembershipActivity.put("type", membershipActivity.getIncoming() ? ActivityConstants.TYPE_ADD_MEMBERSHIP : ActivityConstants.TYPE_REMOVE_MEMBERSHIP);
            jsonMembershipActivity.put("modificationDate", sdf.format(membershipActivity.getMovementDate()));
            jsonMembershipActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(membershipActivity.getGroupId()));

            Group group = GroupLocalServiceUtil.getGroup(membershipActivity.getGroupId());
            jsonMembershipActivity.put("groupLink", "#/group"+ group.getFriendlyURL());

        } catch (Exception e) {
            logger.error("Error while converting membership activity to json");
            return null;
        }

        return jsonMembershipActivity;
    }

}
