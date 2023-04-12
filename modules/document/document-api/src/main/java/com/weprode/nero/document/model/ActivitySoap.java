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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ActivitySoap implements Serializable {

	public static ActivitySoap toSoapModel(Activity model) {
		ActivitySoap soapModel = new ActivitySoap();

		soapModel.setActivityId(model.getActivityId());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setUserId(model.getUserId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setFileName(model.getFileName());
		soapModel.setFolderName(model.getFolderName());
		soapModel.setType(model.getType());
		soapModel.setModificationDate(model.getModificationDate());

		return soapModel;
	}

	public static ActivitySoap[] toSoapModels(Activity[] models) {
		ActivitySoap[] soapModels = new ActivitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActivitySoap[][] toSoapModels(Activity[][] models) {
		ActivitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActivitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActivitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActivitySoap[] toSoapModels(List<Activity> models) {
		List<ActivitySoap> soapModels = new ArrayList<ActivitySoap>(
			models.size());

		for (Activity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActivitySoap[soapModels.size()]);
	}

	public ActivitySoap() {
	}

	public long getPrimaryKey() {
		return _activityId;
	}

	public void setPrimaryKey(long pk) {
		setActivityId(pk);
	}

	public long getActivityId() {
		return _activityId;
	}

	public void setActivityId(long activityId) {
		_activityId = activityId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public String getFolderName() {
		return _folderName;
	}

	public void setFolderName(String folderName) {
		_folderName = folderName;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	private long _activityId;
	private long _fileEntryId;
	private long _folderId;
	private long _userId;
	private long _groupId;
	private String _fileName;
	private String _folderName;
	private int _type;
	private Date _modificationDate;

}