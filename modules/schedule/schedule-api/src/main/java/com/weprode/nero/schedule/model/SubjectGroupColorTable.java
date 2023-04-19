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
 * The table class for the &quot;Schedule_SubjectGroupColor&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SubjectGroupColor
 * @generated
 */
public class SubjectGroupColorTable extends BaseTable<SubjectGroupColorTable> {

	public static final SubjectGroupColorTable INSTANCE =
		new SubjectGroupColorTable();

	public final Column<SubjectGroupColorTable, Long> subjectGroupColorId =
		createColumn(
			"subjectGroupColorId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<SubjectGroupColorTable, String> subject = createColumn(
		"subject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubjectGroupColorTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubjectGroupColorTable, String> color = createColumn(
		"color", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SubjectGroupColorTable() {
		super("Schedule_SubjectGroupColor", SubjectGroupColorTable::new);
	}

}