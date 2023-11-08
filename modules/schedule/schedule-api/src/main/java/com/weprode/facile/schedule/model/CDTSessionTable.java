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

package com.weprode.facile.schedule.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Schedule_CDTSession&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSession
 * @generated
 */
public class CDTSessionTable extends BaseTable<CDTSessionTable> {

	public static final CDTSessionTable INSTANCE = new CDTSessionTable();

	public final Column<CDTSessionTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CDTSessionTable, Date> start = createColumn(
		"start_", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, Date> end = createColumn(
		"end_", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, Integer> slot = createColumn(
		"slot", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, String> fullCoursName = createColumn(
		"fullCoursName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, String> room = createColumn(
		"room", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, String> subject = createColumn(
		"subject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CDTSessionTable, Boolean> isManual = createColumn(
		"isManual", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private CDTSessionTable() {
		super("Schedule_CDTSession", CDTSessionTable::new);
	}

}