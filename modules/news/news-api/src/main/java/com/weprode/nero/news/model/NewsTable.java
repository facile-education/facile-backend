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
 * The table class for the &quot;News_News&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see News
 * @generated
 */
public class NewsTable extends BaseTable<NewsTable> {

	public static final NewsTable INSTANCE = new NewsTable();

	public final Column<NewsTable, Long> newsId = createColumn(
		"newsId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NewsTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NewsTable, String> content = createColumn(
		"content", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Long> authorId = createColumn(
		"authorId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Boolean> isSchoolNews = createColumn(
		"isSchoolNews", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Boolean> isImportant = createColumn(
		"isImportant", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Date> expirationDate = createColumn(
		"expirationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Date> publicationDate = createColumn(
		"publicationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Date> modificationDate = createColumn(
		"modificationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<NewsTable, Long> imageId = createColumn(
		"imageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private NewsTable() {
		super("News_News", NewsTable::new);
	}

}