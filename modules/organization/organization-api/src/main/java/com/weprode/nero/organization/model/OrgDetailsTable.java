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

package com.weprode.nero.organization.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Organization_OrgDetails&quot; database table.
 *
 * @author Marc Salvat
 * @see OrgDetails
 * @generated
 */
public class OrgDetailsTable extends BaseTable<OrgDetailsTable> {

	public static final OrgDetailsTable INSTANCE = new OrgDetailsTable();

	public final Column<OrgDetailsTable, Long> orgId = createColumn(
		"orgId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<OrgDetailsTable, Long> schoolId = createColumn(
		"schoolId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<OrgDetailsTable, String> orgName = createColumn(
		"orgName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<OrgDetailsTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<OrgDetailsTable, Boolean> isArchive = createColumn(
		"isArchive", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private OrgDetailsTable() {
		super("Organization_OrgDetails", OrgDetailsTable::new);
	}

}