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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserContact}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContact
 * @generated
 */
public class UserContactWrapper
	extends BaseModelWrapper<UserContact>
	implements ModelWrapper<UserContact>, UserContact {

	public UserContactWrapper(UserContact userContact) {
		super(userContact);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contactId", getContactId());
		attributes.put("userId", getUserId());
		attributes.put("middleNames", getMiddleNames());
		attributes.put("birthName", getBirthName());
		attributes.put("address", getAddress());
		attributes.put("isAddressAuthorized", isIsAddressAuthorized());
		attributes.put("mail", getMail());
		attributes.put("isMailAuthorized", isIsMailAuthorized());
		attributes.put("mobilePhone", getMobilePhone());
		attributes.put("mobilePhoneSMS", getMobilePhoneSMS());
		attributes.put("homePhone", getHomePhone());
		attributes.put("proPhone", getProPhone());
		attributes.put("familyLink", getFamilyLink());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contactId = (Long)attributes.get("contactId");

		if (contactId != null) {
			setContactId(contactId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String middleNames = (String)attributes.get("middleNames");

		if (middleNames != null) {
			setMiddleNames(middleNames);
		}

		String birthName = (String)attributes.get("birthName");

		if (birthName != null) {
			setBirthName(birthName);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		Boolean isAddressAuthorized = (Boolean)attributes.get(
			"isAddressAuthorized");

		if (isAddressAuthorized != null) {
			setIsAddressAuthorized(isAddressAuthorized);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		Boolean isMailAuthorized = (Boolean)attributes.get("isMailAuthorized");

		if (isMailAuthorized != null) {
			setIsMailAuthorized(isMailAuthorized);
		}

		String mobilePhone = (String)attributes.get("mobilePhone");

		if (mobilePhone != null) {
			setMobilePhone(mobilePhone);
		}

		String mobilePhoneSMS = (String)attributes.get("mobilePhoneSMS");

		if (mobilePhoneSMS != null) {
			setMobilePhoneSMS(mobilePhoneSMS);
		}

		String homePhone = (String)attributes.get("homePhone");

		if (homePhone != null) {
			setHomePhone(homePhone);
		}

		String proPhone = (String)attributes.get("proPhone");

		if (proPhone != null) {
			setProPhone(proPhone);
		}

		String familyLink = (String)attributes.get("familyLink");

		if (familyLink != null) {
			setFamilyLink(familyLink);
		}
	}

	@Override
	public UserContact cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the address of this user contact.
	 *
	 * @return the address of this user contact
	 */
	@Override
	public String getAddress() {
		return model.getAddress();
	}

	/**
	 * Returns the birth name of this user contact.
	 *
	 * @return the birth name of this user contact
	 */
	@Override
	public String getBirthName() {
		return model.getBirthName();
	}

	/**
	 * Returns the contact ID of this user contact.
	 *
	 * @return the contact ID of this user contact
	 */
	@Override
	public long getContactId() {
		return model.getContactId();
	}

	/**
	 * Returns the family link of this user contact.
	 *
	 * @return the family link of this user contact
	 */
	@Override
	public String getFamilyLink() {
		return model.getFamilyLink();
	}

	/**
	 * Returns the home phone of this user contact.
	 *
	 * @return the home phone of this user contact
	 */
	@Override
	public String getHomePhone() {
		return model.getHomePhone();
	}

	/**
	 * Returns the is address authorized of this user contact.
	 *
	 * @return the is address authorized of this user contact
	 */
	@Override
	public boolean getIsAddressAuthorized() {
		return model.getIsAddressAuthorized();
	}

	/**
	 * Returns the is mail authorized of this user contact.
	 *
	 * @return the is mail authorized of this user contact
	 */
	@Override
	public boolean getIsMailAuthorized() {
		return model.getIsMailAuthorized();
	}

	/**
	 * Returns the mail of this user contact.
	 *
	 * @return the mail of this user contact
	 */
	@Override
	public String getMail() {
		return model.getMail();
	}

	/**
	 * Returns the middle names of this user contact.
	 *
	 * @return the middle names of this user contact
	 */
	@Override
	public String getMiddleNames() {
		return model.getMiddleNames();
	}

	/**
	 * Returns the mobile phone of this user contact.
	 *
	 * @return the mobile phone of this user contact
	 */
	@Override
	public String getMobilePhone() {
		return model.getMobilePhone();
	}

	/**
	 * Returns the mobile phone sms of this user contact.
	 *
	 * @return the mobile phone sms of this user contact
	 */
	@Override
	public String getMobilePhoneSMS() {
		return model.getMobilePhoneSMS();
	}

	/**
	 * Returns the primary key of this user contact.
	 *
	 * @return the primary key of this user contact
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the pro phone of this user contact.
	 *
	 * @return the pro phone of this user contact
	 */
	@Override
	public String getProPhone() {
		return model.getProPhone();
	}

	/**
	 * Returns the user ID of this user contact.
	 *
	 * @return the user ID of this user contact
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user contact.
	 *
	 * @return the user uuid of this user contact
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user contact is is address authorized.
	 *
	 * @return <code>true</code> if this user contact is is address authorized; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAddressAuthorized() {
		return model.isIsAddressAuthorized();
	}

	/**
	 * Returns <code>true</code> if this user contact is is mail authorized.
	 *
	 * @return <code>true</code> if this user contact is is mail authorized; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsMailAuthorized() {
		return model.isIsMailAuthorized();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the address of this user contact.
	 *
	 * @param address the address of this user contact
	 */
	@Override
	public void setAddress(String address) {
		model.setAddress(address);
	}

	/**
	 * Sets the birth name of this user contact.
	 *
	 * @param birthName the birth name of this user contact
	 */
	@Override
	public void setBirthName(String birthName) {
		model.setBirthName(birthName);
	}

	/**
	 * Sets the contact ID of this user contact.
	 *
	 * @param contactId the contact ID of this user contact
	 */
	@Override
	public void setContactId(long contactId) {
		model.setContactId(contactId);
	}

	/**
	 * Sets the family link of this user contact.
	 *
	 * @param familyLink the family link of this user contact
	 */
	@Override
	public void setFamilyLink(String familyLink) {
		model.setFamilyLink(familyLink);
	}

	/**
	 * Sets the home phone of this user contact.
	 *
	 * @param homePhone the home phone of this user contact
	 */
	@Override
	public void setHomePhone(String homePhone) {
		model.setHomePhone(homePhone);
	}

	/**
	 * Sets whether this user contact is is address authorized.
	 *
	 * @param isAddressAuthorized the is address authorized of this user contact
	 */
	@Override
	public void setIsAddressAuthorized(boolean isAddressAuthorized) {
		model.setIsAddressAuthorized(isAddressAuthorized);
	}

	/**
	 * Sets whether this user contact is is mail authorized.
	 *
	 * @param isMailAuthorized the is mail authorized of this user contact
	 */
	@Override
	public void setIsMailAuthorized(boolean isMailAuthorized) {
		model.setIsMailAuthorized(isMailAuthorized);
	}

	/**
	 * Sets the mail of this user contact.
	 *
	 * @param mail the mail of this user contact
	 */
	@Override
	public void setMail(String mail) {
		model.setMail(mail);
	}

	/**
	 * Sets the middle names of this user contact.
	 *
	 * @param middleNames the middle names of this user contact
	 */
	@Override
	public void setMiddleNames(String middleNames) {
		model.setMiddleNames(middleNames);
	}

	/**
	 * Sets the mobile phone of this user contact.
	 *
	 * @param mobilePhone the mobile phone of this user contact
	 */
	@Override
	public void setMobilePhone(String mobilePhone) {
		model.setMobilePhone(mobilePhone);
	}

	/**
	 * Sets the mobile phone sms of this user contact.
	 *
	 * @param mobilePhoneSMS the mobile phone sms of this user contact
	 */
	@Override
	public void setMobilePhoneSMS(String mobilePhoneSMS) {
		model.setMobilePhoneSMS(mobilePhoneSMS);
	}

	/**
	 * Sets the primary key of this user contact.
	 *
	 * @param primaryKey the primary key of this user contact
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the pro phone of this user contact.
	 *
	 * @param proPhone the pro phone of this user contact
	 */
	@Override
	public void setProPhone(String proPhone) {
		model.setProPhone(proPhone);
	}

	/**
	 * Sets the user ID of this user contact.
	 *
	 * @param userId the user ID of this user contact
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user contact.
	 *
	 * @param userUuid the user uuid of this user contact
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserContactWrapper wrap(UserContact userContact) {
		return new UserContactWrapper(userContact);
	}

}