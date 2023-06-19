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

import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public class ItemAttachedFileImpl extends ItemAttachedFileBaseImpl {

    public JSONObject convertToJSON() {
        JSONObject jsonAttachedFile = new JSONObject();

        jsonAttachedFile.put(JSONConstants.ATTACHED_FILE_ID, this.getItemAttachedFileId());
        jsonAttachedFile.put(JSONConstants.ITEM_ID, this.getProgressionItemId());
        jsonAttachedFile.put(JSONConstants.FILE_ENTRY_ID, this.getFileEntryId());
        jsonAttachedFile.put(JSONConstants.IS_AUDIO_RECORDING, this.getIsAudioRecording());

        return jsonAttachedFile;
    }
}