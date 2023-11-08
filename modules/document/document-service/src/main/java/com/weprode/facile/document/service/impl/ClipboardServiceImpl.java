/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.document.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.base.ClipboardServiceBaseImpl;
import com.weprode.facile.document.utils.ClipboardUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Clipboard"
	},
	service = AopService.class
)
public class ClipboardServiceImpl extends ClipboardServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ClipboardServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject copy(String folderIds, String fileIds, long targetFolderId, int mode) {
		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			long userId = getGuestOrUserId();

			// Permissions are checker deeper
			logger.info("User " + userId + " copies files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
			return ClipboardUtil.copy(userId, targetFolderId, folderIds, fileIds, mode);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject move (String folderIds, String fileIds, long targetFolderId, int mode) {
		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			long userId = getGuestOrUserId();

			// Permissions are checker deeper
			logger.info("User " + userId + " cuts files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
			return ClipboardUtil.move(userId, targetFolderId, folderIds, fileIds, mode);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

}