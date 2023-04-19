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

package com.weprode.nero.schedule.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Schedule_SessionParentClass&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SessionParentClass
 * @generated
 */
public class SessionParentClassTable
	extends BaseTable<SessionParentClassTable> {

	public static final SessionParentClassTable INSTANCE =
		new SessionParentClassTable();

	public final Column<SessionParentClassTable, Long> sessionParentClassId =
		createColumn(
			"sessionParentClassId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<SessionParentClassTable, Long> sessionId = createColumn(
		"sessionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SessionParentClassTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private SessionParentClassTable() {
		super("Schedule_SessionParentClass", SessionParentClassTable::new);
	}

}