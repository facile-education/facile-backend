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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.facile.document.model.Activity;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.group.constants.ActivityConstants;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.model.GroupActivity;
import com.weprode.facile.group.model.MembershipActivity;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.facile.group.service.base.GroupActivityLocalServiceBaseImpl;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.facile.school.life.service.persistence.RenvoiPK;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.group.model.GroupActivity",
        service = AopService.class
)
public class GroupActivityLocalServiceImpl extends GroupActivityLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GroupActivityLocalServiceImpl.class);

    public List<GroupActivity> getDashboardGroupsActivities (long userId, List<Long> groupIds, Date maxDate, int nbResults,
                                                             boolean withNews, boolean withDocs, boolean withMemberships, boolean withSchoollife, boolean withSessions) {
        List<GroupActivity> groupActivities = new ArrayList<>();

        logger.info("Get dashboard group activities for userId " + userId + " for " + groupIds.size() + " groups, until maxDate " + maxDate);
        if (!(withNews || withDocs || withMemberships || withSchoollife || withSessions) || groupIds.size() == 0) {
            return groupActivities;
        }

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(maxDate);
            cal.add(Calendar.DATE, -7);
            Date minDate = cal.getTime();

            // Limit date is the school year start date
            Date limitMinDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();

            // Get activities by successive range dates going back in time, so that we ensure pagination is fine
            // Do not exceed 1 month back in time
            while (groupActivities.size() < nbResults && maxDate.after(limitMinDate)) {
                // Fetch activities from minDate = maxDate minus 7 days
                //logger.info("Fetching group activities for userId " + userId + " from " + new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(minDate) + " to " + new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(maxDate) + " : having " + groupActivities.size() + " results");

                // Group news
                if (withNews) {
                    List<News> groupNews = NewsLocalServiceUtil.getNewsActivities(user, groupIds, minDate, maxDate, nbResults, true);
                    for (News news : groupNews) {
                        GroupActivity newsActivity = new GroupActivity(news.getNewsId(), 0, news.getPublicationDate(), ActivityConstants.ACTIVITY_TYPE_NEWS);
                        groupActivities.add(newsActivity);
                    }
                }

                // Document activity
                if (withDocs) {
                    List<Activity> activityList = ActivityLocalServiceUtil.getGroupsActivity(user.getUserId(), groupIds, minDate, maxDate,
                            false, true, false, false, false);
                    for (Activity activity : activityList) {
                        GroupActivity docActivity = new GroupActivity(activity.getActivityId(), 0, activity.getModificationDate(), ActivityConstants.ACTIVITY_TYPE_DOCUMENT);
                        groupActivities.add(docActivity);
                    }
                }

                // Membership activity
                if (withMemberships) {
                    // No activity performed by the user himself, only the activity with him as target, just adds, no removals
                    List<MembershipActivity> membershipActivities = MembershipActivityLocalServiceUtil.getMembershipActivity(user.getUserId(), groupIds, minDate, maxDate, false, true, true, false);
                    for (MembershipActivity membershipActivity : membershipActivities) {
                        GroupActivity groupMembershipActivity = new GroupActivity(membershipActivity.getMembershipActivityId(), 0, membershipActivity.getMovementDate(), ActivityConstants.ACTIVITY_TYPE_MEMBERSHIP);
                        groupActivities.add(groupMembershipActivity);
                    }
                }

                // Teachers see their pending renvois
                if (withSchoollife && RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    List<Renvoi> pendingRenvois = RenvoiLocalServiceUtil.getTeacherPendingRenvois(user.getUserId());
                    for (Renvoi pendingRenvoi : pendingRenvois) {
                        // First filter on dates
                        if (pendingRenvoi.getRenvoiDate().after(minDate) && !pendingRenvoi.getRenvoiDate().after(maxDate)) {
                            // Then filter on group : either coursGroupId or classOrgId
                            Organization renvoiClass = OrganizationLocalServiceUtil.getOrganization(pendingRenvoi.getOrgId());
                            CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(pendingRenvoi.getSourceSessionId());
                            if (groupIds.contains(sourceSession.getGroupId()) || groupIds.contains(renvoiClass.getGroupId())) {
                                GroupActivity pendingRenvoiActivity = new GroupActivity(pendingRenvoi.getSchoollifeSessionId(), pendingRenvoi.getStudentId(), pendingRenvoi.getRenvoiDate(), ActivityConstants.ACTIVITY_TYPE_PENDING_RENVOI);
                                groupActivities.add(pendingRenvoiActivity);
                            }
                        }
                    }
                }

                // Doyens and main teachers see the renvois for the students of classes that are affected to him
                if (withSchoollife) {
                    List<Renvoi> schoolRenvois = RenvoiLocalServiceUtil.getDoyenSchoolRenvois(user, minDate, maxDate);
                    for (Renvoi schoolRenvoi : schoolRenvois) {
                        Organization renvoiClass = schoolRenvoi.getOrgId() == 0 ? null : OrganizationLocalServiceUtil.getOrganization(schoolRenvoi.getOrgId());
                        CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(schoolRenvoi.getSourceSessionId());
                        // Filter to group if 1 selected
                        if (groupIds.size() > 1 || (groupIds.contains(sourceSession.getGroupId()) || renvoiClass == null || groupIds.contains(renvoiClass.getGroupId()))) {
                            GroupActivity schoolRenvoiActivity = new GroupActivity(schoolRenvoi.getSchoollifeSessionId(), schoolRenvoi.getStudentId(), schoolRenvoi.getRenvoiDate(), ActivityConstants.ACTIVITY_TYPE_SCHOOL_RENVOI);
                            groupActivities.add(schoolRenvoiActivity);
                        }
                    }
                }

                // Homeworks given
                if (withSessions && RoleUtilsLocalServiceUtil.isStudent(user)) {
                    List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getStudentHomeworkActivity(user.getUserId(), minDate, maxDate);
                    for (Homework givenHomework : givenHomeworks) {
                        GroupActivity homeworkActivity = new GroupActivity(givenHomework.getHomeworkId(), 0, givenHomework.getPublicationDate(), ActivityConstants.ACTIVITY_TYPE_HOMEWORK);
                        groupActivities.add(homeworkActivity);
                    }
                }

                // Sessions content added
                if (withSessions && RoleUtilsLocalServiceUtil.isStudent(user)) {
                    List<CDTSession> sessions = CDTSessionLocalServiceUtil.getGroupsSessionActivity(user.getUserId(), groupIds, minDate, maxDate);
                    for (CDTSession session : sessions) {
                        // Get modificationDate
                        Date modificationDate = SessionTeacherLocalServiceUtil.getLastModificationDate(session.getSessionId(), minDate, maxDate);
                        if (modificationDate != null) {
                            GroupActivity sessionActivity = new GroupActivity(session.getSessionId(), 0, modificationDate, ActivityConstants.ACTIVITY_TYPE_SESSION);
                            groupActivities.add(sessionActivity);
                        }
                    }
                }

                // Groups expired for reactivation
                if (!RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
                    List<Group> userCommunities = CommunityInfosLocalServiceUtil.getUserCommunities(userId, false, false);
                    for (Group userCommunity : userCommunities) {
                        CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(userCommunity.getGroupId());
                        if (communityInfos.getStatus() == 3 &&
                                (groupIds.size() > 1 || groupIds.get(0) == communityInfos.getGroupId()) &&
                                communityInfos.getExpirationDate().after(minDate) &&
                                communityInfos.getExpirationDate().before(maxDate) &&
                                RoleUtilsLocalServiceUtil.isUserGroupAdmin(user, userCommunity.getGroupId())) {
                            GroupActivity sessionActivity = new GroupActivity(userCommunity.getGroupId(), 0, communityInfos.getExpirationDate(), ActivityConstants.ACTIVITY_TYPE_EXPIRED_GROUP);
                            groupActivities.add(sessionActivity);
                        }
                    }
                }

                // Decrease 1 week
                maxDate = minDate;
                cal = Calendar.getInstance();
                cal.setTime(minDate);
                cal.add(Calendar.DATE, -7);
                minDate = cal.getTime();
            }
        } catch (Exception e) {
            logger.error("Error fetching group activities for userId " + userId + " and " + groupIds.size() + " groups", e);
        }

        // Sort activities by action date
        groupActivities.sort(Collections.reverseOrder());
        return groupActivities.subList(0, Math.min(groupActivities.size(), nbResults));
    }

    // Called from the Group service, we get the full list of activities, including current user's ones
    public List<GroupActivity> getFullGroupActivities (long userId, long groupId, Date maxDate, int nbResults) {
        List<GroupActivity> groupActivities = new ArrayList<>();
        logger.info("getGroupActivities for userId " + userId + " and groupId " + groupId + ", until maxDate " + maxDate);

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(maxDate);
            cal.add(Calendar.DATE, -7);
            Date minDate = cal.getTime();

            List<Long> groupIds = new ArrayList<>();
            groupIds.add(groupId);

            // Limit date is the school year start date
            Date limitMinDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();

            // Get activities by successive range dates going back in time, so that we ensure pagination is fine
            // Do not exceed 1 month back in time
            while (groupActivities.size() < nbResults && maxDate.after(limitMinDate)) {
                // Fetch activities from minDate = maxDate minus 7 days
                logger.debug("getGroupActivities for userId " + userId + " from " + minDate + " to " + maxDate);

                // Group news
                List<News> groupNews = NewsLocalServiceUtil.getGroupNewsActivities(user, groupId, minDate, maxDate, nbResults);
                for (News news : groupNews) {
                    GroupActivity newsActivity = new GroupActivity(news.getNewsId(), 0, news.getPublicationDate(), ActivityConstants.ACTIVITY_TYPE_NEWS);
                    groupActivities.add(newsActivity);
                }

                // Document activity
                List<Activity> activityList = ActivityLocalServiceUtil.getGroupsActivity(user.getUserId(), groupIds, minDate, maxDate,
                        true, true, true, true, true);
                for (Activity activity : activityList) {
                    GroupActivity docActivity = new GroupActivity(activity.getActivityId(), 0, activity.getModificationDate(), ActivityConstants.ACTIVITY_TYPE_DOCUMENT);
                    groupActivities.add(docActivity);
                }

                // Membership activity: self one + adds + removals
                List<MembershipActivity> membershipActivities = MembershipActivityLocalServiceUtil.getMembershipActivity(user.getUserId(), groupIds, minDate, maxDate, true, false, true, true);
                for (MembershipActivity membershipActivity : membershipActivities) {
                    GroupActivity groupMembershipActivity = new GroupActivity(membershipActivity.getMembershipActivityId(), 0, membershipActivity.getMovementDate(), ActivityConstants.ACTIVITY_TYPE_MEMBERSHIP);
                    groupActivities.add(groupMembershipActivity);
                }

                // No pending firings
                // Doyens and main teachers see the renvois for the students of classes that are affected to him
                List<Renvoi> schoolRenvois = RenvoiLocalServiceUtil.getGroupRenvois(user, groupIds, minDate, maxDate);
                for (Renvoi schoolRenvoi : schoolRenvois) {
                    GroupActivity schoolRenvoiActivity = new GroupActivity(schoolRenvoi.getSchoollifeSessionId(), schoolRenvoi.getStudentId(), schoolRenvoi.getRenvoiDate(), ActivityConstants.ACTIVITY_TYPE_SCHOOL_RENVOI);
                    groupActivities.add(schoolRenvoiActivity);
                }

                // Homeworks given
                List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getCourseHomeworkActivity(user.getUserId(), groupId, minDate, maxDate);
                for (Homework givenHomework : givenHomeworks) {
                    if (givenHomework.getModificationDate().after(minDate) && givenHomework.getModificationDate().before(maxDate) && groupIds.contains(givenHomework.getCourseId())) {
                        GroupActivity homeworkActivity = new GroupActivity(givenHomework.getHomeworkId(), 0, givenHomework.getModificationDate(), ActivityConstants.ACTIVITY_TYPE_HOMEWORK);
                        groupActivities.add(homeworkActivity);
                    }
                }

                // Sessions content added
                List<CDTSession> sessions = CDTSessionLocalServiceUtil.getGroupsSessionActivity(0, groupIds, minDate, maxDate);
                for (CDTSession session : sessions) {
                    // Get modificationDate
                    Date modificationDate = SessionTeacherLocalServiceUtil.getLastModificationDate(session.getSessionId(), minDate, maxDate);
                    if (modificationDate != null) {
                        GroupActivity sessionActivity = new GroupActivity(session.getSessionId(), 0, modificationDate, ActivityConstants.ACTIVITY_TYPE_SESSION);
                        groupActivities.add(sessionActivity);
                    }
                }

                // Decrease 1 week
                maxDate = minDate;
                cal = Calendar.getInstance();
                cal.setTime(minDate);
                cal.add(Calendar.DATE, -7);
                minDate = cal.getTime();
            }
        } catch (Exception e) {
            logger.error("Error fetching group activities for userId " + userId + " and groupId " + groupId, e);
        }

        // Sort activities by action date
        groupActivities.sort(Collections.reverseOrder());
        return groupActivities.subList(0, Math.min(groupActivities.size(), nbResults));
    }

    // Called from the document service, in the info panel
    public List<GroupActivity> getDocumentGroupActivities (long userId, long groupId, Date maxDate, int nbResults) {
        List<GroupActivity> groupActivities = new ArrayList<>();
        logger.info("Get document activities for userId " + userId + " and groupId " + groupId + ", until maxDate " + maxDate);

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(maxDate);
            cal.add(Calendar.DATE, -7);
            Date minDate = cal.getTime();

            List<Long> groupIds = new ArrayList<>();
            groupIds.add(groupId);

            // Limit date is the school year start date
            Date limitMinDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();

            // Get activities by successive range dates going back in time, so that we ensure pagination is fine
            // Do not exceed 1 month back in time
            while (groupActivities.size() < nbResults && maxDate.after(limitMinDate)) {
                // Fetch activities from minDate = maxDate minus 7 days
                logger.debug("getGroupActivities for userId " + userId + " from " + minDate + " to " + maxDate);

                // Document activity
                List<Activity> activityList = ActivityLocalServiceUtil.getGroupsActivity(user.getUserId(), groupIds, minDate, maxDate,
                        true, true, true, true, true);
                for (Activity activity : activityList) {
                    GroupActivity docActivity = new GroupActivity(activity.getActivityId(), 0, activity.getModificationDate(), ActivityConstants.ACTIVITY_TYPE_DOCUMENT);
                    groupActivities.add(docActivity);
                }

                // Decrease 1 week
                maxDate = minDate;
                cal = Calendar.getInstance();
                cal.setTime(minDate);
                cal.add(Calendar.DATE, -7);
                minDate = cal.getTime();
            }
        } catch (Exception e) {
            logger.error("Error fetching document activities for userId " + userId + " and groupId " + groupId, e);
        }

        // Sort activities by action date
        groupActivities.sort(Collections.reverseOrder());
        return groupActivities.subList(0, Math.min(groupActivities.size(), nbResults));
    }

    public JSONObject convertGroupActivity(long userId, GroupActivity groupActivity) {
        JSONObject jsonActivity = new JSONObject();

        try {
            if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_NEWS) {

                jsonActivity = NewsLocalServiceUtil.convertNewsToJson(groupActivity.getActivityId(), userId, false);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_DOCUMENT) {

                Activity activity = ActivityLocalServiceUtil.getActivity(groupActivity.getActivityId());
                jsonActivity = ActivityLocalServiceUtil.convertActivityToJson(activity, userId);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_MEMBERSHIP) {
                MembershipActivity membershipActivity = MembershipActivityLocalServiceUtil.getMembershipActivity(groupActivity.getActivityId());
                jsonActivity = MembershipActivityLocalServiceUtil.convertMembershipActivityToJson(membershipActivity);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_PENDING_RENVOI) {

                Renvoi renvoi = RenvoiLocalServiceUtil.getRenvoi(new RenvoiPK(groupActivity.getActivityId(), groupActivity.getStudentId()));
                jsonActivity = RenvoiLocalServiceUtil.convertRenvoiToJson(renvoi);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_SCHOOL_RENVOI) {

                Renvoi schoolRenvoi = RenvoiLocalServiceUtil.getRenvoi(new RenvoiPK(groupActivity.getActivityId(), groupActivity.getStudentId()));
                jsonActivity = RenvoiLocalServiceUtil.convertSchoolRenvoi(schoolRenvoi);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_HOMEWORK) {

                Homework homework = HomeworkLocalServiceUtil.getHomework(groupActivity.getActivityId());
                jsonActivity = convertHomeworkActivity(homework);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_SESSION) {

                CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(groupActivity.getActivityId());
                jsonActivity = convertSessionActivity(session, groupActivity.getActivityDate());

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_EXPIRED_GROUP) {

                jsonActivity.put(JSONConstants.MODIFICATION_DATE, new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(groupActivity.getActivityDate()));
                jsonActivity.put(JSONConstants.GROUP_ID, groupActivity.getActivityId());
                jsonActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(groupActivity.getActivityId()));
                jsonActivity.put(JSONConstants.TYPE, ActivityConstants.TYPE_EXPIRED_GROUP);
            }
        } catch (Exception e) {
            logger.error("Error converting group activity", e);
            return null;
        }

        return jsonActivity;
    }

    public Long getAuthorId (GroupActivity groupActivity) {
        try {
            if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_NEWS) {
                News news = NewsLocalServiceUtil.getNews(groupActivity.getActivityId());
                return news.getAuthorId();
                // TODO handle other activity author
            } else {
                return 0L;
            }
        } catch (Exception e) {
            logger.error(e);
            return 0L;
        }
    }

    private JSONObject convertHomeworkActivity (Homework homework) {
        JSONObject homeworkActivity = new JSONObject();

        try {
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            homeworkActivity.put(JSONConstants.HOMEWORK_ID, homework.getHomeworkId());
            User teacher = UserLocalServiceUtil.getUser(homework.getTeacherId());
            homeworkActivity.put(JSONConstants.MODIFICATION_DATE, df.format(homework.getModificationDate()));
            homeworkActivity.put(JSONConstants.TARGET_DATE, df.format(homework.getTargetDate()));
            homeworkActivity.put(JSONConstants.GROUP_ID, homework.getCourseId());
            homeworkActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(homework.getCourseId()));
            homeworkActivity.put(JSONConstants.AUTHOR, teacher.getFullName());
            homeworkActivity.put(JSONConstants.IS_FOR_ALL_STUDENTS, !homework.getIsCustomStudentList());
            if (homework.getIsCustomStudentList()) {
                homeworkActivity.put(JSONConstants.NB_STUDENTS, StudentHomeworkLocalServiceUtil.getHomeworkStudents(homework.getHomeworkId()).size());
            }
            homeworkActivity.put(JSONConstants.TYPE, ActivityConstants.TYPE_HOMEWORK);
        } catch (Exception e) {
            logger.error("Error converting homework activity for homework " + homework.getHomeworkId(), e);
        }

        return homeworkActivity;
    }

    private JSONObject convertSessionActivity (CDTSession session, Date activityDate) {
        JSONObject sessionActivity = new JSONObject();

        try {
            sessionActivity.put(JSONConstants.MODIFICATION_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(activityDate));
            User lastEditor = SessionTeacherLocalServiceUtil.getLastEditor(session.getSessionId(), activityDate);
            if (lastEditor != null) {
                sessionActivity.put(JSONConstants.AUTHOR, lastEditor.getFullName());
            }
            sessionActivity.put(JSONConstants.GROUP_ID, session.getGroupId());
            sessionActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(session.getGroupId()));
            sessionActivity.put(JSONConstants.TARGET_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(session.getStart()));
            sessionActivity.put(JSONConstants.TYPE, ActivityConstants.TYPE_SESSION);
        } catch (Exception e) {
            logger.error("Error converting session activity for session " + session.getSessionId(), e);
        }
        return sessionActivity;
    }
}
