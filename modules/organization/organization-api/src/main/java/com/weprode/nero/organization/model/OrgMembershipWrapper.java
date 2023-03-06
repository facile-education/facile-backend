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

package com.weprode.nero.organization.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OrgMembership}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMembership
 * @generated
 */
public class OrgMembershipWrapper
	extends BaseModelWrapper<OrgMembership>
	implements ModelWrapper<OrgMembership>, OrgMembership {

	public OrgMembershipWrapper(OrgMembership orgMembership) {
		super(orgMembership);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("orgMemberId", getOrgMemberId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("fullYear", isFullYear());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long orgMemberId = (Long)attributes.get("orgMemberId");

		if (orgMemberId != null) {
			setOrgMemberId(orgMemberId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean fullYear = (Boolean)attributes.get("fullYear");

		if (fullYear != null) {
			setFullYear(fullYear);
		}
	}

	@Override
	public OrgMembership cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end date of this org membership.
	 *
	 * @return the end date of this org membership
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the full year of this org membership.
	 *
	 * @return the full year of this org membership
	 */
	@Override
	public boolean getFullYear() {
		return model.getFullYear();
	}

	/**
	 * Returns the group ID of this org membership.
	 *
	 * @return the group ID of this org membership
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the org member ID of this org membership.
	 *
	 * @return the org member ID of this org membership
	 */
	@Override
	public long getOrgMemberId() {
		return model.getOrgMemberId();
	}

	/**
	 * Returns the primary key of this org membership.
	 *
	 * @return the primary key of this org membership
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this org membership.
	 *
	 * @return the start date of this org membership
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this org membership.
	 *
	 * @return the user ID of this org membership
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this org membership.
	 *
	 * @return the user uuid of this org membership
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this org membership is full year.
	 *
	 * @return <code>true</code> if this org membership is full year; <code>false</code> otherwise
	 */
	@Override
	public boolean isFullYear() {
		return model.isFullYear();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end date of this org membership.
	 *
	 * @param endDate the end date of this org membership
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets whether this org membership is full year.
	 *
	 * @param fullYear the full year of this org membership
	 */
	@Override
	public void setFullYear(boolean fullYear) {
		model.setFullYear(fullYear);
	}

	/**
	 * Sets the group ID of this org membership.
	 *
	 * @param groupId the group ID of this org membership
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the org member ID of this org membership.
	 *
	 * @param orgMemberId the org member ID of this org membership
	 */
	@Override
	public void setOrgMemberId(long orgMemberId) {
		model.setOrgMemberId(orgMemberId);
	}

	/**
	 * Sets the primary key of this org membership.
	 *
	 * @param primaryKey the primary key of this org membership
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this org membership.
	 *
	 * @param startDate the start date of this org membership
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this org membership.
	 *
	 * @param userId the user ID of this org membership
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this org membership.
	 *
	 * @param userUuid the user uuid of this org membership
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected OrgMembershipWrapper wrap(OrgMembership orgMembership) {
		return new OrgMembershipWrapper(orgMembership);
	}

}