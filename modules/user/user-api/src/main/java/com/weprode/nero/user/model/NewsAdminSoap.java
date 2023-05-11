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

package com.weprode.nero.user.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.user.service.http.NewsAdminServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NewsAdminSoap implements Serializable {

	public static NewsAdminSoap toSoapModel(NewsAdmin model) {
		NewsAdminSoap soapModel = new NewsAdminSoap();

		soapModel.setNewsAdminId(model.getNewsAdminId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSchoolId(model.getSchoolId());

		return soapModel;
	}

	public static NewsAdminSoap[] toSoapModels(NewsAdmin[] models) {
		NewsAdminSoap[] soapModels = new NewsAdminSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsAdminSoap[][] toSoapModels(NewsAdmin[][] models) {
		NewsAdminSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsAdminSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsAdminSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsAdminSoap[] toSoapModels(List<NewsAdmin> models) {
		List<NewsAdminSoap> soapModels = new ArrayList<NewsAdminSoap>(
			models.size());

		for (NewsAdmin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsAdminSoap[soapModels.size()]);
	}

	public NewsAdminSoap() {
	}

	public long getPrimaryKey() {
		return _newsAdminId;
	}

	public void setPrimaryKey(long pk) {
		setNewsAdminId(pk);
	}

	public long getNewsAdminId() {
		return _newsAdminId;
	}

	public void setNewsAdminId(long newsAdminId) {
		_newsAdminId = newsAdminId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	private long _newsAdminId;
	private long _userId;
	private long _schoolId;

}