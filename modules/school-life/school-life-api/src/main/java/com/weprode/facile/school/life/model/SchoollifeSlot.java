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

package com.weprode.facile.school.life.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SchoollifeSlot service. Represents a row in the &quot;Schoollife_SchoollifeSlot&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.facile.school.life.model.impl.SchoollifeSlotImpl"
)
@ProviderType
public interface SchoollifeSlot extends PersistedModel, SchoollifeSlotModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.facile.school.life.model.impl.SchoollifeSlotImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SchoollifeSlot, Long>
		SCHOOLLIFE_SLOT_ID_ACCESSOR = new Accessor<SchoollifeSlot, Long>() {

			@Override
			public Long get(SchoollifeSlot schoollifeSlot) {
				return schoollifeSlot.getSchoollifeSlotId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SchoollifeSlot> getTypeClass() {
				return SchoollifeSlot.class;
			}

		};

}