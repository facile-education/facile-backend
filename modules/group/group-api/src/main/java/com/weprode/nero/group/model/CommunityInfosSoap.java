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

package com.weprode.nero.group.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.group.service.http.CommunityInfosServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class CommunityInfosSoap implements Serializable {

	public static CommunityInfosSoap toSoapModel(CommunityInfos model) {
		CommunityInfosSoap soapModel = new CommunityInfosSoap();

		soapModel.setCommunityInfosId(model.getCommunityInfosId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setStatus(model.getStatus());
		soapModel.setCreatorId(model.getCreatorId());
		soapModel.setCreationDate(model.getCreationDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setIsPedagogical(model.isIsPedagogical());
		soapModel.setIsContactList(model.isIsContactList());
		soapModel.setColor(model.getColor());

		return soapModel;
	}

	public static CommunityInfosSoap[] toSoapModels(CommunityInfos[] models) {
		CommunityInfosSoap[] soapModels = new CommunityInfosSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommunityInfosSoap[][] toSoapModels(
		CommunityInfos[][] models) {

		CommunityInfosSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommunityInfosSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommunityInfosSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommunityInfosSoap[] toSoapModels(
		List<CommunityInfos> models) {

		List<CommunityInfosSoap> soapModels = new ArrayList<CommunityInfosSoap>(
			models.size());

		for (CommunityInfos model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommunityInfosSoap[soapModels.size()]);
	}

	public CommunityInfosSoap() {
	}

	public long getPrimaryKey() {
		return _communityInfosId;
	}

	public void setPrimaryKey(long pk) {
		setCommunityInfosId(pk);
	}

	public long getCommunityInfosId() {
		return _communityInfosId;
	}

	public void setCommunityInfosId(long communityInfosId) {
		_communityInfosId = communityInfosId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getCreatorId() {
		return _creatorId;
	}

	public void setCreatorId(long creatorId) {
		_creatorId = creatorId;
	}

	public Date getCreationDate() {
		return _creationDate;
	}

	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public boolean getIsPedagogical() {
		return _isPedagogical;
	}

	public boolean isIsPedagogical() {
		return _isPedagogical;
	}

	public void setIsPedagogical(boolean isPedagogical) {
		_isPedagogical = isPedagogical;
	}

	public boolean getIsContactList() {
		return _isContactList;
	}

	public boolean isIsContactList() {
		return _isContactList;
	}

	public void setIsContactList(boolean isContactList) {
		_isContactList = isContactList;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	private long _communityInfosId;
	private long _groupId;
	private int _status;
	private long _creatorId;
	private Date _creationDate;
	private Date _expirationDate;
	private boolean _isPedagogical;
	private boolean _isContactList;
	private String _color;

}