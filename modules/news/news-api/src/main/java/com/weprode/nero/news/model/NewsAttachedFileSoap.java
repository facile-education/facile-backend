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

import com.weprode.nero.news.service.persistence.NewsAttachedFilePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NewsAttachedFileSoap implements Serializable {

	public static NewsAttachedFileSoap toSoapModel(NewsAttachedFile model) {
		NewsAttachedFileSoap soapModel = new NewsAttachedFileSoap();

		soapModel.setNewsId(model.getNewsId());
		soapModel.setFileId(model.getFileId());
		soapModel.setFileName(model.getFileName());

		return soapModel;
	}

	public static NewsAttachedFileSoap[] toSoapModels(
		NewsAttachedFile[] models) {

		NewsAttachedFileSoap[] soapModels =
			new NewsAttachedFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsAttachedFileSoap[][] toSoapModels(
		NewsAttachedFile[][] models) {

		NewsAttachedFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new NewsAttachedFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsAttachedFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsAttachedFileSoap[] toSoapModels(
		List<NewsAttachedFile> models) {

		List<NewsAttachedFileSoap> soapModels =
			new ArrayList<NewsAttachedFileSoap>(models.size());

		for (NewsAttachedFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsAttachedFileSoap[soapModels.size()]);
	}

	public NewsAttachedFileSoap() {
	}

	public NewsAttachedFilePK getPrimaryKey() {
		return new NewsAttachedFilePK(_newsId, _fileId);
	}

	public void setPrimaryKey(NewsAttachedFilePK pk) {
		setNewsId(pk.newsId);
		setFileId(pk.fileId);
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	private long _newsId;
	private long _fileId;
	private String _fileName;

}