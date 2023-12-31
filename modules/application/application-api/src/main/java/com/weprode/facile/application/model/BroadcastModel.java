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

package com.weprode.facile.application.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Broadcast service. Represents a row in the &quot;Application_Broadcast&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.application.model.impl.BroadcastImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Broadcast
 * @generated
 */
@ProviderType
public interface BroadcastModel extends BaseModel<Broadcast> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a broadcast model instance should use the {@link Broadcast} interface instead.
	 */

	/**
	 * Returns the primary key of this broadcast.
	 *
	 * @return the primary key of this broadcast
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this broadcast.
	 *
	 * @param primaryKey the primary key of this broadcast
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the broadcast ID of this broadcast.
	 *
	 * @return the broadcast ID of this broadcast
	 */
	public long getBroadcastId();

	/**
	 * Sets the broadcast ID of this broadcast.
	 *
	 * @param broadcastId the broadcast ID of this broadcast
	 */
	public void setBroadcastId(long broadcastId);

	/**
	 * Returns the school ID of this broadcast.
	 *
	 * @return the school ID of this broadcast
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this broadcast.
	 *
	 * @param schoolId the school ID of this broadcast
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the application ID of this broadcast.
	 *
	 * @return the application ID of this broadcast
	 */
	public long getApplicationId();

	/**
	 * Sets the application ID of this broadcast.
	 *
	 * @param applicationId the application ID of this broadcast
	 */
	public void setApplicationId(long applicationId);

	/**
	 * Returns the is broadcasted of this broadcast.
	 *
	 * @return the is broadcasted of this broadcast
	 */
	public boolean getIsBroadcasted();

	/**
	 * Returns <code>true</code> if this broadcast is is broadcasted.
	 *
	 * @return <code>true</code> if this broadcast is is broadcasted; <code>false</code> otherwise
	 */
	public boolean isIsBroadcasted();

	/**
	 * Sets whether this broadcast is is broadcasted.
	 *
	 * @param isBroadcasted the is broadcasted of this broadcast
	 */
	public void setIsBroadcasted(boolean isBroadcasted);

	/**
	 * Returns the application url of this broadcast.
	 *
	 * @return the application url of this broadcast
	 */
	@AutoEscape
	public String getApplicationUrl();

	/**
	 * Sets the application url of this broadcast.
	 *
	 * @param applicationUrl the application url of this broadcast
	 */
	public void setApplicationUrl(String applicationUrl);

	@Override
	public Broadcast cloneWithOriginalValues();

}