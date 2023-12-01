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

package com.weprode.facile.authentication.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Authentication_LoginLock&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see LoginLock
 * @generated
 */
public class LoginLockTable extends BaseTable<LoginLockTable> {

	public static final LoginLockTable INSTANCE = new LoginLockTable();

	public final Column<LoginLockTable, String> login = createColumn(
		"login", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<LoginLockTable, Integer> failedLoginAttempts =
		createColumn(
			"failedLoginAttempts", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<LoginLockTable, Boolean> isLocked = createColumn(
		"isLocked", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<LoginLockTable, Date> lockEndDate = createColumn(
		"lockEndDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private LoginLockTable() {
		super("Authentication_LoginLock", LoginLockTable::new);
	}

}