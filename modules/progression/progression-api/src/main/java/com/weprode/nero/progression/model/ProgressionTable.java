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

package com.weprode.nero.progression.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Progression_Progression&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Progression
 * @generated
 */
public class ProgressionTable extends BaseTable<ProgressionTable> {

	public static final ProgressionTable INSTANCE = new ProgressionTable();

	public final Column<ProgressionTable, Long> progressionId = createColumn(
		"progressionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ProgressionTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, String> volee = createColumn(
		"volee", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, Long> subjectId = createColumn(
		"subjectId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ProgressionTable, String> color = createColumn(
		"color", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ProgressionTable() {
		super("Progression_Progression", ProgressionTable::new);
	}

}