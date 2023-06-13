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
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.model.Subject;
import com.weprode.nero.schedule.service.SubjectLocalServiceUtil;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class ProgressionImpl extends ProgressionBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionImpl.class);

    public JSONObject convertToJSON() {
        JSONObject jsonProgression = new JSONObject();

        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonProgression.put(JSONConstants.PROGRESSION_ID, this.getProgressionId());
        jsonProgression.put(JSONConstants.CREATED_DATE, df.format(this.getCreateDate()));
        jsonProgression.put(JSONConstants.MODIFIED_DATE, df.format(this.getModifiedDate()));
        jsonProgression.put(JSONConstants.NAME, this.getName());
        jsonProgression.put(JSONConstants.DESCRIPTION, this.getDescription());
        jsonProgression.put(JSONConstants.SUBJECT_ID, this.getSubjectId());
        try {
            Subject subject = SubjectLocalServiceUtil.getSubject(this.getSubjectId());
            jsonProgression.put(JSONConstants.SUBJECT_NAME, subject.getName());
        } catch (Exception e) {
            logger.debug(e);
        }
        jsonProgression.put(JSONConstants.VOLEE, this.getVolee());
        jsonProgression.put(JSONConstants.COLOR, this.getColor());

        return jsonProgression;
    }
}