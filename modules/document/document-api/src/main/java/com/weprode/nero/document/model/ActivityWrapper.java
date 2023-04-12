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

package com.weprode.nero.document.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Activity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Activity
 * @generated
 */
public class ActivityWrapper
	extends BaseModelWrapper<Activity>
	implements Activity, ModelWrapper<Activity> {

	public ActivityWrapper(Activity activity) {
		super(activity);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activityId", getActivityId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("folderId", getFolderId());
		attributes.put("userId", getUserId());
		attributes.put("groupId", getGroupId());
		attributes.put("fileName", getFileName());
		attributes.put("folderName", getFolderName());
		attributes.put("type", getType());
		attributes.put("modificationDate", getModificationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String folderName = (String)attributes.get("folderName");

		if (folderName != null) {
			setFolderName(folderName);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}
	}

	@Override
	public Activity cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the activity ID of this activity.
	 *
	 * @return the activity ID of this activity
	 */
	@Override
	public long getActivityId() {
		return model.getActivityId();
	}

	/**
	 * Returns the file entry ID of this activity.
	 *
	 * @return the file entry ID of this activity
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the file name of this activity.
	 *
	 * @return the file name of this activity
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the folder ID of this activity.
	 *
	 * @return the folder ID of this activity
	 */
	@Override
	public long getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the folder name of this activity.
	 *
	 * @return the folder name of this activity
	 */
	@Override
	public String getFolderName() {
		return model.getFolderName();
	}

	/**
	 * Returns the group ID of this activity.
	 *
	 * @return the group ID of this activity
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modification date of this activity.
	 *
	 * @return the modification date of this activity
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
	}

	/**
	 * Returns the primary key of this activity.
	 *
	 * @return the primary key of this activity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the type of this activity.
	 *
	 * @return the type of this activity
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this activity.
	 *
	 * @return the user ID of this activity
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this activity.
	 *
	 * @return the user uuid of this activity
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the activity ID of this activity.
	 *
	 * @param activityId the activity ID of this activity
	 */
	@Override
	public void setActivityId(long activityId) {
		model.setActivityId(activityId);
	}

	/**
	 * Sets the file entry ID of this activity.
	 *
	 * @param fileEntryId the file entry ID of this activity
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the file name of this activity.
	 *
	 * @param fileName the file name of this activity
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
	}

	/**
	 * Sets the folder ID of this activity.
	 *
	 * @param folderId the folder ID of this activity
	 */
	@Override
	public void setFolderId(long folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets the folder name of this activity.
	 *
	 * @param folderName the folder name of this activity
	 */
	@Override
	public void setFolderName(String folderName) {
		model.setFolderName(folderName);
	}

	/**
	 * Sets the group ID of this activity.
	 *
	 * @param groupId the group ID of this activity
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modification date of this activity.
	 *
	 * @param modificationDate the modification date of this activity
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
	}

	/**
	 * Sets the primary key of this activity.
	 *
	 * @param primaryKey the primary key of this activity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the type of this activity.
	 *
	 * @param type the type of this activity
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this activity.
	 *
	 * @param userId the user ID of this activity
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this activity.
	 *
	 * @param userUuid the user uuid of this activity
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected ActivityWrapper wrap(Activity activity) {
		return new ActivityWrapper(activity);
	}

}