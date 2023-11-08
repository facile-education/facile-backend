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

package com.weprode.facile.user.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;User_Affectation&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Affectation
 * @generated
 */
public class AffectationTable extends BaseTable<AffectationTable> {

	public static final AffectationTable INSTANCE = new AffectationTable();

	public final Column<AffectationTable, Long> affectationId = createColumn(
		"affectationId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AffectationTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Long> orgId = createColumn(
		"orgId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Long> adminUserId = createColumn(
		"adminUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Date> affectationDate = createColumn(
		"affectationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AffectationTable, Date> expirationDate = createColumn(
		"expirationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private AffectationTable() {
		super("User_Affectation", AffectationTable::new);
	}

}