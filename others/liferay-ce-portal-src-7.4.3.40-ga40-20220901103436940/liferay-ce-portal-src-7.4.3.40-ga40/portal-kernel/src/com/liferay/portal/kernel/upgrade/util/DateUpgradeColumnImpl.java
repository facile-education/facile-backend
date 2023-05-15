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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.petra.string.StringPool;

import java.sql.Types;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class DateUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public DateUpgradeColumnImpl(String name) {
		super(name, Types.TIMESTAMP);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		if (StringPool.NULL.equals(oldValue)) {
			return 0;
		}

		Date oldDate = (Date)oldValue;

		return oldDate.getTime();
	}

}