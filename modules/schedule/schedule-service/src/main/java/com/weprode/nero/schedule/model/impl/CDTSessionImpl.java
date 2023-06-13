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

import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.CDTSessionModel;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CDTSessionImpl extends CDTSessionBaseImpl {

    private static final int NB_NEXT_SESSIONS = 15;

    // Used for prev and next session or details panel
    public JSONObject convertToJSON(User user) {
        JSONObject jsonSession = new JSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        // Global infos
        jsonSession.put(JSONConstants.SESSION_ID, this.getSessionId());
        jsonSession.put(JSONConstants.START_DATE, sdf.format(this.getStart()));
        jsonSession.put(JSONConstants.END_DATE, sdf.format(this.getEnd()));
        jsonSession.put(JSONConstants.SUBJECT, this.getSubject());
        jsonSession.put(JSONConstants.GROUP_ID, this.getGroupId());
        jsonSession.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(this.getGroupId()));
        jsonSession.put(JSONConstants.ROOM, this.getRoom());
        jsonSession.put(JSONConstants.IS_MANUAL, this.getIsManual());
        jsonSession.put(JSONConstants.COLOR, CourseDetailsLocalServiceUtil.getCourseColor(this.getGroupId()));

        // Teachers
        List<User> sessionTeachers = SessionTeacherLocalServiceUtil.getTeachers(this.getSessionId());
        jsonSession.put(JSONConstants.TEACHERS, UserUtilsLocalServiceUtil.convertUsersToJson(sessionTeachers));
        jsonSession.put(JSONConstants.IS_CURRENT_USER_TEACHER, sessionTeachers.contains(user));

        return jsonSession;
    }

    // Fetch next sessions for same groupId in the next 50 days
    public List<CDTSession> getNextSessions(User user) {
        List<CDTSession> nextSessions = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        cal.setTime(this.getEnd());
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

        nextSessions.sort(Comparator.comparing(CDTSessionModel::getStart));

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

}