package com.weprode.nero.group.service.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.group.model.GroupActivity;
import com.weprode.nero.group.model.MembershipActivity;
import com.weprode.nero.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.nero.group.service.base.GroupActivityLocalServiceBaseImpl;

import java.util.*;

public class GroupActivityLocalServiceImpl extends GroupActivityLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GroupActivityLocalServiceImpl.class);

    public List<GroupActivity> getGroupsHistory(long userId, List<Long> groupIds, Date maxDate, int nbResults) {
        return getGroupsActivities(userId, groupIds, maxDate, nbResults, true, true, true, true, false, true, true, true);
    }

    public List<GroupActivity> getGroupsActivities (long userId, List<Long> groupIds, Date maxDate, int nbResults) {

        return getGroupsActivities(userId, groupIds, maxDate, nbResults, false, true, true, true, true, true, true, true);
    }

    public List<GroupActivity> getGroupsActivities (long userId, List<Long> groupIds, Date maxDate, int nbResults,
                                                    boolean allHistory, boolean containNews, boolean containDocs, boolean containMembership,
                                                    boolean containPendingFirings, boolean containFirings, boolean containHomework, boolean containsSessions) {

        List<GroupActivity> groupActivities = new ArrayList<>();
        logger.info("getGroupActivities for userId " + userId + " for " + groupIds.size() + " groups, until maxDate " + maxDate);
        if (!(containNews || containDocs || !containMembership || containPendingFirings || containFirings || containHomework)) {
            return groupActivities;
        }

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(maxDate);
            cal.add(Calendar.DATE, -7);
            Date minDate = cal.getTime();

            // Limit date is the school year start date
            // TODO cdt
            Date limitMinDate = new Date();
            // Date limitMinDate = ConfigurationLocalServiceUtil.getConfiguration(UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId()).getStartSessionsDate();

            // Get activities by successive range dates going back in time, so that we ensure pagination is fine
            // Do not exceed 1 month back in time
            while (groupActivities.size() < nbResults && minDate.after(limitMinDate)) {

                // Fetch activities from minDate = maxDate minus 7 days
                logger.info("getGroupActivities for userId " + userId + " from " + minDate + " to " + maxDate);

                // Group news
                // TODO News
                /*if (containNews) {
                    List<News> groupNews = NewsLocalServiceUtil.getNews(user, 0, new Date(), 10, true, false, false);
                    for (News news : groupNews) {
                        GroupActivity newsActivity = new GroupActivity(news.getNewsId(), 0, news.getPublicationDate(), ActivityConstants.ACTIVITY_TYPE_NEWS);
                        groupActivities.add(newsActivity);
                    }
                }*/

                // Document activity
                // TODO Documents
                /*if (containDocs) {
                    List<Activity> activityList;
                    if (allHistory) {
                        activityList = ActivityLocalServiceUtil.getGroupsHistory(user.getUserId(), groupIds, minDate, maxDate);
                    } else {
                        activityList = ActivityLocalServiceUtil.getGroupsActivity(user.getUserId(), groupIds, minDate, maxDate, false);
                    }

                    if (!activityList.isEmpty()) logger.info("Got "+activityList.size()+" doc activities");

                    for (Activity activity : activityList) {
                        GroupActivity docActivity = new GroupActivity(activity.getActivityId(), 0, activity.getModificationDate(), ActivityConstants.ACTIVITY_TYPE_ACTIVITY);
                        groupActivities.add(docActivity);
                    }
                }*/

                // Membership activity
                if (containMembership) {
                    List<MembershipActivity> membershipActivities;
                    if (allHistory) {
                        membershipActivities = MembershipActivityLocalServiceUtil.getMembershipHistory(user.getUserId(), groupIds, minDate, maxDate);
                    } else {
                        membershipActivities = MembershipActivityLocalServiceUtil.getMembershipActivity(user.getUserId(), groupIds, minDate, maxDate, false);
                    }

                    if (!membershipActivities.isEmpty()) logger.info("Got "+membershipActivities.size()+" membership activities");

                    for (MembershipActivity membershipActivity : membershipActivities) {
                        GroupActivity groupMembershipActivity = new GroupActivity(membershipActivity.getMembershipActivityId(), 0, membershipActivity.getMovementDate(), ActivityConstants.ACTIVITY_TYPE_MEMBERSHIP);
                        groupActivities.add(groupMembershipActivity);
                    }
                }

                // Teachers see their pending renvois
                // TODO HHC
                /* if (containPendingFirings) {
                    List<Renvoi> pendingRenvois = RenvoiLocalServiceUtil.getTeacherPendingRenvois(user.getUserId());

                    if (!pendingRenvois.isEmpty()) logger.info("Got "+pendingRenvois.size()+" pending renvois");

                    for (Renvoi pendingRenvoi : pendingRenvois) {
                        if (pendingRenvoi.getRenvoiDate().after(minDate) && pendingRenvoi.getRenvoiDate().before(maxDate)) {
                            GroupActivity pendingRenvoiActivity = new GroupActivity(pendingRenvoi.getSchoollifeSessionId(), pendingRenvoi.getStudentId(), pendingRenvoi.getRenvoiDate(), ActivityConstants.ACTIVITY_TYPE_PENDING_RENVOI);
                            groupActivities.add(pendingRenvoiActivity);
                        }
                    }
                }

                // Doyens and main teachers see the renvois for the students of classes that are affected to him
                if (containFirings) {
                    List<Renvoi> schoolRenvois;
                    if (allHistory) {
                        schoolRenvois = RenvoiLocalServiceUtil.getGroupRenvois(user, groupIds, minDate, maxDate);
                    } else {
                        schoolRenvois = RenvoiLocalServiceUtil.getDoyenSchoolRenvois(user);
                    }

                    if (!schoolRenvois.isEmpty()) logger.info("Got "+schoolRenvois.size()+" school renvois");

                    for (Renvoi schoolRenvoi : schoolRenvois) {
                        if (schoolRenvoi.getRenvoiDate().after(minDate) && schoolRenvoi.getRenvoiDate().before(maxDate)) {
                            GroupActivity schoolRenvoiActivity = new GroupActivity(schoolRenvoi.getSchoollifeSessionId(), schoolRenvoi.getStudentId(), schoolRenvoi.getRenvoiDate(), ActivityConstants.ACTIVITY_TYPE_SCHOOL_RENVOI);
                            groupActivities.add(schoolRenvoiActivity);
                        }
                    }
                }*/

                // Homeworks given
                // TODO cdt
                /*if (containHomework) {
                    List<Homework> givenHomeworks = new ArrayList<Homework>();
                    if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                        givenHomeworks = HomeworkLocalServiceUtil.getStudentHomeworks(user, minDate);
                    } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                        givenHomeworks.addAll(HomeworkLocalServiceUtil.getTeacherHomeworks(user, minDate, 0));
                    }

                    if (!givenHomeworks.isEmpty()) logger.info("Got "+givenHomeworks.size()+" homeworks");

                    for (Homework givenHomework : givenHomeworks) {
                        if (givenHomework.getFromDate().after(minDate) && givenHomework.getFromDate().before(maxDate)) {
                            GroupActivity homeworkActivity = new GroupActivity(givenHomework.getHomeworkId(), 0, givenHomework.getFromDate(), ActivityConstants.ACTIVITY_TYPE_HOMEWORK);
                            groupActivities.add(homeworkActivity);
                        }
                    }
                }

                // Sessions content added
                if (containsSessions) {
                    logger.info("fetch sessions");
                    List<CDTSession> sessions = CDTSessionLocalServiceUtil.getGroupsSessionActivity(allHistory ? 0 : user.getUserId(), groupIds, minDate, maxDate);
                    for (CDTSession session : sessions) {
                        // Get modificationDate
                        Date modificationDate = SessionTeacherLocalServiceUtil.getLastModificationDate(session.getSessionId(), minDate, maxDate);
                        GroupActivity sessionActivity = new GroupActivity(session.getSessionId(), 0, modificationDate, ActivityConstants.ACTIVITY_TYPE_SESSION);
                        groupActivities.add(sessionActivity);
                    }
                }*/

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

    public JSONObject convertGroupActivity(long userId, GroupActivity groupActivity) {

        JSONObject jsonActivity = JSONFactoryUtil.createJSONObject();

        try {
            if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_NEWS) {

                // TODO News
                // jsonActivity = NewsLocalServiceUtil.convertNewsToJson(groupActivity.getActivityId(), userId, false);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_ACTIVITY) {

                // TODO Documents
                // Activity activity = ActivityLocalServiceUtil.getActivity(groupActivity.getActivityId());
                // jsonActivity = ActivityLocalServiceUtil.convertActivityToJson(activity);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_MEMBERSHIP) {
                MembershipActivity membershipActivity = MembershipActivityLocalServiceUtil.getMembershipActivity(groupActivity.getActivityId());
                jsonActivity = MembershipActivityLocalServiceUtil.convertMembershipActivityToJson(membershipActivity);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_PENDING_RENVOI) {

                // TODO HHC
                // Renvoi renvoi = RenvoiLocalServiceUtil.getRenvoi(new RenvoiPK(groupActivity.getActivityId(), groupActivity.getStudentId()));
                // jsonActivity = RenvoiLocalServiceUtil.convertRenvoiToJson(renvoi);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_SCHOOL_RENVOI) {

                // TODO HHC
                // Renvoi schoolRenvoi = RenvoiLocalServiceUtil.getRenvoi(new RenvoiPK(groupActivity.getActivityId(), groupActivity.getStudentId()));
                // jsonActivity = RenvoiLocalServiceUtil.convertSchoolRenvoi(schoolRenvoi);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_HOMEWORK) {

                // TODO cdt
                /* Homework homework = HomeworkLocalServiceUtil.getHomework(groupActivity.getActivityId());
                User teacher = UserLocalServiceUtil.getUser(homework.getTeacherId());
                jsonActivity.put("modificationDate", homework.getFromDate());
                jsonActivity.put("author", teacher.getFullName());
                String target;
                if (homework.getIsCustomStudentList()) {
                    target = StudentHomeworkLocalServiceUtil.getHomeworkStudents(homework.getHomeworkId()).size() + " \u00E9l\u00E8ves";
                } else {
                    target = " tous";
                }
                jsonActivity.put("target", target);*/
                jsonActivity.put("type", ActivityConstants.TYPE_HOMEWORK);

            } else if (groupActivity.getActivityType() == ActivityConstants.ACTIVITY_TYPE_SESSION) {

                // TODO cdt
                /* CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(groupActivity.getActivityId());
                jsonActivity.put("modificationDate", groupActivity.getActivityDate());
                User lastEditor = SessionTeacherLocalServiceUtil.getLastEditor(session.getSessionId(), groupActivity.getActivityDate());
                jsonActivity.put("author", lastEditor.getFullName());
                jsonActivity.put("target", session.getTitle());*/
                jsonActivity.put("type", ActivityConstants.TYPE_SESSION);
            }
        } catch (Exception e) {
            logger.error("Error converting group activity", e);
        }

        return jsonActivity;
    }

}
