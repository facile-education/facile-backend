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

package com.weprode.facile.user.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;User_UserContact&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserContact
 * @generated
 */
public class UserContactTable extends BaseTable<UserContactTable> {

	public static final UserContactTable INSTANCE = new UserContactTable();

	public final Column<UserContactTable, Long> contactId = createColumn(
		"contactId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserContactTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> middleNames = createColumn(
		"middleNames", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> birthName = createColumn(
		"birthName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> address = createColumn(
		"address", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, Boolean> isAddressAuthorized =
		createColumn(
			"isAddressAuthorized", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> mail = createColumn(
		"mail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, Boolean> isMailAuthorized =
		createColumn(
			"isMailAuthorized", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> mobilePhone = createColumn(
		"mobilePhone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> mobilePhoneSMS = createColumn(
		"mobilePhoneSMS", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> homePhone = createColumn(
		"homePhone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> proPhone = createColumn(
		"proPhone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserContactTable, String> familyLink = createColumn(
		"familyLink", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private UserContactTable() {
		super("User_UserContact", UserContactTable::new);
	}

}