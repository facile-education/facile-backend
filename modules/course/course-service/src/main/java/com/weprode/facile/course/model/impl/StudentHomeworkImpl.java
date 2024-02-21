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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class StudentHomeworkImpl extends StudentHomeworkBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(StudentHomeworkImpl.class);

    public JSONObject convertToJSON() {

        JSONObject jsonHomework = new JSONObject();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);
            jsonHomework.put(JSONConstants.HOMEWORK_ID, this.getHomeworkId());
            User student = UserLocalServiceUtil.getUser(this.getStudentId());
            jsonHomework.put(JSONConstants.USER_ID, this.getStudentId());
            jsonHomework.put(JSONConstants.LAST_NAME, student.getLastName());
            jsonHomework.put(JSONConstants.FIRST_NAME, student.getFirstName());
            jsonHomework.put(JSONConstants.FILE_ENTRY_ID, this.getSentFileId());
            if (this.getSentDate() != null) {
                jsonHomework.put(JSONConstants.SENT_DATE, sdf.format(this.getSentDate()));
            }
            jsonHomework.put(JSONConstants.COMMENT, this.getComment());
            if (this.getCorrectionDate() != null) {
                jsonHomework.put(JSONConstants.CORRECTION_DATE, sdf.format(this.getCorrectionDate()));
            }

        } catch (Exception e) {
            logger.error("Error formatting student homework", e);
        }
        return jsonHomework;
    }
}