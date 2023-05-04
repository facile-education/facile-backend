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

package com.weprode.nero.preference.model;

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
public class NotifyConfigSoap implements Serializable {

	public static NotifyConfigSoap toSoapModel(NotifyConfig model) {
		NotifyConfigSoap soapModel = new NotifyConfigSoap();

		soapModel.setNotifyConfigId(model.getNotifyConfigId());
		soapModel.setUserId(model.getUserId());
		soapModel.setActivate(model.isActivate());
		soapModel.setNotifyCasier(model.isNotifyCasier());
		soapModel.setNotifyActu(model.isNotifyActu());
		soapModel.setNotifyGrpDoc(model.isNotifyGrpDoc());
		soapModel.setNotifyAgenda(model.isNotifyAgenda());
		soapModel.setNotifySync(model.isNotifySync());
		soapModel.setDigestPeriod(model.getDigestPeriod());

		return soapModel;
	}

	public static NotifyConfigSoap[] toSoapModels(NotifyConfig[] models) {
		NotifyConfigSoap[] soapModels = new NotifyConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotifyConfigSoap[][] toSoapModels(NotifyConfig[][] models) {
		NotifyConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotifyConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotifyConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotifyConfigSoap[] toSoapModels(List<NotifyConfig> models) {
		List<NotifyConfigSoap> soapModels = new ArrayList<NotifyConfigSoap>(
			models.size());

		for (NotifyConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotifyConfigSoap[soapModels.size()]);
	}

	public NotifyConfigSoap() {
	}

	public long getPrimaryKey() {
		return _notifyConfigId;
	}

	public void setPrimaryKey(long pk) {
		setNotifyConfigId(pk);
	}

	public long getNotifyConfigId() {
		return _notifyConfigId;
	}

	public void setNotifyConfigId(long notifyConfigId) {
		_notifyConfigId = notifyConfigId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public boolean getActivate() {
		return _activate;
	}

	public boolean isActivate() {
		return _activate;
	}

	public void setActivate(boolean activate) {
		_activate = activate;
	}

	public boolean getNotifyCasier() {
		return _notifyCasier;
	}

	public boolean isNotifyCasier() {
		return _notifyCasier;
	}

	public void setNotifyCasier(boolean notifyCasier) {
		_notifyCasier = notifyCasier;
	}

	public boolean getNotifyActu() {
		return _notifyActu;
	}

	public boolean isNotifyActu() {
		return _notifyActu;
	}

	public void setNotifyActu(boolean notifyActu) {
		_notifyActu = notifyActu;
	}

	public boolean getNotifyGrpDoc() {
		return _notifyGrpDoc;
	}

	public boolean isNotifyGrpDoc() {
		return _notifyGrpDoc;
	}

	public void setNotifyGrpDoc(boolean notifyGrpDoc) {
		_notifyGrpDoc = notifyGrpDoc;
	}

	public boolean getNotifyAgenda() {
		return _notifyAgenda;
	}

	public boolean isNotifyAgenda() {
		return _notifyAgenda;
	}

	public void setNotifyAgenda(boolean notifyAgenda) {
		_notifyAgenda = notifyAgenda;
	}

	public boolean getNotifySync() {
		return _notifySync;
	}

	public boolean isNotifySync() {
		return _notifySync;
	}

	public void setNotifySync(boolean notifySync) {
		_notifySync = notifySync;
	}

	public int getDigestPeriod() {
		return _digestPeriod;
	}

	public void setDigestPeriod(int digestPeriod) {
		_digestPeriod = digestPeriod;
	}

	private long _notifyConfigId;
	private long _userId;
	private boolean _activate;
	private boolean _notifyCasier;
	private boolean _notifyActu;
	private boolean _notifyGrpDoc;
	private boolean _notifyAgenda;
	private boolean _notifySync;
	private int _digestPeriod;

}