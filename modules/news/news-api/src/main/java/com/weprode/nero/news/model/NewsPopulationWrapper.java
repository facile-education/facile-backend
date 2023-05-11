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

package com.weprode.nero.news.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NewsPopulation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsPopulation
 * @generated
 */
public class NewsPopulationWrapper
	extends BaseModelWrapper<NewsPopulation>
	implements ModelWrapper<NewsPopulation>, NewsPopulation {

	public NewsPopulationWrapper(NewsPopulation newsPopulation) {
		super(newsPopulation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsId", getNewsId());
		attributes.put("groupId", getGroupId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsId = (Long)attributes.get("newsId");

		if (newsId != null) {
			setNewsId(newsId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public NewsPopulation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the group ID of this news population.
	 *
	 * @return the group ID of this news population
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the news ID of this news population.
	 *
	 * @return the news ID of this news population
	 */
	@Override
	public long getNewsId() {
		return model.getNewsId();
	}

	/**
	 * Returns the primary key of this news population.
	 *
	 * @return the primary key of this news population
	 */
	@Override
	public com.weprode.nero.news.service.persistence.NewsPopulationPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this news population.
	 *
	 * @return the role ID of this news population
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
	 * Sets the group ID of this news population.
	 *
	 * @param groupId the group ID of this news population
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the news ID of this news population.
	 *
	 * @param newsId the news ID of this news population
	 */
	@Override
	public void setNewsId(long newsId) {
		model.setNewsId(newsId);
	}

	/**
	 * Sets the primary key of this news population.
	 *
	 * @param primaryKey the primary key of this news population
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.news.service.persistence.NewsPopulationPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this news population.
	 *
	 * @param roleId the role ID of this news population
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected NewsPopulationWrapper wrap(NewsPopulation newsPopulation) {
		return new NewsPopulationWrapper(newsPopulation);
	}

}