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

package com.weprode.facile.mobile.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.mobile.service.UserMobileTokenLocalServiceUtil;
import com.weprode.facile.mobile.service.base.UserMobileTokenServiceBaseImpl;
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		logger.debug("addUserMobileToken for userId=" + user.getUserId());
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		logger.debug("refreshUserMobileToken for userId=" + user.getUserId() + " and token " + token);
		String refreshToken = UserMobileTokenLocalServiceUtil.refreshMobileToken(user.getUserId(), token);
		result.put(JSONConstants.REFRESH_TOKEN, refreshToken);
		result.put(JSONConstants.SUCCESS, !refreshToken.equals(""));
		return result;
	}

}