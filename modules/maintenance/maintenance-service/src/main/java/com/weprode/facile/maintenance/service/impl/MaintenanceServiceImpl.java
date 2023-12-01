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

package com.weprode.facile.maintenance.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.eel.synchronization.service.SynchronizationLocalServiceUtil;
import com.weprode.facile.maintenance.DataFeedUtil;
import com.weprode.facile.maintenance.FsManagement;
import com.weprode.facile.maintenance.OneShotTools;
import com.weprode.facile.maintenance.PermissionUtil;
import com.weprode.facile.maintenance.service.base.MaintenanceServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=maintenance",
		"json.web.service.context.path=Maintenance"
	},
	service = AopService.class
)
public class MaintenanceServiceImpl extends MaintenanceServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(MaintenanceServiceImpl.class);

	@JSONWebService(value = "start-synchro", method = "POST")
	public JSONObject startSynchro() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (synchro) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			SynchronizationLocalServiceUtil.runGVESynchronization(false);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running message migration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "start-parent-synchro", method = "POST")
	public JSONObject startParentSynchro() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (parent synchro) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			SynchronizationLocalServiceUtil.runGVEParentSynchronization(false);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running message migration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "start-fs-analysis", method = "POST")
	public JSONObject startFsAnalysis() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (fsAnalysis) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("Start exploring file system");
			FsManagement fsManagement = new FsManagement();
			fsManagement.exploreFileSystem();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "start-fs-analysis-v2", method = "POST")
	public JSONObject startFsAnalysisV2() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (fsAnalysisV2) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("Start exploring DB");
			FsManagement fsManagement = new FsManagement();
			fsManagement.exploreDB();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "run-anonymisation", method = "POST")
	public JSONObject runAnonymisation() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (runAnonymization) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Commented for safety
			//new AnonymizationUtil().anonymize();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "run-absence-notifications", method = "POST")
	public JSONObject runAbsenceNotifications() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (runAnonymization) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			SchoollifeSessionLocalServiceUtil.runAbsenceNotifications();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error running absence notifications", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "add-permissions", method = "POST")
	public JSONObject addPermissions() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (addPermissions) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			PermissionUtil.addPermissions();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error adding permissions", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "cleanup-obsolete-folders", method = "POST")
	public JSONObject cleanupObsoleteFolders() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (cleanupObsoleteFolders) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			new OneShotTools().cleanupObsoleteFolders();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error cleaning up dropboxes", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "run-data-feed", method = "POST")
	public JSONObject runDataFeed() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (runDataFeed) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			new DataFeedUtil().runDataFeed();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error cleaning up dropboxes", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "set-news-permissions", method = "POST")
	public JSONObject setNewsPermissions() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error("User " + user.getFullName() + " tries to run a maintenance tool (setNewsPermission) but has no permission");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			new OneShotTools().setNewsPermissions();
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error cleaning up dropboxes", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}


}