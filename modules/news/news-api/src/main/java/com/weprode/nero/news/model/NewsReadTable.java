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

package com.weprode.nero.news.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;News_NewsRead&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see NewsRead
 * @generated
 */
public class NewsReadTable extends BaseTable<NewsReadTable> {

	public static final NewsReadTable INSTANCE = new NewsReadTable();

	public final Column<NewsReadTable, Long> newsId = createColumn(
		"newsId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NewsReadTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NewsReadTable, Date> readDate = createColumn(
		"readDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private NewsReadTable() {
		super("News_NewsRead", NewsReadTable::new);
	}

}