package com.weprode.nero.group.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.group.model.GroupMembership;
import com.weprode.nero.group.service.base.GroupMembershipLocalServiceBaseImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GroupMembershipLocalServiceImpl extends GroupMembershipLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(GroupMembershipLocalServiceImpl.class);

    public GroupMembership addMembership(long userId, long groupId, Date startDate, Date endDate) {
        DateFormat sdf = new SimpleDateFormat("ddMMyyyy");

        logger.info("Adding membership for userId " + userId + ", groupId " + groupId +
                " ,startDate = " + sdf.format(startDate) + " ,endDate = " + sdf.format(endDate));

        try {
            // Loop over existing memberships to compare dates
            List<GroupMembership> groupMemberships = getGroupMemberships(userId, groupId);
            for (GroupMembership groupMembership : groupMemberships) {
                if (isSameDay(groupMembership.getStartDate(), startDate) && isSameDay(groupMembership.getEndDate(), endDate)) {
                    logger.info("Found existing OM");
                    return groupMembership;
                }
            }
            logger.info("Creating new OM");
            GroupMembership groupMembership = groupMembershipPersistence.create(counterLocalService.increment());
            groupMembership.setGroupId(groupId);
            groupMembership.setUserId(userId);
            groupMembership.setStartDate(startDate);
            groupMembership.setEndDate(endDate);
            groupMembership.setFullYear(false);
            groupMembership = groupMembershipPersistence.update(groupMembership);
            return groupMembership;

        } catch (Exception e) {
            logger.error("Error adding org memberships for groupId = " + groupId, e);
        }

        return null;
    }

    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
    }

    public boolean isStudentOrgMember(long userId, long groupId, Date date) {
        List<GroupMembership> groupMemberships = getGroupMemberships(userId, groupId);

        if (groupMemberships != null) {
            for (GroupMembership groupMembership : groupMemberships) {

                if (groupMembership.getFullYear()
                        || (!groupMembership.getFullYear()
                        && (date.after(groupMembership.getStartDate()) || date.equals(groupMembership.getStartDate()))
                        && date.before(groupMembership.getEndDate()))) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<User> getGroupMembers(long groupId, Date date) {
        List<User> userList = new ArrayList<>();

        try {
            List<GroupMembership> groupMemberships = groupMembershipPersistence.findBygroupId(groupId);
            if (groupMemberships != null) {
                for (GroupMembership groupMembership : groupMemberships) {
                    if (groupMembership.getFullYear() || (!groupMembership.getFullYear()
                            && (date.after(groupMembership.getStartDate()) || date.equals(groupMembership.getStartDate()))
                            && date.before(groupMembership.getEndDate()))) {
                        try {
                            userList.add(UserLocalServiceUtil.getUser(groupMembership.getUserId()));
                        } catch (Exception e) {
                            logger.debug(e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching all groupId " + groupId + " members");
        }

        return userList;
    }

    public List<GroupMembership> getGroupMemberships(long userId, long groupId) {
        List<GroupMembership> groupMemberships = new ArrayList<>();

        try {
            groupMemberships = groupMembershipPersistence.findByuserId_groupId(userId, groupId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return groupMemberships;
    }

    public List<GroupMembership> getStudentGroupMemberships(long userId) {
        List<GroupMembership> groupMemberships = new ArrayList<>();

        try {
            groupMemberships = groupMembershipPersistence.findByuserId(userId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return groupMemberships;
    }

    public boolean removeGroupMemberships(long groupId) {
        try {
            groupMembershipPersistence.removeBygroupId(groupId);
            return true;
        } catch (Exception e) {
            logger.error("Error removing org memberships for groupId = " + groupId, e);
        }

        return false;
    }

    public boolean removeUserMemberships(long userId) {
        try {
            groupMembershipPersistence.removeByuserId(userId);
            return true;
        } catch (Exception e) {
            logger.error("Error removing org memberships for userId = " + userId, e);
        }

        return false;
    }

}
