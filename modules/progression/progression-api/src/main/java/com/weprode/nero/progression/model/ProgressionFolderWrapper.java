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
 * This class is a wrapper for {@link ProgressionFolder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolder
 * @generated
 */
public class ProgressionFolderWrapper
	extends BaseModelWrapper<ProgressionFolder>
	implements ModelWrapper<ProgressionFolder>, ProgressionFolder {

	public ProgressionFolderWrapper(ProgressionFolder progressionFolder) {
		super(progressionFolder);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("progressionFolderId", getProgressionFolderId());
		attributes.put("progressionId", getProgressionId());
		attributes.put("parentFolderId", getParentFolderId());
		attributes.put("folderName", getFolderName());
		attributes.put("order", getOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long progressionFolderId = (Long)attributes.get("progressionFolderId");

		if (progressionFolderId != null) {
			setProgressionFolderId(progressionFolderId);
		}

		Long progressionId = (Long)attributes.get("progressionId");

		if (progressionId != null) {
			setProgressionId(progressionId);
		}

		Long parentFolderId = (Long)attributes.get("parentFolderId");

		if (parentFolderId != null) {
			setParentFolderId(parentFolderId);
		}

		String folderName = (String)attributes.get("folderName");

		if (folderName != null) {
			setFolderName(folderName);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}
	}

	@Override
	public ProgressionFolder cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	/**
	 * Returns the folder name of this progression folder.
	 *
	 * @return the folder name of this progression folder
	 */
	@Override
	public String getFolderName() {
		return model.getFolderName();
	}

	/**
	 * Returns the order of this progression folder.
	 *
	 * @return the order of this progression folder
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the parent folder ID of this progression folder.
	 *
	 * @return the parent folder ID of this progression folder
	 */
	@Override
	public long getParentFolderId() {
		return model.getParentFolderId();
	}

	/**
	 * Returns the primary key of this progression folder.
	 *
	 * @return the primary key of this progression folder
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression folder ID of this progression folder.
	 *
	 * @return the progression folder ID of this progression folder
	 */
	@Override
	public long getProgressionFolderId() {
		return model.getProgressionFolderId();
	}

	/**
	 * Returns the progression ID of this progression folder.
	 *
	 * @return the progression ID of this progression folder
	 */
	@Override
	public long getProgressionId() {
		return model.getProgressionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the folder name of this progression folder.
	 *
	 * @param folderName the folder name of this progression folder
	 */
	@Override
	public void setFolderName(String folderName) {
		model.setFolderName(folderName);
	}

	/**
	 * Sets the order of this progression folder.
	 *
	 * @param order the order of this progression folder
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the parent folder ID of this progression folder.
	 *
	 * @param parentFolderId the parent folder ID of this progression folder
	 */
	@Override
	public void setParentFolderId(long parentFolderId) {
		model.setParentFolderId(parentFolderId);
	}

	/**
	 * Sets the primary key of this progression folder.
	 *
	 * @param primaryKey the primary key of this progression folder
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression folder ID of this progression folder.
	 *
	 * @param progressionFolderId the progression folder ID of this progression folder
	 */
	@Override
	public void setProgressionFolderId(long progressionFolderId) {
		model.setProgressionFolderId(progressionFolderId);
	}

	/**
	 * Sets the progression ID of this progression folder.
	 *
	 * @param progressionId the progression ID of this progression folder
	 */
	@Override
	public void setProgressionId(long progressionId) {
		model.setProgressionId(progressionId);
	}

	@Override
	protected ProgressionFolderWrapper wrap(
		ProgressionFolder progressionFolder) {

		return new ProgressionFolderWrapper(progressionFolder);
	}

}