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

package com.weprode.facile.news.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NewsRead}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsRead
 * @generated
 */
public class NewsReadWrapper
	extends BaseModelWrapper<NewsRead>
	implements ModelWrapper<NewsRead>, NewsRead {

	public NewsReadWrapper(NewsRead newsRead) {
		super(newsRead);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsId", getNewsId());
		attributes.put("userId", getUserId());
		attributes.put("readDate", getReadDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsId = (Long)attributes.get("newsId");

		if (newsId != null) {
			setNewsId(newsId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date readDate = (Date)attributes.get("readDate");

		if (readDate != null) {
			setReadDate(readDate);
		}
	}

	@Override
	public NewsRead cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the news ID of this news read.
	 *
	 * @return the news ID of this news read
	 */
	@Override
	public long getNewsId() {
		return model.getNewsId();
	}

	/**
	 * Returns the primary key of this news read.
	 *
	 * @return the primary key of this news read
	 */
	@Override
	public com.weprode.facile.news.service.persistence.NewsReadPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the read date of this news read.
	 *
	 * @return the read date of this news read
	 */
	@Override
	public Date getReadDate() {
		return model.getReadDate();
	}

	/**
	 * Returns the user ID of this news read.
	 *
	 * @return the user ID of this news read
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this news read.
	 *
	 * @return the user uuid of this news read
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the news ID of this news read.
	 *
	 * @param newsId the news ID of this news read
	 */
	@Override
	public void setNewsId(long newsId) {
		model.setNewsId(newsId);
	}

	/**
	 * Sets the primary key of this news read.
	 *
	 * @param primaryKey the primary key of this news read
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.facile.news.service.persistence.NewsReadPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the read date of this news read.
	 *
	 * @param readDate the read date of this news read
	 */
	@Override
	public void setReadDate(Date readDate) {
		model.setReadDate(readDate);
	}

	/**
	 * Sets the user ID of this news read.
	 *
	 * @param userId the user ID of this news read
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this news read.
	 *
	 * @param userUuid the user uuid of this news read
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected NewsReadWrapper wrap(NewsRead newsRead) {
		return new NewsReadWrapper(newsRead);
	}

}