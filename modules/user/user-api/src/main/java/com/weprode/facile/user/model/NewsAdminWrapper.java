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

package com.weprode.facile.user.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NewsAdmin}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdmin
 * @generated
 */
public class NewsAdminWrapper
	extends BaseModelWrapper<NewsAdmin>
	implements ModelWrapper<NewsAdmin>, NewsAdmin {

	public NewsAdminWrapper(NewsAdmin newsAdmin) {
		super(newsAdmin);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsAdminId", getNewsAdminId());
		attributes.put("userId", getUserId());
		attributes.put("schoolId", getSchoolId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsAdminId = (Long)attributes.get("newsAdminId");

		if (newsAdminId != null) {
			setNewsAdminId(newsAdminId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}
	}

	@Override
	public NewsAdmin cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the news admin ID of this news admin.
	 *
	 * @return the news admin ID of this news admin
	 */
	@Override
	public long getNewsAdminId() {
		return model.getNewsAdminId();
	}

	/**
	 * Returns the primary key of this news admin.
	 *
	 * @return the primary key of this news admin
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this news admin.
	 *
	 * @return the school ID of this news admin
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the user ID of this news admin.
	 *
	 * @return the user ID of this news admin
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this news admin.
	 *
	 * @return the user uuid of this news admin
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
	 * Sets the news admin ID of this news admin.
	 *
	 * @param newsAdminId the news admin ID of this news admin
	 */
	@Override
	public void setNewsAdminId(long newsAdminId) {
		model.setNewsAdminId(newsAdminId);
	}

	/**
	 * Sets the primary key of this news admin.
	 *
	 * @param primaryKey the primary key of this news admin
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this news admin.
	 *
	 * @param schoolId the school ID of this news admin
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the user ID of this news admin.
	 *
	 * @param userId the user ID of this news admin
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this news admin.
	 *
	 * @param userUuid the user uuid of this news admin
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected NewsAdminWrapper wrap(NewsAdmin newsAdmin) {
		return new NewsAdminWrapper(newsAdmin);
	}

}