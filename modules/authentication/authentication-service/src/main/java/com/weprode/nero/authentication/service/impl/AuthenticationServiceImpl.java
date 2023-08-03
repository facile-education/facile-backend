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

package com.weprode.nero.authentication.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.authentication.service.base.AuthenticationServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=authentication",
		"json.web.service.context.path=Authentication"
	},
	service = AopService.class
)
public class AuthenticationServiceImpl extends AuthenticationServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(AuthenticationServiceImpl.class);

	// This is a public webservice
	@JSONWebService(value = "check-credentials", method = "POST")
	public JSONObject checkCredentials(String login, String password) {
		JSONObject result = new JSONObject();

		logger.info("checkCredentials for login " + login);
		result.put(JSONConstants.IS_VALID, false);
		try {
			boolean isValid = false;
			boolean isActive = false;
			// First get user from login
			User user = null;
			try {
				user = UserLocalServiceUtil.getUserByScreenName(PortalUtil.getDefaultCompanyId(), login);
			} catch (Exception e) {
				logger.info("Attempt to login with invalid screenName " + login);
			}

			if (user != null) {
				try {
					isValid = PasswordTrackerLocalServiceUtil.isSameAsCurrentPassword(user.getUserId(), password);
					isActive = user.isActive();
					result.put(JSONConstants.USER_ID, user.getUserId());
				} catch (Exception e) {
					logger.info("Error when comparing password with current one", e);
				}
			}
			result.put(JSONConstants.IS_VALID, isValid);
			result.put(JSONConstants.IS_ACTIVE, isActive);

		} catch (SystemException e) {
			logger.error("Error checking login/password pair", e);
		}

		return result;
	}

}