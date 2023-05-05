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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.access.AccessConstants;
import com.weprode.nero.access.model.Access;
import com.weprode.nero.access.model.AccessCategory;
import com.weprode.nero.access.model.AccessProfile;
import com.weprode.nero.access.service.AccessCategoryLocalServiceUtil;
import com.weprode.nero.access.service.AccessLocalServiceUtil;
import com.weprode.nero.access.service.AccessProfileLocalServiceUtil;
import com.weprode.nero.access.service.base.AccessLocalServiceBaseImpl;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.access.model.Access",
	service = AopService.class
)
public class AccessLocalServiceImpl extends AccessLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(AccessLocalServiceImpl.class);

	public JSONArray getSchoolAccesses (long schoolId) {
		JSONArray schoolAccesses = new JSONArray();
		List<AccessCategory> schoolCategories = AccessCategoryLocalServiceUtil.getSchoolCategories(schoolId);
		if (schoolCategories != null) {
			for (AccessCategory schoolCategory : schoolCategories) {
				JSONObject jsonCategory = AccessCategoryLocalServiceUtil.convertToJson(schoolCategory);

				// Loop over accesses
				List<Access> accesses = accessPersistence.findBycategoryId(schoolCategory.getCategoryId());
				JSONArray jsonAccesses = new JSONArray();
				if (accesses != null) {
					for (Access access : accesses) {
						JSONObject jsonAccess = convertToJson(access);

						// Loop over roles
						JSONArray jsonProfiles = new JSONArray();
						List<AccessProfile> accessProfiles = accessProfilePersistence.findByaccessId(access.getAccessId());
						if (accessProfiles != null) {
							for (AccessProfile accessProfile : accessProfiles) {
								jsonProfiles.put(accessProfile.getRoleId());
							}
						}
						jsonAccess.put(AccessConstants.PROFILES, jsonProfiles);
						jsonAccesses.put(jsonAccess);
					}
				}
				jsonCategory.put(AccessConstants.ACCESSES, jsonAccesses);
				schoolAccesses.put(jsonCategory);
			}
		}
		return schoolAccesses;
	}

	public boolean saveSchoolAccesses (long schoolId, String accesses) {

		try {
			// Delete existing categories / accesses / profiles
			AccessCategoryLocalServiceUtil.removeBySchoolId(schoolId);
			JSONArray schoolAccesses = new JSONArray(accesses);
			// Loop over categories
			for (int catIdx = 0 ; catIdx < schoolAccesses.length() ; catIdx++) {
				JSONObject jsonCategory = schoolAccesses.getJSONObject(catIdx);
				// Create category
				AccessCategory category = AccessCategoryLocalServiceUtil.addCategory(schoolId, jsonCategory.getString(AccessConstants.CATEGORY_NAME), jsonCategory.getInt(AccessConstants.POSITION));

				// Loop over accesses
				JSONArray jsonAccesses = jsonCategory.getJSONArray(AccessConstants.ACCESSES);
				for (int accessIdx = 0 ; accessIdx < jsonAccesses.length() ; accessIdx++) {
					JSONObject jsonAccess = jsonAccesses.getJSONObject(accessIdx);
					// Create access
					Access access = AccessLocalServiceUtil.addAccess(category.getCategoryId(),
							jsonAccess.getString(AccessConstants.TITLE),
							jsonAccess.getString(AccessConstants.URL),
							jsonAccess.getString(AccessConstants.THUMBNAIL),
							jsonAccess.getInt(AccessConstants.POSITION));
					// Create profiles
					JSONArray jsonProfiles = jsonAccess.getJSONArray(AccessConstants.PROFILES);
					for (int profileIdx = 0 ; profileIdx < jsonProfiles.length() ; profileIdx++) {
						long roleId = jsonProfiles.getLong(profileIdx);
						AccessProfileLocalServiceUtil.addAccessProfile(access.getAccessId(), roleId);
					}
				}
			}
			logger.info("Saved accesses for schoolId " + schoolId);

		} catch (Exception e) {
			logger.error("Error saving school accesses for schoolId " + schoolId, e);
		}
		return false;
	}

	public JSONArray getUserAccesses (User user) {
		JSONArray userAccesses = new JSONArray();

		List<Organization> schools = UserOrgsLocalServiceUtil.getUserSchools(user);
		int categoryPosition = 0;
		for (Organization school : schools) {
			List<AccessCategory> schoolCategories = AccessCategoryLocalServiceUtil.getSchoolCategories(school.getOrganizationId());
			for (AccessCategory schoolCategory : schoolCategories) {

				// Get accesses for user's roles
				List<Access> userCategoryAccesses = getUserCategoryAccesses(user.getUserId(), schoolCategory.getCategoryId());

				// Add category if there is at least 1 access
				if (!userCategoryAccesses.isEmpty()) {
					JSONObject jsonCategory = AccessCategoryLocalServiceUtil.convertToJson(schoolCategory);
					JSONArray jsonUserAccesses = new JSONArray();
					for (Access userCategoryAccess : userCategoryAccesses) {
						jsonUserAccesses.put(convertToJson(userCategoryAccess));
					}
					jsonCategory.put(AccessConstants.ACCESSES, jsonUserAccesses);
					// Override category position to manage multi-school
					jsonCategory.put(AccessConstants.POSITION, categoryPosition++);
					userAccesses.put(jsonCategory);
				}
			}
		}
		return userAccesses;
	}

	public JSONArray getRoleAccesses (long schoolId, long roleId) {
		JSONArray userAccesses = new JSONArray();

		List<AccessCategory> schoolCategories = AccessCategoryLocalServiceUtil.getSchoolCategories(schoolId);
		for (AccessCategory schoolCategory : schoolCategories) {

			// Get accesses for the given role
			List<Access> roleCategoryAccesses = getRoleCategoryAccesses(roleId, schoolCategory.getCategoryId());

			// Add category if there is at least 1 access
			if (!roleCategoryAccesses.isEmpty()) {
				JSONObject jsonCategory = AccessCategoryLocalServiceUtil.convertToJson(schoolCategory);
				JSONArray jsonUserAccesses = new JSONArray();
				for (Access userCategoryAccess : roleCategoryAccesses) {
					jsonUserAccesses.put(convertToJson(userCategoryAccess));
				}
				jsonCategory.put(AccessConstants.ACCESSES, jsonUserAccesses);
				userAccesses.put(jsonCategory);
			}
		}
		return userAccesses;
	}

	private List<Access> getUserCategoryAccesses(long userId, long categoryId) {
		List<Access> userCategoryAccesses = new ArrayList<>();
		List<Access> categoryAccesses = accessPersistence.findBycategoryId(categoryId);
		for (Access categoryAccess : categoryAccesses) {
			if (AccessProfileLocalServiceUtil.hasUserAccess(userId, categoryAccess.getAccessId())) {
				userCategoryAccesses.add(categoryAccess);
			}
		}
		return userCategoryAccesses;
	}

	private List<Access> getRoleCategoryAccesses(long roleId, long categoryId) {
		List<Access> userCategoryAccesses = new ArrayList<>();
		List<Access> categoryAccesses = accessPersistence.findBycategoryId(categoryId);
		for (Access categoryAccess : categoryAccesses) {
			if (AccessProfileLocalServiceUtil.hasRoleAccess(roleId, categoryAccess.getAccessId())) {
				userCategoryAccesses.add(categoryAccess);
			}
		}
		return userCategoryAccesses;
	}

	public JSONObject convertToJson(Access access) {
		JSONObject jsonAccess = new JSONObject();
		jsonAccess.put(AccessConstants.ACCESS_ID, access.getAccessId());
		jsonAccess.put(AccessConstants.TITLE, access.getTitle());
		jsonAccess.put(AccessConstants.URL, access.getUrl());
		jsonAccess.put(AccessConstants.THUMBNAIL, access.getThumbnail());
		jsonAccess.put(AccessConstants.POSITION, access.getPosition());
		return jsonAccess;
	}

	public Access addAccess(long categoryId, String title, String url, String thumbnail, int position) {
		Access access = accessPersistence.create(counterLocalService.increment());
		access.setCategoryId(categoryId);
		access.setTitle(title);
		access.setUrl(url);
		access.setThumbnail(thumbnail);
		access.setPosition(position);
		return accessPersistence.update(access);
	}

	public void removeByCategoryId(long categoryId) {
		// First remove all AccessProfiles
		List<Access> accesses = accessPersistence.findBycategoryId(categoryId);
		if (accesses != null) {
			for (Access access : accesses) {
				accessProfilePersistence.removeByaccessId(access.getAccessId());
			}
		}
		accessPersistence.removeBycategoryId(categoryId);
	}
}