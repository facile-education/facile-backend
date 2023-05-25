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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.progression.service.http.ProgressionItemServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ProgressionItemSoap implements Serializable {

	public static ProgressionItemSoap toSoapModel(ProgressionItem model) {
		ProgressionItemSoap soapModel = new ProgressionItemSoap();

		soapModel.setProgressionItemId(model.getProgressionItemId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setProgressionId(model.getProgressionId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setHomeworkId(model.getHomeworkId());
		soapModel.setProgressionFolderId(model.getProgressionFolderId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemName(model.getItemName());
		soapModel.setIsHomework(model.isIsHomework());
		soapModel.setDuration(model.getDuration());
		soapModel.setType(model.getType());
		soapModel.setOrder(model.getOrder());

		return soapModel;
	}

	public static ProgressionItemSoap[] toSoapModels(ProgressionItem[] models) {
		ProgressionItemSoap[] soapModels =
			new ProgressionItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProgressionItemSoap[][] toSoapModels(
		ProgressionItem[][] models) {

		ProgressionItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ProgressionItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProgressionItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProgressionItemSoap[] toSoapModels(
		List<ProgressionItem> models) {

		List<ProgressionItemSoap> soapModels =
			new ArrayList<ProgressionItemSoap>(models.size());

		for (ProgressionItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProgressionItemSoap[soapModels.size()]);
	}

	public ProgressionItemSoap() {
	}

	public long getPrimaryKey() {
		return _progressionItemId;
	}

	public void setPrimaryKey(long pk) {
		setProgressionItemId(pk);
	}

	public long getProgressionItemId() {
		return _progressionItemId;
	}

	public void setProgressionItemId(long progressionItemId) {
		_progressionItemId = progressionItemId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getProgressionId() {
		return _progressionId;
	}

	public void setProgressionId(long progressionId) {
		_progressionId = progressionId;
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public long getHomeworkId() {
		return _homeworkId;
	}

	public void setHomeworkId(long homeworkId) {
		_homeworkId = homeworkId;
	}

	public long getProgressionFolderId() {
		return _progressionFolderId;
	}

	public void setProgressionFolderId(long progressionFolderId) {
		_progressionFolderId = progressionFolderId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public boolean getIsHomework() {
		return _isHomework;
	}

	public boolean isIsHomework() {
		return _isHomework;
	}

	public void setIsHomework(boolean isHomework) {
		_isHomework = isHomework;
	}

	public String getDuration() {
		return _duration;
	}

	public void setDuration(String duration) {
		_duration = duration;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	private long _progressionItemId;
	private long _companyId;
	private long _progressionId;
	private long _sessionId;
	private long _homeworkId;
	private long _progressionFolderId;
	private Date _modifiedDate;
	private String _itemName;
	private boolean _isHomework;
	private String _duration;
	private int _type;
	private int _order;

}