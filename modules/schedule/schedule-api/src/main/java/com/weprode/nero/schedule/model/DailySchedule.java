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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the DailySchedule service. Represents a row in the &quot;Schedule_DailySchedule&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DailyScheduleModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.schedule.model.impl.DailyScheduleImpl"
)
@ProviderType
public interface DailySchedule extends DailyScheduleModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.schedule.model.impl.DailyScheduleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DailySchedule, Long> SCHOOL_ID_ACCESSOR =
		new Accessor<DailySchedule, Long>() {

			@Override
			public Long get(DailySchedule dailySchedule) {
				return dailySchedule.getSchoolId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DailySchedule> getTypeClass() {
				return DailySchedule.class;
			}

		};
	public static final Accessor<DailySchedule, Integer> SESSION_ID_ACCESSOR =
		new Accessor<DailySchedule, Integer>() {

			@Override
			public Integer get(DailySchedule dailySchedule) {
				return dailySchedule.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<DailySchedule> getTypeClass() {
				return DailySchedule.class;
			}

		};

}