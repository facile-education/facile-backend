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
 * This class is a wrapper for {@link MembershipActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivity
 * @generated
 */
public class MembershipActivityWrapper
	extends BaseModelWrapper<MembershipActivity>
	implements MembershipActivity, ModelWrapper<MembershipActivity> {

	public MembershipActivityWrapper(MembershipActivity membershipActivity) {
		super(membershipActivity);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("membershipActivityId", getMembershipActivityId());
		attributes.put("groupId", getGroupId());
		attributes.put("actionUserId", getActionUserId());
		attributes.put("targetUserIds", getTargetUserIds());
		attributes.put("incoming", isIncoming());
		attributes.put("movementDate", getMovementDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long membershipActivityId = (Long)attributes.get(
			"membershipActivityId");

		if (membershipActivityId != null) {
			setMembershipActivityId(membershipActivityId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long actionUserId = (Long)attributes.get("actionUserId");

		if (actionUserId != null) {
			setActionUserId(actionUserId);
		}

		String targetUserIds = (String)attributes.get("targetUserIds");

		if (targetUserIds != null) {
			setTargetUserIds(targetUserIds);
		}

		Boolean incoming = (Boolean)attributes.get("incoming");

		if (incoming != null) {
			setIncoming(incoming);
		}

		Date movementDate = (Date)attributes.get("movementDate");

		if (movementDate != null) {
			setMovementDate(movementDate);
		}
	}

	@Override
	public MembershipActivity cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the action user ID of this membership activity.
	 *
	 * @return the action user ID of this membership activity
	 */
	@Override
	public long getActionUserId() {
		return model.getActionUserId();
	}

	/**
	 * Returns the action user uuid of this membership activity.
	 *
	 * @return the action user uuid of this membership activity
	 */
	@Override
	public String getActionUserUuid() {
		return model.getActionUserUuid();
	}

	/**
	 * Returns the group ID of this membership activity.
	 *
	 * @return the group ID of this membership activity
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the incoming of this membership activity.
	 *
	 * @return the incoming of this membership activity
	 */
	@Override
	public boolean getIncoming() {
		return model.getIncoming();
	}

	/**
	 * Returns the membership activity ID of this membership activity.
	 *
	 * @return the membership activity ID of this membership activity
	 */
	@Override
	public long getMembershipActivityId() {
		return model.getMembershipActivityId();
	}

	/**
	 * Returns the movement date of this membership activity.
	 *
	 * @return the movement date of this membership activity
	 */
	@Override
	public Date getMovementDate() {
		return model.getMovementDate();
	}

	/**
	 * Returns the primary key of this membership activity.
	 *
	 * @return the primary key of this membership activity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the target user IDs of this membership activity.
	 *
	 * @return the target user IDs of this membership activity
	 */
	@Override
	public String getTargetUserIds() {
		return model.getTargetUserIds();
	}

	/**
	 * Returns <code>true</code> if this membership activity is incoming.
	 *
	 * @return <code>true</code> if this membership activity is incoming; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncoming() {
		return model.isIncoming();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action user ID of this membership activity.
	 *
	 * @param actionUserId the action user ID of this membership activity
	 */
	@Override
	public void setActionUserId(long actionUserId) {
		model.setActionUserId(actionUserId);
	}

	/**
	 * Sets the action user uuid of this membership activity.
	 *
	 * @param actionUserUuid the action user uuid of this membership activity
	 */
	@Override
	public void setActionUserUuid(String actionUserUuid) {
		model.setActionUserUuid(actionUserUuid);
	}

	/**
	 * Sets the group ID of this membership activity.
	 *
	 * @param groupId the group ID of this membership activity
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets whether this membership activity is incoming.
	 *
	 * @param incoming the incoming of this membership activity
	 */
	@Override
	public void setIncoming(boolean incoming) {
		model.setIncoming(incoming);
	}

	/**
	 * Sets the membership activity ID of this membership activity.
	 *
	 * @param membershipActivityId the membership activity ID of this membership activity
	 */
	@Override
	public void setMembershipActivityId(long membershipActivityId) {
		model.setMembershipActivityId(membershipActivityId);
	}

	/**
	 * Sets the movement date of this membership activity.
	 *
	 * @param movementDate the movement date of this membership activity
	 */
	@Override
	public void setMovementDate(Date movementDate) {
		model.setMovementDate(movementDate);
	}

	/**
	 * Sets the primary key of this membership activity.
	 *
	 * @param primaryKey the primary key of this membership activity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the target user IDs of this membership activity.
	 *
	 * @param targetUserIds the target user IDs of this membership activity
	 */
	@Override
	public void setTargetUserIds(String targetUserIds) {
		model.setTargetUserIds(targetUserIds);
	}

	@Override
	protected MembershipActivityWrapper wrap(
		MembershipActivity membershipActivity) {

		return new MembershipActivityWrapper(membershipActivity);
	}

}