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

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the BroadcastRule service. Represents a row in the &quot;Application_BroadcastRule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.application.model.impl.BroadcastRuleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.application.model.impl.BroadcastRuleImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRule
 * @generated
 */
@ProviderType
public interface BroadcastRuleModel extends BaseModel<BroadcastRule> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a broadcast rule model instance should use the {@link BroadcastRule} interface instead.
	 */

	/**
	 * Returns the primary key of this broadcast rule.
	 *
	 * @return the primary key of this broadcast rule
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this broadcast rule.
	 *
	 * @param primaryKey the primary key of this broadcast rule
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the broadcast rule ID of this broadcast rule.
	 *
	 * @return the broadcast rule ID of this broadcast rule
	 */
	public long getBroadcastRuleId();

	/**
	 * Sets the broadcast rule ID of this broadcast rule.
	 *
	 * @param broadcastRuleId the broadcast rule ID of this broadcast rule
	 */
	public void setBroadcastRuleId(long broadcastRuleId);

	/**
	 * Returns the application ID of this broadcast rule.
	 *
	 * @return the application ID of this broadcast rule
	 */
	public long getApplicationId();

	/**
	 * Sets the application ID of this broadcast rule.
	 *
	 * @param applicationId the application ID of this broadcast rule
	 */
	public void setApplicationId(long applicationId);

	/**
	 * Returns the school ID of this broadcast rule.
	 *
	 * @return the school ID of this broadcast rule
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this broadcast rule.
	 *
	 * @param schoolId the school ID of this broadcast rule
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the role ID of this broadcast rule.
	 *
	 * @return the role ID of this broadcast rule
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this broadcast rule.
	 *
	 * @param roleId the role ID of this broadcast rule
	 */
	public void setRoleId(long roleId);

	/**
	 * Returns the org ID of this broadcast rule.
	 *
	 * @return the org ID of this broadcast rule
	 */
	public long getOrgId();

	/**
	 * Sets the org ID of this broadcast rule.
	 *
	 * @param orgId the org ID of this broadcast rule
	 */
	public void setOrgId(long orgId);

	/**
	 * Returns the group ID of this broadcast rule.
	 *
	 * @return the group ID of this broadcast rule
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this broadcast rule.
	 *
	 * @param groupId the group ID of this broadcast rule
	 */
	public void setGroupId(long groupId);

	@Override
	public BroadcastRule cloneWithOriginalValues();

}