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

package com.weprode.facile.schedule.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SlotConfiguration service. Represents a row in the &quot;Schedule_SlotConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.facile.schedule.model.impl.SlotConfigurationImpl"
)
@ProviderType
public interface SlotConfiguration
	extends PersistedModel, SlotConfigurationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.facile.schedule.model.impl.SlotConfigurationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SlotConfiguration, Long> SCHOOL_ID_ACCESSOR =
		new Accessor<SlotConfiguration, Long>() {

			@Override
			public Long get(SlotConfiguration slotConfiguration) {
				return slotConfiguration.getSchoolId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SlotConfiguration> getTypeClass() {
				return SlotConfiguration.class;
			}

		};
	public static final Accessor<SlotConfiguration, Integer>
		SLOT_NUMBER_ACCESSOR = new Accessor<SlotConfiguration, Integer>() {

			@Override
			public Integer get(SlotConfiguration slotConfiguration) {
				return slotConfiguration.getSlotNumber();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<SlotConfiguration> getTypeClass() {
				return SlotConfiguration.class;
			}

		};

}