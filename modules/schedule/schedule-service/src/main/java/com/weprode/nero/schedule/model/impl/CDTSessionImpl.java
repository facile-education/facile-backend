/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.schedule.model.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.CDTSessionModel;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.service.*;
import com.weprode.nero.schedule.utils.CDTColorUtil;
import com.weprode.nero.schedule.utils.JSONProxy;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Brian Wing Shun Chan
 */
public class CDTSessionImpl extends CDTSessionBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(CDTSessionImpl.class);

    private static final int NB_PREVIOUS_SESSIONS = 3;
    private static final int NB_NEXT_SESSIONS = 15;

    public CDTSessionImpl() {

    }

    public JSONObject convertToJSON() {
        return convertToJSON(false, null);
    }

    // Used for prev and next session or details panel
    public JSONObject convertToJSON(boolean includeDetails, User user) {
        JSONObject jsonSession = JSONFactoryUtil.createJSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        // Global infos
        jsonSession.put("sessionId", this.getSessionId());
        jsonSession.put("title", this.getTitle());
        jsonSession.put("startDate", sdf.format(this.getSessionStart()));
        jsonSession.put("endDate", sdf.format(this.getSessionEnd()));
        jsonSession.put("description", this.getDescription());

        // Class name
        jsonSession.put("className", getSessionGroupName(false));

        if (!includeDetails) {
            return jsonSession;
        }

        // Details
        jsonSession.put("groupId", this.getGroupId());
        jsonSession.put("room", this.getRoom());
        jsonSession.put("isManual", this.getIsManual());
        jsonSession.put("isPublish", this.getPublished());

        // Teachers
        List<User> sessionTeachers = SessionTeacherLocalServiceUtil.getTeachers(this.getSessionId());
        jsonSession.put("teachers", JSONProxy.convertUsersToJson(sessionTeachers));
        jsonSession.put("isCurrentUserTeacher", sessionTeachers.contains(user));

        // Subject
        jsonSession.put("subject", this.getSubject());

        // Audio instructions
        // TODO Attachments
        /* String audioUrl = AttachFileLocalServiceUtil.getSessionAudioInstructionUrl(this.getSessionId());
        jsonSession.put("audioUrl", audioUrl);*/

        // Attached files
        // TODO Attachments
        /* List<AttachFile> sessionAttachFiles = AttachFileLocalServiceUtil.getSessionAttachFiles(this.getSessionId());
        jsonSession.put("attachFiles", JSONProxy.convertAttachFiles(user, sessionAttachFiles));*/

        // To Do homeworks
        long studentId = 0;
        if (RoleUtilsLocalServiceUtil.isStudent(user)) {
            studentId = user.getUserId();
        }
        List<Homework> toDoHomeworks = HomeworkLocalServiceUtil.getToDoHomeworks(this.getSessionId(), studentId);
        jsonSession.put("toDoHomework", JSONProxy.convertHomeworksAsJson(user, toDoHomeworks));

        // Given homeworks
        List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getGivenHomeworks(this.getSessionId(), studentId);
        jsonSession.put("givenHomework", JSONProxy.convertHomeworksAsJson(user, givenHomeworks));

        // Previous and next sessions
        List<CDTSession> previousSessions = this.getPreviousSessions(user);
        List<CDTSession> nextSessions = this.getNextSessions(user);
        jsonSession.put("previousSessions", JSONProxy.convertSessionsToSimpleJson(previousSessions));
        jsonSession.put("nextSessions", JSONProxy.convertSessionsToSimpleJson(nextSessions));

        // Color
        String color = TeacherGroupColorLocalServiceUtil.getTeacherGroupColor(user.getUserId(), this.getGroupId());
        jsonSession.put("color", color);

        // Is progression driven ?
        // TODO Progression
        /* boolean isProgressionDriven = ProgressionItemAssignmentLocalServiceUtil.isSessionAffected(this.getSessionId());
        jsonSession.put("isProgressionDriven", isProgressionDriven);
        if (isProgressionDriven) {
            try {
                long progressionItemId = ProgressionItemAssignmentLocalServiceUtil.getSessionAssignmentItemId(this.getSessionId());
                ProgressionItem item = ProgressionItemLocalServiceUtil.getProgressionItem(progressionItemId);
                Progression progression = ProgressionLocalServiceUtil.getProgression(item.getProgressionId());
                // Only the progression's owner has the progression link
                if (progression.getTeacherId() == user.getUserId()) {
                    String progressionUrl = "/user/" + user.getScreenName() + "/nero#/progression?progressionId=" + item.getProgressionId();
                    jsonSession.put("progressionUrl", progressionUrl);
                } else if (SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), this.getSessionId())) {
                    User progressionOwner = UserLocalServiceUtil.getUser(progression.getTeacherId());
                    jsonSession.put("progressionOwner", progressionOwner.getFullName());
                }
            } catch (Exception e) {
            }
        }*/

        return jsonSession;
    }

    // Used when displaying session in calendar
    public JSONObject convertToJSON(long colorsTeacherId, User user) {
        JSONObject jsonSession = JSONFactoryUtil.createJSONObject();

        boolean isPersonalGroup = false;
        String className = StringPool.BLANK;

        try {
            Group sessionGroup = GroupLocalServiceUtil.getGroup(this.getGroupId());
            if (sessionGroup == null) {
                logger.error("Error when trying to map groupId " + this.getGroupId() + " for user "+user.getFullName());
                return null;
            }
            Organization sessionOrg = OrganizationLocalServiceUtil.fetchOrganization(sessionGroup.getClassPK());

            if (sessionOrg != null) {
                className = OrgUtilsLocalServiceUtil.formatOrgName(sessionOrg.getName(), false);
            } else {
                // Handle the personal group case
                className = sessionGroup.getName();
                isPersonalGroup = true;
            }
        } catch (Exception e) {
            logger.error("Error building JSON for this", e);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        jsonSession.put(JSONConstants.SESSION_ID, this.getSessionId());
        jsonSession.put(JSONConstants.GROUP_ID, this.getGroupId());
        jsonSession.put(JSONConstants.START_DATE, sdf.format(this.getSessionStart()));
        jsonSession.put(JSONConstants.END_DATE, sdf.format(this.getSessionEnd()));
        jsonSession.put(JSONConstants.ROOM, this.getRoom());
        jsonSession.put(JSONConstants.HAS_DESCRIPTION, !this.getDescription().isEmpty());
        jsonSession.put(JSONConstants.IS_MANUAL, this.getIsManual());
        jsonSession.put(JSONConstants.IS_PUBLISH, this.getPublished());
        jsonSession.put(JSONConstants.TITLE, this.getTitle());
        jsonSession.put(JSONConstants.CLASS_NAME, className);
        // jsonSession.put("parentGroupIds", parentGroupIds);
        jsonSession.put(JSONConstants.SUBJECT, this.getSubject());
        jsonSession.put(JSONConstants.IS_PERSONAL_GROUP, isPersonalGroup);

        // Teachers
        List<User> sessionTeachers = SessionTeacherLocalServiceUtil.getTeachers(this.getSessionId(), false);
        jsonSession.put(JSONConstants.TEACHERS, JSONProxy.convertUsersToJson(sessionTeachers));
        jsonSession.put(JSONConstants.IS_CURRENT_USER_TEACHER, sessionTeachers.contains(user));

        // Color
        String color; // default

        // If user is student or parent or personal getting sessions for a given class -> use SubjectGroup colors
        // If user if a teacher or personal getting sessions for a given teacher -> use TeacherGroup colors
        if (colorsTeacherId != 0) {
            color = TeacherGroupColorLocalServiceUtil.getTeacherGroupColor(colorsTeacherId, this.getGroupId());
        } else {
            // Color is based on the class, not the cours -> use the parent class
            List<Long> groupIds = SessionParentClassLocalServiceUtil.getSessionParentGroupIds(this.getSessionId());
            long groupId = (groupIds != null && !groupIds.isEmpty()) ? groupIds.get(0) : this.getGroupId();
            color = SubjectGroupColorLocalServiceUtil.getSubjectGroupColor(groupId, this.getSubject());
        }

        jsonSession.put(JSONConstants.COLOR, color);

        // Attach files
        // TODO Attachments
        /*List<AttachFile> sessionAttachFiles = AttachFileLocalServiceUtil.getSessionAttachFiles(this.getSessionId());
        jsonSession.put("hasAttachFiles", sessionAttachFiles != null && sessionAttachFiles.size() > 0);*/

        // Homeworks
        boolean hasHomeworks = HomeworkLocalServiceUtil.hasHomeworksGivenInSession(this.getSessionId()) || HomeworkLocalServiceUtil.hasHomeworksToDoForSession(this.getSessionId());
        jsonSession.put(JSONConstants.HAS_HOMEWORK, hasHomeworks);

        // Progression item
        // TODO Progression
        /* Long assignedItemId = ProgressionItemAssignmentLocalServiceUtil.getSessionAssignmentItemId(this.getSessionId());
        jsonSession.put("assignedItemId", assignedItemId);*/

        return jsonSession;
    }

    // Fetch previous sessions for same groupId in the previous 28 days
    // User is the user asking for previous sessions
    public List<CDTSession> getPreviousSessions(User user) {
        List<CDTSession> previousSessions = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getSessionStart());
        cal.add(Calendar.DATE, -28);
        Date startDate = cal.getTime();

        cal.setTime(this.getSessionStart());
        cal.add(Calendar.MINUTE, -1);
        Date endDate = cal.getTime();

        List<CDTSession> sessions = CDTSessionLocalServiceUtil.getGroupSessions(this.getGroupId(), startDate, endDate, false);
        boolean isTeacher = RoleUtilsLocalServiceUtil.isTeacher(user);

        for (CDTSession previousSession : sessions) {
            if (previousSession.getSessionId() != this.getSessionId() && (isTeacher && SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), previousSession.getSessionId()))) {
                previousSessions.add(previousSession);
            }
        }

        previousSessions.sort(Comparator.comparing(CDTSessionModel::getSessionStart));

        if (previousSessions.size() > NB_PREVIOUS_SESSIONS) {
            previousSessions = previousSessions.subList(previousSessions.size() - NB_PREVIOUS_SESSIONS, previousSessions.size());
        }

        return previousSessions;
    }

    // Fetch next sessions for same groupId in the next 50 days
    public List<CDTSession> getNextSessions(User user) {
        List<CDTSession> nextSessions = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        cal.setTime(this.getSessionEnd());
        cal.add(Calendar.MINUTE, 1);
        Date startDate = cal.getTime();

        cal.add(Calendar.DATE, 50);
        Date endDate = cal.getTime();

        List<CDTSession> sessions = CDTSessionLocalServiceUtil.getGroupSessions(this.getGroupId(), startDate, endDate, false);
        boolean isTeacher = RoleUtilsLocalServiceUtil.isTeacher(user);

        for (CDTSession nextSession : sessions) {
            if (nextSession.getSessionId() != this.getSessionId() && (isTeacher && SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), nextSession.getSessionId()))) {
                nextSessions.add(nextSession);
            }
        }

        nextSessions.sort(Comparator.comparing(CDTSessionModel::getSessionStart));

        if (nextSessions.size() > NB_NEXT_SESSIONS) {
            nextSessions = nextSessions.subList(0, NB_NEXT_SESSIONS);
        }

        return nextSessions;
    }

    public String getTeacherList() {

        List<User> teachers = SessionTeacherLocalServiceUtil.getTeachers(this.getSessionId());
        StringBuilder teachersStr = new StringBuilder();
        for (User teacher : teachers) {
            teachersStr.append(teacher.getFirstName().substring(0, 1)).append(". ").append(teacher.getLastName()).append(", ");
        }
        if (teachersStr.length() > 2) {
            teachersStr = new StringBuilder(teachersStr.substring(0, teachersStr.length() - 2));
        }
        return teachersStr.toString();
    }

    public String getSessionGroupName(boolean withSchoolName) {
        String groupName = "";

        try {
            if (this.getSessionId() == 0) {
                groupName = SessionParentClassLocalServiceUtil.getParentClassName(this.getSessionId());

            } else {
                Group group = null;
                try {
                    group = GroupLocalServiceUtil.getGroup(this.getGroupId());
                } catch (Exception e) {
                    logger.debug(e);
                }

                if (group != null) {
                    if (group.isRegularSite()) {
                        // Personal group
                        groupName = group.getName();
                        int index = groupName.indexOf("LFR_ORGANIZATION");
                        if (index > 0) {
                            groupName = groupName.substring(0, index);
                        }
                    } else {
                        // Institutional group
                        Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
                        groupName =  OrgUtilsLocalServiceUtil.formatOrgName(organization.getName(), withSchoolName);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Error when getting cdt session group name", e);
        }

        return groupName;
    }
}