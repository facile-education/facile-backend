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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Schedule_ScheduleConfiguration&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfiguration
 * @generated
 */
public class ScheduleConfigurationTable
	extends BaseTable<ScheduleConfigurationTable> {

	public static final ScheduleConfigurationTable INSTANCE =
		new ScheduleConfigurationTable();

	public final Column<ScheduleConfigurationTable, Long> configId =
		createColumn("configId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ScheduleConfigurationTable, Date> projectStartDate =
		createColumn(
			"projectStartDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ScheduleConfigurationTable, Date> schoolYearStartDate =
		createColumn(
			"schoolYearStartDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ScheduleConfigurationTable, Date>
		schoolYearSemesterDate = createColumn(
			"schoolYearSemesterDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ScheduleConfigurationTable, Date> schoolYearEndDate =
		createColumn(
			"schoolYearEndDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ScheduleConfigurationTable, String> h1Weeks =
		createColumn(
			"h1Weeks", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ScheduleConfigurationTable, String> h2Weeks =
		createColumn(
			"h2Weeks", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ScheduleConfigurationTable() {
		super(
			"Schedule_ScheduleConfiguration", ScheduleConfigurationTable::new);
	}

}