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
 * The table class for the &quot;Preference_MobileDevice&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MobileDevice
 * @generated
 */
public class MobileDeviceTable extends BaseTable<MobileDeviceTable> {

	public static final MobileDeviceTable INSTANCE = new MobileDeviceTable();

	public final Column<MobileDeviceTable, Long> mobileDeviceId = createColumn(
		"mobileDeviceId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<MobileDeviceTable, String> manufaturerDeviceId =
		createColumn(
			"manufaturerDeviceId", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, String> deviceModel = createColumn(
		"deviceModel", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, String> manufacturer = createColumn(
		"manufacturer", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, String> operatingSystem =
		createColumn(
			"operatingSystem", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, String> operatingSystemVersion =
		createColumn(
			"operatingSystemVersion", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<MobileDeviceTable, String> browserUA = createColumn(
		"browserUA", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MobileDeviceTable() {
		super("Preference_MobileDevice", MobileDeviceTable::new);
	}

}