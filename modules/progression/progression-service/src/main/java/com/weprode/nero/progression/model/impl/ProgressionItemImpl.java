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
import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.service.ItemAssignmentLocalServiceUtil;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ProgressionItemImpl extends ProgressionItemBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionItemImpl.class);

    public JSONObject convertToJSON(long userId) {
        return this.convertToJSON(userId, true);
    }

    public JSONObject convertToJSON(long userId, boolean isContentIncluded) {
        JSONObject jsonItem = new JSONObject();

        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonItem.put(JSONConstants.ITEM_ID, this.getProgressionItemId());
        jsonItem.put(JSONConstants.PROGRESSION_ID, this.getProgressionId());
        jsonItem.put(JSONConstants.MODIFIED_DATE, df.format(this.getModifiedDate()));
        jsonItem.put(JSONConstants.FOLDER_ID, this.getProgressionFolderId());
        jsonItem.put(JSONConstants.NAME, this.getItemName());
        jsonItem.put(JSONConstants.IS_HOMEWORK, this.getIsHomework());
        jsonItem.put(JSONConstants.TYPE, this.getType());
        jsonItem.put(JSONConstants.ORDER, this.getOrder());
        jsonItem.put(JSONConstants.CONTENTS, new JSONArray());
        jsonItem.put(JSONConstants.ASSIGNMENTS, new JSONArray());

        if (isContentIncluded) {
            try {
                List<ItemContent> itemContents = ItemContentLocalServiceUtil.getContentsByItemId(this.getProgressionItemId());

                JSONArray itemContentsArray = new JSONArray();
                for (ItemContent itemContent : itemContents) {
                    itemContentsArray.put(itemContent.convertToJSON(true));
                }
                jsonItem.put(JSONConstants.CONTENTS, itemContentsArray);
            } catch (Exception e) {
                logger.error("Could not get attached files for itemId="+this.getProgressionItemId(), e);
            }

        }

        // Assignments
        try {
            List<ItemAssignment> assignments = ItemAssignmentLocalServiceUtil.getItemAssignments(this.getProgressionItemId());

            JSONArray assignmentArray = new JSONArray();
            for (ItemAssignment assignment : assignments) {
                assignmentArray.put(assignment.convertToJSON(userId));
            }
            jsonItem.put(JSONConstants.ASSIGNMENTS, assignmentArray);
        } catch (Exception e) {
            logger.error("Could not get assignments for itemId="+this.getProgressionItemId(), e);
        }

        return jsonItem;
    }
}