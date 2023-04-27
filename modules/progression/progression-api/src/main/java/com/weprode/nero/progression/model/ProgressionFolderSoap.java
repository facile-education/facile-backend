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

package com.weprode.nero.progression.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.progression.service.http.ProgressionFolderServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ProgressionFolderSoap implements Serializable {

	public static ProgressionFolderSoap toSoapModel(ProgressionFolder model) {
		ProgressionFolderSoap soapModel = new ProgressionFolderSoap();

		soapModel.setProgressionFolderId(model.getProgressionFolderId());
		soapModel.setProgressionId(model.getProgressionId());
		soapModel.setParentFolderId(model.getParentFolderId());
		soapModel.setFolderName(model.getFolderName());
		soapModel.setOrder(model.getOrder());

		return soapModel;
	}

	public static ProgressionFolderSoap[] toSoapModels(
		ProgressionFolder[] models) {

		ProgressionFolderSoap[] soapModels =
			new ProgressionFolderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProgressionFolderSoap[][] toSoapModels(
		ProgressionFolder[][] models) {

		ProgressionFolderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ProgressionFolderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProgressionFolderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProgressionFolderSoap[] toSoapModels(
		List<ProgressionFolder> models) {

		List<ProgressionFolderSoap> soapModels =
			new ArrayList<ProgressionFolderSoap>(models.size());

		for (ProgressionFolder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProgressionFolderSoap[soapModels.size()]);
	}

	public ProgressionFolderSoap() {
	}

	public long getPrimaryKey() {
		return _progressionFolderId;
	}

	public void setPrimaryKey(long pk) {
		setProgressionFolderId(pk);
	}

	public long getProgressionFolderId() {
		return _progressionFolderId;
	}

	public void setProgressionFolderId(long progressionFolderId) {
		_progressionFolderId = progressionFolderId;
	}

	public long getProgressionId() {
		return _progressionId;
	}

	public void setProgressionId(long progressionId) {
		_progressionId = progressionId;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public String getFolderName() {
		return _folderName;
	}

	public void setFolderName(String folderName) {
		_folderName = folderName;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	private long _progressionFolderId;
	private long _progressionId;
	private long _parentFolderId;
	private String _folderName;
	private int _order;

}