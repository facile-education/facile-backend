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
import com.weprode.nero.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CDTSessionImpl extends CDTSessionBaseImpl {

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

        jsonSession.put(JSONConstants.CAN_SAVE_TEACHER_SUBSTITUTE, SessionTeacherLocalServiceUtil.canSaveTeacherSubstitutes(user));

        // Teachers
        List<User> sessionTeachers = SessionTeacherLocalServiceUtil.getTeachers(this.getSessionId(), false);
        jsonSession.put(JSONConstants.TEACHERS, UserUtilsLocalServiceUtil.convertUsersToJson(sessionTeachers));
        jsonSession.put(JSONConstants.IS_CURRENT_USER_TEACHER, sessionTeachers.contains(user));

        return jsonSession;
    }

}