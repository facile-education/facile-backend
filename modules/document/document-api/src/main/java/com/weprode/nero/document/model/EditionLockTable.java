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
 * The table class for the &quot;Document_EditionLock&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see EditionLock
 * @generated
 */
public class EditionLockTable extends BaseTable<EditionLockTable> {

	public static final EditionLockTable INSTANCE = new EditionLockTable();

	public final Column<EditionLockTable, Long> fileId = createColumn(
		"fileId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EditionLockTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EditionLockTable, Date> editionDate = createColumn(
		"editionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private EditionLockTable() {
		super("Document_EditionLock", EditionLockTable::new);
	}

}