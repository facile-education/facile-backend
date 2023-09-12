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

package com.weprode.nero.about.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.about.service.http.VersionNoteServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class VersionNoteSoap implements Serializable {

	public static VersionNoteSoap toSoapModel(VersionNote model) {
		VersionNoteSoap soapModel = new VersionNoteSoap();

		soapModel.setVersionNoteId(model.getVersionNoteId());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());
		soapModel.setVersionNoteDate(model.getVersionNoteDate());

		return soapModel;
	}

	public static VersionNoteSoap[] toSoapModels(VersionNote[] models) {
		VersionNoteSoap[] soapModels = new VersionNoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VersionNoteSoap[][] toSoapModels(VersionNote[][] models) {
		VersionNoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VersionNoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VersionNoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VersionNoteSoap[] toSoapModels(List<VersionNote> models) {
		List<VersionNoteSoap> soapModels = new ArrayList<VersionNoteSoap>(
			models.size());

		for (VersionNote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VersionNoteSoap[soapModels.size()]);
	}

	public VersionNoteSoap() {
	}

	public long getPrimaryKey() {
		return _versionNoteId;
	}

	public void setPrimaryKey(long pk) {
		setVersionNoteId(pk);
	}

	public long getVersionNoteId() {
		return _versionNoteId;
	}

	public void setVersionNoteId(long versionNoteId) {
		_versionNoteId = versionNoteId;
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

	public Date getVersionNoteDate() {
		return _versionNoteDate;
	}

	public void setVersionNoteDate(Date versionNoteDate) {
		_versionNoteDate = versionNoteDate;
	}

	private long _versionNoteId;
	private String _title;
	private String _content;
	private Date _versionNoteDate;

}