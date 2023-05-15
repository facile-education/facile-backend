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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.VirtualHostModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.VirtualHostImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @generated
 */
@ProviderType
public interface VirtualHostModel
	extends BaseModel<VirtualHost>, CTModel<VirtualHost>, MVCCModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a virtual host model instance should use the {@link VirtualHost} interface instead.
	 */

	/**
	 * Returns the primary key of this virtual host.
	 *
	 * @return the primary key of this virtual host
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this virtual host.
	 *
	 * @param primaryKey the primary key of this virtual host
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this virtual host.
	 *
	 * @return the mvcc version of this virtual host
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this virtual host.
	 *
	 * @param mvccVersion the mvcc version of this virtual host
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this virtual host.
	 *
	 * @return the ct collection ID of this virtual host
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this virtual host.
	 *
	 * @param ctCollectionId the ct collection ID of this virtual host
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the virtual host ID of this virtual host.
	 *
	 * @return the virtual host ID of this virtual host
	 */
	public long getVirtualHostId();

	/**
	 * Sets the virtual host ID of this virtual host.
	 *
	 * @param virtualHostId the virtual host ID of this virtual host
	 */
	public void setVirtualHostId(long virtualHostId);

	/**
	 * Returns the company ID of this virtual host.
	 *
	 * @return the company ID of this virtual host
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this virtual host.
	 *
	 * @param companyId the company ID of this virtual host
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the layout set ID of this virtual host.
	 *
	 * @return the layout set ID of this virtual host
	 */
	public long getLayoutSetId();

	/**
	 * Sets the layout set ID of this virtual host.
	 *
	 * @param layoutSetId the layout set ID of this virtual host
	 */
	public void setLayoutSetId(long layoutSetId);

	/**
	 * Returns the hostname of this virtual host.
	 *
	 * @return the hostname of this virtual host
	 */
	@AutoEscape
	public String getHostname();

	/**
	 * Sets the hostname of this virtual host.
	 *
	 * @param hostname the hostname of this virtual host
	 */
	public void setHostname(String hostname);

	/**
	 * Returns the default virtual host of this virtual host.
	 *
	 * @return the default virtual host of this virtual host
	 */
	public boolean getDefaultVirtualHost();

	/**
	 * Returns <code>true</code> if this virtual host is default virtual host.
	 *
	 * @return <code>true</code> if this virtual host is default virtual host; <code>false</code> otherwise
	 */
	public boolean isDefaultVirtualHost();

	/**
	 * Sets whether this virtual host is default virtual host.
	 *
	 * @param defaultVirtualHost the default virtual host of this virtual host
	 */
	public void setDefaultVirtualHost(boolean defaultVirtualHost);

	/**
	 * Returns the language ID of this virtual host.
	 *
	 * @return the language ID of this virtual host
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this virtual host.
	 *
	 * @param languageId the language ID of this virtual host
	 */
	public void setLanguageId(String languageId);

	@Override
	public VirtualHost cloneWithOriginalValues();

}