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

package com.weprode.facile.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.SlotConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.base.SlotConfigurationServiceBaseImpl;
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
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