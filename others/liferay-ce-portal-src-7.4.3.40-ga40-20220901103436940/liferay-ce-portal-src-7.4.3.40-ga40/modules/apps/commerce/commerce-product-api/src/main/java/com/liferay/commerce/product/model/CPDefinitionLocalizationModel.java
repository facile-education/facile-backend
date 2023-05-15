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
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CPDefinitionLocalization service. Represents a row in the &quot;CPDefinitionLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPDefinitionLocalizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPDefinitionLocalizationImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionLocalization
 * @generated
 */
@ProviderType
public interface CPDefinitionLocalizationModel
	extends BaseModel<CPDefinitionLocalization>,
			CTModel<CPDefinitionLocalization>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp definition localization model instance should use the {@link CPDefinitionLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this cp definition localization.
	 *
	 * @return the primary key of this cp definition localization
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp definition localization.
	 *
	 * @param primaryKey the primary key of this cp definition localization
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this cp definition localization.
	 *
	 * @return the mvcc version of this cp definition localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this cp definition localization.
	 *
	 * @param mvccVersion the mvcc version of this cp definition localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this cp definition localization.
	 *
	 * @return the ct collection ID of this cp definition localization
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this cp definition localization.
	 *
	 * @param ctCollectionId the ct collection ID of this cp definition localization
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the cp definition localization ID of this cp definition localization.
	 *
	 * @return the cp definition localization ID of this cp definition localization
	 */
	public long getCpDefinitionLocalizationId();

	/**
	 * Sets the cp definition localization ID of this cp definition localization.
	 *
	 * @param cpDefinitionLocalizationId the cp definition localization ID of this cp definition localization
	 */
	public void setCpDefinitionLocalizationId(long cpDefinitionLocalizationId);

	/**
	 * Returns the company ID of this cp definition localization.
	 *
	 * @return the company ID of this cp definition localization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp definition localization.
	 *
	 * @param companyId the company ID of this cp definition localization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the cp definition ID of this cp definition localization.
	 *
	 * @return the cp definition ID of this cp definition localization
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cp definition localization.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp definition localization
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the language ID of this cp definition localization.
	 *
	 * @return the language ID of this cp definition localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this cp definition localization.
	 *
	 * @param languageId the language ID of this cp definition localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the name of this cp definition localization.
	 *
	 * @return the name of this cp definition localization
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this cp definition localization.
	 *
	 * @param name the name of this cp definition localization
	 */
	public void setName(String name);

	/**
	 * Returns the short description of this cp definition localization.
	 *
	 * @return the short description of this cp definition localization
	 */
	@AutoEscape
	public String getShortDescription();

	/**
	 * Sets the short description of this cp definition localization.
	 *
	 * @param shortDescription the short description of this cp definition localization
	 */
	public void setShortDescription(String shortDescription);

	/**
	 * Returns the description of this cp definition localization.
	 *
	 * @return the description of this cp definition localization
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this cp definition localization.
	 *
	 * @param description the description of this cp definition localization
	 */
	public void setDescription(String description);

	/**
	 * Returns the meta title of this cp definition localization.
	 *
	 * @return the meta title of this cp definition localization
	 */
	@AutoEscape
	public String getMetaTitle();

	/**
	 * Sets the meta title of this cp definition localization.
	 *
	 * @param metaTitle the meta title of this cp definition localization
	 */
	public void setMetaTitle(String metaTitle);

	/**
	 * Returns the meta description of this cp definition localization.
	 *
	 * @return the meta description of this cp definition localization
	 */
	@AutoEscape
	public String getMetaDescription();

	/**
	 * Sets the meta description of this cp definition localization.
	 *
	 * @param metaDescription the meta description of this cp definition localization
	 */
	public void setMetaDescription(String metaDescription);

	/**
	 * Returns the meta keywords of this cp definition localization.
	 *
	 * @return the meta keywords of this cp definition localization
	 */
	@AutoEscape
	public String getMetaKeywords();

	/**
	 * Sets the meta keywords of this cp definition localization.
	 *
	 * @param metaKeywords the meta keywords of this cp definition localization
	 */
	public void setMetaKeywords(String metaKeywords);

	@Override
	public CPDefinitionLocalization cloneWithOriginalValues();

}