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

package com.weprode.nero.help.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Help_HelpCategory&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategory
 * @generated
 */
public class HelpCategoryTable extends BaseTable<HelpCategoryTable> {

	public static final HelpCategoryTable INSTANCE = new HelpCategoryTable();

	public final Column<HelpCategoryTable, Long> categoryId = createColumn(
		"categoryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HelpCategoryTable, String> categoryName = createColumn(
		"categoryName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpCategoryTable, Long> serviceId = createColumn(
		"serviceId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HelpCategoryTable, Integer> position = createColumn(
		"position", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private HelpCategoryTable() {
		super("Help_HelpCategory", HelpCategoryTable::new);
	}

}