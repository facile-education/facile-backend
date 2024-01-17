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

package com.weprode.facile.contact.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.contact.service.base.ContactServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=contact",
		"json.web.service.context.path=Contact"
	},
	service = AopService.class
)
public class ContactServiceImpl extends ContactServiceBaseImpl {

	@JSONWebService(value = "get-contact-tree", method = "GET")
	public JSONObject getContactTree() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONArray jsonCategories = ContactLocalServiceUtil.getContactTree(user);
		result.put(JSONConstants.CATEGORIES, jsonCategories);

		result.put(JSONConstants.SUCCESS, true);
		return result;
	}


	@JSONWebService(value = "get-org-members", method = "GET")
	public JSONObject getOrgMembers(long orgId, long roleId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		List<User> listMembers = ContactLocalServiceUtil.getListMembers(user, roleId, orgId);
		JSONArray jsonUsers = new JSONArray();
		for (User listMember : listMembers) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(listMember));
		}
		result.put(JSONConstants.USERS, jsonUsers);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "search-directory", method = "GET")
	public JSONObject searchDirectory(String query, long roleId, long schoolId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		List<Long> schoolIds = new ArrayList<>();
		if (schoolId != 0) {
			schoolIds.add(schoolId);
		}
		List<Long> roleIds = new ArrayList<>();
		if (roleId != 0) {
			roleIds.add(roleId);
		}
		List<User> directoryUsers = ContactLocalServiceUtil.directorySearch(user, query, schoolIds, roleIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		JSONArray jsonUsers = new JSONArray();
		for (User directoryUser : directoryUsers) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(directoryUser));
		}
		result.put(JSONConstants.USERS, jsonUsers);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-user-card", method = "GET")
	public JSONObject getUserCard(long contactUserId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONObject jsonContactDetails = ContactLocalServiceUtil.getUserCard(user, contactUserId);
		result.put("contactDetails", jsonContactDetails);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-my-students", method = "GET")
	public JSONObject getMyStudents() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		List<User> myStudents = ContactLocalServiceUtil.getMyStudents(user);
		JSONArray jsonUsers = new JSONArray();
		for (User student : myStudents) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(student));
		}
		result.put(JSONConstants.USERS, jsonUsers);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-my-relatives", method = "GET")
	public JSONObject getMyRelatives() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		List<User> myRelatives = ContactLocalServiceUtil.getMyRelatives(user);
		JSONArray jsonUsers = new JSONArray();
		for (User relative : myRelatives) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(relative));
		}
		result.put(JSONConstants.USERS, jsonUsers);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

}