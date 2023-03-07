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

package com.weprode.nero.group.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GroupMembership}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembership
 * @generated
 */
public class GroupMembershipWrapper
	extends BaseModelWrapper<GroupMembership>
	implements GroupMembership, ModelWrapper<GroupMembership> {

	public GroupMembershipWrapper(GroupMembership groupMembership) {
		super(groupMembership);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("membershipId", getMembershipId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("fullYear", isFullYear());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long membershipId = (Long)attributes.get("membershipId");

		if (membershipId != null) {
			setMembershipId(membershipId);
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
	public GroupMembership cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end date of this group membership.
	 *
	 * @return the end date of this group membership
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the full year of this group membership.
	 *
	 * @return the full year of this group membership
	 */
	@Override
	public boolean getFullYear() {
		return model.getFullYear();
	}

	/**
	 * Returns the group ID of this group membership.
	 *
	 * @return the group ID of this group membership
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the membership ID of this group membership.
	 *
	 * @return the membership ID of this group membership
	 */
	@Override
	public long getMembershipId() {
		return model.getMembershipId();
	}

	/**
	 * Returns the primary key of this group membership.
	 *
	 * @return the primary key of this group membership
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this group membership.
	 *
	 * @return the start date of this group membership
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this group membership.
	 *
	 * @return the user ID of this group membership
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this group membership.
	 *
	 * @return the user uuid of this group membership
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this group membership is full year.
	 *
	 * @return <code>true</code> if this group membership is full year; <code>false</code> otherwise
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
	 * Sets the end date of this group membership.
	 *
	 * @param endDate the end date of this group membership
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets whether this group membership is full year.
	 *
	 * @param fullYear the full year of this group membership
	 */
	@Override
	public void setFullYear(boolean fullYear) {
		model.setFullYear(fullYear);
	}

	/**
	 * Sets the group ID of this group membership.
	 *
	 * @param groupId the group ID of this group membership
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the membership ID of this group membership.
	 *
	 * @param membershipId the membership ID of this group membership
	 */
	@Override
	public void setMembershipId(long membershipId) {
		model.setMembershipId(membershipId);
	}

	/**
	 * Sets the primary key of this group membership.
	 *
	 * @param primaryKey the primary key of this group membership
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this group membership.
	 *
	 * @param startDate the start date of this group membership
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this group membership.
	 *
	 * @param userId the user ID of this group membership
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this group membership.
	 *
	 * @param userUuid the user uuid of this group membership
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected GroupMembershipWrapper wrap(GroupMembership groupMembership) {
		return new GroupMembershipWrapper(groupMembership);
	}

}