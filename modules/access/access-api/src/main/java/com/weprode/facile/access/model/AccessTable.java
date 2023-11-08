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

package com.weprode.facile.access.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Access_Access&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Access
 * @generated
 */
public class AccessTable extends BaseTable<AccessTable> {

	public static final AccessTable INSTANCE = new AccessTable();

	public final Column<AccessTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Long> accessId = createColumn(
		"accessId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AccessTable, Long> categoryId = createColumn(
		"categoryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccessTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<AccessTable, String> externalUrl = createColumn(
		"externalUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Long> folderId = createColumn(
		"folderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Long> fileId = createColumn(
		"fileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Long> thumbnailId = createColumn(
		"thumbnailId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccessTable, Integer> position = createColumn(
		"position", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private AccessTable() {
		super("Access_Access", AccessTable::new);
	}

}