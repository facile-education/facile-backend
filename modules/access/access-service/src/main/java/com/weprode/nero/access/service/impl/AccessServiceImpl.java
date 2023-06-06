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

package com.weprode.nero.access.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.access.service.AccessLocalServiceUtil;
import com.weprode.nero.access.service.base.AccessServiceBaseImpl;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = {
		"json.web.service.context.name=access",
		"json.web.service.context.path=Access"
	},
	service = AopService.class
)
public class AccessServiceImpl extends AccessServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(AccessServiceImpl.class);

	@JSONWebService(value = "get-school-accesses", method = "GET")
	public JSONObject getSchoolAccesses(long schoolId) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();

			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " fetches accesses for school " + schoolId);
			result.put("accesses", AccessLocalServiceUtil.getSchoolAccesses(schoolId));

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error fetching accesses for user " + user.getUserId(), e);
		}
		return result;
	}

	@JSONWebService(value = "save-school-accesses", method = "POST")
	public JSONObject saveSchoolAccesses(long schoolId, String accesses) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();

			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " saves accesses for school " + schoolId);
			AccessLocalServiceUtil.saveSchoolAccesses(user, schoolId, accesses);
			result.put(JSONConstants.SUCCESS, true);	// Only set to TRUE in case of success

		} catch (Exception e) {
			logger.error("Error saving school accesses for schoolId " + schoolId, e);
		}
		return result;
	}

	@JSONWebService(value = "get-user-accesses", method = "GET")
	public JSONObject getUserAccesses() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();

			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " fetches his accesses");
			result.put("accesses", AccessLocalServiceUtil.getUserAccesses(user));

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error fetching accesses for user " + user.getUserId(), e);
		}
		return result;
	}

	@JSONWebService(value = "get-role-accesses", method = "GET")
	public JSONObject getRoleAccesses(long schoolId, long roleId) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();

			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " fetches his accesses");
			result.put("accesses", AccessLocalServiceUtil.getRoleAccesses(schoolId, roleId));

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error fetching accesses for user " + user.getUserId(), e);
		}
		return result;
	}

}