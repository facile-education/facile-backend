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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Schedule_DailySchedule&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DailySchedule
 * @generated
 */
public class DailyScheduleTable extends BaseTable<DailyScheduleTable> {

	public static final DailyScheduleTable INSTANCE = new DailyScheduleTable();

	public final Column<DailyScheduleTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DailyScheduleTable, Integer> sessionId = createColumn(
		"sessionId", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<DailyScheduleTable, String> sessionStartHour =
		createColumn(
			"sessionStartHour", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<DailyScheduleTable, String> sessionEndHour =
		createColumn(
			"sessionEndHour", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DailyScheduleTable() {
		super("Schedule_DailySchedule", DailyScheduleTable::new);
	}

}