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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OrgCiteScolaire}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgCiteScolaire
 * @generated
 */
public class OrgCiteScolaireWrapper
	extends BaseModelWrapper<OrgCiteScolaire>
	implements ModelWrapper<OrgCiteScolaire>, OrgCiteScolaire {

	public OrgCiteScolaireWrapper(OrgCiteScolaire orgCiteScolaire) {
		super(orgCiteScolaire);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parentENTStructureUAI", getParentENTStructureUAI());
		attributes.put("childENTStructureUAI", getChildENTStructureUAI());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String parentENTStructureUAI = (String)attributes.get(
			"parentENTStructureUAI");

		if (parentENTStructureUAI != null) {
			setParentENTStructureUAI(parentENTStructureUAI);
		}

		String childENTStructureUAI = (String)attributes.get(
			"childENTStructureUAI");

		if (childENTStructureUAI != null) {
			setChildENTStructureUAI(childENTStructureUAI);
		}
	}

	@Override
	public OrgCiteScolaire cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the child ent structure uai of this org cite scolaire.
	 *
	 * @return the child ent structure uai of this org cite scolaire
	 */
	@Override
	public String getChildENTStructureUAI() {
		return model.getChildENTStructureUAI();
	}

	/**
	 * Returns the parent ent structure uai of this org cite scolaire.
	 *
	 * @return the parent ent structure uai of this org cite scolaire
	 */
	@Override
	public String getParentENTStructureUAI() {
		return model.getParentENTStructureUAI();
	}

	/**
	 * Returns the primary key of this org cite scolaire.
	 *
	 * @return the primary key of this org cite scolaire
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
	 * Sets the child ent structure uai of this org cite scolaire.
	 *
	 * @param childENTStructureUAI the child ent structure uai of this org cite scolaire
	 */
	@Override
	public void setChildENTStructureUAI(String childENTStructureUAI) {
		model.setChildENTStructureUAI(childENTStructureUAI);
	}

	/**
	 * Sets the parent ent structure uai of this org cite scolaire.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai of this org cite scolaire
	 */
	@Override
	public void setParentENTStructureUAI(String parentENTStructureUAI) {
		model.setParentENTStructureUAI(parentENTStructureUAI);
	}

	/**
	 * Sets the primary key of this org cite scolaire.
	 *
	 * @param primaryKey the primary key of this org cite scolaire
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected OrgCiteScolaireWrapper wrap(OrgCiteScolaire orgCiteScolaire) {
		return new OrgCiteScolaireWrapper(orgCiteScolaire);
	}

}