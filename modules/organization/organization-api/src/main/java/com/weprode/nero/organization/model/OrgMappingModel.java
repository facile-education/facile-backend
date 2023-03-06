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

package com.weprode.nero.organization.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the OrgMapping service. Represents a row in the &quot;Organization_OrgMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.organization.model.impl.OrgMappingModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.organization.model.impl.OrgMappingImpl</code>.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMapping
 * @generated
 */
@ProviderType
public interface OrgMappingModel extends BaseModel<OrgMapping> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a org mapping model instance should use the {@link OrgMapping} interface instead.
	 */

	/**
	 * Returns the primary key of this org mapping.
	 *
	 * @return the primary key of this org mapping
	 */
	public String getPrimaryKey();

	/**
	 * Sets the primary key of this org mapping.
	 *
	 * @param primaryKey the primary key of this org mapping
	 */
	public void setPrimaryKey(String primaryKey);

	/**
	 * Returns the organization ID of this org mapping.
	 *
	 * @return the organization ID of this org mapping
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this org mapping.
	 *
	 * @param organizationId the organization ID of this org mapping
	 */
	public void setOrganizationId(long organizationId);

	/**
	 * Returns the ent structure uai of this org mapping.
	 *
	 * @return the ent structure uai of this org mapping
	 */
	@AutoEscape
	public String getEntStructureUAI();

	/**
	 * Sets the ent structure uai of this org mapping.
	 *
	 * @param entStructureUAI the ent structure uai of this org mapping
	 */
	public void setEntStructureUAI(String entStructureUAI);

	@Override
	public OrgMapping cloneWithOriginalValues();

}