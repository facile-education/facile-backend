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
 * This class is a wrapper for {@link NewsAttachedFile}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFile
 * @generated
 */
public class NewsAttachedFileWrapper
	extends BaseModelWrapper<NewsAttachedFile>
	implements ModelWrapper<NewsAttachedFile>, NewsAttachedFile {

	public NewsAttachedFileWrapper(NewsAttachedFile newsAttachedFile) {
		super(newsAttachedFile);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsFileId", getNewsFileId());
		attributes.put("newsId", getNewsId());
		attributes.put("groupId", getGroupId());
		attributes.put("fileId", getFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsFileId = (Long)attributes.get("newsFileId");

		if (newsFileId != null) {
			setNewsFileId(newsFileId);
		}

		Long newsId = (Long)attributes.get("newsId");

		if (newsId != null) {
			setNewsId(newsId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}
	}

	@Override
	public NewsAttachedFile cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the file ID of this news attached file.
	 *
	 * @return the file ID of this news attached file
	 */
	@Override
	public long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the group ID of this news attached file.
	 *
	 * @return the group ID of this news attached file
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the news file ID of this news attached file.
	 *
	 * @return the news file ID of this news attached file
	 */
	@Override
	public long getNewsFileId() {
		return model.getNewsFileId();
	}

	/**
	 * Returns the news ID of this news attached file.
	 *
	 * @return the news ID of this news attached file
	 */
	@Override
	public long getNewsId() {
		return model.getNewsId();
	}

	/**
	 * Returns the primary key of this news attached file.
	 *
	 * @return the primary key of this news attached file
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
	 * Sets the file ID of this news attached file.
	 *
	 * @param fileId the file ID of this news attached file
	 */
	@Override
	public void setFileId(long fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the group ID of this news attached file.
	 *
	 * @param groupId the group ID of this news attached file
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the news file ID of this news attached file.
	 *
	 * @param newsFileId the news file ID of this news attached file
	 */
	@Override
	public void setNewsFileId(long newsFileId) {
		model.setNewsFileId(newsFileId);
	}

	/**
	 * Sets the news ID of this news attached file.
	 *
	 * @param newsId the news ID of this news attached file
	 */
	@Override
	public void setNewsId(long newsId) {
		model.setNewsId(newsId);
	}

	/**
	 * Sets the primary key of this news attached file.
	 *
	 * @param primaryKey the primary key of this news attached file
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected NewsAttachedFileWrapper wrap(NewsAttachedFile newsAttachedFile) {
		return new NewsAttachedFileWrapper(newsAttachedFile);
	}

}