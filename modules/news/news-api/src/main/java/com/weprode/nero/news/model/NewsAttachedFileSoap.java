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

		soapModel.setNewsFileId(model.getNewsFileId());
		soapModel.setNewsId(model.getNewsId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setFileId(model.getFileId());

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

	public long getPrimaryKey() {
		return _newsFileId;
	}

	public void setPrimaryKey(long pk) {
		setNewsFileId(pk);
	}

	public long getNewsFileId() {
		return _newsFileId;
	}

	public void setNewsFileId(long newsFileId) {
		_newsFileId = newsFileId;
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	private long _newsFileId;
	private long _newsId;
	private long _groupId;
	private long _fileId;

}