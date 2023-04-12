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

package com.weprode.nero.document.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.TrashServiceBaseImpl;

import com.weprode.nero.document.utils.DocumentUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Trash"
	},
	service = AopService.class
)
public class TrashServiceImpl extends TrashServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(TrashServiceImpl.class);

	@JSONWebService(method = "POST")
	public JSONObject deleteDocuments(String folderIdArray, String fileIdArray) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		// Extract fileIds and folderIds as lists of Long
		List<Long> fileIds = DocumentUtil.extractLongIds(fileIdArray);
		List<Long> folderIds = DocumentUtil.extractLongIds(folderIdArray);

		JSONArray failedEntitiesList = JSONFactoryUtil.createJSONArray();

		try {
			User user = getGuestOrUser();
			logger.info("User " + user.getFullName() + " deletes folders " + folderIdArray + " and/or files " + fileIdArray);

			for (long folderId : folderIds) {
				try {
					FolderUtilsLocalServiceUtil.deleteFolder(user.getUserId(), folderId);
				} catch (Exception e) {
					JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
					failedEntity.put(JSONConstants.ID, folderId);
					failedEntitiesList.put(failedEntity);
					logger.error(e);
				}
			}
			for (final long fileEntityId : fileIds) {
				try {
					FileUtilsLocalServiceUtil.deleteFile(user.getUserId(), fileEntityId);
				} catch (Exception e) {
					JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
					failedEntity.put(JSONConstants.ID, fileEntityId);
					failedEntitiesList.put(failedEntity);
					logger.error(e);
				}
			}

			result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}