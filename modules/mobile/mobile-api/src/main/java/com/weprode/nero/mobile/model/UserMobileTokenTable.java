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

package com.weprode.nero.mobile.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Mobile_UserMobileToken&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileToken
 * @generated
 */
public class UserMobileTokenTable extends BaseTable<UserMobileTokenTable> {

	public static final UserMobileTokenTable INSTANCE =
		new UserMobileTokenTable();

	public final Column<UserMobileTokenTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserMobileTokenTable, String> mobileToken =
		createColumn(
			"mobileToken", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private UserMobileTokenTable() {
		super("Mobile_UserMobileToken", UserMobileTokenTable::new);
	}

}