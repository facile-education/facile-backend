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

package com.weprode.facile.course.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.StudentHomework;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
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

    public JSONObject convertToJSON(User user, boolean includeBlocks, boolean withDetails) {
        JSONObject jsonHomework = new JSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        jsonHomework.put(JSONConstants.GROUP_ID, this.getCourseId());
        jsonHomework.put(JSONConstants.HOMEWORK_ID, this.getHomeworkId());
        jsonHomework.put(JSONConstants.TITLE, this.getTitle());
        jsonHomework.put(JSONConstants.TYPE, this.getHomeworkType());
        jsonHomework.put(JSONConstants.MODIFICATION_DATE, sdf.format(this.getModificationDate()));
        jsonHomework.put(JSONConstants.SOURCE_SESSION_ID, this.getSourceSessionId());
        if (!withDetails) {
            try {
                CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(this.getSourceSessionId());
                jsonHomework.put(JSONConstants.SOURCE_SESSION_DATE, sdf.format(sourceSession.getStart()));
            } catch (Exception e) {
                logger.error("Error retrieving session with Id " + this.getSourceSessionId(), e);
            }
        }
        jsonHomework.put(JSONConstants.TO_DATE, sdf.format(this.getTargetDate()));
        try {
            CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(this.getTargetSessionId());
            jsonHomework.put(JSONConstants.TARGET_SESSION_ID, this.getTargetSessionId());
            jsonHomework.put(JSONConstants.TARGET_SLOT_NUMBER, targetSession.getSlot());
        } catch (Exception e) {
            logger.error("Error retrieving session with Id " + this.getTargetSessionId(), e);
        }
        jsonHomework.put(JSONConstants.IS_DRAFT, this.getIsDraft());
        jsonHomework.put(JSONConstants.PUBLICATION_DATE, sdf.format(this.getPublicationDate()));
        if (this.getEstimatedTime() > 0) {
            jsonHomework.put(JSONConstants.ESTIMATED_TIME, this.getEstimatedTime());
        }

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
        jsonHomework.put(JSONConstants.COURSE_ID, this.getCourseId());
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
            if (withDetails) {
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

                // Number of corrected files
                jsonHomework.put(JSONConstants.NB_CORRECTED, StudentHomeworkLocalServiceUtil.countCorrectedWorks(this.getHomeworkId()));

                // Is the correction sent ?
                jsonHomework.put(JSONConstants.IS_CORRECTION_SENT, this.isIsCorrectionSent());
            }
        }

        if (includeBlocks) {
            List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(getHomeworkId());
            JSONArray jsonBlocks = new JSONArray();

            String shortContent = null; // TODO to externalise of 'includeBlocks' content
            for (ContentBlock block : blocks) {
                if (block.getBlockType() == CourseConstants.TYPE_TEXT && shortContent == null) {
                    String text = HtmlParserUtil.extractText(block.getBlockValue());
                    shortContent = text.substring(0, Math.min(100, text.length()));
                    if (text.length() > 100) {
                        shortContent = shortContent.concat("...");
                    }
                }
                jsonBlocks.put(block.convertToJSON());
            }
            jsonHomework.put(JSONConstants.BLOCKS, jsonBlocks);
            jsonHomework.put(JSONConstants.SHORT_CONTENT, shortContent);
        }

        return jsonHomework;
    }

    public JSONObject convertToJSON(User user, boolean includeBlocks) {
        return convertToJSON(user, includeBlocks, true);
    }

}