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

package com.weprode.nero.mobile.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.mobile.model.MobileDevice;
import com.weprode.nero.mobile.service.MobileDeviceLocalServiceUtil;
import com.weprode.nero.mobile.service.base.MobileDeviceServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=mobile",
		"json.web.service.context.path=MobileDevice"
	},
	service = AopService.class
)
public class MobileDeviceServiceImpl extends MobileDeviceServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(MobileDeviceServiceImpl.class);

	@JSONWebService(value = "save-user-device", method = "GET")
	public JSONObject saveUserDevice(String deviceId, long userId) {

		JSONObject result = new JSONObject();

		logger.info("saveUserDevice with userId=" + userId + " and deviceId=" + deviceId);
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Parameter cannot be '0'
		if (userId == 0) {
			result.put("success", false);
			result.put("exception", "The list of ENTPersonJointure can't be 0");
			return result;
		}

		MobileDevice mobileDevice = MobileDeviceLocalServiceUtil.getMobileDeviceByManufacturerDeviceId(deviceId);

		if (mobileDevice != null) {
			// Update device
			if (mobileDevice.getUserId() != userId) {
				mobileDevice.setUserId(userId);
				MobileDeviceLocalServiceUtil.updateMobileDevice(mobileDevice);
				result.put("success", true);
			}
		} else {
			// Create device
			try {
				MobileDeviceLocalServiceUtil.addMobileDevice(deviceId, userId, "unknown", "unknown", "unknown", "unknown", "unknown");
				logger.info("New device "+deviceId+" saved for userId = " + userId);
				result.put("success", true);
			} catch (Exception e) {
				logger.error("Failed to create mobile device with id = " + deviceId + " for userId = " + userId, e);
				result.put("success", false);
				result.put("exception", "Failed to create mobile device for user " + userId);
			}
		}

		return result;
	}


	@JSONWebService(value = "save-full-user-device", method = "GET")
	public JSONObject saveFullUserDevice(String deviceId, long userId, String model, String manufacturer, String os, String osVersion, String browserUA) {

		JSONObject result = new JSONObject();
		logger.info("saveFullUserDevice with userId=" + userId + " and deviceId=" + deviceId);

		// Parameter cannot be '0'
		if (userId == 0) {
			result.put("success", false);
			result.put("exception", "The list of ENTPersonJointure can't be 0");
			return result;
		}

		MobileDevice mobileDevice = MobileDeviceLocalServiceUtil.getMobileDeviceByManufacturerDeviceId(deviceId);

		if (browserUA.length() > 500) {
			logger.error("Browser agent length is too heavy for deviceID " + deviceId + ", so we have to truncate it.\n Original value : " + browserUA);
			browserUA = browserUA.substring(0, 499);
		}

		if (mobileDevice != null) {
			// Update device
			if (mobileDevice.getUserId() != userId ||
					!mobileDevice.getOperatingSystemVersion().equals(osVersion) ||
					!mobileDevice.getBrowserUA().equals(browserUA)) {
				mobileDevice.setUserId(userId);
				mobileDevice.setOperatingSystemVersion(osVersion);
				mobileDevice.setBrowserUA(browserUA);
				MobileDeviceLocalServiceUtil.updateMobileDevice(mobileDevice);
			}
		} else {
			// Create device
			try {
				MobileDeviceLocalServiceUtil.addMobileDevice(deviceId, userId, model, manufacturer, os, osVersion, browserUA);
				logger.info("New device "+deviceId+" saved for userId = " + userId+" with model="+model+", manufacturer="+manufacturer+", os="+os+", osVersion="+osVersion+", browserUA="+browserUA);
				result.put("success", true);
			} catch (Exception e) {
				logger.error("Failed to create mobile device with id = " + deviceId + " for userId = " + userId, e);
				result.put("success", false);
				result.put("exception", "Failed to create mobile device for user " + userId);
			}
		}

		return result;
	}

	@JSONWebService(value = "delete-user-device", method = "DEL")
	public JSONObject deleteUserDevice(String deviceId) {
		JSONObject result = new JSONObject();

		logger.info("deleteUserDevice with manufacturerDeviceId="+deviceId);
		try {
			MobileDevice mobileDevice = MobileDeviceLocalServiceUtil.getMobileDeviceByManufacturerDeviceId(deviceId);
			MobileDeviceLocalServiceUtil.deleteMobileDevice(mobileDevice);
			result.put("success", true);
		} catch (Exception e) {
			logger.error("Failed to remove mobile device with manufacturer id = " + deviceId);
			result.put("success", false);
			result.put("exception", "Cannot delete device " + deviceId);
		}

		return result;
	}

}