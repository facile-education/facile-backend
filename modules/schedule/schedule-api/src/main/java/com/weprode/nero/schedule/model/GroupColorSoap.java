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

package com.weprode.nero.schedule.model;

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
public class GroupColorSoap implements Serializable {

	public static GroupColorSoap toSoapModel(GroupColor model) {
		GroupColorSoap soapModel = new GroupColorSoap();

		soapModel.setGroupId(model.getGroupId());
		soapModel.setColor(model.getColor());

		return soapModel;
	}

	public static GroupColorSoap[] toSoapModels(GroupColor[] models) {
		GroupColorSoap[] soapModels = new GroupColorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GroupColorSoap[][] toSoapModels(GroupColor[][] models) {
		GroupColorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GroupColorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GroupColorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GroupColorSoap[] toSoapModels(List<GroupColor> models) {
		List<GroupColorSoap> soapModels = new ArrayList<GroupColorSoap>(
			models.size());

		for (GroupColor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GroupColorSoap[soapModels.size()]);
	}

	public GroupColorSoap() {
	}

	public long getPrimaryKey() {
		return _groupId;
	}

	public void setPrimaryKey(long pk) {
		setGroupId(pk);
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	private long _groupId;
	private String _color;

}