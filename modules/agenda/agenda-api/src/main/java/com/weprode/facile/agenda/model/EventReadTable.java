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

package com.weprode.facile.agenda.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Agenda_EventRead&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see EventRead
 * @generated
 */
public class EventReadTable extends BaseTable<EventReadTable> {

	public static final EventReadTable INSTANCE = new EventReadTable();

	public final Column<EventReadTable, Long> eventId = createColumn(
		"eventId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EventReadTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EventReadTable, Date> readDate = createColumn(
		"readDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private EventReadTable() {
		super("Agenda_EventRead", EventReadTable::new);
	}

}