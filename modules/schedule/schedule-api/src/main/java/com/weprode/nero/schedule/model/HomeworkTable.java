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
 * The table class for the &quot;Schedule_Homework&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Homework
 * @generated
 */
public class HomeworkTable extends BaseTable<HomeworkTable> {

	public static final HomeworkTable INSTANCE = new HomeworkTable();

	public final Column<HomeworkTable, Long> homeworkId = createColumn(
		"homeworkId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HomeworkTable, Long> type = createColumn(
		"type_", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> sourceSessionId = createColumn(
		"sourceSessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> targetSessionId = createColumn(
		"targetSessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Integer> targetWeekId = createColumn(
		"targetWeekId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Date> targetDate = createColumn(
		"targetDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> estimatedTime = createColumn(
		"estimatedTime", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Date> fromDate = createColumn(
		"fromDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Boolean> isCustomStudentList =
		createColumn(
			"isCustomStudentList", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private HomeworkTable() {
		super("Schedule_Homework", HomeworkTable::new);
	}

}