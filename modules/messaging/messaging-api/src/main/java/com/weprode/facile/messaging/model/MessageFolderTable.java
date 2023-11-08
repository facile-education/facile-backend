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

package com.weprode.facile.messaging.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Messaging_MessageFolder&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolder
 * @generated
 */
public class MessageFolderTable extends BaseTable<MessageFolderTable> {

	public static final MessageFolderTable INSTANCE = new MessageFolderTable();

	public final Column<MessageFolderTable, Long> folderId = createColumn(
		"folderId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MessageFolderTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MessageFolderTable, String> folderName = createColumn(
		"folderName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessageFolderTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MessageFolderTable, Long> parentFolderId = createColumn(
		"parentFolderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private MessageFolderTable() {
		super("Messaging_MessageFolder", MessageFolderTable::new);
	}

}