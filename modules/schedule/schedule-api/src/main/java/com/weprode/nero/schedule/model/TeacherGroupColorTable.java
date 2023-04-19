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
 * The table class for the &quot;Schedule_TeacherGroupColor&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherGroupColor
 * @generated
 */
public class TeacherGroupColorTable extends BaseTable<TeacherGroupColorTable> {

	public static final TeacherGroupColorTable INSTANCE =
		new TeacherGroupColorTable();

	public final Column<TeacherGroupColorTable, Long> teacherGroupColorId =
		createColumn(
			"teacherGroupColorId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<TeacherGroupColorTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TeacherGroupColorTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TeacherGroupColorTable, String> color = createColumn(
		"color", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TeacherGroupColorTable() {
		super("Schedule_TeacherGroupColor", TeacherGroupColorTable::new);
	}

}