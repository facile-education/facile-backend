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

package com.weprode.nero.search.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Search_SearchHistory&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SearchHistory
 * @generated
 */
public class SearchHistoryTable extends BaseTable<SearchHistoryTable> {

	public static final SearchHistoryTable INSTANCE = new SearchHistoryTable();

	public final Column<SearchHistoryTable, Long> searchHistoryId =
		createColumn(
			"searchHistoryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SearchHistoryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SearchHistoryTable, String> query = createColumn(
		"query", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SearchHistoryTable, Date> queryDate = createColumn(
		"queryDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private SearchHistoryTable() {
		super("Search_SearchHistory", SearchHistoryTable::new);
	}

}