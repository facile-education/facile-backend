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
 * The table class for the &quot;Progression_ProgressionItem&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItem
 * @generated
 */
public class ProgressionItemTable extends BaseTable<ProgressionItemTable> {

	public static final ProgressionItemTable INSTANCE =
		new ProgressionItemTable();

	public final Column<ProgressionItemTable, Long> progressionItemId =
		createColumn(
			"progressionItemId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ProgressionItemTable, Long> progressionId =
		createColumn(
			"progressionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Long> homeworkId = createColumn(
		"homeworkId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Long> progressionFolderId =
		createColumn(
			"progressionFolderId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, String> itemName = createColumn(
		"itemName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Boolean> isHomework =
		createColumn(
			"isHomework", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, String> duration = createColumn(
		"duration", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ProgressionItemTable, Integer> order = createColumn(
		"order_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private ProgressionItemTable() {
		super("Progression_ProgressionItem", ProgressionItemTable::new);
	}

}