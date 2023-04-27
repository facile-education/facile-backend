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

package com.weprode.nero.school.life.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Schoollife_SchoollifeSlot&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlot
 * @generated
 */
public class SchoollifeSlotTable extends BaseTable<SchoollifeSlotTable> {

	public static final SchoollifeSlotTable INSTANCE =
		new SchoollifeSlotTable();

	public final Column<SchoollifeSlotTable, Long> schoollifeSlotId =
		createColumn(
			"schoollifeSlotId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SchoollifeSlotTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, Integer> day = createColumn(
		"day", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, String> startHour = createColumn(
		"startHour", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, String> endHour = createColumn(
		"endHour", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, String> room = createColumn(
		"room", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSlotTable, Integer> capacity = createColumn(
		"capacity", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private SchoollifeSlotTable() {
		super("Schoollife_SchoollifeSlot", SchoollifeSlotTable::new);
	}

}