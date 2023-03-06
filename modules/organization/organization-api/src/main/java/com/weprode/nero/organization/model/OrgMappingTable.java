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
 * The table class for the &quot;Organization_OrgMapping&quot; database table.
 *
 * @author Marc Salvat
 * @see OrgMapping
 * @generated
 */
public class OrgMappingTable extends BaseTable<OrgMappingTable> {

	public static final OrgMappingTable INSTANCE = new OrgMappingTable();

	public final Column<OrgMappingTable, Long> organizationId = createColumn(
		"organizationId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<OrgMappingTable, String> entStructureUAI = createColumn(
		"entStructureUAI", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);

	private OrgMappingTable() {
		super("Organization_OrgMapping", OrgMappingTable::new);
	}

}