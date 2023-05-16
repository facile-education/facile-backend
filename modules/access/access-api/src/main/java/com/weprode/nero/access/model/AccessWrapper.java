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

package com.weprode.nero.access.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Access}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Access
 * @generated
 */
public class AccessWrapper
	extends BaseModelWrapper<Access> implements Access, ModelWrapper<Access> {

	public AccessWrapper(Access access) {
		super(access);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("accessId", getAccessId());
		attributes.put("categoryId", getCategoryId());
		attributes.put("title", getTitle());
		attributes.put("type", getType());
		attributes.put("externalUrl", getExternalUrl());
		attributes.put("folderId", getFolderId());
		attributes.put("fileId", getFileId());
		attributes.put("thumbnail", getThumbnail());
		attributes.put("position", getPosition());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long accessId = (Long)attributes.get("accessId");

		if (accessId != null) {
			setAccessId(accessId);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String externalUrl = (String)attributes.get("externalUrl");

		if (externalUrl != null) {
			setExternalUrl(externalUrl);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String thumbnail = (String)attributes.get("thumbnail");

		if (thumbnail != null) {
			setThumbnail(thumbnail);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}
	}

	@Override
	public Access cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the access ID of this access.
	 *
	 * @return the access ID of this access
	 */
	@Override
	public long getAccessId() {
		return model.getAccessId();
	}

	/**
	 * Returns the category ID of this access.
	 *
	 * @return the category ID of this access
	 */
	@Override
	public long getCategoryId() {
		return model.getCategoryId();
	}

	/**
	 * Returns the external url of this access.
	 *
	 * @return the external url of this access
	 */
	@Override
	public String getExternalUrl() {
		return model.getExternalUrl();
	}

	/**
	 * Returns the file ID of this access.
	 *
	 * @return the file ID of this access
	 */
	@Override
	public long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the folder ID of this access.
	 *
	 * @return the folder ID of this access
	 */
	@Override
	public long getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the position of this access.
	 *
	 * @return the position of this access
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this access.
	 *
	 * @return the primary key of this access
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the thumbnail of this access.
	 *
	 * @return the thumbnail of this access
	 */
	@Override
	public String getThumbnail() {
		return model.getThumbnail();
	}

	/**
	 * Returns the title of this access.
	 *
	 * @return the title of this access
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the type of this access.
	 *
	 * @return the type of this access
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the uuid of this access.
	 *
	 * @return the uuid of this access
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the access ID of this access.
	 *
	 * @param accessId the access ID of this access
	 */
	@Override
	public void setAccessId(long accessId) {
		model.setAccessId(accessId);
	}

	/**
	 * Sets the category ID of this access.
	 *
	 * @param categoryId the category ID of this access
	 */
	@Override
	public void setCategoryId(long categoryId) {
		model.setCategoryId(categoryId);
	}

	/**
	 * Sets the external url of this access.
	 *
	 * @param externalUrl the external url of this access
	 */
	@Override
	public void setExternalUrl(String externalUrl) {
		model.setExternalUrl(externalUrl);
	}

	/**
	 * Sets the file ID of this access.
	 *
	 * @param fileId the file ID of this access
	 */
	@Override
	public void setFileId(long fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the folder ID of this access.
	 *
	 * @param folderId the folder ID of this access
	 */
	@Override
	public void setFolderId(long folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets the position of this access.
	 *
	 * @param position the position of this access
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this access.
	 *
	 * @param primaryKey the primary key of this access
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the thumbnail of this access.
	 *
	 * @param thumbnail the thumbnail of this access
	 */
	@Override
	public void setThumbnail(String thumbnail) {
		model.setThumbnail(thumbnail);
	}

	/**
	 * Sets the title of this access.
	 *
	 * @param title the title of this access
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the type of this access.
	 *
	 * @param type the type of this access
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the uuid of this access.
	 *
	 * @param uuid the uuid of this access
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected AccessWrapper wrap(Access access) {
		return new AccessWrapper(access);
	}

}