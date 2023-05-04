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

/**
 * The table class for the &quot;About_EntVersionUser&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUser
 * @generated
 */
public class EntVersionUserTable extends BaseTable<EntVersionUserTable> {

	public static final EntVersionUserTable INSTANCE =
		new EntVersionUserTable();

	public final Column<EntVersionUserTable, Long> versionUserId = createColumn(
		"versionUserId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EntVersionUserTable, Long> entVersionId = createColumn(
		"entVersionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EntVersionUserTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private EntVersionUserTable() {
		super("About_EntVersionUser", EntVersionUserTable::new);
	}

}