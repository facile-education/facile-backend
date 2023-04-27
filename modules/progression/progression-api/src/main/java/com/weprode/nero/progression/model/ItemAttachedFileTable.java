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

/**
 * The table class for the &quot;Progression_ItemAttachedFile&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFile
 * @generated
 */
public class ItemAttachedFileTable extends BaseTable<ItemAttachedFileTable> {

	public static final ItemAttachedFileTable INSTANCE =
		new ItemAttachedFileTable();

	public final Column<ItemAttachedFileTable, Long> itemAttachedFileId =
		createColumn(
			"itemAttachedFileId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<ItemAttachedFileTable, Long> progressionItemId =
		createColumn(
			"progressionItemId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItemAttachedFileTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItemAttachedFileTable, Boolean> isAudioRecording =
		createColumn(
			"isAudioRecording", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItemAttachedFileTable, Boolean> isToBeCompleted =
		createColumn(
			"isToBeCompleted", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private ItemAttachedFileTable() {
		super("Progression_ItemAttachedFile", ItemAttachedFileTable::new);
	}

}