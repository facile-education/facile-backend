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

package com.weprode.nero.access.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Access_AccessProfile&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfile
 * @generated
 */
public class AccessProfileTable extends BaseTable<AccessProfileTable> {

	public static final AccessProfileTable INSTANCE = new AccessProfileTable();

	public final Column<AccessProfileTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccessProfileTable, Long> accessId = createColumn(
		"accessId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AccessProfileTable, Long> roleId = createColumn(
		"roleId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

	private AccessProfileTable() {
		super("Access_AccessProfile", AccessProfileTable::new);
	}

}