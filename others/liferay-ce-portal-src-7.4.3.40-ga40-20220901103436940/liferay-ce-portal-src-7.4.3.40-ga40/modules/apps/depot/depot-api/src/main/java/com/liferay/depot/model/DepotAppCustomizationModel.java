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

package com.liferay.depot.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DepotAppCustomization service. Represents a row in the &quot;DepotAppCustomization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.depot.model.impl.DepotAppCustomizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.depot.model.impl.DepotAppCustomizationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DepotAppCustomization
 * @generated
 */
@ProviderType
public interface DepotAppCustomizationModel
	extends BaseModel<DepotAppCustomization>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a depot app customization model instance should use the {@link DepotAppCustomization} interface instead.
	 */

	/**
	 * Returns the primary key of this depot app customization.
	 *
	 * @return the primary key of this depot app customization
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this depot app customization.
	 *
	 * @param primaryKey the primary key of this depot app customization
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this depot app customization.
	 *
	 * @return the mvcc version of this depot app customization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this depot app customization.
	 *
	 * @param mvccVersion the mvcc version of this depot app customization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the depot app customization ID of this depot app customization.
	 *
	 * @return the depot app customization ID of this depot app customization
	 */
	public long getDepotAppCustomizationId();

	/**
	 * Sets the depot app customization ID of this depot app customization.
	 *
	 * @param depotAppCustomizationId the depot app customization ID of this depot app customization
	 */
	public void setDepotAppCustomizationId(long depotAppCustomizationId);

	/**
	 * Returns the company ID of this depot app customization.
	 *
	 * @return the company ID of this depot app customization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this depot app customization.
	 *
	 * @param companyId the company ID of this depot app customization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the depot entry ID of this depot app customization.
	 *
	 * @return the depot entry ID of this depot app customization
	 */
	public long getDepotEntryId();

	/**
	 * Sets the depot entry ID of this depot app customization.
	 *
	 * @param depotEntryId the depot entry ID of this depot app customization
	 */
	public void setDepotEntryId(long depotEntryId);

	/**
	 * Returns the enabled of this depot app customization.
	 *
	 * @return the enabled of this depot app customization
	 */
	public boolean getEnabled();

	/**
	 * Returns <code>true</code> if this depot app customization is enabled.
	 *
	 * @return <code>true</code> if this depot app customization is enabled; <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Sets whether this depot app customization is enabled.
	 *
	 * @param enabled the enabled of this depot app customization
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Returns the portlet ID of this depot app customization.
	 *
	 * @return the portlet ID of this depot app customization
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this depot app customization.
	 *
	 * @param portletId the portlet ID of this depot app customization
	 */
	public void setPortletId(String portletId);

	@Override
	public DepotAppCustomization cloneWithOriginalValues();

}