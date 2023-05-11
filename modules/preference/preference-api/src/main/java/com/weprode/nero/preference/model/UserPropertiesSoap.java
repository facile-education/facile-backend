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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.preference.service.http.UserPropertiesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class UserPropertiesSoap implements Serializable {

	public static UserPropertiesSoap toSoapModel(UserProperties model) {
		UserPropertiesSoap soapModel = new UserPropertiesSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setManualAccount(model.isManualAccount());
		soapModel.setHideMenu(model.isHideMenu());
		soapModel.setThemeColor(model.getThemeColor());
		soapModel.setEtabId(model.getEtabId());
		soapModel.setPreferedSchoolId(model.getPreferedSchoolId());
		soapModel.setWebdavActivated(model.isWebdavActivated());
		soapModel.setTermsOfUseAgreedDate(model.getTermsOfUseAgreedDate());
		soapModel.setLastSynchroDate(model.getLastSynchroDate());
		soapModel.setLastDashboardAccessDate(
			model.getLastDashboardAccessDate());

		return soapModel;
	}

	public static UserPropertiesSoap[] toSoapModels(UserProperties[] models) {
		UserPropertiesSoap[] soapModels = new UserPropertiesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserPropertiesSoap[][] toSoapModels(
		UserProperties[][] models) {

		UserPropertiesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserPropertiesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserPropertiesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserPropertiesSoap[] toSoapModels(
		List<UserProperties> models) {

		List<UserPropertiesSoap> soapModels = new ArrayList<UserPropertiesSoap>(
			models.size());

		for (UserProperties model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserPropertiesSoap[soapModels.size()]);
	}

	public UserPropertiesSoap() {
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

	public boolean getManualAccount() {
		return _manualAccount;
	}

	public boolean isManualAccount() {
		return _manualAccount;
	}

	public void setManualAccount(boolean manualAccount) {
		_manualAccount = manualAccount;
	}

	public boolean getHideMenu() {
		return _hideMenu;
	}

	public boolean isHideMenu() {
		return _hideMenu;
	}

	public void setHideMenu(boolean hideMenu) {
		_hideMenu = hideMenu;
	}

	public String getThemeColor() {
		return _themeColor;
	}

	public void setThemeColor(String themeColor) {
		_themeColor = themeColor;
	}

	public long getEtabId() {
		return _etabId;
	}

	public void setEtabId(long etabId) {
		_etabId = etabId;
	}

	public long getPreferedSchoolId() {
		return _preferedSchoolId;
	}

	public void setPreferedSchoolId(long preferedSchoolId) {
		_preferedSchoolId = preferedSchoolId;
	}

	public boolean getWebdavActivated() {
		return _webdavActivated;
	}

	public boolean isWebdavActivated() {
		return _webdavActivated;
	}

	public void setWebdavActivated(boolean webdavActivated) {
		_webdavActivated = webdavActivated;
	}

	public Date getTermsOfUseAgreedDate() {
		return _termsOfUseAgreedDate;
	}

	public void setTermsOfUseAgreedDate(Date termsOfUseAgreedDate) {
		_termsOfUseAgreedDate = termsOfUseAgreedDate;
	}

	public Date getLastSynchroDate() {
		return _lastSynchroDate;
	}

	public void setLastSynchroDate(Date lastSynchroDate) {
		_lastSynchroDate = lastSynchroDate;
	}

	public Date getLastDashboardAccessDate() {
		return _lastDashboardAccessDate;
	}

	public void setLastDashboardAccessDate(Date lastDashboardAccessDate) {
		_lastDashboardAccessDate = lastDashboardAccessDate;
	}

	private long _userId;
	private boolean _manualAccount;
	private boolean _hideMenu;
	private String _themeColor;
	private long _etabId;
	private long _preferedSchoolId;
	private boolean _webdavActivated;
	private Date _termsOfUseAgreedDate;
	private Date _lastSynchroDate;
	private Date _lastDashboardAccessDate;

}