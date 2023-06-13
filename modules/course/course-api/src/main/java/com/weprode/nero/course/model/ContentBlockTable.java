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
 * The table class for the &quot;Course_ContentBlock&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlock
 * @generated
 */
public class ContentBlockTable extends BaseTable<ContentBlockTable> {

	public static final ContentBlockTable INSTANCE = new ContentBlockTable();

	public final Column<ContentBlockTable, Long> blockId = createColumn(
		"blockId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ContentBlockTable, Long> courseItemId = createColumn(
		"courseItemId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, Date> modificationDate =
		createColumn(
			"modificationDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, String> blockName = createColumn(
		"blockName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, String> blockValue = createColumn(
		"blockValue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, Integer> blockType = createColumn(
		"blockType", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ContentBlockTable, Integer> order = createColumn(
		"order_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private ContentBlockTable() {
		super("Course_ContentBlock", ContentBlockTable::new);
	}

}