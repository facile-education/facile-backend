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

package com.weprode.nero.progression.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ItemAssignment service. Represents a row in the &quot;Progression_ItemAssignment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.progression.model.impl.ItemAssignmentImpl"
)
@ProviderType
public interface ItemAssignment extends ItemAssignmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.progression.model.impl.ItemAssignmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ItemAssignment, Long>
		PROGRESSION_ITEM_ID_ACCESSOR = new Accessor<ItemAssignment, Long>() {

			@Override
			public Long get(ItemAssignment itemAssignment) {
				return itemAssignment.getProgressionItemId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ItemAssignment> getTypeClass() {
				return ItemAssignment.class;
			}

		};
	public static final Accessor<ItemAssignment, Long> SESSION_ID_ACCESSOR =
		new Accessor<ItemAssignment, Long>() {

			@Override
			public Long get(ItemAssignment itemAssignment) {
				return itemAssignment.getSessionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ItemAssignment> getTypeClass() {
				return ItemAssignment.class;
			}

		};

	public com.liferay.portal.kernel.json.JSONObject convertToJSON(long userId);

}