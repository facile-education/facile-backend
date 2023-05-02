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

package com.weprode.nero.help.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelpItemRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemRole
 * @generated
 */
public class HelpItemRoleWrapper
	extends BaseModelWrapper<HelpItemRole>
	implements HelpItemRole, ModelWrapper<HelpItemRole> {

	public HelpItemRoleWrapper(HelpItemRole helpItemRole) {
		super(helpItemRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("helpItemRoleId", getHelpItemRoleId());
		attributes.put("itemId", getItemId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long helpItemRoleId = (Long)attributes.get("helpItemRoleId");

		if (helpItemRoleId != null) {
			setHelpItemRoleId(helpItemRoleId);
		}

		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public HelpItemRole cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the help item role ID of this help item role.
	 *
	 * @return the help item role ID of this help item role
	 */
	@Override
	public long getHelpItemRoleId() {
		return model.getHelpItemRoleId();
	}

	/**
	 * Returns the item ID of this help item role.
	 *
	 * @return the item ID of this help item role
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the primary key of this help item role.
	 *
	 * @return the primary key of this help item role
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this help item role.
	 *
	 * @return the role ID of this help item role
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the help item role ID of this help item role.
	 *
	 * @param helpItemRoleId the help item role ID of this help item role
	 */
	@Override
	public void setHelpItemRoleId(long helpItemRoleId) {
		model.setHelpItemRoleId(helpItemRoleId);
	}

	/**
	 * Sets the item ID of this help item role.
	 *
	 * @param itemId the item ID of this help item role
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the primary key of this help item role.
	 *
	 * @param primaryKey the primary key of this help item role
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this help item role.
	 *
	 * @param roleId the role ID of this help item role
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected HelpItemRoleWrapper wrap(HelpItemRole helpItemRole) {
		return new HelpItemRoleWrapper(helpItemRole);
	}

}