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

/**
 * The table class for the &quot;About_UserReadVersionNote&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNote
 * @generated
 */
public class UserReadVersionNoteTable
	extends BaseTable<UserReadVersionNoteTable> {

	public static final UserReadVersionNoteTable INSTANCE =
		new UserReadVersionNoteTable();

	public final Column<UserReadVersionNoteTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserReadVersionNoteTable, Boolean>
		hasReadLastVersionNote = createColumn(
			"hasReadLastVersionNote", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private UserReadVersionNoteTable() {
		super("About_UserReadVersionNote", UserReadVersionNoteTable::new);
	}

}