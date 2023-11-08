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

package com.weprode.facile.help.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelpCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategory
 * @generated
 */
public class HelpCategoryWrapper
	extends BaseModelWrapper<HelpCategory>
	implements HelpCategory, ModelWrapper<HelpCategory> {

	public HelpCategoryWrapper(HelpCategory helpCategory) {
		super(helpCategory);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("categoryId", getCategoryId());
		attributes.put("categoryName", getCategoryName());
		attributes.put("serviceId", getServiceId());
		attributes.put("position", getPosition());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String categoryName = (String)attributes.get("categoryName");

		if (categoryName != null) {
			setCategoryName(categoryName);
		}

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}
	}

	@Override
	public HelpCategory cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category ID of this help category.
	 *
	 * @return the category ID of this help category
	 */
	@Override
	public long getCategoryId() {
		return model.getCategoryId();
	}

	/**
	 * Returns the category name of this help category.
	 *
	 * @return the category name of this help category
	 */
	@Override
	public String getCategoryName() {
		return model.getCategoryName();
	}

	/**
	 * Returns the position of this help category.
	 *
	 * @return the position of this help category
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this help category.
	 *
	 * @return the primary key of this help category
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the service ID of this help category.
	 *
	 * @return the service ID of this help category
	 */
	@Override
	public long getServiceId() {
		return model.getServiceId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the category ID of this help category.
	 *
	 * @param categoryId the category ID of this help category
	 */
	@Override
	public void setCategoryId(long categoryId) {
		model.setCategoryId(categoryId);
	}

	/**
	 * Sets the category name of this help category.
	 *
	 * @param categoryName the category name of this help category
	 */
	@Override
	public void setCategoryName(String categoryName) {
		model.setCategoryName(categoryName);
	}

	/**
	 * Sets the position of this help category.
	 *
	 * @param position the position of this help category
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this help category.
	 *
	 * @param primaryKey the primary key of this help category
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the service ID of this help category.
	 *
	 * @param serviceId the service ID of this help category
	 */
	@Override
	public void setServiceId(long serviceId) {
		model.setServiceId(serviceId);
	}

	@Override
	protected HelpCategoryWrapper wrap(HelpCategory helpCategory) {
		return new HelpCategoryWrapper(helpCategory);
	}

}