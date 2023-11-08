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

package com.weprode.facile.preference.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Preference_NotifyConfig&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfig
 * @generated
 */
public class NotifyConfigTable extends BaseTable<NotifyConfigTable> {

	public static final NotifyConfigTable INSTANCE = new NotifyConfigTable();

	public final Column<NotifyConfigTable, Long> notifyConfigId = createColumn(
		"notifyConfigId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<NotifyConfigTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> activate = createColumn(
		"activate", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> notifyCasier = createColumn(
		"notifyCasier", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> notifyActu = createColumn(
		"notifyActu", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> notifyGrpDoc = createColumn(
		"notifyGrpDoc", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> notifyAgenda = createColumn(
		"notifyAgenda", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Boolean> notifySync = createColumn(
		"notifySync", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotifyConfigTable, Integer> digestPeriod = createColumn(
		"digestPeriod", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private NotifyConfigTable() {
		super("Preference_NotifyConfig", NotifyConfigTable::new);
	}

}