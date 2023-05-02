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

package com.weprode.nero.maintenance.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.eel.synchronization.service.SynchronizationLocalServiceUtil;
import com.weprode.nero.maintenance.AnonymizationUtil;
import com.weprode.nero.maintenance.FsManagement;
import com.weprode.nero.maintenance.service.base.MaintenanceServiceBaseImpl;

import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
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

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				result.put("error", "NOT_ALLOWED_EXCEPTION");
				result.put("success", false);
				return result;
			}
		} catch (Exception e) {
			result.put("error", "NOT_ALLOWED_EXCEPTION");
			result.put("success", false);
			return result;
		}

		try {
			SynchronizationLocalServiceUtil.runGVESynchronization();
			result.put("success", true);

		} catch (Exception e) {
			logger.error("Error running message migration", e);
			result.put("success", false);
		}
		return result;
	}

	@JSONWebService(value = "start-parent-synchro", method = "POST")
	public JSONObject startParentSynchro() {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				result.put("error", "NOT_ALLOWED_EXCEPTION");
				result.put("success", false);
				return result;
			}
		} catch (Exception e) {
			result.put("error", "NOT_ALLOWED_EXCEPTION");
			result.put("success", false);
			return result;
		}

		try {
			SynchronizationLocalServiceUtil.runGVEParentSynchronization();
			result.put("success", true);

		} catch (Exception e) {
			logger.error("Error running message migration", e);
			result.put("success", false);
		}
		return result;
	}

	@JSONWebService(value = "start-fs-analysis", method = "POST")
	public JSONObject startFsAnalysis() {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				result.put("error", "NOT_ALLOWED_EXCEPTION");
				result.put("success", false);
				return result;
			}
		} catch (Exception e) {
			result.put("error", "NOT_ALLOWED_EXCEPTION");
			result.put("success", false);
			return result;
		}

		try {
			logger.info("Start exploring file system");
			FsManagement fsManagement = new FsManagement();
			fsManagement.exploreFileSystem();
			result.put("success", true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put("success", false);
		}
		return result;
	}

	@JSONWebService(value = "start-fs-analysis-v2", method = "POST")
	public JSONObject startFsAnalysisV2() {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				result.put("error", "NOT_ALLOWED_EXCEPTION");
				result.put("success", false);
				return result;
			}
		} catch (Exception e) {
			result.put("error", "NOT_ALLOWED_EXCEPTION");
			result.put("success", false);
			return result;
		}

		try {
			logger.info("Start exploring DB");
			FsManagement fsManagement = new FsManagement();
			fsManagement.exploreDB();
			result.put("success", true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put("success", false);
		}
		return result;
	}

	@JSONWebService(value = "run-anonymisation", method = "POST")
	public JSONObject runAnonymisation() {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				result.put("error", "NOT_ALLOWED_EXCEPTION");
				result.put("success", false);
				return result;
			}
		} catch (Exception e) {
			result.put("error", "NOT_ALLOWED_EXCEPTION");
			result.put("success", false);
			return result;
		}

		try {
			AnonymizationUtil.anonymize();
			result.put("success", true);

		} catch (Exception e) {
			logger.error("Error running file system exploration", e);
			result.put("success", false);
		}
		return result;
	}

}