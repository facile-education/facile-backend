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
 * The table class for the &quot;Application_Broadcast&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Broadcast
 * @generated
 */
public class BroadcastTable extends BaseTable<BroadcastTable> {

	public static final BroadcastTable INSTANCE = new BroadcastTable();

	public final Column<BroadcastTable, Long> broadcastId = createColumn(
		"broadcastId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<BroadcastTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastTable, Long> applicationId = createColumn(
		"applicationId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastTable, Boolean> isBroadcasted = createColumn(
		"isBroadcasted", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<BroadcastTable, String> applicationUrl = createColumn(
		"applicationUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private BroadcastTable() {
		super("Application_Broadcast", BroadcastTable::new);
	}

}