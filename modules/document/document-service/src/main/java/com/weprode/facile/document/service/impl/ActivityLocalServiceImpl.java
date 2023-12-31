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

package com.weprode.facile.document.service.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.model.Activity;
import com.weprode.facile.document.service.base.ActivityLocalServiceBaseImpl;
import com.weprode.facile.group.constants.ActivityConstants;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.Activity",
	service = AopService.class
)
public class ActivityLocalServiceImpl extends ActivityLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ActivityLocalServiceImpl.class);

	public Activity addActivity(long fileEntryId, long folderId, long userId, long groupId,
								String fileName, String folderName, int type) {
		Activity activity = null;

		try {
			// Check if groupId is either a personal or an organization group
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			Folder folder = DLAppLocalServiceUtil.getFolder(folderId);
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			if (group.isRegularSite() || group.isOrganization()) {
				if (folder.getParentFolderId() != 0) {
					Folder parentFolder = DLAppServiceUtil.getFolder(folder.getParentFolderId());
					if (fileEntry == null
							|| parentFolder.getName().equals(DocumentConstants.NEWS_FOLDER_NAME)
							|| parentFolder.getName().equals(DocumentConstants.COURSE_FOLDER_NAME)) {
						return null;
					}
				} else if (fileEntry == null
						|| folder.getName().equals(DocumentConstants.THUMBNAILS_FOLDER_NAME)
						|| folder.getName().equals(DocumentConstants.TMP_FILE_FOLDER_NAME)) {
					return null;
				}

				long activityId = counterLocalService.increment();
				activity = activityPersistence.create(activityId);
				activity.setFileEntryId(fileEntryId);
				activity.setFolderId(folderId);
				activity.setUserId(userId);
				activity.setGroupId(groupId);
				activity.setFileName(fileName);
				activity.setFolderName(folderName);
				activity.setType(type);
				activity.setModificationDate(new Date());
				activity = activityPersistence.update(activity);
				logger.info("Created activity for user " + userId + ", fileEntryId " + fileEntryId + " and folderId " + folderId);
			} else {
				logger.debug("Operation done in personal space ("+group.getName(LocaleUtil.getDefault())+") => not recorded");
			}

		} catch (Exception e) {
			logger.error("Error when creating new activity for user "+userId, e);
		}

		return activity;
	}

	public List<Activity> getActivity(List<Long> groupIdList, long creatorId, int start, int end) {
		if (groupIdList == null || groupIdList.isEmpty()) {
			return new ArrayList<>();
		}

		return activityFinder.getActivities(groupIdList, creatorId, start, end);
	}

	public List<Activity> getGroupsActivity(long userId, List<Long> groupIdList, Date minDate, Date maxDate,
											boolean includeUserActivity, boolean withFileCreation,
											boolean withFileModification, boolean withFolderCreation,
											boolean withFolderModification) {

		if (groupIdList == null || groupIdList.isEmpty()) {
			return new ArrayList<>();
		}

		return activityFinder.getGroupsActivities(userId, groupIdList, minDate, maxDate, includeUserActivity,
				withFileCreation, withFileModification, withFolderCreation, withFolderModification);
	}

	public boolean deleteGroupActivity(long groupId) {
		try {
			activityPersistence.removeBygroupId(groupId);
			return true;
		} catch (Exception e) {
			logger.error("Error deleting group activity for groupId " + groupId, e);
		}

		return false;
	}

	public void deleteFileActivity(long fileEntryId) {
		try {
			activityPersistence.removeByfileEntryId(fileEntryId);
		} catch (Exception e) {
			logger.error("Error deleting group activity for fileEntryId " + fileEntryId, e);
		}
	}

	public void deleteFolderActivity(long folderId) {
		try {
			activityPersistence.removeByfolderId(folderId);
		} catch (Exception e) {
			logger.error("Error deleting group activity for folderId " + folderId, e);
		}
	}

	public int countSchoolActivities(long schoolId, Date minDate, Date maxDate) {
		return activityFinder.countSchoolActivities(schoolId, minDate, maxDate);
	}

	public JSONObject convertActivityToJson(Activity activity, long userId) {
		JSONObject jsonActivity = new JSONObject();

		try {
			User user = UserLocalServiceUtil.getUser(userId);
			jsonActivity.put(JSONConstants.ACTIVITY_ID, activity.getActivityId());
			jsonActivity.put(JSONConstants.USER_ID, activity.getUserId());
			User activityUser = UserLocalServiceUtil.getUser(activity.getUserId());
			jsonActivity.put(JSONConstants.USER_NAME, activityUser.getFullName());
			jsonActivity.put(JSONConstants.AUTHOR, activityUser.getFullName());
			jsonActivity.put(JSONConstants.FILE_NAME, activity.getFileName());
			jsonActivity.put(JSONConstants.FOLDER_NAME, activity.getFolderName());
			jsonActivity.put(JSONConstants.GROUP_ID, activity.getGroupId());
			jsonActivity.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(activity.getGroupId()));
			jsonActivity.put(JSONConstants.MODIFICATION_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(activity.getModificationDate()));
			jsonActivity.put(JSONConstants.TYPE, activity.getType());

			boolean isFile = (activity.getType() < ActivityConstants.TYPE_FOLDER_CREATION);
			jsonActivity.put(JSONConstants.TARGET, isFile ? activity.getFileName() : activity.getFolderName());
			if (isFile) {
				jsonActivity.put(JSONConstants.FILE_LINK, "/c/document_library/get_file?fileEntryId=" + activity.getFileEntryId());
				jsonActivity.put(JSONConstants.FILE_ID, activity.getFileEntryId());
				jsonActivity.put(JSONConstants.PARENT_FOLDER_ID, DLAppServiceUtil.getFileEntry(activity.getFileEntryId()).getFolderId());
			} else {
				jsonActivity.put(JSONConstants.FOLDER_ID, activity.getFolderId());
				jsonActivity.put(JSONConstants.PARENT_FOLDER_ID, DLAppServiceUtil.getFolder(activity.getFolderId()).getParentFolderId());
			}
			// Students and parents should not have access to groups
			// To be enhanced when we will have a proper way of managing menu entries
			jsonActivity.put(JSONConstants.READ_ONLY, RoleUtilsLocalServiceUtil.isStudentOrParent(user));

		} catch (Exception e) {
			logger.debug("Error converting activity " + activity.getActivityId() + " : " + e.getMessage());
			// Happened for deleted file or folder -> we currently don't want to display these activities as the link would be empty
			// Only displaying the "deleted" activity for this entry
			return null;
		}

		return jsonActivity;
	}

}