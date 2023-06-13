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

import java.util.Date;

/**
 * The table class for the &quot;Schedule_SessionTeacher&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacher
 * @generated
 */
public class SessionTeacherTable extends BaseTable<SessionTeacherTable> {

	public static final SessionTeacherTable INSTANCE =
		new SessionTeacherTable();

	public final Column<SessionTeacherTable, Long> sessionTeacherId =
		createColumn(
			"sessionTeacherId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SessionTeacherTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionTeacherTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionTeacherTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SessionTeacherTable, Long> substituteId = createColumn(
		"substituteId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionTeacherTable, Date> modificationDate =
		createColumn(
			"modificationDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<SessionTeacherTable, String> privateNotes =
		createColumn(
			"privateNotes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SessionTeacherTable() {
		super("Schedule_SessionTeacher", SessionTeacherTable::new);
	}

}