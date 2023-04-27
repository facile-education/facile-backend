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
 * The table class for the &quot;Progression_ItemContent&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ItemContent
 * @generated
 */
public class ItemContentTable extends BaseTable<ItemContentTable> {

	public static final ItemContentTable INSTANCE = new ItemContentTable();

	public final Column<ItemContentTable, Long> contentId = createColumn(
		"contentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ItemContentTable, Long> progressionItemId =
		createColumn(
			"progressionItemId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, String> contentName = createColumn(
		"contentName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, String> contentValue = createColumn(
		"contentValue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, Integer> contentType = createColumn(
		"contentType", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, Integer> order = createColumn(
		"order_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItemContentTable, Boolean> isToBeCompleted =
		createColumn(
			"isToBeCompleted", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private ItemContentTable() {
		super("Progression_ItemContent", ItemContentTable::new);
	}

}