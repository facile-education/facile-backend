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

package com.weprode.nero.document.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Document_Activity&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Activity
 * @generated
 */
public class ActivityTable extends BaseTable<ActivityTable> {

	public static final ActivityTable INSTANCE = new ActivityTable();

	public final Column<ActivityTable, Long> activityId = createColumn(
		"activityId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ActivityTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, Long> folderId = createColumn(
		"folderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, String> fileName = createColumn(
		"fileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, String> folderName = createColumn(
		"folderName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ActivityTable, Date> modificationDate = createColumn(
		"modificationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ActivityTable() {
		super("Document_Activity", ActivityTable::new);
	}

}