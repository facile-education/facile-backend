package com.weprode.nero.organization.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.organization.model.OrgMembership;
import com.weprode.nero.organization.service.base.OrgMembershipLocalServiceBaseImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrgMembershipLocalServiceImpl extends OrgMembershipLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(OrgMembershipLocalServiceImpl.class);

    public OrgMembership addMembership(long userId, long groupId, Date startDate, Date endDate) {
        DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        logger.info("Adding membership for userId " + userId + ", groupId " + groupId +
                " ,startDate = " + sdf.format(startDate) + " ,endDate = " + sdf.format(endDate));

        try {
            // Loop over existing memberships to compare dates
            List<OrgMembership> orgMemberships = getOrgMemberships(userId, groupId);
            for (OrgMembership orgMembership : orgMemberships) {
                if (isSameDay(orgMembership.getStartDate(), startDate) && isSameDay(orgMembership.getEndDate(), endDate)) {
                    logger.info("Found existing OM");
                    return orgMembership;
                }
            }
            logger.info("Creating new OM");
            OrgMembership orgMembership = orgMembershipPersistence.create(counterLocalService.increment());
            orgMembership.setGroupId(groupId);
            orgMembership.setUserId(userId);
            orgMembership.setStartDate(startDate);
            orgMembership.setEndDate(endDate);
            orgMembership.setFullYear(false);
            orgMembership = orgMembershipPersistence.update(orgMembership);
            return orgMembership;

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
        List<OrgMembership> orgMemberships = getOrgMemberships(userId, groupId);

        if (orgMemberships != null) {
            for (OrgMembership orgMembership : orgMemberships) {

                if (orgMembership.getFullYear()
                        || (!orgMembership.getFullYear()
                        && (date.after(orgMembership.getStartDate()) || date.equals(orgMembership.getStartDate()))
                        && date.before(orgMembership.getEndDate()))) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<User> getOrgMembers(long groupId, Date date) {
        List<User> userList = new ArrayList<>();

        try {
            List<OrgMembership> orgMemberships = orgMembershipPersistence.findBygroupId(groupId);
            if (orgMemberships != null) {
                for (OrgMembership orgMembership : orgMemberships) {
                    if (orgMembership.getFullYear() || (!orgMembership.getFullYear()
                            && (date.after(orgMembership.getStartDate()) || date.equals(orgMembership.getStartDate()))
                            && date.before(orgMembership.getEndDate()))) {
                        try {
                            userList.add(UserLocalServiceUtil.getUser(orgMembership.getUserId()));
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

    public List<OrgMembership> getOrgMemberships(long userId, long groupId) {
        List<OrgMembership> orgMemberships = new ArrayList<>();

        try {
            orgMemberships = orgMembershipPersistence.findByuserId_groupId(userId, groupId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return orgMemberships;
    }

    public List<OrgMembership> getStudentOrgMemberships(long userId) {
        List<OrgMembership> orgMemberships = new ArrayList<>();

        try {
            orgMemberships = orgMembershipPersistence.findByuserId(userId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return orgMemberships;
    }

    public boolean removeOrgMemberships(long groupId) {
        try {
            orgMembershipPersistence.removeBygroupId(groupId);
            return true;
        } catch (Exception e) {
            logger.error("Error removing org memberships for groupId = " + groupId, e);
        }

        return false;
    }

    public boolean removeUserMemberships(long userId) {
        try {
            orgMembershipPersistence.removeByuserId(userId);
            return true;
        } catch (Exception e) {
            logger.error("Error removing org memberships for userId = " + userId, e);
        }

        return false;
    }
}
