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

import com.weprode.nero.news.service.persistence.NewsReadPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NewsReadSoap implements Serializable {

	public static NewsReadSoap toSoapModel(NewsRead model) {
		NewsReadSoap soapModel = new NewsReadSoap();

		soapModel.setNewsId(model.getNewsId());
		soapModel.setUserId(model.getUserId());
		soapModel.setReadDate(model.getReadDate());

		return soapModel;
	}

	public static NewsReadSoap[] toSoapModels(NewsRead[] models) {
		NewsReadSoap[] soapModels = new NewsReadSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsReadSoap[][] toSoapModels(NewsRead[][] models) {
		NewsReadSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsReadSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsReadSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsReadSoap[] toSoapModels(List<NewsRead> models) {
		List<NewsReadSoap> soapModels = new ArrayList<NewsReadSoap>(
			models.size());

		for (NewsRead model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsReadSoap[soapModels.size()]);
	}

	public NewsReadSoap() {
	}

	public NewsReadPK getPrimaryKey() {
		return new NewsReadPK(_newsId, _userId);
	}

	public void setPrimaryKey(NewsReadPK pk) {
		setNewsId(pk.newsId);
		setUserId(pk.userId);
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getReadDate() {
		return _readDate;
	}

	public void setReadDate(Date readDate) {
		_readDate = readDate;
	}

	private long _newsId;
	private long _userId;
	private Date _readDate;

}