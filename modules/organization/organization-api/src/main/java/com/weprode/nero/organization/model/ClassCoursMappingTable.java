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
 * The table class for the &quot;Organization_ClassCoursMapping&quot; database table.
 *
 * @author Marc Salvat
 * @see ClassCoursMapping
 * @generated
 */
public class ClassCoursMappingTable extends BaseTable<ClassCoursMappingTable> {

	public static final ClassCoursMappingTable INSTANCE =
		new ClassCoursMappingTable();

	public final Column<ClassCoursMappingTable, Long> mappingId = createColumn(
		"mappingId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ClassCoursMappingTable, Long> classOrgId = createColumn(
		"classOrgId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ClassCoursMappingTable, Long> coursOrgId = createColumn(
		"coursOrgId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ClassCoursMappingTable() {
		super("Organization_ClassCoursMapping", ClassCoursMappingTable::new);
	}

}