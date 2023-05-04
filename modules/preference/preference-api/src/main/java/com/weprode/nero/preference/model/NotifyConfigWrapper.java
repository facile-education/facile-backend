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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NotifyConfig}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfig
 * @generated
 */
public class NotifyConfigWrapper
	extends BaseModelWrapper<NotifyConfig>
	implements ModelWrapper<NotifyConfig>, NotifyConfig {

	public NotifyConfigWrapper(NotifyConfig notifyConfig) {
		super(notifyConfig);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notifyConfigId", getNotifyConfigId());
		attributes.put("userId", getUserId());
		attributes.put("activate", isActivate());
		attributes.put("notifyCasier", isNotifyCasier());
		attributes.put("notifyActu", isNotifyActu());
		attributes.put("notifyGrpDoc", isNotifyGrpDoc());
		attributes.put("notifyAgenda", isNotifyAgenda());
		attributes.put("notifySync", isNotifySync());
		attributes.put("digestPeriod", getDigestPeriod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notifyConfigId = (Long)attributes.get("notifyConfigId");

		if (notifyConfigId != null) {
			setNotifyConfigId(notifyConfigId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean activate = (Boolean)attributes.get("activate");

		if (activate != null) {
			setActivate(activate);
		}

		Boolean notifyCasier = (Boolean)attributes.get("notifyCasier");

		if (notifyCasier != null) {
			setNotifyCasier(notifyCasier);
		}

		Boolean notifyActu = (Boolean)attributes.get("notifyActu");

		if (notifyActu != null) {
			setNotifyActu(notifyActu);
		}

		Boolean notifyGrpDoc = (Boolean)attributes.get("notifyGrpDoc");

		if (notifyGrpDoc != null) {
			setNotifyGrpDoc(notifyGrpDoc);
		}

		Boolean notifyAgenda = (Boolean)attributes.get("notifyAgenda");

		if (notifyAgenda != null) {
			setNotifyAgenda(notifyAgenda);
		}

		Boolean notifySync = (Boolean)attributes.get("notifySync");

		if (notifySync != null) {
			setNotifySync(notifySync);
		}

		Integer digestPeriod = (Integer)attributes.get("digestPeriod");

		if (digestPeriod != null) {
			setDigestPeriod(digestPeriod);
		}
	}

	@Override
	public NotifyConfig cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the activate of this notify config.
	 *
	 * @return the activate of this notify config
	 */
	@Override
	public boolean getActivate() {
		return model.getActivate();
	}

	/**
	 * Returns the digest period of this notify config.
	 *
	 * @return the digest period of this notify config
	 */
	@Override
	public int getDigestPeriod() {
		return model.getDigestPeriod();
	}

	/**
	 * Returns the notify actu of this notify config.
	 *
	 * @return the notify actu of this notify config
	 */
	@Override
	public boolean getNotifyActu() {
		return model.getNotifyActu();
	}

	/**
	 * Returns the notify agenda of this notify config.
	 *
	 * @return the notify agenda of this notify config
	 */
	@Override
	public boolean getNotifyAgenda() {
		return model.getNotifyAgenda();
	}

	/**
	 * Returns the notify casier of this notify config.
	 *
	 * @return the notify casier of this notify config
	 */
	@Override
	public boolean getNotifyCasier() {
		return model.getNotifyCasier();
	}

	/**
	 * Returns the notify config ID of this notify config.
	 *
	 * @return the notify config ID of this notify config
	 */
	@Override
	public long getNotifyConfigId() {
		return model.getNotifyConfigId();
	}

	/**
	 * Returns the notify grp doc of this notify config.
	 *
	 * @return the notify grp doc of this notify config
	 */
	@Override
	public boolean getNotifyGrpDoc() {
		return model.getNotifyGrpDoc();
	}

	/**
	 * Returns the notify sync of this notify config.
	 *
	 * @return the notify sync of this notify config
	 */
	@Override
	public boolean getNotifySync() {
		return model.getNotifySync();
	}

	/**
	 * Returns the primary key of this notify config.
	 *
	 * @return the primary key of this notify config
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this notify config.
	 *
	 * @return the user ID of this notify config
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this notify config.
	 *
	 * @return the user uuid of this notify config
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this notify config is activate.
	 *
	 * @return <code>true</code> if this notify config is activate; <code>false</code> otherwise
	 */
	@Override
	public boolean isActivate() {
		return model.isActivate();
	}

	/**
	 * Returns <code>true</code> if this notify config is notify actu.
	 *
	 * @return <code>true</code> if this notify config is notify actu; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifyActu() {
		return model.isNotifyActu();
	}

	/**
	 * Returns <code>true</code> if this notify config is notify agenda.
	 *
	 * @return <code>true</code> if this notify config is notify agenda; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifyAgenda() {
		return model.isNotifyAgenda();
	}

	/**
	 * Returns <code>true</code> if this notify config is notify casier.
	 *
	 * @return <code>true</code> if this notify config is notify casier; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifyCasier() {
		return model.isNotifyCasier();
	}

	/**
	 * Returns <code>true</code> if this notify config is notify grp doc.
	 *
	 * @return <code>true</code> if this notify config is notify grp doc; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifyGrpDoc() {
		return model.isNotifyGrpDoc();
	}

	/**
	 * Returns <code>true</code> if this notify config is notify sync.
	 *
	 * @return <code>true</code> if this notify config is notify sync; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifySync() {
		return model.isNotifySync();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this notify config is activate.
	 *
	 * @param activate the activate of this notify config
	 */
	@Override
	public void setActivate(boolean activate) {
		model.setActivate(activate);
	}

	/**
	 * Sets the digest period of this notify config.
	 *
	 * @param digestPeriod the digest period of this notify config
	 */
	@Override
	public void setDigestPeriod(int digestPeriod) {
		model.setDigestPeriod(digestPeriod);
	}

	/**
	 * Sets whether this notify config is notify actu.
	 *
	 * @param notifyActu the notify actu of this notify config
	 */
	@Override
	public void setNotifyActu(boolean notifyActu) {
		model.setNotifyActu(notifyActu);
	}

	/**
	 * Sets whether this notify config is notify agenda.
	 *
	 * @param notifyAgenda the notify agenda of this notify config
	 */
	@Override
	public void setNotifyAgenda(boolean notifyAgenda) {
		model.setNotifyAgenda(notifyAgenda);
	}

	/**
	 * Sets whether this notify config is notify casier.
	 *
	 * @param notifyCasier the notify casier of this notify config
	 */
	@Override
	public void setNotifyCasier(boolean notifyCasier) {
		model.setNotifyCasier(notifyCasier);
	}

	/**
	 * Sets the notify config ID of this notify config.
	 *
	 * @param notifyConfigId the notify config ID of this notify config
	 */
	@Override
	public void setNotifyConfigId(long notifyConfigId) {
		model.setNotifyConfigId(notifyConfigId);
	}

	/**
	 * Sets whether this notify config is notify grp doc.
	 *
	 * @param notifyGrpDoc the notify grp doc of this notify config
	 */
	@Override
	public void setNotifyGrpDoc(boolean notifyGrpDoc) {
		model.setNotifyGrpDoc(notifyGrpDoc);
	}

	/**
	 * Sets whether this notify config is notify sync.
	 *
	 * @param notifySync the notify sync of this notify config
	 */
	@Override
	public void setNotifySync(boolean notifySync) {
		model.setNotifySync(notifySync);
	}

	/**
	 * Sets the primary key of this notify config.
	 *
	 * @param primaryKey the primary key of this notify config
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this notify config.
	 *
	 * @param userId the user ID of this notify config
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this notify config.
	 *
	 * @param userUuid the user uuid of this notify config
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected NotifyConfigWrapper wrap(NotifyConfig notifyConfig) {
		return new NotifyConfigWrapper(notifyConfig);
	}

}