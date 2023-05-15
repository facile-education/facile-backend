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

package com.liferay.commerce.product.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceChannel service. Represents a row in the &quot;CommerceChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CommerceChannelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CommerceChannelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannel
 * @generated
 */
@ProviderType
public interface CommerceChannelModel
	extends BaseModel<CommerceChannel>, CTModel<CommerceChannel>, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce channel model instance should use the {@link CommerceChannel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce channel.
	 *
	 * @return the primary key of this commerce channel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce channel.
	 *
	 * @param primaryKey the primary key of this commerce channel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce channel.
	 *
	 * @return the mvcc version of this commerce channel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce channel.
	 *
	 * @param mvccVersion the mvcc version of this commerce channel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this commerce channel.
	 *
	 * @return the ct collection ID of this commerce channel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this commerce channel.
	 *
	 * @param ctCollectionId the ct collection ID of this commerce channel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this commerce channel.
	 *
	 * @return the uuid of this commerce channel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce channel.
	 *
	 * @param uuid the uuid of this commerce channel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this commerce channel.
	 *
	 * @return the external reference code of this commerce channel
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce channel.
	 *
	 * @param externalReferenceCode the external reference code of this commerce channel
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce channel ID of this commerce channel.
	 *
	 * @return the commerce channel ID of this commerce channel
	 */
	public long getCommerceChannelId();

	/**
	 * Sets the commerce channel ID of this commerce channel.
	 *
	 * @param commerceChannelId the commerce channel ID of this commerce channel
	 */
	public void setCommerceChannelId(long commerceChannelId);

	/**
	 * Returns the company ID of this commerce channel.
	 *
	 * @return the company ID of this commerce channel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce channel.
	 *
	 * @param companyId the company ID of this commerce channel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce channel.
	 *
	 * @return the user ID of this commerce channel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce channel.
	 *
	 * @param userId the user ID of this commerce channel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce channel.
	 *
	 * @return the user uuid of this commerce channel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce channel.
	 *
	 * @param userUuid the user uuid of this commerce channel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce channel.
	 *
	 * @return the user name of this commerce channel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce channel.
	 *
	 * @param userName the user name of this commerce channel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce channel.
	 *
	 * @return the create date of this commerce channel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce channel.
	 *
	 * @param createDate the create date of this commerce channel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce channel.
	 *
	 * @return the modified date of this commerce channel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce channel.
	 *
	 * @param modifiedDate the modified date of this commerce channel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the site group ID of this commerce channel.
	 *
	 * @return the site group ID of this commerce channel
	 */
	public long getSiteGroupId();

	/**
	 * Sets the site group ID of this commerce channel.
	 *
	 * @param siteGroupId the site group ID of this commerce channel
	 */
	public void setSiteGroupId(long siteGroupId);

	/**
	 * Returns the name of this commerce channel.
	 *
	 * @return the name of this commerce channel
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce channel.
	 *
	 * @param name the name of this commerce channel
	 */
	public void setName(String name);

	/**
	 * Returns the type of this commerce channel.
	 *
	 * @return the type of this commerce channel
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this commerce channel.
	 *
	 * @param type the type of this commerce channel
	 */
	public void setType(String type);

	/**
	 * Returns the type settings of this commerce channel.
	 *
	 * @return the type settings of this commerce channel
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this commerce channel.
	 *
	 * @param typeSettings the type settings of this commerce channel
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the commerce currency code of this commerce channel.
	 *
	 * @return the commerce currency code of this commerce channel
	 */
	@AutoEscape
	public String getCommerceCurrencyCode();

	/**
	 * Sets the commerce currency code of this commerce channel.
	 *
	 * @param commerceCurrencyCode the commerce currency code of this commerce channel
	 */
	public void setCommerceCurrencyCode(String commerceCurrencyCode);

	/**
	 * Returns the price display type of this commerce channel.
	 *
	 * @return the price display type of this commerce channel
	 */
	@AutoEscape
	public String getPriceDisplayType();

	/**
	 * Sets the price display type of this commerce channel.
	 *
	 * @param priceDisplayType the price display type of this commerce channel
	 */
	public void setPriceDisplayType(String priceDisplayType);

	/**
	 * Returns the discounts target net price of this commerce channel.
	 *
	 * @return the discounts target net price of this commerce channel
	 */
	public boolean getDiscountsTargetNetPrice();

	/**
	 * Returns <code>true</code> if this commerce channel is discounts target net price.
	 *
	 * @return <code>true</code> if this commerce channel is discounts target net price; <code>false</code> otherwise
	 */
	public boolean isDiscountsTargetNetPrice();

	/**
	 * Sets whether this commerce channel is discounts target net price.
	 *
	 * @param discountsTargetNetPrice the discounts target net price of this commerce channel
	 */
	public void setDiscountsTargetNetPrice(boolean discountsTargetNetPrice);

	@Override
	public CommerceChannel cloneWithOriginalValues();

}