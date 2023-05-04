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

/**
 * The table class for the &quot;Messaging_MessageRecipients&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipients
 * @generated
 */
public class MessageRecipientsTable extends BaseTable<MessageRecipientsTable> {

	public static final MessageRecipientsTable INSTANCE =
		new MessageRecipientsTable();

	public final Column<MessageRecipientsTable, Long> messageId = createColumn(
		"messageId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MessageRecipientsTable, String> recipients =
		createColumn(
			"recipients", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MessageRecipientsTable() {
		super("Messaging_MessageRecipients", MessageRecipientsTable::new);
	}

}