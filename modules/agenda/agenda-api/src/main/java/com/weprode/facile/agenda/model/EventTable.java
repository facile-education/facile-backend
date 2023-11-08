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
 * The table class for the &quot;Agenda_Event&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
public class EventTable extends BaseTable<EventTable> {

	public static final EventTable INSTANCE = new EventTable();

	public final Column<EventTable, Long> eventId = createColumn(
		"eventId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EventTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EventTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EventTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventTable, String> location = createColumn(
		"location", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventTable, Long> authorId = createColumn(
		"authorId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private EventTable() {
		super("Agenda_Event", EventTable::new);
	}

}