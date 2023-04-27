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
 * The table class for the &quot;Progression_ItemAssignment&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignment
 * @generated
 */
public class ItemAssignmentTable extends BaseTable<ItemAssignmentTable> {

	public static final ItemAssignmentTable INSTANCE =
		new ItemAssignmentTable();

	public final Column<ItemAssignmentTable, Long> progressionItemId =
		createColumn(
			"progressionItemId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ItemAssignmentTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ItemAssignmentTable, Long> homeworkId = createColumn(
		"homeworkId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItemAssignmentTable, Date> assignedDate = createColumn(
		"assignedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ItemAssignmentTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ItemAssignmentTable() {
		super("Progression_ItemAssignment", ItemAssignmentTable::new);
	}

}