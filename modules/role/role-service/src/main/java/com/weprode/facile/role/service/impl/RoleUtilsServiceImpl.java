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

package com.weprode.facile.role.service.impl;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsService;
import com.weprode.facile.role.service.base.RoleUtilsServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"json.web.service.context.name=role",
				"json.web.service.context.path=RoleUtils"
		},
		service = RoleUtilsService.class
)
public class RoleUtilsServiceImpl extends RoleUtilsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(RoleUtilsServiceImpl.class);

	@JSONWebService(value = "get-main-roles", method = "GET")
	public JSONObject getMainRoles() {
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
		if (!RoleUtilsLocalServiceUtil.isTeacher(user) &&
                !RoleUtilsLocalServiceUtil.isPersonal(user) &&
				!RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) &&
				!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			JSONArray roles = new JSONArray();
			for (Role role : RoleUtilsLocalServiceUtil.getUserSearchableRoles(user)) {
				JSONObject jsonRole = getRoleAsJSON(role, user);
				roles.put(jsonRole);
			}

			result.put(JSONConstants.ROLES, roles);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while fetching schools for manual user creation", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		
		return result;
	}

	@JSONWebService(value = "get-local-user-roles", method = "GET")
	public JSONObject getLocalUserRoles() {
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
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
				!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
				!RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
				!RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			JSONArray localUsersRoles = new JSONArray();

			for (Role role : RoleUtilsLocalServiceUtil.getAvailableRolesForLocalUser()) {
				JSONObject jsonRole = getRoleAsJSON(role, user);
				localUsersRoles.put(jsonRole);
			}
			result.put(JSONConstants.ROLES, localUsersRoles);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error while fetching schools for manual user creation", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	// Used in app manager (includes admin roles and if role can be apply to a class org)
	@JSONWebService(value = "get-broadcast-roles", method = "GET")
	public JSONObject getBroadcastRoles() {
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
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
				!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
				!RoleUtilsLocalServiceUtil.isSchoolAdmin(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			JSONArray roleList = new JSONArray();

			for (Role role : RoleUtilsLocalServiceUtil.getUserSearchableRoles(user)) {
				final JSONObject curr = new JSONObject();
				curr.put(JSONConstants.ROLE_ID, role.getRoleId());
				curr.put(JSONConstants.DISPLAY_TEXT, role.getTitle(user.getLocale()));
				curr.put(JSONConstants.IS_FOR_CLASS, RoleUtilsLocalServiceUtil.isForClass(role));
				roleList.put(curr);
			}

			Role schoolAdmin = RoleUtilsLocalServiceUtil.getSchoolAdminRole();
			JSONObject schoolAdminRole = new JSONObject();
			schoolAdminRole.put(JSONConstants.ROLE_ID, schoolAdmin.getRoleId());
			schoolAdminRole.put(JSONConstants.DISPLAY_TEXT, "Administrateur local");
			schoolAdminRole.put(JSONConstants.IS_FOR_CLASS, false);
			roleList.put(schoolAdminRole);

			Role collectivityAdminRole = RoleUtilsLocalServiceUtil.getCollectivityAdminRole();
			JSONObject entAdminRole = new JSONObject();
			entAdminRole.put(JSONConstants.ROLE_ID, collectivityAdminRole.getRoleId());
			entAdminRole.put(JSONConstants.DISPLAY_TEXT, "Responsable de collectivité");
			entAdminRole.put(JSONConstants.IS_FOR_CLASS, false);
			roleList.put(entAdminRole);
			
			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.ROLES, roleList);
		} catch (Exception e) {
			logger.error("Error fetching schools", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	private JSONObject getRoleAsJSON(Role role, User user) {
		JSONObject jsonRole = new JSONObject();

		jsonRole.put(JSONConstants.ROLE_ID, role.getRoleId());
		jsonRole.put(JSONConstants.ROLE_CODE, role.getName());
		jsonRole.put(JSONConstants.LABEL, role.getTitle(user.getLocale()));

		return jsonRole;
	}
}
