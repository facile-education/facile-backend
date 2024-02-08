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

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.weprode.facile.access.AccessConstants;
import com.weprode.facile.access.model.Access;
import com.weprode.facile.access.model.AccessCategory;
import com.weprode.facile.access.model.AccessProfile;
import com.weprode.facile.access.service.AccessCategoryLocalServiceUtil;
import com.weprode.facile.access.service.AccessLocalServiceUtil;
import com.weprode.facile.access.service.AccessProfileLocalServiceUtil;
import com.weprode.facile.access.service.base.AccessLocalServiceBaseImpl;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.ThumbnailsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.access.model.Access",
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
						if (jsonAccess != null) {
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
				}
				jsonCategory.put(AccessConstants.ACCESSES, jsonAccesses);
				schoolAccesses.put(jsonCategory);
			}
		}
		return schoolAccesses;
	}

	public void removeAccess(Access access) {
		updatePositionsOnRemove(access);
		AccessLocalServiceUtil.deleteAccess(access);
	}

	private void updatePositionsOnRemove(Access access) {
		List<Access> accesses = accessPersistence.findBycategoryId(access.getCategoryId());

		for (Access categoryAccess : accesses) {
			if (access.getAccessId() == categoryAccess.getAccessId()) continue;

			if (categoryAccess.getPosition() > access.getPosition()) {
				// Access has been move to another folder -> so move up all the following folders
				categoryAccess.setPosition(categoryAccess.getPosition() - 1);
				AccessLocalServiceUtil.updateAccess(categoryAccess);
			}
		}
	}

	public Access updateAccess(long userId, JSONObject jsonAccess) throws PortalException {
		// Get access and update it
		Access access = AccessLocalServiceUtil.getAccess(jsonAccess.getLong(AccessConstants.ACCESS_ID));

		int newPosition = jsonAccess.getInt(AccessConstants.POSITION);
		long categoryId = jsonAccess.getLong(AccessConstants.CATEGORY_ID);
		boolean hasCategoryChanged = (access.getCategoryId() != categoryId);

		// If moved to another category then update previous category positions
		if (hasCategoryChanged) {
			updatePositionsOnRemove(access);
			access.setCategoryId(categoryId);
		}

		// Access has been moved up or down in
		if (access.getPosition() != newPosition || hasCategoryChanged) {

			for (Access categoryAccess : accessPersistence.findBycategoryId(categoryId)) {
				if (access.getAccessId() == categoryAccess.getAccessId()) continue;

				if (categoryAccess.getPosition() >= newPosition && categoryAccess.getPosition() < access.getPosition()
						|| (hasCategoryChanged && categoryAccess.getPosition() >= newPosition)) {
					// Access has been move up so move down the following access until old position
					categoryAccess.setPosition(categoryAccess.getPosition() + 1);
					AccessLocalServiceUtil.updateAccess(categoryAccess);
				} else if (categoryAccess.getPosition() <= newPosition && categoryAccess.getPosition() > access.getPosition()) {
					// Access has been move down so move up the previous access until old position
					categoryAccess.setPosition(categoryAccess.getPosition() - 1);
					AccessLocalServiceUtil.updateAccess(categoryAccess);
				}
			}

			access.setPosition(newPosition);
		}

		access.setTitle(jsonAccess.getString(AccessConstants.TITLE));
		access.setType(jsonAccess.getInt(AccessConstants.TYPE));
		access.setExternalUrl(jsonAccess.getString(AccessConstants.URL));
		access.setFolderId(jsonAccess.getLong(AccessConstants.FOLDER_ID));
		access.setFileId(jsonAccess.getLong(AccessConstants.FILE_ID));

		long thumbnailId = jsonAccess.getLong(AccessConstants.THUMBNAIL_ID);

		// Copy thumbnail file to thumbnail folder
		if (access.getThumbnailId() != thumbnailId && thumbnailId > 0) {
			try {
				FileEntry thumbnail = ThumbnailsLocalServiceUtil.createThumbnailFile(userId, thumbnailId, String.valueOf(access.getAccessId()));
				access.setThumbnailId(thumbnail.getFileEntryId());
			} catch (Exception e) {
				access.setThumbnailId(0L);
				logger.error("Cannot create thumbnail file from fileId " + thumbnailId + " for access id " + access.getAccessId(), e);
			}
		} else if (thumbnailId <= 0){
			access.setThumbnailId(thumbnailId);	// Negative numbers (including 0) are used for front default images
		}

		// Re-create profiles
		AccessProfileLocalServiceUtil.removeByAccessId(access.getAccessId());

		JSONArray jsonProfiles = jsonAccess.getJSONArray(AccessConstants.PROFILES);
		for (int profileIdx = 0 ; profileIdx < jsonProfiles.length() ; profileIdx++) {
			JSONObject jsonProfile = jsonProfiles.getJSONObject(profileIdx);
			long roleId = jsonProfile.getLong(JSONConstants.ROLE_ID);
			AccessProfileLocalServiceUtil.addAccessProfile(access.getAccessId(), roleId);
		}

		access = AccessLocalServiceUtil.updateAccess(access);

		logger.info("Updated access with id " + access.getAccessId());

		return access;
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
						JSONObject jsonAccess = convertToJson(userCategoryAccess);
						if (jsonAccess != null) {
							jsonUserAccesses.put(jsonAccess);
						}
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
					JSONObject jsonAccess = convertToJson(userCategoryAccess);
					if (jsonAccess != null) {
						jsonUserAccesses.put(jsonAccess);
					}
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
			if (AccessProfileLocalServiceUtil.hasUserAccess(userId, categoryAccess.getAccessId())) { // && hasUserViewPermission(userId, categoryAccess)) {
				userCategoryAccesses.add(categoryAccess);
			}
		}
		return userCategoryAccesses;
	}

	private List<Access> getRoleCategoryAccesses(long roleId, long categoryId) {
		List<Access> userCategoryAccesses = new ArrayList<>();
		List<Access> categoryAccesses = accessPersistence.findBycategoryId(categoryId);
		for (Access categoryAccess : categoryAccesses) {
			if (AccessProfileLocalServiceUtil.hasRoleAccess(roleId, categoryAccess.getAccessId())) { // && hasRoleViewPermission(roleId, categoryAccess)) {
				userCategoryAccesses.add(categoryAccess);
			}
		}
		return userCategoryAccesses;
	}

	private boolean hasUserViewPermission(long userId, Access access) {
		// Use DLAppServiceUtil to use current user permissions
		try {
			if (access.getFileId() != 0) {
				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(access.getFileId());
				return PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW);
			} else if (access.getFolderId() != 0) {
				Folder folder = DLAppServiceUtil.getFolder(access.getFolderId());
				return PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW);
			}
		} catch (Exception e) {
			logger.info("Skipping access " + access.getAccessId() + " due to permissions");
			return false;
		}
		return true;
	}

	private boolean hasRoleViewPermission(long roleId, Access access) {
		// Use DLAppLocalServiceUtil to get rid of current user's permissions
		try {
			Role role = RoleLocalServiceUtil.getRole(roleId);
			if (access.getFileId() != 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(access.getFileId());
				return PermissionUtilsLocalServiceUtil.hasRoleFilePermission(role, fileEntry, ActionKeys.VIEW);
			} else if (access.getFolderId() != 0) {
				Folder folder = DLAppLocalServiceUtil.getFolder(access.getFolderId());
				return PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, ActionKeys.VIEW);
			}
		} catch (Exception e) {
			logger.info("Skipping access " + access.getAccessId() + " due to permissions");
			return false;
		}
		return true;
	}

	public JSONObject convertToJson(Access access) {
		JSONObject jsonAccess = new JSONObject();
		jsonAccess.put(AccessConstants.ACCESS_ID, access.getAccessId());
		jsonAccess.put(AccessConstants.TITLE, access.getTitle());
		jsonAccess.put(AccessConstants.TYPE, access.getType());
		jsonAccess.put(AccessConstants.URL, access.getExternalUrl());
		jsonAccess.put(AccessConstants.FOLDER_ID, access.getFolderId());
		if (access.getFolderId() > 0) {
			try {
				jsonAccess.put(AccessConstants.FOLDER_NAME, DLAppServiceUtil.getFolder(access.getFolderId()).getName());
			} catch (Exception e) {
				// Folder is not accessible anymore -> return null to skip the access
				logger.error("Folder " + access.getFolderId() + " may have been deleted -> skipping this access");
				return null;
			}
		}
		jsonAccess.put(AccessConstants.FILE_ID, access.getFileId());
		if (access.getFileId() > 0) {
			try {
				jsonAccess.put(AccessConstants.FILE_NAME, DLAppServiceUtil.getFileEntry(access.getFileId()).getFileName());
			} catch (Exception e) {
				// File is not accessible anymore -> return null to skip the access
				logger.error("File " + access.getFileId() + " may have been deleted -> skipping this access");
				return null;
			}
		}
		jsonAccess.put(AccessConstants.POSITION, access.getPosition());

		// Thumbnail
		if (access.getThumbnailId() != 0) {
			try {
				FileEntry thumbnailFileEntry = DLAppServiceUtil.getFileEntry(access.getThumbnailId());
				jsonAccess.put(AccessConstants.THUMBNAIL_URL, FileUtilsLocalServiceUtil.getDisplayUrl(thumbnailFileEntry, 0, 0, true)); // assume we don't need userId to get image url
			} catch (Exception e) {
				logger.error("Cannot retrieve thumbnail for access " + access.getAccessId() + ", thumbnail fileId = " + access.getThumbnailId(), e);
			}
		} else {
			jsonAccess.put(AccessConstants.THUMBNAIL_URL, JSONConstants.ACCESS_DEFAULT_THUMBNAIL);
		}
		jsonAccess.put(AccessConstants.THUMBNAIL_ID, access.getThumbnailId());
		return jsonAccess;
	}

	public Access addAccess(long userId, long categoryId, String title, int type, String url, long folderId, long fileId, long thumbnailId, int position, JSONArray jsonProfiles) {
		Access access = accessPersistence.create(counterLocalService.increment());
		access.setCategoryId(categoryId);
		access.setTitle(title);
		access.setType(type);
		access.setExternalUrl(url);
		access.setFolderId(folderId);
		access.setFileId(fileId);
		access.setPosition(position);
		// Copy thumbnail file to thumbnail folder
		if (thumbnailId > 0) {
			try {
				FileEntry thumbnail = ThumbnailsLocalServiceUtil.createThumbnailFile(userId, thumbnailId, String.valueOf(access.getAccessId()));
				access.setThumbnailId(thumbnail.getFileEntryId());
			} catch (Exception e) {
				access.setThumbnailId(0L);
				logger.error("Cannot create thumbnail file from fileId " + thumbnailId + " for access id " + access.getAccessId(), e);
			}
		} else {
			access.setThumbnailId(thumbnailId);	// Negative numbers (including 0) are used for front default images
		}

		for (int profileIdx = 0 ; profileIdx < jsonProfiles.length() ; profileIdx++) {
			JSONObject jsonProfile = jsonProfiles.getJSONObject(profileIdx);
			long roleId = jsonProfile.getLong(JSONConstants.ROLE_ID);
			AccessProfileLocalServiceUtil.addAccessProfile(access.getAccessId(), roleId);
		}

		return accessPersistence.update(access);
	}

	public void removeByCategoryId(long categoryId) {
		// First remove all AccessProfiles
		List<Access> accesses = accessPersistence.findBycategoryId(categoryId);
		if (accesses != null) {
			for (Access access : accesses) {
				// Delete thumbnail file if exist
				if (access.getThumbnailId() > 0) {
					ThumbnailsLocalServiceUtil.deleteThumbnailFile(access.getThumbnailId());
				}
				accessProfilePersistence.removeByaccessId(access.getAccessId());
			}
		}
		accessPersistence.removeBycategoryId(categoryId);
	}
}