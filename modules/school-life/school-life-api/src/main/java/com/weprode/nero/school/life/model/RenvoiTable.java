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

import java.util.Date;

/**
 * The table class for the &quot;Schoollife_Renvoi&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Renvoi
 * @generated
 */
public class RenvoiTable extends BaseTable<RenvoiTable> {

	public static final RenvoiTable INSTANCE = new RenvoiTable();

	public final Column<RenvoiTable, Long> schoollifeSessionId = createColumn(
		"schoollifeSessionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<RenvoiTable, Long> studentId = createColumn(
		"studentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<RenvoiTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Date> renvoiDate = createColumn(
		"renvoiDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Long> sourceSessionId = createColumn(
		"sourceSessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Long> sourceSchoollifeSessionId =
		createColumn(
			"sourceSchoollifeSessionId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Long> sourceTeacherId = createColumn(
		"sourceTeacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, String> reason = createColumn(
		"reason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RenvoiTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private RenvoiTable() {
		super("Schoollife_Renvoi", RenvoiTable::new);
	}

}