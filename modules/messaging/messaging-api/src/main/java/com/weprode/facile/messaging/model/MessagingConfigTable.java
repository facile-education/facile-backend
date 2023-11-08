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
 * The table class for the &quot;Messaging_MessagingConfig&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfig
 * @generated
 */
public class MessagingConfigTable extends BaseTable<MessagingConfigTable> {

	public static final MessagingConfigTable INSTANCE =
		new MessagingConfigTable();

	public final Column<MessagingConfigTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MessagingConfigTable, Boolean> isForwardActive =
		createColumn(
			"isForwardActive", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<MessagingConfigTable, String> forwardMail =
		createColumn(
			"forwardMail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessagingConfigTable, Boolean> isSignatureActive =
		createColumn(
			"isSignatureActive", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<MessagingConfigTable, String> signature = createColumn(
		"signature", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MessagingConfigTable, Boolean> isAutoReplyActive =
		createColumn(
			"isAutoReplyActive", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<MessagingConfigTable, String> autoReplyContent =
		createColumn(
			"autoReplyContent", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);

	private MessagingConfigTable() {
		super("Messaging_MessagingConfig", MessagingConfigTable::new);
	}

}