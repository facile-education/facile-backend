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

package com.weprode.facile.organization.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OrgMapping}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMapping
 * @generated
 */
public class OrgMappingWrapper
	extends BaseModelWrapper<OrgMapping>
	implements ModelWrapper<OrgMapping>, OrgMapping {

	public OrgMappingWrapper(OrgMapping orgMapping) {
		super(orgMapping);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("organizationId", getOrganizationId());
		attributes.put("entStructureUAI", getEntStructureUAI());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		String entStructureUAI = (String)attributes.get("entStructureUAI");

		if (entStructureUAI != null) {
			setEntStructureUAI(entStructureUAI);
		}
	}

	@Override
	public OrgMapping cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the ent structure uai of this org mapping.
	 *
	 * @return the ent structure uai of this org mapping
	 */
	@Override
	public String getEntStructureUAI() {
		return model.getEntStructureUAI();
	}

	/**
	 * Returns the organization ID of this org mapping.
	 *
	 * @return the organization ID of this org mapping
	 */
	@Override
	public long getOrganizationId() {
		return model.getOrganizationId();
	}

	/**
	 * Returns the primary key of this org mapping.
	 *
	 * @return the primary key of this org mapping
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ent structure uai of this org mapping.
	 *
	 * @param entStructureUAI the ent structure uai of this org mapping
	 */
	@Override
	public void setEntStructureUAI(String entStructureUAI) {
		model.setEntStructureUAI(entStructureUAI);
	}

	/**
	 * Sets the organization ID of this org mapping.
	 *
	 * @param organizationId the organization ID of this org mapping
	 */
	@Override
	public void setOrganizationId(long organizationId) {
		model.setOrganizationId(organizationId);
	}

	/**
	 * Sets the primary key of this org mapping.
	 *
	 * @param primaryKey the primary key of this org mapping
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected OrgMappingWrapper wrap(OrgMapping orgMapping) {
		return new OrgMappingWrapper(orgMapping);
	}

}