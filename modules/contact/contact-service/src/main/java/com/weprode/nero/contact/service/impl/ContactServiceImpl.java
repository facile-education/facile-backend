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

package com.weprode.nero.contact.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.contact.ContactConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.contact.service.base.ContactServiceBaseImpl;

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

	private static final Log logger = LogFactoryUtil.getLog(ContactServiceImpl.class);

	@JSONWebService(value = "get-contact-tree", method = "GET")
	public JSONObject getContactTree() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " fetches its contact tree");
		JSONArray jsonCategories = ContactLocalServiceUtil.getContactTree(user);
		result.put(ContactConstants.CATEGORIES, jsonCategories);

		result.put("success", true);
		return result;
	}


	@JSONWebService(value = "get-org-members", method = "GET")
	public JSONObject getOrgMembers(long orgId, long roleId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " gets members of org " + orgId + " with roleId " + roleId);
		List<User> listMembers = ContactLocalServiceUtil.getListMembers(user, roleId, orgId);
		JSONArray jsonUsers = new JSONArray();
		for (User listMember : listMembers) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(listMember));
		}
		result.put("users", jsonUsers);
		result.put("success", true);
		return result;
	}

	@JSONWebService(value = "search-directory", method = "GET")
	public JSONObject searchDirectory(String query, long roleId, long schoolId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " searches directory with query '" + query + "', roleId " + roleId + " and schoolId " + schoolId);
		List<Long> schoolIds = new ArrayList<>();
		if (schoolId != 0) {
			schoolIds.add(schoolId);
		}
		List<Long> roleIds = new ArrayList<>();
		if (roleId != 0) {
			roleIds.add(roleId);
		}
		List<User> directoryUsers = ContactLocalServiceUtil.directorySearch(user, query, null, schoolIds, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		JSONArray jsonUsers = new JSONArray();
		for (User directoryUser : directoryUsers) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(directoryUser));
		}
		result.put("users", jsonUsers);
		result.put("success", true);
		return result;
	}

	@JSONWebService(value = "get-contact-details", method = "GET")
	public JSONObject getContactDetails(long contactUserId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " gets details of contact " + contactUserId);
		// TODO re-import getContactDetails from lr61 project
		JSONObject jsonContactDetails = new JSONObject();
		// JSONObject jsonContactDetails = ContactLocalServiceUtil.getContactDetails(user, contactUserId);
		result.put("contactInfos", jsonContactDetails);
		result.put("success", true);
		return result;
	}

	@JSONWebService(value = "get-my-students", method = "GET")
	public JSONObject getMyStudents() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " gets his students");
		List<User> myStudents = ContactLocalServiceUtil.getMyStudents(user);
		JSONArray jsonUsers = new JSONArray();
		for (User student : myStudents) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(student));
		}
		result.put("users", jsonUsers);
		result.put("success", true);
		return result;
	}

	@JSONWebService(value = "get-my-relatives", method = "GET")
	public JSONObject getMyRelatives() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				result.put("success",false);
				result.put("error", "AUTH_EXCEPTION");
				return result;
			}
		} catch (Exception e) {
			result.put("success",false);
			result.put("error", "AUTH_EXCEPTION");
			return result;
		}

		logger.info("User " + user.getUserId() + " gets his relatives");
		List<User> myRelatives = ContactLocalServiceUtil.getMyRelatives(user);
		JSONArray jsonUsers = new JSONArray();
		for (User relative : myRelatives) {
			jsonUsers.put(ContactLocalServiceUtil.convertUserToJson(relative));
		}
		result.put("users", jsonUsers);
		result.put("success", true);
		return result;
	}

}