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
 * This class is a wrapper for {@link BroadcastRule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRule
 * @generated
 */
public class BroadcastRuleWrapper
	extends BaseModelWrapper<BroadcastRule>
	implements BroadcastRule, ModelWrapper<BroadcastRule> {

	public BroadcastRuleWrapper(BroadcastRule broadcastRule) {
		super(broadcastRule);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("broadcastRuleId", getBroadcastRuleId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("roleId", getRoleId());
		attributes.put("orgId", getOrgId());
		attributes.put("groupId", getGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long broadcastRuleId = (Long)attributes.get("broadcastRuleId");

		if (broadcastRuleId != null) {
			setBroadcastRuleId(broadcastRuleId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long orgId = (Long)attributes.get("orgId");

		if (orgId != null) {
			setOrgId(orgId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}
	}

	@Override
	public BroadcastRule cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application ID of this broadcast rule.
	 *
	 * @return the application ID of this broadcast rule
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the broadcast rule ID of this broadcast rule.
	 *
	 * @return the broadcast rule ID of this broadcast rule
	 */
	@Override
	public long getBroadcastRuleId() {
		return model.getBroadcastRuleId();
	}

	/**
	 * Returns the group ID of this broadcast rule.
	 *
	 * @return the group ID of this broadcast rule
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the org ID of this broadcast rule.
	 *
	 * @return the org ID of this broadcast rule
	 */
	@Override
	public long getOrgId() {
		return model.getOrgId();
	}

	/**
	 * Returns the primary key of this broadcast rule.
	 *
	 * @return the primary key of this broadcast rule
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this broadcast rule.
	 *
	 * @return the role ID of this broadcast rule
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the school ID of this broadcast rule.
	 *
	 * @return the school ID of this broadcast rule
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
	 * Sets the application ID of this broadcast rule.
	 *
	 * @param applicationId the application ID of this broadcast rule
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the broadcast rule ID of this broadcast rule.
	 *
	 * @param broadcastRuleId the broadcast rule ID of this broadcast rule
	 */
	@Override
	public void setBroadcastRuleId(long broadcastRuleId) {
		model.setBroadcastRuleId(broadcastRuleId);
	}

	/**
	 * Sets the group ID of this broadcast rule.
	 *
	 * @param groupId the group ID of this broadcast rule
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the org ID of this broadcast rule.
	 *
	 * @param orgId the org ID of this broadcast rule
	 */
	@Override
	public void setOrgId(long orgId) {
		model.setOrgId(orgId);
	}

	/**
	 * Sets the primary key of this broadcast rule.
	 *
	 * @param primaryKey the primary key of this broadcast rule
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this broadcast rule.
	 *
	 * @param roleId the role ID of this broadcast rule
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	/**
	 * Sets the school ID of this broadcast rule.
	 *
	 * @param schoolId the school ID of this broadcast rule
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	@Override
	protected BroadcastRuleWrapper wrap(BroadcastRule broadcastRule) {
		return new BroadcastRuleWrapper(broadcastRule);
	}

}