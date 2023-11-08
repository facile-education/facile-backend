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

package com.weprode.facile.application.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Application_Application&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Application
 * @generated
 */
public class ApplicationTable extends BaseTable<ApplicationTable> {

	public static final ApplicationTable INSTANCE = new ApplicationTable();

	public final Column<ApplicationTable, Long> applicationId = createColumn(
		"applicationId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ApplicationTable, String> applicationName =
		createColumn(
			"applicationName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, String> applicationKey = createColumn(
		"applicationKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, String> categoryName = createColumn(
		"categoryName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, String> image = createColumn(
		"image", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> hasCustomUrl = createColumn(
		"hasCustomUrl", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> hasGlobalUrl = createColumn(
		"hasGlobalUrl", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, String> globalUrl = createColumn(
		"globalUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> exportUser = createColumn(
		"exportUser", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> exportParent = createColumn(
		"exportParent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> exportStudent = createColumn(
		"exportStudent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> exportTeacher = createColumn(
		"exportTeacher", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Boolean> exportOther = createColumn(
		"exportOther", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationTable, Long> menuEntryId = createColumn(
		"menuEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ApplicationTable() {
		super("Application_Application", ApplicationTable::new);
	}

}