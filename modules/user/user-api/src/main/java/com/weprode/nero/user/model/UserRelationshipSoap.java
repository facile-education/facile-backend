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

package com.weprode.nero.user.model;

import com.weprode.nero.user.service.persistence.UserRelationshipPK;

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
public class UserRelationshipSoap implements Serializable {

	public static UserRelationshipSoap toSoapModel(UserRelationship model) {
		UserRelationshipSoap soapModel = new UserRelationshipSoap();

		soapModel.setChildUserId(model.getChildUserId());
		soapModel.setParentUserId(model.getParentUserId());

		return soapModel;
	}

	public static UserRelationshipSoap[] toSoapModels(
		UserRelationship[] models) {

		UserRelationshipSoap[] soapModels =
			new UserRelationshipSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserRelationshipSoap[][] toSoapModels(
		UserRelationship[][] models) {

		UserRelationshipSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserRelationshipSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserRelationshipSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserRelationshipSoap[] toSoapModels(
		List<UserRelationship> models) {

		List<UserRelationshipSoap> soapModels =
			new ArrayList<UserRelationshipSoap>(models.size());

		for (UserRelationship model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserRelationshipSoap[soapModels.size()]);
	}

	public UserRelationshipSoap() {
	}

	public UserRelationshipPK getPrimaryKey() {
		return new UserRelationshipPK(_childUserId, _parentUserId);
	}

	public void setPrimaryKey(UserRelationshipPK pk) {
		setChildUserId(pk.childUserId);
		setParentUserId(pk.parentUserId);
	}

	public long getChildUserId() {
		return _childUserId;
	}

	public void setChildUserId(long childUserId) {
		_childUserId = childUserId;
	}

	public long getParentUserId() {
		return _parentUserId;
	}

	public void setParentUserId(long parentUserId) {
		_parentUserId = parentUserId;
	}

	private long _childUserId;
	private long _parentUserId;

}