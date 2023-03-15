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

package com.weprode.nero.preference.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Preference_UserProperties&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserProperties
 * @generated
 */
public class UserPropertiesTable extends BaseTable<UserPropertiesTable> {

	public static final UserPropertiesTable INSTANCE =
		new UserPropertiesTable();

	public final Column<UserPropertiesTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserPropertiesTable, Boolean> manualAccount =
		createColumn(
			"manualAccount", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Boolean> hideMenu = createColumn(
		"hideMenu", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, String> themeColor = createColumn(
		"themeColor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Long> etabId = createColumn(
		"etabId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Long> preferedSchoolId =
		createColumn(
			"preferedSchoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Boolean> webdavActivated =
		createColumn(
			"webdavActivated", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Date> termsOfUseAgreedDate =
		createColumn(
			"termsOfUseAgreedDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<UserPropertiesTable, Date> lastSynchroDate =
		createColumn(
			"lastSynchroDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);

	private UserPropertiesTable() {
		super("Preference_UserProperties", UserPropertiesTable::new);
	}

}