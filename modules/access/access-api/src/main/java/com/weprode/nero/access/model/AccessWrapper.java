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
		attributes.put("url", getUrl());
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

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
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
	 * Returns the url of this access.
	 *
	 * @return the url of this access
	 */
	@Override
	public String getUrl() {
		return model.getUrl();
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
	 * Sets the url of this access.
	 *
	 * @param url the url of this access
	 */
	@Override
	public void setUrl(String url) {
		model.setUrl(url);
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