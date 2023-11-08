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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Affectation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Affectation
 * @generated
 */
public class AffectationWrapper
	extends BaseModelWrapper<Affectation>
	implements Affectation, ModelWrapper<Affectation> {

	public AffectationWrapper(Affectation affectation) {
		super(affectation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("affectationId", getAffectationId());
		attributes.put("userId", getUserId());
		attributes.put("orgId", getOrgId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("type", getType());
		attributes.put("adminUserId", getAdminUserId());
		attributes.put("affectationDate", getAffectationDate());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long affectationId = (Long)attributes.get("affectationId");

		if (affectationId != null) {
			setAffectationId(affectationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long orgId = (Long)attributes.get("orgId");

		if (orgId != null) {
			setOrgId(orgId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long adminUserId = (Long)attributes.get("adminUserId");

		if (adminUserId != null) {
			setAdminUserId(adminUserId);
		}

		Date affectationDate = (Date)attributes.get("affectationDate");

		if (affectationDate != null) {
			setAffectationDate(affectationDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	@Override
	public Affectation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the admin user ID of this affectation.
	 *
	 * @return the admin user ID of this affectation
	 */
	@Override
	public long getAdminUserId() {
		return model.getAdminUserId();
	}

	/**
	 * Returns the admin user uuid of this affectation.
	 *
	 * @return the admin user uuid of this affectation
	 */
	@Override
	public String getAdminUserUuid() {
		return model.getAdminUserUuid();
	}

	/**
	 * Returns the affectation date of this affectation.
	 *
	 * @return the affectation date of this affectation
	 */
	@Override
	public Date getAffectationDate() {
		return model.getAffectationDate();
	}

	/**
	 * Returns the affectation ID of this affectation.
	 *
	 * @return the affectation ID of this affectation
	 */
	@Override
	public long getAffectationId() {
		return model.getAffectationId();
	}

	/**
	 * Returns the expiration date of this affectation.
	 *
	 * @return the expiration date of this affectation
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the org ID of this affectation.
	 *
	 * @return the org ID of this affectation
	 */
	@Override
	public long getOrgId() {
		return model.getOrgId();
	}

	/**
	 * Returns the primary key of this affectation.
	 *
	 * @return the primary key of this affectation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this affectation.
	 *
	 * @return the school ID of this affectation
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the type of this affectation.
	 *
	 * @return the type of this affectation
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this affectation.
	 *
	 * @return the user ID of this affectation
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this affectation.
	 *
	 * @return the user uuid of this affectation
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
	 * Sets the admin user ID of this affectation.
	 *
	 * @param adminUserId the admin user ID of this affectation
	 */
	@Override
	public void setAdminUserId(long adminUserId) {
		model.setAdminUserId(adminUserId);
	}

	/**
	 * Sets the admin user uuid of this affectation.
	 *
	 * @param adminUserUuid the admin user uuid of this affectation
	 */
	@Override
	public void setAdminUserUuid(String adminUserUuid) {
		model.setAdminUserUuid(adminUserUuid);
	}

	/**
	 * Sets the affectation date of this affectation.
	 *
	 * @param affectationDate the affectation date of this affectation
	 */
	@Override
	public void setAffectationDate(Date affectationDate) {
		model.setAffectationDate(affectationDate);
	}

	/**
	 * Sets the affectation ID of this affectation.
	 *
	 * @param affectationId the affectation ID of this affectation
	 */
	@Override
	public void setAffectationId(long affectationId) {
		model.setAffectationId(affectationId);
	}

	/**
	 * Sets the expiration date of this affectation.
	 *
	 * @param expirationDate the expiration date of this affectation
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the org ID of this affectation.
	 *
	 * @param orgId the org ID of this affectation
	 */
	@Override
	public void setOrgId(long orgId) {
		model.setOrgId(orgId);
	}

	/**
	 * Sets the primary key of this affectation.
	 *
	 * @param primaryKey the primary key of this affectation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this affectation.
	 *
	 * @param schoolId the school ID of this affectation
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the type of this affectation.
	 *
	 * @param type the type of this affectation
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this affectation.
	 *
	 * @param userId the user ID of this affectation
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this affectation.
	 *
	 * @param userUuid the user uuid of this affectation
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected AffectationWrapper wrap(Affectation affectation) {
		return new AffectationWrapper(affectation);
	}

}