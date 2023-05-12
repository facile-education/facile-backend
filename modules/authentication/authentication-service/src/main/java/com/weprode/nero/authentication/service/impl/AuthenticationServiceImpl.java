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
import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.authentication.service.base.AuthenticationServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	@JSONWebService(value = "check-credentials", method = "GET")
	public JSONObject checkCredentials(String login, String password) {
		JSONObject result = new JSONObject();

		result.put("isValid", false);
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
				} catch (Exception e) {
					logger.info("Error when comparing password with current one", e);
				}
			}
			result.put("isValid", isValid);
			result.put("isActive", isActive);

		} catch (SystemException e) {
			logger.error("Error checking login/password pair", e);
		}
		return result;
	}

	@JSONWebService(value = "get-session-validity", method = "GET")
	public JSONObject getSessionValidity() {

		JSONObject result = new JSONObject();
		result.put(JSONConstants.SUCCESS, false);
		result.put("isSessionExpired", false);
		result.put("isSessionWarning", false);

		try {
			User user = getGuestOrUser();
			Class<?> liveUsers = PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.liveusers.LiveUsers");
			Method getSessionUsers = liveUsers.getDeclaredMethod("getSessionUsers", long.class);
			Object map = getSessionUsers.invoke(null, user.getCompanyId());
			Map<String, UserTracker> sessionUsers = (ConcurrentHashMap<String, UserTracker>)map;
			System.out.println("sessionUsers = " + sessionUsers);
			for (Map.Entry<String, UserTracker> sessionUser : sessionUsers.entrySet()) {
				UserTracker userTracker = sessionUser.getValue();
				if (userTracker.getUserId() == user.getUserId()) {
					HttpSession session = PortalSessionContext.get(userTracker.getSessionId());
					long nbSeconds = System.currentTimeMillis() - session.getLastAccessedTime();
					logger.info("Nb milli-seconds = " + nbSeconds);
					if (nbSeconds > session.getMaxInactiveInterval() * 1000L) {
						result.put("isSessionExpired", false);
						result.put("isSessionWarning", true);
						result.put(JSONConstants.SUCCESS, true);
						return result;
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error checking user session validity", e);
		}
		return result;
	}

}