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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class UserReadVersionNoteSoap implements Serializable {

	public static UserReadVersionNoteSoap toSoapModel(
		UserReadVersionNote model) {

		UserReadVersionNoteSoap soapModel = new UserReadVersionNoteSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setHasReadLastVersionNote(model.isHasReadLastVersionNote());

		return soapModel;
	}

	public static UserReadVersionNoteSoap[] toSoapModels(
		UserReadVersionNote[] models) {

		UserReadVersionNoteSoap[] soapModels =
			new UserReadVersionNoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserReadVersionNoteSoap[][] toSoapModels(
		UserReadVersionNote[][] models) {

		UserReadVersionNoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserReadVersionNoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserReadVersionNoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserReadVersionNoteSoap[] toSoapModels(
		List<UserReadVersionNote> models) {

		List<UserReadVersionNoteSoap> soapModels =
			new ArrayList<UserReadVersionNoteSoap>(models.size());

		for (UserReadVersionNote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new UserReadVersionNoteSoap[soapModels.size()]);
	}

	public UserReadVersionNoteSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public boolean getHasReadLastVersionNote() {
		return _hasReadLastVersionNote;
	}

	public boolean isHasReadLastVersionNote() {
		return _hasReadLastVersionNote;
	}

	public void setHasReadLastVersionNote(boolean hasReadLastVersionNote) {
		_hasReadLastVersionNote = hasReadLastVersionNote;
	}

	private long _userId;
	private boolean _hasReadLastVersionNote;

}