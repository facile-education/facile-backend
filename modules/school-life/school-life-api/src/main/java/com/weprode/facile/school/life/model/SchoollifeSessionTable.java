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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Schoollife_SchoollifeSession&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSession
 * @generated
 */
public class SchoollifeSessionTable extends BaseTable<SchoollifeSessionTable> {

	public static final SchoollifeSessionTable INSTANCE =
		new SchoollifeSessionTable();

	public final Column<SchoollifeSessionTable, Long> schoollifeSessionId =
		createColumn(
			"schoollifeSessionId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<SchoollifeSessionTable, Long> schoollifeSlotId =
		createColumn(
			"schoollifeSlotId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Integer> weekNb = createColumn(
		"weekNb", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Boolean> rollCalled =
		createColumn(
			"rollCalled", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SchoollifeSessionTable, Boolean>
		absenceNotificationSent = createColumn(
			"absenceNotificationSent", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private SchoollifeSessionTable() {
		super("Schoollife_SchoollifeSession", SchoollifeSessionTable::new);
	}

}