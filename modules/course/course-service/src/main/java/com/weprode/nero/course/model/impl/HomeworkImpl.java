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

package com.weprode.nero.course.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.model.StudentHomework;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class HomeworkImpl extends HomeworkBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HomeworkImpl.class);

    public JSONObject convertToJSON(User user, boolean includeBlocks) {

        JSONObject jsonHomework = new JSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        jsonHomework.put(JSONConstants.GROUP_ID, this.getCourseId());
        jsonHomework.put(JSONConstants.HOMEWORK_ID, this.getHomeworkId());
        jsonHomework.put(JSONConstants.TITLE, this.getTitle());
        jsonHomework.put(JSONConstants.TYPE, this.getHomeworkType());
        jsonHomework.put(JSONConstants.MODIFICATION_DATE, sdf.format(this.getModificationDate()));
        jsonHomework.put(JSONConstants.SOURCE_SESSION_ID, this.getSourceSessionId());
        jsonHomework.put(JSONConstants.TO_DATE, sdf.format(this.getTargetDate()));
        jsonHomework.put(JSONConstants.TARGET_SESSION_ID, this.getTargetSessionId());
        jsonHomework.put(JSONConstants.IS_DRAFT, this.getIsDraft());
        jsonHomework.put(JSONConstants.PUBLICATION_DATE, this.getPublicationDate());

        // Is it sent ?
        if (RoleUtilsLocalServiceUtil.isStudent(user)) {
            StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(this.getHomeworkId(), user.getUserId());
            if (studentHomework != null) {
                jsonHomework.put(JSONConstants.IS_SENT, studentHomework.getIsSent());
                if (studentHomework.getIsSent() && studentHomework.getSentDate() != null) {
                    jsonHomework.put(JSONConstants.SENT_DATE, sdf.format(studentHomework.getSentDate()));
                    jsonHomework.put(JSONConstants.SENT_FILE_ID, studentHomework.getSentFileId());
                }
            }
        }

        // Sent files
        if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
            jsonHomework.put(JSONConstants.SENT_FILES, StudentHomeworkLocalServiceUtil.getSentFiles(getHomeworkId()));
        } else if (RoleUtilsLocalServiceUtil.isStudent(user)) {
            jsonHomework.put(JSONConstants.SENT_FILE, StudentHomeworkLocalServiceUtil.getStudentSentFile(user.getUserId(), getHomeworkId()));
        }

        long referenceSessionId = (this.getTargetSessionId() != 0) ? this.getTargetSessionId() : this.getSourceSessionId();

        // Cours name
        String coursName = "";
        try {
            long groupId = this.getCourseId();
            if (this.getCourseId() != 0) {
                // Use either source or target session to get the homework's groupId
                groupId = CDTSessionLocalServiceUtil.getCDTSession(referenceSessionId).getGroupId();
            }
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            coursName = OrgUtilsLocalServiceUtil.formatOrgName(group.getName(user.getLocale()), false);
            jsonHomework.put(JSONConstants.COLOR, CourseDetailsLocalServiceUtil.getCourseColor(groupId));
        } catch (Exception e) {
            logger.error("Cannot fetch coursName for homeworkId = " + this.getHomeworkId(), e);
        }
        jsonHomework.put(JSONConstants.COURS, coursName);
        jsonHomework.put(JSONConstants.SUBJECT, CourseDetailsLocalServiceUtil.getCourseSubject(getCourseId()));

        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            try {
                User teacher = UserLocalServiceUtil.getUser(this.getTeacherId());
                JSONObject teacherJson = new JSONObject();
                teacherJson.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
                teacherJson.put(JSONConstants.LAST_NAME, teacher.getLastName());
                jsonHomework.put(JSONConstants.TEACHER, teacherJson);
            } catch (PortalException e) {
                logger.error("Could not fetch homework teacher with id = " + this.getTeacherId(), e);
            }
            jsonHomework.put(JSONConstants.IS_DONE, StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(user.getUserId(), this.getHomeworkId()));
        }
        else {
            // Teacher case

            // Build the student list for the associated class/group
            List<User> targetSessionStudents = CDTSessionLocalServiceUtil.getSessionStudents(referenceSessionId);
            List<User> sourceSessionStudents = CDTSessionLocalServiceUtil.getSessionStudents(this.getSourceSessionId());

            JSONArray targetSessionStudentsJson = UserUtilsLocalServiceUtil.convertUsersToJson(targetSessionStudents);
            jsonHomework.put(JSONConstants.GROUP_STUDENTS, targetSessionStudentsJson);

            // Get assigned students
            List<User> assignedStudents;
            if (this.isIsCustomStudentList()) {
                // Specific student assignment
                assignedStudents = StudentHomeworkLocalServiceUtil.getHomeworkStudents(this.getHomeworkId());
                jsonHomework.put(JSONConstants.SELECTED_STUDENTS, UserUtilsLocalServiceUtil.convertUsersToJson(assignedStudents));
                jsonHomework.put(JSONConstants.IS_WHOLE_CLASS, false);

            } else if (this.getTargetSessionId() == 0) {
                // Free date this => selected students are source session students
                JSONArray sourceSessionStudentsJson = UserUtilsLocalServiceUtil.convertUsersToJson(sourceSessionStudents);
                jsonHomework.put(JSONConstants.SELECTED_STUDENTS, sourceSessionStudentsJson);
                jsonHomework.put(JSONConstants.IS_WHOLE_CLASS, true);

            } else {
                jsonHomework.put(JSONConstants.IS_WHOLE_CLASS, true);

                // Mark all students as selected for C&D
                // Process the intersection between students of source session and students of target session
                List<User> commonStudents = new ArrayList<>();
                for (User sourceStudent : sourceSessionStudents) {
                    for (User targetStudent : targetSessionStudents) {
                        if (sourceStudent.getUserId() == targetStudent.getUserId()) {
                            commonStudents.add(sourceStudent);
                            break;
                        }
                    }
                }

                JSONArray commonStudentsJson = UserUtilsLocalServiceUtil.convertUsersToJson(commonStudents);
                jsonHomework.put(JSONConstants.SELECTED_STUDENTS, commonStudentsJson);
            }

            // Students having done the homework
            List<User> doneStudents = StudentHomeworkLocalServiceUtil.getStudentsHavingDoneHomework(this.getHomeworkId());
            jsonHomework.put(JSONConstants.DONE_STUDENTS, UserUtilsLocalServiceUtil.convertUsersToJson(doneStudents));
        }

        if (includeBlocks) {
            List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(getHomeworkId());
            JSONArray jsonBlocks = new JSONArray();
            for (ContentBlock block : blocks) {
                jsonBlocks.put(block.convertToJSON());
            }
            jsonHomework.put(JSONConstants.BLOCKS, jsonBlocks);
        }

        return jsonHomework;
    }

}