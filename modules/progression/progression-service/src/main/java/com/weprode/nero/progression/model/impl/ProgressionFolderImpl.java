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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.weprode.nero.commons.constants.JSONConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class ProgressionFolderImpl extends ProgressionFolderBaseImpl {

    public JSONObject convertToJSON() {
        JSONObject jsonFolder = JSONFactoryUtil.createJSONObject();

        jsonFolder.put(JSONConstants.FOLDER_ID, this.getProgressionFolderId());
        jsonFolder.put(JSONConstants.PARENT_ID, this.getParentFolderId());
        jsonFolder.put(JSONConstants.PROGRESSION_ID, this.getProgressionId());
        jsonFolder.put(JSONConstants.NAME, this.getFolderName());
        jsonFolder.put(JSONConstants.ORDER, this.getOrder());

        return jsonFolder;
    }
}