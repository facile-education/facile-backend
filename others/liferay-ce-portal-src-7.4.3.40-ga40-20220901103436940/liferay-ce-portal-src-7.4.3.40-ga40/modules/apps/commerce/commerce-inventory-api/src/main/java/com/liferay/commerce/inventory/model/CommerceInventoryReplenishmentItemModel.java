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

package com.liferay.commerce.inventory.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceInventoryReplenishmentItem service. Represents a row in the &quot;CIReplenishmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItem
 * @generated
 */
@ProviderType
public interface CommerceInventoryReplenishmentItemModel
	extends BaseModel<CommerceInventoryReplenishmentItem>, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce inventory replenishment item model instance should use the {@link CommerceInventoryReplenishmentItem} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce inventory replenishment item.
	 *
	 * @return the primary key of this commerce inventory replenishment item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce inventory replenishment item.
	 *
	 * @param primaryKey the primary key of this commerce inventory replenishment item
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce inventory replenishment item.
	 *
	 * @return the mvcc version of this commerce inventory replenishment item
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce inventory replenishment item.
	 *
	 * @param mvccVersion the mvcc version of this commerce inventory replenishment item
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this commerce inventory replenishment item.
	 *
	 * @return the uuid of this commerce inventory replenishment item
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce inventory replenishment item.
	 *
	 * @param uuid the uuid of this commerce inventory replenishment item
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this commerce inventory replenishment item.
	 *
	 * @return the external reference code of this commerce inventory replenishment item
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce inventory replenishment item.
	 *
	 * @param externalReferenceCode the external reference code of this commerce inventory replenishment item
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce inventory replenishment item ID of this commerce inventory replenishment item.
	 *
	 * @return the commerce inventory replenishment item ID of this commerce inventory replenishment item
	 */
	public long getCommerceInventoryReplenishmentItemId();

	/**
	 * Sets the commerce inventory replenishment item ID of this commerce inventory replenishment item.
	 *
	 * @param commerceInventoryReplenishmentItemId the commerce inventory replenishment item ID of this commerce inventory replenishment item
	 */
	public void setCommerceInventoryReplenishmentItemId(
		long commerceInventoryReplenishmentItemId);

	/**
	 * Returns the company ID of this commerce inventory replenishment item.
	 *
	 * @return the company ID of this commerce inventory replenishment item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce inventory replenishment item.
	 *
	 * @param companyId the company ID of this commerce inventory replenishment item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce inventory replenishment item.
	 *
	 * @return the user ID of this commerce inventory replenishment item
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce inventory replenishment item.
	 *
	 * @param userId the user ID of this commerce inventory replenishment item
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce inventory replenishment item.
	 *
	 * @return the user uuid of this commerce inventory replenishment item
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce inventory replenishment item.
	 *
	 * @param userUuid the user uuid of this commerce inventory replenishment item
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce inventory replenishment item.
	 *
	 * @return the user name of this commerce inventory replenishment item
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce inventory replenishment item.
	 *
	 * @param userName the user name of this commerce inventory replenishment item
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce inventory replenishment item.
	 *
	 * @return the create date of this commerce inventory replenishment item
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce inventory replenishment item.
	 *
	 * @param createDate the create date of this commerce inventory replenishment item
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce inventory replenishment item.
	 *
	 * @return the modified date of this commerce inventory replenishment item
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce inventory replenishment item.
	 *
	 * @param modifiedDate the modified date of this commerce inventory replenishment item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce inventory warehouse ID of this commerce inventory replenishment item.
	 *
	 * @return the commerce inventory warehouse ID of this commerce inventory replenishment item
	 */
	public long getCommerceInventoryWarehouseId();

	/**
	 * Sets the commerce inventory warehouse ID of this commerce inventory replenishment item.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID of this commerce inventory replenishment item
	 */
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the sku of this commerce inventory replenishment item.
	 *
	 * @return the sku of this commerce inventory replenishment item
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this commerce inventory replenishment item.
	 *
	 * @param sku the sku of this commerce inventory replenishment item
	 */
	public void setSku(String sku);

	/**
	 * Returns the availability date of this commerce inventory replenishment item.
	 *
	 * @return the availability date of this commerce inventory replenishment item
	 */
	public Date getAvailabilityDate();

	/**
	 * Sets the availability date of this commerce inventory replenishment item.
	 *
	 * @param availabilityDate the availability date of this commerce inventory replenishment item
	 */
	public void setAvailabilityDate(Date availabilityDate);

	/**
	 * Returns the quantity of this commerce inventory replenishment item.
	 *
	 * @return the quantity of this commerce inventory replenishment item
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this commerce inventory replenishment item.
	 *
	 * @param quantity the quantity of this commerce inventory replenishment item
	 */
	public void setQuantity(int quantity);

	@Override
	public CommerceInventoryReplenishmentItem cloneWithOriginalValues();

}