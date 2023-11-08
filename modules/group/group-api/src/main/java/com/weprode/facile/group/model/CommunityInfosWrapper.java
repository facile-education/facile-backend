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

package com.weprode.facile.group.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommunityInfos}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfos
 * @generated
 */
public class CommunityInfosWrapper
	extends BaseModelWrapper<CommunityInfos>
	implements CommunityInfos, ModelWrapper<CommunityInfos> {

	public CommunityInfosWrapper(CommunityInfos communityInfos) {
		super(communityInfos);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("communityInfosId", getCommunityInfosId());
		attributes.put("groupId", getGroupId());
		attributes.put("status", getStatus());
		attributes.put("creatorId", getCreatorId());
		attributes.put("creationDate", getCreationDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("isPedagogical", isIsPedagogical());
		attributes.put("isContactList", isIsContactList());
		attributes.put("color", getColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long communityInfosId = (Long)attributes.get("communityInfosId");

		if (communityInfosId != null) {
			setCommunityInfosId(communityInfosId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long creatorId = (Long)attributes.get("creatorId");

		if (creatorId != null) {
			setCreatorId(creatorId);
		}

		Date creationDate = (Date)attributes.get("creationDate");

		if (creationDate != null) {
			setCreationDate(creationDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Boolean isPedagogical = (Boolean)attributes.get("isPedagogical");

		if (isPedagogical != null) {
			setIsPedagogical(isPedagogical);
		}

		Boolean isContactList = (Boolean)attributes.get("isContactList");

		if (isContactList != null) {
			setIsContactList(isContactList);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}
	}

	@Override
	public CommunityInfos cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the color of this community infos.
	 *
	 * @return the color of this community infos
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the community infos ID of this community infos.
	 *
	 * @return the community infos ID of this community infos
	 */
	@Override
	public long getCommunityInfosId() {
		return model.getCommunityInfosId();
	}

	/**
	 * Returns the creation date of this community infos.
	 *
	 * @return the creation date of this community infos
	 */
	@Override
	public Date getCreationDate() {
		return model.getCreationDate();
	}

	/**
	 * Returns the creator ID of this community infos.
	 *
	 * @return the creator ID of this community infos
	 */
	@Override
	public long getCreatorId() {
		return model.getCreatorId();
	}

	/**
	 * Returns the expiration date of this community infos.
	 *
	 * @return the expiration date of this community infos
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the group ID of this community infos.
	 *
	 * @return the group ID of this community infos
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the is contact list of this community infos.
	 *
	 * @return the is contact list of this community infos
	 */
	@Override
	public boolean getIsContactList() {
		return model.getIsContactList();
	}

	/**
	 * Returns the is pedagogical of this community infos.
	 *
	 * @return the is pedagogical of this community infos
	 */
	@Override
	public boolean getIsPedagogical() {
		return model.getIsPedagogical();
	}

	/**
	 * Returns the primary key of this community infos.
	 *
	 * @return the primary key of this community infos
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this community infos.
	 *
	 * @return the status of this community infos
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns <code>true</code> if this community infos is is contact list.
	 *
	 * @return <code>true</code> if this community infos is is contact list; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsContactList() {
		return model.isIsContactList();
	}

	/**
	 * Returns <code>true</code> if this community infos is is pedagogical.
	 *
	 * @return <code>true</code> if this community infos is is pedagogical; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsPedagogical() {
		return model.isIsPedagogical();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this community infos.
	 *
	 * @param color the color of this community infos
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the community infos ID of this community infos.
	 *
	 * @param communityInfosId the community infos ID of this community infos
	 */
	@Override
	public void setCommunityInfosId(long communityInfosId) {
		model.setCommunityInfosId(communityInfosId);
	}

	/**
	 * Sets the creation date of this community infos.
	 *
	 * @param creationDate the creation date of this community infos
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		model.setCreationDate(creationDate);
	}

	/**
	 * Sets the creator ID of this community infos.
	 *
	 * @param creatorId the creator ID of this community infos
	 */
	@Override
	public void setCreatorId(long creatorId) {
		model.setCreatorId(creatorId);
	}

	/**
	 * Sets the expiration date of this community infos.
	 *
	 * @param expirationDate the expiration date of this community infos
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the group ID of this community infos.
	 *
	 * @param groupId the group ID of this community infos
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets whether this community infos is is contact list.
	 *
	 * @param isContactList the is contact list of this community infos
	 */
	@Override
	public void setIsContactList(boolean isContactList) {
		model.setIsContactList(isContactList);
	}

	/**
	 * Sets whether this community infos is is pedagogical.
	 *
	 * @param isPedagogical the is pedagogical of this community infos
	 */
	@Override
	public void setIsPedagogical(boolean isPedagogical) {
		model.setIsPedagogical(isPedagogical);
	}

	/**
	 * Sets the primary key of this community infos.
	 *
	 * @param primaryKey the primary key of this community infos
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this community infos.
	 *
	 * @param status the status of this community infos
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	@Override
	protected CommunityInfosWrapper wrap(CommunityInfos communityInfos) {
		return new CommunityInfosWrapper(communityInfos);
	}

}