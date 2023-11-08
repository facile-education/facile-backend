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

package com.weprode.facile.group.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Group_GroupMembership&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembership
 * @generated
 */
public class GroupMembershipTable extends BaseTable<GroupMembershipTable> {

	public static final GroupMembershipTable INSTANCE =
		new GroupMembershipTable();

	public final Column<GroupMembershipTable, Long> membershipId = createColumn(
		"membershipId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<GroupMembershipTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<GroupMembershipTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<GroupMembershipTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<GroupMembershipTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<GroupMembershipTable, Boolean> fullYear = createColumn(
		"fullYear", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private GroupMembershipTable() {
		super("Group_GroupMembership", GroupMembershipTable::new);
	}

}