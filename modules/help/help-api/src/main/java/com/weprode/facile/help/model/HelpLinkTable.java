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

package com.weprode.facile.help.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Help_HelpLink&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see HelpLink
 * @generated
 */
public class HelpLinkTable extends BaseTable<HelpLinkTable> {

	public static final HelpLinkTable INSTANCE = new HelpLinkTable();

	public final Column<HelpLinkTable, Long> linkId = createColumn(
		"linkId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HelpLinkTable, Long> itemId = createColumn(
		"itemId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HelpLinkTable, String> linkName = createColumn(
		"linkName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HelpLinkTable, String> linkUrl = createColumn(
		"linkUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private HelpLinkTable() {
		super("Help_HelpLink", HelpLinkTable::new);
	}

}