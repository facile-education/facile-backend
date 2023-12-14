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

package com.weprode.facile.access.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.access.AccessConstants;
import com.weprode.facile.access.service.AccessCategoryLocalServiceUtil;
import com.weprode.facile.access.service.AccessLocalServiceUtil;
import com.weprode.facile.access.service.base.AccessServiceBaseImpl;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
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

			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " get accesses for school " + schoolId);
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

	@JSONWebService(value = "save-school-category", method = "POST")
	public JSONObject saveSchoolCategory(long schoolId, String category) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves category for school " + schoolId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " save category for school " + schoolId);
			JSONObject jsonCategory = new JSONObject(category);
			if (jsonCategory.has(AccessConstants.CATEGORY_ID)) {
				AccessCategoryLocalServiceUtil.updateCategory(jsonCategory.getLong(AccessConstants.CATEGORY_ID), jsonCategory.getString(AccessConstants.CATEGORY_NAME));
			} else {
				AccessCategoryLocalServiceUtil.addCategory(
						schoolId,
						jsonCategory.getString(AccessConstants.CATEGORY_NAME),
						jsonCategory.getInt(AccessConstants.POSITION));
			}
			result.put(JSONConstants.SUCCESS, true);	// Only set to TRUE in case of success

		} catch (Exception e) {
			logger.error("Error saving school accesses for schoolId " + schoolId, e);
		}
		return result;
	}

	@JSONWebService(value = "save-school-access", method = "POST")
	public JSONObject saveSchoolAccess(long schoolId, String access) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves access for school " + schoolId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getUserId() + " save access for school " + schoolId);
			JSONObject jsonAccess = new JSONObject(access);
			if (jsonAccess.has(AccessConstants.ACCESS_ID)) {
				AccessLocalServiceUtil.updateAccess(user.getUserId(), jsonAccess);
			} else {
				AccessLocalServiceUtil.addAccess(
					user.getUserId(),
					jsonAccess.getLong(AccessConstants.CATEGORY_ID),
					jsonAccess.getString(AccessConstants.TITLE),
					jsonAccess.getInt(AccessConstants.TYPE),
					jsonAccess.getString(AccessConstants.URL),
					jsonAccess.getLong(AccessConstants.FOLDER_ID),
					jsonAccess.getLong(AccessConstants.FILE_ID),
					jsonAccess.getLong(AccessConstants.THUMBNAIL_ID),
					jsonAccess.getInt(AccessConstants.POSITION),
					jsonAccess.getJSONArray(AccessConstants.PROFILES)
				);
			}
			result.put(JSONConstants.SUCCESS, true);	// Only set to TRUE in case of success

		} catch (Exception e) {
			logger.error("Error saving school access for schoolId " + schoolId, e);
		}

		return result;
	}

	@JSONWebService(value = "remove-school-access", method = "DELETE")
	public JSONObject removeSchoolAccess(long schoolId, long accessId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " removes access for school " + schoolId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			AccessLocalServiceUtil.removeAccess(AccessLocalServiceUtil.getAccess(accessId));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while removing accessId " + accessId, e);
		}

		return result;
	}

	@JSONWebService(value = "remove-school-category", method = "DELETE")
	public JSONObject removeSchoolCategory(long schoolId, long categoryId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " removes category for school " + schoolId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			AccessCategoryLocalServiceUtil.removeCategory(categoryId, schoolId);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while removing access categoryId " + categoryId, e);
		}

		return result;
	}

	@JSONWebService(value = "get-user-accesses", method = "GET")
	public JSONObject getUserAccesses() {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
					!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
					!RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " fetches accesses for school " + schoolId + " and role " + roleId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			result.put("accesses", AccessLocalServiceUtil.getRoleAccesses(schoolId, roleId));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error fetching accesses for user " + user.getUserId(), e);
		}
		return result;
	}

}