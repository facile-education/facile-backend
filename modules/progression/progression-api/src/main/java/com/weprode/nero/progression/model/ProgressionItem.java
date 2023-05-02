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
 * The extended model interface for the ProgressionItem service. Represents a row in the &quot;Progression_ProgressionItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.progression.model.impl.ProgressionItemImpl"
)
@ProviderType
public interface ProgressionItem extends PersistedModel, ProgressionItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.progression.model.impl.ProgressionItemImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProgressionItem, Long>
		PROGRESSION_ITEM_ID_ACCESSOR = new Accessor<ProgressionItem, Long>() {

			@Override
			public Long get(ProgressionItem progressionItem) {
				return progressionItem.getProgressionItemId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ProgressionItem> getTypeClass() {
				return ProgressionItem.class;
			}

		};

	public org.json.JSONObject convertToJSON(long userId);

	public org.json.JSONObject convertToJSON(
		long userId, boolean isContentIncluded);

}