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

package com.weprode.nero.eel.synchronization.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;EELSynchro_ParentSynchro&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ParentSynchro
 * @generated
 */
public class ParentSynchroTable extends BaseTable<ParentSynchroTable> {

	public static final ParentSynchroTable INSTANCE = new ParentSynchroTable();

	public final Column<ParentSynchroTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ParentSynchroTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ParentSynchroTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ParentSynchroTable, String> fileName = createColumn(
		"fileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ParentSynchroTable, Long> lineCount = createColumn(
		"lineCount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ParentSynchroTable, Long> errorCount = createColumn(
		"errorCount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ParentSynchroTable() {
		super("EELSynchro_ParentSynchro", ParentSynchroTable::new);
	}

}