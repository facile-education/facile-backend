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

package com.weprode.nero.application.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuthorizedSchool}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthorizedSchool
 * @generated
 */
public class AuthorizedSchoolWrapper
	extends BaseModelWrapper<AuthorizedSchool>
	implements AuthorizedSchool, ModelWrapper<AuthorizedSchool> {

	public AuthorizedSchoolWrapper(AuthorizedSchool authorizedSchool) {
		super(authorizedSchool);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("authorizedSchoolId", getAuthorizedSchoolId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("schoolId", getSchoolId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long authorizedSchoolId = (Long)attributes.get("authorizedSchoolId");

		if (authorizedSchoolId != null) {
			setAuthorizedSchoolId(authorizedSchoolId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}
	}

	@Override
	public AuthorizedSchool cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application ID of this authorized school.
	 *
	 * @return the application ID of this authorized school
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the authorized school ID of this authorized school.
	 *
	 * @return the authorized school ID of this authorized school
	 */
	@Override
	public long getAuthorizedSchoolId() {
		return model.getAuthorizedSchoolId();
	}

	/**
	 * Returns the primary key of this authorized school.
	 *
	 * @return the primary key of this authorized school
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this authorized school.
	 *
	 * @return the school ID of this authorized school
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application ID of this authorized school.
	 *
	 * @param applicationId the application ID of this authorized school
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the authorized school ID of this authorized school.
	 *
	 * @param authorizedSchoolId the authorized school ID of this authorized school
	 */
	@Override
	public void setAuthorizedSchoolId(long authorizedSchoolId) {
		model.setAuthorizedSchoolId(authorizedSchoolId);
	}

	/**
	 * Sets the primary key of this authorized school.
	 *
	 * @param primaryKey the primary key of this authorized school
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this authorized school.
	 *
	 * @param schoolId the school ID of this authorized school
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	@Override
	protected AuthorizedSchoolWrapper wrap(AuthorizedSchool authorizedSchool) {
		return new AuthorizedSchoolWrapper(authorizedSchool);
	}

}