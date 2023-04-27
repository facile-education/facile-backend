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

package com.weprode.nero.statistic.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Statistics_LoolStat&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see LoolStat
 * @generated
 */
public class LoolStatTable extends BaseTable<LoolStatTable> {

	public static final LoolStatTable INSTANCE = new LoolStatTable();

	public final Column<LoolStatTable, Long> statId = createColumn(
		"statId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<LoolStatTable, Long> objectId = createColumn(
		"objectId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LoolStatTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LoolStatTable, Boolean> saveAction = createColumn(
		"saveAction", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<LoolStatTable, Date> actionDate = createColumn(
		"actionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<LoolStatTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private LoolStatTable() {
		super("Statistics_LoolStat", LoolStatTable::new);
	}

}