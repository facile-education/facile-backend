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

		attributes.put("newsId", getNewsId());
		attributes.put("fileId", getFileId());
		attributes.put("fileName", getFileName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsId = (Long)attributes.get("newsId");

		if (newsId != null) {
			setNewsId(newsId);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
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
	 * Returns the file name of this news attached file.
	 *
	 * @return the file name of this news attached file
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
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
	public com.weprode.nero.news.service.persistence.NewsAttachedFilePK
		getPrimaryKey() {

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
	 * Sets the file name of this news attached file.
	 *
	 * @param fileName the file name of this news attached file
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
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
	public void setPrimaryKey(
		com.weprode.nero.news.service.persistence.NewsAttachedFilePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected NewsAttachedFileWrapper wrap(NewsAttachedFile newsAttachedFile) {
		return new NewsAttachedFileWrapper(newsAttachedFile);
	}

}