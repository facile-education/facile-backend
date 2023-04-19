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
 * The table class for the &quot;Schedule_StudentHomework&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomework
 * @generated
 */
public class StudentHomeworkTable extends BaseTable<StudentHomeworkTable> {

	public static final StudentHomeworkTable INSTANCE =
		new StudentHomeworkTable();

	public final Column<StudentHomeworkTable, Long> studentHomeworkId =
		createColumn(
			"studentHomeworkId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<StudentHomeworkTable, Long> homeworkId = createColumn(
		"homeworkId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StudentHomeworkTable, Long> studentId = createColumn(
		"studentId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StudentHomeworkTable, Boolean> isDone = createColumn(
		"isDone", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<StudentHomeworkTable, Boolean> isSent = createColumn(
		"isSent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<StudentHomeworkTable, Date> sentDate = createColumn(
		"sentDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<StudentHomeworkTable, Long> sentFileId = createColumn(
		"sentFileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private StudentHomeworkTable() {
		super("Schedule_StudentHomework", StudentHomeworkTable::new);
	}

}