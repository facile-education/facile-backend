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

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the NotifyConfig service. Represents a row in the &quot;Preference_NotifyConfig&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.preference.model.impl.NotifyConfigImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfig
 * @generated
 */
@ProviderType
public interface NotifyConfigModel extends BaseModel<NotifyConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a notify config model instance should use the {@link NotifyConfig} interface instead.
	 */

	/**
	 * Returns the primary key of this notify config.
	 *
	 * @return the primary key of this notify config
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this notify config.
	 *
	 * @param primaryKey the primary key of this notify config
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the notify config ID of this notify config.
	 *
	 * @return the notify config ID of this notify config
	 */
	public long getNotifyConfigId();

	/**
	 * Sets the notify config ID of this notify config.
	 *
	 * @param notifyConfigId the notify config ID of this notify config
	 */
	public void setNotifyConfigId(long notifyConfigId);

	/**
	 * Returns the user ID of this notify config.
	 *
	 * @return the user ID of this notify config
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this notify config.
	 *
	 * @param userId the user ID of this notify config
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this notify config.
	 *
	 * @return the user uuid of this notify config
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this notify config.
	 *
	 * @param userUuid the user uuid of this notify config
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the activate of this notify config.
	 *
	 * @return the activate of this notify config
	 */
	public boolean getActivate();

	/**
	 * Returns <code>true</code> if this notify config is activate.
	 *
	 * @return <code>true</code> if this notify config is activate; <code>false</code> otherwise
	 */
	public boolean isActivate();

	/**
	 * Sets whether this notify config is activate.
	 *
	 * @param activate the activate of this notify config
	 */
	public void setActivate(boolean activate);

	/**
	 * Returns the notify casier of this notify config.
	 *
	 * @return the notify casier of this notify config
	 */
	public boolean getNotifyCasier();

	/**
	 * Returns <code>true</code> if this notify config is notify casier.
	 *
	 * @return <code>true</code> if this notify config is notify casier; <code>false</code> otherwise
	 */
	public boolean isNotifyCasier();

	/**
	 * Sets whether this notify config is notify casier.
	 *
	 * @param notifyCasier the notify casier of this notify config
	 */
	public void setNotifyCasier(boolean notifyCasier);

	/**
	 * Returns the notify actu of this notify config.
	 *
	 * @return the notify actu of this notify config
	 */
	public boolean getNotifyActu();

	/**
	 * Returns <code>true</code> if this notify config is notify actu.
	 *
	 * @return <code>true</code> if this notify config is notify actu; <code>false</code> otherwise
	 */
	public boolean isNotifyActu();

	/**
	 * Sets whether this notify config is notify actu.
	 *
	 * @param notifyActu the notify actu of this notify config
	 */
	public void setNotifyActu(boolean notifyActu);

	/**
	 * Returns the notify grp doc of this notify config.
	 *
	 * @return the notify grp doc of this notify config
	 */
	public boolean getNotifyGrpDoc();

	/**
	 * Returns <code>true</code> if this notify config is notify grp doc.
	 *
	 * @return <code>true</code> if this notify config is notify grp doc; <code>false</code> otherwise
	 */
	public boolean isNotifyGrpDoc();

	/**
	 * Sets whether this notify config is notify grp doc.
	 *
	 * @param notifyGrpDoc the notify grp doc of this notify config
	 */
	public void setNotifyGrpDoc(boolean notifyGrpDoc);

	/**
	 * Returns the notify agenda of this notify config.
	 *
	 * @return the notify agenda of this notify config
	 */
	public boolean getNotifyAgenda();

	/**
	 * Returns <code>true</code> if this notify config is notify agenda.
	 *
	 * @return <code>true</code> if this notify config is notify agenda; <code>false</code> otherwise
	 */
	public boolean isNotifyAgenda();

	/**
	 * Sets whether this notify config is notify agenda.
	 *
	 * @param notifyAgenda the notify agenda of this notify config
	 */
	public void setNotifyAgenda(boolean notifyAgenda);

	/**
	 * Returns the notify sync of this notify config.
	 *
	 * @return the notify sync of this notify config
	 */
	public boolean getNotifySync();

	/**
	 * Returns <code>true</code> if this notify config is notify sync.
	 *
	 * @return <code>true</code> if this notify config is notify sync; <code>false</code> otherwise
	 */
	public boolean isNotifySync();

	/**
	 * Sets whether this notify config is notify sync.
	 *
	 * @param notifySync the notify sync of this notify config
	 */
	public void setNotifySync(boolean notifySync);

	/**
	 * Returns the digest period of this notify config.
	 *
	 * @return the digest period of this notify config
	 */
	public int getDigestPeriod();

	/**
	 * Sets the digest period of this notify config.
	 *
	 * @param digestPeriod the digest period of this notify config
	 */
	public void setDigestPeriod(int digestPeriod);

	@Override
	public NotifyConfig cloneWithOriginalValues();

}