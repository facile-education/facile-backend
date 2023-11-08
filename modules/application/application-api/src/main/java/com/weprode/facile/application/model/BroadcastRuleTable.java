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
 * The table class for the &quot;Application_BroadcastRule&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRule
 * @generated
 */
public class BroadcastRuleTable extends BaseTable<BroadcastRuleTable> {

	public static final BroadcastRuleTable INSTANCE = new BroadcastRuleTable();

	public final Column<BroadcastRuleTable, Long> broadcastRuleId =
		createColumn(
			"broadcastRuleId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<BroadcastRuleTable, Long> applicationId = createColumn(
		"applicationId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastRuleTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastRuleTable, Long> roleId = createColumn(
		"roleId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastRuleTable, Long> orgId = createColumn(
		"orgId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BroadcastRuleTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private BroadcastRuleTable() {
		super("Application_BroadcastRule", BroadcastRuleTable::new);
	}

}