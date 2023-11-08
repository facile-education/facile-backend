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

package com.weprode.facile.organization.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Organization_OrgCiteScolaire&quot; database table.
 *
 * @author Marc Salvat
 * @see OrgCiteScolaire
 * @generated
 */
public class OrgCiteScolaireTable extends BaseTable<OrgCiteScolaireTable> {

	public static final OrgCiteScolaireTable INSTANCE =
		new OrgCiteScolaireTable();

	public final Column<OrgCiteScolaireTable, String> parentENTStructureUAI =
		createColumn(
			"parentENTStructureUAI", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<OrgCiteScolaireTable, String> childENTStructureUAI =
		createColumn(
			"childENTStructureUAI", String.class, Types.VARCHAR,
			Column.FLAG_PRIMARY);

	private OrgCiteScolaireTable() {
		super("Organization_OrgCiteScolaire", OrgCiteScolaireTable::new);
	}

}