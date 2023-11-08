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
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.weprode.facile.access.model.AccessProfile;
import com.weprode.facile.access.service.base.AccessProfileLocalServiceBaseImpl;
import com.weprode.facile.access.service.persistence.AccessProfilePK;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.access.model.AccessProfile",
	service = AopService.class
)
public class AccessProfileLocalServiceImpl extends AccessProfileLocalServiceBaseImpl {

	public AccessProfile addAccessProfile(long accessId, long roleId) {
		AccessProfile accessProfile = accessProfilePersistence.create(new AccessProfilePK(accessId, roleId));
		return accessProfilePersistence.update(accessProfile);
	}

	public JSONArray getAccessProfiles(long accessId) {
		JSONArray jsonAccessProfiles = new JSONArray();
		List<AccessProfile> accessProfiles = accessProfilePersistence.findByaccessId(accessId);
		if (accessProfiles != null) {
			for (AccessProfile accessProfile : accessProfiles) {
				JSONObject jsonProfile = new JSONObject();
				jsonProfile.put("accessId", accessProfile.getAccessId());
				jsonProfile.put("roleId", accessProfile.getRoleId());
				jsonAccessProfiles.put(jsonProfile);
			}
		}
		return jsonAccessProfiles;
	}

	public boolean hasUserAccess(long userId, long accessId) {

		List<AccessProfile> accessProfiles = accessProfilePersistence.findByaccessId(accessId);
		for (AccessProfile accessProfile : accessProfiles) {
			if (RoleLocalServiceUtil.hasUserRole(userId, accessProfile.getRoleId())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRoleAccess (long roleId, long accessId) {
		List<AccessProfile> accessProfiles = accessProfilePersistence.findByaccessId(accessId);
		for (AccessProfile accessProfile : accessProfiles) {
			if (accessProfile.getRoleId() == roleId) {
				return true;
			}
		}
		return false;
	}

	public void removeByAccessId(long accessId) {
		accessProfilePersistence.removeByaccessId(accessId);
	}
}