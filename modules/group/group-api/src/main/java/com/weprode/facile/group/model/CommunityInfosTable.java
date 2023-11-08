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
 * The table class for the &quot;Group_CommunityInfos&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfos
 * @generated
 */
public class CommunityInfosTable extends BaseTable<CommunityInfosTable> {

	public static final CommunityInfosTable INSTANCE =
		new CommunityInfosTable();

	public final Column<CommunityInfosTable, Long> communityInfosId =
		createColumn(
			"communityInfosId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CommunityInfosTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Long> creatorId = createColumn(
		"creatorId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Date> creationDate = createColumn(
		"creationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Date> expirationDate =
		createColumn(
			"expirationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Boolean> isPedagogical =
		createColumn(
			"isPedagogical", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, Boolean> isContactList =
		createColumn(
			"isContactList", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CommunityInfosTable, String> color = createColumn(
		"color", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private CommunityInfosTable() {
		super("Group_CommunityInfos", CommunityInfosTable::new);
	}

}