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

package com.weprode.nero.preference.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Preference_MobileNotification&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MobileNotification
 * @generated
 */
public class MobileNotificationTable
	extends BaseTable<MobileNotificationTable> {

	public static final MobileNotificationTable INSTANCE =
		new MobileNotificationTable();

	public final Column<MobileNotificationTable, Long> mobileNotificationId =
		createColumn(
			"mobileNotificationId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<MobileNotificationTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MobileNotificationTable, Long> etabId = createColumn(
		"etabId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MobileNotificationTable, Boolean> enable = createColumn(
		"enable", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MobileNotificationTable, String> token = createColumn(
		"token", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MobileNotificationTable, String> device = createColumn(
		"device", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MobileNotificationTable() {
		super("Preference_MobileNotification", MobileNotificationTable::new);
	}

}