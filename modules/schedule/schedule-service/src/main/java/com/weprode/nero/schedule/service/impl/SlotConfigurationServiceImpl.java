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

package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.SlotConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.base.SlotConfigurationServiceBaseImpl;
import com.weprode.nero.schedule.utils.JSONProxy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=SlotConfiguration"
	},
	service = AopService.class
)
public class SlotConfigurationServiceImpl extends SlotConfigurationServiceBaseImpl {

	@JSONWebService(value = "get-school-slot-configuration", method = "GET")
	public JSONObject getSchoolSlotConfiguration(long schoolId) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for direction, school admins, ent admins and global admins only
		if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId) && !RoleUtilsLocalServiceUtil.isENTAdmin(user) && !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.CONFIGURATION, SlotConfigurationLocalServiceUtil.getSchoolSlotsAsJson(schoolId));
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "save-school-slot-configuration", method = "POST")
	public JSONObject saveSchoolSlotConfiguration(long schoolId, String jsonConfig) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for direction, school admins, ent admins and global admins only
		if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId) && !RoleUtilsLocalServiceUtil.isENTAdmin(user) && !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONArray jsonSlots = new JSONArray(jsonConfig);
		SlotConfigurationLocalServiceUtil.saveSchoolSlots(schoolId, jsonSlots);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}


}