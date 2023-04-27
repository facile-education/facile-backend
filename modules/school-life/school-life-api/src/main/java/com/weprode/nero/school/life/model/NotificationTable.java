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

package com.weprode.nero.school.life.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Schoollife_Notification&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Notification
 * @generated
 */
public class NotificationTable extends BaseTable<NotificationTable> {

	public static final NotificationTable INSTANCE = new NotificationTable();

	public final Column<NotificationTable, Long> schoollifeSessionId =
		createColumn(
			"schoollifeSessionId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<NotificationTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

	private NotificationTable() {
		super("Schoollife_Notification", NotificationTable::new);
	}

}