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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.news.service.http.NewsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NewsSoap implements Serializable {

	public static NewsSoap toSoapModel(News model) {
		NewsSoap soapModel = new NewsSoap();

		soapModel.setNewsId(model.getNewsId());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());
		soapModel.setAuthorId(model.getAuthorId());
		soapModel.setIsSchoolNews(model.isIsSchoolNews());
		soapModel.setIsImportant(model.isIsImportant());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setModificationDate(model.getModificationDate());
		soapModel.setImageId(model.getImageId());

		return soapModel;
	}

	public static NewsSoap[] toSoapModels(News[] models) {
		NewsSoap[] soapModels = new NewsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsSoap[][] toSoapModels(News[][] models) {
		NewsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsSoap[] toSoapModels(List<News> models) {
		List<NewsSoap> soapModels = new ArrayList<NewsSoap>(models.size());

		for (News model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsSoap[soapModels.size()]);
	}

	public NewsSoap() {
	}

	public long getPrimaryKey() {
		return _newsId;
	}

	public void setPrimaryKey(long pk) {
		setNewsId(pk);
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public long getAuthorId() {
		return _authorId;
	}

	public void setAuthorId(long authorId) {
		_authorId = authorId;
	}

	public boolean getIsSchoolNews() {
		return _isSchoolNews;
	}

	public boolean isIsSchoolNews() {
		return _isSchoolNews;
	}

	public void setIsSchoolNews(boolean isSchoolNews) {
		_isSchoolNews = isSchoolNews;
	}

	public boolean getIsImportant() {
		return _isImportant;
	}

	public boolean isIsImportant() {
		return _isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		_isImportant = isImportant;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	private long _newsId;
	private String _title;
	private String _content;
	private long _authorId;
	private boolean _isSchoolNews;
	private boolean _isImportant;
	private Date _expirationDate;
	private Date _publicationDate;
	private Date _modificationDate;
	private long _imageId;

}