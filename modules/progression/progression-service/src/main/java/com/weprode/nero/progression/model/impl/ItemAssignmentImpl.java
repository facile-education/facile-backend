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

package com.weprode.nero.progression.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.CourseDetailsLocalServiceUtil;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class ItemAssignmentImpl extends ItemAssignmentBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemAssignmentImpl.class);

    public JSONObject convertToJSON(long userId) {
        JSONObject jsonAssignment = new JSONObject();

        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonAssignment.put(JSONConstants.ITEM_ID, this.getProgressionItemId());
        jsonAssignment.put(JSONConstants.SESSION_ID, this.getSessionId());
        jsonAssignment.put(JSONConstants.HOMEWORK_ID, this.getHomeworkId());
        jsonAssignment.put(JSONConstants.ASSIGNED_DATE, df.format(this.getAssignedDate()));
        if (this.getModifiedDate() != null) {
            jsonAssignment.put(JSONConstants.MODIFIED_DATE, df.format(this.getModifiedDate()));
        }
        try {
            CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(this.getSessionId());
            jsonAssignment.put(JSONConstants.SESSION_START_DATE, df.format(cdtSession.getStart()));
            jsonAssignment.put(JSONConstants.SESSION_END_DATE, df.format(cdtSession.getEnd()));

            Group group = GroupLocalServiceUtil.getGroup(cdtSession.getGroupId());
            if (group.isRegularSite()) {
                jsonAssignment.put(JSONConstants.GROUP_NAME, group.getName());
            } else {
                Organization org = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
                String groupName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
                jsonAssignment.put(JSONConstants.GROUP_NAME, groupName);
            }

            String color = CourseDetailsLocalServiceUtil.getCourseColor(cdtSession.getGroupId());
            jsonAssignment.put(JSONConstants.COLOR, color);

        } catch (Exception e) {
            logger.debug(e);
        }

        // If homework : render date
        ProgressionItem progressionItem;
        try {
            progressionItem = ProgressionItemLocalServiceUtil.getProgressionItem(this.getProgressionItemId());
            if (progressionItem.isIsHomework()) {
                Homework homework = HomeworkLocalServiceUtil.getHomework(this.getHomeworkId());
                jsonAssignment.put(JSONConstants.TARGET_DATE, df.format(homework.getTargetDate()));
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return jsonAssignment;
    }
}