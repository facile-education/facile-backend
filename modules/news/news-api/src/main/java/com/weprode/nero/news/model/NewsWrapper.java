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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link News}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see News
 * @generated
 */
public class NewsWrapper
	extends BaseModelWrapper<News> implements ModelWrapper<News>, News {

	public NewsWrapper(News news) {
		super(news);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsId", getNewsId());
		attributes.put("companyId", getCompanyId());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("authorId", getAuthorId());
		attributes.put("isSchoolNews", isIsSchoolNews());
		attributes.put("isImportant", isIsImportant());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("modificationDate", getModificationDate());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsId = (Long)attributes.get("newsId");

		if (newsId != null) {
			setNewsId(newsId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Long authorId = (Long)attributes.get("authorId");

		if (authorId != null) {
			setAuthorId(authorId);
		}

		Boolean isSchoolNews = (Boolean)attributes.get("isSchoolNews");

		if (isSchoolNews != null) {
			setIsSchoolNews(isSchoolNews);
		}

		Boolean isImportant = (Boolean)attributes.get("isImportant");

		if (isImportant != null) {
			setIsImportant(isImportant);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	@Override
	public News cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the author ID of this news.
	 *
	 * @return the author ID of this news
	 */
	@Override
	public long getAuthorId() {
		return model.getAuthorId();
	}

	/**
	 * Returns the company ID of this news.
	 *
	 * @return the company ID of this news
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the content of this news.
	 *
	 * @return the content of this news
	 */
	@Override
	public String getContent() {
		return model.getContent();
	}

	/**
	 * Returns the expiration date of this news.
	 *
	 * @return the expiration date of this news
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the image ID of this news.
	 *
	 * @return the image ID of this news
	 */
	@Override
	public long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the is important of this news.
	 *
	 * @return the is important of this news
	 */
	@Override
	public boolean getIsImportant() {
		return model.getIsImportant();
	}

	/**
	 * Returns the is school news of this news.
	 *
	 * @return the is school news of this news
	 */
	@Override
	public boolean getIsSchoolNews() {
		return model.getIsSchoolNews();
	}

	/**
	 * Returns the modification date of this news.
	 *
	 * @return the modification date of this news
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
	}

	/**
	 * Returns the news ID of this news.
	 *
	 * @return the news ID of this news
	 */
	@Override
	public long getNewsId() {
		return model.getNewsId();
	}

	/**
	 * Returns the primary key of this news.
	 *
	 * @return the primary key of this news
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publication date of this news.
	 *
	 * @return the publication date of this news
	 */
	@Override
	public Date getPublicationDate() {
		return model.getPublicationDate();
	}

	/**
	 * Returns the title of this news.
	 *
	 * @return the title of this news
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns <code>true</code> if this news is is important.
	 *
	 * @return <code>true</code> if this news is is important; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsImportant() {
		return model.isIsImportant();
	}

	/**
	 * Returns <code>true</code> if this news is is school news.
	 *
	 * @return <code>true</code> if this news is is school news; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsSchoolNews() {
		return model.isIsSchoolNews();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the author ID of this news.
	 *
	 * @param authorId the author ID of this news
	 */
	@Override
	public void setAuthorId(long authorId) {
		model.setAuthorId(authorId);
	}

	/**
	 * Sets the company ID of this news.
	 *
	 * @param companyId the company ID of this news
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the content of this news.
	 *
	 * @param content the content of this news
	 */
	@Override
	public void setContent(String content) {
		model.setContent(content);
	}

	/**
	 * Sets the expiration date of this news.
	 *
	 * @param expirationDate the expiration date of this news
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the image ID of this news.
	 *
	 * @param imageId the image ID of this news
	 */
	@Override
	public void setImageId(long imageId) {
		model.setImageId(imageId);
	}

	/**
	 * Sets whether this news is is important.
	 *
	 * @param isImportant the is important of this news
	 */
	@Override
	public void setIsImportant(boolean isImportant) {
		model.setIsImportant(isImportant);
	}

	/**
	 * Sets whether this news is is school news.
	 *
	 * @param isSchoolNews the is school news of this news
	 */
	@Override
	public void setIsSchoolNews(boolean isSchoolNews) {
		model.setIsSchoolNews(isSchoolNews);
	}

	/**
	 * Sets the modification date of this news.
	 *
	 * @param modificationDate the modification date of this news
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
	}

	/**
	 * Sets the news ID of this news.
	 *
	 * @param newsId the news ID of this news
	 */
	@Override
	public void setNewsId(long newsId) {
		model.setNewsId(newsId);
	}

	/**
	 * Sets the primary key of this news.
	 *
	 * @param primaryKey the primary key of this news
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publication date of this news.
	 *
	 * @param publicationDate the publication date of this news
	 */
	@Override
	public void setPublicationDate(Date publicationDate) {
		model.setPublicationDate(publicationDate);
	}

	/**
	 * Sets the title of this news.
	 *
	 * @param title the title of this news
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected NewsWrapper wrap(News news) {
		return new NewsWrapper(news);
	}

}