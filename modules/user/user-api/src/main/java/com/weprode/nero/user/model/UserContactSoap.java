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
public class UserContactSoap implements Serializable {

	public static UserContactSoap toSoapModel(UserContact model) {
		UserContactSoap soapModel = new UserContactSoap();

		soapModel.setContactId(model.getContactId());
		soapModel.setUserId(model.getUserId());
		soapModel.setMiddleNames(model.getMiddleNames());
		soapModel.setBirthName(model.getBirthName());
		soapModel.setAddress(model.getAddress());
		soapModel.setIsAddressAuthorized(model.isIsAddressAuthorized());
		soapModel.setMail(model.getMail());
		soapModel.setIsMailAuthorized(model.isIsMailAuthorized());
		soapModel.setMobilePhone(model.getMobilePhone());
		soapModel.setMobilePhoneSMS(model.getMobilePhoneSMS());
		soapModel.setHomePhone(model.getHomePhone());
		soapModel.setProPhone(model.getProPhone());
		soapModel.setFamilyLink(model.getFamilyLink());

		return soapModel;
	}

	public static UserContactSoap[] toSoapModels(UserContact[] models) {
		UserContactSoap[] soapModels = new UserContactSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserContactSoap[][] toSoapModels(UserContact[][] models) {
		UserContactSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserContactSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserContactSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserContactSoap[] toSoapModels(List<UserContact> models) {
		List<UserContactSoap> soapModels = new ArrayList<UserContactSoap>(
			models.size());

		for (UserContact model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserContactSoap[soapModels.size()]);
	}

	public UserContactSoap() {
	}

	public long getPrimaryKey() {
		return _contactId;
	}

	public void setPrimaryKey(long pk) {
		setContactId(pk);
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getMiddleNames() {
		return _middleNames;
	}

	public void setMiddleNames(String middleNames) {
		_middleNames = middleNames;
	}

	public String getBirthName() {
		return _birthName;
	}

	public void setBirthName(String birthName) {
		_birthName = birthName;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public boolean getIsAddressAuthorized() {
		return _isAddressAuthorized;
	}

	public boolean isIsAddressAuthorized() {
		return _isAddressAuthorized;
	}

	public void setIsAddressAuthorized(boolean isAddressAuthorized) {
		_isAddressAuthorized = isAddressAuthorized;
	}

	public String getMail() {
		return _mail;
	}

	public void setMail(String mail) {
		_mail = mail;
	}

	public boolean getIsMailAuthorized() {
		return _isMailAuthorized;
	}

	public boolean isIsMailAuthorized() {
		return _isMailAuthorized;
	}

	public void setIsMailAuthorized(boolean isMailAuthorized) {
		_isMailAuthorized = isMailAuthorized;
	}

	public String getMobilePhone() {
		return _mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		_mobilePhone = mobilePhone;
	}

	public String getMobilePhoneSMS() {
		return _mobilePhoneSMS;
	}

	public void setMobilePhoneSMS(String mobilePhoneSMS) {
		_mobilePhoneSMS = mobilePhoneSMS;
	}

	public String getHomePhone() {
		return _homePhone;
	}

	public void setHomePhone(String homePhone) {
		_homePhone = homePhone;
	}

	public String getProPhone() {
		return _proPhone;
	}

	public void setProPhone(String proPhone) {
		_proPhone = proPhone;
	}

	public String getFamilyLink() {
		return _familyLink;
	}

	public void setFamilyLink(String familyLink) {
		_familyLink = familyLink;
	}

	private long _contactId;
	private long _userId;
	private String _middleNames;
	private String _birthName;
	private String _address;
	private boolean _isAddressAuthorized;
	private String _mail;
	private boolean _isMailAuthorized;
	private String _mobilePhone;
	private String _mobilePhoneSMS;
	private String _homePhone;
	private String _proPhone;
	private String _familyLink;

}