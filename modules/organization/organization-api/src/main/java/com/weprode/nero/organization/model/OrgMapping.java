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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the OrgMapping service. Represents a row in the &quot;Organization_OrgMapping&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marc Salvat
 * @see OrgMappingModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.organization.model.impl.OrgMappingImpl"
)
@ProviderType
public interface OrgMapping extends OrgMappingModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.organization.model.impl.OrgMappingImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OrgMapping, String>
		ENT_STRUCTURE_UAI_ACCESSOR = new Accessor<OrgMapping, String>() {

			@Override
			public String get(OrgMapping orgMapping) {
				return orgMapping.getEntStructureUAI();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<OrgMapping> getTypeClass() {
				return OrgMapping.class;
			}

		};

}