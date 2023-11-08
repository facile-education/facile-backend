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

package com.weprode.facile.statistic.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Statistics_UserLogin&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserLogin
 * @generated
 */
public class UserLoginTable extends BaseTable<UserLoginTable> {

	public static final UserLoginTable INSTANCE = new UserLoginTable();

	public final Column<UserLoginTable, Long> userLoginId = createColumn(
		"userLoginId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserLoginTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserLoginTable, Date> loginDate = createColumn(
		"loginDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserLoginTable, Integer> role = createColumn(
		"role_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<UserLoginTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserLoginTable, Boolean> isMobileApp = createColumn(
		"isMobileApp", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private UserLoginTable() {
		super("Statistics_UserLogin", UserLoginTable::new);
	}

}