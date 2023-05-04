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

package com.weprode.nero.about.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;About_EntVersion&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see EntVersion
 * @generated
 */
public class EntVersionTable extends BaseTable<EntVersionTable> {

	public static final EntVersionTable INSTANCE = new EntVersionTable();

	public final Column<EntVersionTable, Long> entVersionId = createColumn(
		"entVersionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EntVersionTable, String> version = createColumn(
		"version", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntVersionTable, String> details = createColumn(
		"details", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntVersionTable, Date> versionDate = createColumn(
		"versionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EntVersionTable, Boolean> isLast = createColumn(
		"isLast", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private EntVersionTable() {
		super("About_EntVersion", EntVersionTable::new);
	}

}