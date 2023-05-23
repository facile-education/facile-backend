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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GroupColor}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupColor
 * @generated
 */
public class GroupColorWrapper
	extends BaseModelWrapper<GroupColor>
	implements GroupColor, ModelWrapper<GroupColor> {

	public GroupColorWrapper(GroupColor groupColor) {
		super(groupColor);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("groupId", getGroupId());
		attributes.put("color", getColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}
	}

	@Override
	public GroupColor cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the color of this group color.
	 *
	 * @return the color of this group color
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the group ID of this group color.
	 *
	 * @return the group ID of this group color
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this group color.
	 *
	 * @return the primary key of this group color
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this group color.
	 *
	 * @param color the color of this group color
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the group ID of this group color.
	 *
	 * @param groupId the group ID of this group color
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this group color.
	 *
	 * @param primaryKey the primary key of this group color
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected GroupColorWrapper wrap(GroupColor groupColor) {
		return new GroupColorWrapper(groupColor);
	}

}