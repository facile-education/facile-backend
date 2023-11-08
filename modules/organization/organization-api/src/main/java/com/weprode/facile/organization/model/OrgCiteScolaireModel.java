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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the OrgCiteScolaire service. Represents a row in the &quot;Organization_OrgCiteScolaire&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.organization.model.impl.OrgCiteScolaireModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.organization.model.impl.OrgCiteScolaireImpl</code>.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgCiteScolaire
 * @generated
 */
@ProviderType
public interface OrgCiteScolaireModel extends BaseModel<OrgCiteScolaire> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a org cite scolaire model instance should use the {@link OrgCiteScolaire} interface instead.
	 */

	/**
	 * Returns the primary key of this org cite scolaire.
	 *
	 * @return the primary key of this org cite scolaire
	 */
	public String getPrimaryKey();

	/**
	 * Sets the primary key of this org cite scolaire.
	 *
	 * @param primaryKey the primary key of this org cite scolaire
	 */
	public void setPrimaryKey(String primaryKey);

	/**
	 * Returns the parent ent structure uai of this org cite scolaire.
	 *
	 * @return the parent ent structure uai of this org cite scolaire
	 */
	@AutoEscape
	public String getParentENTStructureUAI();

	/**
	 * Sets the parent ent structure uai of this org cite scolaire.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai of this org cite scolaire
	 */
	public void setParentENTStructureUAI(String parentENTStructureUAI);

	/**
	 * Returns the child ent structure uai of this org cite scolaire.
	 *
	 * @return the child ent structure uai of this org cite scolaire
	 */
	@AutoEscape
	public String getChildENTStructureUAI();

	/**
	 * Sets the child ent structure uai of this org cite scolaire.
	 *
	 * @param childENTStructureUAI the child ent structure uai of this org cite scolaire
	 */
	public void setChildENTStructureUAI(String childENTStructureUAI);

	@Override
	public OrgCiteScolaire cloneWithOriginalValues();

}