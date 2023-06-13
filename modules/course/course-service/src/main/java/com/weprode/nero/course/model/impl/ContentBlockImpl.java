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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.CourseConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class ContentBlockImpl extends ContentBlockBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(ContentBlockImpl.class);

    public JSONObject convertToJSON() {

        JSONObject jsonBlock = new JSONObject();

        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        jsonBlock.put(JSONConstants.BLOCK_ID, this.getBlockId());
        jsonBlock.put(JSONConstants.MODIFIED_DATE, df.format(this.getModificationDate()));
        jsonBlock.put(JSONConstants.BLOCK_NAME, this.getBlockName());
        jsonBlock.put(JSONConstants.BLOCK_TYPE, this.getBlockType());
        jsonBlock.put(JSONConstants.FILE_ENTRY_ID, String.valueOf(this.getFileEntryId())); // Ids are managed as String in front
        if (this.getFileEntryId() != 0) {
            if (this.getBlockType() == CourseConstants.TYPE_RECORD) {
                jsonBlock.put(JSONConstants.LINK, "/c/document_library/get_file?fileEntryId=" + this.getFileEntryId());
            }
            try {
                FileEntry file = DLAppServiceUtil.getFileEntry(this.getFileEntryId());
                jsonBlock.put(JSONConstants.DOWNLOAD_URL, FileUtilsLocalServiceUtil.getDisplayUrl(file, file.getLatestFileVersion().getFileVersionId(), "", 0,false)); // Display URL with typeOfView = "" is download url
            } catch (Exception e) {
                logger.error(e);
            }
        }
        jsonBlock.put(JSONConstants.ORDER, this.getOrder());
        jsonBlock.put(JSONConstants.BLOCK_VALUE, this.getBlockValue());
        return jsonBlock;
    }
}