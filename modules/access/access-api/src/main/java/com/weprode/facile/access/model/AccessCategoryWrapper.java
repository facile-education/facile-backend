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

package com.weprode.facile.access.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccessCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessCategory
 * @generated
 */
public class AccessCategoryWrapper
	extends BaseModelWrapper<AccessCategory>
	implements AccessCategory, ModelWrapper<AccessCategory> {

	public AccessCategoryWrapper(AccessCategory accessCategory) {
		super(accessCategory);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("categoryId", getCategoryId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("categoryName", getCategoryName());
		attributes.put("position", getPosition());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		String categoryName = (String)attributes.get("categoryName");

		if (categoryName != null) {
			setCategoryName(categoryName);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}
	}

	@Override
	public AccessCategory cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category ID of this access category.
	 *
	 * @return the category ID of this access category
	 */
	@Override
	public long getCategoryId() {
		return model.getCategoryId();
	}

	/**
	 * Returns the category name of this access category.
	 *
	 * @return the category name of this access category
	 */
	@Override
	public String getCategoryName() {
		return model.getCategoryName();
	}

	/**
	 * Returns the position of this access category.
	 *
	 * @return the position of this access category
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this access category.
	 *
	 * @return the primary key of this access category
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this access category.
	 *
	 * @return the school ID of this access category
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the uuid of this access category.
	 *
	 * @return the uuid of this access category
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the category ID of this access category.
	 *
	 * @param categoryId the category ID of this access category
	 */
	@Override
	public void setCategoryId(long categoryId) {
		model.setCategoryId(categoryId);
	}

	/**
	 * Sets the category name of this access category.
	 *
	 * @param categoryName the category name of this access category
	 */
	@Override
	public void setCategoryName(String categoryName) {
		model.setCategoryName(categoryName);
	}

	/**
	 * Sets the position of this access category.
	 *
	 * @param position the position of this access category
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this access category.
	 *
	 * @param primaryKey the primary key of this access category
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this access category.
	 *
	 * @param schoolId the school ID of this access category
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the uuid of this access category.
	 *
	 * @param uuid the uuid of this access category
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected AccessCategoryWrapper wrap(AccessCategory accessCategory) {
		return new AccessCategoryWrapper(accessCategory);
	}

}