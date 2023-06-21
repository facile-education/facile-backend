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

package com.weprode.nero.course.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Course_Homework&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Homework
 * @generated
 */
public class HomeworkTable extends BaseTable<HomeworkTable> {

	public static final HomeworkTable INSTANCE = new HomeworkTable();

	public final Column<HomeworkTable, Long> homeworkId = createColumn(
		"homeworkId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HomeworkTable, Integer> homeworkType = createColumn(
		"homeworkType", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> courseId = createColumn(
		"courseId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Date> modificationDate = createColumn(
		"modificationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> sourceSessionId = createColumn(
		"sourceSessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Long> targetSessionId = createColumn(
		"targetSessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Date> targetDate = createColumn(
		"targetDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Boolean> isCustomStudentList =
		createColumn(
			"isCustomStudentList", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Integer> estimatedTime = createColumn(
		"estimatedTime", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Date> publicationDate = createColumn(
		"publicationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HomeworkTable, Boolean> isDraft = createColumn(
		"isDraft", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private HomeworkTable() {
		super("Course_Homework", HomeworkTable::new);
	}

}