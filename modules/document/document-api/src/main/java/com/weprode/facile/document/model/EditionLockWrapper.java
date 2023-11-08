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

package com.weprode.facile.document.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EditionLock}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EditionLock
 * @generated
 */
public class EditionLockWrapper
	extends BaseModelWrapper<EditionLock>
	implements EditionLock, ModelWrapper<EditionLock> {

	public EditionLockWrapper(EditionLock editionLock) {
		super(editionLock);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileId", getFileId());
		attributes.put("userId", getUserId());
		attributes.put("editionDate", getEditionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date editionDate = (Date)attributes.get("editionDate");

		if (editionDate != null) {
			setEditionDate(editionDate);
		}
	}

	@Override
	public EditionLock cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the edition date of this edition lock.
	 *
	 * @return the edition date of this edition lock
	 */
	@Override
	public Date getEditionDate() {
		return model.getEditionDate();
	}

	/**
	 * Returns the file ID of this edition lock.
	 *
	 * @return the file ID of this edition lock
	 */
	@Override
	public long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the primary key of this edition lock.
	 *
	 * @return the primary key of this edition lock
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this edition lock.
	 *
	 * @return the user ID of this edition lock
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this edition lock.
	 *
	 * @return the user uuid of this edition lock
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
	 * Sets the edition date of this edition lock.
	 *
	 * @param editionDate the edition date of this edition lock
	 */
	@Override
	public void setEditionDate(Date editionDate) {
		model.setEditionDate(editionDate);
	}

	/**
	 * Sets the file ID of this edition lock.
	 *
	 * @param fileId the file ID of this edition lock
	 */
	@Override
	public void setFileId(long fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the primary key of this edition lock.
	 *
	 * @param primaryKey the primary key of this edition lock
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this edition lock.
	 *
	 * @param userId the user ID of this edition lock
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this edition lock.
	 *
	 * @param userUuid the user uuid of this edition lock
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected EditionLockWrapper wrap(EditionLock editionLock) {
		return new EditionLockWrapper(editionLock);
	}

}