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

package com.weprode.nero.about.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EntVersionUser}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUser
 * @generated
 */
public class EntVersionUserWrapper
	extends BaseModelWrapper<EntVersionUser>
	implements EntVersionUser, ModelWrapper<EntVersionUser> {

	public EntVersionUserWrapper(EntVersionUser entVersionUser) {
		super(entVersionUser);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("versionUserId", getVersionUserId());
		attributes.put("entVersionId", getEntVersionId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long versionUserId = (Long)attributes.get("versionUserId");

		if (versionUserId != null) {
			setVersionUserId(versionUserId);
		}

		Long entVersionId = (Long)attributes.get("entVersionId");

		if (entVersionId != null) {
			setEntVersionId(entVersionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public EntVersionUser cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the ent version ID of this ent version user.
	 *
	 * @return the ent version ID of this ent version user
	 */
	@Override
	public long getEntVersionId() {
		return model.getEntVersionId();
	}

	/**
	 * Returns the primary key of this ent version user.
	 *
	 * @return the primary key of this ent version user
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this ent version user.
	 *
	 * @return the user ID of this ent version user
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this ent version user.
	 *
	 * @return the user uuid of this ent version user
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version user ID of this ent version user.
	 *
	 * @return the version user ID of this ent version user
	 */
	@Override
	public long getVersionUserId() {
		return model.getVersionUserId();
	}

	/**
	 * Returns the version user uuid of this ent version user.
	 *
	 * @return the version user uuid of this ent version user
	 */
	@Override
	public String getVersionUserUuid() {
		return model.getVersionUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ent version ID of this ent version user.
	 *
	 * @param entVersionId the ent version ID of this ent version user
	 */
	@Override
	public void setEntVersionId(long entVersionId) {
		model.setEntVersionId(entVersionId);
	}

	/**
	 * Sets the primary key of this ent version user.
	 *
	 * @param primaryKey the primary key of this ent version user
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this ent version user.
	 *
	 * @param userId the user ID of this ent version user
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this ent version user.
	 *
	 * @param userUuid the user uuid of this ent version user
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version user ID of this ent version user.
	 *
	 * @param versionUserId the version user ID of this ent version user
	 */
	@Override
	public void setVersionUserId(long versionUserId) {
		model.setVersionUserId(versionUserId);
	}

	/**
	 * Sets the version user uuid of this ent version user.
	 *
	 * @param versionUserUuid the version user uuid of this ent version user
	 */
	@Override
	public void setVersionUserUuid(String versionUserUuid) {
		model.setVersionUserUuid(versionUserUuid);
	}

	@Override
	protected EntVersionUserWrapper wrap(EntVersionUser entVersionUser) {
		return new EntVersionUserWrapper(entVersionUser);
	}

}