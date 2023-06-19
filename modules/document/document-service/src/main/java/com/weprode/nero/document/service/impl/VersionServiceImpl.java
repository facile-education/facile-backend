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


import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.VersionLocalServiceUtil;
import com.weprode.nero.document.service.base.VersionServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Version"
	},
	service = AopService.class
)
public class VersionServiceImpl extends VersionServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(VersionServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject getFileVersions(long fileId) {
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
			logger.info("User " + user.getFullName() + " fetches versions for file " + fileId);

			return VersionLocalServiceUtil.getFileVersions(user, fileId);
		} catch (Exception e) {
			logger.error("Error fetching versions for file " + fileId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject deleteVersion(long fileEntryId, String version) {
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
			logger.info("User " + user.getFullName() + " deletes version " + version + " for file " + fileEntryId);
			result.put(JSONConstants.SUCCESS, VersionLocalServiceUtil.deleteVersion(user, fileEntryId, version));
		} catch (Exception e) {
			logger.error("Error deleting version " + version + " for file " + fileEntryId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject restoreVersion(long fileVersionId) {
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
			logger.info("User " + user.getFullName() + " restores version " + fileVersionId);
			result.put(JSONConstants.SUCCESS, VersionLocalServiceUtil.restoreVersion(user.getUserId(), fileVersionId));
		} catch (Exception e) {
			logger.error("Error restoring version " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject saveVersionDescription(long fileVersionId, String description) {
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
			logger.info("User " + user.getFullName() + " saves description for version " + fileVersionId);
			result.put(JSONConstants.SUCCESS, VersionLocalServiceUtil.saveVersionDescription(fileVersionId, description));
		} catch (Exception e) {
			logger.error("Error saving description for version " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject createMajorVersion(long fileEntryId) {
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
			logger.info("User " + user.getFullName() + " creates major version for file " + fileEntryId);
			result.put(JSONConstants.SUCCESS, VersionLocalServiceUtil.createMajorVersion(user, fileEntryId));
		} catch (Exception e) {
			logger.error("Error creating major version for file " + fileEntryId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}