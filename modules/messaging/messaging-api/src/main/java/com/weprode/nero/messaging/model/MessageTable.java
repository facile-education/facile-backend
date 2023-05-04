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

package com.weprode.nero.messaging.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Messaging_Message&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageTable extends BaseTable<MessageTable> {

	public static final MessageTable INSTANCE = new MessageTable();

	public final Column<MessageTable, Long> messageId = createColumn(
		"messageId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MessageTable, Long> folderId = createColumn(
		"folderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Long> threadId = createColumn(
		"threadId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Long> sendMessageId = createColumn(
		"sendMessageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Long> senderId = createColumn(
		"senderId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Date> sendDate = createColumn(
		"sendDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MessageTable, String> senderName = createColumn(
		"senderName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessageTable, String> messageSubject = createColumn(
		"messageSubject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessageTable, String> messageContent = createColumn(
		"messageContent", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Boolean> isNew = createColumn(
		"isNew", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Date> readDate = createColumn(
		"readDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Boolean> isAnswered = createColumn(
		"isAnswered", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Boolean> isForwarded = createColumn(
		"isForwarded", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MessageTable, Integer> type = createColumn(
		"type_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private MessageTable() {
		super("Messaging_Message", MessageTable::new);
	}

}