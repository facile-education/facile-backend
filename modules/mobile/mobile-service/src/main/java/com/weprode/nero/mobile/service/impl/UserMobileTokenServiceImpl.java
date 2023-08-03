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
import com.weprode.nero.mobile.service.UserMobileTokenLocalServiceUtil;
import com.weprode.nero.mobile.service.base.UserMobileTokenServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=mobile",
		"json.web.service.context.path=UserMobileToken"
	},
	service = AopService.class
)
public class UserMobileTokenServiceImpl extends UserMobileTokenServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(UserMobileTokenServiceImpl.class);

	@JSONWebService(value = "add-mobile-token", method = "GET")
	public JSONObject addMobileToken() {

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

		logger.info("addUserMobileToken for userId=" + user.getUserId());
		String refreshToken = UserMobileTokenLocalServiceUtil.addMobileToken(user.getUserId());
		result.put(JSONConstants.REFRESH_TOKEN, refreshToken);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "refresh-mobile-token", method = "GET")
	public JSONObject refreshMobileToken(String token) {

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

		logger.info("refreshUserMobileToken for userId=" + user.getUserId() + " and token " + token);
		String refreshToken = UserMobileTokenLocalServiceUtil.refreshMobileToken(user.getUserId(), token);
		result.put(JSONConstants.REFRESH_TOKEN, refreshToken);
		result.put(JSONConstants.SUCCESS, !refreshToken.equals(""));
		return result;
	}

}