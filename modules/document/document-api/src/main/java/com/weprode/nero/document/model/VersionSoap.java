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

package com.weprode.nero.document.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.document.service.http.VersionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class VersionSoap implements Serializable {

	public static VersionSoap toSoapModel(Version model) {
		VersionSoap soapModel = new VersionSoap();

		soapModel.setFileVersionId(model.getFileVersionId());
		soapModel.setDlFileEntryId(model.getDlFileEntryId());
		soapModel.setVersionNumber(model.getVersionNumber());
		soapModel.setComment(model.getComment());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setViewCount(model.getViewCount());

		return soapModel;
	}

	public static VersionSoap[] toSoapModels(Version[] models) {
		VersionSoap[] soapModels = new VersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VersionSoap[][] toSoapModels(Version[][] models) {
		VersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VersionSoap[] toSoapModels(List<Version> models) {
		List<VersionSoap> soapModels = new ArrayList<VersionSoap>(
			models.size());

		for (Version model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VersionSoap[soapModels.size()]);
	}

	public VersionSoap() {
	}

	public long getPrimaryKey() {
		return _fileVersionId;
	}

	public void setPrimaryKey(long pk) {
		setFileVersionId(pk);
	}

	public long getFileVersionId() {
		return _fileVersionId;
	}

	public void setFileVersionId(long fileVersionId) {
		_fileVersionId = fileVersionId;
	}

	public long getDlFileEntryId() {
		return _dlFileEntryId;
	}

	public void setDlFileEntryId(long dlFileEntryId) {
		_dlFileEntryId = dlFileEntryId;
	}

	public double getVersionNumber() {
		return _versionNumber;
	}

	public void setVersionNumber(double versionNumber) {
		_versionNumber = versionNumber;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public long getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;
	}

	public long getViewCount() {
		return _viewCount;
	}

	public void setViewCount(long viewCount) {
		_viewCount = viewCount;
	}

	private long _fileVersionId;
	private long _dlFileEntryId;
	private double _versionNumber;
	private String _comment;
	private long _downloadCount;
	private long _viewCount;

}