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

package com.weprode.facile.preference.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserProperties}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProperties
 * @generated
 */
public class UserPropertiesWrapper
	extends BaseModelWrapper<UserProperties>
	implements ModelWrapper<UserProperties>, UserProperties {

	public UserPropertiesWrapper(UserProperties userProperties) {
		super(userProperties);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("manualAccount", isManualAccount());
		attributes.put("hideMenu", isHideMenu());
		attributes.put("themeColor", getThemeColor());
		attributes.put("etabId", getEtabId());
		attributes.put("preferedSchoolId", getPreferedSchoolId());
		attributes.put("webdavActivated", isWebdavActivated());
		attributes.put("termsOfUseAgreedDate", getTermsOfUseAgreedDate());
		attributes.put("lastSynchroDate", getLastSynchroDate());
		attributes.put("lastDashboardAccessDate", getLastDashboardAccessDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean manualAccount = (Boolean)attributes.get("manualAccount");

		if (manualAccount != null) {
			setManualAccount(manualAccount);
		}

		Boolean hideMenu = (Boolean)attributes.get("hideMenu");

		if (hideMenu != null) {
			setHideMenu(hideMenu);
		}

		String themeColor = (String)attributes.get("themeColor");

		if (themeColor != null) {
			setThemeColor(themeColor);
		}

		Long etabId = (Long)attributes.get("etabId");

		if (etabId != null) {
			setEtabId(etabId);
		}

		Long preferedSchoolId = (Long)attributes.get("preferedSchoolId");

		if (preferedSchoolId != null) {
			setPreferedSchoolId(preferedSchoolId);
		}

		Boolean webdavActivated = (Boolean)attributes.get("webdavActivated");

		if (webdavActivated != null) {
			setWebdavActivated(webdavActivated);
		}

		Date termsOfUseAgreedDate = (Date)attributes.get(
			"termsOfUseAgreedDate");

		if (termsOfUseAgreedDate != null) {
			setTermsOfUseAgreedDate(termsOfUseAgreedDate);
		}

		Date lastSynchroDate = (Date)attributes.get("lastSynchroDate");

		if (lastSynchroDate != null) {
			setLastSynchroDate(lastSynchroDate);
		}

		Date lastDashboardAccessDate = (Date)attributes.get(
			"lastDashboardAccessDate");

		if (lastDashboardAccessDate != null) {
			setLastDashboardAccessDate(lastDashboardAccessDate);
		}
	}

	@Override
	public UserProperties cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the etab ID of this user properties.
	 *
	 * @return the etab ID of this user properties
	 */
	@Override
	public long getEtabId() {
		return model.getEtabId();
	}

	/**
	 * Returns the hide menu of this user properties.
	 *
	 * @return the hide menu of this user properties
	 */
	@Override
	public boolean getHideMenu() {
		return model.getHideMenu();
	}

	/**
	 * Returns the last dashboard access date of this user properties.
	 *
	 * @return the last dashboard access date of this user properties
	 */
	@Override
	public Date getLastDashboardAccessDate() {
		return model.getLastDashboardAccessDate();
	}

	/**
	 * Returns the last synchro date of this user properties.
	 *
	 * @return the last synchro date of this user properties
	 */
	@Override
	public Date getLastSynchroDate() {
		return model.getLastSynchroDate();
	}

	/**
	 * Returns the manual account of this user properties.
	 *
	 * @return the manual account of this user properties
	 */
	@Override
	public boolean getManualAccount() {
		return model.getManualAccount();
	}

	/**
	 * Returns the prefered school ID of this user properties.
	 *
	 * @return the prefered school ID of this user properties
	 */
	@Override
	public long getPreferedSchoolId() {
		return model.getPreferedSchoolId();
	}

	/**
	 * Returns the primary key of this user properties.
	 *
	 * @return the primary key of this user properties
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the terms of use agreed date of this user properties.
	 *
	 * @return the terms of use agreed date of this user properties
	 */
	@Override
	public Date getTermsOfUseAgreedDate() {
		return model.getTermsOfUseAgreedDate();
	}

	/**
	 * Returns the theme color of this user properties.
	 *
	 * @return the theme color of this user properties
	 */
	@Override
	public String getThemeColor() {
		return model.getThemeColor();
	}

	/**
	 * Returns the user ID of this user properties.
	 *
	 * @return the user ID of this user properties
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user properties.
	 *
	 * @return the user uuid of this user properties
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the webdav activated of this user properties.
	 *
	 * @return the webdav activated of this user properties
	 */
	@Override
	public boolean getWebdavActivated() {
		return model.getWebdavActivated();
	}

	/**
	 * Returns <code>true</code> if this user properties is hide menu.
	 *
	 * @return <code>true</code> if this user properties is hide menu; <code>false</code> otherwise
	 */
	@Override
	public boolean isHideMenu() {
		return model.isHideMenu();
	}

	/**
	 * Returns <code>true</code> if this user properties is manual account.
	 *
	 * @return <code>true</code> if this user properties is manual account; <code>false</code> otherwise
	 */
	@Override
	public boolean isManualAccount() {
		return model.isManualAccount();
	}

	/**
	 * Returns <code>true</code> if this user properties is webdav activated.
	 *
	 * @return <code>true</code> if this user properties is webdav activated; <code>false</code> otherwise
	 */
	@Override
	public boolean isWebdavActivated() {
		return model.isWebdavActivated();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the etab ID of this user properties.
	 *
	 * @param etabId the etab ID of this user properties
	 */
	@Override
	public void setEtabId(long etabId) {
		model.setEtabId(etabId);
	}

	/**
	 * Sets whether this user properties is hide menu.
	 *
	 * @param hideMenu the hide menu of this user properties
	 */
	@Override
	public void setHideMenu(boolean hideMenu) {
		model.setHideMenu(hideMenu);
	}

	/**
	 * Sets the last dashboard access date of this user properties.
	 *
	 * @param lastDashboardAccessDate the last dashboard access date of this user properties
	 */
	@Override
	public void setLastDashboardAccessDate(Date lastDashboardAccessDate) {
		model.setLastDashboardAccessDate(lastDashboardAccessDate);
	}

	/**
	 * Sets the last synchro date of this user properties.
	 *
	 * @param lastSynchroDate the last synchro date of this user properties
	 */
	@Override
	public void setLastSynchroDate(Date lastSynchroDate) {
		model.setLastSynchroDate(lastSynchroDate);
	}

	/**
	 * Sets whether this user properties is manual account.
	 *
	 * @param manualAccount the manual account of this user properties
	 */
	@Override
	public void setManualAccount(boolean manualAccount) {
		model.setManualAccount(manualAccount);
	}

	/**
	 * Sets the prefered school ID of this user properties.
	 *
	 * @param preferedSchoolId the prefered school ID of this user properties
	 */
	@Override
	public void setPreferedSchoolId(long preferedSchoolId) {
		model.setPreferedSchoolId(preferedSchoolId);
	}

	/**
	 * Sets the primary key of this user properties.
	 *
	 * @param primaryKey the primary key of this user properties
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the terms of use agreed date of this user properties.
	 *
	 * @param termsOfUseAgreedDate the terms of use agreed date of this user properties
	 */
	@Override
	public void setTermsOfUseAgreedDate(Date termsOfUseAgreedDate) {
		model.setTermsOfUseAgreedDate(termsOfUseAgreedDate);
	}

	/**
	 * Sets the theme color of this user properties.
	 *
	 * @param themeColor the theme color of this user properties
	 */
	@Override
	public void setThemeColor(String themeColor) {
		model.setThemeColor(themeColor);
	}

	/**
	 * Sets the user ID of this user properties.
	 *
	 * @param userId the user ID of this user properties
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user properties.
	 *
	 * @param userUuid the user uuid of this user properties
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets whether this user properties is webdav activated.
	 *
	 * @param webdavActivated the webdav activated of this user properties
	 */
	@Override
	public void setWebdavActivated(boolean webdavActivated) {
		model.setWebdavActivated(webdavActivated);
	}

	@Override
	protected UserPropertiesWrapper wrap(UserProperties userProperties) {
		return new UserPropertiesWrapper(userProperties);
	}

}