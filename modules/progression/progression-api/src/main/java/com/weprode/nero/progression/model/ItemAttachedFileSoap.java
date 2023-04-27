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

package com.weprode.nero.progression.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.progression.service.http.ItemAttachedFileServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ItemAttachedFileSoap implements Serializable {

	public static ItemAttachedFileSoap toSoapModel(ItemAttachedFile model) {
		ItemAttachedFileSoap soapModel = new ItemAttachedFileSoap();

		soapModel.setItemAttachedFileId(model.getItemAttachedFileId());
		soapModel.setProgressionItemId(model.getProgressionItemId());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setIsAudioRecording(model.isIsAudioRecording());
		soapModel.setIsToBeCompleted(model.isIsToBeCompleted());

		return soapModel;
	}

	public static ItemAttachedFileSoap[] toSoapModels(
		ItemAttachedFile[] models) {

		ItemAttachedFileSoap[] soapModels =
			new ItemAttachedFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemAttachedFileSoap[][] toSoapModels(
		ItemAttachedFile[][] models) {

		ItemAttachedFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ItemAttachedFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemAttachedFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemAttachedFileSoap[] toSoapModels(
		List<ItemAttachedFile> models) {

		List<ItemAttachedFileSoap> soapModels =
			new ArrayList<ItemAttachedFileSoap>(models.size());

		for (ItemAttachedFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemAttachedFileSoap[soapModels.size()]);
	}

	public ItemAttachedFileSoap() {
	}

	public long getPrimaryKey() {
		return _itemAttachedFileId;
	}

	public void setPrimaryKey(long pk) {
		setItemAttachedFileId(pk);
	}

	public long getItemAttachedFileId() {
		return _itemAttachedFileId;
	}

	public void setItemAttachedFileId(long itemAttachedFileId) {
		_itemAttachedFileId = itemAttachedFileId;
	}

	public long getProgressionItemId() {
		return _progressionItemId;
	}

	public void setProgressionItemId(long progressionItemId) {
		_progressionItemId = progressionItemId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public boolean getIsAudioRecording() {
		return _isAudioRecording;
	}

	public boolean isIsAudioRecording() {
		return _isAudioRecording;
	}

	public void setIsAudioRecording(boolean isAudioRecording) {
		_isAudioRecording = isAudioRecording;
	}

	public boolean getIsToBeCompleted() {
		return _isToBeCompleted;
	}

	public boolean isIsToBeCompleted() {
		return _isToBeCompleted;
	}

	public void setIsToBeCompleted(boolean isToBeCompleted) {
		_isToBeCompleted = isToBeCompleted;
	}

	private long _itemAttachedFileId;
	private long _progressionItemId;
	private long _fileEntryId;
	private boolean _isAudioRecording;
	private boolean _isToBeCompleted;

}