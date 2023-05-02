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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemAttachedFile}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFile
 * @generated
 */
public class ItemAttachedFileWrapper
	extends BaseModelWrapper<ItemAttachedFile>
	implements ItemAttachedFile, ModelWrapper<ItemAttachedFile> {

	public ItemAttachedFileWrapper(ItemAttachedFile itemAttachedFile) {
		super(itemAttachedFile);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemAttachedFileId", getItemAttachedFileId());
		attributes.put("progressionItemId", getProgressionItemId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("isAudioRecording", isIsAudioRecording());
		attributes.put("isToBeCompleted", isIsToBeCompleted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long itemAttachedFileId = (Long)attributes.get("itemAttachedFileId");

		if (itemAttachedFileId != null) {
			setItemAttachedFileId(itemAttachedFileId);
		}

		Long progressionItemId = (Long)attributes.get("progressionItemId");

		if (progressionItemId != null) {
			setProgressionItemId(progressionItemId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean isAudioRecording = (Boolean)attributes.get("isAudioRecording");

		if (isAudioRecording != null) {
			setIsAudioRecording(isAudioRecording);
		}

		Boolean isToBeCompleted = (Boolean)attributes.get("isToBeCompleted");

		if (isToBeCompleted != null) {
			setIsToBeCompleted(isToBeCompleted);
		}
	}

	@Override
	public ItemAttachedFile cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	/**
	 * Returns the file entry ID of this item attached file.
	 *
	 * @return the file entry ID of this item attached file
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the is audio recording of this item attached file.
	 *
	 * @return the is audio recording of this item attached file
	 */
	@Override
	public boolean getIsAudioRecording() {
		return model.getIsAudioRecording();
	}

	/**
	 * Returns the is to be completed of this item attached file.
	 *
	 * @return the is to be completed of this item attached file
	 */
	@Override
	public boolean getIsToBeCompleted() {
		return model.getIsToBeCompleted();
	}

	/**
	 * Returns the item attached file ID of this item attached file.
	 *
	 * @return the item attached file ID of this item attached file
	 */
	@Override
	public long getItemAttachedFileId() {
		return model.getItemAttachedFileId();
	}

	/**
	 * Returns the primary key of this item attached file.
	 *
	 * @return the primary key of this item attached file
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression item ID of this item attached file.
	 *
	 * @return the progression item ID of this item attached file
	 */
	@Override
	public long getProgressionItemId() {
		return model.getProgressionItemId();
	}

	/**
	 * Returns <code>true</code> if this item attached file is is audio recording.
	 *
	 * @return <code>true</code> if this item attached file is is audio recording; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAudioRecording() {
		return model.isIsAudioRecording();
	}

	/**
	 * Returns <code>true</code> if this item attached file is is to be completed.
	 *
	 * @return <code>true</code> if this item attached file is is to be completed; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsToBeCompleted() {
		return model.isIsToBeCompleted();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the file entry ID of this item attached file.
	 *
	 * @param fileEntryId the file entry ID of this item attached file
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets whether this item attached file is is audio recording.
	 *
	 * @param isAudioRecording the is audio recording of this item attached file
	 */
	@Override
	public void setIsAudioRecording(boolean isAudioRecording) {
		model.setIsAudioRecording(isAudioRecording);
	}

	/**
	 * Sets whether this item attached file is is to be completed.
	 *
	 * @param isToBeCompleted the is to be completed of this item attached file
	 */
	@Override
	public void setIsToBeCompleted(boolean isToBeCompleted) {
		model.setIsToBeCompleted(isToBeCompleted);
	}

	/**
	 * Sets the item attached file ID of this item attached file.
	 *
	 * @param itemAttachedFileId the item attached file ID of this item attached file
	 */
	@Override
	public void setItemAttachedFileId(long itemAttachedFileId) {
		model.setItemAttachedFileId(itemAttachedFileId);
	}

	/**
	 * Sets the primary key of this item attached file.
	 *
	 * @param primaryKey the primary key of this item attached file
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression item ID of this item attached file.
	 *
	 * @param progressionItemId the progression item ID of this item attached file
	 */
	@Override
	public void setProgressionItemId(long progressionItemId) {
		model.setProgressionItemId(progressionItemId);
	}

	@Override
	protected ItemAttachedFileWrapper wrap(ItemAttachedFile itemAttachedFile) {
		return new ItemAttachedFileWrapper(itemAttachedFile);
	}

}