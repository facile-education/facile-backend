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
 * The table class for the &quot;Group_MembershipActivity&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivity
 * @generated
 */
public class MembershipActivityTable
	extends BaseTable<MembershipActivityTable> {

	public static final MembershipActivityTable INSTANCE =
		new MembershipActivityTable();

	public final Column<MembershipActivityTable, Long> membershipActivityId =
		createColumn(
			"membershipActivityId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<MembershipActivityTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MembershipActivityTable, Long> actionUserId =
		createColumn(
			"actionUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MembershipActivityTable, String> targetUserIds =
		createColumn(
			"targetUserIds", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MembershipActivityTable, Boolean> incoming =
		createColumn(
			"incoming", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MembershipActivityTable, Date> movementDate =
		createColumn(
			"movementDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private MembershipActivityTable() {
		super("Group_MembershipActivity", MembershipActivityTable::new);
	}

}