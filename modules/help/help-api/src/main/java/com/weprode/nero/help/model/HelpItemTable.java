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

package com.weprode.nero.help.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Help_HelpItem&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see HelpItem
 * @generated
 */
public class HelpItemTable extends BaseTable<HelpItemTable> {

	public static final HelpItemTable INSTANCE = new HelpItemTable();

	public final Column<HelpItemTable, Long> itemId = createColumn(
		"itemId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HelpItemTable, Long> categoryId = createColumn(
		"categoryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, String> itemName = createColumn(
		"itemName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, String> videoURL = createColumn(
		"videoURL", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, String> videoDescription = createColumn(
		"videoDescription", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, String> manual = createColumn(
		"manual", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, Integer> position = createColumn(
		"position", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, String> language = createColumn(
		"language", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpItemTable, Boolean> isManagement = createColumn(
		"isManagement", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private HelpItemTable() {
		super("Help_HelpItem", HelpItemTable::new);
	}

}