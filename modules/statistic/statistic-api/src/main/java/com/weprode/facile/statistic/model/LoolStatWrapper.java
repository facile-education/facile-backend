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

package com.weprode.facile.statistic.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoolStat}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolStat
 * @generated
 */
public class LoolStatWrapper
	extends BaseModelWrapper<LoolStat>
	implements LoolStat, ModelWrapper<LoolStat> {

	public LoolStatWrapper(LoolStat loolStat) {
		super(loolStat);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("statId", getStatId());
		attributes.put("objectId", getObjectId());
		attributes.put("userId", getUserId());
		attributes.put("saveAction", isSaveAction());
		attributes.put("actionDate", getActionDate());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long statId = (Long)attributes.get("statId");

		if (statId != null) {
			setStatId(statId);
		}

		Long objectId = (Long)attributes.get("objectId");

		if (objectId != null) {
			setObjectId(objectId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean saveAction = (Boolean)attributes.get("saveAction");

		if (saveAction != null) {
			setSaveAction(saveAction);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public LoolStat cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the action date of this lool stat.
	 *
	 * @return the action date of this lool stat
	 */
	@Override
	public Date getActionDate() {
		return model.getActionDate();
	}

	/**
	 * Returns the object ID of this lool stat.
	 *
	 * @return the object ID of this lool stat
	 */
	@Override
	public long getObjectId() {
		return model.getObjectId();
	}

	/**
	 * Returns the primary key of this lool stat.
	 *
	 * @return the primary key of this lool stat
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the save action of this lool stat.
	 *
	 * @return the save action of this lool stat
	 */
	@Override
	public boolean getSaveAction() {
		return model.getSaveAction();
	}

	/**
	 * Returns the stat ID of this lool stat.
	 *
	 * @return the stat ID of this lool stat
	 */
	@Override
	public long getStatId() {
		return model.getStatId();
	}

	/**
	 * Returns the type of this lool stat.
	 *
	 * @return the type of this lool stat
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this lool stat.
	 *
	 * @return the user ID of this lool stat
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this lool stat.
	 *
	 * @return the user uuid of this lool stat
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this lool stat is save action.
	 *
	 * @return <code>true</code> if this lool stat is save action; <code>false</code> otherwise
	 */
	@Override
	public boolean isSaveAction() {
		return model.isSaveAction();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action date of this lool stat.
	 *
	 * @param actionDate the action date of this lool stat
	 */
	@Override
	public void setActionDate(Date actionDate) {
		model.setActionDate(actionDate);
	}

	/**
	 * Sets the object ID of this lool stat.
	 *
	 * @param objectId the object ID of this lool stat
	 */
	@Override
	public void setObjectId(long objectId) {
		model.setObjectId(objectId);
	}

	/**
	 * Sets the primary key of this lool stat.
	 *
	 * @param primaryKey the primary key of this lool stat
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this lool stat is save action.
	 *
	 * @param saveAction the save action of this lool stat
	 */
	@Override
	public void setSaveAction(boolean saveAction) {
		model.setSaveAction(saveAction);
	}

	/**
	 * Sets the stat ID of this lool stat.
	 *
	 * @param statId the stat ID of this lool stat
	 */
	@Override
	public void setStatId(long statId) {
		model.setStatId(statId);
	}

	/**
	 * Sets the type of this lool stat.
	 *
	 * @param type the type of this lool stat
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this lool stat.
	 *
	 * @param userId the user ID of this lool stat
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this lool stat.
	 *
	 * @param userUuid the user uuid of this lool stat
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected LoolStatWrapper wrap(LoolStat loolStat) {
		return new LoolStatWrapper(loolStat);
	}

}