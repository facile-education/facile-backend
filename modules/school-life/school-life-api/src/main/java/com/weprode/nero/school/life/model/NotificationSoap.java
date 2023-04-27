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

package com.weprode.nero.school.life.model;

import com.weprode.nero.school.life.service.persistence.NotificationPK;

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
public class NotificationSoap implements Serializable {

	public static NotificationSoap toSoapModel(Notification model) {
		NotificationSoap soapModel = new NotificationSoap();

		soapModel.setSchoollifeSessionId(model.getSchoollifeSessionId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static NotificationSoap[] toSoapModels(Notification[] models) {
		NotificationSoap[] soapModels = new NotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[][] toSoapModels(Notification[][] models) {
		NotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[] toSoapModels(List<Notification> models) {
		List<NotificationSoap> soapModels = new ArrayList<NotificationSoap>(
			models.size());

		for (Notification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationSoap[soapModels.size()]);
	}

	public NotificationSoap() {
	}

	public NotificationPK getPrimaryKey() {
		return new NotificationPK(_schoollifeSessionId, _userId);
	}

	public void setPrimaryKey(NotificationPK pk) {
		setSchoollifeSessionId(pk.schoollifeSessionId);
		setUserId(pk.userId);
	}

	public long getSchoollifeSessionId() {
		return _schoollifeSessionId;
	}

	public void setSchoollifeSessionId(long schoollifeSessionId) {
		_schoollifeSessionId = schoollifeSessionId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _schoollifeSessionId;
	private long _userId;

}