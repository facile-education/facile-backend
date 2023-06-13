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

package com.weprode.nero.course.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Course_SessionContent&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContent
 * @generated
 */
public class SessionContentTable extends BaseTable<SessionContentTable> {

	public static final SessionContentTable INSTANCE =
		new SessionContentTable();

	public final Column<SessionContentTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SessionContentTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionContentTable, Long> teacherId = createColumn(
		"teacherId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionContentTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SessionContentTable, Date> modificationDate =
		createColumn(
			"modificationDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<SessionContentTable, Date> publicationDate =
		createColumn(
			"publicationDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<SessionContentTable, Boolean> isDraft = createColumn(
		"isDraft", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private SessionContentTable() {
		super("Course_SessionContent", SessionContentTable::new);
	}

}