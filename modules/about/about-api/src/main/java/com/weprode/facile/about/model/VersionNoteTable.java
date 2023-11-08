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

package com.weprode.facile.about.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;About_VersionNote&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see VersionNote
 * @generated
 */
public class VersionNoteTable extends BaseTable<VersionNoteTable> {

	public static final VersionNoteTable INSTANCE = new VersionNoteTable();

	public final Column<VersionNoteTable, Long> versionNoteId = createColumn(
		"versionNoteId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<VersionNoteTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<VersionNoteTable, String> content = createColumn(
		"content", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<VersionNoteTable, Date> versionNoteDate = createColumn(
		"versionNoteDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private VersionNoteTable() {
		super("About_VersionNote", VersionNoteTable::new);
	}

}