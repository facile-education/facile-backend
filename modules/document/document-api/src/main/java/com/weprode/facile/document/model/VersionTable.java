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

package com.weprode.facile.document.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Document_Version&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Version
 * @generated
 */
public class VersionTable extends BaseTable<VersionTable> {

	public static final VersionTable INSTANCE = new VersionTable();

	public final Column<VersionTable, Long> fileVersionId = createColumn(
		"fileVersionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<VersionTable, Long> dlFileEntryId = createColumn(
		"dlFileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<VersionTable, Double> versionNumber = createColumn(
		"versionNumber", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<VersionTable, String> comment = createColumn(
		"comment_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<VersionTable, Long> downloadCount = createColumn(
		"downloadCount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<VersionTable, Long> viewCount = createColumn(
		"viewCount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private VersionTable() {
		super("Document_Version", VersionTable::new);
	}

}