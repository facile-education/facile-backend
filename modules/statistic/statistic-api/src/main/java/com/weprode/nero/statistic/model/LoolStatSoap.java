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

package com.weprode.nero.statistic.model;

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
public class LoolStatSoap implements Serializable {

	public static LoolStatSoap toSoapModel(LoolStat model) {
		LoolStatSoap soapModel = new LoolStatSoap();

		soapModel.setStatId(model.getStatId());
		soapModel.setObjectId(model.getObjectId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSaveAction(model.isSaveAction());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LoolStatSoap[] toSoapModels(LoolStat[] models) {
		LoolStatSoap[] soapModels = new LoolStatSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoolStatSoap[][] toSoapModels(LoolStat[][] models) {
		LoolStatSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoolStatSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoolStatSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoolStatSoap[] toSoapModels(List<LoolStat> models) {
		List<LoolStatSoap> soapModels = new ArrayList<LoolStatSoap>(
			models.size());

		for (LoolStat model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoolStatSoap[soapModels.size()]);
	}

	public LoolStatSoap() {
	}

	public long getPrimaryKey() {
		return _statId;
	}

	public void setPrimaryKey(long pk) {
		setStatId(pk);
	}

	public long getStatId() {
		return _statId;
	}

	public void setStatId(long statId) {
		_statId = statId;
	}

	public long getObjectId() {
		return _objectId;
	}

	public void setObjectId(long objectId) {
		_objectId = objectId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public boolean getSaveAction() {
		return _saveAction;
	}

	public boolean isSaveAction() {
		return _saveAction;
	}

	public void setSaveAction(boolean saveAction) {
		_saveAction = saveAction;
	}

	public Date getActionDate() {
		return _actionDate;
	}

	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _statId;
	private long _objectId;
	private long _userId;
	private boolean _saveAction;
	private Date _actionDate;
	private int _type;

}