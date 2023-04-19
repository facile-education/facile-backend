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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.StudentHomework;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionParentClassLocalServiceUtil;
import com.weprode.nero.schedule.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.schedule.utils.JSONProxy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class HomeworkImpl extends HomeworkBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HomeworkImpl.class);

    public HomeworkImpl() {}

    public JSONObject convertToJSON(User user) {
        JSONObject jsonHomework = JSONFactoryUtil.createJSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
        jsonHomework.put(JSONConstants.GROUP_ID, this.getGroupId());
        jsonHomework.put(JSONConstants.HOMEWORK_ID, this.getHomeworkId());
        jsonHomework.put(JSONConstants.DESCRIPTION, this.getDescription());
        jsonHomework.put(JSONConstants.TYPE, this.getType());
        jsonHomework.put(JSONConstants.ESTIMATED_TIME, this.getEstimatedTime());
        jsonHomework.put(JSONConstants.FROM_DATE, sdf.format(this.getFromDate()));
        jsonHomework.put(JSONConstants.SOURCE_SESSION_ID, this.getSourceSessionId());
        jsonHomework.put(JSONConstants.TO_DATE, sdf.format(this.getTargetDate()));
        jsonHomework.put(JSONConstants.TARGET_SESSION_ID, this.getTargetSessionId());

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

        // Dropbox link for teacher
        // TODO Deposit folder
        /*if (RoleUtilsLocalServiceUtil.isTeacher(user) && (this.getType() == 2 || this.getType() == 3)) {
            List<HomeworkDropboxFolder> folderList = HomeworkDropboxFolderLocalServiceUtil.getHomeworkDropboxFolders(this.getHomeworkId());
            if (folderList != null) {
                for (HomeworkDropboxFolder homeworkFolder : folderList) {
                    if (homeworkFolder.getTeacherId() == user.getUserId()) {
                        jsonHomework.put("dropboxLink", "#/casier?folderId=" + homeworkFolder.getFolderId());
                    }
                }
            }
        }*/

        // Audio link
        // TODO Attachments
        /*String audioUrl = AttachFileLocalServiceUtil.getHomeworkAudioInstructionUrl(this.getHomeworkId());
        jsonHomework.put("audioUrl", audioUrl);*/

        // Attach files
        // TODO Attachments
        /*List<AttachFile> homeworkAttachFiles = AttachFileLocalServiceUtil.getHomeworkAttachFiles(this.getHomeworkId());
        jsonHomework.put("attachFiles", JSONProxy.convertAttachFiles(user, homeworkAttachFiles, false));
        jsonHomework.put("attachExercices", JSONProxy.convertAttachFiles(user, homeworkAttachFiles, true));*/

        // Class name
        String className = "";
        String schoolName = "";
        try {
            if (this.getGroupId() == 0) {
                // SubClass
                long referenceSessionId = (this.getTargetSessionId() != 0) ? this.getTargetSessionId() : this.getSourceSessionId();
                className = SessionParentClassLocalServiceUtil.getParentClassName(referenceSessionId);
            } else {
                Group group = GroupLocalServiceUtil.getGroup(this.getGroupId());
                className = OrgUtilsLocalServiceUtil.formatOrgName(group.getName(), false);
                if (!group.isRegularSite()) {
                    // Institutional -> school name
                    schoolName = OrgUtilsLocalServiceUtil.formatOrgName(OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId()).getParentOrganization().getName() , true);
                    jsonHomework.put(JSONConstants.SCHOOL_NAME, schoolName);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot fetch className for homeworkId = " + this.getHomeworkId(), e);
        }
        jsonHomework.put(JSONConstants.CLASS_NAME, className);

        long sessionIdReference = (this.getTargetSessionId() != 0) ? this.getTargetSessionId() : this.getSourceSessionId();

        try {
            CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sessionIdReference);
            jsonHomework.put(JSONConstants.SUBJECT, cdtSession.getSubject());
        } catch (Exception e) {
            logger.error("Error when getting lesson subject for homeworkId " + this.getHomeworkId(), e);
        }

        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)){
            jsonHomework.put(JSONConstants.IS_DONE, StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(user.getUserId(), this.getHomeworkId()));
        }
        else {
            // Teacher case

            // Build the student list for the associated class/group
            List<User> targetSessionStudents = CDTSessionLocalServiceUtil.getSessionStudents(sessionIdReference);
            List<User> sourceSessionStudents = CDTSessionLocalServiceUtil.getSessionStudents(this.getSourceSessionId());

            JSONArray targetSessionStudentsJson = JSONProxy.convertUsersToJson(targetSessionStudents);
            jsonHomework.put("groupStudents", targetSessionStudentsJson);

            // Get assigned students
            List<User> assignedStudents;
            if (this.isIsCustomStudentList()) {
                // Specific student assignment
                assignedStudents = StudentHomeworkLocalServiceUtil.getHomeworkStudents(this.getHomeworkId());
                jsonHomework.put(JSONConstants.SELECTED_STUDENTS, JSONProxy.convertUsersToJson(assignedStudents));
                jsonHomework.put(JSONConstants.IS_WHOLE_CLASS, false);

            } else if (this.getTargetSessionId() == 0) {
                // Free date this => selected students are source session students
                JSONArray sourceSessionStudentsJson = JSONProxy.convertUsersToJson(sourceSessionStudents);
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

                JSONArray commonStudentsJson = JSONProxy.convertUsersToJson(commonStudents);
                jsonHomework.put(JSONConstants.SELECTED_STUDENTS, commonStudentsJson);
            }

            // Students having done the this
            List<User> doneStudents = StudentHomeworkLocalServiceUtil.getStudentsHavingDoneHomework(this.getHomeworkId());
            jsonHomework.put(JSONConstants.DONE_STUDENTS, JSONProxy.convertUsersToJson(doneStudents));
        }

        // Progression item
        // TODO Progression
        /*Long assignedItemId = ProgressionItemAssignmentLocalServiceUtil.getHomeworkAssignmentItemId(this.getHomeworkId());
        jsonHomework.put("assignedItemId", assignedItemId);

        // is Progression driven ?
        boolean isProgressionDriven = assignedItemId != 0;
        jsonHomework.put("isProgressionDriven", isProgressionDriven);
        if (isProgressionDriven) {
            try {
                long progressionItemId = ProgressionItemAssignmentLocalServiceUtil.getHomeworkAssignmentItemId(this.getHomeworkId());
                ProgressionItem item = ProgressionItemLocalServiceUtil.getProgressionItem(progressionItemId);
                Progression progression = ProgressionLocalServiceUtil.getProgression(item.getProgressionId());
                // Only the progression's owner has the progression link
                if (progression.getTeacherId() == user.getUserId()) {
                    String progressionUrl = "/user/" + user.getScreenName() + "/nero#/progression?progressionId=" + item.getProgressionId();
                    jsonHomework.put("progressionUrl", progressionUrl);
                } else if (SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), this.getSourceSessionId())) {
                    User progressionOwner = UserLocalServiceUtil.getUser(progression.getTeacherId());
                    jsonHomework.put("progressionOwner", progressionOwner.getFullName());
                }
            } catch (Exception e) {
            }
        }*/

        return jsonHomework;
    }

}