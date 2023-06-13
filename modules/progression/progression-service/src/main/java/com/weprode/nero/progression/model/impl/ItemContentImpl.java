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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.progression.constants.ProgressionConstants;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class ItemContentImpl extends ItemContentBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(ItemContentImpl.class);

    public JSONObject convertToJSON(boolean isContentIncluded) {
        JSONObject jsonItem = new JSONObject();

        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonItem.put(JSONConstants.ITEM_ID, this.getProgressionItemId());
        jsonItem.put(JSONConstants.BLOCK_ID, this.getContentId());
        jsonItem.put(JSONConstants.MODIFIED_DATE, df.format(this.getModifiedDate()));
        jsonItem.put(JSONConstants.BLOCK_NAME, this.getContentName());
        jsonItem.put(JSONConstants.BLOCK_TYPE, this.getContentType());
        jsonItem.put(JSONConstants.FILE_ENTRY_ID, String.valueOf(this.getFileEntryId())); // Ids are managed as String in front
        if (this.getFileEntryId() != 0) {
            if (this.getContentType() == ProgressionConstants.TYPE_RECORD) {
                jsonItem.put(JSONConstants.LINK, "/c/document_library/get_file?fileEntryId=" + this.getFileEntryId());
            }
            try {
                FileEntry file = DLAppServiceUtil.getFileEntry(this.getFileEntryId());
                jsonItem.put(JSONConstants.DOWNLOAD_URL, FileUtilsLocalServiceUtil.getDisplayUrl(file, file.getLatestFileVersion().getFileVersionId(), "", 0,false)); // Display URL with typeOfView = "" is download url
            } catch (Exception e) {
                logger.error(e);
            }
        }
        jsonItem.put(JSONConstants.ORDER, this.getOrder());

        if (isContentIncluded) {
            jsonItem.put(JSONConstants.BLOCK_VALUE, this.getContentValue());
        }

        return jsonItem;
    }
}