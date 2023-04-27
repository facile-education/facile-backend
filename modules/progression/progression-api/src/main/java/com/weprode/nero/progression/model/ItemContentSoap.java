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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ItemContentSoap implements Serializable {

	public static ItemContentSoap toSoapModel(ItemContent model) {
		ItemContentSoap soapModel = new ItemContentSoap();

		soapModel.setContentId(model.getContentId());
		soapModel.setProgressionItemId(model.getProgressionItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContentName(model.getContentName());
		soapModel.setContentValue(model.getContentValue());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setContentType(model.getContentType());
		soapModel.setOrder(model.getOrder());
		soapModel.setIsToBeCompleted(model.isIsToBeCompleted());

		return soapModel;
	}

	public static ItemContentSoap[] toSoapModels(ItemContent[] models) {
		ItemContentSoap[] soapModels = new ItemContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemContentSoap[][] toSoapModels(ItemContent[][] models) {
		ItemContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemContentSoap[] toSoapModels(List<ItemContent> models) {
		List<ItemContentSoap> soapModels = new ArrayList<ItemContentSoap>(
			models.size());

		for (ItemContent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemContentSoap[soapModels.size()]);
	}

	public ItemContentSoap() {
	}

	public long getPrimaryKey() {
		return _contentId;
	}

	public void setPrimaryKey(long pk) {
		setContentId(pk);
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public long getProgressionItemId() {
		return _progressionItemId;
	}

	public void setProgressionItemId(long progressionItemId) {
		_progressionItemId = progressionItemId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getContentName() {
		return _contentName;
	}

	public void setContentName(String contentName) {
		_contentName = contentName;
	}

	public String getContentValue() {
		return _contentValue;
	}

	public void setContentValue(String contentValue) {
		_contentValue = contentValue;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public int getContentType() {
		return _contentType;
	}

	public void setContentType(int contentType) {
		_contentType = contentType;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	public boolean getIsToBeCompleted() {
		return _isToBeCompleted;
	}

	public boolean isIsToBeCompleted() {
		return _isToBeCompleted;
	}

	public void setIsToBeCompleted(boolean isToBeCompleted) {
		_isToBeCompleted = isToBeCompleted;
	}

	private long _contentId;
	private long _progressionItemId;
	private Date _modifiedDate;
	private String _contentName;
	private String _contentValue;
	private long _fileEntryId;
	private int _contentType;
	private int _order;
	private boolean _isToBeCompleted;

}