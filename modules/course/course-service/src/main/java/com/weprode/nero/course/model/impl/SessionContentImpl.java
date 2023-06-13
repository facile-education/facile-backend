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

import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionContentImpl extends SessionContentBaseImpl {

    public JSONObject convertToJSON(User user, boolean isContentIncluded) {

        JSONObject jsonItem = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonItem.put(JSONConstants.ITEM_ID, this.getSessionId());
        jsonItem.put(JSONConstants.MODIFIED_DATE, df.format(this.getModificationDate()));
        jsonItem.put(JSONConstants.TITLE, this.getTitle());
        jsonItem.put(JSONConstants.BLOCKS, new JSONArray());

        if (isContentIncluded) {
            List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(this.getSessionId());

            JSONArray jsonBlocks = new JSONArray();
            for (ContentBlock block : blocks) {
                jsonBlocks.put(block.convertToJSON());
            }
            jsonItem.put(JSONConstants.BLOCKS, jsonBlocks);
        }
        return jsonItem;
    }
}