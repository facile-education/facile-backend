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

/**
 * The table class for the &quot;News_NewsAttachedFile&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFile
 * @generated
 */
public class NewsAttachedFileTable extends BaseTable<NewsAttachedFileTable> {

	public static final NewsAttachedFileTable INSTANCE =
		new NewsAttachedFileTable();

	public final Column<NewsAttachedFileTable, Long> newsId = createColumn(
		"newsId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NewsAttachedFileTable, Long> fileId = createColumn(
		"fileId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NewsAttachedFileTable, String> fileName = createColumn(
		"fileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private NewsAttachedFileTable() {
		super("News_NewsAttachedFile", NewsAttachedFileTable::new);
	}

}